package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;

import no.ica.fraf.dao.SikkerhetTypeDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.YesNoComboable;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Sikkerhet;
import no.ica.fraf.model.SikkerhetType;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Panel for sikkerhet
 * 
 * @author abr99
 * 
 */
public final class PanelSecurity extends FrafPanel<Sikkerhet> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO for sikkerhettype
	 */
	static SikkerhetTypeDAO sikkerhetTypeDAO = (SikkerhetTypeDAO) ModelUtil
			.getBean("sikkerhetTypeDAO");

	/**
	 * Navn på panel som brukes ved høyreklikk i panel
	 */
	public static final String NAME_SECURITY = "SECURITY";

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 *            frame som har dette panelet
	 * @param avdeling
	 *            gjeldende avdeling
	 * @param applUser
	 *            bruker
	 * @param isReadOnly
	 *            dersom panelet skal være read only
	 */
	public PanelSecurity(DepartmentFrame aInternalFrame, Avdeling avdeling,
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
				JPanel panelSecurityButtons = new JPanel();
				this.add(panelSecurityButtons, BorderLayout.NORTH);
				panelSecurityButtons.setPreferredSize(new java.awt.Dimension(
						10, 30));
				{
					JButton buttonAddSecurity = new JButton();
					buttonAddSecurity.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelSecurityButtons.add(buttonAddSecurity);
					buttonAddSecurity.setText("Legg til sikkerhet");
					buttonAddSecurity.setPreferredSize(new java.awt.Dimension(
							155, 20));
					buttonAddSecurity.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddSecurityActionPerformed(evt);
						}
					});
					buttonAddSecurity.setEnabled(!readOnly);

				}
				{
					JButton buttonRemoveSecurity = new JButton();
					buttonRemoveSecurity
							.setIcon(IconEnum.ICON_DELETE.getIcon());
					panelSecurityButtons.add(buttonRemoveSecurity);
					buttonRemoveSecurity.setText("Fjern sikkerhet");
					buttonRemoveSecurity
							.setPreferredSize(new java.awt.Dimension(150, 20));
					buttonRemoveSecurity
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									buttonRemoveSecurityActionPerformed(evt);
								}
							});
					buttonRemoveSecurity.setEnabled(!readOnly);
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
		addSecurity();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeSecurity();

	}

	/**
	 * Knapp legg til sikkrehet er trykket
	 * 
	 * @param evt
	 */
	void buttonAddSecurityActionPerformed(ActionEvent evt) {
		addSecurity();
	}

	/**
	 * Knapp fjern sikkerhet er trykket
	 * 
	 * @param evt
	 */
	void buttonRemoveSecurityActionPerformed(ActionEvent evt) {
		removeSecurity();
	}

	/**
	 * Legger til ny sikkerhet
	 */
	private void addSecurity() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		Sikkerhet sikkerhet = new Sikkerhet();
		sikkerhet.setAvdeling(currentAvdeling);

		objectTableModel.addRow(sikkerhet);
		addedObjects.add(sikkerhet);
		currentAvdeling.addSikkerhet(sikkerhet);
	}

	/**
	 * Fjerner valgt sikkerhet
	 */
	private void removeSecurity() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette sikkeret?")) {
			return;
		}
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil",
					"Det må velges en sikkerhet");
			return;
		}

		Sikkerhet sikkerhet = objectTableModel.deleteRow(index);
		currentAvdeling.removeSikkerhet(sikkerhet);
		logRemovedObject(sikkerhet);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		Vector<Integer> tmpVector = new Vector<Integer>();
		tmpVector.add(new Integer(1));
		return tmpVector;
	}

	/**
	 * Klasse som håndterer lasting av sikkerhet
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadSecurity implements Threadable {

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
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_SECURITY });
				Set<Sikkerhet> sikkerhets = currentAvdeling.getSikkerhets();
				objectTableModel.setData(sikkerhets);
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
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		String[] columnNames = { "Type", "Verdi", "Tinglyst", "Kommentar" };
		String[] methods = { "SikkerhetType", "SikkerhetVerdi", "Tinglyst",
				"Kommentar" };
		Class[] colParams = { SikkerhetType.class, String.class,
				YesNoInteger.class, String.class };
		return new ObjectTableDef(columnNames, methods, colParams);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// type
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);
		col
				.setCellEditor(GuiUtil.createComboCellEditor(sikkerhetTypeDAO,
						null));

		// Verdi
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Tinglyst
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);
		col.setCellEditor(GuiUtil.createComboCellEditor(new YesNoComboable(),
				null));

		// Kommentar
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(200);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_SECURITY;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Sikkerhet";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadSecurity();
	}
}
