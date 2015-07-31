package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.ReconcilVDAO;
import no.ica.elfa.model.ReconcilV;
import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for DAO mot view RECONCIL_V
 * 
 * @author abr99
 * 
 */
public class ReconcilVDAOHibernate extends BaseDAOHibernate<ReconcilV>
		implements ReconcilVDAO {
	/**
	 * 
	 */
	public ReconcilVDAOHibernate() {
		super(ReconcilV.class);
	}

	/**
	 * @see no.ica.elfa.dao.ReconcilVDAO#findByBatchId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public final List<ReconcilVInterface> findByBatchId(final Integer batchId) {
		return (List<ReconcilVInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(ReconcilV.class).add(
								Restrictions.eq("batchId", batchId)).list();
					}

				});
	}

}
