package no.ica.fraf.dao.hibernate.fenistra;

import java.util.List;

import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.MrKontiOrg;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av LkKontraktobjekterDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class LkKontraktobjekterDAOHibernate extends BaseDAOHibernate<LkKontraktobjekter> implements
		LkKontraktobjekterDAO {
	public LkKontraktobjekterDAOHibernate() {
		super(LkKontraktobjekter.class);
	}

	/**
	 * @see no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO#findByAvdnr(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<LkKontraktobjekter> findByAvdnr(final String avdnr) {
		return (List<LkKontraktobjekter>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(LkKontraktobjekter.class)
								.add(
										Restrictions.ilike("kontraktObjektNr",
												avdnr)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO#findAllIdByMrKonti(no.ica.fraf.model.MrKontiOrg,
	 *      java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findAllIdByMrKonti(final MrKontiOrg mrKonti,
			final List<String> departments) {
		if (mrKonti == null) {
			return findAllId(departments);
		}
		return (List<Integer>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer queryString = new StringBuffer(
								"select lkKontraktobjekter.kontraktObjektId ")
								.append(
										"from LkKontraktobjekter lkKontraktobjekter ")
								.append(
										"where lkKontraktobjekter.kontraktObjektNr in (:deps) ")
								.append(
										"and lkKontraktobjekter.mrTransaksjonTyperEd.mrKonti = :mrKonti");
						Query query = session.createQuery(queryString
								.toString());
						query.setParameterList("deps", departments);
						query.setParameter("mrKonti", mrKonti);
						List list = query.list();
						return list;
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO#findAllId(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findAllId(final List departments) {
		return (List<Integer>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer queryString = new StringBuffer(
								"select kontraktObjektId from LkKontraktobjekter lkKontraktobjekter ")
								.append("where kontraktObjektNr in (:deps)");
						return session.createQuery(queryString.toString())
								.setParameterList("deps", departments,
										Hibernate.STRING).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO#findByIds(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<LkKontraktobjekter> findByIds(final List contractIds) {
		if (contractIds == null || contractIds.size() == 0) {
			return null;
		}
		return (List<LkKontraktobjekter>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(LkKontraktobjekter.class)
								.add(
										Restrictions.in("kontraktObjektId",
												contractIds)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO#getLkKontraktobjekter(java.lang.Integer)
	 */
	public LkKontraktobjekter getLkKontraktobjekter(
			final Integer kontraktObjektId) {
		final LkKontraktobjekter lkKontraktobjekter = (LkKontraktobjekter) getHibernateTemplate()
				.get(LkKontraktobjekter.class, kontraktObjektId);

		return lkKontraktobjekter;
	}

}
