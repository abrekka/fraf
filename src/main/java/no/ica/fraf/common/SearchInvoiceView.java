package no.ica.fraf.common;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av dialog for søk etter faktura
 * 
 * @author abr99
 * 
 */
public class SearchInvoiceView {
	/**
	 * 
	 */
	private SearchInvoiceViewHandler viewHandler;

	/**
	 * 
	 */
	private JTextField textFieldInvoiceNr;

	/**
	 * 
	 */
	private JTextField textFieldAvdnr;

	/**
	 * 
	 */
	private JButton buttonSearch;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JXTable tableInvoices;

	/**
	 * 
	 */
	private JButton buttonShowInvoice;

	/**
	 * @param handler
	 */
	public SearchInvoiceView(SearchInvoiceViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		textFieldAvdnr = viewHandler.getTextFieldAvdnr();
		textFieldInvoiceNr = viewHandler.getTextFieldInvoiceNr();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonSearch = viewHandler.getButtonSearch(window);
		tableInvoices = viewHandler.getTableInvoices();
		buttonShowInvoice = viewHandler.getButtonShowInvoice(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,50dlu,3dlu,p,3dlu,50dlu,280dlu",
				"10dlu,p,3dlu,p,3dlu,150dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.addLabel("Fakturanr:", cc.xy(2, 2));
		builder.add(textFieldInvoiceNr, cc.xy(4, 2));
		builder.addLabel("Avdnr:", cc.xy(6, 2));
		builder.add(textFieldAvdnr, cc.xy(8, 2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonSearch,
				buttonShowInvoice), cc.xyw(2, 4, 7));
		builder.add(new JScrollPane(tableInvoices), cc.xyw(2, 6, 8));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				8, 8));
		return builder.getPanel();
	}
}
