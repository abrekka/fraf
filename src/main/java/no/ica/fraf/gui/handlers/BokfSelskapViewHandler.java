package no.ica.fraf.gui.handlers;

import java.awt.Dimension;
import java.math.BigDecimal;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.edit.EditBokfSelskapView;
import no.ica.fraf.gui.model.BokfSelskapModel;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;

/**
 * Håndterer bokføringsselskap
 * 
 * @author abr99
 * 
 */
public class BokfSelskapViewHandler extends
		AbstractViewHandler<BokfSelskap, BokfSelskapModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param aManager
	 * @param aApplUserType
	 */
	public BokfSelskapViewHandler(OverviewManager<BokfSelskap> aManager,
			ApplUserType aApplUserType) {
		super("Selskap", aManager, aApplUserType);
	}

	/**
	 * Lager tekstfelt for selskapnavn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldSelskapnavn(
			PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_SELSKAPNAVN));
	}

	/**
	 * Lager tekstfelt for fakturanr
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFakturanr(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_FAKTURA_NR_STRING));
	}

	/**
	 * Lager tekstfelt for navn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldNavn(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_NAVN));
	}

	/**
	 * Lager tekstfelt for adresse1
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAdresse1(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_ADRESSE1));
	}

	/**
	 * Lager tekstfelt for adresse2
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAdresse2(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_ADRESSE2));
	}

	/**
	 * Lager tekstfelt for adresse3
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAdresse3(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_ADRESSE3));
	}

	/**
	 * Lager tekstfelt for telefon
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldTelefon(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_TELEFON));
	}

	/**
	 * Lager tekstfelt for fax
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldTelefax(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_TELEFAX));
	}

	/**
	 * Lager tekstfelt for orgNr
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldOrgNr(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_ORG_NR));
	}

	/**
	 * Lager tekstfelt for kontonr
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldKontonr(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_KONTONR));
	}

	/**
	 * Lager tekstfelt for lokasjonsnr
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldLokasjonsnr(
			PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_LOKASJONSNR));
	}

	/**
	 * Lager tekstfelt for filialkonto
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFilialKonto(
			PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_FILIAL_KONTO));
	}

	/**
	 * Lager tekstfelt for postnr
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldPostnr(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_POSTNR));
	}

	/**
	 * Lager tekstfelt for poststed
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldPoststed(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_POSTSTED));
	}

	/**
	 * Lager tekstfelt for momsid
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldMomsid(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_MOMSID));
	}

	/**
	 * Lager tekstfelt for orgnr
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldorgnr(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(BokfSelskapModel.PROPERTY_ORGNR));
	}

	/**
	 * Lager komboboks for til ps
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxTilPs(PresentationModel presentationModel) {
		return new JComboBox(
				new ComboBoxAdapter(
						new Object[] { "Ja", "Nei" },
						presentationModel
								.getBufferedModel(BokfSelskapModel.PROPERTY_TIL_PS_STRING)));
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getClassName()
	 */
	@Override
	public String getClassName() {
		return "BokfSelskap";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getAddRemoveString()
	 */
	@Override
	public String getAddRemoveString() {
		return "selskap";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#hasWriteAccess()
	 */
	@Override
	public Boolean hasWriteAccess() {
		if (applUserType.getTypeName().equalsIgnoreCase("Administrator")) {
			return true;
		}
		return false;
	}

	/**
	 * Åpner for editering
	 * 
	 * @param object
	 * @param searching
	 */
	@Override
	protected void openEditView(BokfSelskap object, boolean searching) {

		EditBokfSelskapView bokfSelskapView = new EditBokfSelskapView(
				searching, object, this);
		WindowInterface dialog = new JDialogAdapter(new JDialog(FrafMain
				.getInstance(), "Selskap", true));
		dialog.setName("EditBokfSelskapView");
		dialog.add(bokfSelskapView.buildPanel(dialog));
		dialog.pack();
		GuiUtil.locateOnScreenCenter(dialog);
		dialog.setVisible(true);

		if (searching) {
			updateViewList(object);
		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getNewObject()
	 */
	@Override
	public BokfSelskap getNewObject() {
		return new BokfSelskap();
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableModel()
	 */
	@Override
	public TableModel getTableModel() {
		return new BokfSelskapTableModel(objectSelectionList);
	}

	/**
	 * Lagrer
	 * 
	 * @param object
	 * @param window
	 */
	@Override
	public void saveObject(BokfSelskapModel object, WindowInterface window) {
		BokfSelskap bokfSelskap = object.getObject();
		int index = objectList.indexOf(bokfSelskap);

		overviewManager.saveObject(bokfSelskap);

		if (index < 0) {
			objectList.add(bokfSelskap);
			noOfObjects++;
		} else {
			objectSelectionList.fireContentsChanged(index, index);
		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Selskap";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getWindowSize()
	 */
	@Override
	public Dimension getWindowSize() {
		return new Dimension(900, 300);
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableWidth()
	 */
	@Override
	public String getTableWidth() {
		return "220dlu";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#setColumnWidth(org.jdesktop.swingx.JXTable)
	 */
	@Override
	public void setColumnWidth(JXTable table) {
		// Selskapnavn
		table.getColumnExt(0).setPreferredWidth(70);
		// Fakturanr
		table.getColumnExt(1).setPreferredWidth(70);
		// Til PS
		table.getColumnExt(2).setPreferredWidth(50);
		// Navn
		table.getColumnExt(3).setPreferredWidth(100);
		// Adresse1
		table.getColumnExt(4).setPreferredWidth(80);
		// Adresse2
		table.getColumnExt(5).setPreferredWidth(130);
		// Adresse3
		table.getColumnExt(6).setPreferredWidth(120);
		// Telefon
		table.getColumnExt(7).setPreferredWidth(90);
		// Telefax
		table.getColumnExt(8).setPreferredWidth(90);
		// Org. nr
		table.getColumnExt(9).setPreferredWidth(90);
		// Kontonr
		table.getColumnExt(10).setPreferredWidth(80);
		// Lokasjonsnr
		table.getColumnExt(11).setPreferredWidth(90);

	}

	/**
	 * @param object
	 * @param presentationModel
	 * @param window
	 * @return feilmelding
	 */
	@Override
	public String checkSaveObject(BokfSelskapModel object,
			PresentationModel presentationModel, WindowInterface window) {
		return null;
	}

	/**
	 * @param object
	 * @return feilmelding
	 */
	@Override
	public String checkDeleteObject(BokfSelskap object) {
		return null;
	}

	/**
	 * Tabellmodell for bokføringsselskap
	 * 
	 * @author abr99
	 * 
	 */
	private static final class BokfSelskapTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Selskapnavn", "Fakturanr",
				"Til PS", "Navn", "Adresse1", "Adresse2", "Adresse3",
				"Telefon", "Telefax", "Org. nr", "Kontonr", "Lokasjonsnr",
				"Postnr", "Poststed", "Orgnr", "Momsid", "Filialkonto" };

		/**
		 * @param listModel
		 */
		BokfSelskapTableModel(ListModel listModel) {
			super(listModel, COLUMNS);
		}

		/**
		 * Henter verdi
		 * 
		 * @param rowIndex
		 * @param columnIndex
		 * @return verdi
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			BokfSelskap bokfSelskap = (BokfSelskap) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return bokfSelskap.getSelskapNavn();
			case 1:
				return bokfSelskap.getFakturaNr();
			case 2:
				return GuiUtil.convertNumberToBoolean(bokfSelskap.getTilPs());
			case 3:
				return bokfSelskap.getNavn();
			case 4:
				return bokfSelskap.getAdresse1();
			case 5:
				return bokfSelskap.getAdresse2();
			case 6:
				return bokfSelskap.getAdresse3();
			case 7:
				return bokfSelskap.getTelefon();
			case 8:
				return bokfSelskap.getTelefax();
			case 9:
				return bokfSelskap.getOrgNr();
			case 10:
				return bokfSelskap.getKontonr();
			case 11:
				return bokfSelskap.getLokasjonsnr();
			case 12:
				return bokfSelskap.getPostnr();
			case 13:
				return bokfSelskap.getPoststed();
			case 14:
				return bokfSelskap.getOrgnr();
			case 15:
				return bokfSelskap.getMomsid();
			case 16:
				return bokfSelskap.getFilialKonto();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

		/**
		 * @see javax.swing.table.TableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
				return String.class;
			case 1:
				return BigDecimal.class;
			case 2:
				return Boolean.class;
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

}
