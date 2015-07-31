package no.ica.fraf.dao.hibernate.fenistra;

import java.util.List;

import no.ica.fraf.dao.fenistra.MrKontiDAO;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.MrKontiOrg;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO mor MrKonti for Hibernate
 * 
 * @author abr99
 * 
 */
public class MrKontiDAOHibernate extends BaseDAOHibernate<MrKontiOrg> implements
		MrKontiDAO {
	public MrKontiDAOHibernate() {
		super(MrKontiOrg.class);
	}

	/**
	 * @see no.ica.fraf.dao.fenistra.MrKontiDAO#findAllExpenses()
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAllExpenses() {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(MrKontiOrg.class).add(
								Restrictions.in("kontogruppeId", new Object[] {
										6, 7 })).addOrder(Order.asc("kontoNr"))
								.list();
					}

				});
	}

}
