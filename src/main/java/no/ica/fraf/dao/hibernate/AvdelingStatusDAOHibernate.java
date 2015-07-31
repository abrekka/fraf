package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.AvdelingStatusDAO;
import no.ica.fraf.model.AvdelingStatus;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av AvdelingStatusDAO
 * 
 * @author abr99
 * 
 */
public class AvdelingStatusDAOHibernate extends BaseDAOHibernate<AvdelingStatus> implements
        AvdelingStatusDAO {
	public AvdelingStatusDAOHibernate() {
		super(AvdelingStatus.class);
	}

    /**
     * @see no.ica.fraf.dao.AvdelingStatusDAO#getAvdelingStatus(java.lang.Integer)
     */
    public AvdelingStatus getAvdelingStatus(final Integer statusId) {
        final AvdelingStatus avdelingStatus = (AvdelingStatus) getHibernateTemplate()
                .get(AvdelingStatus.class, statusId);

        if (avdelingStatus == null) {
            throw new ObjectRetrievalFailureException(AvdelingStatus.class,
                    statusId);
        }

        return avdelingStatus;
    }

    /**
     * @see no.ica.fraf.dao.AvdelingStatusDAO#saveAvdelingStatus(no.ica.fraf.model.AvdelingStatus)
     */
    public void saveAvdelingStatus(final AvdelingStatus avdelingStatus) {
        getHibernateTemplate().saveOrUpdate(avdelingStatus);
    }

    /**
     * @see no.ica.fraf.dao.AvdelingStatusDAO#removeAvdelingStatus(java.lang.Integer)
     */
    public void removeAvdelingStatus(final Integer statusId) {
        getHibernateTemplate().delete(getAvdelingStatus(statusId));
    }

    /**
     * @see no.ica.fraf.dao.AvdelingStatusDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<AvdelingStatus> findAll() {
        return getHibernateTemplate().find("from AvdelingStatus");
    }

}
