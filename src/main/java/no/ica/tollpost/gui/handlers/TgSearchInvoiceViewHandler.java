package no.ica.tollpost.gui.handlers;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;

import no.ica.fraf.common.AbstractSearchViewHandler;
import no.ica.fraf.common.InvoiceConfigInterface;
import no.ica.fraf.common.InvoicePrinterInterface;
import no.ica.fraf.common.SearchInvoiceViewHandler.SearchConfig;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.InvoiceManagerInterface;

public class TgSearchInvoiceViewHandler extends AbstractSearchViewHandler{
	InvoiceManagerInterface invoiceManagerInterface;
	private InvoiceConfigInterface invoiceConfigInterface;
	InvoicePrinterInterface invoicePrinterInterface;
	SearchConfig searchConfig;
	private PresentationModel presentationModel;
	public TgSearchInvoiceViewHandler(
			InvoiceManagerInterface aInvoiceManagerInterface,
			InvoicePrinterInterface aInvoicePrinterInterface,
			InvoiceConfigInterface aInvoiceConfigInterface) {
		invoiceConfigInterface = aInvoiceConfigInterface;
		invoicePrinterInterface = aInvoicePrinterInterface;
		searchConfig = new SearchConfig();
		presentationModel = new PresentationModel(searchConfig);
		/*invoiceList = new ArrayListModel();
		invoiceSelectionList = new SelectionInList((ListModel) invoiceList);*/
		invoiceManagerInterface = aInvoiceManagerInterface;
	}

	public JTextField getTextFieldAvdnr() {
		return BasicComponentFactory.createTextField(presentationModel
				.getModel(SearchConfig.PROPERTY_AVDNR));
	}
	
	public JTextField getTextFieldInvoiceNr() {
		return BasicComponentFactory.createTextField(presentationModel
				.getModel(SearchConfig.PROPERTY_INVOICE_NR));
	}
	
	@Override
	protected Threadable getSearchClass() {
		return new InvoiceSearcher();
	}

	@Override
	protected InvoiceConfigInterface getConfig() {
		return invoiceConfigInterface;
	}

	@Override
	protected InvoiceInterface getSelectedInvoice() {
		return (InvoiceInterface) objectSelectionList.getElementAt(table.convertRowIndexToModel(objectSelectionList.getSelectionIndex()));
	}

	@Override
	protected InvoicePrinterInterface getInvoicePrinter() {
		return invoicePrinterInterface;
	}
	
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
			objectList.clear();
			if (invoices != null) {
				objectList.addAll(invoices);
			}

			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			table.packAll();

		}

	}

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

}
