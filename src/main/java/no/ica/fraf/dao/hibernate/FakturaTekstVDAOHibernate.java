package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.FakturaTekstVDAO;
import no.ica.fraf.model.FakturaTekstV;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for tabell FAKTURA_TEKST
 * 
 * @author abr99
 * 
 */
public class FakturaTekstVDAOHibernate extends
		BaseDAOHibernate<FakturaTekstV> implements FakturaTekstVDAO {
	/**
	 * 
	 */
	public FakturaTekstVDAOHibernate() {
		super(FakturaTekstV.class);
	}

	/**
	 * @see no.ica.fraf.dao.FakturaTekstVDAO#findByFakturaId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<FakturaTekstV> findByFakturaId(final Integer fakturaId) {

		return (List<FakturaTekstV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(FakturaTekstV.class).add(
								Restrictions.eq("fakturaTekstVPK.fakturaId", fakturaId))
								.addOrder(Order.asc("fakturaTekstRek")).list();
					}

				});
	}

}
