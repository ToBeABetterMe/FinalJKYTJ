package com.c102c.app.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.c102c.finalJKYTJ.R;


public class TwoCloumAdapter extends BaseAdapter {
	private List<String[]> datas;
	private Context context;

	public TwoCloumAdapter(Context context, List<String[]> datas) {
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		String[] line = datas.get(position);
		// ֻ��Ϊ��ʱ�Ź���
		if (convertView == null) {
			holder = new Holder();
			holder.dataET = new TextView[line.length];
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.list_item_two_cloum, null);
			holder.dataET[0] = (TextView) convertView
					.findViewById(R.id.list_item_1);
			holder.dataET[1] = (TextView) convertView
					.findViewById(R.id.list_item_2);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.record_layout);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		for (int i = 0; i < line.length; i++) {
			holder.dataET[i].setText(line[i]);
		}
		if (position == 0) {
			holder.layout.setBackgroundResource(R.color.record_title);
		} else {
			// �����������ı���
			holder.layout.setBackgroundResource(R.drawable.button_selector);
		}
		return convertView;
	}

	/***
	 * �����ݴ�����
	 * 
	 * @author jiqunpeng
	 * 
	 *         ����ʱ�䣺2013-12-20 ����3:11:21
	 */
	class Holder {
		TextView[] dataET;// ��ʾ������
		LinearLayout layout;
	}
}