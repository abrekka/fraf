package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.list.ArrayListModel;

public class ApplUserModel extends
		AbstractModel<ApplUser, ApplUserModel> {
	public static final String PROPERTY_FIRST_NAME = "firstName";

	
	public static final String PROPERTY_SURNAME = "surname";

	public static final String PROPERTY_PASSWORD = "password";

	public static final String PROPERTY_USER_NAME = "userName";
	public static final String PROPERTY_APPL_USER_TYPE = "applUserType";
	public static final String PROPERTY_DISABLED_BOOL = "disabledBool";
	
	

	public ApplUserModel(ApplUser object) {
		super(object);
		
	}

	public String getFirstName() {
		return object.getFirstName();
	}

	public void setFirstName(String firstName) {
		String oldName = getFirstName();
		object.setFirstName(firstName);
		firePropertyChange(PROPERTY_FIRST_NAME, oldName, firstName);
	}
	
	public Boolean getDisabledBool() {
		return GuiUtil.convertNumberToBoolean(object.getDisabled());
	}

	public void setDisabledBool(Boolean disabledBool) {
		Boolean oldDisabled = getDisabledBool();
		object.setDisabled(GuiUtil.convertBooleanToNumber(disabledBool));
		firePropertyChange(PROPERTY_DISABLED_BOOL, oldDisabled, disabledBool);
	}


	public String getSurname() {
		return object.getSurname();
	}

	public void setSurname(String surname) {
		String oldName = getSurname();
		object.setSurname(surname);
		firePropertyChange(PROPERTY_SURNAME, oldName, surname);
	}

	public String getPassword() {
		return object.getPassword();
	}

	public void setPassword(String password) {
		String oldPass = getPassword();
		object.setPassword(password);
		firePropertyChange(PROPERTY_PASSWORD, oldPass, password);
	}

	public String getUserName() {
		return object.getUserName();
	}

	public void setUserName(String userName) {
		String oldName = getUserName();
		object.setUserName(userName);
		firePropertyChange(PROPERTY_USER_NAME, oldName, userName);
	}
	public ApplUserType getApplUserType() {
		return object.getApplUserType();
	}

	public void setApplUserType(ApplUserType applUserType) {
		ApplUserType oldType = getApplUserType();
		object.setApplUserType(applUserType);
		firePropertyChange(PROPERTY_APPL_USER_TYPE, oldType, applUserType);
	}



	@Override
	public void addBufferChangeListener(PropertyChangeListener listener,
			PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_FIRST_NAME).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_PASSWORD).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_USER_NAME).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SURNAME).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_APPL_USER_TYPE).addValueChangeListener(listener);
	}

	@Override
	public ApplUserModel getBufferedObjectModel(
			PresentationModel presentationModel) {
		ApplUserModel model = new ApplUserModel(new ApplUser());
		model.setFirstName((String)presentationModel.getBufferedValue(PROPERTY_FIRST_NAME));
		model.setSurname((String)presentationModel.getBufferedValue(PROPERTY_SURNAME));
		model.setPassword((String)presentationModel.getBufferedValue(PROPERTY_PASSWORD));
		model.setUserName((String)presentationModel.getBufferedValue(PROPERTY_USER_NAME));
		model.setApplUserType((ApplUserType)presentationModel.getBufferedValue(PROPERTY_APPL_USER_TYPE));
		return model;
	}


}
