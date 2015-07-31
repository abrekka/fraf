package no.ica.fraf.gui;

import java.awt.Component;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Region;
import no.ica.fraf.model.Rik2KjedeV;
import no.ica.fraf.model.Rik2RegionV;
import no.ica.fraf.util.DataListUtil;
import no.ica.fraf.util.DataListUtilFactory;
import no.ica.fraf.util.ModelUtil;

public class ComboBoxViewTest {
	private JComboBox comboBoxRegion;
	private JComboBox comboBoxChain;

	public Component buildPanel(WindowInterface window) {
		initComponents();
		FormLayout layout = new FormLayout("p,3dlu,p",
				"p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.addLabel("Regioner:",cc.xy(1,1));
		builder.add(comboBoxRegion,cc.xy(3, 1));
		builder.addLabel("Kjeder:",cc.xy(1,3));
		builder.add(comboBoxChain,cc.xy(3, 3));
		return builder.getPanel();
	}

	private void initComponents() {
		DataListUtil dataListUtil = DataListUtilFactory
				.getInstance(new ModelUtil());
		List<String> regions = dataListUtil.getRegionNames();
		List<Chain> chains = dataListUtil.getKjeder();
		if (regions != null) {
			regions.add(0, "alle");
			// ComboBoxModel modelRegion = new
			// DefaultComboBoxModel(regions.toArray());
			ComboBoxModel modelRegion = new DefaultComboBoxModel(new Object[] {
					//regions.get(0), 
					regions.get(1),
					regions.get(2)
					//chains.get(2), 
					//"test", "test2"
					});
			comboBoxRegion = new JComboBox();
			comboBoxRegion.setModel(modelRegion);
		}

		

		if (chains != null) {
			chains.add(0, new Rik2KjedeV(null, "alle", null));
			ComboBoxModel modelChain = new DefaultComboBoxModel(chains
					.toArray());
			comboBoxChain = new JComboBox();
			comboBoxChain.setModel(modelChain);
		}
	}
}
