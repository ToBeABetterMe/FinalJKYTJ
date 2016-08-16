package com.c102c.app.staff_training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.c102c.finalJKYTJ.R;

public class QXPX extends Activity {

	private SimpleAdapter adapter;

	private List<Map<String, String>> datas;

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qxpx);
		Intent intent = getIntent();
		int frombutton = intent.getIntExtra("frombutton", -1);
		datas = new ArrayList<Map<String, String>>();

		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		switch (frombutton) {
		case 1:
			listView = (ListView) findViewById(R.id.lv_qxpxlist);
			map1.put("pxrq", "2013-05-14");
			map1.put("pxdd", "县卫生院");
			map1.put("pxnr", "一体机的相关操作");
			map1.put("pxjg", "不理想");
			map1.put("hdxf", "3");

			map2.put("pxrq", "2014-01-15");
			map2.put("pxdd", "县卫生院");
			map2.put("pxnr", "医疗设备的使用");
			map2.put("pxjg", "理想");
			map2.put("hdxf", "8");

			map3.put("pxrq", "2013-08-14");
			map3.put("pxdd", "县卫生院");
			map3.put("pxnr", "注意事项");
			map3.put("pxjg", "理想");
			map3.put("hdxf", "7");

			adapter = new SimpleAdapter(this, datas, R.layout.qxpx_list_item,
					new String[] { "pxrq", "pxdd", "pxnr", "pxjg", "hdxf" },
					new int[] { R.id.tv_pxrq, R.id.tv_pxdd, R.id.tv_pxnr,
							R.id.tv_pxjg, R.id.tv_hdxf });

			// listView.setAdapter(adapter);
			break;
		case 2:
			setContentView(R.layout.xxpx);
			listView = (ListView) findViewById(R.id.lv_qxpxlist);
			map1.put("pxrq", "2013-05-14");
			map1.put("pxdd", "县培训中心");
			map1.put("pxnr", "一体机的相关操作");
			map1.put("pxjg", "理想");
			map1.put("hdxf", "9");

			map2.put("pxrq", "2014-01-15");
			map2.put("pxdd", "县培训中心");
			map2.put("pxnr", "医疗设备的使用");
			map2.put("pxjg", "不理想");
			map2.put("hdxf", "3");

			map3.put("pxrq", "2013-08-14");
			map3.put("pxdd", "县培训中心");
			map3.put("pxnr", "注意事项");
			map3.put("pxjg", "理想");
			map3.put("hdxf", "7");

			adapter = new SimpleAdapter(this, datas, R.layout.qxpx_list_item,
					new String[] { "pxrq", "pxdd", "pxnr", "pxjg", "hdxf" },
					new int[] { R.id.tv_pxrq, R.id.tv_pxdd, R.id.tv_pxnr,
							R.id.tv_pxjg, R.id.tv_hdxf });

			// listView.setAdapter(adapter);
			break;
		case 3:
			setContentView(R.layout.xcyslh);
			listView = (ListView) findViewById(R.id.lv_qxpxlist);
			map1.put("hyrq", "2013-05-14");
			map1.put("hydd", "村干部活动中心");
			map1.put("hynr", "一体机使用情况");
			map1.put("hyjg", "不理想");

			map2.put("hyrq", "2014-01-15");
			map2.put("hydd", "村干部活动中心");
			map2.put("hynr", "村民健康调查");
			map2.put("hyjg", "理想");

			map3.put("hyrq", "2013-08-14");
			map3.put("hydd", "村干部活动中心");
			map3.put("hynr", "村医出勤情况");
			map3.put("hyjg", "理想");

			adapter = new SimpleAdapter(this, datas, R.layout.xcyslh_list_item,
					new String[] { "hyrq", "hydd", "hynr", "hyjg", },
					new int[] { R.id.tv_hyrq, R.id.tv_hydd, R.id.tv_hynr,
							R.id.tv_hyjg });
			break;
		case 4:
			intent.putExtra("frombutton", 4);
			listView = (ListView) findViewById(R.id.lv_qxpxlist);
			map1.put("xxnr", "一体机使用方法");
			map1.put("kssj", "2013-12-12");
			map1.put("jssj", "2013-12-15");
			map1.put("pxjg", "合格");
			map1.put("hdxf", "7");

			map2.put("xxnr", "医生素质培训");
			map2.put("kssj", "2014-01-15");
			map2.put("jssj", "2014-01-18");
			map2.put("pxjg", "理想");
			map2.put("hdxf", "8");

			map3.put("xxnr", "相关设备使用");
			map3.put("kssj", "2013-08-14");
			map3.put("jssj", "2013-09-14");
			map3.put("pxjg", "不合格");
			map3.put("hdxf", "4");
			adapter = new SimpleAdapter(this, datas, R.layout.qxpx_list_item,
					new String[] { "xxnr", "kssj", "jssj", "pxjg", "hdxf" },
					new int[] { R.id.tv_pxrq, R.id.tv_pxdd, R.id.tv_pxnr,
							R.id.tv_pxjg, R.id.tv_hdxf });
		default:
			break;
		}
		// map1.put("pxrq", "2013-05-14");
		// map1.put("pxdd", "县卫生院");
		// map1.put("pxnr", "一体机的相关操作");
		// map1.put("pxjg", "不理想");
		// map1.put("hdxf", "3");
		//
		// map2.put("pxrq", "2014-01-15");
		// map2.put("pxdd", "县卫生院");
		// map2.put("pxnr", "医疗设备的使用");
		// map2.put("pxjg", "理想");
		// map2.put("hdxf", "8");
		//
		// map3.put("pxrq", "2013-08-14");
		// map3.put("pxdd", "县卫生院");
		// map3.put("pxnr", "注意事项");
		// map3.put("pxjg", "理想");
		// map3.put("hdxf", "7");

		datas.add(map1);
		datas.add(map2);
		datas.add(map3);

		// adapter = new SimpleAdapter(this, datas, R.layout.qxpx_list_item,
		// new String[] { "pxrq", "pxdd", "pxnr", "pxjg",
		// "hdxf" }, new int[] { R.id.tv_pxrq, R.id.tv_pxdd,
		// R.id.tv_pxnr, R.id.tv_pxjg, R.id.tv_hdxf });
		//
		listView.setAdapter(adapter);
	}
}
