package com.c102c.app.system_activity;

import com.c102c.app.activity.InitalActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.finalJKYTJ.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DoctorActivity extends Activity implements OnClickListener {
	Button btn_setDoc, btn_setOrg, btn_use;
	TextView org_name, org_code, doc_name, doc_code, area_code, area_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		btn_setDoc = (Button) findViewById(R.id.btn_setDoc);
		btn_setOrg = (Button) findViewById(R.id.btn_setOrg);
		org_code = (TextView) findViewById(R.id.org_code);
		org_name = (TextView) findViewById(R.id.org_name);
		doc_code = (TextView) findViewById(R.id.doc_code);
		doc_name = (TextView) findViewById(R.id.doc_name);
		area_code = (TextView) findViewById(R.id.area_code);
		area_name = (TextView) findViewById(R.id.area_name);
		area_code.append(Basesence.getAdminAreaCode());
		area_name.append(Basesence.getAdminAreaName());
		org_code.append(Basesence.getorgCode());
		org_name.append(Basesence.getorgName());
		doc_code.append(Basesence.getUser().getUserCode());
		doc_name.append(Basesence.getUser().getUserName());
		btn_setDoc.setOnClickListener(this);

		btn_setOrg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		// 设置新的医生用户
		case R.id.btn_setDoc:
			intent = new Intent(DoctorActivity.this, LoginActivity.class);
			intent.putExtra("from", "1");
			finish();
			startActivity(intent);
			break;
		case R.id.btn_setOrg:
			/*
			 * SharedPreferences sp = SystemActivity.this.getSharedPreferences(
			 * "AKTD", 0); sp.edit().clear().commit();
			 */
			intent = new Intent(DoctorActivity.this, InitalActivity.class);
			intent.putExtra("from", "1");
			/*
			 * AppDB app=AppDB.getInstance(SystemActivity.this);
			 * app.User_delete();
			 */
			AppDB.getInstance(this);
			AppDB.deleteAll();
			finish();
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
