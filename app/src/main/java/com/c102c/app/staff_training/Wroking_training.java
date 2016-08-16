package com.c102c.app.staff_training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.finalJKYTJ.R;

public class Wroking_training extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wroking_training_main);
	}

	public void jumpTo(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.btn1:
			// intent.addCategory(category);
			intent.putExtra("frombutton", 1);
			// intent.setClass(this, QXPX.class);
			break;
		case R.id.btn2:
			intent.putExtra("frombutton", 2);
			// intent.setClass(this, XXPX.class);
			break;
		case R.id.btn3:
			intent.putExtra("frombutton", 3);
			// intent.setClass(this, XCYSLH.class);
			break;
		case R.id.btn4:
			intent.putExtra("frombutton", 4);
			// intent.setClass(this, WLPX.class);
			break;
		}
		intent.setClass(this, QXPX.class);
		startActivity(intent);
	}
}
