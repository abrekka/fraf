package no.ica.fraf.dao;

import java.util.List;


/**
 * Generelt interface for rapport-DAO'er
 * 
 * @author abr99
 * 
 */
public interface ReportDAO {
	/**
	 * Henter data som skal vises i rapport
	 * 
	 * @return data som skal vises i rapport
	 */
	List<Object> getListData();
}
