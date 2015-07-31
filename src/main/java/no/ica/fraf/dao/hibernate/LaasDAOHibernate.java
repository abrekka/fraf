package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Laas;
import no.ica.fraf.model.LaasType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av LaasDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class LaasDAOHibernate extends BaseDAOHibernate<Laas> implements LaasDAO {
	public LaasDAOHibernate() {
		super(Laas.class);
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#getLaas(java.lang.Integer)
	 */
	public Laas getLaas(final Integer laasId) {
		final Laas laas = (Laas) getHibernateTemplate().get(Laas.class, laasId);

		if (laas == null) {
			throw new ObjectRetrievalFailureException(Laas.class, laasId);
		}

		return laas;
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#saveLaas(no.ica.fraf.model.Laas)
	 */
	public void saveLaas(final Laas laas) {
		getHibernateTemplate().saveOrUpdate(laas);
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#removeLaas(java.lang.Integer)
	 */
	public void removeLaas(final Integer laasId) {
		getHibernateTemplate().delete(getLaas(laasId));
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Laas> findAll() {
		return getHibernateTemplate().find("from Laas");
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#findByAvdeling(no.ica.fraf.model.Avdeling)
	 */
	public Laas findByAvdeling(final Avdeling avdeling) {
		return (Laas) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				final List list = session.createCriteria(Laas.class).add(
						Restrictions.like("avdeling", avdeling)).list();

				if (list == null || list.size() == 0) {
					return null;
				}
				return (Laas) list.get(0);
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#removeByUser(no.ica.fraf.model.ApplUser)
	 */
	public void removeByUser(final ApplUser applUser) {
		final List<Laas> list = findByUser(applUser);

		if (list != null) {
			for (Laas laas : list) {
				removeLaas(laas.getLaasId());
			}
		}

	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#findByUser(no.ica.fraf.model.ApplUser)
	 */
	@SuppressWarnings("unchecked")
	public List<Laas> findByUser(final ApplUser applUser) {
		return (List<Laas>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(Laas.class).add(
								Restrictions.like("applUser", applUser)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#findByLaasType(no.ica.fraf.model.LaasType)
	 */
	public Laas findByLaasType(final LaasType laasType) {
		return (Laas) getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				Laas laas = null;
				final List<Laas> list = session.createCriteria(Laas.class).add(
						Restrictions.like("laasType", laasType)).list();

				if (list != null && list.size() != 0) {
					laas = list.get(0);
				}
				return laas;
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.LaasDAO#findByLaasTypeAndId(no.ica.fraf.model.LaasType,
	 *      java.lang.Integer)
	 */
	public Laas findByLaasTypeAndId(final LaasType laasType, final Integer id) {
		return (Laas) getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				Laas laas = null;
				final List<Laas> list = session.createCriteria(Laas.class).add(
						Restrictions.like("laasType", laasType)).add(
						Restrictions.eq("id", id)).list();

				if (list != null && list.size() != 0) {
					laas = list.get(0);
				}
				return laas;
			}

		});
	}

}
