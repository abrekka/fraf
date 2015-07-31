package no.ica.fraf.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av FakturaDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FakturaDAOHibernate extends BaseDAOHibernate<Faktura> implements
		FakturaDAO {
	/**
	 * 
	 */
	private BuntDAO buntDAO;

	public FakturaDAOHibernate() {
		super(Faktura.class);
	}

	/**
	 * Setter dao for bunt
	 * 
	 * @param dao
	 */
	public void setBuntDAO(BuntDAO dao) {
		this.buntDAO = dao;
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#getFaktura(java.lang.Integer)
	 */
	public Faktura getFaktura(final Integer fakturaId) {
		final Faktura faktura = (Faktura) getHibernateTemplate().get(
				Faktura.class, fakturaId);

		return faktura;
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#saveFaktura(no.ica.fraf.model.Faktura)
	 */
	public void saveFaktura(final Faktura faktura) {
		getHibernateTemplate().saveOrUpdate(faktura);
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#removeFaktura(java.lang.Integer)
	 */
	public void removeFaktura(final Integer fakturaId) {
		getHibernateTemplate().delete(getFaktura(fakturaId));
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#findAll()
	 */
	public List findAll() {
		return getHibernateTemplate().find("from Faktura");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List getComboValues(final Object object) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		Faktura faktura;

		while (objectIt.hasNext()) {
			faktura = (Faktura) objectIt.next();
			removeFaktura(faktura.getFakturaId());
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
	 */
	public void persistObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		Faktura faktura;

		while (objectIt.hasNext()) {
			faktura = (Faktura) objectIt.next();
			saveFaktura(faktura);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeFaktura(((Faktura) object).getFakturaId());

	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#loadLazy(no.ica.fraf.model.Faktura,
	 *      no.ica.fraf.enums.LazyLoadFakturaEnum[])
	 */
	public void loadLazy(final Faktura faktura,
			final LazyLoadFakturaEnum[] loadTypes) {
		if (faktura == null || faktura.getFakturaId() == null) {
			return;
		}
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				// session.refresh(faktura);
				session.load(faktura, faktura.getFakturaId());
				Set<?> set;
				List<Object> tmpVector;
				Hibernate.initialize(faktura.getBunt());
				for (LazyLoadFakturaEnum aEnum : loadTypes) {
					switch (aEnum) {
					case LOAD_INVOICE_TEXT:
						set = faktura.getFakturaTeksts();
						tmpVector = new ArrayList<Object>(set);
						tmpVector.iterator();
						break;
					case LOAD_INVOICE_LINES:
						set = faktura.getFakturaLinjes();
						tmpVector = new ArrayList<Object>(set);
						break;
					case LOAD_INVOICE_BOOK:
						final Set<RegnskapKladd> setRegnskapKladd = faktura
								.getRegnskapKladds();
						tmpVector = new ArrayList<Object>(setRegnskapKladd);
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
	 * @see no.ica.fraf.dao.FakturaDAO#findByBuntId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Faktura> findByBuntId(final Integer buntId) {
		return (List<Faktura>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Faktura.class).addOrder(
								Order.asc("avdnr")).addOrder(
								Order.asc("fakturaId")).createCriteria("bunt")
								.add(Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#findByBuntIdAndAvdnr(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Faktura> findByBuntIdAndAvdnr(final Integer buntId,
			final Integer avdnr) {
		return (List<Faktura>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Faktura.class).addOrder(
								Order.asc("avdnr")).add(
								Restrictions.like("avdnr", avdnr))
								.createCriteria("bunt").add(
										Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#lazyLoadBunt(no.ica.fraf.model.Faktura)
	 */
	public void lazyLoadBunt(final Faktura faktura) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				Hibernate.initialize(faktura.getBunt());
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#getCountByBatch(no.ica.fraf.common.Batchable)
	 */
	public Integer getCountByBatch(final Batchable batchable) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery("select count(*) from Faktura as faktura where faktura.bunt.buntId=?");
						query.setInteger(0, batchable.getBatchId());
						Object result = query.iterate().next();
						if (result instanceof Long) {
							return Integer.valueOf(((Long) result).intValue());
						}
						return result;
					}

				});
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#findByBatch(no.ica.fraf.common.Batchable,
	 *      java.lang.Integer, java.lang.Integer,
	 *      no.ica.fraf.xml.InvoiceColumnEnum)
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceInterface> findByBatch(final Batchable batchable,
			final Integer index, final Integer fetchSize,
			final InvoiceColumnEnum orderByColumn,final boolean shouldHaveInvoiceNr,final boolean shouldHaveValue) {
		return (List<InvoiceInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						Criteria criteria = session.createCriteria(
								Faktura.class);
						criteria=shouldHaveInvoiceNr?criteria.add(
								Restrictions.isNotNull("fakturaNr")):criteria;
						criteria=shouldHaveValue?criteria.add(Restrictions.ne("totalBelop", BigDecimal.ZERO)):criteria;

						criteria.addOrder(Order.asc("fakturaId"));
						
						if (orderByColumn != null) {
							criteria.addOrder(Order.asc(orderByColumn
									.getFrafColumnName()));
						}
						criteria.createCriteria("bunt").add(
								Restrictions.eq("buntId", batchable
										.getBatchId()));

						criteria.setFetchSize(fetchSize);
						criteria.setMaxResults(fetchSize);
						criteria.setFirstResult(index);
						//criteria.setFetchMode("fakturaLinjes", FetchMode.SELECT);
						return new ArrayList<InvoiceInterface>(new LinkedHashSet<InvoiceInterface>(criteria.list()));
					}

				});
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#lazyLoad(no.ica.fraf.xml.InvoiceInterface,
	 *      no.ica.elfa.service.LazyLoadInvoiceEnum[])
	 */
	public void lazyLoad(final InvoiceInterface invoiceInterface,
			final LazyLoadInvoiceEnum[] enums) {
		final Faktura faktura = (Faktura) invoiceInterface;
		if (faktura != null && faktura.getFakturaId() != null) {
			getHibernateTemplate().execute(new HibernateCallback() {

				@SuppressWarnings("incomplete-switch")
				public Object doInHibernate(Session session)
						throws HibernateException {
					session.load(faktura, faktura.getFakturaId());
					Set<?> set;

					for (LazyLoadInvoiceEnum lazyEnum : enums) {
						switch (lazyEnum) {
						case INVOCIE_ITEM:
							set = faktura.getFakturaLinjes();
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
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#findByInvoiceNrAndOrAvdnr(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(
			final String invoiceNr, final String avdnr) {
		return (List<InvoiceInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session.createCriteria(Faktura.class);

						if (invoiceNr != null) {
							crit.add(Restrictions.like("fakturaNr", invoiceNr));
						}

						if (avdnr != null && avdnr.length() != 0) {
							crit.add(Restrictions.eq("avdnr", Integer
									.valueOf(avdnr)));
						}
						return crit.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#findByBuntIds(java.util.List,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Collection<Faktura> findByBuntIds(final List<Integer> buntIds,
			final String orderBy) {
		return (Collection<Faktura>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session.createCriteria(Faktura.class);

						if (orderBy != null) {
							crit.addOrder(Order.asc(orderBy));
						}
						crit.setFetchMode("fakturaTeksts", FetchMode.JOIN)
								.setFetchMode("fakturaLinjes", FetchMode.JOIN)
								.createCriteria("bunt").add(
										Restrictions.in("buntId", buntIds));
						List<Faktura> invoices = crit.list();

						return new LinkedHashSet<Faktura>(invoices);
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.FakturaDAO#findByBuntIdLoad(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Collection<Faktura> findByBuntIdLoad(final Integer buntId) {
		return (Collection<Faktura>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						List<Faktura> invoices = session.createCriteria(
								Faktura.class).addOrder(Order.asc("avdnr"))
								.addOrder(Order.asc("fakturaId")).setFetchMode(
										"fakturaTeksts", FetchMode.JOIN)
								.setFetchMode("fakturaLinjes", FetchMode.JOIN)
								.createCriteria("bunt").add(
										Restrictions.like("buntId", buntId))
								.list();

						return new LinkedHashSet<Faktura>(invoices);
					}

				});
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#refreshBatchable(no.ica.fraf.common.Batchable)
	 */
	public void refreshBatchable(Batchable batchable) {
		buntDAO.refresh((Bunt) batchable);

	}

	public void removeForAvdeling(final Avdeling avdeling) {
		getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				List<Faktura> list = session.createCriteria(Faktura.class).add(
						Restrictions.eq("avdeling", avdeling)).list();
				if (list != null) {
					for (Faktura faktura : list) {
						removeFaktura(faktura.getFakturaId());
					}
				}
				return null;
			}

		});

	}

	public Collection<Faktura> findByFakturaNr(final List<String> invoiceNumbers) {
		return (Collection<Faktura>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(final Session session){
				
				return new HashSet<Faktura>(session.createCriteria(Faktura.class).add(Restrictions.in("fakturaNr",invoiceNumbers)).setFetchMode(
						"fakturaTeksts", FetchMode.JOIN)
						.setFetchMode("fakturaLinjes", FetchMode.JOIN).list());
			}
		
		});
	}

	

	public void save(InvoiceInterface invoice) {
		saveFaktura((Faktura)invoice);
		
	}

}
