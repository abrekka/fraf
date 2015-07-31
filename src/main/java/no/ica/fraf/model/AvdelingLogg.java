package no.ica.fraf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Tabell AVDELING_LOGG
 * 
 * @author abr99
 * 
 */
public class AvdelingLogg extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer loggId;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * 
	 */
	private Date loggDato;

	/**
	 * 
	 */
	private String kommentar;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	public AvdelingLogg() {
	}

	/**
	 * @param loggId
	 * @param applUser
	 * @param loggDato
	 * @param kommentar
	 * @param avdeling
	 */
	public AvdelingLogg(Integer loggId, ApplUser applUser, Date loggDato,
			String kommentar, Avdeling avdeling) {
		this.loggId = loggId;
		this.applUser = applUser;
		this.loggDato = loggDato;
		this.kommentar = kommentar;
		this.avdeling = avdeling;
	}

	/**
	 * @return bruker
	 */
	public ApplUser getApplUser() {
		return applUser;
	}

	/**
	 * @param applUser
	 */
	public void setApplUser(ApplUser applUser) {
		this.applUser = applUser;
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
	 * @return kommentar
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * @param kommentar
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	/**
	 * @return loggdato
	 */
	public Date getLoggDato() {
		return loggDato;
	}

	/**
	 * @param loggDato
	 */
	public void setLoggDato(Date loggDato) {
		this.loggDato = loggDato;
	}

	/**
	 * @return id
	 */
	public Integer getLoggId() {
		return loggId;
	}

	/**
	 * @param loggId
	 */
	public void setLoggId(Integer loggId) {
		this.loggId = loggId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AvdelingLogg)) {
			return false;
		}
		AvdelingLogg rhs = (AvdelingLogg) object;
		return new EqualsBuilder().append(this.avdeling, rhs.avdeling).append(
				this.loggDato, rhs.loggDato)
				.append(this.applUser, rhs.applUser).append(this.loggId,
						rhs.loggId).append(this.kommentar, rhs.kommentar)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-353975665, -220695257)
				.append(this.avdeling).append(this.loggDato).append(
						this.applUser).append(this.loggId).append(
						this.kommentar).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("applUser", this.applUser)
				.append("kommentar", this.kommentar).append("loggDato",
						this.loggDato).append("loggId", this.loggId).append(
						"avdeling", this.avdeling).toString();
	}

}
