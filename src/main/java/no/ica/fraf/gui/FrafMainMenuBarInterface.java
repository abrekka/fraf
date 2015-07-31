package no.ica.fraf.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.ApplUser;

public interface FrafMainMenuBarInterface {

	/**
	 * Avslutter system
	 */
	public abstract void systemExit();

	/**
	 * Initierer menyer
	 * 
	 * @param applUser
	 */
	public abstract void initMenus(ApplUser applUser);

	/**
	 * Lager menyer
	 * 
	 * @throws FrafException
	 */
	public abstract void createMenus() throws FrafException;

	/**
	 * Henter menyvindu
	 * 
	 * @return menyvindu
	 */
	public abstract JMenu getMenuWindow();

	/**
	 * Henter menylytter
	 * 
	 * @return menylytter
	 */
	public abstract ActionListener getMenuListener();

	/**
	 * Setter gjeldende bruker
	 * 
	 * @param currentFrafUser
	 */
	public abstract void setCurrentFrafUser(ApplUser currentFrafUser);

	/**
	 * Henter menybar
	 * 
	 * @return menybar
	 */
	public abstract JMenuBar getMenuBar();

	ApplUser getApplicationUser();

}