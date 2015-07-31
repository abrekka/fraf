package no.ica.fraf.service;

import java.util.List;

/**
 * Interface for managere som skal brukes i hjelpeklasser som arver fra AbstractViewHandler
 * @author abr99
 *
 * @param <E>
 */
public interface OverviewManager <E> {
	/**
	 * Hent alle
	 * @return objekter
	 */
	List<E> findAll();
	/**
	 * Finn basert på eksempel
	 * @param object
	 * @return objekter
	 */
	List<E> findByObject(E object);
	/**
	 * Fjern objekt
	 * @param object
	 */
	void removeObject(E object);
	/**
	 * Lagre objekt
	 * @param object
	 */
	void saveObject(E object);
	/**
	 * Oppdaterer objekt
	 * @param object
	 */
	void refreshObject(E object);
}
