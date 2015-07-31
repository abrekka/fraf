package no.ica.elfa.gui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import no.ica.elfa.gui.EditArticleCommissionView;
import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.gui.model.ArticleCommissionModel;
import no.ica.elfa.model.ArticleCommission;
import no.ica.elfa.model.SupplierAccount;
import no.ica.elfa.service.ArticleCommissionManager;
import no.ica.elfa.service.SupplierAccountManager;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.ApplUserUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * Hjelpeklasse for visning av artikkelkommisjon
 * 
 * @author abr99
 * 
 */
public class ArticleCommissionViewHandler implements Closeable {
	/**
	 * 
	 */
	ArticleCommissionManager articleCommissionManager;

	/**
	 * 
	 */
	final ArrayListModel articleList;

	/**
	 * 
	 */
	final SelectionInList articleSelectionList;

	/**
	 * 
	 */
	private JButton buttonEdit;

	/**
	 * 
	 */
	private JButton buttonDelete;

	/**
	 * 
	 */
	private List<SupplierAccount> supplierList;

	/**
	 * 
	 */
	private SupplierAccountManager supplierAccountManager;

	/**
	 * 
	 */
	PresentationModel presentationModel;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * @param aArticleCommissionManager
	 * @param aSupplierAccountManager
	 * @param aApplUser
	 */
	public ArticleCommissionViewHandler(
			ArticleCommissionManager aArticleCommissionManager,
			SupplierAccountManager aSupplierAccountManager, ApplUser aApplUser) {
		articleCommissionManager = aArticleCommissionManager;
		supplierAccountManager = aSupplierAccountManager;

		articleList = new ArrayListModel(articleCommissionManager.findAll());

		articleSelectionList = new SelectionInList((ListModel) articleList);
		applUser = aApplUser;
	}

	/**
	 * @param window
	 * @return avbrytknapp
	 */
	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	/**
	 * @return ny-knapp
	 */
	public JButton getButtonNew() {
		JButton button = new JButton(new NewAction());
		if (!ApplUserUtil.isAdministrator(applUser)) {
			button.setEnabled(false);
		}
		return button;
	}

	/**
	 * @return editerknapp
	 */
	public JButton getButtonEdit() {
		buttonEdit = new JButton(new EditAction());
		buttonEdit.setEnabled(false);
		return buttonEdit;
	}

	/**
	 * @return sletteknapp
	 */
	public JButton getButtonDelete() {
		buttonDelete = new JButton(new DeleteAction());
		buttonDelete.setEnabled(false);
		return buttonDelete;
	}

	/**
	 * Initierer hendelsehåndtering
	 */
	public void initEventhandling() {
		articleSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());

	}

	/**
	 * @param validationResultModel
	 * @return lagreknapp
	 */
	public JButton getButtonSave(ValidationResultModel validationResultModel) {
		JButton button = new JButton(new SaveAction(validationResultModel));
		PropertyConnector.connect(presentationModel,
				PresentationModel.PROPERTYNAME_BUFFERING, button, "enabled");
		button.setEnabled(false);
		button.setIcon(IconEnum.ICON_SAVE.getIcon());
		return button;
	}

	/**
	 * @return comboboks for leverandør
	 */
	public JComboBox getComboBoxSupplier() {
		if (supplierList == null) {
			supplierList = supplierAccountManager.findAll();
		}
		return new JComboBox(
				new ComboBoxAdapter(
						supplierList,
						presentationModel
								.getBufferedModel(ArticleCommissionModel.PROPERTY_SUPPLIER_ACCOUNT)));
	}

	/**
	 * @return tekstfelt for artikelnummer
	 */
	public JTextField getTextFieldArticleNo() {
		DecimalFormat format = new DecimalFormat();
		format.setParseBigDecimal(true);
		return BasicComponentFactory.createFormattedTextField(presentationModel
				.getBufferedModel(ArticleCommissionModel.PROPERTY_ARTICLE_NO),
				format);
	}

	/**
	 * @return comboboks for type
	 */
	public JComboBox getComboBoxType() {
		return new JComboBox(
				new ComboBoxAdapter(
						new String[] { "SUP", "STO" },
						presentationModel
								.getBufferedModel(ArticleCommissionModel.PROPERTY_COMMISSION_TYPE)));
	}

	/**
	 * @return tekstfelt for artikkelnavn
	 */
	public JTextField getTextFieldArticleName() {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(ArticleCommissionModel.PROPERTY_ARTICLE_NAME));
	}

	/**
	 * @return tekstfelt for kode
	 */
	public JTextField getTextFieldCode() {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(ArticleCommissionModel.PROPERTY_CODE));
	}

	/**
	 * @return tekstfelt for kommisjon
	 */
	public JTextField getTextFieldCommission() {
		DecimalFormat format = new DecimalFormat();
		format.setParseBigDecimal(true);
		return BasicComponentFactory
				.createFormattedTextField(
						presentationModel
								.getBufferedModel(ArticleCommissionModel.PROPERTY_COMMISSION_PERCENTAGE),
						format);
	}

	/**
	 * Enabler/disabler knapper
	 */
	void enableButtons() {
		boolean hasSelection = articleSelectionList.hasSelection();
		buttonEdit.setEnabled(hasSelection);
		buttonDelete.setEnabled(hasSelection);
	}

	/**
	 * @return tabell med artikler
	 */
	public JXTable getTableArticles() {
		JXTable table = new JXTable();
		table.setModel(new ArticleTableModel(articleSelectionList));
		table.setSelectionModel(new SingleListSelectionAdapter(
				articleSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.packAll();
		table.addMouseListener(new TableMouseListener());

		return table;
	}

	/**
	 * Setter presentasjonsmodell som skal brukes mot GUI
	 * 
	 * @param validationUpdateHandler
	 */
	public void setPresentationModel(
			PropertyChangeListener validationUpdateHandler) {
		ArticleCommission articleCommission = (ArticleCommission) articleSelectionList
				.getSelection();
		if (articleCommission != null) {
			presentationModel = new PresentationModel(
					new ArticleCommissionModel(articleCommission));
		}

		((ArticleCommissionModel) presentationModel.getBean())
				.addBufferChangeListener(validationUpdateHandler,
						presentationModel);
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		if (presentationModel != null && presentationModel.isBuffering()) {
			if (!GuiUtil.showConfirmDialog(
					"Ønsker du å avslutte uten å lagre?", "Lagre endringer?")) {

				return false;
			}
			presentationModel.triggerFlush();
		}

		return true;
	}

	/**
	 * Tabellmodell for artikler
	 * 
	 * @author abr99
	 * 
	 */
	private final static class ArticleTableModel extends AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Leverandør", "Artikkelnr",
				"Type", "Navn", "Kode", "Provisjon" };

		/**
		 * @param listModel
		 */
		public ArticleTableModel(ListModel listModel) {
			super(listModel, COLUMNS);
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			ArticleCommission article = (ArticleCommission) getRow(row);
			switch (column) {
			case 0:
				return article.getSupplierAccount();
			case 1:
				return article.getArticleNo();
			case 2:
				return article.getCommissionType();
			case 3:
				return article.getArticleName();
			case 4:
				return article.getCode();
			case 5:
				return article.getCommissionPercentage();
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

	}

	/**
	 * Editer artikkel
	 */
	void doEditArticle() {
		EditArticleCommissionView editArticleCommissionView = new EditArticleCommissionView(
				this);
		JDialog dialog = new JDialog(FrafMain.getInstance(), "Artikkel", true);
		WindowInterface window = new JDialogAdapter(dialog);
		window.add(editArticleCommissionView.buildPanel(window));
		window.pack();
		GuiUtil.locateOnScreenCenter(window);
		window.setVisible(true);
	}

	/**
	 * Håndterer innlegging av ny artikkel
	 * 
	 * @author abr99
	 * 
	 */
	private class NewAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public NewAction() {
			super("Ny artikkel...");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			ArticleCommission newArticle = new ArticleCommission();
			articleList.add(0, newArticle);
			articleSelectionList.setSelection(newArticle);
			doEditArticle();

		}
	}

	/**
	 * Håndterer editering av artikkel
	 * 
	 * @author abr99
	 * 
	 */
	private class EditAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public EditAction() {
			super("Editer artikkel...");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			doEditArticle();

		}
	}

	/**
	 * Håndterer sletting av artikkel
	 * 
	 * @author abr99
	 * 
	 */
	private class DeleteAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public DeleteAction() {
			super("Slett artikkel");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			ArticleCommission article = (ArticleCommission) articleSelectionList
					.getSelection();

			if (article != null) {
				if (GuiUtil.showConfirmDialog(
						"Vil du virkelig slette artikkel?", "Slette?")) {
					articleCommissionManager.removeArticleCommission(article);
					articleList.remove(article);
				}
			}

		}
	}

	/**
	 * Håndterer valg av artikkel i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			enableButtons();

		}

	}

	/**
	 * Håndterer lagring av artikkel
	 * 
	 * @author abr99
	 * 
	 */
	private class SaveAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private ValidationResultModel validationResultModel;

		/**
		 * @param aValidationResultModel
		 */
		public SaveAction(ValidationResultModel aValidationResultModel) {
			super("Lagre");
			validationResultModel = aValidationResultModel;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (validationResultModel.hasErrors()) {
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance()
						.getDesktopPane(), "Feil",
						"Alle feil må rettes før lagring");
			} else {
				presentationModel.triggerCommit();
				ArticleCommission article = ((ArticleCommissionModel) presentationModel
						.getBean()).getArticleCommission();
				articleCommissionManager.saveArticleCommission(article);
			}

		}
	}

	/**
	 * Håndterer museklikk
	 * 
	 * @author abr99
	 * 
	 */
	private class TableMouseListener extends MouseAdapter {

		/**
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			if (SwingUtilities.isLeftMouseButton(event)
					&& event.getClickCount() == 2) {
				doEditArticle();
			}
		}

	}

	/**
	 * @return validering
	 */
	public ValidationResult getValidationResult() {
		Validator validator = new ArticleCommissionValidator(presentationModel);
		return validator.validate();
	}

	/**
	 * Validatorklasse som validerer artikkel
	 * 
	 * @author abr99
	 * 
	 */
	private class ArticleCommissionValidator implements Validator {

		/**
		 * Holds the order to be validated.
		 */
		private PresentationModel validationPresentationModel;

		/**
		 * Constructs an OrderValidator on the given Order.
		 * 
		 * @param aPresentationModel
		 */
		public ArticleCommissionValidator(PresentationModel aPresentationModel) {
			this.validationPresentationModel = aPresentationModel;
		}

		// Validation
		// *************************************************************

		/**
		 * Validates this Validator's Order and returns the result as an
		 * instance of {@link ValidationResult}.
		 * 
		 * @return the ValidationResult of the order validation
		 */
		public ValidationResult validate() {
			PropertyValidationSupport support = new PropertyValidationSupport(
					validationPresentationModel, "Artikkel");

			if (ValidationUtils
					.isBlank(ModelUtil
							.nullToString(validationPresentationModel
									.getBufferedValue(ArticleCommissionModel.PROPERTY_SUPPLIER_ACCOUNT)))) {
				support.addError("leverandør", "må settes");
			}

			if (ValidationUtils
					.isBlank(ModelUtil
							.nullToString(validationPresentationModel
									.getBufferedValue(ArticleCommissionModel.PROPERTY_ARTICLE_NO)))) {
				support.addError("nummer", "må settes");
			}

			if (ValidationUtils
					.isBlank(ModelUtil
							.nullToString(validationPresentationModel
									.getBufferedValue(ArticleCommissionModel.PROPERTY_COMMISSION_TYPE)))) {
				support.addError("type", "må settes");
			}

			if (ValidationUtils
					.isBlank(ModelUtil
							.nullToString(validationPresentationModel
									.getBufferedValue(ArticleCommissionModel.PROPERTY_COMMISSION_PERCENTAGE)))) {
				support.addError("provisjon", "må settes");
			}

			return support.getResult();
		}

	}

}
