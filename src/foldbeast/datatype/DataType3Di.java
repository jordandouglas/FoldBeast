package foldbeast.datatype;

import java.util.List;

import beast.base.core.Description;
import beast.base.evolution.datatype.DataType;


@Description("FoldSeek 3Di datatype")
public class DataType3Di extends DataType.Base {
	
	@Override
    public void initAndValidate() {
		
		stateCount = 20;
        codeLength = 1;
        codeMap = "ACDEFGHIKLMNPQRSTVWY" + "X" + GAP_CHAR + MISSING_CHAR;

        mapCodeToStateSet = new int[23][];
        for (int i = 0; i < stateCount; i++) {
            mapCodeToStateSet[i] = new int[1];
            mapCodeToStateSet[i][0] = i;
        }
        int[] all = new int[stateCount];
        for (int i = 0; i < stateCount; i++) {
            all[i] = i;
        }
        mapCodeToStateSet[20] = all;
        mapCodeToStateSet[21] = all;
        mapCodeToStateSet[22] = all;
		
		super.initAndValidate();
	}

	
	public DataType3Di() {
		stateCount = 20;
        codeLength = 1;
        codeMap = "ACDEFGHIKLMNPQRSTVWY" + "X" + GAP_CHAR + MISSING_CHAR;

        mapCodeToStateSet = new int[23][];
        for (int i = 0; i < stateCount; i++) {
            mapCodeToStateSet[i] = new int[1];
            mapCodeToStateSet[i][0] = i;
        }
        int[] all = new int[stateCount];
        for (int i = 0; i < stateCount; i++) {
            all[i] = i;
        }
        mapCodeToStateSet[20] = all;
        mapCodeToStateSet[21] = all;
        mapCodeToStateSet[22] = all;
	}
	

	@Override
	public String getTypeDescription() {
		return "3Di";
	}

	

}
