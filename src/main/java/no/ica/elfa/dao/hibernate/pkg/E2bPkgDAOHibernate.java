package no.ica.elfa.dao.hibernate.pkg;

import no.ica.elfa.dao.hibernate.procedures.GetSequenceNumberStoredProcedure;
import no.ica.elfa.dao.pkg.E2bPkgDAO;
import no.ica.fraf.FrafException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * DAO for uthenting av sekvensnummer for fil
 * 
 * @author abr99
 * 
 */
public class E2bPkgDAOHibernate extends JdbcDaoSupport implements E2bPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @see no.ica.elfa.dao.pkg.E2bPkgDAO#getSequenceNumber()
	 */
	public final Integer getSequenceNumber() throws FrafException {
		GetSequenceNumberStoredProcedure getSequenceNumberStoredProcedure;

		if (test) {
			getSequenceNumberStoredProcedure = new GetSequenceNumberStoredProcedure(
					getJdbcTemplate(),
					GetSequenceNumberStoredProcedure.SQL_TEST);
		} else {
			getSequenceNumberStoredProcedure = new GetSequenceNumberStoredProcedure(
					getJdbcTemplate(), GetSequenceNumberStoredProcedure.SQL);
		}
		return getSequenceNumberStoredProcedure.execute();
	}

	/**
	 * @see no.ica.elfa.dao.pkg.E2bPkgDAO#setTest(boolean)
	 */
	public final void setTest(final boolean isTest) {
		this.test = isTest;

	}

	/**
	 * @see no.ica.elfa.dao.pkg.E2bPkgDAO#getTest()
	 */
	public final boolean getTest() {
		return test;
	}

}
