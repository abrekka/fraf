package no.ica.fraf.dao;

import no.ica.fraf.common.BatchStatusManagerInterface;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.model.BuntStatus;

/**
 * DAO for BUNT_STATUS
 * @author abr99
 *
 */
public interface BuntStatusDAO extends DAO<BuntStatus>,BatchStatusManagerInterface{
	String DAO_NAME = "buntStatusDAO";

	/**
	 * Finner buntstatus basert på kode
	 * @param buntStatusEnum
	 * @return buntstatus
	 */
	BuntStatus findByKode(BuntStatusEnum buntStatusEnum);

}
