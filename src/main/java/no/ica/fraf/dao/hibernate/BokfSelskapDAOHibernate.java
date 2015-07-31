package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.model.BokfSelskap;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BokfSelskapDAO
 * 
 * @author abr99
 * 
 */
public class BokfSelskapDAOHibernate extends BaseDAOHibernate<BokfSelskap>
		implements BokfSelskapDAO {

	/**
	 * 
	 */
	public BokfSelskapDAOHibernate() {
		super(BokfSelskap.class);
	}

	/**
	 * @see no.ica.fraf.dao.BokfSelskapDAO#getBokfSelskap(java.lang.Integer)
	 */
	public BokfSelskap getBokfSelskap(final Integer selskapId) {
		final BokfSelskap bokfSelskap = (BokfSelskap) getHibernateTemplate()
				.get(BokfSelskap.class, selskapId);

		return bokfSelskap;
	}

	/**
	 * @see no.ica.fraf.dao.BokfSelskapDAO#saveBokfSelskap(no.ica.fraf.model.BokfSelskap)
	 */
	public void saveBokfSelskap(final BokfSelskap bokfSelskap) {
		getHibernateTemplate().saveOrUpdate(bokfSelskap);
	}

	/**
	 * @see no.ica.fraf.dao.BokfSelskapDAO#removeBokfSelskap(java.lang.Integer)
	 */
	public void removeBokfSelskap(final Integer selskapId) {
		final BokfSelskap selskap = getBokfSelskap(selskapId);
		if (selskap != null) {
			getHibernateTemplate().delete(selskap);
		}
	}

	/**
	 * @see no.ica.fraf.dao.BokfSelskapDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<BokfSelskap> findAll() {
		return getHibernateTemplate().find("from BokfSelskap");
	}

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List<BokfSelskap> getComboValues(final Object object) {
		final List<BokfSelskap> list = findAll();
		list.add(0, null);
		return list;
	}

	/**
	 * @see no.ica.fraf.dao.BokfSelskapDAO#findByName(java.lang.String)
	 */
	public BokfSelskap findByName(final String selskapNavn) {
		return (BokfSelskap) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final List list = session.createCriteria(
								BokfSelskap.class).add(
								Restrictions.ilike("selskapNavn", selskapNavn))
								.list();
						if (list == null || list.size() != 1) {
							return null;
						}
						return list.get(0);
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	public List<BokfSelskap> findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void deleteObjects(List objects) {
		if (objects == null) {
			return;
		}
		List<BokfSelskap> list = objects;

		for (BokfSelskap bokfSelskap : list) {
			removeBokfSelskap(bokfSelskap.getSelskapId());
		}
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void persistObjects(List objects) {
		if (objects == null) {
			return;
		}
		List<BokfSelskap> list = objects;

		for (BokfSelskap bokfSelskap : list) {
			saveBokfSelskap(bokfSelskap);
		}
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeBokfSelskap(((BokfSelskap) object).getSelskapId());

	}

	/**
	 * Finner basert på objekt
	 * 
	 * @param object
	 * @return selskaper
	 */
	public List<BokfSelskap> findByObject(BokfSelskap object) {
		return findByExampleLike(object);
	}

	/**
	 * @param object
	 */
	public void removeObject(BokfSelskap object) {
		getHibernateTemplate().delete(object);

	}

	/**
	 * @param object
	 */
	@Override
	public void saveObject(BokfSelskap object) {
		getHibernateTemplate().saveOrUpdate(object);

	}

	/**
	 * @param object
	 */
	public void refreshObject(final BokfSelskap object) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				session.refresh(object);
				return null;
			}

		});

	}

}
