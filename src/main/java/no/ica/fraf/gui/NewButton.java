package no.ica.fraf.gui;

import javax.swing.JButton;

import no.ica.fraf.common.WindowInterface;

/**
 * Ny-knapp
 * 
 * @author abr99
 * 
 */
public class NewButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param objectName
	 * @param updateable
	 * @param window
	 */
	public NewButton(String objectName, Updateable updateable,
			WindowInterface window) {
		this(objectName, updateable, window, "Ny");
	}

	/**
	 * @param objectName
	 * @param updateable
	 * @param window
	 * @param actionString
	 */
	public NewButton(String objectName, Updateable updateable,
			WindowInterface window, String actionString) {
		super(new NewAction(objectName, updateable, window, actionString));
	}

	/**
	 * Rydd opp
	 */
	public void dispose() {
		((NewAction) this.getAction()).dispose();
	}
}