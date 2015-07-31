package no.ica.fraf.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.readsale.ReadBudgetSaleFrame;
import no.ica.fraf.gui.readsale.ReadBudgetSaleView;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.util.GuiUtil;

import com.google.inject.Inject;

public class ImportBudgetSaleAction extends AbstractAction {
	private Login login;
	private AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO;
	private AvregningBasisTypeDAO avregningBasisTypeDAO;
	private AvdelingOmsetningDAO avdelingOmsetningDAO;
	private LaasTypeDAO laasTypeDAO;
	private LaasDAO laasDAO;
	private BuntDAO buntDAO;
	private BuntPkgDAO buntPkgDAO;
	private SapSaleManager sapSaleManager;
	private String xmlBaseDir;
	private String xmlFileName;
	
	//private ReadBudgetSaleFrame readBudgetSaleFrame;
	private ReadBudgetSaleView readBudgetSaleView;

	@Inject
	//public ImportBudgetSaleAction(final ReadBudgetSaleFrame aReadBudgetSaleFrame) {
	public ImportBudgetSaleAction(final ReadBudgetSaleView aReadBudgetSaleView) {
		super("Les inn budsjett/omsetning");
		readBudgetSaleView=aReadBudgetSaleView;
	}

	public void actionPerformed(ActionEvent arg0) {
		WindowInterface window =readBudgetSaleView.buildWindow();
		
		GuiUtil.locateOnScreenCenter(window);
		FrafMain.getInstance().addInternalFrame(window.getInternalFrame());
		try {
			window.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
		
		/*readBudgetSaleFrame.setVisible(true);
		readBudgetSaleFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getDesktopPane().getSize(), readBudgetSaleFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(readBudgetSaleFrame);
		try {
			readBudgetSaleFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}*/

	}

}
