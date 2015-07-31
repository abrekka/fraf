package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.AvregningType;

/**
 * DAI for AVREGNING_TYPE
 * 
 * @author abr99
 * 
 */
public interface AvregningTypeDAO extends DAO<AvregningType>, Comboable, DaoInterface<AvregningType> {
	String DAO_NAME = "avregningTypeDAO";

	/**
	 * Henter avregningtype
	 * 
	 * @param avregningTypeId
	 * @return avregningtype
	 */
	AvregningType getAvregningType(Integer avregningTypeId);

	/**
	 * Lagrer avregningtype
	 * 
	 * @param avregningType
	 */
	void saveAvregningType(AvregningType avregningType);

	/**
	 * Sletter avregningtype
	 * 
	 * @param avregningTypeId
	 */
	void removeAvregningType(Integer avregningTypeId);

	/**
	 * Henter alle evregningtyper
	 * 
	 * @return alle avregningtyper
	 */
	List<AvregningType> findAll();

	/**
	 * Finner avregningtype basert på kode
	 * 
	 * @param avregningTypeKode
	 * @return avregningtype
	 */
	AvregningType findByKode(String avregningTypeKode);
}
