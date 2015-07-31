package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.BuntFeilDAO;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntFeil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BuntFeilDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class BuntFeilDAOHibernate extends BaseDAOHibernate<BuntFeil> implements
		BuntFeilDAO {
	public BuntFeilDAOHibernate() {
		super(BuntFeil.class);
	}

	/**
	 * @see no.ica.fraf.dao.BuntFeilDAO#findByBunt(no.ica.fraf.model.Bunt)
	 */
	@SuppressWarnings("unchecked")
	public List<BuntFeil> findByBunt(final Bunt bunt) {
		return (List<BuntFeil>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(BuntFeil.class).add(
								Restrictions.like("bunt", bunt)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.BuntFeilDAO#findByBuntId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<BuntFeil> findByBuntId(final Integer buntId) {
		return (List<BuntFeil>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(BuntFeil.class).addOrder(
								Order.asc("buntFeilId")).createCriteria("bunt")
								.add(Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.BuntFeilDAO#getCountByBuntId(java.lang.Integer)
	 */
	public Integer getCountByBuntId(final Integer batchId) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Object returnValue = null;
						if (batchId != null) {
							final StringBuffer queryString = new StringBuffer(
									"select count(*) from BuntFeil as buntFeil,Bunt as bunt where buntFeil.bunt = bunt and bunt.buntId = :buntId");
							final Query query = session.createQuery(queryString
									.toString());
							query.setParameter("buntId", batchId.intValue());
							returnValue = query.iterate().next();
						}
						if (returnValue instanceof Long) {
							return Integer.valueOf(((Long) returnValue)
									.intValue());
						}
						return returnValue;

					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FeilDAO#findByFakturaId(java.lang.Integer)
	 */
	public List findByFakturaId(final Integer invoiceId) {
		return null;
	}

	/**
	 * @see no.ica.fraf.dao.FeilDAO#findById(java.lang.Integer)
	 */
	public List<?> findById(Integer id) {
		return findByBuntId(id);
	}

}
