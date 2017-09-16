package com.redhat.poc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.drools.core.common.InternalAgenda;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.poc.brms.BrmsConstants;

/**
 * A listener that will track all rule firings in a session.
 * 
 * @author Stephen Masters, Isaac Martin
 */
public class TrackingAgendaEventListener extends DefaultAgendaEventListener  {

	/* EJLP12: How to use
	 
	TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
	kieSession.addEventListener(agendaEventListener );
	
	kieSession.insert(factData);
	((InternalAgenda) kSession.getAgenda()).activateRuleFlowGroup("rule-flow-group-name");
	kieSession.fireAllRules();
	
	List all matched rules
    List<Match> activations =  agendaEventListener.getMatchList();
 	for (Match m : activations) {
		System.out.println("Match rule: " + m.getRule().getName());
	} 	
    
    */

    private static Logger log = LoggerFactory.getLogger(TrackingAgendaEventListener.class);

    private List<Match> matchList = new ArrayList<Match>();

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        Rule rule = event.getMatch().getRule();

        String ruleName = rule.getName();
        Map<String, Object> ruleMetaDataMap = rule.getMetaData();

        matchList.add(event.getMatch());
        StringBuilder sb = new StringBuilder("Rule fired: " + ruleName);

        if (ruleMetaDataMap.size() > 0) {
            sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
            for (String key : ruleMetaDataMap.keySet()) {
                sb.append("\n    key=" + key + ", value="
                        + ruleMetaDataMap.get(key));
            }
        }

        log.debug(sb.toString());
    }

    public boolean isRuleFired(String ruleName) {
        for (Match a : matchList) {
            if (a.getRule().getName().equals(ruleName)) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        matchList.clear();
    }

    public final List<Match> getMatchList() {
        return matchList;
    }

    public String matchsToString() {
        if (matchList.size() == 0) {
            return "No matchs occurred.";
        } else {
            StringBuilder sb = new StringBuilder("Matchs: ");
            for (Match match : matchList) {
                sb.append("\n  rule: ").append(match.getRule().getName());
            }
            return sb.toString();
        }
    }

}
