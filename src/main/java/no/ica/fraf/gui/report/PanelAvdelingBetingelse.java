package no.ica.fraf.gui.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.SpeiletBetingelseDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.dao.view.AvdelingBetingelseVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.AvdelingBetingelseV;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.Region;
import no.ica.fraf.model.Rik2KjedeV;
import no.ica.fraf.model.Rik2RegionV;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.util.DataListUtil;
import no.ica.fraf.util.DataListUtilFactory;
import no.ica.fraf.util.ModelUtil;

import org.springframework.transaction.CannotCreateTransactionException;

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
 * Panel for å hente ut betingelser for avdelinger
 * 
 * @author abr99
 * 
 */
public class PanelAvdelingBetingelse extends AbstractReportPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	private JComboBox comboBoxChain;

	/**
	 * 
	 */
	private JComboBox comboBoxRegion;

	/**
	 * 
	 */
	private JComboBox comboBoxCondition;

	/**
	 * 
	 */
	private JComboBox comboBoxGroup;

	/**
	 * Konstruktør
	 * 
	 * @param aReportFrame
	 */
	public PanelAvdelingBetingelse(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_AVDELING_BETINGELSE);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
	 */
	@Override
	protected void initData() {
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		List<BetingelseType> conditions = betingelseTypeDAO.findAll();

		if (conditions != null) {
			conditions.add(0, new BetingelseType("alle"));
			ComboBoxModel model = new DefaultComboBoxModel(conditions.toArray());
			comboBoxCondition.setModel(model);
		}

		DataListUtil dataListUtil=DataListUtilFactory.getInstance(new ModelUtil());
		//List<Region> regions = dataListUtil.getRegioner();
		List<String> regions = dataListUtil.getRegionNames();

		if (regions != null) {
			regions.add(0, "alle");
			ComboBoxModel modelRegion = new DefaultComboBoxModel(regions.toArray());
			//ComboBoxModel modelRegion = new DefaultComboBoxModel(new Object[]{regions.get(0),regions.get(1),"test","test2"});
			comboBoxRegion.setModel(modelRegion);
		}

		List<Chain> chains = dataListUtil.getKjeder();

		if (chains != null) {
			chains.add(0,new Rik2KjedeV(null,"alle",null));
			ComboBoxModel modelChain = new DefaultComboBoxModel(chains
					.toArray());
			comboBoxChain.setModel(modelChain);
		}

		BetingelseGruppeDAO betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
				.getBean("betingelseGruppeDAO");
		List<BetingelseGruppe> groups = betingelseGruppeDAO.findAll();

		if (groups != null) {
			groups.add(0,new BetingelseGruppe(null,"alle",null,null));
			ComboBoxModel modelGroup = new DefaultComboBoxModel(groups
					.toArray());
			comboBoxGroup.setModel(modelGroup);
		}
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			this.setPreferredSize(new java.awt.Dimension(564, 74));
			{
				{
					JLabel labelCondition = new JLabel();
					panelCenter.add(labelCondition);
					labelCondition.setText("Betingelse:");
				}
				{
					comboBoxCondition = new JComboBox();
					panelCenter.add(comboBoxCondition);
					comboBoxCondition.setBounds(110, 34, 60, 30);
				}
				{
					JLabel labelRegion = new JLabel();
					panelCenter.add(labelRegion);
					labelRegion.setText("Region:");
				}
				{
					comboBoxRegion = new JComboBox();
					panelCenter.add(comboBoxRegion);
				}
				{
					JLabel labelChain = new JLabel();
					panelCenter.add(labelChain);
					labelChain.setText("Kjede:");
				}
				{
					comboBoxChain = new JComboBox();
					panelCenter.add(comboBoxChain);
				}
				{
					JLabel labelGroup = new JLabel();
					panelCenter.add(labelGroup);
					labelGroup.setText("Gruppe:");
				}
				{
					comboBoxGroup = new JComboBox();
					panelCenter.add(comboBoxGroup);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	@Override
	public void enableComponents(boolean enable) {
		super.enableComponents(enable);
		comboBoxChain.setEnabled(enable);
		comboBoxCondition.setEnabled(enable);
		comboBoxRegion.setEnabled(enable);
		comboBoxGroup.setEnabled(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		String errorMsg = null;

		String betingelse = null;
		String region = null;
		String kjede = null;
		String betingelseGruppe = null;

		if (comboBoxRegion.getSelectedIndex() != 0) {
			region = (String) comboBoxRegion.getSelectedItem();
		}

		if (comboBoxCondition.getSelectedIndex() != 0) {
			betingelse = ((BetingelseType) comboBoxCondition.getSelectedItem())
					.getBetingelseNavn();
		}

		if (comboBoxChain.getSelectedIndex() != 0) {
			kjede = ((Chain) comboBoxChain.getSelectedItem()).getNavn();
		}

		if (comboBoxGroup.getSelectedIndex() != 0) {
			betingelseGruppe = ((BetingelseGruppe) comboBoxGroup
					.getSelectedItem()).getBetingelseGruppeNavn();
		}

		// Date fromDate = dateChooser.getDate();
		List<AvdelingBetingelseV> reportLines;

		AvdelingBetingelseVDAO avdelingBetingelseVDAO = (AvdelingBetingelseVDAO) ModelUtil
				.getBean("avdelingBetingelseVDAO");
		reportLines = avdelingBetingelseVDAO.findByBetingelseRegionKjede(
				betingelse, region, kjede, betingelseGruppe);
		try {
			reportLines = getMirrorAmount(reportLines);
		} catch (FrafException e) {
			errorMsg = e.getMessage();
		}
		reportFrame.loadData(reportLines);

		return errorMsg;
	}

	/**
	 * Setter speilet beløp på betingelse
     * @param lines 
     * @return liste med betingelse lagt på speilede kostnader
	 * @throws FrafException
	 */
	private List<AvdelingBetingelseV> getMirrorAmount(
			List<AvdelingBetingelseV> lines) throws FrafException {
		try {

			List<SpeiletBetingelse> speiletBetingelser;
			List<LkKontraktobjekter> kontrakter;
			List<Integer> kontraktObjektIds;
			BigDecimal totalAmount = BigDecimal.valueOf(0);
			SpeiletBetingelseDAO speiletBetingelseDAO = (SpeiletBetingelseDAO) ModelUtil
					.getBean("speiletBetingelseDAO");
			LkKontraktobjekterDAO lkKontraktobjekterDAO = (LkKontraktobjekterDAO) ModelUtil
					.getBean("lkKontraktobjekterDAO");

			for (AvdelingBetingelseV betingelse : lines) {
				if (betingelse.getSpeilet() != null
						&& betingelse.getSpeilet() == 1) {
					speiletBetingelser = speiletBetingelseDAO
							.findByAvdelingBetingelseId(betingelse
									.getAvdelingBetingelseId());

					if (speiletBetingelser != null
							&& speiletBetingelser.size() > 0) {
						kontraktObjektIds = new ArrayList<Integer>();

						for (SpeiletBetingelse speiletBetingelse : speiletBetingelser) {
							kontraktObjektIds.add(speiletBetingelse
									.getKontraktObjektId());
						}

						kontrakter = lkKontraktobjekterDAO
								.findByIds(kontraktObjektIds);

						for (LkKontraktobjekter lkKontraktobjekter : kontrakter) {
							totalAmount = totalAmount.add(lkKontraktobjekter
									.getFakturabeloep());
						}
						betingelse.setBelop(totalAmount);
						totalAmount = BigDecimal.valueOf(0);
					}
				}
			}
		} catch (CannotCreateTransactionException e) {
			throw new FrafException("Kan ikke koble mot Fensitra");

		}
		return lines;
	}

}
