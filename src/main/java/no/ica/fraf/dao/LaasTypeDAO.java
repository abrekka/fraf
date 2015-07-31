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
	 * Finner l�ser for gitt kode
	 * 
	 * @param laasTypeEnum
	 * @return l�ser for gitt kode
	 */
	LaasType findByKode(LaasTypeEnum laasTypeEnum);
}
