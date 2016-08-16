package com.c102c.app.health_record_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.activity.JKCLActivity;
import com.c102c.app.activity.LocalRecordActivity;
import com.c102c.app.activity.MZYLActivity;
import com.c102c.finalJKYTJ.R;

public class UserDetailActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		setContentView(R.layout.userdetail);
	}

	public void jump(View view) {
		Intent intent = null;
		switch (view.getId()) {

		case R.id.userdetail_btn_jbyl:
			// ����ҽ��
			intent = new Intent(UserDetailActivity.this, MZYLActivity.class);
			startActivity(intent);
			break;
		case R.id.userdetail_btn_jkcl:
			// ��������
			intent = new Intent(UserDetailActivity.this, JKCLActivity.class);
			startActivity(intent);
			break;

		case R.id.userdetail_btn_jkda:
			// ��������
			intent = new Intent(UserDetailActivity.this, JKDAActivity.class);
			startActivity(intent);
			break;

		case R.id.userdetail_btn_jktj:
			// �������
			intent = new Intent(UserDetailActivity.this,
					UsersListActivity.class);
			intent.putExtra("from", 7);
			startActivity(intent);
			break;

		case R.id.userdetail_btn_bdjl:
		case R.id.userdetail_btn_cljl:
			//���ز����Ͳ�����¼
			intent = new Intent(UserDetailActivity.this,
					LocalRecordActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void setAllText() {
	}
}
