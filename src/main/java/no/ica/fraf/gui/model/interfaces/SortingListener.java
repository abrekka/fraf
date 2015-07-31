package no.ica.fraf.gui.model.interfaces;

import java.util.List;

/**
 * Interface som implementeres av klasser som �nsker � lytte p� n�r det gj�res
 * en sorteringsendring
 * 
 * @author abr99
 * 
 */
public interface SortingListener {
	/**
	 * Blir kj�rt ved sorteringsednring
	 * 
	 * @param directives
	 */
	public void sortingChanged(List directives);
}
