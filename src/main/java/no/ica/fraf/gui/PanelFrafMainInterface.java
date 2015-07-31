package no.ica.fraf.gui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JInternalFrame;

import no.ica.fraf.model.ApplUser;

public interface PanelFrafMainInterface {

	/**
	 * Initierer
	 * 
	 * @param applUser
	 */
	public abstract void init(ApplUser applUser);

	/**
	 * Legger til frame for systemet
	 * 
	 * @param frame
	 */
	public abstract void addFrame(JInternalFrame frame);

	/**
	 * Lukker alle vinduer
	 */
	public abstract void closeAllFrames();

	/**
	 * Henter ut alle aktive vinduer
	 * 
	 * @return alle aktive vinduer
	 */
	public abstract JInternalFrame[] getAllFrames();

	/**
	 * Henter ut desktoppane
	 * 
	 * @return desktoppane
	 */
	public abstract Container getDesktopPane();
	public Component getComponent();

	public abstract void removeFrame(JInternalFrame frame);

}