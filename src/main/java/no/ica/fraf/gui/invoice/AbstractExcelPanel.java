package no.ica.fraf.gui.invoice;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Abstrakt klasse som brukes av de paneler som har mulighet for generering av
 * excel-fil
 * 
 * @author abr99
 * 
 */
public abstract class AbstractExcelPanel extends JPanel {
	/**
	 * 
	 */
	static Logger logger = Logger.getLogger(AbstractExcelPanel.class);

	/**
	 * 
	 */
	JMenuItem menuItemExcel;

	/**
	 * 
	 */
	JPopupMenu popupMenuExcel;

	/**
	 * 
	 */
	private JScrollPane scrollPaneTable;

	/**
	 * 
	 */
	protected JTable table;

	/**
	 * 
	 */
	protected ObjectTableDef tableModelDef = null;

	/**
	 * 
	 */
	protected ObjectTableDef tableModelDefExcel = null;

	/**
	 * 
	 */
	protected ObjectTableModel tableModel;

	/**
	 * 
	 */
	JInternalFrame internalFrame;

	/**
	 * 
	 */
	ApplUser currentApplUser;

	/**
	 * 
	 */
	private Faktura currentFaktura;

	/**
	 * 
	 */
	protected FakturaDAO fakturaDAO;

	/**
	 * @param faktura
	 * @param applUser
	 * @param parent
	 */
	public AbstractExcelPanel(Faktura faktura, ApplUser applUser,
			JInternalFrame parent) {
		super();
		internalFrame = parent;
		currentApplUser = applUser;

		currentFaktura = faktura;

		if (fakturaDAO == null) {
			fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
		}

		initGUI();
	}

	/**
	 * Initierer GUI
	 */
	protected void initGUI() {
		{
			popupMenuExcel = new JPopupMenu("Excel");
			menuItemExcel = new JMenuItem("Eksporter data til excel");
			menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
			popupMenuExcel.add(menuItemExcel);
			menuItemExcel.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					menuItemExcelAction(e);

				}

			});

		}
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		setPreferredSize(new Dimension(400, 300));
		{
			scrollPaneTable = new JScrollPane();
			this.add(scrollPaneTable, BorderLayout.CENTER);
			{
				this.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentShown(ComponentEvent evt) {
						rootComponentShown(evt);
					}
				});
				table = new JTable();
				scrollPaneTable.setViewportView(table);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						tableMouseClicked(e);

					}

				});
			}
		}
	}

	/**
	 * Popupmeny generer excel
	 * 
	 * @param actionEvent
	 */
	void menuItemExcelAction(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equalsIgnoreCase(
				menuItemExcel.getText())) {
			GuiUtil.setWaitCursor(this);
			
			showDataInExcel();
			GuiUtil.setDefaultCursor(this);
			
		}
	}

	/**
	 * Genererer excelfil
	 */
	private void showDataInExcel() {
		GuiUtil.runInThread(internalFrame, "Generering av excelfil",
				"Genererer excelfil", new ExcelGenerator(), null);

	}

	/**
	 * @return nummerkolonner
	 */
	protected abstract List<Integer> getNumcolList();

	/**
	 * @return filnavn for excel
	 */
	protected abstract String getExcelFileName();

	/**
	 * Klasse som håndterer generering av excelfil
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelGenerator implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			ExcelUtil excelUtil;
			StringBuffer fileName;
			StringBuffer returnString = new StringBuffer();

			ObjectTableModel excelTableModel = new ObjectTableModel(
					tableModelDefExcel);
			excelTableModel.setData(tableModel.getData());

			excelUtil = new ExcelUtil(excelTableModel, getExcelFileName(),
					getExcelFileName());

			fileName = new StringBuffer(getExcelFileName()).append("_Rapport")
					.append(".xls");
			String dir = excelUtil.prepare(currentApplUser, internalFrame);

			try {
				if (dir != null) {
					excelUtil.showDataInExcel(dir.toString(), fileName
							.toString().replaceAll(" ", "_"), (List<Integer>) null,
							getNumcolList(), labelInfo);

					returnString
							.append("Dersom ikke excel starter automatisk, ligger excelfil med navn "
									+ fileName.toString()
									+ " under katalog "
									+ dir.toString());
				}
			} catch (FrafException e) {
				logger.error("Feil ved generering av excelfil", e);
				returnString.append("Feil: ").append(e.getMessage());
				e.printStackTrace();
			} catch (RuntimeException runEx) {
				logger.error("Feil ved generering av excelfil", runEx);
				returnString.append("Feil: ").append(runEx.getMessage());
			}

			return returnString.toString();
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil
						.showMsgFrame(internalFrame, "Ferdig", object
								.toString());
			}

		}

	}

	/**
	 * Museklikk i tabell
	 * 
	 * @param event
	 */
	void tableMouseClicked(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON3) {
			popupMenuExcel.show((JTable) event.getSource(), event.getX(), event
					.getY());
		}
	}

	/**
	 * Panel vises
	 * 
	 * @param evt
	 */
	void rootComponentShown(ComponentEvent evt) {
		initData(currentFaktura);
	}

	/**
	 * Initierere data
	 * 
	 * @param faktura
	 */
	protected abstract void initData(Faktura faktura);
}
