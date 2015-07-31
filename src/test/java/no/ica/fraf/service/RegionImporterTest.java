package no.ica.fraf.service;

import java.io.File;
import java.util.List;

import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.SapChain;
import no.ica.fraf.model.SapRegion;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.service.impl.RegionImporter;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class RegionImporterTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}
	
	public void testImportAll() throws Exception{
		File importFile = new File("base_data/regions.xls");
		assertEquals(true, importFile.exists());
		FrafMain.setStandalone(true);
		final ApplParamDAO paramDAO = mock(ApplParamDAO.class);
		ApplParamUtil.setApplParamDAO(paramDAO);
		final ApplParam paramBaseDataDir = new ApplParam();
		paramBaseDataDir.setParamName("base_data_dir");
		paramBaseDataDir.setParamValue("base_data");
		
		final ApplParam paramBaseFileName = new ApplParam();
		paramBaseFileName.setParamName("base_region_file_name");
		paramBaseFileName.setParamValue("regions.xls");
		checking(new Expectations(){{
			allowing(paramDAO).findByParamName("base_data_dir", 1);will(returnValue(paramBaseDataDir));
			allowing(paramDAO).findByParamName("base_region_file_name", 1);will(returnValue(paramBaseFileName));
		}});
		SapRegionManager sapRegionManager=(SapRegionManager)ModelUtil.getBean(SapRegionManager.MANAGER_NAME);
		
		RegionImporter importer = new RegionImporter(sapRegionManager);
		importer.importData();
		
		List<SapRegion> regions = sapRegionManager.findAll(3,6);
		assertNotNull(regions);
		assertEquals(4, regions.size());
		
		for(SapRegion region:regions){
			Region.valueOf(StringUtils.upperCase(region.getRegionName().replace(" ", "_").replace("/", "").replace("-", ""))).validate(region);
		}
		
		importFile = new File("base_data/regions.xls");
		assertEquals(false, importFile.exists());
	}

	private enum Region{
		RIMI_SØR {
			@Override
			public void validate(SapRegion region) {
				assertEquals("11", region.getSelskap());
				assertEquals(Integer.valueOf(3), region.getRegion());
			}
		},
		RIMI_HEDMARKOPPLAND {
			@Override
			public void validate(SapRegion region) {
				assertEquals("11", region.getSelskap());
				assertEquals(Integer.valueOf(4), region.getRegion());
			}
		},
		RIMI_VEST{
			@Override
			public void validate(SapRegion region) {
				assertEquals("11", region.getSelskap());
				assertEquals(Integer.valueOf(5), region.getRegion());
			}
		},
		RIMI_NORDNORGE{
			@Override
			public void validate(SapRegion region) {
				assertEquals("11", region.getSelskap());
				assertEquals(Integer.valueOf(6), region.getRegion());
			}
		};
		
		public abstract void validate(SapRegion region);
	}
}
