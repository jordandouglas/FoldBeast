package foldbeast.substitutionmodel;

import beast.base.evolution.datatype.DataType;
import foldbeast.datatype.DataType3Di;

public class NullModel extends ScoreBasedSubstitutionModel {


	public NullModel() {
		
	}
	
	@Override
	public int[][] getScores() {
		int [][] scores  = new int[21][21];
		for (int i = 0; i < 21; i ++) {
			for (int j = 0; j < 21; j ++) {
				scores[i][j] = (i == j ? 1 : 0);
			}
		}
		return scores;
	}

}
