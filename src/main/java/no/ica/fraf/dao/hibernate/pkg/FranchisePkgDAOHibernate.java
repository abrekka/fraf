package no.ica.fraf.dao.hibernate.pkg;

import java.util.Date;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.hibernate.procedures.FakturerPeriodeStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.FakturerPerioderStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.LagKredittnotaLinjerStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.LagKredittnotaStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.RegenererStoredProcedure;
import no.ica.fraf.dao.hibernate.procedures.FakturerPeriodeStoredProcedure.FakturerPeriodeStoredProcedureSql;
import no.ica.fraf.dao.hibernate.procedures.FakturerPerioderStoredProcedure.FakturerPerioderStoredProcedureSql;
import no.ica.fraf.dao.hibernate.procedures.LagKredittnotaLinjerStoredProcedure.LagKredittnotaLinjerStoredProcedureSql;
import no.ica.fraf.dao.hibernate.procedures.LagKredittnotaStoredProcedure.LagKredittnotaStoredProcedureSql;
import no.ica.fraf.dao.hibernate.procedures.RegenererStoredProcedure.RegenererStoredProcedureSql;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.gui.FrafMain;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implementasjon av FranchisePkgDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class FranchisePkgDAOHibernate extends JdbcDaoSupport implements
		FranchisePkgDAO {
	/**
	 * True dersom det kjøres mot testdatabase
	 */
	private boolean test = false;

	public Integer fakturerPerioder(final Integer userId, final Integer aar,
			final Integer fraPeriode, final Integer tilPeriode,
			final Integer fraAvdNr, final Integer tilAvdNr,
			final Date fakturaDato, final Date forfallDato,
			final Integer avregningBasisId, final Integer gruppeId,
			final Integer[] betingelseGrupper, final Integer betingelseTypeId,
			final Integer[] notDepartments, final Integer avregningType,
			final Integer selskapId) throws FrafException {
		FakturerPerioderStoredProcedure fakturerProcedure= new FakturerPerioderStoredProcedure(
					getJdbcTemplate(), FakturerPerioderStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		final java.sql.Date sqlFakturaDate = new java.sql.Date(fakturaDato
				.getTime());
		final java.sql.Date sqlForfallDate = new java.sql.Date(forfallDato
				.getTime());
		return fakturerProcedure.execute(userId, aar, fraPeriode, tilPeriode,
				fraAvdNr, tilAvdNr, sqlFakturaDate, sqlForfallDate,
				avregningBasisId, gruppeId, betingelseGrupper,
				betingelseTypeId, notDepartments, avregningType, selskapId);

	}

	/**
	 * @see no.ica.fraf.dao.pkg.FranchisePkgDAO#getTest()
	 */
	public boolean getTest() {
		return test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.FranchisePkgDAO#setTest(boolean)
	 */
	public void setTest(final boolean test) {
		this.test = test;
	}

	/**
	 * @see no.ica.fraf.dao.pkg.FranchisePkgDAO#lagKredittnota(java.lang.Integer,
	 *      java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public Integer lagKredittnota(final Integer userId,
			final Integer fakturaId, final Date invoiceDate, final Date dueDate)
			throws FrafException {
		LagKredittnotaStoredProcedure kredProcedure= new LagKredittnotaStoredProcedure(
					getJdbcTemplate(), LagKredittnotaStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());

		final java.sql.Date sqlFakturaDate = new java.sql.Date(invoiceDate
				.getTime());
		final java.sql.Date sqlForfallDate = new java.sql.Date(dueDate
				.getTime());
		return kredProcedure.execute(userId, fakturaId, sqlFakturaDate,
				sqlForfallDate);

	}

	public Integer fakturerPeriode(final Integer userId, final Integer aar,
			final Integer periode, final Integer fraAvdNr,
			final Integer tilAvdNr, final Date fakturaDato,
			final Date forfallDato, final Integer avregningBasisId,
			final Integer gruppeId, final Integer[] betingelseGrupper,
			final Integer betingelseTypeId, final Integer[] notDepartments,
			final Integer avregningType, final Integer selskapId)
			throws FrafException {
		FakturerPeriodeStoredProcedure fakturerProcedure = new FakturerPeriodeStoredProcedure(
					getJdbcTemplate(), FakturerPeriodeStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		
		final java.sql.Date sqlFakturaDate = new java.sql.Date(fakturaDato
				.getTime());
		final java.sql.Date sqlForfallDate = new java.sql.Date(forfallDato
				.getTime());
		return fakturerProcedure.execute(userId, aar, periode, fraAvdNr,
				tilAvdNr, sqlFakturaDate, sqlForfallDate, avregningBasisId,
				gruppeId, betingelseGrupper, betingelseTypeId, notDepartments,
				avregningType, selskapId);
	}

	/**
	 * @see no.ica.fraf.dao.pkg.FranchisePkgDAO#regenerer(java.lang.Integer,
	 *      java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public void regenerer(Integer userId, Integer fakturaId, Date invoiceDate,
			Date dueDate) throws FrafException {
		RegenererStoredProcedure regenererProcedure = new RegenererStoredProcedure(
					getJdbcTemplate(), RegenererStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());

		final java.sql.Date sqlFakturaDate = new java.sql.Date(invoiceDate
				.getTime());
		final java.sql.Date sqlForfallDate = new java.sql.Date(dueDate
				.getTime());
		regenererProcedure.execute(userId, fakturaId, sqlFakturaDate,
				sqlForfallDate);

	}

	/**
	 * @see no.ica.fraf.dao.pkg.FranchisePkgDAO#lagKredittnotaLinjer(java.lang.Integer,
	 *      java.lang.Integer, java.util.Date, java.util.Date,
	 *      java.lang.Integer[])
	 */
	public Integer lagKredittnotaLinjer(Integer userId, Integer fakturaId,
			Date invoiceDate, Date dueDate, Integer[] linjeIder)
			throws FrafException {
		LagKredittnotaLinjerStoredProcedure kredittnotaLinjerProcedure = new LagKredittnotaLinjerStoredProcedure(
					getJdbcTemplate(),
					LagKredittnotaLinjerStoredProcedureSql.getEnum(test, FrafMain.isStandalone()).getSql());
		
		final java.sql.Date sqlFakturaDate = new java.sql.Date(invoiceDate
				.getTime());
		final java.sql.Date sqlForfallDate = new java.sql.Date(dueDate
				.getTime());
		return kredittnotaLinjerProcedure.execute(userId, fakturaId,
				sqlFakturaDate, sqlForfallDate, linjeIder);
	}

}
