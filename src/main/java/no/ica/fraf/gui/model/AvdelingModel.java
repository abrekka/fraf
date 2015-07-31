package no.ica.fraf.gui.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvdelingMangel;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Department;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.PresentationModel;

public class AvdelingModel extends
AbstractModel<Avdeling, AvdelingModel> {
	public static final String PROPERTY_AVDNR="avdnr";
	public static final String PROPERTY_AVDELING_ID="avdelingId";
	public static final String PROPERTY_NAVN="navn";
	public static final String PROPERTY_FRANCHISETAKER="franchisetaker";
	public static final String PROPERTY_KID="kid";
	public static final String PROPERTY_BOKF_SELSKAP="bokfSelskap";
	public static final String PROPERTY_OPPRETTET_DATO="opprettetDato";
	public static final String PROPERTY_NR_OF_ADENDUM="nrOfAdendum";
	public static final String PROPERTY_KOMMENTAR="kommentar";
	public static final String PROPERTY_PIB_BOOLEAN="pibBoolean";
	public static final String PROPERTY_ARCHIVE_INFO="archiveInfo";
	public static final String PROPERTY_AVDELING_MANGEL_LIST="avdelingMangelList";
	public static final String PROPERTY_DEPARTMENT="department";
	//public static final String PROPERTY_CHAIN="chain";
	public static final String PROPERTY_CHAIN_NAME="chainName";
	public static final String PROPERTY_LAST_KONTRAKT="lastKontrakt";
	public static final String PROPERTY_AVDELING_BETINGELSE="avdelingBetingelse";
	//public static final String PROPERTY_FILIAL="filial";
	
	public static final String PROPERTY_AVDELING_BETINGELSE_LIST="avdelingBetingelseList";
	
	
	
	private final List<AvdelingMangel> avdelingMangelList;
	private final String nrOfAddendum;
	private List<AvdelingBetingelse> avdelingBetingelseList;

	public AvdelingModel(Avdeling avdeling) {
		super(avdeling);
		avdelingMangelList = new ArrayList<AvdelingMangel>();
		if(avdeling.getAvdelingMangels()!=null){
			avdelingMangelList.addAll(avdeling.getAvdelingMangels());
		}
		if(avdeling.getAdendums()!=null){
			nrOfAddendum=String.valueOf(object.getAdendums().size());
		}else{
		 nrOfAddendum= "0";
		}
		
	}
	
	public List<AvdelingBetingelse> getAvdelingBetingelseList(){
		if(avdelingBetingelseList==null){
			avdelingBetingelseList = new ArrayList<AvdelingBetingelse>();
			if(object.getAvdelingBetingelses()!=null){
				avdelingBetingelseList.addAll(object.getAvdelingBetingelses());
			}
		}
		return new ArrayList<AvdelingBetingelse>(avdelingBetingelseList);
	}
	public void SetAvdelingBetingelseList(List<AvdelingBetingelse> avdelingBetingelseList){
		List<AvdelingBetingelse> oldList = getAvdelingBetingelseList();
		this.avdelingBetingelseList.clear();
		this.avdelingBetingelseList.addAll(avdelingBetingelseList);
		firePropertyChange(PROPERTY_AVDELING_BETINGELSE_LIST,oldList,avdelingBetingelseList);
		
	}
	
	public Integer getAvdnr(){
		return object.getAvdnr();
	}
	public void setAvdnr(Integer avdnr){
		Integer oldNr=getAvdnr();
		object.setAvdnr(avdnr);
		firePropertyChange(PROPERTY_AVDNR,oldNr,avdnr);
	}
	public Integer getAvdelingId(){
		return object.getAvdelingId();
	}
	public void setAvdelingId(Integer avdelingId){
		Integer oldId=getAvdelingId();
		object.setAvdelingId(avdelingId);
		firePropertyChange(PROPERTY_AVDELING_ID,oldId,avdelingId);
	}
	
	public String getNavn(){
		if(object.getDepartment()!=null){
			return object.getDepartment().getDepartmentName();
		}
		return null;
	}
	public String getFranchisetaker(){
		return object.getFranchisetaker();
	}
	
	public void setFranchisetaker(String franchisetaker){
		String oldString = getFranchisetaker();
		object.setFranchisetaker(franchisetaker);
		firePropertyChange(PROPERTY_FRANCHISETAKER,oldString,franchisetaker);
	}
	public String getKid(){
		return object.getKid();
	}
	
	public void setKid(String kid){
		String oldString = getKid();
		object.setKid(kid);
		firePropertyChange(PROPERTY_KID,oldString,kid);
	}
	public BokfSelskap getBokfSelskap(){
		return object.getBokfSelskap();
	}
	
	public void setBokfSelskap(BokfSelskap bokfSelskap){
		BokfSelskap oldSelskap = getBokfSelskap();
		object.setBokfSelskap(bokfSelskap);
		firePropertyChange(PROPERTY_BOKF_SELSKAP,oldSelskap,bokfSelskap);
	}
	
	public Date getOpprettetDato(){
		return object.getOpprettetDato();
	}
	
	public void setOpprettetDato(Date opprettetDato){
		Date oldDate = getOpprettetDato();
		object.setOpprettetDato(opprettetDato);
		firePropertyChange(PROPERTY_OPPRETTET_DATO,oldDate,opprettetDato);
	}
	public String getNrOfAdendum(){
		return nrOfAddendum;
	}
	public String getKommentar(){
		return object.getKommentar();
	}
	
	public void setKommentar(String kommentar){
		String oldKommentar = getKommentar();
		object.setKommentar(kommentar);
		firePropertyChange(PROPERTY_KOMMENTAR,oldKommentar,kommentar);
	}
	public Boolean getPibBoolean(){
		return GuiUtil.convertNumberToBoolean(object.getPib());
	}
	
	public void setPibBoolean(Boolean pib){
		Boolean oldPib = getPibBoolean();
		object.setPib(GuiUtil.convertBooleanToNumber(pib));
		firePropertyChange(PROPERTY_PIB_BOOLEAN,oldPib,pib);
	}
	public String getArchiveInfo(){
		return object.getArchiveInfo();
	}
	
	public void setArchiveInfo(String archiveInfo){
		String oldInfo = getArchiveInfo();
		object.setArchiveInfo(archiveInfo);
		firePropertyChange(PROPERTY_ARCHIVE_INFO,oldInfo,archiveInfo);
	}
	public List<AvdelingMangel> getAvdelingMangelList(){
		return avdelingMangelList;
	}
	
	public void setAvdelingMangelList(List<AvdelingMangel> avdelingMangelList){
		List<AvdelingMangel> oldList = new ArrayList<AvdelingMangel>(avdelingMangelList);
		this.avdelingMangelList.clear();
		if(avdelingMangelList!=null){
			this.avdelingMangelList.addAll(avdelingMangelList);
		}
		firePropertyChange(PROPERTY_AVDELING_MANGEL_LIST,oldList,avdelingMangelList);
	}
	
	public void addAvdelingMangel(AvdelingMangel mangel){
		List<AvdelingMangel> oldList = new ArrayList<AvdelingMangel>(avdelingMangelList);
		object.addAvdelingMangel(mangel);
		avdelingMangelList.add(mangel);
		firePropertyChange(PROPERTY_AVDELING_MANGEL_LIST,oldList,avdelingMangelList);
	}
	public void removeAvdelingMangel(AvdelingMangel mangel){
		List<AvdelingMangel> oldList = new ArrayList<AvdelingMangel>(avdelingMangelList);
		avdelingMangelList.remove(mangel);
		object.removeAvdelingMangel(mangel);
		firePropertyChange(PROPERTY_AVDELING_MANGEL_LIST,oldList,avdelingMangelList);
	}
	
	public void addAvdelingBetingelse(AvdelingBetingelse betingelse){
		object.addAvdelingBetingelse(betingelse);
		firePropertyChange(PROPERTY_AVDELING_BETINGELSE,null,betingelse);
	}
	
	public Department getDepartment(){
		return object.getDepartment();
	}
	/*public Rik2KjedeV getRik2KjedeV(){
		if(object.getDepartment()!=null){
			return object.getDepartment().getRik2KjedeV();
		}
		return null;
	}*/
	public String getChainName(){
		return object.getDepartment()!=null?object.getDepartment().getChainName():null;
	}
	
	public AvdelingKontrakt getLastKontrakt(){
			return object.getLastKontrakt();
	}

	/*public Integer getFilial(){
		String datasetConcorde =object.getRik2AvdV().getDatasetConcorde(); 
		if(datasetConcorde !=null && datasetConcorde.equalsIgnoreCase("100")){
			return 1;
		}
		return 0;
	}*/

	@Override
	public AvdelingModel getBufferedObjectModel(PresentationModel presentationModel) {
		AvdelingModel model = new AvdelingModel(new Avdeling());
		model.setAvdnr((Integer)presentationModel.getBufferedValue(PROPERTY_AVDNR));
		model.setFranchisetaker((String)presentationModel.getBufferedValue(PROPERTY_FRANCHISETAKER));
		model.setBokfSelskap((BokfSelskap)presentationModel.getBufferedValue(PROPERTY_BOKF_SELSKAP));
		model.setOpprettetDato((Date)presentationModel.getBufferedValue(PROPERTY_OPPRETTET_DATO));
		model.setKommentar((String)presentationModel.getBufferedValue(PROPERTY_KOMMENTAR));
		model.setPibBoolean((Boolean)presentationModel.getBufferedValue(PROPERTY_PIB_BOOLEAN));
		model.setArchiveInfo((String)presentationModel.getBufferedValue(PROPERTY_ARCHIVE_INFO));
		return model;
	}

	@Override
	public void viewToModel() {
		if(avdelingBetingelseList!=null){
			Set<AvdelingBetingelse> betingelser = object.getAvdelingBetingelses();
			if(betingelser==null){
				betingelser=new HashSet<AvdelingBetingelse>();
			}
			betingelser.addAll(avdelingBetingelseList);
			object.setAvdelingBetingelses(betingelser);
		}
	}

	@Override
	public void addBufferChangeListener(PropertyChangeListener listener, PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_AVDNR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_FRANCHISETAKER).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_BOKF_SELSKAP).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_OPPRETTET_DATO).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_KOMMENTAR).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_PIB_BOOLEAN).addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ARCHIVE_INFO).addValueChangeListener(listener);
	}

}
