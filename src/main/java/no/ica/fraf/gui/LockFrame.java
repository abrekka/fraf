package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Laas;
import no.ica.fraf.model.LaasType;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Dialog som viser låste objekter
 * 
 * @author abr99
 * 
 */
public class LockFrame extends javax.swing.JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JPanel panelSouth;

	/**
	 * 
	 */
	private JScrollPane scrollPaneLocks;

	/**
	 * 
	 */
	private JButton buttonUnlock;

	/**
	 * 
	 */
	private JPanel panelEast;

	/**
	 * 
	 */
	private JTable tableLocks;

	/**
	 * 
	 */
	private JButton buttonOk;

	/**
	 * TableModel for tabell
	 */
	private ObjectTableModel<Laas> objectTableModel;

	/**
	 * DAO for låser
	 */
	private LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");

	/**
	 * Kosntruktør
	 */
	public LockFrame() {
		super();
		initGUI();
		initTable();
		initData();
		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Initierer data
	 */
	private void initData() {
		List<Laas> list = laasDAO.findAll();
		objectTableModel.setData(list);
	}

	/**
	 * Initierer tabell
	 */
	private void initTable() {
		String[] columnNames = { "Type", "Bruker", "Dato", "Avdeling" };
		String[] methods = { "LaasType", "ApplUser", "LaasDato", "Avdeling" };
		Class[] params = { LaasType.class, ApplUser.class, String.class,
				Avdeling.class };
		ColumnFormatEnum[] formatColumns = new ColumnFormatEnum[] {
				ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
				ColumnFormatEnum.DATE_TIME, ColumnFormatEnum.NONE };
		ObjectTableDef laasTableDef = new ObjectTableDef(columnNames, methods,
				params, formatColumns);
		objectTableModel = new ObjectTableModel<Laas>(laasTableDef);

		tableLocks.setModel(objectTableModel);

		// Type
		TableColumn col = tableLocks.getColumnModel().getColumn(0);
		col.setPreferredWidth(70);

		// Bruker
		col = tableLocks.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Dato
		col = tableLocks.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Avdeling
		col = tableLocks.getColumnModel().getColumn(3);
		col.setPreferredWidth(100);

	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(500, 200));
			this.setBounds(25, 25, 500, 200);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setTitle("Oversikt lås");
			this.setResizable(true);
			this.setMaximizable(true);
			this.setIconifiable(true);
			this.setClosable(true);
			{
				panelSouth = new JPanel();
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonOk = new JButton();
					buttonOk.setIcon(IconEnum.ICON_OK.getIcon());
					buttonOk.setMnemonic(KeyEvent.VK_O);
					panelSouth.add(buttonOk);
					buttonOk.setText("OK");
					buttonOk.setPreferredSize(new java.awt.Dimension(80, 25));
					buttonOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonOkActionPerformed(evt);
						}
					});
				}
			}
			{
				scrollPaneLocks = new JScrollPane();
				this.getContentPane().add(scrollPaneLocks, BorderLayout.CENTER);
				{
					tableLocks = new JTable();
					scrollPaneLocks.setViewportView(tableLocks);
					tableLocks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
			{
				panelEast = new JPanel();
				this.getContentPane().add(panelEast, BorderLayout.EAST);
				panelEast.setPreferredSize(new java.awt.Dimension(110, 10));
				{
					buttonUnlock = new JButton();
					buttonUnlock.setIcon(IconEnum.ICON_UNLOCK.getIcon());
					panelEast.add(buttonUnlock);
					buttonUnlock.setText("Fjern lås");
					buttonUnlock.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonUnlockActionPerformed(evt);
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ok-knapp trykket
	 * 
	 * @param evt
	 */
	void buttonOkActionPerformed(ActionEvent evt) {
		dispose();
	}

	/**
	 * Knapp for å låse opp lås trykket, låser opp valgt onjekt
	 * 
	 * @param evt
	 */
	void buttonUnlockActionPerformed(ActionEvent evt) {
		if (tableLocks.getSelectedRow() < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "Det må velges en lås");
			return;
		}

		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette lås?")) {
			return;
		}
		Laas laas = objectTableModel.deleteRow(tableLocks.getSelectedRow());
		laasDAO.removeLaas(laas.getLaasId());
	}

}
