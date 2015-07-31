package no.ica.fraf.dao.hibernate.fenistra;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import junit.framework.TestCase;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.MrKontiOrg;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

/**
 * Testklasse for DAO for kontrakter i Fenistra
 * @author abr99
 *
 */
public class LkKontraktobjekterDAOHibernateTest extends TestCase {
	static {
		BaseManager.setTest(true);
	}
	/**
	 * DAO for kontrakter
	 */
	private LkKontraktobjekterDAO lkKontraktobjekterDAO = (LkKontraktobjekterDAO)ModelUtil.getBean("lkKontraktobjekterDAO");

	/**
	 * Test method for 'no.ica.fraf.dao.hibernate.fenistra.LkKontraktobjekterDAOHibernate.findAllIdByMrKonti(MrKonti, List)'
	 */
	public void testFindAllIdByMrKonti() {
		MrKontiOrg mrKonti = new MrKontiOrg();
		mrKonti.setKonto("Test");
		mrKonti.setKontogruppeId(6);
		mrKonti.setKontoId(13);
		mrKonti.setKontoNr(630053);
		
		List<String> deps = new ArrayList<String>();
		deps.add("1499");
		
		List<Integer> contractIds = lkKontraktobjekterDAO.findAllIdByMrKonti(mrKonti,deps);
		assertNotNull(contractIds);
		assertEquals(1,contractIds.size());
		
		contractIds = lkKontraktobjekterDAO.findAllIdByMrKonti(null,deps);
		assertNotNull(contractIds);
		assertEquals(4,contractIds.size());
	}
	
	/**
	 * Tester findByIds
	 */
	public void testFindByIds() {
		ArrayList<Integer> contractIds = new ArrayList<Integer>();
		contractIds.add(2077);
		
		List<LkKontraktobjekter> contracts = lkKontraktobjekterDAO.findByIds(contractIds);
		assertNotNull(contracts);
		assertEquals(1,contracts.size());
	}

}
