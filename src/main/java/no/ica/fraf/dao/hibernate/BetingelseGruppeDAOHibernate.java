package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.model.BetingelseGruppe;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BetingelseGruppeDAO
 * 
 * @author abr99
 * 
 */
public class BetingelseGruppeDAOHibernate extends BaseDAOHibernate<BetingelseGruppe> implements
        BetingelseGruppeDAO {
	public BetingelseGruppeDAOHibernate() {
		super(BetingelseGruppe.class);
	}

    /**
     * @see no.ica.fraf.dao.BetingelseGruppeDAO#getBetingelseGruppe(java.lang.Integer)
     */
    public BetingelseGruppe getBetingelseGruppe(final Integer gruppeId) {
        final BetingelseGruppe betingelseGruppe = (BetingelseGruppe) getHibernateTemplate()
                .get(BetingelseGruppe.class, gruppeId);

        if (betingelseGruppe == null) {
            throw new ObjectRetrievalFailureException(BetingelseGruppe.class,
                    gruppeId);
        }

        return betingelseGruppe;
    }

    /**
     * @see no.ica.fraf.dao.BetingelseGruppeDAO#saveBetingelseGruppe(no.ica.fraf.model.BetingelseGruppe)
     */
    public void saveBetingelseGruppe(final BetingelseGruppe betingelseGruppe) {
        getHibernateTemplate().saveOrUpdate(betingelseGruppe);
    }

    /**
     * @see no.ica.fraf.dao.BetingelseGruppeDAO#removeBetingelseGruppe(java.lang.Integer)
     */
    public void removeBetingelseGruppe(final Integer gruppeId) {
        getHibernateTemplate().delete(getBetingelseGruppe(gruppeId));
    }

    /**
     * @see no.ica.fraf.dao.BetingelseGruppeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<BetingelseGruppe> findAll() {
        return getHibernateTemplate().find("from BetingelseGruppe");
    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
     */
    public List<BetingelseGruppe> findAll(final Object param) {
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
        BetingelseGruppe betingelseGruppe;

        while (objectIt.hasNext()) {
            betingelseGruppe = (BetingelseGruppe) objectIt.next();
            removeBetingelseGruppe(betingelseGruppe.getBetingelseGruppeId());
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
        BetingelseGruppe betingelseGruppe;

        while (objectIt.hasNext()) {
            betingelseGruppe = (BetingelseGruppe) objectIt.next();
            saveBetingelseGruppe(betingelseGruppe);
        }

    }

    /**
     * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
     */
    public void deleteObject(final Object object) {
        removeBetingelseGruppe(((BetingelseGruppe) object)
                .getBetingelseGruppeId());

    }

    /**
     * @see no.ica.fraf.dao.BetingelseGruppeDAO#findByName(java.lang.String)
     */
    public BetingelseGruppe findByName(final String name) {
        return (BetingelseGruppe) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        final List list = session.createCriteria(
                                BetingelseGruppe.class).add(
                                Restrictions
                                        .ilike("betingelseGruppeNavn", name))
                                .list();

                        if (list == null || list.size() != 1) {
                            return null;
                        }
                        return list.get(0);
                    }

                });
    }

}
