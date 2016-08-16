package com.c102c.app.ggws_activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.ChildSpecial_down;
/*import com.c102c.app.commu.Fetch_by_li;
 import com.c102c.app.measurement_activity.ETempActivity;
 import com.c102c.app.measurement_activity.MeasureOnPC300Activity;*/
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.NeonatalVisit_upload;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ETempActivity;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.util.DateEditText;

/** 新生儿家庭访视记录 */
public class XSEJTFSJL_uploadActivity extends BaseActivity implements
		OnClickListener {
	private Button baby_title_btn;
	private NeonatalVisit_upload neonatalVisit_upload;

	private String healfilenumber = "";
	String specialNo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initViews() {
		setContentView(R.layout.xsejtfsjl_upload);
		baby_title_btn = (Button) findViewById(R.id.baby_title_btn);
		baby_title_btn.setOnClickListener(this);
		neonatalVisit_upload = new NeonatalVisit_upload();
		;

		neonatalVisit_upload.setName(getIntent().getStringExtra("name"));
		neonatalVisit_upload
				.setPersonId(getIntent().getStringExtra("personId"));
		specialNo = getIntent().getStringExtra("specialNo");
		neonatalVisit_upload.setSpecialNo(getIntent().getStringExtra(
				"specialNo"));
		neonatalVisit_upload.setMachineNo(Basesence.getuuid());
		neonatalVisit_upload.setMachineCode(Basesence
				.getmac(XSEJTFSJL_uploadActivity.this));

		SimpleDateFormat tmpDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		((DateEditText) findViewById(R.id.NeonatalVisit_upload_et_flupDate))
				.setText(tmpDateFormat.format(new Date()));
		setAllEditTextEnable(true);
		Bundle bundle = getIntent().getExtras();

		if (bundle.get("from") != null
				&& bundle.getString("from").equals("localHistory")) {
			// 通过类的主键来从数据库寻找类，并添加到表中
			neonatalVisit_upload = new NeonatalVisit_upload();
			String primaryKey = Tools.getPrimaryKey(bundle.getString("uuid"));
			neonatalVisit_upload.setId(Long.parseLong(primaryKey));
			List<NeonatalVisit_upload> data = (List<NeonatalVisit_upload>) AppDB
					.query(neonatalVisit_upload);
			if (data.size() > 0) {
				neonatalVisit_upload = data.get(0);
			}
			setAllEditTextEnable(true);
			// 修改位置
			setUpAllText();
		} else {

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
							+ neonatalVisit_upload.getName() + "</name>"
							+ "</healthRecord></requestParams>";
					Fetch.communicate("M0100020101", this, handler, a);
				}
			}
			setAllText();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.baby_title_btn:
			if (!checkAllEditTextIsNull()) {
			}
			Fetch_by_li.communicate("M0100050201", this, handler, getAllText());
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
			System.out.println("返回书记"+a);
			if (msg.what == Basesence.commusucc) {
				switch (msg.arg1) {
				case Basesence.M0100020101:
					if (a.indexOf("xml") != -1) {
						List<HealthRecord_down> heal = Util
								.getHealthRecord_down(a);
						healfilenumber = heal.get(0).getHealthFileNumber();
						Basesence.setHealFileNumber(healfilenumber);
					} else {
						Toast.makeText(XSEJTFSJL_uploadActivity.this,
								"没找到这个用户的健康档案", 0).show();
					}
					// Toast.makeText(XSEJTFSJL_uploadActivity.this, "成功",
					// 0).show();
					break;
				case Basesence.M0100050201:
					Toast.makeText(XSEJTFSJL_uploadActivity.this, "成功", 0)
							.show();
					break;
				case 0:
					Toast.makeText(XSEJTFSJL_uploadActivity.this,
							"该新生儿已经检测，请勿继续操作！", 0).show();
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				if (msg.obj != null)
					Toast.makeText(XSEJTFSJL_uploadActivity.this,
							"接口调用失败" + msg.obj, 0).show();
				else
					Toast.makeText(XSEJTFSJL_uploadActivity.this, "接口调用失败", 0)
							.show();
			}
			if (healfilenumber!=null&&healfilenumber.length() <= 0) {
				if(a.indexOf("healthRecord")!=-1){
					List<HealthRecord_down> heal = Util
						.getHealthRecord_down((String) msg.obj);
				healfilenumber = heal.get(0).getHealthFileNumber();
				Basesence.setHealFileNumber(healfilenumber);
				}
				
				// String request = Util.setNeonatalVisit_Upload(getAllText());
				// Fetch_by_li.communicate("M0100050201",
				// XSEJTFSJL_uploadActivity.this, handler, getAllText());
			}
			super.handleMessage(msg);
		}
	};

	private void setUpAllText() {
		setTextByid(neonatalVisit_upload.getPersonId(),
				R.id.NeonatalVisit_upload_et_personId);
		setTextByid(neonatalVisit_upload.getSpecialNo(),
				R.id.NeonatalVisit_upload_et_specialNo);
		setTextByid(neonatalVisit_upload.getMachineCode(),
				R.id.NeonatalVisit_upload_et_machineCode);
		setTextByid(neonatalVisit_upload.getMachineNo(),
				R.id.NeonatalVisit_upload_et_machineNo);
		setTextByid(neonatalVisit_upload.getName(),
				R.id.NeonatalVisit_upload_et_name);
		setTextByid(neonatalVisit_upload.getGenderCode(),
				R.id.NeonatalVisit_upload_et_genderCode);
		setTextByid(neonatalVisit_upload.getBirthday(),
				R.id.NeonatalVisit_upload_et_birthday);
		setTextByid(neonatalVisit_upload.getIdCard(),
				R.id.NeonatalVisit_upload_et_idCard);
		setTextByid(neonatalVisit_upload.getHomeAddress(),
				R.id.NeonatalVisit_upload_et_homeAddress);
		setTextByid(neonatalVisit_upload.getFatherName(),
				R.id.NeonatalVisit_upload_et_fatherName);
		setTextByid(neonatalVisit_upload.getMatherName(),
				R.id.NeonatalVisit_upload_et_matherName);
		setTextByid(neonatalVisit_upload.getFatherOccupation(),
				R.id.NeonatalVisit_upload_et_fatherOccupation);
		setTextByid(neonatalVisit_upload.getMatherOccupation(),
				R.id.NeonatalVisit_upload_et_matherOccupation);
		setTextByid(neonatalVisit_upload.getFatherContac(),
				R.id.NeonatalVisit_upload_et_fatherContac);
		setTextByid(neonatalVisit_upload.getMatherContac(),
				R.id.NeonatalVisit_upload_et_matherContac);
		setTextByid(neonatalVisit_upload.getFatherBirthday(),
				R.id.NeonatalVisit_upload_et_fatherBirthday);
		setTextByid(neonatalVisit_upload.getMatherBirthday(),
				R.id.NeonatalVisit_upload_et_matherBirthday);
		setTextByid(neonatalVisit_upload.getWeekOfBirth(),
				R.id.NeonatalVisit_upload_et_weekOfBirth);
		setTextByid(neonatalVisit_upload.getPregnancyBeIllCode(),
				R.id.NeonatalVisit_upload_et_pregnancyBeIllCode);
		setTextByid(neonatalVisit_upload.getPregnancyBeIllOther(),
				R.id.NeonatalVisit_upload_et_pregnancyBeIllOther);
		setTextByid(neonatalVisit_upload.getMidwiferyOrgName(),
				R.id.NeonatalVisit_upload_et_midwiferyOrgName);
		setTextByid(neonatalVisit_upload.getBirthConditionCodes(),
				R.id.NeonatalVisit_upload_et_birthConditionCodes);
		setTextByid(neonatalVisit_upload.getNeonatalAsphyxiaCode(),
				R.id.NeonatalVisit_upload_et_neonatalAsphyxiaCode);
		setTextByid(neonatalVisit_upload.getApgar1(),
				R.id.NeonatalVisit_upload_et_apgar1);
		setTextByid(neonatalVisit_upload.getApgar5(),
				R.id.NeonatalVisit_upload_et_apgar5);
		setTextByid(neonatalVisit_upload.getApgar10(),
				R.id.NeonatalVisit_upload_et_apgar10);
		setTextByid(neonatalVisit_upload.getDeformityCode(),
				R.id.NeonatalVisit_upload_et_deformityCode);
		setTextByid(neonatalVisit_upload.getDeformityOther(),
				R.id.NeonatalVisit_upload_et_deformityOther);
		setTextByid(neonatalVisit_upload.getUNHSCode(),
				R.id.NeonatalVisit_upload_et_UNHSCode);
		setTextByid(neonatalVisit_upload.getNeonatalScreeningCode(),
				R.id.NeonatalVisit_upload_et_neonatalScreeningCode);
		setTextByid(neonatalVisit_upload.getNeonatalScreeningOther(),
				R.id.NeonatalVisit_upload_et_neonatalScreeningOther);
		setTextByid(neonatalVisit_upload.getBirthWeight(),
				R.id.NeonatalVisit_upload_et_birthWeight);
		setTextByid(neonatalVisit_upload.getCurrentWeight(),
				R.id.NeonatalVisit_upload_et_currentWeight);
		setTextByid(neonatalVisit_upload.getBirthHeight(),
				R.id.NeonatalVisit_upload_et_birthHeight);
		setTextByid(neonatalVisit_upload.getFeedingPatternCode(),
				R.id.NeonatalVisit_upload_et_feedingPatternCode);
		setTextByid(neonatalVisit_upload.getFeedingAmount(),
				R.id.NeonatalVisit_upload_et_feedingAmount);
		setTextByid(neonatalVisit_upload.getFeedingTimes(),
				R.id.NeonatalVisit_upload_et_feedingTimes);
		setTextByid(neonatalVisit_upload.getVomitCode(),
				R.id.NeonatalVisit_upload_et_vomitCode);
		setTextByid(neonatalVisit_upload.getFecalCode(),
				R.id.NeonatalVisit_upload_et_fecalCode);
		setTextByid(neonatalVisit_upload.getFecalTimes(),
				R.id.NeonatalVisit_upload_et_fecalTimes);
		setTextByid(neonatalVisit_upload.getTemperature(),
				R.id.NeonatalVisit_upload_et_temperature);
		setTextByid(neonatalVisit_upload.getPulseRate(),
				R.id.NeonatalVisit_upload_et_pulseRate);
		setTextByid(neonatalVisit_upload.getRespiratoryRate(),
				R.id.NeonatalVisit_upload_et_respiratoryRate);
		setTextByid(neonatalVisit_upload.getComplexionCode(),
				R.id.NeonatalVisit_upload_et_complexionCode);
		setTextByid(neonatalVisit_upload.getComplexionOther(),
				R.id.NeonatalVisit_upload_et_complexionOther);
		setTextByid(neonatalVisit_upload.getJaundiceSiteCode(),
				R.id.NeonatalVisit_upload_et_jaundiceSiteCode);
		setTextByid(neonatalVisit_upload.getBregma1(),
				R.id.NeonatalVisit_upload_et_bregma1);
		setTextByid(neonatalVisit_upload.getBregma2(),
				R.id.NeonatalVisit_upload_et_bregma2);
		setTextByid(neonatalVisit_upload.getBregmaCode(),
				R.id.NeonatalVisit_upload_et_bregmaCode);
		setTextByid(neonatalVisit_upload.getBregmaOther(),
				R.id.NeonatalVisit_upload_et_bregmaOther);
		setTextByid(neonatalVisit_upload.getEyeAppearanceCode(),
				R.id.NeonatalVisit_upload_et_eyeAppearanceCode);
		setTextByid(neonatalVisit_upload.getEyeAppearanceDesc(),
				R.id.NeonatalVisit_upload_et_eyeAppearanceDesc);
		setTextByid(neonatalVisit_upload.getLimbsActivityCode(),
				R.id.NeonatalVisit_upload_et_limbsActivityCode);
		setTextByid(neonatalVisit_upload.getLimbsActivityDesc(),
				R.id.NeonatalVisit_upload_et_limbsActivityDesc);
		setTextByid(neonatalVisit_upload.getEarAppearanceCode(),
				R.id.NeonatalVisit_upload_et_earAppearanceCode);
		setTextByid(neonatalVisit_upload.getEarAppearanceDesc(),
				R.id.NeonatalVisit_upload_et_earAppearanceDesc);
		setTextByid(neonatalVisit_upload.getNeckMassCode(),
				R.id.NeonatalVisit_upload_et_neckMassCode);
		setTextByid(neonatalVisit_upload.getNeckMassDesc(),
				R.id.NeonatalVisit_upload_et_neckMassDesc);
		setTextByid(neonatalVisit_upload.getNoseCode(),
				R.id.NeonatalVisit_upload_et_noseCode);
		setTextByid(neonatalVisit_upload.getNoseDesc(),
				R.id.NeonatalVisit_upload_et_noseDesc);
		setTextByid(neonatalVisit_upload.getSkinCode(),
				R.id.NeonatalVisit_upload_et_skinCode);
		setTextByid(neonatalVisit_upload.getSkinOther(),
				R.id.NeonatalVisit_upload_et_skinOther);
		setTextByid(neonatalVisit_upload.getOralCode(),
				R.id.NeonatalVisit_upload_et_oralCode);
		setTextByid(neonatalVisit_upload.getOralDesc(),
				R.id.NeonatalVisit_upload_et_oralDesc);
		setTextByid(neonatalVisit_upload.getAnusCode(),
				R.id.NeonatalVisit_upload_et_anusCode);
		setTextByid(neonatalVisit_upload.getAnusDesc(),
				R.id.NeonatalVisit_upload_et_anusDesc);
		setTextByid(neonatalVisit_upload.getHeartLungAuscultationCode(),
				R.id.NeonatalVisit_upload_et_heartLungAuscultationCode);
		setTextByid(neonatalVisit_upload.getHeartLungAuscultationDesc(),
				R.id.NeonatalVisit_upload_et_heartLungAuscultationDesc);
		setTextByid(neonatalVisit_upload.getExternalGenitalCode(),
				R.id.NeonatalVisit_upload_et_externalGenitalCode);
		setTextByid(neonatalVisit_upload.getExternalGenitalDesc(),
				R.id.NeonatalVisit_upload_et_externalGenitalDesc);
		setTextByid(neonatalVisit_upload.getAbdominalPalpationCode(),
				R.id.NeonatalVisit_upload_et_abdominalPalpationCode);
		setTextByid(neonatalVisit_upload.getAbdominalPalpationDesc(),
				R.id.NeonatalVisit_upload_et_abdominalPalpationDesc);
		setTextByid(neonatalVisit_upload.getSpineCode(),
				R.id.NeonatalVisit_upload_et_spineCode);
		setTextByid(neonatalVisit_upload.getSpineDesc(),
				R.id.NeonatalVisit_upload_et_spineDesc);
		setTextByid(neonatalVisit_upload.getUmbilicalCordCode(),
				R.id.NeonatalVisit_upload_et_umbilicalCordCode);
		setTextByid(neonatalVisit_upload.getUmbilicalCordOther(),
				R.id.NeonatalVisit_upload_et_umbilicalCordOther);
		setTextByid(neonatalVisit_upload.getReferralCode(),
				R.id.NeonatalVisit_upload_et_referralCode);
		setTextByid(neonatalVisit_upload.getReferralReason(),
				R.id.NeonatalVisit_upload_et_referralReason);
		setTextByid(neonatalVisit_upload.getReferralOrg(),
				R.id.NeonatalVisit_upload_et_referralOrg);
		setTextByid(neonatalVisit_upload.getReferralDepartment(),
				R.id.NeonatalVisit_upload_et_referralDepartment);
		setTextByid(neonatalVisit_upload.getGuideCodes(),
				R.id.NeonatalVisit_upload_et_guideCodes);
		setTextByid(neonatalVisit_upload.getFlupDate(),
				R.id.NeonatalVisit_upload_et_flupDate);
		setTextByid(neonatalVisit_upload.getFlupDoctorCode(),
				R.id.NeonatalVisit_upload_et_flupDoctorCode);
		setTextByid(neonatalVisit_upload.getFlupDoctorName(),
				R.id.NeonatalVisit_upload_et_flupDoctorName);
		setTextByid(neonatalVisit_upload.getFlupOrgCode(),
				R.id.NeonatalVisit_upload_et_flupOrgCode);
		setTextByid(neonatalVisit_upload.getFlupOrgName(),
				R.id.NeonatalVisit_upload_et_flupOrgName);
		setTextByid(neonatalVisit_upload.getNextFlupDate(),
				R.id.NeonatalVisit_upload_et_nextFlupDate);
		setTextByid(neonatalVisit_upload.getNextFlupLocation(),
				R.id.NeonatalVisit_upload_et_nextFlupLocation);
	}

	@Override
	protected void setAllText() {
		// TODO Auto-generated method stub
		ChildSpecial_down child = null;
		child = Basesence.getChildSpecial_down();
		if (child != null) {
			setTextByid(child.getName(), R.id.NeonatalVisit_upload_et_name);
			setTextByid(child.getGenderCode(),
					R.id.NeonatalVisit_upload_et_genderCode);
			setTextByid(child.getBirthday(),
					R.id.NeonatalVisit_upload_et_birthday);
			setTextByid(child.getFatherName(),
					R.id.NeonatalVisit_upload_et_fatherName);
			setTextByid(child.getMatherName(),
					R.id.NeonatalVisit_upload_et_matherName);
		}
		setTextByid(neonatalVisit_upload.getName(),
				R.id.NeonatalVisit_upload_et_name);
		setTextByid(neonatalVisit_upload.getPersonId(),
				R.id.NeonatalVisit_upload_et_personId);
		setTextByid(neonatalVisit_upload.getSpecialNo(),
				R.id.NeonatalVisit_upload_et_specialNo);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.NeonatalVisit_upload_et_flupDoctorCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.NeonatalVisit_upload_et_flupDoctorName);
		setTextByid(Basesence.getorgCode(),
				R.id.NeonatalVisit_upload_et_flupOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.NeonatalVisit_upload_et_flupOrgName);
		setTextByid(Basesence.getmac(this),
				R.id.NeonatalVisit_upload_et_machineCode);
		super.setAllText();
	}

	@Override
	protected NeonatalVisit_upload getAllText() {
		neonatalVisit_upload
				.setPersonId(((EditText) findViewById(R.id.NeonatalVisit_upload_et_personId))
						.getText().toString());
		neonatalVisit_upload
				.setSpecialNo(((EditText) findViewById(R.id.NeonatalVisit_upload_et_specialNo))
						.getText().toString());
		neonatalVisit_upload
				.setMachineCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_machineCode))
						.getText().toString());
		neonatalVisit_upload
				.setMachineNo(((EditText) findViewById(R.id.NeonatalVisit_upload_et_machineNo))
						.getText().toString());
		neonatalVisit_upload
				.setName(((EditText) findViewById(R.id.NeonatalVisit_upload_et_name))
						.getText().toString());
		neonatalVisit_upload
				.setGenderCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_genderCode))
						.getText().toString());
		neonatalVisit_upload
				.setBirthday(((EditText) findViewById(R.id.NeonatalVisit_upload_et_birthday))
						.getText().toString());
		neonatalVisit_upload
				.setIdCard(((EditText) findViewById(R.id.NeonatalVisit_upload_et_idCard))
						.getText().toString());
		neonatalVisit_upload
				.setHomeAddress(((EditText) findViewById(R.id.NeonatalVisit_upload_et_homeAddress))
						.getText().toString());
		neonatalVisit_upload
				.setFatherName(((EditText) findViewById(R.id.NeonatalVisit_upload_et_fatherName))
						.getText().toString());
		neonatalVisit_upload
				.setMatherName(((EditText) findViewById(R.id.NeonatalVisit_upload_et_matherName))
						.getText().toString());
		neonatalVisit_upload
				.setFatherOccupation(((EditText) findViewById(R.id.NeonatalVisit_upload_et_fatherOccupation))
						.getText().toString());
		neonatalVisit_upload
				.setMatherOccupation(((EditText) findViewById(R.id.NeonatalVisit_upload_et_matherOccupation))
						.getText().toString());
		neonatalVisit_upload
				.setFatherContac(((EditText) findViewById(R.id.NeonatalVisit_upload_et_fatherContac))
						.getText().toString());
		neonatalVisit_upload
				.setMatherContac(((EditText) findViewById(R.id.NeonatalVisit_upload_et_matherContac))
						.getText().toString());
		neonatalVisit_upload
				.setFatherBirthday(((EditText) findViewById(R.id.NeonatalVisit_upload_et_fatherBirthday))
						.getText().toString());
		neonatalVisit_upload
				.setMatherBirthday(((EditText) findViewById(R.id.NeonatalVisit_upload_et_matherBirthday))
						.getText().toString());
		neonatalVisit_upload
				.setWeekOfBirth(((EditText) findViewById(R.id.NeonatalVisit_upload_et_weekOfBirth))
						.getText().toString());
		neonatalVisit_upload
				.setPregnancyBeIllCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_pregnancyBeIllCode))
						.getText().toString());
		neonatalVisit_upload
				.setPregnancyBeIllOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_pregnancyBeIllOther))
						.getText().toString());
		neonatalVisit_upload
				.setMidwiferyOrgName(((EditText) findViewById(R.id.NeonatalVisit_upload_et_midwiferyOrgName))
						.getText().toString());
		neonatalVisit_upload
				.setBirthConditionCodes(((EditText) findViewById(R.id.NeonatalVisit_upload_et_birthConditionCodes))
						.getText().toString());
		neonatalVisit_upload
				.setNeonatalAsphyxiaCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_neonatalAsphyxiaCode))
						.getText().toString());
		neonatalVisit_upload
				.setApgar1(((EditText) findViewById(R.id.NeonatalVisit_upload_et_apgar1))
						.getText().toString());
		neonatalVisit_upload
				.setApgar5(((EditText) findViewById(R.id.NeonatalVisit_upload_et_apgar5))
						.getText().toString());
		neonatalVisit_upload
				.setApgar10(((EditText) findViewById(R.id.NeonatalVisit_upload_et_apgar10))
						.getText().toString());
		neonatalVisit_upload
				.setDeformityCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_deformityCode))
						.getText().toString());
		neonatalVisit_upload
				.setDeformityOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_deformityOther))
						.getText().toString());
		neonatalVisit_upload
				.setUNHSCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_UNHSCode))
						.getText().toString());
		neonatalVisit_upload
				.setNeonatalScreeningCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_neonatalScreeningCode))
						.getText().toString());
		neonatalVisit_upload
				.setNeonatalScreeningOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_neonatalScreeningOther))
						.getText().toString());
		neonatalVisit_upload
				.setBirthWeight(((EditText) findViewById(R.id.NeonatalVisit_upload_et_birthWeight))
						.getText().toString());
		neonatalVisit_upload
				.setCurrentWeight(((EditText) findViewById(R.id.NeonatalVisit_upload_et_currentWeight))
						.getText().toString());
		neonatalVisit_upload
				.setBirthHeight(((EditText) findViewById(R.id.NeonatalVisit_upload_et_birthHeight))
						.getText().toString());
		neonatalVisit_upload
				.setFeedingPatternCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_feedingPatternCode))
						.getText().toString());
		neonatalVisit_upload
				.setFeedingAmount(((EditText) findViewById(R.id.NeonatalVisit_upload_et_feedingAmount))
						.getText().toString());
		neonatalVisit_upload
				.setFeedingTimes(((EditText) findViewById(R.id.NeonatalVisit_upload_et_feedingTimes))
						.getText().toString());
		neonatalVisit_upload
				.setVomitCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_vomitCode))
						.getText().toString());
		neonatalVisit_upload
				.setFecalCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_fecalCode))
						.getText().toString());
		neonatalVisit_upload
				.setFecalTimes(((EditText) findViewById(R.id.NeonatalVisit_upload_et_fecalTimes))
						.getText().toString());
		neonatalVisit_upload
				.setTemperature(((EditText) findViewById(R.id.NeonatalVisit_upload_et_temperature))
						.getText().toString());
		neonatalVisit_upload
				.setPulseRate(((EditText) findViewById(R.id.NeonatalVisit_upload_et_pulseRate))
						.getText().toString());
		neonatalVisit_upload
				.setRespiratoryRate(((EditText) findViewById(R.id.NeonatalVisit_upload_et_respiratoryRate))
						.getText().toString());
		neonatalVisit_upload
				.setComplexionCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_complexionCode))
						.getText().toString());
		neonatalVisit_upload
				.setComplexionOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_complexionOther))
						.getText().toString());
		neonatalVisit_upload
				.setJaundiceSiteCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_jaundiceSiteCode))
						.getText().toString());
		neonatalVisit_upload
				.setBregma1(((EditText) findViewById(R.id.NeonatalVisit_upload_et_bregma1))
						.getText().toString());
		neonatalVisit_upload
				.setBregma2(((EditText) findViewById(R.id.NeonatalVisit_upload_et_bregma2))
						.getText().toString());
		neonatalVisit_upload
				.setBregmaCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_bregmaCode))
						.getText().toString());
		neonatalVisit_upload
				.setBregmaOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_bregmaOther))
						.getText().toString());
		neonatalVisit_upload
				.setEyeAppearanceCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_eyeAppearanceCode))
						.getText().toString());
		neonatalVisit_upload
				.setEyeAppearanceDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_eyeAppearanceDesc))
						.getText().toString());
		neonatalVisit_upload
				.setLimbsActivityCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_limbsActivityCode))
						.getText().toString());
		neonatalVisit_upload
				.setLimbsActivityDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_limbsActivityDesc))
						.getText().toString());
		neonatalVisit_upload
				.setEarAppearanceCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_earAppearanceCode))
						.getText().toString());
		neonatalVisit_upload
				.setEarAppearanceDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_earAppearanceDesc))
						.getText().toString());
		neonatalVisit_upload
				.setNeckMassCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_neckMassCode))
						.getText().toString());
		neonatalVisit_upload
				.setNeckMassDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_neckMassDesc))
						.getText().toString());
		neonatalVisit_upload
				.setNoseCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_noseCode))
						.getText().toString());
		neonatalVisit_upload
				.setNoseDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_noseDesc))
						.getText().toString());
		neonatalVisit_upload
				.setSkinCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_skinCode))
						.getText().toString());
		neonatalVisit_upload
				.setSkinOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_skinOther))
						.getText().toString());
		neonatalVisit_upload
				.setOralCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_oralCode))
						.getText().toString());
		neonatalVisit_upload
				.setOralDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_oralDesc))
						.getText().toString());
		neonatalVisit_upload
				.setAnusCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_anusCode))
						.getText().toString());
		neonatalVisit_upload
				.setAnusDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_anusDesc))
						.getText().toString());
		neonatalVisit_upload
				.setHeartLungAuscultationCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_heartLungAuscultationCode))
						.getText().toString());
		neonatalVisit_upload
				.setHeartLungAuscultationDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_heartLungAuscultationDesc))
						.getText().toString());
		neonatalVisit_upload
				.setExternalGenitalCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_externalGenitalCode))
						.getText().toString());
		neonatalVisit_upload
				.setExternalGenitalDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_externalGenitalDesc))
						.getText().toString());
		neonatalVisit_upload
				.setAbdominalPalpationCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_abdominalPalpationCode))
						.getText().toString());
		neonatalVisit_upload
				.setAbdominalPalpationDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_abdominalPalpationDesc))
						.getText().toString());
		neonatalVisit_upload
				.setSpineCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_spineCode))
						.getText().toString());
		neonatalVisit_upload
				.setSpineDesc(((EditText) findViewById(R.id.NeonatalVisit_upload_et_spineDesc))
						.getText().toString());
		neonatalVisit_upload
				.setUmbilicalCordCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_umbilicalCordCode))
						.getText().toString());
		neonatalVisit_upload
				.setUmbilicalCordOther(((EditText) findViewById(R.id.NeonatalVisit_upload_et_umbilicalCordOther))
						.getText().toString());
		neonatalVisit_upload
				.setReferralCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_referralCode))
						.getText().toString());
		neonatalVisit_upload
				.setReferralReason(((EditText) findViewById(R.id.NeonatalVisit_upload_et_referralReason))
						.getText().toString());
		neonatalVisit_upload
				.setReferralOrg(((EditText) findViewById(R.id.NeonatalVisit_upload_et_referralOrg))
						.getText().toString());
		neonatalVisit_upload
				.setReferralDepartment(((EditText) findViewById(R.id.NeonatalVisit_upload_et_referralDepartment))
						.getText().toString());
		neonatalVisit_upload
				.setGuideCodes(((EditText) findViewById(R.id.NeonatalVisit_upload_et_guideCodes))
						.getText().toString());
		neonatalVisit_upload
				.setFlupDate(((EditText) findViewById(R.id.NeonatalVisit_upload_et_flupDate))
						.getText().toString());
		neonatalVisit_upload
				.setFlupDoctorCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_flupDoctorCode))
						.getText().toString());
		neonatalVisit_upload
				.setFlupDoctorName(((EditText) findViewById(R.id.NeonatalVisit_upload_et_flupDoctorName))
						.getText().toString());
		neonatalVisit_upload
				.setFlupOrgCode(((EditText) findViewById(R.id.NeonatalVisit_upload_et_flupOrgCode))
						.getText().toString());
		neonatalVisit_upload
				.setFlupOrgName(((EditText) findViewById(R.id.NeonatalVisit_upload_et_flupOrgName))
						.getText().toString());
		neonatalVisit_upload
				.setNextFlupDate(((EditText) findViewById(R.id.NeonatalVisit_upload_et_nextFlupDate))
						.getText().toString());
		neonatalVisit_upload
				.setNextFlupLocation(((EditText) findViewById(R.id.NeonatalVisit_upload_et_nextFlupLocation))
						.getText().toString());
		return neonatalVisit_upload;
	}

	private void setAllEditTextEnable(boolean enabled) {
		if (enabled) {
			setChoiceEditText(R.id.NeonatalVisit_upload_et_genderCode,
					R.array.jkda_et_genderCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_pregnancyBeIllCode,
					R.array.NeonatalVisit_upload_et_pregnancyBeIllCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_birthConditionCodes,
					R.array.NeonatalVisit_upload_et_birthConditionCodes);
			setChoiceEditText(
					R.id.NeonatalVisit_upload_et_neonatalAsphyxiaCode,
					R.array.NeonatalVisit_upload_et_neonatalAsphyxiaCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_deformityCode,
					R.array.NeonatalVisit_upload_et_deformityCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_UNHSCode,
					R.array.NeonatalVisit_upload_et_UNHSCode);
			setChoiceEditText(
					R.id.NeonatalVisit_upload_et_neonatalScreeningCode,
					R.array.NeonatalVisit_upload_et_neonatalScreeningCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_feedingPatternCode,
					R.array.NeonatalVisit_upload_et_feedingPatternCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_vomitCode,
					R.array.NeonatalVisit_upload_et_vomitCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_fecalCode,
					R.array.NeonatalVisit_upload_et_fecalCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_complexionCode,
					R.array.NeonatalVisit_upload_et_complexionCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_jaundiceSiteCode,
					R.array.NeonatalVisit_upload_et_jaundiceSiteCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_bregmaCode,
					R.array.NeonatalVisit_upload_et_bregmaCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_eyeAppearanceCode,
					R.array.NeonatalVisit_upload_et_eyeAppearanceCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_limbsActivityCode,
					R.array.NeonatalVisit_upload_et_limbsActivityCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_earAppearanceCode,
					R.array.NeonatalVisit_upload_et_earAppearanceCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_neckMassCode,
					R.array.NeonatalVisit_upload_et_neckMassCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_noseCode,
					R.array.NeonatalVisit_upload_et_noseCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_skinCode,
					R.array.NeonatalVisit_upload_et_skinCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_oralCode,
					R.array.NeonatalVisit_upload_et_oralCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_anusCode,
					R.array.NeonatalVisit_upload_et_anusCode);
			setChoiceEditText(
					R.id.NeonatalVisit_upload_et_heartLungAuscultationCode,
					R.array.NeonatalVisit_upload_et_heartLungAuscultationCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_externalGenitalCode,
					R.array.NeonatalVisit_upload_et_externalGenitalCode);
			setChoiceEditText(
					R.id.NeonatalVisit_upload_et_abdominalPalpationCode,
					R.array.NeonatalVisit_upload_et_abdominalPalpationCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_spineCode,
					R.array.NeonatalVisit_upload_et_spineCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_umbilicalCordCode,
					R.array.NeonatalVisit_upload_et_umbilicalCordCode);
			setChoiceEditText(R.id.NeonatalVisit_upload_et_referralCode,
					R.array.NeonatalVisit_upload_et_referralCode);

		}
	}

	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_personId,
				"居民个人ID")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_name, "姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_machineNo,
				"一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_machineCode,
				"健康一体机编码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_genderCode,
				"性别")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_birthday,
				"出生日期")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_idCard, "身份证号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_fatherName,
				"父亲名字")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_matherName,
				"母亲名字")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_flupDate,
				"随访日期")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_flupDoctorCode,
				"随访医生代码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.NeonatalVisit_upload_et_flupOrgCode,
				"随访机构代码")) {
			return true;
		}

		return false;
	}

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.NeonatalVisit_upload_et_temperature:
			intent.setClass(XSEJTFSJL_uploadActivity.this, ETempActivity.class);
			break;
		case R.id.NeonatalVisit_upload_et_pulseRate:
			intent.setClass(XSEJTFSJL_uploadActivity.this,
					MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_OXYGEN);
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
		case Basesence.MEASURE_RESULT_ETMP:
			((EditText) findViewById(R.id.NeonatalVisit_upload_et_temperature))
					.setText(data.getStringExtra(ETempActivity.TAG));
			break;
		case Basesence.MEASURE_RESULT_BLOOD_OXYGEN:
			((EditText) findViewById(R.id.NeonatalVisit_upload_et_pulseRate))
					.setText(data.getStringExtra("ml"));
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
