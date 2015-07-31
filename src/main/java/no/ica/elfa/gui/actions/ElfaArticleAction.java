package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.ArticleCommissionView;
import no.ica.elfa.gui.handlers.ArticleCommissionViewHandler;
import no.ica.elfa.service.ArticleCommissionManager;
import no.ica.elfa.service.SupplierAccountManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg artikler
 * 
 * @author abr99
 * 
 */
public class ElfaArticleAction extends AbstractAction {
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
	public ElfaArticleAction(ApplUser aApplUser) {
		super("Artikler...");
		applUser = aApplUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		ArticleCommissionManager articleCommissionManager = (ArticleCommissionManager) ModelUtil
				.getBean("articleCommissionManager");
		SupplierAccountManager supplierAccountManager = (SupplierAccountManager) ModelUtil
				.getBean("supplierAccountManager");

		ArticleCommissionViewHandler articleCommissionViewHandler = new ArticleCommissionViewHandler(
				articleCommissionManager, supplierAccountManager, applUser);
		ArticleCommissionView articleCommissionView = new ArticleCommissionView(
				articleCommissionViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Artikler", new Dimension(625, 470)));

		window.add(articleCommissionView.buildPanel(window));
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