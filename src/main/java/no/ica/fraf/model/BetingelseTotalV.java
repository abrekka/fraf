package no.ica.fraf.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for view BETINGELSE_TOTAL_V
 * 
 * @author abr99
 * 
 */
public class BetingelseTotalV extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private BigDecimal belop;

	/**
	 * 
	 */
	private BetingelseTotalVPK betingelseTotalVPK;

	/**
	 * 
	 */
	private BigDecimal mvaBelop;

	/**
	 * 
	 */
	private BigDecimal totalBelop;

	/**
	 * 
	 */
	private Integer betingelseGruppeId;

	/**
	 * 
	 */
	public BetingelseTotalV() {
	}

	/**
	 * @param totalVPK
	 * @param mvaBelop
	 * @param totalBelop
	 * @param belop
	 * @param betingelseGruppeId
	 */
	public BetingelseTotalV(BetingelseTotalVPK totalVPK, BigDecimal mvaBelop,
			BigDecimal totalBelop, BigDecimal belop, Integer betingelseGruppeId) {
		betingelseTotalVPK = totalVPK;
		this.mvaBelop = mvaBelop;
		this.totalBelop = totalBelop;
		this.belop = belop;
		this.betingelseGruppeId = betingelseGruppeId;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getBetingelseTotalVPK()
	 */
	public BetingelseTotalVPK getBetingelseTotalVPK() {
		return betingelseTotalVPK;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#setBetingelseTotalVPK(no.ica.fraf.model.BetingelseTotalVPK)
	 */
	public void setBetingelseTotalVPK(BetingelseTotalVPK betingelseTotalVPK) {
		this.betingelseTotalVPK = betingelseTotalVPK;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getBelop()
	 */
	public BigDecimal getBelop() {
		return belop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#setBelop(java.math.BigDecimal)
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getBetingelseNavn()
	 */
	public String getBetingelseNavn() {
		return betingelseTotalVPK.getBetingelseNavn();
	}
	

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getBokfSelskap()
	 */
	public String getBokfSelskap() {
		return betingelseTotalVPK.getBokfSelskap();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getFraPeriode()
	 */
	public Integer getFraPeriode() {
		return betingelseTotalVPK.getFraPeriode();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getMvaBelop()
	 */
	public BigDecimal getMvaBelop() {
		return mvaBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#setMvaBelop(java.math.BigDecimal)
	 */
	public void setMvaBelop(BigDecimal mvaBelop) {
		this.mvaBelop = mvaBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getTilPeriode()
	 */
	public Integer getTilPeriode() {
		return betingelseTotalVPK.getTilPeriode();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getTotalBelop()
	 */
	public BigDecimal getTotalBelop() {
		return totalBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#setTotalBelop(java.math.BigDecimal)
	 */
	public void setTotalBelop(BigDecimal totalBelop) {
		this.totalBelop = totalBelop;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getAar()
	 */
	public Integer getAar() {
		return betingelseTotalVPK.getAar();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BetingelseTotalV))
			return false;
		BetingelseTotalV castOther = (BetingelseTotalV) other;
		return new EqualsBuilder().append(betingelseTotalVPK,
				castOther.betingelseTotalVPK).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(betingelseTotalVPK).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"betingelseTotalVPK", betingelseTotalVPK).append("mvaBelop",
				mvaBelop).append("totalBelop", totalBelop).toString();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#getBetingelseGruppeId()
	 */
	public Integer getBetingelseGruppeId() {
		return betingelseGruppeId;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.BetingelseTotalVInterface#setBetingelseGruppeId(java.lang.Integer)
	 */
	public void setBetingelseGruppeId(Integer betingelseGruppeId) {
		this.betingelseGruppeId = betingelseGruppeId;
	}


}
