package cn.blunce.app.updateutils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import cn.blunce.app.updateutils.UpdateManager.UpdateCallback;

import com.c102c.finalJKYTJ.R;

public class MyUpdateCallBack implements UpdateCallback{
	private ProgressDialog updateProgressDialog;
	private UpdateManager updateManager;
	private Context context;
	
	public MyUpdateCallBack(UpdateManager manager, Context context) {
		updateManager = manager;
		this.context = context;
	}
	
	public void downloadProgressChanged(int progress) {
		if (updateProgressDialog != null
				&& updateProgressDialog.isShowing()) {
			updateProgressDialog.setProgress(progress);
		}

	}

	public void downloadCompleted(Boolean sucess, CharSequence errorMsg) {
		if (updateProgressDialog != null
				&& updateProgressDialog.isShowing()) {
			updateProgressDialog.dismiss();
		}
		if (sucess) {
			updateManager.update();
		} else {
			DialogHelper.Confirm(context,
					R.string.dialog_error_title,
					R.string.dialog_downfailed_msg, R.string.dialog_retry,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int which) {
							updateManager.downloadPackage();

						}
					}, R.string.dialog_downfailed_btnnext, null);
		}
	}

	public void downloadCanceled() {
		// TODO Auto-generated method stub

	}

	public void checkUpdateCompleted(Boolean hasUpdate,
			CharSequence updateInfo) {
		if (hasUpdate) {
			DialogHelper.Confirm(
					context,
					context.getText(R.string.dialog_update_title),
					context.getText(R.string.dialog_update_msg).toString()
							+ updateInfo
							+ context.getText(R.string.dialog_update_msg2)
									.toString(),
									context.getText(R.string.dialog_update_btnupdate),
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int which) {
							updateProgressDialog = new ProgressDialog(
									context);
							updateProgressDialog
									.setMessage(context.getText(R.string.dialog_downloading_msg));
							updateProgressDialog.setIndeterminate(false);
							updateProgressDialog
									.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							updateProgressDialog.setMax(100);
							updateProgressDialog.setProgress(0);
							updateProgressDialog.show();

							updateManager.downloadPackage();
						}
					}, context.getText(R.string.dialog_update_btnnext), null);
		}

	}
	
}
