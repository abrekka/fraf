/**
 * 
 */
package no.ica.tollpost.gui;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.ListModel;

import no.ica.tollpost.model.TgFaktura;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

public class InvoiceTableModel extends AbstractTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static String[] COLUMNS = { "Fakturanr", "Avdnr", "Fakturadato",
			"Forfallsdato", "Totalbeløp", "Opprettet", "Fakturatittel",
			"Mottaker", "Adresse1", "Adresse2", "Postnr", "Poststed", "Beløp",
			"Mva", "Type", "KID" };

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");
	private boolean forExcel=false;

	/**
	 * @param listModel
	 */
	public InvoiceTableModel(ListModel listModel,boolean excel) {
		super(listModel, COLUMNS);
		forExcel=excel;

	}

	/**
	 * Henter verdi
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @return verdi
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		TgFaktura tgFaktura = (TgFaktura) getRow(rowIndex);
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		switch (columnIndex) {
		case 0:
			return tgFaktura.getFakturaNr();
		case 1:
			return tgFaktura.getAvdnr();
		case 2:
			return tgFaktura.getFakturaDato();
		case 3:
			return tgFaktura.getForfallDato();
		case 4:
			if (tgFaktura.getFakturaBelop() != null&&!forExcel) {
				return numberFormat.format(tgFaktura.getFakturaBelop());
			}
			return tgFaktura.getFakturaBelop();
		case 5:
			return tgFaktura.getOpprettetDato();
		case 6:
			return tgFaktura.getFakturaTittel();
		case 7:
			return tgFaktura.getMottakerNavn();
		case 8:
			return tgFaktura.getAdresse1();
		case 9:
			return tgFaktura.getAdresse2();
		case 10:
			return tgFaktura.getPostnr();
		case 11:
			return tgFaktura.getPoststed();
		case 12:
			if (tgFaktura.getGrossAmount() != null&&!forExcel) {
				return numberFormat.format(tgFaktura.getGrossAmount());
			}
			return tgFaktura.getGrossAmount();
		case 13:
			if(tgFaktura.getMvaButikk()!=null&&!forExcel){
			return numberFormat.format(tgFaktura.getMvaButikk());
			}
			return tgFaktura.getMvaButikk();
		case 14:
			return tgFaktura.getMeldingstype();
		case 15:
			return tgFaktura.getKid();
		default:
			throw new IllegalStateException("Unknown column");
		}

	}

}