package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.model.ApplUserType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av ApplUserTypeDAO for hibernate
 * 
 * @author abr99
 * 
 */
public class ApplUserTypeDAOHibernate extends
		BaseDAOHibernate<ApplUserType> implements ApplUserTypeDAO {

	/**
	 * 
	 */
	public ApplUserTypeDAOHibernate() {
		super(ApplUserType.class);
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#getApplUserType(java.lang.Integer)
	 */
	public ApplUserType getApplUserType(final Integer typeId) {
		final ApplUserType applUserType = (ApplUserType) getHibernateTemplate()
				.get(ApplUserType.class, typeId);

		if (applUserType == null) {
			throw new ObjectRetrievalFailureException(ApplUserType.class,
					typeId);
		}

		return applUserType;
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#saveApplUserType(no.ica.fraf.model.ApplUserType)
	 */
	public void saveApplUserType(final ApplUserType applUserType) {
		getHibernateTemplate().saveOrUpdate(applUserType);
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#removeApplUserType(java.lang.Integer)
	 */
	public void removeApplUserType(final Integer typeId) {
		getHibernateTemplate().delete(getApplUserType(typeId));
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ApplUserType> findAll() {
		return getHibernateTemplate().find("from ApplUserType");
	}

	/**
	 * Sletter alle typer
	 */
	public void deleteAll() {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.createQuery("delete from ApplUserType").executeUpdate();
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#isAdministrator(no.ica.fraf.model.ApplUserType)
	 */
	public boolean isAdministrator(final ApplUserType applUserType) {
		boolean returnValue = false;
		if (applUserType != null) {
			returnValue = applUserType.getTypeName().equalsIgnoreCase(
					ADMIN_STRING);
		}
		return returnValue;
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#isWriter(no.ica.fraf.model.ApplUserType)
	 */
	public boolean isWriter(final ApplUserType applUserType) {
		boolean returnValue = false;
		if (applUserType != null) {
			returnValue = applUserType.getTypeName().equalsIgnoreCase(
					WRITER_STRING);
		}
		return returnValue;
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#isReader(no.ica.fraf.model.ApplUserType)
	 */
	public boolean isReader(final ApplUserType applUserType) {
		boolean returnValue = false;
		if (applUserType != null) {
			returnValue = applUserType.getTypeName().equalsIgnoreCase(
					READER_STRING);
		}
		return returnValue;
	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#isRegnskap(no.ica.fraf.model.ApplUserType)
	 */
	public boolean isRegnskap(final ApplUserType applUserType) {
		boolean returnValue = false;
		if (applUserType != null) {
			returnValue = applUserType.getTypeName().equalsIgnoreCase(
					REGNSKAP_STRING);
		}
		return returnValue;
	}

	/**
	 * Finner basert på objekt
	 * 
	 * @param object
	 * @return brukertype
	 */
	public List<ApplUserType> findByObject(ApplUserType object) {
		return findByExampleLike(object);
	}

	/**
	 * Fjerner brukertype
	 * 
	 * @param object
	 */
	public void removeObject(ApplUserType object) {
		removeObject(object);

	}

	/**
	 * Oppdaterer brukertype
	 * 
	 * @param object
	 */
	public void refreshObject(ApplUserType object) {
		refreshObject(object);

	}

	/**
	 * @see no.ica.fraf.dao.ApplUserTypeDAO#isMarked(no.ica.fraf.model.ApplUserType)
	 */
	public boolean isMarked(ApplUserType applUserType) {
		boolean returnValue = false;
		if (applUserType != null) {
			returnValue = applUserType.getTypeName().equalsIgnoreCase(
					MARKED_STRING);
		}
		return returnValue;
	}

}
