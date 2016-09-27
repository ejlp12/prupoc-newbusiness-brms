package com.redhat.poc.test;

import java.text.ParseException;

import com.redhat.poc.Utility;

public class TestCalculateAge {
	public static void main(String[] args) throws ParseException {
		//Test only
		String birthDate = "23/09/2000";
		
		System.out.println( "Birthdate: " + birthDate  + ", ageInYear: " + Utility.calculateAgeInYear(birthDate, "dd/MM/yyyy"));
		
		birthDate = "01/01/2016";
		
		System.out.println( "Birthdate: " + birthDate  + ", ageInMonth: " + Utility.calculateAgeInMonth(birthDate, "dd/MM/yyyy"));
	}
}
