package no.ica.tollpost.gui;

public enum TgImportTypeEnum {
	CLAIM("INVOIC"),
	COMMISSION("CREDIT");
	
	private String type;
	private TgImportTypeEnum(String typeString){
		type=typeString;
	}
	public String getType(){
		return type;
	}
}
