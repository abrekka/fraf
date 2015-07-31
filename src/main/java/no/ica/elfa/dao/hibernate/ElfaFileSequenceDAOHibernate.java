package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.FileSequenceDAO;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO som henter ut filsekvens
 * 
 * @author abr99
 * 
 */
public class ElfaFileSequenceDAOHibernate extends BaseDAOHibernate<Integer>
		implements FileSequenceDAO {
	/**
	 * 
	 */
	public ElfaFileSequenceDAOHibernate() {
		super(Integer.class);
	}

	/**
	 * 
	 */
	private final static String SQL_STRING = "select seq_ps_file.nextval as seq from dual";

	/**
	 * 
	 */
	private final static String SQL_STRING_TEST = "select seq_ps_file_test.nextval as seq from dual";

	/**
	 * 
	 */
	Boolean test;

	/**
	 * @param isTest
	 */
	public final void setTest(final Boolean isTest) {
		this.test = isTest;
	}

	/**
	 * @see no.ica.elfa.dao.FileSequenceDAO#getNextNumber()
	 */
	public final Integer getNextNumber() {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
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
