package no.ica.fraf.gui.jgoodies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.gui.model.DataObjectFrameDef;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Klasse som viser dialog for et gitt dataobjekt
 * @author abr99
 *
 */
public class DataObjectView {
	/**
	 * 
	 */
	Object currentParam;

	/**
	 * 
	 */
	boolean isModyfiable;

	/**
	 * 
	 */
	DataObjectFrameDef currentDef;

	/**
	 * 
	 */
	JInternalFrame internalFrame;

	/**
	 * 
	 */
	ObjectTableModelJGoodies tableObjectModel = null;

	/**
	 * 
	 */
	JTable tableData;

	/**
	 * 
	 */
	JButton buttonNew;

	/**
	 * 
	 */
	JButton buttonDelete;

	/**
	 * 
	 */
	JButton buttonSave;

	/**
	 * 
	 */
	JButton buttonRefresh;

	/**
	 * 
	 */
	JButton buttonCancel;

	/**
	 * 
	 */
	ArrayListModel data;
	/**
	 * 
	 */
	boolean modified = false;

	/**
	 * @param def
	 */
	public DataObjectView(DataObjectFrameDef def) {
		this(def, null, true);
	}

	/**
	 * @param def
	 * @param param
	 * @param modifyable
	 */
	public DataObjectView(DataObjectFrameDef def, Object param,
			boolean modifyable) {
		currentParam = param;
		isModyfiable = modifyable;

		currentDef = def;

	}

	/**
	 * Bygger vindu
	 * @return vindu
	 */
	public JInternalFrame buildInternalFrame(Dimension size) {
		internalFrame = InternalFrameBuilder.buildInternalFrame("Kontrakttype",
				size);
		loadData();

		return internalFrame;
	}

	/**
	 * Initierer komponenter
	 */
	void initComponents() {

		tableData.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		if (isModyfiable) {
			buttonDelete = new JButton(new DeleteAction());
			buttonDelete.setIcon(IconEnum.ICON_DELETE.getIcon());
			buttonNew = new JButton(new NewAction());

			buttonNew.setIcon(IconEnum.ICON_CREATE.getIcon());
			buttonSave = new JButton(new SaveAction());
			buttonSave.setIcon(IconEnum.ICON_SAVE.getIcon());
		}
		buttonRefresh = new JButton(new RefreshAction());
		buttonRefresh.setIcon(IconEnum.ICON_REFRESH.getIcon());
		buttonCancel = new JButton(new CloseAction(internalFrame));
		buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
		buttonCancel.setPreferredSize(new Dimension(105, 25));
	}

	/**
	 * Laster inn data
	 */
	private void loadData() {
		GuiUtil.runInThread(internalFrame, currentDef.getTitle(),
				"Henter data...", new DataLoader(), null);
	}

	/**
	 * Klasse som håndterer innlasting av data
	 * @author abr99
	 *
	 */
	private class DataLoader implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[], javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			List objects = currentDef.getDao().findAll(currentParam);
			data = new ArrayListModel(objects);
			if (tableObjectModel == null) {
				ObjectTableDef def = new ObjectTableDef(currentDef
						.getColumnNames(), currentDef.getMethods(), currentDef
						.getParams(), currentDef.getFormatColumns());
				tableObjectModel = new ObjectTableModelJGoodies(def, data);

				tableData = new JTable();
				tableData.setModel(tableObjectModel);

				List<Integer> sizes = Arrays
						.asList(currentDef.getColumnSizes());
				int counter = 0;
				TableColumn col = null;
				Comboable[] comboColumns = currentDef.getComboColumns();
				Comboable comboable;

				for (Integer colSize : sizes) {
					col = tableData.getColumnModel().getColumn(counter);
					col.setPreferredWidth(colSize.intValue());
					col.setMinWidth(colSize.intValue());

					if (comboColumns != null) {
						comboable = comboColumns[counter];

						if (comboable != null) {
							col.setCellEditor(GuiUtil.createComboCellEditor(
									comboable, null));
						}
					}
					counter++;
				}
			}
			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			initComponents();

			FormLayout layout = new FormLayout(
					"20dlu,p:GROW,pref:GROW,5dlu,pref,20dlu",
					"10dlu,p,5dlu,fill:25px,5dlu,fill:25px, 20dlu:grow,p,10dlu,25px,5dlu, p,5dlu, p");

			PanelBuilder builder = new DefaultFormBuilder(layout);
			//PanelBuilder builder = new DefaultFormBuilder(new FormDebugPanel(),layout);
			CellConstraints cc = new CellConstraints();

			builder.addLabel(currentDef.getInfo(), cc.xyw(2, 2, 3));
			builder.add(new JScrollPane(tableData), cc.xywh(2, 4, 2, 4));

			if (isModyfiable) {
				builder.add(buttonNew, cc.xy(5, 4));
				builder.add(buttonDelete, cc.xy(5, 6));
			}

			builder.add(ButtonBarFactory.buildCenteredBar(buttonSave,
					buttonRefresh, buttonCancel), cc.xyw(2, 10, 4));

			internalFrame.add(builder.getPanel(), BorderLayout.CENTER);
		}

	}

	/**
	 * Slettehandling
	 * @author abr99
	 *
	 */
	private class DeleteAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public DeleteAction() {
			super("Slett rad");
		}

		/**
		 * Sletter ett objekt
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (tableData.getSelectedRow() < 0) {
				GuiUtil.showErrorMsgFrame(internalFrame, "Feil",
						"Det må velges en rad.");
			} else {
				if (GuiUtil.showConfirmFrame(internalFrame, "Slette?",
						"Vil du virkelig slette " + currentDef.getObjectName())) {
					Object delObject = data.remove(tableData.getSelectedRow());
					currentDef.getDao().deleteObject(delObject);
				}
			}

		}

	}

	/**
	 * Lagrehandling
	 * @author abr99
	 *
	 */
	private class SaveAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public SaveAction() {
			super("Lagre");
		}

		/**
		 * Lagrer objejekter
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			currentDef.getDao().persistObjects(data);
			modified = false;
		}

	}

	/**
	 * Handling fpr å legge til objekt
	 * @author abr99
	 *
	 */
	private class NewAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public NewAction() {
			super("Ny rad");
		}

		/**
		 * Lager nytt objekt
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				Constructor constructor = currentDef.getObjectClass()
						.getConstructor((Class[]) null);
				Object newObject = constructor.newInstance((Object[]) null);
				data.add(0,newObject);
				tableData.setRowSelectionInterval(0,0);
				modified = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	/**
	 * Oppdaterhandling
	 * @author abr99
	 *
	 */
	private class RefreshAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public RefreshAction() {
			super("Oppdater");
		}

		/**
		 * Oppdaterer vindu
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			List objects = currentDef.getDao().findAll(currentParam);
			data.clear();
			data.addAll(objects);

		}

	}
}
