package no.ica.fraf.common;

import java.util.HashMap;
import java.util.Map;

import no.ica.fraf.gui.FrafMain;

public enum SystemType {
	INTEGRATED(false, 0),STANDALONE(true, 1);
	private boolean standalone;
	private Integer systemType;
	private static final Map<Boolean, SystemType> systemTypes = new HashMap<Boolean, SystemType>();
	static {
		SystemType[] types = SystemType.values();
		for (SystemType type : types) {
			systemTypes.put(type.isStandalone(), type);
		}
	}

	private SystemType(final boolean isStandalone, final Integer aSystemType) {
		standalone = isStandalone;
		systemType = aSystemType;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public boolean isStandalone() {
		return standalone;
	}

	public static SystemType getSystemType(final boolean isStandalone) {
		return systemTypes.get(isStandalone);
	}
}
