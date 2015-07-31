package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.DepViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class DepView {
	private DepViewHandler viewHandler;
	private JXTable tableDep;
	private JButton buttonEdit;
	private JButton buttonClose;
	public DepView(DepViewHandler handler){
		viewHandler=handler;
	}
	private void initComponents(WindowInterface window){
		tableDep=viewHandler.getTableDep();
		buttonEdit=viewHandler.getButtonEdit(window);
		buttonClose=viewHandler.getButtonClose(window);
	}
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p,3dlu,p,10dlu", "10dlu,p,3dlu,p,5dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.addLabel("Linjer uten avdeling",cc.xy(2,2));
		builder.add(new JScrollPane(tableDep),cc.xy(2,4));
		builder.add(buttonEdit,cc.xy(4,4));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonClose),cc.xyw(2,6,3));
		
		return builder.getPanel();
	}
}
