package org.jbpm.test.kieserver;

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

import prupoc.newbusiness.CoverageData;

public class KieExecutionServerClientRuleTest2 {

	private static KieServicesClient kieServicesClient = null;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();      

        BrmsClientUtil.init();
        kieServicesClient =  BrmsClientUtil.getKieServicesClient();
        
        // work with rules
        KieCommands commandsFactory = KieServices.Factory.get().getCommands();
        
        List<Command<?>> commands = new ArrayList<Command<?>>();
        BatchExecutionCommand executionCommand = commandsFactory.newBatchExecution(commands);
        
        CoverageData data1 = new CoverageData();
        data1.setDateOfBirth("11-Dec-2013");
        data1.setHeight("47");
        data1.setWeight("3.0");
        data1.setRuleResultBMI(null);
        
        commands.add(commandsFactory.newInsert(data1, "data1"));
        commands.add(commandsFactory.newFireAllRules());
        commands.add(commandsFactory.newAgendaGroupSetFocus("rule-bmi-female"));
        
        // If we use this, result become an ArrayList and contains many CoverageData
        //commands.add((GenericCommand<?>) KieServices.Factory.get().getCommands().newGetObjects("data1"));
        

        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        ServiceResponse<ExecutionResults> response = ruleClient.executeCommandsWithResults(BrmsClientUtil.getContainerId(), executionCommand);
        System.out.println("\t######### Rules executed. Response: " + response);
        
        Object outputObject;
        if (response.getType().equals(ResponseType.SUCCESS)){
        	ExecutionResults actualData = response.getResult();
        	
        	//System.out.println("\t######### Result BMI: " + ((ArrayList) actualData.getValue("data1")).get(0) );
        	System.out.println("\t######### Result BMI: " + ((CoverageData) actualData.getValue("data1")).getRuleResultBMI() );
        	
        	Collection<String> identifiers = actualData.getIdentifiers();
        	for (String id : identifiers) { 
        		outputObject = actualData.getValue(id); 
        		System.out.println("\t\t######### Response data -> id: " + id + ", value: " + outputObject);

// Commented since we comment the commands.add(...newGetObjects) above
//        		for (Object obj : (List) outputObject) {
//        			System.out.println("\t\t\t######### Output: " + obj);
//        		}
        	}
        }  else {
        	System.out.println("\t######### Error executing rules. Message: " + response.getMsg());
        }
        
        System.out.println("Execution completed in " + (System.currentTimeMillis() - start));


    }
    
 
}