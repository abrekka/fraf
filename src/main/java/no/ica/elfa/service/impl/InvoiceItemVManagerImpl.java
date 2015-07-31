package no.ica.elfa.service.impl;

import java.util.Collection;
import java.util.List;

import no.ica.elfa.dao.InvoiceItemVDAO;
import no.ica.elfa.model.InvoiceItemV;
import no.ica.elfa.service.InvoiceItemVManager;

/**
 * Implementasjon av manager mot view INVOICE_ITEM_V
 * 
 * @author abr99
 * 
 */
public class InvoiceItemVManagerImpl implements InvoiceItemVManager {
	/**
	 * 
	 */
	private InvoiceItemVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setInvoiceItemVDAO(InvoiceItemVDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.InvoiceItemVManager#findByBatchId(java.lang.Integer)
	 */
	public List<InvoiceItemV> findByBatchId(Integer batchId) {
		return dao.findByBatchId(batchId);
	}

	/**
	 * @see no.ica.elfa.service.InvoiceItemVManager#findByInvoiceNumbers(java.util.Collection)
	 */
	public List<InvoiceItemV> findByInvoiceNumbers(Collection invoiceNumbers) {
		return dao.findByInvoiceNrs(invoiceNumbers);
	}

}
