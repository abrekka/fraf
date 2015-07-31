package no.ica.fraf.gui.report;

import java.util.List;

import javax.swing.JLabel;

import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.SpeiletBetingelseMangelV;
import no.ica.fraf.service.SpeiletBetingelseMangelVManager;
import no.ica.fraf.util.ModelUtil;

public class PanelSpeiletBetingelseMangel extends AbstractReportPanel {

	public PanelSpeiletBetingelseMangel(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_SPEILET_BETINGELSE_MANGEL);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	public Object doWork(Object[] params, JLabel labelInfo) {
		SpeiletBetingelseMangelVManager speiletBetingelseMangelVManager=(SpeiletBetingelseMangelVManager)ModelUtil.getBean("speiletBetingelseMangelVManager");
		List<SpeiletBetingelseMangelV> list = speiletBetingelseMangelVManager.findAll();
		reportFrame.loadData(list);
		return null;
	}

}
