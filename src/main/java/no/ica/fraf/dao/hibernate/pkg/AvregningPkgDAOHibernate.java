package no.ica.fraf.dao.hibernate.pkg;

import java.util.Date;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.KjorAvregningStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.LagFakturaerStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.KjorAvregningStoredProcedure.KjorAvregningStoredProcedureSql;
import no.ica.fraf.dao.hibernate.procedures.LagFakturaerStoredProcedure.LagFakturaerStoredProcedureSql;
import no.ica.fraf.dao.pkg.AvregningPkgDAO;
import no.ica.fraf.gui.FrafMain;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av DAO mot pakke AVREGNING_PKG
 * 
 * @author abr99
 * 
 */
public class AvregningPkgDAOHibernate extends JdbcDaoSupport implements
		AvregningPkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	/**
	 * @return testmodus
	 */
	public boolean getTest() {
		return test;
	}

	/**
	 * @param test
	 */
	public void setTest(final boolean test) {
		this.test = test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.AvregningPkgDAO#kjorAvregning(java.lang.Integer)
	 */
	public void kjorAvregning(Integer buntId) throws FrafException {
		KjorAvregningStoredProcedure kjorAvregningProcedure = new KjorAvregningStoredProcedure(
					getJdbcTemplate(), KjorAvregningStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		kjorAvregningProcedure.execute(buntId);

	}

	/**
	 * @see no.ica.fraf.dao.pkg.AvregningPkgDAO#lagFakturaer(java.lang.Integer, java.util.Date, java.util.Date, java.lang.String)
	 */
	public void lagFakturaer(Integer buntId, Date invoiceDate, Date dueDate,String userName)
			throws FrafException {
		LagFakturaerStoredProcedure lagFakturaerStoredProcedure = new LagFakturaerStoredProcedure(
					getJdbcTemplate(), LagFakturaerStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		lagFakturaerStoredProcedure.execute(buntId, invoiceDate, dueDate,userName);

	}

}
