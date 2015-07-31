package no.ica.fraf.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.OverviewView;
import no.ica.fraf.gui.handlers.ApplUserViewHandler;
import no.ica.fraf.gui.model.ApplUserModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg Brukere...
 * 
 * @author abr99
 * 
 */
public class ApplUserAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * @param aApplUser
	 */
	public ApplUserAction(ApplUser aApplUser) {
		super("Brukere...");
		applUser = aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		ApplUserDAO applUserDAO = (ApplUserDAO) ModelUtil
				.getBean("applUserDAO");
		OverviewView<ApplUser, ApplUserModel> overviewView = new OverviewView<ApplUser, ApplUserModel>(
				new ApplUserViewHandler(applUserDAO, applUser.getApplUserType()));

		WindowInterface window = overviewView.buildWindow();
		GuiUtil.locateOnScreenCenter(window);
		FrafMain.getInstance().addInternalFrame(window.getInternalFrame());
		try {
			window.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}

	}

}
