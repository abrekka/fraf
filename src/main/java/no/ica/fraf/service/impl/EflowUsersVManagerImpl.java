package no.ica.fraf.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import no.ica.fraf.dao.EflowUsersVDAO;
import no.ica.fraf.model.EflowUsersV;
import no.ica.fraf.service.EflowUsersVManager;

public class EflowUsersVManagerImpl implements EflowUsersVManager {
	private EflowUsersVDAO dao;
	private static Map<String,EflowUsersV> eflowUsers;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setEflowUsersVDAO(EflowUsersVDAO dao) {
		this.dao = dao;
	}

	public List<EflowUsersV> findByAvdnr(String storeNo) {
		return dao.findByAvdnr(storeNo);
	}

	public Boolean storeInBasware(String avdnr) {
		if(eflowUsers==null){
			initEflowUsers();
		}
		if(eflowUsers.containsKey(avdnr)){
			return true;
		}
		return false;
	}

	private void initEflowUsers(){
		List<EflowUsersV> users = dao.getObjects();
		if(users!=null){
			eflowUsers = new Hashtable<String,EflowUsersV>();
			
			for(EflowUsersV user:users){
				eflowUsers.put(user.getUserName().substring(0,4),user);
			}
		}
	}

	public Map<String, EflowUsersV> getEflowUsers() {
		if(eflowUsers==null){
			initEflowUsers();
		}
		return eflowUsers;
	}

}
