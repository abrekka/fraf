package no.ica.elfa.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.elfa.gui.handlers.ReconcilViewHandler;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av dialog for avstemmingsrapport
 * 
 * @author abr99
 * 
 */
public class ReconcilView {
	/**
	 * 
	 */
	private ReconcilViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableReport;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JButton buttonShowReport;

	/**
	 * 
	 */
	private JComboBox comboBoxBatches;

	/**
	 * 
	 */
	private JButton buttonExcel;

	/**
	 * @param handler
	 */
	public ReconcilView(ReconcilViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableReport = viewHandler.getTableReport();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonShowReport = viewHandler.getButtonShowReport(window);
		comboBoxBatches = viewHandler.getComcoBoxBatches();
		buttonExcel = viewHandler.getButtonExcel(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p,3dlu,150dlu,10dlu",
				"10dlu,p,3dlu,p,3dlu,50dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		// PanelBuilder builder = new PanelBuilder(new FormDebugPanel(),
		// layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Bunt:", cc.xy(2, 2));
		builder.add(comboBoxBatches, cc.xy(4, 2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonShowReport), cc
				.xyw(2, 4, 3));
		builder.add(new JScrollPane(tableReport), cc.xyw(2, 6, 3));
		builder.add(ButtonBarFactory
				.buildCenteredBar(buttonExcel, buttonCancel), cc.xyw(2, 8, 3));

		return builder.getPanel();
	}
}
