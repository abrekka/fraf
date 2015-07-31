package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for view RIK2_STORE_V
 * 
 * @author abr99
 * 
 */
public class Rik2StoreV extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private String selskap;

	/** identifier field */
	private Integer avdnr;

	/** identifier field */
	private String navn;

	/** identifier field */
	private String adr1;

	/** identifier field */
	private String adr2;

	/** identifier field */
	private String adr3;

	/** identifier field */
	private Integer postnr;

	/** identifier field */
	private String poststed;

	/** identifier field */
	private String ansvarlig;

	/** identifier field */
	private Integer region;

	/** identifier field */
	private Integer kjede;

	/**
	 * 
	 */
	private Integer driftsleder;

	/** identifier field */
	private Date dtStart;

	/** identifier field */
	private Date dtSlutt;

	/** identifier field */
	private String avtaletype;

	/**
	 * 
	 */
	private BigDecimal lokasjonsnr;

	/**
	 * 
	 */
	private Integer juridiskEier;

	/**
	 * 
	 */
	private Integer orgNr;

	/**
	 * 
	 */
	private String datasetConcorde;

	/**
	 * 
	 */
	private String juridiskEierNavn;

	/**
	 * 
	 */
	private String juridiskEierAdr1;

	/**
	 * 
	 */
	private Integer juridiskEierPostnr;

	/**
	 * 
	 */
	private String juridiskEierPoststed;

	/**
	 * @param selskap
	 * @param avdnr
	 * @param navn
	 * @param adr1
	 * @param adr2
	 * @param adr3
	 * @param postnr
	 * @param poststed
	 * @param ansvarlig
	 * @param region
	 * @param kjede
	 * @param dtStart
	 * @param dtSlutt
	 * @param avtaletype
	 * @param lokasjonsnr
	 * @param driftsleder
	 * @param juridiskeier
	 * @param orgNr
	 * @param datasetConcorde
	 * @param juridiskEierNavn 
	 * @param juridiskEierAdr1 
	 * @param juridiskEierPostnr 
	 * @param juridiskEierPoststed 
	 */
	public Rik2StoreV(String selskap, Integer avdnr, String navn, String adr1,
			String adr2, String adr3, Integer postnr, String poststed,
			String ansvarlig, Integer region, Integer kjede, Date dtStart,
			Date dtSlutt, String avtaletype, BigDecimal lokasjonsnr,
			Integer driftsleder, Integer juridiskeier, Integer orgNr,
			String datasetConcorde, String juridiskEierNavn,String juridiskEierAdr1,
	Integer juridiskEierPostnr,
	String juridiskEierPoststed) {
		this.selskap = selskap;
		this.avdnr = avdnr;
		this.navn = navn;
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.adr3 = adr3;
		this.postnr = postnr;
		this.poststed = poststed;
		this.ansvarlig = ansvarlig;
		this.region = region;
		this.kjede = kjede;
		this.driftsleder = driftsleder;
		this.dtStart = dtStart;
		this.dtSlutt = dtSlutt;
		this.avtaletype = avtaletype;
		this.lokasjonsnr = lokasjonsnr;
		this.juridiskEier = juridiskeier;
		this.orgNr = orgNr;
		this.datasetConcorde = datasetConcorde;
		this.juridiskEierNavn = juridiskEierNavn;
		this.juridiskEierAdr1=juridiskEierAdr1;
		this.juridiskEierPostnr=juridiskEierPostnr;
		this.juridiskEierPoststed=juridiskEierPoststed;
	}

	/** default constructor */
	public Rik2StoreV() {
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
	 * @return navn
	 */
	public String getNavn() {
		return this.navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @return adresse1
	 */
	public String getAdr1() {
		return this.adr1;
	}

	/**
	 * @param adr1
	 */
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	/**
	 * @return adresse2
	 */
	public String getAdr2() {
		return this.adr2;
	}

	/**
	 * @param adr2
	 */
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	/**
	 * @return adresse3
	 */
	public String getAdr3() {
		return this.adr3;
	}

	/**
	 * @param adr3
	 */
	public void setAdr3(String adr3) {
		this.adr3 = adr3;
	}

	/**
	 * @return postnummer
	 */
	public Integer getPostnr() {
		return this.postnr;
	}

	/**
	 * @param postnr
	 */
	public void setPostnr(Integer postnr) {
		this.postnr = postnr;
	}

	/**
	 * @return poststed
	 */
	public String getPoststed() {
		return this.poststed;
	}

	/**
	 * @param poststed
	 */
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	/**
	 * @return ansvarlig
	 */
	public String getAnsvarlig() {
		return this.ansvarlig;
	}

	/**
	 * @param ansvarlig
	 */
	public void setAnsvarlig(String ansvarlig) {
		this.ansvarlig = ansvarlig;
	}

	/**
	 * @return regionid
	 */
	public Integer getRegion() {
		return this.region;
	}

	/**
	 * @param region
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}

	/**
	 * @return kjedeid
	 */
	public Integer getKjede() {
		return this.kjede;
	}

	/**
	 * @param kjede
	 */
	public void setKjede(Integer kjede) {
		this.kjede = kjede;
	}

	/**
	 * @return startdato
	 */
	public Date getDtStart() {
		return this.dtStart;
	}

	/**
	 * @param dtStart
	 */
	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	/**
	 * @return sluttdato
	 */
	public Date getDtSlutt() {
		return this.dtSlutt;
	}

	/**
	 * @param dtSlutt
	 */
	public void setDtSlutt(Date dtSlutt) {
		this.dtSlutt = dtSlutt;
	}

	/**
	 * @return avtaletype
	 */
	public String getAvtaletype() {
		return this.avtaletype;
	}

	/**
	 * @param avtaletype
	 */
	public void setAvtaletype(String avtaletype) {
		this.avtaletype = avtaletype;
	}

	/**
	 * @return lokasjonsnummer
	 */
	public BigDecimal getLokasjonsnr() {
		return lokasjonsnr;
	}

	/**
	 * @param lokasjonsnr
	 */
	public void setLokasjonsnr(BigDecimal lokasjonsnr) {
		this.lokasjonsnr = lokasjonsnr;
	}

	/**
	 * @return driftslederid
	 */
	public Integer getDriftsleder() {
		return driftsleder;
	}

	/**
	 * @param driftsleder
	 */
	public void setDriftsleder(Integer driftsleder) {
		this.driftsleder = driftsleder;
	}

	/**
	 * @return id til juridisk eier
	 */
	public Integer getJuridiskEier() {
		return juridiskEier;
	}

	/**
	 * @param juridiskEier
	 */
	public void setJuridiskEier(Integer juridiskEier) {
		this.juridiskEier = juridiskEier;
	}

	/**
	 * @return orgnr
	 */
	public Integer getOrgNr() {
		return orgNr;
	}

	/**
	 * @param orgNr
	 */
	public void setOrgNr(Integer orgNr) {
		this.orgNr = orgNr;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("navn", navn).toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Rik2StoreV))
			return false;
		Rik2StoreV castOther = (Rik2StoreV) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).toHashCode();
	}

	/**
	 * @return Returns the datasetConcorde.
	 */
	public String getDatasetConcorde() {
		return datasetConcorde;
	}

	/**
	 * @param datasetConcorde
	 *            The datasetConcorde to set.
	 */
	public void setDatasetConcorde(String datasetConcorde) {
		this.datasetConcorde = datasetConcorde;
	}

	/**
	 * @return navn på juridisk eiere
	 */
	public String getJuridiskEierNavn() {
		return juridiskEierNavn;
	}

	/**
	 * @param juridiskEierNavn
	 */
	public void setJuridiskEierNavn(String juridiskEierNavn) {
		this.juridiskEierNavn = juridiskEierNavn;
	}

	/**
	 * @return adresse til juridisk eier
	 */
	public String getJuridiskEierAdr1() {
		return juridiskEierAdr1;
	}

	/**
	 * @param juridiskEierAdr1
	 */
	public void setJuridiskEierAdr1(String juridiskEierAdr1) {
		this.juridiskEierAdr1 = juridiskEierAdr1;
	}

	/**
	 * @return postnr til juridisk eier
	 */
	public Integer getJuridiskEierPostnr() {
		return juridiskEierPostnr;
	}

	/**
	 * @param juridiskEierPostnr
	 */
	public void setJuridiskEierPostnr(Integer juridiskEierPostnr) {
		this.juridiskEierPostnr = juridiskEierPostnr;
	}

	/**
	 * @return poststed til juridisk eier
	 */
	public String getJuridiskEierPoststed() {
		return juridiskEierPoststed;
	}

	/**
	 * @param juridiskEierPoststed
	 */
	public void setJuridiskEierPoststed(String juridiskEierPoststed) {
		this.juridiskEierPoststed = juridiskEierPoststed;
	}

}
