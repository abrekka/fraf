package no.ica.elfa.dao.hibernate.procedures;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Klasse for prosedyre GENERATE_INVOICES
 * 
 * @author abr99
 * 
 */
public class GenerateInvoicesStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prodesyre
	 */
	public static final String SQL = "INVOICE_PKG.GENERATE_INVOICES";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "INVOICE_PKG_TEST.GENERATE_INVOICES";

	/**
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public GenerateInvoicesStoredProcedure(final JdbcTemplate jdbcTemplate,
			final String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_from_date", Types.DATE));
		declareParameter(new SqlParameter("p_to_date", Types.DATE));
		declareParameter(new SqlParameter("p_invoice_date", Types.DATE));
		compile();
	}

	/**
	 * Utfører prosedyre i database
	 * 
	 * @param userId
	 * @param fromDate
	 * @param toDate
	 * @param invoiceDate
	 */
	public final void execute(final Integer userId, final Date fromDate,
			final Date toDate, final Date invoiceDate) {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_user_id", userId);
		inParams.put("p_from_date", fromDate);
		inParams.put("p_to_date", toDate);
		inParams.put("p_invoice_date", invoiceDate);
		execute(inParams);

	}
}
