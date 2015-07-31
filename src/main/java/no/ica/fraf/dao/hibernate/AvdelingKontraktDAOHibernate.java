package no.ica.fraf.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.AvdelingKontraktDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingKontrakt;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * DAO gort AvdelingKontrakt
 * 
 * @author abr99
 * 
 */
public class AvdelingKontraktDAOHibernate extends BaseDAOHibernate<AvdelingKontrakt> implements
        AvdelingKontraktDAO {
	public AvdelingKontraktDAOHibernate() {
		super(AvdelingKontrakt.class);
	}

    /**
     * @see no.ica.fraf.dao.AvdelingKontraktDAO#getAvdelingKontrakt(java.lang.Integer)
     */
    public AvdelingKontrakt getAvdelingKontrakt(final Integer kontraktId) {
        final AvdelingKontrakt avdelingKontrakt = (AvdelingKontrakt) getHibernateTemplate()
                .get(AvdelingKontrakt.class, kontraktId);

        if (avdelingKontrakt == null) {
            throw new ObjectRetrievalFailureException(AvdelingKontrakt.class,
                    kontraktId);
        }

        return avdelingKontrakt;
    }

    /**
     * @see no.ica.fraf.dao.AvdelingKontraktDAO#saveAvdelingKontrakt(no.ica.fraf.model.AvdelingKontrakt)
     */
    public void saveAvdelingKontrakt(final AvdelingKontrakt avdelingKontrakt) {
        getHibernateTemplate().saveOrUpdate(avdelingKontrakt);
    }

    /**
     * @see no.ica.fraf.dao.AvdelingKontraktDAO#removeAvdelingKontrakt(java.lang.Integer)
     */
    public void removeAvdelingKontrakt(final Integer kontraktId) {
        getHibernateTemplate().delete(getAvdelingKontrakt(kontraktId));
    }

    /**
     * @see no.ica.fraf.dao.AvdelingKontraktDAO#findAll()
     */
    public List findAll() {
        return getHibernateTemplate().find("from AvdelingKontrakt");
    }

    /**
     * @see no.ica.fraf.dao.AvdelingKontraktDAO#getCurrentContract(no.ica.fraf.model.Avdeling)
     */
    public AvdelingKontrakt getCurrentContract(final Avdeling avdeling) {
        return (AvdelingKontrakt) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        AvdelingKontrakt avdelingKontrakt = null;
                        final Date currentDate = Calendar.getInstance()
                                .getTime();
                        final List list = session.createCriteria(
                                AvdelingKontrakt.class).add(
                                Restrictions.like("avdeling", avdeling)).add(
                                Restrictions.le("fraDato", currentDate)).add(
                                Restrictions.ge("tilDato", currentDate)).list();

                        if (list != null && list.size() != 0) {
                                avdelingKontrakt = (AvdelingKontrakt) list
                                        .get(0);
                        }
                        return avdelingKontrakt;
                    }

                });
    }

    /**
     * @see no.ica.fraf.dao.AvdelingKontraktDAO#findByAvdeling(no.ica.fraf.model.Avdeling)
     */
    public List findByAvdeling(final Avdeling avdeling) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                return session.createCriteria(AvdelingKontrakt.class).add(
                        Restrictions.like("avdeling", avdeling)).addOrder(
                        Order.desc("avdelingKontraktId")).list();
            }

        });
    }

}
