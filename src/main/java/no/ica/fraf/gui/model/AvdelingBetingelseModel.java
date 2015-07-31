package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.PresentationModel;

public class AvdelingBetingelseModel extends
AbstractModel<AvdelingBetingelse, AvdelingBetingelseModel> {
	public static final String PROPERTY_BETINGELSE_TYPE="betingelseType";
	public static final String PROPERTY_FRA_DATO="fraDato";
	public static final String PROPERTY_TIL_DATO="tilDato";
	public static final String PROPERTY_SPEILET_BOOL="speiletBool";
	public static final String PROPERTY_SATS_STRING="satsString";
	public static final String PROPERTY_SATS_FRA_BELOP_STRING="satsFraBelopString";
	public static final String PROPERTY_SATS_TIL_BELOP_STRING="satsTilBelopString";
	public static final String PROPERTY_BELOP_STRING="belopString";
	public static final String PROPERTY_BELOP="belop";
	public static final String PROPERTY_AVREGNING_FREKVENS_TYPE="avregningFrekvensType";
	public static final String PROPERTY_AVREGNING_TYPE="avregningType";
	public static final String PROPERTY_TEKST="tekst";
	public static final String PROPERTY_BOKF_SELSKAP="bokfSelskap";
	public static final String PROPERTY_BOKF_AVDELING="bokfAvdeling";
	public static final String PROPERTY_FAKTURA_TEKST="fakturaTekst";
	public static final String PROPERTY_FAKTURA_TEKST_REK_STRING="fakturaTekstRekString";

	public AvdelingBetingelseModel(AvdelingBetingelse object) {
		super(object);
	}
	
	public BetingelseType getBetingelseType(){
		return object.getBetingelseType();
	}
	public void setBetingelseType(BetingelseType betingelseType){
		BetingelseType oldType=getBetingelseType();
		object.setBetingelseType(betingelseType);
		firePropertyChange(PROPERTY_BETINGELSE_TYPE,oldType,betingelseType);
	}
	public Date getFraDato(){
		return object.getFraDato();
	}
	public void setFraDato(Date fraDato){
		Date oldDate=getFraDato();
		if(fraDato!=null){
			try {
				object.setFraDato(GuiUtil.SIMPLE_DATE_FORMAT.parse(GuiUtil.SIMPLE_DATE_FORMAT.format(fraDato)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		object.setFraDato(null);
		}
		firePropertyChange(PROPERTY_FRA_DATO,oldDate,fraDato);
	}
	public Date getTilDato(){
		return object.getTilDato();
	}
	public void setTilDato(Date tilDato){
		Date oldDate=getTilDato();
		if(tilDato!=null){
			try {
				object.setTilDato(GuiUtil.SIMPLE_DATE_FORMAT.parse(GuiUtil.SIMPLE_DATE_FORMAT.format(tilDato)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		object.setTilDato(null);
		}
		firePropertyChange(PROPERTY_TIL_DATO,oldDate,tilDato);
	}
	public Boolean getSpeiletBool(){
		return GuiUtil.convertNumberToBoolean(object.getSpeilet());
	}
	public void setSpeiletBool(Boolean speilet){
		Boolean oldSpeil=getSpeiletBool();
		object.setSpeilet(GuiUtil.convertBooleanToNumber(speilet));
		firePropertyChange(PROPERTY_SPEILET_BOOL,oldSpeil,speilet);
	}
	public String getSatsString(){
		if(object.getSats()!=null){
			return String.valueOf(object.getSats());
		}
		return null;
	}
	public void setSatsString(String sats){
		String oldSats=getSatsString();
		if(sats!=null){
			object.setSats(BigDecimal.valueOf(Double.valueOf(sats.replaceAll(",","."))));
		}else{
			object.setSats(null);
		}
		firePropertyChange(PROPERTY_SATS_STRING,oldSats,sats);
	}
	public String getSatsFraBelopString(){
		if(object.getSatsFraBelop()!=null){
			return String.valueOf(object.getSatsFraBelop());
		}
		return null;
	}
	public void setSatsFraBelopString(String satsFraBelop){
		String oldSats=getSatsFraBelopString();
		if(satsFraBelop!=null){
			object.setSatsFraBelop(BigDecimal.valueOf(Double.valueOf(satsFraBelop.replaceAll(",","."))));
		}else{
			object.setSatsFraBelop(null);
		}
		firePropertyChange(PROPERTY_SATS_FRA_BELOP_STRING,oldSats,satsFraBelop);
	}
	public String getSatsTilBelopString(){
		if(object.getSatsTilBelop()!=null){
			return String.valueOf(object.getSatsTilBelop());
		}
		return null;
	}
	public void setSatsTilBelopString(String satsTilBelop){
		String oldSats=getSatsTilBelopString();
		if(satsTilBelop!=null){
			object.setSatsTilBelop(BigDecimal.valueOf(Double.valueOf(satsTilBelop.replaceAll(",","."))));
		}else{
			object.setSatsTilBelop(null);
		}
		firePropertyChange(PROPERTY_SATS_TIL_BELOP_STRING,oldSats,satsTilBelop);
	}
	public String getBelopString(){
		if(object.getBelop()!=null){
			return String.valueOf(object.getBelop());
		}
		return null;
	}
	public void setBelopString(String belop){
		String oldBelop=getBelopString();
		if(belop!=null&&belop.length()!=0){
			object.setBelop(BigDecimal.valueOf(Double.valueOf(belop.replaceAll(",","."))));
		}else{
			object.setBelop(null);
		}
		firePropertyChange(PROPERTY_BELOP_STRING,oldBelop,belop);
	}
	public BigDecimal getBelop(){
		return object.getBelop();
	}
	public void setBelop(BigDecimal belop){
		if(belop!=null){
		setBelopString(String.valueOf(belop));
		}else{
			setBelopString(null);
		}
	}
	public AvregningFrekvensType getAvregningFrekvensType(){
		return object.getAvregningFrekvensType();
	}
	public void setAvregningFrekvensType(AvregningFrekvensType avregningFrekvensType){
		AvregningFrekvensType oldType=getAvregningFrekvensType();
			object.setAvregningFrekvensType(avregningFrekvensType);
		firePropertyChange(PROPERTY_AVREGNING_FREKVENS_TYPE,oldType,avregningFrekvensType);
	}
	public AvregningType getAvregningType(){
		return object.getAvregningType();
	}
	public void setAvregningType(AvregningType avregningType){
		AvregningType oldType=getAvregningType();
			object.setAvregningType(avregningType);
		firePropertyChange(PROPERTY_AVREGNING_TYPE,oldType,avregningType);
	}
	public String getTekst(){
		return object.getTekst();
	}
	public void setTekst(String tekst){
		String oldTekst=getTekst();
			object.setTekst(tekst);
		firePropertyChange(PROPERTY_TEKST,oldTekst,tekst);
	}
	public BokfSelskap getBokfSelskap(){
		return object.getBokfSelskap();
	}
	public void setBokfSelskap(BokfSelskap bokfSelskap){
		BokfSelskap oldSelskap=getBokfSelskap();
			object.setBokfSelskap(bokfSelskap);
		firePropertyChange(PROPERTY_BOKF_SELSKAP,oldSelskap,bokfSelskap);
	}
	public String getBokfAvdeling(){
		return object.getBokfAvdeling();
	}
	public void setBokfAvdeling(String bokfAvdeling){
		String oldAvdeling=getBokfAvdeling();
			object.setBokfAvdeling(bokfAvdeling);
		firePropertyChange(PROPERTY_BOKF_AVDELING,oldAvdeling,bokfAvdeling);
	}
	public String getFakturaTekst(){
		return object.getFakturaTekst();
	}
	public void setFakturaTekst(String fakturaTekst){
		String oldTekst=getFakturaTekst();
			object.setFakturaTekst(fakturaTekst);
		firePropertyChange(PROPERTY_FAKTURA_TEKST,oldTekst,fakturaTekst);
	}
	public String getFakturaTekstRekString(){
		if(object.getFakturaTekstRek()!=null){
		return String.valueOf(object.getFakturaTekstRek());
		}
		return null;
	}
	public void setFakturaTekstRekString(String fakturaTekstRek){
		String oldRek=getFakturaTekstRekString();
		if(fakturaTekstRek!=null){
			object.setFakturaTekstRek(Integer.valueOf(fakturaTekstRek));
		}else{
			object.setFakturaTekst(null);
		}
		firePropertyChange(PROPERTY_FAKTURA_TEKST_REK_STRING,oldRek,fakturaTekstRek);
	}

	@Override
	public AvdelingBetingelseModel getBufferedObjectModel(PresentationModel presentationModel) {
		AvdelingBetingelseModel model = new AvdelingBetingelseModel(
				new AvdelingBetingelse());
		model.setBetingelseType((BetingelseType) presentationModel
				.getBufferedValue(PROPERTY_BETINGELSE_TYPE));
		model.setFraDato((Date) presentationModel
				.getBufferedValue(PROPERTY_FRA_DATO));
		model.setTilDato((Date) presentationModel
				.getBufferedValue(PROPERTY_TIL_DATO));
		model.setSpeiletBool((Boolean) presentationModel
				.getBufferedValue(PROPERTY_SPEILET_BOOL));
		model.setSatsString((String) presentationModel
				.getBufferedValue(PROPERTY_SATS_STRING));
		model.setSatsFraBelopString((String) presentationModel
				.getBufferedValue(PROPERTY_SATS_FRA_BELOP_STRING));
		model.setSatsTilBelopString((String) presentationModel
				.getBufferedValue(PROPERTY_SATS_TIL_BELOP_STRING));
		model.setBelopString((String) presentationModel
				.getBufferedValue(PROPERTY_BELOP_STRING));
		model.setAvregningFrekvensType((AvregningFrekvensType) presentationModel
				.getBufferedValue(PROPERTY_AVREGNING_FREKVENS_TYPE));
		model.setAvregningType((AvregningType) presentationModel
				.getBufferedValue(PROPERTY_AVREGNING_TYPE));
		model.setTekst((String) presentationModel
				.getBufferedValue(PROPERTY_TEKST));
		model.setBokfSelskap((BokfSelskap) presentationModel
				.getBufferedValue(PROPERTY_BOKF_SELSKAP));
		model.setBokfAvdeling((String) presentationModel
				.getBufferedValue(PROPERTY_BOKF_AVDELING));
		model.setFakturaTekst((String) presentationModel
				.getBufferedValue(PROPERTY_FAKTURA_TEKST));
		return model;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener, PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_BETINGELSE_TYPE)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_FRA_DATO)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_TIL_DATO)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SPEILET_BOOL)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SATS_STRING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SATS_FRA_BELOP_STRING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SATS_TIL_BELOP_STRING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BELOP_STRING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_AVREGNING_FREKVENS_TYPE)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_AVREGNING_TYPE)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_TEKST)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BOKF_SELSKAP)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BOKF_AVDELING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_FAKTURA_TEKST)
		.addValueChangeListener(listener);
		
	}

}
