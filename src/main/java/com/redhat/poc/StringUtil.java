package com.redhat.poc;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import prupoc.newbusiness.CoverageData;

public class StringUtil {

	public static String printSimple(CoverageData data) {
		return CoverageData.class.getCanonicalName() + " [documentType=" + data.getDocumentType() 
				+ ", gender=" + data.getGender()
				+ ", maritalStatus=" + data.getMaritalStatus() + ", height=" + data.getHeight()
				+ ", weight=" + data.getWeight() + ", policyNumber=" + data.getPolicyNumber()
				+ ", policyCode=" + data.getPolicyCode() + ", productCode=" + data.getProductCode()
				+ ", policyOwner=" + data.getPolicyOwner() + ", insurableInterest="
				+ data.getInsurableInterest() + ", occupation=" + data.getOccupation() + ", forenames="
				+ data.getForenames() + ", surname=" + data.getSurname() + ", dateOfBirth="
				+ data.getDateOfBirth() + ", ruleResultBMI=" + data.getRuleResultBMI()
				+ ", ruleResultInsurableInterest=" + data.getRuleResultInsurableInterest()
				+ ", ruleResultOccupationBenefit=" + data.getRuleResultOccupationBenefit()
				+ ", ruleResultNonMedicalLimit=" + data.getRuleResultNonMedicalLimit()
				+ ", ruleResultBenefitLife=" + data.getRuleResultBenefitLife()
				+ ", ruleResultBenefitADD=" + data.getRuleResultBenefitADD()
				+ ", ruleResultBenefitTPD=" + data.getRuleResultBenefitTPD()
				+ ", ruleResultBenefitCC=" + data.getRuleResultBenefitCC()
				+ ", ruleResultBenefitHealth=" + data.getRuleResultBenefitHealth()
				+ ", ageInYear=" + data.getAgeInYear() + ", ageInMonth=" + data.getAgeInMonth() + "]";
	}

	/**
	 * Converts an object to a string representation that lists all fields.
	 * 
	 * @param obj
	 *            an object
	 * @return a string with the object's class name and all field names and
	 *         values
	 */
	public static String toString(Object obj) {
		ArrayList<Object> visited = new ArrayList<Object>();
		
		if (obj == null)
			return "null";
		if (visited.contains(obj))
			return "...";
		visited.add(obj);
		Class cl = obj.getClass();
		if (cl == String.class)
			return (String) obj;
		if (cl.isArray()) {
			String r = cl.getComponentType() + "[]{";
			for (int i = 0; i < Array.getLength(obj); i++) {
				if (i > 0)
					r += ",";
				Object val = Array.get(obj, i);
				if (cl.getComponentType().isPrimitive())
					r += val;
				else
					r += toString(val);
			}
			return r + "}";
		}

		String r = cl.getName();
		// inspect the fields of this class and all superclasses
		do {
			r += "[";
			Field[] fields = cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			// get the names and values of all fields
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					if (!r.endsWith("["))
						r += ",";
					r += f.getName() + "=";
					try {
						Class t = f.getType();
						Object val = f.get(obj);
						if (t.isPrimitive())
							r += val;
						else
							r += toString(val);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			r += "]";
			cl = cl.getSuperclass();
		} while (cl != null);

		return r;
	}
}
