package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell REGNSAP_KLADD
 * 
 * @author abr99
 * 
 */
public class RegnskapKladd extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer regnskapKladdId;

	/** nullable persistent field */
	private Faktura faktura;

	/** nullable persistent field */
	private String selskap;

	/** nullable persistent field */
	private Integer avdnr;

	/** nullable persistent field */
	private String konto;

	/** nullable persistent field */
	private Date regnskapDato;

	/** nullable persistent field */
	private BigDecimal belop;

	/** nullable persistent field */
	private String tekst;

	/** nullable persistent field */
	private String mvaKode;

	/** nullable persistent field */
	private String kontoType;

	/** nullable persistent field */
	private String fakturaNr;

	/** nullable persistent field */
	private Date forfallDato;

	/** nullable persistent field */
	private String kladdNavn;

	/** persistent field */
	private no.ica.fraf.model.Bunt bunt;
	private String prosjekt;

	/**
	 * @param regnskapKladdId
	 * @param faktura
	 * @param selskap
	 * @param avdnr
	 * @param konto
	 * @param regnskapDato
	 * @param belop
	 * @param tekst
	 * @param mvaKode
	 * @param kontoType
	 * @param fakturaNr
	 * @param forfallDato
	 * @param kladdNavn
	 * @param bunt
	 */
	public RegnskapKladd(Integer regnskapKladdId, Faktura faktura,
			String selskap, Integer avdnr, String konto, Date regnskapDato,
			BigDecimal belop, String tekst, String mvaKode, String kontoType,
			String fakturaNr, Date forfallDato, String kladdNavn,
			no.ica.fraf.model.Bunt bunt,String prosjekt) {
		this.regnskapKladdId = regnskapKladdId;
		this.faktura = faktura;
		this.selskap = selskap;
		this.avdnr = avdnr;
		this.konto = konto;
		this.regnskapDato = regnskapDato;
		this.belop = belop;
		this.tekst = tekst;
		this.mvaKode = mvaKode;
		this.kontoType = kontoType;
		this.fakturaNr = fakturaNr;
		this.forfallDato = forfallDato;
		this.kladdNavn = kladdNavn;
		this.bunt = bunt;
		this.prosjekt=prosjekt;
	}

	/** default constructor */
	public RegnskapKladd() {
	}

	/**
	 * @param regnskapKladdId
	 * @param bunt
	 */
	public RegnskapKladd(Integer regnskapKladdId, no.ica.fraf.model.Bunt bunt) {
		this.regnskapKladdId = regnskapKladdId;
		this.bunt = bunt;
	}

	/**
	 * @return id
	 */
	public Integer getRegnskapKladdId() {
		return this.regnskapKladdId;
	}

	/**
	 * @param regnskapKladdId
	 */
	public void setRegnskapKladdId(Integer regnskapKladdId) {
		this.regnskapKladdId = regnskapKladdId;
	}

	/**
	 * @return faktura
	 */
	public Faktura getFaktura() {
		return this.faktura;
	}

	/**
	 * @param faktura
	 */
	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}

	/**
	 * @return selskap
	 */
	public String getSelskap() {
		return this.selskap;
	}

	/**
	 * @param selskap
	 */
	public void setSelskap(String selskap) {
		this.selskap = selskap;
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return this.avdnr;
	}

	/**
	 * @param avdnr
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return konto
	 */
	public String getKonto() {
		return this.konto;
	}

	/**
	 * @param konto
	 */
	public void setKonto(String konto) {
		this.konto = konto;
	}

	/**
	 * @return regnskapdato
	 */
	public Date getRegnskapDato() {
		return this.regnskapDato;
	}

	/**
	 * @param regnskapDato
	 */
	public void setRegnskapDato(Date regnskapDato) {
		this.regnskapDato = regnskapDato;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getBelop() {
		return this.belop;
	}

	/**
	 * @param belop
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return tekst
	 */
	public String getTekst() {
		return this.tekst;
	}

	/**
	 * @param tekst
	 */
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	/**
	 * @return mvakode
	 */
	public String getMvaKode() {
		return this.mvaKode;
	}

	/**
	 * @param mvaKode
	 */
	public void setMvaKode(String mvaKode) {
		this.mvaKode = mvaKode;
	}

	/**
	 * @return kontotype
	 */
	public String getKontoType() {
		return this.kontoType;
	}

	/**
	 * @param kontoType
	 */
	public void setKontoType(String kontoType) {
		this.kontoType = kontoType;
	}

	/**
	 * @return fakturanr
	 */
	public String getFakturaNr() {
		return this.fakturaNr;
	}

	/**
	 * @param fakturaNr
	 */
	public void setFakturaNr(String fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	/**
	 * @return forfallsdato
	 */
	public Date getForfallDato() {
		return this.forfallDato;
	}

	/**
	 * @param forfallDato
	 */
	public void setForfallDato(Date forfallDato) {
		this.forfallDato = forfallDato;
	}

	/**
	 * @return kladdenavn
	 */
	public String getKladdNavn() {
		return this.kladdNavn;
	}

	/**
	 * @param kladdNavn
	 */
	public void setKladdNavn(String kladdNavn) {
		this.kladdNavn = kladdNavn;
	}

	/**
	 * @return bunt
	 */
	public no.ica.fraf.model.Bunt getBunt() {
		return this.bunt;
	}

	/**
	 * @param bunt
	 */
	public void setBunt(no.ica.fraf.model.Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof RegnskapKladd))
			return false;
		RegnskapKladd castOther = (RegnskapKladd) other;
		return new EqualsBuilder().append(regnskapKladdId,
				castOther.regnskapKladdId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(regnskapKladdId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"regnskapKladdId", regnskapKladdId).append("faktura", faktura)
				.append("selskap", selskap).append("avdnr", avdnr).append(
						"konto", konto).append("regnskapDato", regnskapDato)
				.append("belop", belop).append("tekst", tekst).append(
						"mvaKode", mvaKode).append("kontoType", kontoType)
				.append("fakturaNr", fakturaNr).append("forfallDato",
						forfallDato).append("kladdNavn", kladdNavn).append(
						"bunt", bunt).toString();
	}

	public String getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(String prosjekt) {
		this.prosjekt = prosjekt;
	}

}
