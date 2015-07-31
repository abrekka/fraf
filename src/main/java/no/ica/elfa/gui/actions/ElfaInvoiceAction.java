package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.InvoiceView;
import no.ica.elfa.gui.handlers.InvoiceViewHandler;
import no.ica.elfa.service.EepHeadManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg fakturer
 * 
 * @author abr99
 * 
 */
public class ElfaInvoiceAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ApplUser frafUser;

	/**
	 * @param user
	 */
	public ElfaInvoiceAction(ApplUser user) {
		super("Fakturer...");
		frafUser = user;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		EepHeadManager eepHeadManager = (EepHeadManager) ModelUtil
				.getBean("eepHeadManager");
		//ApplUserManager applUserManager = (ApplUserManager) ModelUtil.getBean("applUserManager");
		InvoiceViewHandler invoiceViewHandler = new InvoiceViewHandler(
				eepHeadManager, frafUser
				//, applUserManager
				);
		InvoiceView invoiceView = new InvoiceView(invoiceViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Fakturering", new Dimension(940, 480)));

		window.add(invoiceView.buildPanel(window));
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