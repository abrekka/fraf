package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.SapRegionDAO;
import no.ica.fraf.model.Region;
import no.ica.fraf.model.SapRegion;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SapRegionDAOHibernate extends BaseDAOHibernate<Region> 
		implements SapRegionDAO {
	public SapRegionDAOHibernate() {
		super(SapRegion.class);
	}

	public List<SapRegion> findAll(final Integer regionFrom,
			final Integer regionTo) {
		return (List<SapRegion>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						return session.createCriteria(SapRegion.class).add(
								Restrictions.between("region", regionFrom,
										regionTo)).list();
					}
				});
	}

	public void saveBatch(List<SapRegion> regions) {
		int row = 0;
		for (SapRegion region : regions) {
			row++;
			getHibernateTemplate().saveOrUpdate(region);
			if (row % 20 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();

	}

	public List<Region> findAll() {
		return getObjects();
	}

}
