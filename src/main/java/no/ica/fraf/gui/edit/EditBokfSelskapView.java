package no.ica.fraf.gui.edit;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.AbstractEditView;
import no.ica.fraf.gui.handlers.BokfSelskapViewHandler;
import no.ica.fraf.gui.model.BokfSelskapModel;
import no.ica.fraf.model.BokfSelskap;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Validator;

/**
 * Håndterer editering av selskap
 * 
 * @author abr99
 * 
 */
public class EditBokfSelskapView extends
		AbstractEditView<BokfSelskapModel, BokfSelskap> {
	/**
	 * 
	 */
	private JTextField textFieldSelskapnavn;

	/**
	 * 
	 */
	private JTextField textFieldFakturanr;

	/**
	 * 
	 */
	private JComboBox comboBoxTilPs;

	/**
	 * 
	 */
	private JTextField textFieldNavn;

	/**
	 * 
	 */
	private JTextField textFieldAdresse1;

	/**
	 * 
	 */
	private JTextField textFieldAdresse2;

	/**
	 * 
	 */
	private JTextField textFieldAdresse3;

	/**
	 * 
	 */
	private JTextField textFieldTelefon;

	/**
	 * 
	 */
	private JTextField textFieldTelefax;

	/**
	 * 
	 */
	private JTextField textFieldOrgNr;

	/**
	 * 
	 */
	private JTextField textFieldKontonr;

	/**
	 * 
	 */
	private JTextField textFieldLokasjonsnr;

	/**
	 * 
	 */
	private JTextField textFieldFilialKonto;

	/**
	 * 
	 */
	private JTextField textFieldPostnr;

	/**
	 * 
	 */
	private JTextField textFieldPoststed;

	/**
	 * 
	 */
	private JTextField textFieldOrgnr;

	/**
	 * 
	 */
	private JTextField textFieldMomsid;

	/**
	 * @param searchDialog
	 * @param object
	 * @param aViewHandler
	 */
	public EditBokfSelskapView(boolean searchDialog, BokfSelskap object,
			BokfSelskapViewHandler aViewHandler) {
		super(searchDialog, new BokfSelskapModel(object), aViewHandler);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initEditComponents(no.ica.fraf.common.WindowInterface)
	 */
	@Override
	protected void initEditComponents(WindowInterface aWindow) {
		textFieldSelskapnavn = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldSelskapnavn(presentationModel);
		textFieldFakturanr = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldFakturanr(presentationModel);
		comboBoxTilPs = ((BokfSelskapViewHandler) viewHandler)
				.getComboBoxTilPs(presentationModel);
		textFieldNavn = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldNavn(presentationModel);
		textFieldAdresse1 = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldAdresse1(presentationModel);
		textFieldAdresse2 = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldAdresse2(presentationModel);
		textFieldAdresse3 = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldAdresse3(presentationModel);
		textFieldTelefon = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldTelefon(presentationModel);
		textFieldTelefax = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldTelefax(presentationModel);
		textFieldOrgNr = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldOrgNr(presentationModel);
		textFieldKontonr = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldKontonr(presentationModel);
		textFieldLokasjonsnr = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldLokasjonsnr(presentationModel);
		textFieldFilialKonto = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldFilialKonto(presentationModel);
		textFieldPostnr = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldPostnr(presentationModel);
		textFieldPoststed = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldPoststed(presentationModel);
		textFieldOrgnr = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldPoststed(presentationModel);
		textFieldMomsid = ((BokfSelskapViewHandler) viewHandler)
				.getTextFieldMomsid(presentationModel);

	}

	/**
	 * @param object
	 * @return validator
	 */
	@Override
	protected Validator getValidator(BokfSelskapModel object) {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initComponentAnnotations()
	 */
	@Override
	protected void initComponentAnnotations() {
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#buildEditPanel()
	 */
	@Override
	protected JComponent buildEditPanel() {
		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,p,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Selskapnavn:", cc.xy(2, 2));
		builder.add(textFieldSelskapnavn, cc.xy(4, 2));
		builder.addLabel("Fakturanr:", cc.xy(2, 4));
		builder.add(textFieldFakturanr, cc.xy(4, 4));
		builder.addLabel("Til PS:", cc.xy(2, 6));
		builder.add(comboBoxTilPs, cc.xy(4, 6));
		builder.addLabel("Navn:", cc.xy(2, 8));
		builder.add(textFieldNavn, cc.xy(4, 8));
		builder.addLabel("Adresse1:", cc.xy(2, 10));
		builder.add(textFieldAdresse1, cc.xy(4, 10));
		builder.addLabel("Adresse2:", cc.xy(2, 12));
		builder.add(textFieldAdresse2, cc.xy(4, 12));
		builder.addLabel("Adresse3:", cc.xy(2, 14));
		builder.add(textFieldAdresse3, cc.xy(4, 14));
		builder.addLabel("Telefon:", cc.xy(2, 16));
		builder.add(textFieldTelefon, cc.xy(4, 16));
		builder.addLabel("Telefax:", cc.xy(2, 18));
		builder.add(textFieldTelefax, cc.xy(4, 18));
		builder.addLabel("Org. nr:", cc.xy(2, 20));
		builder.add(textFieldOrgNr, cc.xy(4, 20));
		builder.addLabel("Kontonr:", cc.xy(2, 22));
		builder.add(textFieldKontonr, cc.xy(4, 22));
		builder.addLabel("Lokasjonsnr:", cc.xy(2, 24));
		builder.add(textFieldLokasjonsnr, cc.xy(4, 24));
		builder.addLabel("Filialkonto:", cc.xy(2, 26));
		builder.add(textFieldFilialKonto, cc.xy(4, 26));
		builder.addLabel("Postnr:", cc.xy(2, 28));
		builder.add(textFieldPostnr, cc.xy(4, 28));
		builder.addLabel("Poststed:", cc.xy(2, 30));
		builder.add(textFieldPoststed, cc.xy(4, 30));
		builder.addLabel("Orgnr:", cc.xy(2, 32));
		builder.add(textFieldOrgnr, cc.xy(4, 32));
		builder.addLabel("Momsid:", cc.xy(2, 34));
		builder.add(textFieldMomsid, cc.xy(4, 34));

		builder.add(
				ButtonBarFactory.buildCenteredBar(buttonSave, buttonCancel), cc
						.xyw(2, 36, 3));

		return builder.getPanel();
	}

}
