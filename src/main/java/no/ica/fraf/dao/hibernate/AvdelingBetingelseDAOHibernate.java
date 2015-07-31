package no.ica.fraf.dao.hibernate;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.Bunt;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvdelingBetingelseDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingBetingelseDAOHibernate extends
		BaseDAOHibernate<AvdelingBetingelse> implements
		AvdelingBetingelseDAO {

	/**
	 * 
	 */
	public AvdelingBetingelseDAOHibernate() {
		super(AvdelingBetingelse.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingBetingelseDAO#findByBatch(no.ica.fraf.model.Bunt)
	 */
	@SuppressWarnings("unchecked")
	public List findByBatch(final Bunt bunt) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(AvdelingBetingelse.class).add(
						Restrictions.like("bunt", bunt)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingBetingelseDAO#findOtherByAvdeling(no.ica.fraf.model.Avdeling)
	 */
	@SuppressWarnings("unchecked")
	public Hashtable<AvdelingBetingelse, Vector<AvdelingBetingelse>> findOtherByAvdeling(
			final Avdeling avdeling) {
		return (Hashtable<AvdelingBetingelse, Vector<AvdelingBetingelse>>) getHibernateTemplate()
				.execute(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						List list = session.createCriteria(
								AvdelingBetingelse.class).addOrder(
								Order.desc("fraDato")).add(
								Restrictions.like("avdeling", avdeling))
								.createCriteria("betingelseType").addOrder(
										Order.asc("betingelseNavn"))
								.createCriteria("betingelseGruppe").add(
										Restrictions.like(
												"fakturerMedFranchise",
												new Integer(0))).list();
						Hashtable<AvdelingBetingelse, Vector<AvdelingBetingelse>> hashTable = new Hashtable<AvdelingBetingelse, Vector<AvdelingBetingelse>>();
						Hashtable<BetingelseType, AvdelingBetingelse> hashTableTmp = new Hashtable<BetingelseType, AvdelingBetingelse>();
						Iterator listIt = list.iterator();
						AvdelingBetingelse avdelingBetingelse;
						AvdelingBetingelse avdelingBetingelseTmp;
						Vector<AvdelingBetingelse> betingelser = null;

						while (listIt.hasNext()) {
							avdelingBetingelse = (AvdelingBetingelse) listIt
									.next();
							avdelingBetingelseTmp = hashTableTmp
									.get(avdelingBetingelse.getBetingelseType());

							if (avdelingBetingelseTmp == null) {
								avdelingBetingelseTmp = avdelingBetingelse;
								hashTableTmp.put(avdelingBetingelseTmp
										.getBetingelseType(),
										avdelingBetingelseTmp);
							}

							betingelser = hashTable.get(avdelingBetingelseTmp);

							if (betingelser == null) {
								betingelser = new Vector<AvdelingBetingelse>();
							}
							betingelser.add(avdelingBetingelse);
							hashTable.put(avdelingBetingelseTmp, betingelser);
						}
						return hashTable;
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingBetingelseDAO#saveAvdelingBetingelse(no.ica.fraf.model.AvdelingBetingelse)
	 */
	public void saveAvdelingBetingelse(final AvdelingBetingelse betingelse) {
		try {
			getHibernateTemplate().saveOrUpdate(betingelse);
		} catch (DataAccessException e) {
			getHibernateTemplate().flush();
			throw e;

		}

	}

	/**
	 * @see no.ica.fraf.dao.AvdelingBetingelseDAO#findSpeiletByAvdeling(no.ica.fraf.model.Avdeling)
	 */
	public List findSpeiletByAvdeling(final Avdeling avdeling) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(AvdelingBetingelse.class).add(
						Restrictions.like("speilet", Integer.valueOf(1))).add(
						Restrictions.like("avdeling", avdeling)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingBetingelseDAO#findNumberByType(no.ica.fraf.model.BetingelseType)
	 */
	public Integer findNumberByType(final BetingelseType betingelseType) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final StringBuffer queryString = new StringBuffer(
								"select count(*) from AvdelingBetingelse avdelingBetingelse where avdelingBetingelse.betingelseType=:betingelseType");
						final Query query = session.createQuery(queryString
								.toString());
						Object result = query.setParameter("betingelseType",
								betingelseType).iterate().next();
						if (result instanceof Long) {
							return Integer.valueOf(((Long) result).intValue());
						}
						return result;

					}

				});
	}

	/**
	 * @see no.ica.fraf.service.OverviewManager#findAll()
	 */
	public List<AvdelingBetingelse> findAll() {
		return this.findAllOrdered("");
	}

	/**
	 * @param object
	 * @return betingelser
	 */
	public List<AvdelingBetingelse> findByObject(AvdelingBetingelse object) {
		return this.findByExampleLike(object);
	}

	/**
	 * Fjerner betingelse
	 * 
	 * @param object
	 */
	public void removeObject(AvdelingBetingelse object) {
		removeObject(object.getAvdelingBetingelseId());

	}

	/**
	 * Oppdaterer betingelse
	 * 
	 * @param object
	 */
	public void refreshObject(final AvdelingBetingelse object) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				session.refresh(object);
				return null;
			}

		});

	}

}
