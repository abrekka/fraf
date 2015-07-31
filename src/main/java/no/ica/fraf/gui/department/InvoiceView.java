package no.ica.fraf.gui.department;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.InvoiceViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Viser fakturaer for en avdeling
 * 
 * @author abr99
 * 
 */
public class InvoiceView {
	/**
	 * 
	 */
	private InvoiceViewHandler viewHandler;

	/**
	 * 
	 */
	private JButton buttonCredit;

	/**
	 * 
	 */
	private JButton buttonRegenerate;

	/**
	 * 
	 */
	private JButton buttonShowInvoice;

	/**
	 * 
	 */
	private JXTable tableInvoices;

	/**
	 * @param handler
	 */
	public InvoiceView(InvoiceViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		buttonCredit = viewHandler.getButtonCredit(window);
		buttonRegenerate = viewHandler.getButtonRegenerate(window);
		buttonShowInvoice = viewHandler.getButtonShowInvoice(window);
		tableInvoices = viewHandler.getTableInvoices(window);
	}

	/**
	 * Bygger vinduspanel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("FILL:540dlu:grow", "FILL:130dlu:grow,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		//PanelBuilder builder = new PanelBuilder(new FormDebugPanel(),layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableInvoices), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildLeftAlignedBar(buttonShowInvoice,
				buttonCredit, buttonRegenerate), cc.xy(1, 3));

		return builder.getPanel();
	}
}
