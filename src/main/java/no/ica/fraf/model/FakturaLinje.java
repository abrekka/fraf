package no.ica.fraf.model;

import java.math.BigDecimal;

import no.ica.fraf.xml.InvoiceItemInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell FAKTURA_LINJE
 * 
 * @author atb
 * 
 */
public class FakturaLinje extends BaseObject implements InvoiceItemInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer fakturaLinjeId;

	/** nullable persistent field */
	private BigDecimal sats;

	/** nullable persistent field */
	private String linjeBeskrivelse;

	/** nullable persistent field */
	private BigDecimal belop;

	/** nullable persistent field */
	private BigDecimal omsetningBelop;

	/** nullable persistent field */
	private BigDecimal avregningBelop;

	/** nullable persistent field */
	private String mvaKode;

	/** nullable persistent field */
	private BigDecimal mvaBelop;

	/** nullable persistent field */
	private BigDecimal grunnlagBelop;

	/** nullable persistent field */
	private BigDecimal totalBelop;

	/** nullable persistent field */
	private BigDecimal fastBelop;

	/** persistent field */
	private no.ica.fraf.model.BetingelseType betingelseType;

	/** persistent field */
	private no.ica.fraf.model.Faktura faktura;

	/**
	 * 
	 */
	private String periode;
	private AvdelingBetingelse avdelingBetingelse;
	private Integer reversert;

	private String prosjekt;

	/**
	 * @param fakturaLinjeId
	 * @param sats
	 * @param linjeBeskrivelse
	 * @param belop
	 * @param omsetningBelop
	 * @param avregningBelop
	 * @param mvaKode
	 * @param mvaBelop
	 * @param grunnlagBelop
	 * @param totalBelop
	 * @param fastBelop
	 * @param betingelseType
	 * @param faktura
	 * @param periode
	 */
	public FakturaLinje(Integer fakturaLinjeId, BigDecimal sats,
			String linjeBeskrivelse, BigDecimal belop,
			BigDecimal omsetningBelop, BigDecimal avregningBelop,
			String mvaKode, BigDecimal mvaBelop, BigDecimal grunnlagBelop,
			BigDecimal totalBelop, BigDecimal fastBelop,
			no.ica.fraf.model.BetingelseType betingelseType,
			no.ica.fraf.model.Faktura faktura
			, String periode,AvdelingBetingelse avdelingBetingelse,Integer reversert
			) {
		this.fakturaLinjeId = fakturaLinjeId;
		this.sats = sats;
		this.linjeBeskrivelse = linjeBeskrivelse;
		this.belop = belop;
		this.omsetningBelop = omsetningBelop;
		this.avregningBelop = avregningBelop;
		this.mvaKode = mvaKode;
		this.mvaBelop = mvaBelop;
		this.grunnlagBelop = grunnlagBelop;
		this.totalBelop = totalBelop;
		this.fastBelop = fastBelop;
		this.betingelseType = betingelseType;
		this.faktura = faktura;
		this.periode = periode;
		this.avdelingBetingelse=avdelingBetingelse;
		this.reversert=reversert;
	}

	/** default constructor */
	public FakturaLinje() {
	}

	/**
	 * @param fakturaLinjeId
	 * @param betingelseType
	 * @param faktura
	 */
	public FakturaLinje(Integer fakturaLinjeId,
			no.ica.fraf.model.BetingelseType betingelseType,
			no.ica.fraf.model.Faktura faktura) {
		this.fakturaLinjeId = fakturaLinjeId;
		this.betingelseType = betingelseType;
		this.faktura = faktura;
	}

	/**
	 * @return id
	 */
	public Integer getFakturaLinjeId() {
		return this.fakturaLinjeId;
	}

	/**
	 * @param fakturaLinjeId
	 */
	public void setFakturaLinjeId(Integer fakturaLinjeId) {
		this.fakturaLinjeId = fakturaLinjeId;
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
	 * @return linjebeskrivelse
	 */
	public String getLinjeBeskrivelse() {
		return this.linjeBeskrivelse;
	}

	/**
	 * @param linjeBeskrivelse
	 */
	public void setLinjeBeskrivelse(String linjeBeskrivelse) {
		this.linjeBeskrivelse = linjeBeskrivelse;
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
	 * @return omsetningsbeløp
	 */
	public BigDecimal getOmsetningBelop() {
		return this.omsetningBelop;
	}

	/**
	 * @param omsetningBelop
	 */
	public void setOmsetningBelop(BigDecimal omsetningBelop) {
		this.omsetningBelop = omsetningBelop;
	}

	/**
	 * @return avregningsbeløp
	 */
	public BigDecimal getAvregningBelop() {
		return this.avregningBelop;
	}

	/**
	 * @param avregningBelop
	 */
	public void setAvregningBelop(BigDecimal avregningBelop) {
		this.avregningBelop = avregningBelop;
	}

	/**
	 * @return mvakode
	 */
	public String getMvaKode() {
		return this.mvaKode;
	}

	/**
	 * @param mvaKode
	 */
	public void setMvaKode(String mvaKode) {
		this.mvaKode = mvaKode;
	}

	/**
	 * @return mvabløp
	 */
	public BigDecimal getMvaBelop() {
		return this.mvaBelop;
	}

	/**
	 * @param mvaBelop
	 */
	public void setMvaBelop(BigDecimal mvaBelop) {
		this.mvaBelop = mvaBelop;
	}

	/**
	 * @return grunnlagbeløp
	 */
	public BigDecimal getGrunnlagBelop() {
		return this.grunnlagBelop;
	}
	
	public BigDecimal getGrunnlagBelopPrint() {
		if(getSats()!=null&&getSats().intValue()!=0){
		return this.grunnlagBelop;
		}
		return null;
	}

	/**
	 * @param grunnlagBelop
	 */
	public void setGrunnlagBelop(BigDecimal grunnlagBelop) {
		this.grunnlagBelop = grunnlagBelop;
	}

	/**
	 * @return totalbeløp
	 */
	public BigDecimal getTotalBelop() {
		return this.totalBelop;
	}

	/**
	 * @param totalBelop
	 */
	public void setTotalBelop(BigDecimal totalBelop) {
		this.totalBelop = totalBelop;
	}

	/**
	 * @return fast beløp
	 */
	public BigDecimal getFastBelop() {
		return this.fastBelop;
	}

	/**
	 * @param fastBelop
	 */
	public void setFastBelop(BigDecimal fastBelop) {
		this.fastBelop = fastBelop;
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
	 * @return faktura
	 */
	public no.ica.fraf.model.Faktura getFaktura() {
		return this.faktura;
	}

	/**
	 * @param faktura
	 */
	public void setFaktura(no.ica.fraf.model.Faktura faktura) {
		this.faktura = faktura;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FakturaLinje))
			return false;
		FakturaLinje castOther = (FakturaLinje) other;
		return new EqualsBuilder().append(fakturaLinjeId,
				castOther.fakturaLinjeId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaLinjeId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fakturaLinjeId",
				fakturaLinjeId).append("faktura", faktura).toString();
	}

	/**
	 * @return Returns the periode.
	 */
	public String getPeriode() {
		return periode;
	}

	/**
	 * @param periode
	 *            The periode to set.
	 */
	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getInvoiceItemDescription() {
		return linjeBeskrivelse;
	}

	public AvdelingBetingelse getAvdelingBetingelse() {
		return avdelingBetingelse;
	}

	public void setAvdelingBetingelse(AvdelingBetingelse avdelingBetingelse) {
		this.avdelingBetingelse = avdelingBetingelse;
	}

	public Integer getReversert() {
		return reversert;
	}

	public void setReversert(Integer reversert) {
		this.reversert = reversert;
	}

	public String getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(String aProsjekt) {
		prosjekt=aProsjekt;
		
	}

}
