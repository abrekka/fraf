package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.model.Mva;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av MvaDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class MvaDAOHibernate extends BaseDAOHibernate<Mva> implements MvaDAO {
	public MvaDAOHibernate() {
		super(Mva.class);
	}

    /**
     * @see no.ica.fraf.dao.MvaDAO#getMva(java.lang.Integer)
     */
    public Mva getMva(final Integer mvaId) {
        final Mva mva = (Mva) getHibernateTemplate().get(Mva.class, mvaId);

        if (mva == null) {
            throw new ObjectRetrievalFailureException(Mva.class, mvaId);
        }

        return mva;
    }

    /**
     * @see no.ica.fraf.dao.MvaDAO#saveMva(no.ica.fraf.model.Mva)
     */
    public void saveMva(final Mva mva) {
        getHibernateTemplate().saveOrUpdate(mva);
    }

    /**
     * @see no.ica.fraf.dao.MvaDAO#removeMva(java.lang.Integer)
     */
    public void removeMva(final Integer mvaId) {
        getHibernateTemplate().delete(getMva(mvaId));
    }

    /**
     * @see no.ica.fraf.dao.MvaDAO#findAll()
     */
    @SuppressWarnings("unchecked")
	public List<Mva> findAll() {
        return getHibernateTemplate().find("from Mva");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<Mva> findAll(final Object param) {
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
        Mva mva;

        while (objectIt.hasNext()) {
            mva = (Mva) objectIt.next();
            removeMva(mva.getMvaId());
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
        Mva mva;

        while (objectIt.hasNext()) {
            mva = (Mva) objectIt.next();
            saveMva(mva);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeMva(((Mva) object).getMvaId());

    }

    /**
     * @see no.ica.fraf.dao.MvaDAO#findByMvaKode(java.lang.String)
     */
    public Mva findByMvaKode(final String mvaKode) {
        return (Mva) getHibernateTemplate().execute(new HibernateCallback() {

            @SuppressWarnings("unchecked")
            public Object doInHibernate(Session session)
                    throws HibernateException {
                List<Mva> list = session.createCriteria(Mva.class).add(
                        Restrictions.ilike("mvaKode", mvaKode)).list();

                if (list == null || list.size() != 1) {
                    return null;
                }
                return list.get(0);
            }

        });
    }

}
