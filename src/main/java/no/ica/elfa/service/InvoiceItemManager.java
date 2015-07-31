package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.InvoiceItem;

/**
 * Interface for manager mot tabell INVOICE_ITEM
 * 
 * @author abr99
 * 
 */
public interface InvoiceItemManager {
	/**
	 * Finner alle med gitt bunt
	 * 
	 * @param batchId
	 * @return fakturalinjer
	 */
	List<InvoiceItem> findByBatchId(Integer batchId);
}
