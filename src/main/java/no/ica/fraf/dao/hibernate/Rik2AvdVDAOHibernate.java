package no.ica.fraf.dao.hibernate;

import no.ica.fraf.model.Rik2AvdV;

/**
 * Implementasjon av Rik2AvdVDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class Rik2AvdVDAOHibernate extends AbstractDepartmentDAO {
	private static final String NAME_COLUMN="navn";
	public Rik2AvdVDAOHibernate() {
		super(Rik2AvdV.class);
	}

	/**
	 * @see no.ica.fraf.dao.view.Rik2AvdVDAO#getRik2AvdV(java.lang.Integer)
	 */
	/*public Department getDepartment(final Integer avdId) {
		final Rik2AvdV rik2AvdV = (Rik2AvdV) getHibernateTemplate().get(
				Rik2AvdV.class, avdId);

		return rik2AvdV;
	}*/

	/**
	 * @see no.ica.fraf.dao.view.Rik2AvdVDAO#findAll()
	 */
	/*public List findAll() {
		return getHibernateTemplate().find("from Rik2AvdV order by navn");
	}*/

	/**
	 * @see no.ica.fraf.dao.view.Rik2AvdVDAO#findByButiksNrAndDate(java.lang.Integer,
	 *      java.util.Date)
	 */
	/*public Rik2AvdV findByButiksNrAndDate(final Integer butiksNr,
			final Date dato) {
		return (Rik2AvdV) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<Rik2AvdV> list = session.createCriteria(
								Rik2AvdV.class).add(
								Restrictions.eq("butiksNr", butiksNr)).add(
								Restrictions.or(Restrictions.and(Restrictions
										.isNull("dtSlutt"), Restrictions.le(
										"dtStart", dato)), Restrictions.and(
										Restrictions.le("dtStart", dato),
										Restrictions.ge("dtSlutt", dato))))
								.list();

						if (list != null && list.size() == 1) {
							return list.get(0);
						}
						return null;
					}

				});
	}*/

	/*public Rik2AvdV findByAvdnr(final Integer avdnr) {
		return (Rik2AvdV) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<Rik2AvdV> list = session.createCriteria(
								Rik2AvdV.class).add(
								Restrictions.like("avdnr", avdnr)).list();

						if (list == null || list.size() != 1) {
							return null;
						}

						return list.get(0);
					}

				});
	}*/

	/*@SuppressWarnings("unchecked")
	public List<Department> findAllClosed() {
		return (List<Department>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						return session.createCriteria(Rik2AvdV.class).add(
								Restrictions.isNotNull("dtSlutt")).list();
					}

				});
	}*/

	/*@SuppressWarnings("unchecked")
	public List<Department> findClosedByDate(final Date fromDate) {
		return (List<Department>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						return session.createCriteria(Rik2AvdV.class).add(
								Restrictions.isNotNull("dtSlutt")).add(
								Restrictions.ge("dtSlutt", fromDate)).list();
					}

				});
	}*/

	@Override
	protected String getNameColumnName() {
		return NAME_COLUMN;
	}

}
