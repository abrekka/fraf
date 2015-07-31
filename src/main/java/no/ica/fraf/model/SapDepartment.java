package no.ica.fraf.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SapDepartment extends BaseObject implements Department{
	private Integer avdnr;
	private String avdelingNavn;
	private String kjede;
	private String region;
	private String datasetConcorde;
	//private Integer juridiskEierId;
	private String adr1;
	private String adr2;
	private Integer postnr;
	private String poststed;
	private String ansvarlig;
	private Date dtSlutt;
	private Date dtStart;
	private String avtaletype;
	private Integer butiksNr;
	private Integer driftsleder;
	private BigDecimal lokasjonsnr;
	
	//private SapLegalOwner sapLegalOwner;
	private String juridiskEierNavn;
	private String driftslederNavn;
	private String juridiskEierOrgNr;
	private String juridiskEierAdr1;
	private String juridiskEierPostnr;
	private String juridiskEierPoststed;
	
	public Integer getAvdnr() {
		return avdnr;
	}
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}
	public String getAvdelingNavn() {
		return avdelingNavn;
	}
	public void setAvdelingNavn(String avdelingNavn) {
		this.avdelingNavn = avdelingNavn;
	}
	public String getKjede() {
		return kjede;
	}
	public void setKjede(String kjede) {
		this.kjede = kjede;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDatasetConcorde() {
		return datasetConcorde;
	}
	public void setDatasetConcorde(String datasetConcorde) {
		this.datasetConcorde = datasetConcorde;
	}
	/*public Integer getJuridiskEierId() {
		return juridiskEierId;
	}
	public void setJuridiskEierId(Integer juridiskEierId) {
		this.juridiskEierId = juridiskEierId;
	}*/
	public String getAdr1() {
		return adr1;
	}
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}
	public String getAdr2() {
		return adr2;
	}
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}
	public Integer getPostnr() {
		return postnr;
	}
	public void setPostnr(Integer postnr) {
		this.postnr = postnr;
	}
	public String getPoststed() {
		return poststed;
	}
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}
	public String getAnsvarlig() {
		return ansvarlig;
	}
	public void setAnsvarlig(String ansvarlig) {
		this.ansvarlig = ansvarlig;
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
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SapDepartment))
			return false;
		SapDepartment castOther = (SapDepartment) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).toHashCode();
	}
	@Override
	public String toString() {
		return avdelingNavn;
	}
	public void setButiksNr(Integer aButiksNr) {
		butiksNr=aButiksNr;
		
	}
	public void setDriftsleder(Integer aDriftsleder) {
		driftsleder=aDriftsleder;
		
	}
	public Object getButiksNr() {
		return butiksNr;
	}
	public Object getDriftsleder() {
		return driftsleder;
	}
	public String getDepartmentName() {
		return avdelingNavn;
	}
	/*public SapLegalOwner getSapLegalOwner() {
		return sapLegalOwner;
	}*/
	/*public void setSapLegalOwner(SapLegalOwner sapLegalOwner) {
		this.sapLegalOwner = sapLegalOwner;
	}*/
	public String getJuridiskEierAdr1() {
		return juridiskEierAdr1;
	}
	public String getJuridiskEierNavn() {
		//return sapLegalOwner!=null?sapLegalOwner.getLegalOwnerName():null;
		return juridiskEierNavn;
	}
	public String getJuridiskEierPostnr() {
		return juridiskEierPostnr;
	}
	public String getJuridiskEierPoststed() {
		return juridiskEierPoststed;
	}
	public BigDecimal getLokasjonsnr() {
		return lokasjonsnr;
	}
	public void setLokasjonsnr(BigDecimal lokasjonsnr) {
		this.lokasjonsnr = lokasjonsnr;
	}
	
	public void setJuridiskEierNavn(String aJuridiskEierNavn) {
		this.juridiskEierNavn=aJuridiskEierNavn;
		
	}
	public void setDriftslederNavn(String aDriftslederNavn) {
		this.driftslederNavn=aDriftslederNavn;
		
	}
	public String getDriftslederNavn() {
		return driftslederNavn;
	}
	public void setJuridiskEierOrgNr(String aJuridiskEierOrgNr) {
		this.juridiskEierOrgNr=aJuridiskEierOrgNr;
		
	}
	public String getJuridiskEierOrgNr() {
		return juridiskEierOrgNr;
	}
	public void setJuridiskEierAdr1(String aAdr) {
		this.juridiskEierAdr1=aAdr;
		
	}
	public void setJuridiskEierPostnr(String aPostnr) {
		this.juridiskEierPostnr=aPostnr;
		
	}
	public void setJuridiskEierPoststed(String aPoststed) {
		this.juridiskEierPoststed=aPoststed;
		
	}
	/* (non-Javadoc)
	 * @see no.ica.fraf.model.Department#getChainName()
	 */
	public String getChainName() {
		return kjede;
	}
	
}
