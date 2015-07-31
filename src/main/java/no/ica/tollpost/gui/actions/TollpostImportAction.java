package no.ica.tollpost.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.tollpost.gui.ImportDataView;
import no.ica.tollpost.gui.handlers.ImportDataViewHandler;
import no.ica.tollpost.service.TgMeldingExtVManager;
import no.ica.tollpost.service.TgNotImportedVManager;

public class TollpostImportAction extends AbstractAction {
	private ApplUser applUser;
	public TollpostImportAction(ApplUser aApplUser) {
		super("Importer...");
		applUser=aApplUser;
	}

	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		TgNotImportedVManager tgNotImportedVManager=(TgNotImportedVManager)ModelUtil.getBean("tgNotImportedVManager");
		DepartmentDAO departmentDAO=(DepartmentDAO)ModelUtil.getBean(DepartmentDAO.DAO_NAME);
		TgMeldingExtVManager tgMeldingExtVManager=(TgMeldingExtVManager)ModelUtil.getBean("tgMeldingExtVManager");
		BuntTypeDAO buntTypeDAO=(BuntTypeDAO)ModelUtil.getBean("buntTypeDAO");
		BuntStatusDAO buntStatusDAO=(BuntStatusDAO)ModelUtil.getBean("buntStatusDAO");
		BuntDAO buntDAO=(BuntDAO)ModelUtil.getBean("buntDAO");
		ImportDataViewHandler importDataViewHandler = new ImportDataViewHandler(tgNotImportedVManager,departmentDAO,tgMeldingExtVManager,buntTypeDAO,buntDAO,buntStatusDAO,applUser);
		ImportDataView importDataView = new ImportDataView(
				importDataViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder.buildInternalFrame("Import Tollpost",
				new Dimension(460, 480)));

		window.add(importDataView.buildPanel(window));
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