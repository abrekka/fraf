package no.ica.tollpost.gui;

import no.ica.fraf.gui.model.ApplParamModel;
import no.ica.fraf.util.ModelUtil;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

public class ApplParamValidator implements Validator {

	/**
	 * Holds the order to be validated.
	 */
	private ApplParamModel applParamModel;

	/**
	 * @param applParamElfaModel
	 */
	public ApplParamValidator(ApplParamModel applParamModel) {
		this.applParamModel = applParamModel;
	}

	// Validation *************************************************************

	/**
	 * @see com.jgoodies.validation.Validator#validate()
	 */
	public ValidationResult validate() {
		PropertyValidationSupport support = new PropertyValidationSupport(
				applParamModel, "Parameter");

		if (ValidationUtils.isBlank(ModelUtil.nullToString(applParamModel
				.getParamName()))) {
			support.addError("navn", "må settes");
		}

		if (ValidationUtils.isBlank(ModelUtil.nullToString(applParamModel
				.getParamValue()))) {
			support.addError("verdi", "må settes");
		}

		return support.getResult();
	}

}
