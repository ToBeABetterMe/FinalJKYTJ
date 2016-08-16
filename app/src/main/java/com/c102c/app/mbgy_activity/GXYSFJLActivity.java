package com.c102c.app.mbgy_activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.HypertensionFlup_down;
import com.c102c.app.model.HypertensionFlup_upload;
import com.c102c.app.utils.CustomDialog;
import com.c102c.app.utils.FourColmunAdapter;
import com.c102c.app.utils.Medication;
import com.c102c.app.utils.Tools;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.util.ChoiceEditText;

/***
 * 高血压随访记录表
 * 
 */
public class GXYSFJLActivity extends Activity {
	private Button add;
	private Button addMedicineUsingButton;
	private ListView showMedicineUsingListView;

	private boolean tagEdit = false;

	private List<String[]> datas = null;
	private FourColmunAdapter mAdapter;
	private List<Medication> medicineUsingList = null;

	private HypertensionFlup_down hypertensionFlup_down;
	private HypertensionFlup_upload hypertensionflup_upload;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initViews();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			if (bundle.get("from").equals("new")) {
				setAllEditTextEnable(true);
				tagEdit = true;
				loadYYQKListView("");
				hypertensionflup_upload = new HypertensionFlup_upload();
				hypertensionflup_upload.setName(bundle.getString("name", ""));
				hypertensionflup_upload.setPersonId(bundle.getString(
						"personid", ""));
				hypertensionflup_upload.setMachineCode(Basesence
						.getmac(GXYSFJLActivity.this));
				hypertensionflup_upload.setMachineNo(Basesence.getuuid());
				setAllUpText();
				addMedicineUsingButton.setVisibility(View.VISIBLE);
			} else if (bundle.get("from").equals("list")) {
				hypertensionFlup_down = Basesence.gethypertensionflup_down();
				loadYYQKListView(hypertensionFlup_down.getMedicationList());
				setAllText();
				add.setVisibility(8);
			} else if (bundle.get("from").equals("localHistory")) {
				// 啟晖添加
				// 通过类的主键来从数据库寻找类，并添加到表中
				String primaryKey = Tools.getPrimaryKey(bundle
						.getString("uuid"));
				hypertensionflup_upload = new HypertensionFlup_upload();
				hypertensionflup_upload.setId(Long.parseLong(primaryKey));
				List<HypertensionFlup_upload> data = (List<HypertensionFlup_upload>) AppDB
						.query(hypertensionflup_upload);
				if (data.size() > 0) {
					hypertensionflup_upload = data.get(0);
				}
				setAllEditTextEnable(true);
				setAllUpText();
			}

		}
		super.onCreate(savedInstanceState);
	}

	private void initViews() {
		setContentView(R.layout.gxysfjl_upload);
		add = (Button) findViewById(R.id.gxysfjl_btn_OK);
		addMedicineUsingButton = (Button) findViewById(R.id.add_yyqkButton);
		showMedicineUsingListView = (ListView) findViewById(R.id.medicine_usingListView);
		addMedicineUsingButton.setOnClickListener(clicklistener);
		add.setOnClickListener(clicklistener);

	}

	public void loadYYQKListView(String yyqk) {

		medicineUsingList = Tools.getMedication(yyqk);
		datas = new ArrayList<String[]>();
		if (yyqk.equals("")) {

		} else {
			String[] item = null;
			for (Medication med : medicineUsingList) {
				item = new String[] { med.getDrugCode(), med.getDrugName(),
						med.getDrugUsage(), med.getDosage() };
				datas.add(item);
			}
		}
		mAdapter = new FourColmunAdapter(GXYSFJLActivity.this, datas);
		showMedicineUsingListView.setAdapter(mAdapter);
		if (tagEdit) {
			showMedicineUsingListView
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								final int arg2, long arg3) {
							// TODO Auto-generated method stub

							CustomDialog.YYSM_Edit_Delete(GXYSFJLActivity.this,
									mAdapter, arg2).notifyDataSetChanged();
							;
						}
					});
		}
	}

	private OnClickListener clicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == add) {
				hypertensionflup_upload = getAllText();

				Fetch_by_li.communicate("M0100060202", GXYSFJLActivity.this,
						handler, hypertensionflup_upload);
			} else if (v == addMedicineUsingButton) {
				CustomDialog.YYSM_Add(GXYSFJLActivity.this, mAdapter)
						.notifyDataSetChanged();
			}
		}
	};
	private Handler handler = Basesence.getupdatehandler(GXYSFJLActivity.this);

	protected HypertensionFlup_upload getAllText() {
		HypertensionFlup_upload hypertensionFlup_upload = hypertensionflup_upload;
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
		hypertensionFlup_upload
				.setSymptomOther(((EditText) findViewById(R.id.HypertensionFlup_upload_et_symptomOther))
						.getText().toString());
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
		return hypertensionFlup_upload;
	}

	public void setChoiceEditText(int et_Id, int arrayId) {
		((ChoiceEditText) findViewById(et_Id)).setFixItems(getResources()
				.getStringArray(arrayId));
	}

	// 设置editText可以点击，同时设置单选、多选的编辑框
	private void setAllEditTextEnable(boolean enabled) {
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_machineCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_machineNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_name))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupMode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_symptomCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_symptomOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_weight))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_weightTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_bmi))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_bmiTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_heartRate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_signsOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailySmoking))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailySmokingTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailyDrinking))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_dailyDrinkingTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekMovements))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekMovementsTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_perWeekTimesTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_saltIntakeCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_saltIntakeCodeTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_psychologicalCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_complianceCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_aidCheck))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_doseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_adverseReactionCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupTypeCode))
				.setEnabled(enabled);
		// ((EditText)
		// findViewById(R.id.HypertensionFlup_upload_et_medicationList))
		// .setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_referralReason))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_referralOrg))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_referralDepartment))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_flupOrgName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.HypertensionFlup_upload_et_nextFlupDate))
				.setEnabled(enabled);
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
	}

	protected void setAllUpText() {
		// setTextByid(hypertensionFlup.getId()+"",
		// R.id.HypertensionFlup_upload_et_id);
		setTextByid(hypertensionflup_upload.getPersonId(),
				R.id.HypertensionFlup_upload_et_personId);
		setTextByid(hypertensionflup_upload.getMachineCode(),
				R.id.HypertensionFlup_upload_et_machineCode);
		setTextByid(hypertensionflup_upload.getMachineNo(),
				R.id.HypertensionFlup_upload_et_machineNo);
		// setTextByid(hypertensionFlup_down.getHealthFileNumber(),
		// R.id.HypertensionFlup_upload_et_healthFileNumber);
		setTextByid(hypertensionflup_upload.getName(),
				R.id.HypertensionFlup_upload_et_name);
		setTextByid(hypertensionflup_upload.getFlupDate(),
				R.id.HypertensionFlup_upload_et_flupDate);
		setTextByid(hypertensionflup_upload.getFlupMode(),
				R.id.HypertensionFlup_upload_et_flupMode);
		setTextByid(hypertensionflup_upload.getSymptomCodes(),
				R.id.HypertensionFlup_upload_et_symptomCodes);
		setTextByid(hypertensionflup_upload.getSymptomOther(),
				R.id.HypertensionFlup_upload_et_symptomOther);
		setTextByid(hypertensionflup_upload.getSBP(),
				R.id.HypertensionFlup_upload_et_SBP);
		setTextByid(hypertensionflup_upload.getDBP(),
				R.id.HypertensionFlup_upload_et_DBP);
		setTextByid(hypertensionflup_upload.getWeight(),
				R.id.HypertensionFlup_upload_et_weight);
		setTextByid(hypertensionflup_upload.getWeightTarget(),
				R.id.HypertensionFlup_upload_et_weightTarget);
		setTextByid(hypertensionflup_upload.getBmi(),
				R.id.HypertensionFlup_upload_et_bmi);
		setTextByid(hypertensionflup_upload.getBmiTarget(),
				R.id.HypertensionFlup_upload_et_bmiTarget);
		setTextByid(hypertensionflup_upload.getHeartRate(),
				R.id.HypertensionFlup_upload_et_heartRate);
		setTextByid(hypertensionflup_upload.getSignsOther(),
				R.id.HypertensionFlup_upload_et_signsOther);
		setTextByid(hypertensionflup_upload.getDailySmoking(),
				R.id.HypertensionFlup_upload_et_dailySmoking);
		setTextByid(hypertensionflup_upload.getDailySmokingTarget(),
				R.id.HypertensionFlup_upload_et_dailySmokingTarget);
		setTextByid(hypertensionflup_upload.getDailyDrinking(),
				R.id.HypertensionFlup_upload_et_dailyDrinking);
		setTextByid(hypertensionflup_upload.getDailyDrinkingTarget(),
				R.id.HypertensionFlup_upload_et_dailyDrinkingTarget);
		setTextByid(hypertensionflup_upload.getPerWeekMovements(),
				R.id.HypertensionFlup_upload_et_perWeekMovements);
		setTextByid(hypertensionflup_upload.getPerWeekMovementsTarget(),
				R.id.HypertensionFlup_upload_et_perWeekMovementsTarget);
		setTextByid(hypertensionflup_upload.getPerWeekTimes(),
				R.id.HypertensionFlup_upload_et_perWeekTimes);
		setTextByid(hypertensionflup_upload.getPerWeekTimesTarget(),
				R.id.HypertensionFlup_upload_et_perWeekTimesTarget);
		setTextByid(hypertensionflup_upload.getSaltIntakeCode(),
				R.id.HypertensionFlup_upload_et_saltIntakeCode);
		setTextByid(hypertensionflup_upload.getSaltIntakeTargetCode(),
				R.id.HypertensionFlup_upload_et_saltIntakeCodeTarget);
		setTextByid(hypertensionflup_upload.getPsychologicalCode(),
				R.id.HypertensionFlup_upload_et_psychologicalCode);
		setTextByid(hypertensionflup_upload.getComplianceCode(),
				R.id.HypertensionFlup_upload_et_complianceCode);
		setTextByid(hypertensionflup_upload.getAidCheck(),
				R.id.HypertensionFlup_upload_et_aidCheck);
		setTextByid(hypertensionflup_upload.getDoseCode(),
				R.id.HypertensionFlup_upload_et_doseCode);
		setTextByid(hypertensionflup_upload.getAdverseReactionCode(),
				R.id.HypertensionFlup_upload_et_adverseReactionCode);
		setTextByid(hypertensionflup_upload.getFlupTypeCode(),
				R.id.HypertensionFlup_upload_et_flupTypeCode);
		// setTextByid(hypertensionflup_upload.getMedicationList(),
		// R.id.HypertensionFlup_upload_et_medicationList);
		setTextByid(hypertensionflup_upload.getReferralReason(),
				R.id.HypertensionFlup_upload_et_referralReason);
		setTextByid(hypertensionflup_upload.getReferralOrg(),
				R.id.HypertensionFlup_upload_et_referralOrg);
		setTextByid(hypertensionflup_upload.getReferralDepartment(),
				R.id.HypertensionFlup_upload_et_referralDepartment);
		setTextByid(hypertensionflup_upload.getFlupDoctorCode(),
				R.id.HypertensionFlup_upload_et_flupDoctorCode);
		setTextByid(hypertensionflup_upload.getFlupDoctorName(),
				R.id.HypertensionFlup_upload_et_flupDoctorName);
		setTextByid(hypertensionflup_upload.getFlupOrgCode(),
				R.id.HypertensionFlup_upload_et_flupOrgCode);
		setTextByid(hypertensionflup_upload.getFlupOrgName(),
				R.id.HypertensionFlup_upload_et_flupOrgName);
		setTextByid(hypertensionflup_upload.getNextFlupDate(),
				R.id.HypertensionFlup_upload_et_nextFlupDate);

		// ///////////////////////////////////////////曾德森
		setTextByid(Basesence.getorgCode(),
				R.id.HypertensionFlup_upload_et_flupOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.HypertensionFlup_upload_et_flupOrgName);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.HypertensionFlup_upload_et_flupDoctorName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.HypertensionFlup_upload_et_flupDoctorCode);
		// ////////////////////
	}

	public void setTextByid(String content, int id) {
		if (!TextUtils.isEmpty(content)) {
			EditText et = (EditText) findViewById(id);
			et.setText(content);
		}
	}

	protected void setAllText() {
		// setTextByid(hypertensionFlup.getId()+"",
		// R.id.HypertensionFlup_upload_et_id);
		setTextByid(hypertensionFlup_down.getPersonId(),
				R.id.HypertensionFlup_upload_et_personId);
		// setTextByid(hypertensionFlup_down.getHealthFileNumber(),
		// R.id.HypertensionFlup_upload_et_healthFileNumber);
		setTextByid(hypertensionFlup_down.getName(),
				R.id.HypertensionFlup_upload_et_name);
		setTextByid(hypertensionFlup_down.getFlupDate(),
				R.id.HypertensionFlup_upload_et_flupDate);
		setTextByid(hypertensionFlup_down.getFlupMode(),
				R.id.HypertensionFlup_upload_et_flupMode);
		setTextByid(hypertensionFlup_down.getSymptomCodes(),
				R.id.HypertensionFlup_upload_et_symptomCodes);
		setTextByid(hypertensionFlup_down.getSymptomOther(),
				R.id.HypertensionFlup_upload_et_symptomOther);
		setTextByid(hypertensionFlup_down.getSBP(),
				R.id.HypertensionFlup_upload_et_SBP);
		setTextByid(hypertensionFlup_down.getDBP(),
				R.id.HypertensionFlup_upload_et_DBP);
		setTextByid(hypertensionFlup_down.getWeight(),
				R.id.HypertensionFlup_upload_et_weight);
		setTextByid(hypertensionFlup_down.getWeightTarget(),
				R.id.HypertensionFlup_upload_et_weightTarget);
		setTextByid(hypertensionFlup_down.getBmi(),
				R.id.HypertensionFlup_upload_et_bmi);
		setTextByid(hypertensionFlup_down.getBmiTarget(),
				R.id.HypertensionFlup_upload_et_bmiTarget);
		setTextByid(hypertensionFlup_down.getHeartRate(),
				R.id.HypertensionFlup_upload_et_heartRate);
		setTextByid(hypertensionFlup_down.getSignsOther(),
				R.id.HypertensionFlup_upload_et_signsOther);
		setTextByid(hypertensionFlup_down.getDailySmoking(),
				R.id.HypertensionFlup_upload_et_dailySmoking);
		setTextByid(hypertensionFlup_down.getDailySmokingTarget(),
				R.id.HypertensionFlup_upload_et_dailySmokingTarget);
		setTextByid(hypertensionFlup_down.getDailyDrinking(),
				R.id.HypertensionFlup_upload_et_dailyDrinking);
		setTextByid(hypertensionFlup_down.getDailyDrinkingTarget(),
				R.id.HypertensionFlup_upload_et_dailyDrinkingTarget);
		setTextByid(hypertensionFlup_down.getPerWeekMovements(),
				R.id.HypertensionFlup_upload_et_perWeekMovements);
		setTextByid(hypertensionFlup_down.getPerWeekMovementsTarget(),
				R.id.HypertensionFlup_upload_et_perWeekMovementsTarget);
		setTextByid(hypertensionFlup_down.getPerWeekTimes(),
				R.id.HypertensionFlup_upload_et_perWeekTimes);
		setTextByid(hypertensionFlup_down.getPerWeekTimesTarget(),
				R.id.HypertensionFlup_upload_et_perWeekTimesTarget);
		setTextByid(hypertensionFlup_down.getSaltIntakeCode(),
				R.id.HypertensionFlup_upload_et_saltIntakeCode);
		setTextByid(hypertensionFlup_down.getSaltIntakeTargetCode(),
				R.id.HypertensionFlup_upload_et_saltIntakeCodeTarget);
		setTextByid(hypertensionFlup_down.getPsychologicalCode(),
				R.id.HypertensionFlup_upload_et_psychologicalCode);
		setTextByid(hypertensionFlup_down.getComplianceCode(),
				R.id.HypertensionFlup_upload_et_complianceCode);
		setTextByid(hypertensionFlup_down.getAidCheck(),
				R.id.HypertensionFlup_upload_et_aidCheck);
		setTextByid(hypertensionFlup_down.getDoseCode(),
				R.id.HypertensionFlup_upload_et_doseCode);
		setTextByid(hypertensionFlup_down.getAdverseReactionCode(),
				R.id.HypertensionFlup_upload_et_adverseReactionCode);
		setTextByid(hypertensionFlup_down.getFlupTypeCode(),
				R.id.HypertensionFlup_upload_et_flupTypeCode);
		// setTextByid(hypertensionFlup_down.getMedicationList(),
		// R.id.HypertensionFlup_upload_et_medicationList);
		setTextByid(hypertensionFlup_down.getReferralReason(),
				R.id.HypertensionFlup_upload_et_referralReason);
		setTextByid(hypertensionFlup_down.getReferralOrg(),
				R.id.HypertensionFlup_upload_et_referralOrg);
		setTextByid(hypertensionFlup_down.getReferralDepartment(),
				R.id.HypertensionFlup_upload_et_referralDepartment);
		setTextByid(hypertensionFlup_down.getFlupDoctorCode(),
				R.id.HypertensionFlup_upload_et_flupDoctorCode);
		setTextByid(hypertensionFlup_down.getFlupDoctorName(),
				R.id.HypertensionFlup_upload_et_flupDoctorName);
		setTextByid(hypertensionFlup_down.getFlupOrgCode(),
				R.id.HypertensionFlup_upload_et_flupOrgCode);
		setTextByid(hypertensionFlup_down.getFlupOrgName(),
				R.id.HypertensionFlup_upload_et_flupOrgName);
		setTextByid(hypertensionFlup_down.getNextFlupDate(),
				R.id.HypertensionFlup_upload_et_nextFlupDate);

		// ///////////////////////////////////////////曾德森
		setTextByid(Basesence.getorgCode(),
				R.id.HypertensionFlup_upload_et_flupOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.HypertensionFlup_upload_et_flupOrgName);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.HypertensionFlup_upload_et_flupDoctorName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.HypertensionFlup_upload_et_flupDoctorCode);
		// ////////////////////
	}

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.HypertensionFlup_upload_et_SBP:
		case R.id.HypertensionFlup_upload_et_DBP:
		case R.id.HypertensionFlup_upload_et_heartRate:
			intent.setClass(GXYSFJLActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			break;

		default:
			break;
		}
		startActivityForResult(intent,
				Basesence.MEASURE_RESULT_LEFT_BLOOD_PRESURE);
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
