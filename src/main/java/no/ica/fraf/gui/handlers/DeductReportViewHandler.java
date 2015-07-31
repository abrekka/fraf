package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DeductDetailsViewHandler.DeductExcelTableModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.TotalAvregningV;
import no.ica.fraf.service.AvdelingAvregningManager;
import no.ica.fraf.service.TotalAvregningVManager;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av rapport
 * 
 * @author abr99
 * 
 */
public class DeductReportViewHandler implements Closeable {
	/**
	 * 
	 */
	private ArrayListModel reportList;

	/**
	 * 
	 */
	SelectionInList reportSelectionList;

	/**
	 * 
	 */
	private TotalAvregningVManager totalAvregningVManager;

	/**
	 * 
	 */
	JButton buttonExcelDep;

	/**
	 * 
	 */
	JXTable table;

	/**
	 * 
	 */
	AvdelingAvregningManager avdelingAvregningManager;

	/**
	 * 
	 */
	String directory;

	/**
	 * 
	 */
	ApplUser applUser;

	/**
	 * @param bunt
	 * @param aTotalAvregningVManager
	 * @param aAvdelingAvregningManager
	 * @param excelDir
	 * @param aApplUser
	 */
	public DeductReportViewHandler(Bunt bunt,
			TotalAvregningVManager aTotalAvregningVManager,
			AvdelingAvregningManager aAvdelingAvregningManager,
			String excelDir, ApplUser aApplUser) {
		applUser = aApplUser;
		directory = excelDir;
		avdelingAvregningManager = aAvdelingAvregningManager;
		totalAvregningVManager = aTotalAvregningVManager;
		reportList = new ArrayListModel(totalAvregningVManager
				.findByBuntId(bunt.getBuntId()));
		reportSelectionList = new SelectionInList((ListModel) reportList);
		reportSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new SelectionEmptyHandler());
	}

	/**
	 * Lager avbrytknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	/**
	 * Lager knapp for eksport til excel
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonExcel(WindowInterface window) {
		buttonExcelDep = new JButton(new ExcelAction(window));
		buttonExcelDep.setEnabled(false);
		return buttonExcelDep;
	}

	/**
	 * Lager tabell for rapport
	 * 
	 * @return tabell
	 */
	public JXTable getTableReport() {
		table = new JXTable();

		table.setModel(new ReportTableModel(reportSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				reportSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);

		table.setDragEnabled(false);

		table.setShowGrid(true);
		table.packAll();

		return table;

	}

	/**
	 * Tabellmodlell for rapport
	 * 
	 * @author abr99
	 * 
	 */
	private static final class ReportTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Avdnr", "Avregning" };

		/**
		 * @param listModel
		 */
		public ReportTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			TotalAvregningV totalAvregningV = (TotalAvregningV) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return totalAvregningV.getTotalAvregningVPK().getAvdnr();
			case 1:
				return totalAvregningV.getSumAvregning();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Eksport til excel
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public ExcelAction(WindowInterface aWindow) {
			super("Excel for avdeling");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.setWaitCursor(window);
			TotalAvregningV totalAvregningV = (TotalAvregningV) reportSelectionList
					.getElementAt(table
							.convertRowIndexToModel(reportSelectionList
									.getSelectionIndex()));
			if (totalAvregningV != null) {
				ArrayListModel deductListAll = new ArrayListModel(
						avdelingAvregningManager.findByAvdnr(totalAvregningV
								.getTotalAvregningVPK().getAvdnr(),totalAvregningV.getAar()));

				DeductExcelTableModel tableModel = new DeductExcelTableModel(
						deductListAll);
				ExcelUtil excelUtil = new ExcelUtil(tableModel,
						"Avregningssrapport for "
								+ totalAvregningV.getTotalAvregningVPK()
										.getAvdnr(), "Avregningsrapport");
				ArrayList<Integer> numCols = new ArrayList<Integer>();
				numCols.add(1);
				numCols.add(2);
				numCols.add(3);
				try {
					excelUtil.showDataInExcel(directory + "/"
							+ applUser.getUserName() + "/", "Avregning.xls",
							numCols, numCols, (JLabel) null);

					JOptionPane.showMessageDialog(window.getComponent(),
							"Dersom ikke excel starter automatisk, ligger excelfil med navn Avregning.xls"
									+ " under katalog " + directory + "/"
									+ applUser.getUserName() + "/");
				} catch (FrafException e) {
					e.printStackTrace();
					GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", e
							.getMessage());
				}
			}
			GuiUtil.setDefaultCursor(window);
		}
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Håndterer selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class SelectionEmptyHandler implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonExcelDep.setEnabled(reportSelectionList.hasSelection());

		}

	}
}
