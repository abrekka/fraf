package no.ica.elfa.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Kj�rer prosedyre RUN_IMPORT_FILE
 * 
 * @author abr99
 * 
 */
public class RunImportFileStoredProcedure extends StoredProcedure {
	/**
	 * SQL for � kalle prodesyre
	 */
	public static final String SQL = "EEP_IMPORT_PKG.RUN_IMPORT_FILE";

	/**
	 * SQL for � kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "EEP_IMPORT_PKG_TEST.RUN_IMPORT_FILE";

	/**
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public RunImportFileStoredProcedure(final JdbcTemplate jdbcTemplate,
			final String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		compile();
	}

	/**
	 * Utf�rer prosedyre i database
	 * 
	 * @param userId
	 */
	public final void execute(final Integer userId) {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_user_id", userId);
		execute(inParams);

	}
}
