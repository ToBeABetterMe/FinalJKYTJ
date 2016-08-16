package com.c102c.app.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.model.Area_down;
import com.c102c.app.model.Org_down;
import com.c102c.app.utils.MyMap;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewInitalActivity extends Activity implements OnClickListener {

	private ProgressBar mWaitProgressBar;
	private TextView mLabelTextView;
	private Spinner mChooseSpinner;
	private Button mNextButton;

	private AppDB mAppDB = null;

	public final static int AREA = 0;// 行政区划
	public final static int ADMIN_AREA = 1;// 行政区域
	public final static int MED_ORG = 2;// 医疗机构

	public final static String AREA_NAME = "area_name";
	public final static String AREA_CODE = "area_code";
	public final static String MEDICAL_ORG_NAME = "med_org_name";
	public final static String MEDICAL_ORG_CODE = "medical_org_code";
	public final static String ADMIN_AREA_NAME = "admin_area_name";
	public final static String ADMIN_AREA_CODE = "administrator_area_code";

	private String mAreaName;// 行政区划名字
	private String mAreaCode;// 行政区划代码
	private String mAdminAreaName;// 行政区域名称
	private String mAdminAreaCode;// 行政区域代码
	private String mMedOrgName;// 医疗机构名称
	private String mMedOrgCode;// 医疗机构代码

	private int state;

	private SharedPreferences mSharedPreferences;

	private ArrayAdapter<String> mAdapter;

	private List<Area_down> area_downs = new ArrayList<Area_down>();
	private List<Org_down> org_downs = new ArrayList<Org_down>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		state = getIntent().getIntExtra("register_again", AREA);
		initView();

		if (state == AREA) {
			mSharedPreferences = getSharedPreferences("inital_register",
					Context.MODE_PRIVATE);
			mAreaCode = mSharedPreferences.getString(AREA_CODE, "");
			if (mAreaCode.equals("")) {
				mAppDB = AppDB.getInstance(NewInitalActivity.this);
				final MyMap areaCode = new MyMap(NewInitalActivity.this,
						R.raw.area_code);
				mAdapter = new ArrayAdapter<String>(this,
						R.layout.inital_sipinner_item_layout, loadAreaName());
				mChooseSpinner.setAdapter(mAdapter);
				mChooseSpinner
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								try {
									mAreaName = mAdapter.getItem(arg2);
									mAreaCode = areaCode.getItem(mAdapter
											.getItem(arg2));
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub

							}
						});
			} else {
				Basesence.setorgCode(mSharedPreferences.getString(
						MEDICAL_ORG_CODE, ""));
				Basesence.setorgName(mSharedPreferences.getString(
						MEDICAL_ORG_NAME, ""));
				Basesence
						.setinisec(mSharedPreferences.getString(AREA_CODE, ""));
				Basesence.setareaName(mSharedPreferences.getString(AREA_NAME,
						""));
				Basesence.setAdminAreaCode(mSharedPreferences.getString(
						ADMIN_AREA_CODE, ""));
				Basesence.setAdminAreaName(mSharedPreferences.getString(
						ADMIN_AREA_NAME, ""));
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		} else {

		}
	}

	public List<String> loadAreaName() {
		List<String> areaNames = new ArrayList<String>();

		try {
			InputStream in = getResources().openRawResource(R.raw.area_code);
			InputStreamReader reader = new InputStreamReader(in, "gbk");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] strings = line.split(",");
				areaNames.add(strings[1]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return areaNames;
	}

	private void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_new_inital_location);
		mWaitProgressBar = (ProgressBar) findViewById(R.id.wait_ProgressBar);
		mLabelTextView = (TextView) findViewById(R.id.labelTextView);
		mChooseSpinner = (Spinner) findViewById(R.id.chooseSpinner);
		mNextButton = (Button) findViewById(R.id.nextButton);
		mNextButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.nextButton:
			switch (state) {
			case AREA:
				mLabelTextView.setVisibility(View.INVISIBLE);
				mChooseSpinner.setVisibility(View.INVISIBLE);
				mNextButton.setVisibility(View.INVISIBLE);
				mWaitProgressBar.setVisibility(View.VISIBLE);
				Basesence.setinisec(mAreaCode);
				Fetch.communicate("M0000010101", NewInitalActivity.this,
						handler);
				break;
			case ADMIN_AREA:
				mLabelTextView.setVisibility(View.INVISIBLE);
				mChooseSpinner.setVisibility(View.INVISIBLE);
				mNextButton.setVisibility(View.INVISIBLE);
				mWaitProgressBar.setVisibility(View.VISIBLE);
				Basesence.setAdminAreaCode(mAdminAreaCode);
				Basesence.setAdminAreaName(mAdminAreaName);
				Fetch.communicate("M0000010102", NewInitalActivity.this,
						handler);
				break;
			case MED_ORG:
				mLabelTextView.setVisibility(View.INVISIBLE);
				mChooseSpinner.setVisibility(View.INVISIBLE);
				mNextButton.setVisibility(View.INVISIBLE);
				mWaitProgressBar.setVisibility(View.VISIBLE);
				SharedPreferences preferences = getSharedPreferences(
						"register_again", Context.MODE_PRIVATE);
				Editor editor = preferences.edit();
				Basesence.setorgCode(mMedOrgCode);
				Basesence.setorgName(mMedOrgName);
				editor.putString(AREA_CODE, mAreaCode);
				editor.putString(AREA_NAME, mAreaName);
				editor.putString(MEDICAL_ORG_NAME, mMedOrgName);
				editor.putString(MEDICAL_ORG_CODE, mMedOrgCode);
				editor.putString(ADMIN_AREA_NAME, mAdminAreaName);
				editor.putString(ADMIN_AREA_CODE, mAdminAreaCode);
				editor.commit();
				Fetch.communicate("G0301000101", NewInitalActivity.this,
						handler);
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			if (msg.what == Basesence.commusucc) {
				String a;
				switch (msg.arg1) {
				case Basesence.M0000010101:// 行政区域下载
					a = (String) msg.obj;
					area_downs = new ArrayList<Area_down>(Util.getArea_down(a));
					showChooseAdminAreaView(area_downs);
					break;
				case Basesence.M0000010102:
					a = (String) msg.obj;
					org_downs = new ArrayList<Org_down>(Util.getOrg_down(a));
					showMedOrgView(org_downs);
					break;
				case Basesence.G0301000101:
					Toast.makeText(NewInitalActivity.this, "注册成功",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(NewInitalActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				if (msg.arg2 == 2) {
					Toast.makeText(NewInitalActivity.this, "该一体机已经注册过",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(NewInitalActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				}
				if (msg.obj != null)
					Toast.makeText(NewInitalActivity.this, "接口调用失败" + msg.obj,
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(NewInitalActivity.this, "接口调用失败",
							Toast.LENGTH_SHORT).show();
			}
		}

		private void showMedOrgView(List<Org_down> org_downs) {
			// TODO Auto-generated method stub
			
		}

		private void showChooseAdminAreaView(List<Area_down> area_downs) {
			// TODO Auto-generated method stub
			mWaitProgressBar.setVisibility(View.INVISIBLE);
			mLabelTextView.setText("行政区域选择：");
			mLabelTextView.setVisibility(View.VISIBLE);
			mChooseSpinner.setVisibility(View.VISIBLE);
		};
	};
}
