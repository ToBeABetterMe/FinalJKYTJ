package com.c102c.app.mbgy_activity;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.activity.MainActivity;
import com.c102c.app.commu.Basesence;
import com.c102c.app.health_record_activity.JKDAActivity;
import com.c102c.app.health_record_activity.JKTJActivity;
import com.c102c.app.model.HypertensionSpecial_down;
import com.c102c.finalJKYTJ.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/***
 * 高血压专项档案
 */
public class GXYZXDAActivity extends BaseActivity {
	private HypertensionSpecial_down hypertensionSpecial_down;
	private Button new_hy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		// 高血压专项档案
 		setContentView(R.layout.gxyzxda);
		new_hy = (Button) findViewById(R.id.btn_new_hy);
		new_hy.setOnClickListener(clicklistener);
		hypertensionSpecial_down = Basesence.getHypertensionspecial_down();
	}

	private OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v == new_hy){
				Intent intent = new Intent(GXYZXDAActivity.this ,GXYSFJLActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("from", "new");
				bundle.putString("name", hypertensionSpecial_down.getName());
				bundle.putString("personid",hypertensionSpecial_down.getPersonId());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		}
	};

	@Override
	protected void setAllText() {
//		setTextByid(hypertensionSpecial.getId() + "",
//				R.id.HypertensionSpecial_et_id);
		setTextByid(hypertensionSpecial_down.getPersonId(),
				R.id.HypertensionSpecial_et_personId);
		setTextByid(hypertensionSpecial_down.getSpecialNo(),
				R.id.HypertensionSpecial_et_specialNo);
		setTextByid(hypertensionSpecial_down.getName(),
				R.id.HypertensionSpecial_et_name);
		setTextByid(hypertensionSpecial_down.getRegisterDate(),
				R.id.HypertensionSpecial_et_registerDate);
		setTextByid(hypertensionSpecial_down.getRegisterOrgCode(),
				R.id.HypertensionSpecial_et_registerOrgCode);
		setTextByid(hypertensionSpecial_down.getRegisterDoctorCode(),
				R.id.HypertensionSpecial_et_registerDoctorCode);
		setTextByid(hypertensionSpecial_down.getRegisterDoctorName(),
				R.id.HypertensionSpecial_et_registerDoctorName);
		setTextByid(hypertensionSpecial_down.getDiagnoseDate(),
				R.id.HypertensionSpecial_et_diagnoseDate);
		setTextByid(hypertensionSpecial_down.getDiagnoseOrgCode(),
				R.id.HypertensionSpecial_et_diagnoseOrgCode);
		setTextByid(hypertensionSpecial_down.getDiagnoseDoctorCode(),
				R.id.HypertensionSpecial_et_diagnoseDoctorCode);
		setTextByid(hypertensionSpecial_down.getDiagnoseDoctorName(),
				R.id.HypertensionSpecial_et_diagnoseDoctorName);
		setTextByid(hypertensionSpecial_down.getSBP(),
				R.id.HypertensionSpecial_et_SBP);
		setTextByid(hypertensionSpecial_down.getDBP(),
				R.id.HypertensionSpecial_et_DBP);
		setTextByid(hypertensionSpecial_down.getBloodPressureLevel(),
				R.id.HypertensionSpecial_et_bloodPressureLevel);
		setTextByid(hypertensionSpecial_down.getNextFlupDate(),
				R.id.HypertensionSpecial_et_nextFlupDate);

	}
}