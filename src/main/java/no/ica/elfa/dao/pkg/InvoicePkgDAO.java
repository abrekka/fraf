package no.ica.elfa.dao.pkg;

import java.util.Date;

import no.ica.fraf.model.ApplUser;

/**
 * Interface for DAO for pakke INVOICE_PKG
 * 
 * @author abr99
 * 
 */
public interface InvoicePkgDAO {
	/**
	 * Kjører prosedyre GENERATE_INVOICES
	 * 
	 * @param user
	 * @param fromDate
	 * @param toDate
	 * @param invoiceDate
	 */
	void generateInvoices(ApplUser user, Date fromDate, Date toDate,
			Date invoiceDate);

	/**
	 * Kjører prosedyre GENERATE_CREDIT
	 * 
	 * @param user
	 * @param batchId
	 * @param invoiceDate
	 */
	void generateCredit(ApplUser user, Integer batchId, Date invoiceDate);
}
