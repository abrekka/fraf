package no.ica.fraf.dao.hibernate.impl;

import java.math.BigDecimal;

import junit.framework.TestCase;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

/**
 * Tester DAO BokfSelskapDAO
 * 
 * @author abr99
 * 
 */
public class BokfSelskapDAOHibernateTest extends TestCase {

	public BokfSelskapDAOHibernateTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	/**
	 * DAO for Bokfføringsselskap
	 */
	private BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
			.getBean("bokfSelskapDAO");

	/**
	 * Bokføringsselskap
	 */
	private BokfSelskap bokfSelskap;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// utilTest.setUp();
		super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// utilTest.tearDown();

		if (bokfSelskap.getSelskapId() != null) {
			bokfSelskapDAO.removeBokfSelskap(bokfSelskap.getSelskapId());
		}
		super.tearDown();
	}

	/**
	 * Test method for
	 * 'no.ica.fraf.dao.hibernate.BokfSelskapDAOHibernate.saveBokfSelskap(BokfSelskap)'
	 * 
	 * @throws Exception
	 */
	public void testSaveBokfSelskap() throws Exception {
		bokfSelskap = new BokfSelskap();
		bokfSelskap.setSelskapNavn("3000");
		bokfSelskap.setTilPs(0);
		bokfSelskap.setFakturaNr(new BigDecimal(552660000));

		bokfSelskapDAO.saveBokfSelskap(bokfSelskap);

		bokfSelskap = bokfSelskapDAO.findByName("3000");
		assertNotNull(bokfSelskap);
		assertEquals("3000", bokfSelskap.getSelskapNavn());
	}

}
