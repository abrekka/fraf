package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.InvoiceItemDAO;
import no.ica.elfa.model.InvoiceItem;
import no.ica.elfa.service.InvoiceItemManager;

/**
 * Implementasjon av manager mot tabell INVOICE_ITEM
 * 
 * @author abr99
 * 
 */
public class InvoiceItemManagerImpl implements InvoiceItemManager {
	/**
	 * 
	 */
	private InvoiceItemDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setInvoiceItemDAO(InvoiceItemDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.InvoiceItemManager#findByBatchId(java.lang.Integer)
	 */
	public List<InvoiceItem> findByBatchId(Integer batchId) {
		return dao.findByBatchId(batchId);
	}

}
