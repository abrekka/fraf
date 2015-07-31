package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


/**
 * Representerer den lagrede prosedyren LES_INN_OMSETNING i pakken
 * AVDELING_OMSETNING
 * 
 * @author abr99
 * 
 */
public class LesInnOmsetningStoredProcedure extends StoredProcedure {
	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(LesInnOmsetningStoredProcedure.class);

	/**
	 * SQl for å kalle prosedyre
	 */
	//public static final String SQL = "AVDELING_OMSETNING_PKG.LES_INN_OMSETNING";

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "AVDELING_OMSETNING_PKG_TEST.LES_INN_OMSETNING";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public LesInnOmsetningStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(true);

		declareParameter(new SqlOutParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_avregning_basis_type_id",
				Types.INTEGER));
		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_aar", Types.INTEGER));
		declareParameter(new SqlParameter("p_periode", Types.INTEGER));
		declareParameter(new SqlParameter("p_fra_avdnr", Types.INTEGER));
		declareParameter(new SqlParameter("p_til_avdnr", Types.INTEGER));

		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param basisTypeId
	 * @param userId
	 * @param aar
	 * @param periode
	 * @param fraAvdNr
	 * @param tilAvdNr
	 * @return bunt id
	 * @throws FrafException
	 */
	public Integer execute(final Integer basisTypeId, final Integer userId,
			final Integer aar, final Integer periode, final Integer fraAvdNr,
			final Integer tilAvdNr) throws FrafException {
		final Map<String, Object> inParams = new HashMap<String, Object>(7);

		inParams.put("p_avregning_basis_type_id", basisTypeId);
		inParams.put("p_user_id", userId);
		inParams.put("p_aar", aar);
		inParams.put("p_periode", periode);
		inParams.put("p_fra_avdnr", fraAvdNr);
		inParams.put("p_til_avdnr", tilAvdNr);

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
	
	public enum LesInnOmsetningStoredProcedureSql{
		SQL("AVDELING_OMSETNING_PKG.LES_INN_OMSETNING",false,false),
		SQL_TEST("AVDELING_OMSETNING_PKG_TEST.LES_INN_OMSETNING",true,false),
		SQL_STANDALONE("AVD_OMS_STANDALONE_PKG.LES_INN_OMSETNING",false,true),
		SQL_STANDALONE_TEST("AVD_OMS_STANDALONE_PKG_TEST.LES_INN_OMSETNING",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,LesInnOmsetningStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,LesInnOmsetningStoredProcedureSql>>();
		static{
			LesInnOmsetningStoredProcedureSql[] list = LesInnOmsetningStoredProcedureSql.values();
			for(LesInnOmsetningStoredProcedureSql enumItem:list){
				Map<Boolean,LesInnOmsetningStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, LesInnOmsetningStoredProcedureSql>():map;
				map.put(enumItem.isStandalone, enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private LesInnOmsetningStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static LesInnOmsetningStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,LesInnOmsetningStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
