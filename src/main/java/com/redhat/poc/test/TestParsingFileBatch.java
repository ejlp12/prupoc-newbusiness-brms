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
import com.redhat.poc.StringUtil;

import prupoc.newbusiness.CoverageData;
import prupoc.newbusiness.SumAssured;


public class TestParsingFileBatch {

	public static void main(String[] args) {
		
		
			SampleDataFactory.setGeneratorType(SampleDataFactory.GENERATOR_FROM_FILE);			
			SampleDataGeneratorInf generator = SampleDataFactory.getGenerator();
			
			System.out.println("\t#### Directory to be scanned: " + SampleDataFromFileGenerator.INPUT_DIR);
			
			// Read all files in directory
			List<Object>  dataList = generator.getList();
			

			System.out.println("\t#### Found data files, count: " + dataList.size());
			
			// Print out all result data after parsing
			for ( Object obj : dataList) {
				System.out.println("=====================");
				
				// Print out object just for testing
				CoverageData data = (CoverageData) obj;
				System.out.println(StringUtil.toString(data));
				
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
			}
			
	}
	

}
