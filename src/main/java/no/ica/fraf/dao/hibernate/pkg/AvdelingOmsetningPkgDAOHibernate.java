package no.ica.fraf.dao.hibernate.pkg;

import java.math.BigDecimal;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.LesInnOmsetningStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.SettInnManueltBudsjettStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.LesInnOmsetningStoredProcedure.LesInnOmsetningStoredProcedureSql;
import no.ica.fraf.dao.hibernate.procedures.SettInnManueltBudsjettStoredProcedure.SettInnManueltBudsjettStoredProcedureSql;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.gui.FrafMain;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av AvdelingOmsetningPkgDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingOmsetningPkgDAOHibernate extends JdbcDaoSupport implements
		AvdelingOmsetningPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @see no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO#getTest()
	 */
	public boolean getTest() {
		return test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO#setTest(boolean)
	 */
	public void setTest(final boolean test) {
		this.test = test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO#lesInnOmsetning(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	public Integer lesInnOmsetning(final Integer basisTypeId,
			final Integer userId, final Integer aar, final Integer periode, final Integer fraAvdNr,
			final Integer tilAvdNr) throws FrafException {
		LesInnOmsetningStoredProcedure omsProcedure = new LesInnOmsetningStoredProcedure(
				getJdbcTemplate(), LesInnOmsetningStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		
		/*if (test) {
			omsProcedure = new LesInnOmsetningStoredProcedure(
					getJdbcTemplate(), LesInnOmsetningStoredProcedure.SQL_TEST);
		} else {
			omsProcedure = new LesInnOmsetningStoredProcedure(
					getJdbcTemplate(), LesInnOmsetningStoredProcedure.SQL);
		}*/
		return omsProcedure.execute(basisTypeId,
				userId, aar, periode, fraAvdNr, tilAvdNr);
	}

	/**
	 * @see no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO#settInnManueltBudsjett(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.math.BigDecimal,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	public void settInnManueltBudsjett(final Integer userId, final Integer avdnr,
			final Integer aar, final BigDecimal belopAar, final Integer avdelingId, final Integer buntId)
			throws FrafException {
		SettInnManueltBudsjettStoredProcedure budsjettProcedure = new SettInnManueltBudsjettStoredProcedure(
					getJdbcTemplate(),
					SettInnManueltBudsjettStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		budsjettProcedure.execute(userId, avdnr, aar,
				belopAar, avdelingId, buntId);

	}

}
