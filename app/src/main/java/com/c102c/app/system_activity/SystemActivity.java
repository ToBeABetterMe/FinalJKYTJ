package com.c102c.app.system_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.c102c.app.health_record_activity.UsersListActivity;
import com.c102c.finalJKYTJ.R;

public class SystemActivity extends Activity implements OnClickListener {
	Button btn_setDoc;
	private Button mOffLineButton; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system);
		btn_setDoc=(Button)findViewById(R.id.btn_setDoctor);
		mOffLineButton=(Button)findViewById(R.id.offline_button);
		btn_setDoc.setOnClickListener(this);
		mOffLineButton.setOnClickListener(this);
		init();
	}

	private void init() {}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		// 设置新的医生用户
		case R.id.btn_setDoctor:
			intent = new Intent(SystemActivity.this, DoctorActivity.class);
			finish();
			startActivity(intent);
			break;
		case R.id.offline_button:
			intent = new Intent(SystemActivity.this, UsersListActivity.class);
			intent.putExtra("from", 8);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
