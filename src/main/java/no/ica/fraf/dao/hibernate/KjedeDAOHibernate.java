package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.KjedeDAO;
import no.ica.fraf.model.Kjede;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av KjedeDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class KjedeDAOHibernate extends BaseDAOHibernate<Kjede> implements KjedeDAO {
	public KjedeDAOHibernate() {
		super(Kjede.class);
	}

    /**
     * @see no.ica.fraf.dao.KjedeDAO#getKjede(java.lang.Integer)
     */
    public Kjede getKjede(final Integer kjedeId) {
        final Kjede kjede = (Kjede) getHibernateTemplate().get(Kjede.class,
                kjedeId);

        if (kjede == null) {
            throw new ObjectRetrievalFailureException(Kjede.class, kjedeId);
        }

        return kjede;
    }

    /**
     * @see no.ica.fraf.dao.KjedeDAO#saveKjede(no.ica.fraf.model.Kjede)
     */
    public void saveKjede(final Kjede kjede) {
        getHibernateTemplate().saveOrUpdate(kjede);
    }

    /**
     * @see no.ica.fraf.dao.KjedeDAO#removeKjede(java.lang.Integer)
     */
    public void removeKjede(final Integer kjedeId) {
        getHibernateTemplate().delete(getKjede(kjedeId));
    }

    /**
     * @see no.ica.fraf.dao.KjedeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
	public List<Kjede> findAll() {
        return getHibernateTemplate().find("from Kjede");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<Kjede> findAll(final Object param) {
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
        Kjede kjede;

        while (objectIt.hasNext()) {
            kjede = (Kjede) objectIt.next();
            removeKjede(kjede.getKjedeId());
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
        Kjede kjede;

        while (objectIt.hasNext()) {
            kjede = (Kjede) objectIt.next();
            saveKjede(kjede);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeKjede(((Kjede) object).getKjedeId());

    }

}
