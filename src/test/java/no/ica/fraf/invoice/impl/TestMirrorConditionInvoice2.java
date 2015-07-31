package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.BuntFeil;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaFeil;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

public class TestMirrorConditionInvoice2 extends TestCase {
	public TestMirrorConditionInvoice2(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}

	/**
	 * Hjelpeklasse
	 */
	private UtilTest utilTest = new UtilTest();

	/**
	 * 
	 */
	private Avdeling testAvdeling;

	

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		utilTest.setUp();
		super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		if (testAvdeling != null) {
			utilTest.removeAvdeling(testAvdeling);
		}
		utilTest.tearDown();
		super.tearDown();
	}


	/**
	 * Tester at fakturering av speilet betingelse tar med seg kostnad som
	 * ligger tilbake i tid, dette er tatt bort og kun de som ligger innenfor periode skal tas med
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceIncludeOldEtt() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.01.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
        utilTest.addSpeiletKostnad("2005.09.01", "2005.09.30", BigDecimal.valueOf(1000), 100,
                speiletBetingelse);
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 200,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2005.11.01", "2005.11.30", BigDecimal.valueOf(2000), 300,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 11, 11,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(1, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(1, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());
		assertEquals(1, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		/*assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals("05/10", fakturaLinje.getPeriode());

		fakturaLinje = linjer.get(1);*/

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(2000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(500), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(2500), fakturaLinje.getTotalBelop());
        assertEquals("05/11", fakturaLinje.getPeriode());
        
/*        fakturaLinje = linjer.get(2);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals("05/9", fakturaLinje.getPeriode());*/
	}
    

    /**
     * Tester at fakturering av speilet betingelse tar med seg kostnad som
     * ligger tilbake i tid, dette er tatt bort kun de som ligger innenfor periode skal tas med
     * 
     * @throws Exception
     */
    public void testMirrorInvoiceIncludeOldFor() throws Exception {
        AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
                "KVA", "FEL", 1, "2006.01.01", "2009.08.29", "FOR",
                new BigDecimal(1000), null, null, null);
        utilTest.addAvdelingKontrakt("2004.08.27", "2009.08.29");
        SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
                avdelingBetingelse, "KVA", 1, "2006.01.01");
        utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(1000), 100,
                speiletBetingelse);
        utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(1000), 200,
                speiletBetingelse);
        utilTest.addSpeiletKostnad("2006.08.01", "2006.10.31", BigDecimal.valueOf(2000), 300,
                speiletBetingelse);
        Integer buntId = utilTest.fakturerPeriode(2006, 9, 9,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());

        
        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        /*assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals("06/1-3", fakturaLinje.getPeriode());

        fakturaLinje = linjer.get(1);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals("06/4-6", fakturaLinje.getPeriode());
        
        fakturaLinje = linjer.get(2);*/

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(2000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(500), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(2500), fakturaLinje.getTotalBelop());
        assertEquals("06/8-10", fakturaLinje.getPeriode());
    }


	/**
	 * Tester at speilet betingelse for kvartal ikke blir fakturert i periode 10
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceKvartalNotMonth() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"KVA", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "KVA", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.12.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);

		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(0, list.size());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(1, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

	}


	/**
	 * Tester at speilet betingelse kvartal blir fakturert i periode 12
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceKvartal() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"KVA", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "KVA", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.12.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);

		Integer buntId = utilTest.fakturerPeriode(2005, 12, 12,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(1, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(1, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());
		assertEquals(1, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
	}


	/**
	 * Tester at speilet betingelse som starter ut i måned blir beregnet for
	 * måned med 31 dager
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionNotFirstInMonth31()
			throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.05", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		BigDecimal belop = BigDecimal.valueOf(1000);
		BigDecimal days = BigDecimal.valueOf(31);
		BigDecimal dayBelop = belop.divide(days, new MathContext(100));
		BigDecimal belopDays = BigDecimal.valueOf(27);
		BigDecimal wantedResult = dayBelop.multiply(belopDays,
				MathContext.DECIMAL32);
		wantedResult = wantedResult.round(new MathContext(5,
				RoundingMode.HALF_EVEN));

		BigDecimal mva = wantedResult.multiply(BigDecimal.valueOf(0.25)).round(
				new MathContext(5, RoundingMode.HALF_EVEN));
		BigDecimal total = wantedResult.add(mva).round(
				new MathContext(6, RoundingMode.HALF_EVEN));

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(2, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());
		assertEquals(
				"Det er differanse mellom speilet kostnad og beløp fra FRAF",
				fakturaFeilListe.get(1).getFeilKode().getFeilBeskrivelse());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(2, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());
		assertEquals(1, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(wantedResult, fakturaLinje.getBelop());
		assertEquals(mva, fakturaLinje.getMvaBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
	}


	/**
	 * Tester at speilet betingelse som starter ut i måned blir beregnet for
	 * måned med 30 dager
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionNotFirstInMonth30()
			throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.11.05", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.11.01", "2005.11.30", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 11, 11,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		BigDecimal belop = BigDecimal.valueOf(1000);
		BigDecimal days = BigDecimal.valueOf(30);
		BigDecimal dayBelop = belop.divide(days, new MathContext(100));
		BigDecimal belopDays = BigDecimal.valueOf(26);
		BigDecimal wantedResult = dayBelop.multiply(belopDays,
				MathContext.DECIMAL32);
		wantedResult = wantedResult.round(new MathContext(5,
				RoundingMode.HALF_EVEN));

		BigDecimal mva = wantedResult.multiply(BigDecimal.valueOf(0.25)).round(
				new MathContext(5, RoundingMode.HALF_EVEN));
		BigDecimal total = wantedResult.add(mva).round(
				new MathContext(6, RoundingMode.HALF_EVEN));

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(2, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());
		assertEquals(
				"Det er differanse mellom speilet kostnad og beløp fra FRAF",
				fakturaFeilListe.get(1).getFeilKode().getFeilBeskrivelse());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(2, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());
		assertEquals(1, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(wantedResult, fakturaLinje.getBelop());
		assertEquals(mva, fakturaLinje.getMvaBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
	}


}
