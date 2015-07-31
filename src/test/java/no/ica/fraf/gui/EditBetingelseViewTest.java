package no.ica.fraf.gui;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JDialog;

import junit.extensions.abbot.ComponentTestFixture;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.gui.edit.EditBetingelseTypeView;
import no.ica.fraf.gui.handlers.BetingelseGruppeEnum;
import no.ica.fraf.gui.handlers.BetingelseViewHandler;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

import org.fest.swing.core.RobotFixture;
import org.fest.swing.fixture.DialogFixture;

public class EditBetingelseViewTest extends ComponentTestFixture{
	static {
		BaseManager.setTest(true);
	}
	
	private RobotFixture robot;
	private DialogFixture dialogFixture;
	private BetingelseTypeDAO betingelseTypeDAO;
	@Override
	protected void setUp() throws Exception {
		robot=RobotFixture.robotWithNewAwtHierarchy();
		ApplUserDAO applUserDAO=(ApplUserDAO)ModelUtil.getBean("applUserDAO");
		ApplUser applUser = applUserDAO.findByUser("abr99",null);
		betingelseTypeDAO =(BetingelseTypeDAO)ModelUtil.getBean("betingelseTypeDAO"); 
		BetingelseViewHandler betingelseViewHandler=new BetingelseViewHandler(betingelseTypeDAO,applUser.getApplUserType(),BetingelseGruppeEnum.ADMIN);
		EditBetingelseTypeView view = new EditBetingelseTypeView(false,new BetingelseType(),betingelseViewHandler);
		
		JDialog dialog = new JDialog();
		WindowInterface window=new JDialogAdapter(dialog);
		dialog.add(view.buildPanel(window));
		dialogFixture = new DialogFixture(robot,dialog);
	}
	@Override
	protected void tearDown() throws Exception {
		robot.cleanUp();
		
		BetingelseType type=betingelseTypeDAO.findByKode("TEST");
		if(type!=null&&type.getBetingelseTypeId()!=null){
			betingelseTypeDAO.removeBetingelseType(type.getBetingelseTypeId());
		}
	}
	
	public void testShow(){
		dialogFixture.show();
		dialogFixture.requireVisible();
	}
	
	public void testInsertNew(){
		dialogFixture.show();
		
		dialogFixture.textBox("TextFieldKode").enterText("TEST");
		dialogFixture.comboBox("ComboBoxBetingelseGruppe").selectItem("Diverse");
		dialogFixture.comboBox("ComboBoxMvatypeE").selectItem("IN");
		dialogFixture.textBox("TextFieldVatusetypeE").enterText("R100");
		dialogFixture.textBox("TextFieldSats").enterText("2");
		
		dialogFixture.comboBox("ComboBoxLinkAvtale").selectItem("Ja");
		dialogFixture.button("SaveBetingelseType").click();
		
		BetingelseType type=betingelseTypeDAO.findByKode("TEST");
		assertNotNull(type);
		assertEquals("Diverse",type.getBetingelseGruppe().getBetingelseGruppeNavn());
		assertEquals("IN",type.getXmlMvatypeE());
		assertEquals("R100",type.getXmlVatusetypeE());
		assertEquals(Integer.valueOf(1),type.getLinkAvtale());
		
	}
	
	public void testInsertNewAllFields(){
		dialogFixture.show();
		
		dialogFixture.textBox("TextFieldKode").enterText("TEST");
		dialogFixture.textBox("TextFieldNavn").enterText("TESTNAVN");
		dialogFixture.comboBox("ComboBoxMvaKodeE").selectItem("03");
		dialogFixture.comboBox("ComboBoxMvaKodeF").selectItem("00");
		dialogFixture.comboBox("ComboBoxBetingelseGruppe").selectItem("Diverse");
		dialogFixture.textBox("TextFieldInntektskontoE").enterText("100");
		dialogFixture.textBox("TextFieldInntektskontoF").enterText("200");
		dialogFixture.textBox("TextFieldBokfAvdelingE").enterText("AvdelingE");
		dialogFixture.textBox("TextFieldBokfAvdelingF").enterText("AvdelingF");
		dialogFixture.textBox("TextFieldXmlKontoE").enterText("300");
		dialogFixture.textBox("TextFieldXmlKontoF").enterText("400");
		dialogFixture.comboBox("ComboBoxXmlMvaKodeE").selectItem("01");
		dialogFixture.comboBox("ComboBoxXmlMvaKodeF").selectItem("13");
		dialogFixture.comboBox("ComboBoxSelskap").selectItem("100");
		
		dialogFixture.comboBox("ComboBoxTermin").selectItem("Måned");
		dialogFixture.comboBox("ComboBoxAvregning").selectItem("Etterskuddsvis");
		dialogFixture.textBox("TextFieldFakturaTekst").enterText("Fakturatekst");
		dialogFixture.textBox("TextFieldRekkefolge").enterText("1");
		dialogFixture.textBox("TextFieldSats").enterText("2");
		dialogFixture.comboBox("ComboBoxMvatypeE").selectItem("IN");
		dialogFixture.textBox("TextFieldVatusetypeE").enterText("R100");
		dialogFixture.comboBox("ComboBoxMvatypeF").selectItem("UT");
		dialogFixture.textBox("TextFieldVatusetypeF").enterText("R200");
		
		dialogFixture.comboBox("ComboBoxLinkAvtale").selectItem("Ja");
		dialogFixture.button("SaveBetingelseType").click();
		
		BetingelseType type=betingelseTypeDAO.findByKode("TEST");
		assertNotNull(type);
		assertEquals("TESTNAVN",type.getBetingelseNavn());
		assertEquals("03",type.getMvaE().getMvaKode());
		assertEquals("00",type.getMvaF().getMvaKode());
		assertEquals("Diverse",type.getBetingelseGruppe().getBetingelseGruppeNavn());
		assertEquals("100",type.getInntektskontoE());
		assertEquals("200",type.getInntektskontoF());
		assertEquals("AvdelingE",type.getBokfAvdelingE());
		assertEquals("AvdelingF",type.getBokfAvdelingF());
		assertEquals("300",type.getXmlKontoE());
		assertEquals("400",type.getXmlKontoF());
		assertEquals("01",type.getXmlMvaE().getMvaKode());
		assertEquals("13",type.getXmlMvaF().getMvaKode());
		assertEquals("100",type.getBokfSelskap().getSelskapNavn());
		
		assertEquals("Måned",type.getAvregningFrekvensType().getAvregningFrekvensTypeTxt());
		assertEquals("Etterskuddsvis",type.getAvregningType().getAvregningTypeTxt());
		assertEquals("Fakturatekst",type.getFakturaTekst());
		assertEquals(Integer.valueOf(1),type.getFakturaTekstRek());
		assertEquals(BigDecimal.valueOf(2),type.getSats());
		assertEquals("IN",type.getXmlMvatypeE());
		assertEquals("UT",type.getXmlMvatypeF());
		assertEquals("R100",type.getXmlVatusetypeE());
		assertEquals("R200",type.getXmlVatusetypeF());
		assertEquals(Integer.valueOf(1),type.getLinkAvtale());
		
	}

}
