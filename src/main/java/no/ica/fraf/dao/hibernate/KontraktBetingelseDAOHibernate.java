package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.KontraktBetingelseDAO;
import no.ica.fraf.model.KontraktBetingelse;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av KontraktBetingelseDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class KontraktBetingelseDAOHibernate extends BaseDAOHibernate<KontraktBetingelse> implements
        KontraktBetingelseDAO {
	public KontraktBetingelseDAOHibernate() {
		super(KontraktBetingelse.class);
	}

    /**
     * @see no.ica.fraf.dao.KontraktBetingelseDAO#getKontraktBetingelse(java.lang.Integer)
     */
    public KontraktBetingelse getKontraktBetingelse(final Integer betingelseId) {
        final KontraktBetingelse betingelse = (KontraktBetingelse) getHibernateTemplate()
                .get(KontraktBetingelse.class, betingelseId);

        if (betingelse == null) {
            throw new ObjectRetrievalFailureException(KontraktBetingelse.class,
                    betingelseId);
        }

        return betingelse;
    }

    /**
     * @see no.ica.fraf.dao.KontraktBetingelseDAO#saveKontraktBetingelse(no.ica.fraf.model.KontraktBetingelse)
     */
    public void saveKontraktBetingelse(final KontraktBetingelse betingelse) {
        getHibernateTemplate().saveOrUpdate(betingelse);
    }

    /**
     * @see no.ica.fraf.dao.KontraktBetingelseDAO#removeKontraktBetingelse(java.lang.Integer)
     */
    public void removeKontraktBetingelse(final Integer betingelseId) {
        getHibernateTemplate().delete(getKontraktBetingelse(betingelseId));
    }

    /**
     * @see no.ica.fraf.dao.KontraktBetingelseDAO#findAll()
     */
    @SuppressWarnings("unchecked")
	public List<KontraktBetingelse> findAll() {
        return getHibernateTemplate().find("from KontraktBetingelse");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<KontraktBetingelse> findAll(final Object param) {
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
        KontraktBetingelse betingelse;

        while (objectIt.hasNext()) {
            betingelse = (KontraktBetingelse) objectIt.next();
            removeKontraktBetingelse(betingelse.getKontraktBetingelseId());
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
        KontraktBetingelse betingelse;

        while (objectIt.hasNext()) {
            betingelse = (KontraktBetingelse) objectIt.next();
            saveKontraktBetingelse(betingelse);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeKontraktBetingelse(((KontraktBetingelse) object)
                .getKontraktBetingelseId());

    }

}
