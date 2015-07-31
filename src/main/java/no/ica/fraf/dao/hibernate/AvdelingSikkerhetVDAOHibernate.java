package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.view.AvdelingSikkerhetVDAO;
import no.ica.fraf.model.AvdelingSikkerhetV;
import no.ica.fraf.model.SikkerhetType;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * DAO klasse for view AVDELING_SIKKERHET_V
 * 
 * @author abr99
 * 
 */
public class AvdelingSikkerhetVDAOHibernate extends
		BaseDAOHibernate<AvdelingSikkerhetV> implements
		AvdelingSikkerhetVDAO {

	public AvdelingSikkerhetVDAOHibernate() {
		super(AvdelingSikkerhetV.class);
	}

	/**
	 * @see no.ica.fraf.dao.view.AvdelingSikkerhetVDAO#findSikkerhet(no.ica.fraf.model.SikkerhetType)
	 */
	@SuppressWarnings("unchecked")
	public List<AvdelingSikkerhetV> findSikkerhet(
			final SikkerhetType sikkerhetType) {
		return (List<AvdelingSikkerhetV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session
								.createCriteria(AvdelingSikkerhetV.class);

						if (sikkerhetType != null) {
							crit.add(Restrictions.like(
									"avdelingSikkerhetVPK.sikkerhetTypeId",
									sikkerhetType.getSikkerhetTypeId()));
						}
						return crit.list();
					}

				});
	}

}
