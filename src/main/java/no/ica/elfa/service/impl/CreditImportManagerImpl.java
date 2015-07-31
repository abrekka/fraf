package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.CreditImportDAO;
import no.ica.elfa.model.CreditImport;
import no.ica.elfa.service.CreditImportManager;

/**
 * Implementasjon av manager mot tabell CREDIT_IMPORT
 * 
 * @author abr99
 * 
 */
public class CreditImportManagerImpl implements CreditImportManager {
	/**
	 * 
	 */
	private CreditImportDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setCreditImportDAO(CreditImportDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.CreditImportManager#findAllNotImported()
	 */
	public List<CreditImport> findAllNotImported() {
		return dao.findAllNotImported();
	}

	/**
	 * @see no.ica.elfa.service.CreditImportManager#fileIsImported(java.lang.String)
	 */
	public Boolean fileIsImported(String fileName) {
		CreditImport example = new CreditImport();
		example.setFileName(fileName);
		List<CreditImport> list = dao.findByExampleLike(example);
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}

}
