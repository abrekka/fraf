package no.ica.fraf.dao.hibernate.procedures;


import java.sql.Date;
import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.RegenererStoredProcedure.RegenererStoredProcedureSql;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Representerer den lagrede prosedyren BOKFOR_BUNT i pakken REGNSKAP_PKG
 * 
 * @author abr99
 * 
 */
public class BokforBuntStoredProcedure extends StoredProcedure {
	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(BokforBuntStoredProcedure.class);
	/**
	 * SQL for å kalle prosedyre i databasen
	 */
	//public static final String SQL = "REGNSKAP_PKG_tmp.BOKFOR_BUNT";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "REGNSKAP_PKG_TEST.BOKFOR_BUNT";

	/**
	 * SQL for kalle prosedyre i test_test database
	 */
	//public static final String SQL_TEST_TEST = "TEST_REGNSKAP_PKG.BOKFOR_BUNT";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public BokforBuntStoredProcedure(JdbcTemplate jdbcTemplate, String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(false);

		declareParameter(new SqlParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_bokfor_dato", Types.DATE));

		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param buntId
	 *            bunt som skal bokføres
	 * @param bokforDato
	 *            bokføringsdato
	 * @throws FrafException
	 */
	public void execute(final Integer buntId, final Date bokforDato) throws FrafException {
		final Map<String,Object> inParams = new HashMap<String,Object>(2);
		inParams.put("p_bunt_id", buntId);
		inParams.put("p_bokfor_dato", bokforDato);

		try {
			execute(inParams);
		} catch (DataAccessException e) {
			logger.error("Feil i execute",e);
			//e.printStackTrace();
			throw new FrafException(e);
		}
	}
	
	public enum BokforBuntStoredProcedureSql{
		SQL("REGNSKAP_PKG_tmp.BOKFOR_BUNT",false,false),
		SQL_TEST("REGNSKAP_PKG_TEST.BOKFOR_BUNT",true,false),
		SQL_STANDALONE("REGNSKAP_STANDALONE_PKG_tmp.BOKFOR_BUNT",false,true),
		SQL_STANDALONE_TEST("REGNSKAP_STANDALONE_PKG_TEST.BOKFOR_BUNT",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,BokforBuntStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,BokforBuntStoredProcedureSql>>();
		static{
			BokforBuntStoredProcedureSql[] list = BokforBuntStoredProcedureSql.values();
			for(BokforBuntStoredProcedureSql enumItem:list){
				Map<Boolean,BokforBuntStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, BokforBuntStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private BokforBuntStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static BokforBuntStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,BokforBuntStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
