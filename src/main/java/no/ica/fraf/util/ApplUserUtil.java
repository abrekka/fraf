package no.ica.fraf.util;

import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.model.ApplUser;

public class ApplUserUtil {
	private static ApplUserTypeDAO applUserTypeDAO;
	
	public static boolean isAdministrator(ApplUser applUser){
		init();
		if(applUser !=null){
		return applUserTypeDAO.isAdministrator(applUser.getApplUserType());
		}
		return false;
	}
	
	private static void init(){
		if(applUserTypeDAO==null){
			applUserTypeDAO=(ApplUserTypeDAO)ModelUtil.getBean("applUserTypeDAO");
		}
	}
	
	public static boolean isRegnskap(ApplUser applUser){
		init();
		if(applUser !=null){
		return applUserTypeDAO.isRegnskap(applUser.getApplUserType());
		}
		return false;
	}
	public static boolean isMarked(ApplUser applUser){
		init();
		if(applUser !=null){
		return applUserTypeDAO.isMarked(applUser.getApplUserType());
		}
		return false;
	}
	
}
