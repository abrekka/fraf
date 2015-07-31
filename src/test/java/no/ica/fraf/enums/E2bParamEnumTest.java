package no.ica.fraf.enums;

import no.ica.fraf.FrafException;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.xml.E2bParamEnum;
import junit.framework.TestCase;

public class E2bParamEnumTest extends TestCase{
	static {
		BaseManager.setTest(true);
	}
	public void testGetStandalone() throws FrafException{
		FrafMain.setStandalone(true);
		
		String value =E2bParamEnum.E2B_PSKONTO_NAVN.getParamValue();
		assertEquals("accountnumber", value);
	}
	
	public void testGetIntegrated() throws FrafException{
		FrafMain.setStandalone(false);
		
		String value =E2bParamEnum.E2B_PSKONTO_NAVN.getParamValue();
		assertEquals("pskonto", value);
	}
	public void testGetValue() throws FrafException{
		FrafMain.setStandalone(false);
		
		String value =E2bParamEnum.E2B_CURRENCY.getParamValue();
		assertEquals("NOK", value);
	}
}
