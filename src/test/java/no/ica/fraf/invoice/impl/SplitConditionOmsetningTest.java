package no.ica.fraf.invoice.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.ImportBetingelseDAO;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class SplitConditionOmsetningTest extends TestCase {
	public SplitConditionOmsetningTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
    private UtilTest utilTest = new UtilTest();
    SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyyyy");
    protected void setUp() throws Exception {
        utilTest.setUp();
        
        
    }
    protected void tearDown() throws Exception {
		utilTest.tearDown();
	}

    public void testSplitConditionOmsetningInfo()throws Exception{
    	utilTest.addAvdelingKontrakt("2005.01.01","2009.12.31");
    	utilTest.addAvdelingBetingelse("MND","FRA",0,"2005.01.01","2009.12.31","ETT",null,BigDecimal.valueOf(3.5),null,null);
    	utilTest.addAvdelingBetingelse("MND","HUS",0,"2005.01.01","2008.05.04","ETT",null,BigDecimal.valueOf(4.85),null,null);
    	utilTest.addAvdelingBetingelse("MND","HUS",0,"2008.05.05","2009.12.31","ETT",null,BigDecimal.valueOf(3.2),null,null);
    	FranchisePkgDAO franchisePkgDAO=(FranchisePkgDAO)ModelUtil.getBean("franchisePkgDAO");
    	BuntDAO buntDAO=(BuntDAO)ModelUtil.getBean("buntDAO");
    	FakturaDAO fakturaDAO=(FakturaDAO)ModelUtil.getBean("fakturaDAO");
    	BigDecimal omsetningApril = BigDecimal.valueOf(3658742.66);
    	utilTest.addAvdelingOmsetning(2008,omsetningApril,4,BigDecimal.ZERO);
    	Integer buntId=franchisePkgDAO.fakturerPeriode(10,2008,4,9999,9999,dateFormat.parse("03062008"),dateFormat.parse("17062008"),1,1,null,null,null,null,1);
    	utilTest.addBunt(buntId);
    	BigDecimal korreksjonApril = BigDecimal.valueOf(444689.88);
    	utilTest.updateAvdelingOmsetning(2008,korreksjonApril,4);
    	BigDecimal omsetning = BigDecimal.valueOf(3658742.66);
    	//BigDecimal avregning = BigDecimal.valueOf(444689.88);
    	utilTest.addAvdelingOmsetning(2008,omsetning,5,BigDecimal.ZERO);
    	
    	
    	
    	buntId=franchisePkgDAO.fakturerPeriode(10,2008,5,9999,9999,dateFormat.parse("03062008"),dateFormat.parse("17062008"),1,1,null,null,null,null,1);
    	assertNotNull(buntId);
    	utilTest.addBunt(buntId);
    	Bunt bunt = buntDAO.findByBuntId(buntId);
    	assertNotNull(bunt);
    	buntDAO.lazyLoadBunt(bunt,new LazyLoadBatchEnum[]{LazyLoadBatchEnum.INVOICES});
    	
    	assertNotNull(bunt);
    	assertEquals(true,bunt.getFakturas().size()==1);
    	
    	Faktura faktura = (Faktura)bunt.getFakturas().iterator().next();
    	
    	fakturaDAO.lazyLoad(faktura,new LazyLoadInvoiceEnum[]{LazyLoadInvoiceEnum.INVOCIE_ITEM});
    	
    	assertEquals(true,faktura.getFakturaLinjes().size()==3);
    	Set<FakturaLinje> linjer = faktura.getFakturaLinjes();
    	int count=0;
    	for(FakturaLinje linje:linjer){
    		
    		
    		if(linje.getLinjeBeskrivelse().trim().equalsIgnoreCase("Franchise-avgift")){
    			count++;
    			assertEquals(omsetning,linje.getOmsetningBelop());
    			assertEquals(korreksjonApril,linje.getAvregningBelop());
    			assertEquals(omsetning.add(korreksjonApril),linje.getGrunnlagBelop());
    			assertEquals(omsetning.add(korreksjonApril).multiply(BigDecimal.valueOf(0.035)).setScale(2,RoundingMode.HALF_UP),linje.getBelop());
    			assertEquals("03",linje.getMvaKode());
    			assertEquals(omsetning.add(korreksjonApril).multiply(BigDecimal.valueOf(0.035)).multiply(BigDecimal.valueOf(0.25)).setScale(2,RoundingMode.UP),linje.getMvaBelop());
    			assertEquals(omsetning.add(korreksjonApril).multiply(BigDecimal.valueOf(0.035)).add(omsetning.add(korreksjonApril).multiply(BigDecimal.valueOf(0.035)).multiply(BigDecimal.valueOf(0.25))).setScale(2,RoundingMode.UP),linje.getTotalBelop());
    		}
    		if(linje.getLinjeBeskrivelse().trim().equalsIgnoreCase("Husleie")&&linje.getSats().equals(BigDecimal.valueOf(4.85))){
    			count++;
    			assertEquals(omsetning.divide(BigDecimal.valueOf(31),new MathContext(100)).multiply(BigDecimal.valueOf(4)).setScale(2,RoundingMode.HALF_UP),linje.getOmsetningBelop());
    			assertEquals(korreksjonApril,linje.getAvregningBelop());
    			assertEquals(omsetning.divide(BigDecimal.valueOf(31),new MathContext(100)).multiply(BigDecimal.valueOf(4)).add(korreksjonApril).setScale(2,RoundingMode.HALF_UP),linje.getGrunnlagBelop());
    			assertEquals(omsetning.divide(BigDecimal.valueOf(31),new MathContext(100)).multiply(BigDecimal.valueOf(4)).add(korreksjonApril).multiply(BigDecimal.valueOf(0.0485)).setScale(2,RoundingMode.HALF_UP),linje.getBelop());
    			assertEquals("03",linje.getMvaKode());
    			assertEquals(omsetning.divide(BigDecimal.valueOf(31),new MathContext(100)).multiply(BigDecimal.valueOf(4)).add(korreksjonApril).multiply(BigDecimal.valueOf(0.0485)).multiply(BigDecimal.valueOf(0.25)).setScale(2,RoundingMode.HALF_UP),linje.getMvaBelop());
    			assertEquals(linje.getBelop().add(linje.getMvaBelop()).setScale(2,RoundingMode.HALF_UP),linje.getTotalBelop());
    		}
    		if(linje.getLinjeBeskrivelse().trim().equalsIgnoreCase("Husleie")&&linje.getSats().equals(BigDecimal.valueOf(3.2))){
    			count++;
    			assertEquals(omsetning.divide(BigDecimal.valueOf(31),new MathContext(100)).multiply(BigDecimal.valueOf(27)).setScale(1,RoundingMode.UP),linje.getOmsetningBelop().setScale(1,RoundingMode.UP));
    			assertEquals(BigDecimal.valueOf(0),linje.getAvregningBelop());
    			assertEquals(linje.getOmsetningBelop(),linje.getGrunnlagBelop());
    			assertEquals(linje.getOmsetningBelop().multiply(BigDecimal.valueOf(0.032)).setScale(1,RoundingMode.HALF_UP),linje.getBelop().setScale(1,RoundingMode.HALF_UP));
    			assertEquals("03",linje.getMvaKode());
    			assertEquals(linje.getBelop().multiply(BigDecimal.valueOf(0.25)).setScale(2,RoundingMode.HALF_UP),linje.getMvaBelop());
    			assertEquals(linje.getBelop().add(linje.getMvaBelop()).setScale(2,RoundingMode.HALF_UP),linje.getTotalBelop());
    		}
    		if(linje.getLinjeBeskrivelse().trim().equalsIgnoreCase("Inventarleie")){
    			count++;
    			assertEquals(omsetning,linje.getOmsetningBelop());
    			assertEquals(korreksjonApril,linje.getAvregningBelop());
    			assertEquals(omsetning.add(korreksjonApril),linje.getGrunnlagBelop());
    			assertEquals(linje.getGrunnlagBelop().multiply(BigDecimal.valueOf(0.02)).setScale(2,RoundingMode.HALF_UP),linje.getBelop());
    			assertEquals("03",linje.getMvaKode());
    			assertEquals(linje.getBelop().multiply(BigDecimal.valueOf(0.25)).setScale(2,RoundingMode.HALF_UP),linje.getMvaBelop());
    			assertEquals(linje.getBelop().add(linje.getMvaBelop()).setScale(2,RoundingMode.HALF_UP),linje.getTotalBelop());
    		}
    	}
    	assertEquals(3,count);
    }
}
