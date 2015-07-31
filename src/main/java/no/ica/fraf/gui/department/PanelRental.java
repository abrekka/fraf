package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.fenistra.FenistraInvoiceFrame;
import no.ica.fraf.gui.model.Column;
import no.ica.fraf.gui.model.DateField;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.SpeiletTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BaseObject;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.util.AvdelingLoggUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel som viser innleie fra Fenistra
 */
public final class PanelRental extends FrafPanel<LkKontraktobjekter> {
	/**
	 * Klasse som git comboboks verdier med betingelser som er speilet
	 */
	AvdelingBetingelseSpeilet avdelingBetingelseSpeilet = new AvdelingBetingelseSpeilet();

	/**
	 * Inneholder objekter som er lagt til for logging
	 */
	Hashtable<Integer, BaseObject> addedRentalObjects = new Hashtable<Integer, BaseObject>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	SpeiletTableModel rentalTableModel;

	/**
	 * DAO for kontrakter
	 */
	LkKontraktobjekterDAO lkKontraktobjekterDAO = (LkKontraktobjekterDAO) ModelUtil
			.getBean("lkKontraktobjekterDAO");

	/**
	 * DAO for frekvens
	 */
	AvregningFrekvensTypeDAO avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
			.getBean("avregningFrekvensTypeDAO");

	/**
	 * Navn på panel som brukes ved høyreklikkmeny
	 */
	public static final String NAME_RENTAL = "RENTAL";

	/**
	 * Kolonnenavn
	 */
	/**
	 * 
	 */
	JButton buttonOpen;

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelRental(DepartmentFrame aInternalFrame, Avdeling avdeling,
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
				JPanel panelNorth = new JPanel();
				this.add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonOpen = new JButton();
					buttonOpen.setEnabled(false);
					panelNorth.add(buttonOpen);
					buttonOpen.setText("Vis fakturaer");
					buttonOpen.setIcon(IconEnum.ICON_SHOW.getIcon());
					buttonOpen.setMnemonic(KeyEvent.VK_V);
					buttonOpen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonOpenActionPerformed(evt);
						}
					});
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
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette speiling?")) {
			return;
		}
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil",
					"Det må velges en betingelse");
			return;
		}

		SpeiletBetingelse speiletBetingelse = rentalTableModel
				.removeSpeiling(index);
		currentAvdeling.removeSpeiletBetingelse(speiletBetingelse);

		logRemovedObject(speiletBetingelse);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected TableModel getExcelTableModel() {
		SpeiletTableModel rentalTableModelExcel = new SpeiletTableModel();
		rentalTableModelExcel.setData(rentalTableModel.getData(),
				currentAvdeling);
		return rentalTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		Vector<Integer> tmpVector = new Vector<Integer>();
		tmpVector.add(new Integer(4));
		tmpVector.add(new Integer(5));
		return tmpVector;
	}

	/**
	 * Åpner fakturaer fra Fenistra
	 * 
	 * @param evt
	 */
	void buttonOpenActionPerformed(ActionEvent evt) {
		showInvoices();

	}

	/**
	 * Viser fakturaer fra Fenistra
	 */
	private void showInvoices() {
		if (tableData.getSelectedRow() < 0) {
			GuiUtil.showErrorMsgFrame(departmentFrame, "Feil",
					"Det må velges en betingelse");
			return;
		}
		LkKontraktobjekter lkKontraktobjekter = (LkKontraktobjekter) rentalTableModel
				.getObjectAtIndex(tableData.getSelectedRow());
		FenistraInvoiceFrame fenistraInvoiceFrame = new FenistraInvoiceFrame(
				lkKontraktobjekter);

		fenistraInvoiceFrame.setVisible(true);
		FrafMain.getInstance().getContentPane().add(fenistraInvoiceFrame);
		fenistraInvoiceFrame.setLocation(GuiUtil.getCenter(FrafMain
				.getInstance().getSize(), fenistraInvoiceFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(fenistraInvoiceFrame);
		try {
			fenistraInvoiceFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Kjøres ved klikk på tabell. Dersom dobbelklikk vises tilhørende fakturaer
	 * i Fenistra. Dersom ett klikk enables knapp for visning av fakturaer i
	 * Fenistra
	 * 
	 * @param evt
	 */
	@Override
	protected void tableDataMouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			GuiUtil.setWaitCursor(departmentFrame);
			showInvoices();
			GuiUtil.setDefaultCursor(departmentFrame);
		} else if (evt.getClickCount() == 1) {
			buttonOpen.setEnabled(true);
		}
	}

	/**
	 * Klasse som laster data i en egen tråd
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadData implements Threadable {

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
			String errorMsg = null;
			try {
				if (currentAvdeling != null) {
					avdelingDAO
							.loadLazy(
									currentAvdeling,
									new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_MIRROR });
					List<LkKontraktobjekter> kontrakter = getSpeiletData();

					rentalTableModel.setData(kontrakter, currentAvdeling);

					TableColumn col = tableData.getColumnModel().getColumn(6);
					col.setCellEditor(GuiUtil.createComboCellEditor(
							avdelingBetingelseSpeilet, currentAvdeling));
				}
			} catch (FrafException ex) {
				errorMsg = ex.getMessage();
			}
			buttonOpen.setEnabled(false);
			return errorMsg;
		}

		/**
		 * Finner speilede betingelser
		 * 
		 * @return speilede betingelser
		 * @throws FrafException
		 */
		private List<LkKontraktobjekter> getSpeiletData() throws FrafException {
			List<LkKontraktobjekter> kontrakter = null;
			// finner alle kontrakter for avdeling i Fenistra
			try {
				kontrakter = lkKontraktobjekterDAO.findByAvdnr(String
						.valueOf(currentAvdeling.getAvdnr()));
			} catch (RuntimeException e) {
				String msg;
				if (e.getMessage().indexOf("Cannot open") != -1) {
					msg = "Kunne ikke koble til Fenistra";
				} else {
					msg = e.getMessage();
				}
				throw new FrafException(msg);
			}

			if (kontrakter != null) {
				// henter ut speilede betingelser for avdeling
				Set<SpeiletBetingelse> speiletBetingelser = currentAvdeling
						.getSpeiletBetingelses();

				SpeiletBetingelse speiletBetingelse;

				// går gjennom alle kontrakter og setter avregningfrekvens
				for (LkKontraktobjekter lkKontraktobjekter : kontrakter) {
					lkKontraktobjekter
							.setAvregningFrekvensType(avregningFrekvensTypeDAO
									.findByTerminer(lkKontraktobjekter
											.getTerminer()));
					speiletBetingelse = findSpeiletBetingelse(
							speiletBetingelser, lkKontraktobjekter
									.getKontraktObjektId());
					lkKontraktobjekter.setSpeiletBetingelse(speiletBetingelse);
				}

			}
			return kontrakter;
		}

		/**
		 * Finner speilet betingelse for kontrakt
		 * 
		 * @param speiletBetingelser
		 * @param kontraktObjektId
		 * @return speilet betingelse for kontrakt
		 */
		private SpeiletBetingelse findSpeiletBetingelse(
				Set<SpeiletBetingelse> speiletBetingelser,
				Integer kontraktObjektId) {
			if (speiletBetingelser == null) {
				return null;
			}

			for (SpeiletBetingelse speiletBetingelse : speiletBetingelser) {
				if (speiletBetingelse != null
						&& speiletBetingelse.getKontraktObjektId().equals(
								kontraktObjektId)) {
					return speiletBetingelse;
				}
			}
			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(departmentFrame, "Feil", object
						.toString());
			}
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#beforeSave()
	 */
	@Override
	public void beforeSave() throws FrafException {
		Set<Integer> keys = addedRentalObjects.keySet();
		BaseObject baseObject = null;
		String errorString;

		for (Integer key : keys) {
			baseObject = addedRentalObjects.get(key);
			errorString = baseObject.validateObject();

			if (errorString != null) {
				throw new FrafException(errorString);
			}

			AvdelingLoggUtil
					.logg(currentApplUser, currentAvdeling, "Lagt til "
							+ baseObject.getObjectName() + ": "
							+ baseObject.toString());
		}
		addedRentalObjects.clear();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.ObjectModifyListener#objectModified(java.lang.Object,
	 *      no.ica.fraf.gui.model.Column)
	 */
	@Override
	public void objectModified(Object object, Column updateColumn) {
		super.objectModified(object, updateColumn);
		if (((BaseObject) object).getObjectId() == null) {
			if (currentAvdeling != null) {
				currentAvdeling.setModified(true);
			}
			addedRentalObjects.put(((SpeiletBetingelse) object)
					.getKontraktObjektId(), (BaseObject) object);
		}
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
		rentalTableModel = new SpeiletTableModel();
		if (readOnly) {
			rentalTableModel.setEditable(false);
		}
		rentalTableModel.addObjectModifyListener(thisPointer);

		tableData.setModel(rentalTableModel);

		// Betingelse
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(200);

		// Terminer
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(90);
		// col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Fra dato
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(90);

		// Til dato
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(90);

		// Årsbeløp
		col = tableData.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);

		// Terminbeløp
		col = tableData.getColumnModel().getColumn(5);
		col.setPreferredWidth(100);

		// Speilet betingelse
		col = tableData.getColumnModel().getColumn(6);
		col.setPreferredWidth(200);

		// Speilingsdato
		col = tableData.getColumnModel().getColumn(7);
		col.setPreferredWidth(100);
		col.setCellEditor(new DateField());

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_RENTAL;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Innleie";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadData();
	}
}
