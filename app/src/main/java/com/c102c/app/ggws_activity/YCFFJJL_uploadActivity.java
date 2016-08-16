package com.c102c.app.ggws_activity;

import java.util.List;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.health_record_activity.JKTJActivity;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.PregnantRecheck_upload;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.measurement.MeasureUrineActivity;
import com.health.util.SingleChoiceEditText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/** 孕产妇复检记录上传 */
public class YCFFJJL_uploadActivity extends BaseActivity {
	private String healfilenumber = "";
	private PregnantRecheck_upload pregnantRecheck_upload;
	PregnantSpecial_down pregnantSpecial_down;

	String specialNo = null;

	@Override
	protected void initViews() {
		setContentView(R.layout.ycffjjl_upload);
		setAllEditTextEnabled(true);
		pregnantRecheck_upload = new PregnantRecheck_upload();
		pregnantSpecial_down = Basesence.getPregnantSpecial_down();
		getAllText();
	}

	public void upload(View view) {
		AppDB.getInstance(this);
		if (checkAllEditTextIsNull()) {
			return;
		}
		Fetch_by_li.communicate("M0100040202", this, handler, getAllText());
	}

	private void settext() {
		// TODO Auto-generated method stub
		setTextByid(pregnantSpecial_down.getName(),
				R.id.PregnantRecheck_upload_et_name);
		setTextByid(pregnantSpecial_down.getPersonId(),
				R.id.PregnantRecheck_upload_et_personId);
		setTextByid(Basesence.getmac(this),
				R.id.PregnantRecheck_upload_et_machineCode);
		setTextByid(Basesence.getorgCode(),
				R.id.PregnantRecheck_upload_et_checkOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.PregnantRecheck_upload_et_checkOrgName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.PregnantRecheck_upload_et_checkDoctorCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.PregnantRecheck_upload_et_checkDoctorName);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAllEditTextEnabled(true);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			pregnantRecheck_upload = new PregnantRecheck_upload();

			if (bundle.getString("from") != null) {
				if (bundle.getString("from").equals("localHistory")) {
					// 通过类的主键来从数据库寻找类，并添加到表中
					String primaryKey = Tools.getPrimaryKey(bundle
							.getString("uuid"));
					pregnantRecheck_upload.setId(Long.parseLong(primaryKey));
					List<PregnantRecheck_upload> data = (List<PregnantRecheck_upload>) AppDB
							.query(pregnantRecheck_upload);
					if (data.size() > 0) {
						pregnantRecheck_upload = data.get(0);
					}
				}
				setAllEditTextEnabled(true);
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
		setTextByid(pregnantRecheck_upload.getPersonId(),
				R.id.PregnantRecheck_upload_et_personId);
		setTextByid(pregnantRecheck_upload.getSpecialNo(),
				R.id.PregnantRecheck_upload_et_specialNo);
		setTextByid(pregnantRecheck_upload.getMachineCode(),
				R.id.PregnantRecheck_upload_et_machineCode);
		setTextByid(pregnantRecheck_upload.getMachineNo(),
				R.id.PregnantRecheck_upload_et_machineNo);
		setTextByid(pregnantRecheck_upload.getPregnantManualNo(),
				R.id.PregnantRecheck_upload_et_pregnantManualNo);
		setTextByid(pregnantRecheck_upload.getName(),
				R.id.PregnantRecheck_upload_et_name);
		setTextByid(pregnantRecheck_upload.getAntenatalCareDate(),
				R.id.PregnantRecheck_upload_et_antenatalCareDate);
		setTextByid(pregnantRecheck_upload.getGestationalWeeks(),
				R.id.PregnantRecheck_upload_et_gestationalWeeks);
		setTextByid(pregnantRecheck_upload.getChiefComplaint(),
				R.id.PregnantRecheck_upload_et_chiefComplaint);
		setTextByid(pregnantRecheck_upload.getWeight(),
				R.id.PregnantRecheck_upload_et_weight);
		setTextByid(pregnantRecheck_upload.getFundusHeight(),
				R.id.PregnantRecheck_upload_et_fundusHeight);
		setTextByid(pregnantRecheck_upload.getAbdominalCircumference(),
				R.id.PregnantRecheck_upload_et_abdominalCircumference);
		setTextByid(pregnantRecheck_upload.getFoetalCirculationCode(),
				R.id.PregnantRecheck_upload_et_foetalCirculationCode);
		setTextByid(pregnantRecheck_upload.getFetalHeartRate(),
				R.id.PregnantRecheck_upload_et_fetalHeartRate);
		setTextByid(pregnantRecheck_upload.getSBP(),
				R.id.PregnantRecheck_upload_et_SBP);
		setTextByid(pregnantRecheck_upload.getDBP(),
				R.id.PregnantRecheck_upload_et_DBP);
		setTextByid(pregnantRecheck_upload.getHemoglobin(),
				R.id.PregnantRecheck_upload_et_hemoglobin);
		setTextByid(pregnantRecheck_upload.getLeucocyte(),
				R.id.PregnantRecheck_upload_et_leucocyte);
		setTextByid(pregnantRecheck_upload.getCheckOther(),
				R.id.PregnantRecheck_upload_et_checkOther);
		setTextByid(pregnantRecheck_upload.getTypeCode(),
				R.id.PregnantRecheck_upload_et_typeCode);
		setTextByid(pregnantRecheck_upload.getTypeDesc(),
				R.id.PregnantRecheck_upload_et_typeDesc);
		setTextByid(pregnantRecheck_upload.getGuideCodes(),
				R.id.PregnantRecheck_upload_et_guideCodes);
		setTextByid(pregnantRecheck_upload.getGuideOther(),
				R.id.PregnantRecheck_upload_et_guideOther);
		setTextByid(pregnantRecheck_upload.getReferralCode(),
				R.id.PregnantRecheck_upload_et_referralCode);
		setTextByid(pregnantRecheck_upload.getReferralReason(),
				R.id.PregnantRecheck_upload_et_referralReason);
		setTextByid(pregnantRecheck_upload.getReferralOrg(),
				R.id.PregnantRecheck_upload_et_referralOrg);
		setTextByid(pregnantRecheck_upload.getReferralDepartment(),
				R.id.PregnantRecheck_upload_et_referralDepartment);
		setTextByid(pregnantRecheck_upload.getCheckDoctorCode(),
				R.id.PregnantRecheck_upload_et_checkDoctorCode);
		setTextByid(pregnantRecheck_upload.getCheckDoctorName(),
				R.id.PregnantRecheck_upload_et_checkDoctorName);
		setTextByid(pregnantRecheck_upload.getCheckOrgCode(),
				R.id.PregnantRecheck_upload_et_checkOrgCode);
		setTextByid(pregnantRecheck_upload.getCheckOrgName(),
				R.id.PregnantRecheck_upload_et_checkOrgName);
		setTextByid(pregnantRecheck_upload.getNextFlupDate(),
				R.id.PregnantRecheck_upload_et_nextFlupDate);

	}

	/** 检查EditText不为空。 */
	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_personId,
				"居民个人ID")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_machineCode,
				"健康一体机编码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_machineNo,
				"一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_name, "孕妇姓名")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantRecheck_upload_et_antenatalCareDate, "产检日期")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantRecheck_upload_et_gestationalWeeks, "孕周")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_fundusHeight,
				"宫底高度")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantRecheck_upload_et_abdominalCircumference, "腹围")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_SBP, "收缩压")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_DBP, "舒张压")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantRecheck_upload_et_checkDoctorCode, "产检医生代码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantRecheck_upload_et_checkOrgCode,
				"产检机构代码")) {
			return true;
		}
		return false;
	}

	private void setAllEditTextEnabled(boolean enabled) {
		if (enabled) {
			setChoiceEditText(
					R.id.PregnantRecheck_upload_et_foetalCirculationCode,
					R.array.PregnantRecheck_upload_et_foetalCirculationCode);
			setChoiceEditText(R.id.PregnantRecheck_upload_et_leucocyte,
					R.array.PregnantRecheck_upload_et_leucocyte);
			setChoiceEditText(R.id.PregnantRecheck_upload_et_typeCode,
					R.array.PregnantRecheck_upload_et_typeCode);
			setChoiceEditText(R.id.PregnantRecheck_upload_et_guideCodes,
					R.array.PregnantRecheck_upload_et_guideCodes);
			setChoiceEditText(R.id.PregnantRecheck_upload_et_referralCode,
					R.array.PregnantRecheck_upload_et_referralCode);
		}
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_machineCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_machineNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_pregnantManualNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_name))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_antenatalCareDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_gestationalWeeks))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_chiefComplaint))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_weight))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_fundusHeight))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_abdominalCircumference))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_foetalCirculationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_fetalHeartRate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_hemoglobin))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_leucocyte))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_typeCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_typeDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_guideCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_guideOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralReason))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralOrg))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralDepartment))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkOrgName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantRecheck_upload_et_nextFlupDate))
				.setEnabled(enabled);

	}

	@Override
	protected PregnantRecheck_upload getAllText() {
		pregnantRecheck_upload
				.setPersonId(((EditText) findViewById(R.id.PregnantRecheck_upload_et_personId))
						.getText().toString());
		pregnantRecheck_upload
				.setSpecialNo(((EditText) findViewById(R.id.PregnantRecheck_upload_et_specialNo))
						.getText().toString());
		pregnantRecheck_upload
				.setMachineCode(((EditText) findViewById(R.id.PregnantRecheck_upload_et_machineCode))
						.getText().toString());
		pregnantRecheck_upload
				.setMachineNo(((EditText) findViewById(R.id.PregnantRecheck_upload_et_machineNo))
						.getText().toString());
		pregnantRecheck_upload
				.setPregnantManualNo(((EditText) findViewById(R.id.PregnantRecheck_upload_et_pregnantManualNo))
						.getText().toString());
		pregnantRecheck_upload
				.setName(((EditText) findViewById(R.id.PregnantRecheck_upload_et_name))
						.getText().toString());
		pregnantRecheck_upload
				.setAntenatalCareDate(((EditText) findViewById(R.id.PregnantRecheck_upload_et_antenatalCareDate))
						.getText().toString());
		pregnantRecheck_upload
				.setGestationalWeeks(((EditText) findViewById(R.id.PregnantRecheck_upload_et_gestationalWeeks))
						.getText().toString());
		pregnantRecheck_upload
				.setChiefComplaint(((EditText) findViewById(R.id.PregnantRecheck_upload_et_chiefComplaint))
						.getText().toString());
		pregnantRecheck_upload
				.setWeight(((EditText) findViewById(R.id.PregnantRecheck_upload_et_weight))
						.getText().toString());
		pregnantRecheck_upload
				.setFundusHeight(((EditText) findViewById(R.id.PregnantRecheck_upload_et_fundusHeight))
						.getText().toString());
		pregnantRecheck_upload
				.setAbdominalCircumference(((EditText) findViewById(R.id.PregnantRecheck_upload_et_abdominalCircumference))
						.getText().toString());
		pregnantRecheck_upload
				.setFoetalCirculationCode(((EditText) findViewById(R.id.PregnantRecheck_upload_et_foetalCirculationCode))
						.getText().toString());
		pregnantRecheck_upload
				.setFetalHeartRate(((EditText) findViewById(R.id.PregnantRecheck_upload_et_fetalHeartRate))
						.getText().toString());
		pregnantRecheck_upload
				.setSBP(((EditText) findViewById(R.id.PregnantRecheck_upload_et_SBP))
						.getText().toString());
		pregnantRecheck_upload
				.setDBP(((EditText) findViewById(R.id.PregnantRecheck_upload_et_DBP))
						.getText().toString());
		pregnantRecheck_upload
				.setHemoglobin(((EditText) findViewById(R.id.PregnantRecheck_upload_et_hemoglobin))
						.getText().toString());
		pregnantRecheck_upload
				.setLeucocyte(((EditText) findViewById(R.id.PregnantRecheck_upload_et_leucocyte))
						.getText().toString());
		pregnantRecheck_upload
				.setCheckOther(((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkOther))
						.getText().toString());
		pregnantRecheck_upload
				.setTypeCode(((EditText) findViewById(R.id.PregnantRecheck_upload_et_typeCode))
						.getText().toString());
		pregnantRecheck_upload
				.setTypeDesc(((EditText) findViewById(R.id.PregnantRecheck_upload_et_typeDesc))
						.getText().toString());
		pregnantRecheck_upload
				.setGuideCodes(((EditText) findViewById(R.id.PregnantRecheck_upload_et_guideCodes))
						.getText().toString());
		pregnantRecheck_upload
				.setGuideOther(((EditText) findViewById(R.id.PregnantRecheck_upload_et_guideOther))
						.getText().toString());
		pregnantRecheck_upload
				.setReferralCode(((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralCode))
						.getText().toString());
		pregnantRecheck_upload
				.setReferralReason(((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralReason))
						.getText().toString());
		pregnantRecheck_upload
				.setReferralOrg(((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralOrg))
						.getText().toString());
		pregnantRecheck_upload
				.setReferralDepartment(((EditText) findViewById(R.id.PregnantRecheck_upload_et_referralDepartment))
						.getText().toString());
		pregnantRecheck_upload
				.setCheckDoctorCode(((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkDoctorCode))
						.getText().toString());
		pregnantRecheck_upload
				.setCheckDoctorName(((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkDoctorName))
						.getText().toString());
		pregnantRecheck_upload
				.setCheckOrgCode(((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkOrgCode))
						.getText().toString());
		pregnantRecheck_upload
				.setCheckOrgName(((EditText) findViewById(R.id.PregnantRecheck_upload_et_checkOrgName))
						.getText().toString());
		pregnantRecheck_upload
				.setNextFlupDate(((EditText) findViewById(R.id.PregnantRecheck_upload_et_nextFlupDate))
						.getText().toString());
		return pregnantRecheck_upload;
	}

	public void onClick(View v) {
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
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
						Toast.makeText(YCFFJJL_uploadActivity.this,
								"没找到这个用户的健康档案", 0).show();
					}
					// Toast.makeText(XSEJTFSJL_uploadActivity.this, "成功",
					// 0).show();
					break;
				case Basesence.M0100040202:
					Toast.makeText(YCFFJJL_uploadActivity.this, "成功", 0).show();
					break;
				case 0:
					Toast.makeText(YCFFJJL_uploadActivity.this,
							"该孕妇已经做过检测，请勿继续操作！", 0).show();
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				Toast.makeText(YCFFJJL_uploadActivity.this,
						a, 0).show();
			}
			super.handleMessage(msg);
		}

	};

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.PregnantRecheck_upload_et_DBP:
		case R.id.PregnantRecheck_upload_et_SBP:
			intent.setClass(YCFFJJL_uploadActivity.this,
					MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			break;
		case R.id.PregnantRecheck_upload_et_leucocyte_label:
			intent.setClass(YCFFJJL_uploadActivity.this,
					MeasureUrineActivity.class);
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
			((EditText) findViewById(R.id.PregnantRecheck_upload_et_SBP))
					.setText(data.getStringExtra("ssy"));
			((EditText) findViewById(R.id.PregnantRecheck_upload_et_DBP))
					.setText(data.getStringExtra("szy"));
			break;
		case Basesence.MEASURE_RESULT_URINE:
			((SingleChoiceEditText) findViewById(R.id.PregnantRecheck_upload_et_leucocyte))
					.setText(ToUrineData(data.getStringExtra("ndb")));
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private String ToUrineData(String orgData) {
		if (orgData.equals("-")) {
			return "0";
		} else if (orgData.equals("+-")) {
			return "1";
		} else if (orgData.equals("+1")) {
			return "2";
		} else if (orgData.equals("+2")) {
			return "3";
		} else if (orgData.equals("+3")) {
			return "4";
		} else if (orgData.equals("+4")) {
			return "5";
		} else {
			Toast.makeText(YCFFJJL_uploadActivity.this, "尿液分析数据错误",
					Toast.LENGTH_LONG).show();
			return "6";
		}
	}
}
