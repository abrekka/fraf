package no.ica.fraf.model;

import java.util.Date;

import no.ica.fraf.common.ApplUserInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Tabell APPL_USER
 * 
 * @author abr99
 * 
 */
public class ApplUser extends BaseObject implements ApplUserInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public static final String USER_ID_PROPERTY = "userId";

	/**
	 * 
	 */
	public static final String USER_NAME_PROPERTY = "userName";

	/**
	 * 
	 */
	public static final String FIRST_NAME_PROPERTY = "firstName";

	/**
	 * 
	 */
	public static final String SURNAME_PROPERTY = "surname";

	/**
	 * 
	 */
	public static final String PASSWORD_PROPERTY = "password";

	/**
	 * 
	 */
	public static final String LNF_PROPERTY = "lnf";

	/**
	 * 
	 */
	public static final String START_DATE_PROPERTY = "startDate";
	/**
	 * 
	 */
	public static final String GUI_VERSION_PROPERTY = "guiVersion";

	/**
	 * 
	 */
	public static final String APPL_USER_TYPE_PROPERTY =  "applUserType";
	//*******
	
	

	/**
	 * 
	 */
	private Integer userId;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private String firstName;

	/**
	 * 
	 */
	private String surname;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String lnf;

	/**
	 * 
	 */
	private Date startDate;
	/**
	 * 
	 */
	private String guiVersion;

	/**
	 * 
	 */
	private no.ica.fraf.model.ApplUserType applUserType;
	private Integer disabled;

	/**
	 * @param userName
	 * @param firstName
	 * @param surname
	 * @param password
	 * @param applUserType
	 * @param lnf
	 * @param startDate
	 * @param guiVersion
	 */
	public ApplUser(String userName, String firstName, String surname,
			String password, ApplUserType applUserType, String lnf,
			Date startDate,String guiVersion,Integer disabled) {// , WorksheetLock worksheetLock) {
		this.userName = userName;
		this.firstName = firstName;
		this.surname = surname;
		this.password = password;
		this.applUserType = applUserType;
		this.lnf = lnf;
		this.startDate = startDate;
		this.guiVersion = guiVersion;
		this.disabled=disabled;
	}

	/**
	 * 
	 */
	public ApplUser() {
	}

	/**
	 * @param userName
	 * @param applUserType
	 */
	public ApplUser(String userName, ApplUserType applUserType) {
		this.userName = userName;
		this.applUserType = applUserType;
	}

	/**
	 * @return id
	 */
	public Integer getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		Integer oldInteger = getUserId();
		this.userId = userId;
        firePropertyChange(USER_ID_PROPERTY, oldInteger, userId);
		
		
	}

	/**
	 * @return brukernavn
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		
		String oldString = getUserName();
		this.userName = userName;
        firePropertyChange(USER_NAME_PROPERTY, oldString, userName);
	}

	/**
	 * @return fornavn
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		
		String oldString = getFirstName();
		this.firstName = firstName;
        firePropertyChange(FIRST_NAME_PROPERTY, oldString, firstName);
	}

	/**
	 * @return etternavn
	 */
	public String getSurname() {
		return this.surname;
	}

	/**
	 * @param surname
	 */
	public void setSurname(String surname) {
		
		String oldString = getSurname();
		this.surname = surname;
        firePropertyChange(SURNAME_PROPERTY, oldString, surname);
	}

	/**
	 * @return passord
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		
		String oldString = getPassword();
		this.password = password;
        firePropertyChange(PASSWORD_PROPERTY, oldString, password);
	}

	/**
	 * @return type
	 */
	public ApplUserType getApplUserType() {
		return this.applUserType;
	}

	/**
	 * @param applUserType
	 */
	public void setApplUserType(ApplUserType applUserType) {
		
		ApplUserType oldApplUserType = getApplUserType();
		this.applUserType = applUserType;
        firePropertyChange(APPL_USER_TYPE_PROPERTY, oldApplUserType, applUserType);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ApplUser))
			return false;
		ApplUser castOther = (ApplUser) other;
		return new EqualsBuilder().append(userId, castOther.userId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (firstName != null && surname != null) {
			return firstName + " " + surname;
		}
		return userName;
	}

	/**
	 * @return look and feel
	 */
	public String getLnf() {
		return lnf;
	}

	/**
	 * @param lnf
	 */
	public void setLnf(String lnf) {
		
		String oldString = getLnf();
		this.lnf = lnf;
        firePropertyChange(LNF_PROPERTY, oldString, lnf);
	}

	/**
	 * @return påloggingsdato
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		
		Date oldDate = getStartDate();
		this.startDate = startDate;
        firePropertyChange(START_DATE_PROPERTY, oldDate, startDate);
	}

	/**
	 * @return Returns the guiVersion.
	 */
	public String getGuiVersion() {
		return guiVersion;
	}

	/**
	 * @param guiVersion The guiVersion to set.
	 */
	public void setGuiVersion(String guiVersion) {
		
		String oldString = getGuiVersion();
		this.guiVersion = guiVersion;
        firePropertyChange(GUI_VERSION_PROPERTY, oldString, guiVersion);
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public ApplUser getApplUser() {
		return this;
	}

}
