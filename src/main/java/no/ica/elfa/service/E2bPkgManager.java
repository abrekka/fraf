package no.ica.elfa.service;

import no.ica.fraf.FrafException;

/**
 * Interface for manager som går mot prosedyre E2B_PKG
 * 
 * @author abr99
 * 
 */
public interface E2bPkgManager {
	/**
	 * Henter sekvensnummer fra sekvens i database
	 * 
	 * @return sekvensnummer
	 * @throws FrafException
	 */
	//Integer getSequenceNumber() throws FrafException;

	/**
	 * Henter filnavn
	 * 
	 * @param seqString
	 * @return filnavn
	 * @throws FrafException
	 */
	String getFilename() throws FrafException;
}
