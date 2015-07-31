package no.ica.fraf.gui.importing;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.ImportBetingelseDAO;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

/**
 * Testklasse for ImportCondition
 * @author abr99
 *
 */
public class ImportConditionTest extends TestCase {
	static {
		BaseManager.setTest(true);
	}
	
	/**
	 * Hjelpeklasse
	 */
	private UtilTest utilTest = new UtilTest();
	
	

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		ImportBetingelseDAO importBetingelseDAO = (ImportBetingelseDAO)ModelUtil.getBean("importBetingelseDAO");
		importBetingelseDAO.deleteAll();
		utilTest.setUp();
		super.setUp();
	}



	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		ImportBetingelseDAO importBetingelseDAO = (ImportBetingelseDAO)ModelUtil.getBean("importBetingelseDAO");
		importBetingelseDAO.deleteAll();
		utilTest.tearDown();
		super.tearDown();
	}



	/**
	 * Test method for 'no.ica.fraf.gui.importing.ImportCondition.doImport(boolean, File, boolean, Object)'
	 */
	public void testDoImportCondition() {
		ApplUserDAO applUserDAO = (ApplUserDAO)ModelUtil.getBean("applUserDAO");
		AvdelingDAO avdelingDAO = (AvdelingDAO)ModelUtil.getBean("avdelingDAO");
		ApplUser applUser = applUserDAO.findByUser("abr99",null);
		ImportCondition importCondition = new ImportCondition(null,applUser);
		URL fileUrl = getClass().getClassLoader().getResource("import_condition_test.csv");
		File importFile = new File(fileUrl.getPath());
		assertEquals(true,importFile.exists());
		importCondition.doImport(true,importFile,true,null);
		
		ImportBetingelseDAO importBetingelseDAO = (ImportBetingelseDAO)ModelUtil.getBean("importBetingelseDAO");
		List list = importBetingelseDAO.findAll();
		assertEquals(0,list.size());
		
		Avdeling avdeling = avdelingDAO.findByAvdnr(9999);
		assertNotNull(avdeling);
		
		avdelingDAO.loadLazy(avdeling,new LazyLoadAvdelingEnum[]{LazyLoadAvdelingEnum.LOAD_CONDITION});
		Set<AvdelingBetingelse> betingelser = avdeling.getAvdelingBetingelses();
		assertEquals(1,betingelser.size());
		AvdelingBetingelse avdelingBetingelse = betingelser.iterator().next();
		
		assertEquals(BigDecimal.valueOf(1125),avdelingBetingelse.getBelop());
		assertEquals("MND",avdelingBetingelse.getAvregningFrekvensType().getAvregningFrekvensTypeKode());
		assertEquals("Basisservice avg à 1125 kr pr automat mars 06",avdelingBetingelse.getTekst());
		assertNull(avdelingBetingelse.getFakturaTekst());
		assertEquals("100",avdelingBetingelse.getBokfSelskap().getSelskapNavn());
		assertEquals("TOMRAL",avdelingBetingelse.getBetingelseType().getBetingelseTypeKode());
		
	}
	
	/**
	 * Tester import av betingelser som ibbeholder info om konto,avdeling og momskode
	 */
	public void testDoImportConditionDiv() {
		ApplUserDAO applUserDAO = (ApplUserDAO)ModelUtil.getBean("applUserDAO");
		AvdelingDAO avdelingDAO = (AvdelingDAO)ModelUtil.getBean("avdelingDAO");
		ApplUser applUser = applUserDAO.findByUser("abr99",null);
		ImportCondition importCondition = new ImportCondition(null,applUser);
		URL fileUrl = getClass().getClassLoader().getResource("import_div_test.csv");
		File importFile = new File(fileUrl.getPath());
		assertEquals(true,importFile.exists());
		importCondition.doImport(true,importFile,true,null);
		
		ImportBetingelseDAO importBetingelseDAO = (ImportBetingelseDAO)ModelUtil.getBean("importBetingelseDAO");
		List list = importBetingelseDAO.findAll();
		assertEquals(0,list.size());
		
		Avdeling avdeling = avdelingDAO.findByAvdnr(9999);
		assertNotNull(avdeling);
		
		avdelingDAO.loadLazy(avdeling,new LazyLoadAvdelingEnum[]{LazyLoadAvdelingEnum.LOAD_CONDITION});
		Set<AvdelingBetingelse> betingelser = avdeling.getAvdelingBetingelses();
		assertEquals(1,betingelser.size());
		AvdelingBetingelse avdelingBetingelse = betingelser.iterator().next();
		
		assertEquals(BigDecimal.valueOf(-1738.53),avdelingBetingelse.getBelop());
		assertEquals("MND",avdelingBetingelse.getAvregningFrekvensType().getAvregningFrekvensTypeKode());
		assertEquals("Svinnkompensasjon februar 2006",avdelingBetingelse.getTekst());
		assertEquals("1% av kjøp Frukt&Grønt ",avdelingBetingelse.getFakturaTekst());
		assertEquals("110",avdelingBetingelse.getBokfSelskap().getSelskapNavn());
		assertEquals("FEL",avdelingBetingelse.getBetingelseType().getBetingelseTypeKode());
		assertNotNull(avdelingBetingelse.getKonto());
		assertEquals("910140",avdelingBetingelse.getKonto());
		assertEquals("10133180",avdelingBetingelse.getBokfAvdeling());
		assertEquals("13",avdelingBetingelse.getMva().getMvaKode());
	}
	
	/*public void testImportSiemens(){
		ApplUserDAO applUserDAO = (ApplUserDAO)ModelUtil.getBean("applUserDAO");
		
		ApplUser applUser = applUserDAO.findByUser("abr99",null);
		ImportCondition importCondition = new ImportCondition(null,applUser);
		URL fileUrl = getClass().getClassLoader().getResource("Siemens.sdv");
		File importFile = new File(fileUrl.getPath());
		assertEquals(true,importFile.exists());
		Object errorMsg=importCondition.doImport(true,importFile,true,null);
		assertNull(errorMsg);
		
	}*/

}
