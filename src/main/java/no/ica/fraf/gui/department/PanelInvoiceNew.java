package no.ica.fraf.gui.department;

import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.table.TableModel;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.InvoiceViewHandler;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Faktura;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Panel for visning av fakturaer
 * 
 * @author abr99
 * 
 */
public final class PanelInvoiceNew extends FrafPanel<Faktura> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Navn på panel som brukes til popupmeny
	 */
	public static final String NAME_INVOICE = "INVOICE";

	/**
	 * 
	 */
	private InvoiceViewHandler invoiceViewHandler;

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelInvoiceNew(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
		invoiceViewHandler = new InvoiceViewHandler(avdeling, applUser,
				isReadOnly);
		runInWheel = true;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
	}

	/**
	 * Klikk med mus i tabell. Enabler tabell.
	 * 
	 * @param evt
	 */
	@Override
	protected void tableDataMouseClicked(MouseEvent evt) {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		WindowInterface window = new JInternalFrameAdapter(departmentFrame);
		invoiceViewHandler.refresh(window);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_INVOICE;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Fakturaer";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		doRefresh = true;
		return invoiceViewHandler.getLoadClass(currentAvdeling,
				departmentFrame, this);
	}
}
