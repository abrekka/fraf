package no.ica.fraf.gui.readmirror;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.PanelBatch;
import no.ica.fraf.gui.model.interfaces.BatchSelectionListener;

import com.google.inject.Inject;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Dialog som brukes for å lese inn speilede betingelser
 * 
 * @author abr99
 * 
 */
public class ReadMirrorFrame extends javax.swing.JInternalFrame implements
        BatchSelectionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private JPanel panelSouth;

    /**
     * 
     */
    private JButton buttonRead;

    /**
     * 
     */
    private JPanel panelNorth;

    /**
     * 
     */
    private JMonthChooser monthChooser;

    /**
     * 
     */
    private JLabel labelPeriode;

    /**
     * 
     */
    private JButton buttonCancel;

    /**
     * DAO for speilet betingelse
     */

    /**
     * 
     */
    private JTabbedPane tabbedPane;

    /**
     * 
     */
    private JYearChooser yearChooser;

    /**
     * 
     */
    private JLabel labelYear;

    /**
     * Datoformaterer
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy.MM.dd");

    /**
     * Bruker
     */
    private Login login;

    /**
     * 
     */
    private JTextField textFieldTo;

    /**
     * 
     */
    private JLabel labelTo;

    /**
     * 
     */
    private JTextField textFieldFrom;

    /**
     * 
     */
    private JLabel labelFrom;

    /**
     * 
     */
    private PanelMirror panelMirror;
    private BuntDAO buntDAO;
    private BuntPkgDAO buntPkgDAO;
    @Inject
    public ReadMirrorFrame(final Login aLogin,BuntDAO aBuntDAO,BuntPkgDAO aBuntPkgDAO) {
        super();
        buntDAO =aBuntDAO;
        buntPkgDAO =aBuntPkgDAO;
        login = aLogin;
        initGUI();
        setFrameIcon(IconEnum.ICON_FRAF.getIcon());

    }

    /**
     * Initierer GUI
     */
    private void initGUI() {
        try {
            setPreferredSize(new Dimension(600, 500));
            this.setBounds(25, 25, 600, 500);
            BorderLayout thisLayout = new BorderLayout();
            this.getContentPane().setLayout(thisLayout);
            setVisible(true);
            this.setTitle("Les inn speilet betingelser");
            this.setIconifiable(true);
            this.setMaximizable(true);
            this.setResizable(true);
            {
                panelSouth = new JPanel();
                this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
                panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
                {
                    buttonCancel = new JButton();
                    panelSouth.add(buttonCancel);
                    buttonCancel.setText("Avbryt");
                    buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
                    buttonCancel.setMnemonic(KeyEvent.VK_A);
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
                panelNorth = new JPanel();
                GridBagLayout panelNorthLayout = new GridBagLayout();
                panelNorth.setLayout(panelNorthLayout);
                this.getContentPane().add(panelNorth, BorderLayout.NORTH);
                panelNorth.setPreferredSize(new java.awt.Dimension(10, 100));
                panelNorth.setBorder(BorderFactory
                        .createTitledBorder("Utvalgskriterie"));
                {
                    buttonRead = new JButton();
                    panelNorth.add(buttonRead, new GridBagConstraints(0, 1, 8,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(10, 0, 0, 0),
                            0, 0));
                    buttonRead.setText("Les inn");
                    buttonRead.setIcon(IconEnum.ICON_READ.getIcon());
                    buttonRead.setMnemonic(KeyEvent.VK_L);
                    buttonRead
                            .setPreferredSize(new java.awt.Dimension(100, 25));
                    buttonRead.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            buttonReadActionPerformed(evt);
                        }
                    });
                }
                {
                    labelPeriode = new JLabel();
                    panelNorth.add(labelPeriode, new GridBagConstraints(2, 0,
                            1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    labelPeriode.setText("Periode:");
                }
                {
                    monthChooser = new JMonthChooser();
                    panelNorth.add(monthChooser, new GridBagConstraints(3, 0,
                            1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    monthChooser.setPreferredSize(new java.awt.Dimension(110,
                            20));
                }
                {
                    labelYear = new JLabel();
                    panelNorth.add(labelYear, new GridBagConstraints(0, 0, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
                            0));
                    labelYear.setText("År:");
                }
                {
                    yearChooser = new JYearChooser();
                    panelNorth.add(yearChooser, new GridBagConstraints(1, 0, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
                            0));
                    yearChooser
                            .setPreferredSize(new java.awt.Dimension(80, 20));
                }
                {
                    labelFrom = new JLabel();
                    panelNorth.add(labelFrom, new GridBagConstraints(4, 0, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    labelFrom.setText("Fra avd:");
                }
                {
                    textFieldFrom = new JTextField();
                    panelNorth.add(textFieldFrom, new GridBagConstraints(5, 0,
                            1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
                            0));
                    textFieldFrom.setPreferredSize(new java.awt.Dimension(60,
                            20));
                }
                {
                    labelTo = new JLabel();
                    panelNorth.add(labelTo, new GridBagConstraints(6, 0, 1, 1,
                            0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    labelTo.setText("Til avd:");
                }
                {
                    textFieldTo = new JTextField();
                    panelNorth.add(textFieldTo, new GridBagConstraints(7, 0, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
                            0));
                    textFieldTo
                            .setPreferredSize(new java.awt.Dimension(60, 20));
                }
            }
            {
                tabbedPane = new JTabbedPane();
                this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
                {
                    panelMirror = new PanelMirror(this);
                    tabbedPane.addTab("Speilet betingelser",
                            IconEnum.ICON_MIRROR.getIcon(), panelMirror, null);
                }
                {
                    PanelBatch panelBatch = new PanelBatch(this,
                            BuntTypeEnum.BATCH_TYPE_MIRROR,buntDAO,buntPkgDAO);
                    tabbedPane.addTab("Logg", IconEnum.ICON_LOG.getIcon(),
                            panelBatch, null);
                    panelBatch.addBatchSelectionListener(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lukk vindu
     * 
     * @param evt
     */
    void buttonCancelActionPerformed(ActionEvent evt) {
        dispose();
    }

    /**
     * Importer betingelser
     * 
     * @param evt
     */
    void buttonReadActionPerformed(ActionEvent evt) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearChooser.getYear(), monthChooser.getMonth(), 1);
        Date contractDate = null;
        Integer fromDep = null;
        Integer toDep = null;

        if (textFieldFrom.getText().length() == 0) {
            fromDep = 1000;
        } else {
            fromDep = Integer.valueOf(textFieldFrom.getText());
        }

        if (textFieldTo.getText().length() == 0) {
            toDep = 9999;
        } else {
            toDep = Integer.valueOf(textFieldTo.getText());
        }
        try {
            contractDate = dateFormat.parse(dateFormat.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        panelMirror.importMirrorConditions(contractDate, login.getApplUser(),
                fromDep, toDep);

    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.BatchSelectionListener#batchSelected(java.lang.Integer)
     */
    public void batchSelected(Integer batchId) {
        panelMirror.loadDataFromBatch(batchId);

    }

    /**
     * Enabler/disabler knapper
     * @param enable
     */
    public void enableComponents(boolean enable){
    	buttonCancel.setEnabled(enable);
    	buttonRead.setEnabled(enable);
    	tabbedPane.setEnabled(enable);
    }
}
