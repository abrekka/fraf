package no.ica.fraf.dao.hibernate.impl;

import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;

public class FakturaDAOTest extends TestCase{
	static{
		BaseManager.setTest(true);
        FrafMain.setStandalone(true);
	}
	public FakturaDAOTest(){
		
	}
	public FakturaDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testFindByBatch(){
		FakturaDAO fakturaDAO=(FakturaDAO)ModelUtil.getBean(FakturaDAO.DAO_NAME);
		BuntDAO buntDAO=(BuntDAO)ModelUtil.getBean(BuntDAO.DAO_NAME);
		BuntTypeDAO buntTypeDAO=(BuntTypeDAO)ModelUtil.getBean(BuntTypeDAO.DAO_NAME);
		BuntStatusDAO buntStatusDAO=(BuntStatusDAO)ModelUtil.getBean(BuntStatusDAO.DAO_NAME);
		BuntStatus buntStatus = buntStatusDAO.findByKode(BuntStatusEnum.FAKTURERT);
		BuntType buntType = buntTypeDAO.findByKode(BuntTypeEnum.BATCH_TYPE_INVOICE);
		Bunt example = new Bunt();
		example.setBuntStatus(buntStatus);
		example.setBuntType(buntType);
		List<Bunt> bunter=buntDAO.findByExampleLike(example);
		Collections.sort(bunter);
		assertEquals(true, bunter.size()!=0);
		List<InvoiceInterface> list=fakturaDAO.findByBatch(bunter.get(bunter.size()-1), 0, 10, InvoiceColumnEnum.DEPARTMENT_NR,false,true);
		assertEquals(true, list.size()!=0);
	}
	
	public void testFindByBatch10900(){
		FakturaDAO fakturaDAO=(FakturaDAO)ModelUtil.getBean(FakturaDAO.DAO_NAME);
		BuntDAO buntDAO=(BuntDAO)ModelUtil.getBean(BuntDAO.DAO_NAME);
		Batchable batchable=buntDAO.findByBuntId(10900);
		List<InvoiceInterface> fakturaer=fakturaDAO.findByBatch(batchable, 0, 100, InvoiceColumnEnum.INVOICE_NR, true, true);
		for(InvoiceInterface faktura:fakturaer){
			if(faktura.getStoreNo()==5872){
				System.out.println("5972");
			}
		}
	}
}
