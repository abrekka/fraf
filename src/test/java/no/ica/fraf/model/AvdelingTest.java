package no.ica.fraf.model;

import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

/**
 * Testklasse for Avdeling
 * @author abr99
 *
 */
public class AvdelingTest extends TestCase {

	static {
		BaseManager.setTest(true);
	}
	/**
	 * DAO for avdeling
	 */
	private AvdelingDAO avdelingDAO = (AvdelingDAO)ModelUtil.getBean("avdelingDAO");
	
	/**
	 * Test method for 'no.ica.fraf.model.Avdeling.getEierforholds()'
	 */
	public void testGetEierforholds() {
		Avdeling avdeling = avdelingDAO.findByAvdnr(1499);
		assertNotNull(avdeling);
		
		Set set = avdeling.getEierforholds();
		assertNotNull(set);
	}
	
	/**
	 * Tester å hente eierforhold etter betingelser
	 */
	public void testGetEierforholdsAfterBetingelses() {
		Avdeling avdeling = avdelingDAO.findByAvdnr(1499);
		assertNotNull(avdeling);
		
		Set set = avdeling.getAvdelingBetingelses();
		assertNotNull(set);
		
		set = avdeling.getEierforholds();
		assertNotNull(set);
	}

}
