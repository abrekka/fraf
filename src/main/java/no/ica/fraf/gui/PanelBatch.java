package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.BatchSelectionListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Dette panelet inneholder informasjon om innlesningsbunter(omsetining,budsjett
 * og speilede betingelser)
 * 
 * @author abr99
 * 
 */
public class PanelBatch extends javax.swing.JPanel implements Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JScrollPane scrollPaneBatch;

	/**
	 * 
	 */
	JTable tableBatch;

	/**
	 * 
	 */
	JButton buttonRollback;

	/**
	 * 
	 */
	private JPanel panelSouth;

	/**
	 * TableModel for tabell
	 */
	ObjectTableModel<Bunt> batchTableModel;

	/**
	 * Frame som har dette panelet
	 */
	JInternalFrame internalFrame;

	/**
	 * DAO for bunt
	 */
	private BuntDAO buntDAO;

	/**
	 * DAO for pakke BUNT_PKG i database
	 */
	BuntPkgDAO buntPkgDAO;

	/**
	 * Liste over klasser som skal ha beskjed når en bunt velges
	 */
	private Vector<BatchSelectionListener> batchSelectionListeners = new Vector<BatchSelectionListener>();

	/**
	 * Type bunt
	 */
	private BuntTypeEnum buntTypeEnum;

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param aBuntTypeEnum
	 */
	public PanelBatch(JInternalFrame aInternalFrame, BuntTypeEnum aBuntTypeEnum,BuntDAO aBuntDAO,BuntPkgDAO aBuntPkgDAO) {
		super();
		internalFrame = aInternalFrame;
		buntTypeEnum = aBuntTypeEnum;
		buntDAO = aBuntDAO;
		buntPkgDAO = aBuntPkgDAO;
		initGUI();
		initTable();
	}

	/**
	 * Initierer GUI
	 * 
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent evt) {
					rootComponentShown(evt);
				}
			});
			{
				scrollPaneBatch = new JScrollPane();
				this.add(scrollPaneBatch, BorderLayout.CENTER);
				{
					tableBatch = new JTable();
					tableBatch
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPaneBatch.setViewportView(tableBatch);
					tableBatch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					tableBatch.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							tableBatchMouseClicked(evt);
						}
					});
				}
			}
			{
				panelSouth = new JPanel();
				FlowLayout panelSouthLayout = new FlowLayout();
				panelSouthLayout.setAlignment(FlowLayout.LEFT);
				panelSouth.setLayout(panelSouthLayout);
				this.add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonRollback = new JButton();
					buttonRollback.setIcon(IconEnum.ICON_ROLLBACK.getIcon());
					buttonRollback.setMnemonic(KeyEvent.VK_T);
					panelSouth.add(buttonRollback);
					buttonRollback.setText("Tilbakerull");
					buttonRollback.setEnabled(false);
					buttonRollback.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonRollbackActionPerformed(evt);
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initierer tabell
	 */
	private void initTable() {
		String[] columnNames = { "Buntnr", "Status", "Dato", "Bruker", "År",
				"Fra periode", "Til periode", "Fra avd.", "Til avd." };
		String[] methods = { "BuntId", "BuntStatus", "OpprettetDato",
				"ApplUser", "Aar", "FraPeriode", "TilPeriode", "FraAvdnr",
				"TilAvdnr" };
		Class[] params = { Integer.class, BuntStatus.class, Date.class,
				ApplUser.class, Integer.class, Integer.class, Integer.class,
				Integer.class, Integer.class };
		ObjectTableDef batchTableDef = new ObjectTableDef(columnNames, methods,
				params);
		batchTableModel = new ObjectTableModel<Bunt>(batchTableDef);
		batchTableModel.setEditable(false);

		tableBatch.setModel(batchTableModel);

		// Buntnr
		TableColumn col = tableBatch.getColumnModel().getColumn(0);
		col.setPreferredWidth(50);

		// Status
		col = tableBatch.getColumnModel().getColumn(1);
		col.setPreferredWidth(50);

		// Dato
		col = tableBatch.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Bruker
		col = tableBatch.getColumnModel().getColumn(3);
		col.setPreferredWidth(100);

		// År
		col = tableBatch.getColumnModel().getColumn(4);
		col.setPreferredWidth(50);

		// Fra Periode
		col = tableBatch.getColumnModel().getColumn(5);
		col.setPreferredWidth(70);

		// Til Periode
		col = tableBatch.getColumnModel().getColumn(6);
		col.setPreferredWidth(70);

		// Fra avd
		col = tableBatch.getColumnModel().getColumn(7);
		col.setPreferredWidth(50);

		// Til avd
		col = tableBatch.getColumnModel().getColumn(8);
		col.setPreferredWidth(50);

	}

	/**
	 * Kjøres når panel skal vises
	 * 
	 * @param evt
	 */
	void rootComponentShown(ComponentEvent evt) {
		loadData();
	}

	/**
	 * Laster data
	 */
	public void loadData() {
		GuiUtil.runInThread(internalFrame, "Bunter", "Henter bunter", this,
				null);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		buttonRollback.setEnabled(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	@SuppressWarnings("unchecked")
	public Object doWork(Object[] params, JLabel labelInfo) {
		List<Bunt> batches = buntDAO.findByBatchType(buntTypeEnum);
		batchTableModel.setData(batches);
		return new Boolean(true);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
	}

	/**
	 * Ruller tilbake bunt
	 * 
	 * @param evt
	 */
	void buttonRollbackActionPerformed(ActionEvent evt) {
		if (tableBatch.getSelectedRow() < 0) {
			GuiUtil.showErrorMsgFrame(internalFrame, "Feil",
					"Det må velges en bunt!");
			return;
		}
		if (!GuiUtil.showConfirmFrame(internalFrame, "Rulle tilbake?",
				"Vil du virkelig rulle tilbake innlesning")) {
			return;
		}
		GuiUtil.runInThread(internalFrame, "Ruller tilbake bunt",
				"Ruller tilbake...", new Rollback(), null);
	}

	/**
	 * Kjøres ved museklikk i tabell. Enabler tilbakerullingsknapp og sender
	 * beskjed om at bunt er valgt
	 * 
	 * @param evt
	 */
	void tableBatchMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			buttonRollback.setEnabled(true);

			Bunt bunt = (Bunt) batchTableModel.getObjectAtIndex(tableBatch
					.getSelectedRow());

			if (bunt != null) {
				fireBatchSelected(bunt.getBuntId());
			}
		}
	}

	/**
	 * Fyrer event om at bunt er valgt til lyttere
	 * 
	 * @param batchId
	 */
	void fireBatchSelected(Integer batchId) {
		Iterator listenerIt = batchSelectionListeners.iterator();

		while (listenerIt.hasNext()) {
			((BatchSelectionListener) listenerIt.next()).batchSelected(batchId);
		}
	}

	/**
	 * Legger til lytter for valgt bunt
	 * 
	 * @param batchSelectionListener
	 */
	public void addBatchSelectionListener(
			BatchSelectionListener batchSelectionListener) {
		batchSelectionListeners.add(batchSelectionListener);
	}

	/**
	 * Klasse som ruller tilbake bunt
	 * 
	 * @author abr99
	 * 
	 */
	private class Rollback implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			internalFrame.setEnabled(enable);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			String errorString = null;
			try {
				Bunt bunt = (Bunt) batchTableModel.getObjectAtIndex(tableBatch
						.getSelectedRow());

				buntPkgDAO.slettBunt(bunt.getBuntId());
				loadData();
				buttonRollback.setEnabled(false);
			} catch (FrafException e) {
				errorString = GuiUtil.getUserExceptionMsg(e);

				e.printStackTrace();
			}
			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(internalFrame, "Feil", object
						.toString());
			}
			fireBatchSelected(null);
		}

	}

	/**
	 * Setter bunttype
	 * 
	 * @param aBuntTypeEnum
	 */
	public void setBatchType(BuntTypeEnum aBuntTypeEnum) {
		this.buntTypeEnum = aBuntTypeEnum;
	}

}
