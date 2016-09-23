package org.jbpm.test.kieserver;

import prupoc.newbusiness.CoverageData;

public interface PrupocBrmsClientInf {

	public CoverageData executeRuleNonMedLimit(CoverageData data);
	
	public CoverageData executeRuleBenefitComponent(CoverageData data);

	public CoverageData executeRuleInsurableIntereset(CoverageData data);
	
	public CoverageData executeRuleBMI(CoverageData data);	
}
