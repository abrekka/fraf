package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;

import no.ica.fraf.model.ApplParam;

import com.jgoodies.binding.PresentationModel;

public class ApplParamModel extends AbstractModel<ApplParam, ApplParamModel> {
	public static final String PROPERTY_PARAM_NAME = "paramName";

	/**
	 * 
	 */
	public static final String PROPERTY_PARAM_VALUE = "paramValue";

	public ApplParamModel(ApplParam object) {
		super(object);
	}

	public String getParamName() {
		return object.getParamName();
	}

	/**
	 * @param paramName
	 */
	public void setParamName(String paramName) {
		String oldName = getParamName();
		object.setParamName(paramName);
		firePropertyChange(PROPERTY_PARAM_NAME, oldName, paramName);
	}

	/**
	 * @return parameterverdi
	 */
	public String getParamValue() {
		return object.getParamValue();
	}

	/**
	 * @param paramValue
	 */
	public void setParamValue(String paramValue) {
		String oldValue = getParamName();
		object.setParamValue(paramValue);
		firePropertyChange(PROPERTY_PARAM_VALUE, oldValue, paramValue);
	}

	@Override
	public ApplParamModel getBufferedObjectModel(
			PresentationModel presentationModel) {
		ApplParamModel model = new ApplParamModel(new ApplParam());
		model.setParamName((String) presentationModel
				.getBufferedValue(PROPERTY_PARAM_NAME));
		model.setParamValue((String) presentationModel
				.getBufferedValue(PROPERTY_PARAM_VALUE));
		return model;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener,
			PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_PARAM_NAME)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_PARAM_VALUE)
				.addValueChangeListener(listener);
	}

}
