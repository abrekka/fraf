package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.ImportBetingelse;

/**
 * DAO for IMPORT_BETINGELSE
 * 
 * @author abr99
 * 
 */
public interface ImportBetingelseDAO extends DAO<ImportBetingelse> {
	String DAO_NAME = "importBetingelseDAO";

	/**
	 * Finner importbetingelse
	 * 
	 * @param betingelseId
	 * @return importbetingelse
	 */
	ImportBetingelse getImportBetingelse(Integer betingelseId);

	/**
	 * Lagerer importbetingelse
	 * 
	 * @param importBetingelse
	 */
	void saveImportBetingelse(ImportBetingelse importBetingelse);

	/**
	 * Sletter importbetingelse
	 * 
	 * @param betingelseId
	 */
	void removeImportBetingelse(Integer betingelseId);

	/**
	 * Finner alle importbetingelser
	 * 
	 * @return alle importbetingelser
	 */
	List<Object> findAll();

	/**
	 * Sletter alle importbetingelser
	 */
	void deleteAll();

	/**
	 * Sletter alle importbetingelser i liste
	 * 
	 * @param betingelser
	 */
	void deleteList(List betingelser);
}
