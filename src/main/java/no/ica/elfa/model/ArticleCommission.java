package no.ica.elfa.model;

import java.math.BigDecimal;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Representerer tabell ARTICLE_COMMISSION
 * 
 * @author abr99
 * 
 */
public class ArticleCommission extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer articleCommissionId;

	/**
	 * 
	 */
	private String commissionType;

	/**
	 * 
	 */
	private BigDecimal articleNo;

	/**
	 * 
	 */
	private Integer formatId;

	/**
	 * 
	 */
	private Integer regionId;

	/**
	 * 
	 */
	private BigDecimal commissionPercentage;

	/**
	 * 
	 */
	private String commissionComment;

	/**
	 * 
	 */
	private SupplierAccount supplierAccount;

	/**
	 * 
	 */
	private String articleName;

	/**
	 * 
	 */
	private String code;

	/**
	 * 
	 */
	public ArticleCommission() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param number
	 * @param code
	 * @param comment
	 * @param percentage
	 * @param type
	 * @param id2
	 * @param id3
	 * @param supplierAccount
	 */
	public ArticleCommission(Integer id, String name, BigDecimal number,
			String code, String comment, BigDecimal percentage, String type,
			Integer id2, Integer id3, SupplierAccount supplierAccount) {
		super();
		articleCommissionId = id;
		articleName = name;
		articleNo = number;
		this.code = code;
		commissionComment = comment;
		commissionPercentage = percentage;
		commissionType = type;
		formatId = id2;
		regionId = id3;
		this.supplierAccount = supplierAccount;
	}

	/**
	 * @return id
	 */
	public Integer getArticleCommissionId() {
		return articleCommissionId;
	}

	/**
	 * @param articleCommissionId
	 */
	public void setArticleCommissionId(Integer articleCommissionId) {
		this.articleCommissionId = articleCommissionId;
	}

	/**
	 * @return artikkelnavn
	 */
	public String getArticleName() {
		return articleName;
	}

	/**
	 * @param articleName
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	/**
	 * @return artikkelnummer
	 */
	public BigDecimal getArticleNo() {
		return articleNo;
	}

	/**
	 * @param articleNo
	 */
	public void setArticleNo(BigDecimal articleNo) {
		this.articleNo = articleNo;
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
	 * @return kommentar
	 */
	public String getCommissionComment() {
		return commissionComment;
	}

	/**
	 * @param commissionComment
	 */
	public void setCommissionComment(String commissionComment) {
		this.commissionComment = commissionComment;
	}

	/**
	 * @return prosent
	 */
	public BigDecimal getCommissionPercentage() {
		return commissionPercentage;
	}

	/**
	 * @param commissionPercentage
	 */
	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	/**
	 * @return type
	 */
	public String getCommissionType() {
		return commissionType;
	}

	/**
	 * @param commissionType
	 */
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	/**
	 * @return formatid
	 */
	public Integer getFormatId() {
		return formatId;
	}

	/**
	 * @param formatId
	 */
	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	/**
	 * @return regionid
	 */
	public Integer getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return leverandørkonto
	 */
	public SupplierAccount getSupplierAccount() {
		return supplierAccount;
	}

	/**
	 * @param supplierAccount
	 */
	public void setSupplierAccount(SupplierAccount supplierAccount) {
		this.supplierAccount = supplierAccount;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ArticleCommission))
			return false;
		ArticleCommission castOther = (ArticleCommission) other;
		return new EqualsBuilder().append(commissionType,
				castOther.commissionType)
				.append(articleNo, castOther.articleNo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(commissionType).append(articleNo)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("commissionType",
				commissionType).append("articleNo", articleNo).append(
				"articleName", articleName).append("code", code).toString();
	}
}
