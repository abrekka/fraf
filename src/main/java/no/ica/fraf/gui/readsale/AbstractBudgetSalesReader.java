package no.ica.fraf.gui.readsale;

import javax.swing.JLabel;

import no.ica.fraf.common.Locker;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.util.GuiUtil;

/**
 * Klasse som håndterer lasting av omsetning / budsjett
 * 
 * @author abr99
 * 
 */
public abstract class AbstractBudgetSalesReader implements Threadable {

	//private final ReadBudgetSaleFrame readBudgetSaleFrame;
	private Integer year;
	private Integer period;
	private Integer depFrom;
	private Integer depTo;
	private AvregningBasisType avregningBasisType;
	private AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO;
	private Login login;
	private Locker locker;
	private WindowInterface window;
	private ListHolder listHolder;

	AbstractBudgetSalesReader(final ListHolder aListHolder,
			final Integer aYear, final Integer aPeriod, final Integer aDepFrom,
			final Integer aDepTo, final AvregningBasisType aAvregningBasisType,
			final AvdelingOmsetningPkgDAO aAvdelingOmsetningPkgDAO,
			final Login aLogin,final Locker aLocker,final WindowInterface aWindow) {
		window=aWindow;
		locker=aLocker;
		login = aLogin;
		avdelingOmsetningPkgDAO = aAvdelingOmsetningPkgDAO;
		avregningBasisType = aAvregningBasisType;
		depFrom = aDepFrom;
		depTo = aDepTo;
		year = aYear;
		period = aPeriod;
		listHolder=aListHolder;
	}
	
	protected abstract String prepareImport();

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		//readBudgetSaleFrame.enableFrameComponents(enable);

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		labelInfo.setText("Importerer...");
		String errorString=prepareImport();
		errorString += importBudgetSale();
		return errorString;
	}

	private String importBudgetSale() {
		Integer basisTypeID = null;

		String errorString = "";

		if (avregningBasisType != null) {
			basisTypeID = avregningBasisType.getAvregningBasisTypeId();
		}

		try {
			Integer buntId = avdelingOmsetningPkgDAO.lesInnOmsetning(
					basisTypeID, login.getApplUser().getUserId(), year, period, depFrom,
					depTo);

			//readBudgetSaleFrame.loadData(buntId);
			listHolder.loadData(buntId);

		} catch (Exception e) {
			errorString = GuiUtil.getUserExceptionMsg(e);
			e.printStackTrace();
		}
		locker.unlock();
		return errorString;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			//GuiUtil.showErrorMsgFrame(readBudgetSaleFrame, "Feil", object.toString());
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object.toString());
		}

	}

}