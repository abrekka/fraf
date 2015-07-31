package no.ica.fraf.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.enums.LazyLoadKontraktTypeEnum;
import no.ica.fraf.model.KontraktType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av KontraktTypeDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class KontraktTypeDAOHibernate extends BaseDAOHibernate<KontraktType> implements
		KontraktTypeDAO {
	public KontraktTypeDAOHibernate() {
		super(KontraktType.class);
	}

	/**
	 * @see no.ica.fraf.dao.KontraktTypeDAO#getKontraktType(java.lang.String)
	 */
	public KontraktType getKontraktType(final String typeId) {
		final KontraktType kontraktType = (KontraktType) getHibernateTemplate()
				.get(KontraktType.class, typeId);

		if (kontraktType == null) {
			throw new ObjectRetrievalFailureException(KontraktType.class,
					typeId);
		}

		return kontraktType;
	}

	/**
	 * @see no.ica.fraf.dao.KontraktTypeDAO#saveKontraktType(no.ica.fraf.model.KontraktType)
	 */
	public void saveKontraktType(final KontraktType kontraktType) {
		getHibernateTemplate().saveOrUpdate(kontraktType);
	}

	/**
	 * @see no.ica.fraf.dao.KontraktTypeDAO#removeKontraktType(java.lang.String)
	 */
	public void removeKontraktType(final String typeId) {
		getHibernateTemplate().delete(getKontraktType(typeId));
	}

	/**
	 * @see no.ica.fraf.dao.KontraktTypeDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<KontraktType> findAll() {
		return getHibernateTemplate().find("from KontraktType");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	public List<KontraktType> findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List getComboValues(final Object object) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.hibernate.BaseDAOHibernate#cancelObjectUpdates(java.util.List)
	 */
	@Override
	public void cancelObjectUpdates(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();

		while (objectIt.hasNext()) {
			getHibernateTemplate().evict(objectIt.next());
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		KontraktType kontraktType;

		while (objectIt.hasNext()) {
			kontraktType = (KontraktType) objectIt.next();
			removeKontraktType(kontraktType.getKontraktTypeId());
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
		KontraktType kontraktType;

		while (objectIt.hasNext()) {
			kontraktType = (KontraktType) objectIt.next();
			saveKontraktType(kontraktType);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeKontraktType(((KontraktType) object).getKontraktTypeId());

	}

	/**
	 * @see no.ica.fraf.dao.KontraktTypeDAO#findByKode(java.lang.String)
	 */
	public KontraktType findByKode(final String kode) {
		return (KontraktType) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<KontraktType> list = session.createCriteria(
								KontraktType.class).add(
								Restrictions.ilike("kontraktTypeKode", kode))
								.list();

						if (list == null || list.size() != 1) {
							return null;
						}
						return list.get(0);
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.KontraktTypeDAO#loadLazy(no.ica.fraf.model.KontraktType,
	 *      no.ica.fraf.enums.LazyLoadKontraktTypeEnum[])
	 */
	public void loadLazy(final KontraktType kontraktType,
			final LazyLoadKontraktTypeEnum[] lazyLoads) {
		if (kontraktType == null || kontraktType.getKontraktTypeId() == null) {
			return;
		}
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.load(kontraktType, kontraktType.getKontraktTypeId());

				List<Object> list;
				Set<?> set;

				for (LazyLoadKontraktTypeEnum lazyEnum : lazyLoads) {
					switch (lazyEnum) {

					case BETINGELSE:
						set = kontraktType.getKontraktBetingelses();
						list = new ArrayList<Object>(set);
						list.iterator();
						break;
					default:
						break;
					}
				}

				return null;
			}

		});

	}

}
