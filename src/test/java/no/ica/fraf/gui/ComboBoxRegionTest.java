package no.ica.fraf.gui;

import javax.swing.JDialog;

import junit.extensions.abbot.ComponentTestFixture;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.service.impl.BaseManager;

import org.fest.swing.core.RobotFixture;
import org.fest.swing.fixture.DialogFixture;

public class ComboBoxRegionTest extends ComponentTestFixture{
	static {
		BaseManager.setTest(true);
	}

	private RobotFixture robot;
	private DialogFixture dialogFixture;
	
	@Override
	protected void setUp() throws Exception {
		robot=RobotFixture.robotWithNewAwtHierarchy();
		
		ComboBoxViewTest view = new ComboBoxViewTest();
		
		JDialog dialog = new JDialog();
		WindowInterface window=new JDialogAdapter(dialog);
		dialog.add(view.buildPanel(window));
		dialogFixture = new DialogFixture(robot,dialog);
	}
	@Override
	protected void tearDown() throws Exception {
		robot.cleanUp();
		
	}
	public void testShow(){
		dialogFixture.show();
		dialogFixture.requireVisible();
	}
}
