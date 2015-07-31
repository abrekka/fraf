package no.ica.fraf.gui;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.handlers.BetingelseViewHandler;
import no.ica.fraf.gui.model.BetingelseTypeModel;
import no.ica.fraf.model.BetingelseType;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class OverviewBetingelseView extends OverviewView<BetingelseType, BetingelseTypeModel>{
	private JCheckBox checkBoxFilter;
	public OverviewBetingelseView(AbstractViewHandler handler) {
		super(handler);
	}
	
	public JComponent buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("15dlu,"
				+ viewHandler.getTableWidth() + ":grow,3dlu,p,15dlu",
				"10dlu,p,3dlu,10dlu,p,3dlu,p,3dlu,p,3dlu,p,"
						+ viewHandler.getTableHeight() + ":grow,p,5dlu,p,5dlu");
		//PanelBuilder builder = new PanelBuilder(layout,new FormDebugPanel());
		PanelBuilder builder = new PanelBuilder(layout);
		JScrollPane scrollPaneTable = new JScrollPane(table);
		CellConstraints cc = new CellConstraints();
		scrollPaneTable.setBorder(Borders.EMPTY_BORDER);
		builder.add(labelHeading, cc.xy(2, 2));
		builder.add(checkBoxFilter,cc.xy(4,2));
		builder.add(scrollPaneTable, cc.xywh(2, 4, 1, 9));
		builder.add(buildButtonPanel(), cc.xywh(4, 5, 1, 10));
		builder.add(ButtonBarFactory
				.buildCenteredBar(buttonExcel, buttonCancel), cc.xyw(2, 15, 3));

		mainPanel = builder.getPanel();
		return mainPanel;
	}
	protected void initComponents(WindowInterface window) {
		super.initComponents(window);
		checkBoxFilter=((BetingelseViewHandler)viewHandler).getCheckBoxFilter();
	}


}
