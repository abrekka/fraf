package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.view.FakturaBuntVDAO;
import no.ica.fraf.model.FakturaBuntV;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av FakturaBuntVDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FakturaBuntVDAOHibernate extends BaseDAOHibernate<FakturaBuntV> implements
		FakturaBuntVDAO {
	public FakturaBuntVDAOHibernate() {
		super(FakturaBuntV.class);
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#getFakturaBuntV(java.lang.Integer)
	 */
	public FakturaBuntV getFakturaBuntV(final Integer fakturaBuntId) {
		final FakturaBuntV fakturaBuntV = (FakturaBuntV) getHibernateTemplate()
				.get(FakturaBuntV.class, fakturaBuntId);

		if (fakturaBuntV == null) {
			throw new ObjectRetrievalFailureException(FakturaBuntV.class,
					fakturaBuntId);
		}

		return fakturaBuntV;
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#findAll()
	 */
	public List findAll() {
		return getHibernateTemplate().find("from FakturaBuntV");
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#getCount()
	 */
	public Integer getCount() {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final StringBuffer queryString = new StringBuffer(
								"select count(*) from FakturaBuntV");
						final Query query = session.createQuery(queryString
								.toString());
						Object result = query.iterate().next();
						if (result instanceof Long) {
							return Integer.valueOf(((Long) result).intValue());
						}
						return result;

					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#findAllPaged(int, int)
	 */
	public List findAllPaged(final int currentIndex, final int fetchSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {

				Criteria criteria = session.createCriteria(FakturaBuntV.class)
						.addOrder(Order.desc("buntId"));

				criteria.setFetchSize(fetchSize);
				criteria.setMaxResults(fetchSize);
				criteria.setFirstResult(currentIndex);
				return criteria.list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#refresh(no.ica.fraf.model.FakturaBuntV)
	 */
	public void refresh(final FakturaBuntV fakturaBuntV) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.refresh(fakturaBuntV);
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#load(no.ica.fraf.model.FakturaBuntV)
	 */
	public void load(FakturaBuntV fakturaBuntV) {
		getHibernateTemplate().load(fakturaBuntV, fakturaBuntV.getBuntId());

	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#findByBatchId(java.lang.Integer)
	 */
	public FakturaBuntV findByBatchId(final Integer batchId) {
		return (FakturaBuntV) getHibernateTemplate().get(FakturaBuntV.class,
				batchId);
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#findByYearAndPeriod(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaBuntV> findByYearAndPeriod(final Integer year,
			final Integer period) {
		return (List<FakturaBuntV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(FakturaBuntV.class).add(
								Restrictions.eq("aar", year)).add(
								Restrictions.eq("fraPeriode", period)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.view.FakturaBuntVDAO#findByOpprettetMaaned(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaBuntV> findByOpprettetMaaned(final Integer year,
			final Integer period) {
		return (List<FakturaBuntV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(FakturaBuntV.class).add(
								Restrictions.eq("opprettetAar", year)).add(
								Restrictions.eq("opprettetMaaned", period))
								.list();
					}

				});
	}

}
