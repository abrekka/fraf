package no.ica.fraf.service;

import java.util.Date;

public class InvoiceConfig {
	private Integer year;

	private Integer fromPeriode;

	private Integer toPeriode;

	private Integer fromAvdnr;

	private Integer toAvdnr;

	private Integer userId;

	private Date invoiceDate;

	private Date dueDate;

	private Integer avregningBasisId;

	private Integer betingelseGruppeId;

	private Integer[] betingelseGrupper;

	private Integer betingelseTypeId;

	private Integer[] notDepartments;

	private Integer fakturerAvregningType;

	private Integer selskapId;

	public InvoiceConfig(Integer aAvregningBasisId, Integer aBetingelseGruppeId, Integer[] someBetingelseGrupper,
			Integer aBetingelseTypeId, Date aDueDate, Integer aFakturerAvregningType, Integer aFromAvdnr,
			Integer aFromPeriode, Date aInvoiceDate, Integer[] someNotDepartments, Integer aSelskapId,
			Integer aToAvdnr, Integer aToPeriode, Integer aUserId, Integer year) {
		super();
		// TODO Auto-generated constructor stub
		avregningBasisId = aAvregningBasisId;
		betingelseGruppeId = aBetingelseGruppeId;
		betingelseGrupper = someBetingelseGrupper;
		betingelseTypeId = aBetingelseTypeId;
		dueDate = aDueDate;
		fakturerAvregningType = aFakturerAvregningType;
		fromAvdnr = aFromAvdnr;
		fromPeriode = aFromPeriode;
		invoiceDate = aInvoiceDate;
		notDepartments = someNotDepartments;
		selskapId = aSelskapId;
		toAvdnr = aToAvdnr;
		toPeriode = aToPeriode;
		userId = aUserId;
		this.year = year;
	}

	public Integer getAvregningBasisId() {
		return avregningBasisId;
	}

	public void setAvregningBasisId(Integer avregningBasisId) {
		this.avregningBasisId = avregningBasisId;
	}

	public Integer getBetingelseGruppeId() {
		return betingelseGruppeId;
	}

	public void setBetingelseGruppeId(Integer betingelseGruppeId) {
		this.betingelseGruppeId = betingelseGruppeId;
	}

	public Integer[] getBetingelseGrupper() {
		return betingelseGrupper;
	}

	public void setBetingelseGrupper(Integer[] betingelseGrupper) {
		this.betingelseGrupper = betingelseGrupper;
	}

	public Integer getBetingelseTypeId() {
		return betingelseTypeId;
	}

	public void setBetingelseTypeId(Integer betingelseTypeId) {
		this.betingelseTypeId = betingelseTypeId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getFakturerAvregningType() {
		return fakturerAvregningType;
	}

	public void setFakturerAvregningType(Integer fakturerAvregningType) {
		this.fakturerAvregningType = fakturerAvregningType;
	}

	public Integer getFromAvdnr() {
		return fromAvdnr;
	}

	public void setFromAvdnr(Integer fromAvdnr) {
		this.fromAvdnr = fromAvdnr;
	}

	public Integer getFromPeriode() {
		return fromPeriode;
	}

	public void setFromPeriode(Integer fromPeriode) {
		this.fromPeriode = fromPeriode;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer[] getNotDepartments() {
		return notDepartments;
	}

	public void setNotDepartments(Integer[] notDepartments) {
		this.notDepartments = notDepartments;
	}

	public Integer getSelskapId() {
		return selskapId;
	}

	public void setSelskapId(Integer selskapId) {
		this.selskapId = selskapId;
	}

	public Integer getToAvdnr() {
		return toAvdnr;
	}

	public void setToAvdnr(Integer toAvdnr) {
		this.toAvdnr = toAvdnr;
	}

	public Integer getToPeriode() {
		return toPeriode;
	}

	public void setToPeriode(Integer toPeriode) {
		this.toPeriode = toPeriode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
