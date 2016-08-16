package com.c102c.app.resource_mangage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.c102c.finalJKYTJ.R;

public class DoctorMark extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mask);

		Button btnRenyuan = (Button) findViewById(R.id.btn_renyuanguanli);
		btnRenyuan.setOnClickListener(this);
		Button btnFangWu = (Button) findViewById(R.id.btn_fangwujijianzzhuwu);
		btnFangWu.setOnClickListener(this);
		Button btnOther = (Button) findViewById(R.id.btn_otherwupin);
		btnOther.setOnClickListener(this);
		Button btnXqjb = (Button) findViewById(R.id.btn_xqjbqk);
		btnXqjb.setOnClickListener(this);
		// btnXqjb.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		Button btnWssbz = (Button) findViewById(R.id.btn_wssbzdj);
		btnWssbz.setOnClickListener(this);

	}

	// public void OnClickListener
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_renyuanguanli:
			//
			intent = new Intent(DoctorMark.this, RenYuanGuanLi.class);
			break;
		case R.id.btn_fangwujijianzzhuwu:
			intent = new Intent(DoctorMark.this, FangWuAndBuildings.class);
			break;
		case R.id.btn_otherwupin:
			intent = new Intent(DoctorMark.this, OtherThingsList.class);
			break;
		case R.id.btn_xqjbqk:
			intent = new Intent(DoctorMark.this, XQJBQK.class);
			break;
		case R.id.btn_wssbzdj:
			intent.setClass(this, WSSBZDJ.class);

		default:
			break;
		}
		startActivity(intent);

	}
}
