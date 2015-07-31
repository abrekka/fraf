package no.ica.elfa.dao.hibernate;

import java.util.Collection;
import java.util.List;

import no.ica.elfa.dao.InvoiceItemVDAO;
import no.ica.elfa.model.InvoiceItemV;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO mot view INVOICE_ITEM_V
 * 
 * @author abr99
 * 
 */
public class InvoiceItemVDAOHibernate extends
		BaseDAOHibernate<InvoiceItemV> implements InvoiceItemVDAO {
	/**
	 * 
	 */
	public InvoiceItemVDAOHibernate() {
		super(InvoiceItemV.class);
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceItemVDAO#findByBatchId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public final List<InvoiceItemV> findByBatchId(final Integer batchId) {
		return (List<InvoiceItemV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(InvoiceItemV.class).add(
								Restrictions.eq("batchId", batchId)).addOrder(
								Order.asc("invoiceId")).list();
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceItemVDAO#findByInvoiceNrs(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	public final List<InvoiceItemV> findByInvoiceNrs(final Collection invoiceNumbers) {
		return (List<InvoiceItemV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(InvoiceItemV.class).add(
								Restrictions.in("invoiceNr", invoiceNumbers))
								.addOrder(Order.asc("invoiceNr")).list();
					}

				});
	}

}
