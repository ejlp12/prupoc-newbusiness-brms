package com.redhat.poc.test;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsConstants;
import com.redhat.poc.brms.BrmsExecutionService;

public class TestRuleExecution {
	
	// Set rule-flow-group or activation-group for testing
	static String ACTIVATION_GROUP = BrmsConstants.RULE_FLOW_GROUP_BMI;	

    public static void main(String[] args) throws Exception {
    	
    	// It will read a single txt files in a directory, parse and generate a CoverageData object.
    	// Change in Application.properties if you want to select other file name
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_DUMMY_POJO);
    	CoverageData factData = SampleDataFactory.getGenerator().getSingle();
    	
    	// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(factData, ACTIVATION_GROUP);
    	
    	if (ruleResult != null ) {
    		System.out.println("\t#### Result: " + StringUtil.printSimple(ruleResult));	
    		System.out.println("\t#### BMI: " + ruleResult.getRuleResultBMI());
    		System.out.println("\t#### AgeInMonth: " + ruleResult.getAgeInMonth());
    	} else {
    		System.err.println("\t#### Rule execution is failed");
    	}

    	
    }
    
 
}