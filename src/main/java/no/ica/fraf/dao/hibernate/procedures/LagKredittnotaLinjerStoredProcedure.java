package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.LagKredittnotaStoredProcedure.LagKredittnotaStoredProcedureSql;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;
/**
 * Kjører prosedyre LAG_KREDITTNOTA_LINJER i pakke FRANCHISE_PKG
 * 
 * @author abr99
 * 
 */
public class LagKredittnotaLinjerStoredProcedure extends StoredProcedure {
	/**
	 * SQL for å kalle prosedyre
	 */
	//public static final String SQL = "FRANCHISE_PKG.lag_kredittnota_linjer";

	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(LagKredittnotaLinjerStoredProcedure.class);

	/**
	 * SQL for å kalle prosedyre i testdatabase
	 */
	//public static final String SQL_TEST = "FRANCHISE_PKG_TEST.lag_kredittnota_linjer";


	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public LagKredittnotaLinjerStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(true);

		declareParameter(new SqlOutParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_faktura_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_faktura_dato", Types.DATE));
		declareParameter(new SqlParameter("p_forfall_dato", Types.DATE));
		declareParameter(new SqlParameter("p_faktura_linje_ider", Types.ARRAY));

		compile();
	}

	/**
	 * Utfører prosedyre
	 * 
	 * @param userId
	 * @param fakturaId
	 * @param invoiceDate
	 * @param dueDate
	 * @param fakturaLinjeIder 
	 * @return buntid
	 * @throws FrafException
	 */
	public Integer execute(final Integer userId, final Integer fakturaId,
			final java.sql.Date invoiceDate, final java.sql.Date dueDate,final Integer[] fakturaLinjeIder)
			throws FrafException {
		Connection conn = null;
		ARRAY array = null;
		try {
			conn = this.getJdbcTemplate().getDataSource().getConnection();

			C3P0NativeJdbcExtractor extractor = new C3P0NativeJdbcExtractor();
			// CommonsDbcpNativeJdbcExtractor extractor = new
			// CommonsDbcpNativeJdbcExtractor();
			conn = extractor.getNativeConnection(conn);

			final ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
					"AVDNR_TAB_TYPE", conn);
			array = new ARRAY(desc, conn, fakturaLinjeIder);
		} catch (SQLException e1) {
			logger.error("Feil i execute", e1);
			// e1.printStackTrace();
		}
		final Map<String, Object> inParams = new HashMap<String, Object>(4);
		inParams.put("p_user_id", userId);
		inParams.put("p_faktura_id", fakturaId);
		inParams.put("p_faktura_dato", invoiceDate);
		inParams.put("p_forfall_dato", dueDate);
		inParams.put("p_faktura_linje_ider", array);

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
	
	public enum LagKredittnotaLinjerStoredProcedureSql{
		SQL("FRANCHISE_PKG.lag_kredittnota_linjer",false,false),
		SQL_TEST("FRANCHISE_PKG_TEST.lag_kredittnota_linjer",true,false),
		SQL_STANDALONE("FRANCHISE_STANDALONE_PKG.lag_kredittnota_linjer",false,true),
		SQL_STANDALONE_TEST("FRANCHISE_STANDALONE_PKG_TEST.lag_kredittnota_linjer",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,LagKredittnotaLinjerStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,LagKredittnotaLinjerStoredProcedureSql>>();
		static{
			LagKredittnotaLinjerStoredProcedureSql[] list = LagKredittnotaLinjerStoredProcedureSql.values();
			for(LagKredittnotaLinjerStoredProcedureSql enumItem:list){
				Map<Boolean,LagKredittnotaLinjerStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, LagKredittnotaLinjerStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private LagKredittnotaLinjerStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static LagKredittnotaLinjerStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,LagKredittnotaLinjerStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
