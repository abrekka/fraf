package no.ica.tollpost.dao.hibernate.procedures;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.FakturerPerioderStoredProcedure;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;

public class ImporterStoredProcedure extends StoredProcedure {
	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(ImporterStoredProcedure.class);

	/**
	 * SQL for a kalle prosedyren
	 */
	public static final String SQL = "TG_IMPORT_PKG.IMPORTER";

	/**
	 * SQL for å kalle prosedyren i testdatabase
	 */
	public static final String SQL_TEST = "TG_IMPORT_PKG_TEST.IMPORTER";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public ImporterStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_melding_ider", Types.ARRAY));
		
		
		compile();
	}

	public void execute(final Integer[] meldingIder,final Integer userId) throws FrafException{
		Connection conn = null;
		ARRAY array = null;
		try {
			conn = this.getJdbcTemplate().getDataSource().getConnection();

			C3P0NativeJdbcExtractor extractor = new C3P0NativeJdbcExtractor();
			// CommonsDbcpNativeJdbcExtractor extractor = new
			// CommonsDbcpNativeJdbcExtractor();
			conn = extractor.getNativeConnection(conn);

			final ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
					"AVDNR_TAB_TYPE", conn);
			array = new ARRAY(desc, conn, meldingIder);
		} catch (SQLException e1) {
			logger.error("Feil i execute", e1);
			// e1.printStackTrace();
		}
		final Map<String, Object> inParams = new HashMap<String, Object>();
		inParams.put("p_user_id", userId);
		inParams.put("p_melding_ider", array);
		
		
		


			try {
				execute(inParams);
			} catch (UncategorizedSQLException e) {
				e.printStackTrace();
				throw new FrafException(e);
			}
	}
}
