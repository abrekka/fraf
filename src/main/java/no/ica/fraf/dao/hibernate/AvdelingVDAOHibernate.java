package no.ica.fraf.dao.hibernate;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.AvdelingVColumnEnum;
import no.ica.fraf.enums.FieldTypeEnum;
import no.ica.fraf.gui.model.TableSorter.Directive;
import no.ica.fraf.model.AvdelingV;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvdelingVDAO
 * 
 * @author abr99
 * 
 */
public class AvdelingVDAOHibernate extends
BaseDAOHibernate<AvdelingV> implements AvdelingVDAO {
	
	/**
	 * 
	 */
	
	public AvdelingVDAOHibernate() {
		super(AvdelingV.class);
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#getAvdelingV(java.lang.Integer)
	 */
	public AvdelingV getAvdelingV(final Integer avdelingVId) {
		final AvdelingV avdelingV = (AvdelingV) getHibernateTemplate().get(
				AvdelingV.class, avdelingVId);

		return avdelingV;
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#findAll(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingV> findAll(final Integer fromDep, final Integer toDep) {
		return (List<AvdelingV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Integer from;
						Integer to;

						Criteria crit = session.createCriteria(AvdelingV.class);

						if (fromDep != null || toDep != null) {
							if (fromDep != null) {
								from = fromDep;
							} else {
								from = Integer.valueOf(1000);
							}

							if (toDep != null) {
								to = toDep;
							} else {
								to = Integer.valueOf(9999);
							}
							crit.add(Restrictions.between("avdnr", from, to));

						}
						crit.addOrder(Order.asc("avdnr"));
						return crit.list();
					}

				});
	}

	public List findAllPaged(final int startIndex, final int fetchSize,
			final List<Directive> directives) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {

				Criteria criteria = session.createCriteria(AvdelingV.class);

				criteria = addOrder(criteria, directives);

				criteria.setFetchSize(fetchSize);
				criteria.setMaxResults(fetchSize);
				criteria.setFirstResult(startIndex);
				return criteria.list();
			}

		});
	}

	/**
	 * Legger til sorterting
	 * 
	 * @param criteria
	 * @param directives
	 * @return Criteria med sortering
	 */
	Criteria addOrder(final Criteria criteria, final List<Directive> directives) {
		if (directives == null || directives.size() == 0) {
			criteria.addOrder(Order.asc(AVDELING_NAVN));
		} else {

			for (Directive directive : directives) {
				switch (directive.getDirection()) {
				case ASCENDING:
					criteria.addOrder(Order.asc(AvdelingV
							.getColumndefName(directive.getColumn())));
					break;
				case DESCENDING:
					criteria.addOrder(Order.desc(AvdelingV
							.getColumndefName(directive.getColumn())));
					break;
				case NOT_SORTED:
					break;
				default:
					break;
				}
			}
		}

		return criteria;
	}

	DetachedCriteria addOrder(final DetachedCriteria criteria, final List<Directive> directives) {
		if (directives == null || directives.size() == 0) {
			criteria.addOrder(Order.asc(AVDELING_NAVN));
		} else {

			for (Directive directive : directives) {
				switch (directive.getDirection()) {
				case ASCENDING:
					criteria.addOrder(Order.asc(AvdelingV
							.getColumndefName(directive.getColumn())));
					break;
				case DESCENDING:
					criteria.addOrder(Order.desc(AvdelingV
							.getColumndefName(directive.getColumn())));
					break;
				case NOT_SORTED:
					break;
				default:
					break;
				}
			}
		}

		return criteria;
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#getCount()
	 */
	public Integer getCount() {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final StringBuffer queryString = new StringBuffer(
								"select count(*) from AvdelingV");
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
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#findAllSelskapRegnskap()
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAllSelskapRegnskap() {
		return getHibernateTemplate().find(
				"select distinct selskapRegnskap from AvdelingV");
	}

	/**
	 * Legger til parametre i filter
	 * 
	 * @param filterValue
	 * @param filterName
	 * @param queryBuffer
	 * @param fieldType
	 * @return filter
	 */
	public StringBuffer appendFilter(final Object filterValue,
			final String filterName, final StringBuffer queryBuffer,
			final FieldTypeEnum fieldType) {
		if (filterValue != null) {
			if (queryBuffer.indexOf("where") >= 0) {
				queryBuffer.append(" and ");
			} else {
				queryBuffer.append(" where ");
			}

			switch (fieldType) {
			case FIELD_TYPE_STRING:
				queryBuffer.append("upper(avdelingV.").append(filterName)
						.append(") like :").append(filterName);
				break;
			case FIELD_TYPE_NUMBER:
				queryBuffer.append("avdelingV.").append(filterName).append(
						" like :").append(filterName);
				break;
			case FIELD_TYPE_DATE:
				queryBuffer.append("to_char(avdelingV.").append(filterName)
						.append(",'YYYYMMDD') = to_char(:").append(filterName)
						.append(",'YYYYMMDD')");
				break;
			case FIELD_TYPE_YES_NO:
				queryBuffer.append("avdelingV.").append(filterName).append(
						" like :").append(filterName);
				break;
			default:
				break;
			}

		}
		return queryBuffer;
	}

	/**
	 * Lager query basert på filter
	 * 
	 * @param filter
	 * @param query
	 * @return query
	 */
	String createQueryString(final AvdelingV filter, final StringBuffer query) {
		String returnString = null;
		StringBuffer queryString = query;
		if (filter != null) {
			final List<AvdelingVColumnEnum> columns = AvdelingVColumnEnum
					.getAllEnums();

			Object columnValue = null;

			for (AvdelingVColumnEnum avdelingVColumnEnum : columns) {
				columnValue = getColumnValue(avdelingVColumnEnum, filter);
				queryString = appendFilter(columnValue, avdelingVColumnEnum
						.getColumnName(), queryString, avdelingVColumnEnum
						.getColumnType());

			}

			returnString = queryString.toString();
		}
		return returnString;
	}

	/**
	 * Henter ut verdi fra kolonne
	 * 
	 * @param columnEnum
	 * @param filter
	 * @return verdi
	 */
	private Object getColumnValue(final AvdelingVColumnEnum columnEnum,
			final AvdelingV filter) {
		Object object = null;
		try {
			final Method method = filter.getClass().getMethod(
					columnEnum.getMethodName(), (Class[]) null);

			object = method.invoke(filter, (Object[]) null);
			if (object != null
					&& columnEnum.getColumnType() == FieldTypeEnum.FIELD_TYPE_STRING) {
				object = ((String) object).toUpperCase();
			}

		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Feil i getColumnValue", e);
		}
		return object;
	}

	/**
	 * Setter parameter i query
	 * 
	 * @param filter
	 * @param columnEnum
	 * @param query
	 */
	void setQueryParameter(final AvdelingV filter,
			final AvdelingVColumnEnum columnEnum, final Query query) {
		final Object object = getColumnValue(columnEnum, filter);

		if (object != null) {
			query.setParameter(columnEnum.getColumnName(), object);
		}
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#getCountByFilter(no.ica.fraf.model.AvdelingV)
	 */
	public Integer getCountByFilter(final AvdelingV filter) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria=getFindByExampleLikeCriteria(filter);

						criteria.setProjection(Projections.rowCount()); 
						 

						Object result = criteria.list().iterator().next();
						if (result instanceof Long) {
							return Integer.valueOf(((Long) result).intValue());
						}
						return result;

					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#findAllPaged(int, int,
	 *      java.util.List, no.ica.fraf.model.AvdelingV)
	 */
	public Collection findAllPaged(final int startIndex, final int fetchSize,
			final List<Directive> directives, final AvdelingV filter) {
		return (Collection) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria=getFindByExampleLikeCriteria(filter);
						criteria = addOrder(criteria, directives);
						

						criteria.setFetchSize(fetchSize);
						criteria.setMaxResults(fetchSize);
						criteria.setFirstResult(startIndex);
						try {
							return new LinkedHashSet<AvdelingV>(criteria.list());
						} catch (ObjectNotFoundException e) {
							return null;
						}
						/*Criteria criteria = session
								.createCriteria(AvdelingV.class);

						if (filter != null) {
							if (filter.getAvdnr() != null) {
								criteria.add(Restrictions.like("avdnr", filter
										.getAvdnr()));
							}

							if (filter.getAvdelingNavn() != null) {
								criteria.add(Restrictions.ilike(AVDELING_NAVN,
										filter.getAvdelingNavn()));
							}

							if (filter.getJuridiskNavn() != null) {
								criteria.add(Restrictions.ilike("juridiskNavn",
										filter.getJuridiskNavn()));
							}

							if (filter.getKontraktFraDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(KONTRAKT_FRA_DATO)=?", filter
												.getKontraktFraDato(),
										Hibernate.DATE));

							}

							if (filter.getKontraktTilDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(KONTRAKT_TIL_DATO)=?", filter
												.getKontraktTilDato(),
										Hibernate.DATE));

							}

							if (filter.getOpprettetDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(OPPRETTET_DATO)=?", filter
												.getOpprettetDato(),
										Hibernate.DATE));

							}

							if (filter.getEndretDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(ENDRET_DATO)=?", filter
												.getEndretDato(),
										Hibernate.DATE));

							}

							if (filter.getStatus() != null) {
								criteria.add(Restrictions.eq("status", filter
										.getStatus()));
							}

							if (filter.getKontraktType() != null) {
								criteria.add(Restrictions.eq("kontraktType",
										filter.getKontraktType()));
							}

							if (filter.getRegion() != null) {
								criteria.add(Restrictions.eq("region", filter
										.getRegion()));
							}

							if (filter.getKjede() != null) {
								criteria.add(Restrictions.eq("kjede", filter
										.getKjede()));
							}

							if (filter.getBokfSelskap() != null) {
								criteria.add(Restrictions.eq("bokfSelskap",
										filter.getBokfSelskap()));
							}

							if (filter.getSelskapRegnskap() != null) {
								criteria.add(Restrictions.eq("selskapRegnskap",
										filter.getSelskapRegnskap()));
							}

							if (filter.getKontraktUtgaar() != null) {
								criteria.add(Restrictions.eq("kontraktUtgaar",
										filter.getKontraktUtgaar()));
							}
							if (filter.getSalgssjef() != null) {
								criteria.add(Restrictions.eq("salgssjef",
										filter.getSalgssjef()));
							}

							if (filter.getFornyelseTypeTxt() != null) {
								criteria.add(Restrictions.eq(
										"fornyelseTypeTxt", filter
												.getFornyelseTypeTxt()));
							}

							if (filter.getPib() != null) {
								criteria.add(Restrictions.eq("pib", filter
										.getPib()));
							}

							if (filter.getAnsvarlig() != null) {
								criteria.add(Restrictions.ilike("ansvarlig",
										filter.getAnsvarlig()));
							}

							if (filter.getFranchisetaker() != null) {
								criteria.add(Restrictions.ilike(
										"franchisetaker", filter
												.getFranchisetaker()));
							}
						}*/

						
					}

				});
	}

	/**
	 * @see no.ica.fraf.util.ExcelListable#getNextList(java.lang.Object[], int,
	 *      int)
	 */
	@SuppressWarnings("unchecked")
	public Collection getNextList(Object[] filter, int currentIndex,
			int fetchSize) {
		return findAllPaged(currentIndex, fetchSize,
				(List<Directive>) filter[0], (AvdelingV) filter[1]);
	}

	/**
	 * @see no.ica.fraf.util.ExcelListable#getCount(java.lang.Object[])
	 */
	public Integer getCount(final Object[] filter) {
		return getCountByFilter((AvdelingV) filter[1]);
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#findAll(java.util.List,
	 *      no.ica.fraf.model.AvdelingV)
	 */
	@SuppressWarnings("unchecked")
	public Collection<AvdelingV> findAll(final List<Directive> directives,
			final AvdelingV filter) {
		return (Collection<AvdelingV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria=getFindByExampleLikeCriteria(filter);
						criteria = addOrder(criteria, directives);
						/*Criteria criteria = session
								.createCriteria(AvdelingV.class);

						if (filter != null) {
							if (filter.getAvdnr() != null) {
								criteria.add(Restrictions.like("avdnr", filter
										.getAvdnr()));
							}

							if (filter.getAvdelingNavn() != null) {
								criteria.add(Restrictions.ilike(AVDELING_NAVN,
										filter.getAvdelingNavn()));
							}

							if (filter.getJuridiskNavn() != null) {
								criteria.add(Restrictions.ilike("juridiskNavn",
										filter.getJuridiskNavn()));
							}

							if (filter.getKontraktFraDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(KONTRAKT_FRA_DATO)=?", filter
												.getKontraktFraDato(),
										Hibernate.DATE));

							}

							if (filter.getKontraktTilDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(KONTRAKT_TIL_DATO)=?", filter
												.getKontraktTilDato(),
										Hibernate.DATE));

							}

							if (filter.getOpprettetDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(OPPRETTET_DATO)=?", filter
												.getOpprettetDato(),
										Hibernate.DATE));

							}

							if (filter.getEndretDato() != null) {
								criteria.add(Expression.sql(
										"TRUNC(ENDRET_DATO)=?", filter
												.getEndretDato(),
										Hibernate.DATE));

							}

							if (filter.getStatus() != null) {
								criteria.add(Restrictions.eq("status", filter
										.getStatus()));
							}

							if (filter.getKontraktType() != null) {
								criteria.add(Restrictions.eq("kontraktType",
										filter.getKontraktType()));
							}

							if (filter.getRegion() != null) {
								criteria.add(Restrictions.eq("region", filter
										.getRegion()));
							}

							if (filter.getKjede() != null) {
								criteria.add(Restrictions.eq("kjede", filter
										.getKjede()));
							}

							if (filter.getBokfSelskap() != null) {
								criteria.add(Restrictions.eq("bokfSelskap",
										filter.getBokfSelskap()));
							}

							if (filter.getSelskapRegnskap() != null) {
								criteria.add(Restrictions.ilike(
										"selskapRegnskap", filter
												.getSelskapRegnskap()));
							}

							if (filter.getKontraktUtgaar() != null) {
								criteria.add(Restrictions.eq("kontraktUtgaar",
										filter.getKontraktUtgaar()));
							}
							if (filter.getSalgssjef() != null) {
								criteria.add(Restrictions.eq("salgssjef",
										filter.getSalgssjef()));
							}

							if (filter.getFornyelseTypeTxt() != null) {
								criteria.add(Restrictions.eq(
										"fornyelseTypeTxt", filter
												.getFornyelseTypeTxt()));
							}

							if (filter.getPib() != null) {
								criteria.add(Restrictions.eq("pib", filter
										.getPib()));
							}

							if (filter.getAnsvarlig() != null) {
								criteria.add(Restrictions.ilike("ansvarlig",
										filter.getAnsvarlig()));
							}

							if (filter.getFranchisetaker() != null) {
								criteria.add(Restrictions.ilike(
										"franchisetaker", filter
												.getFranchisetaker()));
							}
							if (filter.getArchiveInfo() != null) {
								criteria.add(Restrictions.ilike("archiveInfo",
										filter.getArchiveInfo()));
							}
						}

						criteria = addOrder(criteria, directives);*/

						try {
							return new LinkedHashSet<AvdelingV>(criteria.list());
							//return new LinkedHashSet<AvdelingV>(criteria.list());
						} catch (ObjectNotFoundException e) {
							return null;
						}
					}

				});

	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingVDAO#findByAvdnr(java.lang.Integer)
	 */
	public AvdelingV findByAvdnr(final Integer avdnr) {
		return (AvdelingV) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<AvdelingV> list = session.createCriteria(
								AvdelingV.class).add(
								Restrictions.eq("avdnr", avdnr)).list();
						if (list != null && list.size() == 1) {
							return list.get(0);
						}
						return null;
					}

				});
	}

}
