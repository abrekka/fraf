package no.ica.fraf.service;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.SapChain;
import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.model.SapDistrictManager;
import no.ica.fraf.model.SapRegion;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.service.impl.StoreInfoImporterImpl;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jmock.integration.junit3.MockObjectTestCase;

public class StoreInfoImporterTest extends MockObjectTestCase {
	//private static final String STORES_XML_FILENAME="stores.xml";
	private static final String STORES_XML_FILENAME="SAP_EXS03A_StoreInfo_091019182554.xml";
	private SapDepartmentManager sapDepartmentManager;
	private SapRegionManager sapRegionManager;
	private SapChainManager sapChainManager;
	private SapDistrictManagerManager sapDistrictManagerManager;
	
	static {
		BaseManager.setTest(true);
		FrafMain.setStandalone(true);
	}
	
	@Override
	protected void setUp() throws Exception {
		sapDepartmentManager=(SapDepartmentManager)ModelUtil.getBean(SapDepartmentManager.MANAGER_NAME);
		sapRegionManager=(SapRegionManager)ModelUtil.getBean(SapRegionManager.MANAGER_NAME);
		sapChainManager=(SapChainManager)ModelUtil.getBean(SapChainManager.MANAGER_NAME);
		sapDistrictManagerManager=(SapDistrictManagerManager)ModelUtil.getBean(SapDistrictManagerManager.MANAGER_NAME);
	}

	public void testCheckStoreInfoFile() throws Exception{
		File file =new File("base_data/stores.xml");
		if(!file.exists()){
			File importedFile = new File("base_data/imported/stores.xml");
			File importDir=new File("base_data");
			FileUtils.copyFileToDirectory(importedFile, importDir);

			FileUtils.forceDelete(importedFile);
		}
		SapXmlDataImporter storeInfoImporter=new StoreInfoImporterImpl("base_data",STORES_XML_FILENAME,sapDepartmentManager,sapRegionManager,sapChainManager,sapDistrictManagerManager,"h://imported/");
		assertEquals(true, storeInfoImporter.checkFiles());
	}
	
	public void testImportStoreInfo() throws Exception{
		SapXmlDataImporter storeInfoImporter=new StoreInfoImporterImpl("base_data",STORES_XML_FILENAME,sapDepartmentManager,sapRegionManager,sapChainManager,sapDistrictManagerManager,"h://imported/");
		storeInfoImporter.importXmlData();
		
		List<SapDepartment> departments = sapDepartmentManager.findAll(1500,1500);
		assertNotNull(departments);
		assertEquals(true, departments.size()==1);
		
		for(SapDepartment avd:departments){
			Avdeling.valueOf(StringUtils.upperCase(avd.getAvdelingNavn().replace(" ", "_"))).validate(avd);
		}
		
		List<SapRegion> regions = sapRegionManager.findAll(3,6);
		assertNotNull(regions);
		assertEquals(4, regions.size());
		
		for(SapRegion region:regions){
			Region.valueOf(StringUtils.upperCase(region.getRegionName().replace(" ", "_").replace("/", "").replace("-", ""))).validate(region);
		}
		
		List<SapChain> chains = sapChainManager.findAll(10,19);
		assertNotNull(chains);
		assertEquals(10, chains.size());
		
		for(SapChain chain:chains){
			Chain.valueOf(StringUtils.upperCase(chain.getChainName().replace(" ", "_").replace("&", ""))).validate(chain);
		}
		
		List<SapDistrictManager> districtManagers = sapDistrictManagerManager.findAll();
		assertNotNull(districtManagers);
		assertEquals(true, districtManagers.size()>5);
		
		
		
		File importFile = new File("base_data/stores.xml");
		assertEquals(false, importFile.exists());
	}
	
	private enum Avdeling{
		RIMI_KASPERGÅRDEN {
			@Override
			public void validate(SapDepartment avdeling) {
				assertEquals("Kasper Andersensvei 4-8", avdeling.getAdr1());
				assertEquals("RIMI NORGE", avdeling.getKjede());
				assertEquals("RIMI ØST", avdeling.getRegion());
				assertEquals("AP", avdeling.getDatasetConcorde());
				assertEquals("Rønning Handel AS", avdeling.getJuridiskEierNavn());
				assertEquals(Integer.valueOf(2815), avdeling.getPostnr());
				assertEquals("GJØVIK", avdeling.getPoststed());
				assertEquals("Trond Rønningsveen", avdeling.getAnsvarlig());
				assertEquals("2008-04-29", DATE_FORMAT.format(avdeling.getDtSlutt()));
				assertEquals("2005-03-07", DATE_FORMAT.format(avdeling.getDtStart()));
				//assertEquals(Integer.valueOf(301629), avdeling.getButiksNr());
				assertEquals("P150", avdeling.getAvtaletype());
				assertEquals("Arild Haugen", avdeling.getDriftslederNavn());
				assertEquals(BigDecimal.valueOf(Double.valueOf("7080001091187")), avdeling.getLokasjonsnr());
				assertEquals(BigInteger.valueOf(987895217), avdeling.getJuridiskEierOrgNr());
				assertEquals("Valdresveien 6", avdeling.getJuridiskEierAdr1());
				assertEquals("2815", avdeling.getJuridiskEierPostnr());
				assertEquals("GJØVIK", avdeling.getJuridiskEierPoststed());
			}
		},
		RIMI_TRANBERG {
			@Override
			public void validate(SapDepartment avdeling) {
				assertEquals("Prost Blomsgt 28", avdeling.getAdr1());
				assertEquals("08", avdeling.getKjede());
				assertEquals("N12", avdeling.getRegion());
				assertEquals("3076", avdeling.getDatasetConcorde());
				assertEquals("Papanicolas AS", avdeling.getJuridiskEierNavn());
				assertEquals(Integer.valueOf(2819), avdeling.getPostnr());
				assertEquals("GJØVIK", avdeling.getPoststed());
				assertEquals("Demetris Papanicolas", avdeling.getAnsvarlig());
				assertNull(avdeling.getDtSlutt());
				assertEquals("2005-03-07", DATE_FORMAT.format(avdeling.getDtStart()));
				//assertEquals(Integer.valueOf(301613), avdeling.getButiksNr());
				assertEquals("P150", avdeling.getAvtaletype());
				assertEquals("Arild Haugen", avdeling.getDriftslederNavn());
				assertEquals(BigDecimal.valueOf(Double.valueOf("7080001091170")).setScale(0), avdeling.getLokasjonsnr());
				assertEquals(BigInteger.valueOf(987877529), avdeling.getJuridiskEierOrgNr());
				assertEquals("Prost Blomsgt 28", avdeling.getJuridiskEierAdr1());
				assertEquals("2819", avdeling.getJuridiskEierPostnr());
				assertEquals("GJØVIK", avdeling.getJuridiskEierPoststed());
			}
		};
		private static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
		public abstract void validate(SapDepartment avdeling);
	}
	
	private enum Region{
		RIMI_SØR {
			@Override
			public void validate(SapRegion region) {
				assertEquals(Integer.valueOf(3), region.getRegion());
			}
		},
		RIMI_HEDMARKOPPLAND {
			@Override
			public void validate(SapRegion region) {
				assertEquals(Integer.valueOf(4), region.getRegion());
			}
		},
		RIMI_VEST{
			@Override
			public void validate(SapRegion region) {
				assertEquals(Integer.valueOf(5), region.getRegion());
			}
		},
		RIMI_NORDNORGE{
			@Override
			public void validate(SapRegion region) {
				assertEquals(Integer.valueOf(6), region.getRegion());
			}
		};
		
		public abstract void validate(SapRegion region);
	}
	
	private enum Chain{
		ICA_NÆR {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(10), chain.getKjede());
			}
		},
		MEKKA {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(11), chain.getKjede());
			}
		},
		MAURITZ {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(12), chain.getKjede());
			}
		},
		HAKON_MENY {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(13), chain.getKjede());
			}
		},
		LIVI {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(14), chain.getKjede());
			}
		},
		ICA_RETT_HJEM {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(15), chain.getKjede());
			}
		},
		STOP__SHOP {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(16), chain.getKjede());
			}
		},
		TEST {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(17), chain.getKjede());
			}
		},
		SERVICE_MAT {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(18), chain.getKjede());
			}
		},
		ICA_MAXI {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(19), chain.getKjede());
			}
		},
		RIMI {
			@Override
			public void validate(SapChain chain) {
				assertEquals(Integer.valueOf(34), chain.getKjede());
			}
		};
		
		public abstract void validate(SapChain chain);
	}
	
	
}
