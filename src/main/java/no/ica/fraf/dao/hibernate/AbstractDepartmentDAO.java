package no.ica.fraf.dao.hibernate;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.model.Department;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public abstract class AbstractDepartmentDAO extends BaseDAOHibernate<Department> implements
DepartmentDAO {

	public AbstractDepartmentDAO(Class<?> aClass) {
		super(aClass);
	}
	
	protected abstract String getNameColumnName();
	
	public List<Department> findAll() {
		return this.findAllOrdered(getNameColumnName());
	}
	public Department getDepartment(final Integer avdNr) {
		final Department department = (Department) getHibernateTemplate().get(
				clazz, avdNr);

		return department;
	}
	
	public Department findByButiksNrAndDate(final Integer butiksNr,
			final Date dato) {
		return (Department) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<Department> list = session.createCriteria(
								clazz).add(
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
	}
	
	public Department findByAvdnr(final Integer avdnr) {
		return (Department) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<Department> list = session.createCriteria(
								clazz).add(
								Restrictions.like("avdnr", avdnr)).list();

						if (list == null || list.size() != 1) {
							return null;
						}

						return list.get(0);
					}

				});
	}
	
	public List<Department> findAllClosed() {
		return (List<Department>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						return session.createCriteria(clazz).add(
								Restrictions.isNotNull("dtSlutt")).list();
					}

				});
	}
	
	public List<Department> findClosedByDate(final Date fromDate) {
		return (List<Department>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {

						return session.createCriteria(clazz).add(
								Restrictions.isNotNull("dtSlutt")).add(
								Restrictions.ge("dtSlutt", fromDate)).list();
					}

				});
	}

}
