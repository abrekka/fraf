package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import no.ica.fraf.enums.FrafActionEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.ModelUtil;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Hovedpanelet til systemet. Det er dett panelet som alle andre knyttes opp til
 * (DesktopPane). Dette panelet innehodler også toolbar for systemet
 * 
 * @author abr99
 * 
 */
public class PanelFrafMain extends javax.swing.JPanel implements PanelFrafMainInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JDesktopPane desktopPane;

	/**
	 * Klasse som håndterer menyvalg
	 */
	private FrafMainFrameHandlerInterface frafMainFrameHandler;

	/**
	 * 
	 */
	private JToolBar toolBar;

	/**
	 * Konstruktør
	 * 
	 * @param applUser
	 */
	@Inject
	public PanelFrafMain(final FrafMainFrameHandlerInterface aFrafMainFrameHandlerInterface,@Assisted final ApplUser applUser) {
		super();
		frafMainFrameHandler=aFrafMainFrameHandlerInterface;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.gui.PanelFrafMainInterface#init(no.ica.fraf.model.ApplUser)
	 */
	public void init(ApplUser applUser) {
		//frafMainFrameHandler = new FrafMainFrameHandler(applUser);
		//frafMainFrameHandler = (FrafMainFrameHandlerInterface)ModelUtil.getBean("frafMainFrameHandler");
		initGUI();
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		try {

			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				desktopPane = new JDesktopPane();
				this.add(desktopPane, BorderLayout.CENTER);

				if (FrafMain.isTest) {
					{
						JLabel labelTest = new JLabel();
						desktopPane.add(labelTest);
						labelTest.setText("TEST");
						labelTest.setBounds(8, 6, 544, 212);
						labelTest.setFont(new java.awt.Font("Dialog", 1, 200));
					}
					{
						JLabel jLabel1 = new JLabel();
						desktopPane.add(jLabel1);
						jLabel1.setText("TEST");
						jLabel1.setBounds(401, 401, 576, 166);
						jLabel1.setFont(new java.awt.Font("Dialog", 1, 200));
					}
				}
			}
			{
				toolBar = new JToolBar();
				this.add(toolBar, BorderLayout.NORTH);
				toolBar.setPreferredSize(new java.awt.Dimension(18, 25));
				toolBar.setSize(400, 25);
				{
					JButton buttonExit = new JButton();
					buttonExit.setToolTipText("Avslutter system");
					toolBar.add(buttonExit);
					buttonExit.setActionCommand(FrafActionEnum.EXIT
							.getActionString());
					buttonExit.addActionListener(frafMainFrameHandler);
					buttonExit.setIcon(IconEnum.ICON_EXIT.getIcon());
					buttonExit.setSize(21, 21);
					buttonExit.setPreferredSize(new java.awt.Dimension(21, 21));
					buttonExit.setMaximumSize(new java.awt.Dimension(21, 21));
				}
				{
					JLabel labelSpace = new JLabel();
					toolBar.add(labelSpace);
					labelSpace.setPreferredSize(new java.awt.Dimension(5, 10));
					labelSpace.setMinimumSize(new java.awt.Dimension(10, 10));
					labelSpace.setMaximumSize(new java.awt.Dimension(30, 0));
				}
				addShortcutButton("Søk avdelinger",IconEnum.ICON_SEARCH,FrafActionEnum.SEARCH_DEPARTMENT);
				addShortcutButton("Opprett avdeling",IconEnum.ICON_CREATE,FrafActionEnum.CREATE_DEPARTMENT);
				{
					JLabel labelSpace2 = new JLabel();
					toolBar.add(labelSpace2);
					labelSpace2.setMaximumSize(new java.awt.Dimension(20, 0));
				}
				//addShortcutButton("Les inn budsjett/omsetning",IconEnum.ICON_SOLD,FrafActionEnum.READ_BUDGET);
				addShortcutButton("Fakturer",IconEnum.ICON_INVOICE,FrafActionEnum.INVOICE);
				addShortcutButton("Importer betingelser",IconEnum.ICON_IMPORT,FrafActionEnum.IMPORT);
				{
					JLabel labelSpace3 = new JLabel();
					toolBar.add(labelSpace3);
					labelSpace3.setMaximumSize(new java.awt.Dimension(20, 0));
				}
				{
					addShortcutButton("Rapport totale betingelser",
							IconEnum.ICON_SUM_CONDITION,
							FrafActionEnum.REPORT_CONDITION_TOTAL);
				}
				{
					addShortcutButton("Rapport budsjettert omsetning",
							IconEnum.ICON_BUDGET,
							FrafActionEnum.REPORT_DEPARTMENT_BUDGET);
				}
				{
					addShortcutButton("Rapport nye avdelinger",
							IconEnum.ICON_NEW,
							FrafActionEnum.REPORT_NEW_DEPARTMENT);
				}

				{
					addShortcutButton("Rapport betingelser",
							IconEnum.ICON_CONDITION,
							FrafActionEnum.REPORT_CONDITIONS);
				}
				{
					addShortcutButton("Rapport manglende budsjett",
							IconEnum.ICON_NO_BUDGET,
							FrafActionEnum.REPORT_NO_BUDGET);
				}
				{
					addShortcutButton("Rapport total fakturering",
							IconEnum.ICON_INVOICE_SUM,
							FrafActionEnum.REPORT_TOTAL_INVOICE);
				}
				{
					addShortcutButton("Rapport avdelingsmangler",
							IconEnum.ICON_BUG,
							FrafActionEnum.REPORT_MISSING);

				}
				{
					addShortcutButton("Rapport speilede betingelser",
							IconEnum.ICON_MIRROR,
							FrafActionEnum.REPORT_MIRROR);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.gui.PanelFrafMainInterface#addFrame(javax.swing.JInternalFrame)
	 */
	public void addFrame(JInternalFrame frame) {
		desktopPane.add(frame);
	}
	public void removeFrame(JInternalFrame frame){
		desktopPane.remove(frame);
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.gui.PanelFrafMainInterface#closeAllFrames()
	 */
	public void closeAllFrames() {
		if (desktopPane == null) {
			return;
		}
		JInternalFrame[] frames = desktopPane.getAllFrames();
		List frameList = Arrays.asList(frames);
		JInternalFrame frame = null;

		Iterator frameIt = frameList.iterator();
		try {
			while (frameIt.hasNext()) {
				frame = (JInternalFrame) frameIt.next();
				frame.setClosed(true);
			}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.gui.PanelFrafMainInterface#getAllFrames()
	 */
	public JInternalFrame[] getAllFrames() {
		return desktopPane.getAllFrames();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.gui.PanelFrafMainInterface#getDesktopPane()
	 */
	public Container getDesktopPane() {
		return desktopPane;
	}

	/**
	 * Legger til knapp på toolbar
	 * 
	 * @param tooltopText
	 * @param iconEnum
	 * @param frafActionEnum
	 */
	private void addShortcutButton(String tooltopText, IconEnum iconEnum,
			FrafActionEnum frafActionEnum) {
		JButton button = new JButton();
		toolBar.add(button);
		button.setToolTipText(tooltopText);
		button.setIcon(iconEnum.getIcon());
		button.setPreferredSize(new java.awt.Dimension(20, 20));
		button.setSize(20, 20);
		button.setMaximumSize(new Dimension(20,20));
		button.setActionCommand(frafActionEnum.getActionString());
		button.setMinimumSize(new java.awt.Dimension(20, 20));
		button.setMaximumSize(new java.awt.Dimension(20, 20));
		button.addActionListener(frafMainFrameHandler);
	}

	public Component getComponent() {
		return this;
	}
}
