package no.ica.tollpost.dao.pkg.hibernate;

import no.ica.elfa.dao.pkg.E2bPkgDAO;
import no.ica.fraf.FrafException;
import no.ica.tollpost.dao.hibernate.procedures.TgGetSequenceNumberStoredProcedure;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class TgE2bPkgDAOHibernate extends JdbcDaoSupport implements E2bPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;
	
	/**
	 * @see no.ica.elfa.dao.pkg.E2bPkgDAO#setTest(boolean)
	 */
	public void setTest(boolean test) {
		this.test = test;

	}

	/**
	 * @see no.ica.elfa.dao.pkg.E2bPkgDAO#getTest()
	 */
	public boolean getTest() {
		return test;
	}

	public Integer getSequenceNumber() throws FrafException {
		TgGetSequenceNumberStoredProcedure tgGetSequenceNumberStoredProcedure;

		if (test) {
			tgGetSequenceNumberStoredProcedure = new TgGetSequenceNumberStoredProcedure(
					getJdbcTemplate(),
					TgGetSequenceNumberStoredProcedure.SQL_TEST);
		} else {
			tgGetSequenceNumberStoredProcedure = new TgGetSequenceNumberStoredProcedure(
					getJdbcTemplate(), TgGetSequenceNumberStoredProcedure.SQL);
		}
		return tgGetSequenceNumberStoredProcedure.execute();
	}


}
