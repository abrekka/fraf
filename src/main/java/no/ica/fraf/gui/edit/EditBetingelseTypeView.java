package no.ica.fraf.gui.edit;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.AbstractEditView;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.handlers.BetingelseViewHandler;
import no.ica.fraf.gui.model.BetingelseTypeModel;
import no.ica.fraf.gui.validator.BetingelseTypeValidator;
import no.ica.fraf.model.BetingelseType;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Vindu for editering av betingelsetype
 * 
 * @author abr99
 * 
 */
public class EditBetingelseTypeView extends
		AbstractEditView<BetingelseTypeModel, BetingelseType> {

	private JTextField textFieldKode;
	private JTextField textFieldNavn;
	private JComboBox comboBoxMvaKodeE;
	private JComboBox comboBoxMvaKodeF;
	private JComboBox comboBoxBetingelseGruppe;

	/**
	 * 
	 */
	private JTextField textFieldInntektskontoE;

	private JTextField textFieldInntektskontoF;

	/**
	 * 
	 */
	private JTextField textFieldXmlKontoE;

	private JTextField textFieldXmlKontoF;

	/**
	 * 
	 */
	private JComboBox comboBoxXmlMvaKodeE;

	private JComboBox comboBoxXmlMvaKodeF;

	/**
	 * 
	 */
	private JComboBox comboBoxSelskap;

	/**
	 * 
	 */
	private JComboBox comboBoxKjede;

	/**
	 * 
	 */
	private JComboBox comboBoxTermin;

	/**
	 * 
	 */
	private JComboBox comboBoxAvregning;

	/**
	 * 
	 */
	private JTextField textFieldFakturatekst;

	/**
	 * 
	 */
	private JTextField textFieldRekkefølge;

	/**
	 * 
	 */
	private JTextField textFieldSats;

	/**
	 * 
	 */
	private JTextField textFieldBelop;

	/**
	 * 
	 */
	private JTextField textFieldBelopPrStk;

	/**
	 * 
	 */
	private JTextField textFieldBokfAvdelingE;
	private JTextField textFieldBokfAvdelingF;

	/**
	 * 
	 */
	private JComboBox comboBoxMvatypeE;

	private JComboBox comboBoxMvatypeF;

	/**
	 * 
	 */
	private JTextField textFieldVatusetypeE;

	private JTextField textFieldVatusetypeF;

	/**
	 * 
	 */
	private JComboBox comboBoxLinkAvtale;

	private JComboBox comboBoxInaktiv;

	public EditBetingelseTypeView(
			boolean searchDialog,
			BetingelseType betingelseType,
			AbstractViewHandler<BetingelseType, BetingelseTypeModel> aViewHandler) {
		super(searchDialog, new BetingelseTypeModel(betingelseType),
				aViewHandler);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#buildEditPanel()
	 */
	@Override
	protected JComponent buildEditPanel() {

		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,80dlu,5dlu,p,3dlu,40dlu,5dlu,p,3dlu,40dlu,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Kode:", cc.xy(2, 2));
		builder.add(textFieldKode, cc.xy(4, 2));
		builder.addLabel("Navn:", cc.xy(2, 4));
		builder.add(textFieldNavn, cc.xy(4, 4));
		builder.addLabel("Gruppe:", cc.xy(2, 6));
		builder.add(comboBoxBetingelseGruppe, cc.xy(4, 6));
		builder.addLabel("Selskap:", cc.xy(2, 8));
		builder.add(comboBoxSelskap, cc.xy(4, 8));
		builder.addLabel("Kjede:", cc.xy(2, 10));
		builder.add(comboBoxKjede, cc.xy(4, 10));
		builder.addLabel("Termin:", cc.xy(2, 12));
		builder.add(comboBoxTermin, cc.xy(4, 12));
		builder.addLabel("Avregning:", cc.xy(2, 14));
		builder.add(comboBoxAvregning, cc.xy(4, 14));
		builder.addLabel("Avdeling egeneid:", cc.xy(2, 16));
		builder.add(textFieldBokfAvdelingE, cc.xy(4, 16));
		builder.addLabel("Avdeling franchise:", cc.xy(2, 18));
		builder.add(textFieldBokfAvdelingF, cc.xy(4, 18));
		builder.addLabel("Rekkefølge:", cc.xy(2, 20));
		builder.add(textFieldRekkefølge, cc.xy(4, 20));
		builder.addLabel("Inaktiv:", cc.xy(2, 22));
		builder.add(comboBoxInaktiv, cc.xy(4, 22));

//		builder.addLabel("MVA kode egeneid:", cc.xy(6, 2));
//		builder.add(comboBoxMvaKodeE, cc.xy(8, 2));
		builder.addLabel("MVA kode franchise:", cc.xy(6, 2));
		builder.add(comboBoxMvaKodeF, cc.xy(8, 2));
//		builder.addLabel("Inntektskonto egeneid:", cc.xy(6, 6));
//		builder.add(textFieldInntektskontoE, cc.xy(8, 6));
		builder.addLabel("Inntektskonto franchise:", cc.xy(6, 4));
		builder.add(textFieldInntektskontoF, cc.xy(8, 4));
		builder.addLabel("Link avtale:", cc.xy(6, 6));
		builder.add(comboBoxLinkAvtale, cc.xy(8, 6));
		builder.addLabel("Sats:", cc.xy(6, 8));
		builder.add(textFieldSats, cc.xy(8, 8));
		builder.addLabel("Beløp:", cc.xy(6, 10));
		builder.add(textFieldBelop, cc.xy(8, 10));
		builder.addLabel("Beløp pr. stk:", cc.xy(6, 12));
		builder.add(textFieldBelopPrStk, cc.xy(8, 12));
		builder.addLabel("XML konto franchise:", cc.xy(6, 14));
		builder.add(textFieldXmlKontoF, cc.xy(8, 14));
		builder.addLabel("XML MVA kode franchise:", cc.xy(6, 16));
		builder.add(comboBoxXmlMvaKodeF, cc.xy(8, 16));
		builder.addLabel("XML Mvatype franchise:", cc.xy(6, 18));
		builder.add(comboBoxMvatypeF, cc.xy(8, 18));
		builder.addLabel("XML Vatusetype franchise:", cc.xy(6, 20));
		builder.add(textFieldVatusetypeF, cc.xy(8, 20));
		builder.addLabel("Fakturatekst:", cc.xy(2, 24));
		builder.add(textFieldFakturatekst, cc.xyw(4, 24, 5));

//		builder.addLabel("XML konto egeneid:", cc.xy(10, 2));
//		builder.add(textFieldXmlKontoE, cc.xy(12, 2));
		
//		builder.addLabel("XML MVA kode egeneid:", cc.xy(10, 6));
//		builder.add(comboBoxXmlMvaKodeE, cc.xy(12, 6));
		
//		builder.addLabel("XML Mvatype egeneid:", cc.xy(10, 10));
//		builder.add(comboBoxMvatypeE, cc.xy(12, 10));
		
//		builder.addLabel("XML Vatusetype egeneid:", cc.xy(10, 14));
//		builder.add(textFieldVatusetypeE, cc.xy(12, 14));
		

		builder.add(
				ButtonBarFactory.buildCenteredBar(buttonSave, buttonCancel), cc
						.xyw(2, 26, 11));

		return new IconFeedbackPanel(validationResultModel, builder.getPanel());
	}

	/**
	 * Henter validator
	 * 
	 * @param object
	 * @return validator
	 */
	@Override
	protected Validator getValidator(BetingelseTypeModel object) {
		return new BetingelseTypeValidator(object);

	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initComponentAnnotations()
	 */
	@Override
	protected void initComponentAnnotations() {
		ValidationComponentUtils.setMandatory(comboBoxBetingelseGruppe, true);
		ValidationComponentUtils.setMessageKey(comboBoxBetingelseGruppe,
				"Betingelse.gruppe");

		ValidationComponentUtils.setMandatory(textFieldKode, true);
		ValidationComponentUtils
				.setMessageKey(textFieldKode, "Betingelse.kode");

		ValidationComponentUtils.setMandatory(comboBoxAvregning, true);
		ValidationComponentUtils.setMessageKey(comboBoxAvregning,
				"Betingelse.avregning");
		ValidationComponentUtils.setMandatory(comboBoxTermin, true);
		ValidationComponentUtils.setMessageKey(comboBoxTermin,
				"Betingelse.frekvens");

		ValidationComponentUtils.setMandatory(textFieldBelop, true);
		ValidationComponentUtils.setMessageKey(textFieldBelop,
				"Betingelse.belop");

//		ValidationComponentUtils.setMandatory(comboBoxMvatypeE, true);
//		ValidationComponentUtils.setMessageKey(comboBoxMvatypeE,
//				"Betingelse.xml mvatype");

//		ValidationComponentUtils.setMandatory(textFieldVatusetypeE, true);
//		ValidationComponentUtils.setMessageKey(textFieldVatusetypeE,
//				"Betingelse.xml vatusetype");
		ValidationComponentUtils.setMandatory(comboBoxLinkAvtale, true);
		ValidationComponentUtils.setMessageKey(comboBoxLinkAvtale,
				"Betingelse.link");

	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initEditComponents(no.ica.fraf.common.WindowInterface)
	 */
	@Override
	protected void initEditComponents(WindowInterface window1) {
		textFieldKode = ((BetingelseViewHandler) viewHandler)
				.getTextFieldKode(presentationModel);
		textFieldNavn = ((BetingelseViewHandler) viewHandler)
				.getTextFieldNavn(presentationModel);
		comboBoxMvaKodeE = ((BetingelseViewHandler) viewHandler)
				.getComboBoxMvaKodeE(presentationModel);
		comboBoxMvaKodeF = ((BetingelseViewHandler) viewHandler)
				.getComboBoxMvaKodeF(presentationModel);
		comboBoxBetingelseGruppe = ((BetingelseViewHandler) viewHandler)
				.getComboBoxBetingelseGruppe(presentationModel);
		textFieldInntektskontoE = ((BetingelseViewHandler) viewHandler)
				.getTextFieldInntektskontoE(presentationModel);
		textFieldInntektskontoF = ((BetingelseViewHandler) viewHandler)
				.getTextFieldInntektskontoF(presentationModel);
		textFieldXmlKontoE = ((BetingelseViewHandler) viewHandler)
				.getTextFieldXmlKontoE(presentationModel);
		textFieldXmlKontoF = ((BetingelseViewHandler) viewHandler)
				.getTextFieldXmlKontoF(presentationModel);
		comboBoxXmlMvaKodeE = ((BetingelseViewHandler) viewHandler)
				.getComboBoxXmlMvaKodeE(presentationModel);
		comboBoxXmlMvaKodeF = ((BetingelseViewHandler) viewHandler)
				.getComboBoxXmlMvaKodeF(presentationModel);
		comboBoxSelskap = ((BetingelseViewHandler) viewHandler)
				.getComboBoxSelskap(presentationModel);
		comboBoxKjede = ((BetingelseViewHandler) viewHandler)
				.getComboBoxKjede(presentationModel);
		comboBoxTermin = ((BetingelseViewHandler) viewHandler)
				.getComboBoxTermin(presentationModel);
		comboBoxAvregning = ((BetingelseViewHandler) viewHandler)
				.getComboBoxAvregning(presentationModel);
		textFieldFakturatekst = ((BetingelseViewHandler) viewHandler)
				.getTextFieldFakturaTekst(presentationModel);
		textFieldRekkefølge = ((BetingelseViewHandler) viewHandler)
				.getTextFieldRekkefolge(presentationModel);
		textFieldSats = ((BetingelseViewHandler) viewHandler)
				.getTextFieldSats(presentationModel);
		textFieldBelop = ((BetingelseViewHandler) viewHandler)
				.getTextFieldBelop(presentationModel);
		textFieldBelopPrStk = ((BetingelseViewHandler) viewHandler)
				.getTextFieldBelopPrStk(presentationModel);
		textFieldBokfAvdelingE = ((BetingelseViewHandler) viewHandler)
				.getTextFieldBokfAvdelingE(presentationModel);
		textFieldBokfAvdelingF = ((BetingelseViewHandler) viewHandler)
		.getTextFieldBokfAvdelingF(presentationModel);
		comboBoxMvatypeE = ((BetingelseViewHandler) viewHandler)
				.getComboBoxMvatypeE(presentationModel);
		comboBoxMvatypeF = ((BetingelseViewHandler) viewHandler)
				.getComboBoxMvatypeF(presentationModel);
		textFieldVatusetypeE = ((BetingelseViewHandler) viewHandler)
				.getTextFieldVatusetypeE(presentationModel);
		textFieldVatusetypeF = ((BetingelseViewHandler) viewHandler)
				.getTextFieldVatusetypeF(presentationModel);
		comboBoxLinkAvtale = ((BetingelseViewHandler) viewHandler)
				.getComboBoxLinkAvtale(presentationModel);
		comboBoxInaktiv = ((BetingelseViewHandler) viewHandler)
				.getComboBoxInaktiv(presentationModel);
	}

}
