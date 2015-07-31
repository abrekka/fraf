package no.ica.fraf.modules;

import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.common.XmlGenerator;
import no.ica.fraf.common.XmlGeneratorFactory;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.FornyelseTypeDAO;
import no.ica.fraf.dao.KjedeDAO;
import no.ica.fraf.dao.KontraktBetingelseDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.SikkerhetTypeDAO;
import no.ica.fraf.dao.fenistra.LfFakturaPosterDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.FrafMainFrameHandler;
import no.ica.fraf.gui.FrafMainFrameHandlerInterface;
import no.ica.fraf.gui.FrafMainMenuBar;
import no.ica.fraf.gui.FrafMainMenuBarInterface;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.LoginDialog;
import no.ica.fraf.gui.PanelFrafMain;
import no.ica.fraf.gui.PanelFrafMainFactory;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.service.SapXmlDataImporter;
import no.ica.fraf.service.impl.SaleImporter;
import no.ica.fraf.service.impl.StoreInfoImporterImpl;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.EGetableFactory;
import no.ica.fraf.xml.EGetableFactoryImpl;
import no.ica.fraf.xml.InvoiceCreatorFactory;
import no.ica.fraf.xml.InvoiceCreatorFactoryImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.name.Names;
import com.google.inject.util.Providers;

public class FrafModule extends AbstractModule{
	private FrafModule(){
		
	}

	@Override
	protected void configure() {
		if(FrafMain.isStandalone()){
			bind(SapXmlDataImporter.class).annotatedWith(Names.named("storeinfo")).to(StoreInfoImporterImpl.class).in(Singleton.class);
			bind(SapXmlDataImporter.class).annotatedWith(Names.named("sale")).to(SaleImporter.class).in(Singleton.class);
		}else{
			bind(SapXmlDataImporter.class).annotatedWith(Names.named("storeinfo")).toProvider(Providers.of((SapXmlDataImporter)null));
			bind(SapXmlDataImporter.class).annotatedWith(Names.named("sale")).toProvider(Providers.of((SapXmlDataImporter)null));
		}
		bind(FrafMainMenuBarInterface.class).to(FrafMainMenuBar.class).in(Singleton.class);
		bind(FrafMainFrameHandlerInterface.class).to(FrafMainFrameHandler.class).in(Singleton.class);
		bind(E2bPkgManager.class).toInstance((E2bPkgManager) ModelUtil.getBean("frafE2bPkgManager"));
		bind(XmlGeneratorFactory.class).toProvider(FactoryProvider.newFactory(XmlGeneratorFactory.class, XmlGenerator.class));
		bind(InvoiceCreatorFactory.class).to(InvoiceCreatorFactoryImpl.class);
		//bind(InvoiceCreatorFactory.class).toProvider(FactoryProvider.newFactory(InvoiceCreatorFactory.class, InvoiceCreator.class));
		//bind(InvoiceManagerInterface.class).toInstance((InvoiceManagerInterface)ModelUtil.getBean("fakturaDAO"));

		bind(PaperManager.class).toInstance((PaperManager) ModelUtil.getBean(PaperManager.MANAGER_NAME));
		bind(DepartmentDAO.class).toInstance((DepartmentDAO) ModelUtil.getBean(DepartmentDAO.DAO_NAME));	
		bind(LaasTypeDAO.class).toInstance((LaasTypeDAO) ModelUtil.getBean(LaasTypeDAO.DAO_NAME));
		bind(LaasDAO.class).toInstance((LaasDAO) ModelUtil.getBean(LaasDAO.DAO_NAME));
		bind(MvaDAO.class).toInstance((MvaDAO) ModelUtil.getBean(MvaDAO.DAO_NAME));
		bind(BuntDAO.class).toInstance((BuntDAO) ModelUtil.getBean(BuntDAO.DAO_NAME));
		bind(LfFakturaPosterDAO.class).toInstance((LfFakturaPosterDAO) ModelUtil.getBean("lfFakturaPosterDAO"));
		bind(LkKontraktobjekterDAO.class).toInstance((LkKontraktobjekterDAO) ModelUtil.getBean("lkKontraktobjekterDAO"));
		bind(FakturaTekstVManager.class).toInstance((FakturaTekstVManager) ModelUtil.getBean(FakturaTekstVManager.MANAGER_NAME));
		try {
			bindConstant().annotatedWith(Names.named("onaka_path")).to(ApplParamUtil.getStringParam("onaka_path"));
			bindConstant().annotatedWith(Names.named("base_data")).to(ApplParamUtil.getStringParam("base_data"));
			bindConstant().annotatedWith(Names.named("base_stores_file_name")).to(ApplParamUtil.getStringParam("base_stores_file_name"));
			bindConstant().annotatedWith(Names.named("excel_file_path")).to(ApplParamUtil.getStringParam("excel_file_path"));
			bindConstant().annotatedWith(Names.named("base_sales_file_name")).to(ApplParamUtil.getStringParam("base_sales_file_name"));
			bindConstant().annotatedWith(Names.named("imported_dir")).to(ApplParamUtil.getStringParam("imported_dir"));
		} catch (FrafException e) {
			e.printStackTrace();
			throw new FrafRuntimeException(e.getMessage());
		}
		bind(EGetableFactory.class).to(EGetableFactoryImpl.class).in(Singleton.class);
		bind(Locker.class).to(Locking.class);
		bind(ApplUserDAO.class).toInstance((ApplUserDAO) ModelUtil.getBean(ApplUserDAO.DAO_NAME));
		bind(PanelFrafMainFactory.class).toProvider(FactoryProvider.newFactory(PanelFrafMainFactory.class,PanelFrafMain.class));
		bind(KontraktTypeDAO.class).toInstance((KontraktTypeDAO) ModelUtil.getBean(KontraktTypeDAO.DAO_NAME));
		bind(AvregningTypeDAO.class).toInstance((AvregningTypeDAO) ModelUtil.getBean(AvregningTypeDAO.DAO_NAME));
		bind(AvregningBasisTypeDAO.class).toInstance((AvregningBasisTypeDAO) ModelUtil.getBean(AvregningBasisTypeDAO.DAO_NAME));
		bind(AvregningFrekvensTypeDAO.class).toInstance((AvregningFrekvensTypeDAO) ModelUtil.getBean(AvregningFrekvensTypeDAO.DAO_NAME));
		bind(BetingelseTypeDAO.class).toInstance((BetingelseTypeDAO) ModelUtil.getBean(BetingelseTypeDAO.DAO_NAME));
		bind(FornyelseTypeDAO.class).toInstance((FornyelseTypeDAO) ModelUtil.getBean(FornyelseTypeDAO.DAO_NAME));
		bind(KontraktBetingelseDAO.class).toInstance((KontraktBetingelseDAO) ModelUtil.getBean(KontraktBetingelseDAO.DAO_NAME));
		bind(ApplParamDAO.class).toInstance((ApplParamDAO) ModelUtil.getBean(ApplParamDAO.DAO_NAME));
		bind(SikkerhetTypeDAO.class).toInstance((SikkerhetTypeDAO) ModelUtil.getBean(SikkerhetTypeDAO.DAO_NAME));
		bind(BetingelseGruppeDAO.class).toInstance((BetingelseGruppeDAO) ModelUtil.getBean(BetingelseGruppeDAO.DAO_NAME));
		bind(ChainDAO.class).toInstance((ChainDAO) ModelUtil.getBean(ChainDAO.DAO_NAME));
		bind(KjedeDAO.class).toInstance((KjedeDAO) ModelUtil.getBean(KjedeDAO.DAO_NAME));
		bind(Login.class).to(LoginDialog.class);
		bind(AvdelingOmsetningDAO.class).toInstance((AvdelingOmsetningDAO) ModelUtil.getBean(AvdelingOmsetningDAO.DAO_NAME));
		bind(AvdelingOmsetningPkgDAO.class).toInstance((AvdelingOmsetningPkgDAO) ModelUtil.getBean(AvdelingOmsetningPkgDAO.DAO_NAME));
		bind(BuntPkgDAO.class).toInstance((BuntPkgDAO) ModelUtil.getBean(BuntPkgDAO.DAO_NAME));
		bind(SapSaleManager.class).toInstance((SapSaleManager) ModelUtil.getBean(SapSaleManager.MANAGER_NAME));
	}
	
	

}
