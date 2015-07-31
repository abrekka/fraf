package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.LoggDAO;
import no.ica.fraf.model.Logg;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av LoaggDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class LoggDAOHibernate extends BaseDAOHibernate<Logg> implements LoggDAO {
	public LoggDAOHibernate() {
		super(Logg.class);
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#getLogg(java.lang.Integer)
	 */
	public Logg getLogg(final Integer loggId) {
		final Logg logg = (Logg) getHibernateTemplate().get(Logg.class, loggId);

		if (logg == null) {
			throw new ObjectRetrievalFailureException(Logg.class, loggId);
		}

		return logg;
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#saveLogg(no.ica.fraf.model.Logg)
	 */
	public void saveLogg(final Logg logg) {
		getHibernateTemplate().saveOrUpdate(logg);
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#removeLogg(java.lang.Integer)
	 */
	public void removeLogg(final Integer loggId) {
		getHibernateTemplate().delete(getLogg(loggId));
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Logg> findAll() {
		return getHibernateTemplate().find("from Logg order by logId desc");
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#findAllOrderById()
	 */
	public List findAllOrderById() {
		return getHibernateTemplate().find("from Logg order by logId");
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#deleteAll()
	 */
	public void deleteAll() {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.createQuery("delete from Logg").executeUpdate();
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.logging.SaveLogInterface#save(java.lang.Object)
	 */
	public void save(final Object object) {
		saveLogg((Logg) object);

	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#getCount()
	 */
	public Integer getCount() {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final Query query = session
								.createQuery("select count(*) from Logg");
						Object result = query.iterate().next();
						if(result instanceof Long){
							return Integer.valueOf(((Long)result).intValue());
						}
						return result;
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.LoggDAO#findAll(int, int)
	 */
	public List findAll(final int startIndex, final int fetchSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {

				final Criteria criteria = session.createCriteria(Logg.class)
						.addOrder(Order.desc("logId"));

				criteria.setFetchSize(fetchSize);
				criteria.setMaxResults(fetchSize);
				criteria.setFirstResult(startIndex);
				return criteria.list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	public List<Logg> findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void deleteObjects(List objects) {
		if (objects == null) {
			return;
		}
		List<Logg> list = objects;

		for (Logg logg : list) {
			removeLogg(logg.getLogId());
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void persistObjects(List objects) {
		if (objects == null) {
			return;
		}
		List<Logg> list = objects;

		for (Logg logg : list) {
			saveLogg(logg);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeLogg(((Logg) object).getLogId());

	}

}
