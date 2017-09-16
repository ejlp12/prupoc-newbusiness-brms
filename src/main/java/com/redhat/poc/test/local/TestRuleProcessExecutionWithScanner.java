package com.redhat.poc.test.local;

import java.io.IOException;
import java.io.InputStream;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.common.InternalAgenda;
import org.drools.core.event.DebugProcessEventListener;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieScanner;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import prupoc.newbusiness.CoverageData;
import ch.qos.logback.classic.Level;

import com.redhat.poc.StringUtil;
import com.redhat.poc.brms.BrmsConstants;
import com.redhat.poc.test.AppProperties;
import com.redhat.poc.test.EjlpDebugAgendaEventListener;
import com.redhat.poc.test.SampleDataFactory;

public class TestRuleProcessExecutionWithScanner {
	
	private static final boolean AUDIT_FILE_LOG = false;
	private static Logger LOG = LoggerFactory.getLogger(TestRuleProcessExecutionWithScanner.class);
	private static ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


	
	private static KieServices kieServices = null;
	private static KieRuntimeLogger kieLogger = null;

	public static void main(String[] args) {
		
		// ## Set logger to DEBUG 
		rootLogger.setLevel(Level.DEBUG);

		final String serverUrl = AppProperties.getString("brms.guvnorUrl");
		final String user = AppProperties.getString("brms.username");
		final String password = AppProperties.getString("brms.password");
		;
		final String projectRelease = AppProperties
				.getString("brms.projectRelease");

		// works even without -SNAPSHOT versions
		String url = serverUrl + "/prupoc/newbusiness/1.2/newbusiness-1.2.jar";

		// make sure you use "LATEST" here!
		String[] projectNames = projectRelease.split(":");
		ReleaseIdImpl releaseId = new ReleaseIdImpl(projectNames[0], projectNames[1], "LATEST");

		// KieServices kieServices = KieServices.Factory.get();
		kieServices = KieServices.Factory.get();
		KieRepository kieRepo = kieServices.getRepository();

		InputStream is = null;

		UrlResource urlResource = (UrlResource) kieServices.getResources().newUrlResource(url);
		urlResource.setBasicAuthentication("enabled");
		urlResource.setUsername(user);
		urlResource.setPassword(password);

		try {
			is = urlResource.getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		KieModule kModule = kieRepo.addKieModule(kieServices.getResources().newInputStreamResource(is));
		KieContainer kieContainer = kieServices.newKieContainer(kModule.getReleaseId());

		KieBase kBase = kieContainer.getKieBase();
		for (KiePackage a : kBase.getKiePackages()) {
			LOG.debug("\t###### Package: " + a.getName());
			for (Rule r : a.getRules()) {
				LOG.debug("\t\t###### Rule: " + r.getName());
			}
		}

		// KieContainer kieContainer = ks.newKieContainer(releaseId); //
		// java.lang.RuntimeException: Cannot find KieModule: prupoc:newbusiness:LATEST

		// check every 5 seconds if there is a new version at the URL
		KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
		kieScanner.start(5000L);
		// alternatively:
		// kieScanner.scanNow();

		// Scanner scanner = new Scanner(System.in);
		// while (true) {
		runRule(kieContainer);
		// LOG.debug("Press enter in order to run the test again....");
		// scanner.nextLine();
		// }
	}

	private static void runRule(KieContainer kieKontainer) {

		// It will read a single txt files in a directory, parse and generate a CoverageData object.
		// Change in Application.properties if you want to select other file name
		SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
		CoverageData factData = SampleDataFactory.getGenerator().getSingle();


		// Statefull Session
		KieSession kSession = kieKontainer.newKieSession();
		if (rootLogger.isDebugEnabled()) {
		    kSession.addEventListener( new DebugAgendaEventListener() );
			kSession.addEventListener(new EjlpDebugAgendaEventListener());
		    kSession.addEventListener( new DebugRuleRuntimeEventListener() );
		    kSession.addEventListener( new DebugProcessEventListener() );
		}

		// For debug
		// @See http://developers.redhat.com/blog/2016/08/12/get-it-done-with-these-5-techniques-to-debug-your-jboss-brms-applications/
		if (AUDIT_FILE_LOG) {
			kieLogger = kieServices.getLoggers().newFileLogger(kSession, "audit");
		}

		kSession.insert(factData);
		
//		String ruleflow = BrmsConstants.RULE_FLOW_GROUP_NON_MED_LIMIT;
//		LOG.info("\t##### Activate ruleflow group: " + ruleflow);
//		((InternalAgenda) kSession.getAgenda()).activateRuleFlowGroup(ruleflow);

		
		
		int numOfRule = kSession.fireAllRules();
		LOG.debug("No of rule fired >> " + numOfRule);
				
		LOG.debug("\t##### Result >> " + StringUtil.printSimple(factData) );
		LOG.debug("Result AgeInMonth >> " + factData.getAgeInMonth() );
		LOG.debug("Result AgeInYear >> " + factData.getAgeInYear() );
		LOG.debug("Result RuleResultBMI >> " + factData.getRuleResultBMI() );
		LOG.debug("Result ProductCode >> " + factData.getProductCode() );
		LOG.debug("Result RuleResultProductCategory >> " + factData.getRuleResultProductCategory() );
		LOG.debug("Result CurrentADDSumAssured >> " + factData.getCurrentADDSumAssured() );
		LOG.debug("Result CurrentADDSumAssured >> " + factData.getCurrentLifeSumAssured() );
		LOG.debug("Result RuleResultSumAssuredHigherIDRNML >> " + factData.getRuleResultSumAssuredHigherIDRNML() );
		LOG.debug("Result RuleResultNonMedicalLimit >> " + factData.getRuleResultNonMedicalLimit() );
		
		System.err.println("********************************************** CLASS INI BELUM DIKERJAKAN **********************************************");
		
		
//		factData.setRuleResultProductCategory("Regular");
//		kSession.insert(factData);
//		
//		LOG.info("\t##### Activate ruleflow group: " + "rule-non-med-limit2");
//		((InternalAgenda) kSession.getAgenda()).activateRuleFlowGroup("rule-non-med-limit2");
//		
//		numOfRule = kSession.fireAllRules();
//		LOG.debug("\t##### No of rule fired >> " + numOfRule);
//				
//
//		LOG.debug("Result RuleResultSumAssuredHigherIDRNML >> " + factData.getRuleResultSumAssuredHigherIDRNML() );
//		LOG.debug("Result RuleResultNonMedicalLimit >> " + factData.getRuleResultNonMedicalLimit() );		

		if (AUDIT_FILE_LOG) {
			kieLogger.close();
		}

		kSession.dispose();
	}
}