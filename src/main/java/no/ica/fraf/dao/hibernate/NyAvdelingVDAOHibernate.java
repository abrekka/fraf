package no.ica.fraf.dao.hibernate;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.view.NyAvdelingVDAO;
import no.ica.fraf.model.NyAvdelingV;
import no.ica.fraf.model.NyAvdelingVInterface;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av NyAvdelingDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class NyAvdelingVDAOHibernate extends AbstractNyAvdelingVDAOHibernate {
    
	public NyAvdelingVDAOHibernate() {
		super(NyAvdelingV.class);
	}

   
}
