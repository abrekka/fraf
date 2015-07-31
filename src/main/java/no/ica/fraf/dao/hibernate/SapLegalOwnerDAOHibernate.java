package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.SapLegalOwnerDAO;
import no.ica.fraf.model.SapLegalOwner;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SapLegalOwnerDAOHibernate extends BaseDAOHibernate<SapLegalOwner>
		implements SapLegalOwnerDAO {
	public SapLegalOwnerDAOHibernate() {
		super(SapLegalOwner.class);
	}

	public List<SapLegalOwner> findAll(final Integer nrFrom,
			final Integer nrTo) {
		return (List<SapLegalOwner>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						return session.createCriteria(SapLegalOwner.class).add(
								Restrictions.between("nr", nrFrom,
										nrTo)).list();
					}
				});
	}

	public void saveBatch(List<SapLegalOwner> legalOwners) {
		int row = 0;
		for (SapLegalOwner legalOwner : legalOwners) {
			row++;
			getHibernateTemplate().saveOrUpdate(legalOwner);
			if (row % 20 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();

	}

}
