package no.ica.elfa.model;

import no.ica.fraf.enums.BuntStatusEnum;

/**
 * Enum som brukes til å angi hvilken aksjon som gjøres for but
 * 
 * @author abr99
 * 
 */
public enum ActionEnum {
	/**
	 * Bokføring
	 */
	BOKFOR(BuntStatusEnum.BOKFØRT),
	/**
	 * XML generering
	 */
	XML(BuntStatusEnum.XML);

	private BuntStatusEnum buntStatusEnum;
	private ActionEnum(BuntStatusEnum statusEnum){
		buntStatusEnum=statusEnum;
	}
	public BuntStatusEnum getBuntStatusEnum() {
		return buntStatusEnum;
	}
}
