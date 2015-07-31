package no.ica.fraf.gui.invoicerun;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.util.ApplicationUtil;

/**
 * TableModel for visning av avdelingsnummer sammen med en checkboks
 * 
 * @author abr99
 * 
 */
public class DepartmentNrTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Avdelignsnummere
	 */
	private ArrayList<Integer> depNumbers;

	/**
	 * Valgte avdelingsnummere
	 */
	private ArrayList<Integer> depNumbersChoosen;

	/**
	 * Avdelingsnumre som ikke er valgt
	 */
	private ArrayList<Integer> depNumbersNotChoosen;

	/**
	 * 
	 */
	private ArrayList<AvdelingV> avdelinger;

	/**
	 * Kolonnenavn
	 */
	private static String[] columnNames = new String[] { "Faktureres", "Avdnr",
			"Navn","Status" };

	/**
	 * 
	 */
	public DepartmentNrTableModel() {

		

	}
	
	/**
	 * Setter data for modell
	 * @param departments
	 * @param removedDeps
	 */
	@SuppressWarnings("unchecked")
	public void setData(List<AvdelingV> departments,
			List<Integer> removedDeps){
		this.avdelinger = new ArrayList<AvdelingV>(departments);

		depNumbers = getAvdnrFromAvdelingV(avdelinger);

		if (removedDeps != null) {
			depNumbersNotChoosen = new ArrayList<Integer>(removedDeps);
			depNumbersChoosen = new ArrayList<Integer>(ApplicationUtil.getDiff(
					depNumbers, depNumbersNotChoosen));
		} else {
			depNumbersNotChoosen = new ArrayList<Integer>();
			depNumbersChoosen = new ArrayList<Integer>(depNumbers);
		}
		fireTableDataChanged();
	}

	/**
	 * Henter ut avdnr fra alle avdelinger
	 * 
	 * @param currentDepartments
	 * @return list med avdnr
	 */
	private ArrayList<Integer> getAvdnrFromAvdelingV(
			List<AvdelingV> currentDepartments) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (AvdelingV avdeling : currentDepartments) {
			returnList.add(avdeling.getAvdnr());
		}
		return returnList;
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		if (depNumbers != null) {
			return depNumbers.size();
		}
		return 0;
	}

	/**
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			if (depNumbersChoosen.contains(depNumbers.get(rowIndex))) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		case 1:
			return depNumbers.get(rowIndex);
		case 2:
			return avdelinger.get(rowIndex).getAvdelingNavn();
		case 3:
			return avdelinger.get(rowIndex).getStatus();
		default:
			return null;
		}
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 *      int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			if (((Boolean) aValue).booleanValue()) {
				depNumbersChoosen.add(depNumbersNotChoosen
						.remove(depNumbersNotChoosen.indexOf(depNumbers
								.get(rowIndex))));

			} else {
				depNumbersNotChoosen.add(depNumbersChoosen
						.remove(depNumbersChoosen.indexOf(depNumbers
								.get(rowIndex))));
			}
		}
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Henter ut avdnr for avdelinger som er tatt bort i array av int
	 * 
	 * @return avdelinger som er tatt bort
	 */
	public Integer[] getNotChoosenDepartments() {
		Integer[] returnArray = new Integer[depNumbersNotChoosen.size()];
		return depNumbersNotChoosen.toArray(returnArray);
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
