package no.ica.fraf.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.ReportDAO;
import no.ica.fraf.dao.view.AvdelingOversiktVDAO;
import no.ica.fraf.model.AvdelingOversiktV;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvdelingOversiktVDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingOversiktVDAOHibernate extends
		BaseDAOHibernate<AvdelingOversiktV> implements ReportDAO,
		AvdelingOversiktVDAO {
	public AvdelingOversiktVDAOHibernate() {
		super(AvdelingOversiktV.class);
	}

	/**
	 * @see no.ica.fraf.dao.ReportDAO#getListData()
	 */
	public List getListData() {
		return getHibernateTemplate().find("from AvdelingOversiktV");
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingOversiktVDAO#findByStatusBokfSelskapChainYear(java.lang.String,
	 *      no.ica.fraf.model.BokfSelskap, no.ica.fraf.model.Rik2KjedeV,
	 *      java.lang.Integer)
	 */
	public List findByStatusBokfSelskapChainYear(final String status,
			final BokfSelskap bokfSelskap, final Chain chain,
			final Integer year) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				final Criteria crit = session
						.createCriteria(AvdelingOversiktV.class);
				final Date currentDate = Calendar.getInstance().getTime();

				if (status != null) {
					crit.add(Restrictions.ilike("status", status));
				}

				if (bokfSelskap != null) {
					crit.add(Restrictions.ilike("bokfSelskap", bokfSelskap
							.getSelskapNavn()));
				}

				if (chain != null) {
					crit.add(Restrictions.ilike("kjede", chain.getNavn()));
				}

				crit.add(Restrictions.le("fraDato", currentDate)).add(
						Restrictions.ge("tilDato", currentDate)).add(
						Restrictions.like("avdelingOversiktVPK.aar", year))
						.addOrder(Order.asc("avdelingNavn"));

				return crit.list();
			}

		});
	}

}
