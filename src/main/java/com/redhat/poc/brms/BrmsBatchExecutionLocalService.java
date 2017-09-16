package com.redhat.poc.brms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.core.event.DebugProcessEventListener;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.poc.StringUtil;
import com.redhat.poc.test.EjlpDebugAgendaEventListener;

public class BrmsBatchExecutionLocalService implements BrmsBatchExecutionServiceInf {
	
	private static Logger LOG = LoggerFactory.getLogger(BrmsBatchExecutionLocalService.class);
	private static ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	private static KieRuntimeLogger kieLogger = null;

	@Override
	public List<Object> execute(List<Object> factData, String ruleFlowGroup) {
		
		KieContainer kieContainer = BrmsLocalUtil.getKieContainer();
		
		 KieCommands commandsFactory = KieServices.Factory.get().getCommands();
		 //CommandFactory.
		
		List<Command<?>> commands = new ArrayList<Command<?>>();
		if (ruleFlowGroup != null && ruleFlowGroup.trim().equalsIgnoreCase("")) {
			commands.add(commandsFactory.newAgendaGroupSetFocus(ruleFlowGroup));
		} 		 		
		commands.add(commandsFactory.newInsertElements(factData, "FACTS"));	
		//commands.add(commandsFactory.newFireAllRules("hasil"));	// TIDAK PERLU?	 

		// Stateless Session
		StatelessKieSession kSession = kieContainer.newStatelessKieSession();
		
		if (rootLogger.isDebugEnabled()) {
		    //kSession.addEventListener( new DebugAgendaEventListener() );
			kSession.addEventListener(new EjlpDebugAgendaEventListener());
		    kSession.addEventListener( new DebugRuleRuntimeEventListener() );
		    kSession.addEventListener( new DebugProcessEventListener() );
		}

		// For debug
		// @See http://developers.redhat.com/blog/2016/08/12/get-it-done-with-these-5-techniques-to-debug-your-jboss-brms-applications/
		if (BrmsLocalUtil.AUDIT_FILE_LOG) {
			kieLogger = KieServices.Factory.get().getLoggers().newFileLogger(kSession, "audit");
		}

		LOG.debug("\t##### Execute using StatelessKieSession.");
		Object objResult = kSession.execute( commandsFactory.newBatchExecution(commands) );
		
		ExecutionResults result = (ExecutionResults) objResult;
		
		Object outputObject = null;
		Object resultList = null; // return List of CoverageData
		
    	Collection<String> identifiers = result.getIdentifiers();
    	for (String id : identifiers) { 
    		outputObject = result.getValue(id); 
    		System.out.println("\t\t######### Response data -> id: " + id + ", value: " + outputObject.getClass().getCanonicalName() + " -> ");
    		System.out.println("\t\t\t" + outputObject);

    		if (id.equals("FACTS")) {
    			resultList = outputObject; // set for return value of this method
        		for (Object obj : (List) outputObject) {
        			System.out.println("\t\t\t######### Output: " + StringUtil.toString(obj));
        		}        			
    		}

    	}

		
	

		if (BrmsLocalUtil.AUDIT_FILE_LOG) {
			kieLogger.close();
		}
		
		return (List<Object>) outputObject;

	}

}
