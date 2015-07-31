package no.ica.fraf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Tabell AVDLEING_KONTRAKT
 * 
 * @author abr99
 * 
 */
public class AvdelingKontrakt extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdelingKontraktId;

	/**
	 * 
	 */
	private KontraktType kontraktType;

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
	private AvregningType avregningType;

	/**
	 * 
	 */
	private AvregningBasisType avregningBasisType;

	/**
	 * 
	 */
	private AvregningFrekvensType avregningFrekvensType;

	/**
	 * 
	 */
	private no.ica.fraf.model.Avdeling avdeling;

	/**
	 * 
	 */
	private FornyelseType fornyelseType;

	/**
	 * 
	 */
	private Date underskriftDato;

	/**
	 * @param avdelingKontraktId
	 * @param kontraktType
	 * @param fraDato
	 * @param tilDato
	 * @param avregningType
	 * @param avregningBasisType
	 * @param avregningFrekvensType
	 * @param avdeling
	 * @param fornyelseType
	 * @param underskriftDato
	 */
	public AvdelingKontrakt(Integer avdelingKontraktId,
			KontraktType kontraktType, Date fraDato, Date tilDato,
			AvregningType avregningType, AvregningBasisType avregningBasisType,
			AvregningFrekvensType avregningFrekvensType,
			no.ica.fraf.model.Avdeling avdeling, FornyelseType fornyelseType,
			Date underskriftDato) {
		this.avdelingKontraktId = avdelingKontraktId;
		this.kontraktType = kontraktType;
		this.fraDato = fraDato;
		this.tilDato = tilDato;
		this.avregningType = avregningType;
		this.avregningBasisType = avregningBasisType;
		this.avregningFrekvensType = avregningFrekvensType;
		this.avdeling = avdeling;
		this.fornyelseType = fornyelseType;
		this.underskriftDato = underskriftDato;
	}

	/**
	 * 
	 */
	public AvdelingKontrakt() {
	}

	/**
	 * @param avdelingKontraktId
	 * @param avdeling
	 */
	public AvdelingKontrakt(Integer avdelingKontraktId,
			no.ica.fraf.model.Avdeling avdeling) {
		this.avdelingKontraktId = avdelingKontraktId;
		this.avdeling = avdeling;
	}

	/**
	 * @return id
	 */
	public Integer getAvdelingKontraktId() {
		return this.avdelingKontraktId;
	}

	/**
	 * @param avdelingKontraktId
	 */
	public void setAvdelingKontraktId(Integer avdelingKontraktId) {
		this.avdelingKontraktId = avdelingKontraktId;
	}

	/**
	 * @return type
	 */
	public KontraktType getKontraktType() {
		return this.kontraktType;
	}

	/**
	 * @param kontraktType
	 */
	public void setKontraktType(KontraktType kontraktType) {
		this.kontraktType = kontraktType;
	}

	/**
	 * @return fra dato
	 */
	public Date getFraDato() {
		return this.fraDato;
	}

	/**
	 * @param fraDato
	 */
	public void setFraDato(Date fraDato) {
		this.fraDato = fraDato;
	}

	/**
	 * @return til dato
	 */
	public Date getTilDato() {
		return this.tilDato;
	}

	/**
	 * @param tilDato
	 */
	public void setTilDato(Date tilDato) {
		this.tilDato = tilDato;
	}

	/**
	 * @return avregning
	 */
	public AvregningType getAvregningType() {
		return this.avregningType;
	}

	/**
	 * @param avregningType
	 */
	public void setAvregningType(AvregningType avregningType) {
		this.avregningType = avregningType;
	}

	/**
	 * @return avregningbasis
	 */
	public AvregningBasisType getAvregningBasisType() {
		return this.avregningBasisType;
	}

	/**
	 * @param avregningBasisType
	 */
	public void setAvregningBasisType(AvregningBasisType avregningBasisType) {
		this.avregningBasisType = avregningBasisType;
	}

	/**
	 * @return frekvens
	 */
	public AvregningFrekvensType getAvregningFrekvensType() {
		return this.avregningFrekvensType;
	}

	/**
	 * @param avregningFrekvensType
	 */
	public void setAvregningFrekvensType(
			AvregningFrekvensType avregningFrekvensType) {
		this.avregningFrekvensType = avregningFrekvensType;
	}

	/**
	 * @return avdeling
	 */
	public no.ica.fraf.model.Avdeling getAvdeling() {
		return this.avdeling;
	}

	/**
	 * @param avdeling
	 */
	public void setAvdeling(no.ica.fraf.model.Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingKontrakt))
			return false;
		AvdelingKontrakt castOther = (AvdelingKontrakt) other;
		return new EqualsBuilder().append(avdelingKontraktId,
				castOther.avdelingKontraktId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingKontraktId).toHashCode();
	}

	/**
	 * @return fornyelsetype
	 */
	public FornyelseType getFornyelseType() {
		return fornyelseType;
	}

	/**
	 * @param fornyelseType
	 */
	public void setFornyelseType(FornyelseType fornyelseType) {
		this.fornyelseType = fornyelseType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"kontraktType", kontraktType).append("fraDato", fraDato)
				.append("tilDato", tilDato).append("avregningType",
						avregningType).append("avregningBasisType",
						avregningBasisType).append("avregningFrekvensType",
						avregningFrekvensType).toString();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return avdelingKontraktId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Kontrakt";
	}

	/**
	 * @return undersktiftsdato
	 */
	public Date getUnderskriftDato() {
		return underskriftDato;
	}

	/**
	 * @param underskriftDato
	 */
	public void setUnderskriftDato(Date underskriftDato) {
		this.underskriftDato = underskriftDato;
	}
}
