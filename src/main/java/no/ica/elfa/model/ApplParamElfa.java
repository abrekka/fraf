package no.ica.elfa.model;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell APPL_PARAM
 * 
 * @author abr99
 * 
 */
public class ApplParamElfa extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer paramId;

	/** identifier field */
	private String paramName;

	/** nullable persistent field */
	private String paramValue;

	/** nullable persistent field */
	private String paramDescr;

	/** persistent field */
	private String userEdit;

	/** nullable persistent field */
	private String applParamGroupId;

	/** persistent field */
	private String userOverride;

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ApplParamElfa))
			return false;
		ApplParamElfa castOther = (ApplParamElfa) other;
		return new EqualsBuilder().append(paramName, castOther.paramName)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(paramName).toHashCode();
	}

	/**
	 * @param paramId
	 * @param paramName
	 * @param paramValue
	 * @param paramDescr
	 * @param userEdit
	 * @param applParamGroupId
	 * @param userOverride
	 */
	public ApplParamElfa(Integer paramId, String paramName, String paramValue,
			String paramDescr, String userEdit, String applParamGroupId,
			String userOverride) {
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.paramDescr = paramDescr;
		this.userEdit = userEdit;
		this.applParamGroupId = applParamGroupId;
		this.userOverride = userOverride;
	}

	/** default constructor */
	public ApplParamElfa() {
	}

	/**
	 * @param paramName
	 * @param userEdit
	 * @param userOverride
	 */
	public ApplParamElfa(String paramName, String userEdit, String userOverride) {
		this.paramName = paramName;
		this.userEdit = userEdit;
		this.userOverride = userOverride;
	}

	/**
	 * @return parameternavn
	 */
	public String getParamName() {
		return this.paramName;
	}

	/**
	 * @param paramName
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return parameterverdi
	 */
	public String getParamValue() {
		return this.paramValue;
	}

	/**
	 * @param paramValue
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * @return beskrivelse
	 */
	public String getParamDescr() {
		return this.paramDescr;
	}

	/**
	 * @param paramDescr
	 */
	public void setParamDescr(String paramDescr) {
		this.paramDescr = paramDescr;
	}

	/**
	 * @return kan endres av bruker
	 */
	public String getUserEdit() {
		return this.userEdit;
	}

	/**
	 * @param userEdit
	 */
	public void setUserEdit(String userEdit) {
		this.userEdit = userEdit;
	}

	/**
	 * @return gruppe
	 */
	public String getApplParamGroupId() {
		return this.applParamGroupId;
	}

	/**
	 * @param applParamGroupId
	 */
	public void setApplParamGroupId(String applParamGroupId) {
		this.applParamGroupId = applParamGroupId;
	}

	/**
	 * @return overskrivbar
	 */
	public String getUserOverride() {
		return this.userOverride;
	}

	/**
	 * @param userOverride
	 */
	public void setUserOverride(String userOverride) {
		this.userOverride = userOverride;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("paramName", getParamName())
				.toString();
	}

	/**
	 * @return id
	 */
	public Integer getParamId() {
		return paramId;
	}

	/**
	 * @param paramId
	 */
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

}
