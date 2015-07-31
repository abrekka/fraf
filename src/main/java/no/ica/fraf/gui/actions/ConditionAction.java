package no.ica.fraf.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.OverviewBetingelseView;
import no.ica.fraf.gui.OverviewView;
import no.ica.fraf.gui.handlers.BetingelseGruppeEnum;
import no.ica.fraf.gui.handlers.BetingelseViewHandler;
import no.ica.fraf.gui.model.BetingelseTypeModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.util.GuiUtil;

/**
 * Håndterer menyvalg Betingelser...
 * 
 * @author abr99
 * 
 */
public class ConditionAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private BetingelseTypeDAO betingelseTypeDAO;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * @param aBetingelseTypeDAO
	 * @param aApplUser
	 */
	public ConditionAction(BetingelseTypeDAO aBetingelseTypeDAO,
			ApplUser aApplUser) {
		super("Betingelser...");
		betingelseTypeDAO = aBetingelseTypeDAO;
		applUser = aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		/*OverviewView<BetingelseType, BetingelseTypeModel> overviewView = new OverviewView<BetingelseType, BetingelseTypeModel>(
				new BetingelseViewHandler(betingelseTypeDAO, applUser
						.getApplUserType(), BetingelseGruppeEnum.ADMIN));*/
		OverviewBetingelseView overviewView = new OverviewBetingelseView(
				new BetingelseViewHandler(betingelseTypeDAO, applUser
						.getApplUserType(), BetingelseGruppeEnum.ADMIN));

		WindowInterface window = overviewView.buildWindow();
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
