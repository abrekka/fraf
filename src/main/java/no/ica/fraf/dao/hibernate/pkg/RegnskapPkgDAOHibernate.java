package no.ica.fraf.dao.hibernate.pkg;



import java.util.Date;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.BokforBuntStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.BokforBuntStoredProcedure.BokforBuntStoredProcedureSql;
import no.ica.fraf.dao.pkg.RegnskapPkgDAO;
import no.ica.fraf.gui.FrafMain;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av RegnskapPkgDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class RegnskapPkgDAOHibernate extends JdbcDaoSupport implements
		RegnskapPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @see no.ica.fraf.dao.pkg.RegnskapPkgDAO#bokforBunt(java.lang.Integer,
	 *      java.sql.Date)
	 */
	public void bokforBunt(final Integer buntId, final Date bokforDato)
			throws FrafException {
		BokforBuntStoredProcedure buntProcedure = new BokforBuntStoredProcedure(
					getJdbcTemplate(), BokforBuntStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());

		
		final java.sql.Date sqlBokforDate = new java.sql.Date(bokforDato.getTime());
		
		buntProcedure.execute(buntId, sqlBokforDate);

	}

	/**
	 * @see no.ica.fraf.dao.pkg.RegnskapPkgDAO#getTest()
	 */
	public boolean getTest() {
		return test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.RegnskapPkgDAO#setTest(boolean)
	 */
	public void setTest(final boolean test) {
		this.test = test;
	}

}
