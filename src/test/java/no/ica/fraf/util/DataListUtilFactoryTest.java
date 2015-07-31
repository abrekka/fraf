package no.ica.fraf.util;

import no.ica.fraf.dao.AvdelingStatusDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.FornyelseTypeDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.dao.SapChainDAO;
import no.ica.fraf.dao.SapDistrictManagerDAO;
import no.ica.fraf.dao.SapRegionDAO;
import no.ica.fraf.dao.view.Rik2DistriktssjeferVDAO;
import no.ica.fraf.dao.view.Rik2KjedeVDAO;
import no.ica.fraf.dao.view.Rik2RegionVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.service.impl.BaseManager;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class DataListUtilFactoryTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}

	public void testGetInstanceIntegratedWithDaoInConstructor() {
		FrafMain.setStandalone(false);
		final BeanFinder aBeanFinder = mock(BeanFinder.class);
		final Rik2KjedeVDAO rik2KjedeVDAO = mock(Rik2KjedeVDAO.class);
		final Rik2RegionVDAO rik2RegionVDAO = mock(Rik2RegionVDAO.class);
		final BokfSelskapDAO bokfSelskapDAO = mock(BokfSelskapDAO.class);
		final AvdelingStatusDAO avdelingStatusDAO = mock(AvdelingStatusDAO.class);
		final KontraktTypeDAO kontraktTypeDAO = mock(KontraktTypeDAO.class);
		final Rik2DistriktssjeferVDAO rik2DistriktssjeferVDAO = mock(Rik2DistriktssjeferVDAO.class);
		final FornyelseTypeDAO fornyelseTypeDAO = mock(FornyelseTypeDAO.class);

		checking(new Expectations() {
			{

				oneOf(aBeanFinder).getBeanIstance("districtManagerDAO");
				will(returnValue(rik2DistriktssjeferVDAO));
				oneOf(aBeanFinder).getBeanIstance("chainDAO");
				will(returnValue(rik2KjedeVDAO));
				oneOf(aBeanFinder).getBeanIstance("regionDAO");
				will(returnValue(rik2RegionVDAO));
				oneOf(aBeanFinder).getBeanIstance("bokfSelskapDAO");
				will(returnValue(bokfSelskapDAO));
				oneOf(aBeanFinder).getBeanIstance("avdelingStatusDAO");
				will(returnValue(avdelingStatusDAO));
				oneOf(aBeanFinder).getBeanIstance("kontraktTypeDAO");
				will(returnValue(kontraktTypeDAO));
				oneOf(aBeanFinder).getBeanIstance("fornyelseTypeDAO");
				will(returnValue(fornyelseTypeDAO));
			}
		});

		DataListUtil dataListUtil = DataListUtilFactory
				.getInstance(aBeanFinder);
		assertNotNull(dataListUtil);
	}

	public void testGetInstanceStandaloneWithDaoInConstructor() {
		FrafMain.setStandalone(true);
		final BeanFinder aBeanFinder = mock(BeanFinder.class);
		final BokfSelskapDAO bokfSelskapDAO = mock(BokfSelskapDAO.class);
		final SapDistrictManagerDAO sapDistrictManagerDAO = mock(SapDistrictManagerDAO.class);
		final FornyelseTypeDAO fornyelseTypeDAO = mock(FornyelseTypeDAO.class);
		final SapChainDAO sapChainDAO = mock(SapChainDAO.class);
		final KontraktTypeDAO kontraktTypeDAO = mock(KontraktTypeDAO.class);
		final SapRegionDAO sapRegionDAO = mock(SapRegionDAO.class);
		final AvdelingStatusDAO avdelingStatusDAO = mock(AvdelingStatusDAO.class);

		checking(new Expectations() {
			{
				oneOf(aBeanFinder).getBeanIstance("bokfSelskapDAO");
				will(returnValue(bokfSelskapDAO));
				oneOf(aBeanFinder).getBeanIstance("districtManagerDAO");
				will(returnValue(sapDistrictManagerDAO));
				oneOf(aBeanFinder).getBeanIstance("fornyelseTypeDAO");
				will(returnValue(fornyelseTypeDAO));
				oneOf(aBeanFinder).getBeanIstance("chainDAO");
				will(returnValue(sapChainDAO));
				oneOf(aBeanFinder).getBeanIstance("kontraktTypeDAO");
				will(returnValue(kontraktTypeDAO));
				oneOf(aBeanFinder).getBeanIstance("regionDAO");
				will(returnValue(sapRegionDAO));
				oneOf(aBeanFinder).getBeanIstance("avdelingStatusDAO");
				will(returnValue(avdelingStatusDAO));
			}
		});

		DataListUtil dataListUtil = DataListUtilFactory
				.getInstance(aBeanFinder);
		assertNotNull(dataListUtil);
	}
}
