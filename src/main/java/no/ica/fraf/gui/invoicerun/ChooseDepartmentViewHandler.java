package no.ica.fraf.gui.invoicerun;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.model.AvdelingV;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for valg av avdelinger
 * 
 * @author abr99
 * 
 */
public class ChooseDepartmentViewHandler implements Closeable {
	/**
	 * 
	 */
	private ArrayListModel departmentList;

	/**
	 * 
	 */
	private SelectionInList departmentSelectionList;

	/**
	 * 
	 */
	private AvdelingVDAO avdelingVDAO;

	/**
	 * 
	 */
	Hashtable<AvdelingV, AvdelingV> depNumbersNotChoosen = new Hashtable<AvdelingV, AvdelingV>();

	/**
	 * @param aAvdelingVDAO
	 * @param fromDepartment
	 * @param toDepartment
	 * @throws FrafException 
	 */
	public ChooseDepartmentViewHandler(AvdelingVDAO aAvdelingVDAO,
			Integer fromDepartment, Integer toDepartment) throws FrafException {
		avdelingVDAO = aAvdelingVDAO;
		departmentList = new ArrayListModel();

		List<AvdelingV> departments = avdelingVDAO.findAll(fromDepartment,
				toDepartment);

		if (departments != null) {
			departmentList.addAll(departments);
		}
		departmentSelectionList = new SelectionInList(
				(ListModel) departmentList);

	}

	/**
	 * Lager tabell for avdelinger
	 * 
	 * @return tabell
	 */
	public JXTable getTableDepartments() {
		JXTable table = new JXTable();

		table.setModel(new DepartmentTableModel(departmentSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				departmentSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		departmentSelectionList.clearSelection();

		return table;
	}

	/**
	 * Lager ok-knapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonOk(WindowInterface window) {
		return new CancelButton(window, this, false, "Ok", IconEnum.ICON_OK,
				null);
	}

	/**
	 * Tabellmodell for avdelinger
	 * 
	 * @author abr99
	 * 
	 */
	private final class DepartmentTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param listModel
		 */
		public DepartmentTableModel(ListModel listModel) {
			super(listModel, new String[] { "Faktureres", "Avdnr", "Navn",
					"Status" });

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			AvdelingV avdelingV = (AvdelingV) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				if (depNumbersNotChoosen.contains(avdelingV)) {
					return Boolean.FALSE;
				}
				return Boolean.TRUE;
			case 1:
				return avdelingV.getAvdnr();
			case 2:
				return avdelingV.getAvdelingNavn();
			case 3:
				return avdelingV.getStatus();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

		/**
		 * @see javax.swing.table.TableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return Boolean.class;
			case 1:
				return Integer.class;
			case 2:

			case 3:
				return String.class;
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

		/**
		 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int,
		 *      int)
		 */
		@Override
		public void setValueAt(Object object, int row, int col) {
			AvdelingV avdelingV = (AvdelingV) getRow(row);
			if (col == 0) {
				if ((Boolean) object) {
					depNumbersNotChoosen.remove(avdelingV);
				} else {
					depNumbersNotChoosen.put(avdelingV, avdelingV);
				}
			}
		}

		/**
		 * @see javax.swing.table.TableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int row, int col) {
			return col == 0;
		}

	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Henter fjernede avdelinger
	 * 
	 * @return avdelingsnumre
	 */
	public Integer[] getRemovedDepartments() {
		Set<AvdelingV> deps = depNumbersNotChoosen.keySet();
		Integer[] numbers = new Integer[deps.size()];
		int counter = 0;
		for (AvdelingV avd : deps) {
			numbers[counter] = avd.getAvdnr();
			counter++;
		}
		return numbers;
	}

}
