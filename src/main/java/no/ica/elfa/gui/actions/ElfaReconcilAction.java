package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.service.ReconcilVManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.ReconcilView;
import no.ica.fraf.common.ReconcilViewHandler;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg avstemmingsrapport
 * 
 * @author abr99
 * 
 */
public class ElfaReconcilAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String excelDir;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * @param dir
	 * @param aApplUser
	 */
	public ElfaReconcilAction(String dir, ApplUser aApplUser) {
		super("Avstemmingsrapport...");
		excelDir = dir;
		applUser = aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		ReconcilVManager reconcilVManager = (ReconcilVManager) ModelUtil.getBean("reconcilVManager");

		ReconcilViewHandler viewHandler = new ReconcilViewHandler(buntDAO,
				reconcilVManager, excelDir, applUser,SystemEnum.ELFA);
		ReconcilView view = new ReconcilView(viewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Avstemmingsrapport", new Dimension(340,
						230)));

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
