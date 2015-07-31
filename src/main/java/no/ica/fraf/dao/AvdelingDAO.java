package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;

/**
 * DAO for AVDELING
 * 
 * @author abr99
 * 
 */
public interface AvdelingDAO extends DAO<Avdeling> {

	String DAO_NAME = "avdelingDAO";
	/**
	 * Henter avdeling
	 * 
	 * @param avdelingId
	 * @return avdeling
	 */
	Avdeling getAvdeling(Integer avdelingId);

	/**
	 * Lagrer avdeling
	 * 
	 * @param avdeling
	 */
	void saveAvdeling(Avdeling avdeling);

	/**
	 * Sletter avdeling
	 * 
	 * @param avdelingId
	 */
	void removeAvdeling(Integer avdelingId);
	void removeAvdeling(Avdeling avdeling);

	/**
	 * Finner alle evdelinger
	 * 
	 * @return alle avdelinger
	 */
	List findAll();

	/**
	 * Lazy loader collections
	 * 
	 * @param avdeling
	 * @param lazyLoads
	 */
	void loadLazy(Avdeling avdeling, LazyLoadAvdelingEnum[] lazyLoads);

	/**
	 * Finner betingelser som tilhører franchisegruppen
	 * 
	 * @param avdeling
	 * @return betingelser
	 */
	List<AvdelingBetingelse> findFranchiseConditions(Avdeling avdeling);

	/**
	 * Finner avdeling basert på avdnr
	 * 
	 * @param avdnr
	 * @return avdeling
	 */
	Avdeling findByAvdnr(Integer avdnr);
	/**
	 * Finner avdnr til avdelinger i FRAF, kan begrenses fra og til
	 * @param fromDep
	 * @param toDep
	 * @return avdelingsnummere
	 */
	List<Integer> findAllId(Integer fromDep,Integer toDep);
	/**
	 * Oppdaterer avdeling
	 * @param avdeling
	 */
	void refresh(Avdeling avdeling);
}
