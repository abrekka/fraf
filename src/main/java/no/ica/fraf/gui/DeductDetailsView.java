package no.ica.fraf.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DeductDetailsViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av avregingsdetaljer
 * 
 * @author abr99
 * 
 */
public class DeductDetailsView {
	/**
	 * 
	 */
	private DeductDetailsViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableDeduct;

	/**
	 * 
	 */
	private JButton buttonExcel;

	/**
	 * @param handler
	 */
	public DeductDetailsView(DeductDetailsViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableDeduct = viewHandler.getTableDeduct();
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
		FormLayout layout = new FormLayout("350dlu", "150dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableDeduct), cc.xy(1, 1));
		builder
				.add(ButtonBarFactory.buildCenteredBar(buttonExcel), cc
						.xy(1, 3));

		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler.getDeductComponentListener());
		return panel;
	}
}
