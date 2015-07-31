package no.ica.fraf.enums;

import no.ica.elfa.model.ActionEnum;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BuntStatus;

/**
 * Enum for buntstatus
 * 
 * @author abr99
 * 
 */
public enum BuntStatusEnum {
	/**
	 * Ny
	 */
	NY("NY") {
		@Override
		public boolean canRollback() {
			return true;
		}

		@Override
		public boolean canBook() {
			return false;
		}

		@Override
		public boolean canXml() {
			return false;
		}

		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			throw new IllegalStateException(
			"Buntstatusovergang er ikke definert");
		}

		@Override
		public boolean canDeduct() {
			return true;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/**
	 * Fakturert
	 */
	FAKTURERT("FA") {
		@Override
		public boolean canRollback() {
			return true;
		}

		@Override
		public boolean canBook() {
			return true;
		}

		@Override
		public boolean canXml() {
			return getSystemType()==SystemType.INTEGRATED?false:true;
		}

		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			return actionEnum.getBuntStatusEnum();
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/**
	 * Bokført
	 */
	BOKFØRT("BF") {
		@Override
		public boolean canRollback() {
			return false;
		}

		@Override
		public boolean canBook() {
			return false;
		}

		@Override
		public boolean canXml() {
			return true;
		}

		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			return BuntStatusEnum.XML_BOKFØRT;
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/**
	 * XML
	 */
	XML("XM") {
		@Override
		public boolean canRollback() {
			return false;
		}

		@Override
		public boolean canBook() {
			return true;
		}

		@Override
		public boolean canXml() {
			return false;
		}

		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			return BuntStatusEnum.XML_BOKFØRT;
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/**
	 * Avregnet
	 */
	AVREGNET("AV") {
		@Override
		public boolean canRollback() {
			return true;
		}

		@Override
		public boolean canBook() {
			return false;
		}

		@Override
		public boolean canXml() {
			return false;
		}

		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			throw new IllegalStateException(
			"Buntstatusovergang er ikke definert");
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			return true;
		}
	},
	/**
	 * Xml/Bokført
	 */
	XML_BOKFØRT("XB") {
		@Override
		public boolean canRollback() {
			return false;
		}

		@Override
		public boolean canBook() {
			return false;
		}

		@Override
		public boolean canXml() {
			return false;
		}

		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			throw new IllegalStateException(
			"Buntstatusovergang er ikke definert");
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	IMPORTERT("IMP") {
		@Override
		public boolean canRollback() {
			return true;
		}

		@Override
		public boolean canBook() {
			return false;
		}

		@Override
		public boolean canXml() {
			return false;
		}
		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			throw new IllegalStateException(
			"Buntstatusovergang er ikke definert");
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	INNLEST("INN") {
		@Override
		public boolean canRollback() {
			return true;
		}

		@Override
		public boolean canBook() {
			return false;
		}

		@Override
		public boolean canXml() {
			return false;
		}
		@Override
		public BuntStatusEnum getNextStatus(ActionEnum actionEnum) {
			throw new IllegalStateException(
			"Buntstatusovergang er ikke definert");
		}

		@Override
		public boolean canDeduct() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canInvoice() {
			// TODO Auto-generated method stub
			return false;
		}
	};
	/**
	 * Kode
	 */
	private String kode;
	

	/**
	 * @param kodeString
	 */
	private BuntStatusEnum(String kodeString) {
		kode = kodeString;
	}

	public abstract boolean canRollback();
	public abstract boolean canBook();
	public abstract boolean canXml();
	public abstract boolean canDeduct();
	public abstract boolean canInvoice();
	public abstract BuntStatusEnum getNextStatus(ActionEnum actionEnum);
	
	/**
	 * @return Returns the kode.
	 */
	public String getKode() {
		return kode;
	}
	private static SystemType getSystemType(){
		return SystemType.getSystemType(FrafMain.isStandalone());
	}

	

	

	

	/**
	 * Henter enum ut i fra status
	 * 
	 * @param status
	 * @return statusenum
	 */
	/*public static BuntStatusEnum getEnum(BuntStatus status) {
		if (status != null) {

			if (status.getBeskrivelse().equalsIgnoreCase("Ny")) {
				return BUNT_STATUS_NY;
			} else if (status.getBeskrivelse().equalsIgnoreCase("Fakturert")) {
				return BUNT_STATUS_FA;
			} else if (status.getBeskrivelse().equalsIgnoreCase("Bokført")) {
				return BUNT_STATUS_BF;
			} else if (status.getBeskrivelse().equalsIgnoreCase("Xml")) {
				return BUNT_STATUS_XM;
			} else if (status.getBeskrivelse().equalsIgnoreCase("Avregnet")) {
				return BUNT_STATUS_AV;
			} else if (status.getBeskrivelse().equalsIgnoreCase("Xml/Bokført")) {
				return BUNT_STATUS_XB;
			}
		}
		return null;
	}*/
}
