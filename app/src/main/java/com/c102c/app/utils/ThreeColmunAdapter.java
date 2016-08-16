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

public class ThreeColmunAdapter extends BaseAdapter {

	private static final int LENGTH = 3;
	private List<String[]> mData;
	private Context mContext;
	private Holder holder;

	public ThreeColmunAdapter(Context context, List<String[]> data) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String[] line = mData.get(position);
		if (convertView == null) {
			holder = new Holder();
			holder.dataET = new TextView[LENGTH];
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater
					.inflate(R.layout.user_list_item_layout, null);
			holder.dataET[0] = (TextView) convertView.findViewById(R.id.item_1);
			holder.dataET[1] = (TextView) convertView.findViewById(R.id.item_2);
			holder.dataET[2] = (TextView) convertView.findViewById(R.id.item_3);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.user_list_item_layout);
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

		/*
		 * if (position == 0) {
		 * holder.layout.setBackgroundResource(R.color.DeepSkyBlue);
		 * holder.setTextColor(R.color.GhostWhite); } else {
		 * holder.layout.setBackgroundResource(R.drawable.button_selector);
		 * holder.setTextColor(R.color.LightSkyBlue); }
		 */
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
