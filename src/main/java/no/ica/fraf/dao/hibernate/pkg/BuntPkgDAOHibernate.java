package no.ica.fraf.dao.hibernate.pkg;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.RullTilbakeBuntStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.SlettBuntStoredProcedure;
import no.ica.fraf.dao.pkg.BuntPkgDAO;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av BuntPkgDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class BuntPkgDAOHibernate extends JdbcDaoSupport implements BuntPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @see no.ica.fraf.dao.pkg.BuntPkgDAO#slettBunt(java.lang.Integer)
	 */
	public void slettBunt(final Integer buntId) throws FrafException {
		slettBunt(buntId, 0);
	}

	/**
	 * @see no.ica.fraf.dao.pkg.BuntPkgDAO#getTest()
	 */
	public boolean getTest() {
		return test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.BuntPkgDAO#setTest(boolean)
	 */
	public void setTest(final boolean test) {
		this.test = test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.BuntPkgDAO#slettBunt(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public void slettBunt(final Integer buntId, final Integer ignoreError)
			throws FrafException {
		SlettBuntStoredProcedure slettProcedure;

		if (test) {
			slettProcedure = new SlettBuntStoredProcedure(getJdbcTemplate(),
					SlettBuntStoredProcedure.SQL_TEST);
		} else {
			slettProcedure = new SlettBuntStoredProcedure(getJdbcTemplate(),
					SlettBuntStoredProcedure.SQL);
		}
		slettProcedure.execute(buntId, ignoreError);

	}

	/**
	 * @see no.ica.fraf.dao.pkg.BuntPkgDAO#rullTilbakeBunt(java.lang.Integer)
	 */
	public void rullTilbakeBunt(Integer buntId) throws FrafException {
		RullTilbakeBuntStoredProcedure rullTilbakeBuntProcedure;

		if (test) {
			rullTilbakeBuntProcedure = new RullTilbakeBuntStoredProcedure(
					getJdbcTemplate(), RullTilbakeBuntStoredProcedure.SQL_TEST);
		} else {
			rullTilbakeBuntProcedure = new RullTilbakeBuntStoredProcedure(
					getJdbcTemplate(), RullTilbakeBuntStoredProcedure.SQL);
		}
		rullTilbakeBuntProcedure.execute(buntId);

	}

}
