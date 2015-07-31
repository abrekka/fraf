package no.ica.elfa.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.elfa.gui.ElfaInvoiceConfig;
import no.ica.elfa.gui.InvoicePrinter;
import no.ica.elfa.service.InvoiceItemVManager;
import no.ica.elfa.service.InvoiceManager;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.SearchInvoiceView;
import no.ica.fraf.common.SearchInvoiceViewHandler;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer menyvalg søk faktura
 * 
 * @author abr99
 * 
 */
public class ElfaSearchAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ElfaSearchAction() {
		super("Søk faktura...");
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		InvoiceManager invoiceManager = (InvoiceManager) ModelUtil
				.getBean("invoiceManager");
		BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
				.getBean("bokfSelskapDAO");
		InvoiceItemVManager invoiceItemVManager = (InvoiceItemVManager) ModelUtil
				.getBean("invoiceItemVManager");
		BokfSelskap selskap = bokfSelskapDAO.findByName("110");

		InvoicePrinter invoicePrinter = new InvoicePrinter(invoiceItemVManager,
				selskap);

		ElfaInvoiceConfig elfaInvoiceConfig = new ElfaInvoiceConfig();
		SearchInvoiceViewHandler viewHandler = new SearchInvoiceViewHandler(
				invoiceManager, invoicePrinter, elfaInvoiceConfig);
		SearchInvoiceView view = new SearchInvoiceView(viewHandler);

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Fakturaer", new Dimension(810, 380)));

		window.add(view.buildPanel(window));
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
