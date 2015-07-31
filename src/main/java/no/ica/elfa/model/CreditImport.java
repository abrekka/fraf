package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;
import no.ica.fraf.model.Bunt;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Representere tabell CREDIT_IMPORT
 * 
 * @author abr99
 * 
 */
public class CreditImport extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer creditImportId;

	/**
	 * 
	 */
	private Date creditDate;

	/**
	 * 
	 */
	private String code;

	/**
	 * 
	 */
	private BigDecimal price;

	/**
	 * 
	 */
	private Integer depNr;

	/**
	 * 
	 */
	private Integer counter;

	/**
	 * 
	 */
	private Bunt bunt;

	/**
	 * 
	 */
	private String fileName;

	/**
	 * 
	 */
	public CreditImport() {
		super();
	}

	public CreditImport(String code, Integer counter, Date date, Integer id,
			Integer nr, BigDecimal price, Bunt bunt, String fileName) {
		super();
		this.code = code;
		this.counter = counter;
		creditDate = date;
		creditImportId = id;
		depNr = nr;
		this.price = price;
		this.bunt = bunt;
		this.fileName = fileName;
	}

	/**
	 * @return kode
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return krediteringsdato
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * @param creditDate
	 */
	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * @return id
	 */
	public Integer getCreditImportId() {
		return creditImportId;
	}

	/**
	 * @param creditImportId
	 */
	public void setCreditImportId(Integer creditImportId) {
		this.creditImportId = creditImportId;
	}

	/**
	 * @return avdnr
	 * 
	 */
	public Integer getDepNr() {
		return depNr;
	}

	/**
	 * @param depNr
	 */
	public void setDepNr(Integer depNr) {
		this.depNr = depNr;
	}

	/**
	 * @return pris
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return teller
	 */
	public Integer getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 */
	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CreditImport))
			return false;
		CreditImport castOther = (CreditImport) other;
		return new EqualsBuilder().append(creditDate, castOther.creditDate)
				.append(code, castOther.code).append(price, castOther.price)
				.append(depNr, castOther.depNr).append(counter,
						castOther.counter).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(creditDate).append(code).append(
				price).append(depNr).append(counter).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("creditDate", creditDate)
				.append("code", code).append("price", price).append("depNr",
						depNr).append("counter", counter).toString();
	}

	/**
	 * @return bunt
	 */
	public Bunt getBunt() {
		return bunt;
	}

	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @return filnavn
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
