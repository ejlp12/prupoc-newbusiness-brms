package org.jbpm.test.kieserver;

import prupoc.newbusiness.CoverageData;
import prupoc.newbusiness.RuleResultOccupationBenefit;

public class PrupocBrmsClientDummy implements PrupocBrmsClientInf {
	
	
	public static String RULE_FLOW_GROUP_NON_MED_LIMIT = "rule-non-med-limit";
	public static String RULE_FLOW_GROUP_BENEFIT_COMPONENT = "rule-benefit-component";
	public static String RULE_FLOW_GROUP_INSURABLE_INTEREST = "rule-insurable-interest";
	public static String RULE_FLOW_GROUP_BMI = "rule-bmi";
	
	public CoverageData executeRuleNonMedLimit(CoverageData data) {
		
		data.setRuleResultNonMedicalLimit("T9M");
		return data;	
	}
	
	public CoverageData executeRuleBenefitComponent(CoverageData data) {
		data.setRuleResultBenefitADD("3x");
		data.setRuleResultBenefitCC("Standard");
		data.setRuleResultBenefitHealth("Decline");
		data.setRuleResultBenefitLife("RDO");
		data.setRuleResultBenefitTPD("RDO");
		
		RuleResultOccupationBenefit benefit = new RuleResultOccupationBenefit();
		benefit.setADD("3x");
		benefit.setCC("Standard");
		benefit.setHealth("Decline");
		benefit.setLife("RDO");
		benefit.setTPD("RDO");
		
		data.setRuleResultOccupationBenefit(benefit);
		
		return data;
	}

	public CoverageData executeRuleInsurableIntereset(CoverageData data) {
		data.setRuleResultInsurableInterest("Standard");
		//data.setRuleResultInsurableInterest("Refer");
		return data;
	}
	
	public CoverageData executeRuleBMI(CoverageData data) {
		data.setRuleResultBMI("Standard");
		//data.setRuleResultBMI("Refer");
		
		return data;
	}	
	
	

}
