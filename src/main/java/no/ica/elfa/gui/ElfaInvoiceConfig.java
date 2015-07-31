package no.ica.elfa.gui;

import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.InvoiceConfigInterface;

/**
 * Implementasjon av konfigurasjonsobjekt for ELFA
 * 
 * @author abr99
 * 
 */
public class ElfaInvoiceConfig implements InvoiceConfigInterface {

	/**
	 * @see no.ica.fraf.common.InvoiceConfigInterface#getTableModel(javax.swing.ListModel)
	 */
	public TableModel getTableModel(ListModel listModel) {
		return new InvoiceTableModel(listModel);
	}

}
