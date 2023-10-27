package foldbeast.datatype;

import java.util.List;

import beast.base.core.Description;
import beast.base.evolution.datatype.DataType;


@Description("FoldSeek 3Di datatype")
public class DataType3Di extends DataType.Base {
	
	@Override
    public void initAndValidate() {
		
		stateCount = 21;
        codeLength = 1;
        codeMap = "ACDEFGHIKLMNPQRSTVWYX" + GAP_CHAR + MISSING_CHAR;

        mapCodeToStateSet = new int[23][];
        for (int i = 0; i < 21; i++) {
            mapCodeToStateSet[i] = new int[1];
            mapCodeToStateSet[i][0] = i;
        }
        int[] all = new int[21];
        for (int i = 0; i < 21; i++) {
            all[i] = i;
        }
        mapCodeToStateSet[21] = all;
        mapCodeToStateSet[22] = all;
		
		super.initAndValidate();
	}

	
	public DataType3Di() {
		stateCount = 21;
        codeLength = 1;
        codeMap = "ACDEFGHIKLMNPQRSTVWYX" + GAP_CHAR + MISSING_CHAR;

        mapCodeToStateSet = new int[23][];
        for (int i = 0; i < 21; i++) {
            mapCodeToStateSet[i] = new int[1];
            mapCodeToStateSet[i][0] = i;
        }
        int[] all = new int[21];
        for (int i = 0; i < 21; i++) {
            all[i] = i;
        }
        mapCodeToStateSet[21] = all;
        mapCodeToStateSet[22] = all;
	}
	

	@Override
	public String getTypeDescription() {
		return "3Di";
	}

	

}
