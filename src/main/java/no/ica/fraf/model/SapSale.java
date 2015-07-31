package no.ica.fraf.model;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SapSale extends BaseObject{

	private Integer saleId;
	private Integer year;
	private Integer periode;
	private Integer departmentNr;
	private BigDecimal sale;
	private BigDecimal budget;

	

	public BigDecimal getBudget() {
		return budget;
	}

	public void setYear(Integer aYear) {
		year=aYear;
		
	}

	public void setPeriode(Integer aPeriode) {
		periode=aPeriode;
		
	}

	public void setDepartmentNr(Integer aDepartmentNr) {
		departmentNr=aDepartmentNr;
		
	}

	public void setSale(BigDecimal aSale) {
		sale=aSale;
		
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SapSale))
			return false;
		SapSale castOther = (SapSale) other;
		return new EqualsBuilder().append(year, castOther.year).append(periode,
				castOther.periode).append(departmentNr, castOther.departmentNr)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(year).append(periode).append(
				departmentNr).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("saleId", saleId).append(
				"year", year).append("periode", periode).append("departmentNr",
				departmentNr).append("sale", sale).toString();
	}

	public Integer getDepartmentNr() {
		return departmentNr;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getPeriode() {
		return periode;
	}

	public BigDecimal getSale() {
		return sale;
	}

	public void setBudget(BigDecimal aBudget) {
		budget=aBudget;
		
	}
	
}
