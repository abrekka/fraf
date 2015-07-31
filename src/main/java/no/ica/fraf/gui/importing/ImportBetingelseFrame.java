package no.ica.fraf.gui.importing;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.ImportEnum;
import no.ica.fraf.model.ApplUser;

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
 * Vindu som brukes til å importere budsjett eller betingelser
 * 
 * @author abr99
 * 
 */
public class ImportBetingelseFrame extends javax.swing.JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private JButton buttonCancel;

    /**
     * Bruker
     */
    private ApplUser currentApplUser;

    /**
     * Gjeldende importtype
     */
    private ImportEnum currentImportType;

    /**
     * Konstruktør
     * 
     * @param applUser
     * @param importType
     */
    public ImportBetingelseFrame(ApplUser applUser, ImportEnum importType) {
        super();
        currentApplUser = applUser;
        currentImportType = importType;

        initGUI();
        setFrameIcon(IconEnum.ICON_FRAF.getIcon());
    }

    /**
     * Initierer GUI
     */
    private void initGUI() {
        try {
            this.setPreferredSize(new java.awt.Dimension(1000, 500));
            this.setBounds(0, 0, 1000, 500);
            BorderLayout thisLayout = new BorderLayout();
            this.getContentPane().setLayout(thisLayout);
            setVisible(true);
            this.setTitle("Import av " + currentImportType.getName());
            this.setClosable(true);
            this.setMaximizable(true);
            this.setIconifiable(true);
            this.setResizable(true);
            {
                JPanel panelNorth = new JPanel();
                GridBagLayout panelNorthLayout = new GridBagLayout();
                panelNorth.setLayout(panelNorthLayout);
                this.getContentPane().add(panelNorth, BorderLayout.NORTH);
                panelNorth.setPreferredSize(new java.awt.Dimension(10, 10));
            }
            {
                JPanel panelSouth = new JPanel();
                this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
                panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
                {
                    buttonCancel = new JButton();
                    buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
                    buttonCancel.setMnemonic(KeyEvent.VK_A);
                    panelSouth.add(buttonCancel);
                    buttonCancel.setText("Avbryt");
                    buttonCancel.setPreferredSize(new java.awt.Dimension(100,
                            25));
                    buttonCancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            buttonCancelActionPerformed(evt);
                        }
                    });
                }
            }
            {
                JTabbedPane tabbedPaneImport = new JTabbedPane();
                this.getContentPane()
                        .add(tabbedPaneImport, BorderLayout.CENTER);
                {
                    PanelImport panelImport = new PanelImport(this,
                            currentApplUser, currentImportType);
                    tabbedPaneImport.addTab("Import", IconEnum.ICON_IMPORT
                            .getIcon(), panelImport, null);
                }
                {
                    PanelImportLog panelImportLog = new PanelImportLog(this,
                            currentImportType, currentApplUser);
                    tabbedPaneImport.addTab("Logg",
                            IconEnum.ICON_LOG.getIcon(), panelImportLog, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lukker vindu
     * 
     * @param evt
     */
    void buttonCancelActionPerformed(ActionEvent evt) {
        dispose();
    }

    /**
     * Enabler/disabler knapper
     * 
     * @param enable
     */
    public void enableComponents(boolean enable) {
        buttonCancel.setEnabled(enable);
    }

	/**
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		buttonCancel.setEnabled(enabled);
		super.setEnabled(enabled);
	}

}
