package no.ica.fraf.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.util.ModelUtil;

/**
 * Enum for e2b-parametre
 * 
 * @author abr99
 * 
 */
public enum E2bParamEnum {
	/**
	 * Locationid for egeneid
	 */
	E2B_SUPPLIER_LOCATION_ID_OWN("e2b_supplier_location_id_own"),
	/**
	 * Locationid for franchise
	 */
	//SUPPLIER_LOCATION_ID_FRANCHISE("e2b_supplier_location_id_franchise"),
	E2B_SUPPLIER_LOCATION_ID_FRANCHISE("e2b_supplier_location_id_franchise"),
	/**
	 * Locationid for tollpost
	 */
	//SUPPLIER_LOCATION_ID_TG("e2b_supplier_location_id_tg"),
	E2B_SUPPLIER_LOCATION_ID_TG("e2b_supplier_location_id_tg"),
	/**
	 * Leverandørnavn
	 */
	//SUPPLIER_NAME("e2b_supplier_name"),
	E2B_SUPPLIER_NAME("e2b_supplier_name"),
	/**
	 * Leverandørnavn fir tollpost
	 */
	//SUPPLIER_NAME_TG("e2b_supplier_name_tg"),
	E2B_SUPPLIER_NAME_TG("e2b_supplier_name_tg"),
	/**
	 * Leverandøradresse1
	 */
	E2B_SUPPLIER_ADDRESS1("e2b_supplier_address1"),
	/**
	 * Leverandøradresse1 for tollpost
	 */
	E2B_SUPPLIER_ADDRESS1_TG("e2b_supplier_address1_tg"),
	/**
	 * Leverandør postnummer
	 */
	E2B_SUPPLIER_POSTALCODE("e2b_supplier_postalcode"),
	/**
	 * Leverandør postnummer for tollpost
	 */
	E2B_SUPPLIER_POSTALCODE_TG("e2b_supplier_postalcode_tg"),
	/**
	 * Leverandør poststed
	 */
	E2B_SUPPLIER_DISTRICT("e2b_supplier_district"),
	/**
	 * Leverandør poststed for tollpost
	 */
	E2B_SUPPLIER_DISTRICT_TG("e2b_supplier_district_tg"),
	/**
	 * Leverandør orgnr
	 */
	E2B_SUPPLIER_ORG_NR("e2b_supplier_org_nr"),
	/**
	 * Leverandør orgnr for tollpost
	 */
	E2B_SUPPLIER_ORG_NR_TG("e2b_supplier_org_nr_tg"),
	/**
	 * Leverandør momskode
	 */
	E2B_SUPPLIER_VATID("e2b_supplier_vatid"),
	/**
	 * Leverandør momskode for tollpost
	 */
	E2B_SUPPLIER_VATID_TG("e2b_supplier_vatid_tg"),
	/**
	 * Leverandør kontonummer
	 */
	E2B_SUPPLIER_ACCOUNTNR("e2b_supplier_accountnr"),
	/**
	 * Leverandør kontonummer for tollpost
	 */
	E2B_SUPPLIER_ACCOUNTNR_TG("e2b_supplier_accountnr_tg"),
	/**
	 * Testindikator
	 */
	E2B_TEST_INDICATOR("e2b_test_indicator"),
	/**
	 * Meldingseier
	 */
	E2B_MESSAGE_OWNER("e2b_message_owner"),
	/**
	 * Meldingstype
	 */
	E2B_MESSAGE_TYPE("e2b_message_type"),
	/**
	 * Meldingsversjon
	 */
	E2B_MESSAGE_VERSION("e2b_message_version"),
	/**
	 * Dokumenttype
	 */
	E2B_DOCUMENT_TYPE("e2b_document_type"),
	/**
	 * Dokumenttypetekst
	 */
	E2B_DOCUMENT_TYPE_TXT("e2b_document_type_txt"),
	/**
	 * Dokumentstatus
	 */
	E2B_DOCUMENT_STATUS("e2b_document_status"),
	/**
	 * Dokumentstatustekst
	 */
	E2B_DOCUMENT_STATUS_TXT("e2b_document_status_txt"),
	/**
	 * Fakturamottaker egeneid
	 */
	E2B_INVOICEE_OWN_STORE_NAME("e2b_invoicee_own_store_name"),
	/**
	 * Lokasjnsnummer for fakturamottaker egeneid
	 */
	E2B_INVOICEE_OWN_STORE_LOCATION_ID("e2b_invoicee_own_store_location_id"),
	/**
	 * Adresse1 for fakturamottaker egeneid
	 */
	E2B_INVOICEE_OWN_STORE_ADDRESS1("e2b_invoicee_own_store_address1"),
	/**
	 * Postnummer for fakturamottaker egeneid
	 */
	E2B_INVOICEE_OWN_STORE_POSTALCODE("e2b_invoicee_own_store_postalcode"),
	/**
	 * Poststed for fakturamottaker egeneid
	 */
	E2B_INVOICEE_OWN_STORE_DISTRICT("e2b_invoicee_own_store_district"),
	/**
	 * Valuta
	 */
	E2B_CURRENCY("e2b_currency"),
	/**
	 * PS-konto for telekort franchise
	 */
	E2B_PSKONTO_CARD_FRANCHISE("e2b_pskonto_card_franchise"),
	/**
	 * PS-konto for provisjon franchise
	 */
	E2B_PSKONTO_COMMISION_FRANCHISE("e2b_pskonto_commision_franchise"),
	/**
	 * PS-konto telekort egeneid
	 */
	E2B_PSKONTO_CARD_OWN_STORE("e2b_pskonto_card_own_store"),
	/**
	 * PS-konto provisjon egeneid
	 */
	E2B_PSKONTO_COMMISION_OWN_STORE("e2b_pskonto_commision_own_store"),
	/**
	 * Mvakode telekort franchise
	 */
	E2B_MVACODE_CARD_FRANCHISE("e2b_mvacode_card_franchise"),
	/**
	 * Mvakode for oppkrav franchise for tollpost
	 */
	E2B_MVACODE_TG_OPPKRAV_FRANCHISE("e2b_mvacode_tg_oppkrav_franchise"),
	/**
	 * Mvakode for provisjon franchise for tollpost
	 */
	E2B_MVACODE_TG_PROVISJON_FRANCHISE("e2b_mvacode_tg_provisjon_franchise"),
	/**
	 * Mvakode telekort franchise
	 */
	E2B_MVACODE_CARD_OWN_STORE("e2b_mvacode_card_own_store"),
	/**
	 * Mvakode for oppkrav egeneide for tollpost
	 */
	E2B_MVACODE_TG_OPPKRAV_OWN_STORE("e2b_mvacode_tg_oppkrav_own_store"),
	/**
	 * Mvakode for provisjon egeneide for tollpost
	 */
	E2B_MVACODE_TG_PROVISJON_OWN_STORE("e2b_mvacode_tg_provisjon_own_store"),
	/**
	 * Mvatype for egeneid
	 */
	E2B_MVATYPE_CARD_OWN_STORE("e2b_mvatype_card_own_store"),
	/**
	 * Mvatype for oppkrav egeneide for tollpost
	 */
	E2B_MVATYPE_TG_OPPKRAV_OWN_STORE("e2b_mvatype_tg_oppkrav_own_store"),
	/**
	 * Mvakode provisjon franchise
	 */
	E2B_MVACODE_COMMISION_FRANCHISE("e2b_mvacode_commision_franchise"),
	/**
	 * Mvakode provisjon egeneid
	 */
	E2B_MVACODE_COMMISION_OWN_STORE("e2b_mvacode_commision_own_store"),
	/**
	 * Momsprosent telekort
	 */
	E2B_MVAPROCENT_CARD("e2b_mvaprocent_card"),
	/**
	 * Mvaprosent for oppkrav tollpost
	 */
	E2B_MVAPROCENT_TG_OPPKRAV("e2b_mvaprocent_tg_oppkrav"),
	/**
	 * Mvaprosent for provisjon tollpost
	 */
	E2B_MVAPROCENT_TG_PROVISJON("e2b_mvaprocent_tg_provisjon"),
	/**
	 * Momsprosent provisjon
	 */
	E2B_MVAPROCENT_COMMISION("e2b_mvaprocent_commision"),
	/**
	 * Momsbruktype telekort egeneid
	 */
	E2B_VATUSETYPE_CARD_OWN_STORE("e2b_vatusetype_card_own_store"),
	/**
	 * Vatuse for oppkrav egeneide tollpost
	 */
	E2B_VATUSETYPE_TG_OPPKRAV_OWN_STORE("e2b_vatusetype_tg_oppkrav_own_store"),
	/**
	 * Momsbruktype provisjon egeneid
	 */
	E2B_VATUSETYPE_COMMISION_OWN_STORE("e2b_vatusetype_commision_own_store"),
	/**
	 * Filsuffix
	 */
	E2B_FILE_SUFFIX("e2b_file_suffix"),
	/**
	 * Filsuffix for xml-fil tollpost
	 */
	E2B_FILE_SUFFIX_TG("e2b_file_suffix_tg"),
	/**
	 * PS-konto for oppkrav egeneide tollpost
	 */
	E2B_PSKONTO_TG_OPPKRAV_OWN_STORE("e2b_pskonto_tg_oppkrav_own_store"),
	/**
	 * PS-konto for oppjrav franchise tollpost
	 */
	E2B_PSKONTO_TG_OPPKRAV_FRANCHISE("e2b_pskonto_tg_oppkrav_franchise"),
	/**
	 * PS-konto provisjon franchise tollpost
	 */
	E2B_PSKONTO_TG_PROVISJON_FRANCHISE("e2b_pskonto_tg_provisjon_franchise"),
	/**
	 * PS-konto provisjon egeneide tollpost
	 */
	E2B_PSKONTO_TG_PROVISJON_OWN_STORE("e2b_pskonto_tg_provisjon_own_store"),
	/**
	 * Navn på felt for pskonto
	 */
	E2B_PSKONTO_NAVN("e2b_pskonto_navn"),
	/**
	 * Navn på felt for avdeling
	 */
	E2B_PSAVDELING_NAVN("e2b_psavdeling_navn"),
	/**
	 * Navn på felt for bruttobeløp
	 */
	E2B_BRUTTOBELØP_NAVN("e2b_bruttobeløp_navn"),
	/**
	 * Navn på felt for mvakode
	 */
	E2B_MVAKODE_NAVN("e2b_mvakode_navn"),
	/**
	 * Navn på felt for mvatype
	 */
	E2B_MVATYPE_NAVN("e2b_mvatype_navn"),
	/**
	 * Navn på felt for nettobeløp
	 */
	E2B_NETTOBELØP_NAVN("e2b_nettobeløp_navn"),
	/**
	 * Navn på felt for mvaprosent
	 */
	E2B_MVAPROSENT_NAVN("e2b_mvaprosent_navn"),
	/**
	 * Navn på felt for mvabeløp
	 */
	E2B_MVABELØP_NAVN("e2b_mvabeløp_navn"),
	/**
	 * Navn på felt for vatuse
	 */
	E2B_VATUSE_NAVN("e2b_vatuse_navn"),
	/**
	 * Verdi for feltet to i envelope på xml
	 */
	E2B_ENVELOPE_TO("e2b_envelope_to"),
	/**
	 * Verdi for feltet interchange id i XML
	 */
	E2B_INTERCHANGE_ID("e2b_interchange_id"),
	/**
	 * Filsuffix for FRAF-XML
	 */
	E2B_FILE_SUFFIX_FRAF("e2b_file_suffix_fraf"),
	E2B_BOOKING_ACCOUNTNUMBER_NAME("e2b_booking_accountnumber_name"),
	E2B_BOOKING_VATCODE_NAME("e2b_booking_vatcode_name"),
	E2B_BOOKING_COMPANY_NAME("e2b_booking_company_name"),
	E2B_BOOKING_DEPARTMENT_NAME("e2b_booking_department_name"), 
	E2B_PROJECTCODE_NAME("e2b_projectcode_name")
	;
	/**
	 * Parameterverdi
	 */
	//private String paramValue;

	/**
	 * 
	 */
	private String paramName;
	private static boolean initiated=false;
	private static ApplParamDAO applParamDAO;

	/**
	 * 
	 */
	//private static ApplParamManager applParamManager = (ApplParamManager) ModelUtil.getBean("applParamManager");

	/**
	 * @param aParamName
	 */
	private E2bParamEnum(String aParamName) {
		paramName = aParamName;
	}
	public String getParamName(){
		return paramName;
	}

	/**
	 * @return Returns the paramValue.
	 * @throws FrafException
	 */
	public String getParamValue() throws FrafException {
		initParams();
		String value = paramValues.get(SystemType.getSystemType(FrafMain.isStandalone()).ordinal());
		value = value!=null?value:paramValues.get(null);
		/*if(!initiated){
			initE2bParams();
		}*/
		if (value == null) {
			throw new FrafException(paramName + " mangler verdi");
		}
		return value;
	}

	/**
	 * Henter paramatervedi
	 * 
	 * @param value
	 */
	/*public void setParamValue(String value) {
		this.paramValue = value;
	}*/

	/**
	 * Henter enum basert på navn
	 * 
	 * @param name
	 * @return enum
	 */
	/*public static E2bParamEnum getE2bParamEnum(String name) {
		if (name.equalsIgnoreCase("e2b_supplier_location_id_own")) {
			return SUPPLIER_LOCATION_ID_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_supplier_location_id_franchise")) {
			return SUPPLIER_LOCATION_ID_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_supplier_name")) {
			return SUPPLIER_NAME;
		} else if (name.equalsIgnoreCase("e2b_supplier_address1")) {
			return SUPPLIER_ADDRESS1;
		} else if (name.equalsIgnoreCase("e2b_supplier_postalcode")) {
			return SUPPLIER_POSTAL_CODE;
		} else if (name.equalsIgnoreCase("e2b_supplier_district")) {
			return SUPPLIER_POSTAL_DISTRICT;
		} else if (name.equalsIgnoreCase("e2b_supplier_org_nr")) {
			return SUPPLIER_ORG_NR;
		} else if (name.equalsIgnoreCase("e2b_supplier_vatid")) {
			return SUPPLIER_VAT_ID;
		} else if (name.equalsIgnoreCase("e2b_supplier_accountnr")) {
			return SUPPLIER_ACCOUNT_NR;
		} else if (name.equalsIgnoreCase("e2b_test_indicator")) {
			return TEST_INDICATOR;
		} else if (name.equalsIgnoreCase("e2b_message_owner")) {
			return MESSAGE_OWNER;
		} else if (name.equalsIgnoreCase("e2b_message_type")) {
			return MESSAGE_TYPE;
		} else if (name.equalsIgnoreCase("e2b_message_version")) {
			return MESSAGE_VERSION;
		} else if (name.equalsIgnoreCase("e2b_document_type")) {
			return DOCUMENT_TYPE;
		} else if (name.equalsIgnoreCase("e2b_document_type_txt")) {
			return DOCUMENT_TYPE_TXT;
		} else if (name.equalsIgnoreCase("e2b_document_status")) {
			return DOCUMENT_STATUS;
		} else if (name.equalsIgnoreCase("e2b_document_status_txt")) {
			return DOCUMENT_STATUS_TXT;
		} else if (name.equalsIgnoreCase("e2b_invoicee_own_store_name")) {
			return INVOICEE_NAME_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_invoicee_own_store_location_id")) {
			return INVOICEE_LOCATION_ID_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_invoicee_own_store_address1")) {
			return INVOICEE_ADDRESS1_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_invoicee_own_store_postalcode")) {
			return INVOICEE_POSTAL_CODE_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_invoicee_own_store_district")) {
			return INVOICEE_POSTAL_DISTRICT_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_currency")) {
			return CURRENCY;
		} else if (name.equalsIgnoreCase("e2b_pskonto_card_franchise")) {
			return PS_ACCOUNT_CARD_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_pskonto_commision_franchise")) {
			return PS_ACCOUNT_COMMISSION_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_pskonto_card_own_store")) {
			return PS_ACCOUNT_CARD_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_pskonto_commision_own_store")) {
			return PS_ACCOUNT_COMMISSION_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_card_franchise")) {
			return MVA_CODE_CARD_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_card_own_store")) {
			return MVA_CODE_CARD_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvatype_card_own_store")) {
			return MVA_TYPE_CARD_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_commision_franchise")) {
			return MVA_CODE_COMMISSION_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_commision_own_store")) {
			return MVA_CODE_COMMISSION_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvaprocent_card")) {
			return MVA_PROCENT_CARD;
		} else if (name.equalsIgnoreCase("e2b_mvaprocent_commision")) {
			return MVA_PROCENT_COMMISSION;
		} else if (name.equalsIgnoreCase("e2b_vatusetype_card_own_store")) {
			return VAT_USE_TYPE_CARD_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_vatusetype_commision_own_store")) {
			return VAT_USE_TYPE_COMMISSION_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_file_suffix")) {
			return E2B_FILE_SUFFIX_ELFA;
		} else if (name.equalsIgnoreCase("e2b_pskonto_tg_oppkrav_own_store")) {
			return PS_ACCOUNT_TG_CLAIM_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_pskonto_tg_oppkrav_franchise")) {
			return PS_ACCOUNT_TG_CLAIM_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_tg_oppkrav_own_store")) {
			return MVA_CODE_TG_CLAIM_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvatype_tg_oppkrav_own_store")) {
			return MVA_TYPE_TG_CLAIM_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_tg_oppkrav_franchise")) {
			return MVA_CODE_TG_CLAIM_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_mvaprocent_tg_oppkrav")) {
			return MVA_PROCENT_TG_CLAIM;
		} else if (name.equalsIgnoreCase("e2b_vatusetype_tg_oppkrav_own_store")) {
			return VAT_USE_TYPE_TG_CLAIM_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_pskonto_navn")) {
			return E2B_PS_ACCOUNT_NAME;
		} else if (name.equalsIgnoreCase("e2b_psavdeling_navn")) {
			return E2B_PS_DEPARTMENT_NAME;
		} else if (name.equalsIgnoreCase("e2b_bruttobeløp_navn")) {
			return E2B_PS_GROSS_AMOUNT_NAME;
		} else if (name.equalsIgnoreCase("e2b_mvakode_navn")) {
			return E2B_PS_VAT_CODE_NAME;
		} else if (name.equalsIgnoreCase("e2b_mvatype_navn")) {
			return E2B_PS_VAT_TYPE_NAME;
		} else if (name.equalsIgnoreCase("e2b_nettobeløp_navn")) {
			return E2B_PS_NET_AMOUNT_NAME;
		} else if (name.equalsIgnoreCase("e2b_mvaprosent_navn")) {
			return E2B_PS_VAT_PERCENTAGE_NAME;
		} else if (name.equalsIgnoreCase("e2b_mvabeløp_navn")) {
			return E2B_PS_VAT_AMOUNT_NAME;
		} else if (name.equalsIgnoreCase("e2b_vatuse_navn")) {
			return E2B_PS_VAT_USE_NAME;
		} else if (name.equalsIgnoreCase("e2b_file_suffix_tg")) {
			return E2B_FILE_SUFFIX_TG;
		} else if (name.equalsIgnoreCase("e2b_envelope_to")) {
			return E2B_ENVELOPE_TO;
		} else if (name.equalsIgnoreCase("e2b_interchange_id")) {
			return E2B_INTERCHANGE_ID;
		} else if (name.equalsIgnoreCase("e2b_mvacode_tg_provisjon_own_store")) {
			return MVA_CODE_TG_COMMISSION_OWN_STORE;
		} else if (name.equalsIgnoreCase("e2b_mvacode_tg_provisjon_franchise")) {
			return MVA_CODE_TG_COMMISSION_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_mvaprocent_tg_provisjon")) {
			return MVA_PROCENT_TG_COMMISSION;
		} else if (name.equalsIgnoreCase("e2b_supplier_location_id_tg")) {
			return SUPPLIER_LOCATION_ID_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_name_tg")) {
			return SUPPLIER_NAME_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_address1_tg")) {
			return SUPPLIER_ADDRESS1_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_postalcode_tg")) {
			return SUPPLIER_POSTAL_CODE_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_district_tg")) {
			return SUPPLIER_POSTAL_DISTRICT_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_accountnr_tg")) {
			return SUPPLIER_ACCOUNT_NR_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_org_nr_tg")) {
			return SUPPLIER_ORG_NR_TG;
		} else if (name.equalsIgnoreCase("e2b_supplier_vatid_tg")) {
			return SUPPLIER_VAT_ID_TG;
		} else if (name.equalsIgnoreCase("e2b_file_suffix_fraf")) {
			return E2B_FILE_SUFFIX_FRAF;
		} else if (name.equalsIgnoreCase("e2b_pskonto_tg_provisjon_franchise")) {
			return PS_ACCOUNT_TG_COMMISSION_FRANCHISE;
		} else if (name.equalsIgnoreCase("e2b_pskonto_tg_provisjon_own_store")) {
			return PS_ACCOUNT_TG_COMMISSION_OWN_STORE;
		}
		else if (name.equalsIgnoreCase("e2b_booking_accountnumber_name")) {
			return E2B_BOOKING_ACCOUNTNUMBER_NAME;
		}
		else if (name.equalsIgnoreCase("e2b_booking_vatcode_name")) {
			return E2B_BOOKING_VATCODE_NAME;
		}
		else if (name.equalsIgnoreCase("e2b_booking_company_name")) {
			return E2B_BOOKING_COMPANY_NAME;
		}
		else if (name.equalsIgnoreCase("e2b_booking_department_name")) {
			return E2B_BOOKING_DEPARTMENT_NAME;
		}

		else {
			return null;
		}
	}*/
	
	public static void setApplParamDAO(ApplParamDAO paramDAO){
		applParamDAO=paramDAO;
	}
	
	private Map<Integer,String> paramValues=new HashMap<Integer, String>();
	
	public void setParamValue(String paramValue,Integer system){
		paramValues.put(system, paramValue);
	}
	
	private static void  initParams(){
		if(!initiated){
		initiated=true;
		applParamDAO=applParamDAO==null?(ApplParamDAO)ModelUtil.getBean("applParamDAO"):applParamDAO;
		
		List<ApplParam> params = applParamDAO.findByParamNameStartWith("e2b");
		

		if (params != null) {
			for (ApplParam param : params) {
				E2bParamEnum paramEnum = E2bParamEnum.valueOf(StringUtils.upperCase(param.getParamName()));
				paramEnum.setParamValue(param.getParamValue(), param.getSystemType());
			}
		}
		}
	}
	/**
	 * Initierer e2b-parametre
	 */
	/*public static void initE2bParams() {
		initiated=true;
		applParamDAO=applParamDAO==null?(ApplParamDAO)ModelUtil.getBean("applParamDAO"):applParamDAO;
		//List<ApplParamElfa> params = applParamManager.findByParamNameStartWith("e2b");
		List<ApplParam> params = applParamDAO.findByParamNameStartWith("e2b");
		E2bParamEnum paramEnum;

		if (params != null) {
			for (ApplParam param : params) {
				paramEnum = E2bParamEnum.getE2bParamEnum(param.getParamName());
				paramEnum.setParamValue(param.getParamValue());
			}
		}
	}*/
}
