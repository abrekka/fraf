package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ClaimView {
	private ClaimViewHandler viewHandler;

	private JXTable tableClaims;
	private JButton buttonExcel;

	public ClaimView(ClaimViewHandler handler) {
		viewHandler = handler;
	}

	private void initComponents(WindowInterface window) {
		tableClaims = viewHandler.getTableClaims();
		buttonExcel = viewHandler.getButtonExcel(window);
	}

	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("550dlu", "230dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableClaims), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonExcel),cc.xy(1,3));

		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler
				.getClaimComponentListener(window));
		return panel;
	}
}
