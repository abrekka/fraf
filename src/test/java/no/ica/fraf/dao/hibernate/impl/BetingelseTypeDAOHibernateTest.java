package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.Chain;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

/**
 * Tester DAO BetingelseTypeDAO
 * 
 * @author abr99
 * 
 */
public class BetingelseTypeDAOHibernateTest extends TestCase {
	

	/**
	 * Hjelpeklasse
	 */
	private UtilTest utilTest = new UtilTest();

	/**
	 * DAO for betingelsetype
	 */
	private BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
			.getBean("betingelseTypeDAO");

	/**
	 * Betingelsetype
	 */
	private BetingelseType betingelseType;
	private ChainDAO chainDAO;
	private boolean standalone;
	
	public BetingelseTypeDAOHibernateTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
        standalone=isStandalone;
	}

	@Override
	protected void setUp() throws Exception {
		FrafMain.setStandalone(standalone);
		chainDAO = (ChainDAO)ModelUtil.getBean(ChainDAO.DAO_NAME);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		if (betingelseType != null) {
			betingelseTypeDAO.removeBetingelseType(betingelseType
					.getBetingelseTypeId());
		}
		super.tearDown();
	}

	/**
	 * Test method for
	 * 'no.ica.fraf.dao.hibernate.BetingelseTypeDAOHibernate.saveBetingelseType(BetingelseType)'
	 */
	public void testSaveBetingelseType() {
		BetingelseGruppe betingelseGruppe = utilTest
				.findBetingelseGruppe("Diverse");
		betingelseType = new BetingelseType();
		//betingelseType.setIsDebit(1);
		betingelseType.setBetingelseGruppe(betingelseGruppe);
		betingelseType.setInntektskontoE("111111");
		betingelseType.setBetingelseNavn("TEST");
		betingelseType.setBetingelseTypeKode("TEST");
		betingelseType.setLinkAvtale(1);

		betingelseTypeDAO.saveBetingelseType(betingelseType);

		betingelseType = betingelseTypeDAO.findByKode("TEST");
		assertNotNull(betingelseType);

	}
	
	public void testGetBetingelseType(){
		BetingelseType betingelseType=betingelseTypeDAO.getBetingelseType(47);
		assertNotNull(betingelseType);
		assertEquals("B25SLD", betingelseType.getBetingelseTypeKode());
	}

	public void testFindAll(){
		List<BetingelseType> list = betingelseTypeDAO.findAll();
		assertNotNull(list);
		assertEquals(true, list.size()>140);
	}
	
	public void testFindAllFranchise(){
		List<BetingelseType> list = betingelseTypeDAO.findAllFranchise();
		assertNotNull(list);
		assertEquals(true, list.size()>10);
	}
	public void testFindAllOthers(){
		List<BetingelseType> list = betingelseTypeDAO.findAllOthers();
		assertNotNull(list);
		assertEquals(true, list.size()>10);
	}
	
	public void testFindByKode(){
		BetingelseType betingelseType=betingelseTypeDAO.findByKode("TOMRAS");
		assertNotNull(betingelseType);
		assertEquals("Tomra Serviceavgift", betingelseType.getBetingelseNavn());
	}
	
	public void testFindByGroupName(){
		List<BetingelseType> list = betingelseTypeDAO.findByGroupName("Diverse");
		assertNotNull(list);
		assertEquals(true, list.size()>10);
	}
	public void testFindByGroupNameAndKjede(){
		
		Chain chain = chainDAO.findByName("RIMI NORGE");
		List<BetingelseType> list=betingelseTypeDAO.findByGroupNameAndKjede("Regnskap", chain);
		assertNotNull(list);
		assertEquals(true, list.size()>10);
	}
	public void testFindByKodeKjede(){
		Chain chain = chainDAO.findByName("RIMI NORGE");
		List<BetingelseType> list=betingelseTypeDAO.findByKodeKjede(null,"RIMIEPF", chain);
		assertNotNull(list);
		assertEquals(true, list.size()>0);
	}
	public void testFindAllAktiv(){
		List<BetingelseType> list =  betingelseTypeDAO.findAllAktiv();
		assertNotNull(list);
		assertEquals(true, list.size()>10);
	}
	
}
