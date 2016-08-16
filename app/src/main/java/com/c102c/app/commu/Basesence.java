package com.c102c.app.commu;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.c102c.app.mbgy_activity.TNBSFJLActivity;
import com.c102c.app.model.*;

import android.R.integer;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Basesence {
	private static UUID uuid; // 每次上传时的requistmessageid，采用uuid
	private static String ini_token; // 初始化时使用的token ， 行政区划代码
	private static String AdminAreaName;// 村级行政区域名称
	private static String AdminAreaCode;// 村级行政区域代码
	private static String orgCode; // 医疗机构代码
	private static String orgName; // 医疗机构名称
	private static String areaName; // 行政区划名字

	private static HealthRecord_down tempHealthRecord_down;// 健康档案
	private static HypertensionSpecial_down hypertensionspecial_down;// 高血压
	private static DiabetesSpecial_down diabetesspecial_down;// 糖尿病
	private static HypertensionFlup_down hypertensionflup_down;// 高血压随访
	private static DiabetesFlup_down DiabetesFlup_down;
	private static HealthExamination healthExamination;
	private static ChildSpecial_down childdown;
	private static PregnantSpecial_down pregnantdown;

	// ///////////////曾德森10.6
	public static ChildSpecial_down childSpecial_down;
	public static PregnantSpecial_down pregnantSpecial_down;
	// ///////////////曾德森10.6

	public final static int commufail = 0x0018;
	public final static int commusucc = 0x0019;
	public final static int G0101000101 = 0x10100010;
	public final static int M0000010101 = 0x010101;
	public final static int M0000010102 = 0x010102;
	public final static int G0301000101 = 0x30100010;
	public final static int M0000010103 = 0x010103;
	public final static int M0100020101 = 0x020101;
	public final static int M0100020201 = 0x020201;
	public final static int M0100020202 = 0x020202;
	public final static int M0100030101 = 0x030101;
	public final static int M0100030201 = 0x030201;
	public final static int M0100040101 = 0x040101;
	public final static int M0100040202 = 0x040202;
	public final static int M0100040203 = 0x040203;
	public final static int M0100040204 = 0x040204;
	public final static int M0100050101 = 0x050101;
	public final static int M0100050201 = 0x050201;
	public final static int M0100060101 = 0x060101;
	public final static int M0100060102 = 0x060102;
	public final static int M0100060201 = 0x060201;
	public final static int M0100060202 = 0x060202;
	public final static int M0100070101 = 0x070101;
	public final static int M0100070102 = 0x070102;
	public final static int M0100070201 = 0x070201;
	public final static int M0100100101 = 0x100101;
	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓Blunce添加↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 测量数据使用
	public final static int MEASURE = 0x00000001;
	public final static int MEASURE_RESULT_ETMP = 0x00000002;
	public final static int MEASURE_RESULT_LEFT_BLOOD_PRESURE = 0x00000003;
	public final static int MEASURE_RESULT_RIGHT_BLOOD_PRESURE = 0x00000004;
	public final static int MEASURE_RESULT_BLOOD_SUGAR = 0x00000005;
	public final static int MEASURE_RESULT_BLOOD_OXYGEN = 0x00000006;
	public final static int MEASURE_RESULT_XIN_DIAN = 0x00000007;
	public final static int MEASURE_RESULT_URINE = 0x00000008;
	public final static int MEASURE_RESULT_WBC = 0x00000009;

	// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑Blunce添加↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓曾德森添加↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public final static int M0100040201 = 0x040201;

	// 健康档案编号
	private static String HealFileNumber;

	// 后期肯定会出现错误的地方，我也是醉了
	public static String getHealFileNumber() {
		return "70308061";
	}

	public static void setHealFileNumber(String healFileNumber) {
		HealFileNumber = healFileNumber;
	}

	// 医生
	private static User_down user;

	public static User_down getUser() {
		return user;
	}

	public static void setUser(User_down user) {
		Basesence.user = user;
	}

	// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑曾德森添加↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// static class myhandler extends Handler {
	// public myhandler(Object object){
	// myhandler.object=object;
	// }
	// public static Object object;
	// public static Handler getupdatehandler(final Context context) {
	// Handler handler = new Handler() {
	// public void handleMessage(Message msg) {
	// Field f;
	// try {
	// f = object.getClass().getField("state");
	// f.setAccessible(true);
	// } catch (NoSuchFieldException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// if (msg.what == Basesence.commusucc) {
	// if (msg.obj != null)
	// Toast.makeText(context, "成功", Toast.LENGTH_SHORT)
	// .show();
	// else
	// Toast.makeText(context, "成功", Toast.LENGTH_SHORT)
	// .show();
	// f.set(object, "");
	// } else if (msg.what == Basesence.commufail) {
	// if (msg.obj != null)
	// Toast.makeText(context, "接口调用失败" + msg.obj,
	// Toast.LENGTH_LONG).show();
	// else
	// Toast.makeText(context, "接口调用失败", Toast.LENGTH_SHORT)
	// .show();
	// }
	//
	// };
	// };
	// return handler;
	// }
	// }

	public static Handler getupdatehandler(final Context context) {
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == Basesence.commusucc) {
					if (msg.obj != null)
						Toast.makeText(context, "成功", Toast.LENGTH_SHORT)
								.show();
					else
						Toast.makeText(context, "成功", Toast.LENGTH_SHORT)
								.show();
				} else if (msg.what == Basesence.commufail) {
					if (msg.obj != null)
						Toast.makeText(context, "接口调用失败" + msg.obj,
								Toast.LENGTH_LONG).show();
					else
						Toast.makeText(context, "接口调用失败", Toast.LENGTH_SHORT)
								.show();
				}

			};
		};
		return handler;
	}

	public static void setinisec(String x) {
		ini_token = x;
		return;
	}

	public static String getini_token() {
		return ini_token;
	}

	public static void setareaName(String x) {
		areaName = x;
		return;
	}

	public static String getareaName() {
		return areaName;
	}

	public static void setorgName(String x) {
		orgName = x;
		return;
	}

	public static String getorgName() {
		return orgName;
	}

	public static void setorgCode(String x) {
		orgCode = x;
		return;
	}

	public static String getorgCode() {
		return orgCode;
	}

	public static void setAdminAreaName(String x) {
		AdminAreaName = x;
		return;
	}

	public static String getAdminAreaName() {
		return AdminAreaName;
	}

	public static void setAdminAreaCode(String x) {
		AdminAreaCode = x;
		return;
	}

	public static String getAdminAreaCode() {
		return AdminAreaCode;
	}

	public static String getuuid() {
		uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String gettime() {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df1.format(new Date());
		// return df1.toString();
	}

	public static String getmac(Context acont) {
		WifiManager wifi = (WifiManager) acont
				.getSystemService(acont.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public static HealthRecord_down getTempHealthRecord_down() {
		return tempHealthRecord_down;
	}

	public static void setTempHealthRecord_down(HealthRecord_down tmp) {
		tempHealthRecord_down = tmp;
	}

	public static HypertensionSpecial_down getHypertensionspecial_down() {
		return hypertensionspecial_down;
	}

	public static void setHypertensionspecial_down(
			HypertensionSpecial_down hypertensionspecial_down) {
		Basesence.hypertensionspecial_down = hypertensionspecial_down;
		return;
	}

	public static DiabetesSpecial_down getDiabetesspecial_down() {
		return diabetesspecial_down;
	}

	public static void setDiabetesspecial_down(
			DiabetesSpecial_down diabetesspecial_down) {
		Basesence.diabetesspecial_down = diabetesspecial_down;
	}

	public static HypertensionFlup_down gethypertensionflup_down() {
		return hypertensionflup_down;
	}

	public static void sethypertensionflup_down(
			HypertensionFlup_down mhypertensionflup_down) {
		Basesence.hypertensionflup_down = mhypertensionflup_down;
	}

	public static DiabetesFlup_down getDiabetesFlup_down() {
		return DiabetesFlup_down;
	}

	public static void setDiabetesFlup_down(DiabetesFlup_down diabetesFlup_down) {
		DiabetesFlup_down = diabetesFlup_down;
	}

	public static HealthExamination getHealthExamination() {
		return healthExamination;
	}

	public static void setHealthExamination(HealthExamination healthExamination) {
		Basesence.healthExamination = healthExamination;
	}

	public static ChildSpecial_down getChilddown() {
		return childdown;
	}

	public static void setChilddown(ChildSpecial_down childdown) {
		Basesence.childdown = childdown;
	}

	public static PregnantSpecial_down getPregnantdown() {
		return pregnantdown;
	}

	public static void setPregnantdown(PregnantSpecial_down pregnantdown) {
		Basesence.pregnantdown = pregnantdown;
	}

	public static PregnantSpecial_down getPregnantSpecial_down() {
		return pregnantSpecial_down;
	}

	public static void setPregnantSpecial_down(
			PregnantSpecial_down pregnantSpecial_down) {
		Basesence.pregnantSpecial_down = pregnantSpecial_down;
	}

	// ///////////////曾德森10.6
	public static ChildSpecial_down getChildSpecial_down() {
		return childSpecial_down;
	}

	public static void setChildSpecial_down(ChildSpecial_down childSpecial_down) {
		Basesence.childSpecial_down = childSpecial_down;
	}
	// ///////////////曾德森10.6
}
