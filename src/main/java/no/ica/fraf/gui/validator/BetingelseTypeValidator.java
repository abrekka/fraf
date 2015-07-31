package no.ica.fraf.gui.validator;

import no.ica.fraf.gui.model.BetingelseTypeModel;
import no.ica.fraf.util.ModelUtil;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * Validator for betingelsetype
 * 
 * @author abr99
 * 
 */
public class BetingelseTypeValidator implements Validator {

	/**
	 * Holds the order to be validated.
	 */
	private BetingelseTypeModel betingelseTypeModel;

	/**
	 * @param betingelseTypeModel
	 */
	public BetingelseTypeValidator(BetingelseTypeModel betingelseTypeModel) {
		this.betingelseTypeModel = betingelseTypeModel;
	}

	// Validation *************************************************************

	/**
	 * Validerer betingelsetype
	 * {@link ValidationResult}.
	 * 
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate() {
		PropertyValidationSupport support = new PropertyValidationSupport(
				betingelseTypeModel, "Betingelse");

		if (ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
				.getBetingelseGruppe()))) {
			support.addError("gruppe", "må settes");
		}

		/*if (ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
				.getIsDebitBool()))) {
			support.addError("debit", "må settes");
		}*/

		if (ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
				.getBetingelseTypeKode()))) {
			support.addError("kode", "må settes");
		}
		if (ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
				.getLinkAvtale()))) {
			support.addError("link", "må settes");
		}
		if (ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
				.getAvregningFrekvensType()))) {
			support.addError("frekvens", "må settes");
		}
		if (ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
				.getAvregningType()))) {
			support.addError("avregning", "må settes");
		}

		int belopCount = 0;

		if (ValidationUtils.isNotBlank(ModelUtil
				.nullToString(betingelseTypeModel.getSats()))) {
			belopCount++;
		}
		if (ValidationUtils.isNotBlank(ModelUtil
				.nullToString(betingelseTypeModel.getBelop()))) {
			belopCount++;
		}
		if (ValidationUtils.isNotBlank(ModelUtil
				.nullToString(betingelseTypeModel.getBelopPrStk()))) {
			belopCount++;
		}

		if (belopCount > 1) {
			support
					.addError("belop",
							"det kan bare sette sats, beløp eller beløp pr.stk, ikke flere av disse");
		} /*else if (belopCount == 0) {
			support.addError("belop",
					"det må settes sats, beløp eller beløp pr.stk");
		}*/
		
//		if(ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
//				.getXmlMvatypeE()))){
//			support
//			.addError("xml mvatype",
//					"det må settes xml mvatype for egeneid");
//		}
//		
//		if(ValidationUtils.isBlank(ModelUtil.nullToString(betingelseTypeModel
//				.getXmlVatusetypeE()))){
//			support
//			.addError("xml vatusetype",
//					"det må settes xml vatusetype for egeneid");
//		}

		return support.getResult();
	}

}
