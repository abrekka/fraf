package no.ica.fraf.gui.report;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.view.TotalFaktureringVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.Rik2RegionV;
import no.ica.fraf.model.TotalFaktureringV;
import no.ica.fraf.util.DataListUtil;
import no.ica.fraf.util.DataListUtilFactory;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

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
 * Utvalg for rapporten total fakturering
 * 
 * @author abr99
 * 
 */
public class PanelTotalInvoice extends AbstractReportPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JComboBox comboBoxRegion;

	/**
	 * 
	 */
	private JComboBox comboBoxChain;

	/**
	 * 
	 */
	private JComboBox comboBoxCondition;
	private JComboBox comboBoxConditionGroup;

	/**
	 * 
	 */
	private JComboBox comboBoxCompany;

	/**
	 * 
	 */
	private JTextField textFieldDepFrom;

	/**
	 * 
	 */
	private JTextField textFieldDepTo;

	/**
	 * 
	 */
	private JTextField textFieldInvoice;

	/**
	 * 
	 */
	private JComboBox comboBoxSalesManager;

	/**
	 * 
	 */
	private JMonthChooser monthChooserFrom;

	/**
	 * 
	 */
	private JMonthChooser monthChooserTo;

	/**
	 * 
	 */
	private JYearChooser yearChooserFrom;

	/**
	 * 
	 */
	private JYearChooser yearChooserTo;

	/**
	 * 
	 */
	private TotalFaktureringV filter;
	private List<TotalFaktureringV> reportLines;

	/**
	 * Konstruktør
	 * 
	 * @param aReportFrame
	 */
	public PanelTotalInvoice(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_TOTAL_FAKTURERING);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			//this.setPreferredSize(new java.awt.Dimension(1500, 150));
			GridBagLayout panelCenterLayout = new GridBagLayout();
			panelCenter.setLayout(panelCenterLayout);
			{
				{
					JLabel labelYearFrom = new JLabel();
					panelCenter.add(labelYearFrom, new GridBagConstraints(0, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelYearFrom.setText("Fra år:");
				}
				{
					yearChooserFrom = new JYearChooser();
					panelCenter.add(yearChooserFrom, new GridBagConstraints(1,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					yearChooserFrom.setPreferredSize(new Dimension(50, 20));
				}
				{
					JLabel labelYearTo = new JLabel();
					panelCenter.add(labelYearTo, new GridBagConstraints(0, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0,
							0));
					labelYearTo.setText("Til år:");
				}
				{
					yearChooserTo = new JYearChooser();
					panelCenter.add(yearChooserTo, new GridBagConstraints(1, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
					yearChooserTo.setPreferredSize(new Dimension(50, 20));
				}

				{
					JLabel labelInvoice = new JLabel();
					panelCenter.add(labelInvoice, new GridBagConstraints(0, 3,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0,
							0));
					labelInvoice.setText("Fakturanr:");
				}
				{
					textFieldInvoice = new JTextField();
					textFieldInvoice.setPreferredSize(new Dimension(70, 20));
					panelCenter.add(textFieldInvoice, new GridBagConstraints(1,
							3, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
				}

				{
					JLabel labelPeriodFrom = new JLabel();
					panelCenter.add(labelPeriodFrom, new GridBagConstraints(2,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelPeriodFrom.setText("Periode fra:");
				}
				{
					monthChooserFrom = new JMonthChooser();
					monthChooserFrom.setPreferredSize(new Dimension(110, 20));
					panelCenter.add(monthChooserFrom, new GridBagConstraints(3,
							0, 0, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
				}
				{
					JLabel labelPeriodTo = new JLabel();
					panelCenter.add(labelPeriodTo, new GridBagConstraints(2, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
					labelPeriodTo.setText("Periode til:");
				}
				{
					monthChooserTo = new JMonthChooser();
					monthChooserTo.setPreferredSize(new Dimension(110, 20));
					panelCenter.add(monthChooserTo, new GridBagConstraints(3,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
				}

				{
					JLabel labelDepFrom = new JLabel();
					panelCenter.add(labelDepFrom, new GridBagConstraints(0, 2,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelDepFrom.setText("Fra avd:");
				}
				{
					textFieldDepFrom = new JTextField();
					textFieldDepFrom.setPreferredSize(new Dimension(50, 20));
					panelCenter.add(textFieldDepFrom, new GridBagConstraints(1,
							2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
				}

				{
					JLabel labelDepTo = new JLabel();
					panelCenter.add(labelDepTo, new GridBagConstraints(2, 2, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelDepTo.setText("Til avd:");
				}
				{
					textFieldDepTo = new JTextField();
					textFieldDepTo.setPreferredSize(new Dimension(50, 20));
					panelCenter.add(textFieldDepTo, new GridBagConstraints(3,
							2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
				}

				{
					JLabel labelCompany = new JLabel();
					panelCenter.add(labelCompany, new GridBagConstraints(5, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelCompany.setText("Selskap:");
				}

				{
					comboBoxCompany = new JComboBox();
					comboBoxCompany.setPreferredSize(new Dimension(100, 20));
					panelCenter.add(comboBoxCompany, new GridBagConstraints(5,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
				}
				
				{
					JLabel labelConditionGroup = new JLabel();
					panelCenter.add(labelConditionGroup, new GridBagConstraints(6,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelConditionGroup.setText("Betingelsegruppe:");
				}
				{
					comboBoxConditionGroup = new JComboBox();
					comboBoxConditionGroup.setPreferredSize(new Dimension(120, 20));
					panelCenter.add(comboBoxConditionGroup, new GridBagConstraints(
							6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
				}

				{
					JLabel labelCondition = new JLabel();
					panelCenter.add(labelCondition, new GridBagConstraints(7,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelCondition.setText("Betingelse:");
				}
				{
					comboBoxCondition = new JComboBox();
					comboBoxCondition.setPreferredSize(new Dimension(120, 20));
					panelCenter.add(comboBoxCondition, new GridBagConstraints(
							7, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
				}
				{
					JLabel labelChain = new JLabel();
					panelCenter.add(labelChain, new GridBagConstraints(8, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelChain.setText("Kjede:");
				}
				{
					comboBoxChain = new JComboBox();
					comboBoxChain.setPreferredSize(new Dimension(120, 20));
					panelCenter.add(comboBoxChain, new GridBagConstraints(8, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
				}
				{
					JLabel labelRegion = new JLabel();
					panelCenter.add(labelRegion, new GridBagConstraints(9, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelRegion.setText("Region:");
				}
				{
					comboBoxRegion = new JComboBox();
					comboBoxRegion.setPreferredSize(new Dimension(120, 20));
					panelCenter.add(comboBoxRegion, new GridBagConstraints(9,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
				}
				{
					JLabel labelSalesManager = new JLabel();
					panelCenter.add(labelSalesManager, new GridBagConstraints(
							10, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelSalesManager.setText("Salgssjef:");
				}
				{
					comboBoxSalesManager = new JComboBox();
					comboBoxSalesManager
							.setPreferredSize(new Dimension(120, 20));
					panelCenter.add(comboBoxSalesManager,
							new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,
									GridBagConstraints.WEST,
									GridBagConstraints.NONE, new Insets(0, 5,
											0, 0), 0, 0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
	 */
	@Override
	protected void initData() {
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		BetingelseGruppeDAO betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
		.getBean("betingelseGruppeDAO");
		List<BetingelseType> list = betingelseTypeDAO.findAll();
		ComboBoxModel model;

		if (list != null) {
			list.add(0,new BetingelseType("alle"));
			model = new DefaultComboBoxModel(list.toArray());
			comboBoxCondition.setModel(model);
		}
		
		List<BetingelseGruppe> listGroup = betingelseGruppeDAO.findAll();
		
		if (listGroup != null) {
			listGroup.add(0,new BetingelseGruppe(null,"alle",null,null));
			model = new DefaultComboBoxModel(listGroup.toArray());
			comboBoxConditionGroup.setModel(model);
		}
		
		DataListUtil dataListUtil=DataListUtilFactory.getInstance(new ModelUtil());

		List listTmp = dataListUtil.getKjeder();

		if (listTmp != null) {
			listTmp.add(0, "alle");
			model = new DefaultComboBoxModel(listTmp.toArray());
			comboBoxChain.setModel(model);
		}

		listTmp = dataListUtil.getRegioner();

		if (listTmp != null) {
			listTmp.add(0, "alle");
			model = new DefaultComboBoxModel(listTmp.toArray());
			comboBoxRegion.setModel(model);
		}

		listTmp = dataListUtil.getDistriktssjefer();

		if (listTmp != null) {
			listTmp.add(0, "alle");
			model = new DefaultComboBoxModel(listTmp.toArray());
			comboBoxSalesManager.setModel(model);
		}

		listTmp = dataListUtil.getBokfSelskaper();

		if (listTmp != null) {
			listTmp.add(0, "alle");
			model = new DefaultComboBoxModel(listTmp.toArray());
			comboBoxCompany.setModel(model);
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		if(reportLines!=null){
			reportLines.clear();
		}
		//String betingelseTypeNavn = null;
		String betingelseTypeKode = null;
		Integer betingelseGruppeId = null;
		String kjedeNavn = null;
		String regionNavn = null;
		String salgssjefNavn = null;
		filter = new TotalFaktureringV();
		String selskapNavn = null;
		Integer avdNrFra = null;
		Integer avdNrTil = null;

		if (textFieldInvoice.getText().length() != 0) {
			filter.setFakturaNr(textFieldInvoice.getText());
		} else {

			if (comboBoxConditionGroup.getSelectedIndex() > 0) {
				//betingelseTypeNavn = ((BetingelseType) comboBoxCondition.getSelectedItem()).getBetingelseNavn();
				betingelseGruppeId = ((BetingelseGruppe) comboBoxConditionGroup.getSelectedItem()).getBetingelseGruppeId();
			}
			if (comboBoxCondition.getSelectedIndex() > 0) {
				//betingelseTypeNavn = ((BetingelseType) comboBoxCondition.getSelectedItem()).getBetingelseNavn();
				betingelseTypeKode = ((BetingelseType) comboBoxCondition.getSelectedItem()).getBetingelseTypeKode();
			}
			if (comboBoxChain.getSelectedIndex() > 0) {
				kjedeNavn = ((Chain) comboBoxChain.getSelectedItem())
						.getNavn();
			}
			if (comboBoxRegion.getSelectedIndex() > 0) {
				regionNavn = ((Rik2RegionV) comboBoxRegion.getSelectedItem())
						.getNavn();
			}
			if (comboBoxSalesManager.getSelectedIndex() > 0) {
				salgssjefNavn = (String) comboBoxSalesManager.getSelectedItem();
			}

			if (comboBoxCompany.getSelectedIndex() > 0) {
				selskapNavn = ((BokfSelskap) comboBoxCompany.getSelectedItem())
						.getSelskapNavn();
			}

			if (textFieldDepFrom.getText().length() != 0) {
				avdNrFra = Integer.valueOf(textFieldDepFrom.getText());
			}

			if (textFieldDepTo.getText().length() != 0) {
				avdNrTil = Integer.valueOf(textFieldDepTo.getText());
			}

			//filter.setLinjeBeskrivelse(betingelseTypeNavn);
			filter.setBetingelseGruppeId(betingelseGruppeId);
			filter.setBetingelseTypeKode(betingelseTypeKode);
			filter.setAar(yearChooserFrom.getYear());
			filter.setTilAar(yearChooserTo.getYear());
			//filter.setFraPeriode(monthChooserFrom.getMonth() + 1);
			//filter.setTilPeriode(monthChooserTo.getMonth() + 1);
			// filter.setTotalFaktureringVPK(totalFaktureringVPK);
			filter.setKjedeNavn(kjedeNavn);
			filter.setRegionNavn(regionNavn);
			filter.setSalgssjef(salgssjefNavn);
			filter.setSelskapNavn(selskapNavn);
			filter.setAvdnr(avdNrFra);
			filter.setAvdNrTil(avdNrTil);
		}

		TotalFaktureringVDAO totalFaktureringVDAO = (TotalFaktureringVDAO) ModelUtil
				.getBean("totalFaktureringVDAO");
		reportLines = totalFaktureringVDAO.findByFilter(filter,monthChooserFrom.getMonth() + 1,monthChooserTo.getMonth() + 1);/*
																		 * yearChooser
																		 * .getYear(),
																		 * monthChooserFrom.getMonth() +
																		 * 1,
																		 * monthChooserTo
																		 * .getMonth() +
																		 * 1,
																		 * betingelseTypeNavn,
																		 * kjedeNavn,
																		 * regionNavn,
																		 * salgssjefNavn);
																		 */
		reportFrame.loadData(reportLines);
		return Boolean.TRUE;
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#getFilter()
	 */
	@Override
	public Object getFilter() {
		return filter;
	}

	public Collection<Faktura> getInvoices(){
		Collection<Faktura> invoices=null;
		List<String> invoiceNumbers = getInvoiceNumbers();
		
		if(invoiceNumbers!=null){
			if(invoiceNumbers.size()>1000){
				GuiUtil.showErrorMsgDialog(this,"Feil","Det er for mange fakturaer, gjør et snevrere søk");
			}else{
			FakturaDAO fakturaDAO=(FakturaDAO)ModelUtil.getBean("fakturaDAO");
			invoices = fakturaDAO.findByFakturaNr(invoiceNumbers);
			}
			
		}
		return invoices;
	}
	
	private List<String> getInvoiceNumbers(){
		List<String> numbers=null;
		if(reportLines!=null){
			numbers=new ArrayList<String>();
			for(TotalFaktureringV linje:reportLines){
				if(!numbers.contains(linje.getFakturaNr())){
					numbers.add(linje.getFakturaNr());
				}
			}
		}
		return numbers;
	}
}
