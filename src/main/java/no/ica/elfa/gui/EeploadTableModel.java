/**
 * 
 */
package no.ica.elfa.gui;

import javax.swing.ListModel;

import no.ica.elfa.model.EepHeadLoad;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

/**
 * Tabellmodell for load-data
 * 
 * @author abr99
 * 
 */
public final class EeploadTableModel extends AbstractTableAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final String[] COLUMNS = new String[] { "Sekvensnr", "Type",
			"Filtype", "Filnavn", "Fildato", "Periodestart", "Periodestopp",
			"Antall", "Oprettet" };

	/**
	 * @param list
	 */
	public EeploadTableModel(ListModel list) {
		super(list, COLUMNS);
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column) {
		EepHeadLoad eepHeadLoad = (EepHeadLoad) getRow(row);
		switch (column) {
		case 0:
			return eepHeadLoad.getSequenceNumber();
		case 1:
			return eepHeadLoad.getRecordType();
		case 2:
			return eepHeadLoad.getFileType();
		case 3:
			return eepHeadLoad.getFileName();
		case 4:
			return eepHeadLoad.getFileDate();
		case 5:
			return eepHeadLoad.getPeriodStart();
		case 6:
			return eepHeadLoad.getPeriodEnd();
		case 7:
			return eepHeadLoad.getNumberOfRecords();
		case 8:
			return eepHeadLoad.getCreatedDate();
		default:
			throw new RuntimeException("Kolonne er ikke definert");

		}
	}

}