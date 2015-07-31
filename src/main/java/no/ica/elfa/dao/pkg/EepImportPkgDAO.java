package no.ica.elfa.dao.pkg;

import no.ica.fraf.model.ApplUser;

/**
 * Interface for DAO for pakke EEP_IMPORT_PKG
 * 
 * @author abr99
 * 
 */
public interface EepImportPkgDAO {
	/**
	 * Kjører prosedyre RUN_IMPORT_FILE i pakke
	 * 
	 * @param user
	 */
	void runImportFile(ApplUser user);
}
