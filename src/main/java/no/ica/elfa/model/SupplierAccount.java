package no.ica.elfa.model;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Representerer tabell SUPPLIER_ACCOUNT
 * 
 * @author abr99
 * 
 */
public class SupplierAccount extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer supplierNo;

	/**
	 * 
	 */
	private String accountName;

	/**
	 * 
	 */
	private String accountNo;

	/**
	 * 
	 */
	public SupplierAccount() {
		super();
	}

	/**
	 * @param name
	 * @param no
	 * @param no2
	 */
	public SupplierAccount(String name, String no, Integer no2) {
		super();
		accountName = name;
		accountNo = no;
		supplierNo = no2;
	}

	/**
	 * @return kontonavn
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return kontonummer
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return leverandørnummer
	 */
	public Integer getSupplierNo() {
		return supplierNo;
	}

	/**
	 * @param supplierNo
	 */
	public void setSupplierNo(Integer supplierNo) {
		this.supplierNo = supplierNo;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SupplierAccount))
			return false;
		SupplierAccount castOther = (SupplierAccount) other;
		return new EqualsBuilder().append(supplierNo, castOther.supplierNo)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(supplierNo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return accountName;
	}
}
