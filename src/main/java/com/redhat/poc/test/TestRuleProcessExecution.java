package com.redhat.poc.test;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsConstants;
import com.redhat.poc.brms.BrmsExecutionService;
import com.redhat.poc.brms.BrmsProcessExecutionService;

public class TestRuleProcessExecution {
	
	// Set rule-flow-group or activation-group for testing
	static String PROCESS_NAME = "newbusiness.ruleflow_non_med_limit";

    public static void main(String[] args) throws Exception {
    	
    	// It will read a single txt files in a directory, parse and generate a CoverageData object.
    	// Change in Application.properties if you want to select other file name
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	CoverageData factData = SampleDataFactory.getGenerator().getSingle();
    	
    	// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsProcessExecutionService.execute(factData, PROCESS_NAME);
    	
    	if (ruleResult != null ) {
    		System.out.println("\t#### Result: " + StringUtil.printSimple(ruleResult));	
    		System.out.println("\t#### Result: " + ruleResult.getAgeInYear());	
    		System.out.println("\t#### getAgeInYear: " + ruleResult.getAgeInYear());	
    		System.out.println("\t#### getRuleResultProductCategory: " + ruleResult.getRuleResultProductCategory());
    		System.out.println("\t#### getRuleResultSumAssuredHigherIDRNML: " + ruleResult.getRuleResultSumAssuredHigherIDRNML());
    		System.out.println("\t#### getRuleResultNonMedicalLimit: " + ruleResult.getRuleResultNonMedicalLimit());
    		
    	} else {
    		System.err.println("\t#### Rule execution is failed");
    	}

    	
    }
    
 
}