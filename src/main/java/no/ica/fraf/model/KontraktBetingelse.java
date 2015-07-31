package no.ica.fraf.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell KONTRALT_BETINGELSE
 * 
 * @author abr99
 * 
 */
public class KontraktBetingelse extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer kontraktBetingelseId;

	/** nullable persistent field */
	private BigDecimal sats;

	/** nullable persistent field */
	private BigDecimal belop;

	/** persistent field */
	private no.ica.fraf.model.KontraktType kontraktType;

	/** persistent field */
	private no.ica.fraf.model.BetingelseType betingelseType;

	/**
	 * 
	 */
	private AvregningFrekvensType avregningFrekvensType;

	/**
	 * 
	 */
	private AvregningType avregningType;

	/**
	 * @param kontraktBetingelseId
	 * @param sats
	 * @param belop
	 * @param kontraktType
	 * @param betingelseType
	 * @param avregningFrekvensType
	 * @param avregningType
	 */
	public KontraktBetingelse(Integer kontraktBetingelseId, BigDecimal sats,
			BigDecimal belop, no.ica.fraf.model.KontraktType kontraktType,
			no.ica.fraf.model.BetingelseType betingelseType,
			AvregningFrekvensType avregningFrekvensType,
			AvregningType avregningType) {
		this.kontraktBetingelseId = kontraktBetingelseId;
		this.sats = sats;
		this.belop = belop;
		this.kontraktType = kontraktType;
		this.betingelseType = betingelseType;
		this.avregningFrekvensType = avregningFrekvensType;
		this.avregningType = avregningType;
	}

	/** default constructor */
	public KontraktBetingelse() {
	}

	/**
	 * @param kontraktBetingelseId
	 * @param kontraktType
	 * @param betingelseType
	 */
	public KontraktBetingelse(Integer kontraktBetingelseId,
			no.ica.fraf.model.KontraktType kontraktType,
			no.ica.fraf.model.BetingelseType betingelseType) {
		this.kontraktBetingelseId = kontraktBetingelseId;
		this.kontraktType = kontraktType;
		this.betingelseType = betingelseType;
	}

	/**
	 * @return id
	 */
	public Integer getKontraktBetingelseId() {
		return this.kontraktBetingelseId;
	}

	/**
	 * @param kontraktBetingelseId
	 */
	public void setKontraktBetingelseId(Integer kontraktBetingelseId) {
		this.kontraktBetingelseId = kontraktBetingelseId;
	}

	/**
	 * @return sats
	 */
	public BigDecimal getSats() {
		return this.sats;
	}

	/**
	 * @param sats
	 */
	public void setSats(BigDecimal sats) {
		this.sats = sats;
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
	 * @return kontrakttype
	 */
	public no.ica.fraf.model.KontraktType getKontraktType() {
		return this.kontraktType;
	}

	/**
	 * @param kontraktType
	 */
	public void setKontraktType(no.ica.fraf.model.KontraktType kontraktType) {
		this.kontraktType = kontraktType;
	}

	/**
	 * @return betingelsetype
	 */
	public no.ica.fraf.model.BetingelseType getBetingelseType() {
		return this.betingelseType;
	}

	/**
	 * @param betingelseType
	 */
	public void setBetingelseType(
			no.ica.fraf.model.BetingelseType betingelseType) {
		this.betingelseType = betingelseType;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof KontraktBetingelse))
			return false;
		KontraktBetingelse castOther = (KontraktBetingelse) other;
		return new EqualsBuilder().append(kontraktType, castOther.kontraktType)
				.append(betingelseType, castOther.betingelseType).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kontraktType)
				.append(betingelseType).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("kontraktType", kontraktType)
				.append("betingelseType", betingelseType).toString();
	}

	/**
	 * @return avregningfrekvens
	 */
	public AvregningFrekvensType getAvregningFrekvensType() {
		return avregningFrekvensType;
	}

	/**
	 * @param avregningFrekvensType
	 */
	public void setAvregningFrekvensType(
			AvregningFrekvensType avregningFrekvensType) {
		this.avregningFrekvensType = avregningFrekvensType;
	}

	/**
	 * @return avregningtype
	 */
	public AvregningType getAvregningType() {
		return avregningType;
	}

	/**
	 * @param avregningType
	 */
	public void setAvregningType(AvregningType avregningType) {
		this.avregningType = avregningType;
	}
}
