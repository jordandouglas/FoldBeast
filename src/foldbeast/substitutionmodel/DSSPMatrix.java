package foldbeast.substitutionmodel;

import beast.base.core.Citation;



public class DSSPMatrix extends ScoreBasedSubstitutionModel {

	
	@Override
	public int getStates() {
		return 8;
	}
	
	public DSSPMatrix() {
		
	}
	
	@Override
	public int[][] getScores() {
		
		// BEGHINST
		
		
		// Diagonal matrix with 2 on diagonal
		int dimension = 8;
		int[][] scores = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			scores[i][i] = 2;
        }
		
		
		// Increase the score between the three types of helix (G, H, and I)
		final int helixScore = 1;
		scores[2][3] = helixScore;
		scores[3][2] = helixScore;
		scores[2][4] = helixScore;
		scores[4][2] = helixScore;
		scores[3][4] = helixScore;
		scores[4][3] = helixScore;
		
		
		return scores;
	}



}
