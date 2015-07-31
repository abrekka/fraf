package no.ica.elfa.dao;

import java.util.List;

import no.ica.elfa.model.CreditImport;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO mot tabell CREDIT_IMPORT
 * 
 * @author abr99
 * 
 */
public interface CreditImportDAO extends DAO<CreditImport> {
	/**
	 * Finner alle somm ikke har blitt importert
	 * 
	 * @return alle som ikke har blitt importert
	 */
	List<CreditImport> findAllNotImported();
}
