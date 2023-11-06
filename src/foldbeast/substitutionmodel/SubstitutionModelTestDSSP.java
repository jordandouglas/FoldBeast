package foldbeast.substitutionmodel;



import beast.base.core.Description;
import foldbeast.datatype.SecondaryStructure;
import beast.base.evolution.datatype.DataType;

@Description("Substitution model that can average over a number of amino acid substitution models " +
		"as well as switch between the model's frequencies and external frequencies (as for example " +
		"empirical frequencies informed by an alignment).")
public class SubstitutionModelTestDSSP extends SubstitutionModelTest3Di {
	
	
	@Override
	public boolean canHandleDataType(DataType dataType) {
        return dataType instanceof SecondaryStructure;
	}
	


}
