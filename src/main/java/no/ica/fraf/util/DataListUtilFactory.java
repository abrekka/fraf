package no.ica.fraf.util;

import java.lang.reflect.Constructor;

import no.ica.fraf.FrafRuntimeException;

public class DataListUtilFactory {
	public static final DataListUtil getInstance(BeanFinder beanFinder){
		//return SystemType.getSystemType(FrafMain.isStandalone()).isStandalone()?getDataListUtilStandalone(beanFinder):getDataListUtilIntegrated(beanFinder);
		return getDataListUtilImpl(beanFinder);
	}
	
	private static DataListUtil getDataListUtilImpl(BeanFinder beanFinder) {
		try {
			Constructor<DataListUtilImpl> constructor=DataListUtilImpl.class.getDeclaredConstructor(BeanFinder.class);
			constructor.setAccessible(true);
			DataListUtilImpl dataListUtil=constructor.newInstance(beanFinder);
			return dataListUtil;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafRuntimeException(e.getMessage());
		}
	}
	

	/*private static DataListUtil getDataListUtilStandalone(BeanFinder beanFinder) {
		try {
			Constructor<DataListUtilStandalone> constructor=DataListUtilStandalone.class.getDeclaredConstructor(BeanFinder.class);
			constructor.setAccessible(true);
			DataListUtilStandalone dataListUtil=constructor.newInstance(beanFinder);
			return dataListUtil;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafRuntimeException(e.getMessage());
		}
	}

	private static DataListUtil getDataListUtilIntegrated(BeanFinder beanFinder) {
		try {
			Constructor<DataListUtilIntegrated> constructor=DataListUtilIntegrated.class.getDeclaredConstructor(BeanFinder.class);
			constructor.setAccessible(true);
			DataListUtilIntegrated dataListUtil=constructor.newInstance(beanFinder);
			return dataListUtil;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafRuntimeException(e.getMessage());
		}
	}*/

}
