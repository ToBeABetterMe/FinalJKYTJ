package com.c102c.app.commu;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;

import org.apache.http.client.HttpResponseException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.c102c.app.model.User_down;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;

public class Fetch {
	private static String url = "http://219.223.247.25:8000/api/PlatformService.wsdl";
	private static String namespace = "http://219.223.247.25:8000/api/PlatformService.wsdl";
	private static String webmethod = "send";
	private static String result;
	private static String sence;

	public static void communicate(final String method, Context context,
			final Handler handler, String string) {
		sence = string;
		communicate(method, context, handler);
	}

	public static void communicate(final String method, Context context,
			final Handler handler) {

		/**
		 * 
		 * ====================================================================
		 * 
		 * == ====== ����ͨ�õ�xmlͷ �μ��ĵ�
		 * 
		 * ������������������������������������������������������������������������������������������������
		 * 
		 * ����������������������������������������������������������
		 */
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><req"
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
		/*
		 * 
		 * ��������������������������������������������������������������������������������������������������������������������������������������������
		 * 
		 * ���������� ����ͨ�õ�xmlͷ �μ��ĵ�
		 * 
		 * ==================================================
		 * 
		 * ============================
		 */

		/**
		 * 
		 * ====================================================================
		 * 
		 * == ====== ����ͨ�õ�xml��content�ڵ㣬G0101000101��method�������ýڵ㲢����Ҫbase64����
		 * 
		 * ��������������
		 * 
		 * ������������������������������������������������������������������������������������������������������������������������������
		 * 
		 * ��������������
		 */
		if (!method.equalsIgnoreCase("G0101000101")) {
			// ����������������������������������������������������������������������ɭ��ӡ�����������������������������������������������������������������

			if (method.equals("M0100050201") || method.equals("M0100040201")
					|| method.equals("M0100040202")
					|| method.equals("M0100040203")
					|| method.equals("M0100040204")) {
				xml += "<healthFileNumber>"
						+ Basesence.getHealFileNumber()
						+ "</healthFileNumber><businessDate>2015-9-33 </businessDate>";
			}

			// ����������������������������������������������������������������������ɭ��ӡ�����������������������������������������������������������������

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
						+ "</machineCode> <machineNo>" + Basesence.getuuid()
						+ "</machineNo>" + sence
						+ "</healthRecord></requestParams>";
				break;
			case "M0100030201":
			case "M0100060202":
				cont += sence;
				sence = null;
				break;
			// ����������������������������������������������������������������������ɭ��ӡ�����������������������������������������������������������������

			case "M0100050201":
			case "M0100040201":
			case "M0100040202":
			case "M0100040203":
			case "M0100040204":
				cont = sence;
				sence = null;
				break;

			// ����������������������������������������������������������������������ɭ��ӡ�����������������������������������������������������������������

			default:
				break;
			}
			cont = Base64.encodeToString(cont.getBytes(), Base64.DEFAULT);
			// byte[] tmp = Base64.decode(cont,0);

			// cont = new String(tmp);

			xml += cont;
			xml += "]]></content>";
		}
		xml += "</request-message>";

		/*
		 * 
		 * ��������������������������������������������������������������������������������������������������������������������������������������������
		 * 
		 * ���������� ����ͨ�õ�xml��content�ڵ㣬G��ͷ��method�������ýڵ㲢����Ҫbase64����
		 * 
		 * ================
		 * 
		 * ==============================================================
		 */

		/**
		 * 
		 * ====================================================================
		 * 
		 * == ====== �������handler���ݵõ�����Ϣ
		 * 
		 * ����������������������������������������������������������������������������������������
		 * 
		 * ������������������������������������������������������������������
		 */

		final String tmpxml = xml;
		Thread athread = new Thread() {
			@Override
			public void run() {
				Log.i("xml", tmpxml);

				// Systemtem.out.println(tmpxml);

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
					ht.debug = true;
					
					ht.call(namespace + method, envelope);
					results2 = envelope.getResponse();
					//Log.i("results",ht.responseDump);
					//results2 = envelope.getResponse();
					//SoapObject detail = (SoapObject) results2.getProperty(method+"Result");
					
					//Log.i("results", results2.toString());
					//result =  results2.toString();
					result = results2.toString();
//					Log.i("results", result);
//					System.out.println(result);
					

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
					handler.obtainMessage(Basesence.commufail, "��֪�����쳣")
							.sendToTarget();
					// Log.i("%%Exception", "��֪�����쳣");
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
//					System.out.println(result);

				} else
					result = null;
//				System.out.println(result);
				if (status0 == 1) {
					handler.obtainMessage(Basesence.commufail, mm)
							.sendToTarget();
				} else if (result == null)
					handler.obtainMessage(Basesence.commusucc, arg1, status0,
							mm).sendToTarget();
				else
					handler.obtainMessage(Basesence.commusucc, arg1, status0,
							result).sendToTarget();
			}
		};
		athread.start();
		/*
		 * 
		 * ��������������������������������������������������������������������������������������������������������������������������������������������
		 * 
		 * ���������� �������handler���ݵõ�����Ϣ
		 * 
		 * ==============================================
		 * 
		 * ================================
		 */
	}
}