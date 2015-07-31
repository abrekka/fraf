package no.ica.fraf.gui.model;

import no.ica.fraf.util.ModelUtil;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

public class ConditionValidator implements Validator {

	/**
	 * Holds the order to be validated.
	 */
	private AvdelingBetingelseModel avdelingBetingelseTypeModel;

	/**
	 * @param betingelseTypeModel
	 */
	public ConditionValidator(
			AvdelingBetingelseModel avdelingBetingelseTypeModel) {
		this.avdelingBetingelseTypeModel = avdelingBetingelseTypeModel;
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
				avdelingBetingelseTypeModel, "Betingelse");

		if (ValidationUtils.isBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel.getFraDato()))) {
			support.addError("fra dato", "m� settes");
		}
		if (ValidationUtils.isBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel.getTilDato()))) {
			support.addError("til dato", "m� settes");
		}
		if (ValidationUtils.isBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel
						.getAvregningFrekvensType()))) {
			support.addError("termin", "m� settes");
		}
		if (ValidationUtils.isBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel.getAvregningType()))) {
			support.addError("avregning", "m� settes");
		}
		if (ValidationUtils.isBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel.getBetingelseType()))) {
			support.addError("type", "m� settes");
		}
		int belopCount = 0;
		if (ValidationUtils.isNotBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel.getSatsString()))) {
			belopCount++;
		}
		if (ValidationUtils.isNotBlank(ModelUtil
				.nullToString(avdelingBetingelseTypeModel.getBelopString()))) {
			belopCount++;
		}

		if (belopCount > 1) {
			support
					.addError("belop",
							"det kan bare sette sats, bel�p eller bel�p pr.stk, ikke flere av disse");
		} else if (belopCount == 0&&!avdelingBetingelseTypeModel.getSpeiletBool()) {
			support.addWarning("belop",
					"dersom det ikke settes sats, bel�p eller bel�p pr.stk vil bel�p fra betingelsetypen bli brukt");
		}

		return support.getResult();
	}

}
