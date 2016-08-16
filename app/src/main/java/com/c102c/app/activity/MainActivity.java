package com.c102c.app.activity;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;
import cn.blunce.app.updateutils.MyUpdateCallBack;
import cn.blunce.app.updateutils.UpdateManager;

import com.c102c.app.administrative_manager.Administrative_manager;
import com.c102c.app.base.AppDB;
import com.c102c.app.common.AppManager;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.health_record_activity.YWGLActivity;
import com.c102c.app.mbgy_activity.MBGYActivity;
import com.c102c.app.model.RequestMessage;
import com.c102c.app.resource_mangage.DoctorMark;
import com.c102c.app.staff_training.Staff_Training;
import com.c102c.app.system_activity.SystemActivity;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.WebTool;
import com.c102c.finalJKYTJ.R;
import com.health.util.T;

public class MainActivity extends BaseActivity {

	private UpdateManager updateManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);

		initViews();

		updateManager = new UpdateManager(MainActivity.this, null);
		updateManager.setCallback(new MyUpdateCallBack(updateManager, this));
		updateManager.checkUpdate();

	}

	/** 初始化view */
	@Override
	protected void initViews() {
		setContentView(R.layout.main_ui);
		new Thread() {

		};
	}

	public void jumpTo(View view) {
		Intent intent = null;
		switch (view.getId()) {
		// 业务管理
		case R.id.main_btn_ywgl:
			intent = new Intent(MainActivity.this, JKGLActivity.class);
			startActivity(intent);
	//		intent = new Intent(MainActivity.this, YWGLActivity.class);
	//		startActivity(intent);
			break;
		// 慢病干预
		case R.id.main_btn_mbgy:
			intent = new Intent(MainActivity.this, MBGYActivity.class);
			startActivity(intent);
			break;
		// 资源管理
		case R.id.main_btn_zygl:
			intent = new Intent(MainActivity.this, DoctorMark.class);
			startActivity(intent);
			break;
		// 系统维护
		case R.id.main_btn_xtwh:
			intent = new Intent(MainActivity.this, UserSelectActivity.class);
			startActivity(intent);
	//		intent = new Intent(MainActivity.this, SystemActivity.class);
	//		startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 结束Activity从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void setAllText() {
	}

	public void uploadAllOfflineData() {
		if (!WebTool.isNetConnected(MainActivity.this)) {
			T.showShort(MainActivity.this, "没有网络，请检查连接。");
			return;
		}
		List<RequestMessage> messages = AppDB
				.getAllRequestMessagesListToUpload();
		if (messages.size() > 0) {
			// 自动上传全部数据
			ProgressDialog MyDialog = ProgressDialog.show(MainActivity.this,
					"提示", "数据正在上传，请等待...", true);
			MyDialog.show();
			// 执行上传数据的操作。
			for (int i = 0; i < messages.size(); i++) {
				RequestMessage message = messages.get(i);
				String primaryKey = Tools.getPrimaryKey(message.getUuid());
				String xml = message.getXml();
				String uuid = message.getUuid();
				Fetch_by_li.communicate(uuid, MainActivity.this, handler, xml,
						true);
			}
			MyDialog.dismiss();
		}
	}

	public void deleteAllUploadedData() {
		List<RequestMessage> alloks = AppDB.getAllRequestMessagesListOK();
		for (int i = 0; i < alloks.size(); i++) {
			AppDB.DeleteDataByPrimaryKey(alloks.get(i).getUuid());
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Basesence.commufail:
				if (msg.obj == null)
					Toast.makeText(MainActivity.this, "接口调用失败",
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(MainActivity.this, "接口调用失败" + msg.obj,
							Toast.LENGTH_SHORT).show();
				break;
			case Basesence.commusucc:
				if (msg.obj != null)
					Toast.makeText(MainActivity.this, "成功" + msg.obj,
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT)
							.show();
			default:
				break;
			}
		}
	};

}
