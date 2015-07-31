package no.ica.elfa.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.model.ReconcilV;
import no.ica.elfa.service.ReconcilVManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning og generering av avstemmingsrapport
 * 
 * @author abr99
 * 
 */
public class ReconcilViewHandler implements Closeable {
	/**
	 * 
	 */
	private final ArrayListModel reportList;

	/**
	 * 
	 */
	private final SelectionInList reportSelectionList;

	/**
	 * 
	 */
	private final ArrayListModel batchList;

	/**
	 * 
	 */
	final SelectionInList batchSelectionList;

	/**
	 * 
	 */
	private ReconcilVManager reconcilVManager;

	/**
	 * 
	 */
	JButton buttonShowReport;

	/**
	 * 
	 */
	private JXTable table;

	/**
	 * 
	 */
	TableModel tableModel;

	/**
	 * 
	 */
	String directory;

	/**
	 * 
	 */
	ApplUser applUser;

	public ReconcilViewHandler(ReconcilVManager aReconcilVManager,
			String excelDir, ApplUser aApplUser) {
		applUser = aApplUser;
		this.directory = excelDir;
		reconcilVManager = aReconcilVManager;
		reportList = new ArrayListModel();
		reportSelectionList = new SelectionInList((ListModel) reportList);
		BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		batchList = new ArrayListModel(buntDAO.findAllElfaBatches());
		batchSelectionList = new SelectionInList((ListModel) batchList);
	}

	/**
	 * Lager excel-knapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonExcel(WindowInterface window) {
		JButton button = new JButton(new ExcelAction(window));
		button.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return button;
	}

	/**
	 * Lager tabell for rapport
	 * 
	 * @return tabell
	 */
	public JXTable getTableReport() {
		table = new JXTable();

		tableModel = new ReconcilTableModel(reportSelectionList);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setColumnControlVisible(true);
		table.setSearchable(null);

		table.setDragEnabled(false);

		table.setShowGrid(true);
		table.packAll();
		return table;

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
	 * Lager knapp for å vise rapport
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonShowReport(WindowInterface window) {
		buttonShowReport = new JButton(new ShowReportAction(window));
		buttonShowReport.setEnabled(false);
		batchSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
		return buttonShowReport;
	}

	/**
	 * Lager comboboks for bunter
	 * 
	 * @return comboboks
	 */
	public JComboBox getComcoBoxBatches() {
		return BasicComponentFactory.createComboBox(batchSelectionList);
	}

	/**
	 * Tabellmodell for rapport
	 * 
	 * @author abr99
	 * 
	 */
	private static class ReconcilTableModel extends AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Netto", "Mva", "Type", "Antall" };

		/**
		 * @param listModel
		 */
		public ReconcilTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			ReconcilV reconcilV = (ReconcilV) getRow(row);
			switch (column) {
			case 0:
				return reconcilV.getNet();
			case 1:
				return reconcilV.getVat();
			case 2:
				return reconcilV.getInvoiceType();
			case 3:
				return reconcilV.getInvoiceCount();
			default:
				throw new IllegalStateException("Unknown column");
			}
		}
	}

	/**
	 * Viser rapport
	 * 
	 * @param window
	 */
	void showReport(WindowInterface window) {
		GuiUtil.setWaitCursor(window);
		Bunt bunt = (Bunt) batchSelectionList.getSelection();
		if (bunt != null) {
			List<ReconcilVInterface> lines = reconcilVManager
					.findByBatchId(bunt.getBatchId());

			if (lines != null) {
				reportList.clear();
				reportList.addAll(lines);
			}
		}
		table.packAll();
		GuiUtil.setDefaultCursor(window);
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Viser rapport
	 * 
	 * @author abr99
	 * 
	 */
	private class ShowReportAction extends AbstractAction {
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
		public ShowReportAction(WindowInterface aWindow) {
			super("Vis rapport");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			showReport(window);

		}
	}

	/**
	 * Håndterer selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonShowReport.setEnabled(batchSelectionList.hasSelection());

		}

	}

	/**
	 * Lager excelfil
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
			super("Excel");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Bunt bunt = (Bunt) batchSelectionList.getSelection();
			ExcelUtil excelUtil = new ExcelUtil(tableModel,
					"Avstemmingsrapport - " + bunt.getFileName(),
					"Avstemmingsrapport");
			try {
				excelUtil.showDataInExcel(directory + "/"
						+ applUser.getUserName() + "/", "Avstemming.xls",
						(List<Integer>) null, (List<Integer>) null,
						(JLabel) null);

				GuiUtil.showMsgFrame(window.getComponent(), "Excefil",
						"Dersom ikke excel starter automatisk, ligger excelfil med navn Avstemming.xls"
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
