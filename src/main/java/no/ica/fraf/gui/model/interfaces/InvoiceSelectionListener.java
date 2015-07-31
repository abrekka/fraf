package no.ica.fraf.gui.model.interfaces;

/**
 * Interface som m� implementeres av de som skal lytte p� selektering av faktura
 * @author abr99
 *
 */
public interface InvoiceSelectionListener {
	/**
	 * Blir kj�rt n�r faktura velges
	 * @param invoiceId
	 */
	void invoiceSelected(Integer invoiceId);
}
