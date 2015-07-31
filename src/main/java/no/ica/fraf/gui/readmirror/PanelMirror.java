package no.ica.fraf.gui.readmirror;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.SpeiletBetingelseDAO;
import no.ica.fraf.dao.SpeiletKostnadDAO;
import no.ica.fraf.dao.fenistra.LfFakturaPosterDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.SpeiletKostnadTableModel;
import no.ica.fraf.gui.model.TableSorter;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.model.LfFakturaPoster;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.model.SpeiletKostnad;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JDateChooser;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Panel som brukes til import av speilede betingelser
 * 
 * @author abr99
 * 
 */
public class PanelMirror extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JPanel panelNorth;

	/**
	 * 
	 */
	private JTable tableMirror;

	/**
	 * 
	 */
	private JScrollPane scrollPaneMirror;

	/**
	 * 
	 */
	SpeiletKostnadTableModel speiletKostnadTableModel = new SpeiletKostnadTableModel();

	/**
	 * 
	 */
	private TableSorter tableSorter = new TableSorter(speiletKostnadTableModel);

	/**
	 * 
	 */
	private JButton buttonSeek;

	/**
	 * 
	 */
	private JDateChooser dateChooser;

	/**
	 * 
	 */
	private JLabel labelDate;

	/**
	 * 
	 */
	private JTextField textFieldDep;

	/**
	 * 
	 */
	private JLabel labelDep;

	/**
	 * DAO for speilet betingelse
	 */
	SpeiletBetingelseDAO speiletBetingelseDAO = (SpeiletBetingelseDAO) ModelUtil
			.getBean("speiletBetingelseDAO");

	/**
	 * DAO for fakturaposter i Fenistra
	 */
	LfFakturaPosterDAO lfFakturaPosterDAO = (LfFakturaPosterDAO) ModelUtil
			.getBean("lfFakturaPosterDAO");

	/**
	 * DAO for speilet kostnad
	 */
	SpeiletKostnadDAO speiletKostnadDAO = (SpeiletKostnadDAO) ModelUtil
			.getBean("speiletKostnadDAO");

	/**
	 * DAO for bunttype
	 */
	BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil.getBean("buntTypeDAO");

	/**
	 * DAO for bunt
	 */
	BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");

	/**
	 * DAo for buntstatus
	 */
	BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
			.getBean("buntStatusDAO");

	/**
	 * Vinsu som bruker panel
	 */
	ReadMirrorFrame readMirrorFrame;

	/**
	 * @param aInternalFrame
	 */
	public PanelMirror(ReadMirrorFrame aInternalFrame) {
		super();
		readMirrorFrame = aInternalFrame;
		initGUI();
		setColumnWidths();
	}

	/**
	 * Setter kolonnebredder for tabell
	 */
	private void setColumnWidths() {
		// Avdeling
		TableColumn col = tableMirror.getColumnModel().getColumn(0);
		col.setPreferredWidth(70);

		// Speilet betingelse
		col = tableMirror.getColumnModel().getColumn(1);
		col.setPreferredWidth(110);

		// Fenistra betingelse
		col = tableMirror.getColumnModel().getColumn(2);
		col.setPreferredWidth(200);

		// Fra dato
		col = tableMirror.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);

		// Til dato
		col = tableMirror.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);

		// Beløp
		col = tableMirror.getColumnModel().getColumn(5);
		col.setPreferredWidth(80);
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
				panelNorth = new JPanel();
				this.add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					labelDep = new JLabel();
					panelNorth.add(labelDep);
					labelDep.setText("Avdeling:");
				}
				{
					textFieldDep = new JTextField();
					panelNorth.add(textFieldDep);
					textFieldDep
							.setPreferredSize(new java.awt.Dimension(50, 20));
				}
				{
					labelDate = new JLabel();
					panelNorth.add(labelDate);
					labelDate.setText("Dato:");
				}
				{
					dateChooser = new JDateChooser(true);
					panelNorth.add(dateChooser);
					dateChooser
							.setPreferredSize(new java.awt.Dimension(120, 20));
				}
				{
					buttonSeek = new JButton();
					buttonSeek.setIcon(IconEnum.ICON_SEARCH.getIcon());
					buttonSeek.setMnemonic(KeyEvent.VK_S);
					panelNorth.add(buttonSeek);
					buttonSeek.setText("Søk");
					buttonSeek.setPreferredSize(new java.awt.Dimension(80, 20));
					buttonSeek.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonSeekActionPerformed(evt);
						}
					});
				}
			}
			{
				scrollPaneMirror = new JScrollPane();
				this.add(scrollPaneMirror, BorderLayout.CENTER);
				{
					tableMirror = new JTable();
					scrollPaneMirror.setViewportView(tableMirror);
					tableMirror.setModel(tableSorter);
					tableSorter.setTableHeader(tableMirror.getTableHeader());
					tableMirror.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Importerer speilede kostnader
	 * 
	 * @param contractDate
	 * @param applUser
	 * @param fromDep
	 * @param toDep
	 */
	public void importMirrorConditions(Date contractDate, ApplUser applUser,
			Integer fromDep, Integer toDep) {
		GuiUtil.runInThread(readMirrorFrame, "Speilet betingelser",
				"Leser inn betingelser...", new MirrorConditionReader(),
				new Object[] { applUser, contractDate, fromDep, toDep });

	}

	/**
	 * Søk etter avdeling
	 * 
	 * @param evt
	 */
	void buttonSeekActionPerformed(ActionEvent evt) {
		Integer avdnr = null;
		Date date = null;

		if (textFieldDep.getText().length() != 0) {
			avdnr = new Integer(textFieldDep.getText());
		}

		date = dateChooser.getDate();
		List<SpeiletKostnad> list = speiletKostnadDAO.findByAvdnrDate(avdnr,
				date);
		speiletKostnadTableModel.setData(list);

	}

	/**
	 * Klasse som håndterer lasting av speilede betingelser
	 * 
	 * @author abr99
	 * 
	 */
	private class MirrorConditionReader implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			enableButtons(enable);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			readMirrorFrame.enableComponents(false);
			String errorString = null;
			SpeiletKostnad speiletKostnad=null;

			try {
				Integer fromDep = (Integer) params[2];
				Integer toDep = (Integer) params[3];
				List<SpeiletBetingelse> betingelser = speiletBetingelseDAO
						.findByContractDate((Date) params[1], fromDep, toDep);

				if (betingelser == null) {
					return Boolean.TRUE;
				}
				List fakturaPoster;
				List innlest;
				Iterator faktIt;
				LfFakturaPoster lfFakturaPoster;
				
				AvregningFrekvensType avregningFrekvensType;
				BigDecimal postBelop;

				// går gjennom alle speilede betingelser
				for (SpeiletBetingelse speiletBetingelse : betingelser) {
					System.out.println(String.format("****** Speiletbetingelse ID %s",speiletBetingelse.getSpeiletId()));
					avregningFrekvensType = speiletBetingelse
							.getAvregningFrekvensType();

					// henter ut kostander som allerede er blitt innlest
					innlest = speiletKostnadDAO
							.findFakturaPostIdBySpeiletBetingelse(speiletBetingelse);
					// henter kostnader som ikke har blitt lest inn
					fakturaPoster = lfFakturaPosterDAO.findByKontraktObjektId(
							speiletBetingelse.getKontraktObjektId(), innlest,
							speiletBetingelse.getSpeiletFraDato());

					if (fakturaPoster != null) {
						faktIt = fakturaPoster.iterator();

						while (faktIt.hasNext()) {
							lfFakturaPoster = (LfFakturaPoster) faktIt.next();
							for (int i = 1; i <= avregningFrekvensType
									.getAntallMnd(); i++) {
								speiletKostnad = new SpeiletKostnad();
								speiletKostnad
										.setBelop(lfFakturaPoster
												.getPostBeloep()
												.divide(
														BigDecimal
																.valueOf(avregningFrekvensType
																		.getAntallMnd()),BigDecimal.ROUND_HALF_EVEN));
								speiletKostnad.setFakturaPostId(lfFakturaPoster
										.getFakturaPostId());
								speiletKostnad.setFraDato(getFromDateInPeriode(lfFakturaPoster
										.getFra(),i));
								speiletKostnad
										.setSpeiletBetingelse(speiletBetingelse);
								speiletKostnad.setTilDato(getToDateInPeriode(lfFakturaPoster
										.getTil(),i,avregningFrekvensType.getAntallMnd()));

								speiletKostnadTableModel.addRow(speiletKostnad);
							}
						}
					}

				}
				List<SpeiletKostnad> speiletKostnader = speiletKostnadTableModel.getData();

				if (speiletKostnader != null && speiletKostnader.size() != 0) {
					BuntType buntType = buntTypeDAO
							.findByKode(BuntTypeEnum.BATCH_TYPE_MIRROR);
					BuntStatus buntStatus = buntStatusDAO
							.findByKode(BuntStatusEnum.NY);
					Bunt bunt = new Bunt();
					bunt.setApplUser((ApplUser) params[0]);
					bunt.setBuntStatus(buntStatus);
					bunt.setBuntType(buntType);
					bunt.setOpprettetDato(Calendar.getInstance().getTime());
					buntDAO.saveBunt(bunt);

					Iterator speiletIt = speiletKostnader.iterator();

					while (speiletIt.hasNext()) {
						speiletKostnad = (SpeiletKostnad) speiletIt.next();
						speiletKostnad.setInnlesBunt(bunt);
						speiletKostnadDAO.saveSpeiletKostnad(speiletKostnad);
					}
				}
			} catch (RuntimeException ex) {
				ex.printStackTrace();
				speiletKostnad.getFakturaPostId();
//				speiletKostnader.size();
				errorString = ex.getMessage();
				/*
				 * GuiUtil.showErrorMsgFrame(internalFrame, "Feil", ex
				 * .getMessage());
				 */
				
				speiletKostnadTableModel.setData(null);
			}
			return errorString;
		}

		private Date getFromDateInPeriode(Date fromDate, int period) {
			if (period == 1) {
				return fromDate;
			}
			Calendar calFrom = Calendar.getInstance();
			calFrom.setTime(fromDate);
			calFrom.add(Calendar.MONTH, period-1);
			calFrom.set(Calendar.DAY_OF_MONTH, 1);

			return calFrom.getTime();
		}

		private Date getToDateInPeriode(Date toDate, int period,
				int months) {
			if (period == months || months == 1) {
				return toDate;
			}
			Calendar calTo = Calendar.getInstance();
			calTo.setTime(toDate);
			calTo.add(Calendar.MONTH, period -months);
			calTo.set(Calendar.DAY_OF_MONTH, calTo
					.getActualMaximum(Calendar.DAY_OF_MONTH));

			return calTo.getTime();
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(readMirrorFrame, "Feil", object
						.toString());
			}
			readMirrorFrame.enableComponents(true);
		}

	}

	/**
	 * Laster data for gitt bunt
	 * 
	 * @param batchId
	 */
	public void loadDataFromBatch(Integer batchId) {
		if (batchId == null) {
			speiletKostnadTableModel.setData(null);
			return;
		}
		GuiUtil.runInThread(readMirrorFrame, "Data", "Henter data...",
				new BatchLoad(), new Object[] { batchId });

	}

	/**
	 * Klasse som håndterer lasting av bunter
	 * 
	 * @author abr99
	 * 
	 */
	private class BatchLoad implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			enableButtons(enable);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			readMirrorFrame.enableComponents(false);
			List<SpeiletKostnad> list = null;

			list = speiletKostnadDAO.findByBunt((Integer) params[0]);
			speiletKostnadTableModel.setData(list);
			return Boolean.TRUE;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			readMirrorFrame.enableComponents(true);
		}

	}

	/**
	 * Enabler/disabler knapper
	 * 
	 * @param enable
	 */
	void enableButtons(boolean enable) {
		buttonSeek.setEnabled(enable);
	}
}
