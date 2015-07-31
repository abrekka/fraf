package no.ica.fraf.enums;

/**
 * Enum for bunttype
 * 
 * @author abr99
 * 
 */
public enum BuntTypeEnum {
	/**
	 * Bunttype faktura
	 */
	BATCH_TYPE_INVOICE {
		@Override
		public String getKode() {
			return "FAK";
		}
	},
	/**
	 * Bunttype omsetning
	 */
	BATCH_TYPE_SALE {
		@Override
		public String getKode() {
			return "INN_OMS";
		}
	},
	/**
	 * Bunttype import
	 */
	BATCH_TYPE_IMPORT {
		@Override
		public String getKode() {
			return "IMPORT";
		}
	},
	/**
	 * Bunttype budsjett
	 */
	BATCH_TYPE_BUDGET {
		@Override
		public String getKode() {
			return "INN_BUD";
		}
	},
	/**
	 * Bunttype speilet kostnad
	 */
	BATCH_TYPE_MIRROR {
		@Override
		public String getKode() {
			return "INN_SPE";
		}
	},
	/**
	 * Bunttype import faktura
	 */
	BATCH_TYPE_IMP_FAK {
		@Override
		public String getKode() {
			return "INN_FAK";
		}
	},
	/**
	 * Bunttype import faktura
	 */
	BATCH_TYPE_TG_IMPORT {
		@Override
		public String getKode() {
			return "TG_IMPORT";
		}
	},
	/**
	 * Avregning
	 */
	BATCH_TYPE_INN_AVR {
		@Override
		public String getKode() {
			return "INN_AVR";
		}
	},
	BATCH_TYPE_ELFA{
		@Override
		public String getKode() {
			return "ELFA";
		}
	};
	/**
	 * Henter ut strengkode for bunttype
	 * 
	 * @return strengkode for bunttype
	 */
	public abstract String getKode();
}
