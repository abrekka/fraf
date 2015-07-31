package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.FakturerPeriodeStoredProcedure.FakturerPeriodeStoredProcedureSql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Kjører prosedyre KJOR_AVREGNING i pakke AVREGNING_PKG
 * 
 * @author abr99
 * 
 */
public class KjorAvregningStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prodesyre
	 */
	//public static final String SQL = "AVREGNING_PKG.KJOR_AVREGNING";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "AVREGNING_PKG_TEST.KJOR_AVREGNING";

	/**
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public KjorAvregningStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));
		compile();
	}

	/**
	 * @param buntId
	 * @throws FrafException
	 */
	public void execute(final Integer buntId) throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_bunt_id", buntId);
		try {
			execute(inParams);
		} catch (Exception e) {

			e.printStackTrace();
			throw new FrafException(e);
		}

	}
	
	public enum KjorAvregningStoredProcedureSql{
		SQL("AVREGNING_PKG.KJOR_AVREGNING",false,false),
		SQL_TEST("AVREGNING_PKG_TEST.KJOR_AVREGNING",true,false),
		SQL_STANDALONE("AVREGNING_STANDALONE_PKG.KJOR_AVREGNING",false,true),
		SQL_STANDALONE_TEST("AVREGNING_STANDALONE_PKG_TEST.KJOR_AVREGNING",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,KjorAvregningStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,KjorAvregningStoredProcedureSql>>();
		static{
			KjorAvregningStoredProcedureSql[] list = KjorAvregningStoredProcedureSql.values();
			for(KjorAvregningStoredProcedureSql enumItem:list){
				Map<Boolean,KjorAvregningStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, KjorAvregningStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private KjorAvregningStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static KjorAvregningStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,KjorAvregningStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
