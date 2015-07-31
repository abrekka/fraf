package no.ica.fraf.dao.hibernate;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import no.ica.fraf.dao.SpeiletBetingelseDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.SpeiletBetingelse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av SpeiletBetingelseDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class SpeiletBetingelseDAOHibernate extends BaseDAOHibernate<SpeiletBetingelse> implements
		SpeiletBetingelseDAO {
	public SpeiletBetingelseDAOHibernate() {
		super(SpeiletBetingelse.class);
	}

	/**
	 * @see no.ica.fraf.dao.SpeiletBetingelseDAO#findByAvdeling(no.ica.fraf.model.Avdeling)
	 */
	public Map findByAvdeling(final Avdeling avdeling) {
		return (Map) getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				Map<Integer, SpeiletBetingelse> map = new Hashtable<Integer, SpeiletBetingelse>();
				List<Object[]> list = session
						.createQuery(
								"select speiletBetingelse.kontraktObjektId,speiletBetingelse from SpeiletBetingelse speiletBetingelse where speiletBetingelse.avdeling = :avdeling")
						.setParameter("avdeling", avdeling).list();

				if (list != null) {
					for (Object[] objects : list) {
						map.put((Integer) objects[0],
								(SpeiletBetingelse) objects[1]);
					}
				}
				return map;
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.SpeiletBetingelseDAO#findAll()
	 */
	public List findAll() {
		return getHibernateTemplate().find("from SpeiletBetingelse");
	}

	/**
	 * @see no.ica.fraf.dao.SpeiletBetingelseDAO#findAllKontraktObjektIder()
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findAllKontraktObjektIder() {
		return getHibernateTemplate().find(
				"select kontraktObjektId from SpeiletBetingelse");
	}

	/**
	 * @see no.ica.fraf.dao.SpeiletBetingelseDAO#findByContractDate(java.util.Date,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	public List findByContractDate(final Date contractDate,
			final Integer fromDep, final Integer toDep) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session
						.createQuery(
								"select speiletBetingelse from "
										+ "SpeiletBetingelse speiletBetingelse,AvdelingBetingelse avdelingBetingelse,Avdeling avdeling "
										+ "where avdeling.avdnr between :depFrom and :depTo and "
										+ "avdelingBetingelse.avdeling = avdeling and "
										+ "speiletBetingelse.avdelingBetingelse = avdelingBetingelse and "
										+ ":dato <= avdelingBetingelse.tilDato")
						.setParameter("dato", contractDate).setParameter(
								"depFrom", fromDep)
						.setParameter("depTo", toDep).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.SpeiletBetingelseDAO#findByBetingelseType(no.ica.fraf.model.BetingelseType)
	 */
	@SuppressWarnings("unchecked")
	public List<SpeiletBetingelse> findByBetingelseType(
			final BetingelseType betingelseType) {
		return (List<SpeiletBetingelse>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(SpeiletBetingelse.class)
								.createCriteria("avdelingBetingelse").add(
										Restrictions.like("betingelseType",
												betingelseType)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.SpeiletBetingelseDAO#findByAvdelingBetingelseId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<SpeiletBetingelse> findByAvdelingBetingelseId(
			final Integer avdelingBetingelseId) {
		return (List<SpeiletBetingelse>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(SpeiletBetingelse.class)
								.createCriteria("avdelingBetingelse").add(
										Restrictions.like(
												"avdelingBetingelseId",
												avdelingBetingelseId)).list();
					}

				});
	}

}
