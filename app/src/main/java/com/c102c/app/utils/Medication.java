package com.c102c.app.utils;

/** ��ҩ��� */
public class Medication {
	/** ҩƷ���� */
	private String drugName = "";
	/** ҩƷ���� */
	private String drugCode = "";
	/** �÷� */
	private String drugUsage = "";
	/** �÷� */
	private String usage = "";
	/** ҩƷ�÷����� */
	private String drugUsageAdd = "";
	/** ҩƷ���� */
	private String drugDosage = "";
	/** �������� */
	private String drugDosageAdd = "";
	/** ���� */
	private String dosage = "";

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		if (drugCode == null) {
			return;
		}
		this.drugCode = drugCode;
	}

	public String getDrugUsage() {
		return drugUsage;
	}

	public void setDrugUsage(String drugUsage) {
		if (drugUsage == null) {
			return;
		}
		this.drugUsage = drugUsage;
	}

	public String getDrugUsageAdd() {
		return drugUsageAdd;
	}

	public void setDrugUsageAdd(String drugUsageAdd) {
		if (drugUsageAdd == null) {
			return;
		}
		this.drugUsageAdd = drugUsageAdd;
	}

	public String getDrugDosage() {
		return drugDosage;
	}

	public void setDrugDosage(String drugDosage) {
		if (drugDosage == null) {
			return;
		}
		this.drugDosage = drugDosage;
	}

	public String getDrugDosageAdd() {
		return drugDosageAdd;
	}

	public void setDrugDosageAdd(String drugDosageAdd) {
		if (drugDosageAdd == null) {
			return;
		}
		this.drugDosageAdd = drugDosageAdd;
	}

	public String getMedicationComplianceCode() {
		return medicationComplianceCode;
	}

	public void setMedicationComplianceCode(String medicationComplianceCode) {
		if (medicationComplianceCode == null) {
			return;
		}
		this.medicationComplianceCode = medicationComplianceCode;
	}

	/** ��ҩʱ�� */
	private String medicationTime = "";
	/** ��ҩ���� */
	private String doseCode = "";
	/** ��һ����ҩ���ӣ��ֶβ�һ������˼����һ��������ͬһ��xml���֣� */
	private String medicationComplianceCode = "";

	/** ҩƷ���� */
	public String getDrugName() {
		return drugName;
	}

	/** ҩƷ���� */
	public void setDrugName(String drugName) {
		if (drugName == null) {
			return;
		}
		this.drugName = drugName;
	}

	/** �÷� */
	public String getUsage() {
		return usage;
	}

	/** �÷� */
	public void setUsage(String usage) {
		if (usage == null) {
			return;
		}
		this.usage = usage;
	}

	/** ���� */
	public String getDosage() {
		return dosage;
	}

	/** ���� */
	public void setDosage(String dosage) {
		if (dosage == null) {
			return;
		}
		this.dosage = dosage;
	}

	/** ��ҩʱ�� */
	public String getMedicationTime() {
		return medicationTime;
	}

	/** ��ҩʱ�� */
	public void setMedicationTime(String medicationTime) {
		if (medicationTime == null) {
			return;
		}
		this.medicationTime = medicationTime;
	}

	/** ��ҩ���� */
	public String getDoseCode() {
		return doseCode;
	}

	/** ��ҩ���� */
	public void setDoseCode(String doseCode) {
		if (doseCode == null) {
			return;
		}
		this.doseCode = doseCode;
	}

	@Override
	public String toString() {
		return "Medication [drugName=" + drugName + ", drugCode=" + drugCode
				+ ", drugUsage=" + drugUsage + ", usage=" + usage
				+ ", drugUsageAdd=" + drugUsageAdd + ", drugDosage="
				+ drugDosage + ", drugDosageAdd=" + drugDosageAdd + ", dosage="
				+ dosage + ", medicationTime=" + medicationTime + ", doseCode="
				+ doseCode + ", medicationComplianceCode="
				+ medicationComplianceCode + "]";
	}

}
