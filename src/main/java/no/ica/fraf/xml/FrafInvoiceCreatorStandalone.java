package no.ica.fraf.xml;

import java.util.Hashtable;
import java.util.Map;

import com.google.inject.internal.Preconditions;

import no.e2B.xmlSchema.DiscountChargesAndTaxType;
import no.e2B.xmlSchema.InvoiceSummaryType;
import no.e2B.xmlSchema.PostingDetailsType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.Mva;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.util.ApplParamUtil;

public class FrafInvoiceCreatorStandalone extends FrafInvoiceCreator {
	private static Map<String, String> vatCodes = new Hashtable<String, String>();

	private FrafInvoiceCreatorStandalone(MvaDAO mvaDAO,
			InvoiceManagerInterface fakturaDAO,
			FakturaTekstVManager fakturaTekstVManager) {
		super(mvaDAO, fakturaDAO, fakturaTekstVManager);
	}

	@Override
	protected void addPostingDetails(final EGetable egetable,
			final PostingDetailsType[] postingDetailsTypes, int counter,
			DepartmentTypeEnum departmentTypeEnum, FakturaLinje fakturaLinje)
			throws FrafException {
		// booking_accountnumber
		String accountNumber = getBookingAccountNumber(fakturaLinje,
				departmentTypeEnum);
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_BOOKING_ACCOUNTNUMBER_NAME.getParamValue(),
				accountNumber);
		// booking_vatcode
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_BOOKING_VATCODE_NAME.getParamValue(),
				// getOpositeVatCode(fakturaLinje.getMvaKode()));
				fakturaLinje.getMvaKode());

		// booking_company
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_BOOKING_COMPANY_NAME.getParamValue(),
				fakturaLinje.getFaktura().getBokfSelskap().getSelskapNavn());
		// booking_department
		String depNr = getBookingDepartmentNumber(fakturaLinje,
				departmentTypeEnum);
		String departmentPrefix = ApplParamUtil
				.getStringParam("department_prefix");
		depNr = depNr != null ? depNr : (departmentPrefix + String
				.valueOf(fakturaLinje.getFaktura().getAvdnr()));

		postingDetailsTypes[counter++] = egetable
				.createPostingDetailsType(
						E2bParamEnum.E2B_BOOKING_DEPARTMENT_NAME
								.getParamValue(), depNr);

		// projectcode
		String projectCode = fakturaLinje.getProsjekt();
		projectCode = projectCode != null ? projectCode : "";
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_PROJECTCODE_NAME.getParamValue(), projectCode);

	}

	private String getOpositeVatCode(String vatCode) {
		String opositeVatCode = vatCodes.get(vatCode);
		if (opositeVatCode == null) {
			Mva mva = mvaDAO.findByMvaKode(vatCode);
			if (mva == null) {
				System.out.println();
			}
			opositeVatCode = mva.getMotsattMvaKode();
			vatCodes.put(mva.getMvaKode(), opositeVatCode);
		}
		return opositeVatCode;
	}

	private String getBookingDepartmentNumber(FakturaLinje fakturaLinje,
			DepartmentTypeEnum departmentTypeEnum) {
		return fakturaLinje.getAvdelingBetingelse() != null
				&& fakturaLinje.getAvdelingBetingelse().getBokfAvdeling() != null ? fakturaLinje
				.getAvdelingBetingelse().getBokfAvdeling()
				: getBetingelseTypeAvdeling(fakturaLinje.getBetingelseType(),
						departmentTypeEnum);
	}

	private String getBetingelseTypeAvdeling(BetingelseType betingelseType,
			DepartmentTypeEnum departmentTypeEnum) {
		return departmentTypeEnum == DepartmentTypeEnum.OWN ? betingelseType
				.getBokfAvdelingE() : betingelseType.getBokfAvdelingF();
	}

	private String getBookingAccountNumber(final FakturaLinje fakturaLinje,
			DepartmentTypeEnum departmentTypeEnum) {
		// Preconditions.checkArgument(
		// fakturaLinje.getAvdelingBetingelse() != null, String.format(
		// "Fakturalinje for avdeling %s må ha betingelse for %s",
		// fakturaLinje.getFaktura().getAvdnr(), fakturaLinje
		// .getLinjeBeskrivelse()));
		return fakturaLinje.getAvdelingBetingelse() != null
				&& fakturaLinje.getAvdelingBetingelse().getKonto() != null ? fakturaLinje
				.getAvdelingBetingelse().getKonto()
				: getBetingelseTypeKonto(fakturaLinje.getBetingelseType(),
						departmentTypeEnum);

	}

	private String getBetingelseTypeKonto(final BetingelseType betingelseType,
			final DepartmentTypeEnum departmentTypeEnum) {
		return departmentTypeEnum == DepartmentTypeEnum.OWN ? betingelseType
				.getInntektskontoE() : betingelseType.getInntektskontoF();
	}

	public void createDiscountTotals(EGetable egetable,
			InvoiceInterface invoiceInterface) {
		InvoiceSummaryType invoiceSummaryType = egetable
				.getInvoiceSummaryType();
		DiscountChargesAndTaxType discount = invoiceSummaryType
				.addNewDiscountTotals();
		discount.setCode(String.valueOf(invoiceInterface.getStoreNo()));
		discount.setDescription("accountnumber");
		discount.setUnitOfMeasure(invoiceInterface.getCompanyName());

	}

	public RefWithCodeType[] getRef(InvoiceInterface invoiceInterface,
			DepartmentTypeEnum departmentTypeEnum) {
		RefWithCodeType ref = RefWithCodeType.Factory.newInstance();
		ref.setCode("FRAF");

		if (invoiceInterface.getGrossAmount().intValue() >= 0) {
			ref.setText("YF");
		} else {
			ref.setText("XF");
		}

		/*
		 * switch (departmentTypeEnum) { case OWN: ref.setText("FE"); break;
		 * case DAUGHTER: case FRANCHISE: ref.setText("DR"); break; }
		 */

		return new RefWithCodeType[] { ref };
	}
}
