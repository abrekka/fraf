package no.ica.elfa.gui;

import javax.swing.JLabel;

import no.ica.elfa.dao.pkg.EepImportPkgDAO;
import no.ica.elfa.gui.handlers.FileImportViewHandler;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer sjekk av import
 * 
 * @author abr99
 * 
 */
public class CheckImport implements Threadable {
	/**
	 * 
	 */
	private ApplUser applicationUser;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private FileImportViewHandler viewHandler;

	/**
	 * @param user
	 * @param aWindow
	 * @param handler
	 */
	public CheckImport(ApplUser user, WindowInterface aWindow,
			FileImportViewHandler handler) {
		applicationUser = user;
		window = aWindow;
		viewHandler = handler;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		labelInfo.setText("Sjekker importerte filer");
		String error = null;
		try {
			EepImportPkgDAO eepImportPkgDAO = (EepImportPkgDAO) ModelUtil
					.getBean("eepImportPkgDAO");
			eepImportPkgDAO.runImportFile(applicationUser);
			viewHandler.refresh();
		} catch (RuntimeException e) {
			error = GuiUtil.getUserExceptionMsg(e);
			e.printStackTrace();
		}
		return error;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}

	}

}
