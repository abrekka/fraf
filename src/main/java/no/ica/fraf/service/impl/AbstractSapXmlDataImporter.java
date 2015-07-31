package no.ica.fraf.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import no.ica.fraf.FrafException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public abstract class AbstractSapXmlDataImporter<T extends XmlObject>  {
	private File xmlFile;
	protected String baseDir;
	private String dataFileNamePattern;
	private String importedDir;
	
	
	public AbstractSapXmlDataImporter(final String baseDataDir,
			final String filenamePattern,final String aImportedDir
			) {
		baseDir = baseDataDir;
		dataFileNamePattern = filenamePattern;
		importedDir=aImportedDir;
		
	}
	abstract protected T getXmlData(File aXmlFile,XmlOptions xmlOptions)throws FrafException;
	abstract protected void importData(T xmlObject)throws FrafException;
	public void importXmlData() throws FrafException{
		File importDir = new File(baseDir);
		String[] files=importDir.list(new SapFileFilter());
		if(files.length==0){
			throw new FrafException("Det finnes ingen filer å importere");
		}
		if(files!=null&&files.length!=0){
			for(String filename:files){
				openXmlFile(filename);
				getAndParseXmlFile();
				moveDataFile();
			}
		
		}
	}
	
	
	
	private void getAndParseXmlFile() throws FrafException {
			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions = xmlOptions.setCharacterEncoding("ISO_8859-1");
			HashMap<String, String> suggestedPrefixes=new HashMap<String, String>();
			suggestedPrefixes.put("http://ica.no/FRAF/EXS", "");
			suggestedPrefixes.put("http://ica.no/FRAF/", "");
			suggestedPrefixes.put("http://ica.no/FRAF", "");
			xmlOptions.setLoadSubstituteNamespaces(suggestedPrefixes);//SaveSuggestedPrefixes(suggestedPrefixes);
			T xmlData = getXmlData(xmlFile,xmlOptions);
			importData(xmlData);
			
			/*STOREDATADocument storedtaDoc = STOREDATADocument.Factory.parse(
					storesXmlFile, xmlOptions);
			STOREDATA storedata = storedtaDoc.getSTOREDATA();
			importDepartments(storedata);
			importRegions(storedata);
			importChains(storedata);
			importDistrictManagers();*/
		
	}
	private void openXmlFile(String dataFileName) throws FrafException {
		xmlFile = new File(baseDir + "/" + dataFileName);

		if (!xmlFile.exists()) {
			throw new FrafException("Sapfil " + dataFileName
					+ " i katalog " + baseDir + " finnes ikke");
		}

	}
	private void moveDataFile() throws FrafException {
		try {

			File importedDirectory = new File(importedDir);
			if (!importedDirectory.exists()) {
				FileUtils.forceMkdir(importedDirectory);
			}
			FileUtils.copyFileToDirectory(xmlFile, importedDirectory);

			FileUtils.forceDelete(xmlFile);
		} catch (IOException e) {
			e.printStackTrace();
			// throw new FrafException(e);
		}

	}
	
	public boolean checkFiles() throws FrafException {
		//File importDir = new File(baseDir + "/" + dataFileName);
		File importDir = new File(baseDir);
		if(!importDir.exists()){
			throw new FrafException(baseDir+" eksisterer ikke!");
		}
		String[] files=importDir.list(new SapFileFilter());
		//String[] files=importDir.list();
		/*File testFile =new File("h://Fraf65.10416");
		if(testFile.exists()){
			File destDir=new File("//noaoslsr0090/fraf_ver/out/");
			if(destDir.exists()){
			try {
				FileUtils.copyFileToDirectory(testFile, destDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}*/
		return files!=null&&files.length!=0;
	}
	
	public class SapFileFilter extends AbstractFileFilter{
		
		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			return name.indexOf(dataFileNamePattern)!=-1;
		}
		
	}
}
