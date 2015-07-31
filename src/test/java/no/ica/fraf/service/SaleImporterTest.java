package no.ica.fraf.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.SapSale;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.service.impl.SaleImporter;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class SaleImporterTest extends MockObjectTestCase {
	private static final String IMPORTED_DIR = "imported";
	private static final String BASE_DATA_DIR = "base_data";
	private static final String NO_SALES_XML_FILENAME = "no_file.xml";
	//private String SALES_XML_FILENAME="sales.xml";
	private String SALES_XML_FILENAME="TEST_FranchiseesSales.xml";
	private Integer TEST_YEAR=2009;
	//private Integer TEST_PERIODE=5;
	private Integer TEST_PERIODE=10;
	//private Integer TEST_DEPNR=1327;
	private Integer TEST_DEPNR=5849;
	private SapSaleManager sapSaleManager;
	static {
		BaseManager.setTest(true);
		FrafMain.setStandalone(true);
	}
	
	@Override
	protected void setUp() throws Exception {
		sapSaleManager=(SapSaleManager)ModelUtil.getBean(SapSaleManager.MANAGER_NAME);
	}


	@Override
	protected void tearDown() throws Exception {
		SapSale sale = sapSaleManager.findByAvdnrPeriode(TEST_DEPNR, TEST_YEAR, TEST_PERIODE);
		if(sale!=null){
			sapSaleManager.removeSapSale(sale);
		}
		
	}


	public void testImportSalesData() throws Exception{
		SaleImporter saleImporter = new SaleImporter(sapSaleManager,BASE_DATA_DIR,SALES_XML_FILENAME,IMPORTED_DIR);
		saleImporter.importXmlData();
		
		SapSale sale = sapSaleManager.findByAvdnrPeriode(TEST_DEPNR, TEST_YEAR, TEST_PERIODE);
		assertNotNull(sale);
		
		String selectorString="DEP_"+TEST_DEPNR+"_"+TEST_YEAR+"_"+TEST_PERIODE;
			Sale.valueOf(selectorString).validate(sale);
			
			FileUtils.copyFileToDirectory(new File(IMPORTED_DIR+"//"+SALES_XML_FILENAME), new File(BASE_DATA_DIR));
	}
	
	public void testNoImportFile(){
		try{
		SaleImporter saleImporter = new SaleImporter(sapSaleManager,BASE_DATA_DIR,NO_SALES_XML_FILENAME,IMPORTED_DIR);
		saleImporter.importXmlData();
		assertFalse("Her skulle det kommet en feilmelding",true);
		
		SapSale sale = sapSaleManager.findByAvdnrPeriode(TEST_DEPNR, TEST_YEAR, TEST_PERIODE);
		assertNotNull(sale);
		
		String selectorString="DEP_"+TEST_DEPNR+"_"+TEST_YEAR+"_"+TEST_PERIODE;
			Sale.valueOf(selectorString).validate(sale);
		}catch(FrafException ex){
			
		}
	}
	
	
	
	private enum Sale{
		DEP_1327_2009_5 {
			@Override
			public void validate(SapSale sale) {
				assertEquals(Integer.valueOf(2009), sale.getYear());
				assertEquals(Integer.valueOf(5), sale.getPeriode());
				assertEquals(Integer.valueOf(1327), sale.getDepartmentNr());
				assertEquals(BigDecimal.valueOf(2465527.84), sale.getSale());
				
			}
		},
		DEP_1342_2009_8 {
			@Override
			public void validate(SapSale sale) {
				assertEquals(Integer.valueOf(2009), sale.getYear());
				assertEquals(Integer.valueOf(8), sale.getPeriode());
				assertEquals(Integer.valueOf(1342), sale.getDepartmentNr());
				assertEquals(BigDecimal.valueOf(-9777.6), sale.getSale());
				
			}
		},
		DEP_5849_2009_10 {
			@Override
			public void validate(SapSale sale) {
				assertEquals(Integer.valueOf(2009), sale.getYear());
				assertEquals(Integer.valueOf(10), sale.getPeriode());
				assertEquals(Integer.valueOf(5849), sale.getDepartmentNr());
				assertEquals(BigDecimal.valueOf(14385964.91), sale.getSale());
				
			}
		};
		public abstract void validate(SapSale sale);
	}

}
