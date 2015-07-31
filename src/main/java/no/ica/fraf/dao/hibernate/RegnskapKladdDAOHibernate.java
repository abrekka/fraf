package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.RegnskapKladdDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.RegnskapKladd;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for RegnskapKladd for Hibernate
 * 
 * @author abr99
 * 
 */
public class RegnskapKladdDAOHibernate extends BaseDAOHibernate<RegnskapKladd>
		implements RegnskapKladdDAO {
	public RegnskapKladdDAOHibernate() {
		super(RegnskapKladd.class);
	}

	/**
	 * @see no.ica.fraf.dao.RegnskapKladdDAO#findRegnskapKladdByBuntId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<RegnskapKladd> findRegnskapKladdByBuntId(final Integer buntId) {
		return (List<RegnskapKladd>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(RegnskapKladd.class)
								.addOrder(Order.asc("regnskapKladdId"))
								.createCriteria("bunt").add(
										Restrictions.like("buntId", buntId))
								.list();
					}

				});
	}

	public void removeForAvdeling(final Avdeling avdeling) {
		getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				List<RegnskapKladd> list = session.createCriteria(
						RegnskapKladd.class).add(
						Restrictions.eq("avdnr", avdeling.getAvdnr())).list();
				if (list != null) {
					for (RegnskapKladd kladd : list) {
						removeObject(kladd.getRegnskapKladdId());
					}
				}
				return null;
			}

		});

	}

}
