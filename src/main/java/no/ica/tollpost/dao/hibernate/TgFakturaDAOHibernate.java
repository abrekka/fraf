package no.ica.tollpost.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.tollpost.dao.TgFakturaDAO;
import no.ica.tollpost.model.TgFaktura;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TgFakturaDAOHibernate extends BaseDAOHibernate<TgFaktura>
		implements TgFakturaDAO {
	public TgFakturaDAOHibernate() {
		super(TgFaktura.class);
	}

	public List<TgFaktura> findByBunt(final Bunt bunt) {
		return (List<TgFaktura>) getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createCriteria(TgFaktura.class).add(
								Restrictions.eq("bunt", bunt)).list();
					}

				});
	}

	public List<InvoiceInterface> findByBatch(final Batchable batchable,
			final int startIndex, final int fetchSize,final InvoiceColumnEnum orderByColumn) {
		return (List<InvoiceInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						Criteria criteria = session.createCriteria(
								TgFaktura.class);
						
						if(orderByColumn!=null){
							criteria.addOrder(
									Order.asc(orderByColumn.getFrafColumnName()));
						}
						
						
								criteria.createCriteria("bunt").add(
										Restrictions.eq("buntId", batchable
												.getBatchId()));

						criteria.setFetchSize(fetchSize);
						criteria.setMaxResults(fetchSize);
						criteria.setFirstResult(startIndex);
						return criteria.list();
					}

				});
	}

	public Integer getCountByBatch(final Batchable batchable) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select count(*) from TgFaktura as tgFaktura where tgFaktura.bunt.buntId=?");
						query.setInteger(0, batchable.getBatchId());
						Object result = query.iterate().next();
						if(result instanceof Long){
							return Integer.valueOf(((Long)result).intValue());
						}
						return result;
					}

				});
	}

	public void lazyLoad(final TgFaktura tgFaktura,
			final LazyLoadInvoiceEnum[] enums) {
		if (tgFaktura != null && tgFaktura.getTgFakturaId() != null) {
			getHibernateTemplate().execute(new HibernateCallback() {

				@SuppressWarnings("incomplete-switch")
				public Object doInHibernate(Session session)
						throws HibernateException {
					session.load(tgFaktura, tgFaktura.getTgFakturaId());
					Set<?> set;
					// List<LazyLoadOrderEnum> enumList =
					// Arrays.asList(lazyEnums);

					for (LazyLoadInvoiceEnum lazyEnum : enums) {
						switch (lazyEnum) {
						case INVOCIE_ITEM:
							set = tgFaktura.getTgFakturaLinjer();
							set.iterator();
							break;
						}
					}
					return null;
				}

			});
		}

	}

	public List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(
			final BigDecimal invoiceNr, final Integer avdnr) {
		return (List<InvoiceInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria crit = session.createCriteria(TgFaktura.class);

						if (invoiceNr != null) {
							crit.add(Restrictions.like("fakturaNr", invoiceNr));
						}

						if (avdnr != null) {
							crit.add(Restrictions.eq("avdnr", avdnr));
						}
						return crit.list();
					}

				});
	}

}
