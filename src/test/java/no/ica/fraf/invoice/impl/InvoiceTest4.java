package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.FakturaTekst;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class InvoiceTest4 extends TestCase {
	public InvoiceTest4(boolean isStandalone){
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
     * Teste forskudd budsjett for avdeling to ganger med samme periode
     * 
     * @throws Exception
     */
    public void testInvoiceForskuddKvaBudgetTwice() throws Exception {
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

        buntId = utilTest.fakturerPeriode(new Integer(2005), 12, 12,
                "Franchiseavgift", 9998,9998, "BUD", null, null,1,null);
        utilTest.addBunt(buntId);

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());
    }

    /**
     * Tester fakturering av forskudd budsjett for avdeling opprettet i januar
     * 
     * @throws Exception
     */
    public void testInvoiceForskuddKvaBudgetJanuar() throws Exception {
        testAvdeling = utilTest.addAvdeling(9998, "2006.01.01");
        BokfSelskap bokfSelskap = utilTest.findBokfSelskap("100");
        testAvdeling.setBokfSelskap(bokfSelskap);

        utilTest.addAvdelingBetingelse("KVA", "FRA", 0, "2006.01.01",
                "2010.01.31", "FOR", null, BigDecimal.valueOf(10), null, null,
                testAvdeling, null, null, null, null, null, null);
        utilTest.addAvdelingBudget(2006, BigDecimal.valueOf(1000), 1,
                testAvdeling);
        utilTest.addAvdelingBudget(2006, BigDecimal.valueOf(1000), 2,
                testAvdeling);
        utilTest.addAvdelingBudget(2006, BigDecimal.valueOf(1000), 3,
                testAvdeling);

        utilTest.addAvdelingKontrakt("2006.01.01", "2010.01.31", testAvdeling,
                "KVA", "FOR", "A", "BUD");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
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


    /**
     * Tester fakturering av forskudd budsjett for avdeling når det allerede er
     * fakturert
     * 
     * @throws Exception
     */
    public void testInvoiceForskuddKvaBudgetJanuarInvoiced() throws Exception {
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

        buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 9998,9998, "BUD", null, null,1,null);
        utilTest.addBunt(buntId);

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());

    }


    /**
     * Test fakturering av to perioder mhp regnskap
     * 
     * @throws Exception
     */
    public void test16045InvoiceTwoPeriodRegnskap() throws Exception {
        utilTest
                .addAvdelingBetingelse("MND", "KONTREK", 0, "2005.10.01",
                        "2006.01.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31");
        Integer buntId = utilTest.fakturerPeriode(new Integer(2005), 10, 11,
                "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(2, list.size());
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

        linjer = utilTest.findFakturaLinjerByFakturaId(list.get(1)
                .getFakturaId());
        assertEquals(1, linjer.size());

        fakturaLinje = linjer.get(0);

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Kontorrekvisita ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(250), fakturaLinje.getMvaBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());
        assertEquals(new BigDecimal(1250), fakturaLinje.getTotalBelop());

        utilTest.bokfoerBunt(buntId, "2006.05.01");

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(2, list.size());
        assertNotNull(list.get(0).getFakturaNr());

        Faktura faktura = list.get(0);
        utilTest
                .lazyLoadFaktura(faktura, new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_BOOK});
        Set<RegnskapKladd> kladder = faktura.getRegnskapKladds();
        assertEquals(2, kladder.size());

        faktura = list.get(1);
        utilTest
                .lazyLoadFaktura(faktura, new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_BOOK});
        kladder = faktura.getRegnskapKladds();
        assertEquals(2, kladder.size());

    }


    /**
     * Test fakturering av franchiseavgift som starter 2. jan for avdeling som
     * starter opp 2.januar, noe som betyr at omsetning skal ikke justeres
     * 
     * @throws Exception
     */
    public void test16047InvoiceFranchise2JanDep2Jan() throws Exception {
        testAvdeling = utilTest.addAvdeling(9976, "2006.01.01");
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.02",
                "2006.01.31", "ETT", null, BigDecimal.valueOf(3), null, null,
                testAvdeling, null, null, null, null, null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31", testAvdeling);
        utilTest.addAvdelingOmsetning(2006, BigDecimal.valueOf(1000), 1,
                testAvdeling,null);
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 9976,9976, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        assertEquals(new BigDecimal(3), fakturaLinje.getSats());
        assertEquals("Franchise-avgift ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(new BigDecimal(1000), fakturaLinje.getGrunnlagBelop());
        assertEquals(new BigDecimal(0), fakturaLinje.getAvregningBelop());
        assertEquals(new BigDecimal(1000), fakturaLinje.getGrunnlagBelop());
        assertEquals(new BigDecimal(30), fakturaLinje.getBelop());
        assertEquals(new BigDecimal(7.5), fakturaLinje.getMvaBelop().round(
                new MathContext(2, RoundingMode.HALF_EVEN)));
        assertEquals(new BigDecimal(37.5), fakturaLinje.getTotalBelop().round(
                new MathContext(3, RoundingMode.HALF_EVEN)));
        assertEquals(new BigDecimal(37.5), fakturaLinje.getTotalBelop().round(
                new MathContext(3, RoundingMode.HALF_EVEN)));

    }


    
}
