package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.dao.hibernate.procedures.LesInnOmsetningStoredProcedure.LesInnOmsetningStoredProcedureSql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Representerer den lagrede prosedyren IMPORTER_BETINGELSER i pakken
 * IMPORT_BETINGELSE_PKG
 * 
 * @author abr99
 * 
 */
public class ImportBetingelseStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prodesyre
	 */
	//public static final String SQL = "IMPORT_BETINGELSE_PKG.IMPORTER_BETINGELSER";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "IMPORT_BETINGELSE_PKG_TEST.IMPORTER_BETINGELSER";

	/**
	 * SQL for å kalle prosedyre i test_test database
	 */
	//public static final String SQL_TEST_TEST = "TEST_IMPORT_BETINGELSE_PKG.IMPORTER_BETINGELSER";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public ImportBetingelseStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param userId
	 */
	public void execute(final Integer userId) {
		final Map<String, Object> inParams = new HashMap<String, Object>(1);
		inParams.put("p_user_id", userId);
		execute(inParams);

	}
	
	public enum ImportBetingelseStoredProcedureSql{
		SQL("IMPORT_BETINGELSE_PKG.IMPORTER_BETINGELSER",false,false),
		SQL_TEST("IMPORT_BETINGELSE_PKG_TEST.IMPORTER_BETINGELSER",true,false),
		SQL_STANDALONE("IMPORT_BET_STANDALONE_PKG.IMPORTER_BETINGELSER",false,true),
		SQL_STANDALONE_TEST("IMPORT_BET_STANDALONE_PKG_TEST.IMPORTER_BETINGELSER",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,ImportBetingelseStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,ImportBetingelseStoredProcedureSql>>();
		static{
			ImportBetingelseStoredProcedureSql[] list = ImportBetingelseStoredProcedureSql.values();
			for(ImportBetingelseStoredProcedureSql enumItem:list){
				Map<Boolean,ImportBetingelseStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, ImportBetingelseStoredProcedureSql>():map;
				map.put(enumItem.isStandalone, enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private ImportBetingelseStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static ImportBetingelseStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,ImportBetingelseStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
