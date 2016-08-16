package com.c102c.app.utils;

import java.util.List;

import com.c102c.finalJKYTJ.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.LinearLayout;

public class FourColmunAdapter extends BaseAdapter {

	private static final int LENGTH = 4;
	private List<String[]> mData;
	private Context mContext;
	private Holder holder;

	public FourColmunAdapter(Context context, List<String[]> data) {
		// TODO Auto-generated constructor stub

		mContext = context;
		mData = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub

		return position;
	}

	public void deleteItemId(int position) {
		mData.remove(position);
	}

	public void addItem(String[] item) {
		mData.add(item);
	}

	public List<String[]> getListItem() {
		return mData;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		String[] line = mData.get(position);
		if (convertView == null) {
			holder = new Holder();
			holder.dataET = new TextView[LENGTH];
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater
					.inflate(R.layout.list_item_four_layout, null);
			holder.dataET[0] = (TextView) convertView.findViewById(R.id.item_1);
			holder.dataET[1] = (TextView) convertView.findViewById(R.id.item_2);
			holder.dataET[2] = (TextView) convertView.findViewById(R.id.item_3);
			holder.dataET[3] = (TextView) convertView.findViewById(R.id.item_4);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.list_item_four_layout);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		for (int i = 0; i < LENGTH; i++) {
			String item;
			if (line[i].length() > 200) {
				item = line[i].substring(0, 200) + "...";
			} else {
				item = line[i];
			}
			holder.dataET[i].setText(item);
		}

		return convertView;
	}

	public class Holder {
		public TextView[] dataET;
		public LinearLayout layout;

		private void setTextColor(int color) {
			for (int i = 0; i < dataET.length; i++) {
				dataET[i].setTextColor(color);
			}
		}
	}
}