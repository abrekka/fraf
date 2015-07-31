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

public class InvoiceTest2 extends TestCase {
	public InvoiceTest2(boolean isStandalone){
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
        BigDecimal belopDays = BigDecimal.valueOf(20);
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

    /**
     * Tester at faktura for betingelse som start og slutter smame datoer som
     * periode
     * 
     * @throws Exception
     */
    public void testInvoiceStartStopInPeriodMonth() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2006.01.01",
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
        BigDecimal belopDays = BigDecimal.valueOf(31);
        BigDecimal wantedResult = dayBelop.multiply(belopDays,
                MathContext.DECIMAL32).round(
                new MathContext(4, RoundingMode.HALF_EVEN));
        wantedResult = wantedResult.round(new MathContext(5,
                RoundingMode.HALF_EVEN));

        BigDecimal mva = wantedResult.multiply(BigDecimal.valueOf(0.25)).round(
                new MathContext(3, RoundingMode.HALF_EVEN));
        BigDecimal total = wantedResult.add(mva).round(
                new MathContext(4, RoundingMode.HALF_EVEN));

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Kontorrekvisita ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(wantedResult, fakturaLinje.getBelop());
        assertEquals(mva, fakturaLinje.getMvaBelop());
        assertEquals(total, fakturaLinje.getTotalBelop());
        assertEquals(total, fakturaLinje.getTotalBelop());

    }


    /**
     * Tester endring av sats midt i periode
     * 
     * @throws Exception
     */
    public void testInvoiceChangeSatsInPeriod() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2006.01.20", "ETT", null, new BigDecimal(3), null, null);
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.21",
                "2010.01.31", "ETT", null, new BigDecimal(2), null, null);

        BigDecimal omsetning = new BigDecimal(100000);

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

        //FakturaLinje fakturaLinje = linjer.get(0);

        // 3225,81
        BigDecimal omsetningPrDag = omsetning.divide(new BigDecimal(31),
                MathContext.DECIMAL32);
        // 64516,13
        BigDecimal omsetningForste = omsetningPrDag.multiply(
                new BigDecimal(20), MathContext.DECIMAL32).round(
                new MathContext(6, RoundingMode.HALF_EVEN));
        // 35483,91
        BigDecimal omsetningAndre = omsetningPrDag.multiply(new BigDecimal(11),
                MathContext.DECIMAL32);
        // 193,55
        BigDecimal forsteBelop = omsetningForste.multiply(new BigDecimal(0.03),
                MathContext.DECIMAL32).round(
                new MathContext(6, RoundingMode.HALF_EVEN));
        // 70,97
        BigDecimal andreBelop = omsetningAndre.multiply(new BigDecimal(0.02),
                MathContext.DECIMAL32).round(
                new MathContext(5, RoundingMode.HALF_EVEN));

        BigDecimal mvaForste = forsteBelop.multiply(BigDecimal.valueOf(0.25))
                .round(new MathContext(5, RoundingMode.HALF_EVEN));
        BigDecimal mvaAndre = andreBelop.multiply(BigDecimal.valueOf(0.25))
                .round(new MathContext(5, RoundingMode.HALF_EVEN));

        BigDecimal totalForste = forsteBelop.add(mvaForste).round(
                new MathContext(6, RoundingMode.HALF_EVEN));
        BigDecimal totalAndre = andreBelop.add(mvaAndre).round(
                new MathContext(4, RoundingMode.HALF_EVEN));

        int linjeCount=0;
        for(FakturaLinje fakturaLinje:linjer){
        	linjeCount++;
        	if(fakturaLinje.getSats().equals(BigDecimal.valueOf(3))){
        		assertEquals("Franchise-avgift ", fakturaLinje.getLinjeBeskrivelse());
                assertEquals(forsteBelop, fakturaLinje.getBelop());
                assertEquals(mvaForste, fakturaLinje.getMvaBelop());
                assertEquals(totalForste, fakturaLinje.getTotalBelop());
                assertEquals(totalForste, fakturaLinje.getTotalBelop());
                assertEquals(omsetningForste, fakturaLinje.getGrunnlagBelop().round(
                        new MathContext(6, RoundingMode.HALF_EVEN)));
        	}else if(fakturaLinje.getSats().equals(BigDecimal.valueOf(2))){
        		assertEquals("Franchise-avgift ", fakturaLinje.getLinjeBeskrivelse());
                assertEquals(andreBelop, fakturaLinje.getBelop());
                assertEquals(mvaAndre, fakturaLinje.getMvaBelop());
                assertEquals(totalAndre, fakturaLinje.getTotalBelop());
                assertEquals(totalAndre, fakturaLinje.getTotalBelop());
                assertEquals(omsetningAndre, fakturaLinje.getGrunnlagBelop());
        	}
        }

        assertEquals(2,linjeCount);

    }

    /**
     * Tester fakturering ved hjelp av sats
     * 
     * @throws Exception
     */
    public void testInvoiceSats() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, new BigDecimal(3), null, null);
        BigDecimal omsetning = new BigDecimal(100000);

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
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal forsteBelop = omsetning.multiply(new BigDecimal(0.03),
                MathContext.DECIMAL32).round(
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
        assertEquals(omsetning, fakturaLinje.getGrunnlagBelop().round(
                new MathContext(6, RoundingMode.HALF_EVEN)));

    }


    /**
     * Tester fakturering av sats med gitt tak på omsetning
     * 
     * @throws Exception
     */
    public void testInvoiceSatsTak() throws Exception {

        BigDecimal omsetning = new BigDecimal(100000);
        BigDecimal tak = new BigDecimal(50000);
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, new BigDecimal(3), BigDecimal
                        .valueOf(0), tak);

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
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal forsteBelop = tak.multiply(new BigDecimal(0.03),
                MathContext.DECIMAL32).round(
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
        assertEquals(tak, fakturaLinje.getGrunnlagBelop().round(
                new MathContext(6, RoundingMode.HALF_EVEN)));

    }


    /**
     * Tester sats med tak større enn omsetning
     * 
     * @throws Exception
     */
    public void testInvoiceSatsTakBiggerThanBelop() throws Exception {

        BigDecimal omsetning = new BigDecimal(100000);
        BigDecimal tak = new BigDecimal(300000);
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, new BigDecimal(3), BigDecimal
                        .valueOf(0), tak);

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
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal forsteBelop = omsetning.multiply(new BigDecimal(0.03),
                MathContext.DECIMAL32).round(
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
        assertEquals(omsetning, fakturaLinje.getGrunnlagBelop().round(
                new MathContext(6, RoundingMode.HALF_EVEN)));

    }


    /**
     * Tester grenser med min og max verdi
     * 
     * @throws Exception
     */
    public void testInvoiceSatsTakFromTo() throws Exception {

        BigDecimal omsetning = new BigDecimal(100000);
        BigDecimal gulv = BigDecimal.valueOf(40000);
        BigDecimal tak = BigDecimal.valueOf(50000);

        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, new BigDecimal(3), gulv, tak);

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
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal forsteBelop = tak.subtract(gulv).multiply(
                new BigDecimal(0.03), MathContext.DECIMAL32).round(
                new MathContext(3, RoundingMode.HALF_EVEN));
        BigDecimal mvaForste = forsteBelop.multiply(BigDecimal.valueOf(0.25))
                .round(new MathContext(2, RoundingMode.HALF_EVEN));
        BigDecimal totalForste = forsteBelop.add(mvaForste).round(
                new MathContext(6, RoundingMode.HALF_EVEN));

        assertEquals(new BigDecimal(3), fakturaLinje.getSats());
        assertEquals("Franchise-avgift ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(forsteBelop, fakturaLinje.getBelop());
        assertEquals(mvaForste, fakturaLinje.getMvaBelop());
        assertEquals(totalForste, fakturaLinje.getTotalBelop());
        assertEquals(totalForste, fakturaLinje.getTotalBelop());
        assertEquals(tak.subtract(gulv).round(
                new MathContext(6, RoundingMode.HALF_EVEN)), fakturaLinje
                .getGrunnlagBelop().round(
                        new MathContext(6, RoundingMode.HALF_EVEN)));

    }


    /**
     * Tester tak som ligger over og gulv i mellom
     * 
     * @throws Exception
     */
    public void testInvoiceSatsTakOverGulvBetweeen() throws Exception {

        BigDecimal omsetning = new BigDecimal(100000);
        BigDecimal gulv = BigDecimal.valueOf(40000);
        BigDecimal tak = BigDecimal.valueOf(200000);

        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, new BigDecimal(3), gulv, tak);

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
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal forsteBelop = omsetning.subtract(gulv).multiply(
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
        assertEquals(omsetning.subtract(gulv).round(
                new MathContext(6, RoundingMode.HALF_EVEN)), fakturaLinje
                .getGrunnlagBelop().round(
                        new MathContext(6, RoundingMode.HALF_EVEN)));

    }

    /**
     * Tester tak og gulv som ligger over grunnlag
     * 
     * @throws Exception
     */
    public void testInvoiceSatsTakOverGulvOver() throws Exception {

        BigDecimal omsetning = new BigDecimal(100000);
        BigDecimal gulv = BigDecimal.valueOf(200000);
        BigDecimal tak = BigDecimal.valueOf(300000);

        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2006.01.01",
                "2010.01.31", "ETT", null, new BigDecimal(3), gulv, tak);

        utilTest.addAvdelingOmsetning(new Integer(2006), omsetning,
                new Integer(1),null);

        utilTest.addAvdelingKontrakt("2005.10.01", "2010.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 1, 1,
                "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());
    }


    /**
     * Tester fakturering av sats med tak og uten tak
     * 
     * @throws Exception
     */
    
    

}
