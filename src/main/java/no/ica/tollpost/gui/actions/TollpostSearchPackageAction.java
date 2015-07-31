package no.ica.tollpost.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.tollpost.gui.TgFakturaPrinter;
import no.ica.tollpost.gui.TgSearchInvoiceView;
import no.ica.tollpost.gui.TgSearchPackageView;
import no.ica.tollpost.gui.TollpostInvoiceConfig;
import no.ica.tollpost.gui.TollpostPackageConfig;
import no.ica.tollpost.gui.handlers.TgSearchInvoiceViewHandler;
import no.ica.tollpost.gui.handlers.TgSearchPackageViewHandler;
import no.ica.tollpost.service.TgFakturaLinjeVManager;
import no.ica.tollpost.service.TgFakturaManager;

public class TollpostSearchPackageAction extends AbstractAction {
	public TollpostSearchPackageAction() {
		super("Søk pakkeid...");
	}

	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		TgFakturaManager tgFakturaManager = (TgFakturaManager)ModelUtil.getBean("tgFakturaManager");
		BokfSelskapDAO bokfSelskapDAO=(BokfSelskapDAO)ModelUtil.getBean("bokfSelskapDAO");
		
		TgFakturaLinjeVManager tgFakturaLinjeVManager=(TgFakturaLinjeVManager)ModelUtil.getBean("tgFakturaLinjeVManager");
		BokfSelskap selskap = bokfSelskapDAO.findByName("100");
		
		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder.buildInternalFrame("Pakker",
				new Dimension(900, 375)));
		
		TgFakturaPrinter tgFakturaPrinter=new TgFakturaPrinter(tgFakturaLinjeVManager,selskap,window);
		
		//SearchInvoiceViewHandler viewHandler = new SearchInvoiceViewHandler(tgFakturaManager,tgFakturaPrinter,tollpostInvoiceConfig);
		TgSearchPackageViewHandler viewHandler = new TgSearchPackageViewHandler(tgFakturaPrinter);
		//SearchInvoiceView view = new SearchInvoiceView(viewHandler);
		TgSearchPackageView view = new TgSearchPackageView(viewHandler);
		
		

		window.add(view.buildPanel(window));
		window.pack();
		GuiUtil.locateOnScreenCenter(window);
		FrafMain.getInstance().addInternalFrame(window.getInternalFrame());
		try {
			window.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
		GuiUtil.setDefaultCursor(FrafMain.getInstance());
		
	}

}
