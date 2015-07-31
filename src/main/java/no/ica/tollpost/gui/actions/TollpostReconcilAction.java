package no.ica.tollpost.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

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
import no.ica.tollpost.service.TgReconcilVManager;

public class TollpostReconcilAction extends AbstractAction {
	/**
	 * 
	 */
	private String excelDir;

	/**
	 * 
	 */
	private ApplUser applUser;
	public TollpostReconcilAction(String dir, ApplUser aApplUser) {
		super("Avstemmingsrapport...");
		excelDir = dir;
		applUser = aApplUser;
		
	}
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.setWaitCursor(FrafMain.getInstance());
			BuntDAO buntDAO = (BuntDAO) ModelUtil
					.getBean("buntDAO");
			TgReconcilVManager tgReconcilVManager = (TgReconcilVManager) ModelUtil
					.getBean("tgReconcilVManager");

			ReconcilViewHandler viewHandler = new ReconcilViewHandler(buntDAO,
					tgReconcilVManager, excelDir, applUser,SystemEnum.TOLLPOST);
			ReconcilView view = new ReconcilView(viewHandler);

			WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
					.buildInternalFrame("Avstemmingsrapport", new Dimension(295,
							200)));

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
