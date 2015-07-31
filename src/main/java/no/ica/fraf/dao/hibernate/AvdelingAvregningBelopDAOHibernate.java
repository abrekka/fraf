package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.AvdelingAvregningBelopDAO;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.Bunt;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for tabell AVDELING_AVREGNING_BELOP
 * 
 * @author abr99
 * 
 */
public class AvdelingAvregningBelopDAOHibernate extends
		BaseDAOHibernate<AvdelingAvregningBelop> implements
		AvdelingAvregningBelopDAO {
	/**
	 * 
	 */
	public AvdelingAvregningBelopDAOHibernate() {
		super(AvdelingAvregningBelop.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingAvregningBelopDAO#findByBunt(no.ica.fraf.model.Bunt)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingAvregningBelop> findByBunt(final Bunt bunt) {
		return (List<AvdelingAvregningBelop>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(
								AvdelingAvregningBelop.class).addOrder(
								Order.asc("maaned")).createCriteria(
								"avdelingAvregningImport").add(
								Restrictions.eq("bunt", bunt)).addOrder(
								Order.asc("avdnr")).list();
					}

				});
	}

}
