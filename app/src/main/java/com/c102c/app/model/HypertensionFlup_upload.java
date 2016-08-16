package com.c102c.app.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "HYPERTENSION_FLUP_UPLOAD".
 */
public class HypertensionFlup_upload {

	private Long id;
	private String personId;
	private String specialNo;
	private String machineCode;
	private String machineNo;
	private String name;
	private String flupDate;
	private String flupMode;
	private String symptomCodes;
	private String symptomOther;
	private String SBP;
	private String DBP;
	private String weight;
	private String weightTarget;
	private String bmi;
	private String bmiTarget;
	private String heartRate;
	private String signsOther;
	private String dailySmoking;
	private String dailySmokingTarget;
	private String dailyDrinking;
	private String dailyDrinkingTarget;
	private String perWeekMovements;
	private String perWeekMovementsTarget;
	private String perWeekTimes;
	private String perWeekTimesTarget;
	private String saltIntakeCode;
	private String saltIntakeTargetCode;
	private String psychologicalCode;
	private String complianceCode;
	private String aidCheck;
	private String doseCode;
	private String adverseReactionCode;
	private String flupTypeCode;
	private String medicationList;
	private String referralReason;
	private String referralOrg;
	private String referralDepartment;
	private String flupDoctorCode;
	private String flupDoctorName;
	private String flupOrgCode;
	private String flupOrgName;
	private String nextFlupDate;

	// KEEP FIELDS - put your custom fields here

	public HypertensionFlup_upload() {
		id = System.currentTimeMillis();
		personId = "";
		specialNo = "";
		machineCode = "";
		machineNo = "";
		name = "";
		flupDate = "";
		flupMode = "";
		symptomCodes = "";
		symptomOther = "";
		SBP = "";
		DBP = "";
		weight = "";
		weightTarget = "";
		bmi = "";
		bmiTarget = "";
		heartRate = "";
		signsOther = "";
		dailySmoking = "";
		dailySmokingTarget = "";
		dailyDrinking = "";
		dailyDrinkingTarget = "";
		perWeekMovements = "";
		perWeekMovementsTarget = "";
		perWeekTimes = "";
		perWeekTimesTarget = "";
		saltIntakeCode = "";
		saltIntakeTargetCode = "";
		psychologicalCode = "";
		complianceCode = "";
		aidCheck = "";
		doseCode = "";
		adverseReactionCode = "";
		flupTypeCode = "";
		medicationList = "";
		referralReason = "";
		referralOrg = "";
		referralDepartment = "";
		flupDoctorCode = "";
		flupDoctorName = "";
		flupOrgCode = "";
		flupOrgName = "";
		nextFlupDate = "";

	}

	// KEEP FIELDS END

	public HypertensionFlup_upload(Long id) {
		this.id = id;
	}

	public HypertensionFlup_upload(Long id, String personId, String specialNo,
			String machineCode, String machineNo, String name, String flupDate,
			String flupMode, String symptomCodes, String symptomOther,
			String SBP, String DBP, String weight, String weightTarget,
			String bmi, String bmiTarget, String heartRate, String signsOther,
			String dailySmoking, String dailySmokingTarget,
			String dailyDrinking, String dailyDrinkingTarget,
			String perWeekMovements, String perWeekMovementsTarget,
			String perWeekTimes, String perWeekTimesTarget,
			String saltIntakeCode, String saltIntakeTargetCode,
			String psychologicalCode, String complianceCode, String aidCheck,
			String doseCode, String adverseReactionCode, String flupTypeCode,
			String medicationList, String referralReason, String referralOrg,
			String referralDepartment, String flupDoctorCode,
			String flupDoctorName, String flupOrgCode, String flupOrgName,
			String nextFlupDate) {
		this.id = id;
		this.personId = personId;
		this.specialNo = specialNo;
		this.machineCode = machineCode;
		this.machineNo = machineNo;
		this.name = name;
		this.flupDate = flupDate;
		this.flupMode = flupMode;
		this.symptomCodes = symptomCodes;
		this.symptomOther = symptomOther;
		this.SBP = SBP;
		this.DBP = DBP;
		this.weight = weight;
		this.weightTarget = weightTarget;
		this.bmi = bmi;
		this.bmiTarget = bmiTarget;
		this.heartRate = heartRate;
		this.signsOther = signsOther;
		this.dailySmoking = dailySmoking;
		this.dailySmokingTarget = dailySmokingTarget;
		this.dailyDrinking = dailyDrinking;
		this.dailyDrinkingTarget = dailyDrinkingTarget;
		this.perWeekMovements = perWeekMovements;
		this.perWeekMovementsTarget = perWeekMovementsTarget;
		this.perWeekTimes = perWeekTimes;
		this.perWeekTimesTarget = perWeekTimesTarget;
		this.saltIntakeCode = saltIntakeCode;
		this.saltIntakeTargetCode = saltIntakeTargetCode;
		this.psychologicalCode = psychologicalCode;
		this.complianceCode = complianceCode;
		this.aidCheck = aidCheck;
		this.doseCode = doseCode;
		this.adverseReactionCode = adverseReactionCode;
		this.flupTypeCode = flupTypeCode;
		this.medicationList = medicationList;
		this.referralReason = referralReason;
		this.referralOrg = referralOrg;
		this.referralDepartment = referralDepartment;
		this.flupDoctorCode = flupDoctorCode;
		this.flupDoctorName = flupDoctorName;
		this.flupOrgCode = flupOrgCode;
		this.flupOrgName = flupOrgName;
		this.nextFlupDate = nextFlupDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getSpecialNo() {
		return specialNo;
	}

	public void setSpecialNo(String specialNo) {
		this.specialNo = specialNo;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlupDate() {
		return flupDate;
	}

	public void setFlupDate(String flupDate) {
		this.flupDate = flupDate;
	}

	public String getFlupMode() {
		return flupMode;
	}

	public void setFlupMode(String flupMode) {
		this.flupMode = flupMode;
	}

	public String getSymptomCodes() {
		return symptomCodes;
	}

	public void setSymptomCodes(String symptomCodes) {
		this.symptomCodes = symptomCodes;
	}

	public String getSymptomOther() {
		return symptomOther;
	}

	public void setSymptomOther(String symptomOther) {
		this.symptomOther = symptomOther;
	}

	public String getSBP() {
		return SBP;
	}

	public void setSBP(String SBP) {
		this.SBP = SBP;
	}

	public String getDBP() {
		return DBP;
	}

	public void setDBP(String DBP) {
		this.DBP = DBP;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeightTarget() {
		return weightTarget;
	}

	public void setWeightTarget(String weightTarget) {
		this.weightTarget = weightTarget;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getBmiTarget() {
		return bmiTarget;
	}

	public void setBmiTarget(String bmiTarget) {
		this.bmiTarget = bmiTarget;
	}

	public String getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	public String getSignsOther() {
		return signsOther;
	}

	public void setSignsOther(String signsOther) {
		this.signsOther = signsOther;
	}

	public String getDailySmoking() {
		return dailySmoking;
	}

	public void setDailySmoking(String dailySmoking) {
		this.dailySmoking = dailySmoking;
	}

	public String getDailySmokingTarget() {
		return dailySmokingTarget;
	}

	public void setDailySmokingTarget(String dailySmokingTarget) {
		this.dailySmokingTarget = dailySmokingTarget;
	}

	public String getDailyDrinking() {
		return dailyDrinking;
	}

	public void setDailyDrinking(String dailyDrinking) {
		this.dailyDrinking = dailyDrinking;
	}

	public String getDailyDrinkingTarget() {
		return dailyDrinkingTarget;
	}

	public void setDailyDrinkingTarget(String dailyDrinkingTarget) {
		this.dailyDrinkingTarget = dailyDrinkingTarget;
	}

	public String getPerWeekMovements() {
		return perWeekMovements;
	}

	public void setPerWeekMovements(String perWeekMovements) {
		this.perWeekMovements = perWeekMovements;
	}

	public String getPerWeekMovementsTarget() {
		return perWeekMovementsTarget;
	}

	public void setPerWeekMovementsTarget(String perWeekMovementsTarget) {
		this.perWeekMovementsTarget = perWeekMovementsTarget;
	}

	public String getPerWeekTimes() {
		return perWeekTimes;
	}

	public void setPerWeekTimes(String perWeekTimes) {
		this.perWeekTimes = perWeekTimes;
	}

	public String getPerWeekTimesTarget() {
		return perWeekTimesTarget;
	}

	public void setPerWeekTimesTarget(String perWeekTimesTarget) {
		this.perWeekTimesTarget = perWeekTimesTarget;
	}

	public String getSaltIntakeCode() {
		return saltIntakeCode;
	}

	public void setSaltIntakeCode(String saltIntakeCode) {
		this.saltIntakeCode = saltIntakeCode;
	}

	public String getSaltIntakeTargetCode() {
		return saltIntakeTargetCode;
	}

	public void setSaltIntakeTargetCode(String saltIntakeTargetCode) {
		this.saltIntakeTargetCode = saltIntakeTargetCode;
	}

	public String getPsychologicalCode() {
		return psychologicalCode;
	}

	public void setPsychologicalCode(String psychologicalCode) {
		this.psychologicalCode = psychologicalCode;
	}

	public String getComplianceCode() {
		return complianceCode;
	}

	public void setComplianceCode(String complianceCode) {
		this.complianceCode = complianceCode;
	}

	public String getAidCheck() {
		return aidCheck;
	}

	public void setAidCheck(String aidCheck) {
		this.aidCheck = aidCheck;
	}

	public String getDoseCode() {
		return doseCode;
	}

	public void setDoseCode(String doseCode) {
		this.doseCode = doseCode;
	}

	public String getAdverseReactionCode() {
		return adverseReactionCode;
	}

	public void setAdverseReactionCode(String adverseReactionCode) {
		this.adverseReactionCode = adverseReactionCode;
	}

	public String getFlupTypeCode() {
		return flupTypeCode;
	}

	public void setFlupTypeCode(String flupTypeCode) {
		this.flupTypeCode = flupTypeCode;
	}

	public String getMedicationList() {
		return medicationList;
	}

	public void setMedicationList(String medicationList) {
		this.medicationList = medicationList;
	}

	public String getReferralReason() {
		return referralReason;
	}

	public void setReferralReason(String referralReason) {
		this.referralReason = referralReason;
	}

	public String getReferralOrg() {
		return referralOrg;
	}

	public void setReferralOrg(String referralOrg) {
		this.referralOrg = referralOrg;
	}

	public String getReferralDepartment() {
		return referralDepartment;
	}

	public void setReferralDepartment(String referralDepartment) {
		this.referralDepartment = referralDepartment;
	}

	public String getFlupDoctorCode() {
		return flupDoctorCode;
	}

	public void setFlupDoctorCode(String flupDoctorCode) {
		this.flupDoctorCode = flupDoctorCode;
	}

	public String getFlupDoctorName() {
		return flupDoctorName;
	}

	public void setFlupDoctorName(String flupDoctorName) {
		this.flupDoctorName = flupDoctorName;
	}

	public String getFlupOrgCode() {
		return flupOrgCode;
	}

	public void setFlupOrgCode(String flupOrgCode) {
		this.flupOrgCode = flupOrgCode;
	}

	public String getFlupOrgName() {
		return flupOrgName;
	}

	public void setFlupOrgName(String flupOrgName) {
		this.flupOrgName = flupOrgName;
	}

	public String getNextFlupDate() {
		return nextFlupDate;
	}

	public void setNextFlupDate(String nextFlupDate) {
		this.nextFlupDate = nextFlupDate;
	}

	// KEEP METHODS - put your custom methods here
	// KEEP METHODS END

}