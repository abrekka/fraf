package no.ica.fraf.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DeductViewHandler;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av all informasjon rundt avregning
 * 
 * @author abr99
 * 
 */
public class DeductView {
	/**
	 * 
	 */
	private JTextField textFieldFileName;

	/**
	 * 
	 */
	private JButton buttonSelectFile;

	/**
	 * 
	 */
	private DeductViewHandler viewHandler;

	/**
	 * 
	 */
	private JButton buttonImport;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * @param handler
	 */
	public DeductView(DeductViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		textFieldFileName = viewHandler.getTextFieldFileName();
		buttonSelectFile = viewHandler.getButtonSelectFile(window);
		buttonImport = viewHandler.getButtonImport(window);
		buttonCancel = viewHandler.getButtonCancel(window);
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
				"10dlu,100dlu,3dlu,100dlu,3dlu,p,3dlu,p,3dlu,200dlu",
				"10dlu,p,3dlu,p,5dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add(buildBatchPane(window), "Bunter");
		tabbedPane.add(buildImportPane(), "Importert");
		tabbedPane.add(buildDeductPane(window), "Avregning");
		tabbedPane.add(buildInvoicePane(window), "Fakturaer");
		builder.addLabel("Fil:", cc.xy(2, 2));
		builder.add(textFieldFileName, cc.xy(4, 2));
		builder.add(buttonSelectFile, cc.xy(6, 2));
		builder.add(buttonImport, cc.xy(8, 2));
		builder.add(tabbedPane, cc.xyw(2, 4, 9));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				6, 9));
		return builder.getPanel();
	}

	/**
	 * Bygger panel for bunter
	 * 
	 * @param window
	 * @return panel
	 */
	private JPanel buildBatchPane(WindowInterface window) {
		return viewHandler.getBatchPanel(window);
	}

	/**
	 * Bygger panel for import
	 * 
	 * @return panel
	 */
	private JPanel buildImportPane() {
		return viewHandler.getImportPanel();
	}

	/**
	 * Bygger panel for avregning
	 * 
	 * @param window
	 * @return panel
	 */
	private JPanel buildDeductPane(WindowInterface window) {
		return viewHandler.getDeductPanel(window);
	}

	/**
	 * Bygger panel for fakturaer
	 * 
	 * @param window
	 * @return panel
	 */
	private JPanel buildInvoicePane(WindowInterface window) {
		return viewHandler.getInvoicePanel(window);
	}
}
