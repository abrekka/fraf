package no.ica.elfa.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import no.ica.elfa.gui.handlers.ImportCreditViewHandler;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av dialog for import av kredittfiler
 * 
 * @author abr99
 * 
 */
public class ImportCreditView {
	/**
	 * 
	 */
	private ImportCreditViewHandler viewHandler;

	/**
	 * 
	 */
	private JTextField textFieldFilename;

	/**
	 * 
	 */
	private JButton buttonFileSelection;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JButton buttonReadFile;

	/**
	 * 
	 */
	private JButton buttonImportData;

	/**
	 * 
	 */
	private JXTable tableImport;

	/**
	 * 
	 */
	private JButton buttonDeleteAll;

	/**
	 * @param handler
	 */
	public ImportCreditView(ImportCreditViewHandler handler) {
		this.viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		textFieldFilename = viewHandler.getTextFieldFilename();
		buttonFileSelection = viewHandler.getButtonFileSelection();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonReadFile = viewHandler.getButtonReadFile(window);
		buttonImportData = viewHandler.getButtonImportData(window);
		tableImport = viewHandler.getTableImport();
		buttonDeleteAll = viewHandler.getButtonDeleteAll();
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
				"10dlu,p,3dlu,70dlu,3dlu,50dlu,3dlu,15dlu,20dlu:grow,10dlu,10dlu",
				"10dlu,p,3dlu,p,3dlu,200dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Fil:", cc.xy(2, 2));
		builder.add(textFieldFilename, cc.xyw(4, 2, 3));
		builder.add(buttonFileSelection, cc.xy(8, 2));
		builder.add(ButtonBarFactory.buildLeftAlignedBar(buttonReadFile,
				buttonImportData, buttonDeleteAll), cc.xyw(2, 4, 8));
		builder.add(new JScrollPane(tableImport), cc.xyw(2, 6, 8));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				8, 8));

		return builder.getPanel();
	}
}
