package no.ica.fraf.xml;

import java.lang.reflect.Constructor;

import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.PaperManager;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public class EGetableFactoryImpl implements EGetableFactory {
	//private InvoiceManagerInterface invoiceManagerInterface;
	private E2bPkgManager e2bPkgManager;
	private PaperManager paperManager;
	private Locker locker;
	private DepartmentDAO departmentDAO;
	private String xmlDir;
	private InvoiceCreatorFactory invoiceCreatorFactory;

	@Inject
	public EGetableFactoryImpl(
			final E2bPkgManager aE2bPkgManager,
			final PaperManager aPaperManager, final Locker aLocker,
			final DepartmentDAO aDepartmentDAO,
			@Named("onaka_path") final String aXmlDir,
			final InvoiceCreatorFactory aInvoiceCreatorFactory) {
		//invoiceManagerInterface = aInvoiceManagerInterface;
		e2bPkgManager = aE2bPkgManager;
		paperManager = aPaperManager;
		locker = aLocker;
		departmentDAO = aDepartmentDAO;
		xmlDir = aXmlDir;
		invoiceCreatorFactory=aInvoiceCreatorFactory;
	}

	public EGetable getInstance(String aExportDir,

	ApplUserInterface aApplUser,

	InvoiceColumnEnum orderColumn,SystemEnum systemEnum,BokfSelskap bokfSelskap,InvoiceManagerInterface invoiceManagerInterface) {
		return SystemType.getSystemType(FrafMain.isStandalone()).isStandalone() ? getEGetStandalone(
				aExportDir,invoiceManagerInterface, aApplUser,
				orderColumn,systemEnum,bokfSelskap)
				: getEgetIntegrated(aExportDir, 
						invoiceManagerInterface,
						//e2bPkgManager, 
						aApplUser, 
						//paperManager, 
						orderColumn,
						//,locker, departmentDAO, xmlDir
						systemEnum,bokfSelskap
						);
	}

	private EGetable getEGetStandalone(String aExportDir,
			InvoiceManagerInterface aInvoiceManager,
			//E2bPkgManager aE2bPkgManager, 
			ApplUserInterface aApplUser,
			InvoiceColumnEnum orderColumn, 
			//Locker locker,
			//DepartmentDAO departmentDAO, 
			//String onakaDir
			SystemEnum systemEnum,
			BokfSelskap bokfSelskap
			) {
		try {
			Constructor<EGetStandalone> constructor = EGetStandalone.class
					.getDeclaredConstructor(String.class,
							InvoiceManagerInterface.class, E2bPkgManager.class,
							ApplUserInterface.class, InvoiceColumnEnum.class,
							Locker.class, DepartmentDAO.class, String.class,InvoiceCreator.class);
			constructor.setAccessible(true);
			InvoiceCreator invoiceCreator=invoiceCreatorFactory.create(systemEnum,aInvoiceManager, bokfSelskap);
			EGetStandalone egetable = constructor.newInstance(aExportDir,
					aInvoiceManager, e2bPkgManager, aApplUser, orderColumn,
					locker, departmentDAO, xmlDir,invoiceCreator);
			return egetable;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafRuntimeException(e.getMessage());
		}
	}

	private EGetable getEgetIntegrated(String aExportDir,
			InvoiceManagerInterface aInvoiceManager,
			//E2bPkgManager aE2bPkgManager, 
			ApplUserInterface aApplUser,
			//PaperManager paperManager, 
			InvoiceColumnEnum orderColumn,
			//Locker locker, DepartmentDAO departmentDAO, String onakaDir,
			SystemEnum systemEnum,
			BokfSelskap bokfSelskap) {
		try {
			Constructor<EGetIntegrated> constructor = EGetIntegrated.class
					.getDeclaredConstructor(String.class,
							InvoiceManagerInterface.class, E2bPkgManager.class,
							ApplUserInterface.class, PaperManager.class,
							InvoiceColumnEnum.class, Locker.class,
							DepartmentDAO.class, String.class,InvoiceCreator.class);
			constructor.setAccessible(true);
			InvoiceCreator invoiceCreator=invoiceCreatorFactory.create(systemEnum,aInvoiceManager, bokfSelskap);
			EGetIntegrated egetable = constructor.newInstance(aExportDir,
					aInvoiceManager, e2bPkgManager, aApplUser, paperManager,
					orderColumn, locker, departmentDAO, xmlDir,invoiceCreator);
			return egetable;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafRuntimeException(e.getMessage());
		}
	}
}
