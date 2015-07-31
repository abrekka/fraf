package no.ica.fraf.common;

import java.io.PrintWriter;

import no.ica.fraf.FrafException;
import no.ica.fraf.xml.InvoiceInterface;

/**
 * Interface for bokf�ring
 * 
 * @author abr99
 * 
 */
public interface BookingInterface {
	/**
	 * Bokf�rer fakturalinje
	 * 
	 * @param invoiceInterface
	 * @param batchable
	 * @param printWriter
	 * @throws FrafException
	 */
	void bookInvoiceLine(InvoiceInterface invoiceInterface,
			Batchable batchable, PrintWriter printWriter) throws FrafException;

	/**
	 * Henter filnavn for bokf�ringsfil
	 * 
	 * @param seq
	 * @return filnavn
	 */
	String getFileName(Integer seq);

}
