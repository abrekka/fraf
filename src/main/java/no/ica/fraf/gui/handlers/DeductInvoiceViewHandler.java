package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.InvoiceReport;
import no.ica.fraf.gui.invoice.InvoiceFrame;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visinig og håndtering av avregningsfakturaer
 * 
 * @author abr99
 * 
 */
public class DeductInvoiceViewHandler {
	/**
	 * 
	 */
	private JXTable table;

	/**
	 * 
	 */
	private final ArrayListModel invoiceList;

	/**
	 * 
	 */
	final SelectionInList invoiceSelectionList;

	/**
	 * 
	 */
	Bunt lastBunt;

	/**
	 * 
	 */
	private FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	JButton buttonPrintInvoice;

	/**
	 * 
	 */
	JButton buttonShowInvoice;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * 
	 */
	//EflowUsersVManager eflowUsersVManager;

	DeductBatchViewHandler deductBatchViewHandler;

	public DeductInvoiceViewHandler(
			DeductBatchViewHandler aDeductBatchViewHandler,
			FakturaDAO aFakturaDAO, ApplUser aApplUser
			//,EflowUsersVManager aEflowUsersVManager
			) {
		//eflowUsersVManager = aEflowUsersVManager;
		applUser = aApplUser;
		fakturaDAO = aFakturaDAO;
		deductBatchViewHandler = aDeductBatchViewHandler;
		invoiceList = new ArrayListModel();
		invoiceSelectionList = new SelectionInList((ListModel) invoiceList);
		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new SelectionEmptyHandler());
	}

	/**
	 * Henter komponentlytter
	 * 
	 * @return komponentlytter
	 */
	public ComponentListener getInvoiceComponentListener() {
		return new InvoiceComponentListener();
	}

	/**
	 * Lager tabell for fakturaer
	 * 
	 * @return tabell
	 */
	public JXTable getTableInvoice() {
		table = new JXTable();

		table.setModel(new DeductInvoiceTableModel(invoiceSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				invoiceSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);

		table.setDragEnabled(false);

		table.setShowGrid(true);
		table.setSortable(true);
		invoiceSelectionList.clearSelection();

		// Id
		table.getColumnExt(0).setPreferredWidth(50);
		// Opprettet
		table.getColumnExt(1).setPreferredWidth(100);
		// Opprettet av
		table.getColumnExt(2).setPreferredWidth(100);

		return table;

	}

	/**
	 * Lager knapp for utskrift av faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonPrintInvoice(WindowInterface window) {
		buttonPrintInvoice = new JButton(new PrintAction(window));
		buttonPrintInvoice.setEnabled(false);
		return buttonPrintInvoice;
	}

	/**
	 * Lager knapp for å vise faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonShowInvoice(WindowInterface window) {
		buttonShowInvoice = new JButton(new ShowAction(window, applUser));
		buttonShowInvoice.setEnabled(false);
		return buttonShowInvoice;
	}

	/**
	 * Tabellmodell for avregningsfakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private static final class DeductInvoiceTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Fakturanr", "Avdnr",
				"Fakturadato", "Forfallsdato", "Tittel", "Beløp", "Mva-beløp",
				"Total" };

		/**
		 * 
		 */
		private final static SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd.MM.yyyy");

		/**
		 * @param listModel
		 */
		public DeductInvoiceTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Faktura faktura = (Faktura) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return faktura.getFakturaNr();
			case 1:
				return faktura.getAvdnr();
			case 2:
				return dateFormat.format(faktura.getFakturaDato());
			case 3:
				if (faktura.getForfallDato() != null) {
					return dateFormat.format(faktura.getForfallDato());
				}
				return null;
			case 4:
				return faktura.getFakturaTittel();
			case 5:
				return faktura.getBelop();
			case 6:
				return faktura.getMvaBelop();
			case 7:
				return faktura.getTotalBelop();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Komponentlytter
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceComponentListener extends ComponentAdapter {

		/**
		 * 
		 */
		public InvoiceComponentListener() {
		}

		/**
		 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
		 */
		@Override
		public void componentShown(ComponentEvent evt) {
			Bunt bunt = deductBatchViewHandler.getSelectedBatch();
			if (lastBunt == null || !lastBunt.equals(bunt)) {
				lastBunt = bunt;
				if (bunt != null) {
					GuiUtil.setWaitCursor(FrafMain.getInstance());
					loadInvoices(bunt);
					GuiUtil.setDefaultCursor(FrafMain.getInstance());
				}
			}
		}
	}

	/**
	 * Laster fakturaer
	 * 
	 * @param bunt
	 */
	void loadInvoices(Bunt bunt) {
		invoiceList.clear();

		List<Faktura> fakturaer = fakturaDAO.findByBuntId(bunt.getBuntId());

		if (fakturaer != null) {
			invoiceList.addAll(fakturaer);
		}

		table.packAll();
	}

	/**
	 * Utskrift
	 * 
	 * @author abr99
	 * 
	 */
	private class PrintAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public PrintAction(WindowInterface aWindow) {
			super("Skriv ut...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			InvoiceReport invoiceReport = new InvoiceReport(window,
					 null);
			Faktura faktura = (Faktura) invoiceSelectionList.getSelection();
			invoiceReport.showInvoiceReport(faktura, null);

		}
	}

	/**
	 * Vis faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class ShowAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private ApplUser applUser1;

		/**
		 * @param aWindow
		 * @param aApplUser
		 */
		public ShowAction(WindowInterface aWindow, ApplUser aApplUser) {
			super("Vis...");
			window = aWindow;
			applUser1 = aApplUser;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.setWaitCursor(window.getComponent());

			Faktura faktura = (Faktura) invoiceSelectionList.getSelection();

			InvoiceFrame invoiceFrame = new InvoiceFrame(faktura, applUser1);

			invoiceFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
					.getSize(), invoiceFrame.getSize()));
			FrafMain.getInstance().addInternalFrame(invoiceFrame);

			invoiceFrame.setVisible(true);
			try {
				invoiceFrame.setSelected(true);
			} catch (java.beans.PropertyVetoException ex) {
				ex.printStackTrace();
			}
			GuiUtil.setDefaultCursor(window.getComponent());

		}
	}

	/**
	 * Håndterer selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class SelectionEmptyHandler implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			boolean hasSelection = invoiceSelectionList.hasSelection();
			buttonPrintInvoice.setEnabled(hasSelection);
			buttonShowInvoice.setEnabled(hasSelection);

		}

	}
}
