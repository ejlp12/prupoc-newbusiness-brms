package com.redhat.poc.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import prupoc.newbusiness.CoverageData;

public class SampleDataGenerator implements SampleDataGeneratorInf {
	
	private static Logger LOG = LoggerFactory.getLogger(SampleDataFromFileGenerator.class);
	
	public List<Object> getList() {
		
        CoverageData data1 = new CoverageData();
        data1.setDateOfBirth("1/10/2016");
        data1.setHeight("48");
        data1.setWeight("2.8");
        data1.setGender("M");

        CoverageData data2 = new CoverageData();
        data2.setDateOfBirth("01/09/2016");
        data2.setHeight("57");
        data2.setWeight("5.3");
        data1.setGender("M");

        CoverageData data3 = new CoverageData();
        data3.setDateOfBirth("01/10/2016");
        data3.setHeight("47");
        data3.setWeight("2.6");
        data1.setGender("F");

        
        List<Object> factData = new ArrayList<Object>();
        factData.add(data1);
        factData.add(data2);
        factData.add(data3);
        
        return factData;
	}
	
	public CoverageData getSingle() {
		CoverageData data1 = new CoverageData();
        data1.setDateOfBirth("1/09/2016");
        data1.setHeight("57");
        data1.setWeight("4");
        data1.setGender("M");
        
        return data1;
        
	}

}
