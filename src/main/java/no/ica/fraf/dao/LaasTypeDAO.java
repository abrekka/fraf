package no.ica.fraf.dao;

import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.model.LaasType;

/**
 * DAO for LAAS_TYPE
 * 
 * @author abr99
 * 
 */
public interface LaasTypeDAO extends DAO<LaasType> {
	String DAO_NAME = "laasTypeDAO";

	/**
	 * Finner låser for gitt kode
	 * 
	 * @param laasTypeEnum
	 * @return låser for gitt kode
	 */
	LaasType findByKode(LaasTypeEnum laasTypeEnum);
}
