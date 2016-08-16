package com.c102c.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.c102c.app.utils.NetWork.NetType;
import com.health.util.T;


public class WebTool {
	/***
	 * ��������Ƿ���������
	 * 
	 * @param context
	 * @return boolean true��ʾ��������ã�false��ʾ���粻����
	 */
	public static boolean isNetConnected(Context context) {
		NetType type = cheackNetConnection(context);
		switch (type) {
		case NET_3G:
			T.showLong(context, "��ǰ����Ϊ�ƶ����磬���ܻ������������!");
		case NET_WIFI:
			return true;
		case NET_NONE:
		default:
			T.showLong(context, "��ǰ���粻����!");
			return false;
		}
	}

	/***
	 * �����������״̬
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
		// ��ȡWIFI��������״̬
		NetworkInfo.State wifi_state = ConManager.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI).getState(); //
		if (wifi_state == NetworkInfo.State.CONNECTED) {
			return NetType.NET_WIFI;
		} else {
			// �ж�3G��������״̬
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
