package no.ica.tollpost.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import no.ica.fraf.common.AbstractSearchView;
import no.ica.fraf.common.AbstractSearchViewHandler;
import no.ica.tollpost.gui.handlers.TgSearchPackageViewHandler;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TgSearchPackageView extends AbstractSearchView{
	private JTextField textFieldSendNr;
	private JTextField textFieldKolliId;

	public TgSearchPackageView(AbstractSearchViewHandler handler) {
		super(handler);
	}
	private void initSearchComponents(){
		textFieldKolliId=((TgSearchPackageViewHandler)viewHandler).getTextFieldKolliId();
		textFieldSendNr=((TgSearchPackageViewHandler)viewHandler).getTextFieldSendNr();
	}

	@Override
	protected JPanel buildSearchPanel() {
		initSearchComponents();
		FormLayout layout = new FormLayout("p,3dlu,80dlu,3dlu,p,3dlu,80dlu", "p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.addLabel("Sendingsnr:",cc.xy(1,1));
		builder.add(textFieldSendNr,cc.xy(3,1));
		builder.addLabel("Kollinr:",cc.xy(5,1));
		builder.add(textFieldKolliId,cc.xy(7,1));
		
		return builder.getPanel();
		
	}

}
