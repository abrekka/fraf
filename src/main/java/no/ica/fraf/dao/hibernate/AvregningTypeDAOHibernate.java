package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.model.AvregningType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av AvregningTypeDAO
 * 
 * @author abr99
 * 
 */
public class AvregningTypeDAOHibernate extends BaseDAOHibernate<AvregningType> implements
		AvregningTypeDAO {
	public AvregningTypeDAOHibernate() {
		super(AvregningType.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvregningTypeDAO#getAvregningType(java.lang.Integer)
	 */
	public AvregningType getAvregningType(final Integer typeId) {
		final AvregningType avregningType = (AvregningType) getHibernateTemplate()
				.get(AvregningType.class, typeId);

		if (avregningType == null) {
			throw new ObjectRetrievalFailureException(AvregningType.class,
					typeId);
		}

		return avregningType;
	}

	/**
	 * @see no.ica.fraf.dao.AvregningTypeDAO#saveAvregningType(no.ica.fraf.model.AvregningType)
	 */
	public void saveAvregningType(final AvregningType avregningType) {
		getHibernateTemplate().saveOrUpdate(avregningType);
	}

	/**
	 * @see no.ica.fraf.dao.AvregningTypeDAO#removeAvregningType(java.lang.Integer)
	 */
	public void removeAvregningType(final Integer typeId) {
		getHibernateTemplate().delete(getAvregningType(typeId));
	}

	/**
	 * @see no.ica.fraf.dao.AvregningTypeDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<AvregningType> findAll() {
		return getHibernateTemplate().find("from AvregningType");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	public List<AvregningType> findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List getComboValues(final Object object) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		AvregningType avregningType;

		while (objectIt.hasNext()) {
			avregningType = (AvregningType) objectIt.next();
			removeAvregningType(avregningType.getAvregningTypeId());
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
	 */
	public void persistObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		AvregningType avregningType;

		while (objectIt.hasNext()) {
			avregningType = (AvregningType) objectIt.next();
			saveAvregningType(avregningType);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeAvregningType(((AvregningType) object).getAvregningTypeId());

	}

	/**
	 * @see no.ica.fraf.dao.AvregningTypeDAO#findByKode(java.lang.String)
	 */
	public AvregningType findByKode(final String avregningTypeKode) {
		return (AvregningType) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final List list = session.createCriteria(
								AvregningType.class).add(
								Restrictions.ilike("avregningTypeKode",
										avregningTypeKode)).list();
						if (list == null || list.size() != 1) {
							return null;
						}
						return list.get(0);
					}

				});
	}

}
