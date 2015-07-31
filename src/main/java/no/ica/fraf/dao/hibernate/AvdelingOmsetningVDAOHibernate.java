package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.AvdelingOmsetningVDAO;
import no.ica.fraf.model.AvdelingOmsetningV;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av interface for AvdleingOmsetningV for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingOmsetningVDAOHibernate extends BaseDAOHibernate<AvdelingOmsetningV> implements
		AvdelingOmsetningVDAO {
	public AvdelingOmsetningVDAOHibernate() {
		super(AvdelingOmsetningV.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingOmsetningVDAO#findByPeriodeAndStatus(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, java.lang.String)
	 */
	public List findByPeriodeAndStatus(final Integer fromYear,
			final Integer fromPeriode, final Integer toYear,
			final Integer toPeriode, final Integer avdnr, final String status) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria crit = session
						.createCriteria(AvdelingOmsetningV.class).add(
								Restrictions.between(
										"avdelingOmsetningVPK.aar", fromYear,
										toYear)).add(
								Restrictions.between(
										"avdelingOmsetningVPK.periode",
										fromPeriode, toPeriode));

				if (avdnr != null) {
					crit.add(Restrictions.like("avdelingOmsetningVPK.avdnr",
							avdnr));
				}
				if (status != null) {
					crit.add(Restrictions.like("status", status));
				}
				return crit.list();
			}

		});
	}

}
