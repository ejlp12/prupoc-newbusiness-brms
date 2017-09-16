package com.redhat.poc.brms;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.common.InternalAgenda;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugProcessEventListener;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieScanner;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.Match;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.kie.server.api.marshalling.MarshallingFormat;
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

public class BrmsLocalUtil {
	
	public static boolean AUDIT_FILE_LOG = false;
	private static Logger LOG = LoggerFactory.getLogger(BrmsLocalUtil.class);
	private static ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


	
	private static KieServices kieServices = null;

	public static KieContainer getKieContainer() {
		
		// ## Set logger to DEBUG 
		if (AppProperties.getString("brms.debug").equalsIgnoreCase("true")) {
			rootLogger.setLevel(Level.DEBUG);
		}
		
		if (AppProperties.getString("brms.audit").equalsIgnoreCase("true")) {
			AUDIT_FILE_LOG = true;
		}

		final String serverUrl = AppProperties.getString("brms.guvnorUrl");
		final String user = AppProperties.getString("brms.username");
		final String password = AppProperties.getString("brms.password");
		;
		final String projectRelease = AppProperties
				.getString("brms.projectRelease");

		// works even without -SNAPSHOT versions
		String url = serverUrl + AppProperties.getString("brms.kjarUrl");

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

		// KieContainer kieContainer = ks.newKieContainer(releaseId); //java.lang.RuntimeException: Cannot find KieModule: prupoc:newbusiness:LATEST
		

		// check if there is a new version at the URL
		KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
		// alternatively:
		LOG.debug("\t##### Scanning now...");
		kieScanner.scanNow();

		return kieContainer;

	}

}