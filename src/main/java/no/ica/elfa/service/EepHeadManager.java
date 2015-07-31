package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.EepHead;
import no.ica.fraf.common.LineManager;

/**
 * Interface for manager mot tabell EEP_HEAD
 * 
 * @author abr99
 * 
 */
public interface EepHeadManager extends LineManager{
	/**
	 * Finner hoder som ikke er fakturert
	 * 
	 * @return hoder
	 */
	List<EepHead> findNotInvoiced();

	/**
	 * Lagrer hode
	 * 
	 * @param head
	 */
	void saveEepHead(EepHead head);

	/**
	 * Finner hoder basert på sekvensnummer
	 * 
	 * @param number
	 * @return hoder
	 */
	EepHead findBySequenceNumber(Integer number);
}
