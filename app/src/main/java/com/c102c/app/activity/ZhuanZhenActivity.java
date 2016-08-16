package com.c102c.app.activity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.LocalZhuanZhen;
import com.c102c.finalJKYTJ.R;
import com.health.util.ChoiceEditText;
import com.health.util.DateEditText;
import com.health.util.SingleChoiceEditText;

public class ZhuanZhenActivity extends Activity {

	private EditText etName;
	private EditText etGender;
	private EditText etAge;
	private EditText etSerialId;
	private EditText etPhone;
	private EditText etAddress;
	private EditText etInHos;
	private EditText etOutHos;
	private EditText etDate;
	private EditText etKeshi;
	private EditText etJiezhenDoc;
	private EditText etZhuanzhenDoc;
	private EditText etContactPhone;

	private EditText etFirstImp;
	private EditText etZhenduanResult;
	private EditText etCheckResult;
	private EditText etCurrentDisease;
	private EditText etPreviousHis;
	private EditText etCureProcess;
	private EditText etAdvices;
	private static String grxh;
	private String action;
	private String serialId;
	private static Context context;
	private AppDB appdb = null;
	private final static String TABLE_NAME = "ZHUANZHEN";
	private ExecutorService exec = Executors.newSingleThreadExecutor();
	private LocalZhuanZhen mlocalzhuanzhen;

	private HealthRecord_down mHealthRecord_down;

	private LocalZhuanZhen getAllText() {
		LocalZhuanZhen localZhuanZhen = new LocalZhuanZhen();
		localZhuanZhen.setPersonId(mHealthRecord_down.getPersonId());
		localZhuanZhen.setName(((EditText) findViewById(R.id.et_name))
				.getText().toString());
		localZhuanZhen.setGender(((EditText) findViewById(R.id.et_gender))
				.getText().toString());
		localZhuanZhen.setAge(((EditText) findViewById(R.id.et_age)).getText()
				.toString());
		return localZhuanZhen;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zhuanzhen_table);
		mHealthRecord_down = Basesence.getTempHealthRecord_down();
		context = this;

		initView();
		AppDB.getInstance(this);
		fillEditText();

	}

	private void initView() {
		etAddress = (EditText) findViewById(R.id.et_address);
		etAdvices = (EditText) findViewById(R.id.et_advices);
		etAge = (EditText) findViewById(R.id.et_age);
		etCheckResult = (EditText) findViewById(R.id.et_checkResult);
		etContactPhone = (EditText) findViewById(R.id.et_contactPhone);
		etCureProcess = (EditText) findViewById(R.id.et_cureProcess);
		etCurrentDisease = (EditText) findViewById(R.id.et_currentDisease);
		etDate = (EditText) findViewById(R.id.et_date);
		etFirstImp = (EditText) findViewById(R.id.et_firstImpression);
		etInHos = (EditText) findViewById(R.id.et_inHos);
		etJiezhenDoc = (EditText) findViewById(R.id.et_jiezhenDoc);
		etKeshi = (EditText) findViewById(R.id.et_keshi);
		etName = (EditText) findViewById(R.id.et_name);
		etOutHos = (EditText) findViewById(R.id.et_outHos);
		etPhone = (EditText) findViewById(R.id.et_phone);
		etPreviousHis = (EditText) findViewById(R.id.et_previousHis);
		etSerialId = (EditText) findViewById(R.id.et_serialId);
		etZhenduanResult = (EditText) findViewById(R.id.et_zhenduanResult);
		etZhuanzhenDoc = (EditText) findViewById(R.id.et_zhuanzhenDoc);
		etGender = (EditText) findViewById(R.id.et_gender);
	}

	public void saveZhuanZhenTable(View view) {
		AppDB.add(getAllText());
		finish();
	}

	public void fillEditText() {
		Intent intent = getIntent();
		action = intent.getStringExtra("action");
		if (action != null) {
			if ("detail".equals(action)) {
				mlocalzhuanzhen = MZYLActivity.getMlcalzhuanzhen();

				((EditText) findViewById(R.id.et_name)).setText(mlocalzhuanzhen
						.getName());
				((EditText) findViewById(R.id.et_gender))
						.setText(mlocalzhuanzhen.getGender());
				((EditText) findViewById(R.id.et_age)).setText(mlocalzhuanzhen
						.getAge());
				((EditText) findViewById(R.id.et_phone))
						.setText(mlocalzhuanzhen.getTelephone());
				((EditText) findViewById(R.id.et_address))
						.setText(mlocalzhuanzhen.getAddress());
				((EditText) findViewById(R.id.et_inHos))
						.setText(mlocalzhuanzhen.getIn_company());
				((EditText) findViewById(R.id.et_outHos))
						.setText(mlocalzhuanzhen.getOut_company());
				((DateEditText) findViewById(R.id.et_date))
						.setText(mlocalzhuanzhen.getDate());
				((EditText) findViewById(R.id.et_keshi))
						.setText(mlocalzhuanzhen.getJiezhen_keshi());
				((EditText) findViewById(R.id.et_jiezhenDoc))
						.setText(mlocalzhuanzhen.getIn_doctor());
				((EditText) findViewById(R.id.et_zhuanzhenDoc))
						.setText(mlocalzhuanzhen.getOut_doctor());
			} else if ("add".equals(action)) {
				((EditText) findViewById(R.id.et_name))
						.setText(mHealthRecord_down.getName());
				((EditText) findViewById(R.id.et_gender))
						.setText(mHealthRecord_down.getGenderCode());
				((EditText) findViewById(R.id.et_serialId))
						.setText(mHealthRecord_down.getHealthFileNumber());
			}
		}
	}

	protected void setChoiceEditText(int id, String[] items, String editableItem) {
		ChoiceEditText cet = (ChoiceEditText) findViewById(id);
		cet.setFixItems(items);
		if (editableItem != null)
			cet.setEditableItem(editableItem);
	}
}
