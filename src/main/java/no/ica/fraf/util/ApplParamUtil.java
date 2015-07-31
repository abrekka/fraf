package no.ica.fraf.util;

import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;

/**
 * Hjelpeklasse for paramtere.
 * 
 * @author abr99
 * 
 */
public class ApplParamUtil {
	
	/**
	 * DAO for parametre.
	 */
	private static ApplParamDAO applParamDAO;
	private static Map<String,String> paramValues=new HashMap<String, String>();

	public ApplParamUtil(ApplParamDAO paramDao){
		applParamDAO=paramDao;
	}
	/**
	 * Henter parameterverdi som streng
	 * 
	 * @param paramName
	 * @return parameterverdi
	 * @throws FrafException 
	 */
	public static String getStringParam(final String paramName) throws FrafException{
		String value = paramValues.get(paramName);
		return value!=null?value:getParamFromDb(paramName);
		
	}
	
	private static String getParamFromDb(final String paramName) throws FrafException{
		applParamDAO=applParamDAO==null?(ApplParamDAO) ModelUtil.getBean("applParamDAO"):applParamDAO;
		ApplParam applParam = null;
		applParam = applParamDAO.findByParamName(paramName,SystemType.getSystemType(FrafMain.isStandalone()).getSystemType());
		String value = null;

		if (applParam != null) {
			value = applParam.getParamValue();
			paramValues.put(paramName, value);
		}else{
			throw new FrafException("Kunne ikke finne parameter "+paramName);
		}
		return value;	
	}
	
	public static void setApplParamDAO(ApplParamDAO paramDAO){
		applParamDAO=paramDAO;
	}
	
}
