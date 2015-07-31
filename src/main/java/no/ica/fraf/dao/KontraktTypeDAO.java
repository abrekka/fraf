package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.enums.LazyLoadKontraktTypeEnum;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.KontraktType;

/**
 * DAO for KONTRAKT_TYPE
 * 
 * @author abr99
 * 
 */
public interface KontraktTypeDAO extends DAO<KontraktType>, Comboable, DaoInterface<KontraktType> {
	String DAO_NAME = "kontraktTypeDAO";

	/**
	 * Henter kontrakttype
	 * 
	 * @param kontraktTypeId
	 * @return kontrakttype
	 */
	KontraktType getKontraktType(String kontraktTypeId);

	/**
	 * Lagrer kontrakttype
	 * 
	 * @param kontraktType
	 */
	void saveKontraktType(KontraktType kontraktType);

	/**
	 * Sletter kontrakttype
	 * 
	 * @param kontraktTypeId
	 */
	void removeKontraktType(String kontraktTypeId);

	/**
	 * Finner alle kontrakttyper
	 * 
	 * @return alle kontrakttyper
	 */
	List<KontraktType> findAll();

	/**
	 * Finner kontrakttype ved hjelp av kode
	 * 
	 * @param kode
	 * @return kontrakttype
	 */
	KontraktType findByKode(String kode);

	/**
	 * Lazy laster
	 * 
	 * @param kontraktType
	 * @param lazyLoads
	 */
	void loadLazy(KontraktType kontraktType,
			LazyLoadKontraktTypeEnum[] lazyLoads);
}
