package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

public class FakturaPkgDaoTest extends TestCase {
	public FakturaPkgDaoTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
    
    Avdeling avdeling9976;
    BetingelseType betingelseTypeTest;
    
    private UtilTest utilTest = new UtilTest();

    protected void setUp() throws Exception {
        super.setUp();
        utilTest.setUp();
    }
    
    protected void tearDown() throws Exception {
    	utilTest.removeRegnskapForAvdeling(avdeling9976);
    	utilTest.removeFakturaForAvdleing(avdeling9976);
    	utilTest.removeAvdeling(avdeling9976);
   

        utilTest.tearDown();
        utilTest.removeBetingelseType(betingelseTypeTest);
    }
    
    public void testNoInvoiceNr() throws Exception {
        utilTest.addAvdelingBetingelse("MND", "KONTREK", 0, "2005.10.01",
                "2006.01.31", "ETT", new BigDecimal(1000), null, null, null);
        utilTest.addAvdelingKontrakt("2005.10.01", "2006.01.31");

        Integer buntId = utilTest.fakturerPeriode(new Integer(2005),
                new Integer(10), new Integer(10), "Diverse",3);
        utilTest.addBunt(buntId);

        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        
        Faktura faktura = list.get(0);
        assertNotNull(faktura);
        
        assertNull(faktura.getFakturaNr());
        
        utilTest.bokfoerBunt(buntId,"2008.08.20");
        
        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        
        faktura = list.get(0);
        assertNotNull(faktura);
        
        assertNotNull(faktura.getFakturaNr());
    }
    
    public void testInvoiceTwoGroups() throws Exception{
    	utilTest.addAvdelingBetingelse("MND", "CREDIT", 0, "2008.01.01",
                "2008.12.31", "ETT", new BigDecimal(1000), null, null, null);
    	utilTest.addAvdelingBetingelse("MND", "TOMRA", 0, "2008.01.01",
                "2008.12.31", "ETT", new BigDecimal(2000), null, null, null);
        utilTest.addAvdelingKontrakt("2008.01.01", "2008.12.31");
        
        Integer buntId = utilTest.fakturerPeriode(new Integer(2008),
                1, 1, null,1,new Integer[]{2,5});
        utilTest.addBunt(buntId);
        
        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        
        Faktura faktura = list.get(0);
        assertNotNull(faktura);
        
        assertEquals(Integer.valueOf(1),faktura.getBokfSelskap().getSelskapId());
        
        utilTest.lazyLoadFaktura(faktura,new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_LINES,LazyLoadFakturaEnum.LOAD_INVOICE_BOOK});
        
        Set<FakturaLinje> linjer = faktura.getFakturaLinjes();
        assertEquals(2,linjer.size());
        
        for(FakturaLinje linje:linjer){
        	if(linje.getBetingelseType().getBetingelseTypeKode().equalsIgnoreCase("CREDIT")){
        		assertEquals(BigDecimal.valueOf(1000),linje.getBelop());
        	}else{
        		assertEquals(BigDecimal.valueOf(2000),linje.getBelop());
        	}
        }
        Set<RegnskapKladd> kladder = faktura.getRegnskapKladds();
        
        assertEquals(3,kladder.size());
        
        for(RegnskapKladd kladd:kladder){
        	if(kladd.getKontoType().equalsIgnoreCase("D")){
        		assertEquals("FRAF",kladd.getTekst());
        	}
        }
        
    }
    
    public void testInvoiceOwnAndFranchiseSameCondition() throws Exception{
    	BokfSelskap selskap = utilTest.findBokfSelskap("100");
    	betingelseTypeTest=utilTest.addBetingelseType("IT","TestIt","TET",selskap,"1","2","01","03");
    	
    	utilTest.addAvdelingBetingelse("MND", "TET", 0, "2008.01.01",
                "2008.12.31", "ETT", new BigDecimal(1000), null, null, null);
    	utilTest.addAvdelingKontrakt("2008.01.01", "2008.12.31");
    	
    	avdeling9976 = utilTest.addAvdeling(9976,"2008.01.01");
    	utilTest.addAvdelingBetingelse("MND", "TET", 0, "2008.01.01",
                "2008.12.31", "ETT", new BigDecimal(1000), null, null, null,avdeling9976,null,null,null,null,null,null);
    	utilTest.addAvdelingKontrakt("2008.01.01", "2008.12.31",avdeling9976);
    	
    	
    	
    	
        
        Integer buntId = utilTest.fakturerPeriode(new Integer(2008),
                1, 1, null,1,new Integer[]{5});
        utilTest.addBunt(buntId);
        
        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        
        Faktura faktura = list.get(0);
        utilTest.lazyLoadFaktura(faktura,new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_BOOK});
        
Set<RegnskapKladd> kladder = faktura.getRegnskapKladds();
        
        assertEquals(2,kladder.size());
        
        for(RegnskapKladd kladd:kladder){
        	if(kladd.getKontoType().equalsIgnoreCase("F")){
        	assertEquals("2",kladd.getKonto());
        	assertEquals("03",kladd.getMvaKode());
        	}
        }
        
        buntId = utilTest.fakturerPeriode(new Integer(2008),
                1, 1, null,1,new Integer[]{5},9976);
        utilTest.addBunt(buntId);
        
        list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
        
        faktura = list.get(0);
        utilTest.lazyLoadFaktura(faktura,new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_BOOK});
        
        kladder = faktura.getRegnskapKladds();
                
                assertEquals(2,kladder.size());
                
                for(RegnskapKladd kladd:kladder){
                	if(kladd.getKontoType().equalsIgnoreCase("F")){
                	assertEquals("2",kladd.getKonto());
                	assertEquals("03",kladd.getMvaKode());
                	}
                }
    }
    
    public void testInvoiceDepWithTwoContractsExpired()throws Exception{
    	BokfSelskap selskap = utilTest.findBokfSelskap("100");
    	betingelseTypeTest=utilTest.addBetingelseType("Div Bonus/avr","TestIt","TET",selskap,"1","2","01","03");
    	utilTest.addAvdelingBetingelse("MND", "TET", 0, "2008.01.01",
                "2008.12.31", "ETT", new BigDecimal(1000), null, null, null);
    	
    	utilTest.addAvdelingKontrakt("2007.01.01", "2007.12.31");
    	utilTest.addAvdelingKontrakt("2008.01.01", "2008.10.31");
    	
    	Integer buntId = utilTest.fakturerPeriode(new Integer(2008),
                11, 11, null,1,new Integer[]{7});
        utilTest.addBunt(buntId);
        
        List<Faktura> list = utilTest.findFakturaerByBuntId(buntId);
        assertEquals(1, list.size());
    }
}
