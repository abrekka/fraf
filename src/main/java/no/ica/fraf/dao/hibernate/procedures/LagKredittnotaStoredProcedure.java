package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.FakturerPerioderStoredProcedure.FakturerPerioderStoredProcedureSql;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Representerer den lagrede prosedyren LAG_KREDITTNOTA i pakken FRANCHISE_PKG
 * 
 * @author abr99
 * 
 */
public class LagKredittnotaStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prosedyre
	 */
	//public static final String SQL = "FRANCHISE_PKG.lag_kredittnota";

	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(LagKredittnotaStoredProcedure.class);

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "FRANCHISE_PKG_TEST.lag_kredittnota";

	/**
	 * SQL for å kalle prosedyre i test_test database
	 */
	//public static final String SQL_TEST_TEST = "test_FRANCHISE_PKG.lag_kredittnota";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public LagKredittnotaStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(true);

		declareParameter(new SqlOutParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_faktura_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_faktura_dato", Types.DATE));
		declareParameter(new SqlParameter("p_forfall_dato", Types.DATE));

		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param userId
	 * @param fakturaId
	 * @param invoiceDate
	 * @param dueDate
	 * @return buntid
	 * @throws FrafException
	 */
	public Integer execute(final Integer userId, final Integer fakturaId,
			final java.sql.Date invoiceDate, final java.sql.Date dueDate)
			throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(4);
		final Integer buntId = Integer.valueOf(0);
		inParams.put("p_bunt_id", buntId);
		inParams.put("p_user_id", userId);
		inParams.put("p_faktura_id", fakturaId);
		inParams.put("p_faktura_dato", invoiceDate);
		inParams.put("p_forfall_dato", dueDate);

		try {
			final Map outParams = execute(inParams);
			Integer newBuntId = null;

			if (outParams.size() > 0) {
				newBuntId = Integer.valueOf(String.valueOf(outParams
						.get("p_bunt_id")));
			}
			return newBuntId;
		} catch (DataAccessException e) {
			logger.error("Feil i execute", e);
			// e.printStackTrace();
			throw new FrafException(e);
		}
	}
	public enum LagKredittnotaStoredProcedureSql{
		SQL("FRANCHISE_PKG.lag_kredittnota",false,false),
		SQL_TEST("FRANCHISE_PKG_TEST.lag_kredittnota",true,false),
		SQL_STANDALONE("FRANCHISE_STANDALONE_PKG.lag_kredittnota",false,true),
		SQL_STANDALONE_TEST("FRANCHISE_STANDALONE_PKG_TEST.lag_kredittnota",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,LagKredittnotaStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,LagKredittnotaStoredProcedureSql>>();
		static{
			LagKredittnotaStoredProcedureSql[] list = LagKredittnotaStoredProcedureSql.values();
			for(LagKredittnotaStoredProcedureSql enumItem:list){
				Map<Boolean,LagKredittnotaStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, LagKredittnotaStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private LagKredittnotaStoredProcedureSql(String aSql,boolean test,boolean standalone){
			sqlString=aSql;
			isTest=test;
			isStandalone=standalone;
		}
		public String getSql(){
			return sqlString;
		}
		public boolean isTest(){
			return isTest;
		}
		public boolean isStandalone(){
			return isStandalone;
		}
		
		public static LagKredittnotaStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,LagKredittnotaStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
