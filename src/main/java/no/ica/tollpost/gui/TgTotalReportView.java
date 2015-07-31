package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.TgTotalReportViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TgTotalReportView {
	private TgTotalReportViewHandler viewHandler;
	private JXTable tableReport;
	private JButton buttonExcel;
	private JButton buttonCancel;
	
	public TgTotalReportView(TgTotalReportViewHandler handler){
		viewHandler = handler;
	}
	private void initComponents(WindowInterface window){
		tableReport = viewHandler.getTableReport();
		buttonCancel=viewHandler.getButtonCancel(window);
		buttonExcel=viewHandler.getButtonExcel(window);
		
	}
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,200dlu:grow,10dlu", "10dlu,100dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.add(new JScrollPane(tableReport),cc.xy(2,2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonExcel,buttonCancel),cc.xy(2,4));
		
		return builder.getPanel();
	}
}
