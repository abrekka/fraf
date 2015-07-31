package no.ica.fraf.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.OverviewView;
import no.ica.fraf.gui.handlers.BokfSelskapViewHandler;
import no.ica.fraf.gui.model.BokfSelskapModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg Selskap...
 * @author abr99
 *
 */
public class CompanyAction extends AbstractAction {
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
	public CompanyAction(ApplUser aApplUser) {
		super("Selskap...");
		applUser = aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		BokfSelskapDAO bokfSelskapDAO=(BokfSelskapDAO)ModelUtil.getBean("bokfSelskapDAO");
		OverviewView<BokfSelskap, BokfSelskapModel> overviewView = new OverviewView<BokfSelskap, BokfSelskapModel>(
				new BokfSelskapViewHandler(bokfSelskapDAO,applUser.getApplUserType()));

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
