package no.ica.tollpost.gui.handlers;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import no.ica.fraf.common.AbstractSearchViewHandler;
import no.ica.fraf.common.InvoiceConfigInterface;
import no.ica.fraf.common.InvoicePrinterInterface;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.tollpost.gui.TollpostPackageConfig;
import no.ica.tollpost.model.TgImport;
import no.ica.tollpost.service.TgFakturaManager;
import no.ica.tollpost.service.TgImportManager;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;

public class TgSearchPackageViewHandler extends AbstractSearchViewHandler {
	SearchPackageConfig searchConfig;

	private PresentationModel presentationModel;
	InvoicePrinterInterface invoicePrinterInterface;

	public TgSearchPackageViewHandler(InvoicePrinterInterface aInvoicePrinterInterface) {
		invoicePrinterInterface = aInvoicePrinterInterface;
		searchConfig = new SearchPackageConfig();
		presentationModel = new PresentationModel(searchConfig);
	}
	
	public JTextField getTextFieldSendNr() {
		return BasicComponentFactory.createTextField(presentationModel
				.getModel(SearchPackageConfig.PROPERTY_SEND_NR));
	}
	public JTextField getTextFieldKolliId() {
		return BasicComponentFactory.createTextField(presentationModel
				.getModel(SearchPackageConfig.PROPERTY_KOLLI_ID));
	}

	@Override
	protected Threadable getSearchClass() {
		return new PackageSearcher();
	}

	@Override
	protected InvoiceConfigInterface getConfig() {
		return new TollpostPackageConfig();
	}

	@Override
	protected InvoiceInterface getSelectedInvoice() {
		TgImport tgImport = (TgImport)objectSelectionList.getElementAt(table.convertRowIndexToView(objectSelectionList.getSelectionIndex()));
		
		return tgImport.getTgFaktura();
	}

	@Override
	protected InvoicePrinterInterface getInvoicePrinter() {
		return invoicePrinterInterface;
	}

	private class PackageSearcher implements Threadable {

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
			labelInfo.setText("Søker etter pakke...");

			TgImportManager tgImportManager = (TgImportManager) ModelUtil
					.getBean("tgImportManager");
			List<TgImport> importList = tgImportManager.findBySendNrAndKolliId(
					searchConfig.getSendNr(), searchConfig.getKolliId());

			objectList.clear();
			if (importList != null) {
				
				for(TgImport tgImport:importList){
					tgImportManager.lazyLoadBunt(tgImport);
				}
				objectList.addAll(importList);
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

	public class SearchPackageConfig extends Model {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public static final String PROPERTY_SEND_NR = "sendNr";

		/**
		 * 
		 */
		public static final String PROPERTY_KOLLI_ID = "kolliId";

		/**
		 * 
		 */
		private String sendNr;

		/**
		 * 
		 */
		private String kolliId;

		/**
		 * @return avdnr
		 */
		public String getSendNr() {
			return sendNr;
		}

		/**
		 * @param avdnr
		 */
		public void setSendNr(String sendNr) {
			this.sendNr = sendNr;
		}

		/**
		 * @return fakturanr
		 */
		public String getKolliId() {
			return kolliId;
		}

		/**
		 * @param invoiceNr
		 */
		public void setKolliId(String kolliId) {
			this.kolliId = kolliId;
		}

	}

}
