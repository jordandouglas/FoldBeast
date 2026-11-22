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

@Description("Site model that jumps between with and without gamma sites, as well as with and without invariant sites. Specific to 3di characters")
@Citation(value="Douglas J. & Bromham L., Reconstructing substitution histories on phylogenies, with accuracy, precision, and coverage, Systematic Biology, 2026;, syag081", DOI="10.1093/sysbio/syag081")
public class FoldBeastModelTestSiteModel3Di extends FoldBeastModelTestSiteModel {

	
}
