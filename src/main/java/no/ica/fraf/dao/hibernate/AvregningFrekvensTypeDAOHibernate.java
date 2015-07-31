package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.model.AvregningFrekvensType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvregningFrekvensTypeDAO
 * 
 * @author abr99
 * 
 */
public class AvregningFrekvensTypeDAOHibernate extends BaseDAOHibernate<AvregningFrekvensType>
        implements AvregningFrekvensTypeDAO {
	public AvregningFrekvensTypeDAOHibernate() {
		super(AvregningFrekvensType.class);
	}

    /**
     * @see no.ica.fraf.dao.AvregningFrekvensTypeDAO#getAvregningFrekvensType(java.lang.Integer)
     */
    public AvregningFrekvensType getAvregningFrekvensType(final Integer typeId) {
        final AvregningFrekvensType frekvensType = (AvregningFrekvensType) getHibernateTemplate()
                .get(AvregningFrekvensType.class, typeId);

        if (frekvensType == null) {
            throw new ObjectRetrievalFailureException(
                    AvregningFrekvensType.class, typeId);
        }

        return frekvensType;
    }

    /**
     * @see no.ica.fraf.dao.AvregningFrekvensTypeDAO#saveAvregningFrekvensType(no.ica.fraf.model.AvregningFrekvensType)
     */
    public void saveAvregningFrekvensType(
            final AvregningFrekvensType frekvensType) {
        getHibernateTemplate().saveOrUpdate(frekvensType);
    }

    /**
     * @see no.ica.fraf.dao.AvregningFrekvensTypeDAO#removeAvregningFrekvensType(java.lang.Integer)
     */
    public void removeAvregningFrekvensType(final Integer typeId) {
        getHibernateTemplate().delete(getAvregningFrekvensType(typeId));
    }

    /**
     * @see no.ica.fraf.dao.AvregningFrekvensTypeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
	public List<AvregningFrekvensType> findAll() {
        return getHibernateTemplate().find("from AvregningFrekvensType");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<AvregningFrekvensType> findAll(final Object param) {
        return findAll();
    }

    /**
     * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
     */
    public List getComboValues(final Object object) {
        return findAll();
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
     */
    public void deleteObjects(final List objects) {
        if (objects == null) {
            return;
        }

        final Iterator objectIt = objects.iterator();
        AvregningFrekvensType frekvensType;

        while (objectIt.hasNext()) {
            frekvensType = (AvregningFrekvensType) objectIt.next();
            removeAvregningFrekvensType(frekvensType
                    .getAvregningFrekvensTypeId());
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
     */
    public void persistObjects(final List objects) {
        if (objects == null) {
            return;
        }

        final Iterator objectIt = objects.iterator();
        AvregningFrekvensType frekvensType;

        while (objectIt.hasNext()) {
            frekvensType = (AvregningFrekvensType) objectIt.next();
            saveAvregningFrekvensType(frekvensType);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeAvregningFrekvensType(((AvregningFrekvensType) object)
                .getAvregningFrekvensTypeId());

    }

    /**
     * @see no.ica.fraf.dao.AvregningFrekvensTypeDAO#findByKode(java.lang.String)
     */
    public AvregningFrekvensType findByKode(final String frekvensKode) {
        return (AvregningFrekvensType) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        final List list = session.createCriteria(
                                AvregningFrekvensType.class).add(
                                Restrictions.ilike("avregningFrekvensTypeKode",
                                        frekvensKode)).list();

                        if (list == null || list.size() != 1) {
                            return null;
                        }
                        return list.get(0);
                    }

                });
    }

    /**
     * @see no.ica.fraf.dao.AvregningFrekvensTypeDAO#findByTerminer(java.lang.Integer)
     */
    public AvregningFrekvensType findByTerminer(final Integer terminer) {
        return (AvregningFrekvensType) getHibernateTemplate().execute(
                new HibernateCallback() {

                    @SuppressWarnings("unchecked")
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        AvregningFrekvensType frekvensType = null;

                        if (terminer != null && terminer.intValue() != 0) {
                            final Integer months = Integer
                                    .valueOf(12 / terminer.intValue());
                            final List<AvregningFrekvensType> list = session
                                    .createCriteria(AvregningFrekvensType.class)
                                    .add(Restrictions.like("antallMnd", months))
                                    .list();

                            if (list != null && list.size() != 0) {
                                frekvensType = list.get(0);
                            }
                        }
                        return frekvensType;
                    }
                });
    }
}
