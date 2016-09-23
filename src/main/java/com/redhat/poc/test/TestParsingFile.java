package com.redhat.poc.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import com.redhat.poc.CoverageDataParser;

import prupoc.newbusiness.CoverageData;
import prupoc.newbusiness.SumAssured;


public class TestParsingFile {

	public static void main(String[] args) {
		
		
		String filename = "/Users/ejlp12/workspace_pru_poc_newbusiness/inifile/src/test/resources/[Data] 512_2.txt";
		CoverageData data = parseFile(filename);
	
	}
	
	public static CoverageData parseFile(String filename) {
		
		CoverageData data = null;
		
		try {
			
			//Parse data
			data = CoverageDataParser.parse(filename);
			
			System.out.println("=====================");
			
			// Print out object just for testing
			System.out.println(data);
			
			// Print out field which has multiple data (List)
			for (SumAssured data1 : data.getSumAssured()) {
				System.out.println( "sumAssured =" + data1 );
			}
			for (String data1 : data.getOccupation()) {
				System.out.println( "occupation = " + data1 );
			}

			for (String data1 : data.getImpairment()) {
				System.out.println( "impairment = " + data1 );
			}
			for (String data1 : data.getAvocation()) {
				System.out.println( "avocation = " + data1 );
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	

}
