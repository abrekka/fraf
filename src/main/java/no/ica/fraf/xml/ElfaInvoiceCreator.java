package no.ica.fraf.xml;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import no.e2B.xmlSchema.BaseItemDetailsType;
import no.e2B.xmlSchema.InvoiceDetailsType;
import no.e2B.xmlSchema.InvoiceSummaryType;
import no.e2B.xmlSchema.InvoiceType;
import no.e2B.xmlSchema.PartyType;
import no.e2B.xmlSchema.PostingDetailsType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.e2B.xmlSchema.VatInfoType;
import no.ica.elfa.model.Invoice;
import no.ica.elfa.model.InvoiceItem;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Department;

public class ElfaInvoiceCreator implements InvoiceCreator {
	private InvoiceManagerInterface invoiceManager;

	private BokfSelskap bokfSelskap;

	private ElfaInvoiceCreator(final InvoiceManagerInterface aInvoiceManager,final BokfSelskap aBokfSelskap) {
		//invoiceManager = (InvoiceManager) ModelUtil.getBean("invoiceManager");
		invoiceManager=aInvoiceManager;
		//BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil.getBean("bokfSelskapDAO");
		
		//bokfSelskap = bokfSelskapDAO.findByName("110");
		bokfSelskap = aBokfSelskap;
	}

	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	public int createInvoiceDetails(List<InvoiceItemInterface> invoiceItems,
			Department department, DepartmentTypeEnum departmentTypeEnum,
			EGetable egetable) throws FrafException {
		InvoiceType invoiceType = egetable.getInvoiceType();
		int lineCounter = 0;
		for (InvoiceItemInterface invoiceItemInterface : invoiceItems) {
			lineCounter = createDetails(invoiceType, invoiceItemInterface,department,departmentTypeEnum,egetable,lineCounter);
					

		}
		return lineCounter;

	}

	private int createDetails(InvoiceType invoiceType,
			InvoiceItemInterface invoiceItemInterface, Department department,
			DepartmentTypeEnum departmentTypeEnum, EGetable egetable, int lineCount)
			throws FrafException {
		InvoiceDetailsType invoiceDetailsType;
		int lineCounter = lineCount;
		// int lineCounter = 0;
		invoiceDetailsType = invoiceType.addNewInvoiceDetails();
		InvoiceItem invoiceItem = (InvoiceItem) invoiceItemInterface;

		// TELEKORT
		lineCounter++;
		createCard(invoiceDetailsType, String.valueOf(lineCounter), invoiceItem
				.getInvoiceItemDescription(), String.valueOf(invoiceItem
				.getArticleNo()), invoiceItem.getArticlePrice(), invoiceItem
				.getAmountTotal(), invoiceItem.getNumberOfArticles(),
				new BigDecimal(0), department, invoiceItem, departmentTypeEnum, egetable);

		// PROVISJON
		invoiceDetailsType = invoiceType.addNewInvoiceDetails();
		lineCounter++;
		createCommission(invoiceDetailsType, String.valueOf(lineCounter),
				"Provisjon påfylling", "P"
						+ String.valueOf(invoiceItem.getArticleNo()),
				invoiceItem.getCommissionAmountSto().multiply(
						new BigDecimal(-1)), new BigDecimal(25), department,
				invoiceItem, departmentTypeEnum, egetable);
		return lineCounter;

	}

	private void createCard(InvoiceDetailsType invoiceDetailsType,
			String lineItemNum, String description, String productId,
			BigDecimal articlePrice, BigDecimal lineItemAmount,
			BigDecimal quantityInvoiced, BigDecimal vatPercent,
			Department department, InvoiceItem invoiceItem,
			DepartmentTypeEnum departmentTypeEnum, EGetable egetable)
			throws FrafException {

		BaseItemDetailsType baseItemDetailsType = egetable
				.createBaseItemDetailsType(invoiceDetailsType, lineItemNum,
						description, productId, articlePrice, lineItemAmount,
						quantityInvoiced, vatPercent);

		PostingDetailsType[] postingDetailsTypes = egetable
				.createPostingDetailsTypes(departmentTypeEnum);

		int counter = 0;

		String postingCode=null;

		switch(departmentTypeEnum){
		case OWN:
			postingCode = E2bParamEnum.E2B_PSKONTO_CARD_OWN_STORE
			.getParamValue();
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingCode = E2bParamEnum.E2B_PSKONTO_CARD_FRANCHISE
			.getParamValue();
			break;
		}
		
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"pskonto", postingCode);

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"psavdeling", department.getAvdnr().toString());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"bruttobelop", invoiceItem.getAmountTotal().toString());

		// momskode for telekort
		switch(departmentTypeEnum){
		case OWN:
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					"mvakode", E2bParamEnum.E2B_MVACODE_CARD_OWN_STORE
							.getParamValue());

			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					"mvatype", E2bParamEnum.E2B_MVATYPE_CARD_OWN_STORE
							.getParamValue());
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					"mvakode", E2bParamEnum.E2B_MVACODE_CARD_FRANCHISE
							.getParamValue());
			break;
		}
		
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"nettobelop", invoiceItem.getAmountTotal().toString());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"mvaprosent", E2bParamEnum.E2B_MVAPROCENT_CARD.getParamValue());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"mvabelop", invoiceItem.getAmountTotal().multiply(
						new BigDecimal(E2bParamEnum.E2B_MVAPROCENT_CARD
								.getParamValue())).divide(new BigDecimal(100),
						BigDecimal.ROUND_CEILING).toString());

		switch(departmentTypeEnum){
		case OWN:
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					"vatusetype", E2bParamEnum.E2B_VATUSETYPE_CARD_OWN_STORE
							.getParamValue());
			break;
		}

		baseItemDetailsType.setPostingDetailsArray(postingDetailsTypes);
	}

	private void createCommission(InvoiceDetailsType invoiceDetailsType,
			String lineItemNum, String description, String productId,
			BigDecimal lineItemAmount, BigDecimal vatPercent,
			Department department, InvoiceItem invoiceItem,
			DepartmentTypeEnum departmentTypeEnum, EGetable egetable)
			throws FrafException {
		BaseItemDetailsType baseItemDetailsType = egetable
				.createBaseItemDetailsType(invoiceDetailsType, lineItemNum,
						description, productId, null, lineItemAmount, null,
						vatPercent);

		PostingDetailsType[] postingDetailsTypes = egetable
				.createPostingDetailsTypes(departmentTypeEnum);

		int counter = 0;

		String postingCode=null;

		switch (departmentTypeEnum) {
		case OWN:
			postingCode = E2bParamEnum.E2B_PSKONTO_COMMISION_OWN_STORE
					.getParamValue();
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingCode = E2bParamEnum.E2B_PSKONTO_COMMISION_FRANCHISE
					.getParamValue();
			break;
		}

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"pskonto", postingCode);

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"psavdeling", department.getAvdnr().toString());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"bruttobelop", invoiceItem.getCommissionAmountSto().add(
						invoiceItem.getVatAmount())
						.multiply(new BigDecimal(-1)).toString());

		switch (departmentTypeEnum) {
		case OWN:
			postingCode = E2bParamEnum.E2B_MVACODE_COMMISION_OWN_STORE
					.getParamValue();
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					"mvatype", E2bParamEnum.E2B_MVATYPE_CARD_OWN_STORE
							.getParamValue());
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingCode = E2bParamEnum.E2B_MVACODE_COMMISION_FRANCHISE
					.getParamValue();
			break;
		}

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"mvakode", postingCode);

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"nettobelop", invoiceItem.getCommissionAmountSto().multiply(
						new BigDecimal(-1)).toString());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"mvabelop", invoiceItem.getVatAmount().multiply(
						new BigDecimal(-1)).toString());

		switch (departmentTypeEnum) {
		case OWN:
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					"vatusetype",
					E2bParamEnum.E2B_VATUSETYPE_COMMISION_OWN_STORE
							.getParamValue());
			break;
		}


		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				"mvaprosent", E2bParamEnum.E2B_MVAPROCENT_COMMISION
						.getParamValue());

		baseItemDetailsType.setPostingDetailsArray(postingDetailsTypes);
	}

	public void createVatInfo(EGetable egetable,
			InvoiceInterface invoiceInterface, DepartmentTypeEnum departmentTypeEnum)
			throws FrafException {
		Invoice invoice = (Invoice) invoiceInterface;
		InvoiceSummaryType invoiceSummaryType=egetable.getInvoiceSummaryType();
		// Mva-spesifikasjon telekort
		VatInfoType vatInfoTypeCard = invoiceSummaryType.addNewVatTotalsInfo();
		vatInfoTypeCard.setVatPercent(new BigDecimal(
				E2bParamEnum.E2B_MVAPROCENT_CARD.getParamValue()));
		vatInfoTypeCard.setVatBaseAmount(invoice.getAmountTotal());

		// Mva-spesifikasjon provisjon
		VatInfoType vatInfoTypeCommission = invoiceSummaryType
				.addNewVatTotalsInfo();
		vatInfoTypeCommission.setVatPercent(new BigDecimal(
				E2bParamEnum.E2B_MVAPROCENT_COMMISION.getParamValue()));
		vatInfoTypeCommission.setVatBaseAmount(invoice.getCommissionAmountSto()
				.multiply(new BigDecimal(-1)));

	}

	public String getLocationId(DepartmentTypeEnum departmentTypeEnum,
			InvoiceInterface invoiceInterface) throws FrafException {
		String locationId=null;
		switch(departmentTypeEnum){
		case OWN:
			locationId = E2bParamEnum.E2B_SUPPLIER_LOCATION_ID_OWN
			.getParamValue();
			break;
		case DAUGHTER:
		case FRANCHISE:
			locationId = E2bParamEnum.E2B_SUPPLIER_LOCATION_ID_FRANCHISE
			.getParamValue();
			break;
		}
		return locationId;
	}

	public PartyType getSupplier(String locationId, EGetable egetable,
			InvoiceInterface invoiceInterface, DepartmentTypeEnum departmentTypeEnum)
			throws FrafException {
		String accountNr;

		if (invoiceInterface.getKidNr().length() < 12) {
			accountNr = bokfSelskap.getFilialKonto();
		} else {
			accountNr = bokfSelskap.getKontonr();
		}

		return egetable.createPartyType(locationId, E2bParamEnum.E2B_SUPPLIER_NAME
				.getParamValue(), null, E2bParamEnum.E2B_SUPPLIER_ADDRESS1
				.getParamValue(), E2bParamEnum.E2B_SUPPLIER_POSTALCODE
				.getParamValue(), E2bParamEnum.E2B_SUPPLIER_DISTRICT
				.getParamValue(), accountNr, E2bParamEnum.E2B_SUPPLIER_ORG_NR
				.getParamValue(), E2bParamEnum.E2B_SUPPLIER_VATID.getParamValue());
	}

	public String[] getHeaderFreeText(Batchable batchable,
			InvoiceInterface invoiceInterface) {
		StringBuffer freeTextBuffer = new StringBuffer("Avregningsperiode: ")
				.append(formatter.format(batchable.getFromDate())).append(
						" til ")
				.append(formatter.format(batchable.getToDate()));
		return new String[] { freeTextBuffer.toString() };
	}

	public List<InvoiceItemInterface> orderInvoiceLines(
			Set<InvoiceItemInterface> lines) {

		return new ArrayList<InvoiceItemInterface>(lines);
	}

	public void setXmlGenerationDate(InvoiceInterface invoiceInterface) {
		Invoice invoice = (Invoice) invoiceInterface;
		invoice.setXmlGenerert(Calendar.getInstance().getTime());
		invoiceManager.save(invoice);

	}

	public String getEnvelopeLocationNr(InvoiceInterface firstInvoice)
			throws FrafException {
		return E2bParamEnum.E2B_SUPPLIER_LOCATION_ID_OWN.getParamValue();
	}

	public RefWithCodeType[] getRef(InvoiceInterface invoiceInterface,
			DepartmentTypeEnum departmentTypeEnum) {
		return null;
	}

	public void createDiscountTotals(EGetable egetable,
			InvoiceInterface invoiceInterface) {
		// TODO Auto-generated method stub
		
	}

}
