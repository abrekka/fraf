package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class InvoiceTest6 extends TestCase {
	public InvoiceTest6(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
    private UtilTest utilTest = new UtilTest();
    private Avdeling testAvdeling;
    private BetingelseType betingelseTypeAdslTest;
    private BetingelseType betingelseTypeTomraTest;
    private BokfSelskap bokfSelskap400;
    private BokfSelskap bokfSelskap500;
    private AvdelingBetingelse avdelingBetingelse;
    private AvdelingBetingelseDAO avdelingBetingelseDAO = (AvdelingBetingelseDAO) ModelUtil
    .getBean("avdelingBetingelseDAO");
    
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

        if (testAvdeling != null) {
        	utilTest.removeOmsetningForAvdeling(testAvdeling);
            utilTest.removeAvdeling(testAvdeling);
        }

        utilTest.tearDown();

        if (betingelseTypeAdslTest != null) {
            utilTest.removeBetingelseType(betingelseTypeAdslTest);
        }

        if (betingelseTypeTomraTest != null) {
            utilTest.removeBetingelseType(betingelseTypeTomraTest);
        }

        if (bokfSelskap400 != null) {
            utilTest.removeBokfSelskap(bokfSelskap400);
        }

        if (bokfSelskap500 != null) {
            utilTest.removeBokfSelskap(bokfSelskap500);
        }

        if (avdelingBetingelse != null) {
            avdelingBetingelseDAO.removeObject(avdelingBetingelse);
        }

        super.tearDown();

    }



    /**
     * Tester fakturering hvor kun betingelser som er forskuddsvis blir tatt med
     * 
     * @throws Exception
     */
    public void test16836InvoiceAvregningTypeFOR() throws Exception {
        utilTest
                .addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest
                .addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                        "2006.12.31", "FOR", BigDecimal.valueOf(2000), null,
                        null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 9999,9999, "OMS", null, "FOR",1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());
        assertEquals("Franchise-avgift ", linjer.get(0).getLinjeBeskrivelse());

    }

    /**
     * Tester fakturering hvor avdleing har utgått kontrakt
     * 
     * @throws Exception
     */
    public void testInvoiceNot7601() throws Exception {
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 3, 3,
                "Franchiseavgift", 7601,7601, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());

    }


    /**
     * Tester at fakturalinje får med periodeinfo
     * 
     * @throws Exception
     */
    public void testInvoiceFakturaLinjePeriode() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2005.10.01",
                "2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
        utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
        Integer buntId = utilTest.fakturerPeriode(new Integer(2005),
                new Integer(10), new Integer(10), "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Kontorrekvisita ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertNotNull(fakturaLinje.getPeriode());
        assertEquals("05/10", fakturaLinje.getPeriode());

    }


    /**
     * Tester at fakturalinje får med periodeinfo for kvartal
     * 
     * @throws Exception
     */
    public void testInvoiceFakturaLinjePeriodeKva() throws Exception {
        utilTest.addAvdelingBetingelse("KVA", "KONTREK", 0, "2005.07.01",
                "2006.12.31", "ETT", new BigDecimal(1000), null, null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
        Integer buntId = utilTest.fakturerPeriode(new Integer(2005), 9, 9,
                "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Kontorrekvisita ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertNotNull(fakturaLinje.getPeriode());
        assertEquals("05/7-9", fakturaLinje.getPeriode());

    }


    /**
     * Tester fakturering av betingelse som er importert
     * 
     * @throws Exception
     */
    public void testInvoiceImportedCondition() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "TOMRA", 0, "2006.03.01",
                "2006.03.31", "ETT", new BigDecimal(1125), null, null, null,
                "100", null, null, null,
                "Basisservice avg à 1125 kr pr automat mars 06", null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 3, 3,
                "IT",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Tomra Basisservice avg à 1125 kr pr automat mars 06",
                fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1125), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(281.25), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1406.25), fakturaLinje.getTotalBelop());
        assertNotNull(fakturaLinje.getPeriode());
        assertEquals("06/3", fakturaLinje.getPeriode());
        assertEquals("TOMRA", fakturaLinje.getBetingelseType()
                .getBetingelseTypeKode());

        Faktura faktura = list.get(0);

        assertEquals("FAKTURA ", faktura.getFakturaTittel());

        utilTest.bokfoerBunt(buntId, "2006.05.01");

        List<RegnskapKladd> kladder = utilTest
                .findRegnskapKladdByBuntId(buntId);
        assertEquals(2, kladder.size());

        RegnskapKladd regnskapKladd = kladder.get(0);
        assertNotNull(regnskapKladd);

        assertEquals("100", regnskapKladd.getSelskap());
        assertEquals(Integer.valueOf(9999), regnskapKladd.getAvdnr());
        assertEquals("100", regnskapKladd.getSelskap());
        assertEquals("150036", regnskapKladd.getKonto());
        assertEquals(BigDecimal.valueOf(-1406.25), regnskapKladd.getBelop());
        assertEquals("Tomra", regnskapKladd.getTekst());
        assertEquals("F", regnskapKladd.getKontoType());
        assertEquals("FRAF", regnskapKladd.getKladdNavn());

        regnskapKladd = kladder.get(1);
        assertNotNull(regnskapKladd);

        assertEquals("100", regnskapKladd.getSelskap());
        assertEquals(Integer.valueOf(9999), regnskapKladd.getAvdnr());
        assertEquals("100", regnskapKladd.getSelskap());
        assertEquals("9999", regnskapKladd.getKonto());
        assertEquals(BigDecimal.valueOf(1406.25), regnskapKladd.getBelop());
        assertEquals("IT", regnskapKladd.getTekst());
        assertEquals("D", regnskapKladd.getKontoType());
        assertEquals("FRAF", regnskapKladd.getKladdNavn());

    }

    /**
     * Tester fakturering av betingelse som er importert som inneholder
     * konto,mva,avdeling
     * 
     * @throws Exception
     */
    public void testInvoiceImportedConditionDIV() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "DIV", 0, "2006.02.01",
                "2006.02.28", "ETT", new BigDecimal(-1738.53), null, null,
                null, "110", "910140", "10133180", "13",
                "Svinnkompensasjon februar 2006", "1% av kjøp Frukt&Grønt");
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 2, 2,
                "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals(" Svinnkompensasjon februar 2006", fakturaLinje
                .getLinjeBeskrivelse());
        assertEquals(new BigDecimal(-1738.53).round(new MathContext(6,
                RoundingMode.HALF_EVEN)), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(-243.39).round(new MathContext(5,
                RoundingMode.HALF_EVEN)), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(-1981.92).round(new MathContext(6,
                RoundingMode.HALF_EVEN)), fakturaLinje.getTotalBelop());
        assertNotNull(fakturaLinje.getPeriode());
        assertEquals("06/2", fakturaLinje.getPeriode());
        assertEquals("DIV", fakturaLinje.getBetingelseType()
                .getBetingelseTypeKode());

        Faktura faktura = list.get(0);

        assertEquals("KREDITTNOTA ", faktura.getFakturaTittel());

        utilTest.bokfoerBunt(buntId, "2006.03.01");

        List<RegnskapKladd> kladder = utilTest
                .findRegnskapKladdByBuntId(buntId);
        assertEquals(2, kladder.size());

        Iterator<RegnskapKladd> kladdIt = kladder.iterator();

        RegnskapKladd regnskapKladd = kladdIt.next();
        assertNotNull(regnskapKladd);

        assertEquals("F", regnskapKladd.getKontoType());
        assertEquals("110", regnskapKladd.getSelskap());
        assertEquals(Integer.valueOf(10133180), regnskapKladd.getAvdnr());
        assertEquals("910140", regnskapKladd.getKonto());
        assertEquals(BigDecimal.valueOf(1981.92), regnskapKladd.getBelop());
        assertEquals("DIV", regnskapKladd.getTekst());
        assertEquals("FRAF", regnskapKladd.getKladdNavn());
        assertEquals("13", regnskapKladd.getMvaKode());

        regnskapKladd = kladdIt.next();
        assertNotNull(regnskapKladd);

        assertEquals("110", regnskapKladd.getSelskap());
        assertEquals(Integer.valueOf(9999), regnskapKladd.getAvdnr());
        assertEquals("110", regnskapKladd.getSelskap());
        assertEquals("9999", regnskapKladd.getKonto());
        assertEquals(BigDecimal.valueOf(-1981.92), regnskapKladd.getBelop());
        assertEquals("Diverse", regnskapKladd.getTekst());
        assertEquals("D", regnskapKladd.getKontoType());
        assertEquals("FRAF", regnskapKladd.getKladdNavn());
    }


}
