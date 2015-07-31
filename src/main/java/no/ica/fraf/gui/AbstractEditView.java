package no.ica.fraf.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.CancelListener;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.model.AbstractModel;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.DefaultValidationResultModel;

/**
 * @author abr99
 * 
 * @param <E>
 * @param <T>
 */
public abstract class AbstractEditView<E, T> implements Closeable, Updateable,
		CancelListener {
	/**
	 * Gjeldende editeringsvindu
	 */
	WindowInterface window;

	/**
	 * Lagreknapp
	 */
	protected JButton buttonSave;

	/**
	 * Avbrytknapp
	 */
	protected JButton buttonCancel;

	/**
	 * Oppdater
	 */
	protected JButton buttonRefresh;

	/**
	 * Presentasjonsklasse for editeringsvindu
	 */
	protected PresentationModel presentationModel;

	/**
	 * Trigger som brukes til å trigge commit eller flush av objekter
	 */
	Trigger trigger;

	/**
	 * Validerinsresultat for objekt det editeres på
	 */
	protected final ValidationResultModel validationResultModel = new DefaultValidationResultModel();

	/**
	 * Klasse som håndterer vindusvariable
	 */
	protected AbstractViewHandler<T, E> viewHandler;

	/**
	 * True dersom det skal søkes
	 */
	protected boolean search = false;

	/**
	 * True dersom søkeknappen er trykket, for å skille på Avbrytknapp
	 */
	boolean searchPressed = false;

	/**
	 * Gjeldende objekt som vises i vindu
	 */
	AbstractModel<T, E> currentObject;

	/**
	 * True dersom vindu er kansellert
	 */
	private boolean canceled = false;

	/**
	 * Konstruktør
	 * 
	 * @param searchDialog
	 *            true dersom det er en søkedialog
	 * @param object
	 *            objektet som det skal editeres på
	 * @param aViewHandler
	 */
	public AbstractEditView(boolean searchDialog, AbstractModel<T, E> object,
			AbstractViewHandler<T, E> aViewHandler) {
		this(searchDialog, object, aViewHandler, null);
	}

	/**
	 * @param searchDialog
	 * @param object
	 * @param aViewHandler
	 * @param aPresentationModel
	 */
	public AbstractEditView(boolean searchDialog, AbstractModel<T, E> object,
			AbstractViewHandler<T, E> aViewHandler,
			PresentationModel aPresentationModel) {
		viewHandler = aViewHandler;

		trigger = new Trigger();

		if (aPresentationModel == null) {
			presentationModel = new PresentationModel(object, trigger);
		} else {
			presentationModel = aPresentationModel;
			presentationModel.setTriggerChannel(trigger);
		}

		search = searchDialog;
		currentObject = object;
	}

	/**
	 * Initierer komponenene for det spesifikke editeringsvinduet
	 * 
	 * @param aWindow
	 */
	protected abstract void initEditComponents(WindowInterface aWindow);

	/**
	 * Henter validator for gjeldende objekt
	 * 
	 * @param object
	 * @return validator
	 */
	protected abstract Validator getValidator(E object);

	/**
	 * Initierer validering for gjeldende komponenter
	 */
	protected abstract void initComponentAnnotations();

	/**
	 * Lager editeringspanel
	 * 
	 * @return editeringspanel
	 */
	protected abstract JComponent buildEditPanel();

	/**
	 * Initierer felles komponenter
	 * 
	 * @param window1
	 */
	private void initComponents(WindowInterface window1) {
		if (search) {
			buttonSave = new JButton(new SearchAction());
			buttonSave.setIcon(IconEnum.ICON_SEARCH.getIcon());
			buttonSave.setName("EditSearch" + viewHandler.getClassName());
			buttonCancel = new CancelButton(window1, this, false, "Avbryt",
					IconEnum.ICON_CANCEL, this);
		} else {
			buttonSave = new SaveButton(this, window1);
			buttonSave.setName("Save" + viewHandler.getClassName());
			buttonSave.setEnabled(false);
			buttonCancel = new CancelButton(window1, this);

		}

		buttonCancel.setName("EditCancel" + viewHandler.getClassName());
		initEditComponents(window1);

		buttonRefresh = new RefreshButton(this, window1);

	}

	/**
	 * Oppdaterer validering
	 */
	void updateValidationResult() {
		Validator validator = getValidator(currentObject
				.getBufferedObjectModel(presentationModel));
		if (validator != null) {
			ValidationResult result = validator.validate();
			validationResultModel.setResult(result);
		}
	}

	/**
	 * Initierer hendelsehåndtering
	 */
	private void initEventHandling() {
		if (!search) {
			PropertyConnector.connect(presentationModel,
					PresentationModel.PROPERTYNAME_BUFFERING, buttonSave,
					"enabled");
		}

		PropertyChangeListener handler = new ValidationUpdateHandler();
		//presentationModel.addPropertyChangeListener(PresentationModel.PROPERTYNAME_BUFFERING,handler);
		currentObject.addBufferChangeListener(handler, presentationModel);
	}

	/**
	 * Bygger editeringsvindu
	 * 
	 * @param windowInterface
	 * @return editeringsvindu
	 */
	public JComponent buildPanel(WindowInterface windowInterface) {
		window = windowInterface;
		initComponents(windowInterface);
		updateValidationResult();
		if (!search) {
			initComponentAnnotations();
		}
		initEventHandling();
		return buildEditPanel();
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		boolean canClose = true;
		if (presentationModel.isBuffering()) {
			if (GuiUtil.showConfirmDialog("Lagre?",
					"Det er gjort endringer, skal det lagres?")) {
				canClose = false;
			}
		}
		if (canClose) {
			currentObject.modelToView();
			viewHandler.setFlushing(true);
			trigger.triggerFlush();
			viewHandler.setFlushing(false);
			viewHandler.fireClose();
		}
		return canClose;
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doSave(no.ica.fraf.common.WindowInterface)
	 */
	@SuppressWarnings("unchecked")
	public void doSave(WindowInterface window1) {
		if (validationResultModel.hasErrors()) {
			GuiUtil.showErrorMsgDialog(null, "Rett feil",
					"Rett alle feil før lagring!");
		} else {
			E object = currentObject.getBufferedObjectModel(presentationModel);
			String errorString = viewHandler.checkSaveObject(object,
					presentationModel, window1);
			if (errorString == null || errorString.length() == 0) {
				trigger.triggerCommit();
				currentObject.viewToModel();
				viewHandler
						.saveObject((E) presentationModel.getBean(), window1);
			} else {

				GuiUtil.showErrorMsgDialog((Component) null, "Feil",
						errorString);

			}
		}

	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doDelete()
	 */
	public boolean doDelete() {
		return true;
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doNew()
	 */
	public void doNew() {
	}

	/**
	 * Klasse som håndterer oppdatering av validering når kunde endrer seg
	 * 
	 * @author atle.brekka
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

	/**
	 * Klasse som håndterer søking
	 * 
	 * @author atle.brekka
	 * 
	 */
	private class SearchAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Konstruktør
		 */
		public SearchAction() {
			super("Søk");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			trigger.triggerCommit();
			searchPressed = true;
			window.dispose();

		}
	}

	/**
	 * Sjekker om søkeknappen er trykket
	 * 
	 * @return true dersom søkeknapp er trykket
	 */
	public boolean isSearch() {
		return searchPressed;
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doRefresh()
	 */
	@SuppressWarnings("unchecked")
	public void doRefresh() {
		viewHandler.refreshObject((E) presentationModel.getBean());
		presentationModel.triggerCommit();

	}

	/**
	 * @see no.ica.elfa.gui.buttons.CancelListener#canceled()
	 */
	public void canceled() {
		canceled = true;
	}

	/**
	 * Sjekker om vindu er kansellert
	 * 
	 * @return true dersom kansellert
	 */
	public boolean isCanceled() {
		return canceled;
	}
	
	/**
	 * Legger til bufferlytter
	 * @param listener
	 */
	public void addBufferChangeListener(PropertyChangeListener listener){
		((AbstractModel)presentationModel.getBean()).addPropertyChangeListener(listener);
	}
}
