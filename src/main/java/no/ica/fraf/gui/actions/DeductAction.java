package no.ica.fraf.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.gui.DeductView;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.handlers.DeductViewHandler;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.AvdelingAvregningBelopManager;
import no.ica.fraf.service.AvdelingAvregningImportManager;
import no.ica.fraf.service.AvdelingAvregningManager;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.service.TotalAvregningVManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Avregning
 * 
 * @author abr99
 * 
 */
public class DeductAction extends AbstractAction {
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
	public DeductAction(ApplUser aApplUser) {
		super("Avregning...");
		applUser = aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		AvdelingAvregningBelopManager avdelingAvregningBelopManager = (AvdelingAvregningBelopManager) ModelUtil
				.getBean("avdelingAvregningBelopManager");
		AvdelingAvregningManager avdelingAvregningManager = (AvdelingAvregningManager) ModelUtil
				.getBean("avdelingAvregningManager");
		FakturaDAO fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
		BetingelseGruppeDAO betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
				.getBean("betingelseGruppeDAO");
		BuntPkgDAO buntPkgDAO = (BuntPkgDAO) ModelUtil.getBean("buntPkgDAO");
		TotalAvregningVManager totalAvregningVManager = (TotalAvregningVManager) ModelUtil
				.getBean("totalAvregningVManager");
		//FakturaVDAO fakturaVDAO = (FakturaVDAO) ModelUtil.getBean("fakturaVDAO");
		ImportBetingelsePkgDAO importBetingelsePkgDAO = (ImportBetingelsePkgDAO) ModelUtil
				.getBean("importBetingelsePkgDAO");

		AvdelingAvregningImportManager avdelingAvregningImportManager = (AvdelingAvregningImportManager) ModelUtil
				.getBean("avdelingAvregningImportManager");
		//EflowUsersVManager eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");

		String excelDir = null;
		try {
			excelDir = ApplParamUtil.getStringParam("excel_file_path");
		} catch (FrafException e) {
			e.printStackTrace();
			GuiUtil.showErrorMsgDialog(FrafMain.getInstance().getRootPane(),
					"Feil", e.getMessage());
		}

		DeductViewHandler deductViewHandler = new DeductViewHandler(buntDAO,
				applUser, avdelingAvregningBelopManager,
				avdelingAvregningManager, fakturaDAO, betingelseGruppeDAO,
				buntPkgDAO, excelDir, totalAvregningVManager, 
				//fakturaVDAO,
				importBetingelsePkgDAO, avdelingAvregningImportManager);
		DeductView deductView = new DeductView(deductViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Avregning", new Dimension(860, 420)));

		window.add(deductView.buildPanel(window));
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
