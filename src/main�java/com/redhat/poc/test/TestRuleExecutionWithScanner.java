package com.redhat.poc.test;



	import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieScanner;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.server.api.marshalling.MarshallingFormat;

import prupoc.newbusiness.CoverageData;

import com.redhat.poc.brms.BrmsConstants;
import com.thoughtworks.xstream.core.util.Base64Encoder;

	public class TestRuleExecutionWithScanner {

	    public static void main(String[] args) {
	    	
	        final String serverUrl = AppProperties.getString("brms.guvnorUrl");
	        final String user = AppProperties.getString("brms.username");
	        final String password = AppProperties.getString("brms.password");;
	        final String projectRelease = AppProperties.getString("brms.projectRelease");

	        // works even without -SNAPSHOT versions
	        String url = serverUrl + "/prupoc/newbusiness/1.2/newbusiness-1.2.jar";

	        
	        // make sure you use "LATEST" here!
	        String[] projectNames = projectRelease.split(":");
	        ReleaseIdImpl releaseId = new ReleaseIdImpl(projectNames[0], projectNames[1], "LATEST");

	        KieServices kieServices = KieServices.Factory.get();
	        KieRepository kieRepo = kieServices.getRepository();
	        
	        InputStream is = null;
//			try {
//				HttpURLConnection http = (HttpURLConnection)new URL(url).openConnection();
//				String authEnc = new Base64Encoder().encode((user + ":" + password).getBytes());
//				http.setRequestMethod("GET");
//				http.setRequestProperty("Authorization", "Basic "+ authEnc);
//				http.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
//				http.setRequestProperty("Accept","*/*");
//				System.out.println("HTTP response code:" + http.getResponseCode());
//				is = http.getInputStream();
//			} catch (MalformedURLException e) {
//				throw new RuntimeException(e);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}

	        UrlResource urlResource = (UrlResource) kieServices.getResources().newUrlResource(url);
	        urlResource.setBasicAuthentication("enabled"); 
	        urlResource.setUsername(user); 
	        urlResource.setPassword(password);
	        
			try {
				is = urlResource.getInputStream();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	        
//	        try {
//	        	DefaultHttpClient client = new DefaultHttpClient();				
//				Credentials credentials = new UsernamePasswordCredentials(user, password);
//				client.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
//				System.out.println("URL : " + url);
//				HttpGet request = new HttpGet(url);
//				HttpResponse response = client.execute(request);
//				System.out.println("HTTP response code:" + response.getStatusLine());
//				InputStream instream = response.getEntity().getContent();
//			} catch (IllegalStateException | IOException e) {
//				e.printStackTrace();
//			}
	        
	        

	        KieModule kModule = kieRepo.addKieModule( kieServices.getResources().newInputStreamResource(is) );
	        KieContainer kieContainer = kieServices.newKieContainer(kModule.getReleaseId());	
	        
	        //KieContainer kieContainer = ks.newKieContainer(releaseId); // java.lang.RuntimeException: Cannot find KieModule: prupoc:newbusiness:LATEST

	        // check every 5 seconds if there is a new version at the URL
	        KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
	        kieScanner.start(5000L);
	        // alternatively:
	        // kieScanner.scanNow();

	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            runRule(kieContainer);
	            System.out.println("Press enter in order to run the test again....");
	            scanner.nextLine();
	        }
	    }

	    private static void runRule(KieContainer kieKontainer) {
	    	System.out.println("kieKontainer: " + kieKontainer);
	        StatelessKieSession kSession = kieKontainer.newStatelessKieSession("testSession");
	        //kSession.setGlobal("out", System.out);
	        //kSession.execute("testRuleAgain");
	        
	    	// It will read a single txt files in a directory, parse and generate a CoverageData object.
	    	// Change in Application.properties if you want to select other file name
	    	SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);
	    	CoverageData factData = SampleDataFactory.getGenerator().getSingle();
	    	
	    	KieCommands commandsFactory = KieServices.Factory.get().getCommands();
	    	
	        List<Command<?>> commands = new ArrayList<Command<?>>();                
	        commands.add(commandsFactory.newInsert(factData, "test"));        
	        commands.add(commandsFactory.newAgendaGroupSetFocus(BrmsConstants.RULE_FLOW_GROUP_BMI + "-female"));  
	        commands.add(commandsFactory.newFireAllRules("hasil"));
	    	
	        System.out.println("kSession: " + kSession);
	    	kSession.execute(commands);
	    }
	}
