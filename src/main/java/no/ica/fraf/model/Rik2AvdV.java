package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for view RIK2_AVD_V
 * @author abr99
 *
 */
/**
 * @author abr99
 *
 */
/**
 * @author abr99
 *
 */
/**
 * @author abr99
 * 
 */
public class Rik2AvdV extends BaseObject implements Department{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	private Integer kjede;

	/**
	 * 
	 */
	private Integer region;

	/**
	 * 
	 */
	private String datasetConcorde;

	/**
	 * 
	 */
	private Integer juridiskEier;

	/**
	 * 
	 */
	private String adr1;

	/**
	 * 
	 */
	private String adr2;

	/**
	 * 
	 */
	private Integer postnr;

	/**
	 * 
	 */
	private String poststed;

	/**
	 * 
	 */
	private String ansvarlig;

	private Date dtSlutt;

	private Date dtStart;

	private Integer butiksNr;

	private String avtaletype;

	private Rik2KjedeV rik2KjedeV;

	private BigDecimal lokasjonsnr;

	private String juridiskEierPoststed;

	private String juridiskEierNavn;

	private String juridiskEierAdr1;
	private String juridiskEierPostnr;

	/**
	 * 
	 */
	public Rik2AvdV() {
		super();
	}

	/**
	 * @param avdnr
	 * @param navn
	 * @param kjede
	 * @param region
	 * @param datasetConcorde
	 * @param juridiskEier
	 * @param adr1
	 * @param adr2
	 * @param postnr
	 * @param poststed
	 * @param ansvarlig
	 */
	public Rik2AvdV(Integer avdnr, String navn, Integer kjede, Integer region,
			String datasetConcorde, Integer juridiskEier, String adr1,
			String adr2, Integer postnr, String poststed, String ansvarlig,
			Date dtSlutt, Date dtStart, Integer butiksNr, String avtaletype,
			Rik2KjedeV rik2KjedeV, BigDecimal lokasjonsnr,
			String juridiskEierPoststed, String juridiskEierNavn, String juridiskEierAdr1, String juridiskEierPostnr) {
		super();
		this.avdnr = avdnr;
		this.navn = navn;
		this.kjede = kjede;
		this.region = region;
		this.datasetConcorde = datasetConcorde;
		this.juridiskEier = juridiskEier;
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.postnr = postnr;
		this.poststed = poststed;
		this.ansvarlig = ansvarlig;
		this.dtSlutt = dtSlutt;
		this.dtStart = dtStart;
		this.butiksNr = butiksNr;
		this.avtaletype = avtaletype;
		this.rik2KjedeV = rik2KjedeV;
		this.lokasjonsnr = lokasjonsnr;
		this.juridiskEierPoststed = juridiskEierPoststed;
		this.juridiskEierNavn = juridiskEierNavn;
		this.juridiskEierAdr1=juridiskEierAdr1;
		this.juridiskEierPostnr=juridiskEierPostnr;
	}

	/**
	 * @return adresse1
	 */
	public String getAdr1() {
		return adr1;
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
		return adr2;
	}

	/**
	 * @param adr2
	 */
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	/**
	 * @return ansvarlig
	 */
	public String getAnsvarlig() {
		return ansvarlig;
	}

	/**
	 * @param ansvarlig
	 */
	public void setAnsvarlig(String ansvarlig) {
		this.ansvarlig = ansvarlig;
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
	 * @return selskap
	 */
	public String getDatasetConcorde() {
		return datasetConcorde;
	}

	/**
	 * @param datasetConcorde
	 */
	public void setDatasetConcorde(String datasetConcorde) {
		this.datasetConcorde = datasetConcorde;
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
	 * @return id til kjede
	 */
	public Integer getKjede() {
		return kjede;
	}

	/**
	 * @param kjede
	 */
	public void setKjede(Integer kjede) {
		this.kjede = kjede;
	}

	/**
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @return postnr
	 */
	public Integer getPostnr() {
		return postnr;
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
		return poststed;
	}

	/**
	 * @param poststed
	 */
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	/**
	 * @return id til region
	 */
	public Integer getRegion() {
		return region;
	}

	/**
	 * @param region
	 */
	public void setRegion(Integer region) {
		this.region = region;
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
		if (!(other instanceof Rik2AvdV))
			return false;
		Rik2AvdV castOther = (Rik2AvdV) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).toHashCode();
	}

	public Integer getButiksNr() {
		return butiksNr;
	}

	public void setButiksNr(Integer butiksNr) {
		this.butiksNr = butiksNr;
	}

	public Date getDtSlutt() {
		return dtSlutt;
	}

	public void setDtSlutt(Date dtSlutt) {
		this.dtSlutt = dtSlutt;
	}

	public Date getDtStart() {
		return dtStart;
	}

	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	public String getAvtaletype() {
		return avtaletype;
	}

	public void setAvtaletype(String avtaletype) {
		this.avtaletype = avtaletype;
	}

	public Rik2KjedeV getRik2KjedeV() {
		return rik2KjedeV;
	}

	public void setRik2KjedeV(Rik2KjedeV rik2KjedeV) {
		this.rik2KjedeV = rik2KjedeV;
	}

	public BigDecimal getLokasjonsnr() {
		return lokasjonsnr;
	}

	public void setLokasjonsnr(BigDecimal lokasjonsnr) {
		this.lokasjonsnr = lokasjonsnr;
	}

	public String getJuridiskEierPoststed() {
		return juridiskEierPoststed;
	}

	public void setJuridiskEierPoststed(String juridiskEierPoststed) {
		this.juridiskEierPoststed = juridiskEierPoststed;
	}

	public String getJuridiskEierNavn() {
		return juridiskEierNavn;
	}

	public void setJuridiskEierNavn(String juridiskEierNavn) {
		this.juridiskEierNavn = juridiskEierNavn;
	}

	public String getJuridiskEierAdr1() {
		return juridiskEierAdr1;
	}

	public void setJuridiskEierAdr1(String juridiskEierAdr1) {
		this.juridiskEierAdr1 = juridiskEierAdr1;
	}

	public String getJuridiskEierPostnr() {
		return juridiskEierPostnr;
	}

	public void setJuridiskEierPostnr(String juridiskEierPostnr) {
		this.juridiskEierPostnr = juridiskEierPostnr;
	}

	public String getDepartmentName() {
		return navn;
	}

	public String getChainName() {
		return rik2KjedeV!=null?rik2KjedeV.getNavn():null;
	}
}
