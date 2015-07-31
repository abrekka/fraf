package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.AvregningFrekvensType;

/**
 * DAO for AVREGNING_FREKVENS_TYPE
 * 
 * @author abr99
 * 
 */
public interface AvregningFrekvensTypeDAO extends DAO<AvregningFrekvensType>, Comboable, DaoInterface<AvregningFrekvensType> {
	String DAO_NAME = "avregningFrekvensTypeDAO";

	/**
	 * Henter frekvenstype
	 * 
	 * @param frekvensTypeId
	 * @return frekvenstype
	 */
	AvregningFrekvensType getAvregningFrekvensType(
			Integer frekvensTypeId);

	/**
	 * Lagrer frekvenstype
	 * 
	 * @param frekvensType
	 */
	void saveAvregningFrekvensType(AvregningFrekvensType frekvensType);

	/**
	 * Sletter frekvenstype
	 * 
	 * @param frekvensTypeId
	 */
	void removeAvregningFrekvensType(Integer frekvensTypeId);

	/**
	 * Finner alle frekvenstyper
	 * 
	 * @return alle frekvenstyper
	 */
	List<AvregningFrekvensType> findAll();

	/**
	 * Finner frekvenstype basert på kode
	 * 
	 * @param frekvensKode
	 * @return frekvenstype
	 */
	AvregningFrekvensType findByKode(String frekvensKode);

	/**
	 * Finner frekvenstype basert på antall terminer
	 * 
	 * @param terminer
	 * @return frekvenstype
	 */
	AvregningFrekvensType findByTerminer(Integer terminer);
}
