package foldbeast.substitutionmodel;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import beast.base.core.Citation;
import beast.base.core.Description;
import beast.base.core.Input;
import beast.base.core.Input.Validate;
import beast.base.inference.parameter.BooleanParameter;
import beast.base.inference.parameter.IntegerParameter;
import foldbeast.datatype.DataType3Di;
import beast.base.core.Log;
import beast.base.evolution.datatype.Aminoacid;
import beast.base.evolution.datatype.DataType;
import beast.base.evolution.tree.Node;
import beast.base.evolution.substitutionmodel.EmpiricalSubstitutionModel;
import beast.base.evolution.substitutionmodel.GeneralSubstitutionModel;

@Description("Substitution model that can average over a number of amino acid substitution models " +
		"as well as switch between the model's frequencies and external frequencies (as for example " +
		"empirical frequencies informed by an alignment).")
@Citation(value="Remco Bouckaert. OBAMA: OBAMA for Bayesian amino-acid model averaging. PeerJ 8, e9460",
		year = 2020, firstAuthorSurname = "bouckaert", DOI="doi.org/10.7717/peerj.9460")
public class SubstitutionModelTest3Di extends GeneralSubstitutionModel {
	final public Input<BooleanParameter> useExternalFreqsInput = new Input<>("useExternalFreqs", "if false, use substitution model frequencies, "
			+ "otherwise use frequencies from frequencies input (e.g. empirical frequencies)", new BooleanParameter("false"));
	final public Input<List<ScoreBasedSubstitutionModel>> substModelInput = new Input<>("model", "empicial amino acid substitution model", new ArrayList<>(), Validate.REQUIRED);
	final public Input<IntegerParameter> modelIndicatorInput = new Input<>("modelIndicator", "index of the model in list of models that is used for its rates and frequencies", Validate.REQUIRED);

	BooleanParameter useExternalFreqs;
	IntegerParameter modelIndicator;
	List<ScoreBasedSubstitutionModel> models;
	
	public SubstitutionModelTest3Di() {
		ratesInput.setRule(Validate.OPTIONAL);
	}
	
	@Override
	public void initAndValidate() {
        frequencies = frequenciesInput.get();

		useExternalFreqs = useExternalFreqsInput.get();
		models = substModelInput.get();
		modelIndicator = modelIndicatorInput.get();
		if (modelIndicator.getUpper() > models.size() - 1) {
			Log.warning("Setting upper limit of " + modelIndicator.getID() + " to " + (models.size()-1) +".");
			modelIndicator.setUpper(models.size() - 1);
		}
		if (modelIndicator.getLower() < 0) {
			Log.warning("Setting lower limit of " + modelIndicator.getID() + " to 0.");
			modelIndicator.setLower(0);
		}
		
		
        updateMatrix = true;
        nrOfStates = frequencies.getFreqs().length;

        try {
			eigenSystem = createEigenSystem();
		} catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
        //eigenSystem = new DefaultEigenSystem(m_nStates);

        rateMatrix = new double[nrOfStates][nrOfStates];
        relativeRates = new double[nrOfStates*(nrOfStates-1)];
        storedRelativeRates = new double[nrOfStates*(nrOfStates-1)];

	}
	
	
	@Override
	public void setupRelativeRates() {
		ScoreBasedSubstitutionModel model = models.get(modelIndicator.getValue());
    	double [] empiricalRates = model.getRelativeRates();
        System.arraycopy(empiricalRates, 0, relativeRates, 0, empiricalRates.length);
    }

	@Override
	public double[] getFrequencies() {
		if (useExternalFreqs.getValue()) {
			return super.getFrequencies();
		}
		ScoreBasedSubstitutionModel model = models.get(modelIndicator.getValue());
        return model.getFrequencies();
	}
	
	
    @Override
    public double[] getRateMatrix(Node node) {
    	ScoreBasedSubstitutionModel model = models.get(modelIndicator.getValue());
        return model.setUpQMatrix();
    }	
	
	
	@Override
	public boolean canHandleDataType(DataType dataType) {
        return dataType instanceof DataType3Di;
	}
	
	@Override
	protected boolean requiresRecalculation() {
		if (useExternalFreqs.isDirtyCalculation()) {
			updateMatrix = true;
			return true;
		}
		if (modelIndicator.isDirtyCalculation()) {
			updateMatrix = true;
			return true;
		}
		return super.requiresRecalculation();
	}

}
