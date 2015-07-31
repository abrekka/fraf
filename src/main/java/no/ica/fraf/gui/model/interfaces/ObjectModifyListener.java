package no.ica.fraf.gui.model.interfaces;

import no.ica.fraf.gui.model.Column;

/**
 * Interface som implementeres av de klasser som �nsker � lytte p� at et objekt
 * har blitt oppdatert
 * 
 * @author abr99
 * 
 */
public interface ObjectModifyListener {
	/**
	 * Blir kalt p� klasse som har implementert dett interfacet n�r et objekt
	 * har blitt oppdatert
	 * 
	 * @param object
	 * @param updateColumn
	 */
	public void objectModified(Object object, Column updateColumn);
}
