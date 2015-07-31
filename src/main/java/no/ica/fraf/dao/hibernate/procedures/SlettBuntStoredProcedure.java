package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.FrafException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Representerer den lagrede prosedyren SLETT_BUNT i pakken BUNT_PKG
 * 
 * @author abr99
 * 
 */
public class SlettBuntStoredProcedure extends StoredProcedure {
	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(SlettBuntStoredProcedure.class);
	/**
	 * SQL for å kalle prosedyre
	 */
	public static final String SQL = "BUNT_PKG.SLETT_BUNT";

	/**
	 * SAL for å kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "BUNT_PKG_TEST.SLETT_BUNT";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public SlettBuntStoredProcedure(JdbcTemplate jdbcTemplate, String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_ignore_error", Types.INTEGER));
		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param buntId
	 * @param ignoreError
	 * @throws FrafException
	 */
	public void execute(final Integer buntId,final Integer ignoreError) throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_bunt_id", buntId);
		inParams.put("p_ignore_error", ignoreError);

		try {
			execute(inParams);
		} catch (DataAccessException e) {
			logger.error("Feil i execute",e);
			//e.printStackTrace();
			throw new FrafException(e);
		}
	}

}
