package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Rik2KjedeV;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BetingelseTypeDAO
 * 
 * @author abr99
 * 
 */
public class BetingelseTypeDAOHibernate extends
		BaseDAOHibernate<BetingelseType> implements BetingelseTypeDAO {

	/**
	 * 
	 */
	public BetingelseTypeDAOHibernate() {
		super(BetingelseType.class);

	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#getBetingelseType(java.lang.Integer)
	 */
	public BetingelseType getBetingelseType(final Integer typeId) {
		final BetingelseType betingelseType = (BetingelseType) getHibernateTemplate()
				.get(BetingelseType.class, typeId);

		if (betingelseType == null) {
			throw new ObjectRetrievalFailureException(BetingelseType.class,
					typeId);
		}

		return betingelseType;
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#saveBetingelseType(no.ica.fraf.model.BetingelseType)
	 */
	public void saveBetingelseType(final BetingelseType betingelseType) {
		getHibernateTemplate().saveOrUpdate(betingelseType);
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#removeBetingelseType(java.lang.Integer)
	 */
	public void removeBetingelseType(final Integer typeId) {
		getHibernateTemplate().delete(getBetingelseType(typeId));
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<BetingelseType> findAll() {
		return getHibernateTemplate().find(
				"from BetingelseType order by betingelseNavn");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	public List<BetingelseType> findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List getComboValues(final Object param) {
		return findAllByFranchise((Integer) param);
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		BetingelseType betingelseType;

		while (objectIt.hasNext()) {
			betingelseType = (BetingelseType) objectIt.next();
			removeBetingelseType(betingelseType.getBetingelseTypeId());
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
		BetingelseType betingelseType;

		while (objectIt.hasNext()) {
			betingelseType = (BetingelseType) objectIt.next();
			saveBetingelseType(betingelseType);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeBetingelseType(((BetingelseType) object).getBetingelseTypeId());

	}

	/**
	 * Finner alle betingelsetyper tilhørende franchiseruppe
	 * 
	 * @param integer
	 * @return alle betingelsetyper tilhørende franchiseruppe
	 */
	@SuppressWarnings("unchecked")
	private List<BetingelseType> findAllByFranchise(final Integer integer) {
		return (List<BetingelseType>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(BetingelseType.class)
								.addOrder(Order.asc("betingelseTypeKode"))
								.createCriteria("betingelseGruppe").add(
										Restrictions
												.like("fakturerMedFranchise",
														integer)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findAllFranchise()
	 */
	public List<BetingelseType> findAllFranchise() {
		return findAllByFranchise(Integer.valueOf(1));
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findAllOthers()
	 */
	public List findAllOthers() {
		return findAllByFranchise(Integer.valueOf(0));
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findByKode(java.lang.String)
	 */
	public BetingelseType findByKode(final String TypeKode) {
		return (BetingelseType) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final List list = session.createCriteria(
								BetingelseType.class).add(
								Restrictions.ilike("betingelseTypeKode",
										TypeKode)).list();
						if (list == null || list.size() != 1) {
							return null;
						}
						return list.get(0);
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findByGroupName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<BetingelseType> findByGroupName(final String groupname) {
		return (List<BetingelseType>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(BetingelseType.class)
								.addOrder(Order.asc("betingelseNavn"))
								.createCriteria("betingelseGruppe").add(
										Restrictions.ilike(
												"betingelseGruppeNavn",
												groupname)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findByGroupNameAndKjede(java.lang.String,
	 *      no.ica.fraf.model.Rik2KjedeV)
	 */
	@SuppressWarnings("unchecked")
	public List<BetingelseType> findByGroupNameAndKjede(final String groupName,
			final Chain chain) {
		return (List<BetingelseType>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(BetingelseType.class)
								.add(Restrictions.eq("chain", chain))
								.addOrder(Order.asc("betingelseNavn"))
								.createCriteria("betingelseGruppe").add(
										Restrictions.ilike(
												"betingelseGruppeNavn",
												groupName)).list();
					}

				});
	}

	/**
	 * Finner basert på objekt
	 * 
	 * @param object
	 * @return betingelser
	 */
	public List<BetingelseType> findByObject(BetingelseType object) {
		return this.findByExampleLike(object);
	}

	/**
	 * Fjerner betingelse
	 * 
	 * @param object
	 */
	public void removeObject(BetingelseType object) {
		this.removeBetingelseType(object.getBetingelseTypeId());

	}

	/**
	 * Oppdaterer objekt
	 * 
	 * @param object
	 */
	public void refreshObject(final BetingelseType object) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				session.refresh(object);
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.BetingelseTypeDAO#findByKodeKjede(java.lang.Integer,
	 *      java.lang.String, no.ica.fraf.model.Rik2KjedeV)
	 */
	@SuppressWarnings("unchecked")
	public List<BetingelseType> findByKodeKjede(final Integer id,
			final String kode, final Chain chain) {
		return (List<BetingelseType>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session.createCriteria(
								BetingelseType.class).add(
								Restrictions.eq("betingelseTypeKode", kode));
						if (id != null) {
							crit.add(Restrictions.ne("betingelseTypeId", id));
						}
						if (chain == null) {
							crit.add(Restrictions.isNull("chain"));
						} else {
							crit.add(Restrictions.eq("chain", chain));
						}
						return crit.list();
					}

				});
	}

	@SuppressWarnings("unchecked")
	public List<BetingelseType> findByKodeKjedeFilial(final Integer id,
			final String kode, final Chain chain, final Integer forFilial) {
		return (List<BetingelseType>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session.createCriteria(
								BetingelseType.class).add(
								Restrictions.eq("betingelseTypeKode", kode));
						if (id != null) {
							crit.add(Restrictions.ne("betingelseTypeId", id));
						}
						if (chain == null) {
							crit.add(Restrictions.isNull("chain"));
						} else {
							crit.add(Restrictions.eq("chain", chain));
						}

						crit.add(Restrictions.sqlRestriction(
								"nvl(for_filial,0)=?", forFilial,
								Hibernate.INTEGER));
						return crit.list();
					}

				});
	}

	

	@SuppressWarnings("unchecked")
	public List<BetingelseType> findAllAktiv() {
		return (List<BetingelseType>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {
						return session.createCriteria(BetingelseType.class)
								.add(
										Restrictions.or(Restrictions
												.isNull("inaktiv"),
												Restrictions.eq("inaktiv", 0)))
								.addOrder(Order.asc("betingelseTypeKode"))
								.list();
					}

				});
	}

}
