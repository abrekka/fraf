package no.ica.tollpost.dao.pkg.hibernate;

import no.ica.fraf.FrafException;
import no.ica.tollpost.dao.hibernate.procedures.FakturerStoredProcedure;
import no.ica.tollpost.dao.hibernate.procedures.SettBuntBokfortStoredProcedure;
import no.ica.tollpost.dao.pkg.TgFakturaPkgDAO;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class TgFakturaPkgDAOHibernate extends JdbcDaoSupport implements
TgFakturaPkgDAO {
	private boolean test = false;
	public void setTest(boolean test) {
		this.test = test;
		
	}
	public boolean getTest() {
		return test;
		
	}


	public void fakturer(Integer buntId)throws FrafException {
		FakturerStoredProcedure fakturerProcedure;

		if (test) {
			fakturerProcedure = new FakturerStoredProcedure(
					getJdbcTemplate(), FakturerStoredProcedure.SQL_TEST);
		} else {
			fakturerProcedure = new FakturerStoredProcedure(
					getJdbcTemplate(), FakturerStoredProcedure.SQL);
		}
		fakturerProcedure.execute(buntId);
		
	}
	public void settBuntBokfort(Integer buntId) throws FrafException{
		SettBuntBokfortStoredProcedure settBuntBokfortProcedure;

		if (test) {
			settBuntBokfortProcedure = new SettBuntBokfortStoredProcedure(
					getJdbcTemplate(), SettBuntBokfortStoredProcedure.SQL_TEST);
		} else {
			settBuntBokfortProcedure = new SettBuntBokfortStoredProcedure(
					getJdbcTemplate(), SettBuntBokfortStoredProcedure.SQL);
		}
		settBuntBokfortProcedure.execute(buntId);
		
	}

}
