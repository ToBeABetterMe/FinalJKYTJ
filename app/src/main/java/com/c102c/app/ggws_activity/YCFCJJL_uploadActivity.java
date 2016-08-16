package com.c102c.app.ggws_activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.health_record_activity.JKTJActivity;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.PregnantFirstCheck_upload;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.measurement.MeasureUrineActivity;
import com.health.util.DateEditText;
import com.health.util.SingleChoiceEditText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YCFCJJL_uploadActivity extends BaseActivity implements
		OnClickListener {
	private Button ycfcjjl_upload_btn_upload;
	private PregnantFirstCheck_upload pregnantFirstCheck_upload;
	private String healfilenumber = "";
	PregnantSpecial_down pregnantSpecial_down;

	String specialNo = null;

	protected void initViews() {
		setContentView(R.layout.ycfcjjl_upload);
		setAllEditTextEnable(true);
		SimpleDateFormat tmpDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		((DateEditText) findViewById(R.id.PregnantFirstCheck_upload_et_antenatalCareDate))
				.setText(tmpDateFormat.format(new Date()));
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_machineNo))
				.setText(String.valueOf(System.currentTimeMillis()));
		ycfcjjl_upload_btn_upload = (Button) findViewById(R.id.ycfcjjl_upload_btn_upload);
		pregnantFirstCheck_upload = new PregnantFirstCheck_upload();
		ycfcjjl_upload_btn_upload.setOnClickListener(this);
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
			pregnantFirstCheck_upload = new PregnantFirstCheck_upload();

			if (bundle.getString("from") != null) {
				if (bundle.getString("from").equals("localHistory")) {
					// 通过类的主键来从数据库寻找类，并添加到表中
					String primaryKey = Tools.getPrimaryKey(bundle
							.getString("uuid"));
					pregnantFirstCheck_upload.setId(Long.parseLong(primaryKey));
					List<PregnantFirstCheck_upload> data = (List<PregnantFirstCheck_upload>) AppDB
							.query(pregnantFirstCheck_upload);
					if (data.size() > 0) {
						pregnantFirstCheck_upload = data.get(0);
					}
				}
				setAllEditTextEnable(true);
				// 修改we瓯子
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
		setTextByid(pregnantFirstCheck_upload.getPersonId(),
				R.id.PregnantFirstCheck_upload_et_personId);
		setTextByid(pregnantFirstCheck_upload.getSpecialNo(),
				R.id.PregnantFirstCheck_upload_et_specialNo);
		setTextByid(pregnantFirstCheck_upload.getMachineCode(),
				R.id.PregnantFirstCheck_upload_et_machineCode);
		setTextByid(pregnantFirstCheck_upload.getMachineNo(),
				R.id.PregnantFirstCheck_upload_et_machineNo);
		setTextByid(pregnantFirstCheck_upload.getPregnantManualNo(),
				R.id.PregnantFirstCheck_upload_et_pregnantManualNo);
		setTextByid(pregnantFirstCheck_upload.getName(),
				R.id.PregnantFirstCheck_upload_et_name);
		setTextByid(pregnantFirstCheck_upload.getAntenatalCareDate(),
				R.id.PregnantFirstCheck_upload_et_antenatalCareDate);
		setTextByid(pregnantFirstCheck_upload.getGestationalWeeks(),
				R.id.PregnantFirstCheck_upload_et_gestationalWeeks);
		setTextByid(pregnantFirstCheck_upload.getAge(),
				R.id.PregnantFirstCheck_upload_et_age);
		setTextByid(pregnantFirstCheck_upload.getHusbandName(),
				R.id.PregnantFirstCheck_upload_et_husbandName);
		setTextByid(pregnantFirstCheck_upload.getHusbandAge(),
				R.id.PregnantFirstCheck_upload_et_husbandAge);
		setTextByid(pregnantFirstCheck_upload.getHusbandPhone(),
				R.id.PregnantFirstCheck_upload_et_husbandPhone);
		setTextByid(pregnantFirstCheck_upload.getGravidityTimes(),
				R.id.PregnantFirstCheck_upload_et_gravidityTimes);
		setTextByid(pregnantFirstCheck_upload.getDeliveryTimes(),
				R.id.PregnantFirstCheck_upload_et_deliveryTimes);
		setTextByid(pregnantFirstCheck_upload.getVaginalDeliveryTimes(),
				R.id.PregnantFirstCheck_upload_et_vaginalDeliveryTimes);
		setTextByid(pregnantFirstCheck_upload.getCaesareanSectionTimes(),
				R.id.PregnantFirstCheck_upload_et_caesareanSectionTimes);
		setTextByid(pregnantFirstCheck_upload.getLMP(),
				R.id.PregnantFirstCheck_upload_et_LMP);
		setTextByid(pregnantFirstCheck_upload.getExpectedBirthDate(),
				R.id.PregnantFirstCheck_upload_et_expectedBirthDate);
		setTextByid(pregnantFirstCheck_upload.getPastHistoryCodes(),
				R.id.PregnantFirstCheck_upload_et_pastHistoryCodes);
		setTextByid(pregnantFirstCheck_upload.getPastHistoryOther(),
				R.id.PregnantFirstCheck_upload_et_pastHistoryOther);
		setTextByid(pregnantFirstCheck_upload.getFamilyHistoryCodes(),
				R.id.PregnantFirstCheck_upload_et_familyHistoryCodes);
		setTextByid(pregnantFirstCheck_upload.getFamilyHistoryOther(),
				R.id.PregnantFirstCheck_upload_et_familyHistoryOther);
		setTextByid(pregnantFirstCheck_upload.getPersonalHistoryCodes(),
				R.id.PregnantFirstCheck_upload_et_personalHistoryCodes);
		setTextByid(pregnantFirstCheck_upload.getPersonalHistoryOther(),
				R.id.PregnantFirstCheck_upload_et_personalHistoryOther);
		setTextByid(pregnantFirstCheck_upload.getSurgeryHistoryCode(),
				R.id.PregnantFirstCheck_upload_et_surgeryHistoryCode);
		setTextByid(pregnantFirstCheck_upload.getSurgeryHistoryOther(),
				R.id.PregnantFirstCheck_upload_et_surgeryHistoryOther);
		setTextByid(pregnantFirstCheck_upload.getAbortionTimes(),
				R.id.PregnantFirstCheck_upload_et_abortionTimes);
		setTextByid(pregnantFirstCheck_upload.getStillbirthTimes(),
				R.id.PregnantFirstCheck_upload_et_stillbirthTimes);
		setTextByid(pregnantFirstCheck_upload.getDeadBirthTimes(),
				R.id.PregnantFirstCheck_upload_et_deadBirthTimes);
		setTextByid(pregnantFirstCheck_upload.getNeonatalDeath(),
				R.id.PregnantFirstCheck_upload_et_neonatalDeath);
		setTextByid(pregnantFirstCheck_upload.getBirthDefects(),
				R.id.PregnantFirstCheck_upload_et_birthDefects);
		setTextByid(pregnantFirstCheck_upload.getHeight(),
				R.id.PregnantFirstCheck_upload_et_height);
		setTextByid(pregnantFirstCheck_upload.getWeight(),
				R.id.PregnantFirstCheck_upload_et_weight);
		setTextByid(pregnantFirstCheck_upload.getBmi(),
				R.id.PregnantFirstCheck_upload_et_bmi);
		setTextByid(pregnantFirstCheck_upload.getSBP(),
				R.id.PregnantFirstCheck_upload_et_SBP);
		setTextByid(pregnantFirstCheck_upload.getDBP(),
				R.id.PregnantFirstCheck_upload_et_DBP);
		setTextByid(pregnantFirstCheck_upload.getCardiacAuscultationCode(),
				R.id.PregnantFirstCheck_upload_et_cardiacAuscultationCode);
		setTextByid(pregnantFirstCheck_upload.getCardiacAuscultationDesc(),
				R.id.PregnantFirstCheck_upload_et_cardiacAuscultationDesc);
		setTextByid(pregnantFirstCheck_upload.getLungAuscultationCode(),
				R.id.PregnantFirstCheck_upload_et_lungAuscultationCode);
		setTextByid(pregnantFirstCheck_upload.getLungAuscultationDesc(),
				R.id.PregnantFirstCheck_upload_et_lungAuscultationDesc);
		setTextByid(pregnantFirstCheck_upload.getVulvaCode(),
				R.id.PregnantFirstCheck_upload_et_vulvaCode);
		setTextByid(pregnantFirstCheck_upload.getVulvaDesc(),
				R.id.PregnantFirstCheck_upload_et_vulvaDesc);
		setTextByid(pregnantFirstCheck_upload.getVaginaCode(),
				R.id.PregnantFirstCheck_upload_et_vaginaCode);
		setTextByid(pregnantFirstCheck_upload.getVaginaDesc(),
				R.id.PregnantFirstCheck_upload_et_vaginaDesc);
		setTextByid(pregnantFirstCheck_upload.getCervixCode(),
				R.id.PregnantFirstCheck_upload_et_cervixCode);
		setTextByid(pregnantFirstCheck_upload.getCervixDesc(),
				R.id.PregnantFirstCheck_upload_et_cervixDesc);
		setTextByid(pregnantFirstCheck_upload.getUterusCode(),
				R.id.PregnantFirstCheck_upload_et_uterusCode);
		setTextByid(pregnantFirstCheck_upload.getUterusDesc(),
				R.id.PregnantFirstCheck_upload_et_uterusDesc);
		setTextByid(pregnantFirstCheck_upload.getUterineAccessoriesCode(),
				R.id.PregnantFirstCheck_upload_et_uterineAccessoriesCode);
		setTextByid(pregnantFirstCheck_upload.getUterineAccessoriesDesc(),
				R.id.PregnantFirstCheck_upload_et_uterineAccessoriesDesc);
		setTextByid(pregnantFirstCheck_upload.getHemoglobin(),
				R.id.PregnantFirstCheck_upload_et_hemoglobin);
		setTextByid(pregnantFirstCheck_upload.getLeucocyte(),
				R.id.PregnantFirstCheck_upload_et_leucocyte);
		setTextByid(pregnantFirstCheck_upload.getPlatelet(),
				R.id.PregnantFirstCheck_upload_et_platelet);
		setTextByid(pregnantFirstCheck_upload.getBloodOther(),
				R.id.PregnantFirstCheck_upload_et_bloodOther);
		setTextByid(pregnantFirstCheck_upload.getUrineProteinCode(),
				R.id.PregnantFirstCheck_upload_et_urineProteinCode);
		setTextByid(pregnantFirstCheck_upload.getUrineSugarCode(),
				R.id.PregnantFirstCheck_upload_et_urineSugarCode);
		setTextByid(pregnantFirstCheck_upload.getUrineKetoneCode(),
				R.id.PregnantFirstCheck_upload_et_urineKetoneCode);
		setTextByid(pregnantFirstCheck_upload.getUrineOccultBloodCode(),
				R.id.PregnantFirstCheck_upload_et_urineOccultBloodCode);
		setTextByid(pregnantFirstCheck_upload.getUrineOther(),
				R.id.PregnantFirstCheck_upload_et_urineOther);
		setTextByid(pregnantFirstCheck_upload.getBloodGroupCode(),
				R.id.PregnantFirstCheck_upload_et_bloodGroupCode);
		setTextByid(pregnantFirstCheck_upload.getRhBloodGroupCode(),
				R.id.PregnantFirstCheck_upload_et_rhBloodGroupCode);
		setTextByid(pregnantFirstCheck_upload.getBloodGlucose(),
				R.id.PregnantFirstCheck_upload_et_bloodGlucose);
		setTextByid(pregnantFirstCheck_upload.getGPT(),
				R.id.PregnantFirstCheck_upload_et_GPT);
		setTextByid(pregnantFirstCheck_upload.getGOT(),
				R.id.PregnantFirstCheck_upload_et_GOT);
		setTextByid(pregnantFirstCheck_upload.getAlbumin(),
				R.id.PregnantFirstCheck_upload_et_albumin);
		setTextByid(pregnantFirstCheck_upload.getTBIL(),
				R.id.PregnantFirstCheck_upload_et_TBIL);
		setTextByid(pregnantFirstCheck_upload.getDBIL(),
				R.id.PregnantFirstCheck_upload_et_DBIL);
		setTextByid(pregnantFirstCheck_upload.getSerumCreatinine(),
				R.id.PregnantFirstCheck_upload_et_serumCreatinine);
		setTextByid(pregnantFirstCheck_upload.getBUN(),
				R.id.PregnantFirstCheck_upload_et_BUN);
		setTextByid(pregnantFirstCheck_upload.getVaginalFluidCodes(),
				R.id.PregnantFirstCheck_upload_et_vaginalFluidCodes);
		setTextByid(pregnantFirstCheck_upload.getVaginalFluidOther(),
				R.id.PregnantFirstCheck_upload_et_vaginalFluidOther);
		setTextByid(pregnantFirstCheck_upload.getVaginalCleanlinessCode(),
				R.id.PregnantFirstCheck_upload_et_vaginalCleanlinessCode);
		setTextByid(pregnantFirstCheck_upload.getHBsAgCode(),
				R.id.PregnantFirstCheck_upload_et_HBsAgCode);
		setTextByid(pregnantFirstCheck_upload.getAntiHBsCode(),
				R.id.PregnantFirstCheck_upload_et_antiHBsCode);
		setTextByid(pregnantFirstCheck_upload.getHBeAgCode(),
				R.id.PregnantFirstCheck_upload_et_HBeAgCode);
		setTextByid(pregnantFirstCheck_upload.getHBeAbCode(),
				R.id.PregnantFirstCheck_upload_et_HBeAbCode);
		setTextByid(pregnantFirstCheck_upload.getHBcAbCode(),
				R.id.PregnantFirstCheck_upload_et_HBcAbCode);
		setTextByid(pregnantFirstCheck_upload.getSyphilisCode(),
				R.id.PregnantFirstCheck_upload_et_syphilisCode);
		setTextByid(pregnantFirstCheck_upload.getHIVCode(),
				R.id.PregnantFirstCheck_upload_et_HIVCode);
		setTextByid(pregnantFirstCheck_upload.getBScan(),
				R.id.PregnantFirstCheck_upload_et_BScan);
		setTextByid(pregnantFirstCheck_upload.getOverallAssessmentCode(),
				R.id.PregnantFirstCheck_upload_et_overallAssessmentCode);
		setTextByid(pregnantFirstCheck_upload.getOverallAssessmentDesc(),
				R.id.PregnantFirstCheck_upload_et_overallAssessmentDesc);
		setTextByid(pregnantFirstCheck_upload.getHealthGuideCodes(),
				R.id.PregnantFirstCheck_upload_et_healthGuideCodes);
		setTextByid(pregnantFirstCheck_upload.getHealthGuideOther(),
				R.id.PregnantFirstCheck_upload_et_healthGuideOther);
		setTextByid(pregnantFirstCheck_upload.getReferralCode(),
				R.id.PregnantFirstCheck_upload_et_referralCode);
		setTextByid(pregnantFirstCheck_upload.getReferralReason(),
				R.id.PregnantFirstCheck_upload_et_referralReason);
		setTextByid(pregnantFirstCheck_upload.getReferralOrg(),
				R.id.PregnantFirstCheck_upload_et_referralOrg);
		setTextByid(pregnantFirstCheck_upload.getReferralDepartment(),
				R.id.PregnantFirstCheck_upload_et_referralDepartment);
		setTextByid(pregnantFirstCheck_upload.getCheckDoctorCode(),
				R.id.PregnantFirstCheck_upload_et_checkDoctorCode);
		setTextByid(pregnantFirstCheck_upload.getCheckDoctorName(),
				R.id.PregnantFirstCheck_upload_et_checkDoctorName);
		setTextByid(pregnantFirstCheck_upload.getCheckOrgCode(),
				R.id.PregnantFirstCheck_upload_et_checkOrgCode);
		setTextByid(pregnantFirstCheck_upload.getCheckOrgName(),
				R.id.PregnantFirstCheck_upload_et_checkOrgName);
		setTextByid(pregnantFirstCheck_upload.getNextFlupDate(),
				R.id.PregnantFirstCheck_upload_et_nextFlupDate);
	};

	private void settext() {
		// TODO Auto-generated method stub
		setTextByid(pregnantSpecial_down.getName(),
				R.id.PregnantFirstCheck_upload_et_name);
		setTextByid(pregnantSpecial_down.getPersonId(),
				R.id.PregnantFirstCheck_upload_et_personId);

		setTextByid(Basesence.getmac(this),
				R.id.PregnantFirstCheck_upload_et_machineCode);
		setTextByid(pregnantSpecial_down.getHusbandName(),
				R.id.PregnantFirstCheck_upload_et_husbandName);
		setTextByid(pregnantSpecial_down.getHusbandPhone(),
				R.id.PregnantFirstCheck_upload_et_husbandPhone);
		setTextByid(pregnantSpecial_down.getLMP(),
				R.id.PregnantFirstCheck_upload_et_LMP);
		setTextByid(Basesence.getorgCode(),
				R.id.PregnantFirstCheck_upload_et_checkOrgCode);
		setTextByid(Basesence.getorgName(),
				R.id.PregnantFirstCheck_upload_et_checkOrgName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.PregnantFirstCheck_upload_et_checkDoctorCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.PregnantFirstCheck_upload_et_checkDoctorName);
	}

	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.PregnantFirstCheck_upload_et_personId,
				"居民个人ID")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantFirstCheck_upload_et_name,
				"孕妇姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantFirstCheck_upload_et_machineNo,
				"一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_antenatalCareDate, "产检日期")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_gestationalWeeks, "孕周")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_husbandName, "丈夫姓名")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_husbandPhone, "丈夫电话")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantFirstCheck_upload_et_LMP,
				"末次月经")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_expectedBirthDate, "预产期")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_syphilisCode, "梅毒血清学试验")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.PregnantFirstCheck_upload_et_HIVCode,
				"HIV抗体检测")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_checkDoctorCode, "产检医生代码")) {
			return true;
		}
		if (setEditTextNullMessage(
				R.id.PregnantFirstCheck_upload_et_checkOrgCode, "产检机构代码")) {
			return true;
		}
		return false;
	}

	private void setAllEditTextEnable(boolean enabled) {
		if (enabled) {
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_pastHistoryCodes,
					R.array.PregnantFirstCheck_upload_et_pastHistoryCodes);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_familyHistoryCodes,
					R.array.PregnantFirstCheck_upload_et_familyHistoryCodes);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_personalHistoryCodes,
					R.array.PregnantFirstCheck_upload_et_personalHistoryCodes);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_surgeryHistoryCode,
					R.array.PregnantFirstCheck_upload_et_surgeryHistoryCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_cardiacAuscultationCode,
					R.array.PregnantFirstCheck_upload_et_cardiacAuscultationCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_lungAuscultationCode,
					R.array.PregnantFirstCheck_upload_et_lungAuscultationCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_vulvaCode,
					R.array.PregnantFirstCheck_upload_et_vulvaCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_vaginaCode,
					R.array.PregnantFirstCheck_upload_et_vaginaCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_cervixCode,
					R.array.PregnantFirstCheck_upload_et_cervixCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_uterusCode,
					R.array.PregnantFirstCheck_upload_et_uterusCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_uterineAccessoriesCode,
					R.array.PregnantFirstCheck_upload_et_uterineAccessoriesCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_urineProteinCode,
					R.array.PregnantFirstCheck_upload_et_urineProteinCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_urineSugarCode,
					R.array.PregnantFirstCheck_upload_et_urineSugarCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_urineKetoneCode,
					R.array.PregnantFirstCheck_upload_et_urineKetoneCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_urineOccultBloodCode,
					R.array.PregnantFirstCheck_upload_et_urineOccultBloodCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_bloodGroupCode,
					R.array.PregnantFirstCheck_upload_et_bloodGroupCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_rhBloodGroupCode,
					R.array.PregnantFirstCheck_upload_et_rhBloodGroupCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_vaginalFluidCodes,
					R.array.PregnantFirstCheck_upload_et_vaginalFluidCodes);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_vaginalCleanlinessCode,
					R.array.PregnantFirstCheck_upload_et_vaginalCleanlinessCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBsAgCode,
					R.array.PregnantFirstCheck_upload_et_HBsAgCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_antiHBsCode,
					R.array.PregnantFirstCheck_upload_et_antiHBsCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBeAgCode,
					R.array.PregnantFirstCheck_upload_et_HBeAgCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBeAbCode,
					R.array.PregnantFirstCheck_upload_et_HBeAbCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBcAbCode,
					R.array.PregnantFirstCheck_upload_et_HBcAbCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_syphilisCode,
					R.array.PregnantFirstCheck_upload_et_syphilisCode);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HIVCode,
					R.array.PregnantFirstCheck_upload_et_HIVCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_overallAssessmentCode,
					R.array.PregnantFirstCheck_upload_et_overallAssessmentCode);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_healthGuideCodes,
					R.array.PregnantFirstCheck_upload_et_healthGuideCodes);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_referralCode,
					R.array.PregnantFirstCheck_upload_et_referralCode);
		} else {
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_pastHistoryCodes, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_familyHistoryCodes, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_personalHistoryCodes, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_surgeryHistoryCode, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_cardiacAuscultationCode,
					-1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_lungAuscultationCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_vulvaCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_vaginaCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_cervixCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_uterusCode, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_uterineAccessoriesCode,
					-1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_urineProteinCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_urineSugarCode,
					-1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_urineKetoneCode, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_urineOccultBloodCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_bloodGroupCode,
					-1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_rhBloodGroupCode, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_vaginalFluidCodes, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_vaginalCleanlinessCode,
					-1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBsAgCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_antiHBsCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBeAgCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBeAbCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HBcAbCode, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_syphilisCode,
					-1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_HIVCode, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_overallAssessmentCode, -1);
			setChoiceEditText(
					R.id.PregnantFirstCheck_upload_et_healthGuideCodes, -1);
			setChoiceEditText(R.id.PregnantFirstCheck_upload_et_referralCode,
					-1);
		}
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_personId))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_specialNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_machineCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_machineNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_pregnantManualNo))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_name))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_antenatalCareDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_gestationalWeeks))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_age))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_husbandName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_husbandAge))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_husbandPhone))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_gravidityTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_deliveryTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalDeliveryTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_caesareanSectionTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_LMP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_expectedBirthDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_pastHistoryCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_pastHistoryOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_familyHistoryCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_familyHistoryOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_personalHistoryCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_personalHistoryOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_surgeryHistoryCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_surgeryHistoryOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_abortionTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_stillbirthTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_deadBirthTimes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_neonatalDeath))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_birthDefects))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_height))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_weight))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bmi))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_SBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_DBP))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cardiacAuscultationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cardiacAuscultationDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_lungAuscultationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_lungAuscultationDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vulvaCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vulvaDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginaCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginaDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cervixCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cervixDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterusCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterusDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterineAccessoriesCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterineAccessoriesDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_hemoglobin))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_leucocyte))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_platelet))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineProteinCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineSugarCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineKetoneCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineOccultBloodCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodGroupCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_rhBloodGroupCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodGlucose))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_GPT))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_GOT))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_albumin))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_TBIL))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_DBIL))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_serumCreatinine))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_BUN))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalFluidCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalFluidOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalCleanlinessCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBsAgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_antiHBsCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBeAgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBeAbCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBcAbCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_syphilisCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HIVCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_BScan))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_overallAssessmentCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_overallAssessmentDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_healthGuideCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_healthGuideOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralReason))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralOrg))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralDepartment))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkOrgName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_nextFlupDate))
				.setEnabled(enabled);

	}

	@Override
	protected PregnantFirstCheck_upload getAllText() {
		pregnantFirstCheck_upload
				.setPersonId(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_personId))
						.getText().toString());
		pregnantFirstCheck_upload
				.setSpecialNo(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_specialNo))
						.getText().toString());
		pregnantFirstCheck_upload
				.setMachineCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_machineCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setMachineNo(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_machineNo))
						.getText().toString());
		pregnantFirstCheck_upload
				.setPregnantManualNo(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_pregnantManualNo))
						.getText().toString());
		pregnantFirstCheck_upload
				.setName(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_name))
						.getText().toString());
		pregnantFirstCheck_upload
				.setAntenatalCareDate(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_antenatalCareDate))
						.getText().toString());
		pregnantFirstCheck_upload
				.setGestationalWeeks(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_gestationalWeeks))
						.getText().toString());
		pregnantFirstCheck_upload
				.setAge(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_age))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHusbandName(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_husbandName))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHusbandAge(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_husbandAge))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHusbandPhone(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_husbandPhone))
						.getText().toString());
		pregnantFirstCheck_upload
				.setGravidityTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_gravidityTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setDeliveryTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_deliveryTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVaginalDeliveryTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalDeliveryTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCaesareanSectionTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_caesareanSectionTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setLMP(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_LMP))
						.getText().toString());
		pregnantFirstCheck_upload
				.setExpectedBirthDate(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_expectedBirthDate))
						.getText().toString());
		pregnantFirstCheck_upload
				.setPastHistoryCodes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_pastHistoryCodes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setPastHistoryOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_pastHistoryOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setFamilyHistoryCodes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_familyHistoryCodes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setFamilyHistoryOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_familyHistoryOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setPersonalHistoryCodes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_personalHistoryCodes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setPersonalHistoryOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_personalHistoryOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setSurgeryHistoryCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_surgeryHistoryCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setSurgeryHistoryOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_surgeryHistoryOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setAbortionTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_abortionTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setStillbirthTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_stillbirthTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setDeadBirthTimes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_deadBirthTimes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setNeonatalDeath(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_neonatalDeath))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBirthDefects(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_birthDefects))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHeight(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_height))
						.getText().toString());
		pregnantFirstCheck_upload
				.setWeight(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_weight))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBmi(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bmi))
						.getText().toString());
		pregnantFirstCheck_upload
				.setSBP(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_SBP))
						.getText().toString());
		pregnantFirstCheck_upload
				.setDBP(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_DBP))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCardiacAuscultationCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cardiacAuscultationCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCardiacAuscultationDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cardiacAuscultationDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setLungAuscultationCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_lungAuscultationCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setLungAuscultationDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_lungAuscultationDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVulvaCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vulvaCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVulvaDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vulvaDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVaginaCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginaCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVaginaDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginaDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCervixCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cervixCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCervixDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_cervixDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUterusCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterusCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUterusDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterusDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUterineAccessoriesCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterineAccessoriesCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUterineAccessoriesDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_uterineAccessoriesDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHemoglobin(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_hemoglobin))
						.getText().toString());
		pregnantFirstCheck_upload
				.setLeucocyte(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_leucocyte))
						.getText().toString());
		pregnantFirstCheck_upload
				.setPlatelet(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_platelet))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBloodOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUrineProteinCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineProteinCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUrineSugarCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineSugarCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUrineKetoneCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineKetoneCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUrineOccultBloodCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineOccultBloodCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setUrineOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBloodGroupCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodGroupCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setRhBloodGroupCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_rhBloodGroupCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBloodGlucose(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodGlucose))
						.getText().toString());
		pregnantFirstCheck_upload
				.setGPT(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_GPT))
						.getText().toString());
		pregnantFirstCheck_upload
				.setGOT(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_GOT))
						.getText().toString());
		pregnantFirstCheck_upload
				.setAlbumin(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_albumin))
						.getText().toString());
		pregnantFirstCheck_upload
				.setTBIL(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_TBIL))
						.getText().toString());
		pregnantFirstCheck_upload
				.setDBIL(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_DBIL))
						.getText().toString());
		pregnantFirstCheck_upload
				.setSerumCreatinine(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_serumCreatinine))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBUN(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_BUN))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVaginalFluidCodes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalFluidCodes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVaginalFluidOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalFluidOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setVaginalCleanlinessCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_vaginalCleanlinessCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHBsAgCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBsAgCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setAntiHBsCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_antiHBsCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHBeAgCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBeAgCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHBeAbCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBeAbCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHBcAbCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HBcAbCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setSyphilisCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_syphilisCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHIVCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_HIVCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setBScan(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_BScan))
						.getText().toString());
		pregnantFirstCheck_upload
				.setOverallAssessmentCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_overallAssessmentCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setOverallAssessmentDesc(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_overallAssessmentDesc))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHealthGuideCodes(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_healthGuideCodes))
						.getText().toString());
		pregnantFirstCheck_upload
				.setHealthGuideOther(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_healthGuideOther))
						.getText().toString());
		pregnantFirstCheck_upload
				.setReferralCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setReferralReason(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralReason))
						.getText().toString());
		pregnantFirstCheck_upload
				.setReferralOrg(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralOrg))
						.getText().toString());
		pregnantFirstCheck_upload
				.setReferralDepartment(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_referralDepartment))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCheckDoctorCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkDoctorCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCheckDoctorName(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkDoctorName))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCheckOrgCode(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkOrgCode))
						.getText().toString());
		pregnantFirstCheck_upload
				.setCheckOrgName(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_checkOrgName))
						.getText().toString());
		pregnantFirstCheck_upload
				.setNextFlupDate(((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_nextFlupDate))
						.getText().toString());
		return pregnantFirstCheck_upload;
	}

	public void onClick(View v) {
		AppDB.getInstance(this);
		if (checkAllEditTextIsNull()) {
			return;
		}

		Fetch_by_li.communicate("M0100040201", this, handler, getAllText());
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
						Toast.makeText(YCFCJJL_uploadActivity.this,
								"没找到这个用户的健康档案", 0).show();
					}
					// Toast.makeText(XSEJTFSJL_uploadActivity.this, "成功",
					// 0).show();
					break;
				case Basesence.M0100040201:
					Toast.makeText(YCFCJJL_uploadActivity.this, "成功", 0).show();
					break;
				case 0:
					Toast.makeText(YCFCJJL_uploadActivity.this,
							"该孕妇已经做过检测，请勿继续操作！", 0).show();
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
					if(msg.arg1==1234){
						Toast.makeText(YCFCJJL_uploadActivity.this,
								(String)msg.obj, 0).show();
					}
					else
				Toast.makeText(YCFCJJL_uploadActivity.this,
						a, 0).show();
			}
			super.handleMessage(msg);
		}

	};

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.PregnantFirstCheck_upload_et_DBP:
		case R.id.PregnantFirstCheck_upload_et_SBP:
			intent.setClass(YCFCJJL_uploadActivity.this,
					MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			break;
		case R.id.PregnantFirstCheck_upload_et_urineOther:
			intent.setClass(YCFCJJL_uploadActivity.this,
					MeasureUrineActivity.class);
			break;
		case R.id.PregnantFirstCheck_upload_et_bloodGlucose:
			intent.setClass(YCFCJJL_uploadActivity.this,
					MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_SUGAR);
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
			((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_DBP))
					.setText(data.getStringExtra("szy"));
			((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_SBP))
					.setText(data.getStringExtra("ssy"));
			break;
		case Basesence.MEASURE_RESULT_URINE:
			((SingleChoiceEditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineProteinCode))
					.setText(ToUrineData(data.getStringExtra("ndb")));
			((SingleChoiceEditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineOccultBloodCode))
					.setText(ToUrineData(data.getStringExtra("nqx")));
			((SingleChoiceEditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineSugarCode))
					.setText(ToUrineData(data.getStringExtra("nt")));
			((SingleChoiceEditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineKetoneCode))
					.setText(ToUrineData(data.getStringExtra("ntt")));
			((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_urineOther))
					.setText(data.getStringExtra("nqt"));
			break;
		case Basesence.MEASURE_RESULT_BLOOD_SUGAR:
			((EditText) findViewById(R.id.PregnantFirstCheck_upload_et_bloodGlucose))
					.setText(data.getStringExtra("xt"));
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
			Toast.makeText(YCFCJJL_uploadActivity.this, "尿液分析数据错误",
					Toast.LENGTH_LONG).show();
			return "6";
		}
	}
}
