package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaV;
import no.ica.fraf.model.FakturaVInterface;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av FakturaVDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FakturaVDAOHibernate extends AbstractFakturaVDAO {
	public FakturaVDAOHibernate() {
		super(FakturaV.class);
	}

	
	

	



	
}
