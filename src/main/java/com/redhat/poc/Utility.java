package com.redhat.poc;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utility {
	
	public static int calculateAgeInMonth(String birthDate, String dateFormat) {
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern(dateFormat);
		LocalDate birthdate = dtf.parseDateTime(birthDate).toLocalDate();
		LocalDate now = new LocalDate();
		Months age = Months.monthsBetween(birthdate, now);
	
		return age.getMonths();
		
	}
	
	public static int calculateAgeInYear(String birthDate, String dateFormat) {
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern(dateFormat);
		LocalDate birthdate = dtf.parseDateTime(birthDate).toLocalDate();
		LocalDate now = new LocalDate();
		Years age = Years.yearsBetween(birthdate, now);
	
		return age.getYears();
		
	}
}
