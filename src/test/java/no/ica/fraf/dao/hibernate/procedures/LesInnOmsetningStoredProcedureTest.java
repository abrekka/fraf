package no.ica.fraf.dao.hibernate.procedures;

import no.ica.fraf.dao.hibernate.procedures.LesInnOmsetningStoredProcedure.LesInnOmsetningStoredProcedureSql;
import junit.framework.TestCase;

public class LesInnOmsetningStoredProcedureTest extends TestCase {
	public void testGetLesInnOmsetningStoredProcedureSql(){
		LesInnOmsetningStoredProcedureSql enumItem=LesInnOmsetningStoredProcedureSql.getEnum(false, false);
		assertEquals("AVDELING_OMSETNING_PKG.LES_INN_OMSETNING", enumItem.getSql());
	}
	public void testGetLesInnOmsetningStoredProcedureSqlTest(){
		LesInnOmsetningStoredProcedureSql enumItem=LesInnOmsetningStoredProcedureSql.getEnum(true, false);
		assertEquals("AVDELING_OMSETNING_PKG_TEST.LES_INN_OMSETNING", enumItem.getSql());
	}
	public void testGetLesInnOmsetningStoredProcedureSqlStandalone(){
		LesInnOmsetningStoredProcedureSql enumItem=LesInnOmsetningStoredProcedureSql.getEnum(false, true);
		assertEquals("AVD_OMS_STANDALONE_PKG.LES_INN_OMSETNING", enumItem.getSql());
	}
	public void testGetLesInnOmsetningStoredProcedureSqlStandaloneTest(){
		LesInnOmsetningStoredProcedureSql enumItem=LesInnOmsetningStoredProcedureSql.getEnum(true, true);
		assertEquals("AVD_OMS_STANDALONE_PKG_TEST.LES_INN_OMSETNING", enumItem.getSql());
	}
}
