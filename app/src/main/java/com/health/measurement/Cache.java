package com.health.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 保存数据到缓存SharedPreferences
 * 
 * @author jiqunpeng
 * 
 *         创建时间：2013-10-25 上午10:22:42
 */
public class Cache {

	public static final String KRLBP = "KrlBp";

	public static final String PC300 = "PC300";
	public static final String BENECHECK = "BeneCheck";// 百捷血糖设备
	public static final String GMPUA = "GmpUa";// 尿液分析仪
	public static final String WEILION="weilion";

	private static final String USER_ID = "user_id";
	public static final String ITEM = "ITEM";
	public static final String BP = "bp";
	public static final String TEMP = "temp";
	public static final String BO = "bo";
	public static final String GLU = "glu";
	public static final String UA = "ua";
	public static final String CHOL = "chol";
	public static final String URINE = "urine";
	private static final String GROUP_ID = "group_id";
	private static final String CONTAC_NAMES = "names";
	private static final String CONTACT = "Contact";
	public static final String KRLECG = "KRLECG";
	public static final String NICKNAME = "nickname";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String SEX = "sex";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String TOKEN = "token";
	public static final String IDREADER = "idreader";
	public static final String DOCGUID = "docguid";
	public static final String VERSION="version";
	Context context;
	private SharedPreferences sharedPrefrences;
	private Editor editor;

	public Cache(Context context) {
		this.context = context;
		sharedPrefrences = context.getSharedPreferences("padhealth",
				Context.MODE_PRIVATE);
		editor = sharedPrefrences.edit();
	}

	

	/**
	 * 保存设备地址
	 * 
	 * @param device
	 * @param address
	 */
	public void saveDeviceAddress(String device, String address) {
		editor.putString(device, address);
		editor.commit();// 提交
	}

	/**
	 * 获取设备的地址
	 * 
	 * @param device
	 * @return
	 */
	public String getDeviceAddress(String device) {
		return sharedPrefrences.getString(device, null);
	}

	


}
