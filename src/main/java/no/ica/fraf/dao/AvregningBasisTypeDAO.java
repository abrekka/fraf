package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.AvregningBasisType;

/**
 * DAO for AVREGNING_BASIS_TYPE
 * 
 * @author abr99
 * 
 */
public interface AvregningBasisTypeDAO extends DAO<AvregningBasisType>, Comboable, DaoInterface<AvregningBasisType> {
	String DAO_NAME = "avregningBasisTypeDAO";

	/**
	 * Henter avregningbasis
	 * 
	 * @param typeId
	 * @return avregningbasis
	 */
	AvregningBasisType getAvregningBasisType(Integer typeId);

	/**
	 * Lagrer avregningbasistype
	 * 
	 * @param basisType
	 */
	void saveAvregningBasisType(AvregningBasisType basisType);

	/**
	 * Sletter avregningbasistype
	 * 
	 * @param basisTypeId
	 */
	void removeAvregningBasisType(Integer basisTypeId);

	/**
	 * Henter alle avregningbasistype
	 * 
	 * @return alle avregningbasistype
	 */
	List<AvregningBasisType> findAll();

	/**
	 * Finner avregningbasistype basert på kode
	 * 
	 * @param kode
	 * @return avrengningbasistype
	 */
	AvregningBasisType findByKode(String kode);
}
