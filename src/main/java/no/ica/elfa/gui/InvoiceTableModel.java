/**
 * 
 */
package no.ica.elfa.gui;

import java.text.SimpleDateFormat;

import javax.swing.ListModel;

import no.ica.elfa.model.Invoice;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

/**
 * Tabellmodell for faktura
 * 
 * @author abr99
 * 
 */
public class InvoiceTableModel extends AbstractTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static String[] COLUMNS = { "Fakturanr", "Opprettet", "Avdnr",
			"Fra dato", "Til dato", "Fakturadato", "Forfallsdato",
			"Totalbeløp", "Moms", "Kommisjon", "Fakturabeløp", "KID" };

	/**
	 * 
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * @param listModel
	 */
	public InvoiceTableModel(ListModel listModel) {
		super(listModel, COLUMNS);

	}

	/**
	 * Henter verdi
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @return verdi
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Invoice invoice = (Invoice) getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return invoice.getInvoiceNr();
		case 1:
			return dateFormat.format(invoice.getCreatedDate());
		case 2:
			return invoice.getStoreNo();
		case 3:
			if (invoice.getFromDate() != null) {
				return dateFormat.format(invoice.getFromDate());
			}
			return null;
		case 4:
			if (invoice.getToDate() != null) {
				return dateFormat.format(invoice.getToDate());
			}
			return null;
		case 5:
			return dateFormat.format(invoice.getInvoiceDate());
		case 6:
			return dateFormat.format(invoice.getDueDate());
		case 7:
			return invoice.getAmountTotal();
		case 8:
			return invoice.getVatAmount();
		case 9:
			return invoice.getCommissionAmountSto();
		case 10:
			return invoice.getAmountInvoice();
		case 11:
			return invoice.getKidNr();
		default:
			throw new IllegalStateException("Unknown column");
		}

	}

}