package no.ica.fraf.gui.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.table.TableColumn;

import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Faktura;

import org.apache.log4j.Logger;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Panel som viser bokføringsdetaljer om en faktura
 * 
 * @author abr99
 * 
 */
// public class PanelInvoiceBook extends javax.swing.JPanel {
public class PanelInvoiceBook extends AbstractExcelPanel {
	/**
	 * 
	 */
	private static final String[] COLUMN_NAMES = { "Selskap", "Avdnr", "Dato",
			"Type", "Konto", "Beløp", "Tekst", "Mva-kode", "Forfallsdato",
			"Fakturanr", "Kladd","Prosjekt" };

	/**
	 * 
	 */
	private static final String[] METHODS = { "Selskap", "Avdnr",
			"RegnskapDato", "KontoType", "Konto", "Belop", "Tekst", "MvaKode",
			"ForfallDato", "FakturaNr", "KladdNavn","Prosjekt" };

	/**
	 * 
	 */
	private static final Class[] PARAMS = { String.class, Integer.class,
			Date.class, String.class, String.class, BigDecimal.class,
			String.class, String.class, Date.class, String.class, String.class,String.class };

	/**
	 * 
	 */
	private static final ColumnFormatEnum[] FORMATS = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * 
	 */
	private static final ColumnFormatEnum[] FORMATS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.DATE_SHORT, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.DATE_SHORT, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE , ColumnFormatEnum.NONE};

	/**
	 * 
	 */
	static Logger loggerBook = Logger.getLogger(PanelInvoiceBook.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kontruktør
	 * 
	 * @param faktura
	 * @param applUser
	 * @param parent
	 */
	public PanelInvoiceBook(Faktura faktura, ApplUser applUser,
			JInternalFrame parent) {
		super(faktura, applUser, parent);
	}

	/**
	 * Laster data
	 * 
	 * @param faktura
	 */
	@Override
	protected void initData(Faktura faktura) {
		if (tableModel == null) {
			tableModelDef = new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS,
					FORMATS);
			tableModelDefExcel = new ObjectTableDef(COLUMN_NAMES, METHODS,
					PARAMS, FORMATS_EXCEL);

			tableModel = new ObjectTableModel(tableModelDef);
			tableModel.setEditable(false);
			table.setModel(tableModel);

			// Selskap
			TableColumn col = table.getColumnModel().getColumn(0);
			col.setPreferredWidth(50);

			// Avdnr
			col = table.getColumnModel().getColumn(1);
			col.setPreferredWidth(40);

			// Dato
			col = table.getColumnModel().getColumn(2);
			col.setPreferredWidth(80);

			// Type
			col = table.getColumnModel().getColumn(3);
			col.setPreferredWidth(40);

			// Konto
			col = table.getColumnModel().getColumn(4);
			col.setPreferredWidth(70);

			// Beløp
			col = table.getColumnModel().getColumn(5);
			col.setPreferredWidth(80);

			// Tekst
			col = table.getColumnModel().getColumn(6);
			col.setPreferredWidth(100);

			// Mva-kode
			col = table.getColumnModel().getColumn(7);
			col.setPreferredWidth(60);

			// Forfallsdato
			col = table.getColumnModel().getColumn(8);
			col.setPreferredWidth(80);

			// Fakturanr
			col = table.getColumnModel().getColumn(9);
			col.setPreferredWidth(80);

			// Kladd
			col = table.getColumnModel().getColumn(10);
			col.setPreferredWidth(50);
			
//			 Prosjekt
			col = table.getColumnModel().getColumn(11);
			col.setPreferredWidth(50);

			if (faktura != null) {

				fakturaDAO.loadLazy(faktura,
						new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_BOOK});
				Set<?> invoiceBooks = faktura.getRegnskapKladds();
				tableModel.setData(invoiceBooks);

			}
		}
	}

	/**
	 * @see no.ica.fraf.gui.invoice.AbstractExcelPanel#getNumcolList()
	 */
	@Override
	protected List<Integer> getNumcolList() {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		numList.add(1);
		numList.add(4);
		numList.add(5);
		return numList;
	}

	/**
	 * @see no.ica.fraf.gui.invoice.AbstractExcelPanel#getExcelFileName()
	 */
	@Override
	protected String getExcelFileName() {
		return "Bokforing";
	}

}
