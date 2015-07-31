package no.ica.elfa.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import no.ica.elfa.gui.handlers.BatchViewHandler;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av bunter
 * 
 * @author abr99
 * 
 */
public class BatchView {
	/**
	 * 
	 */
	private BatchViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableBatch;

	/**
	 * 
	 */
	private JButton buttonXml;

	/**
	 * 
	 */
	private JButton buttonPrint;

	/**
	 * 
	 */
	private JButton buttonBook;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JButton buttonRefresh;

	/**
	 * 
	 */
	private JXTable tableInvoices;

	/**
	 * 
	 */
	private JButton buttonMakeInvoices;

	/**
	 * 
	 */
	private JButton buttonShowInvoice;
	

	/**
	 * @param handler
	 */
	public BatchView(BatchViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableBatch = viewHandler.getTableBatch();
		buttonBook = viewHandler.getButtonBook(window);
		buttonPrint = viewHandler.getButtonPrint(window);
		buttonXml = viewHandler.getButtonXml(window);
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonRefresh = viewHandler.getButtonRefresh();
		tableInvoices = viewHandler.getTableInvoices();
		buttonMakeInvoices = viewHandler.getButtonMakeInvoices(window);
		buttonShowInvoice = viewHandler.getButtonShowInvoice(window);
		
		viewHandler.initEventHandling();
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p", "10dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Bunter", buildBatchPane());
		tabbedPane.add("Fakturaer", buildInvoicePane(window));
		builder.add(tabbedPane, cc.xy(2, 2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc
				.xy(2, 4));
		return builder.getPanel();
	}

	/**
	 * Bygger panel for bunt
	 * 
	 * @return panel
	 */
	private JPanel buildBatchPane() {
		FormLayout layout = new FormLayout("10dlu,p", "10dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.add(new JScrollPane(tableBatch), cc.xy(2, 2));
		builder
				.add(ButtonBarFactory.buildCenteredBar(buttonMakeInvoices,
						buttonXml, buttonPrint, buttonBook, buttonRefresh), cc
						.xy(2, 4));
		return builder.getPanel();
	}

	/**
	 * Bygger panel for faktura
	 * 
	 * @param window
	 * @return panel
	 */
	private JPanel buildInvoicePane(WindowInterface window) {
		FormLayout layout = new FormLayout("10dlu,400dlu",
				"10dlu,200dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.add(new JScrollPane(tableInvoices), cc.xy(2, 2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonShowInvoice), cc
				.xy(2, 4));
		// builder.add(ButtonBarFactory.buildCenteredBar(buttonXml,buttonPrint,buttonBook,buttonRefresh),cc.xy(2,4));
		JPanel invoicePanel = builder.getPanel();
		invoicePanel.addComponentListener(viewHandler
				.getInvoiceComponentListener(window));
		return invoicePanel;
	}
}
