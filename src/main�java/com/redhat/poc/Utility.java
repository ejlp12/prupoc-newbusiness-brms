package com.redhat.poc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

// This class will need Java 1.8
public class Utility {

	public static Integer calculateAgeInYear(String dateOfBirth, String dateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		LocalDate birthdate = formatter.parse(dateOfBirth).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int age = Period.between(birthdate, now).getYears();

		return age;
	}

	public static Integer calculateAgeInMonth(String dateOfBirth, String dateFormat) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		LocalDate birthdate = formatter.parse(dateOfBirth).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int age = Period.between(birthdate, now).getYears();
		age = (age * 12) + Period.between(birthdate, now).getMonths();

		return age;
	}

}
