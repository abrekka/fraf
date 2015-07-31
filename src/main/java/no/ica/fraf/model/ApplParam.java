package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Tabell APPL_PARAM
 * 
 * @author abr99
 * 
 */
public class ApplParam extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer paramId;

	/**
	 * 
	 */
	private String paramName;

	/**
	 * 
	 */
	private String paramValue;

	/**
	 * 
	 */
	private String paramDescr;
	private String systemName;
	private Integer systemType;

	/**
	 * @param paramId
	 * @param paramName
	 * @param paramValue
	 * @param paramDescr
	 */
	public ApplParam(Integer paramId, String paramName, String paramValue,
			String paramDescr,String systemName,final Integer aSystemType) {
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.paramDescr = paramDescr;
		this.systemName=systemName;
		this.systemType=aSystemType;
	}

	/**
	 * 
	 */
	public ApplParam() {
	}

	/**
	 * @param paramId
	 * @param paramName
	 * @param paramValue
	 */
	public ApplParam(Integer paramId, String paramName, String paramValue) {
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	/**
	 * @return navn
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
	 * @return verdi
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

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ApplParam))
			return false;
		ApplParam castOther = (ApplParam) other;
		return new EqualsBuilder().append(paramId, castOther.paramId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(paramId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("paramName", paramName)
				.toString();
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

}
