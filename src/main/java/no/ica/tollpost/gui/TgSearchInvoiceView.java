package no.ica.tollpost.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import no.ica.fraf.common.AbstractSearchView;
import no.ica.fraf.common.AbstractSearchViewHandler;
import no.ica.tollpost.gui.handlers.TgSearchInvoiceViewHandler;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TgSearchInvoiceView extends AbstractSearchView{
	/**
	 * 
	 */
	private JTextField textFieldInvoiceNr;

	/**
	 * 
	 */
	private JTextField textFieldAvdnr;

	public TgSearchInvoiceView(AbstractSearchViewHandler handler) {
		super(handler);
		
	}
	
	private void initSearchComponents(){
		textFieldAvdnr = ((TgSearchInvoiceViewHandler)viewHandler).getTextFieldAvdnr();
		textFieldInvoiceNr = ((TgSearchInvoiceViewHandler)viewHandler).getTextFieldInvoiceNr();
	}

	@Override
	protected JPanel buildSearchPanel() {
		initSearchComponents();
		FormLayout layout = new FormLayout("p,3dlu,50dlu,3dlu,p,3dlu,50dlu", "p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.addLabel("Fakturanr:",cc.xy(1,1));
		builder.add(textFieldInvoiceNr,cc.xy(3,1));
		builder.addLabel("Avdnr:",cc.xy(5,1));
		builder.add(textFieldAvdnr,cc.xy(7,1));
		
		return builder.getPanel();
		
	}

}
