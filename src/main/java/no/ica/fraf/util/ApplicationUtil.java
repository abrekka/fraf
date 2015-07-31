package no.ica.fraf.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Hjelpeklasse
 * 
 * @author abr99
 * 
 */
public class ApplicationUtil {
	/**
	 * Finner union mellom to collection
	 * 
	 * @param col1
	 *            den største collection
	 * @param col2
	 *            subset
	 * @return union mellom to collection
	 */
	public static List<Object> getUnion(Collection col1, Collection col2) {
		ArrayList<Object> returnList = new ArrayList<Object>();
		if (col1 != null && col2 != null) {
			for (Object object : col2) {
				if (col1.contains(object)) {
					returnList.add(object);
				}
			}
		}
		return returnList;
	}
	
	/**
	 * Finner differansen mellom to collection
	 * @param col1 største collection
	 * @param col2 subset
	 * @return differanse
	 */
	public static List getDiff(Collection col1,Collection col2){
		ArrayList<Object> returnList = new ArrayList<Object>();
		if(col1 != null && col2 != null){
			for(Object object:col1){
				if(!col2.contains(object)){
					returnList.add(object);
				}
			}
		}
		return returnList;
	}
	
	/**
	 * Konverterer array av Integer til array av int
	 * @param integerArray
	 * @return array av int
	 */
	public static int[] convertIntegerArrayToInt(Integer[] integerArray){
		int[] intArray = null;
		if(integerArray != null){
			intArray = new int[integerArray.length];
			int counter = 0;
			for(Integer integer:integerArray){
				intArray[counter] = integer.intValue();
				counter++;
			}
		}
		return intArray;
	}
}
