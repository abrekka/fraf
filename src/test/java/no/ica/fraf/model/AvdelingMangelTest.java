package no.ica.fraf.model;

import junit.framework.TestCase;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingMangel;
import no.ica.fraf.model.MangelType;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

/**
 * Tester AVDELING_MANGEL
 * @author abr99
 *
 */
public class AvdelingMangelTest extends TestCase {
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
		utilTest.setUp();
	}
	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		utilTest.tearDown();
	}
	
	/**
	 * Tester innlegging av ny mangeltype på avdeling
	 * @throws Exception
	 */
	public void testInsertAvdelingMangel()throws Exception{
		MangelType mangelType = utilTest.addMangelType("TEST","Test");
		Avdeling avdeling = utilTest.getAvdeling();
		AvdelingMangel avdelingMangel = new AvdelingMangel();
		avdelingMangel.setMangelType(mangelType);
		avdelingMangel.setAvdeling(avdeling);
		avdeling.addAvdelingMangel(avdelingMangel);
		utilTest.saveAvdeling(avdeling);
	}
	

}
