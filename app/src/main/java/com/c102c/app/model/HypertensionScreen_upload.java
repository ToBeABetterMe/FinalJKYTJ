package com.c102c.app.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "HYPERTENSION_SCREEN_UPLOAD".
 */
public class HypertensionScreen_upload {

	private Long id;
	private String personId;
	private String machineCode;
	private String machineNo;
	private String name;
	private String screenDate;
	private String screenType;
	private String SBP;
	private String DBP;
	private String bloodPressureLevel;
	private String highValueCode;
	private String highRiskCode;
	private String screenDoctorCode;
	private String screenDoctorName;
	private String screenOrgCode;
	private String screenOrgName;
	private String nextScreenDate;
	private String screenGuideCode;

	// KEEP FIELDS - put your custom fields here

	public HypertensionScreen_upload() {
		id = System.currentTimeMillis();
		personId = "";
		machineCode = "";
		machineNo = "";
		name = "";
		screenDate = "";
		screenType = "";
		SBP = "";
		DBP = "";
		bloodPressureLevel = "";
		highValueCode = "";
		highRiskCode = "";
		screenDoctorCode = "";
		screenDoctorName = "";
		screenOrgCode = "";
		screenOrgName = "";
		nextScreenDate = "";
		screenGuideCode = "";

	}

	// KEEP FIELDS END

	public HypertensionScreen_upload(Long id) {
		this.id = id;
	}

	public HypertensionScreen_upload(Long id, String personId,
			String machineCode, String machineNo, String name,
			String screenDate, String screenType, String SBP, String DBP,
			String bloodPressureLevel, String highValueCode,
			String highRiskCode, String screenDoctorCode,
			String screenDoctorName, String screenOrgCode,
			String screenOrgName, String nextScreenDate, String screenGuideCode) {
		this.id = id;
		this.personId = personId;
		this.machineCode = machineCode;
		this.machineNo = machineNo;
		this.name = name;
		this.screenDate = screenDate;
		this.screenType = screenType;
		this.SBP = SBP;
		this.DBP = DBP;
		this.bloodPressureLevel = bloodPressureLevel;
		this.highValueCode = highValueCode;
		this.highRiskCode = highRiskCode;
		this.screenDoctorCode = screenDoctorCode;
		this.screenDoctorName = screenDoctorName;
		this.screenOrgCode = screenOrgCode;
		this.screenOrgName = screenOrgName;
		this.nextScreenDate = nextScreenDate;
		this.screenGuideCode = screenGuideCode;
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

	public String getScreenDate() {
		return screenDate;
	}

	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
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

	public String getBloodPressureLevel() {
		return bloodPressureLevel;
	}

	public void setBloodPressureLevel(String bloodPressureLevel) {
		this.bloodPressureLevel = bloodPressureLevel;
	}

	public String getHighValueCode() {
		return highValueCode;
	}

	public void setHighValueCode(String highValueCode) {
		this.highValueCode = highValueCode;
	}

	public String getHighRiskCode() {
		return highRiskCode;
	}

	public void setHighRiskCode(String highRiskCode) {
		this.highRiskCode = highRiskCode;
	}

	public String getScreenDoctorCode() {
		return screenDoctorCode;
	}

	public void setScreenDoctorCode(String screenDoctorCode) {
		this.screenDoctorCode = screenDoctorCode;
	}

	public String getScreenDoctorName() {
		return screenDoctorName;
	}

	public void setScreenDoctorName(String screenDoctorName) {
		this.screenDoctorName = screenDoctorName;
	}

	public String getScreenOrgCode() {
		return screenOrgCode;
	}

	public void setScreenOrgCode(String screenOrgCode) {
		this.screenOrgCode = screenOrgCode;
	}

	public String getScreenOrgName() {
		return screenOrgName;
	}

	public void setScreenOrgName(String screenOrgName) {
		this.screenOrgName = screenOrgName;
	}

	public String getNextScreenDate() {
		return nextScreenDate;
	}

	public void setNextScreenDate(String nextScreenDate) {
		this.nextScreenDate = nextScreenDate;
	}

	public String getScreenGuideCode() {
		return screenGuideCode;
	}

	public void setScreenGuideCode(String screenGuideCode) {
		this.screenGuideCode = screenGuideCode;
	}

	// KEEP METHODS - put your custom methods here
	// KEEP METHODS END

}