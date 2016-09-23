package com.redhat.poc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import prupoc.newbusiness.CoverageData;
import prupoc.newbusiness.SumAssured;

public class CoverageDataParser {
	
	public static final String HEADER_DOCUMENT_TYPE = "DOCUMENT TYPE";
	public static final String HEADER_LIFE_1 = "LIFE 1";
	public static final String HEADER_LIFE_1_BUILD = "LIFE 1 BUILD";
	public static final String HEADER_LIFE_1_HABITS = "LIFE 1 HABITS";
	public static final String HEADER_LIFE_1_OCCUPATIONS = "LIFE 1 OCCUPATIONS";
	public static final String HEADER_LIFE_1_AVOCATIONS = "LIFE 1 AVOCATIONS";
	public static final String HEADER_LIFE_1_COUNTRIES = "LIFE 1 COUNTRIES";
	public static final String HEADER_LIFE_1_IMPAIRMENTS = "LIFE 1 IMPAIRMENTS";
	public static final String HEADER_LIFE_1_SUM_ASSUREDS = "LIFE 1 SUM ASSUREDS";
	public static final String HEADER_LIFE_1_INCOME = "LIFE 1 INCOME";
	public static final String HEADER_POLICY = "POLICY";

	public static CoverageData parse(String filename) throws IOException {

		CoverageData data = new CoverageData();
		SumAssured sumAssured = null;
		List<SumAssured> sumAssuredList = new ArrayList<SumAssured>();
		List<String> occupations = new ArrayList<String>();
		List<String> impairments = new ArrayList<String>();
		List<String> avocations = new ArrayList<String>();
		
		
		BufferedReader b = new BufferedReader(new FileReader(filename));
		String line = "";
		String section = "";
		
		
		while ((line = b.readLine()) != null) {
			String trimline = line.trim();

			if (trimline.isEmpty())
				continue;

			// Check if it is a section e.g. [SECTION], then ignore it.
			if (trimline.charAt(0) == '[' && trimline.endsWith("]")) {
				section = trimline.substring(1, trimline.length() - 1);
				// System.out.println("SECTION: " + section);
			} else {
				
				// Split each key-value pair to key = param[0] and value = param[1]
				String[] param = trimline.split("=");
				//System.out.println( param[0].trim() + " -> " + param[1].trim());

				// For some section, we need to create an array because it contains multiple same key-names
				switch (section) {
				case HEADER_LIFE_1_OCCUPATIONS:
					if (param[0].equals("Occupation")) {
						occupations.add(param[1]);
					} 				
					
					break;
				case HEADER_LIFE_1_IMPAIRMENTS:
					if (param[0].equals("Impairment")) {
						impairments.add(param[1]);
					} 
					
					break;
				case HEADER_LIFE_1_AVOCATIONS:				
					if (param[0].equals("Avocation")) {
						avocations.add(param[1]);
					} 	
					
					break;
				case HEADER_LIFE_1_SUM_ASSUREDS:
					
					if (param[0].equals("SumAssuredType")) {
						sumAssured = new SumAssured();
						sumAssured.setType(param[1]);
					} else if( param[0].equals("SumAssuredValue") ) {
						sumAssured.setValue(new BigDecimal(param[1].replaceAll(",", "")));
					} else if( param[0].equals("SumAssuredCurrency") ) {
						sumAssured.setCurrency(param[1]);
						
						//System.out.println(sumAssured);
						sumAssuredList.add(sumAssured);
						
						data.setSumAssured(sumAssuredList);
					}					
					
					break;
				default:
					
					
					// Set data by calling setXXX() method using Java reflection API
					Class[] paramString = new Class[1];
					paramString[0] = String.class;
	
					try {
						System.out.println("\t... [TEST] set" + param[0]);
						Method method = data.getClass().getDeclaredMethod("set" + param[0], paramString);
						method.invoke(data, new String(param[1]));
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}					
					
					break;
				}	
			}

		}
		b.close();
		
		
		
		data.setAgeInMonth(Utility.calculateAgeInMonth(data.getDateOfBirth(), "dd/MM/yyyy"));
		data.setAgeInYear(Utility.calculateAgeInYear(data.getDateOfBirth(), "dd/MM/yyyy"));
		
		// Set all array variables
		data.setOccupation( occupations );
		data.setImpairment( impairments );
		data.setAvocation( avocations );
		data.setSumAssured( sumAssuredList );	
		
		return data;
	}

}
