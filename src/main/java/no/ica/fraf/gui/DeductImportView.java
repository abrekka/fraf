package no.ica.fraf.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.gui.handlers.DeductImportViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av importert avregning
 * 
 * @author abr99
 * 
 */
public class DeductImportView {
	/**
	 * 
	 */
	private DeductImportViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableImport;

	/**
	 * @param handler
	 */
	public DeductImportView(DeductImportViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 */
	private void initComponents() {
		tableImport = viewHandler.getTableImport();
	}

	/**
	 * Bygger panel for visning av avregningimport
	 * 
	 * @return panel
	 */
	public JPanel buildPanel() {
		initComponents();
		FormLayout layout = new FormLayout("250dlu", "150dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableImport), cc.xy(1, 1));

		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler.getImportComponentListener());
		return panel;
	}
}
