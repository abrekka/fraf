package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.EepHeadLoadDAO;
import no.ica.elfa.model.EepHeadLoad;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO mot tabell EEP_LOAD
 * 
 * @author abr99
 * 
 */
public class EepHeadLoadDAOHibernate extends BaseDAOHibernate<EepHeadLoad>
		implements EepHeadLoadDAO {
	/**
	 * Konstruktør
	 */
	public EepHeadLoadDAOHibernate() {
		super(EepHeadLoad.class);
	}

	/**
	 * @see no.ica.elfa.dao.EepHeadLoadDAO#deleteImportFile(no.ica.elfa.model.EepHeadLoad)
	 */
	public final void deleteImportFile(EepHeadLoad head) {
		getHibernateTemplate()
				.bulkUpdate(
						"delete from EepLineItemLoad eepLineItemLoad where eepLineItemLoad.eepHeadLoad = ?",
						head);
		getHibernateTemplate().delete(head);

	}

	/**
	 * @see no.ica.elfa.dao.EepHeadLoadDAO#findBySequenceNumber(java.lang.Integer)
	 */
	public final EepHeadLoad findBySequenceNumber(final Integer nr) {
		return (EepHeadLoad) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<EepHeadLoad> list = session.createCriteria(
								EepHeadLoad.class).add(
								Restrictions.eq("sequenceNumber", nr)).list();
						if (list != null && list.size() == 1) {
							return list.get(0);
						}
						return null;
					}

				});
	}

}
