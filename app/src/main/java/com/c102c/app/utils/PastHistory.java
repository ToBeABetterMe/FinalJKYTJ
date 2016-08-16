package com.c102c.app.utils;

import android.R.string;

/** º»Õ˘ ∑¡–±Ì */
public class PastHistory {
	private String pastHistoryType = "";
	private String pastHistoryCode = "";
	private String pastHistoryAdd = "";
	private String pastHistoryDate = "";

	public String getPastHistoryType() {
		return pastHistoryType;
	}

	public PastHistory() {
		// TODO Auto-generated constructor stub
	}

	public PastHistory(String[] str) {
		// TODO Auto-generated constructor stub
		pastHistoryType = str[0];
		pastHistoryCode = str[1];
		pastHistoryAdd = str[2];
		pastHistoryAdd = str[3];
	}

	public void setPastHistoryType(String pastHistoryType) {
		if (pastHistoryType == null) {
			return;
		}
		this.pastHistoryType = pastHistoryType;
	}

	public String getPastHistoryCode() {
		return pastHistoryCode;
	}

	public void setPastHistoryCode(String pastHistoryCode) {
		if (pastHistoryCode == null) {
			return;
		}
		this.pastHistoryCode = pastHistoryCode;
	}

	public String getPastHistoryAdd() {
		return pastHistoryAdd;
	}

	public void setPastHistoryAdd(String pastHistoryAdd) {
		if (pastHistoryAdd == null) {
			return;
		}
		this.pastHistoryAdd = pastHistoryAdd;
	}

	public String getPastHistoryDate() {
		return pastHistoryDate;
	}

	public void setPastHistoryDate(String pastHistoryDate) {
		if (pastHistoryDate == null) {
			return;
		}
		this.pastHistoryDate = pastHistoryDate;
	}

	@Override
	public String toString() {
		return "PastHistory [pastHistoryType=" + pastHistoryType
				+ ", pastHistoryCode=" + pastHistoryCode + ", pastHistoryAdd="
				+ pastHistoryAdd + ", pastHistoryDate=" + pastHistoryDate + "]";
	}
}
