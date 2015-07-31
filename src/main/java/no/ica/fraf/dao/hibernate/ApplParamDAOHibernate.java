package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.model.ApplParam;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementering av ApplParamDAO
 * 
 * @author abr99
 * 
 */
public class ApplParamDAOHibernate extends BaseDAOHibernate<ApplParam>
		implements ApplParamDAO {

	public ApplParamDAOHibernate() {
		super(ApplParam.class);
	}

	/**
	 * @see no.ica.fraf.dao.ApplParamDAO#getApplParam(java.lang.Integer)
	 */
	public ApplParam getApplParam(final Integer paramId) {
		final ApplParam applParam = (ApplParam) getHibernateTemplate().get(
				ApplParam.class, paramId);

		if (applParam == null) {
			throw new ObjectRetrievalFailureException(ApplParam.class, paramId);
		}

		return applParam;
	}

	/**
	 * @see no.ica.fraf.dao.ApplParamDAO#saveApplParam(no.ica.fraf.model.ApplParam)
	 */
	public void saveApplParam(final ApplParam applParam) {
		getHibernateTemplate().saveOrUpdate(applParam);
	}

	/**
	 * @see no.ica.fraf.dao.ApplParamDAO#removeApplParam(java.lang.Integer)
	 */
	public void removeApplParam(final Integer paramId) {
		getHibernateTemplate().delete(getApplParam(paramId));
	}

	/**
	 * @see no.ica.fraf.dao.ApplParamDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ApplParam> findAll() {
		return getHibernateTemplate().find("from ApplParam");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	public List<ApplParam> findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.ApplParamDAO#deleteAll()
	 */
	public void deleteAll() {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.createQuery("delete from ApplParam").executeUpdate();
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.hibernate.BaseDAOHibernate#cancelObjectUpdates(java.util.List)
	 */
	@Override
	public void cancelObjectUpdates(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();

		while (objectIt.hasNext()) {
			getHibernateTemplate().evict(objectIt.next());
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		ApplParam applParam;

		while (objectIt.hasNext()) {
			applParam = (ApplParam) objectIt.next();
			removeApplParam(applParam.getParamId());
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
		ApplParam applParam;

		while (objectIt.hasNext()) {
			applParam = (ApplParam) objectIt.next();
			saveApplParam(applParam);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeApplParam(((ApplParam) object).getParamId());

	}

	/**
	 * @see no.ica.fraf.dao.ApplParamDAO#findByParamName(java.lang.String)
	 */
	public ApplParam findByParamName(final String paramName,final Integer systemType) {
		return (ApplParam) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						List<ApplParam> params=session.createCriteria(
								ApplParam.class).add(
										Restrictions.ilike("paramName", paramName)).add(Restrictions.or(Restrictions.isNull("systemType"), Restrictions.eq("systemType", systemType))).list();

						if (params.size() != 1) {
							return null;
						}
						return params.get(0);
					}

				});
	}

	@SuppressWarnings("unchecked")
	public List<ApplParam> findByParamNameStartWith(final String likeString) {
		return (List<ApplParam>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(ApplParam.class).add(
								Restrictions.ilike("paramName", likeString
										+ "%")).list();
					}

				});
	}

	public List<ApplParam> findByObject(ApplParam object) {
		return findByExampleLike(object);
	}

	public void removeObject(ApplParam object) {
		removeApplParam(object.getParamId());

	}

	public void refresh(final ApplParam applParam) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				session.refresh(applParam);
				return null;
			}

		});

	}

	public void refreshObject(ApplParam object) {
		refresh(object);

	}

	@SuppressWarnings("unchecked")
	public List<ApplParam> findAllBySystemName(final String systemName) {
		return (List<ApplParam>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(ApplParam.class).add(
								Restrictions.ilike("systemName", systemName))
								.list();
					}

				});
	}

}
