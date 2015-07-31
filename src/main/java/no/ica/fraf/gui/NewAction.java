package no.ica.fraf.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.util.GuiUtil;

/**
 * Håndterer ny-knapp
 * 
 * @author abr99
 * 
 */
public class NewAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Updateable updateable;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * @param objectName
	 * @param updateable
	 * @param window
	 * 
	 */
	public NewAction(String objectName, Updateable updateable,
			WindowInterface window) {
		this(objectName, updateable, window, "Ny");
	}

	/**
	 * @param objectName
	 * @param updateable
	 * @param window
	 * @param actionString
	 */
	public NewAction(String objectName, Updateable updateable,
			WindowInterface window, String actionString) {
		super(actionString + " " + objectName + "...");
		this.updateable = updateable;
		this.window = window;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		GuiUtil.setWaitCursor(window.getComponent());
		updateable.doNew();
		GuiUtil.setDefaultCursor(window.getComponent());

	}

	/**
	 * Rydder opp
	 */
	public void dispose() {
		updateable = null;
		window = null;
	}
}