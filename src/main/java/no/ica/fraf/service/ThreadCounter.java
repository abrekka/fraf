/**
 * 
 */
package no.ica.fraf.service;

import java.util.ArrayList;
import java.util.List;

public class ThreadCounter{
	
	private List<String> errorMsgList=new ArrayList<String>();
	private List<Integer> buntList=new ArrayList<Integer>();
	
	public synchronized void addBuntId(Integer buntId){
		buntList.add(buntId);
	}
	public synchronized int getCount(){
		return buntList.size();
	}
	public synchronized void addErrorMsg(String msg){
		errorMsgList.add(msg);
	}
	public List<Integer> getBuntList(){
		return buntList;
	}
}