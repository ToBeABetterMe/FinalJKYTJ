package com.c102c.app.ggws_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.health_record_activity.UsersListActivity;
import com.c102c.finalJKYTJ.R;

public class GGWS_Activity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ggws_layout);
	}

	public void jumpTo(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		// ¶ùÍ¯±£½¡
		case R.id.ggws_btn_child:
			intent = new Intent(GGWS_Activity.this, UsersListActivity.class);
			
			/////////////////////ÔøµÂÉ­
			intent.putExtra("from", 2);
			//////////////////////
			break;
		// ÔÐ²ú¸¾
		case R.id.ggws_btn_pregnancy:
			//Fetch.communicate("M0100040101", this, handler);
			intent = new Intent(GGWS_Activity.this, UsersListActivity.class);
			intent.putExtra("from", 1);
			break;
		}
		startActivity(intent);
	}
	Handler handler =new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			//system.out.println(msg.obj);
			super.handleMessage(msg);
		}
};
}
