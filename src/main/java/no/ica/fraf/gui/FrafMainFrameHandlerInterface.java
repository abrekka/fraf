package no.ica.fraf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LoggDAO;
import no.ica.fraf.dao.MangelTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.model.ApplUser;

/**
 * Interface for klasse som skal håndterer aksjoner
 * 
 * @author abr99
 * 
 */
public interface FrafMainFrameHandlerInterface extends ActionListener {

	/**
	 * Setter bruker
	 * 
	 * @param aApplUser
	 */
	public abstract void setApplUser(ApplUser aApplUser);

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public abstract void actionPerformed(ActionEvent actionEvent);

	/**
	 * Avslutter system
	 */
	public abstract void exitSystem();

	/**
	 * Kaskade vinduer
	 */
	public abstract void cascadeWindows();

	/**
	 * Setter bruker
	 * 
	 * @param currentApplUser
	 */
	public abstract void setCurrentApplUser(ApplUser currentApplUser);

	/**
	 * @param applUserDAO
	 */
	//public abstract void setApplUserDAO(ApplUserDAO applUserDAO);

	/**
	 * @param avregningFrekvensTypeDAO
	 */
	/*public abstract void setAvregningFrekvensTypeDAO(
			AvregningFrekvensTypeDAO avregningFrekvensTypeDAO);*/

	/**
	 * @param avregningTypeDAO
	 */
	//public abstract void setAvregningTypeDAO(AvregningTypeDAO avregningTypeDAO);

	/**
	 * @param betingelseGruppeDAO
	 */
	/*public abstract void setBetingelseGruppeDAO(
			BetingelseGruppeDAO betingelseGruppeDAO);*/

	/**
	 * @param betingelseTypeDAO
	 */
	/*public abstract void setBetingelseTypeDAO(
			BetingelseTypeDAO betingelseTypeDAO);*/

	/**
	 * @param bokfSelskapDAO
	 */
	//public abstract void setBokfSelskapDAO(BokfSelskapDAO bokfSelskapDAO);

	/**
	 * @param kontraktTypeDAO
	 */
	//public abstract void setKontraktTypeDAO(KontraktTypeDAO kontraktTypeDAO);

	/**
	 * @param loggDAO
	 */
	//public abstract void setLoggDAO(LoggDAO loggDAO);

	/**
	 * @param laasDAO
	 */
	//public abstract void setLaasDAO(LaasDAO laasDAO);

	/**
	 * @param mangelTypeDAO
	 */
	//public abstract void setMangelTypeDAO(MangelTypeDAO mangelTypeDAO);

	/**
	 * @param mvaDAO
	 */
	
	ApplUser getCurrentApplUser();
	void iconfy(boolean iconfy);
	void closeAllFrames();
	void createContractTypeFrame()throws FrafException;
	void createSettlementTypeFrame() throws FrafException;
	void createSettlementBasisTypeFrame() throws FrafException;
	void createSettlementFrequencyTypeFrame() throws FrafException;
	void createContractRenewalTypeFrame() throws FrafException;
	void createMvaFrame() throws FrafException;
	void createContractConditionFrame() throws FrafException;
	void createSystemParamFrame() throws FrafException;
	void createSecurityTypeFrame() throws FrafException;
	void createConditionGroupFrame() throws FrafException;
	void createChainFrame() throws FrafException;
	void createActiveUsersFrame() throws FrafException;
	void createCompanyFrame() throws FrafException;
	void createSystemLogFrame() throws FrafException;
}