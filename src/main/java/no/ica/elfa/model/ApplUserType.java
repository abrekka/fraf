package no.ica.elfa.model;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/** 
 * Klasse for tabell APP_USER_TYPE
 * @author abr99
 *
 */
/**
 * @author abr99
 * 
 */
public class ApplUserType extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer typeId;

	/** persistent field */
	private String typeName;

	/**
	 * @param typeId
	 * @param typeName
	 */
	public ApplUserType(Integer typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}

	/** default constructor */
	public ApplUserType() {
	}

	/**
	 * @return id
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return navn
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ApplUserType))
			return false;
		ApplUserType castOther = (ApplUserType) other;
		return new EqualsBuilder().append(typeId, castOther.typeId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(typeId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return typeName;
	}

}
