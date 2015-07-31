package no.ica.fraf.dao.hibernate.fenistra;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.fenistra.LfFakturaPosterDAO;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.LfFakturaPoster;
import no.ica.fraf.model.LkKontraktobjekter;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Klasse for tabell LF_FAKTURAPOSTER i Fensitra
 * 
 * @author abr99
 * 
 */
public class LfFakturaPosterDAOHibernate extends BaseDAOHibernate<LfFakturaPoster> implements
        LfFakturaPosterDAO {
	
	public LfFakturaPosterDAOHibernate() {
		super(LfFakturaPoster.class);
	}

    /**
     * @see no.ica.fraf.dao.fenistra.LfFakturaPosterDAO#findByKontrakt(no.ica.fraf.model.LkKontraktobjekter)
     */
    @SuppressWarnings("unchecked")
	public List<LfFakturaPoster> findByKontrakt(final LkKontraktobjekter lkKontrakter) {
        return (List<LfFakturaPoster>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
            	Object returnObject = null;
            	if (lkKontrakter != null) {
                returnObject = session.createCriteria(LfFakturaPoster.class).add(
                        Restrictions.like("kontraktObjektId",
                                lkKontrakter.getKontraktObjektId()))
                        .addOrder(Order.desc("fakturaNr")).list();
            	}
            	return returnObject;
            }

        });
    }

    /**
     * @see no.ica.fraf.dao.fenistra.LfFakturaPosterDAO#findByKontraktObjektIder(java.util.List)
     */
    public List findByKontraktObjektIder(final List list) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                return session.createCriteria(LfFakturaPoster.class).add(
                        Restrictions.in("kontraktObjektId", list)).list();
            }

        });
    }

    /**
     * @see no.ica.fraf.dao.fenistra.LfFakturaPosterDAO#findByKontraktObjektId(java.lang.Integer,
     *      java.util.List, java.util.Date)
     */
    public List findByKontraktObjektId(final Integer kontraktObjektId,
            final List innlest, final Date speiletFraDato) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                final Criteria crit = session.createCriteria(LfFakturaPoster.class)
                        .add(
                                Restrictions.like("kontraktObjektId",
                                        kontraktObjektId)).add(
                                Restrictions.ge("fra", speiletFraDato));

                if (innlest != null && innlest.size() != 0) {
                    crit.add(
                            Restrictions.not(Restrictions.in("fakturaPostId",
                                    innlest))).list();
                }
                return crit.list();
            }

        });
    }

}
