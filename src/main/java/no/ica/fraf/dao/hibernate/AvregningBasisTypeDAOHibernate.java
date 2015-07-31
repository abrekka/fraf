package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.model.AvregningBasisType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvregningBasisTypeDAO
 * 
 * @author abr99
 * 
 */
public class AvregningBasisTypeDAOHibernate extends BaseDAOHibernate<AvregningBasisType> implements
        AvregningBasisTypeDAO {
	public AvregningBasisTypeDAOHibernate() {
		super(AvregningBasisType.class);
	}

    /**
     * @see no.ica.fraf.dao.AvregningBasisTypeDAO#getAvregningBasisType(java.lang.Integer)
     */
    public AvregningBasisType getAvregningBasisType(final Integer typeId) {
        final AvregningBasisType basisType = (AvregningBasisType) getHibernateTemplate()
                .get(AvregningBasisType.class, typeId);

        if (basisType == null) {
            throw new ObjectRetrievalFailureException(AvregningBasisType.class,
                    typeId);
        }

        return basisType;
    }

    /**
     * @see no.ica.fraf.dao.AvregningBasisTypeDAO#saveAvregningBasisType(no.ica.fraf.model.AvregningBasisType)
     */
    public void saveAvregningBasisType(final AvregningBasisType basisType) {
        getHibernateTemplate().saveOrUpdate(basisType);
    }

    /**
     * @see no.ica.fraf.dao.AvregningBasisTypeDAO#removeAvregningBasisType(java.lang.Integer)
     */
    public void removeAvregningBasisType(final Integer typeId) {
        getHibernateTemplate().delete(getAvregningBasisType(typeId));
    }

    /**
     * @see no.ica.fraf.dao.AvregningBasisTypeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<AvregningBasisType> findAll() {
        return getHibernateTemplate().find("from AvregningBasisType");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<AvregningBasisType> findAll(final Object param) {
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
        AvregningBasisType basisType;

        while (objectIt.hasNext()) {
            basisType = (AvregningBasisType) objectIt.next();
            removeAvregningBasisType(basisType.getAvregningBasisTypeId());
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
        AvregningBasisType basisType;

        while (objectIt.hasNext()) {
            basisType = (AvregningBasisType) objectIt.next();
            saveAvregningBasisType(basisType);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeAvregningBasisType(((AvregningBasisType) object)
                .getAvregningBasisTypeId());

    }

    /**
     * @see no.ica.fraf.dao.AvregningBasisTypeDAO#findByKode(java.lang.String)
     */
    public AvregningBasisType findByKode(final String kode) {
        return (AvregningBasisType) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        final List list = session.createCriteria(
                                AvregningBasisType.class).add(
                                Restrictions.ilike("avregningBasisTypeKode",
                                        kode)).list();

                        if (list == null || list.size() != 1) {
                            return null;
                        }
                        return list.get(0);
                    }

                });
    }

}
