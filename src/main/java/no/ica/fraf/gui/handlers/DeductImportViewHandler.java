package no.ica.fraf.gui.handlers;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.service.AvdelingAvregningBelopManager;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av avregningimport
 * 
 * @author abr99
 * 
 */
public class DeductImportViewHandler {
	/**
	 * 
	 */
	private ArrayListModel deductImportList;

	/**
	 * 
	 */
	private SelectionInList deductImportSelectionList;

	DeductBatchViewHandler deductBatchViewHandler;

	/**
	 * 
	 */
	Bunt lastBunt;

	/**
	 * 
	 */
	private JXTable table;

	/**
	 * 
	 */
	private AvdelingAvregningBelopManager avdelingAvregningBelopManager;

	public DeductImportViewHandler(Bunt bunt,
			DeductBatchViewHandler aDeductBatchViewHandler,
			AvdelingAvregningBelopManager aAvdelingAvregningBelopManager) {
		avdelingAvregningBelopManager = aAvdelingAvregningBelopManager;
		deductBatchViewHandler = aDeductBatchViewHandler;
		deductImportList = new ArrayListModel();

		if (bunt != null && bunt.getAvdelingAvregningImports() != null) {
			deductImportList.addAll(bunt.getAvdelingAvregningImports());
		}
		deductImportSelectionList = new SelectionInList(
				(ListModel) deductImportList);

	}

	/**
	 * Henter selekteringsliste for avregningimort
	 * 
	 * @return liste
	 */
	public SelectionInList getDeductImportSelectionList() {
		return deductImportSelectionList;
	}

	/**
	 * Lager tabell for avregningimport
	 * 
	 * @return tabell
	 */
	public JXTable getTableImport() {
		table = new JXTable();

		table.setModel(new DeductImportTableModel(deductImportSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				deductImportSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		deductImportSelectionList.clearSelection();

		table.packAll();
		table.setSortable(true);
		return table;

	}

	/**
	 * Henter komponentlytter
	 * 
	 * @return komponentlytter
	 */
	public ComponentListener getImportComponentListener() {
		return new ImportComponentListener();
	}

	/**
	 * Tabellmodell for avregningimport
	 * 
	 * @author abr99
	 * 
	 */
	private static final class DeductImportTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Avdnr", "Måned", "Beløp" };

		/**
		 * @param listModel
		 */
		public DeductImportTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			AvdelingAvregningBelop avdelingAvregningBelop = (AvdelingAvregningBelop) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return avdelingAvregningBelop.getAvdelingAvregningImport()
						.getAvdnr();
			case 1:
				return avdelingAvregningBelop.getMaaned();
			case 2:
				return avdelingAvregningBelop.getBelop();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Komponentlytter
	 * 
	 * @author abr99
	 * 
	 */
	private class ImportComponentListener extends ComponentAdapter {

		/**
		 * 
		 */
		public ImportComponentListener() {
		}

		/**
		 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
		 */
		@Override
		public void componentShown(ComponentEvent evt) {
			Bunt bunt = deductBatchViewHandler.getSelectedBatch();

			if (lastBunt == null || !lastBunt.equals(bunt)) {
				lastBunt = bunt;
				if (bunt != null) {
					GuiUtil.setWaitCursor(FrafMain.getInstance());
					loadImport(bunt);
					GuiUtil.setDefaultCursor(FrafMain.getInstance());
				}
			}
		}
	}

	/**
	 * Laster import
	 * 
	 * @param bunt
	 */
	void loadImport(Bunt bunt) {
		deductImportList.clear();

		List<AvdelingAvregningBelop> belop = avdelingAvregningBelopManager
				.findByBunt(bunt);

		if (belop != null) {
			deductImportList.addAll(belop);
		}
		table.packAll();
	}

	/**
	 * Henter selektert import
	 * 
	 * @return import
	 */
	public AvdelingAvregningBelop getSelectedImport() {
		int index = deductImportSelectionList.getSelectionIndex();
		if (index != -1) {
			index = table.convertRowIndexToModel(index);
			return (AvdelingAvregningBelop) deductImportSelectionList
					.getElementAt(index);
		}
		return null;
	}
}
