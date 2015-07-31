package no.ica.fraf.gui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import no.ica.elfa.model.Invoice;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;

import com.jgoodies.binding.beans.Model;

public class BuntModel extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public static final String PROPERTY_INVOICES = "invoices";

	/**
	 * 
	 */
	private Bunt currentBunt;

	/**
	 * @param batch
	 */
	public BuntModel(Bunt bunt) {
		currentBunt = bunt;
	}

	/**
	 * @return bunt
	 */
	public Bunt getBunt() {
		return currentBunt;
	}

	/**
	 * @return fakturaer
	 */
	public List<Invoice> getInvoices() {
		List<Invoice> invoices = new ArrayList<Invoice>();
		Set<Invoice> invoiceSet = currentBunt.getInvoices();
		if (invoiceSet != null) {
			invoices.addAll(invoiceSet);
		}
		return invoices;
	}

	/**
	 * @return buntid
	 */
	public Integer getBuntId() {
		return currentBunt.getBuntId();
	}

	/**
	 * @return opprettet dato
	 */
	public Date getCreatedDate() {
		return currentBunt.getCreatedDate();
	}

	/**
	 * @return bruker
	 */
	public ApplUser getApplUser() {
		return currentBunt.getApplUser();
	}

	/**
	 * @return fra dato
	 */
	public Date getFraDato() {
		return currentBunt.getFraDato();
	}

	/**
	 * @return til dato
	 */
	public Date getTilDato() {
		return currentBunt.getTilDato();
	}

	/**
	 * @return buntstatus
	 */
	public BuntStatus getBuntStatus() {
		return currentBunt.getBuntStatus();
	}

	/**
	 * @return filnavn
	 */
	public String getFileName() {
		return currentBunt.getFileName();
	}
}
