package no.ica.elfa.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.ListModel;

import no.ica.elfa.model.Invoice;
import no.ica.elfa.model.InvoiceItemV;
import no.ica.elfa.service.InvoiceItemVManager;
import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.InvoicePrinterInterface;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
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

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Håndterer utskrift av ELFA-fakturaer
 * 
 * @author abr99
 * 
 */
public class InvoicePrinter implements InvoicePrinterInterface {
	/**
	 * 
	 */
	private Bunt bunt;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private BuntDAO buntDAO;

	/**
	 * 
	 */
	private InvoiceItemVManager invoiceItemVManager;

	/**
	 * 
	 */
	private BokfSelskap selskap;

	/**
	 * 
	 */
	//private EflowUsersVManager eflowUsersVManager;

	/**
	 * 
	 */
	//private static Map<String, EflowUsersV> eflowUsers;

	/**
	 * 
	 */
	private boolean singleInvoice = false;

	/**
	 * 
	 */
	private Invoice currentInvoice;

	public InvoicePrinter(Bunt invoiceBunt, WindowInterface aWindow,
			BuntDAO aBuntDAO, InvoiceItemVManager aInvoiceItemVManager,
			BokfSelskap aSelskap
			//, EflowUsersVManager aEflowUsersVManager
			) {
		bunt = invoiceBunt;
		window = aWindow;
		buntDAO = aBuntDAO;
		invoiceItemVManager = aInvoiceItemVManager;
		selskap = aSelskap;
		//eflowUsersVManager = aEflowUsersVManager;
	}

	public InvoicePrinter(Invoice invoice,
			InvoiceItemVManager aInvoiceItemVManager, BokfSelskap aSelskap) {
		singleInvoice = true;
		currentInvoice = invoice;
		invoiceItemVManager = aInvoiceItemVManager;
		selskap = aSelskap;
	}

	public InvoicePrinter(InvoiceItemVManager aInvoiceItemVManager,
			BokfSelskap aSelskap) {
		invoiceItemVManager = aInvoiceItemVManager;
		selskap = aSelskap;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
	}

	/**
	 * Genererer fakturaer
	 * 
	 * @return feilmelding
	 */
	private String generateInvoices() {
		String errorString = null;
		/*if (eflowUsers == null) {
			eflowUsers = eflowUsersVManager.getEflowUsers();
		}*/
		try {
			buntDAO
					.lazyLoadBatch(
							bunt,
							new LazyLoadBatchEnum[] { LazyLoadBatchEnum.ELFA_INVOICES });
			Set<Invoice> invoices = bunt.getInvoices();

			List<BigDecimal> invoiceNumbers = getPrintableInvoices(invoices);
			List<InvoiceItemV> invoiceItemVs;
			if (invoiceNumbers != null && invoiceNumbers.size() != 0) {
				invoiceItemVs = invoiceItemVManager
						.findByInvoiceNumbers(invoiceNumbers);
			} else {
				invoiceItemVs = new ArrayList<InvoiceItemV>();
			}

			String icaKontoTekst = ApplParamUtil.getStringParam("ica_konto_tekst");
			 
			String icaKonto = ApplParamUtil.getStringParam("ica_konto_kontonr");
			
			ElfaInvoiceTableModel tableModel = new ElfaInvoiceTableModel(
					new SelectionInList(invoiceItemVs), selskap, icaKontoTekst,
					icaKonto);
			ReportViewer reportViewer = new ReportViewer("Elfa fakturaer");
			GuiUtil.locateOnScreenCenter(reportViewer);
			FrafMain.getInstance().addInternalFrame(reportViewer);
			reportViewer.generateElfaInvoiceReport(tableModel);
		} catch (FrafException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		return errorString;
	}

	/**
	 * Genererer faktura
	 * 
	 * @return feilmelding
	 */
	private String generateInvoice() {
		String errorString = null;
		try {
			List<BigDecimal> invoiceNumbers = new ArrayList<BigDecimal>();
			invoiceNumbers.add(currentInvoice.getInvoiceNr());
			List<InvoiceItemV> invoiceItemVs;
			if (invoiceNumbers != null && invoiceNumbers.size() != 0) {
				invoiceItemVs = invoiceItemVManager
						.findByInvoiceNumbers(invoiceNumbers);
			} else {
				invoiceItemVs = new ArrayList<InvoiceItemV>();
			}

			String icaKontoTekst = ApplParamUtil.getStringParam("ica_konto_tekst");
			

			String icaKonto = ApplParamUtil.getStringParam("ica_konto_kontonr");
			

			ElfaInvoiceTableModel tableModel = new ElfaInvoiceTableModel(
					new SelectionInList(invoiceItemVs), selskap, icaKontoTekst,
					icaKonto);
			ReportViewer reportViewer = new ReportViewer("Elfa fakturaer");
			GuiUtil.locateOnScreenCenter(reportViewer);
			FrafMain.getInstance().addInternalFrame(reportViewer);
			reportViewer.generateElfaInvoiceReport(tableModel);
		} catch (FrafException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		return errorString;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		String errorString = null;
		labelInfo.setText("Genererer fakturaer for ELFA");
		if (!singleInvoice) {
			errorString = generateInvoices();
		} else {
			errorString = generateInvoice();
		}
		return errorString;
	}

	/**
	 * Henter fakturaer som skal på papir
	 * 
	 * @param allInvoices
	 * @return liste med fakturanumre
	 * @throws FrafException
	 */
	private List<BigDecimal> getPrintableInvoices(Set<Invoice> allInvoices)
			throws FrafException {
		List<BigDecimal> invoiceNumbers = new ArrayList<BigDecimal>();
		/*PaperInvoice paperInvoice = new PaperInvoice();
		paperInvoice.init();*/
		PaperManager paperManager=(PaperManager)ModelUtil.getBean(PaperManager.MANAGER_NAME);
		if (allInvoices != null) {
			for (Invoice invoice : allInvoices) {
				if (paperManager.shouldHavePaperInvoice(invoice.getStoreNo())) {
					invoiceNumbers.add(invoice.getInvoiceNr());
				}
			}
		}
		return invoiceNumbers;
	}

	/**
	 * Sjekker om faktura skal til basware
	 * 
	 * @param avdnr
	 * @return true dersom faktura skal til basware
	 */
	/*private Boolean storeInBasware(String avdnr) {
		if (eflowUsers.containsKey(avdnr)) {
			return true;
		}
		return false;
	}*/

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}

	}

	/**
	 * Tabellmodell for fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private static class ElfaInvoiceTableModel extends AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private BokfSelskap selskap;

		/**
		 * 
		 */
		private String icaKontoTekst;

		/**
		 * 
		 */
		private String icaKonto;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "INVOICE_ID",
				"MOTTAKER_NAVN", "JURIDISK_NAVN", "ADRESSE1", "ADRESSE2",
				"POSTNR", "POSTSTED", "FAKTURA_DATO", "FORFALL_DATO",
				"FAKTURA_NR", "AVDNR", "FORETAKSNUMMER", "FAKTURERT_AV",
				"AVSENDER_KONTONUMMER", "PHONE", "FAX", "KID", "TOTAL_BELOP",
				"MVA_BELOP", "BELOP", "AVSENDER_ADRESSE1", "AVSENDER_ADRESSE2",
				"AVSENDER_ADRESSE3", "AVSENDER_NAVN", "ARTICLE_NO",
				"ARTICLE_NAME", "NUMBER_OF_ARTICLES", "ARTICLE_PRICE",
				"ITEM_AMOUNT_TOTAL", "LINE_COMMISSION_AMOUNT_STO",
				"ITEM_VAT_AMOUNT", "INVOICE_AMOUNT_ITEM",
				"COMMISSION_AMOUNT_STO", "ICA_KONTO_TEKST", "INVOICE_HEADING",
				"PERIOD" };

		/**
		 * @param listModel
		 * @param aSelskap
		 * @param icaText
		 * @param icaKontoStreng
		 */
		public ElfaInvoiceTableModel(ListModel listModel, BokfSelskap aSelskap,
				String icaText, String icaKontoStreng) {
			super(listModel, COLUMNS);
			selskap = aSelskap;
			icaKontoTekst = icaText;
			icaKonto = icaKontoStreng;
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			InvoiceItemV invoiceItemV = (InvoiceItemV) getRow(row);
			switch (column) {
			case 0:
				return invoiceItemV.getInvoiceId();
			case 1:
				return invoiceItemV.getStoreName();
			case 2:
				return invoiceItemV.getJuridiskEierNavn();
			case 3:
				return invoiceItemV.getAdr1();
			case 4:
				return invoiceItemV.getAdr2();
			case 5:
				return String.format("%1$04d", invoiceItemV.getPostnr());
			case 6:
				return invoiceItemV.getPoststed();
			case 7:
				return invoiceItemV.getInvoiceDate();
			case 8:
				return invoiceItemV.getDueDate();
			case 9:
				return String.format("%1$.0f", invoiceItemV.getInvoiceNr());
			case 10:
				return invoiceItemV.getAvdnr();
			case 11:
				return selskap.getOrgNr();
			case 12:
				return invoiceItemV.getUserName();
			case 13:
				if (invoiceItemV.getKid() != null) {
					return icaKonto;
				}
				return selskap.getKontonr();
			case 14:
				return selskap.getTelefon();
			case 15:
				return selskap.getTelefax();
			case 16:
				if (invoiceItemV.getKid() != null) {
					return String.valueOf(invoiceItemV.getKid());
				}
				return invoiceItemV.getKidNr();
			case 17:
				return invoiceItemV.getAmountTotal();
			case 18:
				return invoiceItemV.getVatAmount().multiply(
						BigDecimal.valueOf(-1));
			case 19:
				return invoiceItemV.getAmountInvoice();
			case 20:
				return selskap.getAdresse1();
			case 21:
				return selskap.getAdresse2();
			case 22:
				return selskap.getAdresse3();
			case 23:
				return selskap.getNavn();
			case 24:
				return invoiceItemV.getArticleNo();
			case 25:
				return invoiceItemV.getInvoiceItemDescription();
			case 26:
				return invoiceItemV.getNumberOfArticles();
			case 27:
				return invoiceItemV.getArticlePrice();
			case 28:
				return invoiceItemV.getItemAmountTotal();
			case 29:
				return invoiceItemV.getLineCommissionAmountSto().multiply(
						BigDecimal.valueOf(-1));
			case 30:
				return invoiceItemV.getItemVatAmount().multiply(
						BigDecimal.valueOf(-1));
			case 31:
				return invoiceItemV.getInvoiceAmountItem();
			case 32:
				return invoiceItemV.getCommissionAmountSto().multiply(
						BigDecimal.valueOf(-1));
			case 33:
				if (invoiceItemV.getKid() != null) {
					return icaKontoTekst;
				}
				return "";
			case 34:
				if (invoiceItemV.getAmountInvoice().doubleValue() >= 0) {
					return "Faktura - Elektroniske ladekort";
				}
				return "Kreditnota - Elektroniske ladekort";
			case 35:
				return invoiceItemV.getPeriod();
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

		/**
		 * @see javax.swing.table.TableModel#getColumnName(int)
		 */
		@Override
		public String getColumnName(int column) {
			return COLUMNS[column];
		}

	}

	/**
	 * @see no.ica.fraf.common.InvoicePrinterInterface#setInvoice(no.ica.fraf.xml.InvoiceInterface)
	 */
	public void setInvoice(InvoiceInterface invoice) {
		currentInvoice = (Invoice) invoice;
		singleInvoice = true;

	}

}
