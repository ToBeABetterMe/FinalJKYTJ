package com.c102c.app.ggws_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.health_record_activity.UsersListActivity;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.finalJKYTJ.R;

public class YCFActivity extends BaseActivity {
 PregnantSpecial_down pre;
	@Override
	protected void initViews() {
		setContentView(R.layout.ycfactivity);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	}
	public void jump(View view) {
		Intent intent = null;
		switch (view.getId()) {
	
		case R.id.ycfactivity_btn_cjjl:
				intent = new Intent(YCFActivity.this, UsersListActivity.class);
				intent.putExtra("from", 10);
			break;
		case R.id.ycfactivity_btn_fjjl:
				intent = new Intent(YCFActivity.this, UsersListActivity.class);
				intent.putExtra("from", 11);
	
		//	intent = new Intent(YCFActivity.this, YCFFJJL_uploadActivity.class);
			break;
		case R.id.ycfactivity_btn_chfsjl:
			intent = new Intent(YCFActivity.this, UsersListActivity.class);
			intent.putExtra("from", 12);

			break;
		case R.id.ycfactivity_btn_ch42fsjl:
			intent = new Intent(YCFActivity.this, UsersListActivity.class);
			
			intent.putExtra("from", 13);
			
			break;
		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}
}
