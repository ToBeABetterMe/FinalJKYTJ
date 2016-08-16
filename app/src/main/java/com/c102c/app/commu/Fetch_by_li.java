package com.c102c.app.commu;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.c102c.app.base.AppDB;
import com.c102c.app.model.DiabetesFlup_upload;
import com.c102c.app.model.HealthExamination;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.HypertensionFlup_upload;
import com.c102c.app.model.LocalMeasurementBaiXiBao;
import com.c102c.app.model.LocalMeasurementEWen;
import com.c102c.app.model.LocalMeasurementNiaoYe;
import com.c102c.app.model.LocalMeasurementXinDian;
import com.c102c.app.model.LocalMeasurementXueTang;
import com.c102c.app.model.LocalMeasurementXueYa;
import com.c102c.app.model.LocalMeasurementXueYang;
import com.c102c.app.model.NeonatalVisit_upload;
import com.c102c.app.model.Postpartum42Visit_upload;
import com.c102c.app.model.PostpartumVisit_upload;
import com.c102c.app.model.PregnantFirstCheck_upload;
import com.c102c.app.model.PregnantRecheck_upload;
import com.c102c.app.model.RequestMessage;
import com.c102c.app.utils.Util;
import com.c102c.app.utils.WebTool;

public class Fetch_by_li {
	private static String url = "http://219.223.247.25:8000/api/PlatformService.wsdl";
	private static String namespace = "http://219.223.247.25:8000/api/PlatformService.wsdl";
	private static String webmethod = "send";
	private static String result;
	private static String sence;
	// 添加一个methed为的字符串
	public static String uploadXML = "";
	public static boolean xmlFlag;

	/** 设置xml请求数据 */
	public static void setUploadXML(String uploadXML) {
		Fetch_by_li.uploadXML = uploadXML;
	}

	public static void communicate(final String method, Context context,
			final Handler handler, String string) {
		sence = string;
		communicate(method, context, handler);
	}

	// 啟晖添加
	private static String mUUID = "";

	/***
	 * 
	 * @param method
	 * @param context
	 * @param handler
	 * @param xml
	 *            要请求的xml （从数据库获取的）
	 * @param flag
	 *            是否要直接请求xml的标记。
	 */
	public static void communicate(String uuid, Context context,
			final Handler handler, String xml, boolean flag) {
		mUUID = uuid;
		String type = uuid.split(";")[0];
		String method = "";
		switch (type) {
		case "DiabetesFlup_upload":
			method = "M0100070201";
			break;
		case "HealthRecord_down":
			method = "M0100020202";
			break;
		case "HypertensionFlup_upload":
			method = "M0100060202";
			break;
		case "HealthExamination":
			method = "M0100030201";
			break;
		case "PregnantFirstCheck_upload":
			method = "M0100040201";
			break;
		case "PregnantRecheck_upload":
			method = "M0100040202";
			break;
		case "PostpartumVisit_upload":
			method = "M0100040203";
			break;
		case "Postpartum42Visit_upload":
			method = "M0100040204";
			break;
		case "NeonatalVisit_upload":
			method = "M0100050201";
			break;
		default:
			break;
		}
		uploadXML = xml;
		xmlFlag = flag;
		communicate(method, context, handler);
	}

	public static Object object;

	// 一个记录查看本地记录然后编辑时候 的需要标记//复用了mUUID

	public static void communicate(final String method, Context context,
			final Handler handler, Object object) {
		Fetch_by_li.object = object;
		if (object instanceof DiabetesFlup_upload) {
			sence = Util.setDiabetesFlup_Upload((DiabetesFlup_upload) object);
			mUUID = "DiabetesFlup_upload" + ";"
					+ ((DiabetesFlup_upload) object).getId();
		}
		if (object instanceof HealthRecord_down) {
			sence = Util.setHealthRecord_Upload((HealthRecord_down) object);
			mUUID = "HealthRecord_down" + ";"
					+ ((HealthRecord_down) object).getPersonId();
		}
		if (object instanceof HypertensionFlup_upload) {
			sence = Util
					.setHypertensionFlup_Upload((HypertensionFlup_upload) object);
			mUUID = "HypertensionFlup_upload" + ";"
					+ ((HypertensionFlup_upload) object).getId();
		}
		if (object instanceof HealthExamination) {
			sence = Util.setHealthExamination((HealthExamination) object);
			mUUID = "HealthExamination" + ";"
					+ ((HealthExamination) object).getBusinessKey();
		}
		if (object instanceof PregnantFirstCheck_upload) {
			sence = Util
					.setPregnantFirstCheck_Upload((PregnantFirstCheck_upload) object);
			mUUID = "PregnantFirstCheck_upload" + ";"
					+ ((PregnantFirstCheck_upload) object).getId();
		}
		if (object instanceof PregnantRecheck_upload) {
			sence = Util
					.setPregnantRecheck_Upload((PregnantRecheck_upload) object);
			mUUID = "PregnantRecheck_upload" + ";"
					+ ((PregnantRecheck_upload) object).getId();
		}
		if (object instanceof PostpartumVisit_upload) {
			sence = Util
					.setPostpartumVisit_Upload((PostpartumVisit_upload) object);
			mUUID = "PostpartumVisit_upload" + ";"
					+ ((PostpartumVisit_upload) object).getId();
		}
		if (object instanceof Postpartum42Visit_upload) {
			sence = Util
					.setPostpartum42Visit_Upload((Postpartum42Visit_upload) object);
			mUUID = "Postpartum42Visit_upload" + ";"
					+ ((Postpartum42Visit_upload) object).getId();
		}
		if (object instanceof NeonatalVisit_upload) {
			sence = Util.setNeonatalVisit_Upload((NeonatalVisit_upload) object);
			mUUID = "NeonatalVisit_upload" + ";"
					+ ((NeonatalVisit_upload) object).getId();
		}
		
		/*增加关于健康测量数据的上传部分 */
		if (object instanceof LocalMeasurementXueYa) {
			sence = Util.setMeasurementXueYa_Upload((LocalMeasurementXueYa) object);
			mUUID = "MeasurementXueYa_Upload" + ";"
					+ ((LocalMeasurementXueYa) object).getId();
		}
		if (object instanceof LocalMeasurementXueYang) {
			sence = Util.setMeasurementXueYang_Upload((LocalMeasurementXueYang) object);
			mUUID = "MeasurementXueYang_Upload" + ";"
					+ ((LocalMeasurementXueYang) object).getId();
		}
		if (object instanceof LocalMeasurementXueTang) {
			sence = Util.setMeasurementXueTang_Upload((LocalMeasurementXueTang) object);
			mUUID = "MeasurementXueTang_Upload" + ";"
					+ ((LocalMeasurementXueTang) object).getId();
		}
		if (object instanceof LocalMeasurementEWen) {
			sence = Util.setMeasurementEWen_Upload((LocalMeasurementEWen) object);
			mUUID = "MeasurementEWen_Upload" + ";"
					+ ((LocalMeasurementEWen) object).getId();
		}
		if (object instanceof LocalMeasurementNiaoYe) {
			sence = Util.setMeasurementNiaoYe_Upload((LocalMeasurementNiaoYe) object);
			mUUID = "MeasurementNiaoYe_Upload" + ";"
					+ ((LocalMeasurementNiaoYe) object).getId();
		}
		if (object instanceof LocalMeasurementXinDian) {
			sence = Util.setMeasurementXinDian_Upload((LocalMeasurementXinDian) object);
			mUUID = "MeasurementXinDian_Upload" + ";"
					+ ((LocalMeasurementXinDian) object).getId();
		}
		if (object instanceof LocalMeasurementBaiXiBao) {
			sence = Util.setMeasurementBaiXiBao_Upload((LocalMeasurementBaiXiBao) object);
			mUUID = "MeasurementBaiXiBao_Upload" + ";"
					+ ((LocalMeasurementBaiXiBao) object).getId();
		}
		
		/* *************end*************** */
		
		
		communicate(method, context, handler);
	}

	public static void communicate(final String method, Context context,
			final Handler handler) {

		/**
		 * ====================================================================
		 * == ====== 构造通用的xml头 参见文档
		 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		 */
		String xml;
		if (xmlFlag) {
			xml = uploadXML;
			Pattern pattern = Pattern
					.compile("<requestMessageId>\\w{8}-[\\w]{4}-[\\w]{4}-[\\w]{4}-[\\w]{12}</requestMessageId>");
			Matcher matcher = pattern.matcher(xml);
			xml = matcher.replaceFirst("<requestMessageId>"+Basesence.getuuid()+"</requestMessageId>");
			xmlFlag = false;
			uploadXML = null;
		} else {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><req"
					+ "uest-message><requestMessageId>"
					+ Basesence.getuuid()
					+ "</requestMessageId><securityToken>";

			if (method == "G0101000101" || method == "M0000010101"
					|| method == "M0000010102")
				xml += Basesence.getini_token();
			else
				xml += Basesence.getmac(context);

			xml += "</securityToken><actionType>";
			xml += method;
			xml += "</actionType><requestTime>";
			xml += Basesence.gettime();
			xml += "</requestTime>";
			// //////////德森

			if (method.equals("M0100050201") || method.equals("M0100040201")
					|| method.equals("M0100040202")
					|| method.equals("M0100040203")
					|| method.equals("M0100040204")) {
				xml += "<healthFileNumber>"
						+ Basesence.getHealFileNumber()
						+ "</healthFileNumber><businessDate>2015-9-33 </businessDate>";
			}
			// //////////////////
			/*
			 * ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
			 * ↑↑↑↑ ↑↑↑↑↑ 构造通用的xml头 参见文档
			 * ==================================================
			 * ============================
			 */

			/**
			 * ================================================================
			 * ==== == ======
			 * 构造通用的xml的content节点，G0101000101的method不包含该节点并且需要base64编码 ↓↓↓↓↓↓↓
			 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
			 * ↓↓↓↓↓↓↓
			 */
			if (!method.equalsIgnoreCase("G0101000101")) {
				xml += "<content><![CDATA[";
				String cont = "<?xml version=\"1.0\" encoding=\"UTF-8\""
						+ " standalone=\"yes\"?>";
				switch (method) {
				case "M0000010101":
					cont += "<requestParams pageStart=\"1\" pageSize=\"5000\">";
					cont += "<areaCode>";
					cont += Basesence.getini_token();
					cont += "</areaCode>";
					cont += "</requestParams>";
					break;
				case "M0000010102":
					cont += "<requestParams pageStart=\"1\" pageSize=\"1000\">"
							+ "</requestParams>";
					break;
				case "M0100060101":
				case "M0100040101":
				case "M0100050101":
				case "M0100070102":
				case "M0100060102":
				case "M0100070101":
					cont += "<requestParams pageStart=\"1\" pageSize=\"1000\">"
							+ "<areaCode>" + Basesence.getAdminAreaCode()
							+ "</areaCode>" + "</requestParams>";
					break;
				case "M0100030101":
					cont += "<requestParams pageStart=\"1\" pageSize=\"1000\">"
							+ "<healthExamination><householdRegisterCode>";
					cont += Basesence.getAdminAreaCode();
					cont += "</householdRegisterCode><personFlupRows>999</personFlupRows> <healthFileNumber>";
					cont += Basesence.getTempHealthRecord_down()
							.getHealthFileNumber();
					cont += "</healthFileNumber></healthExamination></requestParams>";
					break;
				case "G0301000101":
					cont += "<requestParams><machine><machineCode>";
					cont += Basesence.getmac(context);
					cont += "</machineCode> <areaCode>";
					cont += Basesence.getini_token();
					cont += "</areaCode> <orgCode>";
					cont += Basesence.getorgCode();
					cont += "</orgCode> <orgName>";
					cont += Basesence.getorgName();
					cont += "</orgName> </machine> </requestParams> ";
					break;
				case "M0000010103":
					cont += "<requestParams pageStart=\"1\" pageSize=\"1000\"><orgCode>";
					cont += Basesence.getorgCode();
					cont += "</orgCode></requestParams>";
					break;
				case "M0100020101":
					if (sence != null) {
						cont = sence;
						sence = null;
					} else {
						cont = "<requestParams pageStart=\"1\" pageSize=\"500\"><healthRecord>"
								+ "<householdRegisterCode>"
								+ Basesence.getAdminAreaCode()
								+ "</householdRegisterCode></healthRecord></requestParams>";
					}
					break;
				case "M0100070201":
					cont += sence;
					break;
				case "M0100020201":
				case "M0100020202":
					cont += "<requestParams><healthRecord><machineCode>"
							+ Basesence.getmac(context)
							+ "</machineCode> <machineNo>"
							+ Basesence.getuuid() + "</machineNo>" + sence
							+ "</healthRecord></requestParams>";
					break;
				case "M0100030201":
				case "M0100060202":
					cont += sence;
					sence = null;
					break;
				// ///////////////////曾德森添加
				case "M0100050201":
				case "M0100040201":
				case "M0100040202":
				case "M0100040203":
				case "M0100040204":
					cont = sence;
					sence = null;
					break;
				/////////////////////Guoquan添加
				case "M0100080101":	
				case "M0100080201":
					cont += "<requestparams><jkcl><responsibleDoctorCode>"
						  + Basesence.getTempHealthRecord_down()
							.getResponsibleDoctorCode();
					cont += "</responsibleDoctorCode>";
					cont += sence +"</jkcl>"+ "</requestparams>";
					sence = null;
					break;	  	
					
				default:
					break;
				}
				Log.e("xml",cont);
				cont = Base64.encodeToString(cont.getBytes(), Base64.DEFAULT);
				// byte[] tmp = Base64.decode(cont,0);
				// cont = new String(tmp);
				xml += cont;
				xml += "]]></content>";
			}
			xml += "</request-message>";
		}
		/*
		 * ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		 * ↑↑↑↑↑ 构造通用的xml的content节点，G开头的method不包含该节点并且需要base64编码
		 * ================
		 * ==============================================================
		 */

		/**
		 * ====================================================================
		 * == ====== 向给定的handler传递得到的消息
		 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		 */

		// ///李啟晖添加。

		String tmpxml = xml;
		// 判断是否有网络，如果没网就保存数据库
		if (!WebTool.isNetConnected(context) && !xmlFlag) {
			RequestMessage message = new RequestMessage();
			String type = "";
			String primaryKey = "";
			String name = "";
			// tag是否可以上传的标记
			boolean tag = false;
			if (object instanceof DiabetesFlup_upload) {
				sence = Util
						.setDiabetesFlup_Upload((DiabetesFlup_upload) object);
				type = "DiabetesFlup_upload";
				primaryKey = ((DiabetesFlup_upload) object).getId() + "";
				name = ((DiabetesFlup_upload) object).getName();
				tag = true;
			}
			if (object instanceof HypertensionFlup_upload
					&& method == "M0100060202") {
				sence = Util
						.setHypertensionFlup_Upload((HypertensionFlup_upload) object);
				type = "HypertensionFlup_upload";
				primaryKey = ((HypertensionFlup_upload) object).getId() + "";
				name = ((HypertensionFlup_upload) object).getName();
				tag = true;
			}
			if (object instanceof HealthRecord_down && method == "M0100020201"
					|| method == "M0100020202") {
				sence = Util.setHealthRecord_Upload((HealthRecord_down) object);
				type = "HealthRecord_down";
				primaryKey = ((HealthRecord_down) object).getPersonId() + "";
				name = ((HealthRecord_down) object).getName();
				tag = true;
			}
			if (object instanceof HealthExamination && method == "M0100030201") {

				// 啟晖修改
				sence = Util.setHealthExamination((HealthExamination) object);
				if (!TextUtils.isEmpty(((HealthExamination) object)
						.getBusinessKey())) {
					primaryKey = ((HealthExamination) object).getBusinessKey();
				} else {
					primaryKey = System.currentTimeMillis() + "";
					((HealthExamination) object).setBusinessKey(primaryKey);
				}
				type = "HealthExamination";
				name = ((HealthExamination) object).getName();
				tag = true;
			}
			if (object instanceof PregnantFirstCheck_upload) {
				sence = Util
						.setPregnantFirstCheck_Upload((PregnantFirstCheck_upload) object);
				type = "PregnantFirstCheck_upload";
				primaryKey = ((PregnantFirstCheck_upload) object).getId() + "";
				name = ((PregnantFirstCheck_upload) object).getName();
				tag = true;
			}
			if (object instanceof PregnantRecheck_upload) {
				type = "PregnantRecheck_upload";
				sence = Util
						.setPregnantRecheck_Upload((PregnantRecheck_upload) object);
				primaryKey = ((PregnantRecheck_upload) object).getId() + "";
				name = ((PregnantRecheck_upload) object).getName();
				tag = true;
			}
			if (object instanceof PostpartumVisit_upload) {
				sence = Util
						.setPostpartumVisit_Upload((PostpartumVisit_upload) object);
				type = "PostpartumVisit_upload";
				primaryKey = ((PostpartumVisit_upload) object).getId() + "";
				name = ((PostpartumVisit_upload) object).getName();
				tag = true;
			}
			if (object instanceof Postpartum42Visit_upload) {
				sence = Util
						.setPostpartum42Visit_Upload((Postpartum42Visit_upload) object);
				type = "Postpartum42Visit_upload";
				primaryKey = ((Postpartum42Visit_upload) object).getId() + "";
				name = ((Postpartum42Visit_upload) object).getName();
				tag = true;
			}
			if (object instanceof NeonatalVisit_upload) {
				type = "NeonatalVisit_upload";
				sence = Util
						.setNeonatalVisit_Upload((NeonatalVisit_upload) object);
				primaryKey = ((NeonatalVisit_upload) object).getId() + "";
				name = ((NeonatalVisit_upload) object).getName();
				tag = true;
			}
			if (tag) {
				message.setUuid(type + ";" + primaryKey);
				message.setXml(tmpxml);
				message.setName(name);
				message.setState("0");
				if (AppDB.add(message) && AppDB.add(object)) {
					handler.obtainMessage(Basesence.commufail, 1234, 4321,
							"保存到数据库成功").sendToTarget();
				} else {
					handler.obtainMessage(Basesence.commufail, 1234, 4321,
							"保存到数据库失败").sendToTarget();
				}
			}
			return;
		}
		
		// 以下修改了部分内容
		class MyThread extends Thread {
			String tmpxml = "";
			String uuid = "";

			MyThread(String tmpxml, String uuid) {
				this.tmpxml = tmpxml;
				this.uuid = uuid;
			}

			@Override
			public void run() {
				Log.i("xml", tmpxml);
				// if(tmpxml.contains("</request-message>")){
				SoapObject so = new SoapObject(namespace, webmethod);
				so.addProperty("arg0", tmpxml);
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.bodyOut = so;
				envelope.setOutputSoapObject(so);
				Object results2 = new Object();
				try {
					HttpTransportSE ht = new HttpTransportSE(url, 20000);
					// ht.debug = true;
					ht.call(namespace + method, envelope);
					// results = (SoapObject)envelope.getResponse();
					results2 = envelope.getResponse();
					// //Log.i("results", results2.toString());
					result = results2.toString();
					// //system.out.println(result);
					// results = (SoapObject) envelope.bodyIn;
				} catch (IOException e) {
					// Log.i("%%IOException##", "IOException");
					handler.obtainMessage(Basesence.commufail, "IOException")
							.sendToTarget();
					e.printStackTrace();
					return;
				} catch (XmlPullParserException e) {
					handler.obtainMessage(Basesence.commufail,
							"XmlPullParserException").sendToTarget();
					// Log.i("%%XmlPullParserException##",
					// "XmlPullParserException");
					e.printStackTrace();
					return;
				} catch (Exception e) {
					handler.obtainMessage(Basesence.commufail, "不知名的异常")
							.sendToTarget();
					// Log.i("%%Exception", "不知名的异常");
					e.printStackTrace();
					return;
				}
				Basesence abasesence = new Basesence();
				int arg1 = 0;
				try {
					Field f = abasesence.getClass().getDeclaredField(method);
					arg1 = f.getInt(null);
				} catch (NoSuchFieldException e) {
					handler.obtainMessage(Basesence.commufail).sendToTarget();
					e.printStackTrace();
					return;
				} catch (IllegalAccessException e) {
					handler.obtainMessage(Basesence.commufail).sendToTarget();
					e.printStackTrace();
					return;
				} catch (IllegalArgumentException e) {
					handler.obtainMessage(Basesence.commufail).sendToTarget();
					e.printStackTrace();
					return;
				}
				int status = result.indexOf("<status>") + 8;
				int fstatus = result.indexOf("</status>");
				int status0 = Integer.parseInt(result
						.substring(status, fstatus));
				int o = result.indexOf("CDATA[") + 6;
				int to = result.indexOf("]]>");
				int mm1 = result.indexOf("Description>");
				int mm2 = result.indexOf("</statusDescription>");
				String mm = "";
				if (mm1 != -1 && mm2 != -1)
					mm = result.substring(mm1 + 12, mm2);
				if (o != -1 && to != -1) {
					result = result.substring(o, to);
					byte[] tmp = Base64.decode(result, 0);
					result = new String(tmp);
					// //system.out.println(result);
				} else
					result = null;
				if (status0 == 1) {
					handler.obtainMessage(Basesence.commufail, mm)
							.sendToTarget();

				} else if (result == null) {
					handler.obtainMessage(Basesence.commusucc, arg1, status0,
							mm).sendToTarget();
					AppDB.setRequestMessageStateOK(uuid);
				} else {
					handler.obtainMessage(Basesence.commusucc, arg1, status0,
							result).sendToTarget();
					AppDB.setRequestMessageStateOK(uuid);
				}
				// system.out.println(uuid);
				Fetch_by_li.xmlFlag = false;
				Fetch_by_li.mUUID = "";
				Fetch_by_li.uploadXML = "";

			}
		}
		;
		MyThread mythread = new MyThread(tmpxml, mUUID);
		mythread.start();
		/*
		 * ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		 * ↑↑↑↑↑ 向给定的handler传递得到的消息
		 * ==============================================
		 * ================================
		 */
	}
}
