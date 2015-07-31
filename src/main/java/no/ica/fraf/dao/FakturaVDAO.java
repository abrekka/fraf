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
	 * Finner faktura basert p� id
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
	 * Finner fakturaer basert p� buntid
	 * 
	 * @param buntId
	 * @return fakturaer basert p� buntid
	 */
	List<FakturaVInterface> findByBuntId(Integer buntId);

	/**
	 * Finner fakturaer basert p� fakturaid
	 * 
	 * @param fakturaId
	 * @return fakturaer basert p� fakturaid
	 */
	List<FakturaVInterface> findByFakturaId(Integer fakturaId);

	/**
	 * Laster bunt
	 * 
	 * @param faktura
	 */
	void loadBunt(Faktura faktura);

	/**
	 * Finner basert p� buntider
	 * 
	 * @param buntIds
	 * @return fakturaer
	 */
	List<FakturaVInterface> findByBuntIds(List<Integer> buntIds);
}
