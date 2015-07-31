package no.ica.fraf.dao.hibernate;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.view.NyAvdelingVDAO;
import no.ica.fraf.model.NyAvdelingVInterface;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public abstract class AbstractNyAvdelingVDAOHibernate extends
		BaseDAOHibernate<NyAvdelingVInterface> implements NyAvdelingVDAO {

	public AbstractNyAvdelingVDAOHibernate(Class<?> aClass) {
		super(aClass);
	}

	public List<NyAvdelingVInterface> findByDate(final Date fromDate) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(clazz);
				criteria = fromDate != null ? criteria.add(Restrictions.ge(
						"dtStart", fromDate)) : criteria;
				return criteria.list();
			}

		});
	}

	public List getListData() {
		return getObjects();
	}
}
