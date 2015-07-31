package no.ica.fraf.gui.report;

import java.util.List;

import javax.swing.JLabel;

import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.util.ModelUtil;

/**
 * Panel for rapport over arkiv
 * @author abr99
 *
 */
public class PanelArchive extends AbstractReportPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param aReportFrame
     */
    public PanelArchive(ReportFrame aReportFrame) {
        super(aReportFrame, ReportEnum.REPORT_ARCHIVE);
    }

    /**
     * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
     */
    @Override
    protected void initData() {
        
        
    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[], javax.swing.JLabel)
     */
    @SuppressWarnings("unchecked")
    public Object doWork(Object[] params, JLabel labelInfo) {
        List<Avdeling> reportLines;

        AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
                .getBean("avdelingDAO");
        reportLines = avdelingDAO.findAll();
        

        reportFrame.loadData(reportLines);
        return Boolean.TRUE;
    }
    
    
}
