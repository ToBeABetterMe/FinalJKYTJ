package com.c102c.app.resource_mangage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.c102c.finalJKYTJ.R;

public class RenYuanGuanLi extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.renyuanguanli);
	}
}
