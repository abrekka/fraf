package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.FakturaLinjeDAO;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.FakturaLinje;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av FakturaLinjeDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FakturaLinjeDAOHibernate extends BaseDAOHibernate<FakturaLinje> implements
		FakturaLinjeDAO {
	public FakturaLinjeDAOHibernate() {
		super(FakturaLinje.class);
	}

	/**
	 * @see no.ica.fraf.dao.FakturaLinjeDAO#findByFakturaId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaLinje> findByFakturaId(final Integer fakturaId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(FakturaLinje.class).addOrder(
						Order.asc("linjeBeskrivelse")).addOrder(
						Order.asc("periode")).createCriteria("faktura").add(
						Restrictions.like("fakturaId", fakturaId)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaLinjeDAO#findByBuntId(java.lang.Integer)
	 */
	public List findByBuntId(final Integer buntId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(FakturaLinje.class)
						.createCriteria("faktura").createCriteria("bunt").add(
								Restrictions.like("buntId", buntId)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaLinjeDAO#saveFakturaLinje(no.ica.fraf.model.FakturaLinje)
	 */
	public void saveFakturaLinje(final FakturaLinje fakturaLinje) {
		getHibernateTemplate().saveOrUpdate(fakturaLinje);
	}

	/**
	 * @see no.ica.fraf.dao.FakturaLinjeDAO#findGrunnlagByBuntId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaLinje> findGrunnlagByBuntId(final Integer buntId) {
		return (List<FakturaLinje>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(FakturaLinje.class)
								.createCriteria("faktura").add(
										Restrictions.eq("brukSomGrunnlag", 1))
								.addOrder(Order.asc("avdnr")).createCriteria(
										"bunt").add(
										Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaLinjeDAO#findByAvdelingBetingelse(no.ica.fraf.model.AvdelingBetingelse)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaLinje> findByAvdelingBetingelse(
			final AvdelingBetingelse betingelse) {
		return (List<FakturaLinje>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(FakturaLinje.class).add(
								Restrictions.eq("avdelingBetingelse",
										betingelse)).list();
					}

				});
	}

}
