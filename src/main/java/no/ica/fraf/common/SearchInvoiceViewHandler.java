package no.ica.fraf.common;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.InvoiceManagerInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse ved søk etter faktura
 * 
 * @author abr99
 * 
 */
public class SearchInvoiceViewHandler implements Closeable {
	/**
	 * 
	 */
	private PresentationModel presentationModel;

	/**
	 * 
	 */
	JXTable tableInvoices;

	/**
	 * 
	 */
	final ArrayListModel invoiceList;

	/**
	 * 
	 */
	final SelectionInList invoiceSelectionList;

	/**
	 * 
	 */
	InvoiceManagerInterface invoiceManagerInterface;

	/**
	 * 
	 */
	SearchConfig searchConfig;

	/**
	 * 
	 */
	JButton buttonShowInvoice;

	/**
	 * 
	 */
	InvoicePrinterInterface invoicePrinterInterface;

	/**
	 * 
	 */
	private InvoiceConfigInterface invoiceConfigInterface;

	/**
	 * @param aInvoiceManagerInterface
	 * @param aInvoicePrinterInterface
	 * @param aInvoiceConfigInterface
	 */
	public SearchInvoiceViewHandler(
			InvoiceManagerInterface aInvoiceManagerInterface,
			InvoicePrinterInterface aInvoicePrinterInterface,
			InvoiceConfigInterface aInvoiceConfigInterface) {
		invoiceConfigInterface = aInvoiceConfigInterface;
		invoicePrinterInterface = aInvoicePrinterInterface;
		searchConfig = new SearchConfig();
		presentationModel = new PresentationModel(searchConfig);
		invoiceList = new ArrayListModel();
		invoiceSelectionList = new SelectionInList((ListModel) invoiceList);
		invoiceManagerInterface = aInvoiceManagerInterface;
	}

	/**
	 * Lager tekstfelt for avdnr
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAvdnr() {
		return BasicComponentFactory.createTextField(presentationModel
				.getModel(SearchConfig.PROPERTY_AVDNR));
	}

	/**
	 * Lager tekstfelt for fakturanr
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldInvoiceNr() {
		return BasicComponentFactory.createTextField(presentationModel
				.getModel(SearchConfig.PROPERTY_INVOICE_NR));
	}

	/**
	 * Lager avbrytknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	/**
	 * Lager søkeknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonSearch(WindowInterface window) {
		return new JButton(new SearchAction(window));
	}

	/**
	 * Lager knapp for å vose faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonShowInvoice(WindowInterface window) {
		buttonShowInvoice = new JButton(new ShowInvoiceAction(window));
		buttonShowInvoice.setEnabled(false);
		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
		return buttonShowInvoice;
	}

	/**
	 * Lager tabell for å vise fakturaer
	 * 
	 * @return tabell
	 */
	public JXTable getTableInvoices() {
		tableInvoices = new JXTable();

		tableInvoices.setModel(invoiceConfigInterface
				.getTableModel(invoiceSelectionList));
		tableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableInvoices.setSelectionModel(new SingleListSelectionAdapter(
				invoiceSelectionList.getSelectionIndexHolder()));
		tableInvoices.setColumnControlVisible(true);

		tableInvoices.setDragEnabled(false);
		tableInvoices.setSortable(true);

		tableInvoices.setShowGrid(true);
		tableInvoices.packAll();
		return tableInvoices;
	}

	/**
	 * Søkekriterier
	 * 
	 * @author abr99
	 * 
	 */
	public class SearchConfig extends Model {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public static final String PROPERTY_INVOICE_NR = "invoiceNr";

		/**
		 * 
		 */
		public static final String PROPERTY_AVDNR = "avdnr";

		/**
		 * 
		 */
		private String avdnr;

		/**
		 * 
		 */
		private String invoiceNr;

		/**
		 * @return avdnr
		 */
		public String getAvdnr() {
			return avdnr;
		}

		/**
		 * @param avdnr
		 */
		public void setAvdnr(String avdnr) {
			this.avdnr = avdnr;
		}

		/**
		 * @return fakturanr
		 */
		public String getInvoiceNr() {
			return invoiceNr;
		}

		/**
		 * @param invoiceNr
		 */
		public void setInvoiceNr(String invoiceNr) {
			this.invoiceNr = invoiceNr;
		}

	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Søker
	 * 
	 * @author abr99
	 * 
	 */
	private class SearchAction extends AbstractAction {
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
		public SearchAction(WindowInterface aWindow) {
			super("Søk");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new InvoiceSearcher(), null);
		}
	}

	/**
	 * Søker i egen tråd
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceSearcher implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Søker etter fakturaer...");

			List<InvoiceInterface> invoices = invoiceManagerInterface
					.findByInvoiceNrAndOrAvdnr(searchConfig.getInvoiceNr(),
							searchConfig.getAvdnr());
			invoiceList.clear();
			if (invoices != null) {
				invoiceList.addAll(invoices);
			}

			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			tableInvoices.packAll();

		}

	}

	/**
	 * Viser faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class ShowInvoiceAction extends AbstractAction {
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
		public ShowInvoiceAction(WindowInterface aWindow) {
			super("Vis faktura...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			InvoiceInterface invoice = (InvoiceInterface) invoiceSelectionList
					.getSelection();
			if (invoice != null) {
				invoicePrinterInterface.setInvoice(invoice);
				GuiUtil.runInThreadWheel(window.getRootPane(),
						invoicePrinterInterface, null);
			}

		}
	}

	/**
	 * Håndterer selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonShowInvoice.setEnabled(invoiceSelectionList.hasSelection());

		}

	}
}
