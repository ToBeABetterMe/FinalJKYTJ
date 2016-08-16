package com.c102c.app.ggws_activity;

import com.c102c.app.health_record_activity.UsersListActivity;
import com.c102c.finalJKYTJ.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ETBJActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.etbjactivity);
	}

	public void jumpTo(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.etbj_btn_etzxda:
			//儿童专项档案
			intent = new Intent(ETBJActivity.this, UsersListActivity.class);
			intent.putExtra("from", 2);
			startActivity(intent);
			break;
		case R.id.etbj_btn_xsejtfsjl:
			//新生儿家庭随访记录上传
			intent = new Intent(ETBJActivity.this,
					XSEJTFSJL_uploadActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
