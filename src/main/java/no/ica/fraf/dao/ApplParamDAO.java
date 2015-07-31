package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.ApplParam;
import no.ica.fraf.service.OverviewManager;

/**
 * DAO for APP_PARAM
 * 
 * @author abr99
 * 
 */
public interface ApplParamDAO extends DAO<ApplParam>, DaoInterface<ApplParam>,
		OverviewManager<ApplParam> {
	String DAO_NAME = "applParamDAO";

	/**
	 * Henter ApplParam basert på id
	 * 
	 * @param appParamid
	 * @return ApplParam
	 */
	ApplParam getApplParam(Integer appParamid);

	/**
	 * Lagrer ApplParam
	 * 
	 * @param applParam
	 */
	void saveApplParam(ApplParam applParam);

	/**
	 * Fjerner parameter
	 * 
	 * @param appParamid
	 */
	void removeApplParam(Integer appParamid);

	/**
	 * Henter alle parametre
	 * 
	 * @return alle parametre
	 */
	List<ApplParam> findAll();

	/**
	 * Sletter alle parametre
	 */
	void deleteAll();

	/**
	 * Finner bart på parameternavn
	 * 
	 * @param paramName
	 * @return parameter
	 */
	ApplParam findByParamName(String paramName,Integer systemType);

	List<ApplParam> findByParamNameStartWith(String likeString);

	List<ApplParam> findAllBySystemName(String systemName);
}
