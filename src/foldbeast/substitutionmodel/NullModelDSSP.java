package foldbeast.substitutionmodel;

import beast.base.evolution.datatype.DataType;
import foldbeast.datatype.DataType3Di;

public class NullModelDSSP extends ScoreBasedSubstitutionModel {

	@Override
	public int getStates() {
		return 8;
	}


	public NullModelDSSP() {
		
	}
	
	@Override
	public int[][] getScores() {
		int nstates = getStates();
		int [][] scores  = new int[nstates][nstates];
		for (int i = 0; i < nstates; i ++) {
			for (int j = 0; j < nstates; j ++) {
				scores[i][j] = (i == j ? 1 : 0);
			}
		}
		return scores;
	}

}
