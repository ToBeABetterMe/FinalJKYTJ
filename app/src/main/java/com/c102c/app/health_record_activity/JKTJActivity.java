package com.c102c.app.health_record_activity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.HealthExamination;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ETempActivity;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.measurement.MeasureUrineActivity;
import com.health.measurement.MeasureWeilionECG;
import com.health.util.ChoiceEditText;
import com.health.util.DateEditText;
import com.health.util.SingleChoiceEditText;

public class JKTJActivity extends Activity implements OnClickListener,
		TextWatcher {

	private Button ok;
	private EditText mETempEditText;
	private EditText mPulseRateEditText;
	private EditText mLeftSBPEditText;
	private EditText mRightSBPEditText;
	private EditText mLeftDBPEditText;
	private EditText mRightDBPEditText;
	private EditText mFastingPlasmaGlucoseEditText;
	private EditText mBloodOxygen;
	private SingleChoiceEditText mXinDianSingleChoiceEditText;
	private SingleChoiceEditText mXinDianDesSingleChoiceEditText;
	private EditText mXinDianDataEditText;

	// 尿液分析仪,可以测量 白细胞 亚硝酸盐 尿胆原 蛋白质 pH值 潜血 比重 酮体 胆红素 葡萄糖 维生素C 12个项目
	private SingleChoiceEditText mNDBSingleChoiceEditText;// 尿蛋白
	private SingleChoiceEditText mNQXSingleChoiceEditText;// 尿潜血
	private SingleChoiceEditText mNTSingleChoiceEditText;// 尿糖
	private SingleChoiceEditText mNTTSingleChoiceEditText;// 尿酮体
	private EditText mOtherNEditText;// 尿常规其他

	private HealthExamination healthExamination;

	private String mECGData = "";

	public final static int HEAD_TEMP = 0x001;

	private EditText mHeightEditText;
	private EditText mWeightEditText;
	private EditText mTZZSEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		initViews();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			if (bundle.get("from").equals("list")) {
				healthExamination = Basesence.getHealthExamination();
				setAllText();
			} else if (bundle.get("from").equals("new")) {
				healthExamination = new HealthExamination();
				healthExamination.setName(bundle.getString("name"));
				healthExamination.setPersonId((bundle.getString("personid")));
				healthExamination.setHealthFileNumber(bundle.getString("NO"));
				healthExamination.setMachineNo(Basesence.getuuid());
				healthExamination.setMachineCode(Basesence
						.getmac(JKTJActivity.this));
				setAllEditTextEnable(true);
				ok.setVisibility(View.VISIBLE);

				SimpleDateFormat tmpDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat tmpDateFormat1 =new SimpleDateFormat("yyyy-MM-dd");
				((DateEditText) findViewById(R.id.jktj_et_examinationDate))
						.setText(tmpDateFormat1.format(new Date()));
				((DateEditText) findViewById(R.id.jktj_et_operateTime))
						.setText(tmpDateFormat.format(new Date()));
				setAllText();
			} else if (bundle.get("from").equals("localHistory")) {
				// 啟晖添加
				// 通过类的主键来从数据库寻找类，并添加到表中
				healthExamination = new HealthExamination();
				String primaryKey = Tools.getPrimaryKey(bundle
						.getString("uuid"));
				healthExamination.setBusinessKey(primaryKey);
				List<HealthExamination> data = (List<HealthExamination>) AppDB
						.query(healthExamination);
				if (data.size() > 0) {
					healthExamination = data.get(0);
				}
				setAllEditTextEnable(true);
				ok.setVisibility(View.VISIBLE);
				setAllText();
			}
		}
		super.onCreate(savedInstanceState);
	}

	/** 给editText设置要选择的数组 */
	public void setChoiceEditText(int et_Id, int arrayId) {
		if (arrayId == -1) {
			((ChoiceEditText) findViewById(et_Id)).setFixItems(null);
		} else
			((ChoiceEditText) findViewById(et_Id)).setFixItems(getResources()
					.getStringArray(arrayId));
	}

	protected void initViews() {
		setContentView(R.layout.jktj);
		ok = (Button) findViewById(R.id.jktj_btn_OK);
		mETempEditText = (EditText) findViewById(R.id.jktj_et_temperature);

		mPulseRateEditText = (EditText) findViewById(R.id.jktj_et_pulseRate);
		mLeftSBPEditText = (EditText) findViewById(R.id.jktj_et_leftSBP);
		mRightSBPEditText = (EditText) findViewById(R.id.jktj_et_rightSBP);
		mLeftDBPEditText = (EditText) findViewById(R.id.jktj_et_leftDBP);
		mRightDBPEditText = (EditText) findViewById(R.id.jktj_et_rightDBP);
		mBloodOxygen = (EditText) findViewById(R.id.tv_bo);
		mFastingPlasmaGlucoseEditText = (EditText) findViewById(R.id.jktj_et_fastingPlasmaGlucose1);

		mXinDianSingleChoiceEditText = (SingleChoiceEditText) findViewById(R.id.jktj_et_ECGCode);
		mXinDianDesSingleChoiceEditText = (SingleChoiceEditText) findViewById(R.id.jktj_et_ECGDesc);
		mXinDianDataEditText = (EditText) findViewById(R.id.jktj_et_ECGData);

		mNDBSingleChoiceEditText = (SingleChoiceEditText) findViewById(R.id.jktj_et_urineProteinCode);
		mNQXSingleChoiceEditText = (SingleChoiceEditText) findViewById(R.id.jktj_et_urineOccultBloodCode);
		mNTSingleChoiceEditText = (SingleChoiceEditText) findViewById(R.id.jktj_et_urineSugarCode);
		mNTTSingleChoiceEditText = (SingleChoiceEditText) findViewById(R.id.jktj_et_urineKetoneCode);
		mOtherNEditText = (EditText) findViewById(R.id.jktj_et_urineOther);

		mHeightEditText = (EditText) findViewById(R.id.jktj_et_height);
		mWeightEditText = (EditText) findViewById(R.id.jktj_et_weight);
		mTZZSEditText = (EditText) findViewById(R.id.jktj_et_bmi);

		mHeightEditText.addTextChangedListener(this);
		mWeightEditText.addTextChangedListener(this);

		mETempEditText.setOnClickListener(this);
		mBloodOxygen.setOnClickListener(this);
		mPulseRateEditText.setOnClickListener(this);
		mLeftSBPEditText.setOnClickListener(this);
		mRightSBPEditText.setOnClickListener(this);
		mLeftDBPEditText.setOnClickListener(this);
		mRightDBPEditText.setOnClickListener(this);

		mXinDianDataEditText.setOnClickListener(this);

		mFastingPlasmaGlucoseEditText.setOnClickListener(this);

		mOtherNEditText.setOnClickListener(this);

		ok.setOnClickListener(this);
		setAllEditTextEnable(false);
		healthExamination = new HealthExamination();
	}

	/** 通过id来设置显示Text文本内容 */
	public void setTextByid(String content, int id) {
		if (!TextUtils.isEmpty(content)) {
			EditText et = (EditText) findViewById(id);
			et.setText(content);
		}
	}

	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.jktj_btn_OK:
			if (!checkAllEditTextIsNull()) {
				healthExamination = getAllText();
				// Fetch.communicate("M0100030201", JKTJActivity.this, handler,
				// Util.setHealthExamination(healthExamination));
				Fetch_by_li.communicate("M0100030201", JKTJActivity.this,
						handler, healthExamination);
			}
			break;
		case R.id.jktj_et_temperature:
			// 额温测量
			intent = new Intent(JKTJActivity.this, ETempActivity.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		// case R.id.jktj_et_pulseRate:
		case R.id.jktj_et_leftSBP:
		case R.id.jktj_et_leftDBP:
			// 血压左测量
			intent = new Intent(JKTJActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jktj_et_rightDBP:
		case R.id.jktj_et_rightSBP:
			// 血压右测量
			intent = new Intent(JKTJActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token",
					MeasureOnPC300Activity.BLOOD_PRESSURE_RIGHT);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jktj_et_fastingPlasmaGlucose1:
			// 血糖测量
			intent = new Intent(JKTJActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_SUGAR);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jktj_et_ECGData:
			// 心电测量
			intent = new Intent(JKTJActivity.this, MeasureWeilionECG.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jktj_et_urineOther:
			// 尿液分析
			intent = new Intent(JKTJActivity.this, MeasureUrineActivity.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.tv_bo:
			// 血氧测量
			intent = new Intent(JKTJActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_OXYGEN);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		default:
			break;
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (resultCode) {
		case Basesence.MEASURE_RESULT_ETMP:
			mETempEditText.setText(data.getStringExtra(ETempActivity.TAG));
			break;
		case Basesence.MEASURE_RESULT_LEFT_BLOOD_PRESURE:
			mPulseRateEditText.setText(data.getStringExtra("ml"));
			mLeftDBPEditText.setText(data.getStringExtra("szy"));
			mLeftSBPEditText.setText(data.getStringExtra("ssy"));
			mFastingPlasmaGlucoseEditText.setText(data.getStringExtra("xt"));
			break;
		case Basesence.MEASURE_RESULT_RIGHT_BLOOD_PRESURE:
			mPulseRateEditText.setText(data.getStringExtra("ml"));
			mRightDBPEditText.setText(data.getStringExtra("szy"));
			mRightSBPEditText.setText(data.getStringExtra("ssy"));
			mFastingPlasmaGlucoseEditText.setText(data.getStringExtra("xt"));
			break;
		case Basesence.MEASURE_RESULT_BLOOD_SUGAR:
			mFastingPlasmaGlucoseEditText.setText(data.getStringExtra("xt"));
			break;
		case Basesence.MEASURE_RESULT_XIN_DIAN:
			((SingleChoiceEditText) findViewById(R.id.jktj_et_ECGDesc))
					.setText(data.getStringExtra("xdjg"));
			mXinDianDataEditText.setText("...");
			// mECGData = data.getStringExtra("xdsj");
			// 心电数据后期上传
			break;
		case Basesence.MEASURE_RESULT_URINE:
			mNDBSingleChoiceEditText.setText(ToUrineData(data
					.getStringExtra("ndb")));
			mNQXSingleChoiceEditText.setText(ToUrineData(data
					.getStringExtra("nqx")));
			mNTSingleChoiceEditText.setText(ToUrineData(data
					.getStringExtra("nt")));
			mNTTSingleChoiceEditText.setText(ToUrineData(data
					.getStringExtra("ntt")));
			mOtherNEditText.setText(data.getStringExtra("nqt"));
			break;
		case Basesence.MEASURE_RESULT_BLOOD_OXYGEN:
			mBloodOxygen.setText(data.getStringExtra("xy"));
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Handler handler = Basesence.getupdatehandler(JKTJActivity.this);

	// public void onclick(View paramView) {

	/** 检查EditText不为空。 */
	private boolean checkAllEditTextIsNull() {
		if (setEditTextNullMessage(R.id.jktj_et_personId, "居民个人ID")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_machineCode, "健康一体机编码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_machineNo, "一体机业务序号")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_name, "姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_examinationDate, "体检日期")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_orgCode, "体检机构代码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_operatorCode, "操作员代码")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_operatorName, "操作员姓名")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_operateTime, "操作时间")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_temperature, "体温")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_pulseRate, "脉率")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_respiratoryRate, "呼吸频率")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_height, "身高")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_weight, "体重")) {
			return true;
		}
		if (setEditTextNullMessage(R.id.jktj_et_waistline, "腰围")) {
			return true;
		}
		return false;
	}

	/** 设置EditText为空时的提示消息 */
	public boolean setEditTextNullMessage(int etId, String message) {
		String str = "不能为空";
		if (TextUtils.isEmpty(((EditText) findViewById(etId)).getText()
				.toString().trim())) {
			Toast.makeText(JKTJActivity.this, message + str, Toast.LENGTH_SHORT)
					.show();
			return true;
		}
		return false;
	}

	// 设置所有的EditText为enable
	private void setAllEditTextEnable(boolean enabled) {
		((EditText) findViewById(R.id.jktj_et_machineNo)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_machineCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_personId)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_healthFileNumber))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_name)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_examinationDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_orgCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_operatorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_operatorName))
				.setEnabled(enabled);
//		((EditText) findViewById(R.id.jktj_et_operateTime)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_responsibleDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_responsibleDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_symptomCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_symptomOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_temperature)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_pulseRate)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_respiratoryRate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_height)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_weight)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_bmi)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_waistline)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_leftSBP)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_leftDBP)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_rightSBP)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_rightDBP)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_theAgedStatus))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_selfCareAbilityCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cognitiveFunction))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cognitiveFunctionScore))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_emotionalState))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_emotionalStateScore))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_exerciseFrequencyCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_everyWorkoutTime))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_insistOnExerciseTime))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_exerciseMode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_eatingHabitsCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_smokingStatusCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dailySmoking))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_smokingAge)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_smokingCessationAge))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_drinkingFrequencyCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dailyDrinking))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_temperanceCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_temperanceAge))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_drinkingAge)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_whetherDrunk))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_alcoholTypeCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_alcoholTypeOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_exposureStateCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_hazardousWork))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_workingTime)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dust)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dustProtective))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dustProtectiveDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_radiogen)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_radiogenProtective))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_radiogenProtectiveDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_physical)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_physicalProtective))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_physicalProtectiveDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_chemistry)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_chemistryProtective))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_chemistryProtectiveDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_otherToxicant))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_otherProtective))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_otherProtectiveDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_lipsCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dentitionCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_missingTeethLeftUp))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_missingTeethRightUp))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_missingTeethLeftDown))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_missingTeethRightDown))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cariesLeftUp))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cariesRightUp))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cariesLeftDown))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cariesRightDown))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dentureLeftUp))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dentureRightUp))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dentureLeftDown))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dentureRightDown))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_throatCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_visionRight)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_visionLeft)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_redressVisionRight))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_redressVisionLeft))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_hearingCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_motorFunctionCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_fundusCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_fundusAbnormal))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_skinCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_skinOther)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_scleraCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_scleraOther)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_lymphNodeCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_lymphNodeOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_barrelChestCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_breathSoundCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_breathSoundOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_raleCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_raleOther)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_heartRate)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_rhythmCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cardiacSouffleCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cardiacSouffleDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenTendernessCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenTendernessDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenMassCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenMassDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenLiverCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenLiverDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenSplenomegalyCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenSplenomegalyDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenShiftingDullnessCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_abdomenShiftingDullnessDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_dorsalisPedisPulseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_analFingerCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_analFingerDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_breastCodes)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_breastDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vulvaCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vulvaDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vaginaCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vaginaDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cervixCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cervixDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_uterusCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_uterusDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_uterineAccessoriesCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_uterineAccessoriesDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_examinationOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_hemoglobin)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_leucocyte)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_platelet)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_bloodOther)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_urineProteinCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_urineSugarCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_urineKetoneCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_urineOccultBloodCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_urineOther)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_fastingPlasmaGlucose1))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_fastingPlasmaGlucose2))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_ECGCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_ECGDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_ECGData)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_urineTraceAlbuminCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_fecalOccultBloodCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_glycatedHemoglobin))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_HBsAgCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_GPT)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_GOT)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_albumin)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_TBIL)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_DBIL)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_serumCreatinine))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_BUN)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_serumPotassiumConcentration))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_serumSodiumConcentration))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_TCHO)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_triglyceride))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_LDL)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_HDL)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_chestXRayCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_chestXRayDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_BScanCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_BScanDesc)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_papSmearCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_papSmearDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_checkOther)).setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_flatAndQualityCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_qiDeficiencyCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_yangXuzhiCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_yinDeficiencyCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_phlegmDampnessQualityCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_hotAndHumidQualityCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_bloodStasisCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_qiStagnationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_specialQualityCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cerebrovascularCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_cerebrovascularDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_kidneyDiseaseCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_kidneyDiseaseDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_heartDiseaseCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_heartDiseaseDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vascularDiseaseCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vascularDiseaseDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_eyeDiseaseCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_eyeDiseaseDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_nerveSystemDiseaseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_nerveSystemDiseaseDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_otherSystemDiseaseCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_otherSystemDiseaseDesc))
				.setEnabled(enabled);
		// 住院史
		// ((EditText) findViewById(R.id.jktj_et_hospitalizedHistory))
		// .setEnabled(enabled);
		// 家庭病床史
		// ((EditText) findViewById(R.id.jktj_et_familyBedHistory))
		// .setEnabled(enabled);
		// 主要用药情况
		// ((EditText) findViewById(R.id.jktj_et_medicationList))
		// .setEnabled(enabled);
		// 非免疫规划预防接种史
		// ((EditText) findViewById(R.id.jktj_et_vaccinationHistory))
		// .setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_healthEvaluationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_diseaseName1))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_diseaseName2))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_diseaseName3))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_diseaseName4))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_healthGuidanceCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_healthGuidanceDesc))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_riskFactorsCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_weightReduction))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_vaccinationName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jktj_et_riskFactorsOther))
				.setEnabled(enabled);
		if (enabled) {
			setChoiceEditText(R.id.jktj_et_symptomCodes,
					R.array.jktj_et_symptomCodes);
			setChoiceEditText(R.id.jktj_et_theAgedStatus,
					R.array.jktj_et_theAgedStatus);
			setChoiceEditText(R.id.jktj_et_selfCareAbilityCode,
					R.array.jktj_et_selfCareAbilityCode);
			setChoiceEditText(R.id.jktj_et_cognitiveFunction,
					R.array.jktj_et_cognitiveFunction);
			setChoiceEditText(R.id.jktj_et_emotionalState,
					R.array.jktj_et_emotionalState);
			setChoiceEditText(R.id.jktj_et_exerciseFrequencyCode,
					R.array.jktj_et_exerciseFrequencyCode);
			setChoiceEditText(R.id.jktj_et_eatingHabitsCodes,
					R.array.jktj_et_eatingHabitsCodes);
			setChoiceEditText(R.id.jktj_et_smokingStatusCode,
					R.array.jktj_et_smokingStatusCode);
			setChoiceEditText(R.id.jktj_et_drinkingFrequencyCode,
					R.array.jktj_et_drinkingFrequencyCode);
			setChoiceEditText(R.id.jktj_et_temperanceCode,
					R.array.jktj_et_temperanceCode);
			setChoiceEditText(R.id.jktj_et_whetherDrunk,
					R.array.jktj_et_whetherDrunk);
			setChoiceEditText(R.id.jktj_et_alcoholTypeCodes,
					R.array.jktj_et_alcoholTypeCodes);
			setChoiceEditText(R.id.jktj_et_exposureStateCode,
					R.array.jktj_et_exposureStateCode);
			setChoiceEditText(R.id.jktj_et_dustProtective,
					R.array.jktj_et_dustProtective);
			setChoiceEditText(R.id.jktj_et_radiogenProtective,
					R.array.jktj_et_radiogenProtective);
			setChoiceEditText(R.id.jktj_et_physicalProtective,
					R.array.jktj_et_physicalProtective);
			setChoiceEditText(R.id.jktj_et_chemistryProtective,
					R.array.jktj_et_chemistryProtective);
			setChoiceEditText(R.id.jktj_et_otherProtective,
					R.array.jktj_et_otherProtective);
			setChoiceEditText(R.id.jktj_et_lipsCode, R.array.jktj_et_lipsCode);
			setChoiceEditText(R.id.jktj_et_dentitionCodes,
					R.array.jktj_et_dentitionCodes);
			setChoiceEditText(R.id.jktj_et_throatCode,
					R.array.jktj_et_throatCode);
			setChoiceEditText(R.id.jktj_et_hearingCode,
					R.array.jktj_et_hearingCode);
			setChoiceEditText(R.id.jktj_et_motorFunctionCode,
					R.array.jktj_et_motorFunctionCode);
			setChoiceEditText(R.id.jktj_et_fundusCode,
					R.array.jktj_et_fundusCode);
			setChoiceEditText(R.id.jktj_et_skinCode, R.array.jktj_et_skinCode);
			setChoiceEditText(R.id.jktj_et_scleraCode,
					R.array.jktj_et_scleraCode);
			setChoiceEditText(R.id.jktj_et_lymphNodeCode,
					R.array.jktj_et_lymphNodeCode);
			setChoiceEditText(R.id.jktj_et_barrelChestCode,
					R.array.jktj_et_barrelChestCode);
			setChoiceEditText(R.id.jktj_et_breathSoundCode,
					R.array.jktj_et_breathSoundCode);
			setChoiceEditText(R.id.jktj_et_raleCode, R.array.jktj_et_raleCode);
			setChoiceEditText(R.id.jktj_et_rhythmCode,
					R.array.jktj_et_rhythmCode);
			setChoiceEditText(R.id.jktj_et_cardiacSouffleCode,
					R.array.jktj_et_cardiacSouffleCode);
			setChoiceEditText(R.id.jktj_et_abdomenTendernessCode,
					R.array.jktj_et_abdomenTendernessCode);
			setChoiceEditText(R.id.jktj_et_abdomenMassCode,
					R.array.jktj_et_abdomenMassCode);
			setChoiceEditText(R.id.jktj_et_abdomenLiverCode,
					R.array.jktj_et_abdomenLiverCode);
			setChoiceEditText(R.id.jktj_et_abdomenSplenomegalyCode,
					R.array.jktj_et_abdomenSplenomegalyCode);
			setChoiceEditText(R.id.jktj_et_abdomenShiftingDullnessCode,
					R.array.jktj_et_abdomenShiftingDullnessCode);
			setChoiceEditText(R.id.jktj_et_legEdemaCode,
					R.array.jktj_et_legEdemaCode);
			setChoiceEditText(R.id.jktj_et_dorsalisPedisPulseCode,
					R.array.jktj_et_dorsalisPedisPulseCode);
			setChoiceEditText(R.id.jktj_et_analFingerCodes,
					R.array.jktj_et_analFingerCodes);
			setChoiceEditText(R.id.jktj_et_breastCodes,
					R.array.jktj_et_breastCodes);
			setChoiceEditText(R.id.jktj_et_vulvaCode, R.array.jktj_et_vulvaCode);
			setChoiceEditText(R.id.jktj_et_vaginaCode,
					R.array.jktj_et_vaginaCode);
			setChoiceEditText(R.id.jktj_et_cervixCode,
					R.array.jktj_et_vcervixCode);
			setChoiceEditText(R.id.jktj_et_uterusCode,
					R.array.jktj_et_uterusCode);
			setChoiceEditText(R.id.jktj_et_uterineAccessoriesCode,
					R.array.jktj_et_uterineAccessoriesCode);
			setChoiceEditText(R.id.jktj_et_urineProteinCode,
					R.array.jktj_et_urineProteinCode);
			setChoiceEditText(R.id.jktj_et_urineSugarCode,
					R.array.jktj_et_urineSugarCode);
			setChoiceEditText(R.id.jktj_et_urineKetoneCode,
					R.array.jktj_et_urineKetoneCode);
			setChoiceEditText(R.id.jktj_et_urineOccultBloodCode,
					R.array.jktj_et_urineOccultBloodCode);
			setChoiceEditText(R.id.jktj_et_ECGCode, R.array.jktj_et_ECGCode);
			setChoiceEditText(R.id.jktj_et_fecalOccultBloodCode,
					R.array.jktj_et_fecalOccultBloodCode);
			setChoiceEditText(R.id.jktj_et_HBsAgCode, R.array.jktj_et_HBsAgCode);
			setChoiceEditText(R.id.jktj_et_chestXRayCode,
					R.array.jktj_et_chestXRayCode);
			setChoiceEditText(R.id.jktj_et_BScanCode, R.array.jktj_et_BScanCode);
			setChoiceEditText(R.id.jktj_et_papSmearCode,
					R.array.jktj_et_papSmearCode);
			setChoiceEditText(R.id.jktj_et_flatAndQualityCode,
					R.array.jktj_et_flatAndQualityCode);
			setChoiceEditText(R.id.jktj_et_qiDeficiencyCode,
					R.array.jktj_et_qiDeficiencyCode);
			setChoiceEditText(R.id.jktj_et_yangXuzhiCode,
					R.array.jktj_et_yangXuzhiCode);
			setChoiceEditText(R.id.jktj_et_yinDeficiencyCode,
					R.array.jktj_et_yinDeficiencyCode);
			setChoiceEditText(R.id.jktj_et_phlegmDampnessQualityCode,
					R.array.jktj_et_phlegmDampnessQualityCode);
			setChoiceEditText(R.id.jktj_et_hotAndHumidQualityCode,
					R.array.jktj_et_hotAndHumidQualityCode);
			setChoiceEditText(R.id.jktj_et_bloodStasisCode,
					R.array.jktj_et_bloodStasisCode);
			setChoiceEditText(R.id.jktj_et_qiStagnationCode,
					R.array.jktj_et_qiStagnationCode);
			setChoiceEditText(R.id.jktj_et_specialQualityCode,
					R.array.jktj_et_specialQualityCode);
			setChoiceEditText(R.id.jktj_et_cerebrovascularCodes,
					R.array.jktj_et_cerebrovascularCodes);
			setChoiceEditText(R.id.jktj_et_kidneyDiseaseCodes,
					R.array.jktj_et_kidneyDiseaseCodes);
			setChoiceEditText(R.id.jktj_et_heartDiseaseCodes,
					R.array.jktj_et_heartDiseaseCodes);
			setChoiceEditText(R.id.jktj_et_vascularDiseaseCodes,
					R.array.jktj_et_vascularDiseaseCodes);
			setChoiceEditText(R.id.jktj_et_eyeDiseaseCodes,
					R.array.jktj_et_eyeDiseaseCodes);
			setChoiceEditText(R.id.jktj_et_nerveSystemDiseaseCode,
					R.array.jktj_et_nerveSystemDiseaseCode);
			setChoiceEditText(R.id.jktj_et_otherSystemDiseaseCode,
					R.array.jktj_et_otherSystemDiseaseCode);
			setChoiceEditText(R.id.jktj_et_healthEvaluationCode,
					R.array.jktj_et_healthEvaluationCode);
			setChoiceEditText(R.id.jktj_et_healthGuidanceCodes,
					R.array.jktj_et_healthGuidanceCodes);
			setChoiceEditText(R.id.jktj_et_riskFactorsCodes,
					R.array.jktj_et_riskFactorsCodes);
		} else {
			setChoiceEditText(R.id.jktj_et_symptomCodes, -1);
			setChoiceEditText(R.id.jktj_et_theAgedStatus, -1);
			setChoiceEditText(R.id.jktj_et_selfCareAbilityCode, -1);
			setChoiceEditText(R.id.jktj_et_cognitiveFunction, -1);
			setChoiceEditText(R.id.jktj_et_emotionalState, -1);
			setChoiceEditText(R.id.jktj_et_exerciseFrequencyCode, -1);
			setChoiceEditText(R.id.jktj_et_eatingHabitsCodes, -1);
			setChoiceEditText(R.id.jktj_et_smokingStatusCode, -1);
			setChoiceEditText(R.id.jktj_et_drinkingFrequencyCode, -1);
			setChoiceEditText(R.id.jktj_et_temperanceCode, -1);
			setChoiceEditText(R.id.jktj_et_whetherDrunk, -1);
			setChoiceEditText(R.id.jktj_et_alcoholTypeCodes, -1);
			setChoiceEditText(R.id.jktj_et_exposureStateCode, -1);
			setChoiceEditText(R.id.jktj_et_dustProtective, -1);
			setChoiceEditText(R.id.jktj_et_radiogenProtective, -1);
			setChoiceEditText(R.id.jktj_et_physicalProtective, -1);
			setChoiceEditText(R.id.jktj_et_chemistryProtective, -1);
			setChoiceEditText(R.id.jktj_et_otherProtective, -1);
			setChoiceEditText(R.id.jktj_et_lipsCode, -1);
			setChoiceEditText(R.id.jktj_et_dentitionCodes, -1);
			setChoiceEditText(R.id.jktj_et_throatCode, -1);
			setChoiceEditText(R.id.jktj_et_hearingCode, -1);
			setChoiceEditText(R.id.jktj_et_motorFunctionCode, -1);
			setChoiceEditText(R.id.jktj_et_fundusCode, -1);
			setChoiceEditText(R.id.jktj_et_skinCode, -1);
			setChoiceEditText(R.id.jktj_et_scleraCode, -1);
			setChoiceEditText(R.id.jktj_et_lymphNodeCode, -1);
			setChoiceEditText(R.id.jktj_et_barrelChestCode, -1);
			setChoiceEditText(R.id.jktj_et_breathSoundCode, -1);
			setChoiceEditText(R.id.jktj_et_raleCode, -1);
			setChoiceEditText(R.id.jktj_et_rhythmCode, -1);
			setChoiceEditText(R.id.jktj_et_cardiacSouffleCode, -1);
			setChoiceEditText(R.id.jktj_et_abdomenTendernessCode, -1);
			setChoiceEditText(R.id.jktj_et_abdomenMassCode, -1);
			setChoiceEditText(R.id.jktj_et_abdomenLiverCode, -1);
			setChoiceEditText(R.id.jktj_et_abdomenSplenomegalyCode, -1);
			setChoiceEditText(R.id.jktj_et_abdomenShiftingDullnessCode, -1);
			setChoiceEditText(R.id.jktj_et_legEdemaCode, -1);
			setChoiceEditText(R.id.jktj_et_dorsalisPedisPulseCode, -1);
			setChoiceEditText(R.id.jktj_et_analFingerCodes, -1);
			setChoiceEditText(R.id.jktj_et_breastCodes, -1);
			setChoiceEditText(R.id.jktj_et_vulvaCode, -1);
			setChoiceEditText(R.id.jktj_et_vaginaCode, -1);
			setChoiceEditText(R.id.jktj_et_cervixCode, -1);
			setChoiceEditText(R.id.jktj_et_uterusCode, -1);
			setChoiceEditText(R.id.jktj_et_uterineAccessoriesCode, -1);
			setChoiceEditText(R.id.jktj_et_urineProteinCode, -1);
			setChoiceEditText(R.id.jktj_et_urineSugarCode, -1);
			setChoiceEditText(R.id.jktj_et_urineKetoneCode, -1);
			setChoiceEditText(R.id.jktj_et_urineOccultBloodCode, -1);
			setChoiceEditText(R.id.jktj_et_ECGCode, R.array.jktj_et_ECGCode);
			setChoiceEditText(R.id.jktj_et_fecalOccultBloodCode, -1);
			setChoiceEditText(R.id.jktj_et_HBsAgCode, R.array.jktj_et_HBsAgCode);
			setChoiceEditText(R.id.jktj_et_chestXRayCode, -1);
			setChoiceEditText(R.id.jktj_et_BScanCode, R.array.jktj_et_BScanCode);
			setChoiceEditText(R.id.jktj_et_papSmearCode, -1);
			setChoiceEditText(R.id.jktj_et_flatAndQualityCode, -1);
			setChoiceEditText(R.id.jktj_et_qiDeficiencyCode, -1);
			setChoiceEditText(R.id.jktj_et_yangXuzhiCode, -1);
			setChoiceEditText(R.id.jktj_et_yinDeficiencyCode, -1);
			setChoiceEditText(R.id.jktj_et_phlegmDampnessQualityCode, -1);
			setChoiceEditText(R.id.jktj_et_hotAndHumidQualityCode, -1);
			setChoiceEditText(R.id.jktj_et_bloodStasisCode, -1);
			setChoiceEditText(R.id.jktj_et_qiStagnationCode, -1);
			setChoiceEditText(R.id.jktj_et_specialQualityCode, -1);
			setChoiceEditText(R.id.jktj_et_cerebrovascularCodes, -1);
			setChoiceEditText(R.id.jktj_et_kidneyDiseaseCodes, -1);
			setChoiceEditText(R.id.jktj_et_heartDiseaseCodes, -1);
			setChoiceEditText(R.id.jktj_et_vascularDiseaseCodes, -1);
			setChoiceEditText(R.id.jktj_et_eyeDiseaseCodes, -1);
			setChoiceEditText(R.id.jktj_et_nerveSystemDiseaseCode, -1);
			setChoiceEditText(R.id.jktj_et_otherSystemDiseaseCode, -1);
			setChoiceEditText(R.id.jktj_et_healthEvaluationCode, -1);
			setChoiceEditText(R.id.jktj_et_healthGuidanceCodes, -1);
			setChoiceEditText(R.id.jktj_et_riskFactorsCodes, -1);
		}
	}

	protected HealthExamination getAllText() {
		if (healthExamination == null) {
			healthExamination = new HealthExamination();
		}
		HealthExamination healthExamination_upload = healthExamination;
		// healthExamination_upload.setMachineNo(((EditText)findViewById(R.id.jktj_et_machineNo)).getText().toString());
		healthExamination_upload
				.setPersonId(((EditText) findViewById(R.id.jktj_et_personId))
						.getText().toString());
		healthExamination_upload
				.setHealthFileNumber(((EditText) findViewById(R.id.jktj_et_healthFileNumber))
						.getText().toString());
		healthExamination_upload
				.setName(((EditText) findViewById(R.id.jktj_et_name)).getText()
						.toString());
		healthExamination_upload
				.setExaminationDate(((EditText) findViewById(R.id.jktj_et_examinationDate))
						.getText().toString());
		healthExamination_upload
				.setMachineCode(((EditText) findViewById(R.id.jktj_et_machineCode))
						.getText().toString());
		healthExamination_upload
				.setMachineNo(((EditText) findViewById(R.id.jktj_et_machineNo))
						.getText().toString());
		healthExamination_upload
				.setOrgCode(((EditText) findViewById(R.id.jktj_et_orgCode))
						.getText().toString());
		healthExamination_upload
				.setOperatorCode(((EditText) findViewById(R.id.jktj_et_operatorCode))
						.getText().toString());
		healthExamination_upload
				.setOperatorName(((EditText) findViewById(R.id.jktj_et_operatorName))
						.getText().toString());
		healthExamination_upload
				.setOperateTime(((EditText) findViewById(R.id.jktj_et_operateTime))
						.getText().toString());
		healthExamination_upload
				.setResponsibleDoctorCode(((EditText) findViewById(R.id.jktj_et_responsibleDoctorCode))
						.getText().toString());
		healthExamination_upload
				.setResponsibleDoctorName(((EditText) findViewById(R.id.jktj_et_responsibleDoctorName))
						.getText().toString());
		healthExamination_upload
				.setSymptomCodes(((EditText) findViewById(R.id.jktj_et_symptomCodes))
						.getText().toString());
		healthExamination_upload
				.setSymptomOther(((EditText) findViewById(R.id.jktj_et_symptomOther))
						.getText().toString());
		healthExamination_upload
				.setTemperature(((EditText) findViewById(R.id.jktj_et_temperature))
						.getText().toString());
		healthExamination_upload
				.setPulseRate(((EditText) findViewById(R.id.jktj_et_pulseRate))
						.getText().toString());
		healthExamination_upload
				.setRespiratoryRate(((EditText) findViewById(R.id.jktj_et_respiratoryRate))
						.getText().toString());
		healthExamination_upload
				.setHeight(((EditText) findViewById(R.id.jktj_et_height))
						.getText().toString());
		healthExamination_upload
				.setWeight(((EditText) findViewById(R.id.jktj_et_weight))
						.getText().toString());
		healthExamination_upload
				.setBmi(((EditText) findViewById(R.id.jktj_et_bmi)).getText()
						.toString());
		healthExamination_upload
				.setWaistline(((EditText) findViewById(R.id.jktj_et_waistline))
						.getText().toString());
		healthExamination_upload
				.setLeftSBP(((EditText) findViewById(R.id.jktj_et_leftSBP))
						.getText().toString());
		healthExamination_upload
				.setLeftDBP(((EditText) findViewById(R.id.jktj_et_leftDBP))
						.getText().toString());
		healthExamination_upload
				.setRightSBP(((EditText) findViewById(R.id.jktj_et_rightSBP))
						.getText().toString());
		healthExamination_upload
				.setRightDBP(((EditText) findViewById(R.id.jktj_et_rightDBP))
						.getText().toString());
		healthExamination_upload
				.setTheAgedStatus(((EditText) findViewById(R.id.jktj_et_theAgedStatus))
						.getText().toString());
		healthExamination_upload
				.setSelfCareAbilityCode(((EditText) findViewById(R.id.jktj_et_selfCareAbilityCode))
						.getText().toString());
		healthExamination_upload
				.setCognitiveFunction(((EditText) findViewById(R.id.jktj_et_cognitiveFunction))
						.getText().toString());
		healthExamination_upload
				.setCognitiveFunctionScore(((EditText) findViewById(R.id.jktj_et_cognitiveFunctionScore))
						.getText().toString());
		healthExamination_upload
				.setEmotionalState(((EditText) findViewById(R.id.jktj_et_emotionalState))
						.getText().toString());
		healthExamination_upload
				.setEmotionalStateScore(((EditText) findViewById(R.id.jktj_et_emotionalStateScore))
						.getText().toString());
		healthExamination_upload
				.setExerciseFrequencyCode(((EditText) findViewById(R.id.jktj_et_exerciseFrequencyCode))
						.getText().toString());
		healthExamination_upload
				.setEveryWorkoutTime(((EditText) findViewById(R.id.jktj_et_everyWorkoutTime))
						.getText().toString());
		healthExamination_upload
				.setInsistOnExerciseTime(((EditText) findViewById(R.id.jktj_et_insistOnExerciseTime))
						.getText().toString());
		healthExamination_upload
				.setExerciseMode(((EditText) findViewById(R.id.jktj_et_exerciseMode))
						.getText().toString());
		healthExamination_upload
				.setEatingHabitsCodes(((EditText) findViewById(R.id.jktj_et_eatingHabitsCodes))
						.getText().toString());
		healthExamination_upload
				.setSmokingStatusCode(((EditText) findViewById(R.id.jktj_et_smokingStatusCode))
						.getText().toString());
		healthExamination_upload
				.setDailySmoking(((EditText) findViewById(R.id.jktj_et_dailySmoking))
						.getText().toString());
		healthExamination_upload
				.setSmokingAge(((EditText) findViewById(R.id.jktj_et_smokingAge))
						.getText().toString());
		healthExamination_upload
				.setSmokingCessationAge(((EditText) findViewById(R.id.jktj_et_smokingCessationAge))
						.getText().toString());
		healthExamination_upload
				.setDrinkingFrequencyCode(((EditText) findViewById(R.id.jktj_et_drinkingFrequencyCode))
						.getText().toString());
		healthExamination_upload
				.setDailyDrinking(((EditText) findViewById(R.id.jktj_et_dailyDrinking))
						.getText().toString());
		healthExamination_upload
				.setTemperanceCode(((EditText) findViewById(R.id.jktj_et_temperanceCode))
						.getText().toString());
		healthExamination_upload
				.setTemperanceAge(((EditText) findViewById(R.id.jktj_et_temperanceAge))
						.getText().toString());
		healthExamination_upload
				.setDrinkingAge(((EditText) findViewById(R.id.jktj_et_drinkingAge))
						.getText().toString());
		healthExamination_upload
				.setWhetherDrunk(((EditText) findViewById(R.id.jktj_et_whetherDrunk))
						.getText().toString());
		healthExamination_upload
				.setAlcoholTypeCodes(((EditText) findViewById(R.id.jktj_et_alcoholTypeCodes))
						.getText().toString());
		healthExamination_upload
				.setAlcoholTypeOther(((EditText) findViewById(R.id.jktj_et_alcoholTypeOther))
						.getText().toString());
		healthExamination_upload
				.setExposureStateCode(((EditText) findViewById(R.id.jktj_et_exposureStateCode))
						.getText().toString());
		healthExamination_upload
				.setHazardousWork(((EditText) findViewById(R.id.jktj_et_hazardousWork))
						.getText().toString());
		healthExamination_upload
				.setWorkingTime(((EditText) findViewById(R.id.jktj_et_workingTime))
						.getText().toString());
		healthExamination_upload
				.setDust(((EditText) findViewById(R.id.jktj_et_dust)).getText()
						.toString());
		healthExamination_upload
				.setDustProtective(((EditText) findViewById(R.id.jktj_et_dustProtective))
						.getText().toString());
		healthExamination_upload
				.setDustProtectiveDesc(((EditText) findViewById(R.id.jktj_et_dustProtectiveDesc))
						.getText().toString());
		healthExamination_upload
				.setRadiogen(((EditText) findViewById(R.id.jktj_et_radiogen))
						.getText().toString());
		healthExamination_upload
				.setRadiogenProtective(((EditText) findViewById(R.id.jktj_et_radiogenProtective))
						.getText().toString());
		healthExamination_upload
				.setRadiogenProtectiveDesc(((EditText) findViewById(R.id.jktj_et_radiogenProtectiveDesc))
						.getText().toString());
		healthExamination_upload
				.setPhysical(((EditText) findViewById(R.id.jktj_et_physical))
						.getText().toString());
		healthExamination_upload
				.setPhysicalProtective(((EditText) findViewById(R.id.jktj_et_physicalProtective))
						.getText().toString());
		healthExamination_upload
				.setPhysicalProtectiveDesc(((EditText) findViewById(R.id.jktj_et_physicalProtectiveDesc))
						.getText().toString());
		healthExamination_upload
				.setChemistry(((EditText) findViewById(R.id.jktj_et_chemistry))
						.getText().toString());
		healthExamination_upload
				.setChemistryProtective(((EditText) findViewById(R.id.jktj_et_chemistryProtective))
						.getText().toString());
		healthExamination_upload
				.setChemistryProtectiveDesc(((EditText) findViewById(R.id.jktj_et_chemistryProtectiveDesc))
						.getText().toString());
		healthExamination_upload
				.setOtherToxicant(((EditText) findViewById(R.id.jktj_et_otherToxicant))
						.getText().toString());
		healthExamination_upload
				.setOtherProtective(((EditText) findViewById(R.id.jktj_et_otherProtective))
						.getText().toString());
		healthExamination_upload
				.setOtherProtectiveDesc(((EditText) findViewById(R.id.jktj_et_otherProtectiveDesc))
						.getText().toString());
		healthExamination_upload
				.setLipsCode(((EditText) findViewById(R.id.jktj_et_lipsCode))
						.getText().toString());
		healthExamination_upload
				.setDentitionCodes(((EditText) findViewById(R.id.jktj_et_dentitionCodes))
						.getText().toString());
		healthExamination_upload
				.setMissingTeethLeftUp(((EditText) findViewById(R.id.jktj_et_missingTeethLeftUp))
						.getText().toString());
		healthExamination_upload
				.setMissingTeethRightUp(((EditText) findViewById(R.id.jktj_et_missingTeethRightUp))
						.getText().toString());
		healthExamination_upload
				.setMissingTeethLeftDown(((EditText) findViewById(R.id.jktj_et_missingTeethLeftDown))
						.getText().toString());
		healthExamination_upload
				.setMissingTeethRightDown(((EditText) findViewById(R.id.jktj_et_missingTeethRightDown))
						.getText().toString());
		healthExamination_upload
				.setCariesLeftUp(((EditText) findViewById(R.id.jktj_et_cariesLeftUp))
						.getText().toString());
		healthExamination_upload
				.setCariesRightUp(((EditText) findViewById(R.id.jktj_et_cariesRightUp))
						.getText().toString());
		healthExamination_upload
				.setCariesLeftDown(((EditText) findViewById(R.id.jktj_et_cariesLeftDown))
						.getText().toString());
		healthExamination_upload
				.setCariesRightDown(((EditText) findViewById(R.id.jktj_et_cariesRightDown))
						.getText().toString());
		healthExamination_upload
				.setDentureLeftUp(((EditText) findViewById(R.id.jktj_et_dentureLeftUp))
						.getText().toString());
		healthExamination_upload
				.setDentureRightUp(((EditText) findViewById(R.id.jktj_et_dentureRightUp))
						.getText().toString());
		healthExamination_upload
				.setDentureLeftDown(((EditText) findViewById(R.id.jktj_et_dentureLeftDown))
						.getText().toString());
		healthExamination_upload
				.setDentureRightDown(((EditText) findViewById(R.id.jktj_et_dentureRightDown))
						.getText().toString());
		healthExamination_upload
				.setThroatCode(((EditText) findViewById(R.id.jktj_et_throatCode))
						.getText().toString());
		healthExamination_upload
				.setVisionRight(((EditText) findViewById(R.id.jktj_et_visionRight))
						.getText().toString());
		healthExamination_upload
				.setVisionLeft(((EditText) findViewById(R.id.jktj_et_visionLeft))
						.getText().toString());
		healthExamination_upload
				.setRedressVisionRight(((EditText) findViewById(R.id.jktj_et_redressVisionRight))
						.getText().toString());
		healthExamination_upload
				.setRedressVisionLeft(((EditText) findViewById(R.id.jktj_et_redressVisionLeft))
						.getText().toString());
		healthExamination_upload
				.setHearingCode(((EditText) findViewById(R.id.jktj_et_hearingCode))
						.getText().toString());
		healthExamination_upload
				.setMotorFunctionCode(((EditText) findViewById(R.id.jktj_et_motorFunctionCode))
						.getText().toString());
		healthExamination_upload
				.setFundusCode(((EditText) findViewById(R.id.jktj_et_fundusCode))
						.getText().toString());
		healthExamination_upload
				.setFundusAbnormal(((EditText) findViewById(R.id.jktj_et_fundusAbnormal))
						.getText().toString());
		healthExamination_upload
				.setSkinCode(((EditText) findViewById(R.id.jktj_et_skinCode))
						.getText().toString());
		healthExamination_upload
				.setSkinOther(((EditText) findViewById(R.id.jktj_et_skinOther))
						.getText().toString());
		healthExamination_upload
				.setScleraCode(((EditText) findViewById(R.id.jktj_et_scleraCode))
						.getText().toString());
		healthExamination_upload
				.setScleraOther(((EditText) findViewById(R.id.jktj_et_scleraOther))
						.getText().toString());
		healthExamination_upload
				.setLymphNodeCode(((EditText) findViewById(R.id.jktj_et_lymphNodeCode))
						.getText().toString());
		healthExamination_upload
				.setLymphNodeOther(((EditText) findViewById(R.id.jktj_et_lymphNodeOther))
						.getText().toString());
		healthExamination_upload
				.setBarrelChestCode(((EditText) findViewById(R.id.jktj_et_barrelChestCode))
						.getText().toString());
		healthExamination_upload
				.setBreathSoundCode(((EditText) findViewById(R.id.jktj_et_breathSoundCode))
						.getText().toString());
		healthExamination_upload
				.setBreathSoundOther(((EditText) findViewById(R.id.jktj_et_breathSoundOther))
						.getText().toString());
		healthExamination_upload
				.setRaleCode(((EditText) findViewById(R.id.jktj_et_raleCode))
						.getText().toString());
		healthExamination_upload
				.setRaleOther(((EditText) findViewById(R.id.jktj_et_raleOther))
						.getText().toString());
		healthExamination_upload
				.setHeartRate(((EditText) findViewById(R.id.jktj_et_heartRate))
						.getText().toString());
		healthExamination_upload
				.setRhythmCode(((EditText) findViewById(R.id.jktj_et_rhythmCode))
						.getText().toString());
		healthExamination_upload
				.setCardiacSouffleCode(((EditText) findViewById(R.id.jktj_et_cardiacSouffleCode))
						.getText().toString());
		healthExamination_upload
				.setCardiacSouffleDesc(((EditText) findViewById(R.id.jktj_et_cardiacSouffleDesc))
						.getText().toString());
		healthExamination_upload
				.setAbdomenTendernessCode(((EditText) findViewById(R.id.jktj_et_abdomenTendernessCode))
						.getText().toString());
		healthExamination_upload
				.setAbdomenTendernessDesc(((EditText) findViewById(R.id.jktj_et_abdomenTendernessDesc))
						.getText().toString());
		healthExamination_upload
				.setAbdomenMassCode(((EditText) findViewById(R.id.jktj_et_abdomenMassCode))
						.getText().toString());
		healthExamination_upload
				.setAbdomenMassDesc(((EditText) findViewById(R.id.jktj_et_abdomenMassDesc))
						.getText().toString());
		healthExamination_upload
				.setAbdomenLiverCode(((EditText) findViewById(R.id.jktj_et_abdomenLiverCode))
						.getText().toString());
		healthExamination_upload
				.setAbdomenLiverDesc(((EditText) findViewById(R.id.jktj_et_abdomenLiverDesc))
						.getText().toString());
		healthExamination_upload
				.setAbdomenSplenomegalyCode(((EditText) findViewById(R.id.jktj_et_abdomenSplenomegalyCode))
						.getText().toString());
		healthExamination_upload
				.setAbdomenSplenomegalyDesc(((EditText) findViewById(R.id.jktj_et_abdomenSplenomegalyDesc))
						.getText().toString());
		healthExamination_upload
				.setAbdomenShiftingDullnessCode(((EditText) findViewById(R.id.jktj_et_abdomenShiftingDullnessCode))
						.getText().toString());
		healthExamination_upload
				.setAbdomenShiftingDullnessDesc(((EditText) findViewById(R.id.jktj_et_abdomenShiftingDullnessDesc))
						.getText().toString());
		healthExamination_upload
				.setDorsalisPedisPulseCode(((EditText) findViewById(R.id.jktj_et_dorsalisPedisPulseCode))
						.getText().toString());
		healthExamination_upload
				.setAnalFingerCodes(((EditText) findViewById(R.id.jktj_et_analFingerCodes))
						.getText().toString());
		healthExamination_upload
				.setAnalFingerDesc(((EditText) findViewById(R.id.jktj_et_analFingerDesc))
						.getText().toString());
		healthExamination_upload
				.setBreastCodes(((EditText) findViewById(R.id.jktj_et_breastCodes))
						.getText().toString());
		healthExamination_upload
				.setBreastDesc(((EditText) findViewById(R.id.jktj_et_breastDesc))
						.getText().toString());
		healthExamination_upload
				.setVulvaCode(((EditText) findViewById(R.id.jktj_et_vulvaCode))
						.getText().toString());
		healthExamination_upload
				.setVulvaDesc(((EditText) findViewById(R.id.jktj_et_vulvaDesc))
						.getText().toString());
		healthExamination_upload
				.setVaginaCode(((EditText) findViewById(R.id.jktj_et_vaginaCode))
						.getText().toString());
		healthExamination_upload
				.setVaginaDesc(((EditText) findViewById(R.id.jktj_et_vaginaDesc))
						.getText().toString());
		healthExamination_upload
				.setCervixCode(((EditText) findViewById(R.id.jktj_et_cervixCode))
						.getText().toString());
		healthExamination_upload
				.setCervixDesc(((EditText) findViewById(R.id.jktj_et_cervixDesc))
						.getText().toString());
		healthExamination_upload
				.setUterusCode(((EditText) findViewById(R.id.jktj_et_uterusCode))
						.getText().toString());
		healthExamination_upload
				.setUterusDesc(((EditText) findViewById(R.id.jktj_et_uterusDesc))
						.getText().toString());
		healthExamination_upload
				.setUterineAccessoriesCode(((EditText) findViewById(R.id.jktj_et_uterineAccessoriesCode))
						.getText().toString());
		healthExamination_upload
				.setUterineAccessoriesDesc(((EditText) findViewById(R.id.jktj_et_uterineAccessoriesDesc))
						.getText().toString());
		healthExamination_upload
				.setExaminationOther(((EditText) findViewById(R.id.jktj_et_examinationOther))
						.getText().toString());
		healthExamination_upload
				.setHemoglobin(((EditText) findViewById(R.id.jktj_et_hemoglobin))
						.getText().toString());
		healthExamination_upload
				.setLeucocyte(((EditText) findViewById(R.id.jktj_et_leucocyte))
						.getText().toString());
		healthExamination_upload
				.setPlatelet(((EditText) findViewById(R.id.jktj_et_platelet))
						.getText().toString());
		healthExamination_upload
				.setBloodOther(((EditText) findViewById(R.id.jktj_et_bloodOther))
						.getText().toString());
		healthExamination_upload
				.setUrineProteinCode(((EditText) findViewById(R.id.jktj_et_urineProteinCode))
						.getText().toString());
		healthExamination_upload
				.setUrineSugarCode(((EditText) findViewById(R.id.jktj_et_urineSugarCode))
						.getText().toString());
		healthExamination_upload
				.setUrineKetoneCode(((EditText) findViewById(R.id.jktj_et_urineKetoneCode))
						.getText().toString());
		healthExamination_upload
				.setUrineOccultBloodCode(((EditText) findViewById(R.id.jktj_et_urineOccultBloodCode))
						.getText().toString());
		healthExamination_upload
				.setUrineOther(((EditText) findViewById(R.id.jktj_et_urineOther))
						.getText().toString());
		healthExamination_upload
				.setFastingPlasmaGlucose1(((EditText) findViewById(R.id.jktj_et_fastingPlasmaGlucose1))
						.getText().toString());
		healthExamination_upload
				.setFastingPlasmaGlucose2(((EditText) findViewById(R.id.jktj_et_fastingPlasmaGlucose2))
						.getText().toString());
		healthExamination_upload
				.setECGCode(((EditText) findViewById(R.id.jktj_et_ECGCode))
						.getText().toString());
		healthExamination_upload
				.setECGDesc(((EditText) findViewById(R.id.jktj_et_ECGDesc))
						.getText().toString());
		// healthExamination_upload
		// .setECGData(((EditText) findViewById(R.id.jktj_et_ECGData))
		// .getText().toString());
		healthExamination_upload.setECGData(mECGData);
		healthExamination_upload
				.setUrineTraceAlbuminCode(((EditText) findViewById(R.id.jktj_et_urineTraceAlbuminCode))
						.getText().toString());
		healthExamination_upload
				.setFecalOccultBloodCode(((EditText) findViewById(R.id.jktj_et_fecalOccultBloodCode))
						.getText().toString());
		healthExamination_upload
				.setGlycatedHemoglobin(((EditText) findViewById(R.id.jktj_et_glycatedHemoglobin))
						.getText().toString());
		healthExamination_upload
				.setHBsAgCode(((EditText) findViewById(R.id.jktj_et_HBsAgCode))
						.getText().toString());
		healthExamination_upload
				.setGPT(((EditText) findViewById(R.id.jktj_et_GPT)).getText()
						.toString());
		healthExamination_upload
				.setGOT(((EditText) findViewById(R.id.jktj_et_GOT)).getText()
						.toString());
		healthExamination_upload
				.setAlbumin(((EditText) findViewById(R.id.jktj_et_albumin))
						.getText().toString());
		healthExamination_upload
				.setTBIL(((EditText) findViewById(R.id.jktj_et_TBIL)).getText()
						.toString());
		healthExamination_upload
				.setDBIL(((EditText) findViewById(R.id.jktj_et_DBIL)).getText()
						.toString());
		healthExamination_upload
				.setSerumCreatinine(((EditText) findViewById(R.id.jktj_et_serumCreatinine))
						.getText().toString());
		healthExamination_upload
				.setBUN(((EditText) findViewById(R.id.jktj_et_BUN)).getText()
						.toString());
		healthExamination_upload
				.setSerumPotassiumConcentration(((EditText) findViewById(R.id.jktj_et_serumPotassiumConcentration))
						.getText().toString());
		healthExamination_upload
				.setSerumSodiumConcentration(((EditText) findViewById(R.id.jktj_et_serumSodiumConcentration))
						.getText().toString());
		healthExamination_upload
				.setTCHO(((EditText) findViewById(R.id.jktj_et_TCHO)).getText()
						.toString());
		healthExamination_upload
				.setTriglyceride(((EditText) findViewById(R.id.jktj_et_triglyceride))
						.getText().toString());
		healthExamination_upload
				.setLDL(((EditText) findViewById(R.id.jktj_et_LDL)).getText()
						.toString());
		healthExamination_upload
				.setHDL(((EditText) findViewById(R.id.jktj_et_HDL)).getText()
						.toString());
		healthExamination_upload
				.setChestXRayCode(((EditText) findViewById(R.id.jktj_et_chestXRayCode))
						.getText().toString());
		healthExamination_upload
				.setChestXRayDesc(((EditText) findViewById(R.id.jktj_et_chestXRayDesc))
						.getText().toString());
		healthExamination_upload
				.setBScanCode(((EditText) findViewById(R.id.jktj_et_BScanCode))
						.getText().toString());
		healthExamination_upload
				.setBScanDesc(((EditText) findViewById(R.id.jktj_et_BScanDesc))
						.getText().toString());
		healthExamination_upload
				.setPapSmearCode(((EditText) findViewById(R.id.jktj_et_papSmearCode))
						.getText().toString());
		healthExamination_upload
				.setPapSmearDesc(((EditText) findViewById(R.id.jktj_et_papSmearDesc))
						.getText().toString());
		healthExamination_upload
				.setCheckOther(((EditText) findViewById(R.id.jktj_et_checkOther))
						.getText().toString());
		healthExamination_upload
				.setFlatAndQualityCode(((EditText) findViewById(R.id.jktj_et_flatAndQualityCode))
						.getText().toString());
		healthExamination_upload
				.setQiDeficiencyCode(((EditText) findViewById(R.id.jktj_et_qiDeficiencyCode))
						.getText().toString());
		healthExamination_upload
				.setYangXuzhiCode(((EditText) findViewById(R.id.jktj_et_yangXuzhiCode))
						.getText().toString());
		healthExamination_upload
				.setYinDeficiencyCode(((EditText) findViewById(R.id.jktj_et_yinDeficiencyCode))
						.getText().toString());
		healthExamination_upload
				.setPhlegmDampnessQualityCode(((EditText) findViewById(R.id.jktj_et_phlegmDampnessQualityCode))
						.getText().toString());
		healthExamination_upload
				.setHotAndHumidQualityCode(((EditText) findViewById(R.id.jktj_et_hotAndHumidQualityCode))
						.getText().toString());
		healthExamination_upload
				.setBloodStasisCode(((EditText) findViewById(R.id.jktj_et_bloodStasisCode))
						.getText().toString());
		healthExamination_upload
				.setQiStagnationCode(((EditText) findViewById(R.id.jktj_et_qiStagnationCode))
						.getText().toString());
		healthExamination_upload
				.setSpecialQualityCode(((EditText) findViewById(R.id.jktj_et_specialQualityCode))
						.getText().toString());
		healthExamination_upload
				.setCerebrovascularCodes(((EditText) findViewById(R.id.jktj_et_cerebrovascularCodes))
						.getText().toString());
		healthExamination_upload
				.setCerebrovascularDesc(((EditText) findViewById(R.id.jktj_et_cerebrovascularDesc))
						.getText().toString());
		healthExamination_upload
				.setKidneyDiseaseCodes(((EditText) findViewById(R.id.jktj_et_kidneyDiseaseCodes))
						.getText().toString());
		healthExamination_upload
				.setKidneyDiseaseDesc(((EditText) findViewById(R.id.jktj_et_kidneyDiseaseDesc))
						.getText().toString());
		healthExamination_upload
				.setHeartDiseaseCodes(((EditText) findViewById(R.id.jktj_et_heartDiseaseCodes))
						.getText().toString());
		healthExamination_upload
				.setHeartDiseaseDesc(((EditText) findViewById(R.id.jktj_et_heartDiseaseDesc))
						.getText().toString());
		healthExamination_upload
				.setVascularDiseaseCodes(((EditText) findViewById(R.id.jktj_et_vascularDiseaseCodes))
						.getText().toString());
		healthExamination_upload
				.setVascularDiseaseDesc(((EditText) findViewById(R.id.jktj_et_vascularDiseaseDesc))
						.getText().toString());
		healthExamination_upload
				.setEyeDiseaseCodes(((EditText) findViewById(R.id.jktj_et_eyeDiseaseCodes))
						.getText().toString());
		healthExamination_upload
				.setEyeDiseaseDesc(((EditText) findViewById(R.id.jktj_et_eyeDiseaseDesc))
						.getText().toString());
		healthExamination_upload
				.setNerveSystemDiseaseCode(((EditText) findViewById(R.id.jktj_et_nerveSystemDiseaseCode))
						.getText().toString());
		healthExamination_upload
				.setNerveSystemDiseaseDesc(((EditText) findViewById(R.id.jktj_et_nerveSystemDiseaseDesc))
						.getText().toString());
		healthExamination_upload
				.setOtherSystemDiseaseCode(((EditText) findViewById(R.id.jktj_et_otherSystemDiseaseCode))
						.getText().toString());
		healthExamination_upload
				.setOtherSystemDiseaseDesc(((EditText) findViewById(R.id.jktj_et_otherSystemDiseaseDesc))
						.getText().toString());
		// 住院历史
		// healthExamination_upload
		// .setHospitalizedHistory(((EditText)
		// findViewById(R.id.jktj_et_hospitalizedHistory))
		// .getText().toString());
		// 家庭病床史
		// healthExamination_upload
		// .setFamilyBedHistory(((EditText)
		// findViewById(R.id.jktj_et_familyBedHistory))
		// .getText().toString());
		// healthExamination_upload
		// .setMedicationList(((EditText)
		// findViewById(R.id.jktj_et_medicationList))
		// .getText().toString());
		// healthExamination_upload
		// .setVaccinationHistory(((EditText)
		// findViewById(R.id.jktj_et_vaccinationHistory))
		// .getText().toString());
		healthExamination_upload
				.setHealthEvaluationCode(((EditText) findViewById(R.id.jktj_et_healthEvaluationCode))
						.getText().toString());
		healthExamination_upload
				.setDiseaseName1(((EditText) findViewById(R.id.jktj_et_diseaseName1))
						.getText().toString());
		healthExamination_upload
				.setDiseaseName2(((EditText) findViewById(R.id.jktj_et_diseaseName2))
						.getText().toString());
		healthExamination_upload
				.setDiseaseName3(((EditText) findViewById(R.id.jktj_et_diseaseName3))
						.getText().toString());
		healthExamination_upload
				.setDiseaseName4(((EditText) findViewById(R.id.jktj_et_diseaseName4))
						.getText().toString());
		healthExamination_upload
				.setHealthGuidanceCodes(((EditText) findViewById(R.id.jktj_et_healthGuidanceCodes))
						.getText().toString());
		healthExamination_upload
				.setHealthGuidanceDesc(((EditText) findViewById(R.id.jktj_et_healthGuidanceDesc))
						.getText().toString());
		healthExamination_upload
				.setRiskFactorsCodes(((EditText) findViewById(R.id.jktj_et_riskFactorsCodes))
						.getText().toString());
		healthExamination_upload
				.setWeightReduction(((EditText) findViewById(R.id.jktj_et_weightReduction))
						.getText().toString());
		healthExamination_upload
				.setVaccinationName(((EditText) findViewById(R.id.jktj_et_vaccinationName))
						.getText().toString());
		healthExamination_upload
				.setRiskFactorsOther(((EditText) findViewById(R.id.jktj_et_riskFactorsOther))
						.getText().toString());
		return healthExamination_upload;

	}

	protected void setAllText() {
		// setTextByid(healthExamination.getId()+"", R.id.jktj_et_id);
		setTextByid(healthExamination.getPersonId(), R.id.jktj_et_personId);
		setTextByid(healthExamination.getHealthFileNumber(),
				R.id.jktj_et_healthFileNumber);
		setTextByid(healthExamination.getName(), R.id.jktj_et_name);
		setTextByid(healthExamination.getExaminationDate(),
				R.id.jktj_et_examinationDate);
		setTextByid(healthExamination.getOrgCode(), R.id.jktj_et_orgCode);
		setTextByid(healthExamination.getOperatorCode(),
				R.id.jktj_et_operatorCode);
		setTextByid(healthExamination.getMachineCode(),
				R.id.jktj_et_machineCode);
		setTextByid(healthExamination.getMachineNo(), R.id.jktj_et_machineNo);
		setTextByid(healthExamination.getOperatorName(),
				R.id.jktj_et_operatorName);
		setTextByid(healthExamination.getOperateTime(),
				R.id.jktj_et_operateTime);
		setTextByid(healthExamination.getResponsibleDoctorCode(),
				R.id.jktj_et_responsibleDoctorCode);
		setTextByid(healthExamination.getResponsibleDoctorName(),
				R.id.jktj_et_responsibleDoctorName);
		setTextByid(healthExamination.getSymptomCodes(),
				R.id.jktj_et_symptomCodes);
		setTextByid(healthExamination.getSymptomOther(),
				R.id.jktj_et_symptomOther);
		setTextByid(healthExamination.getTemperature(),
				R.id.jktj_et_temperature);
		setTextByid(healthExamination.getPulseRate(), R.id.jktj_et_pulseRate);
		setTextByid(healthExamination.getRespiratoryRate(),
				R.id.jktj_et_respiratoryRate);
		setTextByid(healthExamination.getHeight(), R.id.jktj_et_height);
		setTextByid(healthExamination.getWeight(), R.id.jktj_et_weight);
		setTextByid(healthExamination.getBmi(), R.id.jktj_et_bmi);
		setTextByid(healthExamination.getWaistline(), R.id.jktj_et_waistline);
		setTextByid(healthExamination.getLeftSBP(), R.id.jktj_et_leftSBP);
		setTextByid(healthExamination.getLeftDBP(), R.id.jktj_et_leftDBP);
		setTextByid(healthExamination.getRightSBP(), R.id.jktj_et_rightSBP);
		setTextByid(healthExamination.getRightDBP(), R.id.jktj_et_rightDBP);
		setTextByid(healthExamination.getTheAgedStatus(),
				R.id.jktj_et_theAgedStatus);
		setTextByid(healthExamination.getSelfCareAbilityCode(),
				R.id.jktj_et_selfCareAbilityCode);
		setTextByid(healthExamination.getCognitiveFunction(),
				R.id.jktj_et_cognitiveFunction);
		setTextByid(healthExamination.getCognitiveFunctionScore(),
				R.id.jktj_et_cognitiveFunctionScore);
		setTextByid(healthExamination.getEmotionalState(),
				R.id.jktj_et_emotionalState);
		setTextByid(healthExamination.getEmotionalStateScore(),
				R.id.jktj_et_emotionalStateScore);
		setTextByid(healthExamination.getExerciseFrequencyCode(),
				R.id.jktj_et_exerciseFrequencyCode);
		setTextByid(healthExamination.getEveryWorkoutTime(),
				R.id.jktj_et_everyWorkoutTime);
		setTextByid(healthExamination.getInsistOnExerciseTime(),
				R.id.jktj_et_insistOnExerciseTime);
		setTextByid(healthExamination.getExerciseMode(),
				R.id.jktj_et_exerciseMode);
		setTextByid(healthExamination.getEatingHabitsCodes(),
				R.id.jktj_et_eatingHabitsCodes);
		setTextByid(healthExamination.getSmokingStatusCode(),
				R.id.jktj_et_smokingStatusCode);
		setTextByid(healthExamination.getDailySmoking(),
				R.id.jktj_et_dailySmoking);
		setTextByid(healthExamination.getSmokingAge(), R.id.jktj_et_smokingAge);
		setTextByid(healthExamination.getSmokingCessationAge(),
				R.id.jktj_et_smokingCessationAge);
		setTextByid(healthExamination.getDrinkingFrequencyCode(),
				R.id.jktj_et_drinkingFrequencyCode);
		setTextByid(healthExamination.getDailyDrinking(),
				R.id.jktj_et_dailyDrinking);
		setTextByid(healthExamination.getTemperanceCode(),
				R.id.jktj_et_temperanceCode);
		setTextByid(healthExamination.getTemperanceAge(),
				R.id.jktj_et_temperanceAge);
		setTextByid(healthExamination.getDrinkingAge(),
				R.id.jktj_et_drinkingAge);
		setTextByid(healthExamination.getWhetherDrunk(),
				R.id.jktj_et_whetherDrunk);
		setTextByid(healthExamination.getAlcoholTypeCodes(),
				R.id.jktj_et_alcoholTypeCodes);
		setTextByid(healthExamination.getAlcoholTypeOther(),
				R.id.jktj_et_alcoholTypeOther);
		setTextByid(healthExamination.getExposureStateCode(),
				R.id.jktj_et_exposureStateCode);
		setTextByid(healthExamination.getHazardousWork(),
				R.id.jktj_et_hazardousWork);
		setTextByid(healthExamination.getWorkingTime(),
				R.id.jktj_et_workingTime);
		setTextByid(healthExamination.getDust(), R.id.jktj_et_dust);
		setTextByid(healthExamination.getDustProtective(),
				R.id.jktj_et_dustProtective);
		setTextByid(healthExamination.getDustProtectiveDesc(),
				R.id.jktj_et_dustProtectiveDesc);
		setTextByid(healthExamination.getRadiogen(), R.id.jktj_et_radiogen);
		setTextByid(healthExamination.getRadiogenProtective(),
				R.id.jktj_et_radiogenProtective);
		setTextByid(healthExamination.getRadiogenProtectiveDesc(),
				R.id.jktj_et_radiogenProtectiveDesc);
		setTextByid(healthExamination.getPhysical(), R.id.jktj_et_physical);
		setTextByid(healthExamination.getPhysicalProtective(),
				R.id.jktj_et_physicalProtective);
		setTextByid(healthExamination.getPhysicalProtectiveDesc(),
				R.id.jktj_et_physicalProtectiveDesc);
		setTextByid(healthExamination.getChemistry(), R.id.jktj_et_chemistry);
		setTextByid(healthExamination.getChemistryProtective(),
				R.id.jktj_et_chemistryProtective);
		setTextByid(healthExamination.getChemistryProtectiveDesc(),
				R.id.jktj_et_chemistryProtectiveDesc);
		setTextByid(healthExamination.getOtherToxicant(),
				R.id.jktj_et_otherToxicant);
		setTextByid(healthExamination.getOtherProtective(),
				R.id.jktj_et_otherProtective);
		setTextByid(healthExamination.getOtherProtectiveDesc(),
				R.id.jktj_et_otherProtectiveDesc);
		setTextByid(healthExamination.getLipsCode(), R.id.jktj_et_lipsCode);
		setTextByid(healthExamination.getDentitionCodes(),
				R.id.jktj_et_dentitionCodes);
		setTextByid(healthExamination.getMissingTeethLeftUp(),
				R.id.jktj_et_missingTeethLeftUp);
		setTextByid(healthExamination.getMissingTeethRightUp(),
				R.id.jktj_et_missingTeethRightUp);
		setTextByid(healthExamination.getMissingTeethLeftDown(),
				R.id.jktj_et_missingTeethLeftDown);
		setTextByid(healthExamination.getMissingTeethRightDown(),
				R.id.jktj_et_missingTeethRightDown);
		setTextByid(healthExamination.getCariesLeftUp(),
				R.id.jktj_et_cariesLeftUp);
		setTextByid(healthExamination.getCariesRightUp(),
				R.id.jktj_et_cariesRightUp);
		setTextByid(healthExamination.getCariesLeftDown(),
				R.id.jktj_et_cariesLeftDown);
		setTextByid(healthExamination.getCariesRightDown(),
				R.id.jktj_et_cariesRightDown);
		setTextByid(healthExamination.getDentureLeftUp(),
				R.id.jktj_et_dentureLeftUp);
		setTextByid(healthExamination.getDentureRightUp(),
				R.id.jktj_et_dentureRightUp);
		setTextByid(healthExamination.getDentureLeftDown(),
				R.id.jktj_et_dentureLeftDown);
		setTextByid(healthExamination.getDentureRightDown(),
				R.id.jktj_et_dentureRightDown);
		setTextByid(healthExamination.getThroatCode(), R.id.jktj_et_throatCode);
		setTextByid(healthExamination.getVisionRight(),
				R.id.jktj_et_visionRight);
		setTextByid(healthExamination.getVisionLeft(), R.id.jktj_et_visionLeft);
		setTextByid(healthExamination.getRedressVisionRight(),
				R.id.jktj_et_redressVisionRight);
		setTextByid(healthExamination.getRedressVisionLeft(),
				R.id.jktj_et_redressVisionLeft);
		setTextByid(healthExamination.getHearingCode(),
				R.id.jktj_et_hearingCode);
		setTextByid(healthExamination.getMotorFunctionCode(),
				R.id.jktj_et_motorFunctionCode);
		setTextByid(healthExamination.getFundusCode(), R.id.jktj_et_fundusCode);
		setTextByid(healthExamination.getFundusAbnormal(),
				R.id.jktj_et_fundusAbnormal);
		setTextByid(healthExamination.getSkinCode(), R.id.jktj_et_skinCode);
		setTextByid(healthExamination.getSkinOther(), R.id.jktj_et_skinOther);
		setTextByid(healthExamination.getScleraCode(), R.id.jktj_et_scleraCode);
		setTextByid(healthExamination.getScleraOther(),
				R.id.jktj_et_scleraOther);
		setTextByid(healthExamination.getLymphNodeCode(),
				R.id.jktj_et_lymphNodeCode);
		setTextByid(healthExamination.getLymphNodeOther(),
				R.id.jktj_et_lymphNodeOther);
		setTextByid(healthExamination.getBarrelChestCode(),
				R.id.jktj_et_barrelChestCode);
		setTextByid(healthExamination.getBreathSoundCode(),
				R.id.jktj_et_breathSoundCode);
		setTextByid(healthExamination.getBreathSoundOther(),
				R.id.jktj_et_breathSoundOther);
		setTextByid(healthExamination.getRaleCode(), R.id.jktj_et_raleCode);
		setTextByid(healthExamination.getRaleOther(), R.id.jktj_et_raleOther);
		setTextByid(healthExamination.getHeartRate(), R.id.jktj_et_heartRate);
		setTextByid(healthExamination.getRhythmCode(), R.id.jktj_et_rhythmCode);
		setTextByid(healthExamination.getCardiacSouffleCode(),
				R.id.jktj_et_cardiacSouffleCode);
		setTextByid(healthExamination.getCardiacSouffleDesc(),
				R.id.jktj_et_cardiacSouffleDesc);
		setTextByid(healthExamination.getAbdomenTendernessCode(),
				R.id.jktj_et_abdomenTendernessCode);
		setTextByid(healthExamination.getAbdomenTendernessDesc(),
				R.id.jktj_et_abdomenTendernessDesc);
		setTextByid(healthExamination.getAbdomenMassCode(),
				R.id.jktj_et_abdomenMassCode);
		setTextByid(healthExamination.getAbdomenMassDesc(),
				R.id.jktj_et_abdomenMassDesc);
		setTextByid(healthExamination.getAbdomenLiverCode(),
				R.id.jktj_et_abdomenLiverCode);
		setTextByid(healthExamination.getAbdomenLiverDesc(),
				R.id.jktj_et_abdomenLiverDesc);
		setTextByid(healthExamination.getAbdomenSplenomegalyCode(),
				R.id.jktj_et_abdomenSplenomegalyCode);
		setTextByid(healthExamination.getAbdomenSplenomegalyDesc(),
				R.id.jktj_et_abdomenSplenomegalyDesc);
		setTextByid(healthExamination.getAbdomenShiftingDullnessCode(),
				R.id.jktj_et_abdomenShiftingDullnessCode);
		setTextByid(healthExamination.getAbdomenShiftingDullnessDesc(),
				R.id.jktj_et_abdomenShiftingDullnessDesc);
		setTextByid(healthExamination.getDorsalisPedisPulseCode(),
				R.id.jktj_et_dorsalisPedisPulseCode);
		setTextByid(healthExamination.getAnalFingerCodes(),
				R.id.jktj_et_analFingerCodes);
		setTextByid(healthExamination.getAnalFingerDesc(),
				R.id.jktj_et_analFingerDesc);
		setTextByid(healthExamination.getBreastCodes(),
				R.id.jktj_et_breastCodes);
		setTextByid(healthExamination.getBreastDesc(), R.id.jktj_et_breastDesc);
		setTextByid(healthExamination.getVulvaCode(), R.id.jktj_et_vulvaCode);
		setTextByid(healthExamination.getVulvaDesc(), R.id.jktj_et_vulvaDesc);
		setTextByid(healthExamination.getVaginaCode(), R.id.jktj_et_vaginaCode);
		setTextByid(healthExamination.getVaginaDesc(), R.id.jktj_et_vaginaDesc);
		setTextByid(healthExamination.getCervixCode(), R.id.jktj_et_cervixCode);
		setTextByid(healthExamination.getCervixDesc(), R.id.jktj_et_cervixDesc);
		setTextByid(healthExamination.getUterusCode(), R.id.jktj_et_uterusCode);
		setTextByid(healthExamination.getUterusDesc(), R.id.jktj_et_uterusDesc);
		setTextByid(healthExamination.getUterineAccessoriesCode(),
				R.id.jktj_et_uterineAccessoriesCode);
		setTextByid(healthExamination.getUterineAccessoriesDesc(),
				R.id.jktj_et_uterineAccessoriesDesc);
		setTextByid(healthExamination.getExaminationOther(),
				R.id.jktj_et_examinationOther);
		setTextByid(healthExamination.getHemoglobin(), R.id.jktj_et_hemoglobin);
		setTextByid(healthExamination.getLeucocyte(), R.id.jktj_et_leucocyte);
		setTextByid(healthExamination.getPlatelet(), R.id.jktj_et_platelet);
		setTextByid(healthExamination.getBloodOther(), R.id.jktj_et_bloodOther);
		setTextByid(healthExamination.getUrineProteinCode(),
				R.id.jktj_et_urineProteinCode);
		setTextByid(healthExamination.getUrineSugarCode(),
				R.id.jktj_et_urineSugarCode);
		setTextByid(healthExamination.getUrineKetoneCode(),
				R.id.jktj_et_urineKetoneCode);
		setTextByid(healthExamination.getUrineOccultBloodCode(),
				R.id.jktj_et_urineOccultBloodCode);
		setTextByid(healthExamination.getUrineOther(), R.id.jktj_et_urineOther);
		setTextByid(healthExamination.getFastingPlasmaGlucose1(),
				R.id.jktj_et_fastingPlasmaGlucose1);
		setTextByid(healthExamination.getFastingPlasmaGlucose2(),
				R.id.jktj_et_fastingPlasmaGlucose2);
		setTextByid(healthExamination.getECGCode(), R.id.jktj_et_ECGCode);
		setTextByid(healthExamination.getECGDesc(), R.id.jktj_et_ECGDesc);
		setTextByid(healthExamination.getECGData(), R.id.jktj_et_ECGData);
		setTextByid(healthExamination.getUrineTraceAlbuminCode(),
				R.id.jktj_et_urineTraceAlbuminCode);
		setTextByid(healthExamination.getFecalOccultBloodCode(),
				R.id.jktj_et_fecalOccultBloodCode);
		setTextByid(healthExamination.getGlycatedHemoglobin(),
				R.id.jktj_et_glycatedHemoglobin);
		setTextByid(healthExamination.getHBsAgCode(), R.id.jktj_et_HBsAgCode);
		setTextByid(healthExamination.getGPT(), R.id.jktj_et_GPT);
		setTextByid(healthExamination.getGOT(), R.id.jktj_et_GOT);
		setTextByid(healthExamination.getAlbumin(), R.id.jktj_et_albumin);
		setTextByid(healthExamination.getTBIL(), R.id.jktj_et_TBIL);
		setTextByid(healthExamination.getDBIL(), R.id.jktj_et_DBIL);
		setTextByid(healthExamination.getSerumCreatinine(),
				R.id.jktj_et_serumCreatinine);
		setTextByid(healthExamination.getBUN(), R.id.jktj_et_BUN);
		setTextByid(healthExamination.getSerumPotassiumConcentration(),
				R.id.jktj_et_serumPotassiumConcentration);
		setTextByid(healthExamination.getSerumSodiumConcentration(),
				R.id.jktj_et_serumSodiumConcentration);
		setTextByid(healthExamination.getTCHO(), R.id.jktj_et_TCHO);
		setTextByid(healthExamination.getTriglyceride(),
				R.id.jktj_et_triglyceride);
		setTextByid(healthExamination.getLDL(), R.id.jktj_et_LDL);
		setTextByid(healthExamination.getHDL(), R.id.jktj_et_HDL);
		setTextByid(healthExamination.getChestXRayCode(),
				R.id.jktj_et_chestXRayCode);
		setTextByid(healthExamination.getChestXRayDesc(),
				R.id.jktj_et_chestXRayDesc);
		setTextByid(healthExamination.getBScanCode(), R.id.jktj_et_BScanCode);
		setTextByid(healthExamination.getBScanDesc(), R.id.jktj_et_BScanDesc);
		setTextByid(healthExamination.getPapSmearCode(),
				R.id.jktj_et_papSmearCode);
		setTextByid(healthExamination.getPapSmearDesc(),
				R.id.jktj_et_papSmearDesc);
		setTextByid(healthExamination.getCheckOther(), R.id.jktj_et_checkOther);
		setTextByid(healthExamination.getFlatAndQualityCode(),
				R.id.jktj_et_flatAndQualityCode);
		setTextByid(healthExamination.getQiDeficiencyCode(),
				R.id.jktj_et_qiDeficiencyCode);
		setTextByid(healthExamination.getYangXuzhiCode(),
				R.id.jktj_et_yangXuzhiCode);
		setTextByid(healthExamination.getYinDeficiencyCode(),
				R.id.jktj_et_yinDeficiencyCode);
		setTextByid(healthExamination.getPhlegmDampnessQualityCode(),
				R.id.jktj_et_phlegmDampnessQualityCode);
		setTextByid(healthExamination.getHotAndHumidQualityCode(),
				R.id.jktj_et_hotAndHumidQualityCode);
		setTextByid(healthExamination.getBloodStasisCode(),
				R.id.jktj_et_bloodStasisCode);
		setTextByid(healthExamination.getQiStagnationCode(),
				R.id.jktj_et_qiStagnationCode);
		setTextByid(healthExamination.getSpecialQualityCode(),
				R.id.jktj_et_specialQualityCode);
		setTextByid(healthExamination.getCerebrovascularCodes(),
				R.id.jktj_et_cerebrovascularCodes);
		setTextByid(healthExamination.getCerebrovascularDesc(),
				R.id.jktj_et_cerebrovascularDesc);
		setTextByid(healthExamination.getKidneyDiseaseCodes(),
				R.id.jktj_et_kidneyDiseaseCodes);
		setTextByid(healthExamination.getKidneyDiseaseDesc(),
				R.id.jktj_et_kidneyDiseaseDesc);
		setTextByid(healthExamination.getHeartDiseaseCodes(),
				R.id.jktj_et_heartDiseaseCodes);
		setTextByid(healthExamination.getHeartDiseaseDesc(),
				R.id.jktj_et_heartDiseaseDesc);
		setTextByid(healthExamination.getVascularDiseaseCodes(),
				R.id.jktj_et_vascularDiseaseCodes);
		setTextByid(healthExamination.getVascularDiseaseDesc(),
				R.id.jktj_et_vascularDiseaseDesc);
		setTextByid(healthExamination.getEyeDiseaseCodes(),
				R.id.jktj_et_eyeDiseaseCodes);
		setTextByid(healthExamination.getEyeDiseaseDesc(),
				R.id.jktj_et_eyeDiseaseDesc);
		setTextByid(healthExamination.getNerveSystemDiseaseCode(),
				R.id.jktj_et_nerveSystemDiseaseCode);
		setTextByid(healthExamination.getNerveSystemDiseaseDesc(),
				R.id.jktj_et_nerveSystemDiseaseDesc);
		setTextByid(healthExamination.getOtherSystemDiseaseCode(),
				R.id.jktj_et_otherSystemDiseaseCode);
		setTextByid(healthExamination.getOtherSystemDiseaseDesc(),
				R.id.jktj_et_otherSystemDiseaseDesc);
		setTextByid(healthExamination.getHospitalizedHistory(),
				R.id.jktj_et_hospitalizedHistory);
		setTextByid(healthExamination.getFamilyBedHistory(),
				R.id.jktj_et_familyBedHistory);
		setTextByid(healthExamination.getMedicationList(),
				R.id.jktj_et_medicationList);
		setTextByid(healthExamination.getVaccinationHistory(),
				R.id.jktj_et_vaccinationHistory);
		setTextByid(healthExamination.getHealthEvaluationCode(),
				R.id.jktj_et_healthEvaluationCode);
		setTextByid(healthExamination.getDiseaseName1(),
				R.id.jktj_et_diseaseName1);
		setTextByid(healthExamination.getDiseaseName2(),
				R.id.jktj_et_diseaseName2);
		setTextByid(healthExamination.getDiseaseName3(),
				R.id.jktj_et_diseaseName3);
		setTextByid(healthExamination.getDiseaseName4(),
				R.id.jktj_et_diseaseName4);
		setTextByid(healthExamination.getHealthGuidanceCodes(),
				R.id.jktj_et_healthGuidanceCodes);
		setTextByid(healthExamination.getHealthGuidanceDesc(),
				R.id.jktj_et_healthGuidanceDesc);
		setTextByid(healthExamination.getRiskFactorsCodes(),
				R.id.jktj_et_riskFactorsCodes);
		setTextByid(healthExamination.getWeightReduction(),
				R.id.jktj_et_weightReduction);
		setTextByid(healthExamination.getVaccinationName(),
				R.id.jktj_et_vaccinationName);
		setTextByid(healthExamination.getRiskFactorsOther(),
				R.id.jktj_et_riskFactorsOther);

		// //////////////////////////////////曾德森10.6
		setTextByid(Basesence.getorgName(), R.id.et_jgdm02);
		setTextByid(Basesence.getorgCode(), R.id.jktj_et_orgCode);
		setTextByid(Basesence.getUser().getUserName(),
				R.id.jktj_et_operatorName);
		setTextByid(Basesence.getUser().getUserCode(),
				R.id.jktj_et_operatorCode);
		// //////////////////
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if ((!mHeightEditText.getText().toString().equals(""))
				&& (!mWeightEditText.getText().toString().equals(""))) {
			try {
				mTZZSEditText.setText(calculateTZZS(
						Float.valueOf(mHeightEditText.getText().toString()),
						Float.valueOf(mWeightEditText.getText().toString())));
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(this, "身高或体重非法输入，请检查", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	private String calculateTZZS(float height, float weight) {
		DecimalFormat df = new DecimalFormat("#.0");
		return df.format(weight / (height * height) * 10000);
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
			Toast.makeText(JKTJActivity.this, "尿液分析数据错误", Toast.LENGTH_LONG)
					.show();
			return "6";
		}
	}
}
