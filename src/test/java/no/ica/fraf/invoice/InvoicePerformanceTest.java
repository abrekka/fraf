package no.ica.fraf.invoice;

import junit.framework.TestCase;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

public class InvoicePerformanceTest extends TestCase {
	static {
		BaseManager.setTest(true);
	}

	private UtilTest utilTest = new UtilTest();

	protected void setUp() throws Exception {
		super.setUp();
		utilTest.setUp();
	}

	@Override
	protected void tearDown() throws Exception {

		utilTest.removeBunter();

		utilTest.tearDown();

		super.tearDown();

	}
	
	public void testInvocieGroup()throws Exception{
		long start=System.currentTimeMillis();
		Integer buntId = utilTest.fakturerPeriode(2009, 2, 2, 
			//"Franchiseavgift",
		null,
				1,
		new Integer[] { 5}
			//null
		,1000,3000);
		utilTest.addBunt(buntId);
		long stop = System.currentTimeMillis();
		long duration = stop-start;
		System.out.println(duration);
		System.out.println(duration/1000);
		System.out.println(duration/1000/60);
	}

	
}
