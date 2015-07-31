package no.ica.tollpost.gui;

import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.InvoiceConfigInterface;

public class TollpostInvoiceConfig implements InvoiceConfigInterface{

	public TableModel getTableModel(ListModel listModel) {
		return new InvoiceTableModel(listModel,false);
	}

	

}
