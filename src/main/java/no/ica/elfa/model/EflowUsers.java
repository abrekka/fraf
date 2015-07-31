package no.ica.elfa.model;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse som representerer tabellen EFLOW_USERS i Basware
 * @author abr99
 *
 */
public class EflowUsers extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2586818345726850713L;
	/**
	 * 
	 */
	private String userNetworkName;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	public EflowUsers() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param userNetworkName
	 * @param userName
	 */
	public EflowUsers(String userNetworkName, String userName) {
		super();
		// TODO Auto-generated constructor stub
		this.userNetworkName = userNetworkName;
		this.userName = userName;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the userNetworkName.
	 */
	public String getUserNetworkName() {
		return userNetworkName;
	}
	/**
	 * @param userNetworkName The userNetworkName to set.
	 */
	public void setUserNetworkName(String userNetworkName) {
		this.userNetworkName = userNetworkName;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof EflowUsers))
			return false;
		EflowUsers castOther = (EflowUsers) other;
		return new EqualsBuilder().append(userNetworkName,
				castOther.userNetworkName).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userNetworkName).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("userNetworkName",
				userNetworkName).append("userName", userName).toString();
	}
	
	
}
