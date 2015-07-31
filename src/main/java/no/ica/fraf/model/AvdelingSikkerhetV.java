package no.ica.fraf.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for view AVDELING_SIKKERHET_V
 * 
 * @author abr99
 * 
 */
public class AvdelingSikkerhetV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private AvdelingSikkerhetVPK avdelingSikkerhetVPK;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private String avdelingNavn;

	/**
	 * 
	 */
	private String juridiskNavn;

	/**
	 * 
	 */
	private String sikkerhetTypeTekst;

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private Integer tinglyst;

	/**
	 * 
	 */
	private String kommentar;

	private String status;

	/**
	 * 
	 */
	public AvdelingSikkerhetV() {
		super();
	}

	/**
	 * @param avdelingSikkerhetVPK
	 * @param avdnr
	 * @param avdelingNavn
	 * @param juridiskNavn
	 * @param sikkerhetTypeTekst
	 * @param sikkerhetVerdi
	 * @param tinglyst
	 * @param kommentar
	 */
	public AvdelingSikkerhetV(AvdelingSikkerhetVPK avdelingSikkerhetVPK,
			Integer avdnr, String avdelingNavn, String juridiskNavn,
			String sikkerhetTypeTekst, Integer tinglyst,
			String kommentar,String aStatus) {
		super();
		this.avdelingSikkerhetVPK = avdelingSikkerhetVPK;
		this.avdnr = avdnr;
		this.avdelingNavn = avdelingNavn;
		this.juridiskNavn = juridiskNavn;
		this.sikkerhetTypeTekst = sikkerhetTypeTekst;
		
		this.tinglyst = tinglyst;
		this.kommentar = kommentar;
		this.status=aStatus;
	}

	/**
	 * @return navn
	 */
	public String getAvdelingNavn() {
		return avdelingNavn;
	}

	/**
	 * @param avdelingNavn
	 */
	public void setAvdelingNavn(String avdelingNavn) {
		this.avdelingNavn = avdelingNavn;
	}

	/**
	 * @return nøkkel
	 */
	public AvdelingSikkerhetVPK getAvdelingSikkerhetVPK() {
		return avdelingSikkerhetVPK;
	}

	/**
	 * @param avdelingSikkerhetVPK
	 */
	public void setAvdelingSikkerhetVPK(
			AvdelingSikkerhetVPK avdelingSikkerhetVPK) {
		this.avdelingSikkerhetVPK = avdelingSikkerhetVPK;
	}

	/**
	 * @return avdelingnummer
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
	 * @return juridisknavn
	 */
	public String getJuridiskNavn() {
		return juridiskNavn;
	}

	/**
	 * @param juridiskNavn
	 */
	public void setJuridiskNavn(String juridiskNavn) {
		this.juridiskNavn = juridiskNavn;
	}

	/**
	 * @return kommentar
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * @param kommentar
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	/**
	 * @return sikkerhetstype
	 */
	public String getSikkerhetTypeTekst() {
		return sikkerhetTypeTekst;
	}

	/**
	 * @param sikkerhetTypeTekst
	 */
	public void setSikkerhetTypeTekst(String sikkerhetTypeTekst) {
		this.sikkerhetTypeTekst = sikkerhetTypeTekst;
	}

	/**
	 * @return sikkerhetsverdi
	 */
	public String getSikkerhetVerdi() {
		return avdelingSikkerhetVPK.getSikkerhetVerdi();
	}

	

	/**
	 * @return om det er tinglyst
	 */
	public Integer getTinglyst() {
		return tinglyst;
	}

	/**
	 * @param tinglyst
	 */
	public void setTinglyst(Integer tinglyst) {
		this.tinglyst = tinglyst;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingSikkerhetV))
			return false;
		AvdelingSikkerhetV castOther = (AvdelingSikkerhetV) other;
		return new EqualsBuilder().append(avdelingSikkerhetVPK,
				castOther.avdelingSikkerhetVPK).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingSikkerhetVPK).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("avdelingSikkerhetVPK",
				avdelingSikkerhetVPK).append("avdnr", avdnr).append(
				"avdelingNavn", avdelingNavn).append("juridiskNavn",
				juridiskNavn).append("sikkerhetTypeTekst", sikkerhetTypeTekst)
				.append("tinglyst",
						tinglyst).append("kommentar", kommentar).toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
