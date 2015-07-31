package no.ica.fraf.dao.hibernate.procedures;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.FrafException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Kjører prosedyre GET_SEQUENCE_NUMBER i pakke FRAF_E2B_PKG
 * 
 * @author abr99
 * 
 */
public class FrafGetSequenceNumberStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prosedyre i databasen
	 */
	public static final String SQL = "FRAF_E2B_PKG.GET_SEQUENCE_NUMBER";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "FRAF_E2B_PKG_TEST.GET_SEQUENCE_NUMBER";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public FrafGetSequenceNumberStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(true);

		declareParameter(new SqlOutParameter("p_seq_nr", Types.INTEGER));

		compile();
	}

	/**
	 * @return sekvensnummer
	 * @throws FrafException
	 */
	public Integer execute() throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(0);

		try {
			final Map outParams = execute(inParams);
			Integer newBuntId = null;
			Object object = null;
			if (outParams.size() > 0) {
				object = outParams.get("p_seq_nr");
				if (object instanceof BigDecimal) {
					newBuntId = Integer.valueOf(((BigDecimal) object)
							.intValue());
				} else {
					newBuntId = (Integer) object;
				}
			}
			return newBuntId;
		} catch (DataAccessException e) {
			throw new FrafException(e);
		}
	}
}
