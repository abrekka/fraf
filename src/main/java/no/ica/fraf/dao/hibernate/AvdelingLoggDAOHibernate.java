package no.ica.fraf.dao.hibernate;

import no.ica.fraf.dao.AvdelingLoggDAO;
import no.ica.fraf.model.AvdelingLogg;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av DAO for avdeling_logg for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingLoggDAOHibernate extends BaseDAOHibernate<AvdelingLogg> implements
		AvdelingLoggDAO {
	public AvdelingLoggDAOHibernate() {
		super(AvdelingLogg.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingLoggDAO#getAvdelingLogg(java.lang.Integer)
	 */
	public AvdelingLogg getAvdelingLogg(final Integer avdelingLoggId) {
		final AvdelingLogg avdelingLogg = (AvdelingLogg) getHibernateTemplate()
				.get(AvdelingLogg.class, avdelingLoggId);

		if (avdelingLogg == null) {
			throw new ObjectRetrievalFailureException(AvdelingLogg.class,
					avdelingLoggId);
		}

		return avdelingLogg;
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingLoggDAO#saveAvdeling(no.ica.fraf.model.AvdelingLogg)
	 */
	public void saveAvdeling(final AvdelingLogg avdelingLogg) {
		try {
			getHibernateTemplate().saveOrUpdate(avdelingLogg);
		} catch (DataAccessException e) {
			getHibernateTemplate().flush();
			throw e;

		}

	}

}
