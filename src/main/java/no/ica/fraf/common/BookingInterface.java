package no.ica.fraf.common;

import java.io.PrintWriter;

import no.ica.fraf.FrafException;
import no.ica.fraf.xml.InvoiceInterface;

/**
 * Interface for bokføring
 * 
 * @author abr99
 * 
 */
public interface BookingInterface {
	/**
	 * Bokfører fakturalinje
	 * 
	 * @param invoiceInterface
	 * @param batchable
	 * @param printWriter
	 * @throws FrafException
	 */
	void bookInvoiceLine(InvoiceInterface invoiceInterface,
			Batchable batchable, PrintWriter printWriter) throws FrafException;

	/**
	 * Henter filnavn for bokføringsfil
	 * 
	 * @param seq
	 * @return filnavn
	 */
	String getFileName(Integer seq);

}
