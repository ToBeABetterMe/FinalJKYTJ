package com.c102c.app.utils;

/**<vaccinationHistory>
 <vaccination>
 <inoculationName></inoculationName>
 <inoculationDate></inoculationDate>
 <orgName></orgName>
 </vaccination>
 <vaccination>
 <inoculationName></inoculationName>
 <inoculationDate></inoculationDate>
 <orgName></orgName>
 </vaccination>
 </vaccinationHistory>*/
/**
 * 非 免 疫 规 划 预 防 接
 */
public class Vaccination {
	/** 接种名称 */
	private String inoculationName = "";
	/** 接种日期 */
	private String inoculationDate = "";
	/** 接种机构名称 */
	private String orgName = "";

	public String getInoculationName() {
		return inoculationName;
	}

	public void setInoculationName(String inoculationName) {
		if (inoculationName == null) {
			return;
		}
		this.inoculationName = inoculationName;
	}

	public String getInoculationDate() {
		return inoculationDate;
	}

	public void setInoculationDate(String inoculationDate) {
		if (inoculationDate == null) {
			return;
		}
		this.inoculationDate = inoculationDate;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {

		if (orgName == null) {
			return;
		}
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "Vaccination [inoculationName=" + inoculationName
				+ ", inoculationDate=" + inoculationDate + ", orgName="
				+ orgName + "]";
	}

}
