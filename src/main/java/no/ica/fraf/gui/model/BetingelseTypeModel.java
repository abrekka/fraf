package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Mva;
import no.ica.fraf.model.Rik2KjedeV;

import com.jgoodies.binding.PresentationModel;

public class BetingelseTypeModel extends
		AbstractModel<BetingelseType, BetingelseTypeModel> {
	public static final String PROPERTY_BETINGELSE_TYPE_KODE = "betingelseTypeKode";

	public static final String PROPERTY_BETINGELSE_NAVN = "betingelseNavn";

	//public static final String PROPERTY_IS_DEBIT_BOOL = "isDebitBool";

	public static final String PROPERTY_MVA_E = "mvaE";
	public static final String PROPERTY_MVA_F = "mvaF";

	public static final String PROPERTY_BETINGELSE_GRUPPE = "betingelseGruppe";

	public static final String PROPERTY_INNTEKTSKONTO_E = "inntektskontoE";
	public static final String PROPERTY_INNTEKTSKONTO_F = "inntektskontoF";
	public static final String PROPERTY_XML_KONTO_E = "xmlKontoE";
	public static final String PROPERTY_XML_KONTO_F = "xmlKontoF";
	public static final String PROPERTY_XML_MVA_E = "xmlMvaE";
	public static final String PROPERTY_XML_MVA_F = "xmlMvaF";
	public static final String PROPERTY_BOKF_SELSKAP = "bokfSelskap";
	public static final String PROPERTY_CHAIN = "chain";
	public static final String PROPERTY_AVREGNING_FREKVENS_TYPE = "avregningFrekvensType";
	public static final String PROPERTY_AVREGNING_TYPE = "avregningType";
	public static final String PROPERTY_FAKTURA_TEKST = "fakturaTekst";
	public static final String PROPERTY_REKKEFOLGE = "rekkefølge";
	public static final String PROPERTY_SATS = "sats";
	public static final String PROPERTY_BELOP = "belop";
	public static final String PROPERTY_BETINGELSE_TYPE_ID = "betingelseTypeId";
	public static final String PROPERTY_BELOP_PR_STK = "belopPrStk";
	public static final String PROPERTY_BOKF_AVDELING_E = "bokfAvdelingE";
	//public static final String PROPERTY_FOR_FILIAL_STRING = "forFilialString";
	//public static final String PROPERTY_FOR_FILIAL = "forFilial";
	public static final String PROPERTY_XML_MVATYPE_E = "xmlMvatypeE";
	public static final String PROPERTY_XML_MVATYPE_F = "xmlMvatypeF";
	public static final String PROPERTY_XML_VATUSETYPE_E = "xmlVatusetypeE";
	public static final String PROPERTY_XML_VATUSETYPE_F = "xmlVatusetypeF";
	public static final String PROPERTY_LINK_AVTALE_STRING = "linkAvtaleString";
	public static final String PROPERTY_LINK_AVTALE = "linkAvtale";
	public static final String PROPERTY_INAKTIV_STRING = "inaktivString";
	public static final String PROPERTY_BOKF_AVDELING_F = "bokfAvdelingF";

	public BetingelseTypeModel(BetingelseType object) {
		super(object);
	}

	public String getBetingelseTypeKode() {
		return object.getBetingelseTypeKode();
	}

	public void setBetingelseTypeKode(String betingelseTypeKode) {
		String oldKode = getBetingelseTypeKode();
		object.setBetingelseTypeKode(betingelseTypeKode);
		firePropertyChange(PROPERTY_BETINGELSE_TYPE_KODE, oldKode,
				betingelseTypeKode);
	}

	public String getBetingelseNavn() {
		return object.getBetingelseNavn();
	}

	public void setBetingelseNavn(String betingelseNavn) {
		String oldNavn = getBetingelseNavn();
		object.setBetingelseNavn(betingelseNavn);
		firePropertyChange(PROPERTY_BETINGELSE_NAVN, oldNavn, betingelseNavn);
	}
	
	public Integer getBetingelseTypeId() {
		return object.getBetingelseTypeId();
	}

	public void setBetingelseTypeId(Integer betingelseTypeId) {
		Integer oldId = getBetingelseTypeId();
		object.setBetingelseTypeId(betingelseTypeId);
		firePropertyChange(PROPERTY_BETINGELSE_TYPE_ID, oldId, betingelseTypeId);
	}

	/*public Boolean getIsDebitBool() {
		if(object.getIsDebit()==null){
			object.setIsDebit(0);
		}
		return GuiUtil.convertNumberToBoolean(object.getIsDebit());
	}

	public void setIsDebitBool(Boolean isDebitBool) {
		Boolean oldDebit = getIsDebitBool();
		object.setIsDebit(GuiUtil.convertBooleanToNumber(isDebitBool));
		firePropertyChange(PROPERTY_IS_DEBIT_BOOL, oldDebit, isDebitBool);
	}*/

	public Mva getMvaE() {
		return object.getMvaE();
	}

	public void setMvaE(Mva mva) {
		Mva oldMva = getMvaE();
		object.setMvaE(mva);
		firePropertyChange(PROPERTY_MVA_E, oldMva, mva);
	}
	public Mva getMvaF() {
		return object.getMvaF();
	}

	public void setMvaF(Mva mva) {
		Mva oldMva = getMvaF();
		object.setMvaF(mva);
		firePropertyChange(PROPERTY_MVA_F, oldMva, mva);
	}

	public BetingelseGruppe getBetingelseGruppe() {
		return object.getBetingelseGruppe();
	}

	public void setBetingelseGruppe(BetingelseGruppe betingelseGruppe) {
		BetingelseGruppe oldBetingelseGruppe = getBetingelseGruppe();
		object.setBetingelseGruppe(betingelseGruppe);
		firePropertyChange(PROPERTY_BETINGELSE_GRUPPE, oldBetingelseGruppe,
				betingelseGruppe);
	}
	
	public String getInntektskontoE() {
		return object.getInntektskontoE();
	}

	public void setInntektskontoE(String konto) {
		String oldKonto = getInntektskontoE();
		object.setInntektskontoE(konto);
		firePropertyChange(PROPERTY_INNTEKTSKONTO_E, oldKonto, konto);
	}
	public String getInntektskontoF() {
		return object.getInntektskontoF();
	}

	public void setInntektskontoF(String konto) {
		String oldKonto = getInntektskontoF();
		object.setInntektskontoF(konto);
		firePropertyChange(PROPERTY_INNTEKTSKONTO_F, oldKonto, konto);
	}
	public String getXmlKontoE() {
		return object.getXmlKontoE();
	}

	public void setXmlKontoE(String xmlKonto) {
		String oldKonto = getXmlKontoE();
		object.setXmlKontoE(xmlKonto);
		firePropertyChange(PROPERTY_XML_KONTO_E, oldKonto, xmlKonto);
	}
	public String getXmlKontoF() {
		return object.getXmlKontoF();
	}

	public void setXmlKontoF(String xmlKonto) {
		String oldKonto = getXmlKontoF();
		object.setXmlKontoF(xmlKonto);
		firePropertyChange(PROPERTY_XML_KONTO_F, oldKonto, xmlKonto);
	}
	public Mva getXmlMvaE() {
		return object.getXmlMvaE();
	}

	public void setXmlMvaE(Mva xmlMva) {
		Mva oldMva = getXmlMvaE();
		object.setXmlMvaE(xmlMva);
		firePropertyChange(PROPERTY_XML_MVA_E, oldMva, xmlMva);
	}
	public Mva getXmlMvaF() {
		return object.getXmlMvaF();
	}

	public void setXmlMvaF(Mva xmlMva) {
		Mva oldMva = getXmlMvaF();
		object.setXmlMvaF(xmlMva);
		firePropertyChange(PROPERTY_XML_MVA_F, oldMva, xmlMva);
	}
	public BokfSelskap getBokfSelskap() {
		return object.getBokfSelskap();
	}

	public void setBokfSelskap(BokfSelskap bokfSelskap) {
		BokfSelskap oldSelskap = getBokfSelskap();
		object.setBokfSelskap(bokfSelskap);
		firePropertyChange(PROPERTY_BOKF_SELSKAP, oldSelskap, bokfSelskap);
	}
	public Chain getChain() {
		return object.getChain();
	}

	public void setChain(Chain chain) {
		Chain oldChain = getChain();
		object.setChain(chain);
		firePropertyChange(PROPERTY_CHAIN, oldChain, chain);
	}
	public AvregningFrekvensType getAvregningFrekvensType() {
		return object.getAvregningFrekvensType();
	}

	public void setAvregningFrekvensType(AvregningFrekvensType avregningFrekvensType) {
		AvregningFrekvensType oldAvregningFrekvensType = getAvregningFrekvensType();
		object.setAvregningFrekvensType(avregningFrekvensType);
		firePropertyChange(PROPERTY_AVREGNING_FREKVENS_TYPE, oldAvregningFrekvensType, avregningFrekvensType);
	}
	public AvregningType getAvregningType() {
		return object.getAvregningType();
	}

	public void setAvregningType(AvregningType avregningType) {
		AvregningType oldAvregningType = getAvregningType();
		object.setAvregningType(avregningType);
		firePropertyChange(PROPERTY_AVREGNING_TYPE, oldAvregningType, avregningType);
	}
	public String getFakturaTekst() {
		return object.getFakturaTekst();
	}

	public void setFakturaTekst(String fakturaTekst) {
		String oldTekst = getFakturaTekst();
		object.setFakturaTekst(fakturaTekst);
		firePropertyChange(PROPERTY_FAKTURA_TEKST, oldTekst, fakturaTekst);
	}
	public String getRekkefølge() {
		if(object.getFakturaTekstRek()!=null){
		return object.getFakturaTekstRek().toString();
		}
		return null;
	}

	public void setRekkefølge(String rekkefølge) {
		String oldRekkefølge = getRekkefølge();
		if(rekkefølge!=null){
		object.setFakturaTekstRek(Integer.valueOf(rekkefølge));
		}else{
			object.setFakturaTekstRek(null);
		}
		firePropertyChange(PROPERTY_REKKEFOLGE, oldRekkefølge, rekkefølge);
	}
	public String getSats() {
		if(object.getSats()!=null){
		return object.getSats().toString();
		}
		return null;
	}

	public void setSats(String sats) {
		String oldSats = getSats();
		if(sats!=null&&sats.length()!=0){
		object.setSats(BigDecimal.valueOf(Double.valueOf(sats.replaceAll(",","."))));
		}else{
			object.setSats(null);
		}
		firePropertyChange(PROPERTY_SATS, oldSats, sats);
	}
	public String getBelop() {
		if(object.getBelop()!=null){
		return object.getBelop().toString();
		}
		return null;
	}

	public void setBelop(String belop) {
		String oldBelop = getBelop();
		if(belop!=null&&belop.length()!=0){
		object.setBelop(BigDecimal.valueOf(Double.valueOf(belop.replaceAll(",","."))));
		}else{
			object.setBelop(null);
		}
		firePropertyChange(PROPERTY_BELOP, oldBelop, belop);
	}

	public String getBelopPrStk() {
		if(object.getBelopPrStk()!=null){
		return object.getBelopPrStk().toString();
		}
		return null;
	}

	public void setBelopPrStk(String belopPrStk) {
		String oldBelop = getBelopPrStk();
		if(belopPrStk!=null&&belopPrStk.length()!=0){
		object.setBelopPrStk(BigDecimal.valueOf(Double.valueOf(belopPrStk.replaceAll(",","."))));
		}else{
			object.setBelopPrStk(null);
		}
		firePropertyChange(PROPERTY_BELOP_PR_STK, oldBelop, belopPrStk);
	}
	public String getBokfAvdelingE(){
		return object.getBokfAvdelingE();
	}
	public void setBokfAvdelingE(String bokfAvdeling){
		String oldAvdeling = getBokfAvdelingE();
		object.setBokfAvdelingE(bokfAvdeling);
		firePropertyChange(PROPERTY_BOKF_AVDELING_E,oldAvdeling,bokfAvdeling);
	}
	public String getBokfAvdelingF(){
		return object.getBokfAvdelingF();
	}
	public void setBokfAvdelingF(String bokfAvdeling){
		String oldAvdeling = getBokfAvdelingF();
		object.setBokfAvdelingF(bokfAvdeling);
		firePropertyChange(PROPERTY_BOKF_AVDELING_F,oldAvdeling,bokfAvdeling);
	}
	/*public String getForFilialString(){
		if(object.getForFilial()!=null&&object.getForFilial()==1){
			return "Ja";
		}
		return "Nei";
	}*/
	/*public void setForFilialString(String forFilial){
		String oldFilial = getForFilialString();
		
		if(forFilial!=null&&forFilial.equalsIgnoreCase("Ja")){
			object.setForFilial(1);
		
		}else{
			object.setForFilial(0);
		
		}
		firePropertyChange(PROPERTY_FOR_FILIAL_STRING,oldFilial,forFilial);
	}*/
	
	public Integer getLinkAvtale(){
		return object.getLinkAvtale();
	}
	public void setLinkAvtale(Integer linkAvtale){
		Integer oldLink =getLinkAvtale();
		object.setLinkAvtale(linkAvtale);
		firePropertyChange(PROPERTY_LINK_AVTALE,oldLink,linkAvtale);
	}
	
	public String getLinkAvtaleString(){
		if(object.getLinkAvtale()!=null&&object.getLinkAvtale()==1){
			return "Ja";
		}else if(object.getLinkAvtale()!=null&&object.getLinkAvtale()!=1){
		return "Nei";
		}
		return null;
	}
	public void setLinkAvtaleString(String linkAvtale){
		String oldLink = getLinkAvtaleString();
		
		if(linkAvtale!=null&&linkAvtale.equalsIgnoreCase("Ja")){
			object.setLinkAvtale(1);
		
		}else{
			object.setLinkAvtale(0);
		
		}
		firePropertyChange(PROPERTY_LINK_AVTALE_STRING,oldLink,linkAvtale);
	}
	
	public String getInaktivString(){
		if(object.getInaktiv()!=null&&object.getInaktiv()==1){
			return "Ja";
		}else if(object.getInaktiv()!=null&&object.getInaktiv()!=1){
		return "Nei";
		}
		return "Nei";
	}
	public void setInaktivString(String inaktiv){
		String oldInaktiv = getInaktivString();
		
		if(inaktiv!=null&&inaktiv.equalsIgnoreCase("Ja")){
			object.setInaktiv(1);
		
		}else{
			object.setInaktiv(0);
		
		}
		firePropertyChange(PROPERTY_INAKTIV_STRING,oldInaktiv,inaktiv);
	}

	/*public Integer getForFilial(){
		return object.getForFilial();
	}*/
	/*public void setForFilial(Integer forFilial){
		Integer oldFilial = getForFilial();
		object.setForFilial(forFilial);
		firePropertyChange(PROPERTY_FOR_FILIAL,oldFilial,forFilial);
	}*/
	public String getXmlMvatypeE(){
		return object.getXmlMvatypeE();
	}
	public void setXmlMvatypeE(String xmlMvatype){
		String oldMva = getXmlMvatypeE();
		object.setXmlMvatypeE(xmlMvatype);
		firePropertyChange(PROPERTY_XML_MVATYPE_E,oldMva,xmlMvatype);
	}
	public String getXmlMvatypeF(){
		return object.getXmlMvatypeF();
	}
	public void setXmlMvatypeF(String xmlMvatype){
		String oldMva = getXmlMvatypeF();
		object.setXmlMvatypeF(xmlMvatype);
		firePropertyChange(PROPERTY_XML_MVATYPE_F,oldMva,xmlMvatype);
	}
	public String getXmlVatusetypeE(){
		return object.getXmlVatusetypeE();
	}
	public void setXmlVatusetypeE(String xmlVatusetype){
		String oldVat = getXmlVatusetypeE();
		object.setXmlVatusetypeE(xmlVatusetype);
		firePropertyChange(PROPERTY_XML_VATUSETYPE_E,oldVat,xmlVatusetype);
	}
	public String getXmlVatusetypeF(){
		return object.getXmlVatusetypeF();
	}
	public void setXmlVatusetypeF(String xmlVatusetype){
		String oldVat = getXmlVatusetypeF();
		object.setXmlVatusetypeF(xmlVatusetype);
		firePropertyChange(PROPERTY_XML_VATUSETYPE_F,oldVat,xmlVatusetype);
	}

	@Override
	public BetingelseTypeModel getBufferedObjectModel(
			PresentationModel presentationModel) {
		BetingelseTypeModel model = new BetingelseTypeModel(
				new BetingelseType());
		model.setBetingelseTypeKode((String) presentationModel
				.getBufferedValue(PROPERTY_BETINGELSE_TYPE_KODE));
		model.setBetingelseNavn((String) presentationModel
				.getBufferedValue(PROPERTY_BETINGELSE_NAVN));
		model.setMvaE((Mva) presentationModel.getBufferedValue(PROPERTY_MVA_E));
		model.setMvaF((Mva) presentationModel.getBufferedValue(PROPERTY_MVA_F));
		model.setBetingelseGruppe((BetingelseGruppe) presentationModel
				.getBufferedValue(PROPERTY_BETINGELSE_GRUPPE));
		model.setInntektskontoE((String) presentationModel
				.getBufferedValue(PROPERTY_INNTEKTSKONTO_E));
		model.setInntektskontoF((String) presentationModel
				.getBufferedValue(PROPERTY_INNTEKTSKONTO_F));
		model.setXmlKontoE((String) presentationModel
				.getBufferedValue(PROPERTY_XML_KONTO_E));
		model.setXmlKontoF((String) presentationModel
				.getBufferedValue(PROPERTY_XML_KONTO_F));
		model.setXmlMvaE((Mva) presentationModel.getBufferedValue(PROPERTY_XML_MVA_E));
		model.setXmlMvaF((Mva) presentationModel.getBufferedValue(PROPERTY_XML_MVA_F));
		model.setBokfSelskap((BokfSelskap) presentationModel.getBufferedValue(PROPERTY_BOKF_SELSKAP));
		model.setChain((Chain) presentationModel.getBufferedValue(PROPERTY_CHAIN));
		model.setAvregningFrekvensType((AvregningFrekvensType) presentationModel.getBufferedValue(PROPERTY_AVREGNING_FREKVENS_TYPE));
		model.setAvregningType((AvregningType) presentationModel.getBufferedValue(PROPERTY_AVREGNING_TYPE));
		model.setFakturaTekst((String) presentationModel.getBufferedValue(PROPERTY_FAKTURA_TEKST));
		model.setRekkefølge((String) presentationModel.getBufferedValue(PROPERTY_REKKEFOLGE));
		model.setSats((String) presentationModel.getBufferedValue(PROPERTY_SATS));
		model.setBelop((String) presentationModel.getBufferedValue(PROPERTY_BELOP));
		model.setBetingelseTypeId((Integer) presentationModel.getBufferedValue(PROPERTY_BETINGELSE_TYPE_ID));
		model.setAvregningFrekvensType((AvregningFrekvensType) presentationModel.getBufferedValue(PROPERTY_AVREGNING_FREKVENS_TYPE));
		model.setBelopPrStk((String) presentationModel.getBufferedValue(PROPERTY_BELOP_PR_STK));
		model.setBokfAvdelingE((String) presentationModel.getBufferedValue(PROPERTY_BOKF_AVDELING_E));
		//model.setForFilial((Integer) presentationModel.getBufferedValue(PROPERTY_FOR_FILIAL));
		//model.setForFilialString((String) presentationModel.getBufferedValue(PROPERTY_FOR_FILIAL_STRING));
		model.setXmlMvatypeE((String) presentationModel.getBufferedValue(PROPERTY_XML_MVATYPE_E));
		model.setXmlMvatypeF((String) presentationModel.getBufferedValue(PROPERTY_XML_MVATYPE_F));
		model.setXmlVatusetypeE((String) presentationModel.getBufferedValue(PROPERTY_XML_VATUSETYPE_E));
		model.setXmlVatusetypeF((String) presentationModel.getBufferedValue(PROPERTY_XML_VATUSETYPE_F));
		model.setLinkAvtaleString((String) presentationModel.getBufferedValue(PROPERTY_LINK_AVTALE_STRING));
		model.setInaktivString((String) presentationModel.getBufferedValue(PROPERTY_INAKTIV_STRING));
		model.setBokfAvdelingF((String) presentationModel.getBufferedValue(PROPERTY_BOKF_AVDELING_F));
		return model;
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener,
			PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_BETINGELSE_TYPE_KODE)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BETINGELSE_NAVN)
				.addValueChangeListener(listener);
		//presentationModel.getBufferedModel(PROPERTY_IS_DEBIT_BOOL).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_MVA_E).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_MVA_F).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BETINGELSE_GRUPPE)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_INNTEKTSKONTO_E).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_INNTEKTSKONTO_F).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_KONTO_E).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_KONTO_F).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_MVA_E).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_MVA_F).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BOKF_SELSKAP)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_CHAIN)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_AVREGNING_FREKVENS_TYPE)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_AVREGNING_TYPE)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_FAKTURA_TEKST)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_REKKEFOLGE)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SATS)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BELOP)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BETINGELSE_TYPE_ID)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BELOP_PR_STK)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BOKF_AVDELING_E)
		.addValueChangeListener(listener);
		//presentationModel.getBufferedModel(PROPERTY_FOR_FILIAL).addValueChangeListener(listener);
		//presentationModel.getBufferedModel(PROPERTY_FOR_FILIAL_STRING).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_MVATYPE_E).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_MVATYPE_F).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_VATUSETYPE_E).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_XML_VATUSETYPE_F).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_LINK_AVTALE_STRING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_INAKTIV_STRING)
		.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BOKF_AVDELING_F)
		.addValueChangeListener(listener);
	}

}
