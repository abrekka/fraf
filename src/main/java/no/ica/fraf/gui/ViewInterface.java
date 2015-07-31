package no.ica.fraf.gui;

import javax.swing.JPanel;

import no.ica.fraf.common.WindowInterface;

/**
 * Interface for klasser som bygger panel
 * 
 * @author abr99
 * 
 */
public interface ViewInterface {
	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	JPanel buildPanel(WindowInterface window);
}
