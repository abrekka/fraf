package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.InvoiceItemDAO;
import no.ica.elfa.model.InvoiceItem;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for invoiceitem for Hibernate
 * 
 * @author abr99
 * 
 */
public class InvoiceItemDAOHibernate extends BaseDAOHibernate<InvoiceItem>
		implements InvoiceItemDAO {
	/**
	 * 
	 */
	public InvoiceItemDAOHibernate() {
		super(InvoiceItem.class);
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceItemDAO#findByBatchId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public final List<InvoiceItem> findByBatchId(final Integer batchId) {
		return (List<InvoiceItem>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(InvoiceItem.class)
								.createCriteria("invoice").addOrder(
										Order.asc("invoiceId")).createCriteria(
										"batch").add(
										Restrictions.like("batchId", batchId))
								.list();
					}

				});
	}

}
