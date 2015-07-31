package no.ica.fraf.util;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingStatus;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.FornyelseType;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.model.Region;
import no.ica.fraf.service.impl.BaseManager;

public class DataListUtilIntegratedTest extends TestCase{
	static {
		BaseManager.setTest(true);
		FrafMain.setStandalone(false);
	}
	private DataListUtil dataListUtil;
	@Override
	protected void setUp() throws Exception {
		dataListUtil = DataListUtilFactory
		.getInstance(new ModelUtil());
	}

	public void testGetBokfSelskaper(){
		List<BokfSelskap> companies = dataListUtil.getBokfSelskaper();
		assertNotNull(companies);
		assertEquals(4, companies.size());
	}
	
	public void testGetDistriktssjefer(){
		List<String> managers = dataListUtil.getDistriktssjefer();
		assertNotNull(managers);
		assertEquals(179, managers.size());
	}
	
	public void testGetFornyelseTyper(){
		List<FornyelseType> list = dataListUtil.getFornyelseTyper();
		assertNotNull(list);
		assertEquals(6, list.size());
	}
	
	public void testGetKjeder(){
		List<Chain> list = dataListUtil.getKjeder();
		assertNotNull(list);
		assertEquals(45, list.size());
	}
	public void testGetKontrakttyper(){
		List<KontraktType> list = dataListUtil.getKontrakttyper();
		assertNotNull(list);
		assertEquals(9, list.size());
	}
	public void testGetRegioner(){
		List<Region> list = dataListUtil.getRegioner();
		assertNotNull(list);
		assertEquals(137, list.size());
	}
	
	public void testGetStatuser(){
		List<AvdelingStatus> list = dataListUtil.getStatuser();
		assertNotNull(list);
		assertEquals(5, list.size());
	}
}
