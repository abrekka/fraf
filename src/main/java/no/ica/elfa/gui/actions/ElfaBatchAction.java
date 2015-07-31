package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.BatchView;
import no.ica.elfa.gui.handlers.BatchViewHandler;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.InvoiceItemVManager;
import no.ica.elfa.service.InvoiceManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.ApplUserUtil;
import no.ica.fraf.util.GuiUtil;

/**
 * Håndterer menyvalg bunter
 * 
 * @author abr99
 * 
 */
public class ElfaBatchAction extends AbstractAction implements
		ElfaBatchActionInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ApplUser frafUser;

	private InvoiceManager invoiceManager;

	private E2bPkgManager e2bPkgManager;

	private BokfSelskapDAO bokfSelskapDAO;

	private InvoiceItemVManager invoiceItemVManager;

	//private EflowUsersVManager eflowUsersVManager;

	public ElfaBatchAction() {
		super("Bunter...");

	}

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setApplUser(no.ica.fraf.model.ApplUser)
	 */
	public void setApplUser(ApplUser applUser) {
		frafUser = applUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		BatchViewHandler batchViewHandler = new BatchViewHandler(
				invoiceManager, e2bPkgManager, invoiceItemVManager,
				bokfSelskapDAO, 
				//eflowUsersVManager, 
				ApplUserUtil
						.isAdministrator(frafUser), frafUser);
		BatchView batchView = new BatchView(batchViewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Bunter", new Dimension(750, 520)));

		window.add(batchView.buildPanel(window));
		window.pack();
		GuiUtil.locateOnScreenCenter(window);
		FrafMain.getInstance().addInternalFrame(window.getInternalFrame());
		try {
			window.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setBatchManager(no.ica.elfa.service.BatchManager)
	 */

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setE2bPkgManager(no.ica.elfa.service.E2bPkgManager)
	 */
	public void setE2bPkgManager(E2bPkgManager pkgManager) {
		e2bPkgManager = pkgManager;
	}

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setInvoiceManager(no.ica.elfa.service.InvoiceManager)
	 */
	public void setInvoiceManager(InvoiceManager invoiceManager) {
		this.invoiceManager = invoiceManager;
	}

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setInvoiceItemVManager(no.ica.elfa.service.InvoiceItemVManager)
	 */
	public void setInvoiceItemVManager(InvoiceItemVManager invoiceItemVManager) {
		this.invoiceItemVManager = invoiceItemVManager;
	}

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setBokfSelskapDAO(no.ica.fraf.dao.BokfSelskapDAO)
	 */
	public void setBokfSelskapDAO(BokfSelskapDAO bokfSelskapDAO) {
		this.bokfSelskapDAO = bokfSelskapDAO;
	}

	/**
	 * @see no.ica.elfa.gui.actions.ElfaBatchActionInterface#setEflowUsersVManager(no.ica.fraf.service.EflowUsersVManager)
	 */
	/*public void setEflowUsersVManager(EflowUsersVManager eflowUsersVManager) {
		this.eflowUsersVManager = eflowUsersVManager;
	}*/

}
