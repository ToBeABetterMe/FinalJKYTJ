package com.c102c.app.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.c102c.app.activity.MainActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.model.Area_down;
import com.c102c.app.model.Org_down;
import com.c102c.app.model.User_down;
import com.c102c.app.utils.MyMap;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InitalActivity extends Activity {
	/** 大行政机构代码 */
	private String mAreaCode;// 行政区划代码
	/** 大行政区划名字 */
	private String mAreaName;// 行政区划名字
	/** 医疗机构名称 */
	private String mMedOrgName;// 医疗机构名称
	/** 医疗机构代码 */
	private String mMedOrgCode;// 医疗机构代码
	/** 小行政区域名称 */
	private String mAdminAreaName;// 行政区域名称
	/** 小行政区域代码 */
	private String mAdminAreaCode;// 行政区域代码
	String lastAreaCode = "";
	// private ArrayList<Org_down> tempOrg_downList;
	private ArrayList<String> AdminAreaStrings;
	private ArrayList<String> MedOrgStrings;
	Button btn, btn_ok;
	LayoutInflater flate;
	/** 大区 */
	public final static String AREA_NAME = "area_name";
	public final static String AREA_CODE = "area_code";
	/** 医疗机构 */
	public final static String MEDICAL_ORG_NAME = "med_org_name";
	public final static String MEDICAL_ORG_CODE = "med_org_code";
	/** 小区 */
	public final static String ADMIN_AREA_NAME = "admin_area_name";
	public final static String ADMIN_AREA_CODE = "admin_area_code";
	private Context context;
	List<Fragment> fragmentlist;
	FragmentManager manager;
	Dialog dialog;
	SharedPreferences sp;
	ArrayList<Area_down> tempArea_downList1, tempArea_downList2,
			tempArea_downList3;
	ArrayList<String> AdminAreaStrings1, AdminAreaStrings2, AdminAreaStrings3;
	List<Area_down> area_downList;
	List<Org_down> all_list, all_list1, all_list2;
	List<String> all_1, all_2;
	List<User_down> user_list;
	ListView listview;
	String org_name = "";
	ArrayAdapter<String> AdminAreaAdapter1;
	String mAdminAreaName1 = "", mAdminAreaName2 = "", mAdminAreaName3 = "";
	String from = null;
	Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
	MyAdapter adapter;
	ProgressBar bar;
	LinearLayout linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		context = this;
		flate = LayoutInflater.from(this);
		from = getIntent().getStringExtra("from");
		sp = context.getSharedPreferences("AKTD", 0);
		if (sp.getBoolean("is_use", false) && from == null) {
			// 判断是不是以第一次注册
			mAreaCode = sp.getString(AREA_CODE, "");
			mAreaName = sp.getString(AREA_NAME, "");
			mAdminAreaCode = sp.getString(ADMIN_AREA_CODE, "");
			mAdminAreaName = sp.getString(ADMIN_AREA_NAME, "");
			mMedOrgCode = sp.getString(MEDICAL_ORG_CODE, "");
			mMedOrgName = sp.getString(MEDICAL_ORG_NAME, "");
			String username = sp.getString("username", "");
			String usercode = sp.getString("usercode", "");
			User_down user = new User_down();
			user.setUserCode(usercode);
			user.setUserName(username);
			Basesence.setUser(user);
			Basesence.setAdminAreaCode(mAdminAreaCode);
			Basesence.setAdminAreaName(mAdminAreaName);
			Basesence.setinisec(mAreaCode);
			Basesence.setareaName(mAreaName);
			Basesence.setorgCode(mMedOrgCode);
			Basesence.setorgName(mMedOrgName);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		spinner1 = (Spinner) findViewById(R.id.spinner_area);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		spinner4 = (Spinner) findViewById(R.id.spinner4);
		spinner5 = (Spinner) findViewById(R.id.spinner5);
		bar = (ProgressBar) findViewById(R.id.waitProgressBar);
		linear = (LinearLayout) findViewById(R.id.linear);

		btn_ok = (Button) findViewById(R.id.btn_ok);
		listview = (ListView) findViewById(R.id.user_list);
		listview.setOnItemClickListener(listener);
		context = this.getApplicationContext();
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (from != null && from.equals("1")) {
					Toast.makeText(context, "请选择医生", 0).show();
					return;
				} else if (Basesence.getUser() == null) {
					Toast.makeText(context, "请选择医生", 0).show();
					return;
				}
				Editor ed = sp.edit();
				ed.putString(AREA_CODE, Basesence.getini_token());
				ed.putString(AREA_NAME, Basesence.getareaName());
				ed.putString(ADMIN_AREA_CODE, Basesence.getAdminAreaCode());
				ed.putString(ADMIN_AREA_NAME, Basesence.getareaName()
						+ Basesence.getAdminAreaName());
				ed.putString(MEDICAL_ORG_CODE, Basesence.getorgCode());
				ed.putString(MEDICAL_ORG_NAME, Basesence.getorgName());
				ed.putString("username", Basesence.getUser().getUserName());
				ed.putString("usercode", Basesence.getUser().getUserCode());
				// 不是第一次使用
				ed.putBoolean("is_use", true);
				ed.commit();
				Basesence.setAdminAreaName(Basesence.getareaName()
						+ Basesence.getAdminAreaName());
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		final MyMap areaCode = new MyMap(context, R.raw.area_code);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				R.layout.inital_sipinner_item_layout, loadAreaName());
		spinner1.setAdapter(adapter);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				try {
					bar.setVisibility(View.VISIBLE);
					linear.setVisibility(View.INVISIBLE);
					spinner5.setVisibility(View.INVISIBLE);
					btn_ok.setVisibility(View.INVISIBLE);
					listview.setVisibility(View.INVISIBLE);
					mAreaName = adapter.getItem(arg2);
					mAreaCode = areaCode.getItem(adapter.getItem(arg2));
					Basesence.setinisec(mAreaCode);
					Basesence.setareaName(mAreaName);
					Fetch.communicate("M0000010101", context, handler);
					//system.out
//							.println("第一次选择" + mAreaCode + "name" + mAreaName);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String result;
			if (msg.what == Basesence.commusucc) {
				switch (msg.arg1) {
				case Basesence.M0000010101:// 行政区域下载
					result = (String) msg.obj;
					// //system.out.println("行政区域下载"+result);
					bar.setVisibility(View.INVISIBLE);
					linear.setVisibility(View.VISIBLE);
					two(result);
					break;
				// 医疗机构
				case Basesence.M0000010102:
					result = (String) msg.obj;
					three(result);
					bar.setVisibility(View.INVISIBLE);
					break;
				// 注册
				case Basesence.G0301000101:
					Toast.makeText(context, Basesence.getorgName() + msg.obj, 0)
							.show();
					//system.out.println(msg.obj);
					bar.setVisibility(View.INVISIBLE);
					spinner5.setVisibility(View.VISIBLE);
					four();
					break;
				// 下载用户
				case Basesence.M0000010103:
					String a = (String) msg.obj;
					if (a.indexOf("xml") == -1) {
						Toast.makeText(context, a, 0);

						return;
					}
					//system.out.println(a);
					user_list = Util.getUser_down(a);
					if (user_list.size() <= 0) {
						Toast.makeText(context, "该村没有医生", 0).show();
						return;
					}
					AppDB app = AppDB.getInstance(context);
					app.User_delete();
					for (int i = 0; i < user_list.size(); i++) {
						app.add(user_list.get(i));
					}
					listview.setVisibility(View.VISIBLE);
					adapter.notifyDataSetChanged();
					btn_ok.setVisibility(View.VISIBLE);
					//system.out.println("执行");
					break;

				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				switch (msg.arg1) {
				case Basesence.M0000010101:
					Toast.makeText(context, "医疗机构没有信息", 0).show();

					break;

				default:
					break;
				}
			}
			super.handleMessage(msg);
		}
	};

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

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void two(String areaxml) {
		String areaXML = areaxml;
		//system.out.println("结果" + areaXML);
		area_downList = Util.getArea_down(areaXML);
		tempArea_downList1 = new ArrayList<Area_down>();
		AdminAreaStrings1 = new ArrayList<String>();
		for (Area_down area_down : area_downList) {
			if ("21".equals(area_down.getAreaType())) {
				tempArea_downList1.add(area_down);
				AdminAreaStrings1.add(area_down.getAreaName());
			}
		}
		final ArrayAdapter<String> AdminAreaAdapter = new ArrayAdapter<String>(
				context, R.layout.inital_sipinner_item_layout,
				AdminAreaStrings1);
		spinner2.setAdapter(AdminAreaAdapter);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mAdminAreaName1 = AdminAreaStrings1.get(arg2);
				tempArea_downList2 = new ArrayList<Area_down>();
				AdminAreaStrings2 = new ArrayList<String>();
				String areacode = tempArea_downList1.get(arg2).getAreaCode();
				for (Area_down area_down : area_downList) {
					if (areacode.equals(area_down.getParentAreaCode())) {
						tempArea_downList2.add(area_down);
						AdminAreaStrings2.add(area_down.getAreaName());
					}
				}
				ArrayAdapter<String> AdminAreaAdapter = new ArrayAdapter<String>(
						context, R.layout.inital_sipinner_item_layout,
						AdminAreaStrings2);
				spinner3.setAdapter(AdminAreaAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				tempArea_downList3 = new ArrayList<Area_down>();
				AdminAreaStrings3 = new ArrayList<String>();
				String areacode = tempArea_downList2.get(arg2).getAreaCode();
				for (Area_down area_down : area_downList) {
					if (areacode.equals(area_down.getParentAreaCode())) {
						tempArea_downList3.add(area_down);
						AdminAreaStrings3.add(area_down.getAreaName());
					}
				}
				mAdminAreaCode = tempArea_downList2.get(arg2).getAreaCode();
				mAdminAreaName2 = AdminAreaStrings2.get(arg2);
				if (AdminAreaStrings2.size() == 0) {
					bar.setVisibility(View.VISIBLE);
					//system.out.println("空值");
					Fetch.communicate("M0000010102", context, handler);
					bar.setVisibility(View.VISIBLE);

					spinner5.setVisibility(View.INVISIBLE);
					btn_ok.setVisibility(View.INVISIBLE);
					listview.setVisibility(View.INVISIBLE);
					Basesence.setAdminAreaCode(mAdminAreaCode);
					Basesence.setAdminAreaName(mAdminAreaName1
							+ mAdminAreaName2);
					return;
				}
				//system.out.println("第二次选择" + mAdminAreaName1 + "CODE"
//						+ mAdminAreaCode);

				ArrayAdapter<String> AdminAreaAdapter = new ArrayAdapter<String>(
						context, R.layout.inital_sipinner_item_layout,
						AdminAreaStrings3);
				spinner4.setAdapter(AdminAreaAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		spinner4.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				/*
				 * mAdminAreaCode = tempArea_downList3.get(arg2).getAreaCode();
				 * mAdminAreaCode = mAdminAreaCode.substring(0,
				 * mAdminAreaCode.length() - 3);
				 */
				mAdminAreaName3 = AdminAreaStrings3.get(arg2);
				if (AdminAreaStrings3.size() == 0) {
					//system.out.println("空值");
					return;
				}
				bar.setVisibility(View.VISIBLE);

				spinner5.setVisibility(View.INVISIBLE);
				btn_ok.setVisibility(View.INVISIBLE);
				listview.setVisibility(View.INVISIBLE);
				//system.out.println("第二次选择" + mAdminAreaName2 + "CODE"
//						+ mAdminAreaCode);
				Basesence.setAdminAreaCode(mAdminAreaCode);
				Basesence.setAdminAreaName(mAdminAreaName1 + mAdminAreaName2
						+ mAdminAreaName3);
				Fetch.communicate("M0000010102", context, handler);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void three(String xml) {
		String areaXML = xml;
		//system.out.println("结果" + areaXML);
		all_list = Util.getOrg_down(areaXML);
		all_list1 = new ArrayList<Org_down>();
		all_list2 = new ArrayList<Org_down>();
		all_1 = new ArrayList<String>();
		all_2 = new ArrayList<String>();
		for (int i = 0; i < all_list.size(); i++) {
			if (all_list.get(i).getOrgCode().equals(Basesence.getini_token())) {
				//system.out.println(all_list.get(i).getOrgName() + "代码"
//						+ all_list.get(i).getOrgCode());
				org_name = all_list.get(i).getOrgName();
			}
		}
		for (Org_down org : all_list) {
			if (org.getAreaCode().equals(Basesence.getAdminAreaCode())) {
				all_list1.add(org);
				all_1.add(org.getOrgName());
			}
		}
		final ArrayAdapter<String> AdminAreaAdapter = new ArrayAdapter<String>(
				context, R.layout.inital_sipinner_item_layout, all_1);
		spinner5.setAdapter(AdminAreaAdapter);
		spinner5.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String orgCode = all_list1.get(arg2).getOrgCode();
				String orgName = org_name + all_list1.get(arg2).getOrgName();
				Basesence.setorgName(orgName);
				Basesence.setorgCode(orgCode);

				btn_ok.setVisibility(View.INVISIBLE);
				listview.setVisibility(View.INVISIBLE);
				Fetch.communicate("G0301000101", context, handler);
				bar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	private void four() {
		adapter = new MyAdapter();
		user_list = new ArrayList<User_down>();
		listview.setAdapter(adapter);
		// bar.setVisibility(View.VISIBLE);
		Fetch.communicate("M0000010103", this, handler);
	}

	class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return user_list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return user_list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			A a;
			if (view == null) {
				view = flate.inflate(R.layout.user_item, null);
				a = new A();
				a.txt_name = (TextView) view.findViewById(R.id.txt_name);
				view.setTag(a);
			} else {
				a = (A) view.getTag();
			}
			a.txt_name.setText(user_list.get(position).getUserName());
			return view;
		}

		class A {
			private TextView txt_name;
		}
	}

	OnItemClickListener listener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(context, user_list.get(position).getUserCode(), 0)
					.show();
			showDialog(user_list.get(position).getUserName(),
					user_list.get(position));
		}
	};

	private void showDialog(String msg, final User_down user) {
		AlertDialog.Builder buil = new AlertDialog.Builder(this)
				.setMessage("确定选择" + msg + "医生？").setTitle("提示")
				.setNegativeButton("确定", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						from = "0";
						Basesence.setUser(user);
					}
				}).setNeutralButton("取消", null);
		dialog = buil.create();
		dialog.show();
	}
	//

	/*
	 * @Override public void selectArea(String result,Area_down area) { // TODO
	 * Auto-generated method stub areafragment1=new AreaFragment1();
	 * fragmentlist.add(areafragment1); areafragment1.setListener(this);
	 * FragmentTransaction trans=manager.beginTransaction();
	 * trans.hide(areafragment); Bundle bundle = new Bundle();
	 * bundle.putString("areaXml", result); areafragment1.setArguments(bundle);
	 * // trans.add(R.id.fragment, areafragment1); trans.commit(); }
	 * 
	 * @Override public void selectOrg(String result) { // TODO Auto-generated
	 * method stub Toast.makeText(context, result, 0).show(); Editor
	 * ed=sp.edit(); ed.putString(AREA_CODE, Basesence.getini_token());
	 * ed.putString(AREA_NAME, Basesence.getareaName());
	 * ed.putString(ADMIN_AREA_CODE, Basesence.getAdminAreaCode());
	 * ed.putString(ADMIN_AREA_NAME, Basesence.getareaName()+
	 * Basesence.getAdminAreaName()); ed.putString(MEDICAL_ORG_CODE,
	 * Basesence.getorgCode()); ed.putString(MEDICAL_ORG_NAME,
	 * Basesence.getorgName()); //不是第一次使用 ed.putBoolean("is_use", true);
	 * ed.commit(); Intent intent=new Intent(this,LoginActivity.class);
	 * startActivity(intent); finish(); }
	 * 
	 * @Override public void selectArea1(String result, Area_down area) { //
	 * TODO Auto-generated method stub // TODO Auto-generated method stub
	 * orgfragment=new OrgFragment(); fragmentlist.add(orgfragment);
	 * FragmentTransaction trans=manager.beginTransaction();
	 * trans.hide(areafragment1); orgfragment.setListener(this); Bundle bundle =
	 * new Bundle(); bundle.putString("areaXml", result);
	 * orgfragment.setArguments(bundle); // trans.add(R.id.fragment,
	 * orgfragment); trans.commit(); }
	 */
}
