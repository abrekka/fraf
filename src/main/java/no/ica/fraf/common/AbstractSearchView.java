package no.ica.fraf.common;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class AbstractSearchView {
	protected AbstractSearchViewHandler viewHandler;
	private JButton buttonCancel;
	private JButton buttonSearch;
	private JButton buttonShowInvoice;
	private JXTable table;
	public AbstractSearchView(AbstractSearchViewHandler handler){
		viewHandler=handler;
	}
	protected abstract JPanel buildSearchPanel();

	private void initComponents(WindowInterface window) {
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonSearch = viewHandler.getButtonSearch(window);
		table = viewHandler.getTableInvoices();
		buttonShowInvoice = viewHandler.getButtonShowInvoice(window);
	}
	
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,50dlu,3dlu,p,3dlu,50dlu,230dlu",
				"10dlu,p,3dlu,p,3dlu,150dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.add(buildSearchPanel(), cc.xy(2, 2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonSearch,
				buttonShowInvoice), cc.xyw(2, 4, 7));
		builder.add(new JScrollPane(table), cc.xyw(2, 6, 8));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc.xyw(2,
				8, 8));
		return builder.getPanel();
	}
}
