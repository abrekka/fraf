package no.ica.fraf.common;

import java.util.List;

/**
 * Interface for fakturalinjemanager
 * 
 * @author abr99
 * 
 */
public interface LineManager {
	/**
	 * Finner linjer uten avdelingsnummer
	 * 
	 * @param id
	 * @return fakturalinjer
	 */
	List<Line> findWithoutAvdnrById(Integer id);

	/**
	 * Lagrer fakturalinje
	 * 
	 * @param line
	 */
	void saveLine(Line line);
}
