package no.ica.fraf.dao.hibernate.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.TableSorter.Directive;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

import org.jmock.integration.junit3.MockObjectTestCase;

public class AvdVDAOTest extends MockObjectTestCase {
	public AvdVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	private AvdelingVDAO avdVDAO;
	
	@Override
	protected void setUp() throws Exception {
		avdVDAO = (AvdelingVDAO)ModelUtil.getBean("avdelingVDAO");
	}
	
	public void testGetAvdVDAOStandalone(){
		FrafMain.setStandalone(true);
		assertNotNull(avdVDAO);
	}
	
	public void testFindAllStandalone() throws Exception{
		assertNotNull(avdVDAO);
		List<AvdelingV> departments = avdVDAO.findAll(1000, 2000);
		assertNotNull(departments);
		assertEquals(true, departments.size()>0);
	}
	
	public void testFindAllStandaloneDescending() throws Exception{
		AvdelingV filter =  new AvdelingV();
		filter.setAvdelingNavn("ICA Nær");
		List<Directive> directives=new ArrayList<Directive>();
		directives.add(new Directive(1,no.ica.fraf.enums.SortingEnum.DESCENDING));
		Collection<AvdelingV> departments = avdVDAO.findAll(directives, filter );
		assertNotNull(departments);
		assertEquals(482, departments.size());
		assertEquals("ICA NærTømmerstø Brygge", departments.iterator().next().getAvdelingNavn());
	}
	
	public void testFindAllPaged(){
		List<Directive> directives=new ArrayList<Directive>();
		directives.add(new Directive(1,no.ica.fraf.enums.SortingEnum.DESCENDING));
		AvdelingV filter =  new AvdelingV();
		filter.setStatus("OK");
		avdVDAO.findAllPaged(0, 10, directives, filter);
		Collection<AvdelingV> departments = avdVDAO.findAllPaged(0, 10, directives, filter);
		assertNotNull(departments);
		assertEquals(10, departments.size());
	}
	
	public void testFindAllSelskapRegnskap(){
		List<Object> companies=avdVDAO.findAllSelskapRegnskap();
		assertNotNull(companies);
		assertEquals(613, companies.size());
	}

	public void testFindByAvdnr(){
		AvdelingV avd = avdVDAO.findByAvdnr(1499);
		assertNotNull(avd);
		assertEquals("RIMI Kaspergården", avd.getAvdelingNavn());
	}
	public void testGetAvdelingV() {
		AvdelingV avd = avdVDAO.getAvdelingV(633);
		assertNotNull(avd);
		assertEquals("ICA Nær Bæverfjord", avd.getAvdelingNavn());
	}
	
	public void testGetCount() {
		assertEquals(true, avdVDAO.getCount()>1300);
	}
	public void testGetCountByFilter() {
		AvdelingV filter =  new AvdelingV();
		filter.setRegion("ICA Nær Møre");
		assertEquals(Integer.valueOf(133), avdVDAO.getCountByFilter(filter));
	}
	public void testFindByExample() {
		AvdelingV example =  new AvdelingV();
		example.setRegion("ICA Nær Møre");
		List<AvdelingV> departments = avdVDAO.findByExample(example);
		assertNotNull(departments);
		assertEquals(0, departments.size());
	}
	
	public void testFindByExampleLike() {
		AvdelingV example =  new AvdelingV();
		example.setRegion("ICA Nær Møre");
		List<AvdelingV> departments = avdVDAO.findByExampleLike(example);
		assertNotNull(departments);
		assertEquals(133, departments.size());
	}
	
	public void testGetCountWithFilterObject() {
		AvdelingV filter =  new AvdelingV();
		filter.setRegion("ICA Nær Møre");
		assertEquals(Integer.valueOf(133), avdVDAO.getCount(new Object[]{filter}));
	}
	public void testGetNextList() {
		AvdelingV filter =  new AvdelingV();
		filter.setRegion("ICA Nær Møre");
		List<Directive> directives=new ArrayList<Directive>();
		directives.add(new Directive(1,no.ica.fraf.enums.SortingEnum.DESCENDING));
		Collection<AvdelingV> departments = avdVDAO.getNextList(new Object[]{directives,filter}, 10, 10);
		assertNotNull(departments);
		assertEquals(true, departments.size()!=0);
		assertEquals("ICA Nær Vestsmøla", departments.iterator().next().getAvdelingNavn());
	}
}
