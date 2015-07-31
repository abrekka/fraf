package no.ica.fraf.util;

import java.util.Comparator;

/**
 * Klasse for sammenlikning av bunter
 * @author abr99
 *
 */
public class BuntComparator implements Comparator {

	/**
	 * @param o1
	 * @param o2
	 * @return -1 deroms mindre, 0 dersom likt og 1 dersom større
	 */
	public int compare(Object o1, Object o2) {
		Integer int1 = (Integer)o1;
		Integer int2 = (Integer)o2;
		int returnValue = 0;
		
		if(int1 > int2){
			returnValue = -1;
		}else if(int1 < int2){
			returnValue = 1;
		}
		return returnValue;
	}

}
