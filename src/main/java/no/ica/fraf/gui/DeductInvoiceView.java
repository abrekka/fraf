package no.ica.fraf.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DeductInvoiceViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av avregningsfakturaer
 * 
 * @author abr99
 * 
 */
public class DeductInvoiceView {
	/**
	 * 
	 */
	private DeductInvoiceViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableInvoice;

	/**
	 * 
	 */
	private JButton buttonShowInvoice;

	/**
	 * 
	 */
	private JButton buttonPrintInvoice;

	/**
	 * @param handler
	 */
	public DeductInvoiceView(DeductInvoiceViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableInvoice = viewHandler.getTableInvoice();
		buttonPrintInvoice = viewHandler.getButtonPrintInvoice(window);
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
		FormLayout layout = new FormLayout("420dlu", "150dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableInvoice), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonShowInvoice,
				buttonPrintInvoice), cc.xy(1, 3));

		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler
				.getInvoiceComponentListener());
		return panel;
	}
}
