
package no.ica.fraf.dao.pkg;

import java.util.Date;

import no.ica.fraf.FrafException;

/**
 * Interface for DAO mot databasepakke FRANCHISE_PKG
 * 
 * @author abr99
 * 
 */
public interface FranchisePkgDAO {
	String NAME = "franchisePkgDAO";

	/**
	 * Fakturerer perioder
	 * 
	 * @param userId
	 * @param aar
	 * @param fraPeriode
	 * @param tilPeriode
	 * @param fraAvdNr
	 * @param tilAvdNr
	 * @param fakturaDato
	 * @param forfallDato
	 * @param avregningBasisId
	 * @param betingelseGruppeId
	 * @param betingelseTypeId
	 * @param notDepartments
	 * @param fakturerAvregningType
	 * @param selskapId
	 * @return bunt id
	 * @throws FrafException
	 */
	public Integer fakturerPerioder(Integer userId, Integer aar,
			Integer fraPeriode, Integer tilPeriode, Integer fraAvdNr,
			Integer tilAvdNr, Date fakturaDato, Date forfallDato,
			Integer avregningBasisId, 
			Integer betingelseGruppeId,
			Integer[] betingelseGrupper,
			Integer betingelseTypeId, Integer[] notDepartments,
			Integer fakturerAvregningType, Integer selskapId)
			throws FrafException;

	/**
	 * Fakturerer periode
	 * 
	 * @param userId
	 * @param aar
	 * @param periode
	 * @param fraAvdNr
	 * @param tilAvdNr
	 * @param fakturaDato
	 * @param forfallDato
	 * @param avregningBasisId
	 * @param betingelseGruppeId
	 * @param betingelseTypeId
	 * @param notDepartments
	 * @param fakturerAvregningType
	 * @param selskapId
	 * @return fakturabuntid
	 * @throws FrafException
	 */
	public Integer fakturerPeriode(Integer userId, Integer aar,
			Integer periode, Integer fraAvdNr, Integer tilAvdNr,
			Date fakturaDato, Date forfallDato, Integer avregningBasisId,
			Integer betingelseGruppeId,
			Integer[] betingelseGrupper,
			Integer betingelseTypeId,
			Integer[] notDepartments, Integer fakturerAvregningType,
			Integer selskapId) throws FrafException;

	/**
	 * Lager kredittnota
	 * 
	 * @param userId
	 * @param fakturaId
	 * @param invoiceDate
	 * @param dueDate
	 * @return buntid
	 * @throws FrafException
	 */
	public Integer lagKredittnota(Integer userId, Integer fakturaId,
			Date invoiceDate, Date dueDate) throws FrafException;

	/**
	 * Setter om database er test
	 * 
	 * @param test
	 */
	public void setTest(boolean test);

	/**
	 * Henter om database er test
	 * 
	 * @return true dersom test
	 */
	public boolean getTest();

	/**
	 * Regenerer faktura fra en kredittnota
	 * 
	 * @param userId
	 * @param fakturaId
	 * @param invoiceDate
	 * @param dueDate
	 * @throws FrafException
	 */
	public void regenerer(Integer userId, Integer fakturaId, Date invoiceDate,
			Date dueDate) throws FrafException;

	/**
	 * Lager kredittnota med gitte fakturalinjer
	 * 
	 * @param userId
	 * @param fakturaId
	 * @param invoiceDate
	 * @param dueDate
	 * @param linjeIder
	 * @return buntid
	 * @throws FrafException
	 */
	public Integer lagKredittnotaLinjer(Integer userId, Integer fakturaId,
			Date invoiceDate, Date dueDate, Integer[] linjeIder)
			throws FrafException;
}
