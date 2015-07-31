package no.ica.fraf.dao.pkg;

import java.sql.Connection;

import no.ica.fraf.FrafException;

/**
 * Klasse for databasepakke IMPORT_BETINGELSE
 * 
 * @author abr99
 * 
 */
public interface ImportBetingelsePkgDAO {
	/**
	 * Importerer betingelser
	 * 
	 * @param userId
	 */
	public void importBetingelser(Integer userId);

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
	 * Henter ut databaseforbindelse
	 * 
	 * @return databaseforbindelse
	 * @throws FrafException
	 */
	public Connection getDbConnection() throws FrafException;
}
