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
	 * <typeCode>�ֵ����ʹ���</orgCode> <typeName>�ֵ���������</typeName>
	 * <typeOptionCode>�ֵ�����ѡ�����</typeOptionCode> <items> <item>
	 * <itemCode>�ֵ������</itemCode> <itemName>�ֵ�������</itemName>
	 * <itemOrder>�ֵ�������</itemOrder> <exclusiveCode>�ֵ�����������</exclusiveCode>
	 * <inputCode>�ֵ�����������</inputCode> </item> </items> </dictionaryType>
	 * <dictionaryType> <typeCode>�ֵ����ʹ���</orgCode> <typeName>�ֵ���������</typeName>
	 * <typeOptionCode>�ֵ�����ѡ�����</typeOptionCode> <items> <item>
	 * <itemCode>�ֵ������</itemCode> <itemName>�ֵ�������</itemName>
	 * <itemOrder>�ֵ�������</itemOrder> <exclusiveCode>�ֵ�����������</exclusiveCode>
	 * <inputCode>�ֵ�����������</inputCode> </item> </items> </dictionaryType>
	 * <dictionaryType> <typeCode>�ֵ����ʹ���</orgCode> <typeName>�ֵ���������</typeName>
	 * <typeOptionCode>�ֵ�����ѡ�����</typeOptionCode> <items> <item>
	 * <itemCode>�ֵ������</itemCode> <itemName>�ֵ�������</itemName>
	 * <itemOrder>�ֵ�������</itemOrder> <exclusiveCode>�ֵ�����������</exclusiveCode>
	 * <inputCode>�ֵ�����������</inputCode> </item> </items> </dictionaryType>
	 * </responseDatas>
	 */
	// �Ѿ�ͨ������
	/**
	 * 3.1.2. ��׼�����ֵ�
	 * 
	 * @param str
	 *            xml�ַ���
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
	 * <orgName>��ƽ��</orgName> <orgType>20</orgType> </org> <org>
	 * <orgCode>500228002</orgCode> <orgName>�����</orgName> <orgType>21</orgType>
	 * <parentorgCode>500228</parentorgCode> </org> <org>
	 * <orgCode>500228002001</orgCode> <orgName>�����</orgName>
	 * <orgType>22</orgType> <parentorgCode>500228002</parentorgCode> </org>
	 * <org> <orgCode>500228002001001</orgCode> <orgName>1��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001002</orgCode> <orgName>2��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001003</orgCode> <orgName>3��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001004</orgCode> <orgName>4��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001005</orgCode> <orgName>5��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001006</orgCode> <orgName>6��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001007</orgCode> <orgName>7��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001008</orgCode> <orgName>8��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * <org> <orgCode>500228002001009</orgCode> <orgName>9��</orgName>
	 * <orgType>23</orgType> <parentorgCode>500228002001</parentorgCode> </org>
	 * </responseDatas>
	 */
	// ��ͨ������
	/** �������� ���� 3.1.3 */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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

	/** 3.1.4. ҽ�ƻ��� ���� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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

	// 3.1.5 ���� һ���ע�᲻��Ҫ��ô����
	/**
	 * [ <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <responseDatas
	 * total="354" start="1" returnSize="354"> <user>
	 * <userCode>ҽ�ƻ����û�����</userCode> <userName>ҽ�ƻ����û�����</vName>
	 * <pym>�û�����ƴ����</pym> <wbm>�û����������</wbm> </user> <user>
	 * <userCode>ҽ�ƻ����û�����</userCode> <userName>ҽ�ƻ����û�����</vName>
	 * <pym>�û�����ƴ����</pym> <wbm>�û����������</wbm> </user> <user>
	 * <userCode>ҽ�ƻ����û�����</userCode> <userName>ҽ�ƻ����û�����</vName>
	 * <pym>�û�����ƴ����</pym> <wbm>�û����������</wbm> </user> </responseDatas>
	 */
	/** 3.1.6. ҽ�ƻ����û� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
	 * <nowLiveName>��ɽ����Ϫ��׺״�1��</nowLiveName> <nowLiveAddr>35��</nowLiveAddr>
	 * <householdRegisterCode>500237003008004</householdRegisterCode>
	 * <householdRegisterName>��ɽ�ر������һ���4��</householdRegisterName>
	 * <householdRegisterAddr>109��</householdRegisterAddr>
	 * <registerOrgCode>5002370801</registerOrgCode>
	 * <registerOrgName>������������Ժ</registerOrgName>
	 * <registerDoctorCode>030002</registerDoctorCode>
	 * <registerDoctorName>��XX</registerDoctorName>
	 * <responsibleDoctorCode>5002370100033</responsibleDoctorCode>
	 * <responsibleDoctorName>��XX</responsibleDoctorName>
	 * <registerDate>ҵ���������</registerDate>
	 * <healthFileNumber>5002370061300134</healthFileNumber> <name>����</name>
	 * <genderCode>1</genderCode> <birthday>19995-07-01</birthday>
	 * <idCard>50023719850907727X</idCard> <workUnit>��</workUnit>
	 * <phone>18719121314</birthday> <contacts>������</contacts>
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
	 * <pastHistoryCode>6</pastHistoryCode> <pastHistoryAdd>�ΰ�</pastHistoryAdd>
	 * <pastHistoryDate>2011-07-03</pastHistoryDate> </pastHistory>
	 * <pastHistory> <pastHistoryType>2</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> <pastHistory>
	 * <pastHistoryType>3</pastHistoryType> <pastHistoryCode>1</pastHistoryCode>
	 * </pastHistory> <pastHistory> <pastHistoryType>4</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> </pastHistoryList>
	 * </healthRecord> ...... </responseDatas>
	 */

	/** 3.2.2. �������� ���� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
								str.indexOf("<pastHistoryList>"),                //�����кܴ����� 
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					// ////system.out.println("name"+name);
					if (name.equals("pastHistoryList")) {

					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
	 * <healthRecord> <updateType>3</personId><!-- �޸Ľ�������״̬ -->
	 * <personId>4564756856</personId> <machineNo>һ���ҵ�����</machineNo>
	 * <name>����</name> <recordResultCode>0</recordResultCode>
	 * <recordResultDesc>��¼�����ɹ�</recordResultDesc> </healthRecord>
	 * </responseDatas>
	 */
	/**
	 * 3.2.3.7. �������� ���� ������Ӧ��Ϣ,3.2.3.8. �޸Ľ��� ���� ������Ӧ��Ϣ,3.2.3.9. �޸Ľ��� ���� ״̬
	 * ������Ӧ��Ϣ
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
	 * <healthExamination> <personId>�������ID</personId> <machineExaminationNo> һ
	 * �� �� ҵ �� �� �� </machineExaminationNo> <name>����</name>
	 * <examinationDate>�������</examinationDate> <orgCode>����������</orgCode>
	 * <operatorCode>����Ա����</operatorCode> <operatorName>����Ա����</operatorName>
	 * <operateTime>����ʱ��</operateTime> <responsibleDoctorCode> �� �� ҽ �� �� ��
	 * </responsibleDoctorCode> <responsibleDoctorName> �� �� ҽ �� �� ��
	 * </responsibleDoctorName> <symptomCodes>֢״���루��ѡ��</symptomCodes>
	 * <symptomOther>֢״-����</symptomOther> <temperature>����</temperature>
	 * <pulseRate>����</pulseRate> <respiratoryRate>����Ƶ��</respiratoryRate>
	 * <height>���</height> <weight>����</weight> <bmi>����ָ��</bmi>
	 * <waistline>��Χ</waistline> <leftSBP>���Ѫѹ-����ѹ</leftSBP>
	 * <leftDBP>���Ѫѹ-����ѹ</leftDBP> <rightSBP>�Ҳ�Ѫѹ-����ѹ</rightSBP>
	 * <rightDBP>�Ҳ�Ѫѹ-����ѹ</rightDBP> <theAgedStatus> �� �� �� �� �� ״ ̬ �� �� �� �� �� ��
	 * </theAgedStatus> <selfCareAbilityCode> �� �� �� �� �� �� �� �� �� �� �� �� ��
	 * </selfCareAbilityCode> <cognitiveFunction>��������֪����</cognitiveFunction>
	 * <cognitiveFunctionScore> �� �� �� �� ֪ �� �� �� �� </cognitiveFunctionScore>
	 * <emotionalState>���������״̬</emotionalState> <emotionalStateScore> �� �� �� �� ��
	 * ״ ̬ �� �� </emotionalStateScore> <exerciseFrequencyCode> �� �� �� �� Ƶ �� �� ��
	 * </exerciseFrequencyCode> <everyWorkoutTime>ÿ�ζ���ʱ��</everyWorkoutTime>
	 * <insistOnExerciseTime> �� �� �� �� ʱ �� </insistOnExerciseTime>
	 * <exerciseMode>������ʽ</exerciseMode>
	 * <eatingHabitsCodes>��ʳϰ�ߣ���ѡ��</eatingHabitsCodes>
	 * <smokingStatusCode>����״������</smokingStatusCode>
	 * <dailySmoking>��������</dailySmoking> <smokingAge>��ʼ��������</smokingAge>
	 * <smokingCessationAge>��������</smokingCessationAge> <drinkingFrequencyCode> ��
	 * �� Ƶ �� �� �� </drinkingFrequencyCode> <dailyDrinking>��������</dailyDrinking>
	 * <temperanceCode>�Ƿ���</temperanceCode> <temperanceAge>�������</temperanceAge>
	 * <drinkingAge>��ʼ��������</drinkingAge> <whetherDrunk>��һ���Ƿ����</whetherDrunk>
	 * <alcoholTypeCodes>����������루��ѡ��</alcoholTypeCodes>
	 * <alcoholTypeOther>��������-����</alcoholTypeOther> <exposureStateCode> �� �� �� ְ
	 * ҵ �� ¶ �� �� </exposureStateCode> <hazardousWork>Σ������</hazardousWork>
	 * <workingTime>��ҵʱ��</workingTime> <dust>��������-�۳�</dust>
	 * <dustProtective>�۳�-������ʩ</dustProtective> <dustProtectiveDesc> �� �� �� �� �� ʩ
	 * �� �� </dustProtectiveDesc> <radiogen>��������-��������</radiogen>
	 * <radiogenProtective> �� �� �� �� - �� �� �� ʩ </radiogenProtective>
	 * <radiogenProtectiveDesc> �� �� �� �� �� �� �� ʩ �� �� </radiogenProtectiveDesc>
	 * <physical>��������-��������</physical> <physicalProtective> �� �� �� �� - �� �� �� ʩ
	 * </physicalProtective> <physicalProtectiveDesc> �� �� �� �� �� �� �� ʩ �� ��
	 * </physicalProtectiveDesc>
	 * 
	 * <chemistry>��������-��ѧ����</chemistry> <chemistryProtective> �� ѧ �� �� - �� �� �� ʩ
	 * </chemistryProtective> <chemistryProtectiveDesc> �� ѧ �� �� �� ʩ �� ��
	 * </chemistryProtectiveDesc> <otherToxicant>��������-����</otherToxicant>
	 * <otherProtective>����-������ʩ</otherProtective> <otherProtectiveDesc> �� �� �� ��
	 * �� ʩ �� �� </otherProtectiveDesc> <lipsCode>�ڴ�����</lipsCode>
	 * <dentitionCodes>���У���ѡ��</dentitionCodes>
	 * <missingTeethLeftUp>ȱ������</missingTeethLeftUp>
	 * <missingTeethRightUp>ȱ������</missingTeethRightUp>
	 * <missingTeethLeftDown>ȱ������</missingTeethLeftDown>
	 * <missingTeethRightDown>ȱ������</missingTeethRightDown>
	 * <cariesLeftUp>ȣ������</cariesLeftUp> <cariesRightUp>ȣ������</cariesRightUp>
	 * <cariesLeftDown>ȣ������</cariesLeftDown>
	 * <cariesRightDown>ȣ������</cariesRightDown>
	 * <dentureLeftUp>�ٳ�����</dentureLeftUp> <dentureRightUp>�ٳ�����</dentureRightUp>
	 * <dentureLeftDown>�ٳ�����</dentureLeftDown>
	 * <dentureRightDown>�ٳ�����</dentureRightDown> <throatCode>�ʲ�����</throatCode>
	 * <visionRight>��������</visionRight> <visionLeft>��������</visionLeft>
	 * <redressVisionRight>���۽�������</redressVisionRight>
	 * <redressVisionLeft>���۽�������</redressVisionLeft>
	 * <hearingCode>��������</hearingCode>
	 * <motorFunctionCode>�˶����ܴ���</motorFunctionCode>
	 * <fundusCode>�۵״���</fundusCode> <fundusAbnormal>�۵��쳣���</fundusAbnormal>
	 * <skinCode>Ƥ������</skinCode> <skinOther>Ƥ����������</skinOther>
	 * <scleraCode>��Ĥ����</scleraCode> <scleraOther>��Ĥ��������</scleraOther>
	 * <lymphNodeCode>�ܰͽ����</lymphNodeCode>
	 * <lymphNodeOther>�ܰͽᣨ������</lymphNodeOther>
	 * <barrelChestCode>��-Ͱ״��</barrelChestCode>
	 * <breathSoundCode>��-����������</breathSoundCode>
	 * <breathSoundOther>��-���������쳣��</breathSoundOther>
	 * <raleCode>��-��������</raleCode> <raleOther>��-������������</raleOther>
	 * <heartRate>����-����</heartRate> <rhythmCode>����-�������</rhythmCode>
	 * <cardiacSouffleCode>����-����</cardiacSouffleCode> <cardiacSouffleDesc>����-����
	 * �������� </cardiacSouffleDesc>
	 * <abdomenTendernessCode>����-ѹʹ</abdomenTendernessCode>
	 * <abdomenTendernessDesc> �� �� - ѹ ʹ �� �� �� �� </abdomenTendernessDesc>
	 * <abdomenMassCode>����-����</abdomenMassCode>
	 * <abdomenMassDesc>����-���飨������</abdomenMassDesc>
	 * <abdomenLiverCode>����-�δ�</abdomenLiverCode>
	 * <abdomenLiverDesc>����-�δ�������</abdomenLiverDesc> <abdomenSplenomegalyCode>
	 * �� �� - Ƣ �� </abdomenSplenomegalyCode> <abdomenSplenomegalyDesc> �� �� - Ƣ ��
	 * �� �� �� �� </abdomenSplenomegalyDesc> <abdomenShiftingDullnessCode> �� �� �� ��
	 * �� </abdomenShiftingDullnessCode> <abdomenShiftingDullnessDesc> �� �� �� �� ��
	 * �� </abdomenShiftingDullnessDesc> <legEdemaCode>��֫ˮ��</legEdemaCode>
	 * <dorsalisPedisPulseCode> �� �� �� �� �� �� </dorsalisPedisPulseCode>
	 * <analFingerCodes>����ָ����루��ѡ��</analFingerCodes>
	 * <analFingerDesc>����ָ������</analFingerDesc>
	 * <breastCodes>���ٴ��루��ѡ��</breastCodes> <breastDesc>��������</breastDesc>
	 * <vulvaCode>����-��������</vulvaCode> <vulvaDesc>����-��������</vulvaDesc>
	 * <vaginaCode>����-��������</vaginaCode> <vaginaDesc>����-��������</vaginaDesc>
	 * <cervixCode>����-��������</cervixCode> <cervixDesc>����-��������</cervixDesc>
	 * <uterusCode>����-�������</uterusCode> <uterusDesc>����-��������</uterusDesc>
	 * <uterineAccessoriesCode> �� �� - �� �� �� �� </uterineAccessoriesCode>
	 * <uterineAccessoriesDesc> �� �� - �� �� �� �� </uterineAccessoriesDesc>
	 * <examinationOther>����-����</examinationOther>
	 * <hemoglobin>Ѫ����-Ѫ�쵰��</hemoglobin> <leucocyte>Ѫ����-��ϸ��</leucocyte>
	 * <platelet>Ѫ����-ѪС��</platelet> <bloodOther>Ѫ����-����</bloodOther>
	 * <urineProteinCode>�򳣹�-�򵰰�</urineProteinCode>
	 * <urineSugarCode>�򳣹�-����</urineSugarCode>
	 * <urineKetoneCode>�򳣹�-��ͪ��</urineKetoneCode> <urineOccultBloodCode> �� �� �� -
	 * �� Ǳ Ѫ </urineOccultBloodCode> <urineOther>�򳣹�-����</urineOther>
	 * <fastingPlasmaGlucose1>�ո�Ѫ��</fastingPlasmaGlucose1>
	 * <fastingPlasmaGlucose2>�ո�Ѫ��</fastingPlasmaGlucose2>
	 * <ECGCode>�ĵ�ͼ����</ECGCode> <ECGDesc>�ĵ�ͼ��������</ECGDesc>
	 * <ECGData>�ĵ�ͼ�����ݣ�</ECGData> <urineTraceAlbuminCode> �� ΢ �� �� �� ��
	 * </urineTraceAlbuminCode>
	 * <fecalOccultBloodCode>���ǱѪ</fecalOccultBloodCode>
	 * <glycatedHemoglobin>�ǻ�Ѫ�쵰��</glycatedHemoglobin> <HBsAg>���͸��ױ��濹ԭ</HBsAg>
	 * <GPT>�ι���-Ѫ��ȱ�ת��ø </GPT> <GOT>�ι���-Ѫ��Ȳ�ת��ø </GOT>
	 * <albumin>�ι���-�׵���</albumin> <TBIL>�ι���-�ܵ�����</TBIL> <DBIL>�ι���-��ϵ�����</DBIL>
	 * <serumCreatinine>������-Ѫ�弡��</serumCreatinine> <BUN>������-Ѫ���ص�</BUN>
	 * <serumPotassiumConcentration> �� Ѫ �� Ũ �� </serumPotassiumConcentration>
	 * <serumSodiumConcentration> �� �� �� - Ѫ �� Ũ �� </serumSodiumConcentration>
	 * <TCHO>Ѫ֬-Ѫ֬�ܵ��̴�</TCHO> <triglyceride>Ѫ֬-������֬</triglyceride>
	 * <LDL>Ѫ֬-Ѫ����ܶ�ֵ���׵��̴�</LDL> <HDL>Ѫ֬-Ѫ����ܶ�ֵ���׵��̴�</HDL>
	 * <chestXRayCode>�ز�X��Ƭ����</chestXRayCode>
	 * <chestXRayDesc>�ز�X��Ƭ��������</chestXRayDesc> <BScanCode>B������</BScanCode>
	 * <BScanDesc>B����������</BScanDesc> <papSmearCode>����ͿƬ����</papSmearCode>
	 * <papSmearDesc>����ͿƬ��������</papSmearDesc> <checkOther>�������-����</checkOther>
	 * <flatAndQualityCode>��ҽƽ���ʴ���</flatAndQualityCode>
	 * <qiDeficiencyCode>��ҽ�����ʴ���</qiDeficiencyCode>
	 * <yangXuzhiCode>��ҽ�����ʴ���</yangXuzhiCode>
	 * <yinDeficiencyCode>��ҽ�����ʴ���</yinDeficiencyCode>
	 * <phlegmDampnessQualityCode> �� ҽ ̵ ʪ �� �� �� </phlegmDampnessQualityCode>
	 * <hotAndHumidQualityCode> �� ҽ ʪ �� �� �� �� </hotAndHumidQualityCode>
	 * <bloodStasisCode>��ҽѪ���ʴ���</bloodStasisCode>
	 * <qiStagnationCode>��ҽ�����ʴ���</qiStagnationCode>
	 * <specialQualityCode>��ҽ�ر��ʴ���</specialQualityCode> <cerebrovascularCodes> ��
	 * Ѫ �� �� �� �� �� �� �� ѡ �� </cerebrovascularCodes> <cerebrovascularDesc> �� Ѫ �� ��
	 * �� �� �� �� �� </cerebrovascularDesc> <kidneyDiseaseCodes> �� �� �� �� �� �� �� �� ѡ ��
	 * </kidneyDiseaseCodes> <kidneyDiseaseDesc>���༲�������䣩</kidneyDiseaseDesc>
	 * <heartDiseaseCodes> �� �� �� �� �� �� �� �� ѡ �� </heartDiseaseCodes>
	 * <heartDiseaseDesc>���༲�����������䣩</heartDiseaseDesc> <vascularDiseaseCodes> Ѫ
	 * �� �� �� �� �� �� �� ѡ �� </vascularDiseaseCodes> <vascularDiseaseDesc> Ѫ �� �� �� ��
	 * �� �� �� </vascularDiseaseDesc>
	 * <eyeDiseaseCodes>�۲��������루��ѡ��</eyeDiseaseCodes>
	 * <eyeDiseaseDesc>�۲����������䣩</eyeDiseaseDesc> <nerve//SystemDiseaseCode> �� ��
	 * ϵ ͳ �� �� �� �� </nerve//SystemDiseaseCode> <nerve//SystemDiseaseDesc> �� �� ϵ
	 * ͳ �� �� �� �� �� �� </nerve//SystemDiseaseDesc> <other//SystemDiseaseCode> �� ��
	 * ϵ ͳ �� �� �� �� </other//SystemDiseaseCode> <other//SystemDiseaseDesc> �� �� ϵ
	 * ͳ �� �� �� �� �� �� </other//SystemDiseaseDesc>
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
	 * </vaccinationHistory> <healthEvaluationCode>��������</healthEvaluationCode>
	 * <diseaseName1>�������ۼ�������1</diseaseName1>
	 * <diseaseName2>�������ۼ�������2</diseaseName2>
	 * <diseaseName3>�������ۼ�������3</diseaseName3>
	 * <diseaseName4>�������ۼ�������4</diseaseName4> <healthGuidanceCodes> �� �� ָ �� �� ��
	 * �� �� ѡ �� </healthGuidanceCodes>
	 * <healthGuidanceDesc>����ָ�������䣩</healthGuidanceDesc> <riskFactorsCodes> Σ ��
	 * �� �� �� �� �� �� �� �� ѡ �� </riskFactorsCodes>
	 * <weightReduction>������Ŀ��</weightReduction>
	 * <vaccinationName>���������������</vaccinationName>
	 * <riskFactorsOther>Σ�����ؿ���-����</riskFactorsOther>
	 * <recordResultCode>1</recordResultCode>
	 * <recordResultDesc></recordResultDesc> </healthExamination>
	 * </responseDatas>
	 */
	/** 3.3.2. ��������¼ */

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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
					// //system.out.println("��ʼ����"+name );
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// //system.out.println("��������"+end);
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
					// ////system.out.println("��ʼ����"+name );
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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
	 * <personId>�������ID</personId> <specialNo>��ͯר����</specialNo> <name>����</name>
	 * <genderCode>�Ա����</genderCode> <birthday>��������</birthday>
	 * <householdRegister>������ַ</householdRegister>
	 * <childHealthCardNo>��ͯ���������</childHealthCardNo>
	 * <birthWeight>��������</birthWeight> <birthHeight>������</birthHeight>
	 * <parity>̥��</parity> <deliveryTimes>����</deliveryTimes>
	 * <deliveryGestationalWeeks>��������</deliveryGestationalWeeks>
	 * <deliveryModeCode>���䷽ʽ����</deliveryModeCode> <childbirth>��ʱ���</childbirth>
	 * <birthHospital>����ҽԺ</birthHospital> <fatherName>��������</fatherName>
	 * <matherName>ĸ������</matherName> <fatherContac>������ϵ�绰</fatherContac>
	 * <matherContac>ĸ����ϵ�绰</matherContac> <UNHSCode>����������ɸ�����</UNHSCode>
	 * <CYP17Code>17-a-OHP</CYP17Code> <PKUCode>����ͪ��֢ɸ�����</PKUCode>
	 * <CHCode>�����Լ�״�ٹ��ܵ��´���</CHCode> <highRiskCode>�Ƿ��Σ����</highRiskCode>
	 * <highRiskFactors>��Σ����</highRiskFactors> <apgar1>Apgar 1��������</apgar1>
	 * <apgar5>Apgar 5��������</apgar5> <apgar10>Apgar 10��������</apgar10>
	 * <pastHistory>������ʷ</pastHistory> <allergicHistory>����ʷ</allergicHistory>
	 * <childbirthHospital>�Ӳ�ҽԺ</childbirthHospital>
	 * <childbirthDoctor>�Ӳ�����</childbirthDoctor>
	 * <childbirthAssistant>�Ӳ�����</childbirthAssistant>
	 * <registerDate>��������</registerDate>
	 * <registerOrgCode>���ᵥλ����</registerOrgCode>
	 * <registerOrgName>���ᵥλ����</registerOrgName>
	 * <registerDoctorCode>�����˴���</registerDoctorCode>
	 * <registerDoctorName>����������</registerDoctorName> </childSpecial> ....
	 * </responseDatas>
	 */
	/** 3.5.2. ��ͯר������� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("childSpecial")) {
						child = new ChildSpecial_down();
						// ////system.out.println("�½���");
					} else if (child != null) {
						f = child.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(child, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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
	 * <personId>�������ID</personId> <specialNo>��Ѫѹר����</specialNo>
	 * <name>����</name> <registerDate>��������</registerDate>
	 * <registerOrgCode>������λ����</registerOrgCode>
	 * <registerDoctorCode>�����˴���</registerDoctorCode>
	 * <registerDoctorName>����������</registerDoctorName>
	 * <diagnoseDate>ȷ������</diagnoseDate>
	 * <diagnoseOrgCode>ȷ�ﵥλ����</diagnoseOrgCode>
	 * <diagnoseDoctorCode>ȷ���˴���</diagnoseDoctorCode>
	 * <diagnoseDoctorName>ȷ��������</diagnoseDoctorName> <SBP>����ѹ</SBP>
	 * <DBP>����ѹ</DBP> <bloodPressureLevel>Ѫѹ�ּ�</bloodPressureLevel>
	 * <nextFlupDate>�´��������</nextFlupDate> </hypertensionSpecial> ......
	 * </responseDatas>
	 */
	/** 3.6.2. ��Ѫѹר������� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("hypertensionSpecial")) {
						hy = new HypertensionSpecial_down();
						// ////system.out.println("�½���");
					} else if (hy != null) {
						f = hy.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(hy, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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
	 * <personId>�������ID</personId> <healthFileNumber>�����������</healthFileNumber>
	 * <name>����</name> <flupDate>�������</flupDate> <flupMode>��÷�ʽ</flupMode>
	 * <symptomCodes>֢״���루��ѡ��</symptomCodes> <symptomOther>֢״��������</symptomOther>
	 * <SBP>Ѫѹ-����ѹ</SBP> <DBP>Ѫѹ-����ѹ</DBP> <weight>����</weight>
	 * <weightTarget>����-Ŀ��ֵ</weightTarget> <bmi>����ָ��</bmi>
	 * <bmiTarget>����ָ��-Ŀ��ֵ</bmiTarget> <heartRate>����</heartRate>
	 * <signsOther>����-����</signsOther> <dailySmoking>��������</dailySmoking>
	 * <dailySmokingTarget>��������-Ŀ��ֵ</dailySmokingTarget>
	 * <dailyDrinking>��������</dailyDrinking>
	 * <dailyDrinkingTarget>��������-Ŀ��ֵ</dailyDrinkingTarget>
	 * <perWeekMovements>ÿ���˶�����</perWeekMovements>
	 * <perWeekMovementsTarget>ÿ���˶�����-Ŀ��ֵ</perWeekMovementsTarget>
	 * <perWeekTimes>ÿ���˶�ʱ��</perWeekTimes>
	 * <perWeekTimesTarget>ÿ���˶�ʱ��-Ŀ��ֵ</perWeekTimesTarget>
	 * <saltIntakeCode>�����������</saltIntakeCode>
	 * <saltIntakeTargetCode>�������Ŀ��ֵ����</saltIntakeTargetCode>
	 * <psychologicalCode>�����������</psychologicalCode>
	 * <complianceCode>��ҽ��Ϊ����</complianceCode <aidCheck>�������</aidCheck>
	 * <doseCode>��ҩ�����Դ���</doseCode>
	 * <adverseReactionCode>ҩ�ﲻ����Ӧ����</adverseReactionCode>
	 * <flupTypeCode>�˴���÷������</flupTypeCode> <medicationList> <medication>
	 * <drugCode>ҩƷ����</drugCode> <drugName>ҩƷ����</drugName>
	 * <drugUsage>ҩƷ�÷�</drugUsage> <drugUsageAdd>ҩƷ�÷�����</drugUsageAdd>
	 * <drugDosage>ҩƷ����</drugDosage> <drugDosageAdd>ҩƷ��������</drugDosageAdd>
	 * </medication> ...... </medicationList>
	 * <referralReason>ת��ԭ��</referralReason> <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <flupDoctorCode>���ҽ������</flupDoctorCode>
	 * <flupDoctorName>���ҽ������</flupDoctorName> <flupOrgCode>��û�������</flupOrgCode>
	 * 
	 * <flupOrgName>��û�������</flupOrgName> <nextFlupDate>�´��������</nextFlupDate>
	 * </hypertensionFlup>
	 * 
	 * ...... </responseDatas>
	 */
	/** 3.6.3. ��Ѫѹ��ü�¼ ���� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("hypertensionFlup")) {
						hy = new HypertensionFlup_down();
						// ////system.out.println("�½���");
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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
	 * <personId>�������ID</personId> <specialNo>����ר����</specialNo>
	 * <name>����</name> <registerDate>��������</registerDate>
	 * <registerOrgCode>������λ����</registerOrgCode>
	 * <registerDoctorCode>�����˴���</registerDoctorCode>
	 * <registerDoctorName>����������</registerDoctorName>
	 * <diagnoseDate>ȷ������</diagnoseDate>
	 * <diagnoseOrgCode>ȷ�ﵥλ����</diagnoseOrgCode>
	 * <diagnoseDoctorCode>ȷ��ҽ������</diagnoseDoctorCode>
	 * <diagnoseDoctorName>ȷ��ҽ������</diagnoseDoctorName> <SBP>����ѹ</SBP>
	 * <DBP>����ѹ</DBP> <bloodPressureLevel>Ѫѹ�ּ�</bloodPressureLevel>
	 * <nextFlupDate>�´��������</nextFlupDate>
	 * <diabetesLevelCode>���򲡹���ּ�����</diabetesLevelCode>
	 * <caseType>��������</caseType> <ICDCode>ICD-10����</ICDCode>
	 * <doseCode>�Ƿ��ҩ</doseCode> <noMedicationCode>δ��ҩԭ��</noMedicationCode>
	 * <drugCost>�·�ҩ����</drugCost> <familyHistoryCode>���򲡼���ʷ</familyHistoryCode>
	 * <height>���</height> <randomBloodGlucose>���Ѫ��ֵ</randomBloodGlucose>
	 * <kidneyDiseaseYears>���ಡ������</kidneyDiseaseYears>
	 * <retinalDiseaseYears>����Ĥ��������</retinalDiseaseYears>
	 * <neuropathyYears>�񾭲�������</neuropathyYears>
	 * <skinInfectionYears>Ƥ����Ⱦ����</skinInfectionYears>
	 * <vascularDiseaseYears>Ѫ�ܲ�������</vascularDiseaseYears>
	 * <noComplYears>�޲���֢����</noComplYears>
	 * <complicationDate>���򲡲���֢ʱ��</complicationDate>
	 * <initialDisease>���β���</initialDisease>
	 * <currentDisease>��ǰ����</currentDisease>
	 * <caseSourceCode>������Դ</caseSourceCode>
	 * <caseSourceOther>������Դ����</caseSourceOther> </diabetesSpecial> ......
	 * </responseDatas>
	 */
	/** 3_7_2����ר������� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					// //system.out.println("total" + eventType);
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					if (name.equals("diabetesSpecial")) {
						dia = new DiabetesSpecial_down();
						// ////system.out.println("�½���");
					} else if (dia != null) {
						f = dia.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(dia, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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
	 * <personId>�������ID</personId> <healthFileNumber>�����������</healthFileNumber>
	 * <name>����</name> <flupDate>�������</flupDate> <flupMode>��÷�ʽ</flupMode>
	 * <symptomCodes>֢״���루��ѡ��</symptomCodes> <symptomOther>֢״��������</symptomOther>
	 * <SBP>����ѹ</SBP> <DBP>����ѹ</DBP> <weight>����</weight>
	 * <weightTarget>����-Ŀ��ֵ</weightTarget> <bmi>����ָ��</bmi> <bmi>����ָ��-Ŀ��ֵ</bmi>
	 * <dorsalisPedisPulseCode>�㱳��������</dorsalisPedisPulseCode>
	 * <signsOther>����-����</signsOther> <dailySmoking>��������</dailySmoking>
	 * <dailySmokingTarget>��������-Ŀ��ֵ</dailySmokingTarget>
	 * <dailyDrinking>��������</dailyDrinking>
	 * <dailyDrinkingTarget>��������-Ŀ��ֵ</dailyDrinkingTarget>
	 * <perWeekMovements>ÿ���˶�����</perWeekMovements>
	 * <perWeekMovementsTarget>ÿ���˶�����Ŀ��ֵ</perWeekMovementsTarget>
	 * <perWeekTimes>ÿ���˶�ʱ��</perWeekTimes>
	 * <perWeekTimesTarget>ÿ���˶�ʱ��-Ŀ��ֵ</perWeekTimesTarget>
	 * <stapleFood>��ʳ</stapleFood> <stapleFoodTarget>��ʳ-Ŀ��ֵ</stapleFoodTarget>
	 * <psychologicalCode>�����������</psychologicalCode>
	 * <complianceCode>��ҽ��Ϊ����</complianceCode>
	 * <fastingBloodGlucose>�ո�Ѫ��</fastingBloodGlucose> <HbA1c>�ǻ�Ѫ�쵰��</HbA1c>
	 * <aidCheckDate>�����������</aidCheckDate> <aidCheck>������飨������</aidCheck>
	 * <doseCode>��ҩ�����Դ���</doseCode>
	 * <adverseReactionCode>ҩ�ﲻ����Ӧ����</adverseReactionCode>
	 * <lowBloodSugarCode>��Ѫ�Ƿ�Ӧ</lowBloodSugarCode>
	 * <flupTypeCode>�˴���÷������</flupTypeCode> <medicationList> <medication>
	 * <drugCode>ҩƷ����</drugCode> <drugName>ҩƷ����</drugName>
	 * <drugUsage>ҩƷ�÷�</drugUsage> <drugUsageAdd>ҩƷ�÷�����</drugUsageAdd>
	 * <drugDosage>ҩƷ����</drugDosage> <drugDosageAdd>ҩƷ��������</drugDosageAdd>
	 * </medication> ...... </medicationList>
	 * <insulinMedicationType>�ȵ�����ҩ����</insulinMedicationType>
	 * <insulinMedicationRate>�ȵ�����ҩƵ��</insulinMedicationRate>
	 * <insulinMedicationDose>�ȵ�����ҩ����</insulinMedicationDose>
	 * <referralReason>ת��ԭ��</referralReason> <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <flupDoctorCode>���ҽ������</flupDoctorCode>
	 * <flupDoctorName>���ҽ������</flupDoctorName> <flupOrgCode>��û�������</flupOrgCode>
	 * <flupOrgName>��û�������</flupOrgName> <nextFlupDate>�´��������</nextFlupDate>
	 * </diabetesFlup> ...... </responseDatas>
	 */
	/** 3.7.3. ���� ��ü�¼ ���� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
						// ////system.out.println("�½���");
					} else if (dia != null) {
						f = dia.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(dia, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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
	 * <personId>�������ID</areaCode> <healthFileNumber>�����������</healthFileNumber>
	 * <name>����</name> <genderCode>�Ա����</genderCode> <birthday>��������</birthday>
	 * <idCard>���֤��</idCard> <businessPlan>
	 * <businessTypeName>ҵ���������</businessTypeName>
	 * <businessName>ҵ������</businessName>
	 * <businessStartDate>�ƻ���ʼ����</businessStartDate>
	 * <businessEndDate>�ƻ���ֹ����</businessEndDate> <predictDate>Ԥ��ҵ���������</
	 * predictDate > </businessPlan> <businessPlan>
	 * <businessTypeName>ҵ���������</businessTypeName>
	 * <businessName>ҵ������</businessName>
	 * <businessStartDate>�ƻ���ʼ����</businessStartDate>
	 * <businessEndDate>�ƻ���ֹ����</businessEndDate> <predictDate>Ԥ��ҵ���������</
	 * predictDate > </businessPlan> ...... </person> ...... </person>
	 * </responseDatas> ]
	 */
	/** 3.8.2. ҵ��������� */
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
						// ////system.out.println("�½���");
					} else if (person != null) {
						f = person.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(person, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					String end = parser.getName();
					// ////system.out.println("��������"+end);
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

	// ////////////////////////////////////////////���з�װ�ϴ����ַ�������
	// 3.3.3. ��������¼�ϴ�
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
	 * <pregnantFirstCheck> <personId>�������ID</personId>
	 * <specialNo>�в���ר����</specialNo> <machineCode>����һ�������</machineCode>
	 * <machineNo>һ���ҵ�����</machineNo>
	 * <pregnantManualNo>�в��������ֲ���</pregnantManualNo> <name>�и�����</name>
	 * <antenatalCareDate>��������</antenatalCareDate>
	 * <gestationalWeeks>����</gestationalWeeks> <age>�и�����</age>
	 * <husbandName>�ɷ�����</husbandName> <husbandAge>�ɷ�����</husbandAge>
	 * <husbandPhone>�ɷ�绰</husbandPhone> <gravidityTimes>�д�</gravidityTimes>
	 * <deliveryTimes>����</deliveryTimes>
	 * <vaginalDeliveryTimes>�����������</vaginalDeliveryTimes>
	 * <caesareanSectionTimes>�ʹ�������</caesareanSectionTimes> <LMP>ĩ���¾�</LMP>
	 * <expectedBirthDate>Ԥ����</expectedBirthDate>
	 * <pastHistoryCodes>����ʷ���루��ѡ��</pastHistoryCodes>
	 * <pastHistoryOther>����ʷ����</pastHistoryOther>
	 * <familyHistoryCodes>����ʷ���루��ѡ��</familyHistoryCodes>
	 * <familyHistoryOther>����ʷ����</familyHistoryOther>
	 * <personalHistoryCodes>����ʷ���루��ѡ��</personalHistoryCodes>
	 * <personalHistoryOther>����ʷ����</personalHistoryOther>
	 * <surgeryHistoryCode>��������ʷ����</surgeryHistoryCode>
	 * <surgeryHistoryOther>��������ʷ����</surgeryHistoryOther>
	 * <abortionTimes>��������</abortionTimes>
	 * <stillbirthTimes>��̥����</stillbirthTimes>
	 * <deadBirthTimes>��������</deadBirthTimes>
	 * <neonatalDeath>��������������</neonatalDeath>
	 * <birthDefects>����ȱ�ݶ�����</birthDefects> <height>���</height>
	 * <weight>����</weight> <bmi>����ָ��</bmi> <SBP>����ѹ</SBP> <DBP>����ѹ</DBP>
	 * <cardiacAuscultationCode>�����������</cardiacAuscultationCode>
	 * <cardiacAuscultationDesc>���������쳣</cardiacAuscultationDesc>
	 * <lungAuscultationCode>�β��������</lungAuscultationCode>
	 * <lungAuscultationDesc>�β���������</lungAuscultationDesc>
	 * <vulvaCode>����-��������</vulvaCode> <vulvaDesc>����-��������</vulvaDesc>
	 * <vaginaCode>����-��������</vaginaCode> <vaginaDesc>����-��������</vaginaDesc>
	 * <cervixCode>����-��������</cervixCode> <cervixDesc>����-��������</cervixDesc>
	 * <uterusCode>����-�������</uterusCode> <uterusDesc>����-��������</uterusDesc>
	 * <uterineAccessoriesCode>����-��������</uterineAccessoriesCode>
	 * <uterineAccessoriesDesc>����-��������</uterineAccessoriesDesc>
	 * <hemoglobin>Ѫ�쵰��ֵ</hemoglobin> <leucocyte>��ϸ������ֵ</leucocyte>
	 * <platelet>Ѫ�������ֵ</platelet> <bloodOther>Ѫ����-����</bloodOther>
	 * <urineProteinCode>�򳣹�-�򵰰�</urineProteinCode>
	 * <urineSugarCode>�򳣹�-����</urineSugarCode>
	 * <urineKetoneCode>�򳣹�-��ͪ��</urineKetoneCode>
	 * <urineOccultBloodCode>�򳣹�-��ǱѪ</urineOccultBloodCode>
	 * <urineOther>�򳣹�-����</urineOther> <bloodGroupCode>Ѫ�ʹ���</bloodGroupCode>
	 * <rhBloodGroupCode>RH���Դ���</rhBloodGroupCode>
	 * <bloodGlucose>Ѫ��</bloodGlucose> <GPT>�ι���-Ѫ��ȱ�ת��ø </GPT> <GOT>�ι���-Ѫ��Ȳ�ת��ø
	 * </GOT> <albumin>�ι���-�׵���</albumin> <TBIL>�ι���-�ܵ�����</TBIL>
	 * <DBIL>�ι���-��ϵ�����</DBIL> <serumCreatinine>������-Ѫ�弡��</serumCreatinine>
	 * <BUN>������-Ѫ���ص�</BUN> <vaginalFluidCodes>������������루��ѡ��</vaginalFluidCodes>
	 * <vaginalFluidOther>��������������</vaginalFluidOther>
	 * <vaginalCleanlinessCode>�������ȴ���</vaginalCleanlinessCode>
	 * <HBsAgCode>���͸��ױ��濹ԭ</HBsAgCode> <antiHBsCode>���͸��ױ��濹��</antiHBsCode>
	 * <HBeAgCode>���͸���e��ԭ</HBeAgCode> <HBeAbCode>���͸���e����</HBeAbCode>
	 * <HBcAbCode>���͸��׺��Ŀ���</HBcAbCode> <syphilisCode>÷��Ѫ��ѧ�������</syphilisCode>
	 * <HIVCode>HIV���������</HIVCode> <BScan>B��</BScan>
	 * <overallAssessmentCode>������������</overallAssessmentCode>
	 * <overallAssessmentDesc>������������</overallAssessmentDesc>
	 * <healthGuideCodes>����ָ������</healthGuideCodes>
	 * <healthGuideOther>����ָ������</healthGuideOther>
	 * <referralCode>ת�����</referralCode> <referralReason>ת��ԭ��</referralReason>
	 * <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <checkDoctorCode>����ҽ������</checkDoctorCode>
	 * <checkDoctorName>����ҽ������</checkDoctorName>
	 * <checkOrgCode>�����������</checkOrgCode> <checkOrgName>�����������</checkOrgName>
	 * <nextFlupDate>�´��������</nextFlupDate> </pregnantFirstCheck>
	 * </requestParams>
	 */
	/** 3.4.3. �в��������¼�ϴ� */
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
	 * @param �в���ר�������
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
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
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
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
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
	 * <pregnantRecheck> <personId>�������ID</personId>
	 * <specialNo>�в���ר����</specialNo> <machineCode>����һ�������</machineCode>
	 * <machineNo>һ���ҵ�����</machineNo>
	 * <pregnantManualNo>�в��������ֲ���</pregnantManualNo> <name>�и�����</name>
	 * <antenatalCareDate>��������</antenatalCareDate>
	 * <gestationalWeeks>����</gestationalWeeks>
	 * <chiefComplaint>����</chiefComplaint> <weight>����</weight>
	 * <fundusHeight>���׸߶�</fundusHeight>
	 * <abdominalCircumference>��Χ</abdominalCircumference>
	 * <foetalCirculationCode>̥λ</foetalCirculationCode>
	 * <fetalHeartRate>̥����</fetalHeartRate> <SBP>����ѹ</SBP> <DBP>����ѹ</DBP>
	 * <hemoglobin>Ѫ�쵰��ֵ</hemoglobin> <leucocyte>�򵰰�</leucocyte>
	 * <checkOther>�����������</checkOther> <typeCode>����</typeCode>
	 * <typeDesc>��������</typeDesc> <guideCodes>ָ������</guideCodes>
	 * <guideOther>ָ������</guideOther> <referralCode>ת�����</referralCode>
	 * <referralReason>ת��ԭ��</referralReason> <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <checkDoctorCode>����ҽ������</checkDoctorCode>
	 * <checkDoctorName>����ҽ������</checkDoctorName>
	 * <checkOrgCode>�����������</checkOrgCode> <checkOrgName>�����������</checkOrgName>
	 * <nextFlupDate>�´��������</nextFlupDate> </pregnantRecheck> </requestParams>
	 */
	/** 3.4.4. �в������� ��¼�ϴ� */
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
	 * <postpartumVisit> <personId>�������ID</personId>
	 * <specialNo>�в���ר����</specialNo> <machineCode>����һ�������</machineCode>
	 * <machineNo>һ���ҵ�����</machineNo>
	 * <pregnantManualNo>�в��������ֲ���</pregnantManualNo> <name>�и�����</name>
	 * <flupDate>�������</flupDate> <temperature>����</temperature>
	 * <healthDesc>һ�㽡�����</healthDesc>
	 * <psychologicStatus>һ������״��</psychologicStatus> <SBP>����ѹ</SBP>
	 * <DBP>����ѹ</DBP> <breastCode>�鷿����</breastCode>
	 * <breastDesc>�鷿����</breastDesc> <lochiaCode>��¶����</lochiaCode>
	 * <lochiaDesc>��¶����</lochiaDesc> <uterusCode>�ӹ�����</uterusCode>
	 * <uterusDesc>�ӹ�����</uterusDesc> <woundCode>�˿ڴ���</woundCode>
	 * <woundDesc>�˿�����</woundDesc> <other>����</other> <typeCode>����</typeCode>
	 * <typeDesc>��������</typeDesc> <guideCodes>ָ������</guideCodes>
	 * <guideOther>ָ������</guideOther> <referralCode>ת�����</referralCode>
	 * <referralReason>ת��ԭ��</referralReason> <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <visitDoctorCode>����ҽ������</visitDoctorCode>
	 * <visitDoctorName>����ҽ������</visitDoctorName>
	 * <visitOrgCode>���ӻ�������</visitOrgCode> <visitOrgName>���ӻ�������</visitOrgName>
	 * <nextFlupDate>�´��������</nextFlupDate> </postpartumVisit> </requestParams>
	 */
	/** 3.4.5. �в���������� ��¼�ϴ� */
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
	 * <postpartum42Visit> <personId>�������ID</personId>
	 * <specialNo>�в���ר����</specialNo> <machineCode>����һ�������</machineCode>
	 * <machineNo>һ���ҵ�����</machineNo>
	 * <pregnantManualNo>�в��������ֲ���</pregnantManualNo> <name>�и�����</name>
	 * <flupDate>�������</flupDate> <healthDesc>һ�㽡�����</healthDesc>
	 * <psychologicStatus>һ������״��</psychologicStatus> <SBP>����ѹ</SBP>
	 * <DBP>����ѹ</DBP> <breastCode>�鷿����</breastCode>
	 * <breastDesc>�鷿����</breastDesc> <lochiaCode>��¶����</lochiaCode>
	 * <lochiaDesc>��¶����</lochiaDesc> <uterusCode>�ӹ�����</uterusCode>
	 * <uterusDesc>�ӹ�����</uterusDesc> <woundCode>�˿ڴ���</woundCode>
	 * <woundDesc>�˿�����</woundDesc> <other>����</other> <typeCode>����</typeCode>
	 * <typeDesc>��������</typeDesc> <guideCodes>ָ������</guideCodes>
	 * <guideOther>ָ������</guideOther> <processCode>�������</processCode>
	 * <referralReason>ת��ԭ��</referralReason> <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <visitDoctorCode>����ҽ������</visitDoctorCode>
	 * <visitDoctorName>����ҽ������</visitDoctorName>
	 * <visitOrgCode>���ӻ�������</visitOrgCode> <visitOrgName>���ӻ�������</visitOrgName>
	 * </postpartum42Visit> </requestParams>
	 */
	/** 3.4.6.�в������� 42����� ��¼�ϴ� */
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
	 * <neonatalVisit> <personId>�������ID</personId> <specialNo>��ͯר����</specialNo>
	 * <machineCode>����һ�������</machineCode> <machineNo>һ���ҵ�����</machineNo>
	 * <name>����</name> <genderCode>�Ա����</genderCode> <birthday>��������</birthday>
	 * <idCard>���֤��</idCard> <homeAddress>��ͥסַ</homeAddress>
	 * <fatherName>��������</fatherName> <matherName>ĸ������</matherName>
	 * <fatherOccupation>����ְҵ</fatherOccupation>
	 * <matherOccupation>ĸ��ְҵ</matherOccupation>
	 * <fatherContac>������ϵ�绰</fatherContac> <matherContac>ĸ����ϵ�绰</matherContac>
	 * <fatherBirthday>���׳�������</fatherBirthday>
	 * <matherBirthday>ĸ�׳�������</matherBirthday> <weekOfBirth>��������</weekOfBirth>
	 * <pregnancyBeIllCode>ĸ�������ڻ����������</pregnancyBeIllCode>
	 * <pregnancyBeIllOther>ĸ�������ڻ����������</pregnancyBeIllOther>
	 * <midwiferyOrgName>������������</midwiferyOrgName>
	 * <birthConditionCodes>�����������</birthConditionCodes>
	 * <neonatalAsphyxiaCode>��������Ϣ����</neonatalAsphyxiaCode> <apgar1>Apgar
	 * 1��������</apgar1> <apgar5>Apgar 5��������</apgar5> <apgar10>Apgar
	 * 10��������</apgar10> <deformityCode>�Ƿ��л���</deformityCode>
	 * <deformityOther>��������</deformityOther> <UNHSCode>����������ɸ�����</UNHSCode>
	 * <neonatalScreeningCode>����������ɸ�����</neonatalScreeningCode>
	 * <neonatalScreeningOther>����������ɸ������ </neonatalScreeningOther>
	 * <birthWeight>��������</birthWeight> <currentWeight>Ŀǰ����</currentWeight>
	 * <birthHeight>������</birthHeight>
	 * <feedingPatternCode>ι����ʽ</feedingPatternCode>
	 * <feedingAmount>������</feedingAmount> <feedingTimes>���̴���</feedingTimes>
	 * <vomitCode>Ż�´���</vomitCode> <fecalCode>������</fecalCode>
	 * <fecalTimes>������</fecalTimes> <temperature>����</temperature>
	 * <pulseRate>����</pulseRate> <respiratoryRate>����Ƶ��</respiratoryRate>
	 * <complexionCode>��ɫ����</complexionCode>
	 * <complexionOther>��ɫ����</complexionOther>
	 * <jaundiceSiteCode>���㲿λ����</jaundiceSiteCode> <bregma1>ǰض1</bregma1>
	 * <bregma2>ǰض2</bregma2> <bregmaCode>ǰض����</bregmaCode>
	 * <bregmaOther>ǰض����</bregmaOther>
	 * <eyeAppearanceCode>����۴���</eyeAppearanceCode>
	 * <eyeAppearanceDesc>������쳣</eyeAppearanceDesc>
	 * <limbsActivityCode>��֫��ȴ���</limbsActivityCode>
	 * <limbsActivityDesc>��֫����쳣</limbsActivityDesc>
	 * <earAppearanceCode>����۴���</earAppearanceCode>
	 * <earAppearanceDesc>������쳣</earAppearanceDesc>
	 * <neckMassCode>�����������</neckMassCode> <neckMassDesc>������������</neckMassDesc>
	 * <noseCode>�Ǵ���</noseCode> <noseDesc>���쳣</noseDesc>
	 * <skinCode>Ƥ������</skinCode> <skinOther>Ƥ������</skinOther>
	 * <oralCode>��ǻ����</oralCode> <oralDesc>��ǻ�쳣</oralDesc>
	 * <anusCode>���Ŵ���</anusCode> <anusDesc>�����쳣</anusDesc>
	 * <heartLungAuscultationCode>�ķ�������� </heartLungAuscultationCode>
	 * <heartLungAuscultationDesc>�ķ������쳣 </heartLungAuscultationDesc>
	 * <externalGenitalCode>����ֳ������</externalGenitalCode>
	 * <externalGenitalDesc>����ֳ���쳣</externalGenitalDesc>
	 * <abdominalPalpationCode>�����������</abdominalPalpationCode>
	 * <abdominalPalpationDesc>���������쳣</abdominalPalpationDesc>
	 * <spineCode>��������</spineCode> <spineDesc>�����쳣</spineDesc>
	 * <umbilicalCordCode>�������</umbilicalCordCode>
	 * <umbilicalCordOther>�������</umbilicalCordOther>
	 * <referralCode>ת�ｨ��</referralCode> <referralReason>ת��ԭ��</referralReason>
	 * <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <guideCodes>ָ�����루��ѡ��</guideCodes> <flupDate>�������</flupDate>
	 * <flupDoctorCode>���ҽ������</flupDoctorCode>
	 * <flupDoctorName>���ҽ������</flupDoctorName> <flupOrgCode>��û�������</flupOrgCode>
	 * <flupOrgName>��û�������</flupOrgName> <nextFlupDate>�´��������</nextFlupDate>
	 * <nextFlupLocation>�´���õص�</nextFlupLocation> </neonatalVisit>
	 * </requestParams>
	 */
	/** 3.5.3. ��������ͥ���� ��¼�ϴ� */
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
	 * <hypertensionFlup> <personId>�������ID</personId>
	 * <specialNo>��Ѫѹר����</specialNo> <machineCode>����һ�������</machineCode>
	 * <machineNo>һ���ҵ�����</machineNo> <name>����</name> <flupDate>�������</flupDate>
	 * <flupMode>��÷�ʽ</flupMode> <symptomCodes>֢״���루��ѡ��</symptomCodes>
	 * <symptomOther>֢״��������</symptomOther> <SBP>Ѫѹ-����ѹ</SBP> <DBP>Ѫѹ-����ѹ</DBP>
	 * <weight>����</weight> <weightTarget>����-Ŀ��ֵ</weightTarget> <bmi>����ָ��</bmi>
	 * <bmiTarget>����ָ��-Ŀ��ֵ</bmiTarget> <heartRate>����</heartRate>
	 * <signsOther>����-����</signsOther> <dailySmoking>��������</dailySmoking>
	 * <dailySmokingTarget>��������-Ŀ��ֵ</dailySmokingTarget>
	 * <dailyDrinking>��������</dailyDrinking>
	 * <dailyDrinkingTarget>��������-Ŀ��ֵ</dailyDrinkingTarget>
	 * <perWeekMovements>ÿ���˶�����</perWeekMovements>
	 * <perWeekMovementsTarget>�˶�����Ŀ��ֵ</perWeekMovementsTarget>
	 * <perWeekTimes>ÿ���˶�ʱ��</perWeekTimes>
	 * <perWeekTimesTarget>ÿ���˶�ʱ��-Ŀ��ֵ</perWeekTimesTarget>
	 * <saltIntakeCode>�����������</saltIntakeCode>
	 * <saltIntakeTargetCode>�������Ŀ��ֵ����</saltIntakeTargetCode>
	 * <psychologicalCode>�����������</psychologicalCode>
	 * <complianceCode>��ҽ��Ϊ����</complianceCode <aidCheck>�������</aidCheck>
	 * <doseCode>��ҩ�����Դ���</doseCode>
	 * <adverseReactionCode>ҩ�ﲻ����Ӧ����</adverseReactionCode>
	 * <flupTypeCode>�˴���÷������</flupTypeCode> <medicationList> <medication>
	 * <drugCode>ҩƷ����</drugCode> <drugName>ҩƷ����</drugName>
	 * <drugUsage>ҩƷ�÷�</drugUsage> <drugUsageAdd>ҩƷ�÷�����</drugUsageAdd>
	 * <drugDosage>ҩƷ����</drugDosage> <drugDosageAdd>ҩƷ��������</drugDosageAdd>
	 * </medication> ......
	 * 
	 * </medicationList> <referralReason>ת��ԭ��</referralReason>
	 * <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <flupDoctorCode>���ҽ������</flupDoctorCode>
	 * <flupDoctorName>���ҽ������</flupDoctorName> <flupOrgCode>��û�������</flupOrgCode>
	 * <flupOrgName>��û�������</flupOrgName> <nextFlupDate>�´��������</nextFlupDate>
	 * </hypertensionFlup> </requestParams>
	 */
	/** 3.6.5. ��Ѫѹ��� ��¼�ϴ� */
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
	 * <diabetesFlup> <personId>�������ID</personId> <specialNo>����ר����</specialNo>
	 * <machineCode>����һ�������</machineCode> <machineNo>һ���ҵ�����</machineNo>
	 * <name>����</name> <flupDate>�������</flupDate> <flupMode>��÷�ʽ</flupMode>
	 * <symptomCodes>֢״���루��ѡ��</symptomCodes> <symptomOther>֢״��������</symptomOther>
	 * <SBP>����ѹ</SBP> <DBP>����ѹ</DBP> <weight>����</weight>
	 * <weightTarget>����-Ŀ��ֵ</weightTarget> <bmi>����ָ��</bmi> <bmi>����ָ��-Ŀ��ֵ</bmi>
	 * <dorsalisPedisPulseCode>�㱳��������</dorsalisPedisPulseCode>
	 * <signsOther>����-����</signsOther> <dailySmoking>��������</dailySmoking>
	 * <dailySmokingTarget>��������-Ŀ��ֵ</dailySmokingTarget>
	 * <dailyDrinking>��������</dailyDrinking>
	 * <dailyDrinkingTarget>��������-Ŀ��ֵ</dailyDrinkingTarget>
	 * <perWeekMovements>ÿ���˶�����</perWeekMovements>
	 * <perWeekMovementsTarget>ÿ���˶�����Ŀ��ֵ </perWeekMovementsTarget>
	 * <perWeekTimes>ÿ���˶�ʱ��</perWeekTimes>
	 * <perWeekTimesTarget>ÿ���˶�ʱ��-Ŀ��ֵ</perWeekTimesTarget>
	 * <stapleFood>��ʳ</stapleFood> <stapleFoodTarget>��ʳ-Ŀ��ֵ</stapleFoodTarget>
	 * <psychologicalCode>�����������</psychologicalCode>
	 * <complianceCode>��ҽ��Ϊ����</complianceCode>
	 * <fastingBloodGlucose>�ո�Ѫ��</fastingBloodGlucose> <HbA1c>�ǻ�Ѫ�쵰��</HbA1c>
	 * <aidCheckDate>�����������</aidCheckDate> <aidCheck>������飨������</aidCheck>
	 * <doseCode>��ҩ�����Դ���</doseCode>
	 * <adverseReactionCode>ҩ�ﲻ����Ӧ����</adverseReactionCode>
	 * <lowBloodSugarCode>��Ѫ�Ƿ�Ӧ</lowBloodSugarCode>
	 * <flupTypeCode>�˴���÷������</flupTypeCode> <medicationList> <medication>
	 * <drugCode>ҩƷ����</drugCode> <drugName>ҩƷ����</drugName>
	 * <drugUsage>ҩƷ�÷�</drugUsage> <drugUsageAdd>ҩƷ�÷�����</drugUsageAdd>
	 * <drugDosage>ҩƷ����</drugDosage> <drugDosageAdd>ҩƷ��������</drugDosageAdd>
	 * </medication> ...... </medicationList>
	 * <insulinMedicationType>�ȵ�����ҩ����</insulinMedicationType>
	 * <insulinMedicationRate>�ȵ�����ҩƵ��</insulinMedicationRate>
	 * <insulinMedicationDose>�ȵ�����ҩ����</insulinMedicationDose>
	 * <referralReason>ת��ԭ��</referralReason> <referralOrg>ת�����</referralOrg>
	 * <referralDepartment>ת�����</referralDepartment>
	 * <flupDoctorCode>���ҽ������</flupDoctorCode>
	 * <flupDoctorName>���ҽ������</flupDoctorName> <flupOrgCode>��û�������</flupOrgCode>
	 * <flupOrgName>��û�������</flupOrgName> <nextFlupDate>�´��������</nextFlupDate>
	 * </diabetesFlup> </requestParams>
	 */
	/** 3.7.4. ������� ��¼�ϴ� */

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
	 * <healthRecord> <updateType>1</personId><!�������������� -->
	 * <machineCode>����һ�������</machineCode> <machineNo>һ���ҵ�����</machineNo>
	 * <nowLiveCode>500237026011001</nowLiveCode>
	 * <nowLiveName>��ɽ����Ϫ��׺״�1��</nowLiveName> <nowLiveAddr>35��</nowLiveAddr>
	 * <householdRegisterCode>500237003008004</householdRegisterCode>
	 * <householdRegisterName>��ɽ�ر������һ���4��</householdRegisterName>
	 * <householdRegisterAddr>109��</householdRegisterAddr>
	 * <registerOrgCode>5002370801</registerOrgCode>
	 * <registerOrgName>������������Ժ</registerOrgName>
	 * <registerDoctorCode>030002</registerDoctorCode>
	 * <registerDoctorName>��XX</registerDoctorName>
	 * <responsibleDoctorCode>5002370100033</responsibleDoctorCode>
	 * <responsibleDoctorName>��XX</responsibleDoctorName>
	 * <registerDate>ҵ���������</registerDate>
	 * <healthFileNumber>5002370061300134</healthFileNumber> <name>����</name>
	 * <genderCode>1</genderCode> <birthday>19995-07-01</birthday>
	 * <idCard>50023719850907727X</idCard> <workUnit>��</workUnit>
	 * <phone>18719121314</birthday> <contacts>������</contacts>
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
	 * <pastHistoryCode>6</pastHistoryCode> <pastHistoryAdd>�ΰ�</pastHistoryAdd>
	 * <pastHistoryDate>2011-07-03</pastHistoryDate> </pastHistory>
	 * <pastHistory> <pastHistoryType>2</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> <pastHistory>
	 * <pastHistoryType>3</pastHistoryType> <pastHistoryCode>1</pastHistoryCode>
	 * </pastHistory> <pastHistory> <pastHistoryType>4</pastHistoryType>
	 * <pastHistoryCode>1</pastHistoryCode> </pastHistory> </pastHistoryList>
	 * </healthRecord> <healthRecord> </requestParams>
	 */
	/** 3.2.3-4 �������������� �޸Ľ������� */
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
	/** 3.2.3-4 ����Ѫѹ������¼*/
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
	/** 3.2.3-4 ����Ѫ��������¼*/
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
	/** 3.2.3-4 ����Ѫ�ǲ�����¼*/
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
	/** 3.2.3-4 �������²�����¼*/
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
	/** 3.2.3-4 ������Һ������¼*/
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
	/** 3.2.3-4 �����ĵ������¼*/
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
	
	/** 3.2.3-4 ������ϸ��������¼*/
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
