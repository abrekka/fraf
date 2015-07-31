package no.ica.elfa.gui;

import javax.swing.JComponent;
import javax.swing.JTextField;

import no.ica.elfa.gui.handlers.ApplParamElfaViewHandler;
import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.AbstractEditView;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.model.ApplParamModel;
import no.ica.fraf.model.ApplParam;
import no.ica.tollpost.gui.ApplParamValidator;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Håndterer editering av elfaparameter
 * 
 * @author abr99
 * 
 */
public class EditApplParamElfaView extends
		AbstractEditView<ApplParamModel, ApplParam> {
	/**
	 * 
	 */
	private JTextField textFieldParamName;

	/**
	 * 
	 */
	private JTextField textFieldParamValue;

	public EditApplParamElfaView(boolean searchDialog, ApplParam applParam,
			AbstractViewHandler<ApplParam, ApplParamModel> aViewHandler) {
		super(searchDialog, new ApplParamModel(applParam), aViewHandler);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initEditComponents(no.ica.fraf.common.WindowInterface)
	 */
	@Override
	protected void initEditComponents(WindowInterface aWindow) {
		textFieldParamName = ((ApplParamElfaViewHandler) viewHandler)
				.getTextFieldParamName(presentationModel);
		textFieldParamValue = ((ApplParamElfaViewHandler) viewHandler)
				.getTextFieldParamValue(presentationModel);
	}

	/**
	 * Lager validator for elfaparameter
	 * 
	 * @param object
	 * @return validator
	 */
	@Override
	protected Validator getValidator(ApplParamModel object) {
		return new ApplParamValidator(object);
	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#initComponentAnnotations()
	 */
	@Override
	protected void initComponentAnnotations() {
		ValidationComponentUtils.setMandatory(textFieldParamName, true);
		ValidationComponentUtils.setMessageKey(textFieldParamName,
				"Parameter.navn");

		ValidationComponentUtils.setMandatory(textFieldParamValue, true);
		ValidationComponentUtils.setMessageKey(textFieldParamValue,
				"Parameter.verdi");

	}

	/**
	 * @see no.ica.fraf.gui.AbstractEditView#buildEditPanel()
	 */
	@Override
	protected JComponent buildEditPanel() {
		FormLayout layout = new FormLayout("10dlu,p,3dlu,100dlu,3dlu,p,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Navn:", cc.xy(2, 2));
		builder.add(textFieldParamName, cc.xy(4, 2));
		builder.addLabel("Verdi:", cc.xy(2, 4));
		builder.add(textFieldParamValue, cc.xy(4, 4));
		builder.add(
				ButtonBarFactory.buildCenteredBar(buttonSave, buttonCancel), cc
						.xyw(2, 6, 6));

		return new IconFeedbackPanel(validationResultModel, builder.getPanel());
	}

}
