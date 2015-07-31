package no.ica.fraf.gui;

import no.ica.fraf.common.WindowInterface;

/**
 * Interface for klasser som skal kunne benytte lagre, slette, ny og
 * oppdateringsknapp
 * 
 * @author abr99
 * 
 */
public interface Updateable {
	/**
	 * Utf�rer lagring
	 * @param window 
	 */
	void doSave(WindowInterface window);

	/**
	 * Utf�rer sletting
	 * @return true dersom slettet
	 */
	boolean doDelete();

	/**
	 * Utf�rer opprettelse av ny
	 */
	void doNew();

	/**
	 * Oppdaterer vindu
	 */
	void doRefresh();

}