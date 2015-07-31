package no.ica.fraf.dao.pkg;

import java.util.Calendar;
import java.util.Date;

import no.ica.fraf.FrafException;
import no.ica.fraf.util.ModelUtil;

import org.junit.Test;
import static junit.framework.Assert.*;

public class FranchisePkgDAOIntegrationTest {
	@Test
	public void fakturering_skal_stoppe_dersom_negativ_justering(){
	FranchisePkgDAO franchisePkgDAO=(FranchisePkgDAO)ModelUtil.getBean(FranchisePkgDAO.NAME);
	Date fakturaDato=Calendar.getInstance().getTime();
	Date forfallDato=Calendar.getInstance().getTime();
	try {
		franchisePkgDAO.fakturerPeriode(11, 2010, 8, 1499, 1499, fakturaDato, forfallDato, 1, 1, null, null, null, 1, null);
		assertTrue("Her skulle det ha kommet en exception", false);
	} catch (FrafException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
