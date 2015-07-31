package no.ica.tollpost.dao.pkg.hibernate;

import no.ica.fraf.FrafException;
import no.ica.tollpost.dao.hibernate.procedures.ImporterStoredProcedure;
import no.ica.tollpost.dao.pkg.TgImportPkgDAO;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class TgImportPkgDAOHibernate extends JdbcDaoSupport implements
TgImportPkgDAO {
	private boolean test = false;
	public void setTest(boolean test) {
		this.test = test;
		
	}
	public boolean getTest() {
		return test;
		
	}
	public void importer(Integer[] meldingIder, Integer userId) throws FrafException{
		ImporterStoredProcedure importerProcedure;

		if (test) {
			importerProcedure = new ImporterStoredProcedure(
					getJdbcTemplate(), ImporterStoredProcedure.SQL_TEST);
		} else {
			importerProcedure = new ImporterStoredProcedure(
					getJdbcTemplate(), ImporterStoredProcedure.SQL);
		}
		importerProcedure.execute(meldingIder,userId);

		
	}


}
