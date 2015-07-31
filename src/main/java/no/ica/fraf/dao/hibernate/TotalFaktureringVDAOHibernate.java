package no.ica.fraf.dao.hibernate;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import no.ica.fraf.dao.ReportDAO;
import no.ica.fraf.dao.view.TotalFaktureringVDAO;
import no.ica.fraf.enums.FieldTypeEnum;
import no.ica.fraf.gui.model.TableSorter.Directive;
import no.ica.fraf.model.TotalFaktureringV;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for view TotalFaktureringV for Hibernate
 * 
 * @author abr99
 * 
 */
public class TotalFaktureringVDAOHibernate extends BaseDAOHibernate<TotalFaktureringV> implements
		ReportDAO, TotalFaktureringVDAO {
	public TotalFaktureringVDAOHibernate() {
		super(TotalFaktureringV.class);
	}
	/**
	 * 
	 */
	private static final String AND_STRING = " and ";
	/**
	 * 
	 */
	private static final String FORMAT_STRING = "%02d";

	/**
	 * @see no.ica.fraf.dao.ReportDAO#getListData()
	 */
	public List getListData() {
		return getHibernateTemplate().find("from TotalFaktureringV");
	}

	/**
	 * @see no.ica.fraf.dao.view.TotalFaktureringVDAO#findByFilter(no.ica.fraf.model.TotalFaktureringV)
	 */
	@SuppressWarnings("unchecked")
	public List<TotalFaktureringV> findByFilter(
			final TotalFaktureringV totalFaktureringV,final Integer fromPeriode,final Integer toPeriode) {
		return (List<TotalFaktureringV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria=getFindByExampleLikeCriteria(totalFaktureringV);
						
						
						criteria.addOrder(Order.asc("aar")).addOrder(
								Order.asc("fraPeriode")).addOrder(
								Order.asc("tilPeriode")).addOrder(
								Order.asc("avdnr"));
						
						criteria.add(Restrictions.between("fraPeriode", fromPeriode, toPeriode)).add(Restrictions.between("tilPeriode", fromPeriode, toPeriode));

						return criteria.list();
					}

				});
	}
	
	public List<TotalFaktureringV> findByFilter(
			final TotalFaktureringV totalFaktureringV) {
		return (List<TotalFaktureringV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria=getFindByExampleLikeCriteria(totalFaktureringV);
						
						
						criteria.addOrder(Order.asc("aar")).addOrder(
								Order.asc("fraPeriode")).addOrder(
								Order.asc("tilPeriode")).addOrder(
								Order.asc("avdnr"));

						return criteria.list();
					}

				});
	}

	/**
	 * Lager kriterie
	 * 
	 * @param totalFaktureringV
	 * @param session
	 * @return kriterie
	 */
	Criteria createCriteria(final TotalFaktureringV totalFaktureringV,
			final Session session) {
		final Criteria crit = session.createCriteria(TotalFaktureringV.class);

		/*
		 * if (totalFaktureringV.getAar() != null) {
		 * crit.add(Restrictions.like("aar", totalFaktureringV.getAar())); }
		 */

		if (totalFaktureringV.getAar() != null
				&& totalFaktureringV.getTilAar() != null) {

			final StringBuffer sqlStringBuffer = new StringBuffer(
					"to_number(aar||trim(to_char(fra_periode,'00'))) between ")
					.append(totalFaktureringV.getAar())
					.append(
							String.format(FORMAT_STRING, totalFaktureringV
									.getFraPeriode()))
					.append(AND_STRING)
					.append(totalFaktureringV.getTilAar())
					.append(
							String.format(FORMAT_STRING, totalFaktureringV
									.getTilPeriode()))
					.append(AND_STRING)
					.append(
							"to_number(aar||trim(to_char(til_periode,'00'))) between ")
					.append(totalFaktureringV.getAar()).append(
							String.format(FORMAT_STRING, totalFaktureringV
									.getFraPeriode())).append(AND_STRING).append(
							totalFaktureringV.getTilAar()).append(
							String.format(FORMAT_STRING, totalFaktureringV
									.getTilPeriode()));

			crit.add(Restrictions.sqlRestriction(sqlStringBuffer.toString()));
		}

		/*
		 * if (totalFaktureringV.getFraPeriode() != null &&
		 * totalFaktureringV.getTilPeriode() != null) {
		 * crit.add(Restrictions.between("fraPeriode", totalFaktureringV
		 * .getFraPeriode(), totalFaktureringV.getTilPeriode())); }
		 * 
		 * if (totalFaktureringV.getFraPeriode() != null &&
		 * totalFaktureringV.getTilPeriode() != null) {
		 * crit.add(Restrictions.between("tilPeriode", totalFaktureringV
		 * .getFraPeriode(), totalFaktureringV.getTilPeriode())); }
		 */

		if (totalFaktureringV.getLinjeBeskrivelse() != null) {
			crit.add(Restrictions.ilike("linjeBeskrivelse", totalFaktureringV
					.getLinjeBeskrivelse(), MatchMode.START));
		}
		if (totalFaktureringV.getBetingelseTypeKode() != null) {
			crit.add(Restrictions.ilike("betingelseTypeKode", totalFaktureringV
					.getBetingelseTypeKode(), MatchMode.START));
		}
		
		if (totalFaktureringV.getBetingelseGruppeId() != null) {
			crit.add(Restrictions.like("betingelseGruppeId", totalFaktureringV
					.getBetingelseGruppeId()));
		}

		if (totalFaktureringV.getKjedeNavn() != null) {
			crit.add(Restrictions.ilike("kjedeNavn", totalFaktureringV
					.getKjedeNavn()));
		}

		if (totalFaktureringV.getRegionNavn() != null) {
			crit.add(Restrictions.ilike("regionNavn", totalFaktureringV
					.getRegionNavn()));
		}

		if (totalFaktureringV.getSalgssjef() != null) {
			crit.add(Restrictions.ilike("salgssjef", totalFaktureringV
					.getSalgssjef()));
		}

		if (totalFaktureringV.getSelskapNavn() != null) {
			crit.add(Restrictions.ilike("selskapNavn", totalFaktureringV
					.getSelskapNavn()));
		}

		if (totalFaktureringV.getAvdnr() != null
				&& totalFaktureringV.getAvdNrTil() != null) {
			crit.add(Restrictions.between("avdnr", totalFaktureringV
					.getAvdnr(), totalFaktureringV.getAvdNrTil()));
		}
		
		if (totalFaktureringV.getFakturaNr() != null) {
			crit.add(Restrictions.ilike("fakturaNr", totalFaktureringV
					.getFakturaNr()));
		}

		return crit;
	}

	/**
	 * Finner alle basert på filter og returnerer side for dide
	 * 
	 * @param startIndex
	 * @param fetchSize
	 * @param directives
	 * @param filter
	 * @return alle basert på filter for gjeldende side
	 */
	public Collection findAllPaged(final int startIndex, final int fetchSize,
			final List<Directive> directives, final TotalFaktureringV filter) {
		return (Collection) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {

						Criteria criteria = createCriteria(filter, session);

						criteria = addOrder(criteria, directives);

						criteria.setFetchSize(fetchSize);
						criteria.setMaxResults(fetchSize);
						criteria.setFirstResult(startIndex);
						try {
							return new LinkedHashSet<TotalFaktureringV>(
									criteria.list());
						} catch (ObjectNotFoundException e) {
							return null;
						}
					}

				});
	}

	/**
	 * Legger til sortering
	 * 
	 * @param criteria
	 * @param directives
	 * @return kriterie med sortering
	 */
	Criteria addOrder(final Criteria criteria, final List<Directive> directives) {
		if (directives == null || directives.size() == 0) {
			criteria.addOrder(Order.asc("avdnr"));
		} else {

			for (Directive directive : directives) {
				switch (directive.getDirection()) {
				case ASCENDING:
					criteria.addOrder(Order.asc(TotalFaktureringV
							.getColumndefName(directive.getColumn())));
					break;
				case DESCENDING:
					criteria.addOrder(Order.desc(TotalFaktureringV
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
	 * @see no.ica.fraf.util.ExcelListable#getNextList(java.lang.Object[], int,
	 *      int)
	 */
	@SuppressWarnings("unchecked")
	public Collection getNextList(Object[] filter, int currentIndex,
			int fetchSize) {
		return findAllPaged(currentIndex, fetchSize,
				(List<Directive>) filter[0], (TotalFaktureringV) filter[1]);
	}

	/**
	 * @see no.ica.fraf.util.ExcelListable#getCount(java.lang.Object[])
	 */
	public Integer getCount(final Object[] filter) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final TotalFaktureringV totalFaktureringV = (TotalFaktureringV) filter[1];
						StringBuffer queryString = new StringBuffer(
								"select count(*) from TotalFaktureringV as totalFaktureringV");
						queryString = addFilter(queryString, "aar",
								FieldTypeEnum.FIELD_TYPE_NUMBER,
								totalFaktureringV.getAar());

						if (totalFaktureringV.getFraPeriode() != null
								&& totalFaktureringV.getTilPeriode() != null) {
							if (queryString.indexOf("where") >= 0) {
								queryString.append(AND_STRING);
							} else {
								queryString.append(" where ");
							}
							queryString
									.append(
											"totalFaktureringV.fraPeriode between ")
									.append(totalFaktureringV.getFraPeriode())
									.append(AND_STRING)
									.append(totalFaktureringV.getTilPeriode())
									.append(AND_STRING)
									.append(
											"totalFaktureringV.tilPeriode between ")
									.append(totalFaktureringV.getFraPeriode())
									.append(AND_STRING).append(
											totalFaktureringV.getTilPeriode());
						}
						queryString = addFilter(queryString,
								"linjeBeskrivelse",
								FieldTypeEnum.FIELD_TYPE_STRING,
								totalFaktureringV.getLinjeBeskrivelse());
						queryString = addFilter(queryString, "kjedeNavn",
								FieldTypeEnum.FIELD_TYPE_STRING,
								totalFaktureringV.getKjedeNavn());
						queryString = addFilter(queryString, "regionNavn",
								FieldTypeEnum.FIELD_TYPE_STRING,
								totalFaktureringV.getRegionNavn());
						queryString = addFilter(queryString, "salgssjef",
								FieldTypeEnum.FIELD_TYPE_STRING,
								totalFaktureringV.getSalgssjef());

						final Query query = session.createQuery(queryString
								.toString());
						Object result = query.iterate().next();
						if(result instanceof Long){
							return Integer.valueOf(((Long)result).intValue());
						}
						return result;

					}

				});
	}

	/**
	 * Legger til gilter
	 * 
	 * @param queryBuffer
	 * @param columnName
	 * @param fieldType
	 * @param value
	 * @return sql med filter
	 */
	StringBuffer addFilter(final StringBuffer queryBuffer,
			final String columnName, final FieldTypeEnum fieldType,
			final Object value) {
		if (columnName != null && columnName.length() != 0 && value != null) {
			if (queryBuffer.indexOf("where") >= 0) {
				queryBuffer.append(AND_STRING);
			} else {
				queryBuffer.append(" where ");
			}

			switch (fieldType) {
			case FIELD_TYPE_STRING:
				queryBuffer.append("upper(totalFaktureringV.").append(
						columnName).append(") like '").append(
						((String) value).toUpperCase().trim()).append("%'");
				break;
			case FIELD_TYPE_NUMBER:
				queryBuffer.append(" totalFaktureringV.").append(columnName)
						.append(" = ").append(value);
				break;
			case FIELD_TYPE_DATE:
			case FIELD_TYPE_YES_NO:
				break;
			default:
				break;
			}
		}
		return queryBuffer;
	}

}
