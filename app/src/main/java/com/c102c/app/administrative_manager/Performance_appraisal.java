package com.c102c.app.administrative_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.finalJKYTJ.R;

public class Performance_appraisal extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preformance_apprisal_layout);
	}

	public void jumpTo(View view) {
		Intent intent = new Intent();

		switch (view.getId()) {
		case R.id.button_result:
			intent.setClass(this, GZLKH.class);
			break;
		}
		startActivity(intent);
	}
}
