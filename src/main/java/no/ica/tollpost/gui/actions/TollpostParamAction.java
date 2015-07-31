package no.ica.tollpost.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.OverviewView;
import no.ica.fraf.gui.model.ApplParamModel;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.tollpost.dao.ApplParamTollpostDAO;
import no.ica.tollpost.gui.handlers.ApplParamTollpostViewHandler;

public class TollpostParamAction extends AbstractAction {
	private ApplUser applUser;

	public TollpostParamAction(ApplUser aApplUser) {
		super("Tollpostparametre...");
		applUser = aApplUser;
		/*
		 * applParamElfaDAO = (ApplParamElfaDAO) ModelUtil
		 * .getBean("applParamElfaDAO");
		 */
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		ApplParamTollpostDAO applParamTollpostDAO=(ApplParamTollpostDAO)ModelUtil.getBean("applParamTollpostDAO");
		OverviewView<ApplParam, ApplParamModel> overviewView = new OverviewView<ApplParam, ApplParamModel>(
				new ApplParamTollpostViewHandler(applParamTollpostDAO, applUser
						.getApplUserType()));

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
