package no.ica.fraf.xml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.Util;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Department;
import no.ica.tollpost.model.TgFaktura;
import no.ica.tollpost.model.TgFakturaLinje;
import no.ica.tollpost.service.TgFakturaManager;

import org.apache.commons.lang.builder.CompareToBuilder;

public class TgInvoiceCreator implements InvoiceCreator {
	private InvoiceManagerInterface invoiceManager;

	private BokfSelskap bokfSelskap;

	private TgInvoiceCreator(final InvoiceManagerInterface aInvoiceManager,final BokfSelskap aBokfSelskap) {
		//tgFakturaManager = (TgFakturaManager) ModelUtil.getBean("tgFakturaManager");
		invoiceManager = aInvoiceManager;
		//BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil.getBean("bokfSelskapDAO");
		//bokfSelskap = bokfSelskapDAO.findByName("100");
		bokfSelskap = aBokfSelskap;
	}
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
		int lineCounter=lineCount;
		// int lineCounter = totalLineCounter;
		invoiceDetailsType = invoiceType.addNewInvoiceDetails();
		TgFakturaLinje tgFakturaLinje = (TgFakturaLinje) invoiceItemInterface;
		TgFaktura tgFaktura = tgFakturaLinje.getTgFaktura();
		//E2bParamEnum.initE2bParams();

		// OPPKRAV
		lineCounter++;
		BigDecimal vatPercentage;
		if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
			vatPercentage = BigDecimal
					.valueOf(Long.valueOf(E2bParamEnum.E2B_MVAPROCENT_TG_OPPKRAV
							.getParamValue()));
		} else {//PROVISJON
			vatPercentage = BigDecimal.valueOf(Long
					.valueOf(E2bParamEnum.E2B_MVAPROCENT_TG_PROVISJON
							.getParamValue()));
		}

		BaseItemDetailsType baseItemDetailsType = egetable
				.createBaseItemDetailsType(invoiceDetailsType, String
						.valueOf(lineCounter), tgFakturaLinje
						.getLinjeBeskrivelse(), null, null, tgFakturaLinje
						.getBelop(), BigDecimal.valueOf(tgFakturaLinje
						.getAntall()), vatPercentage);

		PostingDetailsType[] postingDetailsTypes = egetable
				.createPostingDetailsTypes(departmentTypeEnum);

		int counter = 0;

		String postingCode=null;

		switch(departmentTypeEnum){
		case OWN:
//			oppkrav
			if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
				postingCode = E2bParamEnum.E2B_PSKONTO_TG_OPPKRAV_OWN_STORE
						.getParamValue();
			} else {//provisjon
				postingCode = E2bParamEnum.E2B_PSKONTO_TG_PROVISJON_OWN_STORE
						.getParamValue();
			}
			break;
		case DAUGHTER:
		case FRANCHISE:
//			oppkrav
			if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
				postingCode = E2bParamEnum.E2B_PSKONTO_TG_OPPKRAV_FRANCHISE
						.getParamValue();
			} else {//provisjon
				postingCode = E2bParamEnum.E2B_PSKONTO_TG_PROVISJON_FRANCHISE
						.getParamValue();
			}
			break;
		}
		
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_PSKONTO_NAVN.getParamValue(), postingCode);

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_PSAVDELING_NAVN.getParamValue(), department
						.getAvdnr().toString());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_BRUTTOBELØP_NAVN.getParamValue(), Util
						.formatDecimalNumber(tgFakturaLinje.getNettoBelop()));

		// momskode 
		//egeneid
		switch(departmentTypeEnum){
		case OWN:
//			oppkrav
			if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
				postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
						E2bParamEnum.E2B_MVAKODE_NAVN.getParamValue(),
						E2bParamEnum.E2B_MVACODE_TG_OPPKRAV_OWN_STORE
								.getParamValue());
			} else {//provisjon
				postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
						E2bParamEnum.E2B_MVAKODE_NAVN.getParamValue(),
						E2bParamEnum.E2B_MVACODE_TG_PROVISJON_OWN_STORE
								.getParamValue());

			}

			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					E2bParamEnum.E2B_MVATYPE_NAVN.getParamValue(),
					//E2bParamEnum.MVA_TYPE_TG_CLAIM_OWN_STORE.getParamValue());
					E2bParamEnum.E2B_MVATYPE_CARD_OWN_STORE.getParamValue());//endret denne fordi vat_type
			break;
		case DAUGHTER:
		case FRANCHISE:
//			oppkrav
			if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
				postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
						E2bParamEnum.E2B_MVAKODE_NAVN.getParamValue(),
						E2bParamEnum.E2B_MVACODE_TG_OPPKRAV_FRANCHISE
								.getParamValue());
			} else {//provisjon
				postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
						E2bParamEnum.E2B_MVAKODE_NAVN.getParamValue(),
						E2bParamEnum.E2B_MVACODE_TG_PROVISJON_FRANCHISE
								.getParamValue());
			}
			break;
		}
		
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_NETTOBELØP_NAVN.getParamValue(), Util
						.formatDecimalNumber(tgFakturaLinje.getBelop()));
		if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					E2bParamEnum.E2B_MVAPROSENT_NAVN.getParamValue(),
					E2bParamEnum.E2B_MVAPROCENT_TG_OPPKRAV.getParamValue());
		} else {
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					E2bParamEnum.E2B_MVAPROSENT_NAVN.getParamValue(),
					E2bParamEnum.E2B_MVAPROCENT_TG_PROVISJON.getParamValue());
		}
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_MVABELØP_NAVN.getParamValue(), Util
						.formatDecimalNumber(tgFakturaLinje.getMvaBelop()));

		switch(departmentTypeEnum){
		case OWN:
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					E2bParamEnum.E2B_VATUSE_NAVN.getParamValue(),
					E2bParamEnum.E2B_VATUSETYPE_TG_OPPKRAV_OWN_STORE
							.getParamValue());
			break;
		}
		

		baseItemDetailsType.setPostingDetailsArray(postingDetailsTypes);

		return lineCounter;
	}

	public final void createVatInfo(final EGetable egetable,
			final InvoiceInterface invoiceInterface,final DepartmentTypeEnum departmentTypeEnum) throws FrafException {
		TgFaktura tgFaktura = (TgFaktura) invoiceInterface;
		// Mva-spesifikasjon oppkrav
		InvoiceSummaryType invoiceSummaryType=egetable.getInvoiceSummaryType();
		VatInfoType vatInfoTypeCard = invoiceSummaryType.addNewVatTotalsInfo();
		if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
			vatInfoTypeCard.setVatPercent(new BigDecimal(
					E2bParamEnum.E2B_MVAPROCENT_TG_OPPKRAV.getParamValue()));
		} else {
			vatInfoTypeCard.setVatPercent(new BigDecimal(
					E2bParamEnum.E2B_MVAPROCENT_TG_PROVISJON.getParamValue()));
		}
		vatInfoTypeCard.setVatBaseAmount(tgFaktura.getVatBaseAmount());
		vatInfoTypeCard.setVatAmount(tgFaktura.getVatTotalsAmount());

	}

	public String getLocationId(DepartmentTypeEnum departmentTypeEnum,
			InvoiceInterface invoiceInterface) throws FrafException {
		return E2bParamEnum.E2B_SUPPLIER_LOCATION_ID_TG.getParamValue();
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
		return egetable.createPartyType(locationId, E2bParamEnum.E2B_SUPPLIER_NAME_TG
				.getParamValue(), null, E2bParamEnum.E2B_SUPPLIER_ADDRESS1_TG
				.getParamValue(), E2bParamEnum.E2B_SUPPLIER_POSTALCODE_TG
				.getParamValue(), E2bParamEnum.E2B_SUPPLIER_DISTRICT_TG
				.getParamValue(), accountNr, E2bParamEnum.E2B_SUPPLIER_ORG_NR_TG
				.getParamValue(), E2bParamEnum.E2B_SUPPLIER_VATID_TG
				.getParamValue());
	}

	public String[] getHeaderFreeText(Batchable batchable,
			InvoiceInterface invoiceInterface) {
		return new String[] { invoiceInterface.getHeading() };
	}

	public List<InvoiceItemInterface> orderInvoiceLines(
			Set<InvoiceItemInterface> lines) {
		List<InvoiceItemInterface> tmpLines = new ArrayList<InvoiceItemInterface>(
				lines);
		Collections.sort(tmpLines, new TgFakturaLinjeComparator());
		return tmpLines;
	}

	private class TgFakturaLinjeComparator implements Comparator {

		public int compare(Object object1, Object object2) {
			TgFakturaLinje linje1 = (TgFakturaLinje) object1;
			TgFakturaLinje linje2 = (TgFakturaLinje) object2;
			return new CompareToBuilder().append(linje1.getTransDato(),
					linje2.getTransDato()).toComparison();
		}

	}

	public void setXmlGenerationDate(InvoiceInterface invoiceInterface) {
		TgFaktura tgFaktura = (TgFaktura) invoiceInterface;
		tgFaktura.setXmlGenerert(Calendar.getInstance().getTime());
		((TgFakturaManager)invoiceManager).saveTgFaktura(tgFaktura);

	}

	public String getEnvelopeLocationNr(InvoiceInterface firstInvoice)
			throws FrafException {
		return E2bParamEnum.E2B_SUPPLIER_LOCATION_ID_OWN.getParamValue();
	}

	public RefWithCodeType[] getRef(InvoiceInterface invoiceInterface, DepartmentTypeEnum departmentTypeEnum) {
		return null;
	}
	public void createDiscountTotals(EGetable egetable,
			InvoiceInterface invoiceInterface) {
		// TODO Auto-generated method stub
		
	}
}
