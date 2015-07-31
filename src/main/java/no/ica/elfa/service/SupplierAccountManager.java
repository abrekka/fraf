package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.SupplierAccount;

/**
 * Interface for manager mot tabell SUPPLIER_ACCOUNT
 * 
 * @author abr99
 * 
 */
public interface SupplierAccountManager {
	/**
	 * Finner alle
	 * 
	 * @return leverand�rer
	 */
	List<SupplierAccount> findAll();
}
