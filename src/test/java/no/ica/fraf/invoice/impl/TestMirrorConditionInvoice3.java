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

public class TestMirrorConditionInvoice3 extends TestCase {
	public TestMirrorConditionInvoice3(boolean isStandalone){
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
	 * Tester at speilet betingelse som ligger som månedsvis i Fenistra blir
	 * fakturert for kvartal fordi betingelse i Fraf er kvartal
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceDifferentFrequenceKvaMnd() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"KVA", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 11, 11,
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
	 * Tester at speilet betingelse som ligger som kvartal i Fenistra blir
	 * fakturert for måned fordi betingelse i Fraf er måned
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceDifferentFrequenceMndKva() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "KVA", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
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
	 * Betingelse slutter ikke sist i måned
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionNotLastInMonth() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2005.10.25", "ETT",
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
		BigDecimal belopDays = BigDecimal.valueOf(25);
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
	 * Tester betingelse som ikke start i begynnelsen av måneden og slutter ikke
	 * i slutten
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionNotFirstOrLastInMonth()
			throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.05", "2005.10.25", "ETT",
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
		BigDecimal belopDays = BigDecimal.valueOf(21);
		BigDecimal wantedResult = dayBelop.multiply(belopDays,
				MathContext.DECIMAL32);
		wantedResult = wantedResult.round(new MathContext(5,
				RoundingMode.HALF_EVEN));

		BigDecimal mva = wantedResult.round(
				new MathContext(5, RoundingMode.DOWN)).multiply(
				BigDecimal.valueOf(0.25)).round(
				new MathContext(5, RoundingMode.DOWN));
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
	 * Tester forskuddsfakturering
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionForskudd() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "FOR",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.11.01", "2005.11.30", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
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
	 * Tester forskudd kvartal
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionForskuddKva() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "FOR",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "KVA", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 12, 12,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(2, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(2, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());
		assertEquals(1, linjer.size());

	}


	/**
	 * Tester fakturering av speilet betingelse hvor betingelse i Fenistra er 46
	 * 875 fra 01.02.2006 til 21.02.2006 betingelse går til 12.01.2006. Det skal
	 * ikke bli noen faktura
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceCondition2703() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "HUS", 1, "1996.01.12", "2006.01.12", "FOR",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("1996.01.12", "2006.01.12");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2006.02.01", "2006.02.21", BigDecimal.valueOf(46875), 100,
				speiletBetingelse);
		Integer buntId = utilTest
				.fakturerPeriode(2006, 1, 1, "Franchiseavgift",1);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(0, list.size());

	}


	/**
	 * Tester forskudd kvartal ikke starter første periode i kvartal
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceConditionNotFirstInKvartal()
			throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"KVA", "HUS", 1, "2006.02.01", "2006.12.31", "FOR", null, null,
				null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.12.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "KVA", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(3000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 12, 12,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		BigDecimal belop = BigDecimal.valueOf(3000);
		BigDecimal days = BigDecimal.valueOf(90);
		BigDecimal dayBelop = belop.divide(days, new MathContext(100));
		BigDecimal belopDays = BigDecimal.valueOf(59);
		BigDecimal wantedResult = dayBelop.multiply(belopDays,
				MathContext.DECIMAL32);
		wantedResult = wantedResult.round(new MathContext(6,
				RoundingMode.HALF_DOWN));

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
		assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(wantedResult, fakturaLinje.getBelop());
		assertEquals(mva, fakturaLinje.getMvaBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
	}


	/**
	 * Tester forskudd kvartal for avdeling som er opprettet i januar
	 * 
	 * @throws Exception
	 */
	public void test16048MirrorInvoiceConditionForskuddJanuar()
			throws Exception {
		//testAvdeling = utilTest.addAvdeling(9999, "2006.01.01");
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"KVA", "HUS", 1, "2006.02.01", "2006.12.31", "FOR", null, null,
				null, null);
		utilTest.addAvdelingKontrakt("2006.01.01", "2006.12.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "KVA", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(3000), 100,
				speiletBetingelse);
		Integer buntId = utilTest
				.fakturerPeriode(2006, 1, 1, "Franchiseavgift",1);
		utilTest.addBunt(buntId);

		BigDecimal belop = BigDecimal.valueOf(3000);
		BigDecimal days = BigDecimal.valueOf(90);
		BigDecimal dayBelop = belop.divide(days, new MathContext(100));
		BigDecimal belopDays = BigDecimal.valueOf(59);
		BigDecimal wantedResult = dayBelop.multiply(belopDays,
				MathContext.DECIMAL32);
		wantedResult = wantedResult.round(new MathContext(6,
				RoundingMode.HALF_DOWN));

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
		assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(wantedResult, fakturaLinje.getBelop());
		assertEquals(mva, fakturaLinje.getMvaBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
	}


}
