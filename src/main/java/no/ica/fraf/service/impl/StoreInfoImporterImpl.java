package no.ica.fraf.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.SapChain;
import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.model.SapDistrictManager;
import no.ica.fraf.model.SapRegion;
import no.ica.fraf.service.SapChainManager;
import no.ica.fraf.service.SapDepartmentManager;
import no.ica.fraf.service.SapDistrictManagerManager;
import no.ica.fraf.service.SapRegionManager;
import no.ica.fraf.service.SapXmlDataImporter;
import noNamespace.STOREDATADocument;
import noNamespace.CHAINDocument.CHAIN;
import noNamespace.CHAINSDocument.CHAINS;
import noNamespace.REGIONDocument.REGION;
import noNamespace.REGIONSDocument.REGIONS;
import noNamespace.STOREDATADocument.STOREDATA;
import noNamespace.STOREDocument.STORE;
import noNamespace.STORESDocument.STORES;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class StoreInfoImporterImpl extends
		AbstractSapXmlDataImporter<STOREDATADocument> implements
		SapXmlDataImporter {
	// private String baseDir;
	// private String dataFileName;
	// private File storesXmlFile;
	private SapDepartmentManager sapDepartmentManager;
	private SapRegionManager sapRegionManager;
	private SapChainManager sapChainManager;
	private Set<String> districtManagerSet = new HashSet<String>();
	private SapDistrictManagerManager sapDistrictManagerManager;
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyyMMdd");
	private static final String NODATE_STRING = "00000000";

	@Inject
	public StoreInfoImporterImpl(@Named("base_data") final String baseDataDir,
			@Named("base_stores_file_name") final String filename,
			final SapDepartmentManager aSapDepartmentManager,
			final SapRegionManager aSapRegionManager,
			final SapChainManager aSapChainManager,
			final SapDistrictManagerManager aSapDistrictManagerManager,@Named("imported_dir") final String importedDir) {
		super(baseDataDir, filename,importedDir);
		// baseDir = baseDataDir;
		// dataFileName = filename;
		sapDepartmentManager = aSapDepartmentManager;
		sapRegionManager = aSapRegionManager;
		sapChainManager = aSapChainManager;
		sapDistrictManagerManager = aSapDistrictManagerManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.service.StoreInfoImporter#importStoreInfo()
	 */
	/*
	 * public void importStoreInfo() throws FrafException {
	 * 
	 * openXmlFile(); parseXmlFile(); moveDataFile(); }
	 */

	/*
	 * private void parseXmlFile() throws FrafException { try { //
	 * xmlDocument.documentProperties().setEncoding("ISO_8859-1"); XmlOptions
	 * xmlOptions = new XmlOptions(); xmlOptions =
	 * xmlOptions.setCharacterEncoding("ISO_8859-1"); STOREDATADocument
	 * storedtaDoc = STOREDATADocument.Factory.parse( storesXmlFile,
	 * xmlOptions); STOREDATA storedata = storedtaDoc.getSTOREDATA();
	 * importDepartments(storedata); importRegions(storedata);
	 * importChains(storedata); importDistrictManagers(); } catch (Exception e)
	 * { e.printStackTrace(); throw new FrafException(e); }
	 * 
	 * }
	 */

	private void importDistrictManagers() {
		List<SapDistrictManager> managers = new ArrayList<SapDistrictManager>();
		for (String name : districtManagerSet) {
			if (name != null) {
				SapDistrictManager manager = new SapDistrictManager();
				manager.setDistrictManagerName(name);
				managers.add(manager);
			}
		}
		sapDistrictManagerManager.saveBatch(managers);

	}

	private void importChains(STOREDATA storedata) {
		CHAINS chains = storedata.getCHAINS();
		CHAIN[] chainArray = chains.getCHAINArray();
		boolean success = chainArray != null ? importChainArray(chainArray)
				: false;

	}

	private boolean importChainArray(CHAIN[] chainArray) {
		for (CHAIN chain : chainArray) {
			SapChain sapChain = getChain(chain);
			saveRegion(sapChain);
		}
		return true;
	}

	private void saveRegion(SapChain sapChain) {
		sapChainManager.saveSapChain(sapChain);

	}

	private SapChain getChain(CHAIN chain) {
		SapChain sapChain = new SapChain();

		sapChain.setKjede(chain.getID());
		sapChain.setChainName(chain.getDESCRIPTION());
		return sapChain;
	}

	private void importRegions(STOREDATA storedata) {
		REGIONS regions = storedata.getREGIONS();
		REGION[] regionArray = regions.getREGIONArray();
		boolean success = regionArray != null ? importRegionArray(regionArray)
				: false;

	}

	private boolean importRegionArray(REGION[] regionArray) {
		for (REGION region : regionArray) {
			SapRegion sapRegion = getRegion(region);
			saveRegion(sapRegion);
		}
		return true;
	}

	private void saveRegion(SapRegion region) {
		sapRegionManager.saveRegion(region);

	}

	private SapRegion getRegion(REGION region) {
		SapRegion sapRegion = new SapRegion();

		sapRegion.setRegion(region.getID());
		sapRegion.setRegionName(region.getDESCRIPTION());
		return sapRegion;
	}

	private void importDepartments(STOREDATA storedata) throws FrafException {
		STORES stores = storedata.getSTORES();
		STORE[] storeArray = stores.getSTOREArray();
		boolean success = storeArray != null ? importStoreArray(storeArray)
				: false;
	}

	private boolean importStoreArray(final STORE[] storeArray)
			throws FrafException {
		for (STORE store : storeArray) {
			SapDepartment department = getDepartment(store);
			saveDepartment(department);
		}
		return true;
	}

	private void saveDepartment(SapDepartment department) {
		sapDepartmentManager.save(department);

	}

	private SapDepartment getDepartment(STORE store) throws FrafException {
		try {
			SapDepartment department = new SapDepartment();

			department.setAvdnr(Integer.valueOf(store.getWERKS()));
			department.setAvdelingNavn(store.getNAME1());
			department.setAdr1(store.getSTRAS());
			department.setPostnr(Integer.valueOf(store.getPSTLZ()));
			department.setPoststed(store.getORT01());
			department.setDatasetConcorde(store.getBUKRS());
			
			department
					.setLokasjonsnr(store.getLOCCO()!=null&&store.getLOCCO().length() != 0 ? BigDecimal
							.valueOf(Long.valueOf(StringUtils.trim(store.getLOCCO()))) : null);
			department
					.setDtSlutt(store.getSCHLD() != null
							&& store.getSCHLD().length() != 0
							&& !store.getSCHLD()
									.equalsIgnoreCase(NODATE_STRING) ? dateFormatter
							.parse(store.getSCHLD())
							: null);
			department.setDtStart(store.getEROED() != null
					&& store.getEROED().length() != 0 ? dateFormatter
					.parse(store.getEROED()) : null);
			department.setAnsvarlig(concatinateFirstAndLastName(store
					.getSMNAME1(), store.getSMNAMEV()));
			department.setKjede(store.getVTWEG());
			department.setRegion(store.getREGIO());
			department.setJuridiskEierNavn(concatinateFirstAndLastName(store
					.getLONAME1(), store.getLONAMEV()));
			department.setAvtaletype(store.getBETRP());
			department.setDriftslederNavn(concatinateFirstAndLastName(store
					.getDMNAME1(), store.getDMNAMEV()));
			districtManagerSet.add(concatinateFirstAndLastName(store
					.getDMNAME1(), store.getDMNAMEV()));
			department.setJuridiskEierOrgNr(store.getSTCEG());
			department.setJuridiskEierAdr1(store.getLOSTREET());
			department.setJuridiskEierPostnr(store.getLOPOSTCODE1());
			department.setJuridiskEierPoststed(store.getLOCITY1());
			return department;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}

	private String concatinateFirstAndLastName(String firstName, String lastName) {
		return (!StringUtils.isEmpty(firstName) ? firstName:"") + " " + (!StringUtils.isEmpty(lastName) ? lastName:"");
	}

	private String getXmlText(XmlObject xmlObject) {
		return xmlObject.toString();
	}

	/*
	 * private void openXmlFile() throws FrafException { storesXmlFile = new
	 * File(baseDir + "/" + dataFileName);
	 * 
	 * if (!storesXmlFile.exists()) { throw new
	 * FrafException("Filen for avdeligner " + dataFileName + " i katalog " +
	 * baseDir + " finnes ikke"); }
	 * 
	 * }
	 */

	/*
	 * private void moveDataFile() throws FrafException { try {
	 * 
	 * File importedDir = new File(baseDir + "/imported"); if
	 * (!importedDir.exists()) { FileUtils.forceMkdir(importedDir); }
	 * FileUtils.copyFileToDirectory(storesXmlFile, importedDir);
	 * 
	 * FileUtils.forceDelete(storesXmlFile); } catch (IOException e) {
	 * e.printStackTrace(); // throw new FrafException(e); }
	 * 
	 * }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.service.StoreInfoImporter#checkForStoreInfoFile()
	 */
	/*
	 * public boolean checkForStoreInfoFile() { File importDir = new
	 * File(baseDir + "/" + dataFileName); return importDir.exists(); }
	 */

	@Override
	protected STOREDATADocument getXmlData(File xmlFile, XmlOptions xmlOptions)
			throws FrafException {
		try {
			return STOREDATADocument.Factory.parse(xmlFile, xmlOptions);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}

	@Override
	protected void importData(STOREDATADocument xmlObject) throws FrafException {
		STOREDATA storedata = xmlObject.getSTOREDATA();
		importDepartments(storedata);
		importRegions(storedata);
		importChains(storedata);
		importDistrictManagers();

	}
	
	

}
