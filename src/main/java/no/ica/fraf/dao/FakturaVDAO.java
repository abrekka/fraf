package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaV;
import no.ica.fraf.model.FakturaVInterface;

/**
 * DAO for FAKTURA_V
 * 
 * @author abr99
 * 
 */
public interface FakturaVDAO extends DAO<FakturaVInterface> {
	String DAO_NAME = "fakturaVDAO";

	/**
	 * Finner faktura basert på id
	 * 
	 * @param fakturaVId
	 * @return faktura
	 */
	FakturaVInterface getFakturaV(Integer fakturaVId);

	/**
	 * Finner alle fakturaer
	 * 
	 * @return alle fakturaer
	 */
	List findAll();

	/**
	 * Finner fakturaer basert på buntid
	 * 
	 * @param buntId
	 * @return fakturaer basert på buntid
	 */
	List<FakturaVInterface> findByBuntId(Integer buntId);

	/**
	 * Finner fakturaer basert på fakturaid
	 * 
	 * @param fakturaId
	 * @return fakturaer basert på fakturaid
	 */
	List<FakturaVInterface> findByFakturaId(Integer fakturaId);

	/**
	 * Laster bunt
	 * 
	 * @param faktura
	 */
	void loadBunt(Faktura faktura);

	/**
	 * Finner basert på buntider
	 * 
	 * @param buntIds
	 * @return fakturaer
	 */
	List<FakturaVInterface> findByBuntIds(List<Integer> buntIds);
}
