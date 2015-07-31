package no.ica.elfa.model;

import no.ica.fraf.enums.BuntStatusEnum;

/**
 * Enum som brukes til � angi hvilken aksjon som gj�res for but
 * 
 * @author abr99
 * 
 */
public enum ActionEnum {
	/**
	 * Bokf�ring
	 */
	BOKFOR(BuntStatusEnum.BOKF�RT),
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
