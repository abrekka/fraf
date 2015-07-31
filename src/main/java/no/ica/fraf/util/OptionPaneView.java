package no.ica.fraf.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXList;

import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class OptionPaneView<T> implements Closeable{
	private JXList listOptions;
	private JButton buttonOk;
	private ArrayListModel objectList;
	
	public OptionPaneView(Collection<T> objects){
		objectList=new ArrayListModel(objects);
	}
	
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,120dlu,10dlu", "10dlu,p,3dlu,100dlu,3dlu,p,3dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.addLabel("Bruk Ctrl-tasten for å velge flere",cc.xy(2,2));
		builder.add(new JScrollPane(listOptions),cc.xy(2,4));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonOk),cc.xy(2,6));
		
		return builder.getPanel();
	}

	public boolean canClose(String actionString) {
		return true;
	}
	
	public List<T> getSelectedObjects(){
		Object[] selectedObjects =listOptions.getSelectedValues();
		List<T> list = new ArrayList<T>();
		if(selectedObjects!=null){
			for(int i=0;i<selectedObjects.length;i++){
				list.add((T)selectedObjects[i]);
			}
		}
		return list;
	}
	
	private void initComponents(WindowInterface window){
		listOptions=new JXList(objectList);
		buttonOk=new JButton(new OkAction(window));
	}
	
	private class OkAction extends AbstractAction{
		private WindowInterface window;
		public OkAction(WindowInterface aWindow){
			super("Ok");
			window=aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			window.dispose();
			
		}
	}

}
