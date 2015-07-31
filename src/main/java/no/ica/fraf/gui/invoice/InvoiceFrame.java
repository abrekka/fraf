package no.ica.fraf.gui.invoice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.InvoiceReport;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.EflowUsersVManager;

/**
 * Dette vinduet viser fakturadetaljer
 * 
 * @author abr99
 * 
 */
public class InvoiceFrame extends javax.swing.JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JButton buttonPrint;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JPanel panelInvoiceButtons;

	/**
	 * 
	 */
	private JTabbedPane tabbedPaneInvoice;

	/**
	 * 
	 */
	private Faktura currentFaktura;

	/**
	 * 
	 */
	private InvoiceReport invoiceReport;

	/**
	 * 
	 */
	private ApplUser currentApplUser;

	/**
	 * Konstruktør
	 * 
	 * @param faktura
	 * @param applUser
	 */
	public InvoiceFrame(Faktura faktura, ApplUser applUser
			//,EflowUsersVManager eflowUsersVManager
			) {
		super();
		currentApplUser = applUser;
		currentFaktura = faktura;
		invoiceReport = new InvoiceReport(new JInternalFrameAdapter(this),null);

		initGUI();

		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(594, 596));
			this.setBounds(0, 0, 594, 596);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			if (currentFaktura != null) {
				this.setTitle("Faktura - " + currentFaktura.getAvdnr() + " - "
						+ currentFaktura.getFakturaTittel());
			} else {
				this.setTitle("Faktura");
			}
			this.setClosable(true);
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			{
				PanelInvoiceDetails panelInvoiceDetails = new PanelInvoiceDetails(
						currentFaktura);
				this.getContentPane().add(panelInvoiceDetails,
						BorderLayout.NORTH);
				panelInvoiceDetails.setPreferredSize(new java.awt.Dimension(0,
						330));
			}
			{
				tabbedPaneInvoice = new JTabbedPane();
				this.getContentPane().add(tabbedPaneInvoice,
						BorderLayout.CENTER);
				tabbedPaneInvoice.setPreferredSize(new java.awt.Dimension(694,
						200));
				{
					PanelInvoiceLines panelInvoiceLines = new PanelInvoiceLines(
							currentFaktura, currentApplUser, this);
					tabbedPaneInvoice.addTab("Fakturalinjer",
							IconEnum.ICON_INVOICE_LINES.getIcon(),
							panelInvoiceLines, null);
				}
				{
					PanelInvoiceBook panelInvoiceBook = new PanelInvoiceBook(
							currentFaktura, currentApplUser, this);
					tabbedPaneInvoice.addTab("Bokføring", IconEnum.ICON_BOOK
							.getIcon(), panelInvoiceBook, null);
				}
			}
			{
				panelInvoiceButtons = new JPanel();
				this.getContentPane().add(panelInvoiceButtons,
						BorderLayout.SOUTH);
				panelInvoiceButtons.setPreferredSize(new java.awt.Dimension(10,
						40));
				{
					buttonPrint = new JButton();
					buttonPrint.setMnemonic(KeyEvent.VK_P);
					buttonPrint.setIcon(IconEnum.ICON_PRINT.getIcon());
					panelInvoiceButtons.add(buttonPrint);
					buttonPrint.setText("Skriv ut");
					buttonPrint
							.setPreferredSize(new java.awt.Dimension(100, 25));
					buttonPrint.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonPrintActionPerformed(evt);
						}
					});
				}
				{
					buttonCancel = new JButton();
					buttonCancel.setMnemonic(KeyEvent.VK_A);
					buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
					panelInvoiceButtons.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel.setPreferredSize(new java.awt.Dimension(100,
							25));
					buttonCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonCancelActionPerformed(evt);
						}
					});
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Avslutter vindu
	 * 
	 * @param evt
	 */
	void buttonCancelActionPerformed(ActionEvent evt) {
		dispose();
	}

	/**
	 * Vis faktura
	 * 
	 * @param evt
	 */
	void buttonPrintActionPerformed(ActionEvent evt) {
		invoiceReport.showInvoiceReport(currentFaktura, null);
	}

}
