package no.ica.fraf.gui.importing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.ImportEnum;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.TableSorter;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;

/**
 * Panel for overiskt over importbunter
 */
/**
 * @author abr99
 * 
 */
public class PanelImportLog extends javax.swing.JPanel implements Threadable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    JTable tableLog;

    /**
     * 
     */
    TableSorter tableSorterLog;

    /**
     * 
     */
    private TableSorter tableSorterCondition;

    /**
     * Frame som bruker dette panelet
     */
    JInternalFrame internalFrame;

    /**
     * 
     */
    private JTable tableConditions;

    /**
     * 
     */
    JButton buttonRollback;

    /**
     * Interface som brukes til import
     */
    ImportInterface currentImport;

    /**
     * Bruker
     */
    private ApplUser currentApplUser;

    /**
     * 
     */
    ObjectTableModel<Batchable> batchTableModel;

    /**
     * 
     */
    ObjectTableModel<Object> detailsTableModel;

    /**
     * Konstruktør
     * 
     * @param aInternalFrame
     *            frame som bruker panel
     * @param importType
     *            type import, betingelse eller budsjett
     * @param applUser
     *            bruker
     */
    public PanelImportLog(JInternalFrame aInternalFrame, ImportEnum importType,
            ApplUser applUser) {
        super();
        internalFrame = aInternalFrame;
        currentApplUser = applUser;

        currentImport = getImportClass(importType);

        initGUI();
        initTables();
    }

    /**
     * Henter ut riktig implementasjon av ImportInterface basert på importtype
     * 
     * @param importType
     *            betingelse eller budsjett
     * @return implementasjon av ImportInterface
     */
    private ImportInterface getImportClass(ImportEnum importType) {
        switch (importType) {
        case IMPORT_BUDGET:
            return new ImportBudget(currentApplUser);
        case IMPORT_CONDITION:
            return new ImportCondition(internalFrame, currentApplUser);
        case IMPORT_INVOICE:
            return new ImportInvoice(currentApplUser);
        }
        return null;
    }

    /**
     * Initierer GUI
     */
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(597, 300));
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent evt) {
                    rootComponentShown(evt);
                }
            });
            {
                JSplitPane splitPaneLog = new JSplitPane();
                this.add(splitPaneLog, BorderLayout.CENTER);
                splitPaneLog.setPreferredSize(new java.awt.Dimension(220, 28));
                {
                    JScrollPane scrollPaneLog = new JScrollPane();
                    splitPaneLog.add(scrollPaneLog, JSplitPane.LEFT);
                    scrollPaneLog.setPreferredSize(new java.awt.Dimension(260,
                            3));

                    {
                        tableLog = new JTable();
                        scrollPaneLog.setViewportView(tableLog);
                        tableLog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        tableLog.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent evt) {
                                tableLogMouseClicked(evt);
                            }
                        });
                    }
                }
                {
                    JScrollPane scrollPaneConditions = new JScrollPane();
                    splitPaneLog.add(scrollPaneConditions, JSplitPane.RIGHT);
                    {
                        tableConditions = new JTable();
                        scrollPaneConditions.setViewportView(tableConditions);
                        tableConditions
                                .setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    }
                }
            }
            {
                JPanel panelSouth = new JPanel();
                FlowLayout panelSouthLayout = new FlowLayout();
                panelSouthLayout.setAlignment(FlowLayout.LEFT);
                panelSouth.setLayout(panelSouthLayout);
                this.add(panelSouth, BorderLayout.SOUTH);
                panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
                {
                    buttonRollback = new JButton();
                    buttonRollback.setIcon(IconEnum.ICON_ROLLBACK.getIcon());
                    buttonRollback.setMnemonic(KeyEvent.VK_T);
                    panelSouth.add(buttonRollback);
                    buttonRollback.setText("Tilbakerull");
                    buttonRollback.setEnabled(false);
                    buttonRollback.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            buttonRollbackActionPerformed(evt);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initierer tabller
     */
    private void initTables() {
        batchTableModel = currentImport.getTableModelBatch();
        batchTableModel.setEditable(false);
        tableSorterLog = new TableSorter();
        tableSorterLog.setTableModel(batchTableModel);
        tableSorterLog.setTableHeader(tableLog.getTableHeader());

        tableLog.setModel(tableSorterLog);

        currentImport.setColumnWidthsBatch(tableLog);

        // Betingelser
        detailsTableModel = currentImport.getTableModelDetails();
        detailsTableModel.setEditable(false);
        tableSorterCondition = new TableSorter();
        tableSorterCondition.setTableModel(detailsTableModel);
        tableSorterCondition.setTableHeader(tableConditions.getTableHeader());

        tableConditions.setModel(tableSorterCondition);

        currentImport.setColumnWidthsDetails(tableConditions);

    }

    /**
     * Kjøres når panel skal vises
     * 
     * @param evt
     */
    void rootComponentShown(ComponentEvent evt) {
        loadData();
    }

    /**
     * Laster inn data
     */
    public void loadData() {
        GuiUtil.runInThread(internalFrame, "Feil", "Henter bunter", this, null);
    }

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
        List<Batchable> list = currentImport.findBatches();
        batchTableModel.setData(list);
        return new Boolean(true);
    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
     */
    public void doWhenFinished(Object object) {
    }

    /**
     * Laster detaljer
     */
    private void loadDetails() {
        GuiUtil.runInThread(internalFrame, "Detaljer", "Laster detaljer...",
                new DetailLoader(), null);
    }

    /**
     * Tabeller klikket på, enabler knapp for tilbakerulling av bunt
     * 
     * @param evt
     */
    void tableLogMouseClicked(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            buttonRollback.setEnabled(true);
            loadDetails();
        }
    }

    /**
     * Rull tilbake bunt
     * 
     * @param evt
     */
    void buttonRollbackActionPerformed(ActionEvent evt) {
        if (!GuiUtil.showConfirmFrame(internalFrame, "Rulle tilbake?",
                "Vil du virkelig rulle tilbake import")) {
            return;
        }
        buttonRollback.setEnabled(false);
        GuiUtil.runInThread(internalFrame, "Ruller tilbake bunt",
                "Ruller tilbake...", new Rollback(), null);
    }

    /**
     * Klasse som håndterer lasting av detaljer om en bunt
     * 
     * @author abr99
     * 
     */
    private class DetailLoader implements Threadable {

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
            if (tableLog.getSelectedRow() != -1) {
                Object object = batchTableModel.getObjectAtIndex(tableSorterLog
                        .modelIndex(tableLog.getSelectedRow()));

                List<Object> list = currentImport.findDetailsByBatch(object);
                detailsTableModel.setData(list);
            } else {
                detailsTableModel.setData(null);
            }
            return new Boolean(true);
        }

        /**
         * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
         */
        public void doWhenFinished(Object object) {
        }

    }

    /**
     * Klasse som håndterer tilbakerulling
     * 
     * @author abr99
     * 
     */
    private class Rollback implements Threadable {

        /**
         * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
         */
        public void enableComponents(boolean enable) {
            //buttonRollback.setEnabled(enable);

        }

        /**
         * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
         *      javax.swing.JLabel)
         */
        public Object doWork(Object[] params, JLabel labelInfo) {
            try {
                Object object = batchTableModel.getObjectAtIndex(tableSorterLog
                        .modelIndex(tableLog.getSelectedRow()));

                currentImport.rollback(object);

            } catch (FrafException e) {
                String msg = GuiUtil.getUserExceptionMsg(e);
                e.printStackTrace();
                return msg;
            }
            return null;
        }

        /**
         * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
         */
        public void doWhenFinished(Object object) {
            if (object != null) {
                GuiUtil.showErrorMsgFrame(internalFrame, "Feil",
                        (String) object);
            }
            loadData();
            detailsTableModel.deleteData();
        }

    }
}
