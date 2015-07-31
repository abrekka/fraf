package no.ica.fraf.xml;

import no.e2B.xmlSchema.PostingDetailsType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.service.FakturaTekstVManager;

public class FrafInvoiceCreatorIntegrated extends FrafInvoiceCreator{

	private FrafInvoiceCreatorIntegrated(MvaDAO mvaDAO,
			InvoiceManagerInterface fakturaDAO,
			FakturaTekstVManager fakturaTekstVManager) {
		super(mvaDAO, fakturaDAO, fakturaTekstVManager);
	}

	@Override
	protected void addPostingDetails(EGetable egetable,
			PostingDetailsType[] postingDetailsTypes, int counter,
			DepartmentTypeEnum departmentTypeEnum, FakturaLinje fakturaLinje)
			throws FrafException {
		// TODO Auto-generated method stub
		
	}

	public void createDiscountTotals(EGetable egetable,
			InvoiceInterface invoiceInterface) {
		// TODO Auto-generated method stub
		
	}

	public RefWithCodeType[] getRef(InvoiceInterface invoiceInterface,
			DepartmentTypeEnum departmentTypeEnum) {
		RefWithCodeType ref = RefWithCodeType.Factory.newInstance();
		ref.setCode("FRAF");

		switch (departmentTypeEnum) {
		case OWN:
			ref.setText("FE");
			break;
		case DAUGHTER:
		case FRANCHISE:
			ref.setText("FF");
			break;
		}

		return new RefWithCodeType[] { ref };
	}

}
