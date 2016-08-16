package com.c102c.app.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import android.util.Xml;

public class Tools {
	final static String handler = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	/**
	 * <pastHistoryList> <pastHistory> <pastHistoryType>1</pastHistoryType>
	 * <pastHistoryCode>2</pastHistoryCode> <pastHistoryAdd></pastHistoryAdd>
	 * <pastHistoryDate>2009-04-09</pastHistoryDate> </pastHistory>
	 * <pastHistory> <pastHistoryType>1</pastHistoryType>
	 * <pastHistoryCode>6</pastHistoryCode> <pastHistoryAdd>肝癌</pastHistoryAdd>
	 * <pastHistoryDate>2011-07-03</pastHistoryDate> </pastHistory>
	 * <pastHistory> <pastHistoryType>2</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> <pastHistory>
	 * <pastHistoryType>3</pastHistoryType> <pastHistoryCode>1</pastHistoryCode>
	 * </pastHistory> <pastHistory> <pastHistoryType>4</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> </pastHistoryList>
	 */
	/** 既往史列表 */
	public static List<PastHistory> getPastHistory(String str) {
		str = handler + str;

		// //system.out.println(str);
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<PastHistory> past_list = new ArrayList<PastHistory>();
			PastHistory past = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("pastHistory")) {
						past = new PastHistory();
					} else if (past != null && !name.equals("pastHistoryList")) {
						f = past.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(past, parser.nextText());
					}
					// //system.out.println("name" + name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("pastHistory")) {
						past_list.add(past);
						past = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return past_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/** 住院史 */
	public static List<Hospitalized> getHospitalized(String str) {
		str = handler + str;

		// //system.out.println(str);
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<Hospitalized> past_list = new ArrayList<Hospitalized>();
			Hospitalized past = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("hospitalized")) {
						past = new Hospitalized();
					} else if (past != null
							&& !name.equals("hospitalizedHistory")) {
						f = past.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(past, parser.nextText());
					}
					// //system.out.println("name" + name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("hospitalized")) {
						past_list.add(past);
						past = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return past_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/** 家 庭 病 床史 */
	public static List<FamilyBed> getFamilyBed(String str) {
		str = handler + str;

		// //system.out.println(str);
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<FamilyBed> past_list = new ArrayList<FamilyBed>();
			FamilyBed past = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("familyBed")) {
						past = new FamilyBed();
					} else if (past != null && !name.equals("familyBedHistory")) {
						f = past.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(past, parser.nextText());
					}
					// //system.out.println("name" + name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("familyBed")) {
						past_list.add(past);
						past = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return past_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <medicationList> <medication> <drugCode>药品代码</drugCode>
	 * <drugName>药品名称</drugName> <drugUsage>药品用法</drugUsage>
	 * <drugUsageAdd>药品用法补充</drugUsageAdd> <drugDosage>药品用量</drugDosage>
	 * <drugDosageAdd>药品用量补充</drugDosageAdd> </medication> </medicationList>
	 */
	/** 用药情况 */
	public static List<Medication> getMedication(String str) {
		str = handler + str;

		// //system.out.println(str);
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<Medication> past_list = new ArrayList<Medication>();
			Medication past = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("medication")) {
						past = new Medication();
					} else if (past != null && !name.equals("medicationList")) {
						f = past.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(past, parser.nextText());
					}
					// //system.out.println("name" + name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("medication")) {
						past_list.add(past);
						past = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return past_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * <vaccinationHistory> <vaccination> <inoculationName></inoculationName>
	 * <inoculationDate></inoculationDate> <orgName></orgName> </vaccination>
	 * <vaccination> <inoculationName></inoculationName>
	 * <inoculationDate></inoculationDate> <orgName></orgName> </vaccination>
	 * </vaccinationHistory>
	 */
	/**
	 * 非 免 疫 规 划 预 防 接
	 */
	public static List<Vaccination> getVaccination(String str) {
		str = handler + str;

		// //system.out.println(str);
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<Vaccination> past_list = new ArrayList<Vaccination>();
			Vaccination past = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("vaccination")) {
						past = new Vaccination();
					} else if (past != null
							&& !name.equals("vaccinationHistory")) {
						f = past.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(past, parser.nextText());
					}
					// //system.out.println("name" + name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("vaccination")) {
						past_list.add(past);
						past = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return past_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <pastHistoryList> <pastHistory> <pastHistoryType>1</pastHistoryType>
	 * <pastHistoryCode>2</pastHistoryCode> <pastHistoryAdd></pastHistoryAdd>
	 * <pastHistoryDate>2009-04-09</pastHistoryDate> </pastHistory>
	 * <pastHistory> <pastHistoryType>1</pastHistoryType>
	 * <pastHistoryCode>6</pastHistoryCode> <pastHistoryAdd>肝癌</pastHistoryAdd>
	 * <pastHistoryDate>2011-07-03</pastHistoryDate> </pastHistory>
	 * <pastHistory> <pastHistoryType>2</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> <pastHistory>
	 * <pastHistoryType>3</pastHistoryType> <pastHistoryCode>1</pastHistoryCode>
	 * </pastHistory> <pastHistory> <pastHistoryType>4</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> </pastHistoryList>
	 */
	/** 既往史列表 */
	public static String setPastHistory(List<PastHistory> list) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
/*		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		PastHistory past = null;
		try {
			serializer.setOutput(read);
			serializer.startDocument("utf-8", null);
			// serializer.startTag(null,"pastHistoryList");
			for (int i = 0; i < list.size(); i++) {
				past = list.get(i);
				f = past.getClass().getDeclaredFields();
				serializer.startTag(null, "pastHistory");
				for (Field field : f) {
					name = field.getName();
					serializer.startTag(null, name);
					field.setAccessible(true);
					try {
						txt = (String) field.get(past);
						if (null != txt) {
							serializer.text(txt);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					serializer.endTag(null, name);
				}
				serializer.endTag(null, "pastHistory");
			}
			// serializer.endTag(null,"pastHistoryList");
			serializer.endDocument();
			serializer.flush();
			String result = read.toString();
			
			result = result.replace("<?xml version='1.0' encoding='UTF-8' ?>",
					"");
					
			Log.e("xml",result);
			return result;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		PastHistory past = null;
		
		for (int i = 0; i < list.size(); i++) {
			past = list.get(i);
			f = past.getClass().getDeclaredFields();
			str+="<pastHistory>";
			for (Field field : f) {
				name = field.getName();
				str=str+"<"+name+">" ;
				field.setAccessible(true);
				try {
					txt = (String) field.get(past);
					if (null != txt) {
						str+=txt;
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				str=str+"</"+ name+">";
			}
			str+="</pastHistory>";
		}
//		Log.e("xml",str);
		return str;
	}

	/**
	 * <medicationList> <medication> <drugName>药品名称</drugName> <usage>用法</usage>
	 * <dosage>用量</dosage> <medicationTime>用药时间</medicationTime>
	 * <medicationComplianceCode> 用 药 依 从 </medicationComplianceCode>
	 * </medication> <medication> <drugName>药品名称</drugName> <usage>用法</usage>
	 * <dosage>用量</dosage> <medicationTime>用药时间</medicationTime>
	 * <medicationComplianceCode> 用 药 依 从 </medicationComplianceCode>
	 * </medication> </medicationList>
	 */
	public static String setMedication(List<Medication> list) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		Medication past = null;
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			// serializer.startTag(null,"medicationList");
			for (int i = 0; i < list.size(); i++) {
				past = list.get(i);
				f = past.getClass().getDeclaredFields();
				serializer.startTag(null, "medication");
				for (Field field : f) {
					name = field.getName();
					serializer.startTag(null, name);
					field.setAccessible(true);
					try {
						txt = (String) field.get(past);
						if (null != txt) {
							serializer.text(txt);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					serializer.endTag(null, name);
				}
				serializer.endTag(null, "medication");
			}
			// serializer.endTag(null,"medicationList");
			serializer.endDocument();
			serializer.flush();
			String result = read.toString();

			result = result.replace("<?xml version='1.0' encoding='UTF-8' ?>",
					"");
			return result;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <vaccinationHistory> <vaccination>
	 * <inoculationName>接种名称</inoculationName>
	 * <inoculationDate>接种日期</inoculationDate> <orgName>接种机构名称</orgName>
	 * </vaccination> <vaccination> <inoculationName>接种名称</inoculationName>
	 * <inoculationDate>接种日期</inoculationDate> <orgName>接种机构名称</orgName>
	 * </vaccination> </vaccinationHistory>
	 */
	public static String setVaccination(List<Vaccination> list) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		Vaccination past = null;
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			// serializer.startTag(null,"vaccinationHistory");
			for (int i = 0; i < list.size(); i++) {
				past = list.get(i);
				f = past.getClass().getDeclaredFields();
				serializer.startTag(null, "vaccination");
				for (Field field : f) {
					name = field.getName();
					serializer.startTag(null, name);
					field.setAccessible(true);
					try {
						txt = (String) field.get(past);
						if (null != txt) {
							serializer.text(txt);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					serializer.endTag(null, name);
				}
				serializer.endTag(null, "vaccination");
			}
			// serializer.endTag(null,"vaccinationHistory");
			serializer.endDocument();
			serializer.flush();
			String result = read.toString();
			result = result.replace("<?xml version='1.0' encoding='UTF-8' ?>",
					"");
			return result;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <familyBedHistory> <familyBed> <admissionDate>建床日期</admissionDate>
	 * <dischargeDate>撤床日期</dischargeDate> <reason>原因</reason>
	 * <orgName>机构名称</orgName> <medicalRecordNumber>病案号</medicalRecordNumber>
	 * </familyBed> <familyBed> <admissionDate>建床日期</admissionDate>
	 * <dischargeDate>撤床日期</dischargeDate> <reason>原因</reason>
	 * <orgName>机构名称</orgName> <medicalRecordNumber>病案号</medicalRecordNumber>
	 * </familyBed> </familyBedHistory>
	 */

	public static String setFamilyBed(List<FamilyBed> list) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		FamilyBed past = null;
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			// serializer.startTag(null,"familyBedHistory");
			for (int i = 0; i < list.size(); i++) {
				past = list.get(i);
				f = past.getClass().getDeclaredFields();
				serializer.startTag(null, "familyBed");
				for (Field field : f) {
					name = field.getName();
					serializer.startTag(null, name);
					field.setAccessible(true);
					try {
						txt = (String) field.get(past);
						if (null != txt) {
							serializer.text(txt);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					serializer.endTag(null, name);
				}
				serializer.endTag(null, "familyBed");
			}
			// serializer.endTag(null,"familyBedHistory");
			serializer.endDocument();
			serializer.flush();
			String result = read.toString();

			result = result.replace("<?xml version='1.0' encoding='UTF-8' ?>",
					"");
			return result;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <hospitalizedHistory> <hospitalized> <admissionDate>入院日期</admissionDate>
	 * <dischargeDate>出院日期</dischargeDate> <reason>原因</reason>
	 * <orgName>机构名称</orgName> <medicalRecordNumber>病案号</medicalRecordNumber>
	 * </hospitalized> <hospitalized> 重庆市基层（村级）管理信息系统调度中心一体机接口规范 重庆畅中科技有限公司 －
	 * 173 / 404 － <admissionDate>入院日期</admissionDate>
	 * <dischargeDate>出院日期</dischargeDate> <reason>原因</reason>
	 * <orgName>机构名称</orgName> <medicalRecordNumber>病案号</medicalRecordNumber>
	 * </hospitalized> </hospitalizedHistory>
	 */
	public static String setHospitalized(List<Hospitalized> list) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		Hospitalized past = null;
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			// serializer.startTag(null,"hospitalizedHistory");
			for (int i = 0; i < list.size(); i++) {
				past = list.get(i);
				f = past.getClass().getDeclaredFields();
				serializer.startTag(null, "hospitalized");
				for (Field field : f) {
					name = field.getName();
					serializer.startTag(null, name);
					field.setAccessible(true);
					try {
						txt = (String) field.get(past);
						if (null != txt) {
							serializer.text(txt);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					serializer.endTag(null, name);
				}
				serializer.endTag(null, "hospitalized");
			}
			// serializer.endTag(null,"hospitalizedHistory");
			serializer.endDocument();
			serializer.flush();
			String result = read.toString();

			result = result.replace("<?xml version='1.0' encoding='UTF-8' ?>",
					"");
			return result;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/** 通过uuid获取primaryKey主键 */
	public static String getPrimaryKey(String uuid) {
		String[] strs = uuid.split(";");
		if (uuid.split(";").length > 1) {
			return strs[1];
		}
		return "";
	}

	/** 通过uuid获取对应的类型 */
	public static String getType(String uuid) {
		String result = "";
		String[] temp = uuid.split(";");
		if (temp.length > 0) {
			if ("DiabetesFlup_upload".equals(temp[0])) {
				result = "糖尿病随访记录";
			}
			if ("HypertensionFlup_upload".equals(temp[0])) {
				result = "高血压随访记录";
			}
			if ("HealthRecord_down".equals(temp[0])) {
				result = "健康档案";
			}
			if ("HealthExamination".equals(temp[0])) {
				result = "健康体检";
			}
			if ("PregnantFirstCheck_upload".equals(temp[0])) {
				result = "孕产妇初检记录";
			}
			if ("PregnantRecheck_upload".equals(temp[0])) {
				result = "孕产妇复检记录";
			}
			if ("PostpartumVisit_upload".equals(temp[0])) {
				result = "孕产妇产后访视记录";
			}
			if ("Postpartum42Visit_upload".equals(temp[0])) {
				result = "孕产妇产后42天访视记录";
			}
			if ("NeonatalVisit_upload".equals(temp[0])) {
				result = "新生儿家庭访视记录";
			}
		}
		return result;
	}
}
