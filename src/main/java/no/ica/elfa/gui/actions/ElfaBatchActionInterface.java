package no.ica.elfa.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.InvoiceItemVManager;
import no.ica.elfa.service.InvoiceManager;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.EflowUsersVManager;

/**
 * Interface for klasse som håndterer menyvalg bunter. Brukes for å kunne sette
 * parametre i spring-config fil
 * 
 * @author abr99
 * 
 */
public interface ElfaBatchActionInterface extends Action {
	/**
	 * @param applUser
	 */
	void setApplUser(ApplUser applUser);

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	void actionPerformed(ActionEvent arg0);

	/**
	 * @param pkgManager
	 */
	void setE2bPkgManager(E2bPkgManager pkgManager);

	/**
	 * @param invoiceManager
	 */
	void setInvoiceManager(InvoiceManager invoiceManager);

	/**
	 * @param invoiceItemVManager
	 */
	void setInvoiceItemVManager(InvoiceItemVManager invoiceItemVManager);

	/**
	 * @param bokfSelskapDAO
	 */
	void setBokfSelskapDAO(BokfSelskapDAO bokfSelskapDAO);

	/**
	 * @param eflowUsersVManager
	 */
	//void setEflowUsersVManager(EflowUsersVManager eflowUsersVManager);

}
