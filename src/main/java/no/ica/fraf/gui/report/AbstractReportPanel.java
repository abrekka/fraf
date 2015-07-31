package no.ica.fraf.gui.report;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.decorator.HighlighterPipeline;

/**
 * Abstrakt klasse for alle paneler som skal brukes til å gjøre utvalg for
 * rapport
 * 
 * @author abr99
 * 
 */
public abstract class AbstractReportPanel extends javax.swing.JPanel implements
		Threadable {

	/**
	 * Panel med rapportknapp
	 */
	protected JPanel panelSouth;

	/**
	 * Panel med rapportresultat
	 */
	protected JPanel panelCenter;

	/**
	 * 
	 */
	protected JButton buttonReport;

	/**
	 * Vindu som bruker panel
	 */
	protected ReportFrame reportFrame;

	/**
	 * Rapporttype
	 */
	protected ReportEnum reportEnum;
	/**
	 * 
	 */
	protected ObjectTableModel reportTableModel;
	/**
	 * 
	 */
	protected ObjectTableModel reportTableModelExcel;

	/**
	 * @param aReportFrame
	 * @param aReportEnum
	 */
	public AbstractReportPanel(ReportFrame aReportFrame,ReportEnum aReportEnum) {
		super();
		reportFrame = aReportFrame;
		reportEnum = aReportEnum;
		reportTableModel = new ObjectTableModel(reportEnum.getObjectTableDef());
		reportTableModelExcel = new ObjectTableModel(reportEnum.getObjectTableDefExcel());
		initGUI();
		initData();
	}

	/**
	 * Initierer GUI
	 */
	protected void initGUI() {
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		{
			panelSouth = new JPanel();
			this.add(panelSouth, BorderLayout.SOUTH);
			panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
			{
				buttonReport = new JButton();
				buttonReport.setIcon(IconEnum.ICON_REPORT.getIcon());
				panelSouth.add(buttonReport);
				buttonReport.setText("Rapport");
				buttonReport.setPreferredSize(new java.awt.Dimension(100, 25));
				buttonReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buttonReportActionPerformed(evt);
					}
				});
			}
		}
		panelCenter = new JPanel();
		this.add(panelCenter, BorderLayout.CENTER);
	}

	/**
	 * Kjør rapport
	 * 
	 * @param evt
	 */
	protected void buttonReportActionPerformed(ActionEvent evt) {
		GuiUtil.runInThread(reportFrame, "Rapport - "
				+ reportEnum.getReportString(), "Henter data....", this, null);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		reportFrame.enableComponents(enable);
		buttonReport.setEnabled(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if(object != null && object instanceof String){
			GuiUtil.showErrorMsgFrame(reportFrame,"Feil",object.toString());
		}
	}

	/**
	 * Laster data for rapport
	 */
	abstract protected void initData();
	/**
	 * Henter ut filter som er satt på gjeldende rapport
	 * @return filter for gjeldende rapport
	 */
	public Object getFilter(){
		return null;
	}

	/**
	 * @return Returns the reportTableModel.
	 */
	public ObjectTableModel getReportTableModel() {
		return reportTableModel;
	}

	/**
	 * @return Returns the reportTableModelExcel.
	 */
	public ObjectTableModel getReportTableModelExcel() {
		return reportTableModelExcel;
	}
	
	public HighlighterPipeline getHighlighterPipeline(){
		return null;
	}
	public Collection<Faktura> getInvoices(){
		return null;
	}
}
