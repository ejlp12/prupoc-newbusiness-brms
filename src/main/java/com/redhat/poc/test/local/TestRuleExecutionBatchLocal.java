package com.redhat.poc.test.local;

import java.util.List;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsExecutionFactory;
import com.redhat.poc.test.SampleDataFactory;

public class TestRuleExecutionBatchLocal {

	// Set rule-flow-group or activation-group for testing
	static String ACTIVATION_GROUP = "rule-bmi-female";
	

    public static void main(String[] args) throws Exception {	
    	
    	// It will read all txt files in a directory, parse and generate CoverageData objects
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	List<Object> factData = SampleDataFactory.getGenerator().getList();
    	
    	// Execute in the remote BRMS server (container)
    	List<Object> ruleResult = BrmsExecutionFactory.getBatchService(BrmsExecutionFactory.SERVICE_LOCATION_LOCAL)
    			.execute(factData, ACTIVATION_GROUP);
    	
    	if (ruleResult != null) {
    		int i = 1;
        	for (Object obj : ruleResult) {
        		CoverageData data = (CoverageData) obj;
        		System.out.println(i + " " + StringUtil.printSimple(data));
        		System.out.println(i + " ruleResultBMI: " + data.getRuleResultBMI());
        		i++;
        	}    		
    	} else {
    		System.err.println("\t#### Batch rule execution is failed");
    	}

    	
    }
	
}
