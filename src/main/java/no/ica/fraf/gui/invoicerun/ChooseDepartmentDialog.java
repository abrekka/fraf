package no.ica.fraf.gui.invoicerun;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * Vindu for å velge de avdelinger som skal være med i fakturering
 * 
 * @author abr99
 * 
 */
public class ChooseDepartmentDialog extends javax.swing.JDialog implements
		Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * TableModel for tabell
	 */
	private DepartmentNrTableModel departmentNrTableModel;

	/**
	 * Tabell som inneholder avdelinger
	 */
	private JTable tableDepartments;

	/**
	 * Vindu som kaller dialog
	 */
	private JInternalFrame callingFrame;

	/**
	 * Fra avdeling
	 */
	private Integer fromDepartment;

	/**
	 * Til avdeling
	 */
	private Integer toDepartment;

	/**
	 * Avdelinger som ikke skal faktureres
	 */
	private List<Integer> removedDepartments;

	/**
	 * 
	 */
	private JButton buttonOk;

	/**
	 * Dialog som vises ved lasting av data
	 */
	private InfoDialog infoDialog;

	/**
	 * @param aCallingFrame
	 * @param fromDep
	 * @param toDep
	 * @param removedDeps
	 */
	public ChooseDepartmentDialog(JInternalFrame aCallingFrame,
			Integer fromDep, Integer toDep, List<Integer> removedDeps) {
		super(FrafMain.getInstance());
		callingFrame = aCallingFrame;
		fromDepartment = fromDep;
		toDepartment = toDep;
		removedDepartments = removedDeps;
		departmentNrTableModel = new DepartmentNrTableModel();
		infoDialog = new InfoDialog(this);
		initGUI();
		initTable();

		initData();

	}

	/**
	 * Laster data
	 */
	private void initData() {
		GuiUtil.runInThread(callingFrame, "Laster", "Henter avdelinger...",
				this, null);
	}

	/**
	 * Initierere tabell
	 */
	private void initTable() {
		// Faktureres
		TableColumn col = tableDepartments.getColumnModel().getColumn(0);
		col.setPreferredWidth(70);

		// Avdnr
		col = tableDepartments.getColumnModel().getColumn(1);
		col.setPreferredWidth(50);

		// Navn
		col = tableDepartments.getColumnModel().getColumn(2);
		col.setPreferredWidth(170);

		// Status
		col = tableDepartments.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);
	}

	/**
	 * Initerer GUI
	 */
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			this.setBounds(29, 52, 400, 300);

			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Velg avdelinger");

			{
				JPanel panelSouth = new JPanel();
				getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonOk = new JButton();
					buttonOk.setIcon(IconEnum.ICON_OK.getIcon());
					panelSouth.add(buttonOk);
					buttonOk.setText("Ok");
					buttonOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonOkActionPerformed(evt);
						}
					});
				}
			}
			{
				JScrollPane scrollPaneTable = new JScrollPane();
				getContentPane().add(scrollPaneTable, BorderLayout.CENTER);
				{
					tableDepartments = new JTable();
					scrollPaneTable.setViewportView(tableDepartments);
					tableDepartments.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					tableDepartments.setModel(departmentNrTableModel);

				}
			}
			pack();
			setLocationRelativeTo(null);

			this.setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lukker dialog
	 * 
	 * @param evt
	 */
	void buttonOkActionPerformed(ActionEvent evt) {
		dispose();
	}

	/**
	 * Henter ut avdelinger som ikke skal være med i fakturering
	 * 
	 * @return avdelinger som ikke skal være med i fakturering
	 */
	public Integer[] getRemovedDepartments() {
		return departmentNrTableModel.getNotChoosenDepartments();
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		infoDialog.setVisible(!enable);
		buttonOk.setEnabled(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	@SuppressWarnings("unchecked")
	public Object doWork(Object[] params, JLabel labelInfo) {
		String errorMsg=null;
		try {
			AvdelingVDAO avdelingVDAO = (AvdelingVDAO) ModelUtil
					.getBean("avdelingVDAO");

			List<AvdelingV> departments = avdelingVDAO.findAll(fromDepartment,
					toDepartment);

			departmentNrTableModel.setData(departments, removedDepartments);
		} catch (FrafException e) {
			e.printStackTrace();
			errorMsg=e.getMessage();
		}
		return errorMsg;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if(object!=null){
			GuiUtil.showErrorMsgDialog(this, "Feil", object.toString());
		}
	}

	/**
	 * Dialog som vises når data lastes i dialog
	 * 
	 * @author abr99
	 * 
	 */
	private class InfoDialog extends JDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param dialog
		 */
		public InfoDialog(JDialog dialog) {
			super(dialog);
			initGUI();
			setLocationRelativeTo(null);
		}

		/**
		 * Initierer GUI
		 */
		private void initGUI() {
			try {
				FlowLayout thisLayout = new FlowLayout();
				thisLayout.setVgap(20);
				getContentPane().setLayout(thisLayout);
				this.setTitle("Laster data");
				{
					JLabel labelInfo = new JLabel();
					getContentPane().add(labelInfo);
					labelInfo.setText("Laster avdelinger...");
					labelInfo.setPreferredSize(new java.awt.Dimension(100, 15));
				}
				this.setSize(206, 79);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
