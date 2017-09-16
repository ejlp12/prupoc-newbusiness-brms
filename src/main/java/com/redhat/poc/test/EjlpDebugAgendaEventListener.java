package com.redhat.poc.test;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// @See http://developers.redhat.com/blog/2016/08/12/get-it-done-with-these-5-techniques-to-debug-your-jboss-brms-applications/
// Point #3
public class EjlpDebugAgendaEventListener extends DefaultAgendaEventListener {
	 
	   private static final Logger LOGGER = LoggerFactory.getLogger(EjlpDebugAgendaEventListener.class);
	 
	   @Override
	   public void afterMatchFired(AfterMatchFiredEvent event) {
	      Rule rule = event.getMatch().getRule();
	      LOGGER.debug("... Rule fired: " + rule.getName());
	      //System.out.println("Rule fired: " + rule.getName());
	   }
	}
