package com.redhat.poc.test;

import java.util.List;

import org.kie.server.client.KieServicesClient;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsBatchExecutionService;

public class TestRuleExecutionBatch {

    public static void main(String[] args) throws Exception {
    	
    	// It will read all txt files in a directory, parse and generate CoverageData objects
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	List<Object> factData = SampleDataFactory.getGenerator().getList();
    	
    	// Execute in the remote BRMS server (container)
    	List<Object> ruleResult = BrmsBatchExecutionService.execute(factData, "rule-bmi-female");
    	
    	if (ruleResult != null) {
    		int i = 1;
        	for (Object obj : ruleResult) {
        		CoverageData data = (CoverageData) obj;
        		System.out.println(i + " " + StringUtil.printSimple(data));
        		i++;
        	}    		
    	} else {
    		System.err.println("\t#### Batch rule execution is failed");
    	}

    	
    }
  
}