package no.ica.fraf.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import no.e2B.xmlSchema.InterchangeDocument;
import no.ica.fraf.FrafException;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlOptions;

public class XmlWriterInterchangeDocument extends XmlWriter{
	private InterchangeDocument xmlDocument;
	private String xmlSaveDir;
	private XmlFileNameGenerator xmlFileNameGenerator;
	private static final Logger LOGGER = Logger.getLogger(XmlWriterInterchangeDocument.class);
	
	private String xmlCopyDir;
	public XmlWriterInterchangeDocument(final InterchangeDocument aXmlDocument,final String aXmlSaveDir, final String aXmlCopyDir,final XmlFileNameGenerator aXmlFileNameGenerator){
		super(aXmlDocument,aXmlSaveDir,aXmlCopyDir,aXmlFileNameGenerator);
		xmlDocument=aXmlDocument;
		xmlSaveDir=aXmlSaveDir;
		xmlFileNameGenerator=aXmlFileNameGenerator;
		xmlCopyDir=aXmlCopyDir;
	}
	public String writeAndCopyXmlFile(StringBuffer errorBuffer,final boolean printXmlToStdout)
			throws FrafException {

		return xmlDocument.getInterchange().getInvoiceArray().length == 0 ? "Det finnes ingen fakturaer som skal til XML"
				: checkAndWriteFile(errorBuffer,printXmlToStdout);
	}

	/*private String checkAndWriteFile(
			final StringBuffer errorBuffer)
			throws FrafException {
		XmlOptions validateOptions = new XmlOptions();
		List<XmlError> errorList = new ArrayList<XmlError>();
		validateOptions.setErrorListener(errorList);
		
		System.out.println(xmlDocument);
		//LOGGER.info(xmlDocument);
		boolean valid = xmlDocument.validate(validateOptions);

		return !valid ? generateErrorMsg(errorList, errorBuffer)
				: writeAndCopyFile(errorBuffer);
	}*/

	/*private String generateErrorMsg(final List<XmlError> errorList,
			final StringBuffer errorBuffer) {
		for (int j = 0; j < errorList.size(); j++) {
			XmlError error = errorList.get(j);

			errorBuffer.append(" Feil: ").append(error.getMessage()).append(
					"\n");
		}
		return errorBuffer.toString();
	}*/

	/*private String writeAndCopyFile(final StringBuffer errorBuffer)
			throws FrafException {
		try {
			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			xmlOptions.setSavePrettyPrintIndent(4);
			xmlOptions.setUseDefaultNamespace();

			
			File xmlFile = new File(xmlSaveDir + xmlFileNameGenerator.getXmlFileName());

			xmlDocument.save(xmlFile, xmlOptions);

			// String onakaDir = ApplParamUtil.getStringParam("onaka_path");
			File copyDirFile = new File(xmlCopyDir);
			if (!copyDirFile.exists()) {
				errorBuffer.append("Katalog ").append(xmlCopyDir).append(
						" eksisterer ikke!");
			}
			FileUtils.copyFileToDirectory(xmlFile, copyDirFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrafException(e);
		}

		return errorBuffer.toString();
	}*/

}
