package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.MangelTypeDAO;
import no.ica.fraf.model.MangelType;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * IMplementering av DAO for mangeltype for Hibernate
 * 
 * @author abr99
 * 
 */
public class MangelTypeDAOHibernate extends BaseDAOHibernate<MangelType> implements
        MangelTypeDAO {
	public MangelTypeDAOHibernate() {
		super(MangelType.class);
	}

    /**
     * @see no.ica.fraf.dao.MangelTypeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<MangelType> findAll() {
        return getHibernateTemplate().find("from MangelType");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<MangelType> findAll(final Object param) {
        return findAll();
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public void deleteObjects(final List objects) {
        if (objects == null) {
            return;
        }
        List<MangelType> list = objects;

        for (MangelType mangelType : list) {
            removeMangelType(mangelType.getMangelId());
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public void persistObjects(List objects) {
        if (objects == null) {
            return;
        }
        List<MangelType> list = objects;

        for (MangelType mangelType : list) {
            saveMangelType(mangelType);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeMangelType(((MangelType) object).getMangelId());

    }

    /**
     * @see no.ica.fraf.dao.MangelTypeDAO#removeMangelType(java.lang.Integer)
     */
    public void removeMangelType(final Integer mangelId) {
        getHibernateTemplate().delete(getMangelType(mangelId));
    }

    /**
     * @see no.ica.fraf.dao.MangelTypeDAO#getMangelType(java.lang.Integer)
     */
    public MangelType getMangelType(final Integer mangelId) {
        final MangelType mangelType = (MangelType) getHibernateTemplate().get(
                MangelType.class, mangelId);

        if (mangelType == null) {
            throw new ObjectRetrievalFailureException(MangelType.class,
                    mangelId);
        }

        return mangelType;
    }

    /**
     * @see no.ica.fraf.dao.MangelTypeDAO#saveMangelType(no.ica.fraf.model.MangelType)
     */
    public void saveMangelType(final MangelType mangelType) {
        getHibernateTemplate().saveOrUpdate(mangelType);
    }
}
