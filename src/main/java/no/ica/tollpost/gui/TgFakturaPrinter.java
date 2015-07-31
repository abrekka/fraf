/**
 * 
 */
package no.ica.tollpost.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;

import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.InvoicePrinterInterface;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.EflowUsersV;
import no.ica.fraf.report.ReportViewer;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.PaperInvoice;
import no.ica.tollpost.gui.handlers.BatchViewHandler.TgFakturaTableModel;
import no.ica.tollpost.model.TgFaktura;
import no.ica.tollpost.model.TgFakturaLinjeV;
import no.ica.tollpost.service.TgFakturaLinjeVManager;

import com.jgoodies.binding.list.SelectionInList;

public class TgFakturaPrinter implements InvoicePrinterInterface {
	private WindowInterface window;

	private boolean singleInvoice = false;

	//private static Map<String, EflowUsersV> eflowUsers;

	//private EflowUsersVManager eflowUsersVManager;

	private BuntDAO buntDAO;

	private Bunt bunt;
	private TgFakturaLinjeVManager tgFakturaLinjeVManager;
	private BokfSelskap selskap;
	private TgFaktura currentTgFaktura;

	public TgFakturaPrinter(WindowInterface aWindow,
			//EflowUsersVManager aEflowUsersVManager, 
			BuntDAO aBuntDAO,
			Bunt aBunt,TgFakturaLinjeVManager aTgFakturaLinjeVManager,BokfSelskap aBokfSelskap) {
		selskap=aBokfSelskap;
		tgFakturaLinjeVManager=aTgFakturaLinjeVManager;
		bunt = aBunt;
		buntDAO = aBuntDAO;
		//eflowUsersVManager = aEflowUsersVManager;
		window = aWindow;
	}
	
	public TgFakturaPrinter(TgFaktura tgFaktura,
			TgFakturaLinjeVManager aTgFakturaLinjeVManager, BokfSelskap aSelskap,WindowInterface aWindow) {
		window=aWindow;
		singleInvoice = true;
		currentTgFaktura = tgFaktura;
		tgFakturaLinjeVManager = aTgFakturaLinjeVManager;
		
		selskap = aSelskap;
	}
	
	public TgFakturaPrinter(
			TgFakturaLinjeVManager aTgFakturaLinjeVManager, BokfSelskap aSelskap,WindowInterface aWindow) {
		window=aWindow;
		tgFakturaLinjeVManager = aTgFakturaLinjeVManager;
		selskap = aSelskap;
	}

	public void enableComponents(boolean enable) {
		// TODO Auto-generated method stub

	}

	public Object doWork(Object[] params, JLabel labelInfo) {
		String errorString = null;
		labelInfo.setText("Genererer fakturaer for Tollpost");
		if (!singleInvoice) {
			errorString = generateInvoices();
		} else {
			errorString = generateInvoice();
		}
		return errorString;
	}

	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
					object.toString());
		}

	}
	
	private String generateInvoice() {
		String errorString = null;
		try {
			List<BigDecimal> invoiceNumbers = new ArrayList<BigDecimal>();
			invoiceNumbers.add(currentTgFaktura.getFakturaNr());
			List<TgFakturaLinjeV> tgFakturaLinjeVs;
			if (invoiceNumbers != null && invoiceNumbers.size() != 0) {
				tgFakturaLinjeVs = tgFakturaLinjeVManager
						.findByInvoiceNumbers(invoiceNumbers);
			} else {
				tgFakturaLinjeVs = new ArrayList<TgFakturaLinjeV>();
			}

			
			String icaKontoTekst = ApplParamUtil.getStringParam("ica_konto_tekst");
			
			
			String icaKonto = ApplParamUtil.getStringParam("ica_konto_kontonr");
			
			TgFakturaTableModel tableModel = new TgFakturaTableModel(
					new SelectionInList(tgFakturaLinjeVs), selskap, icaKontoTekst,
					icaKonto);
			ReportViewer reportViewer = new ReportViewer("Tollpost faktura");
			GuiUtil.locateOnScreenCenter(reportViewer);
			FrafMain.getInstance().addInternalFrame(reportViewer);
			reportViewer.generateTollpostInvoiceReport(tableModel);
		} catch (FrafException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		return errorString;
	}


	private String generateInvoices() {
		String errorString = null;
		/*if (eflowUsers == null) {
			eflowUsers = eflowUsersVManager.getEflowUsers();
		}*/
		try {
			// List<InvoiceItemV> invoiceItemVs = invoiceItemVManager
			// .findByBatchId(batch.getBatchId());

			buntDAO.lazyLoadBatch(bunt,
					new LazyLoadBatchEnum[] { LazyLoadBatchEnum.INVOICES });
			Set<TgFaktura> tgFakturaer = bunt.getTgFakturas();

			// invoiceItemVs = getPrintableInvoices(invoiceItemVs);

			List<BigDecimal> invoiceNumbers = getPrintableInvoices(tgFakturaer);

			List<TgFakturaLinjeV> tgFakturaLinjeVs;
			if (invoiceNumbers != null && invoiceNumbers.size() != 0) {
				tgFakturaLinjeVs = tgFakturaLinjeVManager
						.findByInvoiceNumbers(invoiceNumbers);
			} else {
				tgFakturaLinjeVs = new ArrayList<TgFakturaLinjeV>();
			}

			String icaKontoTekst = ApplParamUtil.getStringParam("ica_konto_tekst");
			 
			
			String icaKonto = ApplParamUtil.getStringParam("ica_konto_kontonr");
			
			TgFakturaTableModel tableModel = new TgFakturaTableModel(
					new SelectionInList(tgFakturaLinjeVs), selskap,
					icaKontoTekst, icaKonto);
			ReportViewer reportViewer = new ReportViewer("Tollpost fakturaer");
			GuiUtil.locateOnScreenCenter(reportViewer);
			FrafMain.getInstance().addInternalFrame(reportViewer);
			reportViewer.generateTollpostInvoiceReport(tableModel);
		} catch (FrafException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		return errorString;
	}

	private List<BigDecimal> getPrintableInvoices(Set<TgFaktura> allInvoices)
			throws FrafException {
		List<BigDecimal> invoiceNumbers = new ArrayList<BigDecimal>();
		//PaperInvoice paperInvoice = new PaperInvoice();
		//paperInvoice.init();
		PaperManager paperManager=(PaperManager)ModelUtil.getBean(PaperManager.MANAGER_NAME);
		if (allInvoices != null) {
			for (TgFaktura invoice : allInvoices) {
				if (paperManager.shouldHavePaperInvoice(invoice.getStoreNo())) {
					invoiceNumbers.add(invoice.getFakturaNr());
				}
			}
		}
		// return invoiceItems;
		return invoiceNumbers;
	}
	/*private Boolean storeInBasware(String avdnr) {
		if (eflowUsers.containsKey(avdnr)) {
			return true;
		}
		return false;
	}*/

	public void setInvoice(InvoiceInterface invoice) {
		currentTgFaktura=(TgFaktura)invoice;
		singleInvoice=true;
		
	}

}