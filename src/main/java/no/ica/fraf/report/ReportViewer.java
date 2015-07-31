package no.ica.fraf.report;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.ReportTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Dette vinduet brukes til forhåndsvisning for rapporter
 * 
 * @author abr99
 * 
 */
public class ReportViewer extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String currentHeading;

	/**
	 * 
	 */
	//private static String REPORT_FILE_INVOICE = "invoice.jasper";
	private static String REPORT_FILE_INVOICE = "invoice_bean.jasper";

	//private static String REPORT_FILE_DEDUCT = "invoice_avregning.jasper";
	private static String REPORT_FILE_DEDUCT = "invoice_avregning_bean.jasper";

	/**
	 * 
	 */
	//private static String REPORT_FILE_INVOICE_FRANCHISE = "invoice_franchise.jasper";
	private static String REPORT_FILE_INVOICE_FRANCHISE = "invoice_franchise_bean.jasper";

	private static String REPORT_FILE_ELFA_INVOICE = "elfa_invoice.jasper";

	private static String REPORT_FILE_TG_INVOICE = "tg_invoice.jasper";

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(ReportViewer.class);

	/**
	 * 
	 */
	private String reportPath;

	/**
	 * 
	 */
	private JPanel pnlMain;

	/**
	 * 
	 */
	public ReportViewer() {

	}

	/**
	 * @param heading
	 */
	public ReportViewer(String heading) {
		currentHeading = heading;
		initComponents();
		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 */
	private void initComponents() {
		pnlMain = new javax.swing.JPanel();

		setTitle(currentHeading);

		pnlMain.setLayout(new java.awt.BorderLayout());

		getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);
		{
			JPanel panelButtons = new JPanel();
			GridBagLayout panelButtonsLayout = new GridBagLayout();
			panelButtons.setLayout(panelButtonsLayout);
			this.getContentPane().add(panelButtons, BorderLayout.NORTH);
			panelButtons.setPreferredSize(new java.awt.Dimension(10, 50));
			{
				JButton buttonCancel = new JButton();
				buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
				buttonCancel.setMnemonic(KeyEvent.VK_A);
				panelButtons
						.add(buttonCancel, new GridBagConstraints(2, 0, 1, 1,
								0.0, 0.0, GridBagConstraints.CENTER,
								GridBagConstraints.NONE,
								new Insets(0, 10, 0, 0), 0, 0));
				buttonCancel.setText("Avslutt");
				buttonCancel.setPreferredSize(new java.awt.Dimension(90, 25));
				buttonCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buttonCancelActionPerformed(evt);
					}
				});

			}
		}

		pack();
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setSize(new java.awt.Dimension(750, 550));
		setLocation((screenSize.width - 750) / 2, (screenSize.height - 550) / 2);
		this.setPreferredSize(new java.awt.Dimension(695, 632));
		this.setBounds(25, 25, 695, 632);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
	}

	/**
	 * Stenger vindu
	 */
	private void buttonCancelMouseClicked() {
		dispose();
	}

	/**
	 * Genererer rapport
	 * 
	 * @param reportFile
	 * @param tableModel
	 * @param heading
	 * @param parameters
	 * @param printable
	 * @throws FrafException
	 */
	private void generateReport(String reportFile, TableModel tableModel,
			String heading, Map parameters, boolean printable)
			throws FrafException {
		try {
			InputStream stream = getClass().getClassLoader()
					.getResourceAsStream(reportFile);

			if (stream == null) {
				throw new FrafException("Fant ikke rapport");
			}
			/*
			 * JasperPrint pr = JasperFillManager.fillReport(reportFile,
			 * parameters, new JRTableModelDataSource(tableModel));
			 */
			JasperPrint pr = JasperFillManager.fillReport(stream, parameters,
					new JRTableModelDataSource(tableModel));

			JRViewerFraf viewer = new JRViewerFraf(pr);
			viewer.setPrintable(printable);
			viewer.setSavable(printable);
			this.pnlMain.add(viewer, BorderLayout.CENTER);

			displayReport(700, 600);
		} catch (JRException e) {
			e.printStackTrace();
			String msg;
			if (e.getCause() instanceof FileNotFoundException) {
				msg = "Kunne ikke finne rapport " + reportFile;
			} else {
				msg = e.getMessage();
			}
			/*
			 * GuiUtil.showErrorMsgFrame(FrafMain.getInstance().getContentPane(),
			 * "Feil ved generering av rapport", msg);
			 */
			logger.error("Feil ved generering av rapport " + reportFile, e);
			throw new FrafException("Feil ved generering av rapport " + msg);
		}
	}
	private void generateReportBean(String reportFile, Collection<?> beans,
			String heading, Map parameters, boolean printable)
			throws FrafException {
		try {
			InputStream stream = getClass().getClassLoader()
					.getResourceAsStream(reportFile);

			if (stream == null) {
				throw new FrafException("Fant ikke rapport");
			}
			/*
			 * JasperPrint pr = JasperFillManager.fillReport(reportFile,
			 * parameters, new JRTableModelDataSource(tableModel));
			 */
			JasperPrint pr = JasperFillManager.fillReport(stream, parameters,
					new JRBeanCollectionDataSource(beans));

			JRViewerFraf viewer = new JRViewerFraf(pr);
			viewer.setPrintable(printable);
			viewer.setSavable(printable);
			this.pnlMain.add(viewer, BorderLayout.CENTER);

			displayReport(700, 600);
		} catch (JRException e) {
			e.printStackTrace();
			String msg;
			if (e.getCause() instanceof FileNotFoundException) {
				msg = "Kunne ikke finne rapport " + reportFile;
			} else {
				msg = e.getMessage();
			}
			/*
			 * GuiUtil.showErrorMsgFrame(FrafMain.getInstance().getContentPane(),
			 * "Feil ved generering av rapport", msg);
			 */
			logger.error("Feil ved generering av rapport " + reportFile, e);
			throw new FrafException("Feil ved generering av rapport " + msg);
		}
	}

	/**
	 * Henter path hvor rapport skal generers
	 * 
	 * @return path hvor rapport skal generers
	 */
	private String getReportPath() {
		try {
			if (reportPath != null) {
				return reportPath;
			}

			StringBuffer dir = new StringBuffer(ApplParamUtil.getStringParam("report_path"));
			if ((dir.lastIndexOf("\\") != dir.length() - 1)
					&& (dir.lastIndexOf("/") != dir.length() - 1)) {
				dir.append("/");
			}

			if (FrafMain.isTest) {
				dir.append("test/");
			}
			reportPath = dir.toString();
			return reportPath;
		} catch (FrafException e) {
			GuiUtil.showErrorMsgFrame(FrafMain.getInstance().getContentPane(), "Feil", e.getMessage());
		}
		return null;
	}

	/**
	 * Kompilerer rapport, må kjøres dersom det er gjort endringer på layout
	 * 
	 * @param isTest
	 */
	@SuppressWarnings("unused")
	public void compileReports(boolean isTest) {
		if (isTest) {
			/*
			 * URL url =
			 * getClass().getClassLoader().getResource("reports/test/");
			 */

			/*
			 * try {
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/test/tg_invoice.jrxml",
			 * "src/main/resources/reports/test/tg_invoice.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try {
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/test/invoice_avregning.jrxml",
			 * "src/main/resources/reports/test/invoice_avregning.jasper");
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/test/invoice_details_avregning.jrxml",
			 * "src/main/resources/reports/test/invoice_details_avregning.jasper"); }
			 * catch (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try {
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/test/elfa_invoice.jrxml",
			 * "src/main/resources/reports/test/elfa_invoice.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */
			/*
			 * try {
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/test/invoice.jrxml",
			 * "src/main/resources/reports/test/invoice.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*try {
				JasperCompileManager
						.compileReportToFile(
								"reports/test/invoice_franchise.jrxml",
								"src/main/resources/reports/test/invoice_franchise.jasper");
			} catch (JRException e1) {
				e1.printStackTrace();
			}*/

			/*
			 * try { JasperCompileManager.compileReportToFile(
			 * "reports/test/invoice_text.jrxml",
			 * "src/main/resources/reports/test/invoice_text.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager .compileReportToFile(
			 * "reports/test/invoice_cond_text.jrxml",
			 * "src/main/resources/reports/test/invoice_cond_text.jasper"); }
			 * catch (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager .compileReportToFile(
			 * "reports/test/invoice_details.jrxml",
			 * "src/main/resources/reports/test/invoice_details.jasper"); }
			 * catch (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager .compileReportToFile(
			 * "reports/test/invoice_details_franchise.jrxml",
			 * "src/main/resources/reports/test/invoice_details_franchise.jasper"); }
			 * catch (JRException e1) { e1.printStackTrace(); }
			 */
			/*try {
				JasperCompileManager
						.compileReportToFile(
								"reports/test/invoice_franchise_bean.jrxml",
								"src/main/resources/reports/test/invoice_franchise_bean.jasper");
			} catch (JRException e1) {
				e1.printStackTrace();
			}*/
		} else {
			/*try {

				JasperCompileManager.compileReportToFile(
						"reports/tg_invoice.jrxml",
						"src/main/resources/reports/tg_invoice.jasper");
			} catch (JRException e1) {
				e1.printStackTrace();
			}*/
			/*
			 * try {
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/elfa_invoice.jrxml",
			 * "src/main/resources/reports/elfa_invoice.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try {
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/invoice_avregning.jrxml",
			 * "src/main/resources/reports/invoice_avregning.jasper");
			 * 
			 * JasperCompileManager.compileReportToFile(
			 * "reports/invoice_details_avregning.jrxml",
			 * "src/main/resources/reports/invoice_details_avregning.jasper"); }
			 * catch (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager.compileReportToFile(
			 * "reports/invoice.jrxml",
			 * "src/main/resources/reports/invoice.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager.compileReportToFile(
			 * "reports/invoice_franchise.jrxml",
			 * "src/main/resources/reports/invoice_franchise.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager.compileReportToFile(
			 * "reports/invoice_text.jrxml",
			 * "src/main/resources/reports/invoice_text.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager.compileReportToFile(
			 * "reports/invoice_cond_text.jrxml",
			 * "src/main/resources/reports/invoice_cond_text.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager.compileReportToFile(
			 * "reports/invoice_details.jrxml",
			 * "src/main/resources/reports/invoice_details.jasper"); } catch
			 * (JRException e1) { e1.printStackTrace(); }
			 */

			/*
			 * try { JasperCompileManager .compileReportToFile(
			 * "reports/invoice_details_franchise.jrxml",
			 * "src/main/resources/reports/invoice_details_franchise.jasper"); }
			 * catch (JRException e1) { e1.printStackTrace(); }
			 */
			
			 /* try { JasperCompileManager.compileReportToFile(
			  "reports/invoice_bean.jrxml",
			  "src/main/resources/reports/invoice_bean.jasper"); } catch
			  (JRException e1) { e1.printStackTrace(); }*/
			 
		}
	}

	/**
	 * Generer faktura
	 * 
	 * @param tableModel
	 * @param connection
	 * @param isFranchise
	 * @param printable
	 * @throws FrafException
	 */
	public void generateInvoiceReport(TableModel tableModel,
			Connection connection, ReportTypeEnum reportType,
			boolean printable, Map<String, Object> extraParams)
			throws FrafException {

		//compileReports(FrafMain.isTest);

		InputStream imageDraft = null;

		if (!printable) {
			imageDraft = getClass().getClassLoader().getResourceAsStream(
					"reports/img/utkast.gif");
		}


		String paramMaxValue = ApplParamUtil.getStringParam("limit_avr");
		String infoTekst = "Avregninger med beløp til gode / å betale mindre enn kr. "
				+ paramMaxValue + " faktureres / krediteres ikke";

		InputStream iconStream = getClass().getClassLoader()
				.getResourceAsStream("reports/img/ica.gif");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DB_CONNECTION", connection);
		parameters.put("ica_logo", iconStream);
		parameters.put("draft_image", imageDraft);
		parameters.put("info_tekst", infoTekst);

		if (extraParams != null) {
			parameters.putAll(extraParams);
		}

		String reportDir = getReportPath();

		if (reportDir != null) {
			StringBuffer reportFile = new StringBuffer(reportDir);

			switch (reportType) {
			case FRANCHISE:
				reportFile.append(REPORT_FILE_INVOICE_FRANCHISE);
				break;
			case DIV:
				reportFile.append(REPORT_FILE_INVOICE);
				break;
			case DEDUCT:
				reportFile.append(REPORT_FILE_DEDUCT);
				break;
			}
			generateReport(reportFile.toString(), tableModel, "Faktura",
					parameters, printable);
		}

	}
	
	public void generateInvoiceReportFromBean(Collection<?> beans,
			Connection connection, ReportTypeEnum reportType,
			boolean printable, Map<String, Object> extraParams)
			throws FrafException {

		compileReports(FrafMain.isTest);

		InputStream imageDraft = null;

			imageDraft = getClass().getClassLoader().getResourceAsStream(
					"reports/img/utkast.gif");


		String paramMaxValue = ApplParamUtil.getStringParam("limit_avr");
		String infoTekst = "Avregninger med beløp til gode / å betale mindre enn kr. "
				+ paramMaxValue + " faktureres / krediteres ikke";

		InputStream iconStream = getClass().getClassLoader()
				.getResourceAsStream("reports/img/ica.gif");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DB_CONNECTION", connection);
		parameters.put("ica_logo", iconStream);
		parameters.put("draft_image", imageDraft);
		parameters.put("info_tekst", infoTekst);

		if (extraParams != null) {
			parameters.putAll(extraParams);
		}

		String reportDir = getReportPath();

		if (reportDir != null) {
			StringBuffer reportFile = new StringBuffer(reportDir);

			switch (reportType) {
			case FRANCHISE:
				reportFile.append(REPORT_FILE_INVOICE_FRANCHISE);
				break;
			case DIV:
				reportFile.append(REPORT_FILE_INVOICE);
				break;
			case DEDUCT:
				reportFile.append(REPORT_FILE_DEDUCT);
				break;
			}
	
			generateReportBean(reportFile.toString(), beans, "Faktura",
					parameters, printable);
		}

	}

	public void generateElfaInvoiceReport(TableModel tableModel)
			throws FrafException {

		// compileReports(FrafMain.isTest);

		InputStream imageDraft = null;

		InputStream iconStream = getClass().getClassLoader()
				.getResourceAsStream("reports/img/ica.gif");

		String overtrekksrente = ApplParamUtil.getStringParam("overtrekksrente");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ica_logo", iconStream);

		parameters.put("overtrekksrente", overtrekksrente);

		String reportDir = getReportPath();

		if (reportDir != null) {
			StringBuffer reportFile = new StringBuffer(reportDir);

			reportFile.append(REPORT_FILE_ELFA_INVOICE);

			generateReport(reportFile.toString(), tableModel, "Faktura",
					parameters, true);
		}

	}

	public void generateTollpostInvoiceReport(TableModel tableModel)
			throws FrafException {

		// compileReports(FrafMain.isTest);

		InputStream imageDraft = null;

		InputStream iconStream = getClass().getClassLoader()
				.getResourceAsStream("reports/img/ica.gif");

		String overtrekksrente = ApplParamUtil.getStringParam("overtrekksrente");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ica_logo", iconStream);

		parameters.put("overtrekksrente", overtrekksrente);

		String reportDir = getReportPath();

		if (reportDir != null) {
			StringBuffer reportFile = new StringBuffer(reportDir);

			reportFile.append(REPORT_FILE_TG_INVOICE);

			generateReport(reportFile.toString(), tableModel, "Faktura",
					parameters, true);
		}

	}

	/**
	 * Viser rapport
	 * 
	 * @param x
	 * @param y
	 */
	private void displayReport(int x, int y) {
		setVisible(true);
		setSize(x, y);

		try {
			setSelected(true);
		} catch (java.beans.PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Lukker vindu
	 * 
	 * @param evt
	 */
	protected void buttonCancelActionPerformed(ActionEvent evt) {
		buttonCancelMouseClicked();
	}

	/**
	 * Kompilerer rapporter
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ReportViewer viewer = new ReportViewer();
		viewer.compileReports(false);
	}
}
