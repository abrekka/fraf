package no.ica.tollpost.gui.handlers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.model.Department;
import no.ica.fraf.model.Rik2AvdV;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.tollpost.dao.pkg.TgImportPkgDAO;
import no.ica.tollpost.model.TgImport;
import no.ica.tollpost.model.TgLinjeExtV;
import no.ica.tollpost.model.TgMeldingExtV;
import no.ica.tollpost.model.TgNotImportedV;
import no.ica.tollpost.service.LazyLoadTgMeldingExtVEnum;
import no.ica.tollpost.service.TgMeldingExtVManager;
import no.ica.tollpost.service.TgNotImportedVManager;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.PatternHighlighter;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.ArrayListModel;

public class ImportDataViewHandler implements Closeable {
	private ArrayListModel importList;

	private TgNotImportedVManager tgNotImportedVManager;

	private TgMeldingExtVManager tgMeldingExtVManager;

	private JButton buttonImport;

	private JXTable table;

	private ImportTableModel tableModel;

	private DepartmentDAO departmentDAO;

	private BuntTypeDAO buntTypeDAO;

	private BuntStatusDAO buntStatusDAO;

	private BuntDAO buntDAO;
	private ApplUser applUser;
	//private SelectionInList importSelectionList;

	public ImportDataViewHandler(TgNotImportedVManager aTgNotImportedVManager,
			DepartmentDAO aDepartmentDAO,
			TgMeldingExtVManager aTgMeldingExtVManager,
			BuntTypeDAO aBuntTypeDAO, BuntDAO aBuntDAO,BuntStatusDAO aBuntStatusDAO,ApplUser aApplUser) {
		applUser=aApplUser;
		buntDAO = aBuntDAO;
		buntTypeDAO = aBuntTypeDAO;
		buntStatusDAO = aBuntStatusDAO;
		tgMeldingExtVManager = aTgMeldingExtVManager;
		departmentDAO = aDepartmentDAO;
		tgNotImportedVManager = aTgNotImportedVManager;
		importList =new ArrayListModel();
		//importSelectionList = new SelectionInList((ListModel)importList);
		//importSelectionList.addPropertyChangeListener(new SelectionListener());
		loadImportList();
	}

	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}
	
	private void loadImportList(){
		importList.clear();
		importList.addAll(tgNotImportedVManager.findAll());
	}

	public JButton getButtonImport(WindowInterface window) {
		buttonImport = new JButton(new ImportAction(window));
		buttonImport.setEnabled(false);
		table.getSelectionModel().addListSelectionListener(				new TableSelectionListener());
		return buttonImport;
	}

	public JXTable getTableImport() {
		table = new JXTable();

		tableModel = new ImportTableModel(importList);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		  /*table.setSelectionModel(new SingleListSelectionAdapter(
		  importSelectionList.getSelectionIndexHolder()));*/
		 
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		// tableOrders.addMouseListener(new OrderDoubleClickHandler(window));

		table.setDragEnabled(false);
		
		HighlighterPipeline highlighters = new HighlighterPipeline();

		PatternHighlighter pattern = new PatternHighlighter(Color.WHITE,
				Color.LIGHT_GRAY, "1", Pattern.CASE_INSENSITIVE, 6);
		highlighters.addHighlighter(pattern);
		table.setHighlighters(highlighters);
		table.getColumnExt(6).setVisible(false);

		table.setShowGrid(true);
		table.packAll();
		
		// batchSelectionList.clearSelection();
		return table;
	}

	private static final class ImportTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Id","Filnr", "Type", "Transdato",
				"Dt reg", "Beløp","Importert" };

		private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMdd");

		/**
		 * @param listModel
		 */
		public ImportTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * Henter verdi
		 * 
		 * @param rowIndex
		 * @param columnIndex
		 * @return verdi
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			TgNotImportedV tgNotImported = (TgNotImportedV) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return tgNotImported.getMeldingId();
			case 1:
				return tgNotImported.getLopenrFil();
			case 2:
				return tgNotImported.getMeldingstype();
			case 3:
				return dateFormat.format(tgNotImported.getTransDato());
			case 4:
				return tgNotImported.getDtReg();
			case 5:
				return tgNotImported.getBelop();
			case 6:
				return tgNotImported.getImportert();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

		public TgNotImportedV getObjectAt(int row) {
			return (TgNotImportedV) getRow(row);
		}

	}

	public boolean canClose(String actionString) {
		return true;
	}

	private class ImportAction extends AbstractAction {
		private WindowInterface window;

		public ImportAction(WindowInterface aWindow) {
			super("Importer");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			int[] rows = table.getSelectedRows();
			table.clearSelection();
			TgNotImportedV tgNotImportedV = null;
			Rik2AvdV avd = null;
			//List<Integer> meldingIder = new ArrayList<Integer>();
			Integer[] meldingIder = new Integer[rows.length];
			int counter = 0;
			if (rows.length != 0) {
				for (Integer row : rows) {
					
					tgNotImportedV = tableModel.getObjectAt(table.convertRowIndexToModel(row));
					//meldingIder.add(tgNotImportedV.getMeldingId());
					if(tgNotImportedV.getImportert()==0){
					meldingIder[counter]=tgNotImportedV.getMeldingId();
					counter++;
					}
				}
			}
			
			GuiUtil.runInThreadWheel(window.getRootPane(), new TgImporter(
					window,meldingIder), null);

		}
	}

	private class TableSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent arg0) {
			int row = table.getSelectedRow();
			if (row != -1) {
				buttonImport.setEnabled(true);
			}

		}

	}
	
	/*private class SelectionListsner implements PropertyChangeListener{

		public void propertyChange(PropertyChangeEvent arg0) {
			buttonImport.setEnabled(false);
			TgNotImportedV tgNotImportedV = (TgNotImportedV)importSelectionList.getElementAt(table.convertRowIndexToModel(importSelectionList.getSelectionIndex()));
			if(tgNotImportedV.getImportert()==0){
				buttonImport.setEnabled(true);
			}
			
		}
		
	}*/

	private class TgImporter implements Threadable {
		private WindowInterface window;
		private Integer[] meldingIder;

		public TgImporter(WindowInterface aWindow,Integer[] importIder) {
			window = aWindow;
			meldingIder=importIder;
		}

		public void enableComponents(boolean enable) {
		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			String errorMsg=null;
			try {
				labelInfo.setText("Importerer tollpostdata...");
				TgImportPkgDAO tgImportPkgDAO=(TgImportPkgDAO)ModelUtil.getBean("tgImportPkgDAO");
				tgImportPkgDAO.importer(meldingIder,applUser.getUserId());
				loadImportList();
			} catch (FrafException e) {
				e.printStackTrace();
				errorMsg=e.getMessage();
			}
			 
			/*String errorString = null;
			try {
				int[] rows = table.getSelectedRows();
				table.clearSelection();
				TgNotImportedV tgNotImportedV = null;
				Rik2AvdV avd = null;
				List<Integer> meldingIder = new ArrayList<Integer>();
				int counter = 1;
				if (rows.length != 0) {
					for (Integer row : rows) {
						labelInfo.setText("Importerer tollpostdata...");
						tgNotImportedV = tableModel.getObjectAt(table.convertRowIndexToModel(row));
						meldingIder.add(tgNotImportedV.getMeldingId());
						counter++;
						//importList.remove(tgNotImportedV);
					}
					importTg(meldingIder);
					loadImportList();
						
				}
				
			} catch (FrafException e) {
				errorString = e.getMessage();
				e.printStackTrace();
			}
			return errorString;*/
			return errorMsg;
		}

		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
		}

		private void importTg(List<Integer> meldingIder) throws FrafException {
			List<TgMeldingExtV> meldinger = tgMeldingExtVManager
					.findByIds(meldingIder);
			Set<TgLinjeExtV> linjer;
			BuntType buntTgImportType = buntTypeDAO
					.findByKode(BuntTypeEnum.BATCH_TYPE_TG_IMPORT);
			BuntStatus buntTgImportStatus = buntStatusDAO
					.findByKode(BuntStatusEnum.NY);
			Bunt importBunt = new Bunt();
			importBunt.setBuntType(buntTgImportType);
			importBunt.setBuntStatus(buntTgImportStatus);
			importBunt.setOpprettetDato(Calendar.getInstance().getTime());
			importBunt.setApplUser(applUser);
			if (meldinger != null) {
				for (TgMeldingExtV melding : meldinger) {
					tgMeldingExtVManager
							.lazyLoad(
									melding,
									new LazyLoadTgMeldingExtVEnum[] { LazyLoadTgMeldingExtVEnum.TG_LINJE });
					linjer = melding.getTgLinjeExtVs();
					if (linjer != null) {
						importLinjer(linjer, importBunt);
					}
				}
			}
		}

		private void importLinjer(Collection<TgLinjeExtV> linjer,
				Bunt importBunt) throws FrafException {
			if (linjer != null) {
				Department avd = null;
				TgImport tgImport = null;
				for (TgLinjeExtV linje : linjer) {

						
						
					avd = departmentDAO.findByButiksNrAndDate(linje
							.getLokasjonId(), linje.getTransDato());

					if (avd == null) {
						throw new FrafException(
								"Fant ikke avdeling for butiksnr "
										+ linje.getLokasjonId() + " med dato "
										+ linje.getTransDato());
					}
					tgImport = new TgImport();
					tgImport.setAntTrans(linje.getAntTrans());
					tgImport.setAvdnr(avd.getAvdnr());
					tgImport.setBelop(linje.getBelop());
					tgImport.setDtReg(linje.getDtReg());
					tgImport.setKolliId(linje.getKolliId());
					tgImport.setLinjeId(linje.getLinjeId());
					tgImport.setMeldingId(linje.getTgMeldingExtV()
							.getMeldingId());
					tgImport.setMeldingstype(linje.getTgMeldingExtV()
							.getMeldingstype());
					tgImport.setSendingsnr(linje.getSendingsnr());
					tgImport.setTransDato(linje.getTransDato());
					tgImport.setTransfilId(linje.getTransfilId());
					tgImport.setAvtaletype(avd.getAvtaletype());
					tgImport.setDataset(avd.getDatasetConcorde());
					tgImport.setLopenrFil(linje.getTgMeldingExtV().getLopenrFil());
					tgImport.setTransType(linje.getTransType());

					importBunt.addTgImport(tgImport);
					}


				buntDAO.saveBunt(importBunt);
			}
		}
	}
}
