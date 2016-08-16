package com.c102c.app.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.LocalJieZhen;
import com.c102c.app.model.LocalZhuanZhen;
import com.c102c.finalJKYTJ.R;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MZYLActivity extends Activity {
	private Button jiezhenBtn;
	private Button zhuanzhenBtn;
	private Button addBtn;
	private ListView recordView;
	private Context context;

	private HealthRecord_down mHealthRecord_down;
	private List<LocalJieZhen> mJieZhens;
	private List<LocalZhuanZhen> mZhuanZhens;
	private static LocalJieZhen mlocaljiezhen;
	private static LocalZhuanZhen mlcalzhuanzhen;
	private final static String JIEZHENTABLE = "JIEZHENTABLE";
	private final static String ZHUANZHENTABLE = "ZHUANZHEN";
	private final static String[] JIEZHENTITTLE = { "序号", "编号", "接诊医生", "接诊日期" };
	private final static String[] ZHUANZHENTITTLE = { "序号", "编号", "转诊医生",
			"接诊医生", "转诊日期" };
	private static int selectBtnId = -1;
	List<Map<String, String>> list;
	private static String grxh;

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		if (selectBtnId == 0)
			loadjiezhenData();
		else if (selectBtnId == 1)
			loadzhuanzhenData();
		super.onRestart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mzyl_activity_layout);
		jiezhenBtn = (Button) findViewById(R.id.jiezhen_button);
		zhuanzhenBtn = (Button) findViewById(R.id.zhuanzhen_button);
		addBtn = (Button) findViewById(R.id.add_button);
		recordView = (ListView) findViewById(R.id.record_listview);
		context = this;

		mHealthRecord_down = Basesence.getTempHealthRecord_down();
		AppDB.getInstance(this);
		mJieZhens = AppDB.queryByPersonIdJieZhen(mHealthRecord_down
				.getPersonId());
		mZhuanZhens = AppDB.queryByPersonIdZhuanZhen(mHealthRecord_down
				.getPersonId());

		list = new ArrayList<Map<String, String>>();
		jiezhenBtn.setOnClickListener(listener);
		zhuanzhenBtn.setOnClickListener(listener);
		addBtn.setOnClickListener(listener);
		recordView.setOnItemClickListener(itemClickListener);
		recordView.setOnItemLongClickListener(itemLongClickListener);

		selectBtnId = 0;
		loadjiezhenData();
	}

	private View.OnClickListener listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == jiezhenBtn) {
				selectBtnId = 0;
				loadjiezhenData();
			} else if (v == zhuanzhenBtn) {
				selectBtnId = 1;
				loadzhuanzhenData();
			} else {
				if (selectBtnId == -1)
					Toast.makeText(context, "请选择接诊记录或者转诊记录", Toast.LENGTH_LONG)
							.show();
				else {
					addNewTable();
				}

			}
		}
	};

	private void addNewTable() {
		Intent intent = new Intent();
		if (selectBtnId == 0) {
			intent.setClass(context, JieZhenTableActivity.class);
		} else {
			intent.setClass(context, ZhuanZhenActivity.class);
		}
		intent.putExtra("action", "add");
		startActivity(intent);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data == null) {
			return;
		}

		long result = data.getLongExtra("result", -1);
		if (result < 0) {
			Toast.makeText(context, "添加或更新失败!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "添加或更新成功！", Toast.LENGTH_SHORT).show();
			if (selectBtnId == 0) {
				loadjiezhenData();
			} else {
				loadzhuanzhenData();
			}
		}
	}

	private OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (position == 0)
				return;
			Intent intent = new Intent();
			intent.putExtra("action", "detail");
			if (selectBtnId == 0) {
				setMlocaljiezhen(mJieZhens.get(position - 1));
				intent.setClass(context, JieZhenTableActivity.class);
			} else {
				setMlcalzhuanzhen(mZhuanZhens.get(position - 1));
				intent.setClass(context, ZhuanZhenActivity.class);
			}
			startActivity(intent);
		}
	};

	private OnItemLongClickListener itemLongClickListener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(final AdapterView<?> parent, View view,
				final int position, long id) {
			// TODO Auto-generated method stub
			if (position == 0)
				return false;
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("您确定要删除该记录？");
			builder.setPositiveButton("确定", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (selectBtnId == 0) {
						AppDB.deleteJieZhenByInt(position - 1,
								mHealthRecord_down.getPersonId());
						loadjiezhenData();
					} else {
						AppDB.deleteZhuanZhenByInt(position - 1,
								mHealthRecord_down.getPersonId());
						loadzhuanzhenData();
					}
				}
			});
			builder.setNegativeButton("取消", null);
			builder.create().show();
			return true;
		}
	};

	private void loadjiezhenData() {
		list.clear();
		mJieZhens = AppDB.queryByPersonIdJieZhen(mHealthRecord_down
				.getPersonId());
		int i = 0;
		for (LocalJieZhen tmp : mJieZhens) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("序号", i + "");
			map.put("编号", mHealthRecord_down.getHealthFileNumber());
			map.put("接诊医生", tmp.getJiezhen_doctor());
			map.put("接诊日期", tmp.getDate());
			list.add(map);
			i++;
		}

		ListViewAdapter adapter = new ListViewAdapter(context, list,
				JIEZHENTITTLE);
		recordView.setAdapter(adapter);
	}

	private void loadzhuanzhenData() {
		list.clear();
		mZhuanZhens = AppDB.queryByPersonIdZhuanZhen(mHealthRecord_down
				.getPersonId());
		int i = 0;
		for (LocalZhuanZhen tmp : mZhuanZhens) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("序号", i + "");
			map.put("编号", mHealthRecord_down.getHealthFileNumber());
			map.put("接诊医生", tmp.getIn_doctor());
			map.put("转诊医生", tmp.getOut_doctor());
			map.put("转诊日期", tmp.getDate());
			list.add(map);
			i++;
		}
		ListViewAdapter adapter = new ListViewAdapter(context, list,
				ZHUANZHENTITTLE);
		recordView.setAdapter(adapter);
		Log.i("BaseCure", "loadzhuanzhenData");
	}

	public static LocalJieZhen getMlocaljiezhen() {
		return mlocaljiezhen;
	}

	public static void setMlocaljiezhen(LocalJieZhen mlocaljiezhen) {
		MZYLActivity.mlocaljiezhen = mlocaljiezhen;
	}

	public static LocalZhuanZhen getMlcalzhuanzhen() {
		return mlcalzhuanzhen;
	}

	public static void setMlcalzhuanzhen(LocalZhuanZhen mlcalzhuanzhen) {
		MZYLActivity.mlcalzhuanzhen = mlcalzhuanzhen;
	}
}
