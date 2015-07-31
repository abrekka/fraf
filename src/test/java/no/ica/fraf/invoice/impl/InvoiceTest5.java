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
import no.ica.fraf.model.FakturaFeil;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.FakturaTekst;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class InvoiceTest5 extends TestCase {
	public InvoiceTest5(boolean isStandalone){
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
     * Tester fakturering av franchiseavgift i jan, legger til betingelse, ny
     * jan så februar
     * 
     * @throws Exception
     */
    public void test16282InvoiceFranchiseJanJanFeb() throws Exception {
        utilTest
                .addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.12.31");
        utilTest.addAvdelingOmsetning(2006, BigDecimal.valueOf(1000), 1,null);
        utilTest.addAvdelingOmsetning(2006, BigDecimal.valueOf(1000), 2,null);

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        utilTest
                .addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(2000), null,
                        null, null);

        buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        linjer = utilTest.findFakturaLinjerByFakturaId(list.get(0)
                .getFakturaId());
        assertEquals(1, linjer.size());

        buntId = utilTest.fakturerPeriode(new Integer(2006), 2, 2,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());

        linjer = utilTest.findFakturaLinjerByFakturaId(list.get(0)
                .getFakturaId());
        assertEquals(2, linjer.size());

        for (FakturaLinje fakturaLinje : linjer) {
            if (fakturaLinje.getLinjeBeskrivelse().equalsIgnoreCase("Husleie ")) {
                assertEquals(new BigDecimal(0), fakturaLinje.getSats());
                assertEquals(new BigDecimal(1000).round(new MathContext(4,
                        RoundingMode.HALF_EVEN)), fakturaLinje
                        .getGrunnlagBelop().round(
                                new MathContext(4, RoundingMode.HALF_EVEN)));
                assertEquals(new BigDecimal(0), fakturaLinje
                        .getAvregningBelop());
                assertEquals(new BigDecimal(2000), fakturaLinje.getBelop());
                assertEquals(new BigDecimal(500), fakturaLinje.getMvaBelop()
                        .round(new MathContext(3, RoundingMode.HALF_EVEN)));
                assertEquals(new BigDecimal(2500), fakturaLinje.getTotalBelop()
                        .round(new MathContext(4, RoundingMode.HALF_EVEN)));
                assertEquals(new BigDecimal(2500), fakturaLinje.getTotalBelop()
                        .round(new MathContext(4, RoundingMode.HALF_EVEN)));
            }
        }

        Faktura faktura = list.get(0);
        utilTest
                .lazyLoadFaktura(faktura, new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_TEXT});

        Set<FakturaTekst> fakturaTekster = faktura.getFakturaTeksts();

        for (FakturaTekst fakturaTekst : fakturaTekster) {
            if (fakturaTekst.getFakturaTekst().equalsIgnoreCase(
                    "Fakturert forrige periode")) {
                assertEquals(BigDecimal.valueOf(3750), fakturaTekst.getBelop());
            }
        }

    }


    /**
     * Tester logging av mulig dobbeltfakturering
     * 
     * @throws Exception
     */
    public void testInvoiceDoubleFakturaLinje() throws Exception {
        utilTest
                .addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest
                .addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(2, linjer.size());

        List<FakturaFeil> feil = utilTest.findFakturaFeilByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(2, feil.size());
        assertEquals("Mulig dobbeltfakturering", feil.get(1).getFeilKode()
                .getFeilBeskrivelse());

    }


    /**
     * Tester kredittnota og at det blir laget bokføringsinfo
     * 
     * @throws Exception
     */
    public void test16566InvoiceCreditBokforing() throws Exception {
        utilTest
                .addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        utilTest.bokfoerBunt(buntId, "2006.02.01");

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNotNull(list.get(0).getFakturaNr());

        buntId = utilTest.lagKredittnota(list.get(0).getFakturaId());
        utilTest.addBunt(buntId);

        utilTest.bokfoerBunt(buntId, "2006.02.01");

        List<RegnskapKladd> book = utilTest.findRegnskapKladdByBuntId(buntId);
        assertNotNull(book);
        assertEquals(2, book.size());

    }


    /**
     * Tester fakturering hvor avdeling tas bort
     * 
     * @throws Exception
     */
    public void test16184InvoiceNot1499() throws Exception {
        utilTest
                .addAvdelingBetingelse("MND", "HUS", 0, "2006.01.01",
                        "2006.12.31", "ETT", BigDecimal.valueOf(1000), null,
                        null, null);
        utilTest.addAvdelingKontrakt("2005.01.01", "2006.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift", 1499,1499, "OMS", new Integer[] { 1499 }, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());

    }


    /**
     * Tester fakturering hvor kun betingelser som er etterskuddsvis blir tatt
     * med
     * 
     * @throws Exception
     */
    public void test16836InvoiceAvregningTypeETT() throws Exception {
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
                "Franchiseavgift", 9999,9999, "OMS", null, "ETT",1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());
        assertEquals("Husleie ", linjer.get(0).getLinjeBeskrivelse());

    }


}
