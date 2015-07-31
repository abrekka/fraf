package no.ica.fraf.service;

import java.io.File;
import java.util.List;

import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.SapChain;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.service.impl.ChainImporter;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class ChainImporterTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}
	
	public void testImportAll() throws Exception{
		File importFile = new File("base_data/chains.xls");
		assertEquals(true, importFile.exists());
		FrafMain.setStandalone(true);
		final ApplParamDAO paramDAO = mock(ApplParamDAO.class);
		ApplParamUtil.setApplParamDAO(paramDAO);
		final ApplParam paramBaseDataDir = new ApplParam();
		paramBaseDataDir.setParamName("base_data_dir");
		paramBaseDataDir.setParamValue("base_data");
		
		final ApplParam paramBaseFileName = new ApplParam();
		paramBaseFileName.setParamName("base_chain_file_name");
		paramBaseFileName.setParamValue("chains.xls");
		checking(new Expectations(){{
			allowing(paramDAO).findByParamName("base_data_dir", 1);will(returnValue(paramBaseDataDir));
			allowing(paramDAO).findByParamName("base_chain_file_name", 1);will(returnValue(paramBaseFileName));
		}});
		SapChainManager sapChainManager=(SapChainManager)ModelUtil.getBean(SapChainManager.MANAGER_NAME);
		
		ChainImporter importer = new ChainImporter(sapChainManager);
		importer.importData();
		
		List<SapChain> chains = sapChainManager.findAll(10,19);
		assertNotNull(chains);
		assertEquals(10, chains.size());
		
		for(SapChain chain:chains){
			Chain.valueOf(StringUtils.upperCase(chain.getChainName().replace(" ", "_").replace("&", ""))).validate(chain);
		}
		
		importFile = new File("base_data/chains.xls");
		assertEquals(false, importFile.exists());
	}

	private enum Chain{
		ICA_NÆR {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(10), chain.getKjede());
			}
		},
		MEKKA {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(11), chain.getKjede());
			}
		},
		MAURITZ {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(12), chain.getKjede());
			}
		},
		HAKON_MENY {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(13), chain.getKjede());
			}
		},
		LIVI {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(14), chain.getKjede());
			}
		},
		ICA_RETT_HJEM {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(15), chain.getKjede());
			}
		},
		STOP__SHOP {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(16), chain.getKjede());
			}
		},
		TEST {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(17), chain.getKjede());
			}
		},
		SERVICE_MAT {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(18), chain.getKjede());
			}
		},
		ICA_MAXI {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(19), chain.getKjede());
			}
		},
		RIMI {
			@Override
			public void validate(SapChain chain) {
				assertEquals("11", chain.getSelskap());
				assertEquals(Integer.valueOf(34), chain.getKjede());
			}
		};
		
		public abstract void validate(SapChain chain);
	}
}
