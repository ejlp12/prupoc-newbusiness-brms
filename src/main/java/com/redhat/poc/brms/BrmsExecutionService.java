package com.redhat.poc.brms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.internal.runtime.helper.BatchExecutionHelper;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.ServiceResponse.ResponseType;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.RuleServicesClient;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.StringUtil;
import com.redhat.poc.test.SampleDataFactory;

public class BrmsExecutionService {

	private static KieServicesClient kieServicesClient = null;

    public static Object execute(Object factData, String ruleFlowGroup) {
        long start = System.currentTimeMillis();     
        
        BrmsClientUtil.init();        
        kieServicesClient =  BrmsClientUtil.getKieServicesClient();
        
        // work with rules
        KieCommands commandsFactory = KieServices.Factory.get().getCommands();
        
        List<Command<?>> commands = new ArrayList<Command<?>>();                
        commands.add(commandsFactory.newInsert(factData, "factData"));        
        commands.add(commandsFactory.newAgendaGroupSetFocus(ruleFlowGroup));  
        commands.add(commandsFactory.newFireAllRules("firedRules"));
        // If we use this, result become an ArrayList and contains many CoverageData
        commands.add(commandsFactory.newGetObjects("factData"));
        
        
        BatchExecutionCommand executionCommand = commandsFactory.newBatchExecution(commands);
        
        String xStreamXml = BatchExecutionHelper.newXStreamMarshaller().toXML(executionCommand);
        System.out.println("\t######### Request XML: \n" + xStreamXml); 
        

        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        ServiceResponse<ExecutionResults> response = ruleClient.executeCommandsWithResults(BrmsClientUtil.getContainerId(), executionCommand);
        System.out.println("\t######### Rules executed. Response: " + response);
        
        xStreamXml = BatchExecutionHelper.newXStreamMarshaller().toXML(response);
        System.out.println("\t######### Response XML: \n" + xStreamXml); 
        
        Object outputObject = null;
        if (response.getType().equals(ResponseType.SUCCESS)){
        	ExecutionResults actualData = response.getResult();
      	
        	Collection<String> identifiers = actualData.getIdentifiers();
        	for (String id : identifiers) { 
        		outputObject = actualData.getValue(id); 
        		System.out.println("\t\t######### Response data -> id: " + id + ", value: " + outputObject.getClass().getCanonicalName() + " -> ");
        		System.out.println("\t\t\t" + outputObject);

        	}
        }  else {
        	System.out.println("\t######### Error executing rules. Message: " + response.getMsg());
        }
        
        System.out.println("Execution completed in " + (System.currentTimeMillis() - start));
        return outputObject;


    }
  
	
}
