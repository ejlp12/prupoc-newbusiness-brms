package com.redhat.poc.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.KieServerInfo;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.ServiceResponse.ResponseType;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsBatchExecutionService;
import com.redhat.poc.brms.BrmsClientUtil;

import prupoc.newbusiness.CoverageData;

public class TestRuleExecutionBatch {

	private static KieServicesClient kieServicesClient = null;

    public static void main(String[] args) throws Exception {
    	
    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
    	List<Object> factData = SampleDataFactory.getGenerator().getList();
    	
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