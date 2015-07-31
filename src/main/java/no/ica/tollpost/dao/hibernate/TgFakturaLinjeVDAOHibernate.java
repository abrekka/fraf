package no.ica.tollpost.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.tollpost.dao.TgFakturaLinjeVDAO;
import no.ica.tollpost.model.TgFakturaLinjeV;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TgFakturaLinjeVDAOHibernate extends
		BaseDAOHibernate<TgFakturaLinjeV> implements TgFakturaLinjeVDAO {
	public TgFakturaLinjeVDAOHibernate() {
		super(TgFakturaLinjeV.class);
	}

	public List<TgFakturaLinjeV> findByInvoiceNumbers(
			final List<BigDecimal> invoiceNumbers) {
		return (List<TgFakturaLinjeV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createCriteria(TgFakturaLinjeV.class)
								.add(
										Restrictions.in("fakturaNr",
												invoiceNumbers)).addOrder(Order.asc("fakturaNr")).list();
					}

				});
	}

}
