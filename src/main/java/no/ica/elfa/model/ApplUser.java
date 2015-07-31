package no.ica.elfa.model;

import java.util.Date;


import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell APP_USER
 * 
 * @author abr99
 * 
 */
public class ApplUser extends BaseObject implements ApplUserInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer userId;

	/** persistent field */
	private String userName;

	/** nullable persistent field */
	private String firstName;

	/** nullable persistent field */
	private String surname;

	/** nullable persistent field */
	private String password;

	/**
	 * 
	 */
	private String lnf;

	/**
	 * 
	 */
	private Date startDate;

	/** persistent field */
	private no.ica.elfa.model.ApplUserType applUserType;

	/**
	 * @param userName
	 * @param firstName
	 * @param surname
	 * @param password
	 * @param applUserType
	 * @param lnf
	 * @param startDate
	 */
	public ApplUser(String userName, String firstName, String surname,
			String password, ApplUserType applUserType, String lnf,
			Date startDate) {// , WorksheetLock worksheetLock) {
		this.userName = userName;
		this.firstName = firstName;
		this.surname = surname;
		this.password = password;
		this.applUserType = applUserType;
		this.lnf = lnf;
		this.startDate = startDate;
	}

	/** default constructor */
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
		this.userId = userId;
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
		this.userName = userName;
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
		this.firstName = firstName;
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
		this.surname = surname;
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
		this.password = password;
	}

	/**
	 * @return brukertype
	 */
	public ApplUserType getApplUserType() {
		return this.applUserType;
	}

	/**
	 * @param applUserType
	 */
	public void setApplUserType(ApplUserType applUserType) {
		this.applUserType = applUserType;
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
		this.lnf = lnf;
	}

	/**
	 * @return dato for oppstart av applikasjon
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public no.ica.fraf.model.ApplUser getApplUser() {
		return null;
	}

}
