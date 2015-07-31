package no.ica.fraf.gui;

import no.ica.fraf.common.WindowInterface;

/**
 * Interface for klasser som skal kunne vises i FRAF
 * 
 * @author abr99
 * 
 */
public interface Viewer {
	/**
	 * Bygger vindu som skal vises
	 * 
	 * @return vindu (JInternalFrame eller JDialog)
	 */
	WindowInterface buildWindow();

	/**
	 * @return tittel
	 */
	String getTitle();

	/**
	 * Initierer vindu
	 */
	void initWindow();

	/**
	 * Rydder opp
	 */
	void cleanUp();
}
