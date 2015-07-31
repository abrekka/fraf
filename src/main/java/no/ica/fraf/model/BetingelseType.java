package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell BETINGELSE_TYPE
 * 
 * @author abr99
 * 
 */
public class BetingelseType extends BaseObject {

	private static final long serialVersionUID = 1L;

	private Integer betingelseTypeId;

	private String betingelseTypeKode;

	private String betingelseNavn;

	private no.ica.fraf.model.Mva mvaE;
	private no.ica.fraf.model.Mva mvaF;

	private Set kontraktBetingelses;

	private Set fakturaLinjes;

	private BetingelseGruppe betingelseGruppe;

	private String inntektskontoE;
	private String inntektskontoF;

	private String fakturaTekst;

	private BokfSelskap bokfSelskap;

	private Integer fakturaTekstRek;

	private String xmlKontoE;
	private String xmlKontoF;

	private no.ica.fraf.model.Mva xmlMvaE;
	private no.ica.fraf.model.Mva xmlMvaF;

	//private Rik2KjedeV rik2KjedeV;
	private Chain chain;

	private AvregningFrekvensType avregningFrekvensType;

	private AvregningType avregningType;

	private BigDecimal sats;

	private BigDecimal belop;

	private BigDecimal belopPrStk;

	private String bokfAvdelingE;
	private String bokfAvdelingF;
	private String xmlMvatypeE;
	private String xmlMvatypeF;
	private String xmlVatusetypeE;
	private String xmlVatusetypeF;
	private Integer linkAvtale;
	private Integer inaktiv;

	public BetingelseType(String betingelseNavn){
		this.betingelseNavn=betingelseNavn;
	}
	public BetingelseType(Integer betingelseTypeId, String betingelseNavn,
			no.ica.fraf.model.Mva mvaE,
			no.ica.fraf.model.Mva mvaF,
			Set kontraktBetingelses, Set fakturaLinjes,
			String betingelseTypeKode, BetingelseGruppe betingelseGruppe,
			String inntektskontoE, 
			String inntektskontoF,
			String fakturaTekst, BokfSelskap bokfSelskap,
			Integer fakturaTekstRek, 
			String xmlKontoE, 
			String xmlKontoF,
			Mva xmlMvaE,
			Mva xmlMvaF,
			//Rik2KjedeV rik2KjedeV,
			Chain chain,
			AvregningFrekvensType avregningFrekvensType,
			AvregningType avregningType, BigDecimal sats, BigDecimal belop,
			BigDecimal belopPrStk,final String aBokfAvdelingE,
			String xmlMvatypeE,
			String xmlMvatypeF,
			String xmlVatusetypeE,
			String xmlVatusetypeF,
			Integer linkAvtale,Integer isInaktiv,final String aBokfAvdelingF) {
		this.betingelseTypeId = betingelseTypeId;
		this.betingelseNavn = betingelseNavn;
		this.mvaE = mvaE;
		this.mvaF = mvaF;
		this.kontraktBetingelses = kontraktBetingelses;
		this.fakturaLinjes = fakturaLinjes;
		this.betingelseTypeKode = betingelseTypeKode;
		this.betingelseGruppe = betingelseGruppe;
		this.inntektskontoE = inntektskontoE;
		this.inntektskontoF = inntektskontoF;
		this.fakturaTekst = fakturaTekst;
		this.bokfSelskap = bokfSelskap;
		this.fakturaTekstRek = fakturaTekstRek;
		this.xmlKontoE = xmlKontoE;
		this.xmlKontoF = xmlKontoF;
		this.xmlMvaE = xmlMvaE;
		this.xmlMvaF = xmlMvaF;
		//this.rik2KjedeV = rik2KjedeV;
		this.chain=chain;
		this.avregningFrekvensType = avregningFrekvensType;
		this.avregningType = avregningType;
		this.sats = sats;
		this.belop = belop;
		this.belopPrStk = belopPrStk;
		this.bokfAvdelingE=aBokfAvdelingE;
		this.xmlMvatypeE=xmlMvatypeE;
		this.xmlMvatypeF=xmlMvatypeF;
		this.xmlVatusetypeE=xmlVatusetypeE;
		this.xmlVatusetypeF=xmlVatusetypeF;
		this.linkAvtale=linkAvtale;
		this.inaktiv=isInaktiv;
		this.bokfAvdelingF=aBokfAvdelingF;
	}

	/** default constructor */
	public BetingelseType() {
	}

	/**
	 * @param betingelseTypeId
	 * @param isDebit
	 * @param mva
	 * @param kontraktBetingelses
	 * @param fakturaLinjes
	 * @param betingelseGruppe
	 */
	public BetingelseType(Integer betingelseTypeId, 
			//Integer isDebit,
			Mva mvaE, 
			Mva mvaF,
			Set kontraktBetingelses,
			Set fakturaLinjes, // Set avdelingBetingelses,
			BetingelseGruppe betingelseGruppe) {
		this.betingelseTypeId = betingelseTypeId;
		//this.isDebit = isDebit;
		this.mvaE = mvaE;
		this.mvaF = mvaF;
		this.kontraktBetingelses = kontraktBetingelses;
		this.fakturaLinjes = fakturaLinjes;
		// this.avdelingBetingelses = avdelingBetingelses;
		this.betingelseGruppe = betingelseGruppe;
	}

	/**
	 * @return id
	 */
	public Integer getBetingelseTypeId() {
		return this.betingelseTypeId;
	}

	/**
	 * @param betingelseTypeId
	 */
	public void setBetingelseTypeId(Integer betingelseTypeId) {
		this.betingelseTypeId = betingelseTypeId;
	}

	/**
	 * @return navn
	 */
	public String getBetingelseNavn() {
		return this.betingelseNavn;
	}

	/**
	 * @param betingelseNavn
	 */
	public void setBetingelseNavn(String betingelseNavn) {
		this.betingelseNavn = betingelseNavn;
	}

	/**
	 * @return om betingeles er debit
	 */
	/*public Integer getIsDebit() {
		return this.isDebit;
	}*/

	/**
	 * @param isDebit
	 */
	/*public void setIsDebit(Integer isDebit) {
		this.isDebit = isDebit;
	}*/

	/**
	 * @return mva
	 */
	public no.ica.fraf.model.Mva getMvaE() {
		return this.mvaE;
	}

	/**
	 * @param mva
	 */
	public void setMvaE(no.ica.fraf.model.Mva mva) {
		this.mvaE = mva;
	}
	public no.ica.fraf.model.Mva getMvaF() {
		return this.mvaF;
	}

	/**
	 * @param mva
	 */
	public void setMvaF(no.ica.fraf.model.Mva mva) {
		this.mvaF = mva;
	}

	/**
	 * @return kontraktbetingelser
	 */
	public Set getKontraktBetingelses() {
		return this.kontraktBetingelses;
	}

	/**
	 * @param kontraktBetingelses
	 */
	public void setKontraktBetingelses(Set kontraktBetingelses) {
		this.kontraktBetingelses = kontraktBetingelses;
	}

	/**
	 * @return fakturalinjer
	 */
	public Set getFakturaLinjes() {
		return this.fakturaLinjes;
	}

	/**
	 * @param fakturaLinjes
	 */
	public void setFakturaLinjes(Set fakturaLinjes) {
		this.fakturaLinjes = fakturaLinjes;
	}

	/**
	 * @return avdelingbetingelser
	 */
	/*
	 * public Set getAvdelingBetingelses() { return this.avdelingBetingelses; }
	 */

	/**
	 * @param avdelingBetingelses
	 */
	/*
	 * public void setAvdelingBetingelses(Set avdelingBetingelses) {
	 * this.avdelingBetingelses = avdelingBetingelses; }
	 */

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BetingelseType))
			return false;
		BetingelseType castOther = (BetingelseType) other;
		return new EqualsBuilder().append(betingelseTypeId,
				castOther.betingelseTypeId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(betingelseTypeId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (betingelseTypeKode != null && betingelseNavn != null
				&& betingelseNavn.length() != 0) {
			return betingelseTypeKode + " - " + betingelseNavn;
		} else if (betingelseNavn != null && betingelseNavn.length() != 0) {
			return " - " + betingelseNavn;
		}
		return betingelseTypeKode;
	}

	/**
	 * @return kode
	 */
	public String getBetingelseTypeKode() {
		return betingelseTypeKode;
	}

	/**
	 * @param betingelseTypeKode
	 */
	public void setBetingelseTypeKode(String betingelseTypeKode) {
		this.betingelseTypeKode = betingelseTypeKode;
	}

	/**
	 * @return betingelsegruppe
	 */
	public BetingelseGruppe getBetingelseGruppe() {
		return betingelseGruppe;
	}

	/**
	 * @param betingelseGruppe
	 */
	public void setBetingelseGruppe(BetingelseGruppe betingelseGruppe) {
		this.betingelseGruppe = betingelseGruppe;
	}

	/**
	 * @return konto
	 */
	public String getInntektskontoE() {
		return inntektskontoE;
	}

	/**
	 * @param konto
	 */
	public void setInntektskontoE(String konto) {
		this.inntektskontoE = konto;
	}
	public String getInntektskontoF() {
		return inntektskontoF;
	}

	/**
	 * @param konto
	 */
	public void setInntektskontoF(String konto) {
		this.inntektskontoF = konto;
	}

	/**
	 * @return fakturatekst
	 */
	public String getFakturaTekst() {
		return fakturaTekst;
	}

	/**
	 * @param fakturaTekst
	 */
	public void setFakturaTekst(String fakturaTekst) {
		this.fakturaTekst = fakturaTekst;
	}

	/**
	 * @return bokføringsselskap
	 */
	public BokfSelskap getBokfSelskap() {
		return bokfSelskap;
	}

	/**
	 * @param bokfSelskap
	 */
	public void setBokfSelskap(BokfSelskap bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/**
	 * @return rekkefølge
	 */
	public Integer getFakturaTekstRek() {
		return fakturaTekstRek;
	}

	/**
	 * @param fakturaTekstRek
	 */
	public void setFakturaTekstRek(Integer fakturaTekstRek) {
		this.fakturaTekstRek = fakturaTekstRek;
	}

	public String getXmlKontoE() {
		return xmlKontoE;
	}

	public void setXmlKontoE(String xmlKonto) {
		this.xmlKontoE = xmlKonto;
	}
	public String getXmlKontoF() {
		return xmlKontoF;
	}

	public void setXmlKontoF(String xmlKonto) {
		this.xmlKontoF = xmlKonto;
	}

	public Mva getXmlMvaE() {
		return xmlMvaE;
	}

	public void setXmlMvaE(Mva xmlMva) {
		this.xmlMvaE = xmlMva;
	}
	public Mva getXmlMvaF() {
		return xmlMvaF;
	}

	public void setXmlMvaF(Mva xmlMva) {
		this.xmlMvaF = xmlMva;
	}

	public Chain getChain() {
		return chain;
	}

	public void setChain(Chain chain) {
		this.chain = chain;
	}

	public AvregningFrekvensType getAvregningFrekvensType() {
		return avregningFrekvensType;
	}

	public void setAvregningFrekvensType(
			AvregningFrekvensType avregningFrekvensType) {
		this.avregningFrekvensType = avregningFrekvensType;
	}

	public AvregningType getAvregningType() {
		return avregningType;
	}

	public void setAvregningType(AvregningType avregningType) {
		this.avregningType = avregningType;
	}

	public BigDecimal getSats() {
		return sats;
	}

	public void setSats(BigDecimal sats) {
		this.sats = sats;
	}

	public BigDecimal getBelop() {
		return belop;
	}

	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	public BigDecimal getBelopPrStk() {
		return belopPrStk;
	}

	public void setBelopPrStk(BigDecimal belopPrStk) {
		this.belopPrStk = belopPrStk;
	}

	public String getBokfAvdelingE() {
		return bokfAvdelingE;
	}

	public void setBokfAvdelingE(String bokfAvdelingE) {
		this.bokfAvdelingE = bokfAvdelingE;
	}
	public String getBokfAvdelingF() {
		return bokfAvdelingF;
	}

	public void setBokfAvdelingF(String bokfAvdelingF) {
		this.bokfAvdelingF = bokfAvdelingF;
	}

	/*public Integer getForFilial() {
		return forFilial;
	}

	public void setForFilial(Integer forFilial) {
		this.forFilial = forFilial;
	}*/

	public String getXmlMvatypeE() {
		return xmlMvatypeE;
	}

	public void setXmlMvatypeE(String xmlMvatype) {
		this.xmlMvatypeE = xmlMvatype;
	}
	public String getXmlMvatypeF() {
		return xmlMvatypeF;
	}

	public void setXmlMvatypeF(String xmlMvatype) {
		this.xmlMvatypeF = xmlMvatype;
	}

	public String getXmlVatusetypeE() {
		return xmlVatusetypeE;
	}

	public void setXmlVatusetypeE(String xmlVatusetype) {
		this.xmlVatusetypeE = xmlVatusetype;
	}
	public String getXmlVatusetypeF() {
		return xmlVatusetypeF;
	}

	public void setXmlVatusetypeF(String xmlVatusetype) {
		this.xmlVatusetypeF = xmlVatusetype;
	}

	public Integer getLinkAvtale() {
		return linkAvtale;
	}

	public void setLinkAvtale(Integer linkAvtale) {
		this.linkAvtale = linkAvtale;
	}
	public Integer getInaktiv() {
		return inaktiv;
	}
	public void setInaktiv(Integer inaktiv) {
		this.inaktiv = inaktiv;
	}

}
