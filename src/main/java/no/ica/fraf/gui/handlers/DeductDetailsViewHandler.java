package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvdelingAvregning;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.service.AvdelingAvregningImportManager;
import no.ica.fraf.service.AvdelingAvregningManager;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av avregningsdetaljer
 * 
 * @author abr99
 * 
 */
public class DeductDetailsViewHandler {
	/**
	 * 
	 */
	private JXTable table;

	/**
	 * 
	 */
	private ArrayListModel deductList;

	/**
	 * 
	 */
	private SelectionInList deductSelectionList;

	/**
	 * 
	 */
	DeductImportViewHandler deductImportViewHandler;

	/**
	 * 
	 */
	AvdelingAvregningBelop lastAvregningBelop;

	/**
	 * 
	 */
	AvdelingAvregningManager avdelingAvregningManager;

	/**
	 * 
	 */
	AvdelingAvregningImportManager avdelingAvregningImportManager;

	/**
	 * 
	 */
	private String excelDir;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * @param aDeductImportViewHandler
	 * @param aAvdelingAvregningManager
	 * @param aExcelDir
	 * @param aApplUser
	 * @param aAvdelingAvregningImportManager
	 */
	public DeductDetailsViewHandler(
			DeductImportViewHandler aDeductImportViewHandler,
			AvdelingAvregningManager aAvdelingAvregningManager,
			String aExcelDir, ApplUser aApplUser,
			AvdelingAvregningImportManager aAvdelingAvregningImportManager) {
		avdelingAvregningImportManager = aAvdelingAvregningImportManager;
		applUser = aApplUser;
		excelDir = aExcelDir;
		avdelingAvregningManager = aAvdelingAvregningManager;
		deductImportViewHandler = aDeductImportViewHandler;

		deductList = new ArrayListModel();
		deductSelectionList = new SelectionInList((ListModel) deductList);
	}

	/**
	 * Lager tabell for avregning
	 * 
	 * @return tabell
	 */
	public JXTable getTableDeduct() {
		table = new JXTable();

		table.setModel(new DeductTableModel(deductSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				deductSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);

		table.setDragEnabled(false);

		table.setShowGrid(true);
		table.setSortable(true);
		deductSelectionList.clearSelection();

		// Id
		table.getColumnExt(0).setPreferredWidth(50);
		// Opprettet
		table.getColumnExt(1).setPreferredWidth(100);
		// Opprettet av
		table.getColumnExt(2).setPreferredWidth(100);

		return table;

	}

	/**
	 * Tabellmodell for avregning
	 * 
	 * @author abr99
	 * 
	 */
	private static final class DeductTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Betingelse", "Avregnet avgift",
				"Fakturert avgift", "Avregning" };

		/**
		 * @param listModel
		 */
		public DeductTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			AvdelingAvregning avdelingAvregning = (AvdelingAvregning) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return avdelingAvregning.getAvdelingBetingelse();
			case 1:
				return avdelingAvregning.getAvregnetAvgift();
			case 2:
				return avdelingAvregning.getFakturertAvgift();
			case 3:
				return avdelingAvregning.getAvregning();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Henter komponentlytter
	 * 
	 * @return komponentlytter
	 */
	public ComponentListener getDeductComponentListener() {
		return new DeductComponentListener();
	}

	/**
	 * Komponentlytter. Laster data når komponent blir vist
	 * 
	 * @author abr99
	 * 
	 */
	private class DeductComponentListener extends ComponentAdapter {


		/**
		 * 
		 */
		public DeductComponentListener() {
		}

		/**
		 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
		 */
		@Override
		public void componentShown(ComponentEvent evt) {
			AvdelingAvregningBelop avregningBelop = deductImportViewHandler
					.getSelectedImport();
			if (avregningBelop != null) {
				avdelingAvregningImportManager.lazyLoadBunt(avregningBelop
						.getAvdelingAvregningImport());

				if (lastAvregningBelop == null
						|| !lastAvregningBelop.equals(avregningBelop)) {
					lastAvregningBelop = avregningBelop;
					if (avregningBelop != null) {
						GuiUtil.setWaitCursor(FrafMain.getInstance());
						loadDeduct(avregningBelop);
						GuiUtil.setDefaultCursor(FrafMain.getInstance());
					}
				}
			}
		}
	}

	/**
	 * Laster avregning
	 * 
	 * @param avregningBelop
	 */
	void loadDeduct(AvdelingAvregningBelop avregningBelop) {
		deductList.clear();

		List<AvdelingAvregning> avregning = avdelingAvregningManager
				.findByAvdelingAvregningBelop(avregningBelop);

		if (avregning != null) {
			deductList.addAll(avregning);
		}

		table.packAll();

	}

	/**
	 * Lager knapp for eksport til excel
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonExcel(WindowInterface window) {
		return new JButton(new ExcelAction(excelDir, window, applUser));
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
		private String directory;

		/**
		 * 
		 */
		private ApplUser applUser1;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param excelDir
		 * @param aWindow
		 * @param aApplUser
		 */
		public ExcelAction(String excelDir, WindowInterface aWindow,
				ApplUser aApplUser) {
			super("Excel");
			directory = excelDir;
			window = aWindow;
			applUser1 = aApplUser;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			AvdelingAvregningBelop avregningBelop = deductImportViewHandler
					.getSelectedImport();
			ArrayListModel deductListAll = new ArrayListModel(
					avdelingAvregningManager.findByAvdnr(avregningBelop
							.getAvdelingAvregningImport().getAvdnr(),avregningBelop.getAvdelingAvregningImport().getAar()));

			DeductExcelTableModel tableModel = new DeductExcelTableModel(
					deductListAll);
			ExcelUtil excelUtil = new ExcelUtil(tableModel,
					"Avregningssrapport for "
							+ avregningBelop.getAvdelingAvregningImport()
									.getAvdnr(), "Avregningsrapport");
			ArrayList<Integer> numCols = new ArrayList<Integer>();
			numCols.add(0);
			numCols.add(2);
			numCols.add(3);
			numCols.add(4);
			try {
				excelUtil.showDataInExcel(directory + "/"
						+ applUser1.getUserName() + "/", "Avregning.xls",
						numCols, numCols, (JLabel) null);

				GuiUtil.showMsgFrame(window.getComponent(), "Excefil",
						"Dersom ikke excel starter automatisk, ligger excelfil med navn Avregning.xls"
								+ " under katalog " + directory + "/"
								+ applUser1.getUserName() + "/");
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", e
						.getMessage());
			}

		}
	}

	/**
	 * Tabellmodell for eksport av avregning til excel
	 * 
	 * @author abr99
	 * 
	 */
	public static final class DeductExcelTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = {"Måned", "Betingelse", "Avregnet avgift",
				"Fakturert avgift", "Avregning" };

		/**
		 * @param listModel
		 */
		public DeductExcelTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			AvdelingAvregning avdelingAvregning = (AvdelingAvregning) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return avdelingAvregning.getAvdelingAvregningBelop().getMaaned();
			case 1:
				return avdelingAvregning.getAvdelingBetingelse();
			case 2:
				return avdelingAvregning.getAvregnetAvgift();
			case 3:
				return avdelingAvregning.getFakturertAvgift();
			case 4:
				return avdelingAvregning.getAvregning();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

}
