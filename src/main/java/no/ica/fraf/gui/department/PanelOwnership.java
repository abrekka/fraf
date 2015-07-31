package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Eierforhold;
import no.ica.fraf.util.GuiUtil;

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
 * Panel for administrering av eierskap
 * 
 * @author abr99
 * 
 */
public final class PanelOwnership extends FrafPanel<Eierforhold> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panelnavn
	 */
	public static final String NAME_OWNERSHIP = "OWNERSHIP";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "Navn", "Fødselsdato/Org nr",
			"Antall aksjer", "Pålydende" };

	/**
	 * Kolonnemetoder
	 */
	static final String[] METHODS = { "Navn", "FodseldatoOrgNr",
			"AntallAksjer", "Paalydende" };

	/**
	 * Klassetyper for kolonner
	 */
	static final Class[] PARAMS = { String.class, String.class, Integer.class,
			BigDecimal.class };

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelOwnership(DepartmentFrame aInternalFrame, Avdeling avdeling,
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
				JPanel panelOwnershipButtons = new JPanel();
				this.add(panelOwnershipButtons, BorderLayout.NORTH);
				panelOwnershipButtons.setPreferredSize(new java.awt.Dimension(
						10, 30));
				{
					JButton buttonAddOwnership = new JButton();
					buttonAddOwnership.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelOwnershipButtons.add(buttonAddOwnership);
					buttonAddOwnership.setText("Legg til eier");
					buttonAddOwnership.setPreferredSize(new java.awt.Dimension(
							125, 20));
					buttonAddOwnership.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddOwnershipActionPerformed(evt);
						}
					});
					buttonAddOwnership.setEnabled(!readOnly);

				}
				{
					JButton buttonRemoveOwnership = new JButton();
					buttonRemoveOwnership.setIcon(IconEnum.ICON_DELETE
							.getIcon());
					panelOwnershipButtons.add(buttonRemoveOwnership);
					buttonRemoveOwnership.setText("Slette eier");
					buttonRemoveOwnership
							.setPreferredSize(new java.awt.Dimension(125, 20));
					buttonRemoveOwnership
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									buttonRemoveOwnershipActionPerformed(evt);
								}
							});
					buttonRemoveOwnership.setEnabled(!readOnly);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fjern eierskap
	 * 
	 * @param evt
	 */
	void buttonRemoveOwnershipActionPerformed(ActionEvent evt) {
		removeOwnership();
	}

	/**
	 * Legg til eierskap
	 * 
	 * @param evt
	 */
	void buttonAddOwnershipActionPerformed(ActionEvent evt) {
		addOwnership();
	}

	/**
	 * Legger til eierskap
	 */
	private void addOwnership() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		Eierforhold eierforhold = new Eierforhold();
		eierforhold.setAvdeling(currentAvdeling);

		objectTableModel.addRow(eierforhold);
		addedObjects.add(eierforhold);

		currentAvdeling.addEierforhold(eierforhold);
	}

	/**
	 * Fjerner eierskap
	 */
	private void removeOwnership() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette eierforhold?")) {
			return;
		}
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "Det må velges en eier");
			return;
		}

		Eierforhold eierforhold = objectTableModel.deleteRow(index);
		currentAvdeling.removeEierforhold(eierforhold);
		logRemovedObject(eierforhold);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addOwnership();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeOwnership();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		Vector<Integer> tmpVector = new Vector<Integer>();
		tmpVector.add(new Integer(1));
		tmpVector.add(new Integer(2));
		return tmpVector;
	}

	/**
	 * Klasse som håndterer lasting av eierskap
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadOwnership implements Threadable {

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
			String errorString = null;
			try {
				if (currentAvdeling != null) {
					avdelingDAO
							.loadLazy(
									currentAvdeling,
									new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_OWNERSHIP });
					Set<Eierforhold> ownerships = currentAvdeling
							.getEierforholds();
					objectTableModel.setData(ownerships);
				}
			} catch (RuntimeException e) {
				errorString = e.getMessage();
				e.printStackTrace();
			}
			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(departmentFrame, "Feil",
						(String) object);
			}
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
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Navn
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(150);

		// Fødselsdato
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(110);
		// col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Antall aksjer
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);

		// Pålydende
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_OWNERSHIP;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Eierskap";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadOwnership();
	}

}
