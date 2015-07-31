package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.EepHeadDAO;
import no.ica.elfa.model.EepHead;
import no.ica.elfa.model.EepLineItem;
import no.ica.elfa.service.EepHeadManager;
import no.ica.elfa.service.EepLineItemManager;
import no.ica.fraf.common.Line;

/**
 * Implementasjon av manager for tabell EEP_HEAD
 * 
 * @author abr99
 * 
 */
public class EepHeadManagerImpl implements EepHeadManager {
	/**
	 * 
	 */
	private EepHeadDAO dao;

	/**
	 * 
	 */
	private EepLineItemManager eepLineItemManager;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setEepHeadDAO(EepHeadDAO dao) {
		this.dao = dao;
	}

	/**
	 * Setter dao for EEP_LINE_ITEM
	 * 
	 * @param eepLineItemManager
	 */
	public void setEepLineItemManager(EepLineItemManager eepLineItemManager) {
		this.eepLineItemManager = eepLineItemManager;
	}

	/**
	 * @see no.ica.elfa.service.EepHeadManager#findNotInvoiced()
	 */
	public List<EepHead> findNotInvoiced() {
		return dao.findNotInvoiced();
	}

	/**
	 * @see no.ica.elfa.service.EepHeadManager#saveEepHead(no.ica.elfa.model.EepHead)
	 */
	public void saveEepHead(EepHead head) {
		dao.saveObject(head);

	}

	/**
	 * @see no.ica.elfa.service.EepHeadManager#findBySequenceNumber(java.lang.Integer)
	 */
	public EepHead findBySequenceNumber(Integer number) {
		List<EepHead> heads = dao.findBySequenceNumber(number);
		if (heads != null && heads.size() > 0) {
			return heads.get(0);
		}
		return null;
	}

	/**
	 * @see no.ica.fraf.common.LineManager#findWithoutAvdnrById(java.lang.Integer)
	 */
	public List<Line> findWithoutAvdnrById(Integer id) {
		return dao.findWithoutAvdnrById(id);
	}

	/**
	 * @see no.ica.fraf.common.LineManager#saveLine(no.ica.fraf.common.Line)
	 */
	public void saveLine(Line line) {
		eepLineItemManager.saveObject((EepLineItem) line);

	}

}
