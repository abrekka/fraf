package no.ica.fraf.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell EIERFORHOLD
 * 
 * @author atb
 * 
 */
public class Eierforhold extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer eierforholdId;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	private String fodseldatoOrgNr;

	/**
	 * 
	 */
	private Integer antallAksjer;

	/**
	 * 
	 */
	private BigDecimal paalydende;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	public Eierforhold() {
	}

	/**
	 * @param aksjer
	 * @param avdeling
	 * @param id
	 * @param fodseldatoOrgNr
	 * @param navn
	 * @param paalydende
	 */
	public Eierforhold(Integer aksjer, Avdeling avdeling, Integer id,
			String fodseldatoOrgNr, String navn, BigDecimal paalydende) {
		antallAksjer = aksjer;
		this.avdeling = avdeling;
		eierforholdId = id;
		this.fodseldatoOrgNr = fodseldatoOrgNr;
		this.navn = navn;
		this.paalydende = paalydende;
	}

	/**
	 * @return antall aksjer
	 */
	public Integer getAntallAksjer() {
		return antallAksjer;
	}

	/**
	 * @param antallAksjer
	 */
	public void setAntallAksjer(Integer antallAksjer) {
		this.antallAksjer = antallAksjer;
	}

	/**
	 * @return avdeling
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * @param avdeling
	 */
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * @return id
	 */
	public Integer getEierforholdId() {
		return eierforholdId;
	}

	/**
	 * @param eierforholdId
	 */
	public void setEierforholdId(Integer eierforholdId) {
		this.eierforholdId = eierforholdId;
	}

	/**
	 * @return fødsels- eller orgnr
	 */
	public String getFodseldatoOrgNr() {
		return fodseldatoOrgNr;
	}

	/**
	 * @param fodseldatoOrgNr
	 */
	public void setFodseldatoOrgNr(String fodseldatoOrgNr) {
		this.fodseldatoOrgNr = fodseldatoOrgNr;
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
	 * @return pålydende
	 */
	public BigDecimal getPaalydende() {
		return paalydende;
	}

	/**
	 * @param paalydende
	 */
	public void setPaalydende(BigDecimal paalydende) {
		this.paalydende = paalydende;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Eierforhold))
			return false;
		Eierforhold castOther = (Eierforhold) other;
		return new EqualsBuilder().append(eierforholdId,
				castOther.eierforholdId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(eierforholdId).toHashCode();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return eierforholdId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Eierforhold";
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"navn", navn).append("fodseldatoOrgNr", fodseldatoOrgNr)
				.append("antallAksjer", antallAksjer).append("paalydende",
						paalydende).toString();
	}

}
