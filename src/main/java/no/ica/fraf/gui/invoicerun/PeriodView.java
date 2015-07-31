package no.ica.fraf.gui.invoicerun;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.PeriodViewHandler;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JYearChooser;

public class PeriodView {
	private PeriodViewHandler viewHandler;
	private JYearChooser yearChooser;
	private JComboBox comboBoxPeriode;
	private JButton buttonOk;
	private JCheckBox checkBoxAll;
	
	private String periodString;
	
	public PeriodView(PeriodViewHandler handler,String aPeriodString){
		viewHandler=handler;
		periodString=aPeriodString;
	}
	
	private void initComponents(WindowInterface window){
		yearChooser = viewHandler.getYearChooser();
		comboBoxPeriode = viewHandler.getComboBoxPeriode();
		buttonOk = viewHandler.getButtonOk(window);
		checkBoxAll = viewHandler.getCheckBoxAll();
	}
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p,3dlu,40dlu,3dlu,p,3dlu,30dlu,3dlu,p,10dlu", "10dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.addLabel("År:",cc.xy(2,2));
		builder.add(yearChooser,cc.xy(4,2));
		builder.addLabel(periodString,cc.xy(6,2));
		builder.add(comboBoxPeriode,cc.xy(8,2));
		builder.add(checkBoxAll,cc.xy(10,2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonOk),cc.xyw(2,4,9));
		
		return builder.getPanel();
	}
}
