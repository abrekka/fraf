package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FakturaVStandalone implements FakturaVInterface{
	/**
	 * 
	 */
	private Integer fakturaId;

	/**
	 * 
	 */
	private String mottakerNavn;

	/**
	 * 
	 */
	private String adresse1;

	/**
	 * 
	 */
	private String adresse2;

	/**
	 * 
	 */
	private String postnr;

	/**
	 * 
	 */
	private String poststed;

	/**
	 * 
	 */
	private String juridiskNavn;

	/**
	 * 
	 */
	private Date fakturaDato;

	/**
	 * 
	 */
	private Date forfallDato;

	/**
	 * 
	 */
	private String fakturaNr;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private BigDecimal belop;

	/**
	 * 
	 */
	private BigDecimal mvaBelop;

	/**
	 * 
	 */
	private BigDecimal totalBelop;

	/**
	 * 
	 */
	private Integer buntId;

	/**
	 * 
	 */
	private String fakturaTittel;

	/**
	 * 
	 */
	private String kid;

	/**
	 * 
	 */
	private String bokfSelskap;

	/**
	 * 
	 */
	private String fakturertAv;

	/**
	 * 
	 */
	private String avtalenr;

	/**
	 * 
	 */
	private String icaKontoTekst;

	/**
	 * 
	 */
	private String avsenderNavn;

	/**
	 * 
	 */
	private String avsenderAdresse1;

	/**
	 * 
	 */
	private String avsenderAdresse2;

	/**
	 * 
	 */
	private String avsenderAdresse3;

	/**
	 * 
	 */
	private String avsenderTelefon;

	/**
	 * 
	 */
	private String avsenderTelefax;

	/**
	 * 
	 */
	private String avsenderOrgNr;

	/**
	 * 
	 */
	private String avsenderKontonr;
	private Integer harSatsLinje;

	/**
	 * 
	 */
	public FakturaVStandalone() {
		super();
	}


	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderAdresse1()
	 */
	public String getAvsenderAdresse1() {
		return avsenderAdresse1;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderAdresse1(java.lang.String)
	 */
	public void setAvsenderAdresse1(String avsenderAdresse1) {
		this.avsenderAdresse1 = avsenderAdresse1;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderAdresse2()
	 */
	public String getAvsenderAdresse2() {
		return avsenderAdresse2;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderAdresse2(java.lang.String)
	 */
	public void setAvsenderAdresse2(String avsenderAdresse2) {
		this.avsenderAdresse2 = avsenderAdresse2;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderAdresse3()
	 */
	public String getAvsenderAdresse3() {
		return avsenderAdresse3;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderAdresse3(java.lang.String)
	 */
	public void setAvsenderAdresse3(String avsenderAdresse3) {
		this.avsenderAdresse3 = avsenderAdresse3;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderKontonr()
	 */
	public String getAvsenderKontonr() {
		return avsenderKontonr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderKontonr(java.lang.String)
	 */
	public void setAvsenderKontonr(String avsenderKontonr) {
		this.avsenderKontonr = avsenderKontonr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderNavn()
	 */
	public String getAvsenderNavn() {
		return avsenderNavn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderNavn(java.lang.String)
	 */
	public void setAvsenderNavn(String avsenderNavn) {
		this.avsenderNavn = avsenderNavn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderOrgNr()
	 */
	public String getAvsenderOrgNr() {
		return avsenderOrgNr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderOrgNr(java.lang.String)
	 */
	public void setAvsenderOrgNr(String avsenderOrgNr) {
		this.avsenderOrgNr = avsenderOrgNr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderTelefax()
	 */
	public String getAvsenderTelefax() {
		return avsenderTelefax;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderTelefax(java.lang.String)
	 */
	public void setAvsenderTelefax(String avsenderTelefax) {
		this.avsenderTelefax = avsenderTelefax;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvsenderTelefon()
	 */
	public String getAvsenderTelefon() {
		return avsenderTelefon;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvsenderTelefon(java.lang.String)
	 */
	public void setAvsenderTelefon(String avsenderTelefon) {
		this.avsenderTelefon = avsenderTelefon;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAdresse1()
	 */
	public String getAdresse1() {
		return adresse1;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAdresse1(java.lang.String)
	 */
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAdresse2()
	 */
	public String getAdresse2() {
		return adresse2;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAdresse2(java.lang.String)
	 */
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvdnr()
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvdnr(java.lang.Integer)
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getBelop()
	 */
	public BigDecimal getBelop() {
		return belop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setBelop(java.math.BigDecimal)
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getFakturaDato()
	 */
	public Date getFakturaDato() {
		return fakturaDato;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setFakturaDato(java.util.Date)
	 */
	public void setFakturaDato(Date fakturaDato) {
		this.fakturaDato = fakturaDato;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getFakturaId()
	 */
	public Integer getFakturaId() {
		return this.fakturaId;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setFakturaId(java.lang.Integer)
	 */
	public void setFakturaId(Integer fakturaId) {
		this.fakturaId = fakturaId;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getFakturaNr()
	 */
	public String getFakturaNr() {
		return fakturaNr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setFakturaNr(java.lang.String)
	 */
	public void setFakturaNr(String fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getForfallDato()
	 */
	public Date getForfallDato() {
		return forfallDato;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setForfallDato(java.util.Date)
	 */
	public void setForfallDato(Date forfallDato) {
		this.forfallDato = forfallDato;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getJuridiskNavn()
	 */
	public String getJuridiskNavn() {
		return juridiskNavn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setJuridiskNavn(java.lang.String)
	 */
	public void setJuridiskNavn(String juridiskNavn) {
		this.juridiskNavn = juridiskNavn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getMottakerNavn()
	 */
	public String getMottakerNavn() {
		return mottakerNavn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setMottakerNavn(java.lang.String)
	 */
	public void setMottakerNavn(String mottakerNavn) {
		this.mottakerNavn = mottakerNavn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getMvaBelop()
	 */
	public BigDecimal getMvaBelop() {
		return mvaBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setMvaBelop(java.math.BigDecimal)
	 */
	public void setMvaBelop(BigDecimal mvaBelop) {
		this.mvaBelop = mvaBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getPostnr()
	 */
	public String getPostnr() {
		return postnr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setPostnr(java.lang.String)
	 */
	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getPoststed()
	 */
	public String getPoststed() {
		return poststed;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setPoststed(java.lang.String)
	 */
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getTotalBelop()
	 */
	public BigDecimal getTotalBelop() {
		return totalBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setTotalBelop(java.math.BigDecimal)
	 */
	public void setTotalBelop(BigDecimal totalBelop) {
		this.totalBelop = totalBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getBuntId()
	 */
	public Integer getBuntId() {
		return buntId;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setBuntId(java.lang.Integer)
	 */
	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getFakturaTittel()
	 */
	public String getFakturaTittel() {
		return fakturaTittel;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setFakturaTittel(java.lang.String)
	 */
	public void setFakturaTittel(String fakturaTittel) {
		this.fakturaTittel = fakturaTittel;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getKid()
	 */
	public String getKid() {
		return kid;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setKid(java.lang.String)
	 */
	public void setKid(String kid) {
		this.kid = kid;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getBokfSelskap()
	 */
	public String getBokfSelskap() {
		return bokfSelskap;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setBokfSelskap(java.lang.String)
	 */
	public void setBokfSelskap(String bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getFakturertAv()
	 */
	public String getFakturertAv() {
		return fakturertAv;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setFakturertAv(java.lang.String)
	 */
	public void setFakturertAv(String fakturertAv) {
		this.fakturertAv = fakturertAv;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getAvtalenr()
	 */
	public String getAvtalenr() {
		return avtalenr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setAvtalenr(java.lang.String)
	 */
	public void setAvtalenr(String avtalenr) {
		this.avtalenr = avtalenr;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FakturaV))
			return false;
		FakturaVStandalone castOther = (FakturaVStandalone) other;
		return new EqualsBuilder().append(fakturaId, castOther.fakturaId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"fakturaId", fakturaId).toString();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getIcaKontoTekst()
	 */
	public String getIcaKontoTekst() {
		return icaKontoTekst;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setIcaKontoTekst(java.lang.String)
	 */
	public void setIcaKontoTekst(String icaKontoTekst) {
		this.icaKontoTekst = icaKontoTekst;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#getHarSatsLinje()
	 */
	public Integer getHarSatsLinje() {
		return harSatsLinje;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.FakturaVInterface#setHarSatsLinje(java.lang.Integer)
	 */
	public void setHarSatsLinje(Integer harSatsLinje) {
		this.harSatsLinje = harSatsLinje;
	}

}


