package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.CommissionViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class CommissionView {
	private CommissionViewHandler viewHandler;

	private JXTable tableCommission;
	private JButton buttonExcel;

	public CommissionView(CommissionViewHandler handler) {
		viewHandler = handler;
	}

	private void initComponents(WindowInterface window) {
		tableCommission = viewHandler.getTableCommission();
		buttonExcel =viewHandler.getButtonExcel(window); 
	}

	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("p", "240dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableCommission), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonExcel),cc.xy(1,3));

		JPanel panel = builder.getPanel();
		panel.addComponentListener(viewHandler
				.getClaimComponentListener(window));
		return panel;
	}
}
