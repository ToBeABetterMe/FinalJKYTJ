package com.c102c.app.utils;

/** 住院史 */
public class Hospitalized {
	/** 入院日期 */
	private String admissionDate = "";
	/** 出院日期 */
	private String dischargeDate = "";
	/** 原因 */
	private String reason = "";
	/** 机构名称 */
	private String orgName = "";
	/** 病案号 */
	private String medicalRecordNumber = "";

	/** 入院日期 */
	public String getAdmissionDate() {
		return admissionDate;
	}

	/** 入院日期 */
	public void setAdmissionDate(String admissionDate) {
		if (admissionDate == null) {
			return;
		}
		this.admissionDate = admissionDate;
	}

	/** 出院日期 */
	public String getDischargeDate() {
		return dischargeDate;
	}

	/** 出院日期 */
	public void setDischargeDate(String dischargeDate) {
		if (dischargeDate == null) {
			return;
		}
		this.dischargeDate = dischargeDate;
	}

	/** 原因 */
	public String getReason() {

		return reason;
	}

	/** 原因 */
	public void setReason(String reason) {
		if (reason == null) {
			return;
		}
		this.reason = reason;
	}

	/** 机构名称 */
	public String getOrgName() {
		return orgName;
	}

	/** 机构名称 */
	public void setOrgName(String orgName) {
		if (orgName == null) {
			return;
		}
		this.orgName = orgName;
	}

	/** 病案号 */
	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	/** 病案号 */
	public void setMedicalRecordNumber(String medicalRecordNumber) {
		if (medicalRecordNumber == null) {
			return;
		}
		this.medicalRecordNumber = medicalRecordNumber;
	}

	@Override
	public String toString() {
		return "Hospitalized [admissionDate=" + admissionDate
				+ ", dischargeDate=" + dischargeDate + ", reason=" + reason
				+ ", orgName=" + orgName + ", medicalRecordNumber="
				+ medicalRecordNumber + "]";
	}

}
