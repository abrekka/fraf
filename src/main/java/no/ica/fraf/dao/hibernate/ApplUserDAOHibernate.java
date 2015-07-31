package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.model.ApplUser;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av ApplUserDAO
 * 
 * @author abr99
 * 
 */
public class ApplUserDAOHibernate extends BaseDAOHibernate<ApplUser>
		implements ApplUserDAO {

	/**
	 * 
	 */
	public ApplUserDAOHibernate() {
		super(ApplUser.class);
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#getApplUser(java.lang.Integer)
	 */
	public ApplUser getApplUser(final Integer applUserId) {
		final ApplUser applUser = (ApplUser) getHibernateTemplate().get(
				ApplUser.class, applUserId);

		if (applUser == null) {
			throw new ObjectRetrievalFailureException(ApplUser.class,
					applUserId);
		}

		return applUser;
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#saveApplUser(no.ica.fraf.model.ApplUser)
	 */
	public void saveApplUser(final ApplUser applUser) {

		getHibernateTemplate().saveOrUpdate(applUser);
		getHibernateTemplate().flush();

	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#removeApplUser(java.lang.Integer)
	 */
	public void removeApplUser(final Integer applUserId) {
		getHibernateTemplate().delete(getApplUser(applUserId));
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ApplUser> findAll() {
		return getHibernateTemplate().find("from ApplUser");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<ApplUser> findAll(final Object param) {
		return getHibernateTemplate().find(
				"from ApplUser where startDate is not null");
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#deleteAll()
	 */
	public void deleteAll() {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.createQuery("delete from ApplUser").executeUpdate();
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#findByUser(java.lang.String,
	 *      java.lang.String)
	 */
	public ApplUser findByUser(final String userName, final String passwrd) {
		return (ApplUser) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						ApplUser applUser = null;
						if (userName != null) {

							Criteria crit = session.createCriteria(
									ApplUser.class).add(
									Restrictions.ilike("userName", userName));

							if (passwrd != null) {
								crit = crit.add(Restrictions.ilike("password",
										passwrd));
							}

							final List users = crit.list();

							if (users != null && users.size() != 0) {
								applUser = (ApplUser) users.get(0);
							}
						}
						return applUser;
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserDAO#refresh(no.ica.fraf.model.ApplUser)
	 */
	public void refresh(final ApplUser applUser) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				session.refresh(applUser);
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		ApplUser applUser;

		while (objectIt.hasNext()) {
			applUser = (ApplUser) objectIt.next();
			removeApplUser(applUser.getUserId());
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
		ApplUser applUser;

		while (objectIt.hasNext()) {
			applUser = (ApplUser) objectIt.next();
			saveApplUser(applUser);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeApplUser(((ApplUser) object).getUserId());

	}

	/**
	 * Finner basert på objekt
	 * 
	 * @param object
	 * @return brukere
	 */
	public List<ApplUser> findByObject(ApplUser object) {
		return findByExampleLike(object);
	}

	/**
	 * Fjerner bruker
	 * 
	 * @param object
	 */
	public void removeObject(ApplUser object) {
		removeApplUser(object.getUserId());

	}

	/**
	 * Oppdaterer bruker
	 * 
	 * @param object
	 */
	public void refreshObject(ApplUser object) {
		refresh(object);

	}

}
