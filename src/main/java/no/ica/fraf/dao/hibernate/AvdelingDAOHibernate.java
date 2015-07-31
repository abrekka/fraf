package no.ica.fraf.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvdelingDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingDAOHibernate extends BaseDAOHibernate<Avdeling> implements
		AvdelingDAO {
	public AvdelingDAOHibernate() {
		super(Avdeling.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#getAvdeling(java.lang.Integer)
	 */
	public Avdeling getAvdeling(final Integer avdelingId) {
		final Avdeling avdeling = (Avdeling) getHibernateTemplate().get(
				Avdeling.class, avdelingId);

		if (avdeling == null) {
			throw new ObjectRetrievalFailureException(Avdeling.class,
					avdelingId);
		}

		return avdeling;
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#saveAvdeling(no.ica.fraf.model.Avdeling)
	 */
	public void saveAvdeling(final Avdeling avdeling) {
		try {
			getHibernateTemplate().saveOrUpdate(avdeling);
		} catch (DataAccessException e) {
			getHibernateTemplate().flush();
			throw e;

		}

	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#removeAvdeling(java.lang.Integer)
	 */
	public void removeAvdeling(final Integer avdelingId) {
		getHibernateTemplate().delete(getAvdeling(avdelingId));
	}
	public void removeAvdeling(final Avdeling avdeling) {
		getHibernateTemplate().delete(avdeling);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#findAll()
	 */
	public List findAll() {
		return getHibernateTemplate().find("from Avdeling");
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#loadLazy(no.ica.fraf.model.Avdeling,
	 *      no.ica.fraf.enums.LazyLoadAvdelingEnum[])
	 */
	public void loadLazy(final Avdeling avdeling,
			final LazyLoadAvdelingEnum[] lazyEnums) {
		if (avdeling == null || avdeling.getAvdelingId() == null) {
			return;
		}
		
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.load(avdeling, avdeling.getAvdelingId());

				
				
				List<Object> list;
				Set<?> set;

				for (LazyLoadAvdelingEnum lazyEnum : lazyEnums) {
					switch (lazyEnum) {

					case LOAD_OWNERSHIP:
						set = avdeling.getEierforholds();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_INVOICE:
						set = avdeling.getFakturas();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_CONTRACT:
						set = avdeling.getAvdelingKontrakts();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_CONDITION:
						set = avdeling.getAvdelingBetingelses();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_ADENDUM:
						set = avdeling.getAdendums();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_OTHER_CONTRACT:
						set = avdeling.getAnnenKontrakts();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_SECURITY:
						set = avdeling.getSikkerhets();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_LOG:
						set = avdeling.getAvdelingLoggs();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_BUDGET:
						set = avdeling.getAvdelingOmsetnings();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_MIRROR:
						set = avdeling.getSpeiletBetingelses();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_MANGLER:
						
						set = avdeling.getAvdelingMangels();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					case LOAD_GUARANTEE:
						set = avdeling.getGarantier();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					default:
						break;
					}
				}

				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#findFranchiseConditions(no.ica.fraf.model.Avdeling)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingBetingelse> findFranchiseConditions(final Avdeling avdeling) {
		return (List<AvdelingBetingelse>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				loadLazy(
						avdeling,
						new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_CONDITION });
				final Set<AvdelingBetingelse> set = avdeling
						.getAvdelingBetingelses();
				final List<AvdelingBetingelse> returnList = new ArrayList<AvdelingBetingelse>();
				final Integer integer1 = Integer.valueOf(1);

				for (AvdelingBetingelse avdelingBetingelse : set) {
					if (avdelingBetingelse != null
							&& avdelingBetingelse.getBetingelseType() != null
							&& avdelingBetingelse.getBetingelseType()
									.getBetingelseGruppe()
									.getFakturerMedFranchise().equals(integer1)) {
						returnList.add(avdelingBetingelse);
					}
				}

				return returnList;
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#findByAvdnr(java.lang.Integer)
	 */
	public Avdeling findByAvdnr(final Integer avdnr) {
		return (Avdeling) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final List list = session
								.createCriteria(Avdeling.class).add(
										Restrictions.like("avdnr", avdnr))
								.list();

						if (list == null || list.size() != 1) {
							return null;
						}
						return list.get(0);
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#findAllId(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findAllId(final Integer fromDep, final Integer toDep) {
		return (List<Integer>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery("select avdnr from Avdeling where avdnr between :fromDep and :toDep order by avdnr");
						Integer from;
						Integer to;
						if (fromDep == null) {
							from = Integer.valueOf(1000);
						} else {
							from = fromDep;
						}

						if (toDep == null) {
							to = Integer.valueOf(9999);
						} else {
							to = toDep;
						}
						query.setParameter("fromDep", from);
						query.setParameter("toDep", to);
						return query.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingDAO#refresh(no.ica.fraf.model.Avdeling)
	 */
	public void refresh(final Avdeling avdeling) {
		if (avdeling.getAvdelingId() != null) {
			getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException {
					session.flush();
					session.load(avdeling, avdeling.getAvdelingId());
					return null;
				}

			});
		}
		
	}

}
