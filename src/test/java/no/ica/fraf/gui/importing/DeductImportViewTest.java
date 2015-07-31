package no.ica.fraf.gui.importing;

import javax.swing.JDialog;

import junit.extensions.abbot.ComponentTestFixture;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.gui.DeductView;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.handlers.DeductViewHandler;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.service.AvdelingAvregningBelopManager;
import no.ica.fraf.service.AvdelingAvregningImportManager;
import no.ica.fraf.service.AvdelingAvregningManager;
import no.ica.fraf.service.TotalAvregningVManager;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.fest.swing.core.RobotFixture;
import org.fest.swing.finder.JFileChooserFinder;
import org.fest.swing.finder.JOptionPaneFinder;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.JFileChooserFixture;
import org.fest.swing.fixture.JOptionPaneFixture;

public class DeductImportViewTest extends ComponentTestFixture{
	static {
		BaseManager.setTest(true);
	}
	
	private RobotFixture robot;
	private DialogFixture dialogFixture;
	@Override
	protected void setUp() throws Exception {
		robot=RobotFixture.robotWithNewAwtHierarchy();
		
		BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		AvdelingAvregningBelopManager avdelingAvregningBelopManager = (AvdelingAvregningBelopManager) ModelUtil
				.getBean("avdelingAvregningBelopManager");
		AvdelingAvregningManager avdelingAvregningManager = (AvdelingAvregningManager) ModelUtil
				.getBean("avdelingAvregningManager");
		FakturaDAO fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
		BetingelseGruppeDAO betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
				.getBean("betingelseGruppeDAO");
		BuntPkgDAO buntPkgDAO = (BuntPkgDAO) ModelUtil.getBean("buntPkgDAO");
		TotalAvregningVManager totalAvregningVManager = (TotalAvregningVManager) ModelUtil
				.getBean("totalAvregningVManager");
		//FakturaVDAO fakturaVDAO = (FakturaVDAO) ModelUtil.getBean("fakturaVDAO");
		ImportBetingelsePkgDAO importBetingelsePkgDAO = (ImportBetingelsePkgDAO) ModelUtil
				.getBean("importBetingelsePkgDAO");

		AvdelingAvregningImportManager avdelingAvregningImportManager = (AvdelingAvregningImportManager) ModelUtil
				.getBean("avdelingAvregningImportManager");
		//EflowUsersVManager eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");
		
		String excelDir = null;
		try {
			excelDir = ApplParamUtil.getStringParam("excel_file_path");
		} catch (FrafException e) {
			e.printStackTrace();
			GuiUtil.showErrorMsgDialog(FrafMain.getInstance().getRootPane(),
					"Feil", e.getMessage());
		}
		
		ApplUserDAO applUserDAO=(ApplUserDAO)ModelUtil.getBean("applUserDAO");
		ApplUser applUser = applUserDAO.findByUser("abr99",null);

		
		DeductViewHandler deductViewHandler = new DeductViewHandler(buntDAO,
				applUser, avdelingAvregningBelopManager,
				avdelingAvregningManager, fakturaDAO, betingelseGruppeDAO,
				buntPkgDAO, excelDir, totalAvregningVManager, 
				//fakturaVDAO,
				importBetingelsePkgDAO, avdelingAvregningImportManager);
		DeductView deductView = new DeductView(deductViewHandler);
		
		JDialog dialog = new JDialog();
		WindowInterface window=new JDialogAdapter(dialog);
		dialog.add(deductView.buildPanel(window));
		dialogFixture = new DialogFixture(robot,dialog);
	}
	@Override
	protected void tearDown() throws Exception {
		robot.cleanUp();
		
		AvdelingAvregningImportManager avdelingAvregningImportManager=(AvdelingAvregningImportManager)ModelUtil.getBean("avdelingAvregningImportManager");
		AvdelingAvregningImport avdelingAvregningImport=avdelingAvregningImportManager.findByAvdnrAndYear(9999,2007);
		if(avdelingAvregningImport!=null){
			avdelingAvregningImportManager.removeAvdelingAvregningImport(avdelingAvregningImport);
		}
		super.tearDown();
	}
	
	public void testShow(){
		dialogFixture.show();
		dialogFixture.requireVisible();
	}
	
	public void testImportTwice()throws Exception{
		dialogFixture.show();
		
		dialogFixture.button("ButtonSelectFile").click();
		
		JFileChooserFixture fileChooser = JFileChooserFinder.findFileChooser().using(robot);
		fileChooser.fileNameTextBox().enterText("Avregning1495.xls");
		fileChooser.approveButton().click();
		
		
		dialogFixture.button("ButtonImport").click();
		
		JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().using(robot);
		optionPane.textBox().enterText("2007");
		optionPane.buttonWithText("OK").click();
		
		
		optionPane = JOptionPaneFinder.findOptionPane().using(robot);
	}
	
	
}
