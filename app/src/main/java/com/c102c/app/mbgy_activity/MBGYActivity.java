package com.c102c.app.mbgy_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.health_record_activity.UsersListActivity;
import com.c102c.finalJKYTJ.R;

/***
 * ������Ԥ
 *
 */
public class MBGYActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	@Override
	protected void initViews() {
		setContentView(R.layout.mbgy_activity);
	}

	public void jumpActivity(View view) {
		Intent intent = null;
		switch (view.getId()) {
		// ��Ѫѹר���
		case R.id.mbgy_btn_gxysfjh:
			intent = new Intent(MBGYActivity.this, UsersListActivity.class);
			intent.putExtra("from", 3);
			break;
		// ��Ѫѹ��ü�¼
		case R.id.mbgy_btn_gxysfjl:
			intent = new Intent(MBGYActivity.this, UsersListActivity.class);
			intent.putExtra("from", 5);
			break;
		// ����ר���
		case R.id.mbgy_btn_tnbsfjh:
			intent = new Intent(MBGYActivity.this, UsersListActivity.class);
			intent.putExtra("from", 4);
			break;
		// ������ü�¼
		case R.id.mbgy_btn_tnbsfjl:
			intent = new Intent(MBGYActivity.this, UsersListActivity.class);
			intent.putExtra("from", 6);
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	@Override
	protected void setAllText() {
	}
}
