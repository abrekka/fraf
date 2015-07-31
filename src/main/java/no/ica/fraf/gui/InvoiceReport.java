package no.ica.fraf.gui;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.InvoicePrinterInterface;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.enums.ReportTypeEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.EflowUsersV;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.report.ReportViewer;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.PaperInvoice;

import org.apache.log4j.Logger;

/**
 * Denne klassen viser fakturaer
 * 
 * @author abr99
 * 
 */
public final class InvoiceReport implements Threadable, InvoicePrinterInterface {
	/**
	 * Frame som skal vise rapport
	 */
	private WindowInterface internalFrame;

	/**
	 * DAO som brukes til å hente ut databaseforbindelse
	 */
	private ImportBetingelsePkgDAO importBetingelsePkgDAO;

	/**
	 * DAO for faktura view
	 */
	private FakturaVDAO fakturaVDAO;

	/**
	 * 
	 */
	private FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(InvoiceReport.class);

	/**
	 * 
	 */
	private Faktura currentInvoice;

	/**
	 * 
	 */
	//private static Map<String, EflowUsersV> eflowUsers;

	/**
	 * 
	 */
	//private EflowUsersVManager eflowUsersVManager;

	/**
	 * 
	 */
	private String orderInvoiceByColumn;

	/**
	 * @param aInternalFrame
	 * @param aEflowUsersVManager
	 * @param invoiceOrderColumn
	 */
	public InvoiceReport(WindowInterface aInternalFrame,
			//EflowUsersVManager aEflowUsersVManager, 
			String invoiceOrderColumn) {
		orderInvoiceByColumn = invoiceOrderColumn;
		//eflowUsersVManager = aEflowUsersVManager;
		internalFrame = aInternalFrame;

		importBetingelsePkgDAO = (ImportBetingelsePkgDAO) ModelUtil
				.getBean("importBetingelsePkgDAO");
		fakturaVDAO = (FakturaVDAO) ModelUtil.getBean("fakturaVDAO");
		fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
	}

	/**
	 * Viser faktura
	 * 
	 * @param faktura
	 * @param bunter
	 */
	public void showInvoiceReport(Faktura faktura, List<Bunt> bunter) {
		GuiUtil.runInThreadWheel(internalFrame.getRootPane(), this,
				new Object[] { faktura, bunter });
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
	}

	/**
	 * Sjekker om avdeling ligger i basware
	 * 
	 * @param avdnr
	 * @return true dersom i basware
	 */
	/*private Boolean storeInBasware(String avdnr) {
		if (eflowUsers.containsKey(avdnr)) {
			return true;
		}
		return false;
	}*/

	/**
	 * Henter ut fakturaer som skal skrives ut på papir
	 * 
	 * @param allInvoices
	 * @return fakturaer
	 * @throws FrafException
	 */
	// private List<FakturaV> getPrintableInvoices(List<FakturaV> allInvoices)
	private Collection<Faktura> getPrintableInvoices(
			Collection<Faktura> allInvoices) throws FrafException {
		// List<FakturaV> printableInvoices = new ArrayList<FakturaV>();
		List<Faktura> printableInvoices = new ArrayList<Faktura>();
		//PaperInvoice paperInvoice = new PaperInvoice();
		//paperInvoice.init();
		PaperManager paperManager=(PaperManager)ModelUtil.getBean(PaperManager.MANAGER_NAME);
		if (allInvoices != null) {
			// for (FakturaV invoice : allInvoices) {
			for (Faktura invoice : allInvoices) {
				if(paperManager.shouldHavePaperInvoice(invoice.getAvdnr())){
				/*if (paperInvoice.paperInvoice(invoice.getAvdnr())
						|| !storeInBasware(String.valueOf(invoice.getAvdnr()))) {*/
					printableInvoices.add(invoice);
				}
			}
		}
		return printableInvoices;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	@SuppressWarnings("unchecked")
	public Object doWork(Object[] params, JLabel labelInfo) {
		labelInfo.setText("Genererer faktura...");
		Faktura faktura;
		List<Bunt> bunter = null;

		if (params != null) {
			faktura = (Faktura) params[0];

			bunter = (List<Bunt>) params[1];

		} else {
			faktura = currentInvoice;
		}
		String reportTitle = null;
		// List<FakturaV> list = null;
		Collection<Faktura> list = null;

		ReportTypeEnum reportType = ReportTypeEnum.FRANCHISE;
		BetingelseGruppe betingelseGruppe;
		boolean printable = true;
		String errorString = null;

		try {
			if (faktura != null) {
				fakturaVDAO.loadBunt(faktura);
				if (faktura.getBunt().getBuntStatus().getKode()
						.equalsIgnoreCase("BF")) {
					printable = true;
				}
				reportTitle = "Faktura - " + faktura.getAvdnr() + " - "
						+ faktura.getFakturaTittel();
				// list = fakturaVDAO.findByFakturaId(faktura.getFakturaId());
				list = new ArrayList<Faktura>();
				list.add(faktura);
				betingelseGruppe = faktura.getBetingelseGruppe();
				if(faktura.getBunt().getBuntType().getKode().equalsIgnoreCase(BuntTypeEnum.BATCH_TYPE_INN_AVR.getKode())){
					reportType = ReportTypeEnum.DEDUCT;
				}else if (betingelseGruppe.getFakturerMedFranchise().intValue() == 0) {

					reportType = ReportTypeEnum.DIV;
				} 
			} else if (bunter != null) {
				/*if (eflowUsers == null) {
					eflowUsers = eflowUsersVManager.getEflowUsers();
				}*/
				reportTitle = "Fakturaer";
				List<Integer> buntIds = getBuntIds(bunter);
				// list = fakturaVDAO.findByBuntIds(buntIds);
				list = fakturaDAO.findByBuntIds(buntIds, orderInvoiceByColumn);

				// list = getPrintableInvoices(list);
				list = getPrintableInvoices(list);

				if (bunter.get(0).getBetingelseGruppe() != null
						&& bunter.get(0).getBetingelseGruppe()
								.getFakturerMedFranchise().intValue() == 0) {

					reportType = ReportTypeEnum.DIV;
				} else if (bunter.get(0).getBuntType().getKode()
						.equalsIgnoreCase("INN_AVR")) {
					reportType = ReportTypeEnum.DEDUCT;
				}

			}
			if (faktura != null || bunter != null) {

				Connection con = null;

				con = importBetingelsePkgDAO.getDbConnection();

				Map<String, Object> parameters = null;
				fakturaDAO.loadLazy(faktura, new LazyLoadFakturaEnum[] {
						LazyLoadFakturaEnum.LOAD_INVOICE_LINES,
						LazyLoadFakturaEnum.LOAD_INVOICE_TEXT });

				// InvoiceReportModel invoiceReportModel = new
				// InvoiceReportModel(list);
				// InvoiceReportModel invoiceReportModel = new
				// InvoiceReportModel(list);
				ReportViewer reportViewer = new ReportViewer(reportTitle);
				reportViewer.setLocation(GuiUtil.getCenter(FrafMain
						.getInstance().getSize(), reportViewer.getSize()));
				FrafMain.getInstance().addInternalFrame(reportViewer);
				// reportViewer.generateInvoiceReport(invoiceReportModel,
				// con,reportType, printable, parameters);
				reportViewer.generateInvoiceReportFromBean(list, con,
						reportType, printable, parameters);

			}
		} catch (Exception e) {
			logger.error("Feil ved generering av rapport", e);
			errorString = GuiUtil.getUserExceptionMsg(e);
		}
		return errorString;
	}

	/**
	 * Henter ut buntider for bunter
	 * 
	 * @param bunter
	 * @return id'er
	 */
	private List<Integer> getBuntIds(List<Bunt> bunter) {
		List<Integer> integerList = new ArrayList<Integer>();
		for (Bunt bunt : bunter) {
			integerList.add(bunt.getBuntId());
		}
		return integerList;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgFrame(internalFrame.getComponent(), "Feil",
					(String) object);
		}
	}

	/**
	 * @see no.ica.fraf.common.InvoicePrinterInterface#setInvoice(no.ica.fraf.xml.InvoiceInterface)
	 */
	public void setInvoice(InvoiceInterface invoice) {
		currentInvoice = (Faktura) invoice;
	}

}
