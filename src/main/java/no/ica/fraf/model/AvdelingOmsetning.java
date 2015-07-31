package no.ica.fraf.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Tabell AVDELING_OMSETNING
 * 
 * @author abr99
 * 
 */
public class AvdelingOmsetning extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdelingOmsetningId;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private Integer aar;

	/**
	 * 
	 */
	private Integer periode;

	/**
	 * 
	 */
	private AvregningBasisType avregningBasisType;

	/**
	 * 
	 */
	private BigDecimal belop;

	/**
	 * 
	 */
	private BigDecimal korreksjonBelop;

	/**
	 * 
	 */
	private BigDecimal korrigertBelop;

	/**
	 * 
	 */
	private String korreksjonKommentar;

	/**
	 * 
	 */
	private Integer manuell;

	/**
	 * 
	 */
	private Bunt innlesBunt;

	/**
	 * 
	 */
	private Bunt korreksjonBunt;

	/**
	 * 
	 */
	private Bunt fakturaBunt;

	/**
	 * 
	 */
	private Avdeling avdeling;
	private BigDecimal avregning;

	/**
	 * @param avdelingOmsetningId
	 * @param avdnr
	 * @param aar
	 * @param periode
	 * @param avregningBasisType
	 * @param belop
	 * @param korreksjonBelop
	 * @param korrigertBelop
	 * @param korreksjonKommentar
	 * @param manuell
	 * @param innlesBunt
	 * @param korreksjonBunt
	 * @param fakturaBunt
	 * @param avdeling
	 */
	public AvdelingOmsetning(Integer avdelingOmsetningId, Integer avdnr,
			Integer aar, Integer periode,
			AvregningBasisType avregningBasisType, BigDecimal belop,
			BigDecimal korreksjonBelop, BigDecimal korrigertBelop,
			String korreksjonKommentar, Integer manuell, Bunt innlesBunt,
			Bunt korreksjonBunt, Bunt fakturaBunt, Avdeling avdeling,BigDecimal avregning) {
		this.avdelingOmsetningId = avdelingOmsetningId;
		this.avdnr = avdnr;
		this.aar = aar;
		this.periode = periode;
		this.avregningBasisType = avregningBasisType;
		this.belop = belop;
		this.korreksjonBelop = korreksjonBelop;
		this.korrigertBelop = korrigertBelop;
		this.korreksjonKommentar = korreksjonKommentar;
		this.manuell = manuell;
		this.innlesBunt = innlesBunt;
		this.korreksjonBunt = korreksjonBunt;
		this.fakturaBunt = fakturaBunt;
		this.avdeling = avdeling;
		this.avregning=avregning;
	}

	/**
	 * 
	 */
	public AvdelingOmsetning() {
	}

	/**
	 * @param avdelingOmsetningId
	 */
	public AvdelingOmsetning(Integer avdelingOmsetningId) {
		this.avdelingOmsetningId = avdelingOmsetningId;
	}

	/**
	 * @return omsetningId
	 */
	public Integer getAvdelingOmsetningId() {
		return this.avdelingOmsetningId;
	}

	/**
	 * @param avdelingOmsetningId
	 */
	public void setAvdelingOmsetningId(Integer avdelingOmsetningId) {
		this.avdelingOmsetningId = avdelingOmsetningId;
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
	 * @return år
	 */
	public Integer getAar() {
		return this.aar;
	}

	/**
	 * @param aar
	 */
	public void setAar(Integer aar) {
		this.aar = aar;
	}

	/**
	 * @return periode
	 */
	public Integer getPeriode() {
		return this.periode;
	}

	/**
	 * @param periode
	 */
	public void setPeriode(Integer periode) {
		this.periode = periode;
	}

	/**
	 * @return avregningbasistype
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
	 * @return korreksjon
	 */
	public BigDecimal getKorreksjonBelop() {
		return this.korreksjonBelop;
	}

	/**
	 * @param korreksjonBelop
	 */
	public void setKorreksjonBelop(BigDecimal korreksjonBelop) {
		this.korreksjonBelop = korreksjonBelop;
	}

	/**
	 * @return korrigert beløp
	 */
	public BigDecimal getKorrigertBelop() {
		return this.korrigertBelop;
	}

	/**
	 * @param korrigertBelop
	 */
	public void setKorrigertBelop(BigDecimal korrigertBelop) {
		this.korrigertBelop = korrigertBelop;
	}

	/**
	 * @return korreksjonskommentar
	 */
	public String getKorreksjonKommentar() {
		return this.korreksjonKommentar;
	}

	/**
	 * @param korreksjonKommentar
	 */
	public void setKorreksjonKommentar(String korreksjonKommentar) {
		this.korreksjonKommentar = korreksjonKommentar;
	}

	/**
	 * @return manuell
	 */
	public Integer getManuell() {
		return this.manuell;
	}

	/**
	 * @param manuell
	 */
	public void setManuell(Integer manuell) {
		this.manuell = manuell;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return avdelingOmsetningId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Omsetning/Budsjett";
	}

	/**
	 * @return fakturabunt
	 */
	public Bunt getFakturaBunt() {
		return fakturaBunt;
	}

	/**
	 * @param fakturaBunt
	 */
	public void setFakturaBunt(Bunt fakturaBunt) {
		this.fakturaBunt = fakturaBunt;
	}

	/**
	 * @return innlesningsbunt
	 */
	public Bunt getInnlesBunt() {
		return innlesBunt;
	}

	/**
	 * @param innlesBunt
	 */
	public void setInnlesBunt(Bunt innlesBunt) {
		this.innlesBunt = innlesBunt;
	}

	/**
	 * @return korrkejosnsbunt
	 */
	public Bunt getKorreksjonBunt() {
		return korreksjonBunt;
	}

	/**
	 * @param korreksjonBunt
	 */
	public void setKorreksjonBunt(Bunt korreksjonBunt) {
		this.korreksjonBunt = korreksjonBunt;
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingOmsetning))
			return false;
		AvdelingOmsetning castOther = (AvdelingOmsetning) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).append(aar,
				castOther.aar).append(periode, castOther.periode).append(
				avregningBasisType, castOther.avregningBasisType).append(belop,
				castOther.belop).append(korreksjonBelop,
				castOther.korreksjonBelop).append(korrigertBelop,
				castOther.korrigertBelop).append(korreksjonKommentar,
				castOther.korreksjonKommentar).append(manuell,
				castOther.manuell).append(innlesBunt, castOther.innlesBunt)
				.append(korreksjonBunt, castOther.korreksjonBunt).append(
						fakturaBunt, castOther.fakturaBunt).append(avdeling,
						castOther.avdeling).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).append(aar).append(periode)
				.append(avregningBasisType).append(belop).append(
						korreksjonBelop).append(korrigertBelop).append(
						korreksjonKommentar).append(manuell).append(innlesBunt)
				.append(korreksjonBunt).append(fakturaBunt).append(avdeling)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"avdelingOmsetningId", avdelingOmsetningId).append("avdnr",
				avdnr).append("aar", aar).append("periode", periode).append(
				"avregningBasisType", avregningBasisType)
				.append("belop", belop).append("korreksjonBelop",
						korreksjonBelop).append("korrigertBelop",
						korrigertBelop).append("korreksjonKommentar",
						korreksjonKommentar).append("manuell", manuell).append(
						"innlesBunt", innlesBunt).append("korreksjonBunt",
						korreksjonBunt).append("fakturaBunt", fakturaBunt)
				.append("avdeling", avdeling).toString();
	}

	public BigDecimal getAvregning() {
		return avregning;
	}

	public void setAvregning(BigDecimal avregning) {
		this.avregning = avregning;
	}
}
