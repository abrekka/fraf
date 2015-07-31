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
import no.ica.fraf.model.BuntFeil;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class InvoiceTest extends TestCase {
	
	public InvoiceTest(boolean isStandalone){
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
     * Tester at ikke samme betingelse blir fakturert to ganger
     * 
     * @throws Exception
     */
    public void testInvoiceTwice() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2005.10.01",
                "2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
        utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2005),
                new Integer(10), new Integer(10), "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());

        buntId = utilTest.fakturerPeriode(new Integer(2005), new Integer(10),
                new Integer(10), "Diverse",3);
        utilTest.addBunt(buntId);

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(0, list.size());

        List<BuntFeil> feil = utilTest.findBuntFeilById(buntId);
        assertEquals(0, feil.size());
    }

    /**
     * Tester at avregning ikke kommer med i måned to dersom hele omsetning er lest inn
     * @throws Exception
     */
    public void testInvoiceTwiceSameMonth() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "FRA", 0, "2008.01.01",
                "2008.12.31", "ETT", null, new BigDecimal(3.5), null, null);
        utilTest.addAvdelingKontrakt("2005.10.01", "2008.12.31");
        
        utilTest.addAvdelingOmsetning(2008,BigDecimal.valueOf(1000),1,null);

        Integer buntId = utilTest.fakturerPeriode(2008,
                1,1, "Franchiseavgift",1);
        utilTest.addBunt(buntId);
        
        utilTest.addAvdelingOmsetningKorreksjon(2008,BigDecimal.valueOf(100),1);
        utilTest.addAvdelingOmsetning(2008,BigDecimal.valueOf(2000),2,null);
        
        buntId = utilTest.fakturerPeriode(2008,
                2,2, "Franchiseavgift",1);
        utilTest.addBunt(buntId);
        utilTest.addAvdelingOmsetningKorreksjon(2008,BigDecimal.valueOf(200),2);
        
        utilTest.addAvdelingBetingelse("MND", "HUS", 0, "2008.01.01",
                "2008.12.31", "ETT", null, new BigDecimal(3.5), null, null);
        
        buntId = utilTest.fakturerPeriode(2008,
                1,1, "Franchiseavgift",1);
        utilTest.addBunt(buntId);
        
        buntId = utilTest.fakturerPeriode(2008,
                2,2, "Franchiseavgift",1);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        
        Faktura faktura = list.get(0);
        utilTest.lazyLoadFaktura(faktura,new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_LINES});
        Set<FakturaLinje> linjer = faktura.getFakturaLinjes();
        
        assertEquals(1, linjer.size());
        FakturaLinje linje = linjer.iterator().next();
        
        assertEquals(BigDecimal.valueOf(0),linje.getAvregningBelop());

       }

    /**
     * Tester genereging av faktura
     * 
     * @throws Exception
     */
    public void testInvoice() throws Exception {
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

    }

    
    /**
     * Tester kreittnota når bunt ikke er bokført
     * 
     * @throws Exception
     */
    public void testCreditIkkeBokfort() throws Exception {
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

        Faktura faktura = list.get(0);

        try {
            utilTest.lagKredittnota(faktura.getFakturaId());
            assertEquals(
                    "Det skulle vært kastet en exception fordi faktura er ikke bokført",
                    true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tester kredittnota
     * 
     * @throws Exception
     */
    public void testCredit() throws Exception {
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

        utilTest.bokfoerBunt(buntId, "2005.11.01");

        utilTest.addBunt(buntId + 1);

        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        assertNotNull(list.get(0).getFakturaNr());

        Faktura faktura = list.get(0);

        utilTest.lagKredittnota(faktura.getFakturaId());
        faktura = utilTest.getFaktura(faktura.getFakturaId() + 1);
        assertNotNull(faktura);

        utilTest.setBuntStatus(buntId, "FA");

    }

    

}
