package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
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

public class InvoiceTest7 extends TestCase {
	public InvoiceTest7(boolean isStandalone){
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
    /**
     * Datoformaterer
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy.MM.dd");

    
    
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
     * Tester bokføring av kvartalsvis etterskuddsvis
     * 
     * @throws Exception
     */
    public void testInvoiceFakturaBookingKvaEtt() throws Exception {
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

        List<RegnskapKladd> regnskapKladd = utilTest
                .findRegnskapKladdByBuntId(buntId);
       
        utilTest.bokfoerBunt(buntId, "2005.10.31");

        regnskapKladd = utilTest.findRegnskapKladdByBuntId(buntId);
        assertEquals(2, regnskapKladd.size());

        assertEquals("110", regnskapKladd.get(0).getSelskap());
        assertEquals(Integer.valueOf(999910), regnskapKladd.get(0).getAvdnr());
        assertEquals("680010", regnskapKladd.get(0).getKonto());
        assertEquals("2005.10.01", simpleDateFormat.format(regnskapKladd.get(0)
                .getRegnskapDato()));
        assertEquals(BigDecimal.valueOf(-1250), regnskapKladd.get(0).getBelop());
        assertEquals("Kontorrekvisita", regnskapKladd.get(0).getTekst());
        assertEquals("01", regnskapKladd.get(0).getMvaKode());
        assertEquals("F", regnskapKladd.get(0).getKontoType());
        assertNull(regnskapKladd.get(0).getForfallDato());
        assertNotNull(regnskapKladd.get(0).getFakturaNr());
        assertEquals("FRAF", regnskapKladd.get(0).getKladdNavn());

        assertEquals("110", regnskapKladd.get(1).getSelskap());
        assertEquals(Integer.valueOf(9999), regnskapKladd.get(1).getAvdnr());
        assertEquals("9999", regnskapKladd.get(1).getKonto());
        assertEquals("2005.10.01", simpleDateFormat.format(regnskapKladd.get(1)
                .getRegnskapDato()));
        assertEquals(BigDecimal.valueOf(1250), regnskapKladd.get(1).getBelop());
        assertEquals("Diverse", regnskapKladd.get(1).getTekst());
        assertNull(regnskapKladd.get(1).getMvaKode());
        assertNotNull(regnskapKladd.get(1).getFakturaNr());
        assertEquals("D", regnskapKladd.get(1).getKontoType());
        assertEquals("2005.10.01", simpleDateFormat.format(regnskapKladd.get(1)
                .getForfallDato()));
        assertEquals("FRAF", regnskapKladd.get(1).getKladdNavn());
    }


    /**
     * Tester at faktura for betingelse som ikke slutter sist i måneden ikk får
     * full faktura
     * 
     * @throws Exception
     */
    public void testInvoiceAvsluttetAvdelingFastBelop() throws Exception {
        Avdeling avdeling = utilTest.findAvdeling(5952);

        avdelingBetingelse = utilTest.addAvdelingBetingelse("MND", "TERM",
                0, "2006.01.01", "2006.12.31", "ETT", new BigDecimal(1000),
                null, null, null, avdeling, null, null, null, null, null, null);
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 4, 4,
                "Diverse", 5952,5952, "OMS", null, null,1,null);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        FakturaLinje fakturaLinje = linjer.get(0);

        BigDecimal belop = BigDecimal.valueOf(1000);
        BigDecimal days = BigDecimal.valueOf(30);
        BigDecimal dayBelop = belop.divide(days, new MathContext(100));
        BigDecimal belopDays = BigDecimal.valueOf(23);
        BigDecimal wantedResult = dayBelop.multiply(belopDays,
                MathContext.DECIMAL32);
        wantedResult = wantedResult.round(new MathContext(5,
                RoundingMode.HALF_EVEN));

        BigDecimal mva = wantedResult.multiply(BigDecimal.valueOf(0.25)).round(
                new MathContext(5, RoundingMode.HALF_EVEN));
        BigDecimal total = wantedResult.add(mva).round(
                new MathContext(6, RoundingMode.HALF_EVEN));

        assertEquals(new BigDecimal(0), fakturaLinje.getSats());
        assertEquals("Bestillingsterminal leie ", fakturaLinje.getLinjeBeskrivelse());
        assertEquals(wantedResult, fakturaLinje.getBelop());
        assertEquals(mva, fakturaLinje.getMvaBelop());
        assertEquals(total, fakturaLinje.getTotalBelop());
        assertEquals(total, fakturaLinje.getTotalBelop());

    }


    /**
     * Tester laginf av kredittnota, hvor denne bunten rulles tilbake og ny
     * kredittnota skal lages
     * 
     * @throws Exception
     */
    public void testInvoiceKredittnotaRollbackKredittnota() throws Exception {
        // Avdeling avdeling = utilTest.findAvdeling(5952);

        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2006.01.01",
                "2006.12.31", "ETT", new BigDecimal(1000), null, null, null);
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 4, 4,
                "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

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

    /**
     * Tester at en faktura som har blitt reversert ikke lar reversere på nytt
     * 
     * @throws Exception
     */
    public void testInvoiceKredittnotaKredittnota() throws Exception {
        // Avdeling avdeling = utilTest.findAvdeling(5952);

        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2006.01.01",
                "2006.12.31", "ETT", new BigDecimal(1000), null, null, null);
        Integer buntId = utilTest.fakturerPeriode(new Integer(2006), 4, 4,
                "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNull(list.get(0).getFakturaNr());

        List<FakturaLinje> linjer = utilTest.findFakturaLinjerByFakturaId(list
                .get(0).getFakturaId());
        assertEquals(1, linjer.size());

        utilTest.bokfoerBunt(buntId, "2006.04.30");

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNotNull(list.get(0).getFakturaNr());

        Integer buntIdKredittnota = utilTest.lagKredittnota(list.get(0)
                .getFakturaId());
        utilTest.addBunt(buntIdKredittnota);

        try {
            buntIdKredittnota = utilTest.lagKredittnota(list.get(0)
                    .getFakturaId());
            utilTest.addBunt(buntIdKredittnota);
            assertNotNull("Det skulle vært kastet en exception", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


