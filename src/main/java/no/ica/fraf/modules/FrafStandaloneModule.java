package no.ica.fraf.modules;

import no.ica.fraf.service.SapChainManager;
import no.ica.fraf.service.SapDepartmentManager;
import no.ica.fraf.service.SapDistrictManagerManager;
import no.ica.fraf.service.SapRegionManager;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.util.ModelUtil;

import com.google.inject.AbstractModule;

public class FrafStandaloneModule extends AbstractModule{
	private FrafStandaloneModule(){
		
	}
	
	@Override
	protected void configure() {
		bind(SapChainManager.class).toInstance((SapChainManager) ModelUtil.getBean(SapChainManager.MANAGER_NAME));
		bind(SapDepartmentManager.class).toInstance((SapDepartmentManager) ModelUtil.getBean(SapDepartmentManager.MANAGER_NAME));
		bind(SapRegionManager.class).toInstance((SapRegionManager) ModelUtil.getBean(SapRegionManager.MANAGER_NAME));
		bind(SapDistrictManagerManager.class).toInstance((SapDistrictManagerManager) ModelUtil.getBean(SapDistrictManagerManager.MANAGER_NAME));
	}


}
