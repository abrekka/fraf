package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;

import no.ica.fraf.model.Rik2AvdV;

import com.jgoodies.binding.PresentationModel;

public class Rik2AvdVModel extends
AbstractModel<Rik2AvdV, Rik2AvdVModel> {
	public static final String PROPERTY_NAVN="navn";

	public Rik2AvdVModel(Rik2AvdV rik2AvdV) {
		super(rik2AvdV);
	}
	public String getNavn(){
		return object.getNavn();
	}
	public void setNavn(String navn){
		String oldNavn=getNavn();
		object.setNavn(navn);
		firePropertyChange(PROPERTY_NAVN,oldNavn,navn);
	}

	@Override
	public Rik2AvdVModel getBufferedObjectModel(PresentationModel presentationModel) {
		Rik2AvdVModel model = new Rik2AvdVModel(new Rik2AvdV());
		model.setNavn((String)presentationModel.getBufferedValue(PROPERTY_NAVN));
		return model;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener, PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_NAVN).addValueChangeListener(listener);
		
	}

}
