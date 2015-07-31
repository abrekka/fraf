package no.ica.fraf.dao.hibernate.procedures;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.LesInnOmsetningStoredProcedure.LesInnOmsetningStoredProcedureSql;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Representerer den lagrede prosedyren SETT_INN_MANUELT_BUDSJETT i pakken
 * AVDELING_OMSETNING_PKG
 * 
 * @author abr99
 * 
 */
public class SettInnManueltBudsjettStoredProcedure extends StoredProcedure {
	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(SettInnManueltBudsjettStoredProcedure.class);
	/**
	 * SQL for å kalle prosedyre
	 */
	//public static final String SQL = "AVDELING_OMSETNING_PKG.SETT_INN_MANUELT_BUDSJETT";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "AVDELING_OMSETNING_PKG_TEST.SETT_INN_MANUELT_BUDSJETT";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public SettInnManueltBudsjettStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_avdnr", Types.INTEGER));
		declareParameter(new SqlParameter("p_aar", Types.INTEGER));
		declareParameter(new SqlParameter("p_belop_aar", Types.DECIMAL));
		declareParameter(new SqlParameter("p_avdeling_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));

		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param userId
	 * @param avdnr
	 * @param aar
	 * @param belopAar
	 * @param avdelingId
	 * @param buntId
	 * @throws FrafException
	 */
	public void execute(final Integer userId, final Integer avdnr, final Integer aar,
			final BigDecimal belopAar, final Integer avdelingId, final Integer buntId)
			throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(5);
		inParams.put("p_user_id", userId);
		inParams.put("p_avdnr", avdnr);
		inParams.put("p_aar", aar);
		inParams.put("p_belop_aar", belopAar);
		inParams.put("p_avdeling_id", avdelingId);
		inParams.put("p_bunt_id", buntId);

		try {
			execute(inParams);
		} catch (DataAccessException e) {
			logger.error("Feil i execute",e);
			//e.printStackTrace();
			throw new FrafException(e);
		}
	}
	
	public enum SettInnManueltBudsjettStoredProcedureSql{
		SQL("AVDELING_OMSETNING_PKG.SETT_INN_MANUELT_BUDSJETT",false,false),
		SQL_TEST("AVDELING_OMSETNING_PKG_TEST.SETT_INN_MANUELT_BUDSJETT",true,false),
		SQL_STANDALONE("AVD_OMS_STANDALONE_PKG.SETT_INN_MANUELT_BUDSJETT",false,true),
		SQL_STANDALONE_TEST("AVD_OMS_STANDALONE_PKG_TEST.SETT_INN_MANUELT_BUDSJETT",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,SettInnManueltBudsjettStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,SettInnManueltBudsjettStoredProcedureSql>>();
		static{
			SettInnManueltBudsjettStoredProcedureSql[] list = SettInnManueltBudsjettStoredProcedureSql.values();
			for(SettInnManueltBudsjettStoredProcedureSql enumItem:list){
				Map<Boolean,SettInnManueltBudsjettStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, SettInnManueltBudsjettStoredProcedureSql>():map;
				map.put(enumItem.isStandalone, enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private SettInnManueltBudsjettStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static SettInnManueltBudsjettStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,SettInnManueltBudsjettStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
