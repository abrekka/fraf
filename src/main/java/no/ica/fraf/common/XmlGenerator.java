package no.ica.fraf.common;

import javax.swing.JLabel;

import no.ica.elfa.model.ActionEnum;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.EGetable;
import no.ica.fraf.xml.EGetableFactory;
import no.ica.fraf.xml.EGetableFactoryImpl;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceCreatorFactory;
import no.ica.fraf.xml.InvoiceManagerInterface;

import org.apache.commons.lang.StringUtils;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public class XmlGenerator implements Threadable {
	/**
	 * 
	 */
	private WindowInterface window;
	private ApplUser applUser;
	private Bunt bunt;
	private ButtonEnabler buttonEnabler;
	private BuntDAO buntDAO;
	private EGetableFactory eGetableFactory;
	private InvoiceManagerInterface invoiceManagerInterface;

	/**
	 * @param aWindow
	 */
	@Inject
	public XmlGenerator(
			final BuntDAO aBuntDAO,
			final EGetableFactory aEGetableFactory,
			@Assisted final Bunt aBunt,@Assisted final ButtonEnabler aButtonEnabler,@Assisted final WindowInterface aWindow,@Assisted final ApplUser aApplUser,final @Assisted InvoiceManagerInterface aIinvoiceManagerInterface) {
		invoiceManagerInterface=aIinvoiceManagerInterface;
		window = aWindow;
		applUser = aApplUser;
		//invoiceManagerInterface = aInvoiceManagerInterface;
		//e2bPkgManager = aE2bPkgManager;
		//invoiceCreatorFactory = aInvoiceCreatorFactory;
		bunt = aBunt;
		buttonEnabler = aButtonEnabler;
		//paperManager=aPaperManager;
		//departmentDAO=aDepartmentDAO;
		//laasTypeDAO=aLaasTypeDAO;
		//laasDAO=aLaasDAO;
		//mvaDAO=aMvaDAO;
		buntDAO=aBuntDAO;
		//fakturaTekstVManager=aFakturaTekstVManager;
		//onakaDir=aOnakaDir;
		eGetableFactory=aEGetableFactory;
	}
	

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		String returnString = null;
		labelInfo.setText("Genererer XML-fil");

		try {
			String exportPath = ApplParamUtil
					.getStringParam("fraf_eget_export_path");

			if (FrafMain.getInstance().isTest()) {
				exportPath += "/test/";
			}

			//PaperManager paperManager = (PaperManager) ModelUtil.getBean(PaperManager.MANAGER_NAME);
			//DepartmentDAO departmentDAO = (DepartmentDAO) ModelUtil.getBean(DepartmentDAO.DAO_NAME);
			//LaasTypeDAO laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
			//LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
			//MvaDAO mvaDAO = (MvaDAO) ModelUtil.getBean(MvaDAO.DAO_NAME);
			//BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean(BuntDAO.DAO_NAME);
			//FakturaTekstVManager fakturaTekstVManager = (FakturaTekstVManager) ModelUtil.getBean("fakturaTekstVManager");
			//String onakaDir = ApplParamUtil.getStringParam("onaka_path");
			InvoiceColumnEnum orderColumn = InvoiceColumnEnum.getOrderColumn(window); 
			//final EGetable eget = EGetableFactoryImpl.getInstance(exportPath,
			final EGetable eget = eGetableFactory.getInstance(exportPath,
					applUser,
					orderColumn,SystemEnum.FRAF,null,invoiceManagerInterface
					);

			returnString = eget.createEgetDocument(bunt, labelInfo,
					//invoiceCreatorFactory.create(SystemEnum.FRAF, invoiceManagerInterface, null, mvaDAO, fakturaTekstVManager), 
					window);

			if (returnString == null || returnString.length() == 0) {
				BuntStatus status;

				status = getNextBuntStatus(bunt, ActionEnum.XML);

				bunt.setBuntStatus(status);
				buntDAO.saveBunt(bunt);
			}
		} catch (FrafException e) {
			e.printStackTrace();
			returnString = e.getMessage();
		}

		return returnString;
	}

	private BuntStatus getNextBuntStatus(Bunt bunt, ActionEnum actionEnum) {
		BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
				.getBean(BuntStatusDAO.DAO_NAME);
		BuntStatusEnum buntStatusEnum = BuntStatusEnum.valueOf(StringUtils
				.upperCase(bunt.getBuntStatus().getBeskrivelse().replace("/",
						"_")));
		return buntStatusDAO.findByKode(buntStatusEnum
				.getNextStatus(actionEnum));
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null && object.toString().length() != 0) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}
		buttonEnabler.updateEnablement();
	}

}
