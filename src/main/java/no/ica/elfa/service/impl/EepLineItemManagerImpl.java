package no.ica.elfa.service.impl;

import no.ica.elfa.dao.EepLineItemDAO;
import no.ica.elfa.model.EepLineItem;
import no.ica.elfa.service.EepLineItemManager;

/**
 * Implementasjon av manager mot tabell EEP_LINE_ITEM
 * 
 * @author abr99
 * 
 */
public class EepLineItemManagerImpl implements EepLineItemManager {
	/**
	 * 
	 */
	private EepLineItemDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setEepLineItemDAO(EepLineItemDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.EepLineItemManager#saveObject(no.ica.elfa.model.EepLineItem)
	 */
	public void saveObject(EepLineItem eepLineItem) {
		dao.saveObject(eepLineItem);

	}

}
