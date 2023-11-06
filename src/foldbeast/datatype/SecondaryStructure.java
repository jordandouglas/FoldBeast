package foldbeast.datatype;

import java.util.List;

import beast.base.core.Description;
import beast.base.evolution.datatype.DataType;


/**
 * DSSP notation
 * https://swift.cmbi.umcn.nl/gv/dssp/DSSP_2.html
 */
@Description("Protein secondary structure datatype")
public class SecondaryStructure extends DataType.Base {
	
	@Override
    public void initAndValidate() {
		
		stateCount = 8;
        codeLength = 1;
        codeMap = "BEGHINST" + GAP_CHAR + MISSING_CHAR;

        mapCodeToStateSet = new int[10][];
        for (int i = 0; i < 8; i++) {
            mapCodeToStateSet[i] = new int[1];
            mapCodeToStateSet[i][0] = i;
        }
        int[] all = new int[8];
        for (int i = 0; i < 8; i++) {
            all[i] = i;
        }
        mapCodeToStateSet[8] = all;
        mapCodeToStateSet[9] = all;
		
		super.initAndValidate();
	}

	
	public SecondaryStructure() {
		stateCount = 8;
        codeLength = 1;
        codeMap = "BEGHINST" + GAP_CHAR + MISSING_CHAR;

        mapCodeToStateSet = new int[10][];
        for (int i = 0; i < 8; i++) {
            mapCodeToStateSet[i] = new int[1];
            mapCodeToStateSet[i][0] = i;
        }
        int[] all = new int[8];
        for (int i = 0; i < 8; i++) {
            all[i] = i;
        }
        mapCodeToStateSet[8] = all;
        mapCodeToStateSet[9] = all;
	}
	

	@Override
	public String getTypeDescription() {
		return "DSSP";
	}

	

}
