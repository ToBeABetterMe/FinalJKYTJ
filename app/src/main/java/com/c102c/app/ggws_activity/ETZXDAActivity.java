package com.c102c.app.ggws_activity;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.ChildSpecial_down;
import com.c102c.finalJKYTJ.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ETZXDAActivity extends BaseActivity {

	private ChildSpecial_down childSpecial_down;
	Button btn_sfjl;
	@Override
	protected void initViews() {
		setContentView(R.layout.etzx);
		btn_sfjl=(Button)findViewById(R.id.btn_sfjl);
		childSpecial_down = Basesence.getChildSpecial_down();
		setAllText();
		btn_sfjl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ETZXDAActivity.this,XSEJTFSJL_uploadActivity.class);
				Bundle bundle=new Bundle();
				intent.putExtra("name", childSpecial_down.getName());
				intent.putExtra("personId", childSpecial_down.getPersonId());
				intent.putExtra("specialNo", childSpecial_down.getSpecialNo());
				
				startActivity(intent);
			}
		});
	}

	@Override
	protected void setAllText() {
		// setTextByid(childSpecial.getRiskFactorsOther(),
		// R.id.ChildSpecial_et_riskFactorsOther);
//		setTextByid(childSpecial.getId() + "", R.id.ChildSpecial_et_id);
//		setTextByid(childSpecial.getPersonId() + "",
//				R.id.ChildSpecial_et_PersonId);
		setTextByid(childSpecial_down.getSpecialNo(), R.id.ChildSpecial_et_specialNo);
		setTextByid(childSpecial_down.getName(), R.id.ChildSpecial_et_name);
		setTextByid(childSpecial_down.getGenderCode(),
				R.id.ChildSpecial_et_genderCode);
		setTextByid(childSpecial_down.getBirthday(), R.id.ChildSpecial_et_birthday);
		setTextByid(childSpecial_down.getHouseholdRegister(),
				R.id.ChildSpecial_et_householdRegister);
		setTextByid(childSpecial_down.getChildHealthCardNo(),
				R.id.ChildSpecial_et_childHealthCardNo);
		setTextByid(childSpecial_down.getBirthWeight(),
				R.id.ChildSpecial_et_birthWeight);
		setTextByid(childSpecial_down.getBirthHeight(),
				R.id.ChildSpecial_et_birthHeight);
		setTextByid(childSpecial_down.getParity(), R.id.ChildSpecial_et_parity);
		setTextByid(childSpecial_down.getDeliveryTimes(),
				R.id.ChildSpecial_et_deliveryTimes);
		setTextByid(childSpecial_down.getDeliveryGestationalWeeks(),
				R.id.ChildSpecial_et_deliveryGestationalWeeks);
		setTextByid(childSpecial_down.getDeliveryModeCode(),
				R.id.ChildSpecial_et_deliveryModeCode);
		setTextByid(childSpecial_down.getChildbirth(),
				R.id.ChildSpecial_et_childbirth);
		setTextByid(childSpecial_down.getBirthHospital(),
				R.id.ChildSpecial_et_birthHospital);
		setTextByid(childSpecial_down.getFatherName(),
				R.id.ChildSpecial_et_fatherName);
		setTextByid(childSpecial_down.getMatherName(),
				R.id.ChildSpecial_et_matherName);
		setTextByid(childSpecial_down.getFatherContac(),
				R.id.ChildSpecial_et_fatherContac);
		setTextByid(childSpecial_down.getMatherContac(),
				R.id.ChildSpecial_et_matherContac);
		setTextByid(childSpecial_down.getUNHSCode(), R.id.ChildSpecial_et_UNHSCode);
		setTextByid(childSpecial_down.getCYP17Code(), R.id.ChildSpecial_et_CYP17Code);
		setTextByid(childSpecial_down.getPKUCode(), R.id.ChildSpecial_et_PKUCode);
		setTextByid(childSpecial_down.getCHCode(), R.id.ChildSpecial_et_CHCode);
		setTextByid(childSpecial_down.getHighRiskCode(),
				R.id.ChildSpecial_et_highRiskCode);
		setTextByid(childSpecial_down.getHighRiskFactors(),
				R.id.ChildSpecial_et_highRiskFactors);
		setTextByid(childSpecial_down.getApgar1(), R.id.ChildSpecial_et_apgar1);
		setTextByid(childSpecial_down.getApgar5(), R.id.ChildSpecial_et_apgar5);
		setTextByid(childSpecial_down.getApgar10(), R.id.ChildSpecial_et_apgar10);
		setTextByid(childSpecial_down.getPastHistory(),
				R.id.ChildSpecial_et_pastHistory);
		setTextByid(childSpecial_down.getAllergicHistory(),
				R.id.ChildSpecial_et_allergicHistory);
		setTextByid(childSpecial_down.getChildbirthHospital(),
				R.id.ChildSpecial_et_childbirthHospital);
		setTextByid(childSpecial_down.getChildbirthDoctor(),
				R.id.ChildSpecial_et_childbirthDoctor);
		setTextByid(childSpecial_down.getChildbirthAssistant(),
				R.id.ChildSpecial_et_childbirthAssistant);
		setTextByid(childSpecial_down.getRegisterDate(),
				R.id.ChildSpecial_et_registerDate);
		setTextByid(childSpecial_down.getRegisterOrgCode(),
				R.id.ChildSpecial_et_registerOrgCode);
		setTextByid(childSpecial_down.getRegisterOrgName(),
				R.id.ChildSpecial_et_registerOrgName);
		setTextByid(childSpecial_down.getRegisterDoctorCode(),
				R.id.ChildSpecial_et_registerDoctorCode);
		setTextByid(childSpecial_down.getRegisterDoctorName(),
				R.id.ChildSpecial_et_registerDoctorName);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
