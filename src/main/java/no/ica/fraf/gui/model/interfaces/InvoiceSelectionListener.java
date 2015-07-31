package no.ica.fraf.gui.model.interfaces;

/**
 * Interface som må implementeres av de som skal lytte på selektering av faktura
 * @author abr99
 *
 */
public interface InvoiceSelectionListener {
	/**
	 * Blir kjørt når faktura velges
	 * @param invoiceId
	 */
	void invoiceSelected(Integer invoiceId);
}
