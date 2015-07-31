package no.ica.tollpost.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.ApplParam;
import no.ica.tollpost.dao.ApplParamTollpostDAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ApplParamTollpostDAOHibernate extends
		BaseDAOHibernate<ApplParam> implements ApplParamTollpostDAO {
	/**
	 * Konstruktør
	 */
	public ApplParamTollpostDAOHibernate() {
		super(ApplParam.class);
	}

	public List<ApplParam> findAll() {
		return (List<ApplParam>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(ApplParam.class).add(
								Restrictions.like("paramName", "tg",
										MatchMode.START)).list();
					}

				});
	}

	public List<ApplParam> findByObject(ApplParam object) {
		return findByExampleLike(object);
	}

	public void removeObject(ApplParam object) {
		removeObject(object.getParamId());

	}

	public void refreshObject(final ApplParam object) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				session.refresh(object);
				return null;
			}

		});

	}

}
