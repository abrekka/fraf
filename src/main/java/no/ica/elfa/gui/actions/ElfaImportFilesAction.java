package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.FileImportView;
import no.ica.elfa.gui.handlers.FileImportViewHandler;
import no.ica.elfa.service.EepHeadLoadManager;
import no.ica.elfa.service.EepHeadManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg importer salgsfiler
 * 
 * @author abr99
 * 
 */
public class ElfaImportFilesAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApplUser applUser;

	/**
	 * 
	 */
	public ElfaImportFilesAction(ApplUser aApplUser) {
		super("Importer salgsfiler...");
		applUser=aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		EepHeadLoadManager eepHeadLoadManager = (EepHeadLoadManager) ModelUtil
				.getBean("eepHeadLoadManager");
		EepHeadManager eepHeadManager = (EepHeadManager) ModelUtil
				.getBean("eepHeadManager");
		FileImportViewHandler fileImportViewHandler = new FileImportViewHandler(
				eepHeadLoadManager, applUser, eepHeadManager);
		FileImportView fileImportView = new FileImportView(
				fileImportViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Import av salgsfiler", new Dimension(920,
						490)));

		window.add(fileImportView.buildPanel(window));
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