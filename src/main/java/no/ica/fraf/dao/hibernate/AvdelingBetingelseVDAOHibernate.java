package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.view.AvdelingBetingelseVDAO;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingBetingelseV;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvdelingBetingelseVDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingBetingelseVDAOHibernate extends
		BaseDAOHibernate<AvdelingBetingelse> implements AvdelingBetingelseVDAO {
	public AvdelingBetingelseVDAOHibernate() {
		super(AvdelingBetingelse.class);
	}

	/**
	 * @see no.ica.fraf.dao.ReportDAO#getListData()
	 */
	public List getListData() {
		return getHibernateTemplate().find(
				"from AvdelingBetingelseV order by avdnr");
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingBetingelseVDAO#findByBetingelseRegionKjede(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingBetingelseV> findByBetingelseRegionKjede(
			final String betingelseNavn, final String region,
			final String kjede, final String betingelseGruppeNavn) {
		return (List<AvdelingBetingelseV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final Criteria crit = session
								.createCriteria(AvdelingBetingelseV.class);

						if (betingelseNavn != null
								&& betingelseNavn.length() != 0) {
							crit.add(Restrictions.ilike("betingelseNavn",
									betingelseNavn));
						}

						if (region != null && region.length() != 0) {
							crit.add(Restrictions.ilike("region", region));
						}

						if (kjede != null && kjede.length() != 0) {
							crit.add(Restrictions.ilike("kjede", kjede));
						}
						if (betingelseGruppeNavn != null
								&& betingelseGruppeNavn.length() != 0) {
							crit.add(Restrictions.ilike("betingelseGruppeNavn",
									betingelseGruppeNavn));
						}
						return crit.addOrder(Order.asc("avdnr")).list();
					}

				});
	}
}
