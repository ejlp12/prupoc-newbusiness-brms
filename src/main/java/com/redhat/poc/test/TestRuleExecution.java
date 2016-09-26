package com.redhat.poc.test;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsExecutionService;

public class TestRuleExecution {

    public static void main(String[] args) throws Exception {
    	
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	CoverageData factData = SampleDataFactory.getGenerator().getSingle();
    	
    	CoverageData ruleResult = (CoverageData) BrmsExecutionService.execute(factData, "rule-bmi-female");
    	
    	if (ruleResult != null ) {
    		System.out.println("\t#### Result: " + StringUtil.printSimple(ruleResult));	
    	} else {
    		System.err.println("\t#### Batch rule execution is failed");
    	}

    	
    }
    
 
}