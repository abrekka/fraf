package no.ica.elfa.dao.hibernate;

import no.ica.elfa.dao.EepLineItemDAO;
import no.ica.elfa.model.EepLineItem;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

/**
 * Implementasjon av interface for DAO mot tabell EEP_LINE_ITEM
 * 
 * @author abr99
 * 
 */
public class EepLineItemDAOHibernate extends BaseDAOHibernate<EepLineItem>
		implements EepLineItemDAO {
	/**
	 * Konstruktør
	 */
	public EepLineItemDAOHibernate() {
		super(EepLineItem.class);
	}

}
