package no.ica.fraf.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import no.ica.fraf.dao.SapSaleDAO;
import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.model.SapSale;

public class SapSaleDAOHibernate extends BaseDAOHibernate<SapSale> implements
		SapSaleDAO {
	public SapSaleDAOHibernate() {
		super(SapSale.class);
	}

	public void saveBatch(List<SapSale> sales) {
		int row = 0;
		for (SapSale sale : sales) {
			row++;
			getHibernateTemplate().saveOrUpdate(sale);
			if (row % 20 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();

	}

	public SapSale findByAvdnrPeriode(final Integer departmentNr,
			final Integer year, final Integer periode) {
		return (SapSale) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(final Session session) {
						List<SapSale> list= session.createCriteria(SapSale.class).add(
								Restrictions.eq("departmentNr", departmentNr))
								.add(Restrictions.eq("year", year)).add(
										Restrictions.eq("periode", periode))
								.list();
						return list!=null&&list.size()==1?list.get(0):null;
					}
				});
	}
}
