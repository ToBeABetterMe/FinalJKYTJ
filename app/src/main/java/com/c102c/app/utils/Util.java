package com.c102c.app.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import android.util.Xml;

import com.c102c.app.model.Area_down;
import com.c102c.app.model.ChildSpecial_down;
import com.c102c.app.model.DiabetesFlup_down;
import com.c102c.app.model.DiabetesFlup_upload;
import com.c102c.app.model.DiabetesSpecial_down;
import com.c102c.app.model.Dictionary_down;
import com.c102c.app.model.HealthExamination;
import com.c102c.app.model.HealthRecord_Status;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.HypertensionFlup_down;
import com.c102c.app.model.HypertensionFlup_upload;
import com.c102c.app.model.HypertensionSpecial_down;
import com.c102c.app.model.LocalMeasurementBaiXiBao;
import com.c102c.app.model.LocalMeasurementEWen;
import com.c102c.app.model.LocalMeasurementNiaoYe;
import com.c102c.app.model.LocalMeasurementXinDian;
import com.c102c.app.model.LocalMeasurementXueTang;
import com.c102c.app.model.LocalMeasurementXueYa;
import com.c102c.app.model.LocalMeasurementXueYang;
import com.c102c.app.model.NeonatalVisit_upload;
import com.c102c.app.model.Org_down;
import com.c102c.app.model.Person_down;
import com.c102c.app.model.Postpartum42Visit_upload;
import com.c102c.app.model.PostpartumVisit_upload;
import com.c102c.app.model.PregnantFirstCheck_upload;
import com.c102c.app.model.PregnantRecheck_upload;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.app.model.User_down;

public class Util {
	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <dictionaryType>
	 * <typeCode>字典类型代码</orgCode> <typeName>字典类型名称</typeName>
	 * <typeOptionCode>字典类型选项代码</typeOptionCode> <items> <item>
	 * <itemCode>字典项代码</itemCode> <itemName>字典项名称</itemName>
	 * <itemOrder>字典项排序</itemOrder> <exclusiveCode>字典项排他代码</exclusiveCode>
	 * <inputCode>字典项补充输入代码</inputCode> </item> </items> </dictionaryType>
	 * <dictionaryType> <typeCode>字典类型代码</orgCode> <typeName>字典类型名称</typeName>
	 * <typeOptionCode>字典类型选项代码</typeOptionCode> <items> <item>
	 * <itemCode>字典项代码</itemCode> <itemName>字典项名称</itemName>
	 * <itemOrder>字典项排序</itemOrder> <exclusiveCode>字典项排他代码</exclusiveCode>
	 * <inputCode>字典项补充输入代码</inputCode> </item> </items> </dictionaryType>
	 * <dictionaryType> <typeCode>字典类型代码</orgCode> <typeName>字典类型名称</typeName>
	 * <typeOptionCode>字典类型选项代码</typeOptionCode> <items> <item>
	 * <itemCode>字典项代码</itemCode> <itemName>字典项名称</itemName>
	 * <itemOrder>字典项排序</itemOrder> <exclusiveCode>字典项排他代码</exclusiveCode>
	 * <inputCode>字典项补充输入代码</inputCode> </item> </items> </dictionaryType>
	 * </responseDatas>
	 */
	// 已经通过测试
	/**
	 * 3.1.2. 标准数据字典
	 * 
	 * @param str
	 *            xml字符串
	 * @throws IOException
	 */
	public static List<Dictionary_down> getDictionary_down(String str) {
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<Dictionary_down> dic_list = new ArrayList<Dictionary_down>();
			Dictionary_down dic = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("dictionaryType")) {
						dic = new Dictionary_down();
					} else if (dic != null && !name.equals("item")
							&& !name.equals("items")) {
						try {
							f = dic.getClass().getDeclaredField(name);
							try {
								f.setAccessible(true);
								f.set(dic, parser.nextText());
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// ////system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("dictionaryType")) {
						// dic.setInputCode(parser.nextText());
						dic_list.add(dic);
						dic = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return dic_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="2924" returnSize="2924"> <org> <orgCode>500228</orgCode>
	 * <orgName>梁平县</orgName> <orgType>20</orgType> </org> <org>
	 * <orgCode>500228002</orgCode> <orgName>蟠龙镇</orgName> <orgType>21</orgType>
	 * <parentorgCode>500228</parentorgCode> </org> <org>
	 * <orgCode>500228002001</orgCode> <orgName>青垭村</orgName>
	 * <orgType>22</orgType> <parentorgCode>500228002</parentorgCode> </org>
	 * <org> <orgCode>500228002001001</orgCode> <orgName>1组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001002</orgCode> <orgName>2组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001003</orgCode> <orgName>3组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001004</orgCode> <orgName>4组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001005</orgCode> <orgName>5组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001006</orgCode> <orgName>6组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001007</orgCode> <orgName>7组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001008</orgCode> <orgName>8组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001009</orgCode> <orgName>9组</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * </responseDatas>
	 */
	// 已通过测试
	/** 行政区域 下载 3.1.3 */
	public static List<Area_down> getArea_down(String str) {
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<Area_down> area_list = new ArrayList<Area_down>();
			Area_down area = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("area")) {
						area = new Area_down();
					} else if (area != null && !name.equals("area")) {
						f = area.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(area, parser.nextText());
					}
					// system.out.println("name" + name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("area")) {
						area_list.add(area);
						area = null;
						break;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return area_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 3.1.4. 医疗机构 下载 */
	public static List<Org_down> getOrg_down(String str) {
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<Org_down> org_list = new ArrayList<Org_down>();
			Org_down org = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("org")) {
						org = new Org_down();
					} else if (org != null && !name.equals("org")) {
						f = org.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(org, parser.nextText());
					}
					// ////system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("org")) {
						org_list.add(org);
						org = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return org_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 3.1.5 健康 一体机注册不需要怎么解析
	/**
	 * [ <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <user>
	 * <userCode>医疗机构用户编码</userCode> <userName>医疗机构用户名称</vName>
	 * <pym>用户名称拼音码</pym> <wbm>用户名称五笔码</wbm> </user> <user>
	 * <userCode>医疗机构用户编码</userCode> <userName>医疗机构用户名称</vName>
	 * <pym>用户名称拼音码</pym> <wbm>用户名称五笔码</wbm> </user> <user>
	 * <userCode>医疗机构用户编码</userCode> <userName>医疗机构用户名称</vName>
	 * <pym>用户名称拼音码</pym> <wbm>用户名称五笔码</wbm> </user> </responseDatas>
	 */
	/** 3.1.6. 医疗机构用户 */
	public static List<User_down> getUser_down(String str) {

		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<User_down> user_list = new ArrayList<User_down>();
			User_down user = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// ////system.out.println("total"+eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("user")) {
						user = new User_down();
					} else if (user != null && !name.equals("user")) {
						f = user.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(user, parser.nextText());
					}
					// ////system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("user")) {
						user_list.add(user);
						user = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return user_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="568" start="1" returnSize="100"> <healthRecord>
	 * <personId>7550658</personId> <nowLiveCode>500237026011001</nowLiveCode>
	 * <nowLiveName>巫山县三溪乡白鹤村1组</nowLiveName> <nowLiveAddr>35号</nowLiveAddr>
	 * <householdRegisterCode>500237003008004</householdRegisterCode>
	 * <householdRegisterName>巫山县抱龙镇桃花村4组</householdRegisterName>
	 * <householdRegisterAddr>109号</householdRegisterAddr>
	 * <registerOrgCode>5002370801</registerOrgCode>
	 * <registerOrgName>抱龙中心卫生院</registerOrgName>
	 * <registerDoctorCode>030002</registerDoctorCode>
	 * <registerDoctorName>陈XX</registerDoctorName>
	 * <responsibleDoctorCode>5002370100033</responsibleDoctorCode>
	 * <responsibleDoctorName>曾XX</responsibleDoctorName>
	 * <registerDate>业务办理日期</registerDate>
	 * <healthFileNumber>5002370061300134</healthFileNumber> <name>张三</name>
	 * <genderCode>1</genderCode> <birthday>19995-07-01</birthday>
	 * <idCard>50023719850907727X</idCard> <workUnit>无</workUnit>
	 * <phone>18719121314</birthday> <contacts>张三丰</contacts>
	 * <contactsPhone>18757398266</contactsPhone>
	 * <residentType>...</residentType> <ethnicityCode>...</ethnicityCode>
	 * <ethnicityName>...</ethnicityName> <bloodGroupCode>...</bloodGroupCode>
	 * <rhBloodGroupCode>...</rhBloodGroupCode> <eduBGCode>...</eduBGCode>
	 * <occupationCode>...</occupationCode>
	 * <maritalStatusCode>...</maritalStatusCode>
	 * <paymentMethodCodes>...</paymentMethodCodes>
	 * <paymentMethodOther>...</paymentMethodOther>
	 * <drugAllergyHistoryCodes>...</drugAllergyHistoryCodes>
	 * <drugAllergyHistoryOther>...</drugAllergyHistoryOther>
	 * <exposureHistoryCodes>...</exposureHistoryCodes>
	 * <familyHistoryFatherCodes>...</familyHistoryFatherCodes>
	 * <familyHistoryFatherOther>...</familyHistoryFatherOther>
	 * <familyHistoryMatherCodes>...</familyHistoryMatherCodes>
	 * <familyHistoryMatherOther>...</familyHistoryMatherOther>
	 * <brotherAndSisterCodes>...</brotherAndSisterCodes>
	 * <brotherAndSisterOther>...</brotherAndSisterOther>
	 * <familyHistoryChildrenCodes>...</familyHistoryChildrenCodes>
	 * <familyHistoryChildrenOther>...</familyHistoryChildrenOther>
	 * <geneticHistoryCode>...</geneticHistoryCode>
	 * <geneticHistoryOther>...</geneticHistoryOther>
	 * <disabilityCodes>...</disabilityCodes>
	 * <disabilityOther>...</disabilityOther>
	 * <kitchenExhaustCode>...</kitchenExhaustCode>
	 * <fuelTypeCode>...</fuelTypeCode> <waterCode>...</waterCode>
	 * <toiletCode>...</toiletCode>
	 * <livestockColumnCode>...</livestockColumnCode> <pastHistoryList>
	 * <pastHistory> <pastHistoryType>1</pastHistoryType>
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
	 * </healthRecord> ...... </responseDatas>
	 */

	/** 3.2.2. 健康档案 下载 */
	public static List<HealthRecord_down> getHealthRecord_down_before(String str) {

		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<HealthRecord_down> heal_list = new ArrayList<HealthRecord_down>();
			HealthRecord_down heal = null;
			Field f;
			String pastHistoryList = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("healthRecord")) {
						heal = new HealthRecord_down();
					}
					// //////////////list
					else if (name.equals("pastHistoryList")) {
						pastHistoryList = str.substring(
								str.indexOf("<pastHistoryList>"),                //这里有很大问题 
								str.indexOf("</pastHistoryList>") + 18)
								.toString();
						if (!pastHistoryList.equals("")) {
							// pastHistoryList=pastHistoryList+"|";
						}
					} else if (name.equals("pastHistory")) {

					} else if (name.equals("pastHistoryType")) {
//						 pastHistoryList=pastHistoryList+parser.nextText();
					} else if (name.equals("pastHistoryCode")) {
//						 pastHistoryList=pastHistoryList+"_"+parser.nextText();
					} else if (name.equals("pastHistoryAdd")) {
//						 pastHistoryList=pastHistoryList+"_"+parser.nextText();
					} else if (name.equals("pastHistoryDate")) {
//						 pastHistoryList=pastHistoryList+"_"+parser.nextText();
					}
					// ////////////////////list
					else if (heal != null && !name.equals("healthRecord")
							&& !name.equals("pastHistoryList")) {
						f = heal.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(heal, parser.nextText());
					}
					// ////system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("pastHistoryList")) {
						heal.setPastHistoryList(pastHistoryList);
						Log.i("pastHistoryList",pastHistoryList);
						pastHistoryList = "";
					} else if (end.equals("healthRecord")) {
						heal_list.add(heal);
						heal = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return heal_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<HealthRecord_down> getHealthRecord_down(String str) {

		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<HealthRecord_down> heal_list = new ArrayList<HealthRecord_down>();
			HealthRecord_down heal = null;
			Field f;
			String pastHistoryList = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("healthRecord")) {
						heal = new HealthRecord_down();
					}
					// //////////////list
					else if (name.equals("pastHistoryList")) {
						pastHistoryList = "<pastHistoryList>";
		     		} else if (name.equals("pastHistory")) {
		     			pastHistoryList = pastHistoryList + "<pastHistory>";
					} else if (name.equals("pastHistoryType")) {
						 pastHistoryList=pastHistoryList+"<pastHistoryType>"+parser.nextText()+"</pastHistoryType>";
					} else if (name.equals("pastHistoryCode")) {
						 pastHistoryList=pastHistoryList+"<pastHistoryCode>"+parser.nextText()+"</pastHistoryCode>";
					} else if (name.equals("pastHistoryAdd")) {
						 pastHistoryList=pastHistoryList+"<pastHistoryAdd>"+parser.nextText()+"</pastHistoryAdd>";
					} else if (name.equals("pastHistoryDate")) {
						 pastHistoryList=pastHistoryList+"<pastHistoryDate>"+parser.nextText()+"</pastHistoryDate>";
					}
					// ////////////////////list
					else if (heal != null && !name.equals("healthRecord")
							&& !name.equals("pastHistoryList")) {
						f = heal.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(heal, parser.nextText());
					}
					// ////system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("pastHistory"))
					{
						pastHistoryList=pastHistoryList+"</pastHistory>";
					}
					else if (end.equals("pastHistoryList")) {
						pastHistoryList=pastHistoryList+"</pastHistoryList>";
						heal.setPastHistoryList(pastHistoryList);
//						Log.i("pastHistoryList",pastHistoryList);
						pastHistoryList = "";
					} else if (end.equals("healthRecord")) {
						heal_list.add(heal);
						heal = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return heal_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<HealthRecord_down> getHealthRecord_down1(String str) {

		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<HealthRecord_down> heal_list = new ArrayList<HealthRecord_down>();
			HealthRecord_down heal = null;
			Field f;
			String pastHistoryList = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					// ////system.out.println("name"+name);
					if (name.equals("pastHistoryList")) {

					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("pastHistoryList")) {
					} else if (end.equals("healthRecord")) {
						heal_list.add(heal);
						heal = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return heal_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas>
	 * <healthRecord> <updateType>3</personId><!-- 修改健康档案状态 -->
	 * <personId>4564756856</personId> <machineNo>一体机业务序号</machineNo>
	 * <name>张三</name> <recordResultCode>0</recordResultCode>
	 * <recordResultDesc>记录操作成功</recordResultDesc> </healthRecord>
	 * </responseDatas>
	 */
	/**
	 * 3.2.3.7. 新增健康 档案 完整响应消息,3.2.3.8. 修改健康 档案 完整响应消息,3.2.3.9. 修改健康 档案 状态
	 * 完整响应消息
	 */
	public static HealthRecord_Status getHealthRecord_Status(String str) {
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();

			HealthRecord_Status status = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("healthRecord")) {
						status = new HealthRecord_Status();
					} else if (name.equals("updateType")) {
						status.setUpdateType(parser.nextText());
					} else if (name.equals("personId")) {
						status.setPersonId(parser.nextText());
					} else if (name.equals("machineNo")) {
						status.setMachineNo(parser.nextText());
					} else if (name.equals("name")) {
						status.setName(parser.nextText());
					} else if (name.equals("recordResultCode")) {
						status.setRecordResultCode(parser.nextText());
					} else if (name.equals("recordResultDesc")) {
						status.setRecordResultDesc(parser.nextText());
						return status;
					}
					// ////system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					break;
				}
				eventType = parser.next();
			}
			inStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas>
	 * <healthExamination> <personId>居民个人ID</personId> <machineExaminationNo> 一
	 * 体 机 业 务 序 号 </machineExaminationNo> <name>姓名</name>
	 * <examinationDate>体检日期</examinationDate> <orgCode>体检机构代码</orgCode>
	 * <operatorCode>操作员代码</operatorCode> <operatorName>操作员姓名</operatorName>
	 * <operateTime>操作时间</operateTime> <responsibleDoctorCode> 责 任 医 生 代 码
	 * </responsibleDoctorCode> <responsibleDoctorName> 责 任 医 生 姓 名
	 * </responsibleDoctorName> <symptomCodes>症状代码（多选）</symptomCodes>
	 * <symptomOther>症状-其他</symptomOther> <temperature>体温</temperature>
	 * <pulseRate>脉率</pulseRate> <respiratoryRate>呼吸频率</respiratoryRate>
	 * <height>身高</height> <weight>体重</weight> <bmi>体质指数</bmi>
	 * <waistline>腰围</waistline> <leftSBP>左侧血压-收缩压</leftSBP>
	 * <leftDBP>左侧血压-舒张压</leftDBP> <rightSBP>右侧血压-收缩压</rightSBP>
	 * <rightDBP>右侧血压-舒张压</rightDBP> <theAgedStatus> 老 年 人 健 康 状 态 自 我 评 估 代 码
	 * </theAgedStatus> <selfCareAbilityCode> 老 年 人 生 活 自 理 能 力 自 我 评 估
	 * </selfCareAbilityCode> <cognitiveFunction>老年人认知功能</cognitiveFunction>
	 * <cognitiveFunctionScore> 老 年 人 认 知 功 能 评 分 </cognitiveFunctionScore>
	 * <emotionalState>老年人情感状态</emotionalState> <emotionalStateScore> 老 年 人 情 感
	 * 状 态 评 分 </emotionalStateScore> <exerciseFrequencyCode> 体 育 锻 炼 频 率 代 码
	 * </exerciseFrequencyCode> <everyWorkoutTime>每次锻炼时间</everyWorkoutTime>
	 * <insistOnExerciseTime> 坚 持 锻 炼 时 间 </insistOnExerciseTime>
	 * <exerciseMode>锻炼方式</exerciseMode>
	 * <eatingHabitsCodes>饮食习惯（多选）</eatingHabitsCodes>
	 * <smokingStatusCode>吸烟状况代码</smokingStatusCode>
	 * <dailySmoking>日吸烟量</dailySmoking> <smokingAge>开始吸烟年龄</smokingAge>
	 * <smokingCessationAge>戒烟年龄</smokingCessationAge> <drinkingFrequencyCode> 饮
	 * 酒 频 率 代 码 </drinkingFrequencyCode> <dailyDrinking>日饮酒量</dailyDrinking>
	 * <temperanceCode>是否戒酒</temperanceCode> <temperanceAge>戒酒年龄</temperanceAge>
	 * <drinkingAge>开始饮酒年龄</drinkingAge> <whetherDrunk>近一年是否醉酒</whetherDrunk>
	 * <alcoholTypeCodes>饮酒种类代码（多选）</alcoholTypeCodes>
	 * <alcoholTypeOther>饮酒种类-其他</alcoholTypeOther> <exposureStateCode> 是 否 有 职
	 * 业 暴 露 情 况 </exposureStateCode> <hazardousWork>危害工种</hazardousWork>
	 * <workingTime>从业时间</workingTime> <dust>毒物种类-粉尘</dust>
	 * <dustProtective>粉尘-防护措施</dustProtective> <dustProtectiveDesc> 粉 尘 防 护 措 施
	 * 描 述 </dustProtectiveDesc> <radiogen>毒物种类-放射物质</radiogen>
	 * <radiogenProtective> 放 射 物 质 - 防 护 措 施 </radiogenProtective>
	 * <radiogenProtectiveDesc> 放 射 物 质 防 护 措 施 描 述 </radiogenProtectiveDesc>
	 * <physical>毒物种类-物理因素</physical> <physicalProtective> 物 理 因 素 - 防 护 措 施
	 * </physicalProtective> <physicalProtectiveDesc> 物 理 因 素 防 护 措 施 描 述
	 * </physicalProtectiveDesc>
	 * 
	 * <chemistry>毒物种类-化学物质</chemistry> <chemistryProtective> 化 学 物 质 - 防 护 措 施
	 * </chemistryProtective> <chemistryProtectiveDesc> 化 学 防 护 措 施 描 述
	 * </chemistryProtectiveDesc> <otherToxicant>毒物种类-其他</otherToxicant>
	 * <otherProtective>其他-防护措施</otherProtective> <otherProtectiveDesc> 其 他 防 护
	 * 措 施 描 述 </otherProtectiveDesc> <lipsCode>口唇代码</lipsCode>
	 * <dentitionCodes>齿列（多选）</dentitionCodes>
	 * <missingTeethLeftUp>缺齿左上</missingTeethLeftUp>
	 * <missingTeethRightUp>缺齿右上</missingTeethRightUp>
	 * <missingTeethLeftDown>缺齿左下</missingTeethLeftDown>
	 * <missingTeethRightDown>缺齿右下</missingTeethRightDown>
	 * <cariesLeftUp>龋齿左上</cariesLeftUp> <cariesRightUp>龋齿右上</cariesRightUp>
	 * <cariesLeftDown>龋齿左下</cariesLeftDown>
	 * <cariesRightDown>龋齿右下</cariesRightDown>
	 * <dentureLeftUp>假齿左上</dentureLeftUp> <dentureRightUp>假齿右上</dentureRightUp>
	 * <dentureLeftDown>假齿左下</dentureLeftDown>
	 * <dentureRightDown>假齿右下</dentureRightDown> <throatCode>咽部代码</throatCode>
	 * <visionRight>右眼视力</visionRight> <visionLeft>左眼视力</visionLeft>
	 * <redressVisionRight>左眼矫正视力</redressVisionRight>
	 * <redressVisionLeft>右眼矫正视力</redressVisionLeft>
	 * <hearingCode>听力代码</hearingCode>
	 * <motorFunctionCode>运动功能代码</motorFunctionCode>
	 * <fundusCode>眼底代码</fundusCode> <fundusAbnormal>眼底异常情况</fundusAbnormal>
	 * <skinCode>皮肤代码</skinCode> <skinOther>皮肤（其他）</skinOther>
	 * <scleraCode>巩膜代码</scleraCode> <scleraOther>巩膜（其他）</scleraOther>
	 * <lymphNodeCode>淋巴结代码</lymphNodeCode>
	 * <lymphNodeOther>淋巴结（其他）</lymphNodeOther>
	 * <barrelChestCode>肺-桶状胸</barrelChestCode>
	 * <breathSoundCode>肺-呼吸音代码</breathSoundCode>
	 * <breathSoundOther>肺-呼吸音（异常）</breathSoundOther>
	 * <raleCode>肺-罗音代码</raleCode> <raleOther>肺-罗音（其他）</raleOther>
	 * <heartRate>心脏-心率</heartRate> <rhythmCode>心脏-心律情况</rhythmCode>
	 * <cardiacSouffleCode>心脏-杂音</cardiacSouffleCode> <cardiacSouffleDesc>心脏-杂音
	 * （描述） </cardiacSouffleDesc>
	 * <abdomenTendernessCode>腹部-压痛</abdomenTendernessCode>
	 * <abdomenTendernessDesc> 腹 部 - 压 痛 （ 描 述 ） </abdomenTendernessDesc>
	 * <abdomenMassCode>腹部-包块</abdomenMassCode>
	 * <abdomenMassDesc>腹部-包块（描述）</abdomenMassDesc>
	 * <abdomenLiverCode>腹部-肝大</abdomenLiverCode>
	 * <abdomenLiverDesc>腹部-肝大（描述）</abdomenLiverDesc> <abdomenSplenomegalyCode>
	 * 腹 部 - 脾 大 </abdomenSplenomegalyCode> <abdomenSplenomegalyDesc> 腹 部 - 脾 大
	 * （ 描 述 ） </abdomenSplenomegalyDesc> <abdomenShiftingDullnessCode> 移 动 性 浊
	 * 音 </abdomenShiftingDullnessCode> <abdomenShiftingDullnessDesc> 浊 音 （ 描 述
	 * ） </abdomenShiftingDullnessDesc> <legEdemaCode>下肢水肿</legEdemaCode>
	 * <dorsalisPedisPulseCode> 足 背 动 脉 搏 动 </dorsalisPedisPulseCode>
	 * <analFingerCodes>肛门指诊代码（多选）</analFingerCodes>
	 * <analFingerDesc>肛门指诊描述</analFingerDesc>
	 * <breastCodes>乳腺代码（多选）</breastCodes> <breastDesc>乳腺描述</breastDesc>
	 * <vulvaCode>妇科-外阴代码</vulvaCode> <vulvaDesc>妇科-外阴描述</vulvaDesc>
	 * <vaginaCode>妇科-阴道代码</vaginaCode> <vaginaDesc>妇科-阴道描述</vaginaDesc>
	 * <cervixCode>妇科-宫颈代码</cervixCode> <cervixDesc>妇科-宫颈描述</cervixDesc>
	 * <uterusCode>妇科-宫体代码</uterusCode> <uterusDesc>妇科-宫体描述</uterusDesc>
	 * <uterineAccessoriesCode> 妇 科 - 附 件 代 码 </uterineAccessoriesCode>
	 * <uterineAccessoriesDesc> 妇 科 - 附 件 描 述 </uterineAccessoriesDesc>
	 * <examinationOther>查体-其他</examinationOther>
	 * <hemoglobin>血常规-血红蛋白</hemoglobin> <leucocyte>血常规-白细胞</leucocyte>
	 * <platelet>血常规-血小板</platelet> <bloodOther>血常规-其他</bloodOther>
	 * <urineProteinCode>尿常规-尿蛋白</urineProteinCode>
	 * <urineSugarCode>尿常规-尿糖</urineSugarCode>
	 * <urineKetoneCode>尿常规-尿酮体</urineKetoneCode> <urineOccultBloodCode> 尿 常 规 -
	 * 尿 潜 血 </urineOccultBloodCode> <urineOther>尿常规-其他</urineOther>
	 * <fastingPlasmaGlucose1>空腹血糖</fastingPlasmaGlucose1>
	 * <fastingPlasmaGlucose2>空腹血糖</fastingPlasmaGlucose2>
	 * <ECGCode>心电图代码</ECGCode> <ECGDesc>心电图（描述）</ECGDesc>
	 * <ECGData>心电图（数据）</ECGData> <urineTraceAlbuminCode> 尿 微 量 白 蛋 白
	 * </urineTraceAlbuminCode>
	 * <fecalOccultBloodCode>大便潜血</fecalOccultBloodCode>
	 * <glycatedHemoglobin>糖化血红蛋白</glycatedHemoglobin> <HBsAg>乙型肝炎表面抗原</HBsAg>
	 * <GPT>肝功能-血清谷丙转氨酶 </GPT> <GOT>肝功能-血清谷草转氨酶 </GOT>
	 * <albumin>肝功能-白蛋白</albumin> <TBIL>肝功能-总胆红素</TBIL> <DBIL>肝功能-结合胆红素</DBIL>
	 * <serumCreatinine>肾功能-血清肌酐</serumCreatinine> <BUN>肾功能-血尿素氮</BUN>
	 * <serumPotassiumConcentration> 肾 血 钾 浓 度 </serumPotassiumConcentration>
	 * <serumSodiumConcentration> 肾 功 能 - 血 钠 浓 度 </serumSodiumConcentration>
	 * <TCHO>血脂-血脂总胆固醇</TCHO> <triglyceride>血脂-甘油三脂</triglyceride>
	 * <LDL>血脂-血清低密度值蛋白胆固醇</LDL> <HDL>血脂-血清高密度值蛋白胆固醇</HDL>
	 * <chestXRayCode>胸部X线片代码</chestXRayCode>
	 * <chestXRayDesc>胸部X线片（描述）</chestXRayDesc> <BScanCode>B超代码</BScanCode>
	 * <BScanDesc>B超（描述）</BScanDesc> <papSmearCode>宫颈涂片代码</papSmearCode>
	 * <papSmearDesc>宫颈涂片（描述）</papSmearDesc> <checkOther>辅助检查-其他</checkOther>
	 * <flatAndQualityCode>中医平和质代码</flatAndQualityCode>
	 * <qiDeficiencyCode>中医气虚质代码</qiDeficiencyCode>
	 * <yangXuzhiCode>中医阳虚质代码</yangXuzhiCode>
	 * <yinDeficiencyCode>中医阴虚质代码</yinDeficiencyCode>
	 * <phlegmDampnessQualityCode> 中 医 痰 湿 质 代 码 </phlegmDampnessQualityCode>
	 * <hotAndHumidQualityCode> 中 医 湿 热 质 代 码 </hotAndHumidQualityCode>
	 * <bloodStasisCode>中医血瘀质代码</bloodStasisCode>
	 * <qiStagnationCode>中医气郁质代码</qiStagnationCode>
	 * <specialQualityCode>中医特秉质代码</specialQualityCode> <cerebrovascularCodes> 脑
	 * 血 管 疾 病 代 码 （ 多 选 ） </cerebrovascularCodes> <cerebrovascularDesc> 脑 血 管 疾
	 * 病 （ 补 充 ） </cerebrovascularDesc> <kidneyDiseaseCodes> 肾 脏 疾 病 代 码 （ 多 选 ）
	 * </kidneyDiseaseCodes> <kidneyDiseaseDesc>肾脏疾病（补充）</kidneyDiseaseDesc>
	 * <heartDiseaseCodes> 心 脏 疾 病 代 码 （ 多 选 ） </heartDiseaseCodes>
	 * <heartDiseaseDesc>心脏疾病疾病（补充）</heartDiseaseDesc> <vascularDiseaseCodes> 血
	 * 管 疾 病 代 码 （ 多 选 ） </vascularDiseaseCodes> <vascularDiseaseDesc> 血 管 疾 病 （
	 * 补 充 ） </vascularDiseaseDesc>
	 * <eyeDiseaseCodes>眼部疾病代码（多选）</eyeDiseaseCodes>
	 * <eyeDiseaseDesc>眼部疾病（补充）</eyeDiseaseDesc> <nerve//SystemDiseaseCode> 神 经
	 * 系 统 疾 病 代 码 </nerve//SystemDiseaseCode> <nerve//SystemDiseaseDesc> 神 经 系
	 * 统 疾 病 （ 补 充 ） </nerve//SystemDiseaseDesc> <other//SystemDiseaseCode> 其 他
	 * 系 统 疾 病 代 码 </other//SystemDiseaseCode> <other//SystemDiseaseDesc> 其 他 系
	 * 统 疾 病 （ 补 充 ） </other//SystemDiseaseDesc>
	 * 
	 * 
	 * 
	 * 
	 * <hospitalizedHistory> <hospitalized> <admissionDate></admissionDate>
	 * <dischargeDate></dischargeDate> <reason></reason> <orgName></orgName>
	 * <medicalRecordNumber></medicalRecordNumber> </hospitalized>
	 * <hospitalized> <admissionDate></admissionDate>
	 * <dischargeDate></dischargeDate> <reason></reason> <orgName></orgName>
	 * <medicalRecordNumber></medicalRecordNumber> </hospitalized>
	 * </hospitalizedHistory>
	 * 
	 * 
	 * 
	 * 
	 * <familyBedHistory> <familyBed> <admissionDate></admissionDate>
	 * <dischargeDate></dischargeDate> <reason></reason> <orgName></orgName>
	 * <medicalRecordNumber></medicalRecordNumber> </familyBed> <familyBed>
	 * <admissionDate></admissionDate> <dischargeDate></dischargeDate>
	 * <reason></reason> <orgName></orgName>
	 * <medicalRecordNumber></medicalRecordNumber> </familyBed>
	 * </familyBedHistory>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * <medicationList> <medication> <drugName></drugName> <usage></usage>
	 * <dosage></dosage> <medicationTime></medicationTime>
	 * <medicationComplianceCode></medicationComplianceCode> </medication>
	 * <medication> <drugName></drugName> <usage></usage> <dosage></dosage>
	 * <medicationTime></medicationTime>
	 * <medicationComplianceCode></medicationComplianceCode> </medication>
	 * </medicationList>
	 * 
	 * 
	 * 
	 * <vaccinationHistory> <vaccination> <inoculationName></inoculationName>
	 * <inoculationDate></inoculationDate> <orgName></orgName> </vaccination>
	 * <vaccination> <inoculationName></inoculationName>
	 * <inoculationDate></inoculationDate> <orgName></orgName> </vaccination>
	 * </vaccinationHistory> <healthEvaluationCode>健康评价</healthEvaluationCode>
	 * <diseaseName1>健康评价疾病名称1</diseaseName1>
	 * <diseaseName2>健康评价疾病名称2</diseaseName2>
	 * <diseaseName3>健康评价疾病名称3</diseaseName3>
	 * <diseaseName4>健康评价疾病名称4</diseaseName4> <healthGuidanceCodes> 健 康 指 导 代 码
	 * （ 多 选 ） </healthGuidanceCodes>
	 * <healthGuidanceDesc>健康指导（补充）</healthGuidanceDesc> <riskFactorsCodes> 危 险
	 * 因 素 控 制 代 码 （ 多 选 ） </riskFactorsCodes>
	 * <weightReduction>减体重目标</weightReduction>
	 * <vaccinationName>建议疫苗接种名称</vaccinationName>
	 * <riskFactorsOther>危险因素控制-其他</riskFactorsOther>
	 * <recordResultCode>1</recordResultCode>
	 * <recordResultDesc></recordResultDesc> </healthExamination>
	 * </responseDatas>
	 */
	/** 3.3.2. 健康体检记录 */

	public static List<HealthExamination> getHealthExaminationlist_byjiang(
			String str) {
		System.out.println(str);
		List<HealthExamination> list = new ArrayList<HealthExamination>();
		HealthExamination heal = null;
		Field f;
		StringReader inStream = new StringReader(str);
		StringWriter read = new StringWriter();
		XmlPullParser parser = Xml.newPullParser();
		XmlSerializer serializer = Xml.newSerializer();
		try {
			parser.setInput(inStream);
			serializer.setOutput(read);
			int eventType = parser.getEventType();
			ArrayList<String> ignor = new ArrayList<String>();
			ignor.add("hospitalizedHistory");
			ignor.add("familyBedHistory");
			ignor.add("medicationList");
			ignor.add("vaccinationHistory");
			while (eventType != 1) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					System.out.println(name);
					if (name.equals("hospitalizedHistory")) {
						//System.out.println("Problem is here");
					}
					if (name.equals("healthExamination"))
						heal = new HealthExamination();
					else if (ignor.contains(name)) {
						
						String head = "<" + name + ">";
						String end = "</" + name + ">";
						int headnum = str.indexOf(head);
						int endnum = str.indexOf(end);
						head = str.substring(headnum, endnum + end.length());
						while(!(eventType == XmlPullParser.END_TAG && parser.getName() == name)){
							eventType=parser.next();
						}
						//f = heal.getClass().getDeclaredField(parser.getName());
						//f.setAccessible(true);
						//f.set(heal, head);
					} else if (heal != null) {
						f = heal.getClass().getDeclaredField(parser.getName());
						f.setAccessible(true);
						f.set(heal, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					String end = parser.getName();
					if (end.equals("healthExamination")) {
						list.add(heal);
						heal = null;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}

	public static List<HealthExamination> getHealthExaminationlist(String str) {
		List<HealthExamination> list = new ArrayList<HealthExamination>();
		HealthExamination heal = null;
		Field f;
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			String hospitalizedHistory = "";
			String familyBedHistory = "";
			String medicationList = "";
			String vaccinationHistory = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					System.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("healthExamination")) {
						heal = new HealthExamination();
					}
					/**
					 * <admissionDate></admissionDate>
					 * <dischargeDate></dischargeDate> <reason></reason>
					 * <orgName></orgName>
					 * <medicalRecordNumber></medicalRecordNumber>
					 */
					else if (name.equals("hospitalizedHistory")) {
						hospitalizedHistory = str.substring(
								str.indexOf("<hospitalizedHistory>"),
								str.indexOf("</hospitalizedHistory>") + 22);
						str = str.replaceFirst(hospitalizedHistory, "");
						if (!hospitalizedHistory.equals("")) {
							// hospitalizedHistory=hospitalizedHistory+"|";
						}
					} else if (name.equals("hospitalized")) {
					} else if (name.equals("admissionDate")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+parser.nextText();
						}
					} else if (name.equals("doseCode")) {
					} else if (name.equals("dischargeDate")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						}
					} else if (name.equals("reason")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						}
					} else if (name.equals("orgName")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else if (!familyBedHistory.equals("1")) {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						} else {
							// vaccinationHistory=vaccinationHistory+"_"+parser.nextText();
						}
					} else if (name.equals("medicalRecordNumber")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						}
					}
					/**
					 * <familyBed> <admissionDate></admissionDate>
					 * <dischargeDate></dischargeDate> <reason></reason>
					 * <orgName></orgName>
					 * <medicalRecordNumber></medicalRecordNumber> </familyBed>
					 */
					else if (name.equals("familyBedHistory")) {
						familyBedHistory = str.substring(
								str.indexOf("<familyBedHistory>"),
								str.indexOf("</familyBedHistory>") + 19);
						str = str.replaceFirst(familyBedHistory, "");
						if (!familyBedHistory.equals("")) {
							// familyBedHistory=familyBedHistory+"|";
						}
					} else if (name.equals("familyBed")) {
					}
					/**
					 * <medication> <drugName></drugName> <usage></usage>
					 * <dosage></dosage> <medicationTime></medicationTime>
					 * <medicationComplianceCode></medicationComplianceCode>
					 */

					else if (name.equals("medicationList")) {
						medicationList = str.substring(
								str.indexOf("<medicationList>"),
								str.indexOf("</medicationList>") + 17);
						str = str.replaceFirst(medicationList, "");
						if (!medicationList.equals("")) {
							// medicationList=medicationList+"|";
						}

					} else if (name.equals("medication")) {

					} else if (name.equals("drugName")) {
						// medicationList=medicationList+parser.nextText();
					} else if (name.equals("usage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("dosage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("medicationTime")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("medicationComplianceCode")) {
						// medicationList=medicationList+"_"+parser.nextText();
					}

					/**
					 * <vaccination> <inoculationName></inoculationName>
					 * <inoculationDate></inoculationDate> <orgName></orgName>
					 * </vaccination>
					 */
					else if (name.equals("vaccination")) {
					} else if (name.equals("vaccinationHistory")) {
						// //system.out.println(str);
						vaccinationHistory = str.substring(
								str.indexOf("<vaccinationHistory>"),
								str.indexOf("</vaccinationHistory>") + 21);
						str = str.replaceFirst(vaccinationHistory, "");
						if (!vaccinationHistory.equals("")) {
							// vaccinationHistory=vaccinationHistory+"|";
						}
					} else if (name.equals("inoculationName")) {
						// vaccinationHistory=vaccinationHistory+parser.nextText();
					} else if (name.equals("inoculationDate")) {
						// vaccinationHistory=vaccinationHistory+"_"+parser.nextText();
					} else if (name.equals("orgName")) {
						// vaccinationHistory=vaccinationHistory+"_"+parser.nextText();
					} else if (heal != null
							&& !name.equals("healthExamination")
							&& !name.equals("hospitalizedHistory")
							&& !name.equals("familyBedHistory")
							&& !name.equals("medicationList")
							&& !name.equals("vaccinationHistory")
							&& !name.equals("responseDatas")) {
						f = heal.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(heal, parser.nextText());
					}
					// //system.out.println("开始浮出"+name );
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// //system.out.println("结束付出"+end);
					if (end != null && end.equals("hospitalizedHistory")) {
						heal.setHospitalizedHistory(hospitalizedHistory);
						hospitalizedHistory = "1";
					} else if (end != null && end.equals("familyBedHistory")) {
						heal.setFamilyBedHistory(familyBedHistory);
						familyBedHistory = "1";
					} else if (end != null && end.equals("medicationList")) {
						heal.setMedicationList(medicationList);
						medicationList = "1";
					} else if (end != null && end.equals("vaccinationHistory")) {
						heal.setVaccinationHistory(vaccinationHistory);
						vaccinationHistory = "1";
					} else if (end.equals("healthExamination")) {
						list.add(heal);
						// system.out.println(str);
						heal = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return list;
		} catch (Exception e) {

		}
		return null;
	}

	public static HealthExamination getHealthExamination(String str) {
		HealthExamination heal = null;
		Field f;
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			String hospitalizedHistory = "";
			String familyBedHistory = "";
			String medicationList = "";
			String vaccinationHistory = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("responseDatas")) {
						heal = new HealthExamination();
					}
					/**
					 * <admissionDate></admissionDate>
					 * <dischargeDate></dischargeDate> <reason></reason>
					 * <orgName></orgName>
					 * <medicalRecordNumber></medicalRecordNumber>
					 */
					else if (name.equals("hospitalizedHistory")) {
						hospitalizedHistory = str.substring(
								str.indexOf("<hospitalizedHistory>"),
								str.indexOf("</hospitalizedHistory>") + 22);
						if (!hospitalizedHistory.equals("")) {
							// hospitalizedHistory=hospitalizedHistory+"|";
						}

					} else if (name.equals("hospitalized")) {

					} else if (name.equals("admissionDate")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+parser.nextText();
						}
					} else if (name.equals("dischargeDate")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						}
					} else if (name.equals("reason")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						}
					} else if (name.equals("orgName")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else if (!familyBedHistory.equals("1")) {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						} else {
							// vaccinationHistory=vaccinationHistory+"_"+parser.nextText();
						}
					}

					else if (name.equals("medicalRecordNumber")) {
						if (!hospitalizedHistory.equals("1")) {
							// hospitalizedHistory=hospitalizedHistory+"_"+parser.nextText();
						} else {
							// familyBedHistory=familyBedHistory+"_"+parser.nextText();
						}
					}
					/**
					 * <familyBed> <admissionDate></admissionDate>
					 * <dischargeDate></dischargeDate> <reason></reason>
					 * <orgName></orgName>
					 * <medicalRecordNumber></medicalRecordNumber> </familyBed>
					 */

					else if (name.equals("familyBedHistory")) {
						familyBedHistory = str.substring(
								str.indexOf("<familyBedHistory>"),
								str.indexOf("</familyBedHistory>") + 19);
						if (!familyBedHistory.equals("")) {
							// familyBedHistory=familyBedHistory+"|";
						}

					}

					else if (name.equals("familyBed")) {

					}

					/**
					 * <medication> <drugName></drugName> <usage></usage>
					 * <dosage></dosage> <medicationTime></medicationTime>
					 * <medicationComplianceCode></medicationComplianceCode>
					 */

					else if (name.equals("medicationList")) {
						medicationList = str.substring(
								str.indexOf("<medicationList>"),
								str.indexOf("</medicationList>") + 17);
						if (!medicationList.equals("")) {
							// medicationList=medicationList+"|";
						}

					} else if (name.equals("medication")) {

					} else if (name.equals("drugName")) {
						// medicationList=medicationList+parser.nextText();
					} else if (name.equals("usage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("dosage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("medicationTime")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("medicationComplianceCode")) {
						// medicationList=medicationList+"_"+parser.nextText();
					}

					/**
					 * <vaccination> <inoculationName></inoculationName>
					 * <inoculationDate></inoculationDate> <orgName></orgName>
					 * </vaccination>
					 */
					else if (name.equals("vaccination")) {

					} else if (name.equals("vaccinationHistory")) {
						// ////system.out.println(str);
						vaccinationHistory = str.substring(
								str.indexOf("<vaccinationHistory>"),
								str.indexOf("</vaccinationHistory>") + 21);
						if (!vaccinationHistory.equals("")) {

							// vaccinationHistory=vaccinationHistory+"|";
						}
					} else if (name.equals("inoculationName")) {
						// vaccinationHistory=vaccinationHistory+parser.nextText();
					} else if (name.equals("inoculationDate")) {
						// vaccinationHistory=vaccinationHistory+"_"+parser.nextText();
					} else if (name.equals("orgName")) {
						// vaccinationHistory=vaccinationHistory+"_"+parser.nextText();
					} else if (heal != null
							&& !name.equals("healthExamination")
							&& !name.equals("hospitalizedHistory")
							&& !name.equals("familyBedHistory")
							&& !name.equals("medicationList")
							&& !name.equals("vaccinationHistory")
							&& !name.equals("responseDatas")) {
						f = heal.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(heal, parser.nextText());
					}
					// ////system.out.println("开始浮出"+name );
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("hospitalizedHistory")) {
						heal.setHospitalizedHistory(hospitalizedHistory);
						hospitalizedHistory = "1";
					} else if (end != null && end.equals("familyBedHistory")) {
						heal.setFamilyBedHistory(familyBedHistory);
						familyBedHistory = "1";
					} else if (end != null && end.equals("medicationList")) {
						heal.setMedicationList(medicationList);
						medicationList = "1";
					} else if (end != null && end.equals("vaccinationHistory")) {
						heal.setVaccinationHistory(vaccinationHistory);
						vaccinationHistory = "1";
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return heal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <childSpecial>
	 * <personId>居民个人ID</personId> <specialNo>儿童专项编号</specialNo> <name>姓名</name>
	 * <genderCode>性别代码</genderCode> <birthday>出生日期</birthday>
	 * <householdRegister>户籍地址</householdRegister>
	 * <childHealthCardNo>儿童保健卡编号</childHealthCardNo>
	 * <birthWeight>出生体重</birthWeight> <birthHeight>出生身长</birthHeight>
	 * <parity>胎次</parity> <deliveryTimes>产次</deliveryTimes>
	 * <deliveryGestationalWeeks>分娩孕周</deliveryGestationalWeeks>
	 * <deliveryModeCode>分娩方式代码</deliveryModeCode> <childbirth>产时情况</childbirth>
	 * <birthHospital>出生医院</birthHospital> <fatherName>父亲姓名</fatherName>
	 * <matherName>母亲姓名</matherName> <fatherContac>父亲联系电话</fatherContac>
	 * <matherContac>母亲联系电话</matherContac> <UNHSCode>新生儿听力筛查代码</UNHSCode>
	 * <CYP17Code>17-a-OHP</CYP17Code> <PKUCode>苯丙酮尿症筛查代码</PKUCode>
	 * <CHCode>先天性甲状腺功能低下代码</CHCode> <highRiskCode>是否高危代码</highRiskCode>
	 * <highRiskFactors>高危因素</highRiskFactors> <apgar1>Apgar 1分钟评分</apgar1>
	 * <apgar5>Apgar 5分钟评分</apgar5> <apgar10>Apgar 10分钟评分</apgar10>
	 * <pastHistory>既往病史</pastHistory> <allergicHistory>过敏史</allergicHistory>
	 * <childbirthHospital>接产医院</childbirthHospital>
	 * <childbirthDoctor>接产主手</childbirthDoctor>
	 * <childbirthAssistant>接产副手</childbirthAssistant>
	 * <registerDate>建册日期</registerDate>
	 * <registerOrgCode>建册单位代码</registerOrgCode>
	 * <registerOrgName>建册单位名称</registerOrgName>
	 * <registerDoctorCode>建册人代码</registerDoctorCode>
	 * <registerDoctorName>建册人姓名</registerDoctorName> </childSpecial> ....
	 * </responseDatas>
	 */
	/** 3.5.2. 儿童专项档案下载 */
	public static List<ChildSpecial_down> getChildSpecial_down(String str) {
		ChildSpecial_down child = null;
		List<ChildSpecial_down> child_list = new ArrayList<ChildSpecial_down>();
		Field f;
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("childSpecial")) {
						child = new ChildSpecial_down();
						// ////system.out.println("新建了");
					} else if (child != null) {
						f = child.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(child, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("childSpecial")) {
						child_list.add(child);
						child = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return child_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <hypertensionSpecial>
	 * <personId>居民个人ID</personId> <specialNo>高血压专项编号</specialNo>
	 * <name>姓名</name> <registerDate>建档日期</registerDate>
	 * <registerOrgCode>建档单位代码</registerOrgCode>
	 * <registerDoctorCode>建档人代码</registerDoctorCode>
	 * <registerDoctorName>建档人姓名</registerDoctorName>
	 * <diagnoseDate>确诊日期</diagnoseDate>
	 * <diagnoseOrgCode>确诊单位代码</diagnoseOrgCode>
	 * <diagnoseDoctorCode>确诊人代码</diagnoseDoctorCode>
	 * <diagnoseDoctorName>确诊人姓名</diagnoseDoctorName> <SBP>收缩压</SBP>
	 * <DBP>舒张压</DBP> <bloodPressureLevel>血压分级</bloodPressureLevel>
	 * <nextFlupDate>下次随访日期</nextFlupDate> </hypertensionSpecial> ......
	 * </responseDatas>
	 */
	/** 3.6.2. 高血压专项档案下载 */
	public static List<HypertensionSpecial_down> getHypertensionSpecial_down(
			String str) {
		HypertensionSpecial_down hy = null;
		List<HypertensionSpecial_down> hy_list = new ArrayList<HypertensionSpecial_down>();
		Field f;
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("hypertensionSpecial")) {
						hy = new HypertensionSpecial_down();
						// ////system.out.println("新建了");
					} else if (hy != null) {
						f = hy.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(hy, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("hypertensionSpecial")) {
						hy_list.add(hy);
						hy = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return hy_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <hypertensionFlup>
	 * <personId>居民个人ID</personId> <healthFileNumber>健康档案编号</healthFileNumber>
	 * <name>姓名</name> <flupDate>随访日期</flupDate> <flupMode>随访方式</flupMode>
	 * <symptomCodes>症状代码（多选）</symptomCodes> <symptomOther>症状（其他）</symptomOther>
	 * <SBP>血压-收缩压</SBP> <DBP>血压-扩张压</DBP> <weight>体重</weight>
	 * <weightTarget>体重-目标值</weightTarget> <bmi>体质指数</bmi>
	 * <bmiTarget>体质指数-目标值</bmiTarget> <heartRate>心率</heartRate>
	 * <signsOther>体征-其他</signsOther> <dailySmoking>日吸烟量</dailySmoking>
	 * <dailySmokingTarget>日吸烟量-目标值</dailySmokingTarget>
	 * <dailyDrinking>日饮酒量</dailyDrinking>
	 * <dailyDrinkingTarget>日饮酒量-目标值</dailyDrinkingTarget>
	 * <perWeekMovements>每周运动次数</perWeekMovements>
	 * <perWeekMovementsTarget>每周运动次数-目标值</perWeekMovementsTarget>
	 * <perWeekTimes>每次运动时间</perWeekTimes>
	 * <perWeekTimesTarget>每次运动时间-目标值</perWeekTimesTarget>
	 * <saltIntakeCode>摄盐情况代码</saltIntakeCode>
	 * <saltIntakeTargetCode>摄盐情况目标值代码</saltIntakeTargetCode>
	 * <psychologicalCode>心理调整代码</psychologicalCode>
	 * <complianceCode>遵医行为代码</complianceCode <aidCheck>辅助检查</aidCheck>
	 * <doseCode>服药依从性代码</doseCode>
	 * <adverseReactionCode>药物不良反应代码</adverseReactionCode>
	 * <flupTypeCode>此次随访分类代码</flupTypeCode> <medicationList> <medication>
	 * <drugCode>药品代码</drugCode> <drugName>药品名称</drugName>
	 * <drugUsage>药品用法</drugUsage> <drugUsageAdd>药品用法补充</drugUsageAdd>
	 * <drugDosage>药品用量</drugDosage> <drugDosageAdd>药品用量补充</drugDosageAdd>
	 * </medication> ...... </medicationList>
	 * <referralReason>转诊原因</referralReason> <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <flupDoctorCode>随访医生代码</flupDoctorCode>
	 * <flupDoctorName>随访医生名称</flupDoctorName> <flupOrgCode>随访机构代码</flupOrgCode>
	 * 
	 * <flupOrgName>随访机构名称</flupOrgName> <nextFlupDate>下次随访日期</nextFlupDate>
	 * </hypertensionFlup>
	 * 
	 * ...... </responseDatas>
	 */
	/** 3.6.3. 高血压随访记录 下载 */
	public static List<HypertensionFlup_down> getHypertensionFlup_down(
			String str) {
		HypertensionFlup_down hy = null;
		List<HypertensionFlup_down> hy_list = new ArrayList<HypertensionFlup_down>();
		Field f;
		String medicationList = "";
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("hypertensionFlup")) {
						hy = new HypertensionFlup_down();
						// ////system.out.println("新建了");
					} else if (name.equals("medicationList")) {
						medicationList = str.substring(
								str.indexOf("<medicationList>"),
								str.indexOf("</medicationList>") + 17);
						if (!medicationList.equals("")) {
							// medicationList=medicationList+"|";
						}

					} else if (name.equals("medication")) {

					} else if (name.equals("drugCode")) {
						// medicationList=medicationList+parser.nextText();
					} else if (name.equals("drugName")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugUsage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugUsageAdd")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugDosage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugDosageAdd")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (hy != null) {
						f = hy.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(hy, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("hypertensionFlup")) {
						hy_list.add(hy);
						hy = null;
					} else if (end.equals("medicationList")) {
						hy.setMedicationList(medicationList);
						medicationList = "";
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return hy_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <diabetesSpecial>
	 * <personId>居民个人ID</personId> <specialNo>糖尿病专项编号</specialNo>
	 * <name>姓名</name> <registerDate>建档日期</registerDate>
	 * <registerOrgCode>建档单位代码</registerOrgCode>
	 * <registerDoctorCode>建档人代码</registerDoctorCode>
	 * <registerDoctorName>建档人姓名</registerDoctorName>
	 * <diagnoseDate>确诊日期</diagnoseDate>
	 * <diagnoseOrgCode>确诊单位代码</diagnoseOrgCode>
	 * <diagnoseDoctorCode>确诊医生代码</diagnoseDoctorCode>
	 * <diagnoseDoctorName>确诊医生姓名</diagnoseDoctorName> <SBP>收缩压</SBP>
	 * <DBP>舒张压</DBP> <bloodPressureLevel>血压分级</bloodPressureLevel>
	 * <nextFlupDate>下次随访日期</nextFlupDate>
	 * <diabetesLevelCode>糖尿病管理分级代码</diabetesLevelCode>
	 * <caseType>病例种类</caseType> <ICDCode>ICD-10编码</ICDCode>
	 * <doseCode>是否服药</doseCode> <noMedicationCode>未服药原因</noMedicationCode>
	 * <drugCost>月服药费用</drugCost> <familyHistoryCode>糖尿病家族史</familyHistoryCode>
	 * <height>身高</height> <randomBloodGlucose>随机血糖值</randomBloodGlucose>
	 * <kidneyDiseaseYears>肾脏病变年数</kidneyDiseaseYears>
	 * <retinalDiseaseYears>视网膜病变年数</retinalDiseaseYears>
	 * <neuropathyYears>神经病变年数</neuropathyYears>
	 * <skinInfectionYears>皮肤感染年数</skinInfectionYears>
	 * <vascularDiseaseYears>血管病变年数</vascularDiseaseYears>
	 * <noComplYears>无并发症年数</noComplYears>
	 * <complicationDate>糖尿病并发症时间</complicationDate>
	 * <initialDisease>初次病种</initialDisease>
	 * <currentDisease>当前病种</currentDisease>
	 * <caseSourceCode>病例来源</caseSourceCode>
	 * <caseSourceOther>病例来源其他</caseSourceOther> </diabetesSpecial> ......
	 * </responseDatas>
	 */
	/** 3_7_2糖尿病专项档案下载 */
	public static List<DiabetesSpecial_down> getDiabetesSpecial_down(String str) {
		DiabetesSpecial_down dia = null;
		List<DiabetesSpecial_down> dia_list = new ArrayList<DiabetesSpecial_down>();
		Field f;
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("diabetesSpecial")) {
						dia = new DiabetesSpecial_down();
						// ////system.out.println("新建了");
					} else if (dia != null) {
						f = dia.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(dia, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("diabetesSpecial")) {
						dia_list.add(dia);
						dia = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return dia_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <diabetesFlup>
	 * <personId>居民个人ID</personId> <healthFileNumber>健康档案编号</healthFileNumber>
	 * <name>姓名</name> <flupDate>随访日期</flupDate> <flupMode>随访方式</flupMode>
	 * <symptomCodes>症状代码（多选）</symptomCodes> <symptomOther>症状（其他）</symptomOther>
	 * <SBP>收缩压</SBP> <DBP>舒张压</DBP> <weight>体重</weight>
	 * <weightTarget>体重-目标值</weightTarget> <bmi>体质指数</bmi> <bmi>体质指数-目标值</bmi>
	 * <dorsalisPedisPulseCode>足背动脉搏动</dorsalisPedisPulseCode>
	 * <signsOther>体征-其他</signsOther> <dailySmoking>日吸烟量</dailySmoking>
	 * <dailySmokingTarget>日吸烟量-目标值</dailySmokingTarget>
	 * <dailyDrinking>日饮酒量</dailyDrinking>
	 * <dailyDrinkingTarget>日饮酒量-目标值</dailyDrinkingTarget>
	 * <perWeekMovements>每周运动次数</perWeekMovements>
	 * <perWeekMovementsTarget>每周运动次数目标值</perWeekMovementsTarget>
	 * <perWeekTimes>每次运动时间</perWeekTimes>
	 * <perWeekTimesTarget>每次运动时间-目标值</perWeekTimesTarget>
	 * <stapleFood>主食</stapleFood> <stapleFoodTarget>主食-目标值</stapleFoodTarget>
	 * <psychologicalCode>心理调整代码</psychologicalCode>
	 * <complianceCode>遵医行为代码</complianceCode>
	 * <fastingBloodGlucose>空腹血糖</fastingBloodGlucose> <HbA1c>糖化血红蛋白</HbA1c>
	 * <aidCheckDate>辅助检查日期</aidCheckDate> <aidCheck>辅助检查（其他）</aidCheck>
	 * <doseCode>服药依从性代码</doseCode>
	 * <adverseReactionCode>药物不良反应代码</adverseReactionCode>
	 * <lowBloodSugarCode>低血糖反应</lowBloodSugarCode>
	 * <flupTypeCode>此次随访分类代码</flupTypeCode> <medicationList> <medication>
	 * <drugCode>药品代码</drugCode> <drugName>药品名称</drugName>
	 * <drugUsage>药品用法</drugUsage> <drugUsageAdd>药品用法补充</drugUsageAdd>
	 * <drugDosage>药品用量</drugDosage> <drugDosageAdd>药品用量补充</drugDosageAdd>
	 * </medication> ...... </medicationList>
	 * <insulinMedicationType>胰岛素用药种类</insulinMedicationType>
	 * <insulinMedicationRate>胰岛素用药频率</insulinMedicationRate>
	 * <insulinMedicationDose>胰岛素用药剂量</insulinMedicationDose>
	 * <referralReason>转诊原因</referralReason> <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <flupDoctorCode>随访医生代码</flupDoctorCode>
	 * <flupDoctorName>随访医生名称</flupDoctorName> <flupOrgCode>随访机构代码</flupOrgCode>
	 * <flupOrgName>随访机构名称</flupOrgName> <nextFlupDate>下次随访日期</nextFlupDate>
	 * </diabetesFlup> ...... </responseDatas>
	 */
	/** 3.7.3. 糖尿病 随访记录 下载 */
	public static List<DiabetesFlup_down> getDiabetesFlup_down(String str) {
		DiabetesFlup_down dia = null;
		List<DiabetesFlup_down> dia_list = new ArrayList<DiabetesFlup_down>();
		Field f;
		String medicationList = "";
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("medicationList")) {
						medicationList = str.substring(
								str.indexOf("<medicationList>"),
								str.indexOf("</medicationList>") + 17);
						if (!medicationList.equals("")) {
							// medicationList=medicationList+"|";
						}

					}

					else if (name.equals("medication")) {

					} else if (name.equals("drugCode")) {
						// medicationList=medicationList+parser.nextText();
					} else if (name.equals("drugName")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugUsage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugUsageAdd")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugDosage")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("drugDosageAdd")) {
						// medicationList=medicationList+"_"+parser.nextText();
					} else if (name.equals("diabetesFlup")) {
						dia = new DiabetesFlup_down();
						// ////system.out.println("新建了");
					} else if (dia != null) {
						f = dia.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(dia, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("diabetesFlup")) {
						dia_list.add(dia);
						dia = null;
					} else if (end.equals("medicationList")) {
						dia.setMedicationList(medicationList);
						medicationList = "";
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return dia_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="129" start="1" returnSize="129"> <person>
	 * <personId>居民个人ID</areaCode> <healthFileNumber>健康档案编号</healthFileNumber>
	 * <name>姓名</name> <genderCode>性别代码</genderCode> <birthday>出生日期</birthday>
	 * <idCard>身份证号</idCard> <businessPlan>
	 * <businessTypeName>业务分类名称</businessTypeName>
	 * <businessName>业务名称</businessName>
	 * <businessStartDate>计划开始日期</businessStartDate>
	 * <businessEndDate>计划截止日期</businessEndDate> <predictDate>预计业务办理日期</
	 * predictDate > </businessPlan> <businessPlan>
	 * <businessTypeName>业务分类名称</businessTypeName>
	 * <businessName>业务名称</businessName>
	 * <businessStartDate>计划开始日期</businessStartDate>
	 * <businessEndDate>计划截止日期</businessEndDate> <predictDate>预计业务办理日期</
	 * predictDate > </businessPlan> ...... </person> ...... </person>
	 * </responseDatas> ]
	 */
	/** 3.8.2. 业务服务提醒 */
	public static List<Person_down> getPerson_down(String str) {
		Person_down person = null;
		List<Person_down> person_list = new ArrayList<Person_down>();
		Field f;
		String businessPlan = "";
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("businessPlan")) {
						businessPlan = str.substring(
								str.indexOf("<businessPlan>"),
								str.indexOf("</businessPlan>") + 15);
						if (!businessPlan.equals("")) {
							// businessPlan=businessPlan+"|";
						}
					} else if (name.equals("businessTypeName")) {
						// businessPlan=businessPlan+parser.nextText();
					} else if (name.equals("businessName")) {
						// businessPlan=businessPlan+"_"+parser.nextText();
					} else if (name.equals("businessStartDate")) {
						// businessPlan=businessPlan+"_"+parser.nextText();
					} else if (name.equals("businessEndDate")) {
						// businessPlan=businessPlan+"_"+parser.nextText();
					} else if (name.equals("predictDate")) {
						// businessPlan=businessPlan+"_"+parser.nextText();
					}

					else if (name.equals("person")) {
						person = new Person_down();
						// ////system.out.println("新建了");
					} else if (person != null) {
						f = person.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(person, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					// ////system.out.println("结束付出"+end);
					if (end != null && end.equals("person")) {
						person.setBusinessPlan(businessPlan);
						businessPlan = "";
						person_list.add(person);
						person = null;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return person_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ////////////////////////////////////////////所有封装上传的字符串方法
	// 3.3.3. 健康体检记录上传
	public static String setHealthExamination(HealthExamination heal) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			// serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "healthExamination");
			f = heal.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(heal);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "healthExamination");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <pregnantFirstCheck> <personId>居民个人ID</personId>
	 * <specialNo>孕产妇专项编号</specialNo> <machineCode>健康一体机编码</machineCode>
	 * <machineNo>一体机业务序号</machineNo>
	 * <pregnantManualNo>孕产妇保健手册编号</pregnantManualNo> <name>孕妇姓名</name>
	 * <antenatalCareDate>产检日期</antenatalCareDate>
	 * <gestationalWeeks>孕周</gestationalWeeks> <age>孕妇年龄</age>
	 * <husbandName>丈夫姓名</husbandName> <husbandAge>丈夫年龄</husbandAge>
	 * <husbandPhone>丈夫电话</husbandPhone> <gravidityTimes>孕次</gravidityTimes>
	 * <deliveryTimes>产次</deliveryTimes>
	 * <vaginalDeliveryTimes>阴道分娩次数</vaginalDeliveryTimes>
	 * <caesareanSectionTimes>剖宫产次数</caesareanSectionTimes> <LMP>末次月经</LMP>
	 * <expectedBirthDate>预产期</expectedBirthDate>
	 * <pastHistoryCodes>既往史代码（多选）</pastHistoryCodes>
	 * <pastHistoryOther>既往史其他</pastHistoryOther>
	 * <familyHistoryCodes>家族史代码（多选）</familyHistoryCodes>
	 * <familyHistoryOther>家族史其他</familyHistoryOther>
	 * <personalHistoryCodes>个人史代码（多选）</personalHistoryCodes>
	 * <personalHistoryOther>个人史其他</personalHistoryOther>
	 * <surgeryHistoryCode>妇科手术史代码</surgeryHistoryCode>
	 * <surgeryHistoryOther>妇科手术史其他</surgeryHistoryOther>
	 * <abortionTimes>流产次数</abortionTimes>
	 * <stillbirthTimes>死胎次数</stillbirthTimes>
	 * <deadBirthTimes>死产次数</deadBirthTimes>
	 * <neonatalDeath>新生儿死亡人数</neonatalDeath>
	 * <birthDefects>出生缺陷儿人数</birthDefects> <height>身高</height>
	 * <weight>体重</weight> <bmi>体质指数</bmi> <SBP>收缩压</SBP> <DBP>舒张压</DBP>
	 * <cardiacAuscultationCode>心脏听诊代码</cardiacAuscultationCode>
	 * <cardiacAuscultationDesc>心脏听诊异常</cardiacAuscultationDesc>
	 * <lungAuscultationCode>肺部听诊代码</lungAuscultationCode>
	 * <lungAuscultationDesc>肺部听诊描述</lungAuscultationDesc>
	 * <vulvaCode>妇科-外阴代码</vulvaCode> <vulvaDesc>妇科-外阴描述</vulvaDesc>
	 * <vaginaCode>妇科-阴道代码</vaginaCode> <vaginaDesc>妇科-阴道描述</vaginaDesc>
	 * <cervixCode>妇科-宫颈代码</cervixCode> <cervixDesc>妇科-宫颈描述</cervixDesc>
	 * <uterusCode>妇科-宫体代码</uterusCode> <uterusDesc>妇科-宫体描述</uterusDesc>
	 * <uterineAccessoriesCode>妇科-附件代码</uterineAccessoriesCode>
	 * <uterineAccessoriesDesc>妇科-附件描述</uterineAccessoriesDesc>
	 * <hemoglobin>血红蛋白值</hemoglobin> <leucocyte>白细胞计数值</leucocyte>
	 * <platelet>血常规计数值</platelet> <bloodOther>血常规-其他</bloodOther>
	 * <urineProteinCode>尿常规-尿蛋白</urineProteinCode>
	 * <urineSugarCode>尿常规-尿糖</urineSugarCode>
	 * <urineKetoneCode>尿常规-尿酮体</urineKetoneCode>
	 * <urineOccultBloodCode>尿常规-尿潜血</urineOccultBloodCode>
	 * <urineOther>尿常规-其他</urineOther> <bloodGroupCode>血型代码</bloodGroupCode>
	 * <rhBloodGroupCode>RH阴性代码</rhBloodGroupCode>
	 * <bloodGlucose>血糖</bloodGlucose> <GPT>肝功能-血清谷丙转氨酶 </GPT> <GOT>肝功能-血清谷草转氨酶
	 * </GOT> <albumin>肝功能-白蛋白</albumin> <TBIL>肝功能-总胆红素</TBIL>
	 * <DBIL>肝功能-结合胆红素</DBIL> <serumCreatinine>肾功能-血清肌酐</serumCreatinine>
	 * <BUN>肾功能-血尿素氮</BUN> <vaginalFluidCodes>阴道分泌物代码（多选）</vaginalFluidCodes>
	 * <vaginalFluidOther>阴道分泌物其他</vaginalFluidOther>
	 * <vaginalCleanlinessCode>阴道清洁度代码</vaginalCleanlinessCode>
	 * <HBsAgCode>乙型肝炎表面抗原</HBsAgCode> <antiHBsCode>乙型肝炎表面抗体</antiHBsCode>
	 * <HBeAgCode>乙型肝炎e抗原</HBeAgCode> <HBeAbCode>乙型肝炎e抗体</HBeAbCode>
	 * <HBcAbCode>乙型肝炎核心抗体</HBcAbCode> <syphilisCode>梅毒血清学试验代码</syphilisCode>
	 * <HIVCode>HIV抗体检测代码</HIVCode> <BScan>B超</BScan>
	 * <overallAssessmentCode>总体评估代码</overallAssessmentCode>
	 * <overallAssessmentDesc>总体评估描述</overallAssessmentDesc>
	 * <healthGuideCodes>保健指导代码</healthGuideCodes>
	 * <healthGuideOther>保健指导其他</healthGuideOther>
	 * <referralCode>转诊代码</referralCode> <referralReason>转诊原因</referralReason>
	 * <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <checkDoctorCode>产检医生代码</checkDoctorCode>
	 * <checkDoctorName>产检医生名称</checkDoctorName>
	 * <checkOrgCode>产检机构代码</checkOrgCode> <checkOrgName>产检机构名称</checkOrgName>
	 * <nextFlupDate>下次随访日期</nextFlupDate> </pregnantFirstCheck>
	 * </requestParams>
	 */
	/** 3.4.3. 孕产妇初检记录上传 */
	public static String setPregnantFirstCheck_Upload(
			PregnantFirstCheck_upload pre) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "pregnantFirstCheck");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "pregnantFirstCheck");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * 
	 * @param 孕产妇专项档案解析
	 * @return
	 */
	public static List<PregnantSpecial_down> getPregnantSpecial_down(String str) {
		StringReader inStream = new StringReader(str);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(inStream);
			int eventType = parser.getEventType();
			List<PregnantSpecial_down> area_list = new ArrayList<PregnantSpecial_down>();
			PregnantSpecial_down area = null;
			Field f;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					// system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("pregnantSpecial")) {
						area = new PregnantSpecial_down();
						break;
					} else if (area != null && !name.equals("pregnantSpecial")) {
						f = area.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(area, parser.nextText());
					}
					// //system.out.println("name"+name);
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					String end = parser.getName();
					if (end.equals("pregnantSpecial")) {
						area_list.add(area);
						area = null;
						break;
					}
					break;
				}
				eventType = parser.next();
			}
			inStream.close();
			return area_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <pregnantRecheck> <personId>居民个人ID</personId>
	 * <specialNo>孕产妇专项编号</specialNo> <machineCode>健康一体机编码</machineCode>
	 * <machineNo>一体机业务序号</machineNo>
	 * <pregnantManualNo>孕产妇保健手册编号</pregnantManualNo> <name>孕妇姓名</name>
	 * <antenatalCareDate>产检日期</antenatalCareDate>
	 * <gestationalWeeks>孕周</gestationalWeeks>
	 * <chiefComplaint>主诉</chiefComplaint> <weight>体重</weight>
	 * <fundusHeight>宫底高度</fundusHeight>
	 * <abdominalCircumference>腹围</abdominalCircumference>
	 * <foetalCirculationCode>胎位</foetalCirculationCode>
	 * <fetalHeartRate>胎心率</fetalHeartRate> <SBP>收缩压</SBP> <DBP>舒张压</DBP>
	 * <hemoglobin>血红蛋白值</hemoglobin> <leucocyte>尿蛋白</leucocyte>
	 * <checkOther>其他辅助检查</checkOther> <typeCode>分类</typeCode>
	 * <typeDesc>分类描述</typeDesc> <guideCodes>指导代码</guideCodes>
	 * <guideOther>指导其他</guideOther> <referralCode>转诊代码</referralCode>
	 * <referralReason>转诊原因</referralReason> <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <checkDoctorCode>产检医生代码</checkDoctorCode>
	 * <checkDoctorName>产检医生名称</checkDoctorName>
	 * <checkOrgCode>产检机构代码</checkOrgCode> <checkOrgName>产检机构名称</checkOrgName>
	 * <nextFlupDate>下次随访日期</nextFlupDate> </pregnantRecheck> </requestParams>
	 */
	/** 3.4.4. 孕产妇复检 记录上传 */
	public static String setPregnantRecheck_Upload(PregnantRecheck_upload pre) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "pregnantRecheck");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "pregnantRecheck");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <postpartumVisit> <personId>居民个人ID</personId>
	 * <specialNo>孕产妇专项编号</specialNo> <machineCode>健康一体机编码</machineCode>
	 * <machineNo>一体机业务序号</machineNo>
	 * <pregnantManualNo>孕产妇保健手册编号</pregnantManualNo> <name>孕妇姓名</name>
	 * <flupDate>随访日期</flupDate> <temperature>体温</temperature>
	 * <healthDesc>一般健康情况</healthDesc>
	 * <psychologicStatus>一般心理状况</psychologicStatus> <SBP>收缩压</SBP>
	 * <DBP>舒张压</DBP> <breastCode>乳房代码</breastCode>
	 * <breastDesc>乳房描述</breastDesc> <lochiaCode>恶露代码</lochiaCode>
	 * <lochiaDesc>恶露描述</lochiaDesc> <uterusCode>子宫代码</uterusCode>
	 * <uterusDesc>子宫描述</uterusDesc> <woundCode>伤口代码</woundCode>
	 * <woundDesc>伤口描述</woundDesc> <other>其他</other> <typeCode>分类</typeCode>
	 * <typeDesc>分类描述</typeDesc> <guideCodes>指导代码</guideCodes>
	 * <guideOther>指导其他</guideOther> <referralCode>转诊代码</referralCode>
	 * <referralReason>转诊原因</referralReason> <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <visitDoctorCode>访视医生代码</visitDoctorCode>
	 * <visitDoctorName>访视医生名称</visitDoctorName>
	 * <visitOrgCode>访视机构代码</visitOrgCode> <visitOrgName>访视机构名称</visitOrgName>
	 * <nextFlupDate>下次随访日期</nextFlupDate> </postpartumVisit> </requestParams>
	 */
	/** 3.4.5. 孕产妇产后访视 记录上传 */
	public static String setPostpartumVisit_Upload(PostpartumVisit_upload pre) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "postpartumVisit");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "postpartumVisit");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <postpartum42Visit> <personId>居民个人ID</personId>
	 * <specialNo>孕产妇专项编号</specialNo> <machineCode>健康一体机编码</machineCode>
	 * <machineNo>一体机业务序号</machineNo>
	 * <pregnantManualNo>孕产妇保健手册编号</pregnantManualNo> <name>孕妇姓名</name>
	 * <flupDate>随访日期</flupDate> <healthDesc>一般健康情况</healthDesc>
	 * <psychologicStatus>一般心理状况</psychologicStatus> <SBP>收缩压</SBP>
	 * <DBP>舒张压</DBP> <breastCode>乳房代码</breastCode>
	 * <breastDesc>乳房描述</breastDesc> <lochiaCode>恶露代码</lochiaCode>
	 * <lochiaDesc>恶露描述</lochiaDesc> <uterusCode>子宫代码</uterusCode>
	 * <uterusDesc>子宫描述</uterusDesc> <woundCode>伤口代码</woundCode>
	 * <woundDesc>伤口描述</woundDesc> <other>其他</other> <typeCode>分类</typeCode>
	 * <typeDesc>分类描述</typeDesc> <guideCodes>指导代码</guideCodes>
	 * <guideOther>指导其他</guideOther> <processCode>处理代码</processCode>
	 * <referralReason>转诊原因</referralReason> <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <visitDoctorCode>访视医生代码</visitDoctorCode>
	 * <visitDoctorName>访视医生名称</visitDoctorName>
	 * <visitOrgCode>访视机构代码</visitOrgCode> <visitOrgName>访视机构名称</visitOrgName>
	 * </postpartum42Visit> </requestParams>
	 */
	/** 3.4.6.孕产妇产后 42天访视 记录上传 */
	public static String setPostpartum42Visit_Upload(
			Postpartum42Visit_upload pre) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "postpartum42Visit");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "postpartum42Visit");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <neonatalVisit> <personId>居民个人ID</personId> <specialNo>儿童专项编号</specialNo>
	 * <machineCode>健康一体机编码</machineCode> <machineNo>一体机业务序号</machineNo>
	 * <name>姓名</name> <genderCode>性别代码</genderCode> <birthday>出生日期</birthday>
	 * <idCard>身份证号</idCard> <homeAddress>家庭住址</homeAddress>
	 * <fatherName>父亲姓名</fatherName> <matherName>母亲姓名</matherName>
	 * <fatherOccupation>父亲职业</fatherOccupation>
	 * <matherOccupation>母亲职业</matherOccupation>
	 * <fatherContac>父亲联系电话</fatherContac> <matherContac>母亲联系电话</matherContac>
	 * <fatherBirthday>父亲出生日期</fatherBirthday>
	 * <matherBirthday>母亲出生日期</matherBirthday> <weekOfBirth>出生孕周</weekOfBirth>
	 * <pregnancyBeIllCode>母亲妊娠期患病情况代码</pregnancyBeIllCode>
	 * <pregnancyBeIllOther>母亲妊娠期患病情况其他</pregnancyBeIllOther>
	 * <midwiferyOrgName>助产机构名称</midwiferyOrgName>
	 * <birthConditionCodes>出生情况代码</birthConditionCodes>
	 * <neonatalAsphyxiaCode>新生儿窒息代码</neonatalAsphyxiaCode> <apgar1>Apgar
	 * 1分钟评分</apgar1> <apgar5>Apgar 5分钟评分</apgar5> <apgar10>Apgar
	 * 10分钟评分</apgar10> <deformityCode>是否有畸型</deformityCode>
	 * <deformityOther>畸型描述</deformityOther> <UNHSCode>新生儿听力筛查代码</UNHSCode>
	 * <neonatalScreeningCode>新生儿疾病筛查代码</neonatalScreeningCode>
	 * <neonatalScreeningOther>新生儿疾病筛查其他 </neonatalScreeningOther>
	 * <birthWeight>出生体重</birthWeight> <currentWeight>目前体重</currentWeight>
	 * <birthHeight>出生身长</birthHeight>
	 * <feedingPatternCode>喂养方式</feedingPatternCode>
	 * <feedingAmount>吃奶量</feedingAmount> <feedingTimes>吃奶次数</feedingTimes>
	 * <vomitCode>呕吐代码</vomitCode> <fecalCode>大便代码</fecalCode>
	 * <fecalTimes>大便次数</fecalTimes> <temperature>体温</temperature>
	 * <pulseRate>脉率</pulseRate> <respiratoryRate>呼吸频率</respiratoryRate>
	 * <complexionCode>面色代码</complexionCode>
	 * <complexionOther>面色其他</complexionOther>
	 * <jaundiceSiteCode>黄疸部位代码</jaundiceSiteCode> <bregma1>前囟1</bregma1>
	 * <bregma2>前囟2</bregma2> <bregmaCode>前囟代码</bregmaCode>
	 * <bregmaOther>前囟其他</bregmaOther>
	 * <eyeAppearanceCode>眼外观代码</eyeAppearanceCode>
	 * <eyeAppearanceDesc>眼外观异常</eyeAppearanceDesc>
	 * <limbsActivityCode>四肢活动度代码</limbsActivityCode>
	 * <limbsActivityDesc>四肢活动度异常</limbsActivityDesc>
	 * <earAppearanceCode>耳外观代码</earAppearanceCode>
	 * <earAppearanceDesc>耳外观异常</earAppearanceDesc>
	 * <neckMassCode>颈部包块代码</neckMassCode> <neckMassDesc>颈部包块描述</neckMassDesc>
	 * <noseCode>鼻代码</noseCode> <noseDesc>鼻异常</noseDesc>
	 * <skinCode>皮肤代码</skinCode> <skinOther>皮肤其他</skinOther>
	 * <oralCode>口腔代码</oralCode> <oralDesc>口腔异常</oralDesc>
	 * <anusCode>肛门代码</anusCode> <anusDesc>肛门异常</anusDesc>
	 * <heartLungAuscultationCode>心肺听诊代码 </heartLungAuscultationCode>
	 * <heartLungAuscultationDesc>心肺听诊异常 </heartLungAuscultationDesc>
	 * <externalGenitalCode>外生殖器代码</externalGenitalCode>
	 * <externalGenitalDesc>外生殖器异常</externalGenitalDesc>
	 * <abdominalPalpationCode>腹部触诊代码</abdominalPalpationCode>
	 * <abdominalPalpationDesc>腹部触诊异常</abdominalPalpationDesc>
	 * <spineCode>脊柱代码</spineCode> <spineDesc>脊柱异常</spineDesc>
	 * <umbilicalCordCode>脐带代码</umbilicalCordCode>
	 * <umbilicalCordOther>脐带其他</umbilicalCordOther>
	 * <referralCode>转诊建议</referralCode> <referralReason>转诊原因</referralReason>
	 * <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <guideCodes>指导代码（多选）</guideCodes> <flupDate>随访日期</flupDate>
	 * <flupDoctorCode>随访医生代码</flupDoctorCode>
	 * <flupDoctorName>随访医生名称</flupDoctorName> <flupOrgCode>随访机构代码</flupOrgCode>
	 * <flupOrgName>随访机构名称</flupOrgName> <nextFlupDate>下次随访日期</nextFlupDate>
	 * <nextFlupLocation>下次随访地点</nextFlupLocation> </neonatalVisit>
	 * </requestParams>
	 */
	/** 3.5.3. 新生儿家庭访视 记录上传 */
	public static String setNeonatalVisit_Upload(NeonatalVisit_upload pre) {
		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "neonatalVisit");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "neonatalVisit");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <hypertensionFlup> <personId>居民个人ID</personId>
	 * <specialNo>高血压专项编号</specialNo> <machineCode>健康一体机编码</machineCode>
	 * <machineNo>一体机业务序号</machineNo> <name>姓名</name> <flupDate>随访日期</flupDate>
	 * <flupMode>随访方式</flupMode> <symptomCodes>症状代码（多选）</symptomCodes>
	 * <symptomOther>症状（其他）</symptomOther> <SBP>血压-收缩压</SBP> <DBP>血压-舒张压</DBP>
	 * <weight>体重</weight> <weightTarget>体重-目标值</weightTarget> <bmi>体质指数</bmi>
	 * <bmiTarget>体质指数-目标值</bmiTarget> <heartRate>心率</heartRate>
	 * <signsOther>体征-其他</signsOther> <dailySmoking>日吸烟量</dailySmoking>
	 * <dailySmokingTarget>日吸烟量-目标值</dailySmokingTarget>
	 * <dailyDrinking>日饮酒量</dailyDrinking>
	 * <dailyDrinkingTarget>日饮酒量-目标值</dailyDrinkingTarget>
	 * <perWeekMovements>每周运动次数</perWeekMovements>
	 * <perWeekMovementsTarget>运动次数目标值</perWeekMovementsTarget>
	 * <perWeekTimes>每次运动时间</perWeekTimes>
	 * <perWeekTimesTarget>每次运动时间-目标值</perWeekTimesTarget>
	 * <saltIntakeCode>摄盐情况代码</saltIntakeCode>
	 * <saltIntakeTargetCode>摄盐情况目标值代码</saltIntakeTargetCode>
	 * <psychologicalCode>心理调整代码</psychologicalCode>
	 * <complianceCode>遵医行为代码</complianceCode <aidCheck>辅助检查</aidCheck>
	 * <doseCode>服药依从性代码</doseCode>
	 * <adverseReactionCode>药物不良反应代码</adverseReactionCode>
	 * <flupTypeCode>此次随访分类代码</flupTypeCode> <medicationList> <medication>
	 * <drugCode>药品代码</drugCode> <drugName>药品名称</drugName>
	 * <drugUsage>药品用法</drugUsage> <drugUsageAdd>药品用法补充</drugUsageAdd>
	 * <drugDosage>药品用量</drugDosage> <drugDosageAdd>药品用量补充</drugDosageAdd>
	 * </medication> ......
	 * 
	 * </medicationList> <referralReason>转诊原因</referralReason>
	 * <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <flupDoctorCode>随访医生代码</flupDoctorCode>
	 * <flupDoctorName>随访医生名称</flupDoctorName> <flupOrgCode>随访机构代码</flupOrgCode>
	 * <flupOrgName>随访机构名称</flupOrgName> <nextFlupDate>下次随访日期</nextFlupDate>
	 * </hypertensionFlup> </requestParams>
	 */
	/** 3.6.5. 高血压随访 记录上传 */
	public static String setHypertensionFlup_Upload(HypertensionFlup_upload pre) {

		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			// serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "hypertensionFlup");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "hypertensionFlup");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <diabetesFlup> <personId>居民个人ID</personId> <specialNo>糖尿病专项编号</specialNo>
	 * <machineCode>健康一体机编码</machineCode> <machineNo>一体机业务序号</machineNo>
	 * <name>姓名</name> <flupDate>随访日期</flupDate> <flupMode>随访方式</flupMode>
	 * <symptomCodes>症状代码（多选）</symptomCodes> <symptomOther>症状（其他）</symptomOther>
	 * <SBP>收缩压</SBP> <DBP>舒张压</DBP> <weight>体重</weight>
	 * <weightTarget>体重-目标值</weightTarget> <bmi>体质指数</bmi> <bmi>体质指数-目标值</bmi>
	 * <dorsalisPedisPulseCode>足背动脉搏动</dorsalisPedisPulseCode>
	 * <signsOther>体征-其他</signsOther> <dailySmoking>日吸烟量</dailySmoking>
	 * <dailySmokingTarget>日吸烟量-目标值</dailySmokingTarget>
	 * <dailyDrinking>日饮酒量</dailyDrinking>
	 * <dailyDrinkingTarget>日饮酒量-目标值</dailyDrinkingTarget>
	 * <perWeekMovements>每周运动次数</perWeekMovements>
	 * <perWeekMovementsTarget>每周运动次数目标值 </perWeekMovementsTarget>
	 * <perWeekTimes>每次运动时间</perWeekTimes>
	 * <perWeekTimesTarget>每次运动时间-目标值</perWeekTimesTarget>
	 * <stapleFood>主食</stapleFood> <stapleFoodTarget>主食-目标值</stapleFoodTarget>
	 * <psychologicalCode>心理调整代码</psychologicalCode>
	 * <complianceCode>遵医行为代码</complianceCode>
	 * <fastingBloodGlucose>空腹血糖</fastingBloodGlucose> <HbA1c>糖化血红蛋白</HbA1c>
	 * <aidCheckDate>辅助检查日期</aidCheckDate> <aidCheck>辅助检查（其他）</aidCheck>
	 * <doseCode>服药依从性代码</doseCode>
	 * <adverseReactionCode>药物不良反应代码</adverseReactionCode>
	 * <lowBloodSugarCode>低血糖反应</lowBloodSugarCode>
	 * <flupTypeCode>此次随访分类代码</flupTypeCode> <medicationList> <medication>
	 * <drugCode>药品代码</drugCode> <drugName>药品名称</drugName>
	 * <drugUsage>药品用法</drugUsage> <drugUsageAdd>药品用法补充</drugUsageAdd>
	 * <drugDosage>药品用量</drugDosage> <drugDosageAdd>药品用量补充</drugDosageAdd>
	 * </medication> ...... </medicationList>
	 * <insulinMedicationType>胰岛素用药种类</insulinMedicationType>
	 * <insulinMedicationRate>胰岛素用药频率</insulinMedicationRate>
	 * <insulinMedicationDose>胰岛素用药剂量</insulinMedicationDose>
	 * <referralReason>转诊原因</referralReason> <referralOrg>转诊机构</referralOrg>
	 * <referralDepartment>转诊科室</referralDepartment>
	 * <flupDoctorCode>随访医生代码</flupDoctorCode>
	 * <flupDoctorName>随访医生名称</flupDoctorName> <flupOrgCode>随访机构代码</flupOrgCode>
	 * <flupOrgName>随访机构名称</flupOrgName> <nextFlupDate>下次随访日期</nextFlupDate>
	 * </diabetesFlup> </requestParams>
	 */
	/** 3.7.4. 糖尿病随访 记录上传 */

	public static String setDiabetesFlup_Upload(DiabetesFlup_upload pre) {

		Field[] f;
		String name = "";
		String txt = "";
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			// serializer.startDocument("UTF-8", null);
			serializer.startTag(null, "requestParams");
			serializer.startTag(null, "diabetesFlup");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}
				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			serializer.endTag(null, "diabetesFlup");
			serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
			return read.toString();
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
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <requestParams>
	 * <healthRecord> <updateType>1</personId><!—新增健康档案 -->
	 * <machineCode>健康一体机编码</machineCode> <machineNo>一体机业务序号</machineNo>
	 * <nowLiveCode>500237026011001</nowLiveCode>
	 * <nowLiveName>巫山县三溪乡白鹤村1组</nowLiveName> <nowLiveAddr>35号</nowLiveAddr>
	 * <householdRegisterCode>500237003008004</householdRegisterCode>
	 * <householdRegisterName>巫山县抱龙镇桃花村4组</householdRegisterName>
	 * <householdRegisterAddr>109号</householdRegisterAddr>
	 * <registerOrgCode>5002370801</registerOrgCode>
	 * <registerOrgName>抱龙中心卫生院</registerOrgName>
	 * <registerDoctorCode>030002</registerDoctorCode>
	 * <registerDoctorName>陈XX</registerDoctorName>
	 * <responsibleDoctorCode>5002370100033</responsibleDoctorCode>
	 * <responsibleDoctorName>曾XX</responsibleDoctorName>
	 * <registerDate>业务办理日期</registerDate>
	 * <healthFileNumber>5002370061300134</healthFileNumber> <name>张三</name>
	 * <genderCode>1</genderCode> <birthday>19995-07-01</birthday>
	 * <idCard>50023719850907727X</idCard> <workUnit>无</workUnit>
	 * <phone>18719121314</birthday> <contacts>张三丰</contacts>
	 * <contactsPhone>18757398266</contactsPhone>
	 * <residentType>...</residentType> <ethnicityCode>...</ethnicityCode>
	 * <ethnicityName>...</ethnicityName> <bloodGroupCode>...</bloodGroupCode>
	 * <rhBloodGroupCode>...</rhBloodGroupCode> <eduBGCode>...</eduBGCode>
	 * <occupationCode>...</occupationCode>
	 * <maritalStatusCode>...</maritalStatusCode>
	 * <paymentMethodCodes>...</paymentMethodCodes>
	 * <paymentMethodOther>...</paymentMethodOther>
	 * <drugAllergyHistoryCodes>...</drugAllergyHistoryCodes>
	 * <drugAllergyHistoryOther>...</drugAllergyHistoryOther>
	 * <exposureHistoryCodes>...</exposureHistoryCodes>
	 * <familyHistoryFatherCodes>...</familyHistoryFatherCodes>
	 * <familyHistoryFatherOther>...</familyHistoryFatherOther>
	 * <familyHistoryMatherCodes>...</familyHistoryMatherCodes>
	 * <familyHistoryMatherOther>...</familyHistoryMatherOther>
	 * <brotherAndSisterCodes>...</brotherAndSisterCodes>
	 * <brotherAndSisterOther>...</brotherAndSisterOther>
	 * <familyHistoryChildrenCodes>...</familyHistoryChildrenCodes>
	 * <familyHistoryChildrenOther>...</familyHistoryChildrenOther>
	 * <geneticHistoryCode>...</geneticHistoryCode>
	 * <geneticHistoryOther>...</geneticHistoryOther>
	 * <disabilityCodes>...</disabilityCodes>
	 * <disabilityOther>...</disabilityOther>
	 * <kitchenExhaustCode>...</kitchenExhaustCode>
	 * <fuelTypeCode>...</fuelTypeCode> <waterCode>...</waterCode>
	 * <toiletCode>...</toiletCode>
	 * <livestockColumnCode>...</livestockColumnCode> <pastHistoryList>
	 * <pastHistory> <pastHistoryType>1</pastHistoryType>
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
	 * </healthRecord> <healthRecord> </requestParams>
	 */
	/** 3.2.3-4 新增健康档案与 修改健康档案 */
	public static String setHealthRecord_Upload(HealthRecord_down pre) {
		Field[] f;
		String name = "";
		String txt = "";
/*		XmlSerializer serializer = Xml.newSerializer();
		StringWriter read = new StringWriter();
		try {
			serializer.setOutput(read);
			// serializer.startDocument("UTF-8", null);
			// serializer.startTag(null, "requestParams");
			// serializer.startTag(null, "healthRecord");
			f = pre.getClass().getDeclaredFields();
			for (Field field : f) {
				name = field.getName();
				if (name.equals("id")) {
					continue;
				}


				serializer.startTag(null, name);
				field.setAccessible(true);
				try {
					txt = (String) field.get(pre);
					
					if (null != txt) {
						serializer.text(txt);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serializer.endTag(null, name);
			}
			// serializer.endTag(null, "healthRecord");
			// serializer.endTag(null, "requestParams");
			serializer.endDocument();
			serializer.flush();
//			Log.e("pastHistoryList",read.toString());
			return read.toString();
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
*/		
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
		Log.e("pastHistoryList",str);
		return str;
	}
	/** 3.2.3-4 新增血压测量记录*/
	public static String setMeasurementXueYa_Upload(LocalMeasurementXueYa pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
	//	Log.e("pastHistoryList",str);
		return str;
	}
	/** 3.2.3-4 新增血氧测量记录*/
	public static String setMeasurementXueYang_Upload(LocalMeasurementXueYang pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
	//	Log.e("pastHistoryList",str);
		return str;
	}
	/** 3.2.3-4 新增血糖测量记录*/
	public static String setMeasurementXueTang_Upload(LocalMeasurementXueTang pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
		Log.e("pastHistoryList",str);
		return str;
	}
	/** 3.2.3-4 新增额温测量记录*/
	public static String setMeasurementEWen_Upload(LocalMeasurementEWen pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
		Log.e("pastHistoryList",str);
		return str;
	}
	/** 3.2.3-4 新增尿液测量记录*/
	public static String setMeasurementNiaoYe_Upload(LocalMeasurementNiaoYe pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
		Log.e("pastHistoryList",str);
		return str;
	}
	/** 3.2.3-4 新增心电测量记录*/
	public static String setMeasurementXinDian_Upload(LocalMeasurementXinDian pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
		Log.e("pastHistoryList",str);
		return str;
	}
	
	/** 3.2.3-4 新增白细胞测量记录*/
	public static String setMeasurementBaiXiBao_Upload(LocalMeasurementBaiXiBao pre) {
		Field[] f;
		String name = "";
		String txt = "";
		String str="";
		f = pre.getClass().getDeclaredFields();
		for (Field field : f) {
			name = field.getName();
			if (name.equals("id")) {
				continue;
			}
			str=str+"<"+name+">";
			field.setAccessible(true);
			try {
				txt = (String) field.get(pre);
				str += txt;
				}
		    catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str=str+"</"+name+">";
		}
		Log.e("pastHistoryList",str);
		return str;
	}
	
	
	
	
	
	public static String printHexString(byte[] b) {
		StringBuffer returnValue = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			if (i % 2 == 0) {
				returnValue.append(hex.toUpperCase() + "");
			} else {
				returnValue.append(hex.toUpperCase() + " ");
			}
		}

		return returnValue.toString();
	}
}
