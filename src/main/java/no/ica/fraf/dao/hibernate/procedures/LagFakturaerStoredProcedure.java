package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.KjorAvregningStoredProcedure.KjorAvregningStoredProcedureSql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Kjører prosedyre LAG_FAKTURAER i pakke AVREGNING_PKG
 * 
 * @author abr99
 * 
 */
public class LagFakturaerStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prodesyre
	 */
	//public static final String SQL = "AVREGNING_PKG.LAG_FAKTURAER";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "AVREGNING_PKG_TEST.LAG_FAKTURAER";

	/**
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public LagFakturaerStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_invoice_date", Types.DATE));
		declareParameter(new SqlParameter("p_due_date", Types.DATE));
		declareParameter(new SqlParameter("p_user_name", Types.VARCHAR));
		compile();
	}

	/**
	 * @param buntId
	 * @param invoiceDate
	 * @param dueDate
	 * @param userName
	 * @throws FrafException
	 */
	public void execute(final Integer buntId, final Date invoiceDate,
			final Date dueDate, final String userName) throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_bunt_id", buntId);
		inParams.put("p_invoice_date", invoiceDate);
		inParams.put("p_due_date", dueDate);
		inParams.put("p_user_name", userName);
		try {
			execute(inParams);
		} catch (Exception e) {

			e.printStackTrace();
			throw new FrafException(e);
		}

	}
	
	public enum LagFakturaerStoredProcedureSql{
		SQL("AVREGNING_PKG.LAG_FAKTURAER",false,false),
		SQL_TEST("AVREGNING_PKG_TEST.LAG_FAKTURAER",true,false),
		SQL_STANDALONE("AVREGNING_STANDALONE_PKG.LAG_FAKTURAER",false,true),
		SQL_STANDALONE_TEST("AVREGNING_STANDALONE_PKG_TEST.LAG_FAKTURAER",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,LagFakturaerStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,LagFakturaerStoredProcedureSql>>();
		static{
			LagFakturaerStoredProcedureSql[] list = LagFakturaerStoredProcedureSql.values();
			for(LagFakturaerStoredProcedureSql enumItem:list){
				Map<Boolean,LagFakturaerStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, LagFakturaerStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private LagFakturaerStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static LagFakturaerStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,LagFakturaerStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
