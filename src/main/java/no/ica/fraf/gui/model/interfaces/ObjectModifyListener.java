package no.ica.fraf.gui.model.interfaces;

import no.ica.fraf.gui.model.Column;

/**
 * Interface som implementeres av de klasser som ønsker å lytte på at et objekt
 * har blitt oppdatert
 * 
 * @author abr99
 * 
 */
public interface ObjectModifyListener {
	/**
	 * Blir kalt på klasse som har implementert dett interfacet når et objekt
	 * har blitt oppdatert
	 * 
	 * @param object
	 * @param updateColumn
	 */
	public void objectModified(Object object, Column updateColumn);
}
