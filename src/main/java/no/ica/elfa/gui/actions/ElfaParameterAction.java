package no.ica.elfa.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.handlers.ApplParamElfaViewHandler;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.OverviewView;
import no.ica.fraf.gui.model.ApplParamModel;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg Elfaparametre...
 * 
 * @author abr99
 * 
 */
public class ElfaParameterAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	//private ApplParamElfaDAO applParamElfaDAO;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * @param aApplUser
	 */
	public ElfaParameterAction(ApplUser aApplUser) {
		super("Elfaparametre...");
		applUser = aApplUser;
		//applParamElfaDAO = (ApplParamElfaDAO) ModelUtil.getBean("applParamElfaDAO");
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		ApplParamDAO applParamDAO=(ApplParamDAO)ModelUtil.getBean("applParamDAO");
		OverviewView<ApplParam, ApplParamModel> overviewView = new OverviewView<ApplParam, ApplParamModel>(
				new ApplParamElfaViewHandler(applParamDAO, applUser
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
