package com.c102c.app.ggws_activity;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.finalJKYTJ.R;

import android.os.Bundle;

public class YCFZXDA_downloadActivity extends BaseActivity {

	private PregnantSpecial_down pregnantSpecial_down;

	@Override
	protected void initViews() {
		setContentView(R.layout.ycfzxda_download);
		pregnantSpecial_down = Basesence.getPregnantSpecial_down();
		//system.out.println(pregnantSpecial_down);
		if (pregnantSpecial_down == null) {
			//
		} else {
			//system.out.println(pregnantSpecial_down.getPersonId()
//					+ "//////////////////////********");
		}
		setAllText();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setAllText() {
		// setTextByid(pregnantSpecial_down.getId() + "",
		// R.id.PregnantSpecial_down_et_id);
		setTextByid(pregnantSpecial_down.getPersonId(),
				R.id.PregnantSpecial_down_et_personId);
		setTextByid(pregnantSpecial_down.getSpecialNo(),
				R.id.PregnantSpecial_down_et_specialNo);
		setTextByid(pregnantSpecial_down.getName(),
				R.id.PregnantSpecial_down_et_name);
		setTextByid(pregnantSpecial_down.getAge(),
				R.id.PregnantSpecial_down_et_age);
		setTextByid(pregnantSpecial_down.getLMP(),
				R.id.PregnantSpecial_down_et_LMP);
		setTextByid(pregnantSpecial_down.getPregnantManualNo(),
				R.id.PregnantSpecial_down_et_pregnantManualNo);
		setTextByid(pregnantSpecial_down.getHusbandName(),
				R.id.PregnantSpecial_down_et_husbandName);
		setTextByid(pregnantSpecial_down.getHusbandAge(),
				R.id.PregnantSpecial_down_et_husbandAge);
		setTextByid(pregnantSpecial_down.getHusbandPhone(),
				R.id.PregnantSpecial_down_et_husbandPhone);
		setTextByid(pregnantSpecial_down.getGravidityTimes(),
				R.id.PregnantSpecial_down_et_gravidityTimes);
		setTextByid(pregnantSpecial_down.getVaginalDeliveryTimes(),
				R.id.PregnantSpecial_down_et_vaginalDeliveryTimes);
		setTextByid(pregnantSpecial_down.getCaesareanSectionTimes(),
				R.id.PregnantSpecial_down_et_caesareanSectionTimes);
		setTextByid(pregnantSpecial_down.getHighRiskCode(),
				R.id.PregnantSpecial_down_et_highRiskCode);
		setTextByid(pregnantSpecial_down.getHighRiskFactors(),
				R.id.PregnantSpecial_down_et_highRiskFactors);
		setTextByid(pregnantSpecial_down.getHighRiskScore(),
				R.id.PregnantSpecial_down_et_highRiskScore);
		setTextByid(pregnantSpecial_down.getRegisterDate(),
				R.id.PregnantSpecial_down_et_registerDate);
		setTextByid(pregnantSpecial_down.getRegisterOrgCode(),
				R.id.PregnantSpecial_down_et_registerOrgCode);
		setTextByid(pregnantSpecial_down.getRegisterOrgName(),
				R.id.PregnantSpecial_down_et_registerOrgName);
		setTextByid(pregnantSpecial_down.getRegisterDoctorCode(),
				R.id.PregnantSpecial_down_et_registerDoctorCode);
		setTextByid(pregnantSpecial_down.getRegisterDoctorName(),
				R.id.PregnantSpecial_down_et_registerDoctorName);
		setTextByid(pregnantSpecial_down.getCheckStatus(),
				R.id.PregnantSpecial_down_et_checkStatus);
		setTextByid(pregnantSpecial_down.getVisitStatus(),
				R.id.PregnantSpecial_down_et_visitStatus);
		setTextByid(pregnantSpecial_down.getVisit42Status(),
				R.id.PregnantSpecial_down_et_visit42Status);
	}
}
