package com.c102c.app.mbgy_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.HypertensionFlup_upload;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ETempActivity;
import com.health.measurement.MeasureOnPC300Activity;

/***
 * 高血压随访记录上传
 * 
 */
public class GXYSFJL_uploadActivity extends BaseActivity {

	private HypertensionFlup_upload hypertensionFlup_upload;

	@Override
	protected void initViews() {
		setContentView(R.layout.gxysfjl_upload);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_symptomCodes,
				R.array.HypertensionFlup_upload_et_symptomCodes);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_flupMode,
				R.array.HypertensionFlup_upload_et_flupMode);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_saltIntakeCode,
				R.array.HypertensionFlup_upload_et_saltIntakeCode);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_saltIntakeCodeTarget,
				R.array.HypertensionFlup_upload_et_saltIntakeCodeTarget);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_psychologicalCode,
				R.array.HypertensionFlup_upload_et_psychologicalCode);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_complianceCode,
				R.array.HypertensionFlup_upload_et_complianceCode);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_doseCode,
				R.array.HypertensionFlup_upload_et_doseCode);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_adverseReactionCode,
				R.array.HypertensionFlup_upload_et_adverseReactionCode);
		setChoiceEditText(R.id.HypertensionFlup_upload_et_flupTypeCode,
				R.array.HypertensionFlup_upload_et_flupTypeCode);
		hypertensionFlup_upload = new HypertensionFlup_upload();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setAllText() {
		hypertensionFlup_upload
				.setPersonId(((EditText) findViewById(R.id.HypertensionFlup_upload_et_personId))
						.getText().toString());
		hypertensionFlup_upload
				.setSpecialNo(((EditText) findViewById(R.id.HypertensionFlup_upload_et_specialNo))
						.getText().toString());
		hypertensionFlup_upload
				.setMachineCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_machineCode))
						.getText().toString());
		hypertensionFlup_upload
				.setMachineNo(((EditText) findViewById(R.id.HypertensionFlup_upload_et_machineNo))
						.getText().toString());
		hypertensionFlup_upload
				.setName(((EditText) findViewById(R.id.HypertensionFlup_upload_et_name))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupDate(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupDate))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupMode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupMode))
						.getText().toString());
		hypertensionFlup_upload
				.setSymptomCodes(((EditText) findViewById(R.id.HypertensionFlup_upload_et_symptomCodes))
						.getText().toString());
		// hypertensionFlup_upload
		// .setSymptomOther(((EditText)
		// findViewById(R.id.HypertensionFlup_upload_et_symptomOther))
		// .getText().toString());
		hypertensionFlup_upload
				.setSBP(((EditText) findViewById(R.id.HypertensionFlup_upload_et_SBP))
						.getText().toString());
		hypertensionFlup_upload
				.setDBP(((EditText) findViewById(R.id.HypertensionFlup_upload_et_DBP))
						.getText().toString());
		hypertensionFlup_upload
				.setWeight(((EditText) findViewById(R.id.HypertensionFlup_upload_et_weight))
						.getText().toString());
		hypertensionFlup_upload
				.setWeightTarget(((EditText) findViewById(R.id.HypertensionFlup_upload_et_weightTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setBmi(((EditText) findViewById(R.id.HypertensionFlup_upload_et_bmi))
						.getText().toString());
		hypertensionFlup_upload
				.setBmiTarget(((EditText) findViewById(R.id.HypertensionFlup_upload_et_bmiTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setHeartRate(((EditText) findViewById(R.id.HypertensionFlup_upload_et_heartRate))
						.getText().toString());
		hypertensionFlup_upload
				.setSignsOther(((EditText) findViewById(R.id.HypertensionFlup_upload_et_signsOther))
						.getText().toString());
		hypertensionFlup_upload
				.setDailySmoking(((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailySmoking))
						.getText().toString());
		hypertensionFlup_upload
				.setDailySmokingTarget(((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailySmokingTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setDailyDrinking(((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailyDrinking))
						.getText().toString());
		hypertensionFlup_upload
				.setDailyDrinkingTarget(((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailyDrinkingTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setPerWeekMovements(((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekMovements))
						.getText().toString());
		hypertensionFlup_upload
				.setPerWeekMovementsTarget(((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekMovementsTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setPerWeekTimes(((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekTimes))
						.getText().toString());
		hypertensionFlup_upload
				.setPerWeekTimesTarget(((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekTimesTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setSaltIntakeCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_saltIntakeCode))
						.getText().toString());
		hypertensionFlup_upload
				.setSaltIntakeTargetCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_saltIntakeCodeTarget))
						.getText().toString());
		hypertensionFlup_upload
				.setPsychologicalCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_psychologicalCode))
						.getText().toString());
		hypertensionFlup_upload
				.setComplianceCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_complianceCode))
						.getText().toString());
		hypertensionFlup_upload
				.setAidCheck(((EditText) findViewById(R.id.HypertensionFlup_upload_et_aidCheck))
						.getText().toString());
		hypertensionFlup_upload
				.setDoseCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_doseCode))
						.getText().toString());
		hypertensionFlup_upload
				.setAdverseReactionCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_adverseReactionCode))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupTypeCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupTypeCode))
						.getText().toString());
		// hypertensionFlup_upload
		// .setMedicationList(((EditText)
		// findViewById(R.id.HypertensionFlup_upload_et_medicationList))
		// .getText().toString());
		hypertensionFlup_upload
				.setReferralReason(((EditText) findViewById(R.id.HypertensionFlup_upload_et_referralReason))
						.getText().toString());
		hypertensionFlup_upload
				.setReferralOrg(((EditText) findViewById(R.id.HypertensionFlup_upload_et_referralOrg))
						.getText().toString());
		hypertensionFlup_upload
				.setReferralDepartment(((EditText) findViewById(R.id.HypertensionFlup_upload_et_referralDepartment))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupDoctorCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupDoctorCode))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupDoctorName(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupDoctorName))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupOrgCode(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupOrgCode))
						.getText().toString());
		hypertensionFlup_upload
				.setFlupOrgName(((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupOrgName))
						.getText().toString());
		hypertensionFlup_upload
				.setNextFlupDate(((EditText) findViewById(R.id.HypertensionFlup_upload_et_nextFlupDate))
						.getText().toString());

		setTextByid(Basesence.getorgCode(),
				R.id.HypertensionFlup_upload_et_flupOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.HypertensionFlup_upload_et_flupOrgName);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.HypertensionFlup_upload_et_flupDoctorName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.HypertensionFlup_upload_et_flupDoctorCode);
	}

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.HypertensionFlup_upload_et_SBP:
		case R.id.HypertensionFlup_upload_et_DBP:
		case R.id.HypertensionFlup_upload_et_heartRate:
			intent.setClass(this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			break;
		default:
			break;
		}
		startActivityForResult(intent, Basesence.MEASURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (resultCode) {
		case Basesence.MEASURE_RESULT_LEFT_BLOOD_PRESURE:
			((EditText) findViewById(R.id.HypertensionFlup_upload_et_SBP))
					.setText(data.getStringExtra("ssy"));
			((EditText) findViewById(R.id.HypertensionFlup_upload_et_DBP))
					.setText(data.getStringExtra("szy"));
			((EditText) findViewById(R.id.HypertensionFlup_upload_et_heartRate))
					.setText(data.getStringExtra("ml"));
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
