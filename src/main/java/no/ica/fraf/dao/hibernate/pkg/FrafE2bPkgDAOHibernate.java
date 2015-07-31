package no.ica.fraf.dao.hibernate.pkg;

import no.ica.elfa.dao.pkg.E2bPkgDAO;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.FrafGetSequenceNumberStoredProcedure;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av DAO for pakke FRAF_E2B_PKG
 * 
 * @author abr99
 * 
 */
public class FrafE2bPkgDAOHibernate extends JdbcDaoSupport implements E2bPkgDAO {
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

	/**
	 * @see no.ica.elfa.dao.pkg.E2bPkgDAO#getSequenceNumber()
	 */
	public Integer getSequenceNumber() throws FrafException {
		FrafGetSequenceNumberStoredProcedure frafGetSequenceNumberStoredProcedure;

		if (test) {
			frafGetSequenceNumberStoredProcedure = new FrafGetSequenceNumberStoredProcedure(
					getJdbcTemplate(),
					FrafGetSequenceNumberStoredProcedure.SQL_TEST);
		} else {
			frafGetSequenceNumberStoredProcedure = new FrafGetSequenceNumberStoredProcedure(
					getJdbcTemplate(), FrafGetSequenceNumberStoredProcedure.SQL);
		}
		return frafGetSequenceNumberStoredProcedure.execute();
	}

}
