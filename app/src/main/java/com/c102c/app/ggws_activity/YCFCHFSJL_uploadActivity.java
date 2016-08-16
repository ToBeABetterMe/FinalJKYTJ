package com.c102c.app.ggws_activity;

import java.util.List;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.PostpartumVisit_upload;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ETempActivity;
import com.health.measurement.MeasureOnPC300Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/** 孕产妇产后访视 记录上传 */
public class YCFCHFSJL_uploadActivity extends BaseActivity {

	private PostpartumVisit_upload postpartumVisit_upload;
	private String healfilenumber = "";
	PregnantSpecial_down pregnantSpecial_down;
	String specialNo = null;

	@Override
	protected void initViews() {
		setContentView(R.layout.ycfchfsjl_upload);
		setAllEditTextEnable(true);

		postpartumVisit_upload = new PostpartumVisit_upload();
		pregnantSpecial_down = Basesence.getPregnantSpecial_down();
		getAllText();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		;
		setAllEditTextEnable(true);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			postpartumVisit_upload = new PostpartumVisit_upload();
			if (bundle.getString("from") != null) {

				if (bundle.getString("from").equals("localHistory")) {
					// 通过类的主键来从数据库寻找类，并添加到表中
					String primaryKey = Tools.getPrimaryKey(bundle
							.getString("uuid"));
					postpartumVisit_upload.setId(Long.parseLong(primaryKey));
					List<PostpartumVisit_upload> data = (List<PostpartumVisit_upload>) AppDB
							.query(postpartumVisit_upload);
					if (data.size() > 0) {
						postpartumVisit_upload = data.get(0);
					}
				}
				setAllEditTextEnable(true);
				// 修改位置
				setAllText();
			}

		} else {

			specialNo = pregnantSpecial_down.getSpecialNo();
			if (specialNo != null) {
				AppDB app = AppDB.getInstance(this);
				List<Object> list = app.get(new HealthRecord_down());
				HealthRecord_down heal;
				for (Object obj : list) {
					heal = (HealthRecord_down) obj;
					if (heal.getHealthFileNumber() != "") {
						healfilenumber = heal.getHealthFileNumber();
						Basesence.setHealFileNumber(healfilenumber);
						break;
					}
				}
				if (healfilenumber == null || healfilenumber.length() <= 0) {
					String a = "<requestParams pageStart=\"1\" pageSize=\"1\">"
							+ "<healthRecord>" + "<householdRegisterCode>"
							+ Basesence.getAdminAreaCode()
							+ "</householdRegisterCode>" + "<name>"
							+ pregnantSpecial_down.getName() + "</name>"
							+ "</healthRecord></requestParams>";
					Fetch.communicate("M0100020101", this, handler, a);
				}
			}
			settext();
		}
	}

	protected void setAllText() {
		setTextByid(postpartumVisit_upload.getPersonId(),
				R.id.PostpartumVisit_upload_et_personId);
		setTextByid(postpartumVisit_upload.getSpecialNo(),
				R.id.PostpartumVisit_upload_et_specialNo);
		setTextByid(postpartumVisit_upload.getMachineCode(),
				R.id.PostpartumVisit_upload_et_machineCode);
		setTextByid(postpartumVisit_upload.getMachineNo(),
				R.id.PostpartumVisit_upload_et_machineNo);
		setTextByid(postpartumVisit_upload.getPregnantManualNo(),
				R.id.PostpartumVisit_upload_et_pregnantManualNo);
		setTextByid(postpartumVisit_upload.getName(),
				R.id.PostpartumVisit_upload_et_name);
		setTextByid(postpartumVisit_upload.getFlupDate(),
				R.id.PostpartumVisit_upload_et_flupDate);
		setTextByid(postpartumVisit_upload.getTemperature(),
				R.id.PostpartumVisit_upload_et_temperature);
		setTextByid(postpartumVisit_upload.getHealthDesc(),
				R.id.PostpartumVisit_upload_et_healthDesc);
		setTextByid(postpartumVisit_upload.getPsychologicStatus(),
				R.id.PostpartumVisit_upload_et_psychologicStatus);
		setTextByid(postpartumVisit_upload.getSBP(),
				R.id.PostpartumVisit_upload_et_SBP);
		setTextByid(postpartumVisit_upload.getDBP(),
				R.id.PostpartumVisit_upload_et_DBP);
		setTextByid(postpartumVisit_upload.getBreastCode(),
				R.id.PostpartumVisit_upload_et_breastCode);
		setTextByid(postpartumVisit_upload.getBreastDesc(),
				R.id.PostpartumVisit_upload_et_breastDesc);
		setTextByid(postpartumVisit_upload.getLochiaCode(),
				R.id.PostpartumVisit_upload_et_lochiaCode);
		setTextByid(postpartumVisit_upload.getLochiaDesc(),
				R.id.PostpartumVisit_upload_et_lochiaDesc);
		setTextByid(postpartumVisit_upload.getUterusCode(),
				R.id.PostpartumVisit_upload_et_uterusCode);
		setTextByid(postpartumVisit_upload.getUterusDesc(),
				R.id.PostpartumVisit_upload_et_uterusDesc);
		setTextByid(postpartumVisit_upload.getWoundCode(),
				R.id.PostpartumVisit_upload_et_woundCode);
		setTextByid(postpartumVisit_upload.getWoundDesc(),
				R.id.PostpartumVisit_upload_et_woundDesc);
		setTextByid(postpartumVisit_upload.getOther(),
				R.id.PostpartumVisit_upload_et_other);
		setTextByid(postpartumVisit_upload.getTypeCode(),
				R.id.PostpartumVisit_upload_et_typeCode);
		setTextByid(postpartumVisit_upload.getTypeDesc(),
				R.id.PostpartumVisit_upload_et_typeDesc);
		setTextByid(postpartumVisit_upload.getGuideCodes(),
				R.id.PostpartumVisit_upload_et_guideCodes);
		setTextByid(postpartumVisit_upload.getGuideOther(),
				R.id.PostpartumVisit_upload_et_guideOther);
		setTextByid(postpartumVisit_upload.getReferralCode(),
				R.id.PostpartumVisit_upload_et_referralCode);
		setTextByid(postpartumVisit_upload.getReferralReason(),
				R.id.PostpartumVisit_upload_et_referralReason);
		setTextByid(postpartumVisit_upload.getReferralOrg(),
				R.id.PostpartumVisit_upload_et_referralOrg);
		setTextByid(postpartumVisit_upload.getReferralDepartment(),
				R.id.PostpartumVisit_upload_et_referralDepartment);
		setTextByid(postpartumVisit_upload.getVisitDoctorCode(),
				R.id.PostpartumVisit_upload_et_visitDoctorCode);
		setTextByid(postpartumVisit_upload.getVisitDoctorName(),
				R.id.PostpartumVisit_upload_et_visitDoctorName);
		setTextByid(postpartumVisit_upload.getVisitOrgCode(),
				R.id.PostpartumVisit_upload_et_visitOrgCode);
		setTextByid(postpartumVisit_upload.getVisitOrgName(),
				R.id.PostpartumVisit_upload_et_visitOrgName);
		setTextByid(postpartumVisit_upload.getNextFlupDate(),
				R.id.PostpartumVisit_upload_et_nextFlupDate);

	}

	private void settext() {
		// TODO Auto-generated method stub
		setTextByid(pregnantSpecial_down.getName(),
				R.id.PostpartumVisit_upload_et_name);
		setTextByid(pregnantSpecial_down.getPersonId(),
				R.id.PostpartumVisit_upload_et_personId);

		setTextByid(Basesence.getmac(this),
				R.id.PostpartumVisit_upload_et_machineCode);

		setTextByid(Basesence.getorgCode(),
				R.id.PostpartumVisit_upload_et_visitOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.PostpartumVisit_upload_et_visitOrgName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.PostpartumVisit_upload_et_visitDoctorCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.PostpartumVisit_upload_et_visitDoctorName);
	}

	public void upload(View view) {

		switch (view.getId()) {

		case R.id.ycfchfsjl_upload_btn_upload:

			AppDB.getInstance(this);
			if (checkAllEditTextIsNull()) {
				return;
			}
			Fetch_by_li.communicate("M0100040203", this, handler, getAllText());

			break;

		default:
			break;
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			String a = (String) msg.obj;
			//system.out
//					.println("原来的值" + Basesence.M0100040203 + "现在" + msg.arg1);
			if (msg.what == Basesence.commusucc) {
				switch (msg.arg1) {
				case Basesence.M0100020101:
					if (a.indexOf("xml") != -1) {
						List<HealthRecord_down> heal = Util
								.getHealthRecord_down(a);
						healfilenumber = heal.get(0).getHealthFileNumber();
						Basesence.setHealFileNumber(healfilenumber);
					} else {
						Toast.makeText(YCFCHFSJL_uploadActivity.this,
								"没找到这个用户的健康档案", 0).show();
					}
					// Toast.makeText(XSEJTFSJL_uploadActivity.this, "成功",
					// 0).show();
					break;
				case Basesence.M0100040203:
					Toast.makeText(YCFCHFSJL_uploadActivity.this, "成功", 0)
							.show();
					break;
				case 0:
					Toast.makeText(YCFCHFSJL_uploadActivity.this,
							"该孕妇已经做过检测，请勿继续操作！", 0).show();
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				Toast.makeText(YCFCHFSJL_uploadActivity.this,
						a, 0).show();
			}
			super.handleMessage(msg);
		}

	};

	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.PostpartumVisit_upload_et_personId,
				"居民个人ID")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PostpartumVisit_upload_et_machineCode,
				"健康一体机编码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PostpartumVisit_upload_et_machineNo,
				"一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PostpartumVisit_upload_et_name, "孕妇姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PostpartumVisit_upload_et_flupDate,
				"随访日期")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PostpartumVisit_upload_et_visitDoctorCode, "访视医生代码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PostpartumVisit_upload_et_visitOrgCode,
				"访视机构代码")) {
			return true;
		}
		return false;
	}

	private void setAllEditTextEnable(boolean enabled) {
		if (enabled) {
			setChoiceEditText(R.id.PostpartumVisit_upload_et_breastCode,
					R.array.PostpartumVisit_upload_et_breastCode);
			setChoiceEditText(R.id.PostpartumVisit_upload_et_lochiaCode,
					R.array.PostpartumVisit_upload_et_lochiaCode);
			setChoiceEditText(R.id.PostpartumVisit_upload_et_uterusCode,
					R.array.PostpartumVisit_upload_et_uterusCode);
			setChoiceEditText(R.id.PostpartumVisit_upload_et_woundCode,
					R.array.PostpartumVisit_upload_et_woundCode);
			setChoiceEditText(R.id.PostpartumVisit_upload_et_typeCode,
					R.array.PostpartumVisit_upload_et_typeCode);
			setChoiceEditText(R.id.PostpartumVisit_upload_et_guideCodes,
					R.array.PostpartumVisit_upload_et_guideCodes);
			setChoiceEditText(R.id.PostpartumVisit_upload_et_referralCode,
					R.array.PostpartumVisit_upload_et_referralCode);
		}
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_machineCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_machineNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_pregnantManualNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_name))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_flupDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_temperature))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_healthDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_psychologicStatus))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_breastCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_breastDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_lochiaCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_lochiaDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_uterusCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_uterusDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_woundCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_woundDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_other))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_typeCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_typeDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_guideCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_guideOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralReason))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralOrg))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralDepartment))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitOrgName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PostpartumVisit_upload_et_nextFlupDate))
				.setEnabled(enabled);

	}

	@Override
	protected PostpartumVisit_upload getAllText() {
		postpartumVisit_upload
				.setPersonId(((EditText) findViewById(R.id.PostpartumVisit_upload_et_personId))
						.getText().toString());
		postpartumVisit_upload
				.setSpecialNo(((EditText) findViewById(R.id.PostpartumVisit_upload_et_specialNo))
						.getText().toString());
		postpartumVisit_upload
				.setMachineCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_machineCode))
						.getText().toString());
		postpartumVisit_upload
				.setMachineNo(((EditText) findViewById(R.id.PostpartumVisit_upload_et_machineNo))
						.getText().toString());
		postpartumVisit_upload
				.setPregnantManualNo(((EditText) findViewById(R.id.PostpartumVisit_upload_et_pregnantManualNo))
						.getText().toString());
		postpartumVisit_upload
				.setName(((EditText) findViewById(R.id.PostpartumVisit_upload_et_name))
						.getText().toString());
		postpartumVisit_upload
				.setFlupDate(((EditText) findViewById(R.id.PostpartumVisit_upload_et_flupDate))
						.getText().toString());
		postpartumVisit_upload
				.setTemperature(((EditText) findViewById(R.id.PostpartumVisit_upload_et_temperature))
						.getText().toString());
		postpartumVisit_upload
				.setHealthDesc(((EditText) findViewById(R.id.PostpartumVisit_upload_et_healthDesc))
						.getText().toString());
		postpartumVisit_upload
				.setPsychologicStatus(((EditText) findViewById(R.id.PostpartumVisit_upload_et_psychologicStatus))
						.getText().toString());
		postpartumVisit_upload
				.setSBP(((EditText) findViewById(R.id.PostpartumVisit_upload_et_SBP))
						.getText().toString());
		postpartumVisit_upload
				.setDBP(((EditText) findViewById(R.id.PostpartumVisit_upload_et_DBP))
						.getText().toString());
		postpartumVisit_upload
				.setBreastCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_breastCode))
						.getText().toString());
		postpartumVisit_upload
				.setBreastDesc(((EditText) findViewById(R.id.PostpartumVisit_upload_et_breastDesc))
						.getText().toString());
		postpartumVisit_upload
				.setLochiaCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_lochiaCode))
						.getText().toString());
		postpartumVisit_upload
				.setLochiaDesc(((EditText) findViewById(R.id.PostpartumVisit_upload_et_lochiaDesc))
						.getText().toString());
		postpartumVisit_upload
				.setUterusCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_uterusCode))
						.getText().toString());
		postpartumVisit_upload
				.setUterusDesc(((EditText) findViewById(R.id.PostpartumVisit_upload_et_uterusDesc))
						.getText().toString());
		postpartumVisit_upload
				.setWoundCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_woundCode))
						.getText().toString());
		postpartumVisit_upload
				.setWoundDesc(((EditText) findViewById(R.id.PostpartumVisit_upload_et_woundDesc))
						.getText().toString());
		postpartumVisit_upload
				.setOther(((EditText) findViewById(R.id.PostpartumVisit_upload_et_other))
						.getText().toString());
		postpartumVisit_upload
				.setTypeCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_typeCode))
						.getText().toString());
		postpartumVisit_upload
				.setTypeDesc(((EditText) findViewById(R.id.PostpartumVisit_upload_et_typeDesc))
						.getText().toString());
		postpartumVisit_upload
				.setGuideCodes(((EditText) findViewById(R.id.PostpartumVisit_upload_et_guideCodes))
						.getText().toString());
		postpartumVisit_upload
				.setGuideOther(((EditText) findViewById(R.id.PostpartumVisit_upload_et_guideOther))
						.getText().toString());
		postpartumVisit_upload
				.setReferralCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralCode))
						.getText().toString());
		postpartumVisit_upload
				.setReferralReason(((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralReason))
						.getText().toString());
		postpartumVisit_upload
				.setReferralOrg(((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralOrg))
						.getText().toString());
		postpartumVisit_upload
				.setReferralDepartment(((EditText) findViewById(R.id.PostpartumVisit_upload_et_referralDepartment))
						.getText().toString());
		postpartumVisit_upload
				.setVisitDoctorCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitDoctorCode))
						.getText().toString());
		postpartumVisit_upload
				.setVisitDoctorName(((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitDoctorName))
						.getText().toString());
		postpartumVisit_upload
				.setVisitOrgCode(((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitOrgCode))
						.getText().toString());
		postpartumVisit_upload
				.setVisitOrgName(((EditText) findViewById(R.id.PostpartumVisit_upload_et_visitOrgName))
						.getText().toString());
		postpartumVisit_upload
				.setNextFlupDate(((EditText) findViewById(R.id.PostpartumVisit_upload_et_nextFlupDate))
						.getText().toString());
		return postpartumVisit_upload;

	}

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.PostpartumVisit_upload_et_temperature:
			intent.setClass(YCFCHFSJL_uploadActivity.this, ETempActivity.class);
			break;
		case R.id.PostpartumVisit_upload_et_SBP:
		case R.id.PostpartumVisit_upload_et_DBP:
			intent.setClass(YCFCHFSJL_uploadActivity.this,
					MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
		default:
			break;
		}
		startActivityForResult(intent, Basesence.MEASURE_RESULT_ETMP);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (resultCode) {
		case Basesence.MEASURE_RESULT_LEFT_BLOOD_PRESURE:
			((EditText) findViewById(R.id.PostpartumVisit_upload_et_SBP))
					.setText(data.getStringExtra("ssy"));
			((EditText) findViewById(R.id.PostpartumVisit_upload_et_DBP))
					.setText(data.getStringExtra("szy"));
			break;
		case Basesence.MEASURE_RESULT_ETMP:
			((EditText) findViewById(R.id.PostpartumVisit_upload_et_temperature))
					.setText(data.getStringExtra(ETempActivity.TAG));
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
