package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.FornyelseTypeDAO;
import no.ica.fraf.model.FornyelseType;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av FornyelseTypeDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FornyelseTypeDAOHibernate extends BaseDAOHibernate<FornyelseType> implements
        FornyelseTypeDAO {
	public FornyelseTypeDAOHibernate() {
		super(FornyelseType.class);
	}

    /**
     * @see no.ica.fraf.dao.FornyelseTypeDAO#getFornyelseType(java.lang.Integer)
     */
    public FornyelseType getFornyelseType(final Integer typeId) {
        final FornyelseType fornyelseType = (FornyelseType) getHibernateTemplate()
                .get(FornyelseType.class, typeId);

        if (fornyelseType == null) {
            throw new ObjectRetrievalFailureException(FornyelseType.class,
                    typeId);
        }

        return fornyelseType;
    }

    /**
     * @see no.ica.fraf.dao.FornyelseTypeDAO#saveFornyelseType(no.ica.fraf.model.FornyelseType)
     */
    public void saveFornyelseType(final FornyelseType fornyelseType) {
        getHibernateTemplate().saveOrUpdate(fornyelseType);
    }

    /**
     * @see no.ica.fraf.dao.FornyelseTypeDAO#removeFornyelseType(java.lang.Integer)
     */
    public void removeFornyelseType(final Integer typeId) {
        getHibernateTemplate().delete(getFornyelseType(typeId));
    }

    /**
     * @see no.ica.fraf.dao.FornyelseTypeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<FornyelseType> findAll() {
        return getHibernateTemplate().find("from FornyelseType");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<FornyelseType> findAll(final Object param) {
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
        FornyelseType fornyelseType;

        while (objectIt.hasNext()) {
            fornyelseType = (FornyelseType) objectIt.next();
            removeFornyelseType(fornyelseType.getFornyelseTypeId());
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
        FornyelseType fornyelseType;

        while (objectIt.hasNext()) {
            fornyelseType = (FornyelseType) objectIt.next();
            saveFornyelseType(fornyelseType);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeFornyelseType(((FornyelseType) object).getFornyelseTypeId());

    }

}
