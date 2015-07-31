package no.ica.tollpost.gui.handlers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.tollpost.model.TgTotalFaktureringV;
import no.ica.tollpost.service.TgTotalFaktureringVManager;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.ArrayListModel;

public class TgTotalReportViewHandler implements Closeable {
	private Bunt bunt;

	private TgTotalFaktureringVManager tgTotalFaktureringVManager;

	private ArrayListModel reportList;

	private TableModel tableModel;
	private String dir;
	private ApplUser applUser;

	public TgTotalReportViewHandler(Bunt aBunt,
			TgTotalFaktureringVManager aTgTotalFaktureringVManager,String exceDir,ApplUser aApplUser) {
		applUser=aApplUser;
		dir =exceDir;
		tgTotalFaktureringVManager = aTgTotalFaktureringVManager;
		bunt = aBunt;
		reportList = new ArrayListModel(tgTotalFaktureringVManager
				.findByBuntId(bunt.getBuntId()));
	}

	public JXTable getTableReport() {
		JXTable table = new JXTable();

		tableModel = new TotalReportTableModel(reportList);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setColumnControlVisible(true);

		table.packAll();
		return table;
	}

	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	public JButton getButtonExcel(WindowInterface window) {
		JButton button = new JButton(new ExcelAction(dir,applUser,window));;
		button.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return button;
	}

	private static final class TotalReportTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Buntid", "Løpenr", "Total beløp" };

		/**
		 * @param listModel
		 */
		public TotalReportTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * Henter verdi
		 * 
		 * @param rowIndex
		 * @param columnIndex
		 * @return verdi
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			TgTotalFaktureringV tgTotalFaktureringV = (TgTotalFaktureringV) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return tgTotalFaktureringV.getTgTotalFaktureringVPK()
						.getBuntId();
			case 1:
				return tgTotalFaktureringV.getTgTotalFaktureringVPK()
						.getLopenrFil();
			case 2:
				return tgTotalFaktureringV.getTotalBelop();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	public boolean canClose(String actionString) {
		return true;
	}

	private class ExcelAction extends AbstractAction {
		private String directory;

		private ApplUser applUser;
		private WindowInterface window;

		public ExcelAction(String dir, ApplUser aApplUser,WindowInterface aWindow) {
			super("Excel");
			directory = dir;
			applUser = aApplUser;
			window=aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			ExcelUtil excelUtil = new ExcelUtil(tableModel, "Totalfakturering - "+bunt.getFileName(),
					"Totalfakturering");
			try {
				List<Integer> numCols = new ArrayList<Integer>();
				numCols.add(0);
				numCols.add(2);
				
				List<Integer> sumCols = new ArrayList<Integer>();
				
				sumCols.add(2);
				excelUtil.showDataInExcel(directory + "/"
						+ applUser.getUserName() + "/", "Tollpost_Totalfakturering.xls",
						sumCols, numCols,
						(JLabel) null);
				GuiUtil.showMsgDialog(window.getComponent(), "Excefil",
						"Dersom ikke excel starter automatisk, ligger excelfil med navn Tollpost_Totalfakturering.xls"
								+ " under katalog " + directory + "/"
								+ applUser.getUserName() + "/");
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", e
						.getMessage());
			}

		}
	}
}
