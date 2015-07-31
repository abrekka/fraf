/**
 * 
 */
package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Tabell AVDELING_MANGEL
 * 
 * @author abr99
 * 
 */
public class AvdelingMangel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdelingMangelId;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	private MangelType mangelType;

	/**
	 * 
	 */
	public AvdelingMangel() {
		super();
	}

	/**
	 * @param avdeling
	 * @param id
	 * @param type
	 */
	public AvdelingMangel(Avdeling avdeling, Integer id, MangelType type) {
		super();
		this.avdeling = avdeling;
		avdelingMangelId = id;
		mangelType = type;
	}

	/**
	 * @return Returns the avdeling.
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * @param avdeling
	 *            The avdeling to set.
	 */
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * @return Returns the avdelingMangelId.
	 */
	public Integer getAvdelingMangelId() {
		return avdelingMangelId;
	}

	/**
	 * @param avdelingMangelId
	 *            The avdelingMangelId to set.
	 */
	public void setAvdelingMangelId(Integer avdelingMangelId) {
		this.avdelingMangelId = avdelingMangelId;
	}

	/**
	 * @return Returns the mangelType.
	 */
	public MangelType getMangelType() {
		return mangelType;
	}

	/**
	 * @param mangelType
	 *            The mangelType to set.
	 */
	public void setMangelType(MangelType mangelType) {
		this.mangelType = mangelType;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingMangel))
			return false;
		AvdelingMangel castOther = (AvdelingMangel) other;
		return new EqualsBuilder().append(avdeling, castOther.avdeling).append(
				mangelType, castOther.mangelType).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdeling).append(mangelType)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (mangelType != null) {
			return mangelType.toString();
		}
		return null;
	}

}
