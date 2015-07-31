package no.ica.fraf.gui.report;

import java.awt.Color;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.service.AvdelingAvregningImportManager;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.PatternHighlighter;

import com.toedter.calendar.JYearChooser;

public class PanelDeductSummary extends AbstractReportPanel {
	private JYearChooser yearChooser;

	public PanelDeductSummary(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_AVREGNING_SAMMENDARG);
	}
	
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			this.setPreferredSize(new java.awt.Dimension(400, 75));
			{
				{
					JLabel labelYear = new JLabel();
					labelYear.setText("År:");
					panelCenter.add(labelYear);
					
				}
				{
					yearChooser = new JYearChooser();
					panelCenter.add(yearChooser);
					yearChooser
							.setPreferredSize(new java.awt.Dimension(50, 20));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	public Object doWork(Object[] params, JLabel labelInfo) {
		Integer year = yearChooser.getYear();
		List reportLines;
		AvdelingAvregningImportManager importManager=(AvdelingAvregningImportManager)ModelUtil.getBean("avdelingAvregningImportManager");
		

		reportLines= importManager.findDeductInvoicesByYear(year);
		
		
		reportFrame.loadData(reportLines);
		return Boolean.TRUE;
	}

	@Override
	public HighlighterPipeline getHighlighterPipeline() {
		PatternHighlighter highlighter=new PatternHighlighter(new Color(241,51,3),null,"IKKE AVREGNET",Pattern.CASE_INSENSITIVE,4);
		HighlighterPipeline pipeline=new HighlighterPipeline();
		pipeline.addHighlighter(highlighter);
		return pipeline;
	}

}
