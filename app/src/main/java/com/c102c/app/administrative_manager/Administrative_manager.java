package com.c102c.app.administrative_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.finalJKYTJ.R;

public class Administrative_manager extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.administrative_manager_layout);
	}

	public void jumpTo(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.btn1:
			intent.setClass(this, Administrative_notification.class);
			break;
		case R.id.btn2:
			intent.setClass(this, Task_notification.class);
			break;
		case R.id.btn3:
			intent.setClass(this, Performance_appraisal.class);
			break;
		}
		startActivity(intent);
	}
}
