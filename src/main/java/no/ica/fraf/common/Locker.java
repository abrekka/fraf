package no.ica.fraf.common;

import java.awt.Component;

import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Laas;

public interface Locker {
	boolean lock(ApplUserInterface applUser,Component component,LaasTypeEnum laasTypeEnum,Avdeling avdeling);
	void unlock();
}
