package no.ica.fraf.gui.invoicerun;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import no.ica.fraf.common.WindowInterface;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av buntdetaljer
 * 
 * @author abr99
 * 
 */
public class InvoiceBatchDetailsView {
	/**
	 * 
	 */
	private InvoiceBatchDetailsViewHandler viewHandler;

	/**
	 * 
	 */
	private JButton buttonPrint;

	/**
	 * 
	 */
	private JButton buttonShow;

	/**
	 * 
	 */
	private JButton buttonSearch;

	/**
	 * 
	 */
	private JTextField textFieldDepartment;

	/**
	 * 
	 */
	private JTable tableInvoiceDetails;

	/**
	 * @param handler
	 */
	public InvoiceBatchDetailsView(InvoiceBatchDetailsViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		viewHandler.initPopupMenu(window);
		buttonPrint = viewHandler.getButtonPrint(window);
		buttonShow = viewHandler.getButtonShow(window);
		buttonSearch = viewHandler.getButtonSearch(window);
		textFieldDepartment = viewHandler.getTextFieldDepartment();
		tableInvoiceDetails = viewHandler.getTableInvoiceDetails();
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p,3dlu,50dlu,3dlu,p,410dlu",
				"10dlu,p,3dlu,130dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		// PanelBuilder builder = new PanelBuilder(new FormDebugPanel(),layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Avdeling:", cc.xy(2, 2));
		builder.add(textFieldDepartment, cc.xy(4, 2));
		builder.add(buttonSearch, cc.xy(6, 2));
		builder.add(new JScrollPane(tableInvoiceDetails), cc.xyw(2, 4, 6));
		builder.add(ButtonBarFactory.buildLeftAlignedBar(buttonShow,
				buttonPrint), cc.xyw(2, 6, 5));

		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler.getPanelListener(window));

		return panel;
	}
}
