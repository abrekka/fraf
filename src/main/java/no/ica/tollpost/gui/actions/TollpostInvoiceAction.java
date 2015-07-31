package no.ica.tollpost.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.service.E2bPkgManager;


import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.tollpost.dao.pkg.TgFakturaPkgDAO;
import no.ica.tollpost.gui.TollpostView;
import no.ica.tollpost.gui.handlers.TollpostViewHandler;
import no.ica.tollpost.service.TgFakturaLinjeVManager;
import no.ica.tollpost.service.TgFakturaManager;
import no.ica.tollpost.service.TgImportManager;
import no.ica.tollpost.service.TgTotalFaktureringVManager;

public class TollpostInvoiceAction extends AbstractAction {
	private String excelDir;
	private ApplUser applUser;

	public TollpostInvoiceAction(String dir,ApplUser aApplUser) {
		super("Fakturer...");
		excelDir=dir;
		applUser=aApplUser;
	}

	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		BuntDAO buntDAO =(BuntDAO)ModelUtil.getBean("buntDAO");
		TgImportManager tgImportManager=(TgImportManager)ModelUtil.getBean("tgImportManager");
		TgFakturaManager tgFakturaManager=(TgFakturaManager)ModelUtil.getBean("tgFakturaManager");
		
		
		
		E2bPkgManager e2bPkgManager=(E2bPkgManager)ModelUtil.getBean("tgE2bPkgManager");
		BuntStatusDAO buntStatusDAO=(BuntStatusDAO)ModelUtil.getBean("buntStatusDAO");
		BokfSelskapDAO bokfSelskapDAO=(BokfSelskapDAO)ModelUtil.getBean("bokfSelskapDAO");
		TgFakturaLinjeVManager tgFakturaLinjeVManager=(TgFakturaLinjeVManager)ModelUtil.getBean("tgFakturaLinjeVManager");
		//EflowUsersVManager eflowUsersVManager=(EflowUsersVManager)ModelUtil.getBean("eflowUsersVManager");
		TgTotalFaktureringVManager tgTotalFaktureringVManager=(TgTotalFaktureringVManager)ModelUtil.getBean("tgTotalFaktureringVManager");
		//TgFakturaPkgDAO tgFakturaPkgDAO=(TgFakturaPkgDAO)ModelUtil.getBean("tgFakturaPkgDAO");
		
		TollpostViewHandler tollpostViewHandler = new TollpostViewHandler(buntDAO,tgImportManager,tgFakturaManager,
				
				e2bPkgManager,buntStatusDAO,bokfSelskapDAO,
				tgFakturaLinjeVManager,
				//eflowUsersVManager,
				tgTotalFaktureringVManager,excelDir,applUser
				//,tgFakturaPkgDAO
				);
		TollpostView tollpostView = new TollpostView(
				tollpostViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder.buildInternalFrame("Tollpost",
				new Dimension(1000, 510)));

		window.add(tollpostView.buildPanel(window));
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