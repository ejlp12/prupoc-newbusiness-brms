package prupoc.newbusiness;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class CoverageData implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label("Document Type")
   private java.lang.String documentType;
   @org.kie.api.definition.type.Label("Gender")
   private java.lang.String gender;
   @org.kie.api.definition.type.Label("Marital Status")
   private java.lang.String maritalStatus;
   @org.kie.api.definition.type.Label("Height")
   private java.lang.String height;
   @org.kie.api.definition.type.Label("Weight")
   private java.lang.String weight;
   @org.kie.api.definition.type.Label("Alcohol Consumption")
   private java.lang.String alcoholConsumption;
   @org.kie.api.definition.type.Label("Addictive Drug Use")
   private java.lang.String addictiveDrugUse;
   @org.kie.api.definition.type.Label("Medical Drug Use")
   private java.lang.String medicalDrugUse;
   @org.kie.api.definition.type.Label("Smoker Status")
   private java.lang.String smokerStatus;
   @org.kie.api.definition.type.Label("Country")
   private java.lang.String country;
   @org.kie.api.definition.type.Label("Country Of Nationality")
   private java.lang.String countryOfNationality;
   @org.kie.api.definition.type.Label("On Social Visit Pass")
   private java.lang.String onSocialVisitPass;
   @org.kie.api.definition.type.Label("Social Visa Type")
   private java.lang.String socialVisaType;
   @org.kie.api.definition.type.Label("Income Per Year")
   private java.lang.String incomePerYear;
   @org.kie.api.definition.type.Label("Policy Number")
   private java.lang.String policyNumber;
   @org.kie.api.definition.type.Label("Proposal Receive Date")
   private java.lang.String proposalReceiveDate;
   @org.kie.api.definition.type.Label("Processing Date")
   private java.lang.String processingDate;
   @org.kie.api.definition.type.Label("Distribution Channel")
   private java.lang.String distributionChannel;
   @org.kie.api.definition.type.Label("Policy Code")
   private java.lang.String policyCode;
   @org.kie.api.definition.type.Label("Product Code")
   private java.lang.String productCode;
   @org.kie.api.definition.type.Label("Proposed Life")
   private java.lang.String proposedLife;
   @org.kie.api.definition.type.Label("Joint Life1")
   private java.lang.String jointLife1;
   @org.kie.api.definition.type.Label("Joint Life2")
   private java.lang.String jointLife2;
   @org.kie.api.definition.type.Label("Policy Owner")
   private java.lang.String policyOwner;
   @org.kie.api.definition.type.Label("Insurable Interest")
   private java.lang.String insurableInterest;
   @org.kie.api.definition.type.Label("Cover Type")
   private java.lang.String coverType;
   @org.kie.api.definition.type.Label("Risk Prof Sign Date Check")
   private java.lang.String riskProfSignDateCheck;
   @org.kie.api.definition.type.Label("Risk Prof Result")
   private java.lang.String riskProfResult;
   @org.kie.api.definition.type.Label("Risk Prof Suitable")
   private java.lang.String riskProfSuitable;
   @org.kie.api.definition.type.Label("Doc Support Complete")
   private java.lang.String docSupportComplete;
   @org.kie.api.definition.type.Label("Signature Fraud")
   private java.lang.String signatureFraud;
   @org.kie.api.definition.type.Label("Random Check Medical")
   private java.lang.String randomCheckMedical;
   @org.kie.api.definition.type.Label("Sales Branch Code")
   private java.lang.String salesBranchCode;
   @org.kie.api.definition.type.Label("Occupation")
   private java.util.List<java.lang.String> occupation;
   @org.kie.api.definition.type.Label("Impairment")
   private java.util.List<java.lang.String> impairment;
   @org.kie.api.definition.type.Label("Avocation")
   private java.util.List<java.lang.String> avocation;
   @org.kie.api.definition.type.Label("Sum Assured")
   private java.util.List<prupoc.newbusiness.SumAssured> sumAssured;

   @org.kie.api.definition.type.Label("KYC Flag")
   private java.lang.String KYCFlag;

   @org.kie.api.definition.type.Label("SPAJ number")
   private java.lang.String SPAJnumber;

   @org.kie.api.definition.type.Label("SPAJ Complete")
   private java.lang.String SPAJComplete;

   @org.kie.api.definition.type.Label("SQS Complete")
   private java.lang.String SQSComplete;

   @org.kie.api.definition.type.Label("ID Processable")
   private java.lang.String IDProcessable;

   @org.kie.api.definition.type.Label("ID Submitted")
   private java.lang.String IDSubmitted;

   @org.kie.api.definition.type.Label("Forenames")
   private java.lang.String forenames;

   @org.kie.api.definition.type.Label("Surname")
   private java.lang.String surname;

   @org.kie.api.definition.type.Label("Date Of Birth")
   private java.lang.String dateOfBirth;

   @org.kie.api.definition.type.Label("Rule Result BMI")
   private java.lang.String ruleResultBMI;

   @org.kie.api.definition.type.Label("RuleResult InsurableInterest")
   private java.lang.String ruleResultInsurableInterest;

   @org.kie.api.definition.type.Label("Rule Result OccupationBenefit")
   private prupoc.newbusiness.RuleResultOccupationBenefit ruleResultOccupationBenefit;

   @org.kie.api.definition.type.Label("RuleResult NonMedicalLimit")
   private java.lang.String ruleResultNonMedicalLimit;

   private java.lang.String ruleResultBenefitLife;

   private java.lang.String ruleResultBenefitADD;

   private java.lang.String ruleResultBenefitTPD;

   private java.lang.String ruleResultBenefitCC;

   private java.lang.String ruleResultBenefitHealth;

   private java.lang.Integer ageInYear;

   private java.lang.Integer ageInMonth;

   public CoverageData()
   {
   }

   public java.lang.String getDocumentType()
   {
      return this.documentType;
   }

   public void setDocumentType(java.lang.String documentType)
   {
      this.documentType = documentType;
   }

   public java.lang.String getGender()
   {
      return this.gender;
   }

   public void setGender(java.lang.String gender)
   {
      this.gender = gender;
   }

   public java.lang.String getMaritalStatus()
   {
      return this.maritalStatus;
   }

   public void setMaritalStatus(java.lang.String maritalStatus)
   {
      this.maritalStatus = maritalStatus;
   }

   public java.lang.String getHeight()
   {
      return this.height;
   }

   public void setHeight(java.lang.String height)
   {
      this.height = height;
   }

   public java.lang.String getWeight()
   {
      return this.weight;
   }

   public void setWeight(java.lang.String weight)
   {
      this.weight = weight;
   }

   public java.lang.String getAlcoholConsumption()
   {
      return this.alcoholConsumption;
   }

   public void setAlcoholConsumption(java.lang.String alcoholConsumption)
   {
      this.alcoholConsumption = alcoholConsumption;
   }

   public java.lang.String getAddictiveDrugUse()
   {
      return this.addictiveDrugUse;
   }

   public void setAddictiveDrugUse(java.lang.String addictiveDrugUse)
   {
      this.addictiveDrugUse = addictiveDrugUse;
   }

   public java.lang.String getMedicalDrugUse()
   {
      return this.medicalDrugUse;
   }

   public void setMedicalDrugUse(java.lang.String medicalDrugUse)
   {
      this.medicalDrugUse = medicalDrugUse;
   }

   public java.lang.String getSmokerStatus()
   {
      return this.smokerStatus;
   }

   public void setSmokerStatus(java.lang.String smokerStatus)
   {
      this.smokerStatus = smokerStatus;
   }

   public java.lang.String getCountry()
   {
      return this.country;
   }

   public void setCountry(java.lang.String country)
   {
      this.country = country;
   }

   public java.lang.String getCountryOfNationality()
   {
      return this.countryOfNationality;
   }

   public void setCountryOfNationality(java.lang.String countryOfNationality)
   {
      this.countryOfNationality = countryOfNationality;
   }

   public java.lang.String getOnSocialVisitPass()
   {
      return this.onSocialVisitPass;
   }

   public void setOnSocialVisitPass(java.lang.String onSocialVisitPass)
   {
      this.onSocialVisitPass = onSocialVisitPass;
   }

   public java.lang.String getSocialVisaType()
   {
      return this.socialVisaType;
   }

   public void setSocialVisaType(java.lang.String socialVisaType)
   {
      this.socialVisaType = socialVisaType;
   }

   public java.lang.String getIncomePerYear()
   {
      return this.incomePerYear;
   }

   public void setIncomePerYear(java.lang.String incomePerYear)
   {
      this.incomePerYear = incomePerYear;
   }

   public java.lang.String getPolicyNumber()
   {
      return this.policyNumber;
   }

   public void setPolicyNumber(java.lang.String policyNumber)
   {
      this.policyNumber = policyNumber;
   }

   public java.lang.String getProposalReceiveDate()
   {
      return this.proposalReceiveDate;
   }

   public void setProposalReceiveDate(java.lang.String proposalReceiveDate)
   {
      this.proposalReceiveDate = proposalReceiveDate;
   }

   public java.lang.String getProcessingDate()
   {
      return this.processingDate;
   }

   public void setProcessingDate(java.lang.String processingDate)
   {
      this.processingDate = processingDate;
   }

   public java.lang.String getDistributionChannel()
   {
      return this.distributionChannel;
   }

   public void setDistributionChannel(java.lang.String distributionChannel)
   {
      this.distributionChannel = distributionChannel;
   }

   public java.lang.String getPolicyCode()
   {
      return this.policyCode;
   }

   public void setPolicyCode(java.lang.String policyCode)
   {
      this.policyCode = policyCode;
   }

   public java.lang.String getProductCode()
   {
      return this.productCode;
   }

   public void setProductCode(java.lang.String productCode)
   {
      this.productCode = productCode;
   }

   public java.lang.String getProposedLife()
   {
      return this.proposedLife;
   }

   public void setProposedLife(java.lang.String proposedLife)
   {
      this.proposedLife = proposedLife;
   }

   public java.lang.String getJointLife1()
   {
      return this.jointLife1;
   }

   public void setJointLife1(java.lang.String jointLife1)
   {
      this.jointLife1 = jointLife1;
   }

   public java.lang.String getJointLife2()
   {
      return this.jointLife2;
   }

   public void setJointLife2(java.lang.String jointLife2)
   {
      this.jointLife2 = jointLife2;
   }

   public java.lang.String getPolicyOwner()
   {
      return this.policyOwner;
   }

   public void setPolicyOwner(java.lang.String policyOwner)
   {
      this.policyOwner = policyOwner;
   }

   public java.lang.String getInsurableInterest()
   {
      return this.insurableInterest;
   }

   public void setInsurableInterest(java.lang.String insurableInterest)
   {
      this.insurableInterest = insurableInterest;
   }

   public java.lang.String getCoverType()
   {
      return this.coverType;
   }

   public void setCoverType(java.lang.String coverType)
   {
      this.coverType = coverType;
   }

   public java.lang.String getRiskProfSignDateCheck()
   {
      return this.riskProfSignDateCheck;
   }

   public void setRiskProfSignDateCheck(java.lang.String riskProfSignDateCheck)
   {
      this.riskProfSignDateCheck = riskProfSignDateCheck;
   }

   public java.lang.String getRiskProfResult()
   {
      return this.riskProfResult;
   }

   public void setRiskProfResult(java.lang.String riskProfResult)
   {
      this.riskProfResult = riskProfResult;
   }

   public java.lang.String getRiskProfSuitable()
   {
      return this.riskProfSuitable;
   }

   public void setRiskProfSuitable(java.lang.String riskProfSuitable)
   {
      this.riskProfSuitable = riskProfSuitable;
   }

   public java.lang.String getDocSupportComplete()
   {
      return this.docSupportComplete;
   }

   public void setDocSupportComplete(java.lang.String docSupportComplete)
   {
      this.docSupportComplete = docSupportComplete;
   }

   public java.lang.String getSignatureFraud()
   {
      return this.signatureFraud;
   }

   public void setSignatureFraud(java.lang.String signatureFraud)
   {
      this.signatureFraud = signatureFraud;
   }

   public java.lang.String getRandomCheckMedical()
   {
      return this.randomCheckMedical;
   }

   public void setRandomCheckMedical(java.lang.String randomCheckMedical)
   {
      this.randomCheckMedical = randomCheckMedical;
   }

   public java.lang.String getSalesBranchCode()
   {
      return this.salesBranchCode;
   }

   public void setSalesBranchCode(java.lang.String salesBranchCode)
   {
      this.salesBranchCode = salesBranchCode;
   }

   public java.util.List<java.lang.String> getOccupation()
   {
      return this.occupation;
   }

   public void setOccupation(java.util.List<java.lang.String> occupation)
   {
      this.occupation = occupation;
   }

   public java.util.List<java.lang.String> getImpairment()
   {
      return this.impairment;
   }

   public void setImpairment(java.util.List<java.lang.String> impairment)
   {
      this.impairment = impairment;
   }

   public java.util.List<java.lang.String> getAvocation()
   {
      return this.avocation;
   }

   public void setAvocation(java.util.List<java.lang.String> avocation)
   {
      this.avocation = avocation;
   }

   public java.util.List<prupoc.newbusiness.SumAssured> getSumAssured()
   {
      return this.sumAssured;
   }

   public void setSumAssured(
         java.util.List<prupoc.newbusiness.SumAssured> sumAssured)
   {
      this.sumAssured = sumAssured;
   }

   public java.lang.String getKYCFlag()
   {
      return this.KYCFlag;
   }

   public void setKYCFlag(java.lang.String KYCFlag)
   {
      this.KYCFlag = KYCFlag;
   }

   public java.lang.String getSPAJnumber()
   {
      return this.SPAJnumber;
   }

   public void setSPAJnumber(java.lang.String SPAJnumber)
   {
      this.SPAJnumber = SPAJnumber;
   }

   public java.lang.String getSPAJComplete()
   {
      return this.SPAJComplete;
   }

   public void setSPAJComplete(java.lang.String SPAJComplete)
   {
      this.SPAJComplete = SPAJComplete;
   }

   public java.lang.String getSQSComplete()
   {
      return this.SQSComplete;
   }

   public void setSQSComplete(java.lang.String SQSComplete)
   {
      this.SQSComplete = SQSComplete;
   }

   public java.lang.String getIDProcessable()
   {
      return this.IDProcessable;
   }

   public void setIDProcessable(java.lang.String IDProcessable)
   {
      this.IDProcessable = IDProcessable;
   }

   public java.lang.String getIDSubmitted()
   {
      return this.IDSubmitted;
   }

   public void setIDSubmitted(java.lang.String IDSubmitted)
   {
      this.IDSubmitted = IDSubmitted;
   }

   public java.lang.String getForenames()
   {
      return this.forenames;
   }

   public void setForenames(java.lang.String forenames)
   {
      this.forenames = forenames;
   }

   public java.lang.String getSurname()
   {
      return this.surname;
   }

   public void setSurname(java.lang.String surname)
   {
      this.surname = surname;
   }

   public java.lang.String getDateOfBirth()
   {
      return this.dateOfBirth;
   }

   public void setDateOfBirth(java.lang.String dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   public java.lang.String getRuleResultBMI()
   {
      return this.ruleResultBMI;
   }

   public void setRuleResultBMI(java.lang.String ruleResultBMI)
   {
      this.ruleResultBMI = ruleResultBMI;
   }

   public java.lang.String getRuleResultInsurableInterest()
   {
      return this.ruleResultInsurableInterest;
   }

   public void setRuleResultInsurableInterest(
         java.lang.String ruleResultInsurableInterest)
   {
      this.ruleResultInsurableInterest = ruleResultInsurableInterest;
   }

   public prupoc.newbusiness.RuleResultOccupationBenefit getRuleResultOccupationBenefit()
   {
      return this.ruleResultOccupationBenefit;
   }

   public void setRuleResultOccupationBenefit(
         prupoc.newbusiness.RuleResultOccupationBenefit ruleResultOccupationBenefit)
   {
      this.ruleResultOccupationBenefit = ruleResultOccupationBenefit;
   }

   public java.lang.String getRuleResultNonMedicalLimit()
   {
      return this.ruleResultNonMedicalLimit;
   }

   public void setRuleResultNonMedicalLimit(
         java.lang.String ruleResultNonMedicalLimit)
   {
      this.ruleResultNonMedicalLimit = ruleResultNonMedicalLimit;
   }

   public java.lang.String getRuleResultBenefitLife()
   {
      return this.ruleResultBenefitLife;
   }

   public void setRuleResultBenefitLife(java.lang.String ruleResultBenefitLife)
   {
      this.ruleResultBenefitLife = ruleResultBenefitLife;
   }

   public java.lang.String getRuleResultBenefitADD()
   {
      return this.ruleResultBenefitADD;
   }

   public void setRuleResultBenefitADD(java.lang.String ruleResultBenefitADD)
   {
      this.ruleResultBenefitADD = ruleResultBenefitADD;
   }

   public java.lang.String getRuleResultBenefitTPD()
   {
      return this.ruleResultBenefitTPD;
   }

   public void setRuleResultBenefitTPD(java.lang.String ruleResultBenefitTPD)
   {
      this.ruleResultBenefitTPD = ruleResultBenefitTPD;
   }

   public java.lang.String getRuleResultBenefitCC()
   {
      return this.ruleResultBenefitCC;
   }

   public void setRuleResultBenefitCC(java.lang.String ruleResultBenefitCC)
   {
      this.ruleResultBenefitCC = ruleResultBenefitCC;
   }

   public java.lang.String getRuleResultBenefitHealth()
   {
      return this.ruleResultBenefitHealth;
   }

   public void setRuleResultBenefitHealth(java.lang.String ruleResultBenefitHealth)
   {
      this.ruleResultBenefitHealth = ruleResultBenefitHealth;
   }

   public java.lang.Integer getAgeInYear()
   {
      return this.ageInYear;
   }

   public void setAgeInYear(java.lang.Integer ageInYear)
   {
      this.ageInYear = ageInYear;
   }

   public java.lang.Integer getAgeInMonth()
   {
      return this.ageInMonth;
   }

   public void setAgeInMonth(java.lang.Integer ageInMonth)
   {
      this.ageInMonth = ageInMonth;
   }

   public CoverageData(
         java.lang.String documentType,
         java.lang.String gender,
         java.lang.String maritalStatus,
         java.lang.String height,
         java.lang.String weight,
         java.lang.String alcoholConsumption,
         java.lang.String addictiveDrugUse,
         java.lang.String medicalDrugUse,
         java.lang.String smokerStatus,
         java.lang.String country,
         java.lang.String countryOfNationality,
         java.lang.String onSocialVisitPass,
         java.lang.String socialVisaType,
         java.lang.String incomePerYear,
         java.lang.String policyNumber,
         java.lang.String proposalReceiveDate,
         java.lang.String processingDate,
         java.lang.String distributionChannel,
         java.lang.String policyCode,
         java.lang.String productCode,
         java.lang.String proposedLife,
         java.lang.String jointLife1,
         java.lang.String jointLife2,
         java.lang.String policyOwner,
         java.lang.String insurableInterest,
         java.lang.String coverType,
         java.lang.String riskProfSignDateCheck,
         java.lang.String riskProfResult,
         java.lang.String riskProfSuitable,
         java.lang.String docSupportComplete,
         java.lang.String signatureFraud,
         java.lang.String randomCheckMedical,
         java.lang.String salesBranchCode,
         java.util.List<java.lang.String> occupation,
         java.util.List<java.lang.String> impairment,
         java.util.List<java.lang.String> avocation,
         java.util.List<prupoc.newbusiness.SumAssured> sumAssured,
         java.lang.String KYCFlag,
         java.lang.String SPAJnumber,
         java.lang.String SPAJComplete,
         java.lang.String SQSComplete,
         java.lang.String IDProcessable,
         java.lang.String IDSubmitted,
         java.lang.String forenames,
         java.lang.String surname,
         java.lang.String dateOfBirth,
         java.lang.String ruleResultBMI,
         java.lang.String ruleResultInsurableInterest,
         prupoc.newbusiness.RuleResultOccupationBenefit ruleResultOccupationBenefit,
         java.lang.String ruleResultNonMedicalLimit,
         java.lang.String ruleResultBenefitLife,
         java.lang.String ruleResultBenefitADD,
         java.lang.String ruleResultBenefitTPD,
         java.lang.String ruleResultBenefitCC,
         java.lang.String ruleResultBenefitHealth, java.lang.Integer ageInYear,
         java.lang.Integer ageInMonth)
   {
      this.documentType = documentType;
      this.gender = gender;
      this.maritalStatus = maritalStatus;
      this.height = height;
      this.weight = weight;
      this.alcoholConsumption = alcoholConsumption;
      this.addictiveDrugUse = addictiveDrugUse;
      this.medicalDrugUse = medicalDrugUse;
      this.smokerStatus = smokerStatus;
      this.country = country;
      this.countryOfNationality = countryOfNationality;
      this.onSocialVisitPass = onSocialVisitPass;
      this.socialVisaType = socialVisaType;
      this.incomePerYear = incomePerYear;
      this.policyNumber = policyNumber;
      this.proposalReceiveDate = proposalReceiveDate;
      this.processingDate = processingDate;
      this.distributionChannel = distributionChannel;
      this.policyCode = policyCode;
      this.productCode = productCode;
      this.proposedLife = proposedLife;
      this.jointLife1 = jointLife1;
      this.jointLife2 = jointLife2;
      this.policyOwner = policyOwner;
      this.insurableInterest = insurableInterest;
      this.coverType = coverType;
      this.riskProfSignDateCheck = riskProfSignDateCheck;
      this.riskProfResult = riskProfResult;
      this.riskProfSuitable = riskProfSuitable;
      this.docSupportComplete = docSupportComplete;
      this.signatureFraud = signatureFraud;
      this.randomCheckMedical = randomCheckMedical;
      this.salesBranchCode = salesBranchCode;
      this.occupation = occupation;
      this.impairment = impairment;
      this.avocation = avocation;
      this.sumAssured = sumAssured;
      this.KYCFlag = KYCFlag;
      this.SPAJnumber = SPAJnumber;
      this.SPAJComplete = SPAJComplete;
      this.SQSComplete = SQSComplete;
      this.IDProcessable = IDProcessable;
      this.IDSubmitted = IDSubmitted;
      this.forenames = forenames;
      this.surname = surname;
      this.dateOfBirth = dateOfBirth;
      this.ruleResultBMI = ruleResultBMI;
      this.ruleResultInsurableInterest = ruleResultInsurableInterest;
      this.ruleResultOccupationBenefit = ruleResultOccupationBenefit;
      this.ruleResultNonMedicalLimit = ruleResultNonMedicalLimit;
      this.ruleResultBenefitLife = ruleResultBenefitLife;
      this.ruleResultBenefitADD = ruleResultBenefitADD;
      this.ruleResultBenefitTPD = ruleResultBenefitTPD;
      this.ruleResultBenefitCC = ruleResultBenefitCC;
      this.ruleResultBenefitHealth = ruleResultBenefitHealth;
      this.ageInYear = ageInYear;
      this.ageInMonth = ageInMonth;
   }

   
   
}