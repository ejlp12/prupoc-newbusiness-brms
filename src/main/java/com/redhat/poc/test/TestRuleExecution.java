package com.redhat.poc.test;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsExecutionService;

public class TestRuleExecution {

    public static void main(String[] args) throws Exception {
    	
    	// It will read a single txt files in a directory, parse and generate a CoverageData object.
    	// Change in Application.properties if you want to select other file name
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	CoverageData factData = SampleDataFactory.getGenerator().getSingle();
    	
    	// Execute in the remote BRMS server (container)
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(factData, "rule-bmi-female");
    	
    	if (ruleResult != null ) {
    		System.out.println("\t#### Result: " + StringUtil.printSimple(ruleResult));	
    	} else {
    		System.err.println("\t#### Batch rule execution is failed");
    	}

    	
    }
    
 
}