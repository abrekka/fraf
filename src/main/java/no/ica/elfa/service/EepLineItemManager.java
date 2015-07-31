package no.ica.elfa.service;

import no.ica.elfa.model.EepLineItem;

/**
 * Interface for manager mot tabell EEP_LINE_ITEM
 * 
 * @author abr99
 * 
 */
public interface EepLineItemManager {
	/**
	 * Lagrer linje
	 * 
	 * @param eepLineItem
	 */
	void saveObject(EepLineItem eepLineItem);
}
