package no.ica.fraf.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import no.ica.fraf.FrafException;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public class XmlWriter {
	private XmlObject xmlDocument;
	private String xmlSaveDir;
	private XmlFileNameGenerator xmlFileNameGenerator;
	private String xmlCopyDir;

	public XmlWriter(final XmlObject aXmlDocument, final String aXmlSaveDir,
			final String aXmlCopyDir,
			final XmlFileNameGenerator aXmlFileNameGenerator) {
		xmlDocument = aXmlDocument;
		xmlSaveDir = aXmlSaveDir;
		xmlFileNameGenerator = aXmlFileNameGenerator;
		xmlCopyDir = aXmlCopyDir;
	}

	public String writeAndCopyXmlFile(StringBuffer errorBuffer,
			final boolean printXmlToStdout) throws FrafException {

		return checkAndWriteFile(errorBuffer, printXmlToStdout);
	}

	protected String checkAndWriteFile(final StringBuffer errorBuffer,
			final boolean printXmlToStdout) throws FrafException {
		XmlOptions validateOptions = new XmlOptions();
		List<XmlError> errorList = new ArrayList<XmlError>();
		validateOptions.setErrorListener(errorList);

		if (printXmlToStdout) {
			System.out.println(xmlDocument);
		}
		// LOGGER.info(xmlDocument);
		boolean valid = xmlDocument.validate(validateOptions);

		return !valid ? generateErrorMsg(errorList, errorBuffer)
				: writeAndCopyFile(errorBuffer);
	}

	protected String generateErrorMsg(final List<XmlError> errorList,
			final StringBuffer errorBuffer) {
		for (int j = 0; j < errorList.size(); j++) {
			XmlError error = errorList.get(j);

			System.out.println("\n");
			System.out.println("Message: " + error.getMessage() + "\n");
			System.out.println("Location of invalid XML: "
					+ error.getCursorLocation().xmlText() + "\n");

			errorBuffer.append(" Feil: ").append(error.getMessage()).append(
					"\n");
		}
		return errorBuffer.toString();
	}

	protected String writeAndCopyFile(final StringBuffer errorBuffer)
			throws FrafException {
		try {
			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			xmlOptions.setSavePrettyPrintIndent(4);
			xmlOptions.setUseDefaultNamespace();

			File xmlFile = new File(xmlSaveDir
					+ xmlFileNameGenerator.getXmlFileName());

			xmlDocument.save(xmlFile, xmlOptions);

			// String onakaDir = ApplParamUtil.getStringParam("onaka_path");
			if (xmlCopyDir != null) {
				File copyDirFile = new File(xmlCopyDir);
				if (!copyDirFile.exists()) {
					errorBuffer.append("Katalog ").append(xmlCopyDir).append(
							" eksisterer ikke!");
				}
				FileUtils.copyFileToDirectory(xmlFile, copyDirFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrafException(e);
		}

		return errorBuffer.toString();
	}
}
