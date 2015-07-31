package no.ica.fraf.common;

import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.gui.FrafInvoiceTableModel;

/**
 * Implementasjon av konfigurasjonsinterface for FRAF
 * 
 * @author abr99
 * 
 */
public class FrafInvoiceConfig implements InvoiceConfigInterface {

	/**
	 * @see no.ica.fraf.common.InvoiceConfigInterface#getTableModel(javax.swing.ListModel)
	 */
	public TableModel getTableModel(ListModel listModel) {
		SystemEnum test;
		return new FrafInvoiceTableModel(listModel);
	}

}
