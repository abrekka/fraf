package no.ica.elfa.dao;

import java.util.List;

import no.ica.elfa.model.InvoiceItem;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO for tabell INVOICE_ITEM
 * 
 * @author abr99
 * 
 */
public interface InvoiceItemDAO extends DAO<InvoiceItem> {
	/**
	 * Henter alle fakturalinjer for en gitt bunt
	 * 
	 * @param batchId
	 * @return fakturalinjer
	 */
	List<InvoiceItem> findByBatchId(Integer batchId);
}
