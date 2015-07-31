package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.FakturaLinjeDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.list.ArrayListModel;

/**
 * Hjelpeklasse for visning av fakturalinjer
 * 
 * @author abr99
 * 
 */
public class InvoiceLineViewHandler implements Closeable {
	/**
	 * 
	 */
	JXTable table;

	/**
	 * 
	 */
	private final ArrayListModel invoiceLineList;

	/**
	 * 
	 */
	boolean canceled = false;

	/**
	 * 
	 */
	boolean allSelected = false;
	/**
	 * 
	 */
	boolean allVisible=true;

	/**
	 * 
	 */
	private TableModelInvoiceLine tableModel;

	/**
	 * @param invoiceId
	 */
	public InvoiceLineViewHandler(Integer invoiceId) {
		invoiceLineList = new ArrayListModel();

		FakturaLinjeDAO fakturaLinjeDAO = (FakturaLinjeDAO) ModelUtil
				.getBean("fakturaLinjeDAO");
		List<FakturaLinje> invoiceLines = fakturaLinjeDAO
				.findByFakturaId(invoiceId);
		if (invoiceLines != null) {
			for(FakturaLinje linje:invoiceLines){
				if(!GuiUtil.convertNumberToBoolean(linje.getReversert())){
					invoiceLineList.add(linje);
				}else{
					allVisible=false;
				}
			}
			
		}
	}

	/**
	 * Lager tabell for fakturalinjer
	 * 
	 * @return tabell
	 */
	public JXTable getTableInvoiceLines() {
		tableModel = new TableModelInvoiceLine(invoiceLineList);
		table = new JXTable(tableModel);
		table.setColumnControlVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Beskrivelse
		table.getColumnExt(0).setPreferredWidth(90);
		// Peiode
		table.getColumnExt(1).setPreferredWidth(50);
		// Sats
		table.getColumnExt(2).setPreferredWidth(40);
		// Omsetning
		table.getColumnExt(3).setPreferredWidth(90);
		// Avregning
		table.getColumnExt(4).setPreferredWidth(90);
		// Grunnlag
		table.getColumnExt(5).setPreferredWidth(90);
		// Beløp
		table.getColumnExt(6).setPreferredWidth(80);
		// Mvakode
		table.getColumnExt(7).setPreferredWidth(60);
		// Mvabeløp
		table.getColumnExt(8).setPreferredWidth(70);
		// Total
		table.getColumnExt(9).setPreferredWidth(80);
		return table;
	}

	/**
	 * Lager ok-knapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonOk(WindowInterface window) {
		return new CancelButton(window, this, false, "Ok", IconEnum.ICON_OK,
				null);
	}

	/**
	 * Lager avbrytknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, new CancelAction());
	}

	/**
	 * Lager sjekkboks for å velge alle
	 * 
	 * @return sjekkboks
	 */
	public JCheckBox getCheckBoxSelectAll() {
		PropertyAdapter selectedValueModel = new PropertyAdapter(this,
				"allSelected");
		JCheckBox checkBox = BasicComponentFactory.createCheckBox(
				selectedValueModel, "Alle");
		checkBox.addActionListener(new CheckBoxActionListener());
		return checkBox;
	}

	/**
	 * Sjekker om alle er valgt
	 * 
	 * @return true dersom alle er valgt
	 */
	public Boolean getAllSelected() {
		return allSelected;
	}

	/**
	 * Setter alle fakturalinjer valgt
	 * 
	 * @param selected
	 */
	public void setAllSelected(Boolean selected) {
		allSelected = selected;
	}

	/**
	 * Sjekker om dialog er avbrutt
	 * 
	 * @return true dersom avbrutt
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * Henter valgte ider for fakturalinjer
	 * 
	 * @return ider
	 */
	public List<Integer> getSelectedIds() {
		if (allSelected&&allVisible) {
			return null;
		}
		int[] selectedRows = table.getSelectedRows();
		List<Integer> ids = new ArrayList<Integer>();
		FakturaLinje linje;
		for (int i = 0; i < selectedRows.length; i++) {
			linje = tableModel.getFakturaLinje(table
					.convertRowIndexToModel(selectedRows[i]));
			ids.add(linje.getFakturaLinjeId());
		}
		if (ids.size() != 0) {
			return ids;
		}
		return null;
	}

	/**
	 * Tabellmodell for fakturalinjer
	 * 
	 * @author abr99
	 * 
	 */
	private static final class TableModelInvoiceLine extends
			AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Beskrivelse", "Periode",
				"Sats", "Omsetning", "Avregning", "Grunnlag", "Beløp",
				"Mvakode", "Mva-beløp", "Total" };

		/**
		 * @param listModel
		 */
		TableModelInvoiceLine(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * Henter fakturalinje for rad
		 * 
		 * @param rowIndex
		 * @return fakturalinje
		 */
		public FakturaLinje getFakturaLinje(int rowIndex) {
			return (FakturaLinje) getRow(rowIndex);
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (getRowCount() < 1) {
				return null;
			}
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			FakturaLinje fakturaLinje = (FakturaLinje) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return fakturaLinje.getLinjeBeskrivelse();
			case 1:
				return fakturaLinje.getPeriode();
			case 2:
				return fakturaLinje.getSats();
			case 3:
				if (fakturaLinje.getOmsetningBelop() != null) {
					return currencyFormat.format(fakturaLinje
							.getOmsetningBelop());
				}
				return null;
			case 4:
				if (fakturaLinje.getAvregningBelop() != null) {
					return currencyFormat.format(fakturaLinje
							.getAvregningBelop());
				}
				return null;
			case 5:
				if (fakturaLinje.getGrunnlagBelop() != null) {
					return currencyFormat.format(fakturaLinje
							.getGrunnlagBelop());
				}
				return null;
			case 6:
				if (fakturaLinje.getBelop() != null) {
					return currencyFormat.format(fakturaLinje.getBelop());
				}
				return null;
			case 7:
				return fakturaLinje.getMvaKode();
			case 8:
				if (fakturaLinje.getMvaBelop() != null) {
					return currencyFormat.format(fakturaLinje.getMvaBelop());
				}
				return null;
			case 9:
				if (fakturaLinje.getTotalBelop() != null) {
					return currencyFormat.format(fakturaLinje.getTotalBelop());
				}
				return null;
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

		/**
		 * @see javax.swing.table.TableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
			case 1:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return String.class;

			case 2:
				return BigDecimal.class;

			default:
				throw new IllegalStateException("Unknown column");
			}
		}
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Håndterer avrytknapp
	 * 
	 * @author abr99
	 * 
	 */
	private class CancelAction implements Closeable {

		/**
		 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
		 */
		public boolean canClose(String actionString) {
			canceled = true;
			return true;
		}

	}

	/**
	 * Håndterer sjekkboks for å velge alle
	 * 
	 * @author abr99
	 * 
	 */
	private class CheckBoxActionListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (allSelected) {
				table.addRowSelectionInterval(0, table.getRowCount() - 1);
			}

		}

	}
}
