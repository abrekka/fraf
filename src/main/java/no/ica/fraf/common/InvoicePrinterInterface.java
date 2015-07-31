package no.ica.fraf.common;

import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.xml.InvoiceInterface;

/**
 * Interface for utskruft av faktura
 * 
 * @author abr99
 * 
 */
public interface InvoicePrinterInterface extends Threadable {
	/**
	 * Sett faktura som skal skrives ut
	 * 
	 * @param invoice
	 */
	void setInvoice(InvoiceInterface invoice);
}
