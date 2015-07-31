package no.ica.fraf.service;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.model.FakturaTekstV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class FakturaTekstVManagerTest extends TestCase {
	static {
		BaseManager.setTest(true);
	}

	public void testFindByFakturaId(){
		FakturaTekstVManager fakturaTekstVManager=(FakturaTekstVManager)ModelUtil.getBean("fakturaTekstVManager");
		List<FakturaTekstV> tekster =fakturaTekstVManager.findByFakturaId(651418);
		assertEquals(2,tekster.size());
	}
}
