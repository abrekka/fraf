package no.ica.fraf.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import no.ica.fraf.FrafException;
import no.ica.fraf.FrafUncaughtHandler;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.modules.ModuleFactory;
import no.ica.fraf.service.SapXmlDataImporter;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.GuiUtil;
import oracle.jdbc.OracleDatabaseMetaData;

import org.apache.log4j.Logger;

import com.birosoft.liquid.LiquidLookAndFeel;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.internal.Nullable;
import com.google.inject.name.Named;

/**
 * Dette er hovedvinduet for systemet som starter opp systemet. Det er
 * main-metoden i denne klassen som er startpunktet for systemet. Alle dialoger
 * blir lagt til dette vinduet.
 * 
 * @author atb
 * 
 */
public class FrafMain extends javax.swing.JFrame implements
		InternalFrameListener {

	/**
	 * Systemversjon
	 */
	private static String version = null;

	/**
	 * Forteller om det kjøres mot testdatabase
	 */
	public static boolean isTest = false;

	/**
	 * Inneholder info fra config-fil
	 */
	private static String configInfo = null;

	/**
	 * Dialog som vises ved oppstart
	 */
	private static LoadFrame loadFrame = null;
	private static boolean isStandalone;

	// Laster inn informasjon fra properties-fil
	static {
		ResourceBundle configuration = ResourceBundle.getBundle("application");

		version = configuration.getString("version");
		configInfo = configuration.getString("info");
		isTest = Boolean.valueOf(configuration.getString("test"))
				.booleanValue();
		isStandalone = Boolean.valueOf(configuration.getString("standalone"))
				.booleanValue();

		if (isTest) {
			BaseManager.setTest(isTest);
		}
		
//		URL dllUrl=FrafMain.class.getClassLoader().getSystemResource("jdic.dll");
//		if(dllUrl!=null){
//			System.loadLibrary("jdic");
//		}
	}

	static {
		try {

			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			LiquidLookAndFeel.setLiquidDecorations(true, "mac");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3761411897798243634L;

	/**
	 * Gjeldende bruker
	 */
	// private ApplUser currentApplUser = null;
	private Login login;

	/**
	 * Inneholder menytekster for vinduer som er åpne
	 */
	private Vector<String> windowMenuTexts = new Vector<String>();

	/**
	 * Inneholder alle åpne vinduer
	 */
	private Vector<JInternalFrame> windows = new Vector<JInternalFrame>();

	/**
	 * Inneholder menyer for åpne dialoger
	 */
	private Vector<JMenuItem> windowMenus = new Vector<JMenuItem>();

	/**
	 * 
	 */
	private JMenu currentMenuWindow;

	/**
	 * Menybar
	 */
	private FrafMainMenuBarInterface frafMainMenuBar;

	/**
	 * This-peker til bruk i innerklasser
	 */
	private static FrafMain thisPointer;

	/**
	 * Hovedpanel
	 */
	private PanelFrafMainFactory panelFrafMainFactory;
	private PanelFrafMainInterface panelFrafMain;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(FrafMain.class);
	private static String infoString;
	private SapXmlDataImporter storeInfoImporter;
	private SapXmlDataImporter saleImporter;

	/**
	 * Dette er oppstartsmetoden for systemet. Det angis ingen argumenter
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		infoString = "";

		if (isTest) {
			infoString += " test";
		}

		infoString += isStandalone() ? " Standalone" : "";
		loadFrame = new LoadFrame(null, version + infoString, configInfo);
		try {
			System.out.println();
			System.out.println(OracleDatabaseMetaData.getDriverNameInfo() + " "
					+ OracleDatabaseMetaData.getDriverVersionInfo());
			System.out.println("The JDK version is "
					+ System.getProperty("java.version"));
			System.out.println("============================\n");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		FrafMain inst = null;
		boolean started = false;
		try {
			while (!started) {
				Injector injector = Guice.createInjector(ModuleFactory
						.getModules());
				inst = injector.getInstance(FrafMain.class);
				// inst = new FrafMain();
				if (inst != null) {
					started = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			GuiUtil.showErrorMsgDialog(null, "Feil!", e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Konstruktør
	 * 
	 */
	@Inject
	public FrafMain(
			@Nullable @Named("storeinfo") final SapXmlDataImporter aStoreInfoImporter,
			final FrafMainMenuBarInterface aFrafMainMenuBar,
			final PanelFrafMainFactory aPanelFrafMainFactory,
			final Login aLogin,
			@Nullable @Named("sale") final SapXmlDataImporter aSaleImporter) {
		super();
		login = aLogin;
		storeInfoImporter = aStoreInfoImporter;
		saleImporter = aSaleImporter;
		frafMainMenuBar = aFrafMainMenuBar;
		panelFrafMainFactory = aPanelFrafMainFactory;
		Thread.setDefaultUncaughtExceptionHandler(new FrafUncaughtHandler());
		try {

			thisPointer = this;

			logger.info("Starter system");
			setIconImage(IconEnum.ICON_FRAF_BIG.getIcon().getImage());

			if (loadFrame.systemStopped()) {
				frafMainMenuBar.systemExit();
			}

			startSystem(true);

			if (loadFrame.systemStopped()) {
				frafMainMenuBar.systemExit();
			}

			loadFrame.dispose();
			loadFrame = null;
			setVisible(true);
			checkSapFiles(login.getApplUser());
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			System.exit(0);
		} catch (FrafException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	private void checkSapFiles(ApplUser applUser) {
		try {
			if (storeInfoImporter != null
					&& applUser.getApplUserType().getTypeName()
							.equalsIgnoreCase("Administrator")
					&& storeInfoImporter.checkFiles()) {
				boolean doImport = GuiUtil
						.showConfirmDialog(
								"Det har kommet ny avdelingsinfo, vil du laste den inn nå?",
								"Avdelingsinfo");
				if (doImport) {
					importStoreInfo();
				}
			}

		} catch (FrafException e) {
			GuiUtil.showErrorMsgDialog(this, "Feil", e.getMessage());
			e.printStackTrace();
		}

	}

	private void importStoreInfo() {
		GuiUtil.runInThreadWheel(this.getRootPane(), new Threadable() {

			public void enableComponents(boolean enable) {
			}

			public Object doWork(Object[] params, JLabel labelInfo) {
				String errorMsg = null;
				try {
					labelInfo.setText("Laster avdelingsinfo...");
					storeInfoImporter.importXmlData();
				} catch (FrafException e) {
					e.printStackTrace();
					errorMsg = e.getMessage();
				}
				return errorMsg;
			}

			public void doWhenFinished(Object object) {
				if (object != null) {
					GuiUtil.showErrorMsgDialog(loadFrame, "Feil", object
							.toString());
				} else {
					GuiUtil.showMsgDialog(null, "Avdelingsinfo",
							"Ny avdelingsinfo er lastet");
				}

			}
		}, null);
	}

	private void importSale() {
		GuiUtil.runInThreadWheel(this.getRootPane(), new Threadable() {

			public void enableComponents(boolean enable) {
			}

			public Object doWork(Object[] params, JLabel labelInfo) {
				String errorMsg = null;
				try {
					labelInfo.setText("Laster salg...");
					saleImporter.importXmlData();
				} catch (FrafException e) {
					e.printStackTrace();
					errorMsg = e.getMessage();
				}
				return errorMsg;
			}

			public void doWhenFinished(Object object) {
				if (object != null) {
					GuiUtil.showErrorMsgDialog(loadFrame, "Feil", object
							.toString());
				} else {
					GuiUtil.showMsgDialog(null, "Salg", "Nye salg er lastet");
				}

			}
		}, null);
	}

	/**
	 * Singleton. Gir peker til hovedvindu
	 * 
	 * @return hovedvindu
	 */
	public static FrafMain getInstance() {
		return thisPointer;
	}

	/**
	 * Initierer brukergrensnittet
	 * 
	 */
	private void initGUI() {

		try {
			{

				this.setTitle("FRAF" + infoString);
				this.setExtendedState(Frame.MAXIMIZED_BOTH);
				this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				this.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent evt) {
						rootWindowClosing(evt);
					}
				});

			}
			this.setSize(988, 599);

			setJMenuBar(frafMainMenuBar.getMenuBar());

			panelFrafMain = panelFrafMainFactory.create(login.getApplUser());

			currentMenuWindow = frafMainMenuBar.getMenuWindow();
			getContentPane().add(panelFrafMain.getComponent());

			this.setMaximizedBounds(GraphicsEnvironment
					.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see javax.swing.event.InternalFrameListener#internalFrameActivated(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameActivated(InternalFrameEvent arg0) {
	}

	/**
	 * Blir kjørt når an av de åpne dialogen blir lukket. Fjerner menyvalg fra
	 * meny, og fjerner referanser til dialog
	 * 
	 * @see javax.swing.event.InternalFrameListener#internalFrameClosed(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameClosed(InternalFrameEvent internalEvent) {
		Object source = internalEvent.getSource();

		if (source instanceof JInternalFrame) {
			JInternalFrame iFrame = (JInternalFrame) source;
			String title = iFrame.getTitle();
			int index = windowMenuTexts.indexOf(title);

			if (index != -1) {
				panelFrafMain.removeFrame(iFrame);
				JMenuItem menuItem = windowMenus.remove(index);
				currentMenuWindow.remove(menuItem);
				windowMenuTexts.remove(index);
				windows.remove(index);
			}

		}

	}

	/**
	 * @see javax.swing.event.InternalFrameListener#internalFrameClosing(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameClosing(InternalFrameEvent arg0) {
	}

	/**
	 * @see javax.swing.event.InternalFrameListener#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
	}

	/**
	 * @see javax.swing.event.InternalFrameListener#internalFrameDeiconified(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
	}

	/**
	 * @see javax.swing.event.InternalFrameListener#internalFrameIconified(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameIconified(InternalFrameEvent arg0) {
	}

	/**
	 * @see javax.swing.event.InternalFrameListener#internalFrameOpened(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameOpened(InternalFrameEvent arg0) {
	}

	/**
	 * Initierer meny. Disabler admin-valg dersom bruker ikke er administrator
	 * 
	 * @param applUser
	 *            gjeldende bruker
	 */
	private void initMenus(ApplUser applUser) {
		frafMainMenuBar.initMenus(applUser);
		panelFrafMain.init(applUser);
	}

	/**
	 * Starter systemet på nytt. Brukes dersom menyvalget logg på som annen
	 * bruker blir valgt
	 * 
	 * @param useSystemUser
	 *            true dersom bruker innlogget på maskin skal brukes
	 * @return bruker
	 * @throws FrafException
	 */
	public void startSystem(boolean useSystemUser) throws FrafException {
		if (panelFrafMain != null) {
			panelFrafMain.closeAllFrames();
		}

		if (login.getApplUser() != null) {

			frafMainMenuBar.setCurrentFrafUser(login.getApplUser());
			frafMainMenuBar.createMenus();

			initGUI();

			initMenus(login.getApplUser());

		} else if (useSystemUser) {
			frafMainMenuBar.systemExit();
		}
	}

	/**
	 * Blir kjørt når hovedvinduet blir avsluttet. Avslutter systemet
	 * 
	 * @param evt
	 *            hendelse brukes ikke til noe
	 */
	void rootWindowClosing(WindowEvent evt) {
		frafMainMenuBar.systemExit();
	}

	/**
	 * Lager og viser dialog med info om systemet. Versjon hentes fra egen
	 * ressursfil
	 * 
	 */
	public void showInfo() {
		String test = "";
		if (isTest) {
			test = " test";
		}
		GuiUtil.showMsgFrame(getContentPane(), "FRAF", "Versjon " + version
				+ test);
	}

	/**
	 * Legger til intern dialog
	 * 
	 * @param iFrame
	 */
	public void addInternalFrame(JInternalFrame iFrame) {
		addInternalFrame(iFrame, true);
	}

	/**
	 * Legger til dialog slik at den kan aksesseres fra menyvalg
	 * 
	 * @param iFrame
	 *            dialog som skal legges til
	 * @param addOnMenu
	 */
	public void addInternalFrame(JInternalFrame iFrame, boolean addOnMenu) {
		panelFrafMain.addFrame(iFrame);
		if (addOnMenu) {
			JMenuItem menuItemEstateFrame = new JMenuItem(iFrame.getTitle());
			windowMenuTexts.add(iFrame.getTitle());
			windows.add(iFrame);
			windowMenus.add(menuItemEstateFrame);
			menuItemEstateFrame.addActionListener(frafMainMenuBar
					.getMenuListener());
			currentMenuWindow.add(menuItemEstateFrame);

			iFrame.addInternalFrameListener(this);
		}
	}

	/**
	 * Henter ut om database er test
	 * 
	 * @return true dersom test
	 */
	public boolean isTest() {
		return isTest;
	}

	public static boolean isStandalone() {
		return isStandalone;
	}

	/**
	 * Håndterer menyvalg fra vindu der det velges et aktivt vindu
	 * 
	 * @param actionCommand
	 */
	public void handleDefaultMenuAction(String actionCommand) {
		int index = windowMenuTexts.indexOf(actionCommand);

		if (index != -1) {
			try {
				windows.get(index).setSelected(true);
			} catch (PropertyVetoException e) {
				logger.error("Feil i MenuItemListener actionPerformed", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Henter ut desktoppane (hovedpanel)
	 * 
	 * @return desktoppane (hovedpanel)
	 */
	public Container getDesktopPane() {
		return panelFrafMain.getDesktopPane();
	}

	/**
	 * Henter ut alle aktive dialoger
	 * 
	 * @return ut alle aktive dialoger
	 */
	public JInternalFrame[] getAllFrames() {
		return panelFrafMain.getAllFrames();
	}

	/**
	 * @return Returns the version.
	 */
	public static String getVersion() {
		return version;
	}

	public static void setStandalone(boolean standalone) {
		isStandalone = standalone;
	}
}
