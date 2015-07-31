package no.ica.fraf.dao.pkg;

import java.util.Date;

import no.ica.fraf.FrafException;

/**
 * Klasse for databasepakken REGNSKAP_PKG
 * 
 * @author abr99
 * 
 */
public interface RegnskapPkgDAO {
	/**
	 * Bokfører bunt
	 * 
	 * @param buntId
	 * @param bokforDato
	 * @throws FrafException
	 */
	public void bokforBunt(Integer buntId, Date bokforDato)
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

}
