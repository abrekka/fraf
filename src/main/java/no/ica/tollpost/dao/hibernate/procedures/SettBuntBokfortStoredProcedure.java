package no.ica.tollpost.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.FrafException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class SettBuntBokfortStoredProcedure extends StoredProcedure {
	/**
	 * SQL for � kalle prodesyre
	 */
	public static final String SQL = "TG_FAKTURA_PKG.SETT_BUNT_BOKFORT";

	/**
	 * SQL for � kalle prosedyre i testdatabase
	 */
	public static final String SQL_TEST = "TG_FAKTURA_PKG_TEST.SETT_BUNT_BOKFORT";

	public SettBuntBokfortStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));
		compile();
	}
	
	public void execute(final Integer buntId) throws FrafException{
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_bunt_id", buntId);
		try {
			execute(inParams);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new FrafException(e);
		}

	}
}
