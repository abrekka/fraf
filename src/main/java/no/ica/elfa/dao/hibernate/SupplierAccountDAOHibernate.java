package no.ica.elfa.dao.hibernate;

import no.ica.elfa.dao.SupplierAccountDAO;
import no.ica.elfa.model.SupplierAccount;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

/**
 * Implementasjon av DAO mpt tabell SUPPLIER_ACCOUNT
 * 
 * @author abr99
 * 
 */
public class SupplierAccountDAOHibernate extends
		BaseDAOHibernate<SupplierAccount> implements SupplierAccountDAO {
	/**
	 * Konstruktør
	 */
	public SupplierAccountDAOHibernate() {
		super(SupplierAccount.class);
	}

}
