package no.ica.tollpost.gui.handlers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.util.GuiUtil;
import no.ica.tollpost.gui.ExcelGenerator;
import no.ica.tollpost.gui.TgImportTypeEnum;
import no.ica.tollpost.model.TgImport;
import no.ica.tollpost.service.TgImportManager;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.PatternHighlighter;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

public class CommissionViewHandler {
	private final ArrayListModel commissionList;
	private SelectionInList batchSelectionList;
	private TgImportManager tgImportManager;
	private JXTable table;
	private TableModel tableModel;
	private String excelDir;

	private ApplUser applUser;

	private final SelectionInList commissionSelectionList;
	public CommissionViewHandler(SelectionInList aBatchSelectionList,
			TgImportManager aTgImportManager, String dir, ApplUser aApplUser){
		excelDir = dir;
		applUser = aApplUser;
		tgImportManager = aTgImportManager;
		batchSelectionList = aBatchSelectionList;
		commissionList = new ArrayListModel();
		commissionSelectionList=new SelectionInList((ListModel)commissionList);
	}
	
	public JButton getButtonExcel(WindowInterface window) {
		JButton button = new JButton(
				new ExcelAction(window, excelDir, applUser));
		button.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return button;
	}

	public JXTable getTableCommission() {
		table = new JXTable();

		tableModel=new CommissionTableModel(commissionSelectionList);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				commissionSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		// tableOrders.addMouseListener(new OrderDoubleClickHandler(window));

		table.setDragEnabled(false);
		
		HighlighterPipeline highlighters = new HighlighterPipeline();

		PatternHighlighter pattern = new PatternHighlighter(Color.WHITE,
				Color.LIGHT_GRAY, "\\d", Pattern.CASE_INSENSITIVE, 15);
		highlighters.addHighlighter(pattern);
		table.setHighlighters(highlighters);

		table.setShowGrid(true);
		table.packAll();
		commissionSelectionList.clearSelection();
		return table;
	}

	private static final class CommissionTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "LinjeId", "MeldingId", "Avdnr",
				"Transdato","Transtype", "Antall", "Sendingsnr", "KolliId", "Beløp",
				"Meldingstype", "Reg", "Transfilid", "Bunt", "Avtaletype","Dataset","Fakturanr" };

		private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMdd");

		/**
		 * @param listModel
		 */
		public CommissionTableModel(ListModel listModel) {
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

			TgImport tgImport = (TgImport) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return tgImport.getLinjeId();
			case 1:
				return tgImport.getMeldingId();
			case 2:
				return tgImport.getAvdnr();
			case 3:
				return tgImport.getTransDato();
			case 4:
				return tgImport.getTransType();
			case 5:
				return tgImport.getAntTrans();
			case 6:
				return tgImport.getSendingsnr();
			case 7:
				return tgImport.getKolliId();
			case 8:
				return tgImport.getBelop();
			case 9:
				return tgImport.getMeldingstype();
			case 10:
				return tgImport.getDtReg();
			case 11:
				return tgImport.getTransfilId();
			case 12:
				return tgImport.getBunt();
			case 13:
				return tgImport.getAvtaletype();
			case 14:
				return tgImport.getDataset();
			case 15:
				if(tgImport.getTgFaktura()!=null){
				return tgImport.getTgFaktura().getFakturaNr();
				}
				return null;
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

	}


	public ComponentListener getClaimComponentListener(WindowInterface window) {
		return new CommissionComponentListener(window);
	}

	private class CommissionComponentListener extends ComponentAdapter {
		private Bunt lastBunt;

		private WindowInterface window;

		public CommissionComponentListener(WindowInterface aWindow) {
			window = aWindow;
		}

		@Override
		public void componentShown(ComponentEvent evt) {
			Bunt bunt = (Bunt) batchSelectionList.getSelection();
			if (lastBunt == null || !lastBunt.equals(bunt)) {
				lastBunt = bunt;
				if (bunt != null) {
					GuiUtil.runInThreadWheel(window.getRootPane(),
							new CommissionLoader(bunt), null);
				}
			}
		}
	}

	private class CommissionLoader implements Threadable {
		private Bunt bunt;

		public CommissionLoader(Bunt aBunt) {
			bunt = aBunt;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Laster provisjon...");

			// batchManager.lazyLoadBatch(batchModel.getBatch(),new
			// LazyLoadBatchEnum[] { LazyLoadBatchEnum.INVOICES });
			commissionList.clear();

			List<TgImport> tgImports = tgImportManager.findByBuntAndType(bunt,TgImportTypeEnum.COMMISSION);

			if (tgImports != null) {
				commissionList.addAll(tgImports);
			}

			table.packAll();
			return null;
		}

		public void doWhenFinished(Object object) {
			// TODO Auto-generated method stub

		}

	}

	private class ExcelAction extends AbstractAction {
		private WindowInterface window;

		private String directory;

		private ApplUser applUser;

		public ExcelAction(WindowInterface aWindow, String dir,
				ApplUser aApplUser) {
			super("Excel");
			window = aWindow;
			directory = dir;
			applUser = aApplUser;
		}

		public void actionPerformed(ActionEvent arg0) {
			List<Integer> sumCols=new ArrayList<Integer>();
			sumCols.add(8);
			List<Integer> numCols=new ArrayList<Integer>();
			numCols.add(0);
			numCols.add(1);
			numCols.add(2);
			numCols.add(5);
			numCols.add(8);
			//numCols.add(12);
			GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelGenerator(
					window, tableModel, directory, applUser,"Tollpost_provisjon.xls","Provisjon",sumCols,numCols), null);
		}
	}

}
