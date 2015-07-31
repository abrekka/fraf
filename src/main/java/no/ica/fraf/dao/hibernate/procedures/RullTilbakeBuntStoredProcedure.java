package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.FrafException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Kjører prosedyre RULL_TILBKE_BUNT i pakke BUNT_PKG
 * 
 * @author abr99
 * 
 */
public class RullTilbakeBuntStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prosedyre
	 */
	public static final String SQL = "BUNT_PKG.RULL_TILBAKE_BUNT";

	/**
	 * SAL for å kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "BUNT_PKG_TEST.RULL_TILBAKE_BUNT";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public RullTilbakeBuntStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));
		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param buntId
	 * @throws FrafException
	 */
	public void execute(final Integer buntId) throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_bunt_id", buntId);

		try {
			execute(inParams);
		} catch (DataAccessException e) {
			throw new FrafException(e);
		}
	}

}
