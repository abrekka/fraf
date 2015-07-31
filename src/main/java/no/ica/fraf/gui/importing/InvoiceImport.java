package no.ica.fraf.gui.importing;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Mva;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Klasse som brukes til å representere en linje i fakturafil
 * 
 * @author abr99
 * 
 */
public class InvoiceImport implements Comparable {
	/**
	 * 
	 */
	private Integer avdnr;
	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	private BigDecimal belop;

	/**
	 * 
	 */
	private Mva mva;
	/**
	 * 
	 */
	private String mvaKode;

	/**
	 * 
	 */
	private Integer aar;

	/**
	 * 
	 */
	private Integer fraPeriode;

	/**
	 * 
	 */
	private Integer tilPeriode;

	/**
	 * 
	 */
	private BetingelseType betingelseType;
	/**
	 * 
	 */
	private String betingelse;

	/**
	 * 
	 */
	private String fakturanr;

	/**
	 * 
	 */
	private Date fakturaDato;

	/**
	 * 
	 */
	private Date forfallsDato;

	/**
	 * 
	 */
	private BokfSelskap bokfSelskap;

	/**
	 * 
	 */
	private String tekst;

	/**
	 * 
	 */
	private String fakturaTekst;

	/**
	 * 
	 */
	private Integer rekkefolge;

	/**
	 * 
	 */
	private String feilmelding;

	/**
	 * 
	 */
	public InvoiceImport() {
		super();
	}

	/**
	 * @return Returns the avdnr.
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/**
	 * @param avdnr
	 *            The avdnr to set.
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return Returns the betingelse.
	 */
	public BetingelseType getBetingelseType() {
		return betingelseType;
	}

	/**
	 * @param betingelseType
	 *            The betingelse to set.
	 */
	public void setBetingelseType(BetingelseType betingelseType) {
		this.betingelseType = betingelseType;
	}

	/**
	 * @return Returns the bruttoBelop.
	 */
	public BigDecimal getBelop() {
		return belop;
	}

	/**
	 * @param belop
	 *            The bruttoBelop to set.
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return Returns the fakturaDato.
	 */
	public Date getFakturaDato() {
		return fakturaDato;
	}

	/**
	 * @param fakturaDato
	 *            The fakturaDato to set.
	 */
	public void setFakturaDato(Date fakturaDato) {
		this.fakturaDato = fakturaDato;
	}

	/**
	 * @return Returns the fakturanr.
	 */
	public String getFakturanr() {
		return fakturanr;
	}

	/**
	 * @param fakturanr
	 *            The fakturanr to set.
	 */
	public void setFakturanr(String fakturanr) {
		this.fakturanr = fakturanr;
	}

	/**
	 * @return Returns the fakturaTekst.
	 */
	public String getFakturaTekst() {
		return fakturaTekst;
	}

	/**
	 * @param fakturaTekst
	 *            The fakturaTekst to set.
	 */
	public void setFakturaTekst(String fakturaTekst) {
		this.fakturaTekst = fakturaTekst;
	}

	/**
	 * @return Returns the feilmelding.
	 */
	public String getFeilmelding() {
		return feilmelding;
	}

	/**
	 * @param feilmelding
	 *            The feilmelding to set.
	 */
	public void setFeilmelding(String feilmelding) {
		this.feilmelding = feilmelding;
	}

	/**
	 * @return Returns the forfallsDato.
	 */
	public Date getForfallsDato() {
		return forfallsDato;
	}

	/**
	 * @param forfallsDato
	 *            The forfallsDato to set.
	 */
	public void setForfallsDato(Date forfallsDato) {
		this.forfallsDato = forfallsDato;
	}

	/**
	 * @return Returns the fraPeriode.
	 */
	public Integer getFraPeriode() {
		return fraPeriode;
	}

	/**
	 * @param fraPeriode
	 *            The fraPeriode to set.
	 */
	public void setFraPeriode(Integer fraPeriode) {
		this.fraPeriode = fraPeriode;
	}

	/**
	 * @return Returns the mvaKode.
	 */
	public Mva getMva() {
		return mva;
	}

	/**
	 * @param mva
	 *            The mvaKode to set.
	 */
	public void setMva(Mva mva) {
		this.mva = mva;
	}

	/**
	 * @return Returns the rekkefolge.
	 */
	public Integer getRekkefolge() {
		return rekkefolge;
	}

	/**
	 * @param rekkefolge
	 *            The rekkefolge to set.
	 */
	public void setRekkefolge(Integer rekkefolge) {
		this.rekkefolge = rekkefolge;
	}

	/**
	 * @return Returns the selskap.
	 */
	public BokfSelskap getBokfSelskap() {
		return bokfSelskap;
	}

	/**
	 * @param bokfSelskap
	 *            The selskap to set.
	 */
	public void setBokfSelskap(BokfSelskap bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/**
	 * @return Returns the tekst.
	 */
	public String getTekst() {
		return tekst;
	}

	/**
	 * @param tekst
	 *            The tekst to set.
	 */
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	/**
	 * @return Returns the tilPeriode.
	 */
	public Integer getTilPeriode() {
		return tilPeriode;
	}

	/**
	 * @param tilPeriode
	 *            The tilPeriode to set.
	 */
	public void setTilPeriode(Integer tilPeriode) {
		this.tilPeriode = tilPeriode;
	}

	/**
	 * @return Returns the aar.
	 */
	public Integer getAar() {
		return aar;
	}

	/**
	 * @param aar
	 *            The aar to set.
	 */
	public void setAar(Integer aar) {
		this.aar = aar;
	}

	/**
	 * @return Returns the betingelse.
	 */
	public String getBetingelse() {
		return betingelse;
	}

	/**
	 * @param betingelse The betingelse to set.
	 */
	public void setBetingelse(String betingelse) {
		this.betingelse = betingelse;
	}

	/**
	 * @return Returns the mvaKode.
	 */
	public String getMvaKode() {
		return mvaKode;
	}

	/**
	 * @param mvaKode The mvaKode to set.
	 */
	public void setMvaKode(String mvaKode) {
		this.mvaKode = mvaKode;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(final Object other) {
		InvoiceImport castOther = (InvoiceImport) other;
		return new CompareToBuilder().append(avdnr, castOther.avdnr).append(
				fakturanr, castOther.fakturanr).toComparison();
	}

	/**
	 * @return Returns the avdeling.
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * @param avdeling The avdeling to set.
	 */
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
}
