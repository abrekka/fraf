package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.SapDistrictManagerDAO;
import no.ica.fraf.model.DistrictManager;
import no.ica.fraf.model.SapDistrictManager;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SapDistrictManagerDAOHibernate extends BaseDAOHibernate<DistrictManager>
		implements SapDistrictManagerDAO {
	public SapDistrictManagerDAOHibernate() {
		super(SapDistrictManager.class);
	}

	public List<SapDistrictManager> findAll() {
		return (List<SapDistrictManager>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						return session.createCriteria(SapDistrictManager.class).list();
					}
				});
	}

	public void saveBatch(List<SapDistrictManager> managers) {
		int row = 0;
		for (SapDistrictManager districtManager : managers) {
			row++;
			getHibernateTemplate().saveOrUpdate(districtManager);
			if (row % 20 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();

	}

	public List<String> findAllNames() {
		return getHibernateTemplate().find(
        "select distinct districtManagerName from SapDistrictManager order by districtManagerName");
	}

}
