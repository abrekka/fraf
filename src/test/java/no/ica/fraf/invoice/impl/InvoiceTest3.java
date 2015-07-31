package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class InvoiceTest3 extends TestCase {
	public InvoiceTest3(boolean isStandalone){
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
     * Tester at faktura for betingelse som ikke starter i begynnelsen av
     * måneden ikk får full faktura
     * 
     * @throws Exception
     */
    public void testInvoiceNotFirstInMonth() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2006.01.08",
                "2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
        utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal belop = BigDecimal.valueOf(1000);
        BigDecimal days = BigDecimal.valueOf(31);
        BigDecimal dayBelop = belop.divide(days, new MathContext(100));
        BigDecimal belopDays = BigDecimal.valueOf(24);
        BigDecimal wantedResult = dayBelop.multiply(belopDays,
                MathContext.DECIMAL32);
        wantedResult = wantedResult.round(new MathContext(5,
                RoundingMode.HALF_EVEN));

        BigDecimal mva = wantedResult.multiply(BigDecimal.valueOf(0.25)).round(
                new MathContext(5, RoundingMode.HALF_EVEN));
        BigDecimal total = wantedResult.add(mva).round(
                new MathContext(6, RoundingMode.HALF_EVEN));

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Kontorrekvisita ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(wantedResult, fakturaLinje.getBelop());
        assertEquals(mva, fakturaLinje.getMvaBelop());
        assertEquals(total, fakturaLinje.getTotalBelop());
        assertEquals(total, fakturaLinje.getTotalBelop());

    }

    public void testInvoiceSatsTakAndNoTak() throws Exception {

        BigDecimal omsetning = new BigDecimal(100000);
        BigDecimal gulv = BigDecimal.valueOf(0);
        BigDecimal tak = BigDecimal.valueOf(50000);

        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, BigDecimal.valueOf(3), gulv, tak);
        utilTest.addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                "2010.01.31", "ETT", null, BigDecimal.valueOf(4), null, null);

        utilTest.addAvdelingOmsetning(new Integer(2006), omsetning,
                new Integer(1),null);

        utilTest.addAvdelingKontrakt("2005.10.01", "2010.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(2, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal forsteBelop = tak.subtract(gulv).multiply(
                new BigDecimal(0.03), MathContext.DECIMAL32).round(
                new MathContext(4, RoundingMode.HALF_EVEN));
        BigDecimal mvaForste = forsteBelop.multiply(BigDecimal.valueOf(0.25))
                .round(new MathContext(3, RoundingMode.HALF_EVEN));
        BigDecimal totalForste = forsteBelop.add(mvaForste).round(
                new MathContext(6, RoundingMode.HALF_EVEN));

        assertEquals(new BigDecimal(3), fakturaLinje.getSats());
        assertEquals("Franchise-avgift ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(forsteBelop, fakturaLinje.getBelop());
        assertEquals(mvaForste, fakturaLinje.getMvaBelop());
        assertEquals(totalForste, fakturaLinje.getTotalBelop());
        assertEquals(totalForste, fakturaLinje.getTotalBelop());
        assertEquals(tak.round(new MathContext(6, RoundingMode.HALF_EVEN)),
                fakturaLinje.getGrunnlagBelop().round(
                        new MathContext(6, RoundingMode.HALF_EVEN)));

        fakturaLinje = linjer.get(1);

        forsteBelop = omsetning.multiply(new BigDecimal(0.04),
                MathContext.DECIMAL32).round(
                new MathContext(4, RoundingMode.HALF_EVEN));
        mvaForste = forsteBelop.multiply(BigDecimal.valueOf(0.25)).round(
                new MathContext(4, RoundingMode.HALF_EVEN));
        totalForste = forsteBelop.add(mvaForste).round(
                new MathContext(6, RoundingMode.HALF_EVEN));

        assertEquals(BigDecimal.valueOf(4), fakturaLinje.getSats());
        assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(forsteBelop, fakturaLinje.getBelop());
        assertEquals(mvaForste, fakturaLinje.getMvaBelop());
        assertEquals(totalForste, fakturaLinje.getTotalBelop());
        assertEquals(totalForste, fakturaLinje.getTotalBelop());
        assertEquals(omsetning
                .round(new MathContext(6, RoundingMode.HALF_EVEN)),
                fakturaLinje.getGrunnlagBelop().round(
                        new MathContext(6, RoundingMode.HALF_EVEN)));

    }


    /**
     * Tester fakturering av sats med forskudd og avdeling er opprettet i
     * perioden det skal betales forskudd for. Eks konrrakt begynner 1.jan med
     * forskudd av betingelse kvartalsvis
     * 
     * @throws Exception
     */
    public void testInvoiceForskuddKvaCreatedInPeriode() throws Exception {
        testAvdeling = utilTest.addAvdeling(9998, "2006.01.01");
        BokfSelskap bokfSelskap = utilTest.findBokfSelskap("100");
        testAvdeling.setBokfSelskap(bokfSelskap);

        utilTest.addAvdelingBetingelse("KVA", "HUS", 0, "2006.01.01",
                "2010.01.31", "FOR", BigDecimal.valueOf(1000), null, null,
                null, testAvdeling, null, null, null, null, null, null);

        utilTest.addAvdelingKontrakt("2005.10.01", "2010.01.31", testAvdeling);

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 9998,9998, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(BigDecimal.valueOf(0), fakturaLinje.getSats());
        assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(BigDecimal.valueOf(1000), fakturaLinje.getBelop());
        assertEquals(BigDecimal.valueOf(250), fakturaLinje.getMvaBelop());
        assertEquals(BigDecimal.valueOf(1250), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(1250), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(0),fakturaLinje.getGrunnlagBelop());

    }


    /**
     * Tester fakturering av sats med forskudd og avdeling er opprettet i
     * perioden det skal betales forskudd for. Eks konrrakt begynner 1.jan med
     * forskudd av betingelse kvartalsvis
     * 
     * @throws Exception
     */
    public void testInvoiceForskudd6maanCreatedInPeriode() throws Exception {
        testAvdeling = utilTest.addAvdeling(9998, "2006.01.01");
        BokfSelskap bokfSelskap = utilTest.findBokfSelskap("100");
        testAvdeling.setBokfSelskap(bokfSelskap);

        utilTest.addAvdelingBetingelse("HAL", "HUS", 0, "2006.01.01",
                "2010.01.31", "FOR", BigDecimal.valueOf(1000), null, null,
                null, testAvdeling, null, null, null, null, null, null);

        utilTest.addAvdelingKontrakt("2005.10.01", "2010.01.31", testAvdeling);

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 9998,9998, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(BigDecimal.valueOf(0), fakturaLinje.getSats());
        assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(BigDecimal.valueOf(1000), fakturaLinje.getBelop());
        assertEquals(BigDecimal.valueOf(250), fakturaLinje.getMvaBelop());
        assertEquals(BigDecimal.valueOf(1250), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(1250), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(0),fakturaLinje.getGrunnlagBelop());

    }


    /**
     * Tester forskudd når det allerede er faktuert
     * 
     * @throws Exception
     */
    public void testInvoiceForskuddKvaHasInvoice() throws Exception {
        testAvdeling = utilTest.addAvdeling(9998, "2005.01.01");
        BokfSelskap bokfSelskap = utilTest.findBokfSelskap("100");
        testAvdeling.setBokfSelskap(bokfSelskap);

        utilTest.addAvdelingBetingelse("KVA", "HUS", 0, "2005.10.01",
                "2010.01.31", "FOR", BigDecimal.valueOf(1000), null, null,
                null, testAvdeling, null, null, null, null, null, null);

        utilTest.addAvdelingKontrakt("2005.01.01", "2010.01.31", testAvdeling);

        Integer buntId = utilTest.fakturerPeriode(new Integer(2005), 11, 11,
                "Franchiseavgift", 9998,9998, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(BigDecimal.valueOf(0), fakturaLinje.getSats());
        assertEquals("Husleie ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(BigDecimal.valueOf(1000), fakturaLinje.getBelop());
        assertEquals(BigDecimal.valueOf(250), fakturaLinje.getMvaBelop());
        assertEquals(BigDecimal.valueOf(1250), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(1250), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(0),fakturaLinje.getGrunnlagBelop());

        buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 9998,9998, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);
        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());

        buntId = utilTest.fakturerPeriode(new Integer(2006), 3, 3,
                "Franchiseavgift", 9998,9998, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);
        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());

    }


    /**
     * Teste forskudd budsjett for avdeling
     * 
     * @throws Exception
     */
    public void testInvoiceForskuddKvaBudget() throws Exception {
        testAvdeling = utilTest.addAvdeling(9998, "2005.01.01");
        BokfSelskap bokfSelskap = utilTest.findBokfSelskap("100");
        testAvdeling.setBokfSelskap(bokfSelskap);

        utilTest.addAvdelingBetingelse("KVA", "FRA", 0, "2005.12.01",
                "2010.01.31", "FOR", null, BigDecimal.valueOf(10), null, null,
                testAvdeling, null, null, null, null, null, null);
        utilTest.addAvdelingBudget(2006, BigDecimal.valueOf(1000), 1,
                testAvdeling);
        utilTest.addAvdelingBudget(2006, BigDecimal.valueOf(1000), 2,
                testAvdeling);
        utilTest.addAvdelingBudget(2006, BigDecimal.valueOf(1000), 3,
                testAvdeling);

        utilTest.addAvdelingKontrakt("2005.01.01", "2010.01.31", testAvdeling,
                "KVA", "FOR", "A", "BUD");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2005), 12, 12,
                "Franchiseavgift", 9998,9998, "BUD", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(BigDecimal.valueOf(0), fakturaLinje.getAvregningBelop());
        assertEquals(BigDecimal.valueOf(300), fakturaLinje.getBelop());
        assertNull(fakturaLinje.getFastBelop());
        assertEquals(BigDecimal.valueOf(3000), fakturaLinje.getGrunnlagBelop());
        assertEquals(BigDecimal.valueOf(75), fakturaLinje.getMvaBelop());
        assertEquals(BigDecimal.valueOf(3000), fakturaLinje.getOmsetningBelop());
        assertEquals(BigDecimal.valueOf(10), fakturaLinje.getSats());
        assertEquals("Franchise-avgift ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(BigDecimal.valueOf(375), fakturaLinje.getTotalBelop());
        assertEquals(BigDecimal.valueOf(375), fakturaLinje.getTotalBelop());

    }




}
