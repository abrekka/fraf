package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.EflowUsersVDAO;
import no.ica.fraf.model.EflowUsersV;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av EflowUsersDAO for hibernate
 * 
 * @author abr99
 * 
 */
public class EflowUsersVDAOHibernate extends BaseDAOHibernate<EflowUsersV>
		implements EflowUsersVDAO {
	/**
	 * Konstruktør
	 */
	public EflowUsersVDAOHibernate() {
		super(EflowUsersV.class);
	}

	/**
	 * @see no.ica.fraf.dao.EflowUsersVDAO#findByAvdnr(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<EflowUsersV> findByAvdnr(final String avdnr) {
		return (List<EflowUsersV>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session
								.createQuery(
										"select eflowUsersV from EflowUsersV as eflowUsersV where eflowUsersV.userName like '"
												+ avdnr + "%'").list();

					}

				});
	}

}
