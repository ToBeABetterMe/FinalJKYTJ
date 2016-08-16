package com.c102c.app.health_record_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.ggws_activity.GGWS_Activity;
import com.c102c.finalJKYTJ.R;

/** �û����� */
public class YWGLActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void jumpTo(View v) {
		// ��ת���û��б��Activity
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.ywgl_btn_userlist:
			// �û��б�
			intent.setClass(YWGLActivity.this, UsersListActivity.class);
			intent.putExtra("from", 0);
			break;
		case R.id.btn_ggws:
			// ������������
			intent.setClass(YWGLActivity.this, GGWS_Activity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	@Override
	protected void initViews() {
		setContentView(R.layout.ywgl_activity);
	}

	@Override
	protected void setAllText() {

	}
}
