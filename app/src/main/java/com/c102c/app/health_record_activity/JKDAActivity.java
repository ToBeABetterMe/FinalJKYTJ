package com.c102c.app.health_record_activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.activity.NewInitalActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.utils.CustomDialog;
import com.c102c.app.utils.FourColmunAdapter;
import com.c102c.app.utils.PastHistory;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ReadIDCardActivity;

//@SuppressLint("HandlerLeak")
public class JKDAActivity extends BaseActivity {
	private HealthRecord_down healthRecord_down;
	private Button edit;
	private Button save;
	private Button shuaka;
	private Button addJWSButton;
	private boolean addflag = false;
	private boolean tagEdit = false;
	private Button update;
	private List<String[]> datas = null;
	private FourColmunAdapter mAdapter;

	private ListView mJWSListView;

	private boolean localHistoryflag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent(); 
		Bundle bundle = intent.getExtras();
		setContentView(R.layout.jkda);
		if (bundle != null) {
			healthRecord_down = new HealthRecord_down();
			if (bundle.getString("extra") != null) {
				if (bundle.getString("extra").equals("adduser")) {
					// AppDB.get(object);

					SimpleDateFormat tmpDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					((EditText) findViewById(R.id.jkda_et_registerDate))
							.setText(tmpDateFormat.format(new Date()));
					setAllEditTextEnable(true);

					addflag = true;
					if (bundle.getString("from") != null) {
						if (bundle.getString("from").equals("localHistory")) {
							localHistoryflag = true;
							// 通过类的主键来从数据库寻找类，并添加到表中
							String primaryKey = Tools.getPrimaryKey(bundle
									.getString("uuid"));
							healthRecord_down.setPersonId(primaryKey);
							List<HealthRecord_down> data = (List<HealthRecord_down>) AppDB
									.query(healthRecord_down);
							if (data.size() > 0) {
								healthRecord_down = data.get(0);
							}
						}
						setAllEditTextEnable(true);
						setAllText();
					}
				}
			}
		} else
			healthRecord_down = Basesence.getTempHealthRecord_down();

		super.onCreate(savedInstanceState);
		// debug();
	}

	public void debug() {
		AppDB db = AppDB.getInstance(JKDAActivity.this);
		if (db.testWithJWS() != null) {
			// system.out.println(db.testWithJWS().getName());
		} else {
			// system.out.println("都没有既往史");
		}
	}

	@Override
	protected void initViews() {

		edit = (Button) findViewById(R.id.edit);
		save = (Button) findViewById(R.id.save);
		addJWSButton = (Button) findViewById(R.id.add_jwsButton);
		shuaka = (Button) findViewById(R.id.shuaka);
		update = (Button) findViewById(R.id.update);
		mJWSListView = (ListView) findViewById(R.id.jws_listView);

		loadJWSList(Tools
				.getPastHistory(healthRecord_down.getPastHistoryList()));
		edit.setOnClickListener(clicklistener);
		save.setOnClickListener(clicklistener);
		shuaka.setOnClickListener(clicklistener);
		update.setOnClickListener(clicklistener);
		addJWSButton.setOnClickListener(clicklistener);
		if (addflag) {
			update.setVisibility(0);
			save.setVisibility(4);
			edit.setVisibility(4);
			shuaka.setVisibility(View.VISIBLE);
			clicklistener.onClick(edit);
			tagEdit = true;
		}
		if (localHistoryflag) {
			shuaka.setVisibility(View.INVISIBLE);
		}
	}

	private OnClickListener clicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == edit) {
				setAllEditTextEnable(true);
				tagEdit = true;
				addJWSButton.setVisibility(View.VISIBLE);
			} else if (v == save) {
				tagEdit = false;

				String a = Util.setHealthRecord_Upload(getAnEntity());
				Log.e("xxx",a);
				Fetch_by_li.communicate("M0100020202", JKDAActivity.this,
						handler, getAnEntity());
			} else if (v == shuaka) {
				Intent intent = new Intent(JKDAActivity.this,
						ReadIDCardActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("extra", "jkda");
				intent.putExtras(bundle);
				startActivityForResult(intent, 0x86);
			} else if (v == update) {
				tagEdit = false;

				String a = Util.setHealthRecord_Upload(getAnEntity());

				Log.e("xxx",a);
				Fetch_by_li.communicate("M0100020201", JKDAActivity.this,
						handler, getAnEntity());
			} else if (v == addJWSButton) {
				// system.out.println("点击添加");
				CustomDialog.YYSM_Add(JKDAActivity.this, mAdapter)
						.notifyDataSetChanged();
			}

		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 0x86 && resultCode == 0x86) {
			Bundle bundle = data.getExtras();
			healthRecord_down.setIdCard(bundle.getString("IDNo"));
			healthRecord_down.setHealthFileNumber(bundle.getString("IDNo"));
			healthRecord_down.setName(bundle.getString("name"));
			healthRecord_down.setGenderCode(bundle.getString("sex"));
			String tmp = bundle.getString("nation");
			if (tmp.equals("01")) {
				healthRecord_down.setEthnicityCode("1");
			} else {
				healthRecord_down.setEthnicityCode("2");
			}
			String[] tmp2 = getResources().getStringArray(R.array.nation);
			healthRecord_down.setEthnicityName(tmp2[Integer.valueOf(tmp) - 1]);
			healthRecord_down.setBirthday(bundle.getString("birthday"));
			healthRecord_down.setNowLiveAddr(bundle.getString("address"));
			setAllText();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private HealthRecord_down getAnEntity() {
		// TODO Auto-generated method stub
		HealthRecord_down m = getAllText();
		List<PastHistory> list = new ArrayList<PastHistory>();
		for (String[] str : mAdapter.getListItem()) {
			list.add(new PastHistory(str));
//			Log.e("exp",str);
		}
		m.setPastHistoryList(Tools.setPastHistory(list));
		return m;
	}

	private Handler handler = Basesence.getupdatehandler(JKDAActivity.this);

	// private Handler handler = new Handler() {
	// public void handleMessage(Message msg) {
	// if (msg.what == Basesence.commusucc) {
	// Toast.makeText(JKDAActivity.this, "接口调用成功", Toast.LENGTH_SHORT)
	// .show();
	// } else
	// Toast.makeText(JKDAActivity.this, "接口调用失败" + (String) msg.obj,
	// Toast.LENGTH_SHORT).show();
	//
	// }
	// };

	private void setAllEditTextEnable(boolean enabled) {
		((EditText) findViewById(R.id.jkda_et_personId)).setEnabled(enabled);
		// ((EditText)
		// findViewById(R.id.jkda_et_machineCode)).setEnabled(enabled);
		// ((EditText)
		// findViewById(R.id.jkda_et_machineNo)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_nowLiveCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_nowLiveName)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_nowLiveAddr)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_birthday)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_householdRegisterCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_householdRegisterName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_householdRegisterAddr))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_registerOrgCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_registerOrgName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_responsibleDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_responsibleDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_registerDoctorCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_registerDoctorName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_registerDate))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_healthFileNumber))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_name)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_genderCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_birthday)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_idCard)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_workUnit)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_phone)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_contacts)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_contactsPhone))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_residentType))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_ethnicityCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_ethnicityName))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_bloodGroupCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_rhBloodGroupCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_eduBGCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_occupationCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_maritalStatusCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_paymentMethodCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_paymentMethodOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_drugAllergyHistoryCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_drugAllergyHistoryOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_exposureHistoryCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_familyHistoryFatherCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_familyHistoryFatherOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_familyHistoryMatherCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_familyHistoryMatherOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_brotherAndSisterCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_brotherAndSisterOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_familyHistoryChildrenCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_familyHistoryChildrenOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_geneticHistoryCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_geneticHistoryOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_disabilityCodes))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_disabilityOther))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_kitchenExhaustCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_fuelTypeCode))
				.setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_waterCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_toiletCode)).setEnabled(enabled);
		((EditText) findViewById(R.id.jkda_et_livestockColumnCode))
				.setEnabled(enabled);
		// 这个是既往史列表，需要添加
		// ((EditText)findViewById(R.id.jkda_et_pastHistoryList)).setEnabled(enabled);

		// com.health.util.SingleChoiceEditText
		// com.health.util.MultiChoiceEditText
		setChoiceEditText(R.id.jkda_et_genderCode, R.array.jkda_et_genderCode);
		setChoiceEditText(R.id.jkda_et_residentType,
				R.array.jkda_et_residentType);
		setChoiceEditText(R.id.jkda_et_ethnicityCode,
				R.array.jkda_et_ethnicityCode);
		setChoiceEditText(R.id.jkda_et_bloodGroupCode,
				R.array.jkda_et_bloodGroupCode);
		setChoiceEditText(R.id.jkda_et_rhBloodGroupCode,
				R.array.jkda_et_rhBloodGroupCode);
		setChoiceEditText(R.id.jkda_et_eduBGCode, R.array.jkda_et_eduBGCode);
		setChoiceEditText(R.id.jkda_et_occupationCode,
				R.array.jkda_et_occupationCode);
		setChoiceEditText(R.id.jkda_et_maritalStatusCode,
				R.array.jkda_et_maritalStatusCode);
		setChoiceEditText(R.id.jkda_et_paymentMethodCodes,
				R.array.jkda_et_paymentMethodCodes);
		setChoiceEditText(R.id.jkda_et_drugAllergyHistoryCodes,
				R.array.jkda_et_drugAllergyHistoryCodes);
		setChoiceEditText(R.id.jkda_et_exposureHistoryCodes,
				R.array.jkda_et_exposureHistoryCodes);
		setChoiceEditText(R.id.jkda_et_familyHistoryFatherCodes,
				R.array.jkda_et_familyHistoryFatherCodes);
		setChoiceEditText(R.id.jkda_et_familyHistoryMatherCodes,
				R.array.jkda_et_familyHistoryMatherCodes);
		setChoiceEditText(R.id.jkda_et_brotherAndSisterCodes,
				R.array.jkda_et_brotherAndSisterCodes);
		setChoiceEditText(R.id.jkda_et_familyHistoryChildrenCodes,
				R.array.jkda_et_familyHistoryChildrenCodes);
		setChoiceEditText(R.id.jkda_et_geneticHistoryCode,
				R.array.jkda_et_geneticHistoryCode);
		setChoiceEditText(R.id.jkda_et_disabilityCodes,
				R.array.jkda_et_disabilityCodes);
		setChoiceEditText(R.id.jkda_et_kitchenExhaustCode,
				R.array.jkda_et_kitchenExhaustCode);
		setChoiceEditText(R.id.jkda_et_fuelTypeCode,
				R.array.jkda_et_fuelTypeCode);
		setChoiceEditText(R.id.jkda_et_waterCode, R.array.jkda_et_waterCode);
		setChoiceEditText(R.id.jkda_et_toiletCode, R.array.jkda_et_toiletCode);
		setChoiceEditText(R.id.jkda_et_livestockColumnCode,
				R.array.jkda_et_livestockColumnCode);
	}

	@Override
	protected HealthRecord_down getAllText() {
		HealthRecord_down healthRecord_update = new HealthRecord_down();
		if (healthRecord_down != null) {
			healthRecord_update = healthRecord_down;
		}
		healthRecord_update
				.setPersonId(((EditText) findViewById(R.id.jkda_et_personId))
						.getText().toString());

		// 这两个字段是update新增的
		// healthRecord_update
		// .setMachineCode(((EditText)
		// findViewById(R.id.jkda_et_machineCode))
		// .getText().toString());
		// healthRecord_update
		// .setMachineNo(((EditText) findViewById(R.id.jkda_et_machineNo))
		// .getText().toString());
		healthRecord_update
				.setNowLiveCode(((EditText) findViewById(R.id.jkda_et_nowLiveCode))
						.getText().toString());
		healthRecord_update
				.setNowLiveName(((EditText) findViewById(R.id.jkda_et_nowLiveName))
						.getText().toString());
		healthRecord_update
				.setNowLiveAddr(((EditText) findViewById(R.id.jkda_et_nowLiveAddr))
						.getText().toString());
		healthRecord_update
				.setHouseholdRegisterCode(((EditText) findViewById(R.id.jkda_et_householdRegisterCode))
						.getText().toString());
		healthRecord_update
				.setHouseholdRegisterName(((EditText) findViewById(R.id.jkda_et_householdRegisterName))
						.getText().toString());
		healthRecord_update
				.setHouseholdRegisterAddr(((EditText) findViewById(R.id.jkda_et_householdRegisterAddr))
						.getText().toString());
		healthRecord_update
				.setRegisterOrgCode(((EditText) findViewById(R.id.jkda_et_registerOrgCode))
						.getText().toString());
		healthRecord_update
				.setRegisterOrgName(((EditText) findViewById(R.id.jkda_et_registerOrgName))
						.getText().toString());
		healthRecord_update
				.setResponsibleDoctorCode(((EditText) findViewById(R.id.jkda_et_responsibleDoctorCode))
						.getText().toString());
		healthRecord_update
				.setResponsibleDoctorName(((EditText) findViewById(R.id.jkda_et_responsibleDoctorName))
						.getText().toString());
		healthRecord_update
				.setRegisterDoctorCode(((EditText) findViewById(R.id.jkda_et_registerDoctorCode))
						.getText().toString());
		healthRecord_update
				.setRegisterDoctorName(((EditText) findViewById(R.id.jkda_et_registerDoctorName))
						.getText().toString());
		healthRecord_update
				.setRegisterDate(((EditText) findViewById(R.id.jkda_et_registerDate))
						.getText().toString());
		healthRecord_update
				.setHealthFileNumber(((EditText) findViewById(R.id.jkda_et_healthFileNumber))
						.getText().toString());
		healthRecord_update
				.setName(((EditText) findViewById(R.id.jkda_et_name)).getText()
						.toString());
		healthRecord_update
				.setGenderCode(((EditText) findViewById(R.id.jkda_et_genderCode))
						.getText().toString());
		healthRecord_update
				.setBirthday(((EditText) findViewById(R.id.jkda_et_birthday))
						.getText().toString());
		healthRecord_update
				.setIdCard(((EditText) findViewById(R.id.jkda_et_idCard))
						.getText().toString());
		healthRecord_update
				.setWorkUnit(((EditText) findViewById(R.id.jkda_et_workUnit))
						.getText().toString());
		healthRecord_update
				.setPhone(((EditText) findViewById(R.id.jkda_et_phone))
						.getText().toString());
		healthRecord_update
				.setContacts(((EditText) findViewById(R.id.jkda_et_contacts))
						.getText().toString());
		healthRecord_update
				.setContactsPhone(((EditText) findViewById(R.id.jkda_et_contactsPhone))
						.getText().toString());
		healthRecord_update
				.setResidentType(((EditText) findViewById(R.id.jkda_et_residentType))
						.getText().toString());
		healthRecord_update
				.setEthnicityCode(((EditText) findViewById(R.id.jkda_et_ethnicityCode))
						.getText().toString());
		healthRecord_update
				.setEthnicityName(((EditText) findViewById(R.id.jkda_et_ethnicityName))
						.getText().toString());
		healthRecord_update
				.setBloodGroupCode(((EditText) findViewById(R.id.jkda_et_bloodGroupCode))
						.getText().toString());
		healthRecord_update
				.setRhBloodGroupCode(((EditText) findViewById(R.id.jkda_et_rhBloodGroupCode))
						.getText().toString());
		healthRecord_update
				.setEduBGCode(((EditText) findViewById(R.id.jkda_et_eduBGCode))
						.getText().toString());
		healthRecord_update
				.setOccupationCode(((EditText) findViewById(R.id.jkda_et_occupationCode))
						.getText().toString());
		healthRecord_update
				.setMaritalStatusCode(((EditText) findViewById(R.id.jkda_et_maritalStatusCode))
						.getText().toString());
		healthRecord_update
				.setPaymentMethodCodes(((EditText) findViewById(R.id.jkda_et_paymentMethodCodes))
						.getText().toString());
		healthRecord_update
				.setPaymentMethodOther(((EditText) findViewById(R.id.jkda_et_paymentMethodOther))
						.getText().toString());
		healthRecord_update
				.setDrugAllergyHistoryCodes(((EditText) findViewById(R.id.jkda_et_drugAllergyHistoryCodes))
						.getText().toString());
		healthRecord_update
				.setDrugAllergyHistoryOther(((EditText) findViewById(R.id.jkda_et_drugAllergyHistoryOther))
						.getText().toString());
		healthRecord_update
				.setExposureHistoryCodes(((EditText) findViewById(R.id.jkda_et_exposureHistoryCodes))
						.getText().toString());
		healthRecord_update
				.setFamilyHistoryFatherCodes(((EditText) findViewById(R.id.jkda_et_familyHistoryFatherCodes))
						.getText().toString());
		healthRecord_update
				.setFamilyHistoryFatherOther(((EditText) findViewById(R.id.jkda_et_familyHistoryFatherOther))
						.getText().toString());
		healthRecord_update
				.setFamilyHistoryMatherCodes(((EditText) findViewById(R.id.jkda_et_familyHistoryMatherCodes))
						.getText().toString());
		healthRecord_update
				.setFamilyHistoryMatherOther(((EditText) findViewById(R.id.jkda_et_familyHistoryMatherOther))
						.getText().toString());
		healthRecord_update
				.setBrotherAndSisterCodes(((EditText) findViewById(R.id.jkda_et_brotherAndSisterCodes))
						.getText().toString());
		healthRecord_update
				.setBrotherAndSisterOther(((EditText) findViewById(R.id.jkda_et_brotherAndSisterOther))
						.getText().toString());
		healthRecord_update
				.setFamilyHistoryChildrenCodes(((EditText) findViewById(R.id.jkda_et_familyHistoryChildrenCodes))
						.getText().toString());
		healthRecord_update
				.setFamilyHistoryChildrenOther(((EditText) findViewById(R.id.jkda_et_familyHistoryChildrenOther))
						.getText().toString());
		healthRecord_update
				.setGeneticHistoryCode(((EditText) findViewById(R.id.jkda_et_geneticHistoryCode))
						.getText().toString());
		healthRecord_update
				.setGeneticHistoryOther(((EditText) findViewById(R.id.jkda_et_geneticHistoryOther))
						.getText().toString());
		healthRecord_update
				.setDisabilityCodes(((EditText) findViewById(R.id.jkda_et_disabilityCodes))
						.getText().toString());
		healthRecord_update
				.setDisabilityOther(((EditText) findViewById(R.id.jkda_et_disabilityOther))
						.getText().toString());
		healthRecord_update
				.setKitchenExhaustCode(((EditText) findViewById(R.id.jkda_et_kitchenExhaustCode))
						.getText().toString());
		healthRecord_update
				.setFuelTypeCode(((EditText) findViewById(R.id.jkda_et_fuelTypeCode))
						.getText().toString());
		healthRecord_update
				.setWaterCode(((EditText) findViewById(R.id.jkda_et_waterCode))
						.getText().toString());
		healthRecord_update
				.setToiletCode(((EditText) findViewById(R.id.jkda_et_toiletCode))
						.getText().toString());
		healthRecord_update
				.setLivestockColumnCode(((EditText) findViewById(R.id.jkda_et_livestockColumnCode))
						.getText().toString());
//		healthRecord_update
//				.setPastHistoryList(((EditText)	findViewById(R.id.jkda_et_pastHistoryList))
// 						.getText().toString());
		return healthRecord_update;
	}

	// public void abc(View view){
	// setAllText();
	// }
	/** 给bean类设置显示到id里 */
	@Override
	public void setAllText() {
		// String tmp=healthRecord_down.getPersonId();
		// setTextByid(tmp, R.id.jkda_et_personId);
		// setTextByid(healthRecord_down.getIdCard() + "", R.id.jkda_et_Id);
		setTextByid(healthRecord_down.getPersonId(), R.id.jkda_et_personId);
		setTextByid(healthRecord_down.getNowLiveCode(),
				R.id.jkda_et_nowLiveCode);
		setTextByid(healthRecord_down.getNowLiveName(),
				R.id.jkda_et_nowLiveName);
		setTextByid(healthRecord_down.getNowLiveAddr(),
				R.id.jkda_et_nowLiveAddr);
		setTextByid(healthRecord_down.getHouseholdRegisterCode(),
				R.id.jkda_et_householdRegisterCode);
		setTextByid(healthRecord_down.getHouseholdRegisterName(),
				R.id.jkda_et_householdRegisterName);
		setTextByid(healthRecord_down.getHouseholdRegisterAddr(),
				R.id.jkda_et_householdRegisterAddr);
		setTextByid(healthRecord_down.getRegisterOrgCode(),
				R.id.jkda_et_registerOrgCode);
		setTextByid(healthRecord_down.getRegisterOrgName(),
				R.id.jkda_et_registerOrgName);
		setTextByid(healthRecord_down.getResponsibleDoctorCode(),
				R.id.jkda_et_responsibleDoctorCode);
		setTextByid(healthRecord_down.getResponsibleDoctorName(),
				R.id.jkda_et_responsibleDoctorName);
		setTextByid(healthRecord_down.getRegisterDoctorCode(),
				R.id.jkda_et_registerDoctorCode);
		setTextByid(healthRecord_down.getRegisterDoctorName(),
				R.id.jkda_et_registerDoctorName);
		setTextByid(healthRecord_down.getRegisterDate(),
				R.id.jkda_et_registerDate);
		setTextByid(healthRecord_down.getHealthFileNumber(),
				R.id.jkda_et_healthFileNumber);
		setTextByid(healthRecord_down.getName(), R.id.jkda_et_name);
		setTextByid(healthRecord_down.getGenderCode(), R.id.jkda_et_genderCode);
		setTextByid(healthRecord_down.getBirthday(), R.id.jkda_et_birthday);
		setTextByid(healthRecord_down.getIdCard(), R.id.jkda_et_idCard);
		setTextByid(healthRecord_down.getWorkUnit(), R.id.jkda_et_workUnit);
		setTextByid(healthRecord_down.getPhone(), R.id.jkda_et_phone);
		setTextByid(healthRecord_down.getContacts(), R.id.jkda_et_contacts);
		setTextByid(healthRecord_down.getContactsPhone(),
				R.id.jkda_et_contactsPhone);
		setTextByid(healthRecord_down.getResidentType(),
				R.id.jkda_et_residentType);
		setTextByid(healthRecord_down.getEthnicityCode(),
				R.id.jkda_et_ethnicityCode);
		setTextByid(healthRecord_down.getEthnicityName(),
				R.id.jkda_et_ethnicityName);
		setTextByid(healthRecord_down.getBloodGroupCode(),
				R.id.jkda_et_bloodGroupCode);
		setTextByid(healthRecord_down.getRhBloodGroupCode(),
				R.id.jkda_et_rhBloodGroupCode);
		setTextByid(healthRecord_down.getEduBGCode(), R.id.jkda_et_eduBGCode);
		setTextByid(healthRecord_down.getOccupationCode(),
				R.id.jkda_et_occupationCode);
		setTextByid(healthRecord_down.getMaritalStatusCode(),
				R.id.jkda_et_maritalStatusCode);
		setTextByid(healthRecord_down.getPaymentMethodCodes(),
				R.id.jkda_et_paymentMethodCodes);
		setTextByid(healthRecord_down.getPaymentMethodOther(),
				R.id.jkda_et_paymentMethodOther);
		setTextByid(healthRecord_down.getDrugAllergyHistoryCodes(),
				R.id.jkda_et_drugAllergyHistoryCodes);
		setTextByid(healthRecord_down.getDrugAllergyHistoryOther(),
				R.id.jkda_et_drugAllergyHistoryOther);
		setTextByid(healthRecord_down.getExposureHistoryCodes(),
				R.id.jkda_et_exposureHistoryCodes);
		setTextByid(healthRecord_down.getFamilyHistoryFatherCodes(),
				R.id.jkda_et_familyHistoryFatherCodes);
		setTextByid(healthRecord_down.getFamilyHistoryFatherOther(),
				R.id.jkda_et_familyHistoryFatherOther);
		setTextByid(healthRecord_down.getFamilyHistoryMatherCodes(),
				R.id.jkda_et_familyHistoryMatherCodes);
		setTextByid(healthRecord_down.getFamilyHistoryMatherOther(),
				R.id.jkda_et_familyHistoryMatherOther);
		setTextByid(healthRecord_down.getBrotherAndSisterCodes(),
				R.id.jkda_et_brotherAndSisterCodes);
		setTextByid(healthRecord_down.getBrotherAndSisterOther(),
				R.id.jkda_et_brotherAndSisterOther);
		setTextByid(healthRecord_down.getFamilyHistoryChildrenCodes(),
				R.id.jkda_et_familyHistoryChildrenCodes);
		setTextByid(healthRecord_down.getFamilyHistoryChildrenOther(),
				R.id.jkda_et_familyHistoryChildrenOther);
		setTextByid(healthRecord_down.getGeneticHistoryCode(),
				R.id.jkda_et_geneticHistoryCode);
		setTextByid(healthRecord_down.getGeneticHistoryOther(),
				R.id.jkda_et_geneticHistoryOther);
		setTextByid(healthRecord_down.getDisabilityCodes(),
				R.id.jkda_et_disabilityCodes);
		setTextByid(healthRecord_down.getDisabilityOther(),
				R.id.jkda_et_disabilityOther);
		setTextByid(healthRecord_down.getKitchenExhaustCode(),
				R.id.jkda_et_kitchenExhaustCode);
		setTextByid(healthRecord_down.getFuelTypeCode(),
				R.id.jkda_et_fuelTypeCode);
		setTextByid(healthRecord_down.getWaterCode(), R.id.jkda_et_waterCode);
		setTextByid(healthRecord_down.getToiletCode(), R.id.jkda_et_toiletCode);
		setTextByid(healthRecord_down.getLivestockColumnCode(),
				R.id.jkda_et_livestockColumnCode);

//		setTextByid(healthRecord_down.getPastHistoryList(),
//				R.id.jkda_et_pastHistoryList);
		Log.e("getPastHistory",healthRecord_down.getPastHistoryList());

		// ///////////////////////////////////////////曾德森
		if (healthRecord_down != null && healthRecord_down.getName().equals("")) {
			// Toast.makeText(this, "空值", 0).show();
			setTextByid(Basesence.getmac(this), R.id.jkda_et_machineCode);
			setTextByid(Basesence.getorgCode(), R.id.jkda_et_registerOrgCode);
			setTextByid(Basesence.getorgName(), R.id.jkda_et_registerOrgName);
			setTextByid(Basesence.getUser().getUserCode(),
					R.id.jkda_et_registerDoctorCode);
			setTextByid(Basesence.getUser().getUserName(),
					R.id.jkda_et_registerDoctorName);
		}
		// ///////////////////////

	}

	public void loadJWSList(List<PastHistory> pasts) {
		datas = new ArrayList<String[]>();
		String[] item = null;
		for (PastHistory past : pasts) {
			item = new String[] { past.getPastHistoryType(),
					past.getPastHistoryCode(), past.getPastHistoryDate(),
					past.getPastHistoryAdd() };
			datas.add(item);

		}
		mAdapter = new FourColmunAdapter(JKDAActivity.this, datas);
		mJWSListView.setAdapter(mAdapter);

		mJWSListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				if (tagEdit) {
					// system.out.println("编辑");
					CustomDialog.YYSM_Edit_Delete(JKDAActivity.this, mAdapter,
							arg2).notifyDataSetChanged();
				} else {
					// system.out.println("不可编辑");
				}
			}
		});
	}

}
