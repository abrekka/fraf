package no.ica.fraf.gui.handlers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.edit.EditBetingelseTypeView;
import no.ica.fraf.gui.model.BetingelseTypeModel;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Mva;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.DataListUtil;
import no.ica.fraf.util.DataListUtilFactory;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Filter;
import org.jdesktop.swingx.decorator.FilterPipeline;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.PatternFilter;
import org.jdesktop.swingx.decorator.PatternHighlighter;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Håndterer betingelsetyper
 * 
 * @author abr99
 * 
 */
public class BetingelseViewHandler extends
		AbstractViewHandler<BetingelseType, BetingelseTypeModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private List<Mva> mvaList;

	/**
	 * 
	 */
	private List<BetingelseGruppe> betingelseGruppeList;

	/**
	 * 
	 */
	private List<BokfSelskap> selskapList;

	/**
	 * 
	 */
	private List<Chain> kjedeList;

	/**
	 * 
	 */
	private List<AvregningFrekvensType> frekvensList;

	/**
	 * 
	 */
	private List<AvregningType> avregningList;

	/**
	 * 
	 */
	private MvaDAO mvaDAO;

	/**
	 * 
	 */
	private BetingelseGruppeDAO betingelseGruppeDAO;

	/**
	 * 
	 */
	private AvregningFrekvensTypeDAO avregningFrekvensTypeDAO;

	/**
	 * 
	 */
	private AvregningTypeDAO avregningTypeDAO;

	/**
	 * 
	 */
	private AvdelingBetingelseDAO avdelingBetingelseDAO;

	/**
	 * 
	 */
	private BetingelseGruppeEnum groupEnum;
	private JCheckBox checkBoxFilter;

	/**
	 * @param aManager
	 * @param aApplUserType
	 * @param gruppeEnum
	 */
	public BetingelseViewHandler(OverviewManager<BetingelseType> aManager,
			ApplUserType aApplUserType, BetingelseGruppeEnum gruppeEnum) {
		super("Betingelser", aManager, aApplUserType);
		groupEnum = gruppeEnum;
		initManagers();
		mvaList = mvaDAO.findAll();
		if (groupEnum != BetingelseGruppeEnum.ADMIN) {
			betingelseGruppeList = new ArrayList<BetingelseGruppe>();
			betingelseGruppeList.add(betingelseGruppeDAO.findByName(groupEnum
					.getGroupName()));
		} else {
			betingelseGruppeList = betingelseGruppeDAO.findAll();
		}
		DataListUtil dataListUtil=DataListUtilFactory.getInstance(new ModelUtil());
		selskapList = dataListUtil.getBokfSelskaper();
		kjedeList = dataListUtil.getKjeder();
		frekvensList = avregningFrekvensTypeDAO.findAll();
		avregningList = avregningTypeDAO.findAll();
	}

	/**
	 * Initierer managere
	 */
	private void initManagers() {
		avdelingBetingelseDAO = (AvdelingBetingelseDAO) ModelUtil
				.getBean("avdelingBetingelseDAO");
		mvaDAO = (MvaDAO) ModelUtil.getBean("mvaDAO");
		betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
				.getBean("betingelseGruppeDAO");
		avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
				.getBean("avregningFrekvensTypeDAO");
		avregningTypeDAO = (AvregningTypeDAO) ModelUtil
				.getBean("avregningTypeDAO");
	}
	
	public JCheckBox getCheckBoxFilter(){
		checkBoxFilter = new JCheckBox(new FilterAction());
		filterTable();
		return checkBoxFilter;
	}

	private void filterTable() {
		Filter filter = new PatternFilter("Nei",Pattern.CASE_INSENSITIVE, 27);
		FilterPipeline filterPipeline = new FilterPipeline(new Filter[]{filter});
		table.setFilters(filterPipeline);
	}

	/**
	 * Lager tekstfelt for kode
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldKode(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_BETINGELSE_TYPE_KODE));
		textField.setName("TextFieldKode");
		return textField;
	}

	/**
	 * Lager tekstfelt for navn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldNavn(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_BETINGELSE_NAVN));
		textField.setName("TextFieldNavn");
		return textField;
	}

	/**
	 * Lager tekstfelt for konto
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldInntektskontoE(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_INNTEKTSKONTO_E));
		textField.setName("TextFieldInntektskontoE");
		return textField;
	}

	public JTextField getTextFieldInntektskontoF(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_INNTEKTSKONTO_F));
		textField.setName("TextFieldInntektskontoF");
		return textField;
	}

	/**
	 * Lager tekstfelt for xml konto
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldXmlKontoE(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_KONTO_E));
		textField.setName("TextFieldXmlKontoE");
		return textField;
	}

	public JTextField getTextFieldXmlKontoF(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_KONTO_F));
		textField.setName("TextFieldXmlKontoF");
		return textField;
	}

	/**
	 * Lager tekstfelt for fakturatekst
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFakturaTekst(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_FAKTURA_TEKST));
		textField.setName("TextFieldFakturaTekst");
		return textField;
	}

	/**
	 * Lager tekstfelt for rekkefølge
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldRekkefolge(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_REKKEFOLGE));
		textField.setName("TextFieldRekkefolge");
		return textField;
	}

	/**
	 * Lager tekstfelt for sats
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldSats(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_SATS));
		textField.setName("TextFieldSats");
		return textField;
	}

	/**
	 * Lager tekstfelt for beløp
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldBelop(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_BELOP));
		textField.setName("TextFieldBelop");
		return textField;
	}

	/**
	 * Lager tekstfelt for stykkpris
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldBelopPrStk(PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_BELOP_PR_STK));
		textField.setName("TextFieldBelopPrStk");
		return textField;
	}

	/**
	 * Lager tekstfelt for bokføringsavdeling
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldBokfAvdelingE(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_BOKF_AVDELING_E));
		textField.setName("TextFieldBokfAvdelingE");
		return textField;
	}
	public JTextField getTextFieldBokfAvdelingF(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_BOKF_AVDELING_F));
		textField.setName("TextFieldBokfAvdelingF");
		return textField;
	}

	/**
	 * Lager tekstfelt for vatusetype
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldVatusetypeE(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_VATUSETYPE_E));
		textField.setName("TextFieldVatusetypeE");
		return textField;
	}

	public JTextField getTextFieldVatusetypeF(
			PresentationModel presentationModel) {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_VATUSETYPE_F));
		textField.setName("TextFieldVatusetypeF");
		return textField;
	}

	/**
	 * Lager komboboks for mvakode
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxMvaKodeE(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(mvaList, presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_MVA_E)));
		comboBox.setName("ComboBoxMvaKodeE");
		return comboBox;
	}

	public JComboBox getComboBoxMvaKodeF(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(mvaList, presentationModel
						.getBufferedModel(BetingelseTypeModel.PROPERTY_MVA_F)));
		comboBox.setName("ComboBoxMvaKodeF");
		return comboBox;
	}

	/**
	 * Lager komboboks for xml mvakode
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxXmlMvaKodeE(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						mvaList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_MVA_E)));
		comboBox.setName("ComboBoxXmlMvaKodeE");
		return comboBox;
	}

	public JComboBox getComboBoxXmlMvaKodeF(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						mvaList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_MVA_F)));
		comboBox.setName("ComboBoxXmlMvaKodeF");
		return comboBox;
	}

	/**
	 * Lager komboboks for bokføringsselskap
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxSelskap(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						selskapList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_BOKF_SELSKAP)));
		comboBox.setName("ComboBoxSelskap");
		return comboBox;
	}

	/**
	 * Lager kombobks for kjede
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxKjede(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						kjedeList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_CHAIN)));
		comboBox.setName("ComboBoxKjede");
		return comboBox;
	}

	/**
	 * Lager komboboks for termin
	 * 
	 * @param presentationModel
	 * @return kombobks
	 */
	public JComboBox getComboBoxTermin(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						frekvensList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_AVREGNING_FREKVENS_TYPE)));
		comboBox.setName("ComboBoxTermin");
		return comboBox;
	}

	/**
	 * Lager komboboks for betingelsegruppe
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxBetingelseGruppe(
			PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						betingelseGruppeList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_BETINGELSE_GRUPPE)));
		comboBox.setName("ComboBoxBetingelseGruppe");
		return comboBox;
	}

	/**
	 * Lager komboboks for avregning
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxAvregning(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						avregningList,
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_AVREGNING_TYPE)));
		comboBox.setName("ComboBoxAvregning");
		return comboBox;
	}

	/**
	 * Lager komboboks for link til avtale
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxLinkAvtale(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						new Object[] { "Nei", "Ja" },
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_LINK_AVTALE_STRING)));
		comboBox.setName("ComboBoxLinkAvtale");
		return comboBox;
	}

	public JComboBox getComboBoxInaktiv(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						new Object[] { "Nei", "Ja" },
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_INAKTIV_STRING)));
		comboBox.setName("ComboBoxInaktiv");
		return comboBox;
	}

	/**
	 * Lager komboboks for mvatype
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxMvatypeE(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						new Object[] { null, "IN", "UT" },
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_MVATYPE_E)));
		comboBox.setName("ComboBoxMvatypeE");
		return comboBox;
	}

	public JComboBox getComboBoxMvatypeF(PresentationModel presentationModel) {
		JComboBox comboBox = BasicComponentFactory
				.createComboBox(new SelectionInList(
						new Object[] { null, "IN", "UT" },
						presentationModel
								.getBufferedModel(BetingelseTypeModel.PROPERTY_XML_MVATYPE_F)));
		comboBox.setName("ComboBoxMvatypeF");
		return comboBox;
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getClassName()
	 */
	@Override
	public String getClassName() {
		return "BetingelseType";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getAddRemoveString()
	 */
	@Override
	public String getAddRemoveString() {
		return "betingelse";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#hasWriteAccess()
	 */
	@Override
	public Boolean hasWriteAccess() {
		return true;
	}

	/**
	 * Åpner for editering
	 * 
	 * @param object
	 * @param searching
	 */
	@Override
	protected final void openEditView(final BetingelseType object,
			final boolean searching) {
		if (object.getBetingelseTypeId() == null) {
			object.setLinkAvtale(0);
		}
		EditBetingelseTypeView betingelseTypeView = new EditBetingelseTypeView(
				searching, object, this);
		WindowInterface dialog = new JDialogAdapter(new JDialog(FrafMain
				.getInstance(), "Betingelse", true));
		dialog.setName("EditBetingelseTypeView");
		dialog.add(betingelseTypeView.buildPanel(dialog));
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
	public BetingelseType getNewObject() {
		return new BetingelseType();
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableModel()
	 */
	@Override
	public TableModel getTableModel() {
		return new BetingelseTypeTableModel(objectSelectionList);
	}

	/**
	 * Lagrer
	 * 
	 * @param object
	 * @param window
	 */
	@Override
	public void saveObject(BetingelseTypeModel object, WindowInterface window) {
		BetingelseType betingelseType = object.getObject();
		int index = objectList.indexOf(betingelseType);

		overviewManager.saveObject(betingelseType);

		if (index < 0) {
			objectList.add(betingelseType);
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
		return "Betingelser";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getWindowSize()
	 */
	@Override
	public Dimension getWindowSize() {
		return new Dimension(900, 500);
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
		// Kode
		table.getColumnExt(0).setPreferredWidth(50);
		// Navn
		table.getColumnExt(1).setPreferredWidth(150);
		// MVA kode egeneid
		table.getColumnExt(2).setPreferredWidth(110);
		// MVA kode franchise
		table.getColumnExt(3).setPreferredWidth(120);
		// Betingelsegruppe
		table.getColumnExt(4).setPreferredWidth(110);
		// Inntektskonto egeneid
		table.getColumnExt(5).setPreferredWidth(130);
		// Inntektskonto franchise
		table.getColumnExt(6).setPreferredWidth(130);
		// Avdeling egeneid
		table.getColumnExt(7).setPreferredWidth(100);
//		 Avdeling franchise
		table.getColumnExt(8).setPreferredWidth(100);
		// XML konto egenid
		table.getColumnExt(9).setPreferredWidth(110);
		// XML konto franchise
		table.getColumnExt(10).setPreferredWidth(110);
		// XML mva egeneid
		table.getColumnExt(11).setPreferredWidth(130);
		// XML mva franchise
		table.getColumnExt(12).setPreferredWidth(130);
		// Selskap
		table.getColumnExt(13).setPreferredWidth(60);
		// kjede
		table.getColumnExt(14).setPreferredWidth(70);
		// termin
		table.getColumnExt(15).setPreferredWidth(50);
		// avregning
		table.getColumnExt(16).setPreferredWidth(100);
//		 fakturatekst
		table.getColumnExt(17).setPreferredWidth(100);
		
		HighlighterPipeline highlighters = new HighlighterPipeline();

		PatternHighlighter pattern = new PatternHighlighter(Color.WHITE,
				Color.LIGHT_GRAY, "Ja", Pattern.CASE_INSENSITIVE, 27);
		highlighters.addHighlighter(pattern);
		table.setHighlighters(highlighters);


	}

	/**
	 * Sjekker før lagring
	 * 
	 * @param object
	 * @param presentationModel
	 * @param window
	 * @return feilmelding
	 */
	@Override
	public String checkSaveObject(BetingelseTypeModel object,
			PresentationModel presentationModel, WindowInterface window) {
		List<BetingelseType> betingelser = ((BetingelseTypeDAO) overviewManager)
				.findByKodeKjede(object.getObject().getBetingelseTypeId(),
						object.getBetingelseTypeKode(), object.getChain());
		if (betingelser != null && betingelser.size() != 0) {
			return "Kan ikke ha betingelse som har samme kode og kjede";
		}
		return null;
	}

	/**
	 * Sjekker før sletting
	 * 
	 * @param object
	 * @return feilmelding
	 */
	@Override
	public String checkDeleteObject(BetingelseType object) {
		int count = avdelingBetingelseDAO.findNumberByType(object);
		if (count != 0) {
			return "Kan ikke slette type som brukes av en eller flere avdelinger";
		}
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#initObjects()
	 */
	@Override
	protected void initObjects() {
		if (!loaded) {
			setFiltered(false);
			objectSelectionList.clearSelection();
			objectList.clear();
			List<BetingelseType> allObjects;
			if (groupEnum != BetingelseGruppeEnum.ADMIN) {
				allObjects = ((BetingelseTypeDAO) overviewManager)
						.findByGroupName(groupEnum.getGroupName());
			} else {
				allObjects = overviewManager.findAll();
			}
			if (allObjects != null) {
				objectList.addAll(allObjects);
			}
			noOfObjects = objectList.getSize();
			if (table != null) {
				table.scrollRowToVisible(0);
			}
		}
	}

	/**
	 * Tabellmodell for betingelsetype
	 * 
	 * @author abr99
	 * 
	 */
	private static final class BetingelseTypeTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Kode", "Navn",
				"MVA kode egeneid", "MVA kode franschise", "Betingelse gruppe",
				"Inntektskonto egeneid", "Inntektskonto franchise", "Avdeling egeneid","Avdeling franchise",
				"XML konto egeneid", "XML konto franchise",
				"XML mvakode egeneid", "XML mvakode franchise", "Selskap",
				"Kjede", "Termin", "Avregning", "Fakturatekst", "Rekkefølge",
				"Sats", "Beløp", "Beløp pr. stk", "XML Mvatype egeneid",
				"XML Mvatype franchise", "XML Vatusetype egneid",
				"XML Vatusetype franchise", "Link avtale","Inaktiv" };

		/**
		 * @param listModel
		 */
		BetingelseTypeTableModel(ListModel listModel) {
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
			BetingelseType betingelseType = (BetingelseType) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return betingelseType.getBetingelseTypeKode();
			case 1:
				return betingelseType.getBetingelseNavn();
			case 2:
				return betingelseType.getMvaE();
			case 3:
				return betingelseType.getMvaF();
			case 4:
				return betingelseType.getBetingelseGruppe();
			case 5:
				return betingelseType.getInntektskontoE();
			case 6:
				return betingelseType.getInntektskontoF();
			case 7:
				return betingelseType.getBokfAvdelingE();
			case 8:
				return betingelseType.getBokfAvdelingF();
			case 9:
				return betingelseType.getXmlKontoE();
			case 10:
				return betingelseType.getXmlKontoF();
			case 11:
				return betingelseType.getXmlMvaE();
			case 12:
				return betingelseType.getXmlMvaF();
			case 13:
				return betingelseType.getBokfSelskap();
			case 14:
				if (betingelseType.getChain() != null) {
					return betingelseType.getChain().getNavn();
				}
				return null;
			case 15:
				return betingelseType.getAvregningFrekvensType();
			case 16:
				return betingelseType.getAvregningType();
			case 17:
				return betingelseType.getFakturaTekst();
			case 18:
				return betingelseType.getFakturaTekstRek();
			case 19:
				return betingelseType.getSats();
			case 20:
				return betingelseType.getBelop();
			case 21:
				return betingelseType.getBelopPrStk();
			case 22:
				return betingelseType.getXmlMvatypeE();
			case 23:
				return betingelseType.getXmlMvatypeF();
			case 24:
				return betingelseType.getXmlVatusetypeE();
			case 25:
				return betingelseType.getXmlVatusetypeF();
			case 26:
				if (betingelseType.getLinkAvtale() != null
						&& betingelseType.getLinkAvtale() == 1) {
					return "Ja";
				}
				return "Nei";
			case 27:
				if (betingelseType.getInaktiv() != null
						&& betingelseType.getInaktiv() == 1) {
					return "Ja";
				}
				return "Nei";
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
			case 1:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 14:
			case 17:
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
				return String.class;
			case 2:
			case 3:
			case 11:
			case 12:
				return Mva.class;
			case 4:
				return BetingelseGruppe.class;
			case 13:
				return BokfSelskap.class;
			case 15:
				return AvregningFrekvensType.class;
			case 16:
				return AvregningType.class;
			case 18:
				return Integer.class;
			case 19:
			case 20:
			case 21:
				return BigDecimal.class;
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	private class FilterAction extends AbstractAction{
		public FilterAction(){
			super("Vis inaktive");
		}

		public void actionPerformed(ActionEvent arg0) {
			if(!checkBoxFilter.isSelected()){
			filterTable();
			}else{
				table.setFilters(null);
			}
			
		}
	}
}
