package no.ica.fraf.common;

import javax.swing.ListModel;
import javax.swing.table.TableModel;

/**
 * Interface for konfigurasjonsobjekt ved søk etter faktura
 * 
 * @author abr99
 * 
 */
public interface InvoiceConfigInterface {
	/**
	 * Henter tabellmodell for søkeresultat
	 * 
	 * @param listModel
	 * @return tabellmodell
	 */
	TableModel getTableModel(ListModel listModel);
}
