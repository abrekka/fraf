package no.ica.elfa.dao.hibernate.pkg;

import java.util.Date;

import no.ica.elfa.dao.hibernate.procedures.GenerateCreditStoredProcedure;
import no.ica.elfa.dao.hibernate.procedures.GenerateInvoicesStoredProcedure;
import no.ica.elfa.dao.pkg.InvoicePkgDAO;
import no.ica.fraf.model.ApplUser;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av DAO mot pakke INVOICE_PKG
 * 
 * @author abr99
 * 
 */
public class InvoicePkgDAOHibernate extends JdbcDaoSupport implements
		InvoicePkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @param isTest
	 */
	public final void setTest(final boolean isTest) {
		this.test = isTest;
	}

	public final void generateInvoices(final ApplUser user, final Date fromDate, final Date toDate,
			final Date invoiceDate) {
		GenerateInvoicesStoredProcedure invoiceProcedure;

		if (test) {
			invoiceProcedure = new GenerateInvoicesStoredProcedure(
					getJdbcTemplate(), GenerateInvoicesStoredProcedure.SQL_TEST);
		} else {
			invoiceProcedure = new GenerateInvoicesStoredProcedure(
					getJdbcTemplate(), GenerateInvoicesStoredProcedure.SQL);
		}
		Integer userId = null;
		if (user != null) {
			userId = user.getUserId();
		}
		invoiceProcedure.execute(userId, fromDate, toDate, invoiceDate);

	}

	public final void generateCredit(final ApplUser user, final Integer batchId, final Date invoiceDate) {
		GenerateCreditStoredProcedure creditProcedure;

		if (test) {
			creditProcedure = new GenerateCreditStoredProcedure(
					getJdbcTemplate(), GenerateCreditStoredProcedure.SQL_TEST);
		} else {
			creditProcedure = new GenerateCreditStoredProcedure(
					getJdbcTemplate(), GenerateCreditStoredProcedure.SQL);
		}
		Integer userId = null;
		if (user != null) {
			userId = user.getUserId();
		}
		creditProcedure.execute(userId, batchId, invoiceDate);

	}

}
