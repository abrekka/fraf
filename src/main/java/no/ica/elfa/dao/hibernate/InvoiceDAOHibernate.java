package no.ica.elfa.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import no.ica.elfa.dao.InvoiceDAO;
import no.ica.elfa.model.Invoice;
import no.ica.elfa.model.InvoiceItem;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for faktura for Hibernate
 * 
 * @author abr99
 * 
 */
public class InvoiceDAOHibernate extends BaseDAOHibernate<Invoice>
		implements InvoiceDAO {
	/**
	 * Konstruktør
	 */
	public InvoiceDAOHibernate() {
		super(Invoice.class);
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#getInvoice(java.lang.Integer)
	 */
	public final Invoice getInvoice(final Integer invoideId) {
		final Invoice invoice = (Invoice) getHibernateTemplate().get(
				Invoice.class, invoideId);

		if (invoice == null) {
			throw new ObjectRetrievalFailureException(Invoice.class, invoideId);
		}

		return invoice;
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#saveInvoice(no.ica.elfa.model.Invoice)
	 */
	public final void saveInvoice(final Invoice invoice) {
		getHibernateTemplate().saveOrUpdate(invoice);

	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#removeInvoice(java.lang.Integer)
	 */
	public final void removeInvoice(final Integer invoideId) {
		getHibernateTemplate().delete(getInvoice(invoideId));
	}

	@SuppressWarnings("unchecked")
	public final List<Invoice> findByBunt(final Bunt bunt, final int startIndex,
			final int fetchSize,final InvoiceColumnEnum orderByColumn) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {

				Criteria criteria = session.createCriteria(Invoice.class);
				
				if(orderByColumn!=null){
						criteria.addOrder(Order.asc(orderByColumn.getElfaColumnName()));
				}
				criteria.add(
								Restrictions.eq("bunt", bunt));

				criteria.setFetchSize(fetchSize);
				criteria.setMaxResults(fetchSize);
				criteria.setFirstResult(startIndex);
				return criteria.list();
			}

		});
	}

	public final Integer getCountByBunt(final Bunt bunt) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery("select count(*) from Invoice as invoice where invoice.bunt=?");
						query.setEntity(0, bunt);
						Object result = query.iterate().next();
						if (result instanceof Long) {
							return Integer.valueOf(((Long) result).intValue());
						}
						return result;
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#findByInvoiceIds(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public final List<Invoice> findByInvoiceIds(final List invoiceIds) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(Invoice.class).add(
						Restrictions.in("invoiceId", invoiceIds)).list();
			}

		});
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#findByInvoiceNr(java.math.BigDecimal)
	 */
	public final Invoice findByInvoiceNr(final BigDecimal invoiceNr) {
		return (Invoice) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Invoice invoice = null;
						List<Invoice> list = session.createCriteria(
								Invoice.class).add(
								Restrictions.like("invoiceNr", invoiceNr))
								.list();

						if (list != null && list.size() == 1) {
							invoice = list.get(0);
						}
						return invoice;
					}

				});
	}

	@SuppressWarnings("unchecked")
	public final List<Invoice> findByBuntId(final Integer buntId) {
		return (List<Invoice>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Invoice.class).addOrder(
								Order.asc("invoiceId")).createCriteria("bunt")
								.add(Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	@SuppressWarnings("unchecked")
	public final List<Invoice> findByBuntIdAndStoreNo(final Integer buntId,
			final Integer storeNo) {
		return (List<Invoice>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Invoice.class).add(
								Restrictions.like("storeNo", storeNo))
								.createCriteria("bunt").add(
										Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#lazyLoad(no.ica.elfa.model.Invoice)
	 */
	public final void lazyLoad(final Invoice invoice) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.load(invoice, invoice.getInvoiceId());

				Set<InvoiceItem> set = invoice.getInvoiceItems();
				List list = new ArrayList<InvoiceItem>(set);
				list.iterator();

				return null;
			}

		});

	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#findByDate(java.util.Date,
	 *      java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public final List<Invoice> findByDate(final Date fromDate, final Date toDate) {
		return (List<Invoice>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						
						return session.createCriteria(Invoice.class).add(
								Restrictions.gt("storeNo", 8000)).add(
								Restrictions.between("invoiceDate", fromDate,
										toDate)).list();
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#lazyLoad(no.ica.elfa.model.Invoice,
	 *      no.ica.elfa.service.LazyLoadInvoiceEnum[])
	 */
	public final void lazyLoad(final Invoice invoice,
			final LazyLoadInvoiceEnum[] enums) {
		if (invoice != null && invoice.getInvoiceId() != null) {
			getHibernateTemplate().execute(new HibernateCallback() {

				@SuppressWarnings("incomplete-switch")
				public Object doInHibernate(Session session)
						throws HibernateException {
					session.load(invoice, invoice.getInvoiceId());
					Set<?> set;

					for (LazyLoadInvoiceEnum lazyEnum : enums) {
						switch (lazyEnum) {
						case INVOCIE_ITEM:
							set = invoice.getInvoiceItems();
							set.iterator();
							break;
						}
					}
					return null;
				}

			});
		}

	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#findByInvoiceNrAndOrAvdnr(java.math.BigDecimal,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public final List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(
			final BigDecimal invoiceNr, final Integer storeNo) {
		return (List<InvoiceInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session.createCriteria(Invoice.class);

						if (invoiceNr != null) {
							crit.add(Restrictions.like("invoiceNr", invoiceNr));
						}

						if (storeNo != null) {
							crit.add(Restrictions.eq("storeNo", storeNo));
						}
						return crit.list();
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.InvoiceDAO#findByInvoiceNumbers(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public final List<Invoice> findByInvoiceNumbers(final List<Integer> invoiceNumbers) {
		return (List<Invoice>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Invoice.class).add(
								Restrictions.in("invoiceNr", invoiceNumbers));
					}

				});
	}

}
