package com.c102c.app.ggws_activity;

import java.util.List;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.Postpartum42Visit_upload;
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

/** 孕产妇产后 42 天访视 记录 */
public class YCFCH42FSJL_uploadActivity extends BaseActivity {

	private Postpartum42Visit_upload postpartum42Visit_upload;
	private String healfilenumber = "";
	PregnantSpecial_down pregnantSpecial_down;
	String specialNo = null;

	@Override
	protected void initViews() {
		setContentView(R.layout.ycfch42fsjl_upload);
		setAllEditTextEnable(true);
		postpartum42Visit_upload = new Postpartum42Visit_upload();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		;
		setAllEditTextEnable(true);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {

			if (bundle.getString("from") != null) {
				if (bundle.getString("from").equals("localHistory")) {
					//system.out.println("llllllllllllllllllllll");
					// 通过类的主键来从数据库寻找类，并添加到表中
					String primaryKey = Tools.getPrimaryKey(bundle
							.getString("uuid"));
					postpartum42Visit_upload.setId(Long.parseLong(primaryKey));
					List<Postpartum42Visit_upload> data = (List<Postpartum42Visit_upload>) AppDB
							.query(postpartum42Visit_upload);
					if (data.size() > 0) {
						postpartum42Visit_upload = data.get(0);
					}
				}
				setAllEditTextEnable(true);
				//system.out.println("postpartum42Visit_upload"
//						+ postpartum42Visit_upload.getId()
//						+ "postpartum42Visit_upload"
//						+ postpartum42Visit_upload.getVisitDoctorCode());
				// 修改位置;
				setAllText();
			}
		} else {
			//system.out.println("14111111111111111111111");
			pregnantSpecial_down = Basesence.getPregnantSpecial_down();
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
		setTextByid(postpartum42Visit_upload.getPersonId(),
				R.id.Postpartum42Visit_upload_et_personId);
		setTextByid(postpartum42Visit_upload.getSpecialNo(),
				R.id.Postpartum42Visit_upload_et_specialNo);
		setTextByid(postpartum42Visit_upload.getMachineCode(),
				R.id.Postpartum42Visit_upload_et_machineCode);
		setTextByid(postpartum42Visit_upload.getMachineNo(),
				R.id.Postpartum42Visit_upload_et_machineNo);
		setTextByid(postpartum42Visit_upload.getPregnantManualNo(),
				R.id.Postpartum42Visit_upload_et_pregnantManualNo);
		setTextByid(postpartum42Visit_upload.getName(),
				R.id.Postpartum42Visit_upload_et_name);
		setTextByid(postpartum42Visit_upload.getFlupDate(),
				R.id.Postpartum42Visit_upload_et_flupDate);
		setTextByid(postpartum42Visit_upload.getHealthDesc(),
				R.id.Postpartum42Visit_upload_et_healthDesc);
		setTextByid(postpartum42Visit_upload.getPsychologicStatus(),
				R.id.Postpartum42Visit_upload_et_psychologicStatus);
		setTextByid(postpartum42Visit_upload.getSBP(),
				R.id.Postpartum42Visit_upload_et_SBP);
		setTextByid(postpartum42Visit_upload.getDBP(),
				R.id.Postpartum42Visit_upload_et_DBP);
		setTextByid(postpartum42Visit_upload.getBreastCode(),
				R.id.Postpartum42Visit_upload_et_breastCode);
		setTextByid(postpartum42Visit_upload.getBreastDesc(),
				R.id.Postpartum42Visit_upload_et_breastDesc);
		setTextByid(postpartum42Visit_upload.getLochiaCode(),
				R.id.Postpartum42Visit_upload_et_lochiaCode);
		setTextByid(postpartum42Visit_upload.getLochiaDesc(),
				R.id.Postpartum42Visit_upload_et_lochiaDesc);
		setTextByid(postpartum42Visit_upload.getUterusCode(),
				R.id.Postpartum42Visit_upload_et_uterusCode);
		setTextByid(postpartum42Visit_upload.getUterusDesc(),
				R.id.Postpartum42Visit_upload_et_uterusDesc);
		setTextByid(postpartum42Visit_upload.getWoundCode(),
				R.id.Postpartum42Visit_upload_et_woundCode);
		setTextByid(postpartum42Visit_upload.getWoundDesc(),
				R.id.Postpartum42Visit_upload_et_woundDesc);
		setTextByid(postpartum42Visit_upload.getOther(),
				R.id.Postpartum42Visit_upload_et_other);
		setTextByid(postpartum42Visit_upload.getTypeCode(),
				R.id.Postpartum42Visit_upload_et_typeCode);
		setTextByid(postpartum42Visit_upload.getTypeDesc(),
				R.id.Postpartum42Visit_upload_et_typeDesc);
		setTextByid(postpartum42Visit_upload.getGuideCodes(),
				R.id.Postpartum42Visit_upload_et_guideCodes);
		setTextByid(postpartum42Visit_upload.getGuideOther(),
				R.id.Postpartum42Visit_upload_et_guideOther);
		setTextByid(postpartum42Visit_upload.getProcessCode(),
				R.id.Postpartum42Visit_upload_et_processCode);
		setTextByid(postpartum42Visit_upload.getReferralReason(),
				R.id.Postpartum42Visit_upload_et_referralReason);
		setTextByid(postpartum42Visit_upload.getReferralOrg(),
				R.id.Postpartum42Visit_upload_et_referralOrg);
		setTextByid(postpartum42Visit_upload.getReferralDepartment(),
				R.id.Postpartum42Visit_upload_et_referralDepartment);
		setTextByid(postpartum42Visit_upload.getVisitDoctorCode(),
				R.id.Postpartum42Visit_upload_et_visitDoctorCode);
		setTextByid(postpartum42Visit_upload.getVisitDoctorName(),
				R.id.Postpartum42Visit_upload_et_visitDoctorName);
		setTextByid(postpartum42Visit_upload.getVisitOrgCode(),
				R.id.Postpartum42Visit_upload_et_visitOrgCode);
		setTextByid(postpartum42Visit_upload.getVisitOrgName(),
				R.id.Postpartum42Visit_upload_et_visitOrgName);

	};

	private void settext() {
		// TODO Auto-generated method stub
		setTextByid(pregnantSpecial_down.getName(),
				R.id.Postpartum42Visit_upload_et_name);
		setTextByid(pregnantSpecial_down.getPersonId(),
				R.id.Postpartum42Visit_upload_et_personId);

		setTextByid(Basesence.getmac(this),
				R.id.Postpartum42Visit_upload_et_machineCode);

		setTextByid(Basesence.getorgCode(),
				R.id.Postpartum42Visit_upload_et_visitOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.Postpartum42Visit_upload_et_visitOrgName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.Postpartum42Visit_upload_et_visitDoctorCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.Postpartum42Visit_upload_et_visitDoctorName);
	}

	public void upload(View view) {

		switch (view.getId()) {

		case R.id.ycfch42fsjl_upload_btn_upload:

			AppDB.getInstance(this);
			if (checkAllEditTextIsNull()) {
				return;
			}

			Fetch_by_li.communicate("M0100040204", this, handler, getAllText());

			break;

		default:
			break;
		}
	}

	private void setAllEditTextEnable(boolean enabled) {
		if (enabled) {
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_breastCode,
					R.array.Postpartum42Visit_upload_et_breastCode);
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_lochiaCode,
					R.array.Postpartum42Visit_upload_et_lochiaCode);
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_woundCode,
					R.array.Postpartum42Visit_upload_et_woundCode);
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_typeCode,
					R.array.Postpartum42Visit_upload_et_typeCode);
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_guideCodes,
					R.array.Postpartum42Visit_upload_et_guideCodes);
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_uterusCode,
					R.array.Postpartum42Visit_upload_et_uterusCode);
			setChoiceEditText(R.id.Postpartum42Visit_upload_et_processCode,
					R.array.Postpartum42Visit_upload_et_processCode);
		}
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_machineCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_machineNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_pregnantManualNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_name))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_flupDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_healthDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_psychologicStatus))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_breastCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_breastDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_lochiaCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_lochiaDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_uterusCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_uterusDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_woundCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_woundDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_other))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_typeCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_typeDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_guideCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_guideOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_processCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_referralReason))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_referralOrg))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_referralDepartment))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitOrgName))
				.setEnabled(enabled);

	}

	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.Postpartum42Visit_upload_et_personId,
				"居民个人ID")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.Postpartum42Visit_upload_et_machineCode, "健康一体机编码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.Postpartum42Visit_upload_et_machineNo,
				"一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.Postpartum42Visit_upload_et_name,
				"孕妇姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.Postpartum42Visit_upload_et_flupDate,
				"随访日期")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.Postpartum42Visit_upload_et_visitDoctorCode, "访视医生代码")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.Postpartum42Visit_upload_et_visitOrgCode, "访视机构代码")) {
			return true;
		}
		return false;
	}

	@Override
	protected Postpartum42Visit_upload getAllText() {
		postpartum42Visit_upload
				.setPersonId(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_personId))
						.getText().toString());
		postpartum42Visit_upload
				.setSpecialNo(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_specialNo))
						.getText().toString());
		postpartum42Visit_upload
				.setMachineCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_machineCode))
						.getText().toString());
		postpartum42Visit_upload
				.setMachineNo(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_machineNo))
						.getText().toString());
		postpartum42Visit_upload
				.setPregnantManualNo(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_pregnantManualNo))
						.getText().toString());
		postpartum42Visit_upload
				.setName(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_name))
						.getText().toString());
		postpartum42Visit_upload
				.setFlupDate(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_flupDate))
						.getText().toString());
		postpartum42Visit_upload
				.setHealthDesc(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_healthDesc))
						.getText().toString());
		postpartum42Visit_upload
				.setPsychologicStatus(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_psychologicStatus))
						.getText().toString());
		postpartum42Visit_upload
				.setSBP(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_SBP))
						.getText().toString());
		postpartum42Visit_upload
				.setDBP(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_DBP))
						.getText().toString());
		postpartum42Visit_upload
				.setBreastCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_breastCode))
						.getText().toString());
		postpartum42Visit_upload
				.setBreastDesc(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_breastDesc))
						.getText().toString());
		postpartum42Visit_upload
				.setLochiaCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_lochiaCode))
						.getText().toString());
		postpartum42Visit_upload
				.setLochiaDesc(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_lochiaDesc))
						.getText().toString());
		postpartum42Visit_upload
				.setUterusCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_uterusCode))
						.getText().toString());
		postpartum42Visit_upload
				.setUterusDesc(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_uterusDesc))
						.getText().toString());
		postpartum42Visit_upload
				.setWoundCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_woundCode))
						.getText().toString());
		postpartum42Visit_upload
				.setWoundDesc(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_woundDesc))
						.getText().toString());
		postpartum42Visit_upload
				.setOther(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_other))
						.getText().toString());
		postpartum42Visit_upload
				.setTypeCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_typeCode))
						.getText().toString());
		postpartum42Visit_upload
				.setTypeDesc(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_typeDesc))
						.getText().toString());
		postpartum42Visit_upload
				.setGuideCodes(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_guideCodes))
						.getText().toString());
		postpartum42Visit_upload
				.setGuideOther(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_guideOther))
						.getText().toString());
		postpartum42Visit_upload
				.setProcessCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_processCode))
						.getText().toString());
		postpartum42Visit_upload
				.setReferralReason(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_referralReason))
						.getText().toString());
		postpartum42Visit_upload
				.setReferralOrg(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_referralOrg))
						.getText().toString());
		postpartum42Visit_upload
				.setReferralDepartment(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_referralDepartment))
						.getText().toString());
		postpartum42Visit_upload
				.setVisitDoctorCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitDoctorCode))
						.getText().toString());
		postpartum42Visit_upload
				.setVisitDoctorName(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitDoctorName))
						.getText().toString());
		postpartum42Visit_upload
				.setVisitOrgCode(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitOrgCode))
						.getText().toString());
		postpartum42Visit_upload
				.setVisitOrgName(((EditText) findViewById(R.id.Postpartum42Visit_upload_et_visitOrgName))
						.getText().toString());
		return postpartum42Visit_upload;
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			String a = (String) msg.obj;
			if (msg.what == Basesence.commusucc) {

				switch (msg.arg1) {
				case Basesence.M0100020101:
					if (a.indexOf("xml") != -1) {
						List<HealthRecord_down> heal = Util
								.getHealthRecord_down(a);
						healfilenumber = heal.get(0).getHealthFileNumber();
						Basesence.setHealFileNumber(healfilenumber);
					} else {
						Toast.makeText(YCFCH42FSJL_uploadActivity.this,
								"没找到这个用户的健康档案", 0).show();
					}
					// Toast.makeText(XSEJTFSJL_uploadActivity.this, "成功",
					// 0).show();
					break;
				case Basesence.M0100040204:
					Toast.makeText(YCFCH42FSJL_uploadActivity.this, "成功", 0)
							.show();
					break;
				case 0:
					Toast.makeText(YCFCH42FSJL_uploadActivity.this,
							"该孕妇已经做过检测，请勿继续操作！", 0).show();
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				Toast.makeText(YCFCH42FSJL_uploadActivity.this,
						a, 0).show();
			}

			super.handleMessage(msg);
		}

	};

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.Postpartum42Visit_upload_et_temperature:
			intent.setClass(YCFCH42FSJL_uploadActivity.this,
					ETempActivity.class);
			break;
		case R.id.Postpartum42Visit_upload_et_SBP:
		case R.id.Postpartum42Visit_upload_et_DBP:
			intent.setClass(YCFCH42FSJL_uploadActivity.this,
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
			((EditText) findViewById(R.id.Postpartum42Visit_upload_et_SBP))
					.setText(data.getStringExtra("ssy"));
			((EditText) findViewById(R.id.Postpartum42Visit_upload_et_DBP))
					.setText(data.getStringExtra("szy"));
			break;
		case Basesence.MEASURE_RESULT_ETMP:
			((EditText) findViewById(R.id.Postpartum42Visit_upload_et_temperature))
					.setText(data.getStringExtra(ETempActivity.TAG));
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
