package no.ica.fraf.gui.model.interfaces;

import java.util.List;

/**
 * Interface som implementeres av klasser som ønsker å lytte på når det gjøres
 * en sorteringsendring
 * 
 * @author abr99
 * 
 */
public interface SortingListener {
	/**
	 * Blir kjørt ved sorteringsednring
	 * 
	 * @param directives
	 */
	public void sortingChanged(List directives);
}
