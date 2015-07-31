package no.ica.fraf.dao;

import java.util.List;

/**
 * Definerer metoder som skal implementeres av klasser som skal vises i den
 * generelle dialogen DataObjectFrame
 * 
 * @author atb
 * @param <E>
 */
public interface DaoInterface<E> {
	/**
	 * Finner alle objekter for gitt klasse
	 * 
	 * @param param
	 * @return liste over alle forekomster
	 */
	List<E> findAll(Object param);

	/**
	 * Kanselerer alle oppdateringer som er gjort på objekter
	 * 
	 * @param objects
	 *            objekter som skal kanseleres
	 */
	void cancelObjectUpdates(List<E> objects);

	/**
	 * Sletter objekter
	 * 
	 * @param objects
	 *            objekter som skal slettes
	 */
	void deleteObjects(List<E> objects);

	/**
	 * Lagrer objekter
	 * 
	 * @param objects
	 *            objekter som skal lagres
	 */
	void persistObjects(List<E> objects);

	/**
	 * Sletter objekt
	 * 
	 * @param object
	 *            objekt som skal slettes
	 */
	void deleteObject(Object object);
}
