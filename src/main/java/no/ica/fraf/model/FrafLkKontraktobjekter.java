package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell LK_KONTRAKTOBJEKTER i Fenistra
 * 
 * @author abr99
 * 
 */
public class FrafLkKontraktobjekter extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer kontraktObjektId;

	/**
	 * 
	 */
	private String kontraktObjektNr;

	/**
	 * 
	 */
	private String kontraktObjekt;

	/**
	 * 
	 */
	private Integer terminer;

	/**
	 * 
	 */
	private Date startDato;

	/**
	 * 
	 */
	private Date sluttDato;

	/**
	 * 
	 */
	private BigDecimal aarsBeloep;

	/**
	 * 
	 */
	private BigDecimal fakturabeloep;

	/**
	 * Dette er ikke et felt som ligger lagret i tabell, men er kobling til
	 * tabell i FRAF som viser om betingelse skal speiles
	 */
	private SpeiletBetingelse speiletBetingelse;

	/**
	 * Dette er ikk et felt i tabell, men som settes på bakgrunn av feltet
	 * terminer
	 */
	private AvregningFrekvensType avregningFrekvensType;

	/**
	 * Kobling til transaksjonstypered i Fenistra
	 */
//	private MrTransaksjonTyperEd mrTransaksjonTyperEd;

	/**
	 * Konstuktør
	 */
	public FrafLkKontraktobjekter() {
	}

	/**
	 * Konstruktør
	 * 
	 * @param objekt
	 * @param id
	 * @param nr
	 * @param dato
	 * @param dato2
	 * @param terminer
	 * @param aarsBeloep
	 * @param fakturabeloep
	 * @param mrTransaksjonTyperEd
	 */
	public FrafLkKontraktobjekter(String objekt, Integer id, String nr, Date dato,
			Date dato2, Integer terminer, BigDecimal aarsBeloep,
			BigDecimal fakturabeloep, MrTransaksjonTyperEd mrTransaksjonTyperEd) {
		kontraktObjekt = objekt;
		kontraktObjektId = id;
		kontraktObjektNr = nr;
		sluttDato = dato;
		startDato = dato2;
		this.terminer = terminer;
		this.aarsBeloep = aarsBeloep;
		this.fakturabeloep = fakturabeloep;
//		this.mrTransaksjonTyperEd = mrTransaksjonTyperEd;
	}

	/**
	 * @return kontraktobjekt
	 */
	public String getKontraktObjekt() {
		return kontraktObjekt;
	}

	/**
	 * @param kontraktObjekt
	 */
	public void setKontraktObjekt(String kontraktObjekt) {
		this.kontraktObjekt = kontraktObjekt;
	}

	/**
	 * @return kontraktobjektid
	 */
	public Integer getKontraktObjektId() {
		return kontraktObjektId;
	}

	/**
	 * @param kontraktObjektId
	 */
	public void setKontraktObjektId(Integer kontraktObjektId) {
		this.kontraktObjektId = kontraktObjektId;
	}

	/**
	 * @return kontraktobjektnr (avdnr)
	 */
	public String getKontraktObjektNr() {
		return kontraktObjektNr;
	}

	/**
	 * @param kontraktObjektNr
	 */
	public void setKontraktObjektNr(String kontraktObjektNr) {
		this.kontraktObjektNr = kontraktObjektNr;
	}

	/**
	 * @return sluttdato
	 */
	public Date getSluttDato() {
		return sluttDato;
	}

	/**
	 * @param sluttDato
	 */
	public void setSluttDato(Date sluttDato) {
		this.sluttDato = sluttDato;
	}

	/**
	 * @return startdato
	 */
	public Date getStartDato() {
		return startDato;
	}

	/**
	 * @param startDato
	 */
	public void setStartDato(Date startDato) {
		this.startDato = startDato;
	}

	/**
	 * @return terminer
	 */
	public Integer getTerminer() {
		return terminer;
	}

	/**
	 * @param terminer
	 */
	public void setTerminer(Integer terminer) {
		this.terminer = terminer;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FrafLkKontraktobjekter))
			return false;
		FrafLkKontraktobjekter castOther = (FrafLkKontraktobjekter) other;
		return new EqualsBuilder().append(kontraktObjektId,
				castOther.kontraktObjektId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kontraktObjektId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"kontraktObjektId", kontraktObjektId).append(
				"kontraktObjektNr", kontraktObjektNr).append("kontraktObjekt",
				kontraktObjekt).append("terminer", terminer).append(
				"startDato", startDato).append("sluttDato", sluttDato)
				.toString();
	}

	/**
	 * @return fakturabeløp
	 */
	public BigDecimal getFakturabeloep() {
		return fakturabeloep;
	}

	/**
	 * @param fakturabeloep
	 */
	public void setFakturabeloep(BigDecimal fakturabeloep) {
		this.fakturabeloep = fakturabeloep;
	}

	/**
	 * @return årsbeløp
	 */
	public BigDecimal getAarsBeloep() {
		return aarsBeloep;
	}

	/**
	 * @param aarsBeloep
	 */
	public void setAarsBeloep(BigDecimal aarsBeloep) {
		this.aarsBeloep = aarsBeloep;
	}

	/**
	 * @return speilet betingelse fra FRAF
	 */
	public SpeiletBetingelse getSpeiletBetingelse() {
		return speiletBetingelse;
	}

	/**
	 * @param speiletBetingelse
	 */
	public void setSpeiletBetingelse(SpeiletBetingelse speiletBetingelse) {
		this.speiletBetingelse = speiletBetingelse;
	}

	/**
	 * @return frekvenstype
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
	 * @return Returns the mrTransaksjonsTyperEd.
	 */
//	public MrTransaksjonTyperEd getMrTransaksjonTyperEd() {
//		return mrTransaksjonTyperEd;
//	}

	/**
	 * @param mrTransaksjonTyperEd
	 *            The mrTransaksjonsTyperEd to set.
	 */
//	public void setMrTransaksjonTyperEd(
//			MrTransaksjonTyperEd mrTransaksjonTyperEd) {
//		this.mrTransaksjonTyperEd = mrTransaksjonTyperEd;
//	}
}
