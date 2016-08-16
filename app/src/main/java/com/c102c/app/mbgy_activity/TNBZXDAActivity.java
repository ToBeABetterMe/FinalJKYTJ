package com.c102c.app.mbgy_activity;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.DiabetesSpecial_down;
import com.c102c.finalJKYTJ.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 糖尿病专项档案
 * */
public class TNBZXDAActivity extends BaseActivity {
	private DiabetesSpecial_down diabetesSpecial_down;
	private Button edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		// 糖尿病专项档案
		setContentView(R.layout.tnbzxda);
		edit = (Button) findViewById(R.id.tnbdz_btn_edit);
		edit.setOnClickListener(clicklistener);
		setAllEditTextEnable(false);
		diabetesSpecial_down = Basesence.getDiabetesspecial_down();
	}
	private OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = null;
			if(v == edit){
				intent = new Intent(TNBZXDAActivity.this, TNBSFJLActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("from", "new");
				bundle.putString("name", diabetesSpecial_down.getName());
				bundle.putString("personid",diabetesSpecial_down.getPersonId());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		}
	};
	/***
	 * 通过id给edittext设置字符串。
	 * 
	 * @param content
	 * @param viewId
	 */
	public void setTextById(String content, int viewId) {
		EditText et = (EditText) findViewById(viewId);
		if (!TextUtils.isEmpty(content)) {
			et.setText(content);
		}
	}

	private void setAllEditTextEnable(boolean enabled) {
		((EditText) findViewById(R.id.DiabetesSpecial_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_registerDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_registerOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_registerDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_registerDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_diagnoseDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_diagnoseOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_diagnoseDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_diagnoseDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_bloodPressureLevel))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_nextFlupDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_diabetesLevelCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_caseType))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_ICDCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_doseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_noMedicationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_drugCost))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_familyHistoryCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_height))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_randomBloodGlucose))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_kidneyDiseaseYears))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_retinalDiseaseYears))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_neuropathyYears))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_skinInfectionYears))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_vascularDiseaseYears))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_noComplYears))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_complicationDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_initialDisease))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_currentDisease))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_caseSourceCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesSpecial_et_caseSourceOther))
				.setEnabled(enabled);

	}

	@Override
	protected void setAllText() {
		// setTextByid(diabetesSpecial.getId()+"", R.id.DiabetesSpecial_et_id);
		setTextByid(diabetesSpecial_down.getName(), R.id.DiabetesSpecial_et_name);
		setTextByid(diabetesSpecial_down.getPersonId(),
				R.id.DiabetesSpecial_et_personId);
		setTextByid(diabetesSpecial_down.getSpecialNo(),
				R.id.DiabetesSpecial_et_specialNo);
		setTextByid(diabetesSpecial_down.getRegisterDate(),
				R.id.DiabetesSpecial_et_registerDate);
		setTextByid(diabetesSpecial_down.getRegisterOrgCode(),
				R.id.DiabetesSpecial_et_registerOrgCode);
		setTextByid(diabetesSpecial_down.getRegisterDoctorCode(),
				R.id.DiabetesSpecial_et_registerDoctorCode);
		setTextByid(diabetesSpecial_down.getRegisterDoctorName(),
				R.id.DiabetesSpecial_et_registerDoctorName);
		setTextByid(diabetesSpecial_down.getDiagnoseDate(),
				R.id.DiabetesSpecial_et_diagnoseDate);
		setTextByid(diabetesSpecial_down.getDiagnoseOrgCode(),
				R.id.DiabetesSpecial_et_diagnoseOrgCode);
		setTextByid(diabetesSpecial_down.getDiagnoseDoctorCode(),
				R.id.DiabetesSpecial_et_diagnoseDoctorCode);
		setTextByid(diabetesSpecial_down.getDiagnoseDoctorName(),
				R.id.DiabetesSpecial_et_diagnoseDoctorName);
		setTextByid(diabetesSpecial_down.getSBP(), R.id.DiabetesSpecial_et_SBP);
		setTextByid(diabetesSpecial_down.getDBP(), R.id.DiabetesSpecial_et_DBP);
		setTextByid(diabetesSpecial_down.getBloodPressureLevel(),
				R.id.DiabetesSpecial_et_bloodPressureLevel);
		setTextByid(diabetesSpecial_down.getNextFlupDate(),
				R.id.DiabetesSpecial_et_nextFlupDate);
		setTextByid(diabetesSpecial_down.getDiabetesLevelCode(),
				R.id.DiabetesSpecial_et_diabetesLevelCode);
		setTextByid(diabetesSpecial_down.getCaseType(),
				R.id.DiabetesSpecial_et_caseType);
		setTextByid(diabetesSpecial_down.getICDCode(),
				R.id.DiabetesSpecial_et_ICDCode);
		setTextByid(diabetesSpecial_down.getDoseCode(),
				R.id.DiabetesSpecial_et_doseCode);
		setTextByid(diabetesSpecial_down.getNoMedicationCode(),
				R.id.DiabetesSpecial_et_noMedicationCode);
		setTextByid(diabetesSpecial_down.getDrugCost(),
				R.id.DiabetesSpecial_et_drugCost);
		setTextByid(diabetesSpecial_down.getFamilyHistoryCode(),
				R.id.DiabetesSpecial_et_familyHistoryCode);
		setTextByid(diabetesSpecial_down.getHeight(),
				R.id.DiabetesSpecial_et_height);
		setTextByid(diabetesSpecial_down.getRandomBloodGlucose(),
				R.id.DiabetesSpecial_et_randomBloodGlucose);
		setTextByid(diabetesSpecial_down.getKidneyDiseaseYears(),
				R.id.DiabetesSpecial_et_kidneyDiseaseYears);
		setTextByid(diabetesSpecial_down.getRetinalDiseaseYears(),
				R.id.DiabetesSpecial_et_retinalDiseaseYears);
		setTextByid(diabetesSpecial_down.getNeuropathyYears(),
				R.id.DiabetesSpecial_et_neuropathyYears);
		setTextByid(diabetesSpecial_down.getSkinInfectionYears(),
				R.id.DiabetesSpecial_et_skinInfectionYears);
		setTextByid(diabetesSpecial_down.getVascularDiseaseYears(),
				R.id.DiabetesSpecial_et_vascularDiseaseYears);
		setTextByid(diabetesSpecial_down.getNoComplYears(),
				R.id.DiabetesSpecial_et_noComplYears);
		setTextByid(diabetesSpecial_down.getComplicationDate(),
				R.id.DiabetesSpecial_et_complicationDate);
		setTextByid(diabetesSpecial_down.getInitialDisease(),
				R.id.DiabetesSpecial_et_initialDisease);
		setTextByid(diabetesSpecial_down.getCurrentDisease(),
				R.id.DiabetesSpecial_et_currentDisease);
		setTextByid(diabetesSpecial_down.getCaseSourceCode(),
				R.id.DiabetesSpecial_et_caseSourceCode);
		setTextByid(diabetesSpecial_down.getCaseSourceOther(),
				R.id.DiabetesSpecial_et_caseSourceOther);
	}

}
