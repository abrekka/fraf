package no.ica.fraf.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.SpeiletBetingelseDAO;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

import org.junit.Before;
import org.junit.Test;

public class SpeiletBetingelseDAOIT {
	
	@Before
	public void settopp(){
		BaseManager.setTest(true);
	}

	@Test(expected=FrafRuntimeException.class)
	public void skalIkkeKunneLagreSpeiletBetingelseSomHarSpeiletDatoFoer1900() throws Exception{
		BaseManager.setTest(true);
		SpeiletBetingelseDAO speiletBetingelseDAO =(SpeiletBetingelseDAO)ModelUtil.getBean(SpeiletBetingelseDAO.DAO_NAME);
		AvdelingDAO avdelingDAO=(AvdelingDAO)ModelUtil.getBean(AvdelingDAO.DAO_NAME);
		SpeiletBetingelse speiletBetingelse=new SpeiletBetingelse();
		speiletBetingelse.setKontraktObjektId(-1);
		Date speiletFraDato=new SimpleDateFormat("ddMMyyyy").parse("12200101");
		speiletBetingelse.setSpeiletFraDato(speiletFraDato);
		Avdeling avdeling = avdelingDAO.findByAvdnr(1499);
		avdelingDAO.loadLazy(avdeling, new LazyLoadAvdelingEnum[]{LazyLoadAvdelingEnum.LOAD_MIRROR});
		speiletBetingelse= avdeling.getSpeiletBetingelses().iterator().next();
		speiletBetingelse.setSpeiletFraDato(speiletFraDato);
		speiletBetingelseDAO.saveObject(speiletBetingelse);
	}
}
