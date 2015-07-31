package no.ica.fraf.dao.hibernate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.AvregningBasisType;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvdelingOmsetningDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingOmsetningDAOHibernate extends
		BaseDAOHibernate<AvdelingOmsetning> implements AvdelingOmsetningDAO {
	public AvdelingOmsetningDAOHibernate() {
		super(AvdelingOmsetning.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#getAvdelingOmsetning(java.lang.Integer)
	 */
	public AvdelingOmsetning getAvdelingOmsetning(final Integer omsetningId) {
		final AvdelingOmsetning avdelingOmsetning = (AvdelingOmsetning) getHibernateTemplate()
				.get(AvdelingOmsetning.class, omsetningId);

		if (avdelingOmsetning == null) {
			throw new ObjectRetrievalFailureException(AvdelingOmsetning.class,
					omsetningId);
		}

		return avdelingOmsetning;
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#saveAvdelingOmsetning(no.ica.fraf.model.AvdelingOmsetning)
	 */
	public void saveAvdelingOmsetning(final AvdelingOmsetning avdelingOmsetning) {
		getHibernateTemplate().saveOrUpdate(avdelingOmsetning);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#removeAvdelingOmsetning(java.lang.Integer)
	 */
	public void removeAvdelingOmsetning(final Integer omsetningId) {
		getHibernateTemplate().delete(getAvdelingOmsetning(omsetningId));
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#findBudgetByDepartment(no.ica.fraf.model.Avdeling,
	 *      java.lang.Integer)
	 */
	public List<AvdelingOmsetning> findBudgetByDepartment(
			final Avdeling avdeling, final Integer year) {
		return findBudgetSoldByDepartment(avdeling, year, "BUD");
	}

	/**
	 * Finner omsetning og budsjett basert på avdeling år og type
	 * 
	 * @param avdeling
	 * @param year
	 * @param omsType
	 * @return omsetning og budsjett basert på avdeling år og type
	 */
	@SuppressWarnings("unchecked")
	private List<AvdelingOmsetning> findBudgetSoldByDepartment(
			final Avdeling avdeling, final Integer year, final String omsType) {
		return (List<AvdelingOmsetning>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						List list = null;
						if (avdeling != null) {
							list = session.createCriteria(
									AvdelingOmsetning.class).add(
									Restrictions.like("avdnr", avdeling
											.getAvdnr())).add(
									Restrictions.like("aar", year)).addOrder(
									Order.desc("periode")).createCriteria(
									"avregningBasisType").add(
									Restrictions.ilike(
											"avregningBasisTypeKode", omsType))
									.list();
						}
						return list;
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#findSoldByDepartment(no.ica.fraf.model.Avdeling,
	 *      java.lang.Integer)
	 */
	public List<AvdelingOmsetning> findSoldByDepartment(
			final Avdeling avdeling, final Integer year) {
		return findBudgetSoldByDepartment(avdeling, year, "OMS");
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#findBudgetSaleByDepartmentYearOrPeriode(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List findBudgetSaleByDepartmentYearOrPeriode(final Integer dep,
			final Integer year, final Integer periode,
			final String basisTypeKode) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {

				final Criteria criteria = session
						.createCriteria(AvdelingOmsetning.class);

				if (dep != null) {
					criteria.add(Restrictions.like("avdnr", dep));
				}

				if (year != null) {
					criteria.add(Restrictions.like("aar", year));
				}

				if (periode != null) {
					criteria.add(Restrictions.like("periode", periode));
				}

				criteria.createCriteria("avregningBasisType").add(
						Restrictions.ilike("avregningBasisTypeKode",
								basisTypeKode));

				return criteria.list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#findByBunt(java.lang.Integer)
	 */
	public List findByBunt(final Integer batchId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(AvdelingOmsetning.class)
						.createCriteria("innlesBunt").add(
								Restrictions.like("buntId", batchId)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#getAvregningBasisTypeByBatch(java.lang.Integer)
	 */
	public AvregningBasisType getAvregningBasisTypeByBatch(final Integer batchId) {
		return (AvregningBasisType) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final List list = session.createCriteria(
								AvdelingOmsetning.class).createCriteria(
								"innlesBunt").add(
								Restrictions.like("buntId", batchId))
								.setMaxResults(1).list();

						if (list != null && list.size() > 0) {
							final AvdelingOmsetning avdelingOmsetning = (AvdelingOmsetning) list
									.get(0);
							return avdelingOmsetning.getAvregningBasisType();
						}
						return null;
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#findAvdelingUtenBudsjett(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingKontrakt> findAvdelingUtenBudsjett(final Integer aar) {
		return (List<AvdelingKontrakt>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session
								.createQuery(
										"select avdelingKontrakt "
												+ "from Avdeling as avdeling,"
												+ "AvdelingKontrakt as avdelingKontrakt,"
												+ "AvregningBasisType as avregningBasisType, "
												+ "AvdelingV as avdelingV "
												+ "where avdelingV.avdelingId = avdeling.avdelingId and "
												+ " avdelingV.status <> 'Nedlagt' and "
												+ "avdelingKontrakt.avdeling = avdeling and "
												+ "avdelingKontrakt.avregningBasisType = avregningBasisType and "
												+ "avregningBasisType.avregningBasisTypeKode = :kode and "
												+ "(12 <> (select count(avdelingOmsetning.periode) "
												+ "from AvdelingOmsetning as avdelingOmsetning "
												+ "where avdelingOmsetning.avdeling = avdeling and "
												+ "avdelingOmsetning.aar = :aar and "
												+ "avdelingOmsetning.avregningBasisType = avregningBasisType  "
												+ "group by avdelingOmsetning.avdeling,avdelingOmsetning.aar) or "
												+ "not exists(select 1 "
												+ "from AvdelingOmsetning as avdelingOmsetning2 "
												+ "where avdelingOmsetning2.avdeling = avdeling and "
												+ "avdelingOmsetning2.aar = :aar and "
												+ "avdelingOmsetning2.avregningBasisType = avregningBasisType))")
								.setParameter("kode", "BUD").setParameter(
										"aar", aar).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningDAO#saveAvdelingOmsetningList(java.util.List)
	 */
	public void saveAvdelingOmsetningList(final List<AvdelingOmsetning> list) {
		if (list == null) {
			return;
		}

		for (AvdelingOmsetning avdelingOmsetning : list) {
			saveAvdelingOmsetning(avdelingOmsetning);
		}

	}

	public void removeAvdelingOmsetningForAvdeling(final Avdeling avdeling) {
		getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				List<AvdelingOmsetning> list = session.createCriteria(
						AvdelingOmsetning.class).add(
						Restrictions.eq("avdeling", avdeling)).list();
				if (list != null) {
					for (AvdelingOmsetning oms : list) {
						removeAvdelingOmsetning(oms.getAvdelingOmsetningId());
					}
				}
				return null;
			}

		});

	}

	public AvdelingOmsetning findByAvdelingAndPeriod(final Avdeling avdeling,
			final Integer year, final Integer period) {
		return (AvdelingOmsetning) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						List list = null;
						list = session
								.createCriteria(AvdelingOmsetning.class)
								.add(
										Restrictions.like("avdnr", avdeling
												.getAvdnr()))
								.add(Restrictions.like("aar", year))
								.add(Restrictions.eq("periode", period))
								.createCriteria("avregningBasisType")
								.add(
										Restrictions
												.ilike(
														"avregningBasisTypeKode",
														"OMS")).list();

						if (list != null && list.size() == 1) {
							return list.get(0);
						}
						return null;
					}

				});
	}

	@SuppressWarnings("unchecked")
	public final List<Avdeling> findByYearAndNotAvdnr(final Integer year,
			final List<Integer> avdNrList) {
		return (List<Avdeling>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, year);
						cal.set(Calendar.MONTH, 0);
						cal.set(Calendar.DAY_OF_MONTH, 1);
						cal.set(Calendar.HOUR_OF_DAY, cal
								.getActualMinimum(Calendar.HOUR_OF_DAY));
						cal.set(Calendar.MINUTE, cal
								.getMinimum(Calendar.MINUTE));
						System.out.println(cal.getTime());
						
						Criteria criteria= session.createCriteria(Avdeling.class);
						criteria = avdNrList!=null&&avdNrList.size()!=0?criteria.add(Expression.not(Expression.in("avdnr", avdNrList))):criteria;
						
						return criteria.createAlias("department","dep").
													createAlias("avdelingOmsetnings", "oms")
													//.add(Restrictions.or(Restrictions.isNotEmpty("dep.dtSlutt"), Expression.gt("dep.dtSlutt", cal.getTime())))
													.add(Expression.eq("oms.aar", year))
													.add(Restrictions.isNotNull("oms.belop"))
													.add(Restrictions.ne("oms.belop", BigDecimal.ZERO)).list();
						
						/*String sql = "select distinct avdeling from Avdeling avdeling,AvdelingOmsetning avdelingOmsetning,Rik2AvdV rik2AvdV "
								+ "		where avdeling=avdelingOmsetning.avdeling and "
								+ "				avdeling.rik2AvdV=rik2AvdV and "
								+ "				(rik2AvdV.dtSlutt is null or "
								+ "				rik2AvdV.dtSlutt>:yearStart) and "
								+ "				avdelingOmsetning.aar=:year and "
								+ "				avdelingOmsetning.belop is not null and "
								+ "				avdelingOmsetning.belop <> 0 and "
								+ "				avdeling.avdnr not in(:avdnrList)";*/
						/*String sql = "select distinct avdeling from Avdeling avdeling,AvdelingOmsetning avdelingOmsetning,Department department "
							+ "		where avdeling=avdelingOmsetning.avdeling and "
							+ "				avdeling.department=department and "
							+ "				(department.dtSlutt is null or "
							+ "				department.dtSlutt>:yearStart) and "
							+ "				avdelingOmsetning.aar=:year and "
							+ "				avdelingOmsetning.belop is not null and "
							+ "				avdelingOmsetning.belop <> 0 and "
							+ "				avdeling.avdnr not in(:avdnrList)";
						return session.createQuery(sql).setParameter("year",
								year).setParameterList("avdnrList", avdNrList)
								.setParameter("yearStart", cal.getTime())
								.list();*/
					}

				});
	}
	
	public AvdelingOmsetning findBudsjettByAvdelingAndPeriod(final Avdeling avdeling,
			final Integer year, final Integer period) {
		return (AvdelingOmsetning) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						List list = null;
						list = session
								.createCriteria(AvdelingOmsetning.class)
								.add(
										Restrictions.like("avdnr", avdeling
												.getAvdnr()))
								.add(Restrictions.like("aar", year))
								.add(Restrictions.eq("periode", period))
								.createCriteria("avregningBasisType")
								.add(
										Restrictions
												.ilike(
														"avregningBasisTypeKode",
														"BUD")).list();

						if (list != null && list.size() == 1) {
							return list.get(0);
						}
						return null;
					}

				});
	}
}
