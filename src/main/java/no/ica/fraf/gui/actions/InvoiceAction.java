package no.ica.fraf.gui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.FrafMainMenuBarInterface;
import no.ica.fraf.gui.invoicerun.InvoiceRunView;
import no.ica.fraf.gui.invoicerun.InvoiceRunViewHandler;
import no.ica.fraf.util.GuiUtil;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Menyvalg Fakturer periode...
 * 
 * @author abr99
 * 
 */
public class InvoiceAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	private String dir;
	private FrafMainMenuBarInterface menuBar;

	@Inject
	public InvoiceAction(final FrafMainMenuBarInterface aMenuBar, @Named(value="excel_file_path") String excelDir) {
		super("Fakturer periode...");
		menuBar=aMenuBar;
		dir = excelDir;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());

		InvoiceRunViewHandler viewHandler = new InvoiceRunViewHandler(
				menuBar.getApplicationUser(), dir);

		InvoiceRunView view = new InvoiceRunView(viewHandler);
		GuiUtil.createAndShowInternalFrame(view, "Fakturering", new Dimension(
				1005, 570));

		GuiUtil.setDefaultCursor(FrafMain.getInstance());

	}

}
