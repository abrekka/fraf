package no.ica.fraf.dao.hibernate.procedures;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.LesInnOmsetningStoredProcedure.LesInnOmsetningStoredProcedureSql;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;

//import org.springframework.jdbc.support.nativejdbc.CommonsDbcpNativeJdbcExtractor;

/**
 * Klasse som representerer den lagrede prosedyren
 * FRANCHISE_:PKG.FAKTURER_PERIODE
 * 
 * @author abr99
 * 
 */
public class FakturerPeriodeStoredProcedure extends StoredProcedure {
	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(FakturerPeriodeStoredProcedure.class);

	/**
	 * SQL for a kalle prosedyren
	 */
	//public static final String SQL = "FRANCHISE_PKG.FAKTURER_PERIODE";

	/**
	 * SQL for å kalle prosedyren i testdatabase
	 */
	//public static final String SQL_TEST = "FRANCHISE_PKG_TEST.FAKTURER_PERIODE";

	/**
	 * Konstruktør
	 * 
	 * @param jdbcTemplate
	 * @param sqlString
	 */
	public FakturerPeriodeStoredProcedure(JdbcTemplate jdbcTemplate,
			String sqlString) {
		super(jdbcTemplate, sqlString);
		setFunction(true);

		declareParameter(new SqlOutParameter("p_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_user_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_aar", Types.INTEGER));
		declareParameter(new SqlParameter("p_periode", Types.INTEGER));
		declareParameter(new SqlParameter("p_fra_avdnr", Types.INTEGER));
		declareParameter(new SqlParameter("p_til_avdnr", Types.INTEGER));
		declareParameter(new SqlParameter("p_faktura_dato", Types.DATE));
		declareParameter(new SqlParameter("p_forfall_dato", Types.DATE));
		declareParameter(new SqlParameter("p_avregning_basis_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_betingelse_gruppe_id",Types.INTEGER));
		declareParameter(new SqlParameter("p_betingelse_grupper",Types.ARRAY));

		declareParameter(new SqlParameter("l_bunt_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_betingelse_type_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_selskap_id", Types.INTEGER));
		declareParameter(new SqlParameter("p_not_departments", Types.ARRAY));
		declareParameter(new SqlParameter("p_avregning_type_id", Types.INTEGER));
		compile();
	}

	/**
	 * Kjører prosedyre
	 * 
	 * @param userId
	 * @param aar
	 * @param periode
	 * @param fraAvdNr
	 * @param tilAvdNr
	 * @param fakturaDato
	 * @param forfallDato
	 * @param avregningBasisId
	 * @param gruppeId
	 * @param betingelseTypeId
	 * @param notDepartments
	 * @param avregningType
	 * @param selskapId
	 * @return bunt id
	 * @throws FrafException
	 */
	public Integer execute(final Integer userId, final Integer aar,
			final Integer periode, final Integer fraAvdNr,
			final Integer tilAvdNr, final Date fakturaDato,
			final Date forfallDato, final Integer avregningBasisId,
			final Integer gruppeId,
			final Integer[] betingelseGrupper,
			final Integer betingelseTypeId,
			final Integer[] notDepartments, final Integer avregningType,
			final Integer selskapId) throws FrafException {
		Connection conn = null;
		ARRAY arrayNotDepartments = null;
		ARRAY arrayBetingelseGrupper = null;
		try {
			conn = this.getJdbcTemplate().getDataSource().getConnection();

			C3P0NativeJdbcExtractor extractor = new C3P0NativeJdbcExtractor();
			// CommonsDbcpNativeJdbcExtractor extractor = new
			// CommonsDbcpNativeJdbcExtractor();
			conn = extractor.getNativeConnection(conn);

			final ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
					"AVDNR_TAB_TYPE", conn);
			arrayNotDepartments = new ARRAY(desc, conn, notDepartments);
			arrayBetingelseGrupper = new ARRAY(desc, conn, betingelseGrupper);
		} catch (SQLException e1) {
			logger.error("Feil i execute", e1);
			// e1.printStackTrace();
		}
		final Map<String, Object> inParams = new HashMap<String, Object>(7);
		final Integer buntId = Integer.valueOf(0);
		inParams.put("p_bunt_id", buntId);
		inParams.put("p_user_id", userId);
		inParams.put("p_aar", aar);
		inParams.put("p_periode", periode);
		inParams.put("p_fra_avdnr", fraAvdNr);
		inParams.put("p_til_avdnr", tilAvdNr);
		inParams.put("p_faktura_dato", fakturaDato);
		inParams.put("p_forfall_dato", forfallDato);
		inParams.put("p_avregning_basis_id", avregningBasisId);
		inParams.put("p_betingelse_gruppe_id", gruppeId);
		inParams.put("p_betingelse_grupper", arrayBetingelseGrupper);
		inParams.put("l_bunt_id", null);
		inParams.put("p_betingelse_type_id", betingelseTypeId);
		inParams.put("p_selskap_id", selskapId);
		inParams.put("p_not_departments", arrayNotDepartments);
		inParams.put("p_avregning_type_id", avregningType);

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
	
	public enum FakturerPeriodeStoredProcedureSql{
		SQL("FRANCHISE_PKG.FAKTURER_PERIODE",false,false),
		SQL_TEST("FRANCHISE_PKG_TEST.FAKTURER_PERIODE",true,false),
		SQL_STANDALONE("FRANCHISE_STANDALONE_PKG.FAKTURER_PERIODE",false,true),
		SQL_STANDALONE_TEST("FRANCHISE_STANDALONE_PKG_TEST.FAKTURER_PERIODE",true,true);
		private String sqlString;
		private boolean isTest;
		private boolean isStandalone;
		private static final Map<Boolean,Map<Boolean,FakturerPeriodeStoredProcedureSql>> sqlMap=new Hashtable<Boolean, Map<Boolean,FakturerPeriodeStoredProcedureSql>>();
		static{
			FakturerPeriodeStoredProcedureSql[] list = FakturerPeriodeStoredProcedureSql.values();
			for(FakturerPeriodeStoredProcedureSql enumItem:list){
				Map<Boolean,FakturerPeriodeStoredProcedureSql> map=sqlMap.get(enumItem.isTest());
				map=map==null?new Hashtable<Boolean, FakturerPeriodeStoredProcedureSql>():map;
				map.put(enumItem.isStandalone(), enumItem);
				sqlMap.put(enumItem.isTest(), map);
			}
		}
		private FakturerPeriodeStoredProcedureSql(String aSql,boolean test,boolean standalone){
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
		
		public static FakturerPeriodeStoredProcedureSql getEnum(boolean test,boolean standalone){
			Map<Boolean,FakturerPeriodeStoredProcedureSql> testMap=sqlMap.get(test);
			return testMap.get(standalone);
		}
	}
}
