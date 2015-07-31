package no.ica.fraf.gui.department;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.AbstractEditView;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.handlers.ConditionViewHandler;
import no.ica.fraf.gui.model.AvdelingBetingelseModel;
import no.ica.fraf.gui.model.ConditionValidator;
import no.ica.fraf.model.AvdelingBetingelse;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.toedter.calendar.JDateChooser;

/**
 * Dialog for å editere betingelser for avdeling
 * 
 * @author abr99
 * 
 */
public class EditConditionView extends
		AbstractEditView<AvdelingBetingelseModel, AvdelingBetingelse> {

	/**
	 * 
	 */
	private JComboBox comboBoxType;

	/**
	 * 
	 */
	private JDateChooser dateChooserFromDate;

	/**
	 * 
	 */
	private JDateChooser dateChooserToDate;

	/**
	 * 
	 */
	private JCheckBox checkBoxMirror;

	/**
	 * 
	 */
	private JTextField textFieldSats;

	/**
	 * 
	 */
	private JTextField textFieldFromAmount;

	/**
	 * 
	 */
	private JTextField textFieldToAmount;

	/**
	 * 
	 */
	private JTextField textFieldAmount;

	/**
	 * 
	 */
	private JComboBox comboBoxFrequence;

	/**
	 * 
	 */
	private JComboBox comboBoxSettlement;

	/**
	 * 
	 */
	private JTextField textFieldComment;

	/**
	 * 
	 */
	private JComboBox comboBoxCompany;

	/**
	 * 
	 */
	private JTextField textFieldDepartment;

	/**
	 * 
	 */
	private JTextField textFieldInvoiceText;

	/**
	 * 
	 */
	private JTextField textFieldOrder;

	/**
	 * @param searchDialog
	 * @param object
	 * @param aViewHandler
	 */
	public EditConditionView(
			boolean searchDialog,
			AvdelingBetingelse object,
			AbstractViewHandler<AvdelingBetingelse, AvdelingBetingelseModel> aViewHandler) {
		super(searchDialog, new AvdelingBetingelseModel(object), aViewHandler);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initEditComponents(no.ica.fraf.common.WindowInterface)
	 */
	@Override
	protected void initEditComponents(WindowInterface aWindow) {
		comboBoxType = ((ConditionViewHandler) viewHandler)
				.getComboBoxType(presentationModel);
		dateChooserFromDate = ((ConditionViewHandler) viewHandler)
				.getDateChooserFrom(presentationModel);
		dateChooserToDate = ((ConditionViewHandler) viewHandler)
				.getDateChooserTo(presentationModel);
		checkBoxMirror = ((ConditionViewHandler) viewHandler)
				.getCheckBoxMirror(presentationModel);
		textFieldSats = ((ConditionViewHandler) viewHandler)
				.getTextFieldSats(presentationModel);
		textFieldFromAmount = ((ConditionViewHandler) viewHandler)
				.getTextFieldFromAmount(presentationModel);
		textFieldToAmount = ((ConditionViewHandler) viewHandler)
				.getTextFieldToAmount(presentationModel);
		textFieldAmount = ((ConditionViewHandler) viewHandler)
				.getTextFieldAmount(presentationModel);
		comboBoxFrequence = ((ConditionViewHandler) viewHandler)
				.getComboBoxFrequence(presentationModel);
		comboBoxSettlement = ((ConditionViewHandler) viewHandler)
				.getComboBoxSettlement(presentationModel);
		textFieldComment = ((ConditionViewHandler) viewHandler)
				.getTextFieldComment(presentationModel);
		comboBoxCompany = ((ConditionViewHandler) viewHandler)
				.getComboBoxCompany(presentationModel);
		textFieldDepartment = ((ConditionViewHandler) viewHandler)
				.getTextFieldDepartment(presentationModel);
		textFieldInvoiceText = ((ConditionViewHandler) viewHandler)
				.getTextFieldInvoiceText(presentationModel);
		textFieldOrder = ((ConditionViewHandler) viewHandler)
				.getTextFieldOrder(presentationModel);
	}

	/**
	 * Henter validator
	 * 
	 * @param object
	 * @return validator
	 */
	@Override
	protected Validator getValidator(AvdelingBetingelseModel object) {
		return new ConditionValidator(object);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initComponentAnnotations()
	 */
	@Override
	protected void initComponentAnnotations() {
		ValidationComponentUtils.setMandatory(dateChooserFromDate, true);
		ValidationComponentUtils.setMessageKey(dateChooserFromDate,
				"Betingelse.fra dato");

		ValidationComponentUtils.setMandatory(dateChooserToDate, true);
		ValidationComponentUtils.setMessageKey(dateChooserToDate,
				"Betingelse.til dato");

		ValidationComponentUtils.setMandatory(textFieldAmount, true);
		ValidationComponentUtils.setMessageKey(textFieldAmount,
				"Betingelse.belop");

		ValidationComponentUtils.setMandatory(comboBoxFrequence, true);
		ValidationComponentUtils.setMessageKey(comboBoxFrequence,
				"Betingelse.termin");

		ValidationComponentUtils.setMandatory(comboBoxSettlement, true);
		ValidationComponentUtils.setMessageKey(comboBoxSettlement,
				"Betingelse.avregning");
		ValidationComponentUtils.setMandatory(comboBoxType, true);
		ValidationComponentUtils.setMessageKey(comboBoxType, "Betingelse.type");

	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#buildEditPanel()
	 */
	@Override
	protected JComponent buildEditPanel() {
		FormLayout layout = new FormLayout(
				"10dlu,p,3dlu,70dlu,3dlu,30dlu,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Type:", cc.xy(2, 2));
		builder.add(comboBoxType, cc.xyw(4, 2, 3));
		builder.addLabel("Fra dato:", cc.xy(2, 4));
		builder.add(dateChooserFromDate, cc.xy(4, 4));
		builder.addLabel("Til dato:", cc.xy(2, 6));
		builder.add(dateChooserToDate, cc.xy(4, 6));
		builder.add(checkBoxMirror, cc.xy(4, 8));
		builder.addLabel("Sats:", cc.xy(2, 10));
		builder.add(textFieldSats, cc.xy(4, 10));
		builder.addLabel("Sats fra beløp:", cc.xy(2, 12));
		builder.add(textFieldFromAmount, cc.xy(4, 12));
		builder.addLabel("Sats til beløp:", cc.xy(2, 14));
		builder.add(textFieldToAmount, cc.xy(4, 14));
		builder.addLabel("Terminbeløp:", cc.xy(2, 16));
		builder.add(textFieldAmount, cc.xy(4, 16));
		builder.addLabel("Termin:", cc.xy(2, 18));
		builder.add(comboBoxFrequence, cc.xy(4, 18));
		builder.addLabel("Avregning:", cc.xy(2, 20));
		builder.add(comboBoxSettlement, cc.xy(4, 20));
		builder.addLabel("Kommentar:", cc.xy(2, 22));
		builder.add(textFieldComment, cc.xyw(4, 22, 3));
		builder.addLabel("Bokføringsselskap:", cc.xy(2, 24));
		builder.add(comboBoxCompany, cc.xy(4, 24));
		builder.addLabel("Bokføringsavdeling:", cc.xy(2, 26));
		builder.add(textFieldDepartment, cc.xy(4, 26));
		builder.addLabel("Fakturatekst:", cc.xy(2, 28));
		builder.add(textFieldInvoiceText, cc.xy(4, 28));
		builder.addLabel("Rekkefølge:", cc.xy(2, 30));
		builder.add(textFieldOrder, cc.xy(4, 30));
		builder.add(
				ButtonBarFactory.buildCenteredBar(buttonSave, buttonCancel), cc
						.xyw(2, 32, 5));

		return new IconFeedbackPanel(validationResultModel, builder.getPanel());
	}

}
