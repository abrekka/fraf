package no.ica.fraf.util;

import java.math.BigDecimal;

import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.dao.pkg.RegnskapPkgDAO;
import no.ica.fraf.service.impl.BaseManager;

/**
 * Hjelpeklasse for å hente ut DAO-klasser
 * 
 * @author abr99
 * 
 */
public class ModelUtil implements BeanFinder{// extends BaseDAOHibernate {
	/**
	 * Gjør om null-verdier til tom streng
	 * 
	 * @param object
	 * @return tom streng dersom null, ellers object.toString()
	 */
	public static String nullToString(Object object) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	/**
	 * Formaterer BigDecimal
	 * 
	 * @param number
	 * @return formatert BigDecimal
	 */
	public static BigDecimal formatBigDecimal(BigDecimal number) {
		if (number != null) {
			return number.setScale(5, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}

	/**
	 * Henter ut bean fra applicationContext basert på navn
	 * 
	 * @param beanName
	 * @return bean
	 */
	public static Object getBean(String beanName) {
			return BaseManager.getApplicationContext().getBean(beanName);
	}
	
	

	/**
	 * Initierer klasser som representerer databasepakker
	 * 
	 * @param isTest
	 */
	public static void initPkgBeans(boolean isTest) {
		AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO = (AvdelingOmsetningPkgDAO) getBean("avdelingOmsetningPkgDAO");
		avdelingOmsetningPkgDAO.setTest(isTest);

		BuntPkgDAO buntPkgDAO = (BuntPkgDAO) getBean("buntPkgDAO");
		buntPkgDAO.setTest(isTest);

		FranchisePkgDAO franchisePkgDAO = (FranchisePkgDAO) getBean("franchisePkgDAO");
		franchisePkgDAO.setTest(isTest);

		ImportBetingelsePkgDAO importBetingelsePkgDAO = (ImportBetingelsePkgDAO) getBean("importBetingelsePkgDAO");
		importBetingelsePkgDAO.setTest(isTest);

		RegnskapPkgDAO regnskapPkgDAO = (RegnskapPkgDAO) getBean("regnskapPkgDAO");
		regnskapPkgDAO.setTest(isTest);
	}

	public Object getBeanIstance(String beanName) {
		return getBean(beanName);
	}
}
