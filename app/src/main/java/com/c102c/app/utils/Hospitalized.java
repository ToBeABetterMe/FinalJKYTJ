package com.c102c.app.utils;

/** סԺʷ */
public class Hospitalized {
	/** ��Ժ���� */
	private String admissionDate = "";
	/** ��Ժ���� */
	private String dischargeDate = "";
	/** ԭ�� */
	private String reason = "";
	/** �������� */
	private String orgName = "";
	/** ������ */
	private String medicalRecordNumber = "";

	/** ��Ժ���� */
	public String getAdmissionDate() {
		return admissionDate;
	}

	/** ��Ժ���� */
	public void setAdmissionDate(String admissionDate) {
		if (admissionDate == null) {
			return;
		}
		this.admissionDate = admissionDate;
	}

	/** ��Ժ���� */
	public String getDischargeDate() {
		return dischargeDate;
	}

	/** ��Ժ���� */
	public void setDischargeDate(String dischargeDate) {
		if (dischargeDate == null) {
			return;
		}
		this.dischargeDate = dischargeDate;
	}

	/** ԭ�� */
	public String getReason() {

		return reason;
	}

	/** ԭ�� */
	public void setReason(String reason) {
		if (reason == null) {
			return;
		}
		this.reason = reason;
	}

	/** �������� */
	public String getOrgName() {
		return orgName;
	}

	/** �������� */
	public void setOrgName(String orgName) {
		if (orgName == null) {
			return;
		}
		this.orgName = orgName;
	}

	/** ������ */
	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	/** ������ */
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
