package no.ica.fraf.gui.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import no.ica.fraf.model.FakturaLinje;

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
 * Panel som viser fakturalinjer
 * 
 * @author abr99
 * 
 */
public class PanelInvoiceLines extends AbstractExcelPanel {
	/**
	 * 
	 */
	private static final String[] COLUMN_NAMES = { "Beskrivelse", "Periode",
			"Sats", "Omsetning", "Avregning", "Grunnlag", "Beløp", "Mva-kode",
			"Mva-beløp", "Total" };

	/**
	 * 
	 */
	private static final String[] METHODS = { "LinjeBeskrivelse", "Periode",
			"Sats", "OmsetningBelop", "AvregningBelop", "GrunnlagBelop",
			"Belop", "MvaKode", "MvaBelop", "TotalBelop" };

	/**
	 * 
	 */
	private static final Class[] PARAMS = { String.class, String.class,
			BigDecimal.class, BigDecimal.class, BigDecimal.class,
			BigDecimal.class, BigDecimal.class, String.class, BigDecimal.class,
			BigDecimal.class };

	/**
	 * 
	 */
	private static final ColumnFormatEnum[] FORMATS = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.NONE,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY };

	/**
	 * 
	 */
	private static final ColumnFormatEnum[] FORMATS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktør
	 * 
	 * @param faktura
	 * @param applUser
	 * @param parent
	 */
	public PanelInvoiceLines(Faktura faktura, ApplUser applUser,
			JInternalFrame parent) {
		super(faktura, applUser, parent);
		initData(faktura);
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

			// Beskrivelse
			TableColumn col = table.getColumnModel().getColumn(0);
			col.setPreferredWidth(100);

			// Periode
			col = table.getColumnModel().getColumn(1);
			col.setPreferredWidth(70);

			// Stats
			col = table.getColumnModel().getColumn(2);
			col.setPreferredWidth(40);

			// Omsetning
			col = table.getColumnModel().getColumn(3);
			col.setPreferredWidth(80);

			// Avregning
			col = table.getColumnModel().getColumn(4);
			col.setPreferredWidth(80);

			// Grunnlag
			col = table.getColumnModel().getColumn(5);
			col.setPreferredWidth(80);

			// Beløp
			col = table.getColumnModel().getColumn(6);
			col.setPreferredWidth(80);

			// Mva-kode
			col = table.getColumnModel().getColumn(7);
			col.setPreferredWidth(60);

			// Mva-beløp
			col = table.getColumnModel().getColumn(8);
			col.setPreferredWidth(80);

			// Total
			col = table.getColumnModel().getColumn(9);
			col.setPreferredWidth(80);

			if (faktura != null) {

				fakturaDAO.loadLazy(faktura,
						new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_LINES});
				fakturaDAO.lazyLoadBunt(faktura);
				Set<FakturaLinje> invoiceLines = faktura.getFakturaLinjes();
				tableModel.setData(invoiceLines);

			}
		}
	}

	/**
	 * @see no.ica.fraf.gui.invoice.AbstractExcelPanel#getNumcolList()
	 */
	@Override
	protected List<Integer> getNumcolList() {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		numList.add(2);
		numList.add(3);
		numList.add(4);
		numList.add(5);
		numList.add(6);
		numList.add(8);
		numList.add(9);
		return numList;
	}

	/**
	 * @see no.ica.fraf.gui.invoice.AbstractExcelPanel#getExcelFileName()
	 */
	@Override
	protected String getExcelFileName() {
		return "Fakturalinjer";
	}

}
