package com.redhat.poc.test.local;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.common.InternalAgenda;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugProcessEventListener;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieScanner;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
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
import com.thoughtworks.xstream.core.util.Base64Encoder;


/**
 * DIFFERENT METHOD: Use JRE API (HttpURLConnection) for getting KJAR from Maven Repo (business-central)
 * instead of something like this:
 * 
 *		UrlResource urlResource = (UrlResource) kieServices.getResources().newUrlResource(url);
 *		urlResource.setBasicAuthentication("enabled");
 *		urlResource.setUsername(user);
 *		urlResource.setPassword(password); 
 *		InputStream is = urlResource.getInputStream();
 * 
 * */
public class TestRuleExecutionWithScanner2 {

	// To setup a file based audit logger, set it to true
	private static final boolean AUDIT_FILE_LOG = false;
	private static Logger LOG = LoggerFactory.getLogger(TestRuleExecutionWithScanner2.class);
	private static ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	private static KieServices kieServices = null;
	private static KieRuntimeLogger kieLogger = null;
	
	

	public static void main(String[] args) {
		
		rootLogger.setLevel(Level.DEBUG);

		final String serverUrl = AppProperties.getString("brms.guvnorUrl");
		final String user = AppProperties.getString("brms.username");
		final String password = AppProperties.getString("brms.password");

		final String projectRelease = AppProperties.getString("brms.projectRelease");
		
		//
		String releaseParts[] = projectRelease.split(":");
		String kjarPath = "/" + projectRelease.replace(':','/') + "/" + releaseParts[1] + "-" + releaseParts[2] + ".jar";
	
		// works even without -SNAPSHOT versions
		String url = serverUrl + kjarPath;
		LOG.debug("URL: " + url);

		// make sure you use "LATEST" here!
		String[] projectNames = projectRelease.split(":");
		ReleaseIdImpl releaseId = new ReleaseIdImpl(projectNames[0], projectNames[1], "LATEST");

		// KieServices kieServices = KieServices.Factory.get();
		kieServices = KieServices.Factory.get();
		KieRepository kieRepo = kieServices.getRepository();

		// DIFFERENT METHOD: Use JRE API for getting KJAR from Maven Repo (business-central)
		InputStream is = null;
		try {
			HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
			String authEnc = new Base64Encoder().encode((user + ":" + password).getBytes());
			http.setRequestMethod("GET");
			http.setRequestProperty("Authorization", "Basic " + authEnc);
			http.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
			http.setRequestProperty("Accept", "*/*");
			//LOG.debug("HTTP response code:" + http.getResponseCode());
			is = http.getInputStream();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// ## EJLP12: Using Apache HTTP Client
		//try {
		// 		DefaultHttpClient client = new DefaultHttpClient();
		//      Credentials credentials = new UsernamePasswordCredentials(user, password);
		// 		client.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
		//      HttpGet request = new HttpGet(url);
		//      HttpResponse response = client.execute(request);
		//      LOG.debug("HTTP response code:" + response.getStatusLine());
		//      is = response.getEntity().getContent();
		// } catch (IllegalStateException | IOException e) {
		//      e.printStackTrace();
		// }

		KieModule kModule = kieRepo.addKieModule(kieServices.getResources().newInputStreamResource(is));
		KieContainer kieContainer = kieServices.newKieContainer(kModule.getReleaseId());
		//KieContainer kieContainer = kieServices.newKieContainer(releaseId); // java.lang.RuntimeException: Cannot find KieModule: prupoc:newbusiness:LATEST
		LOG.debug("\t##### KieModule releaseId: " + kModule.getReleaseId());

		KieBase kBase = kieContainer.getKieBase();
		for (KiePackage a : kBase.getKiePackages()) {
			LOG.debug("\t###### Package: " + a.getName());
			for (Rule r : a.getRules()) {
				LOG.debug("\t\t###### Rule: " + r.getName());
			}
		}

		

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
		// Stateless Session
		// StatelessKieSession kSession = kieKontainer.newStatelessKieSession();
		// kSession.addEventListener( new DebugAgendaEventListener() );
		// kSession.addEventListener( new DebugRuleRuntimeEventListener() );
		// kSession.addEventListener( new DebugProcessEventListener() );
		//
		// //kSession.setGlobal("out", System.out);
		// //kSession.execute("testRuleAgain");
		//
		// // It will read a single txt files in a directory, parse and generate a CoverageData object.
		// // Change in Application.properties if you want to select other file name
		SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
		CoverageData factData = SampleDataFactory.getGenerator().getSingle();

		// Stateful Session
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
		((InternalAgenda) kSession.getAgenda()).activateRuleFlowGroup(BrmsConstants.RULE_FLOW_GROUP_BMI + "-female");
		kSession.fireAllRules();

		LOG.info("Result >> " + StringUtil.printSimple(factData));

		if (AUDIT_FILE_LOG) {
			kieLogger.close();
		}

		kSession.dispose();
	}
}