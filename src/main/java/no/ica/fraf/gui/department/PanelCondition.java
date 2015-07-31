package no.ica.fraf.gui.department;

import java.util.Vector;

import javax.swing.table.TableModel;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.gui.handlers.ConditionViewHandler;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.util.ModelUtil;

/**
 * Panel som viser betingelser
 * 
 * @author abr99
 * 
 */
public final class PanelCondition extends FrafPanel<AvdelingBetingelse> {
	/**
	 * 
	 */
	private ConditionViewHandler conditionViewHandler;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Navn på panel som brukes ved høyrekilkkmeny
	 */
	public static final String NAME_CONDITION = "CONDITION";

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelCondition(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
		thisPointer = this;
		AvdelingBetingelseDAO avdelingBetingelseDAO = (AvdelingBetingelseDAO) ModelUtil
				.getBean("avdelingBetingelseDAO");
		conditionViewHandler = new ConditionViewHandler(avdelingBetingelseDAO,
				applUser.getApplUserType(), currentAvdeling, applUser,readOnly);
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
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		WindowInterface window = new JInternalFrameAdapter(departmentFrame);
		conditionViewHandler.refresh(window);

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
		return NAME_CONDITION;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Betingelser";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		doRefresh = true;
		return conditionViewHandler.getLoadClass(currentAvdeling,
				departmentFrame, this);
	}

}
