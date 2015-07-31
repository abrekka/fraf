package no.ica.fraf.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import no.e2B.xmlSchema.BaseItemDetailsType;
import no.e2B.xmlSchema.EnvelopeType;
import no.e2B.xmlSchema.InterchangeDocument;
import no.e2B.xmlSchema.InvoiceDetailsType;
import no.e2B.xmlSchema.InvoiceHeaderType;
import no.e2B.xmlSchema.InvoiceSummaryType;
import no.e2B.xmlSchema.InvoiceType;
import no.e2B.xmlSchema.PartyType;
import no.e2B.xmlSchema.PaymentType;
import no.e2B.xmlSchema.PostingDetailsType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.e2B.xmlSchema.VatInfoType;
import no.e2B.xmlSchema.InterchangeDocument.Interchange;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.FakturaTekstV;
import no.ica.fraf.model.FakturaTekstVPK;
import no.ica.fraf.model.Mva;
import no.ica.fraf.model.Rik2AvdV;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ApplParamUtil;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class EGetIntegratedTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}
	private SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd");
	
	public void testGenerateXml()throws Exception{
		FrafMain.setStandalone(false);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final Batchable batchable=mock(Batchable.class);
		final ApplUserInterface applUser = mock(ApplUserInterface.class);
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		//final FakturaDAO fakturaDAO = mock(FakturaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		final BokfSelskap bokfSelskap = new BokfSelskap();

		//final InvoiceCreator invoiceCreator=new InvoiceCreatorFactoryImpl().create(SystemEnum.FRAF, invoiceManager, bokfSelskap, mvaDAO, fakturaTekstVManager);
		final PaperManager paperManager=mock(PaperManager.class);
		final Locker locker=mock(Locker.class);
		final DepartmentDAO departmentDAO=mock(DepartmentDAO.class);
		final E2bPkgManager e2bPkgManager = mock(E2bPkgManager.class);
		
		final List<InvoiceInterface> invoices= new ArrayList<InvoiceInterface>();
		final Set<FakturaLinje> invoiceLines = getInvoiceLines();
		
		final Faktura faktura = getFaktura(invoiceLines);
		invoices.add(faktura);
		
		
		final ApplParamDAO paramDAO=mock(ApplParamDAO.class);
		E2bParamEnum.setApplParamDAO(paramDAO);
		ApplParamUtil.setApplParamDAO(paramDAO);
		
		final List<ApplParam> e2bParams=getParamList();
		final Rik2AvdV rik2AvdV = getRik2AvdV();
		final InvoiceCreatorFactory invoiceCreatorFactory=new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager);
		
		final EGetable egetable = new EGetableFactoryImpl(
				e2bPkgManager, paperManager, locker, departmentDAO, "/xml",
				invoiceCreatorFactory).getInstance("/onaka",applUser, InvoiceColumnEnum.INVOICE_NR,SystemEnum.FRAF,null,null);
		//final EGetable egetable = new EGetableFactoryImpl().getInstance("/xml", invoiceManager, e2bPkgManager, applUser,paperManager,InvoiceColumnEnum.INVOICE_NR,locker,departmentDAO,"/");
		
		final RefWithCodeType ref=RefWithCodeType.Factory.newInstance();
		ref.setCode("FRAF");
		ref.setText("FF");
		
		final List<FakturaTekstV> fakturaTekstVList=new ArrayList<FakturaTekstV>();
		FakturaTekstV tekst = new FakturaTekstV();
		tekst.setFakturaTekstVPK(new FakturaTekstVPK(111,"testTekst",1));
		fakturaTekstVList.add(tekst);
		
		final ApplParam param = new ApplParam();
		param.setParamName("ica_konto_tekst");
		param.setParamValue("Fakturabeløpet deb/kred Deres fakturakonto på forfallsdagen. Eventuell forsinkelsesrente tilkommer. Betaling skjer til ICA Norge AS som administrerer ICA Konto på oppdrag fra de fakturerende selskap innen ICA. Kundefordringer fra andre ICA selskap er ikke overdratt til ICA Norge AS.");
		
		final ApplParam paramOnaka = new ApplParam();
		paramOnaka.setParamName("onaka_path");
		paramOnaka.setParamValue("h:/");
		
		checking(new Expectations(){{
			oneOf(locker).lock(applUser, null, LaasTypeEnum.XML, null);will(returnValue(true));
			allowing(paramDAO).findByParamNameStartWith("e2b");will(returnValue(e2bParams));
			oneOf(batchable).getXmlFreeTextBuffer();will(returnValue(new StringBuffer("fritekst")));
			oneOf(batchable).getXmlComment();will(returnValue(new StringBuffer("kommentar")));
			oneOf(invoiceManager).refreshBatchable(batchable);
			oneOf(invoiceManager).getCountByBatch(batchable);will(returnValue(1));
			oneOf(invoiceManager).findByBatch(batchable, 0, 300, InvoiceColumnEnum.INVOICE_NR,true,true);will(returnValue(invoices));
			oneOf(batchable).getBatchStatus();
			oneOf(batchable).getDirectoryName();
			oneOf(invoiceManager).findByBatch(batchable, 300, 300, InvoiceColumnEnum.INVOICE_NR,true,true);will(returnValue(invoices));
			oneOf(paperManager).storeInBasware(9999);will(returnValue(Boolean.TRUE));
			allowing(invoiceManager).lazyLoad(faktura, new LazyLoadInvoiceEnum[]{LazyLoadInvoiceEnum.INVOCIE_ITEM});
			oneOf(departmentDAO).findByAvdnr(9999);will(returnValue(rik2AvdV));
			allowing(paramDAO).findByParamName("ica_konto_tekst", 0);will(returnValue(param));
			oneOf(fakturaTekstVManager).findByFakturaId(111);will(returnValue(fakturaTekstVList));
			oneOf(invoiceManager).save(faktura);
			oneOf(e2bPkgManager).getFilename();will(returnValue("test00999"));
			oneOf(locker).unlock();
		}});
		
		String msg = egetable.createEgetDocument(batchable, null, null);
		System.out.println(msg);
		assertEquals("", msg);
		
		InterchangeDocument xmlDocument = egetable.getXmlDocument();
		assertNotNull(xmlDocument);
		System.out.println(xmlDocument.toString());
		validateXml(xmlDocument);
	}
	private void validateXml(InterchangeDocument xmlDocument){
		Interchange interchange =xmlDocument.getInterchange();
		
		validateEnvelope(interchange.getEnvelope());
		validateInvoices(interchange.getInvoiceArray());
	}
	private void validateEnvelope(EnvelopeType envelop){
		assertEquals("1", envelop.getInterchangeId());
		assertEquals("7080000068982", envelop.getFrom());
		assertEquals("7080005026994", envelop.getTo());
		assertEquals(dateFormatter.format(Calendar.getInstance().getTime()), envelop.getDate());
		assertEquals(BigInteger.valueOf(1), envelop.getNumberOfMessages());
		assertEquals(BigInteger.valueOf(0), envelop.getTestIndicator());
	}
	private void validateInvoices(InvoiceType[] invoices){
		int count=0;
		
		for(InvoiceType invoice:invoices){
			count++;
			assertEquals("e2b", invoice.getMessageOwner());
			assertEquals("Invoice", invoice.getMessageType());
			assertEquals("3.1", invoice.getMessageVersion());
			assertEquals(BigInteger.valueOf(1), invoice.getNumberOfLines());
			validateHeader(invoice.getInvoiceHeader());
			validateInvoiceDetails(invoice.getInvoiceDetailsArray());
			validateInvoiceSummary(invoice.getInvoiceSummary());
		}
		assertEquals(1, count);
	}
	private void validateInvoiceSummary(InvoiceSummaryType invoiceSummary){
		assertEquals(BigDecimal.valueOf(1000).setScale(2),invoiceSummary.getInvoiceTotals().getGrossAmount());
		assertEquals(BigDecimal.valueOf(100).setScale(2),invoiceSummary.getInvoiceTotals().getVatTotalsAmount());
		assertEquals(BigDecimal.valueOf(900).setScale(2),invoiceSummary.getInvoiceTotals().getNetAmount());
		validateVatTotalsInfo(invoiceSummary.getVatTotalsInfoArray());
		
	}
	private void validateVatTotalsInfo(VatInfoType[] vatInfos){
		int count=0;
		for(VatInfoType vatInfoType:vatInfos){
			count++;
			assertEquals(BigDecimal.valueOf(0), vatInfoType.getVatPercent());
			assertEquals(BigDecimal.valueOf(900).setScale(2), vatInfoType.getVatBaseAmount());
			assertEquals(BigDecimal.valueOf(100).setScale(2), vatInfoType.getVatAmount());
		}
		assertEquals(1, count);
	}
	private void validateInvoiceDetails(InvoiceDetailsType[] invoiceDetails){
		int count=0;
		for(InvoiceDetailsType invoiceDetail:invoiceDetails){
			count++;
			validateBaseItemDetails(invoiceDetail.getBaseItemDetailsArray());
		}
		assertEquals(1, count);
	}
	private void validateBaseItemDetails(BaseItemDetailsType[] baseItemDetails){
		int count=0;
		for(BaseItemDetailsType baseItemDetail:baseItemDetails){
			count++;
			assertEquals("1", baseItemDetail.getLineItemNum());
			assertEquals("linjeBeskrivelse  periode", baseItemDetail.getDescription());
			assertEquals(BigDecimal.valueOf(900.00).setScale(2), baseItemDetail.getLineItemAmount());
			assertEquals(BigDecimal.valueOf(1), baseItemDetail.getQuantityInvoiced());
			validateVatInfo(baseItemDetail.getVatInfoArray());
			validatePostingDetails(baseItemDetail.getPostingDetailsArray());
		}
		assertEquals(1, count);
	}
	private void validatePostingDetails(PostingDetailsType[] postingDetails){
		int count=0;
		for(PostingDetailsType postingDetail:postingDetails){
			count++;
			PostingDetailIntegratedEnum aEnum = PostingDetailIntegratedEnum.getEnumForIndex(count);
			assertNotNull(String.valueOf(count),aEnum);
			assertEquals(aEnum.getDimension(), postingDetail.getDimension());
			assertEquals(aEnum.getCode(), postingDetail.getPostingCode());
		}
		assertEquals(7, count);
	}
	private void validateVatInfo(VatInfoType[] vatInfos){
		int count=0;
		for(VatInfoType vatInfo:vatInfos){
			count++;
			assertEquals(BigDecimal.valueOf(25), vatInfo.getVatPercent());
		}
		assertEquals(1, count);
	}
	private void validateHeader(InvoiceHeaderType header){
		assertEquals("Faktura", header.getInvoiceType().getCodetext());
		assertEquals("380", header.getInvoiceType().getStringValue());
		assertEquals("Orginal", header.getInvoiceStatus().getCodetext());
		assertEquals("9", header.getInvoiceStatus().getStringValue());
		assertEquals("123456789", header.getInvoiceNumber());
		assertEquals(dateFormatter.format(Calendar.getInstance().getTime()), header.getInvoiceDate());
		validateSupplier(header.getSupplier());
		validateBuyer(header.getBuyer());
		validateInvoicee(header.getInvoicee());
		validatePayment(header.getPayment());
		validateRefs(header.getRefArray());
		validateFreeText(header.getFreeTextArray());
	}
	private void validateFreeText(String[] freeTextArray){
		int count=0;
		for(String text:freeTextArray){
			count++;
			assertEquals("testTekst:   *******  Fakturabeløpet deb/kred Deres fakturakonto på forfallsdagen. Eventuell forsinkelsesrente tilkommer. Betaling skjer til ICA Norge AS som administrerer ICA Konto på oppdrag fra de fakturerende selskap innen ICA. Kundefordringer fra andre ICA selskap er ikke overdratt til ICA Norge AS.", text);
		}
		assertEquals(1, count);
	}
	private void validateRefs(RefWithCodeType[] refs){
		int count=0;
		for(RefWithCodeType ref:refs){
			count++;
			assertEquals("FRAF", ref.getCode());
			assertEquals("FF", ref.getText());
		}
		assertEquals(1, count);
	}
	private void validatePayment(PaymentType payment){
		assertEquals(dateFormatter.format(Calendar.getInstance().getTime()), payment.getDueDate());
		assertEquals("NOK", payment.getCurrency());
	}
	private void validateInvoicee(PartyType invoicee){
		validatePartyType(invoicee,"1","testNavn","juridiskEierAdr1","juridiskEierPostnr","juridiskEierPoststed");
		assertEquals("9999", invoicee.getDepartment());
	}
	private void validateSupplier(PartyType supplier){
		validatePartyType(supplier, "7080000068982", "100", "Postboks 6500, Rodeløkka", "0501", "Oslo");
		assertEquals("NO963132794MVA", supplier.getVatId());
	}
	private void validateBuyer(PartyType buyer){
		validatePartyType(buyer,"1","test Avd: 9999","testAdr1","1111","testPoststed");
		assertEquals("9999", buyer.getDepartment());
	}
	private void validatePartyType(PartyType partyType,String locationId,String name,String address1,String postalCode,String postalDistrict){
		assertEquals(locationId, partyType.getLocationId());
		assertEquals(name, partyType.getName());
		assertEquals(address1, partyType.getPostalAddress().getAddress1());
		assertEquals(postalCode, partyType.getPostalAddress().getPostalCode());
		assertEquals(postalDistrict, partyType.getPostalAddress().getPostalDistrict());
	}
	private Rik2AvdV getRik2AvdV(){
		Rik2AvdV rik2AvdV = new Rik2AvdV();
		rik2AvdV.setAvdnr(9999);
		rik2AvdV.setAvtaletype("F");
		rik2AvdV.setLokasjonsnr(BigDecimal.valueOf(1));
		rik2AvdV.setNavn("test");
		rik2AvdV.setAdr1("testAdr1");
		rik2AvdV.setPostnr(1111);
		rik2AvdV.setPoststed("testPoststed");
		rik2AvdV.setJuridiskEierNavn("testNavn");
		rik2AvdV.setJuridiskEierAdr1("juridiskEierAdr1");
		rik2AvdV.setJuridiskEierPostnr("juridiskEierPostnr");
		rik2AvdV.setJuridiskEierPoststed("juridiskEierPoststed");
		return rik2AvdV;
	}
	private Faktura getFaktura(Set<FakturaLinje> invoiceLines){
		Faktura faktura = new Faktura();
		faktura.setFakturaId(111);
		faktura.setAvdnr(9999);
		faktura.setFakturaLinjes(invoiceLines);
		faktura.setFakturaDato(Calendar.getInstance().getTime());
		faktura.setForfallDato(Calendar.getInstance().getTime());
		faktura.setTotalBelop(BigDecimal.valueOf(1000));
		faktura.setMvaBelop(BigDecimal.valueOf(100));
		faktura.setBelop(BigDecimal.valueOf(900));
		faktura.setFakturaNr("123456789");
		
		BokfSelskap selskap = new BokfSelskap();
		selskap.setLokasjonsnr("7080000068982");
		selskap.setNavn("100");
		selskap.setAdresse2("Postboks 6500, Rodeløkka");
		selskap.setPostnr("0501");
		selskap.setPoststed("Oslo");
		selskap.setKontonr("7058 06 57808");
		selskap.setOrgNr("963 132 794 MVA");
		selskap.setMomsid("NO963132794MVA");
		faktura.setBokfSelskap(selskap);
		
		Avdeling avdeling = new Avdeling();
		avdeling.setKid("1234");
		faktura.setAvdeling(avdeling);
		return faktura;
	}
	
	private Set<FakturaLinje> getInvoiceLines(){
		Set<FakturaLinje> lines = new HashSet<FakturaLinje>();
		FakturaLinje linje = new FakturaLinje();
		BetingelseType betingelseType=new BetingelseType();
		Mva xmlMva = new Mva();
		xmlMva.setMvaVerdi(BigDecimal.valueOf(25));
		xmlMva.setMvaKode("03");
		
		betingelseType.setXmlMvaF(xmlMva);
		betingelseType.setXmlKontoF("987654321");
		linje.setBetingelseType(betingelseType);
		linje.setBelop(BigDecimal.valueOf(900));
		linje.setMvaBelop(BigDecimal.valueOf(100));
		linje.setLinjeBeskrivelse("linjeBeskrivelse");
		linje.setPeriode("periode");
		linje.setTotalBelop(BigDecimal.valueOf(1000));
		lines.add(linje);
		
		return lines;
	}
	
	private List<ApplParam> getParamList(){
		final List<ApplParam> e2bParams=new ArrayList<ApplParam>();
		ApplParam param = new ApplParam();
		param.setParamName("e2b_interchange_id");
		param.setParamValue("1");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_envelope_to");
		param.setParamValue("7080005026994");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_test_indicator");
		param.setParamValue("0");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_message_owner");
		param.setParamValue("e2b");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_message_type");
		param.setParamValue("Invoice");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_message_version");
		param.setParamValue("3.1");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_document_type");
		param.setParamValue("380");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_document_type_txt");
		param.setParamValue("Faktura");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_document_status");
		param.setParamValue("9");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_document_status_txt");
		param.setParamValue("Orginal");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_currency");
		param.setParamValue("NOK");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_pskonto_navn");
		param.setParamValue("pskonto");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_psavdeling_navn");
		param.setParamValue("psavdeling");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_bruttobeløp_navn");
		param.setParamValue("bruttobelop");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_mvakode_navn");
		param.setParamValue("mvakode");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_nettobeløp_navn");
		param.setParamValue("nettobelop");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_mvaprosent_navn");
		param.setParamValue("mvaprosent");
		e2bParams.add(param);
		
		param = new ApplParam();
		param.setParamName("e2b_mvabeløp_navn");
		param.setParamValue("mvabelop");
		e2bParams.add(param);
		
		return e2bParams;
	}

	private enum PostingDetailIntegratedEnum{
		PSKONTO(1,"pskonto","987654321"),
		PSAVDELING(2,"psavdeling","9999"),
		BRUTTOBELOP(3,"bruttobelop","1000.00"),
		MVAKODE(4,"mvakode","03"),
		NETTOBELOP(5,"nettobelop","900.00"),
		MVAPROSENT(6,"mvaprosent","25"),
		MVABELOP(7,"mvabelop","100.00"),;
		private int index;
		private String dimension;
		private String postingCode;
		private static Map<Integer,PostingDetailIntegratedEnum> enums=new HashMap<Integer, PostingDetailIntegratedEnum>();
		static{
			PostingDetailIntegratedEnum[] enumArray=PostingDetailIntegratedEnum.values();
			for(PostingDetailIntegratedEnum aEnum:enumArray){
				enums.put(aEnum.getIndex(),aEnum);
			}
		}
		private PostingDetailIntegratedEnum(int aIndex,String aDimension,String aCode){
			index=aIndex;
			dimension=aDimension;
			postingCode=aCode;
		}
		public int getIndex(){
			return index;
		}
		public String getDimension(){
			return dimension;
		}
		public String getCode(){
			return postingCode;
		}
		public static PostingDetailIntegratedEnum getEnumForIndex(int aIndex){
			return enums.get(aIndex);
		}
	}
}
