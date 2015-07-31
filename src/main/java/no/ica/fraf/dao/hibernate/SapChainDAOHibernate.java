package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.SapChainDAO;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.SapChain;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SapChainDAOHibernate extends BaseDAOHibernate<Chain> implements
		SapChainDAO {
	public SapChainDAOHibernate() {
		super(SapChain.class);
	}

	public void saveBatch(List<SapChain> chains) {
		int row = 0;
		for (SapChain chain : chains) {
			row++;
			getHibernateTemplate().saveOrUpdate(chain);
			if (row % 20 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();

	}

	public List<SapChain> findAll(final Integer chainFrom, final Integer chainTo) {
		return (List<SapChain>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						return session.createCriteria(SapChain.class).add(
								Restrictions.between("kjede", chainFrom,
										chainTo)).list();
					}
				});
	}

	public List<Chain> findAll() {
		return getObjects();
	}

	public Chain findByName(final String name) {
		return (Chain)getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(final Session session){
				List<Chain> list = session.createCriteria(SapChain.class).add(Restrictions.eq("chainName", name)).list();
				if(list!=null&&list.size()==1){
					return list.get(0);
				}
				return null;
			}
		});
	}

	public List getComboValues(Object param) {
		return findAll();
	}

}
