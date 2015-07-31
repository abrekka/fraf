package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

/**
 * Tester faktura
 * @author abr99
 *
 */
public class FakturaTest extends TestCase {
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
	 * Tester at fakturanummer blir rullet tilbake ved tilbakerulling av fakturabunt
	 * @throws Exception
	 */
	public void test16049RollbackInvoiceNr()throws Exception{
		utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2005.10.01",
				"2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");

		Integer buntId = utilTest.fakturerPeriode(2005,
				10, 10, "Diverse",3);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());
		
		String fakturaNr = list.get(0).getFakturaNr();
		
		utilTest.slettBunt(buntId);

		buntId = utilTest.fakturerPeriode(2005, 10,
				10, "Diverse",3);
		utilTest.addBunt(buntId);

		list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		assertEquals(fakturaNr,list.get(0).getFakturaNr());
	}
	
	/**
	 * Tester at det ikke er mulig å rulle tilbake en bunt som ikke er den siste bunten
	 * Det er nå lov å rulle tilbake bunter som ikke er sist fordi fakturanummer ettes ikke før bokføring
	 * @throws Exception
	 */
	public void test16049RollbackInbetween()throws Exception{
		utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2005.01.01",
				"2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");

		Integer buntIdRollback = utilTest.fakturerPeriode(2005,
				10, 10, "Diverse",3);
		utilTest.addBunt(buntIdRollback);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntIdRollback);
		assertEquals(1, list.size());
		
		

		Integer buntId = utilTest.fakturerPeriode(2005, 11,
				11, "Diverse",3);
		utilTest.addBunt(buntId);

		list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

			utilTest.slettBunt(buntIdRollback);
	}

	
}
