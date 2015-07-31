package no.ica.fraf.common;

import java.awt.Component;
import java.util.Calendar;

import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Laas;
import no.ica.fraf.model.LaasType;
import no.ica.fraf.util.GuiUtil;

import com.google.inject.Inject;

public class Locking implements Locker {
	private Laas currentLaas;
	private LaasTypeDAO laasTypeDAO;
	private LaasDAO laasDAO;

	@Inject
	public Locking(LaasTypeDAO typeDAO, LaasDAO aLaasDAO) {
		laasTypeDAO = typeDAO;
		laasDAO = aLaasDAO;
	}

	public final boolean lock(ApplUserInterface applUser, Component component,
			LaasTypeEnum laasTypeEnum, Avdeling avdeling) {
		// LaasTypeDAO
		// laasTypeDAO=(LaasTypeDAO)ModelUtil.getBean("laasTypeDAO");
		// LaasDAO laasDAO=(LaasDAO)ModelUtil.getBean("laasDAO");
		LaasType laasType = laasTypeDAO.findByKode(laasTypeEnum);
		Laas laas = null;
		boolean success = true;
		if (avdeling != null) {
			laas = laasDAO.findByAvdeling(avdeling);
		} else {
			laas = laasDAO.findByLaasType(laasType);
		}

		if (laas != null) {
			GuiUtil.showErrorMsgDialog(component, "Låst", laasTypeEnum
					.getText()
					+ " er låst av "
					+ laas.getApplUser()
					+ " prøv igjen senere");
			// return null;
			success = false;
		} else {

			currentLaas = new Laas();
			currentLaas.setApplUser(applUser.getApplUser());
			currentLaas.setLaasDato(Calendar.getInstance().getTime());
			currentLaas.setLaasType(laasType);

			if (avdeling != null) {
				currentLaas.setAvdeling(avdeling);
			}

			laasDAO.saveLaas(currentLaas);
		}
		// return laas;
		return success;
	}

	public final void unlock() {
		if (currentLaas != null) {
			//LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
			laasDAO.removeLaas(currentLaas.getLaasId());
			currentLaas=null;
		}
	}
}
