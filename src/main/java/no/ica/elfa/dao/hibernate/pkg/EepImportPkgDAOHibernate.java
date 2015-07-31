package no.ica.elfa.dao.hibernate.pkg;

import no.ica.elfa.dao.hibernate.procedures.RunImportFileStoredProcedure;
import no.ica.elfa.dao.pkg.EepImportPkgDAO;
import no.ica.fraf.model.ApplUser;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av DAO mot pakke EEP_IMPORT
 * 
 * @author abr99
 * 
 */
public class EepImportPkgDAOHibernate extends JdbcDaoSupport implements
		EepImportPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @param isTest
	 */
	public final void setTest(final boolean isTest) {
		this.test = isTest;
	}

	/**
	 * @see no.ica.elfa.dao.pkg.EepImportPkgDAO#runImportFile(no.ica.fraf.model.ApplUser)
	 */
	public final void runImportFile(final ApplUser user) {
		RunImportFileStoredProcedure importProcedure;

		if (test) {
			importProcedure = new RunImportFileStoredProcedure(
					getJdbcTemplate(), RunImportFileStoredProcedure.SQL_TEST);
		} else {
			importProcedure = new RunImportFileStoredProcedure(
					getJdbcTemplate(), RunImportFileStoredProcedure.SQL);
		}
		Integer userId = null;
		if (user != null) {
			userId = user.getUserId();
		}
		importProcedure.execute(userId);

	}

}
