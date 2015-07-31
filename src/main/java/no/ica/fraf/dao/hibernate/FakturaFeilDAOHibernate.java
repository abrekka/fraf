package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.FakturaFeilDAO;
import no.ica.fraf.model.FakturaFeil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av FakturaFeilDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FakturaFeilDAOHibernate extends BaseDAOHibernate<FakturaFeil> implements
		FakturaFeilDAO {
	public FakturaFeilDAOHibernate() {
		super(FakturaFeil.class);
	}

	/**
	 * @see no.ica.fraf.dao.FakturaFeilDAO#findByBuntId(java.lang.Integer)
	 */
	public List findByBuntId(final Integer buntId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(FakturaFeil.class)
						.createCriteria("faktura").createCriteria("bunt").add(
								Restrictions.like("buntId", buntId)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaFeilDAO#getCountByBuntId(java.lang.Integer)
	 */
	public Integer getCountByBuntId(final Integer buntId) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						if (buntId != null) {
							final StringBuffer queryString = new StringBuffer(
									"select count(*) from FakturaFeil as fakturaFeil,Faktura as faktura,Bunt as bunt where fakturaFeil.faktura = faktura and faktura.bunt = bunt and bunt.buntId = ?");
							final Query query = session.createQuery(queryString
									.toString());
							query.setInteger(0, buntId.intValue());
							Object result = query.iterate().next();
							if (result instanceof Long) {
								return Integer.valueOf(((Long) result)
										.intValue());
							}
							return result;
						}
						return null;

					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaFeilDAO#getCountByFakturaId(java.lang.Integer)
	 */
	public Integer getCountByFakturaId(final Integer invoiceId) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Object returnValue = null;
						if (invoiceId != null) {
							final StringBuffer queryString = new StringBuffer(
									"select count(*) from FakturaFeil as fakturaFeil,Faktura as faktura where fakturaFeil.faktura = faktura and faktura.fakturaId = ?");
							final Query query = session.createQuery(queryString
									.toString());
							query.setInteger(0, invoiceId.intValue());
							returnValue = query.iterate().next();
						}
						if (returnValue instanceof Long) {
							return Integer.valueOf(((Long) returnValue)
									.intValue());
						}
						return returnValue;

					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaFeilDAO#findByFakturaId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaFeil> findByFakturaId(final Integer invoiceId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(FakturaFeil.class).addOrder(
						Order.asc("fakturaFeilId")).createCriteria("faktura")
						.add(Restrictions.like("fakturaId", invoiceId)).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.FeilDAO#findById(java.lang.Integer)
	 */
	public List<?> findById(Integer id) {
		return findByFakturaId(id);
	}

}
