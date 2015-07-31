package no.ica.fraf.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.DAO;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations. </p>
 * 
 * <p>
 * <a href="BaseDAOHibernate.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <E>
 */
public class BaseDAOHibernate<E> extends HibernateDaoSupport implements DAO<E> {
	/**
	 * 
	 */
	Class<?> clazz;

	/**
	 * @param aClass
	 */
	public BaseDAOHibernate(Class<?> aClass) {
		clazz = aClass;
	}

	/**
	 * @param object
	 * @see no.ica.fraf.dao.DAO#saveObject(Object)
	 */
	public void saveObject(final E object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	/**
	 * @see no.ica.fraf.dao.DAO#getObject(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public E getObject(final Serializable classId) {

		return (E) getHibernateTemplate().get(clazz, classId);

	}

	/**
	 * @see no.ica.fraf.dao.DAO#getObjects()
	 */
	@SuppressWarnings("unchecked")
	public List<E> getObjects() {
		return getHibernateTemplate().loadAll(clazz);
	}

	/**
	 * @see no.ica.fraf.dao.DAO#removeObject(java.io.Serializable)
	 */
	public void removeObject(final Serializable classId) {
		getHibernateTemplate().delete(getObject(classId));
	}

	/**
	 * Kanselerere oppdateringer
	 * 
	 * @param objects
	 */
	public void cancelObjectUpdates(final List<E> objects) {
		if (objects == null) {
			return;
		}

		for (E object : objects) {
			getHibernateTemplate().evict(object);
		}
	}

	/**
	 * Finner objekter basert på eksempel
	 * 
	 * @param example
	 * @return objekter
	 */
	@SuppressWarnings("unchecked")
	public List<E> find(E example) {
		return getHibernateTemplate().findByExample(example);
	}

	/**
	 * @param example
	 * @return liste med objekter
	 * @see no.ica.fraf.dao.DAO#findByExample(Object)
	 */
	@SuppressWarnings("unchecked")
	public List<E> findByExample(E example) {
		return getHibernateTemplate().findByExample(example);
	}

	/**
	 * @param example
	 * @return funnet objekt
	 * @see no.ica.fraf.dao.DAO#findByExampleLike(Object)
	 */
	/*@SuppressWarnings("unchecked")
	public List<E> findByExampleLike(E example) {
		Example exam = Example.create(example);
		exam.enableLike(MatchMode.ANYWHERE); // this is it.
		exam.ignoreCase();

		DetachedCriteria c = DetachedCriteria.forClass(clazz).add(exam);

		return getHibernateTemplate().findByCriteria(c);
	}*/

	/**
	 * @see no.ica.fraf.dao.DAO#findAllOrdered(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAllOrdered(final String orderBy) {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(clazz);
						criteria = orderBy != null ? criteria.addOrder(Order
								.asc(orderBy)) : criteria;
						return criteria.list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.DAO#findAllOrderedDesc(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAllOrderedDesc(final String orderBy) {
		return (List<E>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(clazz).addOrder(
								Order.desc(orderBy)).list();
					}

				});
	}

	protected Criteria getFindByExampleLikeCriteria(final E example){
		Example exam = Example.create(example);
        exam.enableLike(MatchMode.ANYWHERE); // this is it.
        exam.ignoreCase();
        
        Criteria criteria = getSession().createCriteria(clazz).add(exam);
        
        return getCriteria(example, criteria);
	}
	public final List<E> findByExampleLike(final E example) {
        Example exam = Example.create(example);
        exam.enableLike(MatchMode.ANYWHERE); // this is it.
        exam.ignoreCase();
        Criteria criteria = getSession().createCriteria(clazz).add(exam);
        
        criteria = getCriteria(example, criteria);

        // DetachedCriteria c = DetachedCriteria.forClass(clazz).add(exam);

        return criteria.list();
    }

    private Criteria getCriteria(final Object example, Criteria criteria) {
        Example exam = Example.create(example);
        exam.enableLike(MatchMode.ANYWHERE); // this is it.
        exam.ignoreCase();
        
        


        try {
            Field[] fields = example.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                
                if (field.getType().getCanonicalName().indexOf(
                        "no.ica.fraf.model") >= 0) {
                    Method method = example.getClass().getMethod(
                            "get" + StringUtils.capitalize(field.getName()),
                            (Class[]) null);
                    if (method.invoke(example, (Object[]) null) != null) {
                        Object object = method.invoke(example,
                                (Object[]) null);
                        Criteria childCriteria = criteria.createCriteria(field.getName()).add(
                                Example.create(object));
                        getCriteria(object, childCriteria);
                    
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return criteria;
    }

}
