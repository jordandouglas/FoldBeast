package foldbeast.app.beauti;


import java.util.ArrayList;
import java.util.List;


import beastfx.app.inputeditor.BeautiDoc;
import beastfx.app.inputeditor.SiteModelInputEditor;
import foldbeast.sitemodel.FoldBeastModelTestSiteModelDSSP;
import foldbeast.substitutionmodel.DSSPMatrix;
import foldbeast.substitutionmodel.FoldSeekSubstitutionMatrix;
import foldbeast.substitutionmodel.NullModelDSSP;
import foldbeast.substitutionmodel.ScoreBasedSubstitutionModel;
import foldbeast.substitutionmodel.SubstitutionModelTestDSSP;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import beast.base.core.BEASTInterface;
import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.evolution.substitutionmodel.SubstitutionModel;

public class FoldBeastModelTestSiteModelInputEditorDSSP extends SiteModelInputEditor {
	static List<ScoreBasedSubstitutionModel> availableModels;
	
	@Override
    public Class<?> type() {
        return FoldBeastModelTestSiteModelDSSP.class;
    }
	
	public FoldBeastModelTestSiteModelInputEditorDSSP() {
		
	}
    
	public FoldBeastModelTestSiteModelInputEditorDSSP(BeautiDoc doc) {
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
		super(doc);
		if (availableModels == null) {		
			availableModels = new ArrayList<>();
			availableModels.add(new NullModelDSSP());
			availableModels.add(new DSSPMatrix());
		}
		
	}

	@Override
	public void init(Input<?> input, BEASTInterface plugin, int itemNr, ExpandOption bExpandOption, boolean bAddButtons) {
		//System.out.println("ZZZZZZZZZZZZZZZZZZZ");
		super.init(input, plugin, itemNr, bExpandOption, bAddButtons);
		
		
		FoldBeastModelTestSiteModelDSSP siteModel = (FoldBeastModelTestSiteModelDSSP)input.get();
		SubstitutionModel sm = siteModel.substModelInput.get();
		SubstitutionModelTestDSSP substModel = (SubstitutionModelTestDSSP) sm;
		List<ScoreBasedSubstitutionModel> models = substModel.substModelInput.get();

		VBox box = (VBox)((HBox)pane.getChildren().get(0)).getChildren().get(0);
		for (ScoreBasedSubstitutionModel m : availableModels) {
			addCheckBox(m, models, box);
		}
		//validate();
	}

	private void addCheckBox(ScoreBasedSubstitutionModel m, List<ScoreBasedSubstitutionModel> models, VBox box) {
		String modelName = m.getClass().getSimpleName();
		String modelLabel = modelName;
		if (modelLabel.startsWith("FoldBeastDSSP")) {
			modelLabel = modelLabel.substring(14);
		}
	
		CheckBox checkBox = new CheckBox(modelLabel);
		checkBox.setId(modelName);
		
		boolean selected = false;
		for (ScoreBasedSubstitutionModel m0 : models) {
			if (m0.getClass() == m.getClass()) {
				selected = true;
				break;
			}
		}
		checkBox.setSelected(selected);
		checkBox.setOnAction(e -> {
			CheckBox b = (CheckBox) e.getSource();
			String label = b.getId();
			setModel(label, b.isSelected());
		});
		checkBox.setPadding(new Insets(5));
		box.getChildren().add(checkBox);
	}

	private void setModel(String label, boolean selected) {
		FoldBeastModelTestSiteModelDSSP siteModel = (FoldBeastModelTestSiteModelDSSP) m_input.get();
		SubstitutionModel sm = siteModel.substModelInput.get();
		SubstitutionModelTestDSSP substModel = (SubstitutionModelTestDSSP) sm;
		List<ScoreBasedSubstitutionModel> models = substModel.substModelInput.get();
		if (!selected) {
			for (int i = 0; i< models.size(); i++) {
				ScoreBasedSubstitutionModel m = models.get(i);
				if (m.getClass().getSimpleName().equals(label)) {
					models.remove(i);
					return;
				}
			}
		} else {
			// make sure it is not already in the list
			for (int i = 0; i< models.size(); i++) {
				ScoreBasedSubstitutionModel m = models.get(i);
				if (m.getClass().getSimpleName().equals(label)) {
					return;
				}
			}
			// add new instance to list
			for (int i = 0; i< availableModels.size(); i++) {
				ScoreBasedSubstitutionModel m = availableModels.get(i);
				if (m.getClass().getSimpleName().equals(label)) {
					models.add(m);
					return;
				}
			}
		}
		
	}
	
}
