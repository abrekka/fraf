package no.ica.elfa.service;

import java.util.Collection;
import java.util.List;

import no.ica.elfa.model.InvoiceItemV;

/**
 * Interface for manager mot view INVOICE_ITEM_V
 * 
 * @author abr99
 * 
 */
public interface InvoiceItemVManager {
	/**
	 * Finner fakturalinjer for gitt buntid
	 * 
	 * @param batchId
	 * @return fakturalinjer
	 */
	List<InvoiceItemV> findByBatchId(Integer batchId);

	/**
	 * Finner fakturalinjer for gitte fakturanumre
	 * 
	 * @param invoiceNumbers
	 * @return fakturalinjer
	 */
	List<InvoiceItemV> findByInvoiceNumbers(Collection invoiceNumbers);
}
