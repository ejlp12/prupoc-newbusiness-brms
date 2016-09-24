package com.redhat.poc.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.jboss.netty.util.internal.jzlib.ZStream;
import org.jbpm.test.kieserver.BrmsClientUtil;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.internal.runtime.helper.BatchExecutionHelper;
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

import prupoc.newbusiness.CoverageData;

public class TestRuleExecution {

	private static KieServicesClient kieServicesClient = null;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();      

        kieServicesClient =  BrmsClientUtil.getKieServicesClient();
        
        // work with rules
        KieCommands commandsFactory = KieServices.Factory.get().getCommands();

        CoverageData data1 = SampleDataFactory.getGenerator().getSingle();
        final String INDENTIFIER = "DATAX";
        
        List<Command<?>> commands = new ArrayList<Command<?>>();
        commands.add(commandsFactory.newInsert(data1, INDENTIFIER));
        commands.add(commandsFactory.newFireAllRules());
        commands.add(commandsFactory.newAgendaGroupSetFocus("rule-bmi-female"));
        
        BatchExecutionCommand executionCommand = commandsFactory.newBatchExecution(commands);
        
        String xStreamXml = BatchExecutionHelper.newXStreamMarshaller().toXML(executionCommand);
        System.out.println("\t######### Request XML: \n" + xStreamXml); 

        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        ServiceResponse<ExecutionResults> response = ruleClient.executeCommandsWithResults(BrmsClientUtil.getContainerId(), xStreamXml);
        System.out.println("\t######### Rules executed. Response: " + response);
        
        Object outputObject;
        if (response.getType().equals(ResponseType.SUCCESS)){
        	ExecutionResults actualData = response.getResult();
        	
        	System.out.println("\t######### Result BMI: " + ((CoverageData) actualData.getValue(INDENTIFIER)).getRuleResultBMI() );
        	
        	Collection<String> identifiers = actualData.getIdentifiers();
        	for (String id : identifiers) { 
        		outputObject = actualData.getValue(id); 
        		System.out.println("\t\t######### Response data -> id: " + id + ", value: " + StringUtil.toString(outputObject) );

        	}
        }  else {
        	System.out.println("\t######### Error executing rules. Message: " + response.getMsg());
        }
        
        System.out.println("Execution completed in " + (System.currentTimeMillis() - start));


    }
    
 
}