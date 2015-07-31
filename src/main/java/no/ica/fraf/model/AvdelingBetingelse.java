package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import no.ica.fraf.util.GuiUtil;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell AVDELING_BETINGELSE
 * 
 * @author abr99
 * 
 */
public class AvdelingBetingelse extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdelingBetingelseId;

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
	private BigDecimal sats;

	/**
	 * 
	 */
	private BigDecimal belop;

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
	private no.ica.fraf.model.BetingelseType betingelseType;

	/**
	 * 
	 */
	private String tekst;

	/**
	 * 
	 */
	private AvregningType avregningType;

	/**
	 * 
	 */
	private BokfSelskap bokfSelskap;

	/**
	 * 
	 */
	private Bunt bunt;

	/**
	 * 
	 */
	private Integer speilet;

	/**
	 * 
	 */
	private Set<SpeiletBetingelse> speiletBetingelses;

	/**
	 * 
	 */
	private String fakturaTekst;

	/**
	 * 
	 */
	private Integer fakturaTekstRek;

	/**
	 * 
	 */
	private BigDecimal satsFraBelop;

	/**
	 * 
	 */
	private BigDecimal satsTilBelop;

	/**
	 * 
	 */
	private String konto;

	/**
	 * 
	 */
	private String bokfAvdeling;

	/**
	 * 
	 */
	private Mva mva;

	private String prosjekt;
	private Integer slettet;

	/**
	 * Konstruktør
	 * 
	 * @param avdelingBetingelseId
	 * @param fraDato
	 * @param tilDato
	 * @param sats
	 * @param belop
	 * @param avregningFrekvensType
	 * @param avdeling
	 * @param betingelseType
	 * @param tekst
	 * @param avregningType
	 * @param bokfSelskap
	 * @param bunt
	 * @param speilet
	 * @param speiletBetingelses
	 * @param fakturaTekst
	 * @param fakturaTekstRek
	 * @param satsFraBelop
	 * @param satsTilBelop
	 * @param konto
	 * @param bokfAvdeling
	 * @param mva
	 */
	public AvdelingBetingelse(Integer avdelingBetingelseId, Date fraDato,
			Date tilDato, BigDecimal sats, BigDecimal belop,
			AvregningFrekvensType avregningFrekvensType,
			no.ica.fraf.model.Avdeling avdeling,
			no.ica.fraf.model.BetingelseType betingelseType, String tekst,
			AvregningType avregningType, BokfSelskap bokfSelskap, Bunt bunt,
			Integer speilet, Set<SpeiletBetingelse> speiletBetingelses,
			String fakturaTekst, Integer fakturaTekstRek,
			BigDecimal satsFraBelop, BigDecimal satsTilBelop, String konto,
			String bokfAvdeling, Mva mva,String prosjekt,Integer slettet) {
		this.avdelingBetingelseId = avdelingBetingelseId;
		this.fraDato = fraDato;
		this.tilDato = tilDato;
		this.sats = sats;
		this.belop = belop;
		this.avregningFrekvensType = avregningFrekvensType;
		this.avdeling = avdeling;
		this.betingelseType = betingelseType;
		this.tekst = tekst;
		this.avregningType = avregningType;
		this.bokfSelskap = bokfSelskap;
		this.bunt = bunt;
		this.speilet = speilet;
		this.speiletBetingelses = speiletBetingelses;
		this.fakturaTekst = fakturaTekst;
		this.fakturaTekstRek = fakturaTekstRek;
		this.satsFraBelop = satsFraBelop;
		this.satsTilBelop = satsTilBelop;
		this.konto = konto;
		this.bokfAvdeling = bokfAvdeling;
		this.mva = mva;
		this.prosjekt = prosjekt;
		this.slettet=slettet;
	}

	/**
	 * Konstruktør
	 */
	public AvdelingBetingelse() {
	}

	/**
	 * @param avdelingBetingelseId
	 * @param avdeling
	 * @param betingelseType
	 */
	public AvdelingBetingelse(Integer avdelingBetingelseId,
			no.ica.fraf.model.Avdeling avdeling,
			no.ica.fraf.model.BetingelseType betingelseType) {
		this.avdelingBetingelseId = avdelingBetingelseId;
		this.avdeling = avdeling;
		this.betingelseType = betingelseType;
	}

	/**
	 * @return id
	 */
	public Integer getAvdelingBetingelseId() {
		return this.avdelingBetingelseId;
	}

	/**
	 * @param avdelingBetingelseId
	 */
	public void setAvdelingBetingelseId(Integer avdelingBetingelseId) {
		this.avdelingBetingelseId = avdelingBetingelseId;
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
	 * @return sats
	 */
	public BigDecimal getSats() {
		return this.sats;
	}

	/**
	 * @return sats som skal vises
	 */
	public BigDecimal getSatsForDisplay() {
		if (this.speilet == null || this.speilet.equals(new Integer(0))) {
			return this.sats;
		}
		return null;
	}

	/**
	 * @param sats
	 */
	public void setSats(BigDecimal sats) {
		this.sats = sats;
	}

	/**
	 * @param sats
	 */
	public void setSatsForDisplay(BigDecimal sats) {
		this.sats = sats;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getBelop() {
		return this.belop;
	}

	/**
	 * @return beløp som skal vises
	 */
	public BigDecimal getBelopForDisplay() {
		if (this.speilet == null || this.speilet.equals(new Integer(0))) {
			return this.belop;
		}
		return null;
	}

	/**
	 * @param belop
	 */
	public void setBelopForDisplay(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @param belop
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return frekvenstype
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
	 * @return betingelsetype
	 */
	public BetingelseType getBetingelseType() {
		return this.betingelseType;
	}

	/**
	 * @param betingelseType
	 */
	public void setBetingelseType(BetingelseType betingelseType) {
		this.betingelseType = betingelseType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"betingelseType", betingelseType).append("fraDato",
				GuiUtil.SIMPLE_DATE_FORMAT.format(fraDato)).append("tilDato",
				GuiUtil.SIMPLE_DATE_FORMAT.format(tilDato)).toString();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return avdelingBetingelseId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Betingelse";
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

	/**
	 * @return tekst
	 */
	public String getTekst() {
		return tekst;
	}

	/**
	 * @param tekst
	 */
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	/**
	 * @return bokføringsselskap
	 */
	public BokfSelskap getBokfSelskap() {
		return bokfSelskap;
	}

	/**
	 * @param bokfSelskap
	 */
	public void setBokfSelskap(BokfSelskap bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/**
	 * @return bunt
	 */
	public Bunt getBunt() {
		return bunt;
	}

	/**
	 * @param bunt
	 */
	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @return om speiling
	 */
	public Integer getSpeilet() {
		return speilet;
	}

	/**
	 * @param speilet
	 */
	public void setSpeilet(Integer speilet) {
		this.speilet = speilet;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingBetingelse))
			return false;
		AvdelingBetingelse castOther = (AvdelingBetingelse) other;
		return new EqualsBuilder().append(avdelingBetingelseId,
				castOther.avdelingBetingelseId).append(fraDato,
				castOther.fraDato).append(tilDato, castOther.tilDato).append(
				sats, castOther.sats).append(belop, castOther.belop).append(
				avregningFrekvensType, castOther.avregningFrekvensType).append(
				avregningType, castOther.avregningType).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingBetingelseId).append(
				fraDato).append(tilDato).append(sats).append(belop).append(
				avregningFrekvensType).append(avregningType).toHashCode();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#setObjectId(java.lang.Object)
	 */
	@Override
	public void setObjectId(Object id) {
		this.avdelingBetingelseId = (Integer) id;
	}

	/**
	 * @return speilede betingelser
	 */
	public Set getSpeiletBetingelses() {
		return speiletBetingelses;
	}

	/**
	 * @param speiletBetingelses
	 */
	public void setSpeiletBetingelses(Set<SpeiletBetingelse> speiletBetingelses) {
		this.speiletBetingelses = speiletBetingelses;
	}

	/**
	 * @return fakturatekst
	 */
	public String getFakturaTekst() {
		return fakturaTekst;
	}

	/**
	 * @param fakturaTekst
	 */
	public void setFakturaTekst(String fakturaTekst) {
		this.fakturaTekst = fakturaTekst;
	}

	/**
	 * @return fakturatekst rekkefølge
	 */
	public Integer getFakturaTekstRek() {
		return fakturaTekstRek;
	}

	/**
	 * @param fakturaTekstRek
	 */
	public void setFakturaTekstRek(Integer fakturaTekstRek) {
		this.fakturaTekstRek = fakturaTekstRek;
	}

	/**
	 * @return Returns the satsFraBelop.
	 */
	public BigDecimal getSatsFraBelop() {
		return satsFraBelop;
	}

	/**
	 * @param satsFraBelop
	 *            The satsFraBelop to set.
	 */
	public void setSatsFraBelop(BigDecimal satsFraBelop) {
		this.satsFraBelop = satsFraBelop;
	}

	/**
	 * @return Returns the satsTilBelop.
	 */
	public BigDecimal getSatsTilBelop() {
		return satsTilBelop;
	}

	/**
	 * @param satsTilBelop
	 *            The satsTilBelop to set.
	 */
	public void setSatsTilBelop(BigDecimal satsTilBelop) {
		this.satsTilBelop = satsTilBelop;
	}

	/**
	 * @return Returns the konto.
	 */
	public String getKonto() {
		return konto;
	}

	/**
	 * @param konto
	 *            The konto to set.
	 */
	public void setKonto(String konto) {
		this.konto = konto;
	}

	/**
	 * @return Returns the bokfAvdeling.
	 */
	public String getBokfAvdeling() {
		return bokfAvdeling;
	}

	/**
	 * @param bokfAvdeling
	 *            The bokfAvdeling to set.
	 */
	public void setBokfAvdeling(String bokfAvdeling) {
		this.bokfAvdeling = bokfAvdeling;
	}

	/**
	 * @return Returns the mvaKode.
	 */
	public Mva getMva() {
		return mva;
	}

	/**
	 * @param mva
	 */
	public void setMva(Mva mva) {
		this.mva = mva;
	}

	public String getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(String prosjekt) {
		this.prosjekt = prosjekt;
	}

	public Integer getSlettet() {
		return slettet;
	}

	public void setSlettet(Integer slettet) {
		this.slettet = slettet;
	}

	public boolean isDeleted(){
		if(slettet==null||slettet==0){
			return false;
		}
		return true;
		
	}
}
