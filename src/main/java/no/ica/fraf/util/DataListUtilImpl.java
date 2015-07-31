package no.ica.fraf.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.ica.fraf.dao.AvdelingStatusDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.dao.DistrictManagerDAO;
import no.ica.fraf.dao.FornyelseTypeDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.dao.RegionDAO;
import no.ica.fraf.model.AvdelingStatus;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.FornyelseType;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.model.Region;

public class DataListUtilImpl implements DataListUtil {
	private static List<BokfSelskap> bokfSelskaper;
	private static List<String> distriktssjefer;
	private static List<FornyelseType> renewables;
	private static List<Chain> kjeder;
	private static List<KontraktType> kontrakttyper;
	private static List<Region> regioner;
	private static List<AvdelingStatus> statuser;
	
	private static BokfSelskapDAO bokfSelskapDAO;
	private static DistrictManagerDAO districtManagerDAO;
	private static FornyelseTypeDAO fornyelseTypeDAO;
	private static ChainDAO chainDAO;
	private static KontraktTypeDAO kontraktTypeDAO;
	private static RegionDAO regionDAO;
	private static AvdelingStatusDAO avdelingStatusDAO;
	private static List<String> regionNames;
	private DataListUtilImpl(BeanFinder beanFinder){
		bokfSelskapDAO = (BokfSelskapDAO)beanFinder.getBeanIstance(BokfSelskapDAO.DAO_NAME);
		districtManagerDAO = (DistrictManagerDAO)beanFinder.getBeanIstance(DistrictManagerDAO.DAO_NAME);
		fornyelseTypeDAO = (FornyelseTypeDAO)beanFinder.getBeanIstance(FornyelseTypeDAO.DAO_NAME);
		chainDAO = (ChainDAO)beanFinder.getBeanIstance(ChainDAO.DAO_NAME);
		kontraktTypeDAO = (KontraktTypeDAO)beanFinder.getBeanIstance(KontraktTypeDAO.DAO_NAME);
		regionDAO = (RegionDAO)beanFinder.getBeanIstance(RegionDAO.DAO_NAME);
		avdelingStatusDAO = (AvdelingStatusDAO)beanFinder.getBeanIstance("avdelingStatusDAO");
	}
	public List<BokfSelskap> getBokfSelskaper() {
		if (bokfSelskaper == null) {
			bokfSelskaper = bokfSelskapDAO.findAll();
		}
		return new ArrayList<BokfSelskap>(bokfSelskaper);
	}
	
	public List<String> getDistriktssjefer() {
		if (distriktssjefer == null) {
			distriktssjefer = districtManagerDAO.findAllNames();
		}
		return new ArrayList<String>(distriktssjefer);
	}
	public List<FornyelseType> getFornyelseTyper() {
		if (renewables == null) {
			renewables = fornyelseTypeDAO.findAll();
		}
		return new ArrayList<FornyelseType>(renewables);
	}
	
	public List<Chain> getKjeder() {
		if (kjeder == null) {
			kjeder = chainDAO.findAll();
		}
		return new ArrayList<Chain>(kjeder);
	}
	public List<KontraktType> getKontrakttyper() {
		if (kontrakttyper == null) {
			kontrakttyper = kontraktTypeDAO.findAll();
		}
		return new ArrayList<KontraktType>(kontrakttyper);
	}
	
	public List<Region> getRegioner() {
		if (regioner == null) {
			regioner = regionDAO.findAll();
		}
		
		return new ArrayList<Region>(regioner);
		
	}
	
	public List<String> getRegionNames(){
		return regionNames==null?createRegionNames():regionNames;
	}
	
	private List<String> createRegionNames() {
		List<Region> regionList = getRegioner();
		regionNames=new ArrayList<String>();
		for(Region region:regionList){
			regionNames.add(region.getNavn());
		}
		Collections.sort(regionNames);
		return regionNames;
	}
	public List<AvdelingStatus> getStatuser() {
		if (statuser == null) {
			avdelingStatusDAO=avdelingStatusDAO==null?(AvdelingStatusDAO)ModelUtil.getBean("avdelingStatusDAO"):avdelingStatusDAO;
			statuser = avdelingStatusDAO.findAll();
		}
		return new ArrayList<AvdelingStatus>(statuser);
	}
	public void loadLists() {
		getBokfSelskaper();
		getKjeder();
		getKontrakttyper();
		getRegioner();
		getStatuser();
	}
}
