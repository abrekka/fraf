package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.ImportCreditView;
import no.ica.elfa.gui.handlers.ImportCreditViewHandler;
import no.ica.elfa.service.CreditImportManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;

/**
 * Håndterer menyvalg kredittfiler
 * 
 * @author abr99
 * 
 */
public class ElfaCreditImportAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ApplUser frafApplUser;

	/**
	 * 
	 */
	private CreditImportManager creditImportManager;

	public ElfaCreditImportAction(ApplUser aApplUser,
			CreditImportManager aCreditImportManager
			//,BatchManager aBatchManager, BatchStatusManager aBatchStatusManager
			) {
		super("Importer kredittnota...");
		this.frafApplUser = aApplUser;
		this.creditImportManager = aCreditImportManager;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		ImportCreditViewHandler importCreditViewHandler = new ImportCreditViewHandler(
				frafApplUser, creditImportManager//, 
				);
		ImportCreditView importCreditView = new ImportCreditView(
				importCreditViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Import av kredittnota", new Dimension(550,
						460)));

		window.add(importCreditView.buildPanel(window));
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