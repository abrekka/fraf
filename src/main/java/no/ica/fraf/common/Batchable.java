package no.ica.fraf.common;

import java.util.Date;
import java.util.Set;

import no.ica.fraf.xml.InvoiceInterface;

/**
 * Interface for klasse som representererer bunttabell
 * 
 * @author abr99
 * 
 */
public interface Batchable {
	/**
	 * @return buntstatus
	 */
	BatchStatusInterface getBatchStatus();

	/**
	 * @return fradato
	 */
	Date getFromDate();

	/**
	 * @return tildato
	 */
	Date getToDate();

	/**
	 * @return buntid
	 */
	Integer getBatchId();

	/**
	 * @return info
	 */
	String getInfoString();

	/**
	 * @return bruker
	 */
	ApplUserInterface getApplUserInterface();

	/**
	 * @return opprettet dato
	 */
	Date getCreatedDate();

	/**
	 * Setter buntstatus
	 * 
	 * @param batchStatusInterface
	 */
	void setBatchStatus(BatchStatusInterface batchStatusInterface);

	/**
	 * @return katalognavn
	 */
	String getDirectoryName();

	/**
	 * @return fakturaer
	 */
	Set<InvoiceInterface> getInvoiceInterfaces(SystemEnum systemEnum);

	/**
	 * Setter filnavn
	 * 
	 * @param fileName
	 */
	void setFileName(String fileName);

	/**
	 * Henter filnavn
	 * 
	 * @return filnavn
	 */
	String getFileName();
	StringBuffer getXmlFreeTextBuffer();
	StringBuffer getXmlComment();
}
