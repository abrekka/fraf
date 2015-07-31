package no.ica.elfa.dao.pkg;

import no.ica.fraf.FrafException;

/**
 * Klasse som representerer pakke E2B_PKG
 * 
 * @author abr99
 * 
 */
public interface E2bPkgDAO {
	/**
	 * Henter neste sekvensnummer for xml-fil
	 * 
	 * @return neste sekvesnnummer
	 * @throws FrafException
	 */
	Integer getSequenceNumber() throws FrafException;

	/**
	 * Setter om database er test
	 * 
	 * @param test
	 */
	void setTest(boolean test);

	/**
	 * Henter om database er test
	 * 
	 * @return true dersom test
	 */
	boolean getTest();
}
