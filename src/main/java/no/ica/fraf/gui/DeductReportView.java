package no.ica.fraf.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DeductReportViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av rapport
 * 
 * @author abr99
 * 
 */
public class DeductReportView {
	/**
	 * 
	 */
	private DeductReportViewHandler viewHandler;

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
	private JButton buttonExcel;

	/**
	 * @param handler
	 */
	public DeductReportView(DeductReportViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableReport = viewHandler.getTableReport();
		buttonCancel = viewHandler.getButtonCancel(window);
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
		FormLayout layout = new FormLayout("10dlu,150dlu:grow,10dlu",
				"10dlu,200dlu:grow,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableReport), cc.xy(2, 2));
		builder.add(ButtonBarFactory
				.buildCenteredBar(buttonExcel, buttonCancel), cc.xy(2, 4));

		return builder.getPanel();
	}
}
