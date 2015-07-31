package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.SupplierAccountDAO;
import no.ica.elfa.model.SupplierAccount;
import no.ica.elfa.service.SupplierAccountManager;

/**
 * Implementasjon av manager mot tabell SUPPLIER_ACCOUNT
 * 
 * @author abr99
 * 
 */
public class SupplierAccountManagerImpl implements SupplierAccountManager {
	/**
	 * 
	 */
	private SupplierAccountDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSupplierAccountDAO(SupplierAccountDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.SupplierAccountManager#findAll()
	 */
	public List<SupplierAccount> findAll() {
		return dao.getObjects();
	}

}
