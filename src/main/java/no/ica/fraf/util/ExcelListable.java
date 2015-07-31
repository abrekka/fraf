package no.ica.fraf.util;

import java.util.Collection;

/**
 * Interface for klasser som skal generere excelfiler basert på sider
 * 
 * @author abr99
 * 
 */
public interface ExcelListable {
	/**
	 * Henter ut neste side
	 * 
	 * @param filter
	 * @param currentIndex
	 * @param fetchSize
	 * @return neste side
	 */
	public Collection getNextList(Object[] filter, int currentIndex,
			int fetchSize);

	/**
	 * Henter ut antall som skal genereres
	 * 
	 * @param filter
	 * @return antall som skal genereres
	 */
	public Integer getCount(Object[] filter);
}
