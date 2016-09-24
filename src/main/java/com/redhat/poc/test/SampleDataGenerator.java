package com.redhat.poc.test;

import java.util.ArrayList;
import java.util.List;

import prupoc.newbusiness.CoverageData;

public class SampleDataGenerator implements SampleDataGeneratorInf {
	
	public List<Object> getList() {
		
        CoverageData data1 = new CoverageData();
        data1.setDateOfBirth("11-Dec-2013");
        data1.setHeight("47");
        data1.setWeight("3.0");

        CoverageData data2 = new CoverageData();
        data2.setDateOfBirth("11-Dec-2016");
        data2.setHeight("58");
        data2.setWeight("3.0");

        CoverageData data3 = new CoverageData();
        data3.setDateOfBirth("11-Dec-2016");
        data3.setHeight("69");
        data3.setWeight("3.0");
        
        List<Object> factData = new ArrayList<Object>();
        factData.add(data1);
        factData.add(data2);
        factData.add(data3);
        
        return factData;
	}
	
	public CoverageData getSingle() {
        CoverageData data1 = new CoverageData();
        data1.setDateOfBirth("11-Dec-2013");
        data1.setHeight("47");
        data1.setWeight("3.0");
        
        return data1;
        
	}

}
