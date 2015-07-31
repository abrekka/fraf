package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

public class InvoiceGroupsTest extends TestCase {
	public InvoiceGroupsTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}

	private UtilTest utilTest = new UtilTest();

	protected void setUp() throws Exception {
		super.setUp();
		utilTest.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {

		utilTest.removeBunter();

		utilTest.tearDown();

		super.tearDown();

	}

	/**
	 * Tester at avregning ikke kommer med i måned to dersom hele omsetning er
	 * lest inn for fakturering av grupper
	 * 
	 * @throws Exception
	 */
	public void testInvoiceTwiceSameMonth() throws Exception {
		utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2008.01.01",
				"2008.12.31", "ETT", null, new BigDecimal(3.5), null, null);
		utilTest
				.addAvdelingBetingelse("MND", "TOMRA", 0, "2008.01.01",
						"2008.12.31", "ETT", BigDecimal.valueOf(1000), null,
						null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2008.12.31");

		utilTest.addAvdelingOmsetning(2008, BigDecimal.valueOf(1000), 1, null);

		Integer buntId = utilTest.fakturerPeriode(2008, 1, 1, null, 1,
				new Integer[] { 1, 5 });
		utilTest.addBunt(buntId);

		utilTest.addAvdelingOmsetningKorreksjon(2008, BigDecimal.valueOf(100),
				1);
		utilTest.addAvdelingOmsetning(2008, BigDecimal.valueOf(2000), 2, null);

		buntId = utilTest.fakturerPeriode(2008, 2, 2, null, 1, new Integer[] {
				1, 5 });
		utilTest.addBunt(buntId);
		utilTest.addAvdelingOmsetningKorreksjon(2008, BigDecimal.valueOf(200),
				2);

		utilTest.addAvdelingBetingelse("MND", "HUS", 0, "2008.01.01",
				"2008.12.31", "ETT", null, new BigDecimal(3.5), null, null);

		buntId = utilTest.fakturerPeriode(2008, 1, 1, null, 1, new Integer[] {
				1, 5 });
		utilTest.addBunt(buntId);

		buntId = utilTest.fakturerPeriode(2008, 2, 2, null, 1, new Integer[] {
				1, 5 });
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		Faktura faktura = list.get(0);
		utilTest
				.lazyLoadFaktura(
						faktura,
						new LazyLoadFakturaEnum[] { LazyLoadFakturaEnum.LOAD_INVOICE_LINES });
		Set<FakturaLinje> linjer = faktura.getFakturaLinjes();

		assertEquals(1, linjer.size());
		FakturaLinje linje = linjer.iterator().next();

		assertEquals(BigDecimal.valueOf(0), linje.getAvregningBelop());

	}

	/**
	 * Tester at faktura for betingelse som ikke slutter sist i måneden ikk får
	 * full faktura
	 * 
	 * Må kjøres separat
	 * 
	 * @throws Exception
	 */
	public void testInvoiceNotLastInMonth() throws Exception {
		utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2006.01.01",
				"2006.01.20", "ETT", new BigDecimal(1000), null, null, null);
		utilTest
				.addAvdelingBetingelse("MND", "DISTR14", 0, "2006.01.01",
						"2006.12.31", "ETT", BigDecimal.valueOf(2000), null,
						null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
				null, 3, new Integer[] { 2, 7 });
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());
		assertNull(list.get(0).getFakturaNr());

		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());
		assertEquals(2, linjer.size());

		for (FakturaLinje linje : linjer) {
			if (linje.getLinjeBeskrivelse()
					.equalsIgnoreCase("Kontottekvisita ")) {
				BigDecimal belop = BigDecimal.valueOf(1000);
				BigDecimal days = BigDecimal.valueOf(31);
				BigDecimal dayBelop = belop.divide(days, new MathContext(100));
				BigDecimal belopDays = BigDecimal.valueOf(20);
				BigDecimal wantedResult = dayBelop.multiply(belopDays,
						MathContext.DECIMAL32);
				wantedResult = wantedResult.round(new MathContext(5,
						RoundingMode.HALF_EVEN));

				BigDecimal mva = wantedResult
						.multiply(BigDecimal.valueOf(0.25)).round(
								new MathContext(5, RoundingMode.HALF_EVEN));
				BigDecimal total = wantedResult.add(mva).round(
						new MathContext(6, RoundingMode.HALF_EVEN));

				assertEquals(new BigDecimal(0), linje.getSats());
				assertEquals("Kontorrekvisita ", linje.getLinjeBeskrivelse());
				assertEquals(wantedResult, linje.getBelop());
				assertEquals(mva, linje.getMvaBelop());
				assertEquals(total, linje.getTotalBelop());
				assertEquals(total, linje.getTotalBelop());
			}
		}

	}
	 public void testInvoiceKredittnotaRollbackKredittnota() throws Exception {
	        // Avdeling avdeling = utilTest.findAvdeling(5952);

	        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2006.01.01",
	                "2006.12.31", "ETT", new BigDecimal(1000), null, null, null);
	        utilTest
			.addAvdelingBetingelse("MND", "DISTR14", 0, "2006.01.01",
					"2006.12.31", "ETT", BigDecimal.valueOf(2000), null,
					null, null);
	        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 4, 4,
	        		null, 3, new Integer[] { 2, 7 });
	        utilTest.addBunt(buntId);

	        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
	        assertEquals(1, list.size());
	        assertNull(list.get(0).getFakturaNr());

	        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
	                .get(0).getFakturaId());
	        assertEquals(2, linjer.size());

	        utilTest.bokfoerBunt(buntId, "2006.04.30");

	        list = utilTest.findFakturaerByBuntId(buntId);
	        assertEquals(1, list.size());
	        assertNotNull(list.get(0).getFakturaNr());

	        Integer buntIdKredittnota = utilTest.lagKredittnota(list.get(0)
	                .getFakturaId());
	        // utilTest.addBunt(buntIdKredittnota);

	        utilTest.slettBunt(buntIdKredittnota);

	        buntIdKredittnota = utilTest.lagKredittnota(list.get(0).getFakturaId());
	        utilTest.addBunt(buntIdKredittnota);

	      
	    }

}
