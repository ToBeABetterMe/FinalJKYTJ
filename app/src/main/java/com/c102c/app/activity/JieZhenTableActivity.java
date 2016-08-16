package com.c102c.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.LocalJieZhen;
import com.c102c.finalJKYTJ.R;
import com.health.util.DateEditText;
import com.health.util.MultiChoiceEditText2;
import com.health.util.SingleChoiceEditText;

public class JieZhenTableActivity extends Activity {

	private EditText etName;
	private LocalJieZhen mlocaljiezhen = null;

	private EditText etGrxb;

	private EditText etCsrq;

	private EditText etSfzh;

	private HealthRecord_down mHealthRecord_down;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.jiezheng_recorder_table);
		AppDB.getInstance(this);

		mHealthRecord_down = Basesence.getTempHealthRecord_down();
		fillEditText();
	}

	private LocalJieZhen getAllText() {
		LocalJieZhen localJieZhen = new LocalJieZhen();

		localJieZhen.setPersonId(mHealthRecord_down.getPersonId());
		localJieZhen.setName(((EditText) findViewById(R.id.et_name)).getText()
				.toString());
		localJieZhen.setGender(((EditText) findViewById(R.id.et_grxb))
				.getText().toString());
		localJieZhen.setIdCard(((EditText) findViewById(R.id.et_sfzh))
				.getText().toString());
		localJieZhen.setBirthday(((EditText) findViewById(R.id.et_csrq))
				.getText().toString());
		localJieZhen.setYibaokahao(((EditText) findViewById(R.id.et_ybkh))
				.getText().toString());
		localJieZhen
				.setJiezhen_doctor(((EditText) findViewById(R.id.et_jiezhen_doctor))
						.getText().toString());
		localJieZhen
				.setDate(((DateEditText) findViewById(R.id.et_jiezhen_date))
						.getText().toString());
		localJieZhen
				.setBingren_des(((EditText) findViewById(R.id.et_subjctive_matarial))
						.getText().toString());
		localJieZhen.setXianbingshi(((EditText) findViewById(R.id.et_xbs))
				.getText().toString());
		localJieZhen.setJiwangshi(((EditText) findViewById(R.id.et_jws))
				.getText().toString());
		localJieZhen
				.setYibanqingkuang(((EditText) findViewById(R.id.met_symps))
						.getText().toString());
		localJieZhen
				.setTigejiancha(((EditText) findViewById(R.id.physical_examination))
						.getText().toString());
		localJieZhen.setXuetang(((EditText) findViewById(R.id.edt_glu))
				.getText().toString());
		localJieZhen.setNiaoye(((EditText) findViewById(R.id.edt_urine))
				.getText().toString());
		localJieZhen.setDanguchun(((EditText) findViewById(R.id.edt_chol))
				.getText().toString());
		localJieZhen.setNiaosuan(((EditText) findViewById(R.id.edt_ua))
				.getText().toString());
		localJieZhen.setBaixibao(((EditText) findViewById(R.id.edt_wbc))
				.getText().toString());
		localJieZhen.setXueya(((EditText) findViewById(R.id.edt_press))
				.getText().toString());
		localJieZhen.setTiwen(((EditText) findViewById(R.id.edt_et)).getText()
				.toString());
		localJieZhen.setXindian(((EditText) findViewById(R.id.edt_xdt))
				.getText().toString());
		localJieZhen.setZhenduan(((EditText) findViewById(R.id.et_evaluation))
				.getText().toString());
		localJieZhen.setChuzhijihua(((EditText) findViewById(R.id.et_plan))
				.getText().toString());
		localJieZhen.setYibanzhiliao(((EditText) findViewById(R.id.et_ybzl))
				.getText().toString());
		localJieZhen
				.setYongyaoqingkuang(((EditText) findViewById(R.id.et_yyqk))
						.getText().toString());
		localJieZhen.setZhiliaojishu(((EditText) findViewById(R.id.et_zljs))
				.getText().toString());
		localJieZhen
				.setQingchuangfenghe(((EditText) findViewById(R.id.et_qcfh))
						.getText().toString());
		localJieZhen.setMengzhenhuanyao(((EditText) findViewById(R.id.et_mzhy))
				.getText().toString());
		localJieZhen
				.setZhongyitizhibianshi(((EditText) findViewById(R.id.et_zytzbs))
						.getText().toString());
		localJieZhen.setJiankangpingjia(((EditText) findViewById(R.id.et_jkpj))
				.getText().toString());
		localJieZhen.setJiankangzhidao(((EditText) findViewById(R.id.et_jkzd))
				.getText().toString());
		localJieZhen.setShoufeiguanli(((EditText) findViewById(R.id.et_sfgl))
				.getText().toString());
		localJieZhen.setTuifeiguanli(((EditText) findViewById(R.id.et_tfgl))
				.getText().toString());
		return localJieZhen;
	}

	public void saveJieZhenTable(View view) {
		AppDB.add(getAllText());
		finish();
	}

	public void fillEditText() {
		if (getIntent().getStringExtra("action") != null) {
			if (getIntent().getStringExtra("action").equals("detail")) {
				mlocaljiezhen = MZYLActivity.getMlocaljiezhen();
				((EditText) findViewById(R.id.et_name)).setText(mlocaljiezhen
						.getName());
				((EditText) findViewById(R.id.et_grxb)).setText(mlocaljiezhen
						.getGender());
				((EditText) findViewById(R.id.et_sfzh)).setText(mlocaljiezhen
						.getIdCard());
				((EditText) findViewById(R.id.et_csrq)).setText(mlocaljiezhen
						.getBirthday());
				((EditText) findViewById(R.id.et_ybkh)).setText(mlocaljiezhen
						.getYibaokahao());
				((EditText) findViewById(R.id.et_jiezhen_doctor))
						.setText(mlocaljiezhen.getJiezhen_doctor());
				((DateEditText) findViewById(R.id.et_jiezhen_date))
						.setText(mlocaljiezhen.getDate());
			} else if (getIntent().getStringExtra("action").equals("add")) {
				((EditText) findViewById(R.id.et_name))
						.setText(mHealthRecord_down.getName());
				((EditText) findViewById(R.id.et_grxb))
						.setText(mHealthRecord_down.getGenderCode());
				((EditText) findViewById(R.id.et_sfzh))
						.setText(mHealthRecord_down.getIdCard());
				((EditText) findViewById(R.id.et_csrq))
						.setText(mHealthRecord_down.getBirthday());
				((EditText) findViewById(R.id.et_serialId))
						.setText(mHealthRecord_down.getHealthFileNumber());
			}
		}
	}

}
