package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.FakturaTekstDAO;
import no.ica.fraf.model.FakturaTekst;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av FakturaTekstDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FakturaTekstDAOHibernate extends BaseDAOHibernate<FakturaTekst> implements
        FakturaTekstDAO {
	public FakturaTekstDAOHibernate() {
		super(FakturaTekst.class);
	}

    /**
     * @see no.ica.fraf.dao.FakturaTekstDAO#findByFakturaId(java.lang.Integer)
     */
    public List findByFakturaId(final Integer fakturaId) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                return session.createCriteria(FakturaTekst.class)
                        .createCriteria("faktura").add(
                                Restrictions.like("fakturaId", fakturaId))
                        .list();
            }

        });
    }

    /**
     * @see no.ica.fraf.dao.FakturaTekstDAO#findByBuntId(java.lang.Integer)
     */
    public List findByBuntId(final Integer buntId) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                return session.createCriteria(FakturaTekst.class)
                        .createCriteria("faktura").addOrder(
                                Order.asc("fakturaId")).createCriteria("bunt")
                        .add(Restrictions.like("buntId", buntId)).list();
            }

        });
    }

}
