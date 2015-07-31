package no.ica.fraf.gui.edit;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.AbstractEditView;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.handlers.ApplUserViewHandler;
import no.ica.fraf.gui.model.AbstractModel;
import no.ica.fraf.gui.model.ApplUserModel;
import no.ica.fraf.gui.validator.ApplUserValidator;
import no.ica.fraf.model.ApplUser;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Vindu for editering av bruker
 * 
 * @author abr99
 * 
 */
public class EditApplUserView extends AbstractEditView<ApplUserModel, ApplUser> {
	/**
	 * 
	 */
	private JTextField textFieldUserName;

	/**
	 * 
	 */
	private JTextField textFieldFirstName;

	/**
	 * 
	 */
	private JTextField textFieldSurname;

	/**
	 * 
	 */
	private JComboBox comboBoxApplUserType;

	/**
	 * 
	 */
	private JPasswordField passwordField;

	/**
	 * 
	 */
	private JCheckBox checkBoxDisabled;

	/**
	 * @param searchDialog
	 * @param object
	 * @param aViewHandler
	 */
	public EditApplUserView(boolean searchDialog,
			AbstractModel<ApplUser, ApplUserModel> object,
			AbstractViewHandler<ApplUser, ApplUserModel> aViewHandler) {
		super(searchDialog, object, aViewHandler);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#buildEditPanel()
	 */
	@Override
	protected JComponent buildEditPanel() {
		FormLayout layout = new FormLayout("10dlu,p,3dlu,100dlu,3dlu,p,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Brukernavn:", cc.xy(2, 2));
		builder.add(textFieldUserName, cc.xy(4, 2));
		builder.addLabel("Fornavn:", cc.xy(2, 4));
		builder.add(textFieldFirstName, cc.xy(4, 4));
		builder.addLabel("Etternavn:", cc.xy(2, 6));
		builder.add(textFieldSurname, cc.xy(4, 6));
		builder.addLabel("Passord:", cc.xy(2, 8));
		builder.add(passwordField, cc.xy(4, 8));
		builder.addLabel("Brukertype:", cc.xy(2, 10));
		builder.add(comboBoxApplUserType, cc.xy(4, 10));
		builder.add(checkBoxDisabled, cc.xy(4, 12));
		builder.add(
				ButtonBarFactory.buildCenteredBar(buttonSave, buttonCancel), cc
						.xyw(2, 14, 6));

		return new IconFeedbackPanel(validationResultModel, builder.getPanel());
	}

	/**
	 * Henter validator
	 * 
	 * @param object
	 * @return validator
	 */
	@Override
	protected Validator getValidator(ApplUserModel object) {
		return new ApplUserValidator(object);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initComponentAnnotations()
	 */
	@Override
	protected void initComponentAnnotations() {
		ValidationComponentUtils.setMandatory(textFieldFirstName, true);
		ValidationComponentUtils.setMessageKey(textFieldFirstName,
				"Bruker.fornavn");

		ValidationComponentUtils.setMandatory(textFieldUserName, true);
		ValidationComponentUtils.setMessageKey(textFieldUserName,
				"Bruker.brukernavn");

		ValidationComponentUtils.setMandatory(textFieldSurname, true);
		ValidationComponentUtils.setMessageKey(textFieldSurname,
				"Bruker.etternavn");

		ValidationComponentUtils.setMandatory(passwordField, true);
		ValidationComponentUtils.setMessageKey(passwordField, "Bruker.passord");

		ValidationComponentUtils.setMandatory(comboBoxApplUserType, true);
		ValidationComponentUtils.setMessageKey(comboBoxApplUserType,
				"Bruker.brukertype");

	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initEditComponents(no.ica.fraf.common.WindowInterface)
	 */
	@Override
	protected void initEditComponents(WindowInterface window1) {
		textFieldUserName = ((ApplUserViewHandler) viewHandler)
				.getTextFieldUserName(presentationModel);

		textFieldFirstName = ((ApplUserViewHandler) viewHandler)
				.getTextFieldFirstName(presentationModel);

		textFieldSurname = ((ApplUserViewHandler) viewHandler)
				.getTextFieldSurname(presentationModel);

		comboBoxApplUserType = ((ApplUserViewHandler) viewHandler)
				.getComboBoxApplUserType(presentationModel);

		passwordField = ((ApplUserViewHandler) viewHandler)
				.getPasswordField(presentationModel);

		checkBoxDisabled = ((ApplUserViewHandler) viewHandler)
				.getCheckBoxDisabled(presentationModel);

	}

}
