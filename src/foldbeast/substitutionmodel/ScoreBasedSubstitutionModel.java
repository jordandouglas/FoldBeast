package foldbeast.substitutionmodel;


import java.lang.reflect.InvocationTargetException;

import beast.base.core.Description;
import beast.base.core.Input.Validate;
import beast.base.evolution.datatype.DataType;
import beast.base.evolution.substitutionmodel.GeneralSubstitutionModel;
import foldbeast.datatype.DataType3Di;

@Description("Amino acid substituion model based on scores.")
abstract public class ScoreBasedSubstitutionModel extends GeneralSubstitutionModel {
	
	public ScoreBasedSubstitutionModel() {
		ratesInput.setRule(Validate.OPTIONAL);
		frequenciesInput.setRule(Validate.OPTIONAL);
		
	}

	double [] Q;
	
	@Override
	public void initAndValidate() {		
        frequencies = frequenciesInput.get();

        updateMatrix = true;
        nrOfStates = getStates();

        try {
			eigenSystem = createEigenSystem();
		} catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

        rateMatrix = new double[nrOfStates][nrOfStates];
        relativeRates = new double[nrOfStates * (nrOfStates-1)];
        storedRelativeRates = new double[relativeRates.length];

        Q = new double[nrOfStates * (nrOfStates-1)];
		setUpQMatrix();
	}
	
	
	 @Override
     public double[] getFrequencies() {
		 double [] freqs = null; 
		 if (frequencies == null){
			freqs = new double[nrOfStates];
			for (int i = 0; i < freqs.length; i ++) {
				freqs[i] = 1.0 / nrOfStates;
			}
		 }else {
			freqs = frequencies.getFreqs();
		 }
         return freqs;
     }
	
	public double[] setUpQMatrix() {
		int [][]scores = getScores();
		double [] freqs = null; 
		if (frequencies == null){
			freqs = new double[nrOfStates];
			for (int i = 0; i < freqs.length; i ++) {
				freqs[i] = 1.0 / nrOfStates;
			}
		}else {
			freqs = getFrequencies();
		}
		
		// normalise to log probs
		// PMatrix[i][j] = exp(score[i][j])/(f[i] f[j])
		double [][] PMatrix = new double[nrOfStates][nrOfStates];
		for (int i = 0; i < nrOfStates; i++) {
			for (int j = i; j < nrOfStates; j++) {
				PMatrix[i][j] = Math.exp(scores[i][j])/(freqs[i] * freqs[j]);
				PMatrix[i][j] = PMatrix[i][j];
			}			
		}
		
		// make sure probabilities add to 1
		for (int i = 0; i < nrOfStates; i++) {
			double sum = 0;
			for (int j = 0; j < nrOfStates; j++) {
				sum += PMatrix[i][j];
			}			
			for (int j = 0; j < nrOfStates; j++) {
				PMatrix[i][j] /= sum;
			}			
		}
		
		eigenDecomposition = eigenSystem.decomposeMatrix(PMatrix);
		
		// take the log of the matrix
        int i, j, k;
        double temp;
        double[] iexp = new double[nrOfStates * nrOfStates];
        // Eigen vectors
        double[] Evec = eigenDecomposition.getEigenVectors();
        // inverse Eigen vectors
        double[] Ievc = eigenDecomposition.getInverseEigenVectors();
        // Eigen values
        double[] Eval = eigenDecomposition.getEigenValues();
        for (i = 0; i < nrOfStates; i++) {
            temp = Math.log(Eval[i]);
            for (j = 0; j < nrOfStates; j++) {
                iexp[i * nrOfStates + j] = Ievc[i * nrOfStates + j] * temp;
            }
        }

        int u = 0;
        for (i = 0; i < nrOfStates; i++) {
            for (j = 0; j < nrOfStates; j++) {
            	if (i != j) {
	                temp = 0.0;
	                for (k = 0; k < nrOfStates; k++) {
	                    temp += Evec[i * nrOfStates + k] * iexp[k * nrOfStates + j];
	                }
	
	                Q[u] = Math.abs(temp);
	                u++;
            	}
            }
        }
        
        return Q;
	}

	@Override
	public void setupRelativeRates() {
    	setUpQMatrix();
    	System.arraycopy(Q, 0, relativeRates, 0, Q.length);
    }
	
	public double[] getRelativeRates() {
		setUpQMatrix();
		System.arraycopy(Q, 0, relativeRates, 0, Q.length);
		return Q;
	}
	

    /** return scores matrix **/
    public abstract int [][] getScores();
	

	@Override
	public boolean canHandleDataType(DataType dataType) {
		return dataType instanceof DataType3Di;
	}
	
	
	public int getStates() {
		return 0;
	}

}
