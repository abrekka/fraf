package no.ica.fraf.dao;

import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.model.BuntType;

/**
 * DAO for BUNT_TYPE
 * 
 * @author abr99
 * 
 */
public interface BuntTypeDAO extends DAO<BuntType> {

	String DAO_NAME = "buntTypeDAO";

	/**
	 * Finner basert på kode
	 * 
	 * @param buntTypeEnum
	 * @return bunttype
	 */
	BuntType findByKode(BuntTypeEnum buntTypeEnum);

}
