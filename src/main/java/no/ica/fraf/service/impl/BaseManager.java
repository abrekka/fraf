package no.ica.fraf.service.impl;

import no.ica.fraf.gui.FrafMain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 * <p>
 * <a href="BaseManager.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseManager {//implements Manager {
	/**
	 * 
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 
	 */
	//protected DAO dao = null;

	/**
	 * 
	 */
	private static boolean test = false;

	/**
	 * 
	 */
	private static String contextPath = "classpath*:/prod/applicationContext*.xml";

	/**
	 * 
	 */
	private static String contextPathTest = "classpath*:/test/applicationContext*.xml";

	/**
	 * 
	 */
	protected static ApplicationContext applicationContext = null;
	private static boolean currentLoadedContextStandalone=false;

	/**
	 * Initierer klasse
	 */
	private static void init() {
		applicationContext=FrafMain.isStandalone()!=currentLoadedContextStandalone?null:applicationContext;
		if (applicationContext != null) {
			return;
		}
		String[] paths;

		/*if (test) {
			paths = new String[] { contextPathTest };
		} else {
			paths = new String[] { contextPath };
		}*/
		paths = new String[] { getContextPath() };
		applicationContext = new ClassPathXmlApplicationContext(paths);
	}
	
	private static String getContextPath(){
		currentLoadedContextStandalone=FrafMain.isStandalone();
		String contextPath = "classpath*:/";
		contextPath+=test?"test/":"prod/";
		contextPath+=FrafMain.isStandalone()?"standalone/":"integrated/";
		contextPath+="applicationContext*.xml";
		return contextPath;
	}

	/**
	 * @see no.ica.fraf.service.Manager#setDAO(no.ica.fraf.dao.DAO)
	 */
	/*public void setDAO(DAO dao) {
		this.dao = dao;
	}*/

	/**
	 * @see no.ica.fraf.service.Manager#getObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	/*public Object getObject(Class clazz, Serializable id) {
		return dao.getObject(clazz, id);
	}*/

	/**
	 * @see no.ica.fraf.service.Manager#getObjects(java.lang.Class)
	 */
	/*public List getObjects(Class clazz) {
		return dao.getObjects(clazz);
	}*/

	/**
	 * @see no.ica.fraf.service.Manager#removeObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	/*public void removeObject(Class clazz, Serializable id) {
		dao.removeObject(clazz, id);
	}*/

	/**
	 * @see no.ica.fraf.service.Manager#saveObject(java.lang.Object)
	 */
	/*public void saveObject(Object o) {
		dao.saveObject(o);
	}*/

	/**
	 * @return context
	 */
	public static ApplicationContext getApplicationContext() {
		init();
		return applicationContext;
	}

	/**
	 * @return Returns the test.
	 */
	public static boolean isTest() {
		return test;
	}

	/**
	 * @param test
	 *            The test to set.
	 */
	public static void setTest(boolean test) {
		BaseManager.test = test;
	}

}
