package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell IMPORT_BETINGELSE
 * 
 * @author abr99
 * 
 */
public class ImportBetingelse extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer importBetingelseId;

	/**
	 * 
	 */
	private Date importDato;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private BigDecimal sumPrFrekvens;

	/**
	 * 
	 */
	private Date fraDato;

	/**
	 * 
	 */
	private Date tilDato;

	/**
	 * 
	 */
	private String betingelseTypeKode;

	/**
	 * 
	 */
	private String frekvensKode;

	/**
	 * 
	 */
	private String avregningTypeKode;

	/**
	 * 
	 */
	private String tekst;

	/**
	 * 
	 */
	private String feilmelding;

	/**
	 * 
	 */
	private String selskapNavn;

	/**
	 * 
	 */
	private String fakturaTekst;

	/**
	 * 
	 */
	private Integer fakturaTekstRek;

	/**
	 * 
	 */
	private String konto;

	/**
	 * 
	 */
	private String bokfAvdeling;

	/**
	 * 
	 */
	private String mvaKode;

	private String prosjekt;
	

	/**
	 * 
	 */
	public ImportBetingelse() {
	}

	/**
	 * @param importBetingelseId
	 * @param importDato
	 * @param avdnr
	 * @param sumPrFrekvens
	 * @param fraDato
	 * @param tilDato
	 * @param betingelseTypeKode
	 * @param frekvensKode
	 * @param avregningTypeKode
	 * @param tekst
	 * @param feilmelding
	 * @param selskapNavn
	 * @param fakturaTekst
	 * @param fakturaTekstRek
	 * @param konto
	 * @param bokfAvdeling
	 * @param mvaKode
	 */
	public ImportBetingelse(Integer importBetingelseId, Date importDato,
			Integer avdnr, BigDecimal sumPrFrekvens, Date fraDato,
			Date tilDato, String betingelseTypeKode, String frekvensKode,
			String avregningTypeKode, String tekst, String feilmelding,
			String selskapNavn, String fakturaTekst, Integer fakturaTekstRek,
			String konto, String bokfAvdeling, String mvaKode,String prosjekt) {
		this.importBetingelseId = importBetingelseId;
		this.importDato = importDato;
		this.avdnr = avdnr;
		this.sumPrFrekvens = sumPrFrekvens;
		this.fraDato = fraDato;
		this.tilDato = tilDato;
		this.betingelseTypeKode = betingelseTypeKode;
		this.frekvensKode = frekvensKode;
		this.avregningTypeKode = avregningTypeKode;
		this.tekst = tekst;
		this.feilmelding = feilmelding;
		this.selskapNavn = selskapNavn;
		this.fakturaTekst = fakturaTekst;
		this.fakturaTekstRek = fakturaTekstRek;
		this.konto = konto;
		this.bokfAvdeling = bokfAvdeling;
		this.mvaKode = mvaKode;
		this.prosjekt = prosjekt;
		
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/**
	 * @param avdnr
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return betingelsekode
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
	 * @return feilmelding
	 */
	public String getFeilmelding() {
		return feilmelding;
	}

	/**
	 * @param feilmelding
	 */
	public void setFeilmelding(String feilmelding) {
		this.feilmelding = feilmelding;
	}

	/**
	 * @return fra dato
	 */
	public Date getFraDato() {
		return fraDato;
	}

	/**
	 * @param fraDato
	 */
	public void setFraDato(Date fraDato) {
		this.fraDato = fraDato;
	}

	/**
	 * @return frekvenskode
	 */
	public String getFrekvensKode() {
		return frekvensKode;
	}

	/**
	 * @param frekvensKode
	 */
	public void setFrekvensKode(String frekvensKode) {
		this.frekvensKode = frekvensKode;
	}

	/**
	 * @return id
	 */
	public Integer getImportBetingelseId() {
		return importBetingelseId;
	}

	/**
	 * @param importBetingelseId
	 */
	public void setImportBetingelseId(Integer importBetingelseId) {
		this.importBetingelseId = importBetingelseId;
	}

	/**
	 * @return importdato
	 */
	public Date getImportDato() {
		return importDato;
	}

	/**
	 * @param importDato
	 */
	public void setImportDato(Date importDato) {
		this.importDato = importDato;
	}

	/**
	 * @return sum
	 */
	public BigDecimal getSumPrFrekvens() {
		return sumPrFrekvens;
	}

	/**
	 * @param sumPrFrekvens
	 */
	public void setSumPrFrekvens(BigDecimal sumPrFrekvens) {
		this.sumPrFrekvens = sumPrFrekvens;
	}

	/**
	 * @return tekst
	 */
	public String getTekst() {
		return tekst;
	}

	/**
	 * @param tekst
	 */
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	/**
	 * @return tildato
	 */
	public Date getTilDato() {
		return tilDato;
	}

	/**
	 * @param tilDato
	 */
	public void setTilDato(Date tilDato) {
		this.tilDato = tilDato;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("importBetingelseId",
				importBetingelseId).toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(importBetingelseId).toHashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ImportBetingelse))
			return false;
		ImportBetingelse castOther = (ImportBetingelse) other;
		return new EqualsBuilder().append(importBetingelseId,
				castOther.importBetingelseId).isEquals();
	}

	/**
	 * @return avregningkode
	 */
	public String getAvregningTypeKode() {
		return avregningTypeKode;
	}

	/**
	 * @param avregningTypeKode
	 */
	public void setAvregningTypeKode(String avregningTypeKode) {
		this.avregningTypeKode = avregningTypeKode;
	}

	/**
	 * @return selskapnavn
	 */
	public String getSelskapNavn() {
		return selskapNavn;
	}

	/**
	 * @param selskapNavn
	 */
	public void setSelskapNavn(String selskapNavn) {
		this.selskapNavn = selskapNavn;
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
	 * @return fakturatekstrekkefølge
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

	/**
	 * @return Returns the konto.
	 */
	public String getKonto() {
		return konto;
	}

	/**
	 * @param konto
	 *            The konto to set.
	 */
	public void setKonto(String konto) {
		this.konto = konto;
	}

	/**
	 * @return Returns the avdeling.
	 */
	public String getBokfAvdeling() {
		return bokfAvdeling;
	}

	/**
	 * @param bokfAvdeling
	 *            The avdeling to set.
	 */
	public void setBokfAvdeling(String bokfAvdeling) {
		this.bokfAvdeling = bokfAvdeling;
	}

	/**
	 * @return Returns the mvaKode.
	 */
	public String getMvaKode() {
		return mvaKode;
	}

	/**
	 * @param mvaKode
	 *            The mvaKode to set.
	 */
	public void setMvaKode(String mvaKode) {
		this.mvaKode = mvaKode;
	}

	public String getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(String prosjekt) {
		this.prosjekt = prosjekt;
	}

	

	
}
