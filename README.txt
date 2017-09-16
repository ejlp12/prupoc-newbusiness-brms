
Given a set of data of customers stored in a file per person in `src/test/resources` in the format of INI file.
We call the customer data in the INI file as Coverage Data.

We need to parse it and execute some rules that defined in JBoss BRMS.

	1. RuleNonMedLimit
	2. RuleBenefitComponent
	3. RuleInsurableIntereset	
	4. RuleBMI


--
INI file will be parsed by com.redhat.poc.CoverageDataParser
Location of INI file is set in the `Application.ptoperties` in parameter `inputDirectory`


To test, run any file in the com.redhat.poc.*
Rule project of course need to be cloned, build and deploy in JBoss BRMS. 
