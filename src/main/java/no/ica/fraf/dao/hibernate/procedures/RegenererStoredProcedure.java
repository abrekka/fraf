package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.LagKredittnotaLinjerStoredProcedure.LagKredittnotaLinjerStoredProcedureSql;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Kjører prosedyre REGENERER i pakke FRANCHISE_PKG
 * 
 * @author abr99
 * 
 */
public class RegenererStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prosedyre
	 */
	//public static final String SQL = "FRANCHISE_PKG.regenerer";

	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(RegenererStoredProcedure.class);

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "FRANCHISE_PKG_TEST.regenerer";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public RegenererStoredProcedure(JdbcTemplate jdbcTemplate, String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

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
	 * @throws FrafException
	 */
	public void execute(final Integer userId, final Integer fakturaId,
			final java.sql.Date invoiceDate, final java.sql.Date dueDate)
			throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(4);
		inParams.put("p_user_id", userId);
		inParams.put("p_faktura_id", fakturaId);
		inParams.put("p_faktura_dato", invoiceDate);
		inParams.put("p_forfall_dato", dueDate);

		try {
			execute(inParams);
		} catch (DataAccessException e) {
			logger.error("Feil i execute", e);
			throw new FrafException(e);
		}
	}
	
	public enum RegenererStoredProcedureSql{
		SQL("FRANCHISE_PKG.regenerer",false,false),
		SQL_TEST("FRANCHISE_PKG_TEST.regenerer",true,false),
		SQL_STANDALONE("FRANCHISE_STANDALONE_PKG.regenerer",false,true),
		SQL_STANDALONE_TEST("FRANCHISE_STANDALONE_PKG_TEST.regenerer",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,RegenererStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,RegenererStoredProcedureSql>>();
		static{
			RegenererStoredProcedureSql[] list = RegenererStoredProcedureSql.values();
			for(RegenererStoredProcedureSql enumItem:list){
				Map<Boolean,RegenererStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, RegenererStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private RegenererStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static RegenererStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,RegenererStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
