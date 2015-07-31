package no.ica.elfa.dao.hibernate.procedures;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Klasse mot prosedyre GENERATE_CREDIT
 * 
 * @author abr99
 * 
 */
public class GenerateCreditStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prodesyre
	 */
	public static final String SQL = "INVOICE_PKG.GENERATE_CREDIT";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "INVOICE_PKG_TEST.GENERATE_CREDIT";

	/**
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public GenerateCreditStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_batch_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_invoice_date", Types.DATE));
		compile();
	}

	/**
	 * Utfører prosedyre i databaseO
	 * 
	 * @param userId
	 * @param batchId
	 * @param invoiceDate
	 */
	public final void execute(final Integer userId, final Integer batchId,
			final Date invoiceDate) {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_user_id", userId);
		inParams.put("p_batch_id", batchId);

		inParams.put("p_invoice_date", invoiceDate);
		execute(inParams);

	}
}
