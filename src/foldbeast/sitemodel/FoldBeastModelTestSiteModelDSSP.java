package foldbeast.sitemodel;

import java.util.Arrays;

import org.apache.commons.math.distribution.GammaDistribution;
import org.apache.commons.math.distribution.GammaDistributionImpl;

import beast.base.core.Citation;
import beast.base.core.Description;
import beast.base.core.Input;
import beast.base.core.Input.Validate;
import beast.base.inference.parameter.IntegerParameter;
import beast.base.inference.util.InputUtil;
import beast.base.evolution.sitemodel.SiteModel;
import beast.base.evolution.tree.Node;

@Description("Site model that jumps between with and without gamma sites, as well as with and without invariant sites. Specific to dssp characters")
public class FoldBeastModelTestSiteModelDSSP extends FoldBeastModelTestSiteModel {

	
}
