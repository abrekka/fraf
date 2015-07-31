package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Rik2KjedeV;
import no.ica.fraf.service.OverviewManager;

/**
 * DAO for BETINGELSE_TYPE
 * 
 * @author abr99
 * 
 */
public interface BetingelseTypeDAO extends DAO<BetingelseType>, Comboable,
		DaoInterface<BetingelseType>, OverviewManager<BetingelseType> {
	String DAO_NAME = "betingelseTypeDAO";



	/**
	 * Henter betingelsetype
	 * 
	 * @param betingelseTypeId
	 * @return betingelsetype
	 */
	BetingelseType getBetingelseType(Integer betingelseTypeId);

	/**
	 * Lagrer betingelsetype
	 * 
	 * @param betingelseType
	 */
	void saveBetingelseType(BetingelseType betingelseType);

	/**
	 * Sletter betingelsetype
	 * 
	 * @param betingelseTypeId
	 */
	void removeBetingelseType(Integer betingelseTypeId);

	/**
	 * Henter alle betingelsetyper
	 * 
	 * @return alle betingelsetyper
	 */
	List<BetingelseType> findAll();

	/**
	 * Finner alle betingelsetyper tilhørende franchisegruppe
	 * 
	 * @return alle betingelsetyper tilhørende franchisegruppe
	 */
	List<BetingelseType> findAllFranchise();

	/**
	 * Finner alle betingelsetyper ikke tilhørende franchisegruppe
	 * 
	 * @return alle betingelsetyper ikke tilhørende franchisegruppe
	 */
	List findAllOthers();

	/**
	 * Finner betingelsetype basert på kode
	 * 
	 * @param typeKode
	 * @return betingelsetype
	 */
	BetingelseType findByKode(String typeKode);

	/**
	 * Finner alle i gitt gruppe
	 * 
	 * @param groupname
	 * @return betingelsetyper
	 */
	List<BetingelseType> findByGroupName(String groupname);

	/**
	 * Finner betingelser basert på gruppe og kjede
	 * 
	 * @param groupName
	 * @param kjede
	 * @return betingelser
	 */
	List<BetingelseType> findByGroupNameAndKjede(String groupName,
			Chain chain);

	/**
	 * Finner basert på kode og kjede
	 * 
	 * @param id
	 * @param kode
	 * @param kjede
	 * @return betingelser
	 */
	List<BetingelseType> findByKodeKjede(Integer id, String kode,
			Chain chain);

	

	List<BetingelseType> findAllAktiv();
}
