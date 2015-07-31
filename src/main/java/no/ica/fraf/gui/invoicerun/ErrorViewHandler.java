package no.ica.fraf.gui.invoicerun;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.FeilDAO;
import no.ica.fraf.enums.FeilEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.BuntFeil;
import no.ica.fraf.model.FakturaFeil;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av feil i fakturabunt
 * 
 * @author abr99
 * 
 */
public class ErrorViewHandler {
	/**
	 * 
	 */
	private ArrayListModel errorList;

	/**
	 * 
	 */
	private SelectionInList errorSelectionList;

	/**
	 * 
	 */
	private FeilEnum currentFeilEnum;

	/**
	 * 
	 */
	private FeilDAO feilDAO;

	/**
	 * 
	 */
	private Integer currentId;
	private String excelDir;

	/**
	 * @param feilEnum
	 * @param aFeilDAO
	 */
	public ErrorViewHandler(FeilEnum feilEnum, FeilDAO aFeilDAO, String dir) {
		excelDir = dir;
		currentFeilEnum = feilEnum;
		feilDAO = aFeilDAO;

		errorList = new ArrayListModel();

		errorSelectionList = new SelectionInList((ListModel) errorList);
	}

	/**
	 * Laster feil for bunt
	 */
	private void loadErrors() {
		List<?> list = feilDAO.findById(currentId);
		if (list != null && list.size() != 0) {
			errorList.addAll(list);
		}
	}

	/**
	 * Lager tabell fo feil
	 * 
	 * @return tabell
	 */
	public JXTable getTableError() {
		JXTable table = new JXTable();

		TableModel tableModel = getTableModel(errorSelectionList);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				errorSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		table.setDragEnabled(false);

		table.setShowGrid(true);
		errorSelectionList.clearSelection();

		TableColumn col = table.getColumnExt(0);
		col.setPreferredWidth(150);

		col = table.getColumnExt(1);
		col.setPreferredWidth(150);

		return table;
	}
	
	public JButton getButtonExcel(WindowInterface window){
		JButton button= new JButton(new ExcelAction(window));
		button.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return button;
	}

	/**
	 * Henter tabellmodell for feiltabell
	 * 
	 * @param list
	 * @return tabellmodell
	 */
	private TableModel getTableModel(SelectionInList list) {
		switch (currentFeilEnum) {
		case BUNT_FEIL:
			return new BatchErrorTableModel(list);
		case FAKTURA_FEIL:
			return new InvoiceErrorTableModel(list);
		}
		return null;
	}

	/**
	 * Tabellmodell for buntfeil
	 * 
	 * @author abr99
	 * 
	 */
	private static final class BatchErrorTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Feil", "Kommentar" };

		/**
		 * @param listModel
		 */
		public BatchErrorTableModel(ListModel listModel) {
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
			BuntFeil buntFeil = (BuntFeil) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return buntFeil.getFeilKode();
			case 1:
				return buntFeil.getKommentar();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Tabellmodell for fakturafeil
	 * 
	 * @author abr99
	 * 
	 */
	private static final class InvoiceErrorTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Faktura-ID", "Feil", "Kommentar" };

		/**
		 * @param listModel
		 */
		public InvoiceErrorTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			FakturaFeil fakturaFeil = (FakturaFeil) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return fakturaFeil.getFaktura();
			case 1:
				return fakturaFeil.getFeilKode();
			case 2:
				return fakturaFeil.getFeilKommentar();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Setter feilid
	 * 
	 * @param id
	 * @param errorCount
	 */
	public void setErrorId(Integer id, Integer errorCount) {
		if (id != null && errorCount != null && errorCount.intValue() != 0) {
			currentId = id;
			loadErrors();
		}
	}
	
	private class ExcelAction extends AbstractAction{
		private WindowInterface window;
		public ExcelAction(WindowInterface aWindow){
			super("Excel");
			window=aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelGenerator(window), null);
			
		}
	}
	private class ExcelGenerator implements Threadable {

		

		private WindowInterface window;


		public ExcelGenerator(
				WindowInterface aWindow) {
			window = aWindow;

			
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Genererer excelfil...");


			ExcelUtil excelUtil = new ExcelUtil(getTableModel(errorSelectionList), "Buntfeil",
					"Buntfeil");
			String fileName = "Buntfeil.xls";
			try {
				/*List<Integer> numCols = new ArrayList<Integer>();
				numCols.add(0);
				numCols.add(3);
				numCols.add(4);
				numCols.add(5);
				numCols.add(8);
				numCols.add(9);
				numCols.add(10);
				numCols.add(11);
				numCols.add(12);
				numCols.add(14);
				numCols.add(16);*/
				excelUtil.showDataInExcel(excelDir, fileName,
						(List<Integer>) null, null, (JLabel) null);
			} catch (FrafException e) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", e
						.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		public void doWhenFinished(Object object) {
			// TODO Auto-generated method stub

		}

	}

}
