package com.c102c.app.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.health_record_activity.JKDAActivity;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.LocalMeasurementBaiXiBao;
import com.c102c.app.model.LocalMeasurementEWen;
import com.c102c.app.model.LocalMeasurementNiaoYe;
import com.c102c.app.model.LocalMeasurementXinDian;
import com.c102c.app.model.LocalMeasurementXueTang;
import com.c102c.app.model.LocalMeasurementXueYa;
import com.c102c.app.model.LocalMeasurementXueYang;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ETempActivity;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.measurement.MeasureUrineActivity;
import com.health.measurement.MeasureWbc;
import com.health.measurement.MeasureWeilionECG;

public class JKCLActivity extends Activity {

	private HealthRecord_down mHealthRecord_down;

	private LocalMeasurementEWen mLocalMeasurementEWen;
	private LocalMeasurementNiaoYe mLocalMeasurementNiaoYe;
	private LocalMeasurementXinDian mLocalMeasurementXinDian;
	private LocalMeasurementXueTang mLocalMeasurementXueTang;
	private LocalMeasurementXueYang mLocalMeasurementXueYang;
	private LocalMeasurementXueYa mLocalMeasurementXueYa;
	private LocalMeasurementBaiXiBao mLocalMeasurementBaiXiBao;

	private static final String TAG = "JKCLActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jkcl_activity_layout);
		mHealthRecord_down = Basesence.getTempHealthRecord_down();
		AppDB.getInstance(this);
	}

	public void StartMeasurement(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.jkcl_test_bp_btn:
			// 血压测量
			intent.setClass(JKCLActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jkcl_test_xueyang_btn:
			// 血氧测量
			intent.setClass(JKCLActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_OXYGEN);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jkcl_test_ewen_btn:
			// 额温测量
			intent.setClass(JKCLActivity.this, ETempActivity.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jkcl_test_xuetang_btn:
			// 血糖测量
			intent.setClass(JKCLActivity.this, MeasureOnPC300Activity.class);
			intent.putExtra("token", MeasureOnPC300Activity.BLOOD_SUGAR);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jkcl_test_niaoyifenxi_btn:
			// 尿液分析
			intent.setClass(JKCLActivity.this, MeasureUrineActivity.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.jkcl_test_ecg12_btn:
			// 十二导心电
			intent.setClass(JKCLActivity.this, MeasureWeilionECG.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		case R.id.test_baixibao:
			// 白细胞
			intent.setClass(JKCLActivity.this, MeasureWbc.class);
			startActivityForResult(intent, Basesence.MEASURE);
			break;
		default:
			break;
		}

	}
	
	private Handler handler = Basesence.getupdatehandler(JKCLActivity.this);
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		switch (resultCode) {
		case Basesence.MEASURE_RESULT_LEFT_BLOOD_PRESURE:
			if (!data.getStringExtra("ssy").equals("")) {
				mLocalMeasurementXueYa = new LocalMeasurementXueYa();
				mLocalMeasurementXueYa.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementXueYa.setName(mHealthRecord_down.getName());
				mLocalMeasurementXueYa
						.setIdCard(mHealthRecord_down.getIdCard());
				mLocalMeasurementXueYa.setSsy(data.getStringExtra("ssy"));
				mLocalMeasurementXueYa.setSzy(data.getStringExtra("szy"));
				mLocalMeasurementXueYa.setMl(data.getStringExtra("ml"));
				mLocalMeasurementXueYa.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementXueYa);
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementXueYa);
				Toast.makeText(this, "添加测量血压记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有测量血压", Toast.LENGTH_LONG).show();
			}
			break;
		case Basesence.MEASURE_RESULT_BLOOD_OXYGEN:
			if (!data.getStringExtra("xy").equals("")) {
				mLocalMeasurementXueYang = new LocalMeasurementXueYang();
				mLocalMeasurementXueYang.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementXueYang.setName(mHealthRecord_down.getName());
				mLocalMeasurementXueYang.setIdCard(mHealthRecord_down
						.getIdCard());
				mLocalMeasurementXueYang.setXy(data.getStringExtra("xy"));
				mLocalMeasurementXueYang.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementXueYang);
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementXueYang);
				Toast.makeText(this, "添加测量血氧记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有测量血氧", Toast.LENGTH_LONG).show();
			}
			break;
		case Basesence.MEASURE_RESULT_BLOOD_SUGAR:
			// mLocalMeasurement.setXt(data.getStringExtra("xt"));
			if (!data.getStringExtra("xt").equals("")) {
				mLocalMeasurementXueTang = new LocalMeasurementXueTang();
				mLocalMeasurementXueTang.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementXueTang.setName(mHealthRecord_down.getName());
				mLocalMeasurementXueTang.setIdCard(mHealthRecord_down
						.getIdCard());
				mLocalMeasurementXueTang.setXt(data.getStringExtra("xt"));
				mLocalMeasurementXueTang.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementXueTang);
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementXueTang);
				Toast.makeText(this, "添加测量血糖记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有测量血糖", Toast.LENGTH_LONG).show();
			}
			break;
		case Basesence.MEASURE_RESULT_ETMP:
			if (!data.getStringExtra(ETempActivity.TAG).equals("")) {
				mLocalMeasurementEWen = new LocalMeasurementEWen();
				mLocalMeasurementEWen.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementEWen.setName(mHealthRecord_down.getName());
				mLocalMeasurementEWen.setIdCard(mHealthRecord_down.getIdCard());
				mLocalMeasurementEWen.setEw(data
						.getStringExtra(ETempActivity.TAG));
				mLocalMeasurementEWen.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementEWen);
				
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementEWen);
				Toast.makeText(this, "添加测量额温记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有测量额温", Toast.LENGTH_LONG).show();
			}
			break;
		case Basesence.MEASURE_RESULT_URINE:
			if (!data.getStringExtra("ndb").equals("")) {
				mLocalMeasurementNiaoYe = new LocalMeasurementNiaoYe();
				mLocalMeasurementNiaoYe.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementNiaoYe.setName(mHealthRecord_down.getName());
				mLocalMeasurementNiaoYe.setIdCard(mHealthRecord_down
						.getIdCard());
				mLocalMeasurementNiaoYe.setPro(data.getStringExtra("ndb"));
				mLocalMeasurementNiaoYe.setBld(data.getStringExtra("nqx"));
				mLocalMeasurementNiaoYe.setUglu(data.getStringExtra("nt"));
				mLocalMeasurementNiaoYe.setKet(data.getStringExtra("ntt"));
				mLocalMeasurementNiaoYe.setNqt(data.getStringExtra("nqt"));
				mLocalMeasurementNiaoYe.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementNiaoYe);
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementNiaoYe);
				Toast.makeText(this, "添加尿液分析记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有进行尿液分析", Toast.LENGTH_LONG).show();
			}
			break;
		case Basesence.MEASURE_RESULT_XIN_DIAN:
			if (!data.getStringExtra("xdjg").equals("")) {
				mLocalMeasurementXinDian = new LocalMeasurementXinDian();
				mLocalMeasurementXinDian.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementXinDian.setName(mHealthRecord_down.getName());
				mLocalMeasurementXinDian.setIdCard(mHealthRecord_down
						.getIdCard());
				mLocalMeasurementXinDian.setXdjg(data.getStringExtra("xdjg"));
				mLocalMeasurementXinDian.setXdsj(data.getStringExtra("xdsj"));
				mLocalMeasurementXinDian.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementXinDian);
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementXinDian);
				Toast.makeText(this, "添加心电测量记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有进行心电测量", Toast.LENGTH_LONG).show();
			}
			break;
		case Basesence.MEASURE_RESULT_WBC:
			if (!data.getStringExtra("wbc").equals("")) {
				mLocalMeasurementBaiXiBao = new LocalMeasurementBaiXiBao();
				mLocalMeasurementBaiXiBao.setPersonId(mHealthRecord_down
						.getPersonId());
				mLocalMeasurementBaiXiBao.setName(mHealthRecord_down.getName());
				mLocalMeasurementBaiXiBao.setIdCard(mHealthRecord_down
						.getIdCard());
				mLocalMeasurementBaiXiBao.setWbc(data.getStringExtra("wbc"));

				mLocalMeasurementBaiXiBao.setTime(sdformat.format(new Date()).toString());
				AppDB.add(mLocalMeasurementBaiXiBao);
				Fetch_by_li.communicate("M0100080201", JKCLActivity.this,
						handler, mLocalMeasurementBaiXiBao);
				Toast.makeText(this, "添加白细胞测量记录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "没有进行白细胞测量", Toast.LENGTH_LONG).show();
			}
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
