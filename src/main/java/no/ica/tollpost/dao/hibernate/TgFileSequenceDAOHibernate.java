package no.ica.tollpost.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import no.ica.elfa.dao.FileSequenceDAO;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TgFileSequenceDAOHibernate extends BaseDAOHibernate<Integer>
		implements FileSequenceDAO {
	public TgFileSequenceDAOHibernate() {
		super(Integer.class);
	}

	private final static String SQL_STRING = "select seq_tg_ps_file.nextval as seq from dual";

	private final static String SQL_STRING_TEST = "select seq_tg_ps_file_test.nextval as seq from dual";
	
	private Boolean test;

	public void setTest(Boolean test) {
		this.test = test;
	}

	public Integer getNextNumber() {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String sqlString;
						if (test) {
							sqlString = SQL_STRING_TEST;
						} else {
							sqlString = SQL_STRING;
						}
						List<Integer> list = session.createSQLQuery(sqlString)
								.addScalar("seq", Hibernate.INTEGER).list();
						return list.get(0);
					}

				});
	}


}
