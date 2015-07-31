package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.AvdelingAvregningDAO;
import no.ica.fraf.model.AvdelingAvregning;
import no.ica.fraf.model.AvdelingAvregningBelop;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for tabell AVDELING_AVREGNING
 * 
 * @author abr99
 * 
 */
public class AvdelingAvregningDAOHibernate extends
		BaseDAOHibernate<AvdelingAvregning> implements AvdelingAvregningDAO {
	/**
	 * 
	 */
	public AvdelingAvregningDAOHibernate() {
		super(AvdelingAvregning.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingAvregningDAO#findByAvdelingAvregningBelop(no.ica.fraf.model.AvdelingAvregningBelop)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingAvregning> findByAvdelingAvregningBelop(
			final AvdelingAvregningBelop belop) {
		return (List<AvdelingAvregning>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(AvdelingAvregning.class)
								.add(
										Restrictions
												.eq("avdelingAvregningBelop",
														belop)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingAvregningDAO#findByAvdnr(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingAvregning> findByAvdnr(final Integer avdnr,final Integer year) {
		return (List<AvdelingAvregning>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(AvdelingAvregning.class)
								.createCriteria("avdelingAvregningBelop")
								.addOrder(Order.asc("maaned")).createCriteria(
										"avdelingAvregningImport").add(
										Restrictions.eq("avdnr", avdnr)).add(Restrictions.eq("aar",year)).list();
					}

				});
	}

}
