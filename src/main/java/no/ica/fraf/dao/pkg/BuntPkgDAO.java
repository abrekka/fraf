package no.ica.fraf.dao.pkg;

import no.ica.fraf.FrafException;

/**
 * Interface for DAO mot pakke BUNT_PKG
 * 
 * @author abr99
 * 
 */
public interface BuntPkgDAO {
	
	String DAO_NAME = "buntPkgDAO";

	/**
	 * Sletter bunt
	 * 
	 * @param buntId
	 * @throws FrafException
	 */
	public void slettBunt(Integer buntId) throws FrafException;

	/**
	 * Sletter bunt og dersom ignoreError er 1 vil det ikke sjekkes om bunt er
	 * bokført eller om det er siste bunt
	 * 
	 * @param buntId
	 * @param ignoreError
	 * @throws FrafException
	 */
	public void slettBunt(Integer buntId, Integer ignoreError)
			throws FrafException;

	/**
	 * Setter om database er test
	 * 
	 * @param test
	 */
	public void setTest(boolean test);

	/**
	 * Henter om database er test
	 * 
	 * @return true dersom test
	 */
	public boolean getTest();

	/**
	 * Ruller tilbake bunt
	 * 
	 * @param buntId
	 * @throws FrafException
	 */
	public void rullTilbakeBunt(Integer buntId) throws FrafException;
	
}
