package no.ica.fraf.gui.invoicerun;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av feil i en faktureringsbunt
 * 
 * @author abr99
 * 
 */
public class ErrorView {
	/**
	 * 
	 */
	private ErrorViewHandler viewHandler;

	/**
	 * 
	 */
	private JXTable tableError;
	private JButton buttonExcel;

	/**
	 * @param handler
	 */
	public ErrorView(ErrorViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 */
	private void initComponents(WindowInterface window) {
		tableError = viewHandler.getTableError();
		buttonExcel = viewHandler.getButtonExcel(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("200dlu", "160dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableError), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonExcel),cc.xy(1,3));

		return builder.getPanel();
	}

}
