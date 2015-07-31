package no.ica.fraf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell LAAS
 * 
 * @author abr99
 * 
 */
public class Laas extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer laasId;

	/**
	 * 
	 */
	private LaasType laasType;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * 
	 */
	private Date laasDato;

	/**
	 * 
	 */
	private Avdeling avdeling;
	private Integer id;

	/**
	 * 
	 */
	public Laas() {
	}

	/**
	 * @param user
	 * @param avdeling
	 * @param dato
	 * @param id
	 * @param type
	 */
	public Laas(ApplUser user, Avdeling avdeling, Date dato, Integer laasId,
			LaasType type,Integer id) {
		applUser = user;
		this.avdeling = avdeling;
		laasDato = dato;
		this.laasId = laasId;
		laasType = type;
		this.id=id;
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
	 * @return dato
	 */
	public Date getLaasDato() {
		return laasDato;
	}

	/**
	 * @param laasDato
	 */
	public void setLaasDato(Date laasDato) {
		this.laasDato = laasDato;
	}

	/**
	 * @return id
	 */
	public Integer getLaasId() {
		return laasId;
	}

	/**
	 * @param laasId
	 */
	public void setLaasId(Integer laasId) {
		this.laasId = laasId;
	}

	/**
	 * @return type
	 */
	public LaasType getLaasType() {
		return laasType;
	}

	/**
	 * @param laasType
	 */
	public void setLaasType(LaasType laasType) {
		this.laasType = laasType;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Laas))
			return false;
		Laas castOther = (Laas) other;
		return new EqualsBuilder().append(laasId, castOther.laasId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(laasId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"laasId", laasId).append("laasType", laasType).append(
				"applUser", applUser).append("laasDato", laasDato).append(
				"avdeling", avdeling).toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
