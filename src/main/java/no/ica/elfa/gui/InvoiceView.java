package no.ica.elfa.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.elfa.gui.handlers.InvoiceViewHandler;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Håndterer visning av dialog for fakturaer
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
	private JXTable tableEepHead;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JButton buttonInvoice;

	/**
	 * 
	 */
	private JDateChooser dateChooserFrom;

	/**
	 * 
	 */
	private JDateChooser dateChooserTo;

	/**
	 * 
	 */
	private JDateChooser dateChooserInvoice;

	/**
	 * 
	 */
	private JButton buttonSetDepNr;

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
		tableEepHead = viewHandler.getTableEepHead();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonInvoice = viewHandler.getButtonInvoice(window);
		dateChooserFrom = viewHandler.getDateChooserFrom();
		dateChooserTo = viewHandler.getDateChooserTo();
		dateChooserInvoice = viewHandler.getDateChooserInvoice();
		buttonSetDepNr = viewHandler.getButtonSetDepNr(window);
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
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,50dlu,3dlu,p,10dlu",
				"10dlu,p,3dlu,p,3dlu,fill:p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		// PanelBuilder builder = new PanelBuilder(new FormDebugPanel(),layout);
		CellConstraints cc = new CellConstraints();
		builder.addLabel("Fra dato:", cc.xy(2, 2));
		builder.add(dateChooserFrom, cc.xy(4, 2));
		builder.addLabel("Til dato:", cc.xy(6, 2));
		builder.add(dateChooserTo, cc.xy(8, 2));
		builder.addLabel("Fakturadato:", cc.xy(10, 2));
		builder.add(dateChooserInvoice, cc.xy(12, 2));
		builder.add(buttonInvoice, cc.xy(14, 2));
		builder.add(new JScrollPane(tableEepHead), cc.xyw(2, 4, 14));
		builder.add(buttonSetDepNr, cc.xy(17, 4));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				6, 16));
		return builder.getPanel();
	}

}
