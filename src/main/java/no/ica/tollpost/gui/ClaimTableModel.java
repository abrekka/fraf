/**
 * 
 */
package no.ica.tollpost.gui;

import java.text.SimpleDateFormat;

import javax.swing.ListModel;

import no.ica.tollpost.model.TgImport;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

final class ClaimTableModel extends AbstractTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static String[] COLUMNS = { "LinjeId", "MeldingId", "Avdnr",
			"Transdato", "Antall", "Sendingsnr", "KolliId", "Beløp",
			"Meldingstype", "Reg", "Transfilid", "Bunt", "Avtaletype",
			"Dataset", "Fakturanr" };

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * @param listModel
	 */
	public ClaimTableModel(ListModel listModel) {
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

		TgImport tgImport = (TgImport) getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return tgImport.getLinjeId();
		case 1:
			return tgImport.getMeldingId();
		case 2:
			return tgImport.getAvdnr();
		case 3:
			return tgImport.getTransDato();
		case 4:
			return tgImport.getAntTrans();
		case 5:
			return tgImport.getSendingsnr();
		case 6:
			return tgImport.getKolliId();
		case 7:
			return tgImport.getBelop();
		case 8:
			return tgImport.getMeldingstype();
		case 9:
			return tgImport.getDtReg();
		case 10:
			return tgImport.getTransfilId();
		case 11:
			return tgImport.getBunt();
		case 12:
			return tgImport.getAvtaletype();
		case 13:
			return tgImport.getDataset();
		case 14:
			//return tgImport.getFakturaId();
			if(tgImport.getTgFaktura()!=null){
			return tgImport.getTgFaktura().getFakturaNr();
			}
			return null;
		default:
			throw new IllegalStateException("Unknown column");
		}
	}

}