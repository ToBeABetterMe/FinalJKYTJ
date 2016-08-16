package com.c102c.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.c102c.app.utils.NetWork.NetType;
import com.health.util.T;


public class WebTool {
	/***
	 * 检测网络是否连接正常
	 * 
	 * @param context
	 * @return boolean true表示网络可以用，false表示网络不可用
	 */
	public static boolean isNetConnected(Context context) {
		NetType type = cheackNetConnection(context);
		switch (type) {
		case NET_3G:
			T.showLong(context, "当前网络为移动网络，可能会产生流量费用!");
		case NET_WIFI:
			return true;
		case NET_NONE:
		default:
			T.showLong(context, "当前网络不可用!");
			return false;
		}
	}

	/***
	 * 检查网络连接状态
	 * 
	 * @return
	 */
	public static NetType cheackNetConnection(Context context) {
		ConnectivityManager ConManager = null;
		try {
			ConManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取WIFI网络连接状态
		NetworkInfo.State wifi_state = ConManager.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI).getState(); //
		if (wifi_state == NetworkInfo.State.CONNECTED) {
			return NetType.NET_WIFI;
		} else {
			// 判断3G网络连接状态
			NetworkInfo.State mobile_state = null;
			// try {
			// //system.out.println(ConManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE));
			// if (ConManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) !=
			// null) {
			// if
			// (ConManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
			// != null) {
			// mobile_state =
			// ConManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			// }
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			if (mobile_state == NetworkInfo.State.CONNECTED) {
				return NetType.NET_3G;
			} else {
				return NetType.NET_NONE;
			}
		}
	}
}
