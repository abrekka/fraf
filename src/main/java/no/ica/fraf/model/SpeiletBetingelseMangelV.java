package no.ica.fraf.model;

import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SpeiletBetingelseMangelV extends BaseObject {
	private Integer avdelingBetingelseId;
	private Integer avdnr;
	private String betingelseNavn;
	private Date fraDato;
	private Date tilDato;
	public SpeiletBetingelseMangelV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpeiletBetingelseMangelV(Integer id, Integer avdnr, String navn, Date dato, Date dato2) {
		super();
		// TODO Auto-generated constructor stub
		avdelingBetingelseId = id;
		this.avdnr = avdnr;
		betingelseNavn = navn;
		fraDato = dato;
		tilDato = dato2;
	}
	public Integer getAvdelingBetingelseId() {
		return avdelingBetingelseId;
	}
	public void setAvdelingBetingelseId(Integer avdelingBetingelseId) {
		this.avdelingBetingelseId = avdelingBetingelseId;
	}
	public Integer getAvdnr() {
		return avdnr;
	}
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}
	public String getBetingelseNavn() {
		return betingelseNavn;
	}
	public void setBetingelseNavn(String betingelseNavn) {
		this.betingelseNavn = betingelseNavn;
	}
	public Date getFraDato() {
		return fraDato;
	}
	public void setFraDato(Date fraDato) {
		this.fraDato = fraDato;
	}
	public Date getTilDato() {
		return tilDato;
	}
	public void setTilDato(Date tilDato) {
		this.tilDato = tilDato;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SpeiletBetingelseMangelV))
			return false;
		SpeiletBetingelseMangelV castOther = (SpeiletBetingelseMangelV) other;
		return new EqualsBuilder().append(avdelingBetingelseId,
				castOther.avdelingBetingelseId).append(avdnr, castOther.avdnr)
				.isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingBetingelseId).append(avdnr)
				.toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("avdelingBetingelseId",
				avdelingBetingelseId).append("avdnr", avdnr).append(
				"betingelseNavn", betingelseNavn).append("fraDato", fraDato)
				.append("tilDato", tilDato).toString();
	}
}
