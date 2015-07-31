package no.ica.fraf.dao.hibernate;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.SapDepartmentDAO;
import no.ica.fraf.model.Department;
import no.ica.fraf.model.SapDepartment;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SapDepartmentDAOHibernate extends AbstractDepartmentDAO implements SapDepartmentDAO {
	private static final String NAME_COLUMN="avdelingNavn";
	public SapDepartmentDAOHibernate() {
		super(SapDepartment.class);
	}

	public List<SapDepartment> findAll(final Integer depFrom, final Integer depTo) {
		return (List<SapDepartment>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(final Session session){
				return session.createCriteria(SapDepartment.class).add(Restrictions.between("avdnr", depFrom, depTo)).list();
			}
		});
	}

	public void saveBatch(List<SapDepartment> departments) {
		int row=0;
		for(SapDepartment department:departments){
			row++;
				getHibernateTemplate().saveOrUpdate(department);
			if(row%20==0){
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		
	}

	@Override
	protected String getNameColumnName() {
		return NAME_COLUMN;
	}

	

	

	

	
	

	

}
