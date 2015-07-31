package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;

import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.PresentationModel;

public class AvdelingVModel extends
AbstractModel<AvdelingV, AvdelingVModel> {
	public static final String PROPERTY_JURIDISK_NAVN="juridiskNavn";
	public static final String PROPERTY_AVDELING_NAVN="avdelingNavn";
	public static final String PROPERTY_ORG_NR="orgNr";
	public static final String PROPERTY_ADR1="adr1";
	public static final String PROPERTY_ADR2="adr2";
	public static final String PROPERTY_POSTNR="postnr";
	public static final String PROPERTY_POSTSTED="poststed";
	public static final String PROPERTY_ANSVARLIG="ansvarlig";
	public static final String PROPERTY_FRANCHISETAKER="franchisetaker";
	public static final String PROPERTY_REGION="region";
	public static final String PROPERTY_SALGSSJEF="salgssjef";
	public static final String PROPERTY_SELSKAP_REGNSKAP="selskapRegnskap";
	public static final String PROPERTY_DT_START_STRING="dtStartString";
	public static final String PROPERTY_STATUS="status";
	public static final String PROPERTY_KONTRAKT_TYPE="kontraktType";
	public static final String PROPERTY_AVTALETYPE="avtaletype";
	public static final String PROPERTY_KJEDE="kjede";
	public static final String PROPERTY_NEDLAGT_STRING="nedlagtString";

	public AvdelingVModel(AvdelingV avdelingV) {
		super(avdelingV);
	}
	
	public String getJuridiskNavn(){
		return object.getJuridiskNavn();
		
	}
	public String getAvdelingNavn(){
		return object.getAvdelingNavn();
		
	}
	public String getOrgNr(){
		return object.getOrgNr();
		
	}
	public String getAdr1(){
		return object.getAdr1();
		
	}
	public String getAdr2(){
		return object.getAdr2();
		
	}
	public String getPostnr(){
		return object.getPostnr();
		
	}
	public String getPoststed(){
		return object.getPoststed();
		
	}
	public String getAnsvarlig(){
		return object.getAnsvarlig();
		
	}
	public String getFranchisetaker(){
		return object.getFranchisetaker();
	}
	public String getRegion(){
		return object.getRegion();
	}
	public String getSalgssjef(){
		return object.getSalgssjef();
	}
	public String getSelskapRegnskap(){
		return object.getSelskapRegnskap();
	}
	public String getDtStartString(){
		if(object.getDtStart()!=null){
		return GuiUtil.SIMPLE_DATE_FORMAT.format(object.getDtStart());
		}
		
		return null;
	}
	public String getNedlagtString(){
		if(object.getNedlagt()!=null){
		return GuiUtil.SIMPLE_DATE_FORMAT.format(object.getNedlagt());
		}
		
		return null;
	}
	public String getStatus(){
		return object.getStatus(); 
	}
	public String getKontraktType(){
		return object.getKontraktType(); 
	}
	public String getAvtaletype(){
		return object.getAvtaletype(); 
	}
	public String getKjede(){
		return object.getKjede(); 
	}

	@Override
	public AvdelingVModel getBufferedObjectModel(PresentationModel presentationModel) {
		return null;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener, PresentationModel presentationModel) {
	}

}
