package no.ica.fraf.dao;

import java.util.Collection;
import java.util.List;

import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.xml.InvoiceManagerInterface;

/**
 * DAO for FAKTURA
 * 
 * @author abr99
 * 
 */
public interface FakturaDAO extends DAO<Faktura>, Comboable, DaoInterface<Faktura>,
		InvoiceManagerInterface {

	String DAO_NAME = "fakturaDAO";
	/**
	 * Henter faktura
	 * 
	 * @param fakturaId
	 * @return faktura
	 */
	Faktura getFaktura(Integer fakturaId);

	/**
	 * Lagerer faktura
	 * 
	 * @param aaktura
	 */
	void saveFaktura(Faktura aaktura);

	/**
	 * Sletter faktura
	 * 
	 * @param fakturaId
	 */
	void removeFaktura(Integer fakturaId);

	/**
	 * Finner alle fakturaer
	 * 
	 * @return alle fakturaer
	 */
	List findAll();

	/**
	 * Lazyloader basert på type
	 * 
	 * @param faktura
	 * @param loadType
	 */
	void loadLazy(Faktura faktura, LazyLoadFakturaEnum[] loadType);

	/**
	 * Finner fakturaer for en gitt bunt
	 * 
	 * @param buntId
	 * @return fakturaer for en gitt bunt
	 */
	List<Faktura> findByBuntId(Integer buntId);
	/**
	 * Finner fakturaer for en gitt bunt samt laster tekst og fakturalinjer
	 * @param buntId
	 * @return fakturaer
	 */
	Collection<Faktura> findByBuntIdLoad(Integer buntId);

	/**
	 * Finner fakturaer basert på buntid og avdnr
	 * 
	 * @param buntId
	 * @param avdnr
	 * @return fakturaer basert på buntid og avdnr
	 */
	List<Faktura> findByBuntIdAndAvdnr(Integer buntId, Integer avdnr);

	/**
	 * Lazy laster bunt
	 * 
	 * @param faktura
	 */
	void lazyLoadBunt(Faktura faktura);

	/**
	 * Finner fakturaer med gitte bunt id'er
	 * 
	 * @param buntIds
	 * @param orderBy 
	 * @return fakturaer
	 */
	public Collection<Faktura> findByBuntIds(final List<Integer> buntIds,String orderBy);
	void removeForAvdeling(Avdeling avdeling);
	Collection<Faktura> findByFakturaNr(List<String> invoiceNumbers);
}
