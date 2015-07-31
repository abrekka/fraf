package no.ica.fraf.model;

import java.math.BigDecimal;

import junit.framework.TestCase;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

/**
 * Test for AvdelingOmsetning
 * @author abr99
 *
 */
public class AvdelingOmsetningTest extends TestCase {
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
	 * Test method for 'no.ica.fraf.model.AvdelingOmsetning.setKorreksjonBelop(BigDecimal)'
	 */
	public void testSetKorreksjonBelop() {
		AvdelingOmsetning avdelingOmsetning = utilTest.addAvdelingOmsetning(2006,BigDecimal.valueOf(1000),1,null);
		avdelingOmsetning.setKorreksjonBelop(BigDecimal.valueOf(-100));
		utilTest.saveAvdelingOmsetning(avdelingOmsetning);
		
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO)ModelUtil.getBean("avdelingOmsetningDAO");
		avdelingOmsetning = avdelingOmsetningDAO.getAvdelingOmsetning(avdelingOmsetning.getAvdelingOmsetningId());
		
		assertEquals(BigDecimal.valueOf(-100),avdelingOmsetning.getKorreksjonBelop());
		assertEquals(BigDecimal.valueOf(900),avdelingOmsetning.getKorrigertBelop());
	}

}
