package no.ica.fraf.logging;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.service.impl.BaseManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 * Klasse som brukes til å logge til database
 * @author abr99
 *
 */
public class AppenderUtil extends BaseDAOHibernate implements HibernateAppenderSessionService {

	
	static {
		BaseManager.getApplicationContext();
    }
	public AppenderUtil() {
		super(null);
	}
	
	/**
	 * @see no.ica.fraf.logging.HibernateAppenderSessionService#openSession()
	 */
	public Session openSession() throws HibernateException {
		return getSession();
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderSessionService#closeSession()
	 */
	public void closeSession() throws HibernateException {
		getSession().close();
		
	}

}
