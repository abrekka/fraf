package no.ica.fraf.service;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.SapLegalOwner;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.service.impl.LegalOwnerImporter;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class LegalOwnerImporterTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}
	
	public void testImportAll() throws Exception{
		File importFile = new File("base_data/legal_owners.xls");
		assertEquals(true, importFile.exists());
		FrafMain.setStandalone(true);
		final ApplParamDAO paramDAO = mock(ApplParamDAO.class);
		ApplParamUtil.setApplParamDAO(paramDAO);
		final ApplParam paramBaseDataDir = new ApplParam();
		paramBaseDataDir.setParamName("base_data_dir");
		paramBaseDataDir.setParamValue("base_data");
		
		final ApplParam paramBaseFileName = new ApplParam();
		paramBaseFileName.setParamName("base_legal_owner_file_name");
		paramBaseFileName.setParamValue("legal_owners.xls");
		checking(new Expectations(){{
			allowing(paramDAO).findByParamName("base_data_dir", 1);will(returnValue(paramBaseDataDir));
			allowing(paramDAO).findByParamName("base_legal_owner_file_name", 1);will(returnValue(paramBaseFileName));
		}});
		SapLegalOwnerManager sapLegalOwnerManager=(SapLegalOwnerManager)ModelUtil.getBean(SapLegalOwnerManager.MANAGER_NAME);
		
		LegalOwnerImporter importer = new LegalOwnerImporter(sapLegalOwnerManager);
		importer.importData();
		
		List<SapLegalOwner> legalOwners = sapLegalOwnerManager.findAll(6,9);
		assertNotNull(legalOwners);
		assertEquals(4, legalOwners.size());
		
		for(SapLegalOwner legalOwner:legalOwners){
			LegalOwner.valueOf(StringUtils.upperCase(legalOwner.getLegalOwnerName().replace(" ", "_").replace("/", "").replace("-", "").replace(".", ""))).validate(legalOwner);
		}
		
		importFile = new File("base_data/legal_owners.xls");
		assertEquals(false, importFile.exists());
	}

	private enum LegalOwner{
		AE_HAAVIK_AS {
			@Override
			public void validate(SapLegalOwner legalOwner) {
				assertEquals(Integer.valueOf(6), legalOwner.getNr());
				assertEquals("Halkjellsgt.4", legalOwner.getAdr1());
				assertEquals("6100", legalOwner.getPostnr());
				assertEquals("VOLDA", legalOwner.getPoststed());
				assertEquals(BigInteger.valueOf(947464981), legalOwner.getOrgNr());
			}
		},
		AG_VIGEMYR_AS {
			@Override
			public void validate(SapLegalOwner legalOwner) {
				assertEquals(Integer.valueOf(7), legalOwner.getNr());
				assertEquals("Øyslebø", legalOwner.getAdr1());
				assertEquals("4532", legalOwner.getPostnr());
				assertEquals("ØYSLEBØ", legalOwner.getPoststed());
				assertEquals(BigInteger.valueOf(917170401), legalOwner.getOrgNr());
			}
		},
		ALLEEN_SENTER {
			@Override
			public void validate(SapLegalOwner legalOwner) {
				assertEquals(Integer.valueOf(8), legalOwner.getNr());
				assertEquals("Postboks 1063 Rom", legalOwner.getAdr1());
				assertEquals("4580", legalOwner.getPostnr());
				assertEquals("LYNGDAL", legalOwner.getPoststed());
				assertNull(legalOwner.getOrgNr());
			}
		},
		ALV_AALEN_EFTF_AS {
			@Override
			public void validate(SapLegalOwner legalOwner) {
				assertEquals(Integer.valueOf(9), legalOwner.getNr());
				assertEquals("Postboks 44", legalOwner.getAdr1());
				assertEquals("6708", legalOwner.getPostnr());
				assertEquals("BRYGGJA", legalOwner.getPoststed());
				assertEquals(BigInteger.valueOf(979717857), legalOwner.getOrgNr());
			}
		};
		
		public abstract void validate(SapLegalOwner legalOwner);
	}
}
