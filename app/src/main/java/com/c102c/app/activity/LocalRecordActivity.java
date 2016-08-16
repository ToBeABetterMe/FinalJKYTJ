package com.c102c.app.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.LocalMeasurementBaiXiBao;
import com.c102c.app.model.LocalMeasurementEWen;
import com.c102c.app.model.LocalMeasurementNiaoYe;
import com.c102c.app.model.LocalMeasurementXinDian;
import com.c102c.app.model.LocalMeasurementXueTang;
import com.c102c.app.model.LocalMeasurementXueYa;
import com.c102c.app.model.LocalMeasurementXueYang;
import com.c102c.app.utils.TwoCloumAdapter;
import com.c102c.finalJKYTJ.R;

public class LocalRecordActivity extends Activity {

	private HealthRecord_down mHealthRecord_down;

	private List<LocalMeasurementEWen> mEWen_List;
	private List<LocalMeasurementNiaoYe> mNiaoYe_List;
	private List<LocalMeasurementXinDian> mXinDian_List;
	private List<LocalMeasurementXueTang> mXueTang_List;
	private List<LocalMeasurementXueYang> mXueYang_List;
	private List<LocalMeasurementXueYa> mXueYa_List;
	private List<LocalMeasurementBaiXiBao> mBaiXiBao_List;

	private List<String[]> datas = new ArrayList<String[]>();
	private TwoCloumAdapter mAdapter;
	private String[] title = { "测量时间", "测量值" };
	private ListView mListView;

	private SimpleDateFormat tmpDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_record_layout);
		mHealthRecord_down = Basesence.getTempHealthRecord_down();

		AppDB.getInstance(this);
		mEWen_List = AppDB
				.queryByPersonIdEWen(mHealthRecord_down.getPersonId());
		mNiaoYe_List = AppDB.queryByPersonIdNiaoYe(mHealthRecord_down
				.getPersonId());
		mXinDian_List = AppDB.queryByPersonIdXinDian(mHealthRecord_down
				.getPersonId());
		mXueTang_List = AppDB.queryByPersonIdXueTang(mHealthRecord_down
				.getPersonId());
		mXueYang_List = AppDB.queryByPersonIdXueYang(mHealthRecord_down
				.getPersonId());
		mXueYa_List = AppDB.queryByPersonIdXueYa(mHealthRecord_down
				.getPersonId());
		mBaiXiBao_List = AppDB.queryByPersonIdBaiXiBao(mHealthRecord_down
				.getPersonId());
		mListView = (ListView) findViewById(R.id.show_table_listview);
		InitalListView();
	}

	public void clickButton(View v) {
		switch (v.getId()) {
		case R.id.show_bp:
			// 血压
			InitalListView();
			break;
		case R.id.show_bo:
			// 血氧
			datas.clear();
			datas.add(title);
			for (LocalMeasurementXueYang localMeasurementXueYang : mXueYang_List) {
				String result = "血氧：" + localMeasurementXueYang.getXy();
				String[] tmp = {
						tmpDateFormat.format(new Date(localMeasurementXueYang
								.getTime())), result };
				datas.add(tmp);
			}
			mAdapter = new TwoCloumAdapter(this, datas);
			mListView.setAdapter(mAdapter);
			break;
		case R.id.show_temp:
			// 额温
			datas.clear();
			datas.add(title);
			for (LocalMeasurementEWen localMeasurementXueYang : mEWen_List) {
				String result = "额温：" + localMeasurementXueYang.getEw();
				String[] tmp = {
						tmpDateFormat.format(new Date(localMeasurementXueYang
								.getTime())), result };
				datas.add(tmp);
			}
			mAdapter = new TwoCloumAdapter(this, datas);
			mListView.setAdapter(mAdapter);
			break;
		case R.id.show_glu:
			// 血糖
			datas.clear();
			datas.add(title);
			for (LocalMeasurementXueTang localMeasurementXueYang : mXueTang_List) {
				String result = "血糖：" + localMeasurementXueYang.getXt();
				String[] tmp = {
						tmpDateFormat.format(new Date(localMeasurementXueYang
								.getTime())), result };
				datas.add(tmp);
			}
			mAdapter = new TwoCloumAdapter(this, datas);
			mListView.setAdapter(mAdapter);
			break;
		case R.id.show_urine:
			// 尿常规
			datas.clear();
			datas.add(title);
			for (LocalMeasurementNiaoYe localMeasurementXueYang : mNiaoYe_List) {
				String result = "pro:" + localMeasurementXueYang.getPro()
						+ "\n" + "bld:" + localMeasurementXueYang.getBld()
						+ "\n" + "uglu:" + localMeasurementXueYang.getUglu()
						+ "\n" + "ket:" + localMeasurementXueYang.getKet()
						+ "\n" + "尿常规-其他：" + localMeasurementXueYang.getNqt();
				String[] tmp = {
						tmpDateFormat.format(new Date(localMeasurementXueYang
								.getTime())), result };
				datas.add(tmp);
			}
			mAdapter = new TwoCloumAdapter(this, datas);
			mListView.setAdapter(mAdapter);
			break;
		case R.id.show_ecg:
			// 心电
			datas.clear();
			datas.add(title);
			for (LocalMeasurementXinDian localMeasurementXueYang : mXinDian_List) {
				String result = "心电结果：" + localMeasurementXueYang.getXdjg();
				String[] tmp = {
						tmpDateFormat.format(new Date(localMeasurementXueYang
								.getTime())), result };
				datas.add(tmp);
			}
			mAdapter = new TwoCloumAdapter(this, datas);
			mListView.setAdapter(mAdapter);
			break;
			
		case R.id.show_wbc:
			// 白细胞
			datas.clear();
			datas.add(title);
			for (LocalMeasurementBaiXiBao localMeasurementXueYang : mBaiXiBao_List) {
				String result = "白细胞结果：" + localMeasurementXueYang.getWbc();
				String[] tmp = {
						tmpDateFormat.format(new Date(localMeasurementXueYang
								.getTime())), result };
				datas.add(tmp);
			}
			mAdapter = new TwoCloumAdapter(this, datas);
			mListView.setAdapter(mAdapter);
			break;
		default:
			break;
		}
	}

	private void InitalListView() {
		// TODO Auto-generated method stub
		datas.clear();
		datas.add(title);
		for (LocalMeasurementXueYa localMeasurementXueYa : mXueYa_List) {
			String result = "收缩压：" + localMeasurementXueYa.getSsy() + "\n"
					+ "舒张压：" + localMeasurementXueYa.getSzy() + '\n' + "脉率："
					+ localMeasurementXueYa.getMl() + '\n';
			String[] tmp = {
					tmpDateFormat.format(new Date(localMeasurementXueYa
							.getTime())), result };
			datas.add(tmp);
		}

		mAdapter = new TwoCloumAdapter(this, datas);
		mListView.setAdapter(mAdapter);
	}
}
