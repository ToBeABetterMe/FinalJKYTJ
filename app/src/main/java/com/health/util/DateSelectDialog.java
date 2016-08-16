package com.health.util;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.c102c.finalJKYTJ.R;

/***
 * 日期选择对话框
 * 
 * @author jiqunpeng
 * 
 *         创建时间：2014-4-9 上午8:56:34
 */
public class DateSelectDialog extends AlertDialog.Builder {
	private DialogTask dialogTask;
	private DatePicker datePicker;

	public DateSelectDialog(Context context) {
		super(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_date_selector, null);
		datePicker = (DatePicker) view.findViewById(R.id.date_picker);
		this.setView(view);
	}

	public DateSelectDialog(Context context, DialogTask dialogTask,
			String posNote, String neuNote, String negNote) {
		this(context);
		this.dialogTask = dialogTask;
		this.setTitle("日期选择");
		this.setPositiveButton(posNote, positivelistener);
		this.setNeutralButton(neuNote, neutrallistener);
		this.setNegativeButton(negNote, null);
	}

	public DateSelectDialog(Context conext, DialogTask dialogTask) {
		this(conext, dialogTask, "保存", "清除", "取消");
	}

	private OnClickListener positivelistener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			int day = datePicker.getDayOfMonth();
			int month = datePicker.getMonth() + 1;
			String month2;
			if	(month<10) {
				month2="0"+month;
			}
			else month2=String.valueOf(month);
			int year = datePicker.getYear();
			String day2;
			if(day<10){
				day2 = "0"+day;
			}
			else day2 = String.valueOf(day);
			String date2 = year+"-"+month2+"-"+day2;
//			String date = String.format(Locale.CHINA, "%4d-%2d-%2d", year,
//					month2, day2);
			dialogTask.process(date2);
		}
	};
	private OnClickListener neutrallistener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialogTask.process("");
		}
	};

	/*
	 * 对话框确认时选择的任务
	 */
	public interface DialogTask {
		public void process(String date);
	}

}
