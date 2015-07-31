package no.ica.elfa.dao;

import java.util.Collection;
import java.util.List;

import no.ica.elfa.model.InvoiceItemV;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO mot view INVOCIE_ITEM_V
 * 
 * @author abr99
 * 
 */
public interface InvoiceItemVDAO extends DAO<InvoiceItemV> {
	/**
	 * Finner fakturalinjer basert p� buntid
	 * 
	 * @param batchId
	 * @return fakturalinjer
	 */
	List<InvoiceItemV> findByBatchId(Integer batchId);

	/**
	 * Finner fakturalinjer basert p� fakturanumre
	 * 
	 * @param invoiceNumbers
	 * @return fakturalinjer
	 */
	List<InvoiceItemV> findByInvoiceNrs(final Collection invoiceNumbers);
}
