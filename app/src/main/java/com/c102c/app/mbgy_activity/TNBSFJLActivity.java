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
import android.widget.Toast;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.DiabetesFlup_down;
import com.c102c.app.model.DiabetesFlup_upload;
import com.c102c.app.utils.CustomDialog;
import com.c102c.app.utils.FourColmunAdapter;
import com.c102c.app.utils.Medication;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.util.ChoiceEditText;

public class TNBSFJLActivity extends Activity {

	private Button button;
	private Button addMedicineUsingButton;
	private ListView showMedicineUsingListView;

	private boolean tagEdit = false;

	private List<String[]> datas = null;
	private FourColmunAdapter mAdapter;
	private List<Medication> medicineUsingList = null;

	private DiabetesFlup_down diabetesFlup_down;
	private DiabetesFlup_upload diabetesFlup_upload;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initViews();
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		if (bundle != null) {
			if (bundle.get("from").equals("list")) {
				diabetesFlup_down = Basesence.getDiabetesFlup_down();
				setAllText();
				loadYYQKListView(diabetesFlup_down.getMedicationList());
				button.setVisibility(8);
			} else if (bundle.get("from").equals("new")) {
				setAllEditTextEnable(true);
				tagEdit = true;
				loadYYQKListView("");
				diabetesFlup_upload = new DiabetesFlup_upload();
				diabetesFlup_upload.setName(bundle.getString("name", ""));
				diabetesFlup_upload.setPersonId(bundle
						.getString("personid", ""));
				diabetesFlup_upload.setMachineCode(Basesence
						.getmac(TNBSFJLActivity.this));
				diabetesFlup_upload.setMachineNo(Basesence.getuuid());
				setAllupText();
				addMedicineUsingButton.setVisibility(View.VISIBLE);
			} else if (bundle.get("from").equals("localHistory")) {
				// 啟晖添加
				// 通过类的主键来从数据库寻找类，并添加到表中
				diabetesFlup_upload = new DiabetesFlup_upload();
				String primaryKey = Tools.getPrimaryKey(bundle
						.getString("uuid"));
				diabetesFlup_upload.setId(Long.parseLong(primaryKey));
				List<DiabetesFlup_upload> data = (List<DiabetesFlup_upload>) AppDB
						.query(diabetesFlup_upload);
				if (data.size() > 0) {
					diabetesFlup_upload = data.get(0);
				}
				setAllEditTextEnable(true);
				setAllupText();
			}
		}
		super.onCreate(savedInstanceState);
	}

	protected void initViews() {
		setContentView(R.layout.tnbsfjl_upload);
		setAllEditTextEnable(false);
		diabetesFlup_down = new DiabetesFlup_down();
		diabetesFlup_upload = new DiabetesFlup_upload();
		button = (Button) findViewById(R.id.tnbsfjl_btn_bianji);
		button.setOnClickListener(clicklistener);
		addMedicineUsingButton = (Button) findViewById(R.id.add_yyqkButton_in_tnbsfjl);
		showMedicineUsingListView = (ListView) findViewById(R.id.medicine_usingListView_in_tnbsfjl);
		addMedicineUsingButton.setOnClickListener(clicklistener);
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
		mAdapter = new FourColmunAdapter(TNBSFJLActivity.this, datas);
		showMedicineUsingListView.setAdapter(mAdapter);
		if (tagEdit) {
			showMedicineUsingListView
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								final int arg2, long arg3) {
							// TODO Auto-generated method stub

							CustomDialog.YYSM_Edit_Delete(TNBSFJLActivity.this,
									mAdapter, arg2).notifyDataSetChanged();
							;
						}
					});
		}
	}

	private OnClickListener clicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == button) {
				if (!checkAllEditTextIsNull())
					Fetch_by_li.communicate("M0100070201",
							TNBSFJLActivity.this, handler, getAllText());
			} else if (v == addMedicineUsingButton) {
				CustomDialog.YYSM_Add(TNBSFJLActivity.this, mAdapter)
						.notifyDataSetChanged();
			}
		}
	};
	private Handler handler = Basesence.getupdatehandler(this);

	/** 检查EditText不为空。 */
	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_personId,
				"居民个人Id")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_machineCode,
				"健康一体机编码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_machineNo,
				"一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_name, "姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_flupDate,
				"随访日期 ")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_DBP, "舒张压")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_SBP, "收缩压")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_weight, "体重")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.DiabetesFlup_upload_et_fastingBloodGlucose, "空腹血糖")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_flupDoctorCode,
				"随访医生代码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.DiabetesFlup_upload_et_flupOrgCode,
				"随访机构代码")) {
			return true;
		}

		return false;
	}

	/** 设置EditText为空时的提示消息 */
	private boolean setEditTextNullMessage(int etId, String message) {
		String str = "不能为空";
		if (TextUtils.isEmpty(((EditText) findViewById(etId)).getText()
				.toString().trim())) {
			Toast.makeText(TNBSFJLActivity.this, message + str,
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	public void setTextByid(String content, int id) {
		if (!TextUtils.isEmpty(content)) {
			EditText et = (EditText) findViewById(id);
			et.setText(content);
		}
	}

	/** 给editText设置要选择的数组 */
	public void setChoiceEditText(int et_Id, int arrayId) {
		if (arrayId == -1) {
			((ChoiceEditText) findViewById(et_Id)).setFixItems(null);
		} else
			((ChoiceEditText) findViewById(et_Id)).setFixItems(getResources()
					.getStringArray(arrayId));
	}

	private void setAllEditTextEnable(boolean enabled) {
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_machineCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_machineNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_name))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupMode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_symptomCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_symptomOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_weight))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_weightTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_bmi))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_dorsalisPedisPulseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_bmiTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_signsOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailySmoking))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailySmokingTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailyDrinking))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailyDrinkingTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekMovements))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekMovementsTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekTimesTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_stapleFood))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_stapleFoodTarget))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_psychologicalCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_complianceCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_fastingBloodGlucose))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_HbA1c))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_aidCheckDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_aidCheck))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_doseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_adverseReactionCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_lowBloodSugarCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupTypeCode))
				.setEnabled(enabled);
		// ((EditText)findViewById(R.id.DiabetesFlup_upload_et_medicationList)).setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_insulinMedicationType))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_insulinMedicationRate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_insulinMedicationDose))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_referralReason))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_referralOrg))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_referralDepartment))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupOrgName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_nextFlupDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.DiabetesFlup_upload_et_bloodPressureLevel))
				.setEnabled(enabled);
		if (enabled) {
			setChoiceEditText(R.id.DiabetesFlup_upload_et_symptomCodes,
					R.array.DiabetesFlup_upload_et_symptomCodes);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_flupMode,
					R.array.DiabetesFlup_upload_et_flupMode);
			setChoiceEditText(
					R.id.DiabetesFlup_upload_et_dorsalisPedisPulseCode,
					R.array.DiabetesFlup_upload_et_dorsalisPedisPulseCode);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_psychologicalCode,
					R.array.DiabetesFlup_upload_et_psychologicalCode);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_complianceCode,
					R.array.DiabetesFlup_upload_et_complianceCode);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_doseCode,
					R.array.DiabetesFlup_upload_et_doseCode);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_adverseReactionCode,
					R.array.DiabetesFlup_upload_et_adverseReactionCode);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_lowBloodSugarCode,
					R.array.DiabetesFlup_upload_et_lowBloodSugarCode);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_flupTypeCode,
					R.array.DiabetesFlup_upload_et_flupTypeCode);
		} else {
			setChoiceEditText(R.id.DiabetesFlup_upload_et_symptomCodes, -1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_flupMode, -1);
			setChoiceEditText(
					R.id.DiabetesFlup_upload_et_dorsalisPedisPulseCode, -1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_psychologicalCode, -1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_complianceCode, -1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_doseCode, -1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_adverseReactionCode,
					-1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_lowBloodSugarCode, -1);
			setChoiceEditText(R.id.DiabetesFlup_upload_et_flupTypeCode, -1);
		}
	}

	protected DiabetesFlup_upload getAllText() {
		if (diabetesFlup_upload == null) {
			diabetesFlup_upload = new DiabetesFlup_upload();
		}
		diabetesFlup_upload
				.setPersonId(((EditText) findViewById(R.id.DiabetesFlup_upload_et_personId))
						.getText().toString());
		diabetesFlup_upload
				.setSpecialNo(((EditText) findViewById(R.id.DiabetesFlup_upload_et_specialNo))
						.getText().toString());
		diabetesFlup_upload
				.setMachineCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_machineCode))
						.getText().toString());
		diabetesFlup_upload
				.setMachineNo(((EditText) findViewById(R.id.DiabetesFlup_upload_et_machineNo))
						.getText().toString());
		diabetesFlup_upload
				.setName(((EditText) findViewById(R.id.DiabetesFlup_upload_et_name))
						.getText().toString());
		diabetesFlup_upload
				.setFlupDate(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupDate))
						.getText().toString());
		diabetesFlup_upload
				.setFlupMode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupMode))
						.getText().toString());
		diabetesFlup_upload
				.setSymptomCodes(((EditText) findViewById(R.id.DiabetesFlup_upload_et_symptomCodes))
						.getText().toString());
		diabetesFlup_upload
				.setSymptomOther(((EditText) findViewById(R.id.DiabetesFlup_upload_et_symptomOther))
						.getText().toString());
		diabetesFlup_upload
				.setSBP(((EditText) findViewById(R.id.DiabetesFlup_upload_et_SBP))
						.getText().toString());
		diabetesFlup_upload
				.setDBP(((EditText) findViewById(R.id.DiabetesFlup_upload_et_DBP))
						.getText().toString());
		diabetesFlup_upload
				.setWeight(((EditText) findViewById(R.id.DiabetesFlup_upload_et_weight))
						.getText().toString());
		diabetesFlup_upload
				.setWeightTarget(((EditText) findViewById(R.id.DiabetesFlup_upload_et_weightTarget))
						.getText().toString());
		diabetesFlup_upload
				.setBmi(((EditText) findViewById(R.id.DiabetesFlup_upload_et_bmi))
						.getText().toString());
		diabetesFlup_upload
				.setDorsalisPedisPulseCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_dorsalisPedisPulseCode))
						.getText().toString());
		diabetesFlup_upload
				.setSignsOther(((EditText) findViewById(R.id.DiabetesFlup_upload_et_signsOther))
						.getText().toString());
		diabetesFlup_upload
				.setDailySmoking(((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailySmoking))
						.getText().toString());
		diabetesFlup_upload
				.setDailySmokingTarget(((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailySmokingTarget))
						.getText().toString());
		diabetesFlup_upload
				.setDailyDrinking(((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailyDrinking))
						.getText().toString());
		diabetesFlup_upload
				.setDailyDrinkingTarget(((EditText) findViewById(R.id.DiabetesFlup_upload_et_dailyDrinkingTarget))
						.getText().toString());
		diabetesFlup_upload
				.setPerWeekMovements(((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekMovements))
						.getText().toString());
		diabetesFlup_upload
				.setPerWeekMovementsTarget(((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekMovementsTarget))
						.getText().toString());
		diabetesFlup_upload
				.setPerWeekTimes(((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekTimes))
						.getText().toString());
		diabetesFlup_upload
				.setPerWeekTimesTarget(((EditText) findViewById(R.id.DiabetesFlup_upload_et_perWeekTimesTarget))
						.getText().toString());
		diabetesFlup_upload
				.setStapleFood(((EditText) findViewById(R.id.DiabetesFlup_upload_et_stapleFood))
						.getText().toString());
		diabetesFlup_upload
				.setStapleFoodTarget(((EditText) findViewById(R.id.DiabetesFlup_upload_et_stapleFoodTarget))
						.getText().toString());
		diabetesFlup_upload
				.setPsychologicalCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_psychologicalCode))
						.getText().toString());
		diabetesFlup_upload
				.setComplianceCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_complianceCode))
						.getText().toString());
		diabetesFlup_upload
				.setFastingBloodGlucose(((EditText) findViewById(R.id.DiabetesFlup_upload_et_fastingBloodGlucose))
						.getText().toString());
		diabetesFlup_upload
				.setHbA1c(((EditText) findViewById(R.id.DiabetesFlup_upload_et_HbA1c))
						.getText().toString());
		diabetesFlup_upload
				.setAidCheckDate(((EditText) findViewById(R.id.DiabetesFlup_upload_et_aidCheckDate))
						.getText().toString());
		diabetesFlup_upload
				.setAidCheck(((EditText) findViewById(R.id.DiabetesFlup_upload_et_aidCheck))
						.getText().toString());
		diabetesFlup_upload
				.setDoseCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_doseCode))
						.getText().toString());
		diabetesFlup_upload
				.setAdverseReactionCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_adverseReactionCode))
						.getText().toString());
		diabetesFlup_upload
				.setLowBloodSugarCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_lowBloodSugarCode))
						.getText().toString());
		diabetesFlup_upload
				.setFlupTypeCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupTypeCode))
						.getText().toString());
		// diabetesFlup_upload
		// .setMedicationList(((EditText)
		// findViewById(R.id.DiabetesFlup_upload_et_medicationList))
		// .getText().toString());
		diabetesFlup_upload
				.setInsulinMedicationType(((EditText) findViewById(R.id.DiabetesFlup_upload_et_insulinMedicationType))
						.getText().toString());
		diabetesFlup_upload
				.setInsulinMedicationRate(((EditText) findViewById(R.id.DiabetesFlup_upload_et_insulinMedicationRate))
						.getText().toString());
		diabetesFlup_upload
				.setInsulinMedicationDose(((EditText) findViewById(R.id.DiabetesFlup_upload_et_insulinMedicationDose))
						.getText().toString());
		diabetesFlup_upload
				.setReferralReason(((EditText) findViewById(R.id.DiabetesFlup_upload_et_referralReason))
						.getText().toString());
		diabetesFlup_upload
				.setReferralOrg(((EditText) findViewById(R.id.DiabetesFlup_upload_et_referralOrg))
						.getText().toString());
		diabetesFlup_upload
				.setReferralDepartment(((EditText) findViewById(R.id.DiabetesFlup_upload_et_referralDepartment))
						.getText().toString());
		diabetesFlup_upload
				.setFlupDoctorCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupDoctorCode))
						.getText().toString());
		diabetesFlup_upload
				.setFlupDoctorName(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupDoctorName))
						.getText().toString());
		diabetesFlup_upload
				.setFlupOrgCode(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupOrgCode))
						.getText().toString());
		diabetesFlup_upload
				.setFlupOrgName(((EditText) findViewById(R.id.DiabetesFlup_upload_et_flupOrgName))
						.getText().toString());
		diabetesFlup_upload
				.setNextFlupDate(((EditText) findViewById(R.id.DiabetesFlup_upload_et_nextFlupDate))
						.getText().toString());
		return diabetesFlup_upload;
	}

	protected void setAllText() {
		// setTextByid(diabetesFlup.getCaseSourceOther(),
		// R.id.DiabetesFlup_et_caseSourceOther);
		// setTextByid(diabetesFlup.getId()+"", R.id.DiabetesFlup_et_id);
		setTextByid(diabetesFlup_down.getPersonId(),
				R.id.DiabetesFlup_upload_et_personId);
		// setTextByid(diabetesFlup_down.getHealthFileNumber(),
		// R.id.DiabetesFlup_upload_et_healthFileNumber);
		setTextByid(diabetesFlup_down.getName(),
				R.id.DiabetesFlup_upload_et_name);
		setTextByid(diabetesFlup_down.getFlupDate(),
				R.id.DiabetesFlup_upload_et_flupDate);
		setTextByid(diabetesFlup_down.getFlupMode(),
				R.id.DiabetesFlup_upload_et_flupMode);
		setTextByid(diabetesFlup_down.getSymptomCodes(),
				R.id.DiabetesFlup_upload_et_symptomCodes);
		setTextByid(diabetesFlup_down.getSymptomOther(),
				R.id.DiabetesFlup_upload_et_symptomOther);
		setTextByid(diabetesFlup_down.getSBP(), R.id.DiabetesFlup_upload_et_SBP);
		setTextByid(diabetesFlup_down.getDBP(), R.id.DiabetesFlup_upload_et_DBP);
		setTextByid(diabetesFlup_down.getWeight(),
				R.id.DiabetesFlup_upload_et_weight);
		setTextByid(diabetesFlup_down.getWeightTarget(),
				R.id.DiabetesFlup_upload_et_weightTarget);
		setTextByid(diabetesFlup_down.getBmi(), R.id.DiabetesFlup_upload_et_bmi);
		setTextByid(diabetesFlup_down.getDorsalisPedisPulseCode(),
				R.id.DiabetesFlup_upload_et_dorsalisPedisPulseCode);
		setTextByid(diabetesFlup_down.getSignsOther(),
				R.id.DiabetesFlup_upload_et_signsOther);
		setTextByid(diabetesFlup_down.getDailySmoking(),
				R.id.DiabetesFlup_upload_et_dailySmoking);
		setTextByid(diabetesFlup_down.getDailySmokingTarget(),
				R.id.DiabetesFlup_upload_et_dailySmokingTarget);
		setTextByid(diabetesFlup_down.getDailyDrinking(),
				R.id.DiabetesFlup_upload_et_dailyDrinking);
		setTextByid(diabetesFlup_down.getDailyDrinkingTarget(),
				R.id.DiabetesFlup_upload_et_dailyDrinkingTarget);
		setTextByid(diabetesFlup_down.getPerWeekMovements(),
				R.id.DiabetesFlup_upload_et_perWeekMovements);
		setTextByid(diabetesFlup_down.getPerWeekMovementsTarget(),
				R.id.DiabetesFlup_upload_et_perWeekMovementsTarget);
		setTextByid(diabetesFlup_down.getPerWeekTimes(),
				R.id.DiabetesFlup_upload_et_perWeekTimes);
		setTextByid(diabetesFlup_down.getPerWeekTimesTarget(),
				R.id.DiabetesFlup_upload_et_perWeekTimesTarget);
		setTextByid(diabetesFlup_down.getStapleFood(),
				R.id.DiabetesFlup_upload_et_stapleFood);
		setTextByid(diabetesFlup_down.getStapleFoodTarget(),
				R.id.DiabetesFlup_upload_et_stapleFoodTarget);
		setTextByid(diabetesFlup_down.getPsychologicalCode(),
				R.id.DiabetesFlup_upload_et_psychologicalCode);
		setTextByid(diabetesFlup_down.getComplianceCode(),
				R.id.DiabetesFlup_upload_et_complianceCode);
		setTextByid(diabetesFlup_down.getFastingBloodGlucose(),
				R.id.DiabetesFlup_upload_et_fastingBloodGlucose);
		setTextByid(diabetesFlup_down.getHbA1c(),
				R.id.DiabetesFlup_upload_et_HbA1c);
		setTextByid(diabetesFlup_down.getAidCheckDate(),
				R.id.DiabetesFlup_upload_et_aidCheckDate);
		setTextByid(diabetesFlup_down.getAidCheck(),
				R.id.DiabetesFlup_upload_et_aidCheck);
		setTextByid(diabetesFlup_down.getDoseCode(),
				R.id.DiabetesFlup_upload_et_doseCode);
		setTextByid(diabetesFlup_down.getAdverseReactionCode(),
				R.id.DiabetesFlup_upload_et_adverseReactionCode);
		setTextByid(diabetesFlup_down.getLowBloodSugarCode(),
				R.id.DiabetesFlup_upload_et_lowBloodSugarCode);
		setTextByid(diabetesFlup_down.getFlupTypeCode(),
				R.id.DiabetesFlup_upload_et_flupTypeCode);
		// setTextByid(diabetesFlup_down.getMedicationList(),
		// R.id.DiabetesFlup_upload_et_medicationList);
		setTextByid(diabetesFlup_down.getInsulinMedicationType(),
				R.id.DiabetesFlup_upload_et_insulinMedicationType);
		setTextByid(diabetesFlup_down.getSpecialNo(),
				R.id.DiabetesFlup_upload_et_specialNo);
		setTextByid(diabetesFlup_down.getInsulinMedicationRate(),
				R.id.DiabetesFlup_upload_et_insulinMedicationRate);
		setTextByid(diabetesFlup_down.getInsulinMedicationDose(),
				R.id.DiabetesFlup_upload_et_insulinMedicationDose);
		setTextByid(diabetesFlup_down.getReferralReason(),
				R.id.DiabetesFlup_upload_et_referralReason);
		setTextByid(diabetesFlup_down.getReferralOrg(),
				R.id.DiabetesFlup_upload_et_referralOrg);
		setTextByid(diabetesFlup_down.getReferralDepartment(),
				R.id.DiabetesFlup_upload_et_referralDepartment);
		setTextByid(diabetesFlup_down.getFlupDoctorCode(),
				R.id.DiabetesFlup_upload_et_flupDoctorCode);
		setTextByid(diabetesFlup_down.getFlupDoctorName(),
				R.id.DiabetesFlup_upload_et_flupDoctorName);
		setTextByid(diabetesFlup_down.getFlupOrgCode(),
				R.id.DiabetesFlup_upload_et_flupOrgCode);
		setTextByid(diabetesFlup_down.getFlupOrgName(),
				R.id.DiabetesFlup_upload_et_flupOrgName);
		setTextByid(diabetesFlup_down.getNextFlupDate(),
				R.id.DiabetesFlup_upload_et_nextFlupDate);

	}

	protected void setAllupText() {
		// setTextByid(diabetesFlup.getCaseSourceOther(),
		// R.id.DiabetesFlup_upload_et_caseSourceOther);
		// setTextByid(diabetesFlup.getId()+"", R.id.DiabetesFlup_upload_et_id);
		setTextByid(diabetesFlup_upload.getPersonId(),
				R.id.DiabetesFlup_upload_et_personId);
		setTextByid(diabetesFlup_upload.getMachineCode(),
				R.id.DiabetesFlup_upload_et_machineCode);
		setTextByid(diabetesFlup_upload.getMachineNo(),
				R.id.DiabetesFlup_upload_et_machineNo);
		setTextByid(diabetesFlup_upload.getName(),
				R.id.DiabetesFlup_upload_et_name);
		setTextByid(diabetesFlup_upload.getFlupDate(),
				R.id.DiabetesFlup_upload_et_flupDate);
		setTextByid(diabetesFlup_upload.getFlupMode(),
				R.id.DiabetesFlup_upload_et_flupMode);
		setTextByid(diabetesFlup_upload.getSymptomCodes(),
				R.id.DiabetesFlup_upload_et_symptomCodes);
		setTextByid(diabetesFlup_upload.getSymptomOther(),
				R.id.DiabetesFlup_upload_et_symptomOther);
		setTextByid(diabetesFlup_upload.getSBP(),
				R.id.DiabetesFlup_upload_et_SBP);
		setTextByid(diabetesFlup_upload.getDBP(),
				R.id.DiabetesFlup_upload_et_DBP);
		setTextByid(diabetesFlup_upload.getWeight(),
				R.id.DiabetesFlup_upload_et_weight);
		setTextByid(diabetesFlup_upload.getWeightTarget(),
				R.id.DiabetesFlup_upload_et_weightTarget);
		setTextByid(diabetesFlup_upload.getBmi(),
				R.id.DiabetesFlup_upload_et_bmi);
		setTextByid(diabetesFlup_upload.getDorsalisPedisPulseCode(),
				R.id.DiabetesFlup_upload_et_dorsalisPedisPulseCode);
		setTextByid(diabetesFlup_upload.getSignsOther(),
				R.id.DiabetesFlup_upload_et_signsOther);
		setTextByid(diabetesFlup_upload.getDailySmoking(),
				R.id.DiabetesFlup_upload_et_dailySmoking);
		setTextByid(diabetesFlup_upload.getDailySmokingTarget(),
				R.id.DiabetesFlup_upload_et_dailySmokingTarget);
		setTextByid(diabetesFlup_upload.getDailyDrinking(),
				R.id.DiabetesFlup_upload_et_dailyDrinking);
		setTextByid(diabetesFlup_upload.getDailyDrinkingTarget(),
				R.id.DiabetesFlup_upload_et_dailyDrinkingTarget);
		setTextByid(diabetesFlup_upload.getPerWeekMovements(),
				R.id.DiabetesFlup_upload_et_perWeekMovements);
		setTextByid(diabetesFlup_upload.getPerWeekMovementsTarget(),
				R.id.DiabetesFlup_upload_et_perWeekMovementsTarget);
		setTextByid(diabetesFlup_upload.getPerWeekTimes(),
				R.id.DiabetesFlup_upload_et_perWeekTimes);
		setTextByid(diabetesFlup_upload.getPerWeekTimesTarget(),
				R.id.DiabetesFlup_upload_et_perWeekTimesTarget);
		setTextByid(diabetesFlup_upload.getStapleFood(),
				R.id.DiabetesFlup_upload_et_stapleFood);
		setTextByid(diabetesFlup_upload.getStapleFoodTarget(),
				R.id.DiabetesFlup_upload_et_stapleFoodTarget);
		setTextByid(diabetesFlup_upload.getPsychologicalCode(),
				R.id.DiabetesFlup_upload_et_psychologicalCode);
		setTextByid(diabetesFlup_upload.getComplianceCode(),
				R.id.DiabetesFlup_upload_et_complianceCode);
		setTextByid(diabetesFlup_upload.getFastingBloodGlucose(),
				R.id.DiabetesFlup_upload_et_fastingBloodGlucose);
		setTextByid(diabetesFlup_upload.getHbA1c(),
				R.id.DiabetesFlup_upload_et_HbA1c);
		setTextByid(diabetesFlup_upload.getAidCheckDate(),
				R.id.DiabetesFlup_upload_et_aidCheckDate);
		setTextByid(diabetesFlup_upload.getAidCheck(),
				R.id.DiabetesFlup_upload_et_aidCheck);
		setTextByid(diabetesFlup_upload.getDoseCode(),
				R.id.DiabetesFlup_upload_et_doseCode);
		setTextByid(diabetesFlup_upload.getAdverseReactionCode(),
				R.id.DiabetesFlup_upload_et_adverseReactionCode);
		setTextByid(diabetesFlup_upload.getLowBloodSugarCode(),
				R.id.DiabetesFlup_upload_et_lowBloodSugarCode);
		setTextByid(diabetesFlup_upload.getFlupTypeCode(),
				R.id.DiabetesFlup_upload_et_flupTypeCode);
		setTextByid(diabetesFlup_upload.getMedicationList(),
				R.id.DiabetesFlup_upload_et_medicationList);
		setTextByid(diabetesFlup_upload.getInsulinMedicationType(),
				R.id.DiabetesFlup_upload_et_insulinMedicationType);
		setTextByid(diabetesFlup_upload.getInsulinMedicationRate(),
				R.id.DiabetesFlup_upload_et_insulinMedicationRate);
		setTextByid(diabetesFlup_upload.getInsulinMedicationDose(),
				R.id.DiabetesFlup_upload_et_insulinMedicationDose);
		setTextByid(diabetesFlup_upload.getReferralReason(),
				R.id.DiabetesFlup_upload_et_referralReason);
		setTextByid(diabetesFlup_upload.getReferralOrg(),
				R.id.DiabetesFlup_upload_et_referralOrg);
		setTextByid(diabetesFlup_upload.getReferralDepartment(),
				R.id.DiabetesFlup_upload_et_referralDepartment);
		setTextByid(diabetesFlup_upload.getFlupDoctorCode(),
				R.id.DiabetesFlup_upload_et_flupDoctorCode);
		setTextByid(diabetesFlup_upload.getFlupDoctorName(),
				R.id.DiabetesFlup_upload_et_flupDoctorName);
		setTextByid(diabetesFlup_upload.getFlupOrgCode(),
				R.id.DiabetesFlup_upload_et_flupOrgCode);
		setTextByid(diabetesFlup_upload.getFlupOrgName(),
				R.id.DiabetesFlup_upload_et_flupOrgName);
		setTextByid(diabetesFlup_upload.getNextFlupDate(),
				R.id.DiabetesFlup_upload_et_nextFlupDate);
		// ////////////////////////////////////////////////曾德森
		setTextByid(Basesence.getorgName(),
				R.id.DiabetesFlup_upload_et_flupOrgName);
		setTextByid(Basesence.getorgCode(),
				R.id.DiabetesFlup_upload_et_flupOrgCode);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.DiabetesFlup_upload_et_flupDoctorCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.DiabetesFlup_upload_et_flupDoctorName);

	}

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.DiabetesFlup_upload_et_SBP:
		case R.id.DiabetesFlup_upload_et_DBP:
			intent.setClass(this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			break;
		case R.id.DiabetesFlup_upload_et_fastingBloodGlucose:
			intent.setClass(this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_SUGAR);
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
			((EditText) findViewById(R.id.DiabetesFlup_upload_et_SBP))
					.setText(data.getStringExtra("ssy"));
			((EditText) findViewById(R.id.DiabetesFlup_upload_et_DBP))
					.setText(data.getStringExtra("szy"));

			break;
		case Basesence.MEASURE_RESULT_BLOOD_SUGAR:
			((EditText) findViewById(R.id.DiabetesFlup_upload_et_fastingBloodGlucose))
					.setText(data.getStringExtra("xt"));
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
