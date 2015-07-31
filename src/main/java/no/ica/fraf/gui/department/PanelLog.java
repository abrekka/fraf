package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingLogg;
import no.ica.fraf.util.AvdelingLoggUtil;
import no.ica.fraf.util.GuiUtil;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Panel som viser logg for avdeling
 * 
 * @author abr99
 * 
 */
public final class PanelLog extends FrafPanel<AvdelingLogg> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Navn på panel
	 */
	public static final String NAME_LOG = "LOG";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "Bruker", "Dato", "Kommentar" };

	/**
	 * Kolonnnemetoder
	 */
	static final String[] METHODS = { "ApplUser", "LoggDato", "Kommentar" };

	/**
	 * Klassetyper for kolonner
	 */
	static final Class[] PARAMS = { ApplUser.class, Date.class, String.class };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.DATE, ColumnFormatEnum.NONE };

	/**
	 * Kosntruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelLog(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				JPanel panelLogButtons = new JPanel();
				this.add(panelLogButtons, BorderLayout.NORTH);
				panelLogButtons
						.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					JButton buttonAddLog = new JButton();
					buttonAddLog.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelLogButtons.add(buttonAddLog);
					buttonAddLog.setText("Legg til kommentar");
					buttonAddLog.setPreferredSize(new java.awt.Dimension(170,
							20));
					buttonAddLog.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddLogActionPerformed(evt);
						}
					});
					buttonAddLog.setEnabled(!readOnly);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addAvdelingLog();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
	}

	/**
	 * Mauell logging
	 * 
	 * @param evt
	 */
	void buttonAddLogActionPerformed(ActionEvent evt) {
		addAvdelingLog();
	}

	/**
	 * Legger til logg for avdeling
	 */
	private void addAvdelingLog() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}

		String comment = GuiUtil.showInputFrame(this, "Kommentar",
				"Kommentar:", "");
		AvdelingLogg avdelingLogg = AvdelingLoggUtil.logg(currentApplUser,
				currentAvdeling, comment);
		objectTableModel.addRow(avdelingLogg);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef logTableDef = new ObjectTableDef(COLUMN_NAMES, METHODS,
				PARAMS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<AvdelingLogg> logTableModelExcel = new ObjectTableModel<AvdelingLogg>(logTableDef);
		logTableModelExcel.setData(objectTableModel.getData());

		return logTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * Klasse som håndterer lasting av logg
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadLog implements Threadable {

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
			if (currentAvdeling != null) {
				avdelingDAO
						.loadLazy(
								currentAvdeling,
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_LOG });
				Set<AvdelingLogg> avdelingLoggs = currentAvdeling
						.getAvdelingLoggs();
				objectTableModel.setData(avdelingLoggs);
			}
			return Boolean.TRUE;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Bruker
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);

		// Dato
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Kommentar
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(440);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_LOG;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Logg";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadLog();
	}
}
