package no.ica.fraf.xml;

import java.util.List;

import no.e2B.xmlSchema.PostingDetailsType;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.Locker;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.service.PaperManager;

/**
 * Klasse som generer e2b faktura filer
 */
public class EGetIntegrated extends EGet {
	private static PaperManager paperManager;
	
	private EGetIntegrated(String aExportDir,
			InvoiceManagerInterface aInvoiceManager,
			E2bPkgManager aE2bPkgManager, ApplUserInterface aApplUser,
			PaperManager aPaperManager,
			InvoiceColumnEnum aOrderColumn, Locker aLocker,
			DepartmentDAO departmentDAO,String onakaDir,InvoiceCreator invoiceCreator) {
		super(aInvoiceManager,aLocker,aExportDir,aApplUser,aOrderColumn,departmentDAO,aE2bPkgManager,onakaDir,invoiceCreator);
		paperManager = aPaperManager;
	}


	/**
	 * @param invoiceInterface
	 * @return true dersom avdeling er i Basware
	 */
	public static boolean storeInBasware(InvoiceInterface invoiceInterface) {
		return paperManager.storeInBasware(invoiceInterface.getStoreNo());

	}



	@Override
	protected boolean shouldGenerateXmlForInvoice(
			InvoiceInterface invoiceInterface,DepartmentTypeEnum departmentTypeEnum) throws FrafException{
		PaperInvoice paperInvoice = new PaperInvoice();
		return storeInBasware(invoiceInterface)&&!paperInvoice.onlyPaperInvoice(invoiceInterface.getStoreNo());
	}

	public PostingDetailsType[] createPostingDetailsTypes(
			DepartmentTypeEnum departmentTypeEnum) {
		PostingDetailsType[] postingDetailsTypes = null;

		switch (departmentTypeEnum) {
		case OWN:
			postingDetailsTypes = new PostingDetailsType[9];
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingDetailsTypes = new PostingDetailsType[7];
			break;
		}

		return postingDetailsTypes;
	}
	
	protected List<InvoiceInterface> getInvoices(Batchable batchable) {
		return invoiceManager.findByBatch(batchable, pagingUtil
					.getCurrentIndex(), pagingUtil.getCurrentFetchSize(),
					orderColumn,true,true);
	}

}
