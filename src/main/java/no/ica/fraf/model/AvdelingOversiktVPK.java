package no.ica.fraf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Nøkkelklasse for AvdelingOversiktV
 * 
 * @author abr99
 * 
 */
public class AvdelingOversiktVPK implements Serializable {
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
	private Integer avdelingBetingelseId;

	/**
	 * 
	 */
	private Integer aar;

	/**
	 * 
	 */
	public AvdelingOversiktVPK() {
	}

	/**
	 * @param id
	 * @param avdnr
	 * @param aar
	 */
	public AvdelingOversiktVPK(Integer id, Integer avdnr, Integer aar) {
		avdelingBetingelseId = id;
		this.avdnr = avdnr;
		this.aar = aar;
	}

	/**
	 * @return betingelseid
	 */
	public Integer getAvdelingBetingelseId() {
		return avdelingBetingelseId;
	}

	/**
	 * @param avdelingBetingelseId
	 */
	public void setAvdelingBetingelseId(Integer avdelingBetingelseId) {
		this.avdelingBetingelseId = avdelingBetingelseId;
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
	 * @return år
	 */
	public Integer getAar() {
		return aar;
	}

	/**
	 * @param aar
	 */
	public void setAar(Integer aar) {
		this.aar = aar;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingOversiktVPK))
			return false;
		AvdelingOversiktVPK castOther = (AvdelingOversiktVPK) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).append(
				avdelingBetingelseId, castOther.avdelingBetingelseId).append(
				aar, castOther.aar).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).append(avdelingBetingelseId)
				.append(aar).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"avdnr", avdnr).append("avdelingBetingelseId",
				avdelingBetingelseId).append("aar", aar).toString();
	}

}
