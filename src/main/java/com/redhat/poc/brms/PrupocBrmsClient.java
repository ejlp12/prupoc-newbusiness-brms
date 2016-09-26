package com.redhat.poc.brms;

import prupoc.newbusiness.CoverageData;
import prupoc.newbusiness.RuleResultOccupationBenefit;

public class PrupocBrmsClient implements PrupocBrmsClientInf {
	
	public CoverageData executeRuleNonMedLimit(CoverageData data) {
		
		// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(data, BrmsConstants.RULE_FLOW_GROUP_NON_MED_LIMIT);
		
		return ruleResult;	
	}
	
	public CoverageData executeRuleBenefitComponent(CoverageData data) {
		
    	// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(data, BrmsConstants.RULE_FLOW_GROUP_BENEFIT_COMPONENT);
		
		return ruleResult;
	}

	public CoverageData executeRuleInsurableIntereset(CoverageData data) {
		
		// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(data, BrmsConstants.RULE_FLOW_GROUP_INSURABLE_INTEREST);
		
		return ruleResult;
	}
	
	public CoverageData executeRuleBMI(CoverageData data) {

		// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(data, BrmsConstants.RULE_FLOW_GROUP_BMI);
		
		return ruleResult;
	}	
	
	

}
