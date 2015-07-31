package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import com.jgoodies.binding.PresentationModel;

import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.util.GuiUtil;

public class BokfSelskapModel extends
AbstractModel<BokfSelskap, BokfSelskapModel> {
	public static final String PROPERTY_SELSKAPNAVN="selskapnavn";
	public static final String PROPERTY_FAKTURA_NR_STRING="fakturaNrString";
	public static final String PROPERTY_TIL_PS_STRING="tilPsString";
	public static final String PROPERTY_NAVN="navn";
	public static final String PROPERTY_ADRESSE1="adresse1";
	public static final String PROPERTY_ADRESSE2="adresse2";
	public static final String PROPERTY_ADRESSE3="adresse3";
	public static final String PROPERTY_TELEFON="telefon";
	public static final String PROPERTY_TELEFAX="telefax";
	public static final String PROPERTY_ORG_NR="orgNr";
	public static final String PROPERTY_KONTONR="kontonr";
	public static final String PROPERTY_LOKASJONSNR="lokasjonsnr";
	public static final String PROPERTY_FILIAL_KONTO="filialKonto";
	public static final String PROPERTY_POSTNR="postnr";
	public static final String PROPERTY_POSTSTED="poststed";
	public static final String PROPERTY_ORGNR="orgnr";
	public static final String PROPERTY_MOMSID="momsid";

	public BokfSelskapModel(BokfSelskap object) {
		super(object);
	}
	
	public String getSelskapnavn(){
		return object.getSelskapNavn();
	}
	public void setSelskapnavn(String selskapnavn){
		String oldNavn=getSelskapnavn();
		object.setSelskapNavn(selskapnavn);
		firePropertyChange(PROPERTY_SELSKAPNAVN,oldNavn,selskapnavn);
	}
	public String getFakturaNrString(){
		if(object.getFakturaNr()!=null){
			return String.valueOf(object.getFakturaNr());
		}
		return null;
	}
	public void setFakturaNrString(String fakturaNr){
		String oldNr=getFakturaNrString();
		if(fakturaNr!=null){
			object.setFakturaNr(BigDecimal.valueOf(Long.valueOf(fakturaNr)));
		}else{
			object.setFakturaNr(null);
		}
		firePropertyChange(PROPERTY_FAKTURA_NR_STRING,oldNr ,fakturaNr);
	}
	public String getTilPsString(){
		if(GuiUtil.convertNumberToBoolean(object.getTilPs())){
			return "Ja";
		}
		return "Nei";
	}
	public void setTilPsString(String tilPs){
		String oldPs=getTilPsString();
		if(tilPs.equalsIgnoreCase("Ja")){
			object.setTilPs(1);
		}else{
			object.setTilPs(0);
		}
		firePropertyChange(PROPERTY_TIL_PS_STRING,oldPs,tilPs);
	}
	public String getNavn(){
		return object.getNavn();
	}
	public void setNavn(String navn){
		String oldNavn=getNavn();
			object.setNavn(navn);
		firePropertyChange(PROPERTY_NAVN,oldNavn  ,navn);
	}
	public String getAdresse1(){
		return object.getAdresse1();
	}
	public void setAdresse1(String adresse1){
		String oldAdresse=getAdresse1();
			object.setAdresse1(adresse1);
		firePropertyChange(PROPERTY_ADRESSE1,oldAdresse,adresse1);
	}
	public String getAdresse2(){
		return object.getAdresse2();
	}
	public void setAdresse2(String adresse2){
		String oldAdresse=getAdresse2();
			object.setAdresse2(adresse2);
		firePropertyChange(PROPERTY_ADRESSE2,oldAdresse,adresse2);
	}
	public String getAdresse3(){
		return object.getAdresse3();
	}
	public void setAdresse3(String adresse3){
		String oldAdresse=getAdresse3();
			object.setAdresse3(adresse3);
		firePropertyChange(PROPERTY_ADRESSE3,oldAdresse,adresse3);
	}
	public String getTelefon(){
		return object.getTelefon();
	}
	public void setTelefon(String telefon){
		String oldTlf=getTelefon();
			object.setTelefon(telefon);
		firePropertyChange(PROPERTY_TELEFON,oldTlf,telefon);
	}
	public String getTelefax(){
		return object.getTelefax();
	}
	public void setTelefax(String telefax){
		String oldTlf=getTelefax();
			object.setTelefax(telefax);
		firePropertyChange(PROPERTY_TELEFAX,oldTlf,telefax);
	}
	public String getOrgNr(){
		return object.getOrgNr();
	}
	public void setOrgNr(String orgNr){
		String oldNr=getOrgNr();
			object.setOrgNr(orgNr);
		firePropertyChange(PROPERTY_ORG_NR,oldNr,orgNr);
	}
	public String getKontonr(){
		return object.getKontonr();
	}
	public void setKontonr(String kontonr){
		String oldNr=getKontonr();
			object.setKontonr(kontonr);
		firePropertyChange(PROPERTY_KONTONR,oldNr,kontonr);
	}
	public String getLokasjonsnr(){
		return object.getLokasjonsnr();
	}
	public void setLokasjonsnr(String lokasjonsnr){
		String oldNr=getLokasjonsnr();
			object.setLokasjonsnr(lokasjonsnr);
		firePropertyChange(PROPERTY_LOKASJONSNR,oldNr,lokasjonsnr);
	}
	public String getFilialKonto(){
		return object.getFilialKonto();
	}
	public void setFilialKonto(String filailKonto){
		String oldNr=getFilialKonto();
			object.setFilialKonto(filailKonto);
		firePropertyChange(PROPERTY_FILIAL_KONTO,oldNr,filailKonto);
	}
	public String getPostnr(){
		return object.getPostnr();
	}
	public void setPostnr(String postnr){
		String oldNr=getPostnr();
			object.setPostnr(postnr);
		firePropertyChange(PROPERTY_POSTNR,oldNr,postnr);
	}
	public String getPoststed(){
		return object.getPoststed();
	}
	public void setPoststed(String poststed){
		String oldSted=getPoststed();
			object.setPoststed(poststed);
		firePropertyChange(PROPERTY_POSTSTED,oldSted,poststed);
	}
	public String getOrgnr(){
		return object.getOrgnr();
	}
	public void setOrgnr(String orgnr){
		String oldNr=getOrgnr();
			object.setOrgnr(orgnr);
		firePropertyChange(PROPERTY_ORGNR,oldNr,orgnr);
	}
	public String getMomsid(){
		return object.getMomsid();
	}
	public void setMomsid(String momsid){
		String oldMoms=getMomsid();
			object.setMomsid(momsid);
		firePropertyChange(PROPERTY_MOMSID,oldMoms,momsid);
	}


	@Override
	public BokfSelskapModel getBufferedObjectModel(PresentationModel presentationModel) {
		BokfSelskapModel model = new BokfSelskapModel(new BokfSelskap());
		model.setSelskapnavn((String)presentationModel.getBufferedValue(PROPERTY_SELSKAPNAVN));
		model.setFakturaNrString((String)presentationModel.getBufferedValue(PROPERTY_FAKTURA_NR_STRING));
		model.setTilPsString((String)presentationModel.getBufferedValue(PROPERTY_TIL_PS_STRING));
		model.setNavn((String)presentationModel.getBufferedValue(PROPERTY_NAVN));
		model.setAdresse1((String)presentationModel.getBufferedValue(PROPERTY_ADRESSE1));
		model.setAdresse2((String)presentationModel.getBufferedValue(PROPERTY_ADRESSE2));
		model.setAdresse3((String)presentationModel.getBufferedValue(PROPERTY_ADRESSE3));
		model.setTelefon((String)presentationModel.getBufferedValue(PROPERTY_TELEFON));
		model.setTelefax((String)presentationModel.getBufferedValue(PROPERTY_TELEFAX));
		model.setOrgNr((String)presentationModel.getBufferedValue(PROPERTY_ORG_NR));
		model.setKontonr((String)presentationModel.getBufferedValue(PROPERTY_KONTONR));
		model.setLokasjonsnr((String)presentationModel.getBufferedValue(PROPERTY_LOKASJONSNR));
		model.setFilialKonto((String)presentationModel.getBufferedValue(PROPERTY_FILIAL_KONTO));
		model.setPostnr((String)presentationModel.getBufferedValue(PROPERTY_POSTNR));
		model.setPoststed((String)presentationModel.getBufferedValue(PROPERTY_POSTSTED));
		model.setOrgnr((String)presentationModel.getBufferedValue(PROPERTY_ORGNR));
		model.setMomsid((String)presentationModel.getBufferedValue(PROPERTY_MOMSID));
		return model;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener, PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_SELSKAPNAVN).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_FAKTURA_NR_STRING).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_TIL_PS_STRING).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_NAVN).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ADRESSE1).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ADRESSE2).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ADRESSE3).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_TELEFON).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_TELEFAX).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ORG_NR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_KONTONR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_LOKASJONSNR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_FILIAL_KONTO).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_POSTNR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_POSTSTED).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ORGNR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_MOMSID).addValueChangeListener(listener);
	}

}
