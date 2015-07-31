package no.ica.fraf.gui.invoicerun;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXList;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.ViewInterface;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

/**
 * Håndterer visning av vindu for fakturering
 * 
 * @author abr99
 * 
 */
public class InvoiceRunView implements ViewInterface {

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private transient JButton buttonRunInvoice;
	private transient JButton buttonLoadBatches;
	private JButton buttonMoreGroups;

	/**
	 * 
	 */
	transient JDateChooser dateChooserDueDate;

	/**
	 * 
	 */
	transient JDateChooser dateChooserInvoiceDate;

	/**
	 * 
	 */
	private JButton buttonDepartments;

	/**
	 * 
	 */
	JComboBox comboBoxSettlement;

	/**
	 * 
	 */
	transient JTextField textFieldToDep;

	/**
	 * 
	 */
	transient JTextField textFieldFromDep;

	/**
	 * 
	 */
	transient JMonthChooser monthChooserFrom;

	/**
	 * 
	 */
	transient JMonthChooser monthChooserTo;

	/**
	 * 
	 */
	transient JYearChooser yearChooser;

	/**
	 * 
	 */
	transient JComboBox comboBoxBasis;

	/**
	 * 
	 */
	transient JComboBox comboBoxInvoiceGroup;

	/**
	 * 
	 */
	private InvoiceRunViewHandler viewHandler;

	/**
	 * 
	 */
	private JComboBox comboBoxDiv;
	private JComboBox comboBoxCompany;
	private JXList listGroups;

	/**
	 * @param handler
	 */
	public InvoiceRunView(InvoiceRunViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonRunInvoice = viewHandler.getButtonRunInvoice(window);
		dateChooserDueDate = viewHandler.getDateChooserDueDate();
		dateChooserInvoiceDate = viewHandler.getDateChooserInvoiceDate();
		buttonDepartments = viewHandler.getButtonDepartments(window);
		comboBoxSettlement = viewHandler.getComboBoxSettlement();
		textFieldToDep = viewHandler.getTextFieldDepTo();
		textFieldFromDep = viewHandler.getTextFieldDepFrom();
		monthChooserFrom = viewHandler.getMonthChooserFrom();
		monthChooserTo = viewHandler.getMonthChooserTo();
		yearChooser = viewHandler.getYearChooser();
		comboBoxBasis = viewHandler.getComboBoxBasis();
		comboBoxInvoiceGroup = viewHandler.getComboBoxInvoiceGroup();
		comboBoxDiv = viewHandler.getComboBoxDiv();
		comboBoxCompany=viewHandler.getComboBoxCompany();
		
		buttonLoadBatches=viewHandler.getButtonLoadBatches(window);
		buttonMoreGroups=viewHandler.getButtonMoreGroups(window);
		listGroups=viewHandler.getListGroups();
	}

	/**
	 * @see no.ica.fraf.gui.ViewInterface#buildPanel(no.ica.fraf.common.WindowInterface)
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,center:p,10dlu",
				"10dlu,p,3dlu,200dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		// PanelBuilder builder = new PanelBuilder(new FormDebugPanel(),layout);
		CellConstraints cc = new CellConstraints();

		viewHandler.buildBatchErrorPanel(window);
		viewHandler.buildInvoiceErrorPanel(window);

		JTabbedPane tabbedPane = viewHandler.getTabbedPane();
		tabbedPane.add("Fakturabunter", viewHandler
				.buildInvoiceBatchPanel(window));
		tabbedPane.add("Fakturaer", viewHandler
				.buildInvoiceBatchDetailsPanel(window));

		builder.add(buildConfigPanel(), cc.xy(2, 2));
		builder.add(tabbedPane, cc.xyw(2, 4, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				6, 1));

		builder.getPanel();

		return builder.getPanel();
	}

	/**
	 * Bygger panel for oppsett av fakturaparametre
	 * 
	 * @return panel
	 */
	private JPanel buildConfigPanel() {
		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,100dlu,3dlu,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		//PanelBuilder builder = new PanelBuilder(new FormDebugPanel(),layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("År:", cc.xy(2, 2));
		builder.add(yearChooser, cc.xy(4, 2));
		builder.addLabel("Fra periode:", cc.xy(6, 2));
		builder.add(monthChooserFrom, cc.xy(8, 2));
		builder.addLabel("Til periode:", cc.xy(10, 2));
		builder.add(monthChooserTo, cc.xy(12, 2));
		
		
		builder.addLabel("Betingelsegruppe:", cc.xy(14, 2));
		builder.add(comboBoxInvoiceGroup, cc.xy(16, 2));
		builder.add(buttonMoreGroups,cc.xy(18,2));
		builder.addLabel("Betingelse:", cc.xy(14, 4));
		builder.add(comboBoxDiv, cc.xy(16, 4));
		builder.addLabel("Valgte grupper:",cc.xy(14,6));
		builder.add(new JScrollPane(listGroups),cc.xywh(16,6,1,5));
		
		builder.addLabel("Selskap:", cc.xy(2, 4));
		builder.add(comboBoxCompany, cc.xy(4, 4));
		builder.addLabel("Avregning:", cc.xy(6, 4));
		builder.add(comboBoxSettlement, cc.xy(8, 4));
		builder.addLabel("Basis:", cc.xy(10, 4));
		builder.add(comboBoxBasis, cc.xy(12, 4));
		
		
		
		
		
		
		builder.addLabel("Fra avd:", cc.xy(2, 6));
		builder.add(textFieldFromDep, cc.xy(4, 6));
		builder.addLabel("Til avd:", cc.xy(6, 6));
		builder.add(textFieldToDep, cc.xy(8, 6));
		builder.addLabel("Fakturadato:", cc.xy(10, 6));
		builder.add(dateChooserInvoiceDate, cc.xy(12, 6));
		builder.add(buttonDepartments, cc.xy(4, 8));
		builder.addLabel("Forfallsdato:", cc.xy(10, 8));
		builder.add(dateChooserDueDate, cc.xy(12, 8));
		
		
		
		builder.add(ButtonBarFactory.buildCenteredBar(buttonRunInvoice,buttonLoadBatches), cc
				.xyw(2, 10, 17));

		return builder.getPanel();
	}

}
