package com.redhat.poc.test;

import java.util.List;

import org.kie.server.client.KieServicesClient;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsBatchExecutionService;
import com.redhat.poc.brms.BrmsConstants;
import com.redhat.poc.brms.BrmsExecutionFactory;

public class TestRuleExecutionBatch {
	
	// Set rule-flow-group or activation-group for testing
	static String ACTIVATION_GROUP = BrmsConstants.RULE_FLOW_GROUP_INSURABLE_INTEREST;
	

    public static void main(String[] args) throws Exception {	
    	
    	// It will read all txt files in a directory, parse and generate CoverageData objects
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	List<Object> factData = SampleDataFactory.getGenerator().getList();
    	
    	// Execute in the remote BRMS server (container)
    	List<Object> ruleResult = BrmsExecutionFactory.getBatchService(BrmsExecutionFactory.SERVICE_LOCATION_REMOTE)
    			.execute(factData, ACTIVATION_GROUP);
    	
    	if (ruleResult != null) {
    		int i = 1;
        	for (Object obj : ruleResult) {
        		CoverageData data = (CoverageData) obj;
        		System.out.println(i + " " + StringUtil.printSimple(data));
        		System.out.println(i + " getGender: " + data.getGender());
        		System.out.println(i + " getMaritalStatus: " + data.getMaritalStatus());
        		System.out.println(i + " getPolicyOwner: " + data.getPolicyOwner());
        		System.out.println(i + " getInsurableInterest: " + data.getInsurableInterest());
        		System.out.println(i + " getAgeInYear: " + data.getAgeInYear());
        		System.out.println(i + " >>> getRuleResultInsurableInterest: " + data.getRuleResultInsurableInterest());
        		//System.out.println(i + " ruleResultBMI: " + data.getRuleResultBMI());
        		i++;
        	}    		
    	} else {
    		System.err.println("\t#### Batch rule execution is failed");
    	}

    	
    }
  
}