package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.Invoice;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.InvoiceManagerInterface;

/**
 * Interface for manager mot tabell INVOICE
 * 
 * @author abr99
 * 
 */
public interface InvoiceManager extends InvoiceManagerInterface {
	String MANAGER_NAME = "invoiceManager";

	List<Invoice> findByBunt(Bunt bunt, Integer index, Integer fetchSize,
			InvoiceColumnEnum orderByColumn);

	List<Invoice> findByBuntId(Integer buntId);

	/**
	 * Lazy laster faktura
	 * 
	 * @param invoice
	 * @param enums
	 */
	void lazyLoad(Invoice invoice, LazyLoadInvoiceEnum[] enums);

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#findByInvoiceNrAndOrAvdnr(java.lang.String,
	 *      java.lang.String)
	 */
	List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(String invoiceNr,
			String avdnr);

	/**
	 * Finner fakturaer basert på fakturanumre
	 * 
	 * @param invoiceNumbers
	 * @return fakturaer
	 */
	List<Invoice> findByInvoiceNumbers(List<Integer> invoiceNumbers);

	/**
	 * Lagrer faktura
	 * 
	 * @param invoice
	 */
	void saveInvoice(Invoice invoice);
}
