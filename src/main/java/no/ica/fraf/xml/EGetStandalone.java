package no.ica.fraf.xml;

import java.util.List;

import no.e2B.xmlSchema.PostingDetailsType;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.Locker;
import no.ica.fraf.dao.DepartmentDAO;

public class EGetStandalone extends EGet {
	

	public EGetStandalone(String aExportDir,
			InvoiceManagerInterface aInvoiceManager,
			E2bPkgManager aE2bPkgManager, ApplUserInterface aApplUser,
			InvoiceColumnEnum aOrderColumn, Locker aLocker,
			DepartmentDAO aDepartmentDAO,String sapDir,InvoiceCreator invoiceCreator) {
		super(aInvoiceManager,aLocker,aExportDir,aApplUser,aOrderColumn,aDepartmentDAO,aE2bPkgManager,sapDir,invoiceCreator);
	}

	



	@Override
	protected boolean shouldGenerateXmlForInvoice(
			InvoiceInterface invoiceInterface,DepartmentTypeEnum departmentTypeEnum) throws FrafException {
		return departmentTypeEnum.equals(DepartmentTypeEnum.FRANCHISE)||departmentTypeEnum.equals(DepartmentTypeEnum.DAUGHTER);
	}





	@Override
	public PostingDetailsType[] createPostingDetailsTypes(
			DepartmentTypeEnum departmentTypeEnum) {
		PostingDetailsType[] postingDetailsTypes = null;

		switch (departmentTypeEnum) {
		case OWN:
			postingDetailsTypes = new PostingDetailsType[13];
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingDetailsTypes = new PostingDetailsType[12];
			break;
		}

		return postingDetailsTypes;
	}
	
	protected List<InvoiceInterface> getInvoices(Batchable batchable) {
		return invoiceManager.findByBatch(batchable, pagingUtil
					.getCurrentIndex(), pagingUtil.getCurrentFetchSize(),
					orderColumn,false,true);
	}
}
