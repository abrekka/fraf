package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.InvoiceViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class InvoiceView {
	private InvoiceViewHandler viewHandler;

	private JXTable tableInvoices;
	private JButton buttonShowInvoice;
	private JButton buttonExcel;

	public InvoiceView(InvoiceViewHandler handler) {
		viewHandler = handler;
	}

	private void initComponents(WindowInterface window) {
		tableInvoices = viewHandler.getTableInvoices();
		buttonShowInvoice = viewHandler.getButtonShowInvoice(window);
		buttonExcel = viewHandler.getButtonExcel(window);
	}

	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("p", "230dlu,3dlu,fill:p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableInvoices), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonExcel,buttonShowInvoice),cc.xy(1,3));

		
		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler.getInvoiceComponentListener(window));
		
		return panel;
	}
}
