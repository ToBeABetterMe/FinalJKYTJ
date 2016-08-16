package com.c102c.app.staff_training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.finalJKYTJ.R;

public class Staff_Training extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_training_main);
	}

	public void jumpTo(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.button_knowledge_searching:
			intent.setClass(this, DocTool.class);
			break;
		case R.id.button_working_training:
			intent.setClass(this, Wroking_training.class);
			break;
		}
		startActivity(intent);
	}
}
