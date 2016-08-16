package com.c102c.app.utils;

/** 用药情况 */
public class Medication {
	/** 药品名称 */
	private String drugName = "";
	/** 药品代码 */
	private String drugCode = "";
	/** 用法 */
	private String drugUsage = "";
	/** 用法 */
	private String usage = "";
	/** 药品用法补充 */
	private String drugUsageAdd = "";
	/** 药品用量 */
	private String drugDosage = "";
	/** 用量补充 */
	private String drugDosageAdd = "";
	/** 用量 */
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

	/** 用药时间 */
	private String medicationTime = "";
	/** 用药依从 */
	private String doseCode = "";
	/** 另一个用药依从（字段不一样，意思可能一样，不在同一个xml出现） */
	private String medicationComplianceCode = "";

	/** 药品名称 */
	public String getDrugName() {
		return drugName;
	}

	/** 药品名称 */
	public void setDrugName(String drugName) {
		if (drugName == null) {
			return;
		}
		this.drugName = drugName;
	}

	/** 用法 */
	public String getUsage() {
		return usage;
	}

	/** 用法 */
	public void setUsage(String usage) {
		if (usage == null) {
			return;
		}
		this.usage = usage;
	}

	/** 用量 */
	public String getDosage() {
		return dosage;
	}

	/** 用量 */
	public void setDosage(String dosage) {
		if (dosage == null) {
			return;
		}
		this.dosage = dosage;
	}

	/** 用药时间 */
	public String getMedicationTime() {
		return medicationTime;
	}

	/** 用药时间 */
	public void setMedicationTime(String medicationTime) {
		if (medicationTime == null) {
			return;
		}
		this.medicationTime = medicationTime;
	}

	/** 用药依从 */
	public String getDoseCode() {
		return doseCode;
	}

	/** 用药依从 */
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
