package com.c102c.app.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.c102c.finalJKYTJ.R;

public class CustomDialog {
	private static EditText editText_1;
	private static EditText editText_2;
	private static EditText editText_3;
	private static EditText editText_4;
	private static View layout;

	public static FourColmunAdapter YYSM_Add(final Context context,
			final FourColmunAdapter adapter) {
		layout = LayoutInflater.from(context).inflate(R.layout.dialog_layout,
				null);
		editText_1 = (EditText) layout.findViewById(R.id.edit_1);
		editText_2 = (EditText) layout.findViewById(R.id.edit_2);
		editText_3 = (EditText) layout.findViewById(R.id.edit_3);
		editText_4 = (EditText) layout.findViewById(R.id.edit_4);
		new AlertDialog.Builder(context).setTitle("添加").setView(layout)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						editText_1 = (EditText) layout
								.findViewById(R.id.edit_1);
						editText_2 = (EditText) layout
								.findViewById(R.id.edit_2);
						editText_3 = (EditText) layout
								.findViewById(R.id.edit_3);
						editText_4 = (EditText) layout
								.findViewById(R.id.edit_4);
						String[] item = new String[] {
								editText_1.getText().toString(),
								editText_2.getText().toString(),
								editText_3.getText().toString(),
								editText_4.getText().toString() };
						adapter.addItem(item);
					}
				}).setNegativeButton("取消", null).show();
		return adapter;
	}

	public static FourColmunAdapter YYSM_Edit_Delete(final Context context,
			final FourColmunAdapter adapter, final int i) {
		layout = LayoutInflater.from(context).inflate(R.layout.dialog_layout,
				null);
		editText_1 = (EditText) layout.findViewById(R.id.edit_1);
		editText_2 = (EditText) layout.findViewById(R.id.edit_2);
		editText_3 = (EditText) layout.findViewById(R.id.edit_3);
		editText_4 = (EditText) layout.findViewById(R.id.edit_4);
		editText_1.setText(((String[]) adapter.getItem(i))[0]);
		editText_2.setText(((String[]) adapter.getItem(i))[1]);
		editText_3.setText(((String[]) adapter.getItem(i))[2]);
		editText_4.setText(((String[]) adapter.getItem(i))[3]);

		new AlertDialog.Builder(context)
				.setTitle("编辑")
				.setView(layout)
				.setPositiveButton("取消", null)
				.setNeutralButton("删除", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						new AlertDialog.Builder(context)
								.setTitle("删除")
								.setMessage("确认删除？")
								.setPositiveButton("删除",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												adapter.deleteItemId(i);

											}
										}).setNegativeButton("取消", null).show();
					}
				})
				.setNegativeButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						((String[]) adapter.getItem(i))[0] = editText_1
								.getText().toString();
						((String[]) adapter.getItem(i))[1] = editText_2
								.getText().toString();
						((String[]) adapter.getItem(i))[2] = editText_3
								.getText().toString();
						((String[]) adapter.getItem(i))[3] = editText_4
								.getText().toString();

					}
				}).show();
		return adapter;
	}
}
