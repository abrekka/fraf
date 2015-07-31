package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.SikkerhetTypeDAO;
import no.ica.fraf.model.SikkerhetType;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av SikkerhetTypeDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class SikkerhetTypeDAOHibernate extends BaseDAOHibernate<SikkerhetType> implements
        SikkerhetTypeDAO {
	public SikkerhetTypeDAOHibernate() {
		super(SikkerhetType.class);
	}

    /**
     * @see no.ica.fraf.dao.SikkerhetTypeDAO#getSikkerhetType(java.lang.Integer)
     */
    public SikkerhetType getSikkerhetType(final Integer typeId) {
        final SikkerhetType sikkerhetType = (SikkerhetType) getHibernateTemplate()
                .get(SikkerhetType.class, typeId);

        if (sikkerhetType == null) {
            throw new ObjectRetrievalFailureException(SikkerhetType.class,
                    typeId);
        }

        return sikkerhetType;
    }

    /**
     * @see no.ica.fraf.dao.SikkerhetTypeDAO#saveSikkerhetType(no.ica.fraf.model.SikkerhetType)
     */
    public void saveSikkerhetType(final SikkerhetType sikkerhetType) {
        getHibernateTemplate().saveOrUpdate(sikkerhetType);
    }

    /**
     * @see no.ica.fraf.dao.SikkerhetTypeDAO#removeSikkerhetType(java.lang.Integer)
     */
    public void removeSikkerhetType(final Integer typeId) {
        getHibernateTemplate().delete(getSikkerhetType(typeId));
    }

    /**
     * @see no.ica.fraf.dao.SikkerhetTypeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<SikkerhetType> findAll() {
        return getHibernateTemplate().find("from SikkerhetType");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<SikkerhetType> findAll(final Object param) {
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
        SikkerhetType sikkerhetType;

        while (objectIt.hasNext()) {
            sikkerhetType = (SikkerhetType) objectIt.next();
            removeSikkerhetType(sikkerhetType.getSikkerhetTypeId());
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
        SikkerhetType sikkerhetType;

        while (objectIt.hasNext()) {
            sikkerhetType = (SikkerhetType) objectIt.next();
            saveSikkerhetType(sikkerhetType);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeSikkerhetType(((SikkerhetType) object).getSikkerhetTypeId());

    }

}
