package no.ica.tollpost.model;

import java.beans.PropertyChangeListener;

import com.jgoodies.binding.PresentationModel;

import no.ica.fraf.gui.model.AbstractModel;
import no.ica.fraf.gui.model.ApplUserModel;
import no.ica.fraf.model.ApplUser;

public class TgImportModel extends
AbstractModel<TgImport, TgImportModel> {

	public TgImportModel(TgImport object) {
		super(object);
	}

	@Override
	public TgImportModel getBufferedObjectModel(PresentationModel presentationModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener, PresentationModel presentationModel) {
		// TODO Auto-generated method stub
		
	}

}
