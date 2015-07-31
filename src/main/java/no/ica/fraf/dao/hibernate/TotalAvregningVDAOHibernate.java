package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.TotalAvregningVDAO;
import no.ica.fraf.model.TotalAvregningV;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for view TOTAL_AVREGNING_V
 * 
 * @author abr99
 * 
 */
public class TotalAvregningVDAOHibernate extends
		BaseDAOHibernate<TotalAvregningV> implements TotalAvregningVDAO {
	/**
	 * 
	 */
	public TotalAvregningVDAOHibernate() {
		super(TotalAvregningV.class);
	}

	/**
	 * @see no.ica.fraf.dao.TotalAvregningVDAO#findByBuntId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<TotalAvregningV> findByBuntId(final Integer buntId) {
		return (List<TotalAvregningV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(TotalAvregningV.class)
								.add(
										Restrictions.eq(
												"totalAvregningVPK.buntId",
												buntId)).list();
					}

				});
	}

}
