package no.ica.fraf.gui.department;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.InvoiceLineViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Viser alle fakturalinjer for en faktura
 * 
 * @author abr99
 * 
 */
public class InvoiceLineView {
	/**
	 * 
	 */
	private InvoiceLineViewHandler viewHandler;

	/**
	 * 
	 */
	private JButton buttonOk;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JCheckBox checkBoxSelectAll;

	/**
	 * 
	 */
	private JXTable tableInvoiceLines;

	/**
	 * @param handler
	 */
	public InvoiceLineView(InvoiceLineViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableInvoiceLines = viewHandler.getTableInvoiceLines();
		buttonOk = viewHandler.getButtonOk(window);
		buttonCancel = viewHandler.getButtonCancel(window);
		checkBoxSelectAll = viewHandler.getCheckBoxSelectAll();
	}

	/**
	 * Bygger vinduspanel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p",
				"10dlu,p,3dlu,100dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(checkBoxSelectAll, cc.xy(2, 2));
		builder.add(new JScrollPane(tableInvoiceLines), cc.xy(2, 4));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonOk, buttonCancel),
				cc.xy(2, 6));
		return builder.getPanel();
	}
}
