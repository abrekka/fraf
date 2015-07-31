package no.ica.elfa.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import no.ica.elfa.gui.handlers.FileImportViewHandler;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av dialog for import av fil
 * 
 * @author abr99
 * 
 */
public class FileImportView {
	/**
	 * 
	 */
	private JTextField textFieldPath;

	/**
	 * 
	 */
	private JButton buttonGetPath;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JButton buttonImportFile;

	/**
	 * 
	 */
	private FileImportViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableEepHeadLoad;

	/**
	 * 
	 */
	private JButton buttonCheckImport;

	/**
	 * 
	 */
	private JButton buttonDeleteFile;

	/**
	 * @param handler
	 */
	public FileImportView(FileImportViewHandler handler) {
		viewHandler = handler;

	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		textFieldPath = viewHandler.getTextFieldPath();
		buttonGetPath = viewHandler.getButtonGetPath();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonImportFile = viewHandler.getButtonImportFile(window);
		tableEepHeadLoad = viewHandler.getTableEepHeadLoad();
		buttonCheckImport = viewHandler.getButtonCheckImport(window);
		buttonDeleteFile = viewHandler.getButtonDeleteFile(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JComponent buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,100dlu,1dlu,p,3dlu,p,3dlu,p,3dlu,p,200dlu",
				"10dlu,p,3dlu,p,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.addLabel("Katalog:", cc.xy(2, 2));
		builder.add(textFieldPath, cc.xy(4, 2));
		builder.add(buttonGetPath, cc.xy(6, 2));
		builder.add(buttonImportFile, cc.xy(8, 2));
		builder.add(buttonCheckImport, cc.xy(10, 2));
		builder.add(buttonDeleteFile, cc.xy(12, 2));
		builder.add(new JScrollPane(tableEepHeadLoad), cc.xyw(2, 4, 12));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				6, 12));
		return builder.getPanel();
	}

}
