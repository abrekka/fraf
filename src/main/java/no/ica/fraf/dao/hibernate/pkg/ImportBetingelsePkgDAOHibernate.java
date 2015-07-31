package no.ica.fraf.dao.hibernate.pkg;

import java.sql.Connection;
import java.sql.SQLException;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.ImportBetingelseStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.ImportBetingelseStoredProcedure.ImportBetingelseStoredProcedureSql;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.gui.FrafMain;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av ImportBetingelsePkgDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class ImportBetingelsePkgDAOHibernate extends JdbcDaoSupport implements
		ImportBetingelsePkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;
	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(ImportBetingelsePkgDAOHibernate.class);

	/**
	 * @see no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO#getTest()
	 */
	public boolean getTest() {
		return test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO#setTest(boolean)
	 */
	public void setTest(final boolean test) {
		this.test = test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO#importBetingelser(java.lang.Integer)
	 */
	public void importBetingelser(final Integer userId) {
		ImportBetingelseStoredProcedure betProcedure = new ImportBetingelseStoredProcedure(
					getJdbcTemplate(), ImportBetingelseStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		betProcedure.execute(userId);
	}

	/**
	 * @see no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO#getDbConnection()
	 */
	public Connection getDbConnection() throws FrafException {
		try {
			return this.getDataSource().getConnection();
		} catch (SQLException e) {
			logger.error("Feil i getDbConnection",e);
			//e.printStackTrace();
			throw new FrafException(e);
		}
	}
}
