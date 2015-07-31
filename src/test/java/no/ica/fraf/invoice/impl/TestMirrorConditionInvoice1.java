package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
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

public class TestMirrorConditionInvoice1 extends TestCase {
	public TestMirrorConditionInvoice1(boolean isStandalone){
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
	 * Tester fakturering når kontrakt mangler
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceNoMirrorNoContract() throws Exception {
		utilTest.addAvdelingBetingelse("MND", "FEL", 1, "2005.10.01",
				"2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingBetingelse("MND", "FEL", 0, "2005.10.01",
				"2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);
		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(0, list.size());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(0, feil.size());
	}



	/**
	 * Tester manglende speilet betingelse
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceNoMirror() throws Exception {
		utilTest.addAvdelingBetingelse("MND", "FEL", 1, "2005.10.01",
				"2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);
		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(0, list.size());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(2, feil.size());

		boolean feilFound = false;

		for (BuntFeil feilBunt : feil) {
			if (feilBunt.getFeilKode().getFeilBeskrivelse().equalsIgnoreCase(
					"Mangler speilet betingelse")) {
				feilFound = true;
				break;
			}
		}

		assertEquals(true, feilFound);
	}


	/**
	 * Tester manglende speilet kostnad
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceNoKostnad() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		utilTest.addSpeiletBetingelse(avdelingBetingelse, "MND", 1,
				"2005.01.01");
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);
		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(0, list.size());

		boolean feilFound = false;

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(2, feil.size());

		feilFound = false;

		for (BuntFeil feilBunt : feil) {
			if (feilBunt.getFeilKode().getFeilBeskrivelse().equalsIgnoreCase(
					"Mangler speilet kostnad")) {
				feilFound = true;
				break;
			}
		}

		assertEquals(true, feilFound);
	}


	/**
	 * Tester fakturering av speilet betingelse
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoice() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
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
	 * Tester fakturering av speilet betingelse som kjøres to ganger. Betingelse
	 * skal bare bli fakturert en gang
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceTwice() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		Integer buntId = utilTest.fakturerPeriode(2005, 10, 10,
				"Franchiseavgift",1);
		utilTest.addBunt(buntId);

		List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(1, list.size());

		buntId = utilTest.fakturerPeriode(2005, 10, 10, "Franchiseavgift",1);
		utilTest.addBunt(buntId);

		list = utilTest.findFakturaerByBuntId(buntId);
		assertEquals(0, list.size());

		List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
		assertEquals(1, feil.size());

		assertEquals("Avdeling mangler omsetning/budsjett", feil.get(0)
				.getFeilKode().getFeilBeskrivelse());
		
		/*assertEquals("Mangler speilet kostnad", feil.get(1)
				.getFeilKode().getFeilBeskrivelse());*/
	}


	/**
	 * Tester fakturering av speilet betingelse som er nyere en periode det
	 * faktureres for. Skal bare fakturere betingelse som ligger innenfor
	 * periode
	 * 
	 * @throws Exception
	 */
	public void testMirrorInvoiceTooNew() throws Exception {
		AvdelingBetingelse avdelingBetingelse = utilTest.addAvdelingBetingelse(
				"MND", "FEL", 1, "2005.10.01", "2006.01.31", "ETT",
				new BigDecimal(1000), null, null, null);
		utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
		SpeiletBetingelse speiletBetingelse = utilTest.addSpeiletBetingelse(
				avdelingBetingelse, "MND", 1, "2005.01.01");
		utilTest.addSpeiletKostnad("2005.10.01", "2005.10.31", BigDecimal.valueOf(1000), 100,
				speiletBetingelse);
		utilTest.addSpeiletKostnad("2005.11.01", "2005.11.30", BigDecimal.valueOf(2000), 200,
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
	}

}
