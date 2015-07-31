package no.ica.elfa.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.elfa.gui.handlers.ArticleCommissionViewHandler;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer vising av artikkelkommisjon
 * 
 * @author abr99
 * 
 */
public class ArticleCommissionView {
	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JXTable tableArticles;

	/**
	 * 
	 */
	private JButton buttonEdit;

	/**
	 * 
	 */
	private JButton buttonNew;

	/**
	 * 
	 */
	private JButton buttonDelete;

	/**
	 * 
	 */
	private ArticleCommissionViewHandler viewHandler;

	/**
	 * @param handler
	 */
	public ArticleCommissionView(ArticleCommissionViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vindiskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		buttonCancel = viewHandler.getButtonCancel(window);
		tableArticles = viewHandler.getTableArticles();
		buttonEdit = viewHandler.getButtonEdit();
		buttonNew = viewHandler.getButtonNew();
		buttonDelete = viewHandler.getButtonDelete();
		viewHandler.initEventhandling();
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p,3dlu,p",
				"10dlu,p,3dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Artikler:", cc.xy(2, 2));
		builder.add(new JScrollPane(tableArticles), cc.xy(2, 4));
		builder.add(buildButtonPanel(), cc.xy(4, 4));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel), cc
				.xyw(2, 6,3));
		return builder.getPanel();
	}

	/**
	 * Bygger knappepanel
	 * 
	 * @return knappepanel
	 */
	private JPanel buildButtonPanel() {
		ButtonStackBuilder builder = new ButtonStackBuilder();
		builder.addGridded(buttonNew);
		builder.addRelatedGap();
		builder.addGridded(buttonEdit);
		builder.addRelatedGap();
		builder.addGridded(buttonDelete);
		builder.addRelatedGap();
		return builder.getPanel();
	}
}
