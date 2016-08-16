package com.c102c.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/*
 * 双向映射
 */
public class MyMap {
	private JSONObject mContent;
	private Context mContext;

	public MyMap(Context context) {
		mContent = new JSONObject();
		mContext = context;
	}

	/*
	 * 映射文件为csv文件
	 */
	public MyMap(Context context, int projectionFile) {
		mContent = new JSONObject();
		mContext = context;
		try {
			InputStream in = mContext.getResources().openRawResource(
					projectionFile);
			InputStreamReader reader = new InputStreamReader(in, "gbk");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] strings = line.split(",");
				addItem(strings[0], strings[1]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void addItem(String A, String B) throws JSONException {
		mContent.put(A, B);
		mContent.put(B, A);
	}

	public String getItem(String X) throws JSONException {
		return (String) mContent.get(X);
	}

}
