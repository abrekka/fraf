package no.ica.fraf.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.FrafInvoiceConfig;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.SearchInvoiceView;
import no.ica.fraf.common.SearchInvoiceViewHandler;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.InvoiceReport;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Menyvalg Søk faktura...
 * 
 * @author abr99
 * 
 */
public class SearchInvoiceAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private FakturaDAO fakturaDAO;

	/**
	 * @param aFakturaDAO
	 */
	public SearchInvoiceAction(FakturaDAO aFakturaDAO) {
		super("Søk faktura...");
		fakturaDAO = aFakturaDAO;

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());

		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame("Fakturaer", new Dimension(810, 375)));
		
		//EflowUsersVManager eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");


		FrafInvoiceConfig frafInvoiceConfig = new FrafInvoiceConfig();
		InvoiceReport invoiceReport = new InvoiceReport(window,null);
		SearchInvoiceViewHandler viewHandler = new SearchInvoiceViewHandler(
				fakturaDAO, invoiceReport, frafInvoiceConfig);
		SearchInvoiceView view = new SearchInvoiceView(viewHandler);

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
