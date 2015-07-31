package no.ica.elfa.model;

import java.math.BigDecimal;

import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Representerer view RECONSIL_V
 * 
 * @author abr99
 * 
 */
public class ReconcilV extends BaseObject implements ReconcilVInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer batchId;

	/**
	 * 
	 */
	private BigDecimal net;

	/**
	 * 
	 */
	private BigDecimal vat;

	/**
	 * 
	 */
	private String invoiceType;

	/**
	 * 
	 */
	private Integer invoiceCount;

	/**
	 * 
	 */
	private Integer xmlCount;

	/**
	 * 
	 */
	public ReconcilV() {
		super();
	}

	/**
	 * @param id
	 * @param count
	 * @param type
	 * @param net
	 * @param vat
	 * @param xmlCount
	 */
	public ReconcilV(Integer id, Integer count, String type, BigDecimal net,
			BigDecimal vat, Integer xmlCount) {
		super();
		batchId = id;
		invoiceCount = count;
		invoiceType = type;
		this.net = net;
		this.vat = vat;
		this.xmlCount = xmlCount;
	}

	/**
	 * @return buntid
	 */
	public Integer getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return antall faktura
	 */
	public Integer getInvoiceCount() {
		return invoiceCount;
	}

	/**
	 * @param invoiceCount
	 */
	public void setInvoiceCount(Integer invoiceCount) {
		this.invoiceCount = invoiceCount;
	}

	/**
	 * @return fakturatype
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return netto
	 */
	public BigDecimal getNet() {
		return net;
	}

	/**
	 * @param net
	 */
	public void setNet(BigDecimal net) {
		this.net = net;
	}

	/**
	 * @return moms
	 */
	public BigDecimal getVat() {
		return vat;
	}

	/**
	 * @param vat
	 */
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ReconcilV))
			return false;
		ReconcilV castOther = (ReconcilV) other;
		return new EqualsBuilder().append(batchId, castOther.batchId).append(
				invoiceType, castOther.invoiceType).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(batchId).append(invoiceType)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("batchId", batchId).append(
				"net", net).append("vat", vat).append("invoiceType",
				invoiceType).append("invoiceCount", invoiceCount).toString();
	}

	/**
	 * @see no.ica.fraf.common.ReconcilVInterface#getXmlCount()
	 */
	public Integer getXmlCount() {
		return xmlCount;
	}

	/**
	 * @param xmlCount
	 */
	public void setXmlCount(Integer xmlCount) {
		this.xmlCount = xmlCount;
	}
}
