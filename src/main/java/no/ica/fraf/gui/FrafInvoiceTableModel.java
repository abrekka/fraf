package no.ica.fraf.gui;

import java.text.SimpleDateFormat;

import javax.swing.ListModel;

import no.ica.fraf.model.Faktura;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

/**
 * Tabellmodell for faktura
 * 
 * @author abr99
 * 
 */
public class FrafInvoiceTableModel extends AbstractTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static String[] COLUMNS = { "Faktura-ID", "Fakturanr", "Avdnr",
			"Beskrivelse", "Fakturadato", "Forfallsdato", "Fakturagrunnlag",
			"Fakturabeløp", "År", "Fra periode", "Til periode",
			"Opprettet dato", "Reversert" };

	/**
	 * 
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * @param listModel
	 */
	public FrafInvoiceTableModel(ListModel listModel) {
		super(listModel, COLUMNS);

	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Faktura faktura = (Faktura) getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return faktura.getFakturaId();

		case 1:
			return faktura.getFakturaNr();
		case 2:
			return faktura.getAvdnr();
		case 3:
			return faktura.getFakturaTekst();
		case 4:
			if (faktura.getFakturaDato() != null) {
				return dateFormat.format(faktura.getFakturaDato());
			}
			return null;
		case 5:
			if (faktura.getForfallDato() != null) {
				return dateFormat.format(faktura.getForfallDato());
			}
			return null;
		case 6:
			return faktura.getGrunnlagBelop();
		case 7:
			return faktura.getTotalBelop();
		case 8:
			return faktura.getAar();
		case 9:
			return faktura.getFraPeriode();
		case 10:
			return faktura.getTilPeriode();
		case 11:
			if (faktura.getOpprettetDato() != null) {
				return dateFormat.format(faktura.getOpprettetDato());
			}
			return null;
		case 12:
			return faktura.getReversert();
		default:
			throw new IllegalStateException("Unknown column");
		}

	}

}