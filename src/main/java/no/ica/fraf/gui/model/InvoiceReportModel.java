package no.ica.fraf.gui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaV;

/**
 * Modell for faktura
 * 
 * @author abr99
 * 
 */
public class InvoiceReportModel extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Kolonnenavn
     */
    private String[] columnNames = { "MOTTAKER_NAVN", 
    		"AVSENDER_NAVN",
            "ADRESSE1", 
            "AVSENDER_ADRESSE1", 
            "ADRESSE2", 
            "AVSENDER_ADRESSE2",
            "POSTNR", 
            "POSTSTED", 
            "AVSENDER_ADRESSE3", 
            "AVSENDER_AVTALENR",
            "JURIDISK_NAVN", 
            "FAKTURA_DATO", 
            "FORFALL_DATO", 
            "FAKTURA_NR",
            "AVDNR", 
            "FORETAKSNUMMER", 
            "PHONE", 
            "FAX", 
            "AVSENDER_KONTONUMMER",
            "KID", 
            "FAKTURERT_AV", 
            "FAKTURA_TITTEL", 
            "FAKTURA_ID", 
            "BELOP",
            "MVA_BELOP", 
            "TOTAL_BELOP", 
            "ICA_KONTO_TEKST",
            "HAR_SATS_LINJE",
            "OVERTREKKSRENTE" };

    /**
     * Data for faktura
     */
    private List<Faktura> data;

    /**
     * Inneholder info om fakturaavsender
     */

    /**
     * Konstruktør
     * 
     * @param fakturaList
     */
    //public InvoiceReportModel(Collection<FakturaV> fakturaList) {
    public InvoiceReportModel(Collection<Faktura> fakturaList) {
        data = new ArrayList<Faktura>();
        data.addAll(fakturaList);
    }

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data == null) {
            return null;
        }

        //FakturaV currentFaktura = data.get(rowIndex);
        Faktura currentFaktura = data.get(rowIndex);

        switch (columnIndex) {
        case 0:
            return currentFaktura.getMottakerNavn();
        // avsendernavn
        case 1:
            return currentFaktura.getAvsenderNavn();
        case 2:
            return currentFaktura.getAdresse1();
        // avsender_adresse1
        case 3:
            return currentFaktura.getAvsenderAdresse1();
        case 4:
            return currentFaktura.getAdresse2();
        // avsenderadress2
        case 5:
            return currentFaktura.getAvsenderAdresse2();
        case 6:
            return String.format("%1$04d",Integer.valueOf(currentFaktura.getPostnr()));
        case 7:
            return currentFaktura.getPoststed();
        // avsender postnr
        case 8:
            return currentFaktura.getAvsenderAdresse3();
        case 9:
            return currentFaktura.getAvtalenr();
        case 10:
            return currentFaktura.getJuridiskNavn();
        case 11:
            return currentFaktura.getFakturaDato();
        case 12:
            return currentFaktura.getForfallDato();
        case 13:
            return currentFaktura.getFakturaNr();
        case 14:
            return currentFaktura.getAvdnr();
        // foretaksnummer
        case 15:
            return currentFaktura.getAvsenderOrgNr();
        // telefon
        case 16:
            return currentFaktura.getAvsenderTelefon();
        // telefax
        case 17:
            return currentFaktura.getAvsenderTelefax();
        case 18:
            return currentFaktura.getAvsenderKontonr();
        case 19:
            return currentFaktura.getKid();
        case 20:
            return currentFaktura.getFakturertAv();
        case 21:
            return currentFaktura.getFakturaTittel();
        case 22:
            return currentFaktura.getFakturaId();
        case 23:
            return currentFaktura.getBelop();
        case 24:
            return currentFaktura.getMvaBelop();
        case 25:
            return currentFaktura.getTotalBelop();
        case 26:
            return currentFaktura.getIcaKontoTekst();
        case 27:
        	if(currentFaktura.getHarSatsLinje()!=null){
            return currentFaktura.getHarSatsLinje();
        	}
        	return 0;
        case 28:
            return currentFaktura.getOvertrekksrente();
        }
        return null;
    }

    /**
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
