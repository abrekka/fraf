package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.dao.SpeiletKostnadDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.SpeiletKostnad;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel som viser speilede kostnader.
 * 
 * @author abr99
 * 
 */
public final class PanelMirrorCondition extends FrafPanel<SpeiletKostnad>  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kolonnenavn
	 */
	private static final String[] COLUMN_NAMES = { "Betingelse", "Fra dato",
			"Til dato", "Beløp", "Fakturabunt" };

	/**
	 * 
	 */
	private JButton buttonDeleteMirror;

	/**
	 * Kolonnemetoder
	 */
	private static final String[] METHODS = {
			"SpeiletBetingelse.AvdelingBetingelse", "FraDato", "TilDato",
			"Belop", "FakturaBunt" };

	/**
	 * Klassetyper for kolonner
	 */
	private static final Class[] PARAMS = { AvdelingBetingelse.class,
			Date.class, Date.class, BigDecimal.class, Bunt.class };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_GUI = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE };

	/**
	 * Kolonneformat for excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * Navn på panel som brukes ved høyreklikk i tabell
	 */
	public static final String NAME_MIRROR = "MIRROR";

	/**
	 * DAo for speilet kostnad
	 */
	SpeiletKostnadDAO speiletKostnadDAO = (SpeiletKostnadDAO) ModelUtil
			.getBean("speiletKostnadDAO");

	/**
	 * Kosntruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelMirrorCondition(DepartmentFrame aInternalFrame,
			Avdeling avdeling, ApplUser applUser, boolean isReadOnly) {
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
				panelNorth.setPreferredSize(new java.awt.Dimension(40, 40));
				{
					buttonDeleteMirror = new JButton();
					buttonDeleteMirror.setEnabled(false);
					panelNorth.add(buttonDeleteMirror);
					buttonDeleteMirror.setText("Fjern speilet kostnad");
					buttonDeleteMirror.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonDeleteMirrorActionPerformed(evt);
						}
					});
					buttonDeleteMirror.setIcon(IconEnum.ICON_DELETE.getIcon());
					buttonDeleteMirror.setEnabled(!readOnly);
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
		deleteMirrorCost();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
	}

	/**
	 * Kostnad skal slettes
	 * 
	 * @param evt
	 */
	void buttonDeleteMirrorActionPerformed(ActionEvent evt) {
		deleteMirrorCost();
	}

	/**
	 * Sletter valgt kostnad
	 */
	private void deleteMirrorCost() {
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "Det må velges en kostnad");
			return;
		}

		SpeiletKostnad speiletKostnad = (SpeiletKostnad) objectTableModel
				.getObjectAtIndex(index);

		if (speiletKostnad.getFakturaBunt() != null) {
			GuiUtil.showErrorMsgFrame(this, "Feil",
					"Kan ikke slette kostnad som har blitt fakturert!");
			return;
		}

		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette kostnad?")) {
			return;
		}
		speiletKostnadDAO.removeSpeiletKostnad(speiletKostnad
				.getSpeiletKostnadId());
		objectTableModel.deleteRow(index);
	}

	/**
	 * Kjøres ved klikk i tablle, enabler sletteknapp
	 * 
	 * @param evt
	 */
	void tableMirrorMouseClicked(MouseEvent evt) {
		if (!readOnly) {
			buttonDeleteMirror.setEnabled(true);
		}
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef mirrorTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<SpeiletKostnad> mirrorTableModelExcel = new ObjectTableModel<SpeiletKostnad>(
				mirrorTableDef);
		mirrorTableModelExcel.setData(objectTableModel.getData());
		return mirrorTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		Vector<Integer> tmpVector = new Vector<Integer>();
		tmpVector.add(new Integer(3));
		return tmpVector;
	}

	/**
	 * Klasse som håndterer lasting av speilede kostnader
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadMirror implements Threadable {

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
			List<SpeiletKostnad> mirrorList = speiletKostnadDAO
					.findByDepartment(currentAvdeling);

			objectTableModel.setData(mirrorList);
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
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS,
				FORMAT_COLUMNS_GUI);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Betingelse
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(200);

		// Fra dato
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Til dato
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Beløp
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(120);

		// Faktura
		col = tableData.getColumnModel().getColumn(4);
		col.setPreferredWidth(120);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_MIRROR;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Speilede betingelser";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadMirror();
	}
}
