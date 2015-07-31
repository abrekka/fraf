package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.CreditImport;

/**
 * Interface for manager mot tabell CREDIT_IMPORT
 * 
 * @author abr99
 * 
 */
public interface CreditImportManager {
	/**
	 * Finner alle som ikke er importert
	 * 
	 * @return kredittimport
	 */
	List<CreditImport> findAllNotImported();

	/**
	 * Sjekker om fil er importtert
	 * 
	 * @param fileName
	 * @return true dersom fil er importert
	 */
	Boolean fileIsImported(String fileName);
}
