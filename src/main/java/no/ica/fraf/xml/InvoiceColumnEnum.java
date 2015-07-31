package no.ica.fraf.xml;

import javax.swing.JOptionPane;

import no.ica.fraf.common.WindowInterface;

public enum InvoiceColumnEnum {
	INVOICE_NR("Fakturanr","invoiceNr","fakturaNr"),
	DEPARTMENT_NR("Avdnr","storeNo","avdnr");
	
	private String columnName;
	private String elfaColumnName;
	private String frafColumnName;
	
	private InvoiceColumnEnum(String aColumnName,String aElfaColumnName,String aFrafColumnName){
		columnName=aColumnName;
		elfaColumnName=aElfaColumnName;
		frafColumnName=aFrafColumnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getElfaColumnName() {
		return elfaColumnName;
	}

	public String getFrafColumnName() {
		return frafColumnName;
	}
	
	public String toString(){
		return columnName;
	}
	
	public static InvoiceColumnEnum getOrderColumn(WindowInterface window) {
		InvoiceColumnEnum orderByEnum = window!=null?(InvoiceColumnEnum) JOptionPane
				.showInputDialog(window.getComponent(), "Velg sortering",
						"Kolonne", JOptionPane.QUESTION_MESSAGE, null,
						new InvoiceColumnEnum[] { InvoiceColumnEnum.INVOICE_NR,
								InvoiceColumnEnum.DEPARTMENT_NR },
						InvoiceColumnEnum.INVOICE_NR):InvoiceColumnEnum.INVOICE_NR;
		return orderByEnum;
	}
	
}
