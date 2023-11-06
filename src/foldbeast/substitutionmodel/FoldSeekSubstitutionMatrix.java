package foldbeast.substitutionmodel;

import beast.base.core.Citation;


@Citation("van Kempen, Michel, et al. \"Fast and accurate protein structure search with Foldseek.\" Nature Biotechnology (2023): 1-4.")
public class FoldSeekSubstitutionMatrix extends ScoreBasedSubstitutionModel {

	
	@Override
	public int getStates() {
		return 21;
	}
	
	public FoldSeekSubstitutionMatrix() {
		
	}
	
	@Override
	public int[][] getScores() {
		
		// A C D E F G H I K L M N P Q R S T V W Y X
		int[][] scores = {
			    {   6,  -3,   1,   2,   3,  -2,  -2,  -7,  -3,  -3, -10,  -5,  -1,   1,  -4,  -7,  -5,  -6,   0,  -2,   0 },
			    {  -3,   6,  -2,  -8,  -5,  -4,  -4, -12, -13,   1, -14,   0,   0,   1,  -1,   0,  -8,   1,  -7,  -9,   0 },
			    {   1,  -2,   4,  -3,   0,   1,   1,  -3,  -5,  -4,  -5,  -2,   1,  -1,  -1,  -4,  -2,  -3,  -2,  -2,   0 },
			    {   2,  -8,  -3,   9,  -2,  -7,  -4, -12, -10,  -7, -17,  -8,  -6,  -3,  -8, -10, -10, -13,  -6,  -3,   0 },
			    {   3,  -5,   0,  -2,   7,  -3,  -3,  -5,   1,  -3,  -9,  -5,  -2,   2,  -5,  -8,  -3,  -7,   4,  -4,   0 },
			    {  -2,  -4,   1,  -7,  -3,   6,   3,   0,  -7,  -7,  -1,  -2,  -2,  -4,   3,  -3,   4,  -6,  -4,  -2,   0 },
			    {  -2,  -4,   1,  -4,  -3,   3,   6,  -4,  -7,  -6,  -6,   0,  -1,  -3,   1,  -3,  -1,  -5,  -5,   3,   0 },
			    {  -7, -12,  -3, -12,  -5,   0,  -4,   8,  -5, -11,   7,  -7,  -6,  -6,  -3,  -9,   6, -12,  -5,  -8,   0 },
			    {  -3, -13,  -5, -10,   1,  -7,  -7,  -5,   9, -11,  -8, -12,  -6,  -5,  -9, -14,  -5, -15,   5,  -8,   0 },
			    {  -3,   1,  -4,  -7,  -3,  -7,  -6, -11, -11,   6, -16,  -3,  -2,   2,  -4,  -4,  -9,   0,  -8,  -9,   0 },
			    { -10, -14,  -5, -17,  -9,  -1,  -6,   7,  -8, -16,  10,  -9,  -9, -10,  -5, -10,   3, -16,  -6,  -9,   0 },
			    {  -5,   0,  -2,  -8,  -5,  -2,   0,  -7, -12,  -3,  -9,   7,   0,  -2,   2,   3,  -4,   0,  -8,  -5,   0 },
			    {  -1,   0,   1,  -6,  -2,  -2,  -1,  -6,  -6,  -2,  -9,   0,   4,   0,   0,  -2,  -4,   0,  -4,  -5,   0 },
			    {   1,   1,  -1,  -3,   2,  -4,  -3,  -6,  -5,   2, -10,  -2,   0,   5,  -2,  -4,  -5,  -1,  -2,  -5,   0 },
			    {  -4,  -1,  -1,  -8,  -5,   3,   1,  -3,  -9,  -4,  -5,   2,   0,  -2,   6,   2,   0,  -1,  -6,  -3,   0 },
			    {  -7,   0,  -4, -10,  -8,  -3,  -3,  -9, -14,  -4, -10,   3,  -2,  -4,   2,   6,  -6,   0, -11,  -9,   0 },
			    {  -5,  -8,  -2, -10,  -3,   4,  -1,   6,  -5,  -9,   3,  -4,  -4,  -5,   0,  -6,   8,  -9,  -5,  -5,   0 },
			    {  -6,   1,  -3, -13,  -7,  -6,  -5, -12, -15,   0, -16,   0,   0,  -1,  -1,   0,  -9,   3, -10, -11,   0 },
			    {   0,  -7,  -2,  -6,   4,  -4,  -5,  -5,   5,  -8,  -6,  -8,  -4,  -2,  -6, -11,  -5, -10,   8,  -6,   0 },
			    {  -2,  -9,  -2,  -3,  -4,  -2,   3,  -8,  -8,  -9,  -9,  -5,  -5,  -5,  -3,  -9,  -5, -11,  -6,   9,   0 },
			    {   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 }
			};
		
		return scores;
	}



}
