package no.ica.fraf.gui.validator;

import no.ica.fraf.gui.model.ApplUserModel;
import no.ica.fraf.util.ModelUtil;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * Validator for bruker
 * 
 * @author abr99
 * 
 */
public class ApplUserValidator implements Validator {

	/**
	 * Holds the order to be validated.
	 */
	private ApplUserModel applUserModel;

	/**
	 * @param applUserModel
	 */
	public ApplUserValidator(ApplUserModel applUserModel) {
		this.applUserModel = applUserModel;
	}

	// Validation *************************************************************

	/**
	 * Validates this Validator's Order and returns the result as an instance of
	 * {@link ValidationResult}.
	 * 
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate() {
		PropertyValidationSupport support = new PropertyValidationSupport(
				applUserModel, "Bruker");

		if (ValidationUtils.isBlank(ModelUtil.nullToString(applUserModel
				.getFirstName()))) {
			support.addError("fornavn", "må settes");
		}

		if (ValidationUtils.isBlank(ModelUtil.nullToString(applUserModel
				.getUserName()))) {
			support.addError("brukernavn", "må settes");
		}

		if (ValidationUtils.isBlank(ModelUtil.nullToString(applUserModel
				.getSurname()))) {
			support.addError("etternavn", "må settes");
		}

		if (ValidationUtils.isBlank(ModelUtil.nullToString(applUserModel
				.getApplUserType()))) {
			support.addError("brukertype", "må settes");
		}

		return support.getResult();
	}

}
