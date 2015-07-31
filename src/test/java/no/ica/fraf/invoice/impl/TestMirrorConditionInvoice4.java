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

public class TestMirrorConditionInvoice4 extends TestCase {
	public TestMirrorConditionInvoice4(boolean isStandalone){
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
	 * Tester fakturering av to påfølgende måneder
	 * 
	 * @throws Exception
	 */
	public void test16098MirrorInvoiceConditionJanFeb() throws Exception {
		//testAvdeling = utilTest.addAvdeling(9998, "2006.01.01");
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "HUS", 1, "2006.01.01", "2006.12.31", "ETT", null, null,
				null, null);
		utilTest.addAvdelingKontrakt("2006.01.01", "2006.12.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.01.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.02.01", "2006.02.28", BigDecimal.valueOf(2000), 200,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.03.01", "2006.03.31", BigDecimal.valueOf(3000), 300,
				speiletBetingelse);
		Integer buntId = utilTest
				.fakturerPeriode(2006, 1, 1, "Franchiseavgift",1);
		utilTest.addBunt(buntId);

		BigDecimal belop = BigDecimal.valueOf(1000);

		BigDecimal mva = belop.multiply(BigDecimal.valueOf(0.25)).round(
				new MathContext(3, RoundingMode.HALF_EVEN));
		BigDecimal total = belop.add(mva).round(
				new MathContext(6, RoundingMode.HALF_EVEN));

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
		assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(belop, fakturaLinje.getBelop());
		assertEquals(mva, fakturaLinje.getMvaBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());

		buntId = utilTest.fakturerPeriode(2006, 2, 2, "Franchiseavgift",1);
		utilTest.addBunt(buntId);

		belop = BigDecimal.valueOf(2000);

		mva = belop.multiply(BigDecimal.valueOf(0.25)).round(
				new MathContext(3, RoundingMode.HALF_EVEN));
		total = belop.add(mva)
				.round(new MathContext(6, RoundingMode.HALF_EVEN));

		list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		fakturaFeilListe = utilTest.findFakturaFeilByFakturaId(list.get(0)
				.getFakturaId());
		assertEquals(1, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());

		feil = utilTest.findBuntFeilById(buntId);
		assertEquals(1, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());

		linjer = utilTest.findFakturaLinjerByFakturaId(list.get(0)
				.getFakturaId());
		assertEquals(1, linjer.size());

		fakturaLinje = linjer.get(0);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(belop, fakturaLinje.getBelop());
		assertEquals(mva, fakturaLinje.getMvaBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());
		assertEquals(total, fakturaLinje.getTotalBelop());

	}


	/**
	 * Tester at periode kommer med på fkturalinje for speilet betingelse
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceFakturaLinjePeriode() throws Exception {
		// etterskuddsvis månedlig
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);

		// forskuddsvis månedlig
		AvdelingBetingelse avdelingBetingelseFor = utilTest
				.addAvdelingBetingelse("MND", "FEL", 1, "2005.10.01",
						"2006.01.31", "FOR", new BigDecimal(1000), null, null,
						null);
		speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseFor, "MND", 2, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.11.01", "2005.11.30", BigDecimal.valueOf(2000), 200,
				speiletBetingelse);

		// forskuddsvis månedlig som ikker tatt med før
		avdelingBetingelse = utilTest.addAvdelingBetingelse("MND", "FEL", 1,
				"2005.01.01", "2006.12.31", "FOR", null, null, null, null);
		speiletBetingelse = utilTest.addSpeiletBetingelse(avdelingBetingelse,
				"MND", 3, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.09.01", "2005.09.30", BigDecimal.valueOf(3000), 300,
				speiletBetingelse);

		// forskuddsvis kvartalsvis
		avdelingBetingelse = utilTest.addAvdelingBetingelse("KVA", "FEL", 1,
				"2005.01.01", "2006.12.31", "FOR", null, null, null, null);
		speiletBetingelse = utilTest.addSpeiletBetingelse(avdelingBetingelse,
				"KVA", 4, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.11.01", "2006.01.31", BigDecimal.valueOf(4000), 400,
				speiletBetingelse);

		// fakturerer
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
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

		assertEquals(3, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("05/10", fakturaLinje.getPeriode());

		fakturaLinje = linjer.get(1);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(2000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(500), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(2500), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(2500), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("05/11", fakturaLinje.getPeriode());

		fakturaLinje = linjer.get(2);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(4000), fakturaLinje.getBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("05-11/06-1", fakturaLinje.getPeriode());

		/*fakturaLinje = linjer.get(3);

		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(3000), fakturaLinje.getBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("05/9", fakturaLinje.getPeriode());*/

	}


	/**
	 * Tester fakturering av speilde kostnader som gjelder for samme periode og
	 * er for samme betingelse. Disse skal slås sammen til en
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceMergePeriod() throws Exception {
		// etterskuddsvis månedlig
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);

		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(3000), 200,
				speiletBetingelse);

		// fakturerer
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);

		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(2, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());
		
		assertEquals("Slått sammen beløp", fakturaFeilListe
				.get(1).getFeilKode().getFeilBeskrivelse());

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
		assertEquals(new BigDecimal(4000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(1000), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(5000), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(5000), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("05/10", fakturaLinje.getPeriode());

	}


	/**
	 * Tester fakturering av speilde kostnader som gjelder for samme periode og
	 * er for samme betingelse, men er to betingelser i fenistra. Disse skal slås sammen til en
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceMergePeriodTwoCondition() throws Exception {
		// etterskuddsvis månedlig
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);

		speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 2, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(3000), 200,
				speiletBetingelse);

		// fakturerer
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);

		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		List<FakturaFeil> fakturaFeilListe = utilTest
				.findFakturaFeilByFakturaId(list.get(0).getFakturaId());
		assertEquals(2, fakturaFeilListe.size());

		assertEquals("Avdeling mangler omsetning/budsjett", fakturaFeilListe
				.get(0).getFeilKode().getFeilBeskrivelse());
		
		assertEquals("Slått sammen beløp", fakturaFeilListe
				.get(1).getFeilKode().getFeilBeskrivelse());

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
		assertEquals(new BigDecimal(4000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(1000), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(5000), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(5000), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("05/10", fakturaLinje.getPeriode());

	}

	
	/**
	 * Tester sammenslåing av speilede kostnader
	 * @throws Exception
	 */
	public void testMirrorInvoiceMergePeriodSeveralConditions() throws Exception {
		AvdelingBetingelse avdelingBetingelseFelles = utilTest.addAvdelingBetingelse(
				"KVA", "FEL", 1, "2005.04.01", "2009.09.12", "FOR",
				null, null, null, null);
		
		AvdelingBetingelse avdelingBetingelseMarked = utilTest.addAvdelingBetingelse(
				"KVA", "MF", 1, "2006.01.01", "2009.09.12", "FOR",
				null, null, null, null);
		
		
		utilTest.addAvdelingKontrakt("2004.09.13", "2009.09.12");
		
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseFelles, "KVA", 1, "2006.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(56561), 100,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(56561), 200,
				speiletBetingelse);
		
		speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseFelles, "KVA", 2, "2006.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(1749), 300,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(1749), 400,
				speiletBetingelse);
		
		speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseMarked, "KVA", 3, "2006.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(50000), 500,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(50000), 600,
				speiletBetingelse);

		// fakturerer
		Integer buntId = utilTest.fakturerPeriode(2006, 03, 03,
				"Franchiseavgift",1);

		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());
		
		



		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());

		assertEquals(2, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		/*assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(58310), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(14577.50).round(new MathContext(8,
				RoundingMode.HALF_EVEN)), fakturaLinje.getMvaBelop().round(new MathContext(6,
						RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/1-3", fakturaLinje.getPeriode());
		
		fakturaLinje = linjer.get(1);*/
		
		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(58310), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(14577.50), fakturaLinje.getMvaBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/4-6", fakturaLinje.getPeriode());

		/*fakturaLinje = linjer.get(2);
		
		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Markedsføring ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(50000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(12500), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/1-3", fakturaLinje.getPeriode());*/

		
		
		
		
		fakturaLinje = linjer.get(1);
		
		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Markedsføring ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(50000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(12500), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/4-6", fakturaLinje.getPeriode());
	}

	
	/**
	 * Tester flere speilede kostander som er slått sammen men inimellom er det kostander som ikke skal være med
	 * @throws Exception
	 */
	public void testMirrorInvoiceMergePeriodSeveralConditionsInbetween() throws Exception {
		AvdelingBetingelse avdelingBetingelseFelles = utilTest.addAvdelingBetingelse(
				"KVA", "FEL", 1, "2005.04.01", "2009.09.12", "FOR",
				null, null, null, null);
		
		AvdelingBetingelse avdelingBetingelseMarked = utilTest.addAvdelingBetingelse(
				"KVA", "MF", 1, "2006.01.01", "2009.09.12", "FOR",
				null, null, null, null);
		
		utilTest.addAvdelingKontrakt("2004.09.13", "2009.09.12");
		
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseFelles, "KVA", 1, "2006.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(56561), 100,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(56561), 200,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.07.01", "2006.09.30", BigDecimal.valueOf(56561), 300,
				speiletBetingelse);
		
		speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseFelles, "KVA", 2, "2006.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(1749), 400,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(1749), 500,
				speiletBetingelse);
		
		
		speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelseMarked, "KVA", 3, "2006.01.01");
		utilTest.addSpeiletKostnad("2006.01.01", "2006.03.31", BigDecimal.valueOf(50000), 600,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2006.04.01", "2006.06.30", BigDecimal.valueOf(50000), 700,
				speiletBetingelse);
		

		// fakturerer
		Integer buntId = utilTest.fakturerPeriode(2006, 03, 03,
				"Franchiseavgift",1);

		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());
		



		List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
				.get(0).getFakturaId());

		assertEquals(2, linjer.size());

		FakturaLinje fakturaLinje = linjer.get(0);

		/*assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(58310), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(14577.50).round(new MathContext(8,
				RoundingMode.HALF_EVEN)), fakturaLinje.getMvaBelop().round(new MathContext(6,
						RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/1-3", fakturaLinje.getPeriode());
		
		fakturaLinje = linjer.get(1);*/
		
		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Felleskostnader ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(58310), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(14577.50), fakturaLinje.getMvaBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertEquals(new BigDecimal(72887.50), fakturaLinje.getTotalBelop().round(new MathContext(6,
				RoundingMode.HALF_EVEN)));
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/4-6", fakturaLinje.getPeriode());

		/*fakturaLinje = linjer.get(2);
		
		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Markedsføring ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(50000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(12500), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/1-3", fakturaLinje.getPeriode());*/

		
		
		
		
		fakturaLinje = linjer.get(1);
		
		assertEquals(new BigDecimal(0), fakturaLinje.getSats());
		assertEquals("Markedsføring ", fakturaLinje.getLinjeBeskrivelse());
		assertEquals(new BigDecimal(50000), fakturaLinje.getBelop());
		assertEquals(new BigDecimal(12500), fakturaLinje.getMvaBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertEquals(new BigDecimal(62500), fakturaLinje.getTotalBelop());
		assertNotNull(fakturaLinje.getPeriode());
		assertEquals("06/4-6", fakturaLinje.getPeriode());

		
	}


}
