package no.ica.elfa.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import no.ica.elfa.gui.handlers.ArticleCommissionViewHandler;
import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.WindowInterface;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Håndterer visning av dialog for editering av artikkelkommisjon
 * 
 * @author abr99
 * 
 */
public class EditArticleCommissionView {
	/**
	 * 
	 */
	private ArticleCommissionViewHandler viewHandler;

	/**
	 * 
	 */
	private JComboBox comboBoxSupplier;

	/**
	 * 
	 */
	private JTextField textFieldArticleNo;

	/**
	 * 
	 */
	private JComboBox comboBoxType;

	/**
	 * 
	 */
	private JTextField textFieldArticleName;

	/**
	 * 
	 */
	private JTextField textFieldCode;

	/**
	 * 
	 */
	private JTextField textFieldCommission;

	/**
	 * 
	 */
	private JButton buttonSave;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	protected final ValidationResultModel validationResultModel = new DefaultValidationResultModel();

	/**
	 * @param handler
	 */
	public EditArticleCommissionView(ArticleCommissionViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		viewHandler.setPresentationModel(new ValidationUpdateHandler());
		comboBoxSupplier = viewHandler.getComboBoxSupplier();
		textFieldArticleNo = viewHandler.getTextFieldArticleNo();
		comboBoxType = viewHandler.getComboBoxType();
		textFieldArticleName = viewHandler.getTextFieldArticleName();
		textFieldCode = viewHandler.getTextFieldCode();
		textFieldCommission = viewHandler.getTextFieldCommission();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonSave = viewHandler.getButtonSave(validationResultModel);

	}

	/**
	 * Initierer validering
	 */
	private void initComponentAnnotations() {
		ValidationComponentUtils.setMandatory(comboBoxSupplier, true);
		ValidationComponentUtils.setMessageKey(comboBoxSupplier,
				"Artikkel.leverandør");

		ValidationComponentUtils.setMandatory(textFieldArticleNo, true);
		ValidationComponentUtils.setMessageKey(textFieldArticleNo,
				"Artikkel.nummer");

		ValidationComponentUtils.setMandatory(comboBoxType, true);
		ValidationComponentUtils.setMessageKey(comboBoxType, "Artikkel.type");

		ValidationComponentUtils.setMandatory(textFieldCommission, true);
		ValidationComponentUtils.setMessageKey(textFieldCommission,
				"Artikkel.provisjon");
	}

	/**
	 * Oppdaterer validering
	 */
	void updateValidationResult() {
		validationResultModel.setResult(viewHandler.getValidationResult());
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JComponent buildPanel(WindowInterface window) {
		initComponents(window);
		initComponentAnnotations();
		updateValidationResult();
		FormLayout layout = new FormLayout("10dlu,p,3dlu,p,10dlu",
				"10dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Leverandør:", cc.xy(2, 2));
		builder.add(comboBoxSupplier, cc.xy(4, 2));
		builder.addLabel("Artikkelnr:", cc.xy(2, 4));
		builder.add(textFieldArticleNo, cc.xy(4, 4));
		builder.addLabel("Type:", cc.xy(2, 6));
		builder.add(comboBoxType, cc.xy(4, 6));
		builder.addLabel("Navn:", cc.xy(2, 8));
		builder.add(textFieldArticleName, cc.xy(4, 8));
		builder.addLabel("Kode:", cc.xy(2, 10));
		builder.add(textFieldCode, cc.xy(4, 10));
		builder.addLabel("Provisjon:", cc.xy(2, 12));
		builder.add(textFieldCommission, cc.xy(4, 12));
		builder.add(
				ButtonBarFactory.buildCenteredBar(buttonSave, buttonCancel), cc
						.xyw(2, 14, 3));

		return new IconFeedbackPanel(validationResultModel, builder.getPanel());
	}

	/**
	 * Håndterer at validering skal oppdateres
	 * 
	 * @author abr99
	 * 
	 */
	final class ValidationUpdateHandler implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			updateValidationResult();
		}

	}
}
