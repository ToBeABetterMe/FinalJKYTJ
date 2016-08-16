package com.c102c.app.activity;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.c102c.finalJKYTJ.R;


@SuppressLint("ResourceAsColor")
public class ListViewAdapter extends BaseAdapter {
	private Context context; // ����������
	private List<Map<String, String>> listItems;
	private LayoutInflater listContainer; // ��ͼ����
	private int textNum;
	private String[] tittle;

	public ListViewAdapter(Context context, List<Map<String, String>> list,
			String[] tittle) {
		this.context = context;
		listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.listItems = list;
		this.tittle = tittle;
		textNum = tittle.length;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position == 0)
			return null;
		return listItems.get(position - 1);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
/**
 * ͨ��tittle�е�ֵ˳���ȡlistitem��ֵ��Ҳ����listitems�е�keyֵ������tittle�е�ֵ�������ſɱ�֤˳���ȡ��
 */
	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("getView", position + "");
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.null_listitem, null);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.null_listitem);
			holder.tv = new TextView[textNum];
			for (int i = 0; i < textNum; i++) {
				holder.tv[i] = new TextView(context);
				holder.tv[i].setId(i);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT, 1);
				holder.tv[i].setGravity(Gravity.CENTER_HORIZONTAL);
				holder.tv[i].setTextSize(20);
				holder.layout.addView(holder.tv[i], lp);
			}
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		for (int j = 0; j < textNum; j++) {
			if (position == 0) {

				holder.tv[j].setText(tittle[j]);
				holder.layout.setBackgroundResource(R.color.record_title);
			} else {
				String str = listItems.get(position - 1).get(tittle[j]);
				if (str == null)
					str = "";
				holder.tv[j].setText(str);
				holder.layout.setBackgroundResource(R.color.record_content);
			}
		}

		return convertView;

	}

	class Holder {
		TextView[] tv;
		LinearLayout layout;
	}

}
